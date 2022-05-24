package com.org.data.packageshareapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageComputerReceiving {
	String computerKey;
	String computerName;
	String ipAddress;
	
	@Test
    public void createComputer() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKeyReceiving"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/manage_computer_receiving.feature", args, true);

        for(Entry<String, Object> entry : result.entrySet()) {
            if("computerKey".equals(entry.getKey())) {
            	computerKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("ComputerkeyReceiving", computerKey);
            }
            else if("computerName".equals(entry.getKey())) {
            	computerName = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("ComputerNameReceiving", computerName);
            }	
            else if("ipAddress".equals(entry.getKey())) {
            	ipAddress = (String) entry.getValue();
                	TestDataPropertyReaderAndWriter.setProperty("IPAddress2", ipAddress);	
            }
        }
    }
}