package com.org.data.packageshareapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageComputerProviding {
	String computerKey;
	String computerName;
	String ipAddress;
	
	@Test
    public void createComputer() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKeyProviding"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/manage_computer_providing.feature", args, true);

        for(Entry<String, Object> entry : result.entrySet()) {
            if("computerKey".equals(entry.getKey())) {
            	computerKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("ComputerkeyProviding", computerKey);
            }
            else if("computerName".equals(entry.getKey())) {
            	computerName = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("ComputerNameProviding", computerName);
            }
            else if("ipAddress".equals(entry.getKey())) {
            	ipAddress = (String) entry.getValue();
                	TestDataPropertyReaderAndWriter.setProperty("IPAddress1", ipAddress);	
            }
        }
    }
}