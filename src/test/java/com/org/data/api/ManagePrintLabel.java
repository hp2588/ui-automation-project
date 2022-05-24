package com.org.data.api;

import static com.org.automation.utils.YamlReader.getData;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManagePrintLabel {
	String printLabelKey;
	String tenantKey;
	
	@Test
    public void createPrintLabel() {
        Map<String, Object> args = new HashMap<String, Object>();
        tenantKey=getData("IDM.tenantKey");
        TestDataPropertyReaderAndWriter.setProperty("TenantKey",tenantKey);
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKey"));
        args.put("stockDefinitionKey", TestDataPropertyReaderAndWriter.getProperty("StockDefinitionKey"));
        args.put("tenantKey", TestDataPropertyReaderAndWriter.getProperty("TenantKey"));
        
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_print_label.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("printLabelKey".equals(entry.getKey())) {
            	printLabelKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("PrintLabelKey", printLabelKey);
            }
        }
    }
}