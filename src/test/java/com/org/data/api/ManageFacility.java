package com.org.data.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageFacility {
	String facilityKey;
	String facilityName;
	String facilityCode;
	
	@Test
    public void createFacility() throws InterruptedException {
        Map<String, Object> args = new HashMap<String, Object>();
        facilityCode = "Code"+System.currentTimeMillis();
        args.put("facilityCode", facilityCode);

        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("externalSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemKey"));
        args.put("facilityCode", facilityCode);
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_facility.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("facilityKey".equals(entry.getKey())) {
            	facilityKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("FacilityKey", facilityKey);
            }
            else if("facilityName".equals(entry.getKey())) {
            	facilityName = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("FacilityName", facilityName);
            }
        }
        
        TestDataPropertyReaderAndWriter.setProperty("FacilityCode", facilityCode);
    	Thread.sleep(5000);
    }
}