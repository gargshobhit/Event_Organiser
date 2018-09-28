package com.deloitte;

import com.deloitte.dao.FileActivityDAO;
import com.deloitte.exception.EventException;
import com.deloitte.util.FileLoader;
import org.junit.Test;

/**
 * Created by gargs on 25/09/2018.
 */
public class FileLoaderTest {

    @Test
    public void loadActivitiesWithOutErros() throws EventException{
        String fileName = "activities_Test.txt";
        new FileLoader().getActivities(fileName);
    }

    @Test(expected=EventException.class)
    public void loadActivities_InvalidFormat_1() throws EventException{
        // first activity has space between time and min
        String fileName = "activities_InvalidFormat_1.txt";
        new FileLoader().getActivities(fileName);
    }

    @Test(expected=EventException.class)
    public void loadActivities_InvalidFormat_2() throws EventException{
        //4th Activity ended with neither SPRINT  nor MIN at end
        String fileName = "activities_InvalidFormat_2.txt";
        new FileLoader().getActivities(fileName);
    }

    @Test(expected=EventException.class)
    public void loadActivities_InvalidFormat_3() throws EventException{
        //2nd Activity has invalid chars at the end
        String fileName = "activities_InvalidFormat_3.txt";
        new FileLoader().getActivities(fileName);
    }
}
