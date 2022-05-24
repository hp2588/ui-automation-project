package com.org.data.packageshareapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageDispenseUnit {
	String dispenseUnitKey;
	long dispenseUnitCode;
	String dispenseUnitDesc;
	
	@Test
    public void createDispenseUnit() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("externalSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemKey"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/manage_dispense_unit.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("dispenseUnitKey".equals(entry.getKey())) {
            	dispenseUnitKey = (String) entry.getValue();
            }
            else if("dispenseUnitCode".equals(entry.getKey())) {
            	dispenseUnitCode = (long) entry.getValue();
            }
            else if("dispenseUnitDesc".equals(entry.getKey())) {
            	dispenseUnitDesc = (String) entry.getValue();
            }
        }
        
        TestDataPropertyReaderAndWriter.setProperty("DispenseUnitKey", dispenseUnitKey);
        TestDataPropertyReaderAndWriter.setProperty("DispenseUnitCode", Long.toString(dispenseUnitCode));
        TestDataPropertyReaderAndWriter.setProperty("DispenseUnitDesc", dispenseUnitDesc);
    }
}