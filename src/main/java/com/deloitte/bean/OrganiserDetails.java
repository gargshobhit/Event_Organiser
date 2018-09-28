package com.deloitte.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gargs on 23/09/2018.
 */
public class OrganiserDetails {
    private String name;
    private List<Activity> assignedActivities = new ArrayList<>(); // these are the activities assiged to Organiser to perform
    private int organiserTotalActivityDurationLeft;

    public int getOrganiserTotalActivityDurationLeft() {
        return organiserTotalActivityDurationLeft;
    }

    public void setOrganiserTotalActivityDurationLeft(int activityDuration) {
        this.organiserTotalActivityDurationLeft = this.organiserTotalActivityDurationLeft - activityDuration;
    }

    public OrganiserDetails(String _name, int _organiserTotalActivityDurationLeft){
        this.name = _name;
        this.organiserTotalActivityDurationLeft = _organiserTotalActivityDurationLeft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getAssignedActivities() {
        return assignedActivities;
    }

    public void setAssignedActivities(Activity assignedActivity) {
        assignedActivities.add(assignedActivity);
    }

    public String toString(){
        StringBuffer bf = new StringBuffer();
        bf.append(name);
        bf.append(":\n");
        assignedActivities.forEach(x -> {bf.append(x);bf.append("\n");});
        return bf.toString();
    }
}
