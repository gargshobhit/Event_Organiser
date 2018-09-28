package com.deloitte.service;

import com.deloitte.bean.Activity;
import com.deloitte.bean.OrganiserDetails;

import java.util.List;

/**
 * Created by gargs on 25/09/2018.
 */
public interface EventService {
    public List<OrganiserDetails> distributeActivitiesToOrganisers(String fileName);
}
