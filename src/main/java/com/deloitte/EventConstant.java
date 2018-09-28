package com.deloitte;

import com.deloitte.util.FileLoader;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gargs on 25/09/2018.
 */
public class EventConstant {
    public static final LocalTime ACTIVITY_START_TIME = LocalTime.of(9,00);
    public static final LocalTime ACTIVITY_END_TIME = LocalTime.of(17,00);
    public static final LocalTime LUNCH_START_TIME = LocalTime.of(12,00);
    public static final int LUNCH_DURATION = 60; // lunch duration in minutes
    public static final LocalTime PRESENTATION_START_TIME = LocalTime.of(17,00);

    // total event duration start from 9:00 till 17:00 8hrs =>  480 mins including 1 hr lunch break
    public static final int EVENT_DURATION = (ACTIVITY_END_TIME.getHour() - ACTIVITY_START_TIME.getHour()) * 60 + ACTIVITY_END_TIME
            .getMinute() - ACTIVITY_START_TIME.getMinute();
    public static final String LUNCH_ACTIVITY_NAME = "Lunch Break";
    public static final String PRESENTATION = "Staff Motivation Presentation";
    public static final String SPRINT = "Sprint";
    public final static Integer DEFAULT_DURATION_FOR_SPRINT = 15;
    public final static String ACTIVITY_TIME_IN_MIN = "min";
    public static final String FILE_NAME = "/activities.txt";

    public static class Messages{
        public static final String ACTIVITY_LOAD_ERROR = "File not in correct format, load Error for Activity:->";
        public static final String ACTIVITY_ASSIGMENT_ERROR = "Activity Can't be assigned to any Organiser, " +
                "Activity not in correct format:-> ";
    }
}
