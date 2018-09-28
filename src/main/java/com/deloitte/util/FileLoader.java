package com.deloitte.util;

import com.deloitte.EventConstant;
import com.deloitte.bean.Activity;
import com.deloitte.exception.EventException;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by gargs on 23/09/2018.
 */
public class FileLoader {

    private List activities = null;
//    private static FileLoader fileLoader = null;

    public List<Activity> getActivities(String fileName){
        if (activities == null) {
            activities = new ArrayList();
            load(fileName);
        }

        return activities;
    }



    private List<Activity>  load(String fileName) throws EventException{
        try(BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName)))){
            String line;
            while ((line = br.readLine()) != null) {
                int duration = EventConstant.DEFAULT_DURATION_FOR_SPRINT;
                int durationLocation = line.trim().lastIndexOf(" ");
                String activityDuration = line.trim().substring(durationLocation + 1);
                String activityName = line.trim().substring(0, durationLocation);
                if (activityDuration.equalsIgnoreCase(EventConstant.SPRINT)) {
                    duration = EventConstant.DEFAULT_DURATION_FOR_SPRINT;
                    activities.add(new Activity(line.trim(), duration));
                } else if (activityDuration.trim().contains(EventConstant.ACTIVITY_TIME_IN_MIN)
                        && activityDuration.trim().length() > EventConstant.ACTIVITY_TIME_IN_MIN.length()) {
                    String findMinutes = activityDuration.substring(0, activityDuration.length() -
                            EventConstant.ACTIVITY_TIME_IN_MIN.length()).trim();
                    if (!Pattern.matches("[0-9]+", findMinutes))
                        throw new EventException(EventConstant.Messages.ACTIVITY_LOAD_ERROR + line);

                    duration = Integer.valueOf(findMinutes);
                    activities.add(new Activity(activityName, duration));
                } else
                    throw new EventException(EventConstant.Messages.ACTIVITY_LOAD_ERROR + line);
            }
        }catch(IOException io){
                io.printStackTrace();
        }
        return activities;
    }
 }
