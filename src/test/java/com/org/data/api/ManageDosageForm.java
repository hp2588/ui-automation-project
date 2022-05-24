package com.org.data.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageDosageForm {
	String dosageFormKey;
	long dosageFormCode;
	String dosageFormDesc;
	
	@Test
    public void createDosageForm() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("externalSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemKey"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_dosage_form.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("dosageFormKey".equals(entry.getKey())) {
            	dosageFormKey = (String) entry.getValue();
            }
            else if("dosageFormCode".equals(entry.getKey())) {
            	dosageFormCode = (long) entry.getValue();
            }
            else if("dosageFormDesc".equals(entry.getKey())) {
            	dosageFormDesc = (String) entry.getValue();
            }
        }
        
        TestDataPropertyReaderAndWriter.setProperty("DosageFormKey", dosageFormKey);
        TestDataPropertyReaderAndWriter.setProperty("DosageFormCode", Long.toString(dosageFormCode));
        TestDataPropertyReaderAndWriter.setProperty("DosageFormDesc", dosageFormDesc);
    }
}