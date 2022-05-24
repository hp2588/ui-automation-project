package com.org.data.packageshareapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageFacilityReceiving {
	String facilityKey;
	String facilityName;
	String facilitycode;
	@Test
    public void createFacility() throws InterruptedException {
        Map<String, Object> args = new HashMap<String, Object>();
        //facilityname
        facilitycode ="Code"+System.currentTimeMillis();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("externalSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemKey"));
        args.put("facilityCode", facilitycode);
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/manage_facilityreceiving.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("facilityKey".equals(entry.getKey())) {
            	facilityKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("FacilityKeyReceiving", facilityKey);
            }
            else if("facilityName".equals(entry.getKey())) {
            	facilityName = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("FacilityNameReceiving", facilityName);
            }
        }
        
        TestDataPropertyReaderAndWriter.setProperty("FacilityCodeReceiving", facilitycode);

    	Thread.sleep(5000);
    }
}