package com.org.data.packageshareapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageStorageSpace {
	String computerKey;
	String computerName;
	
	@Test
    public void createComputer() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKey"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/manage_computer.feature", args, true);

        for(Entry<String, Object> entry : result.entrySet()) {
            if("computerKey".equals(entry.getKey())) {
            	computerKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("Computerkey", computerKey);
            }
            else if("computerName".equals(entry.getKey())) {
            	computerName = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("ComputerName", computerName);
            }
        }
    }
}