package com.org.data.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageDestination {
	String destinationKey;
	long destinationCode;
	String DestinationName;
	
	@Test
    public void createDestination() {
        Map<String, Object> args = new HashMap<String, Object>();
        DestinationName = "Name" + System.currentTimeMillis();
        args.put("descriptionText", DestinationName);
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKey"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_destination.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("destinationKey".equals(entry.getKey())) {
            	destinationKey = (String) entry.getValue();
            }
            else if("destinationCode".equals(entry.getKey())) {
            	destinationCode = (long) entry.getValue();
            }
        }
        
        TestDataPropertyReaderAndWriter.setProperty("DestinationKey", destinationKey);
        TestDataPropertyReaderAndWriter.setProperty("DestinationCode", Long.toString(destinationCode));
        TestDataPropertyReaderAndWriter.setProperty("DestinationName", DestinationName);
    }
}