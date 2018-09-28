package com.deloitte;

import com.deloitte.bean.OrganiserDetails;
import com.deloitte.service.EventService;
import com.deloitte.service.EventServiceImpl;

import java.util.List;

/**
 * Created by gargs on 23/09/2018.
 */
public class ArrangeEvent {

    public static void main(String args[]){

        EventService service = new EventServiceImpl();
        List<OrganiserDetails> taskDetails = service.distributeActivitiesToOrganisers(EventConstant.FILE_NAME);

        taskDetails.forEach(x -> System.out.println(x));

    }
}
