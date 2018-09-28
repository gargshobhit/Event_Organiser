package com.deloitte.dao;

import com.deloitte.bean.Activity;
import com.deloitte.util.FileLoader;

import java.util.List;

/**
 * Created by gargs on 25/09/2018.
 */
public class FileActivityDAO implements  ActivityDAO {

    @Override
    public List<Activity> getEventActivities(String fileName) {
        return new FileLoader().getActivities(fileName);
    }
}
