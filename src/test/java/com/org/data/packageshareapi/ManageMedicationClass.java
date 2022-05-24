package com.org.data.packageshareapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageMedicationClass {
	String medicationClassKey;
	long medicationClassCode;
	String medicationClassDesc;
	
	@Test
    public void createMedicationClass() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("externalSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemKey"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/manage_medication_class.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("medicationClassKey".equals(entry.getKey())) {
            	medicationClassKey = (String) entry.getValue();
            }
            else if("medicationClassCode".equals(entry.getKey())) {
            	medicationClassCode = (long) entry.getValue();
            }
            else if("medicationClassDesc".equals(entry.getKey())) {
            	medicationClassDesc = (String) entry.getValue();
            }
        }
        
        TestDataPropertyReaderAndWriter.setProperty("MedicationClassKey", medicationClassKey);
        TestDataPropertyReaderAndWriter.setProperty("MedClassCode", Long.toString(medicationClassCode));
        TestDataPropertyReaderAndWriter.setProperty("MedicationClassDesc", medicationClassDesc);
    }
}