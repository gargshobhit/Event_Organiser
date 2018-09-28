package com.deloitte.service;

import com.deloitte.EventConstant;
import com.deloitte.bean.Activity;
import com.deloitte.bean.OrganiserDetails;
import com.deloitte.dao.ActivityDAO;
import com.deloitte.dao.FileActivityDAO;
import com.deloitte.exception.EventException;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gargs on 25/09/2018.
 */
public class EventServiceImpl implements EventService {

    @Override
    public List<OrganiserDetails> distributeActivitiesToOrganisers(String activityFileName) {

        List<Activity> activities = new FileActivityDAO().getEventActivities(activityFileName);

        if(activities == null || activities.size() < 1)
            throw new EventException(EventConstant.Messages.ACTIVITY_ASSIGMENT_ERROR);

        List<OrganiserDetails> orgDetails = new LinkedList<>();

        int totalActivityDuration = calculatetotalActivityDuration(activities);
        int teamsRequired = calculateTeamsRequired(totalActivityDuration);

        for(int i = 1;i <= teamsRequired;i++){
            String organiserName = "Team "+i;
            orgDetails.add(new OrganiserDetails(organiserName, EventConstant.EVENT_DURATION));
        }

        //sort all activities in desecnding order of duration
        activities.sort(Comparator.comparing(x -> x.getDuration(),Comparator.reverseOrder()));

        activities.forEach(x -> {
            OrganiserDetails od = orgDetails.stream().max(Comparator.comparingInt
                    (OrganiserDetails::getOrganiserTotalActivityDurationLeft)).get();

            checkAndAddLunchActivity(od); // check for lunch time

            if(od.getOrganiserTotalActivityDurationLeft() >= x.getDuration()) {
                x.setStartTime(EventConstant.ACTIVITY_START_TIME.plusMinutes(EventConstant.EVENT_DURATION - od
                        .getOrganiserTotalActivityDurationLeft()));
                od.setAssignedActivities(x);
                od.setOrganiserTotalActivityDurationLeft(x.getDuration());
            } else
                throw new EventException(EventConstant.Messages.ACTIVITY_ASSIGMENT_ERROR+x.getName());
        });

        addPresentationActivity(orgDetails);

        return orgDetails;
    }

    private static int calculatetotalActivityDuration(List<Activity> activities){
        return activities.stream().mapToInt(x-> x.getDuration()).sum();
    }

    private static int calculateTeamsRequired(int totalActivitiesDuration){
        if(totalActivitiesDuration%EventConstant.EVENT_DURATION !=0)
            return totalActivitiesDuration/EventConstant.EVENT_DURATION + 1;
        return totalActivitiesDuration/EventConstant.EVENT_DURATION;
    }

    private static void checkAndAddLunchActivity(OrganiserDetails od){
        Activity lunchActivity = new Activity(EventConstant.LUNCH_ACTIVITY_NAME, EventConstant.LUNCH_DURATION);
        if (EventConstant.ACTIVITY_START_TIME.plusMinutes(EventConstant.EVENT_DURATION - od
                .getOrganiserTotalActivityDurationLeft()).equals(EventConstant.LUNCH_START_TIME)){
            lunchActivity.setStartTime(EventConstant.ACTIVITY_START_TIME.plusMinutes(EventConstant.EVENT_DURATION - od
                    .getOrganiserTotalActivityDurationLeft()));
            od.setAssignedActivities(lunchActivity);
            od.setOrganiserTotalActivityDurationLeft(lunchActivity.getDuration());
        }
    }

    private static void addPresentationActivity(List<OrganiserDetails> orgDetails){
        // check if presentation can be start before End Of All Activities
        int allActivitiesCompleted = orgDetails.stream().mapToInt
                (OrganiserDetails::getOrganiserTotalActivityDurationLeft).min().getAsInt();
        LocalTime presentationCanStartAt = EventConstant.PRESENTATION_START_TIME.minusMinutes(allActivitiesCompleted);
        Activity presentation = new Activity(EventConstant.PRESENTATION,0);
        presentation.setStartTime(presentationCanStartAt);

        //add presentation activity to all Teams
        orgDetails.forEach(x -> x.setAssignedActivities(presentation));
    }
}
