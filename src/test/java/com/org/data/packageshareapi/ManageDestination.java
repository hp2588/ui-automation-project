package com.org.data.packageshareapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageDestination {
	String destinationKey;
	long destinationCode;
	
	@Test
    public void createDestination() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKeyProviding"));
        args.put("facilityCode", TestDataPropertyReaderAndWriter.getProperty("FacilityCodeReceiving"));
        args.put("destinationName", TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"));
        args.put("packageSharingFacilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKeyReceiving"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/manage_destination.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("destinationKey".equals(entry.getKey())) {
            	destinationKey = (String) entry.getValue();
            }
        }
        
        TestDataPropertyReaderAndWriter.setProperty("DestinationKey", destinationKey);
        TestDataPropertyReaderAndWriter.setProperty("DestinationName",TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"));
    }
}