package com.org.data.packageshareapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageISAProviding {
	String isaKey;
	String isaName;
	String ShortISAName;
	
	@Test
    public void createISA() {
        Map<String, Object> args = new HashMap<String, Object>();
        ShortISAName = "SName" + System.currentTimeMillis();
        args.put("shortDescriptionText", ShortISAName);
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("AdminUserOpaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKeyProviding"));
        args.put("logisticsLabelPrinterKey", TestDataPropertyReaderAndWriter.getProperty("PrinterKeyProviding"));
        args.put("workstationComputerKey", TestDataPropertyReaderAndWriter.getProperty("ComputerkeyProviding"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/manage_isa_providing.feature", args, true);

        for(Entry<String, Object> entry : result.entrySet()) {
            if("isaKey".equals(entry.getKey())) {
            	isaKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("IsaKeyProv", isaKey);
            }
            else if("shortDescriptionText".equals(entry.getKey())) {
            	isaName = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("ISAName1", isaName);
            	TestDataPropertyReaderAndWriter.setProperty("ShortName1", isaName);
            }
        }        
    }
}