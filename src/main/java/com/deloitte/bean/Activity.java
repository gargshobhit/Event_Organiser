package com.deloitte.bean;

import java.time.LocalTime;

/**
 * Created by gargs on 23/09/2018.
 */
public class Activity {
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private int duration; // duration is in minutes

    public Activity(String _name, int _duration){
        this.name = _name;
        this.duration = _duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String toString(){

        StringBuffer bf = new StringBuffer();
        bf.append(startTime);
        bf.append((this.getStartTime().getHour()<12?"am":"pm"));
        bf.append(" : ");
        bf.append(name);
        if(duration > 0) {
            bf.append(" ");
            bf.append(duration);
            bf.append("min");
        }
        return bf.toString();
    }
}
