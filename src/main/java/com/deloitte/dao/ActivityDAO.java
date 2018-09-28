package com.deloitte.dao;

import com.deloitte.bean.Activity;

import java.util.List;

/**
 * Created by gargs on 25/09/2018.
 */
public interface ActivityDAO {
    public List<Activity> getEventActivities(String fileName);
}
