package com.org.data.packageshareapi;

import static com.org.automation.utils.YamlReader.getData;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageCostAmount {
	String printLabelKey;
	
	@Test
    public void createCostAmount() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("AdminUserOpaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKey"));
        args.put("formularyKey", TestDataPropertyReaderAndWriter.getProperty("FormularyKey"));
        args.put("vendorKey", TestDataPropertyReaderAndWriter.getProperty("VendorKey"));
        args.put("tenantKey", getData("IDM.tenantKey"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/manage_cost_amount.feature", args, true);

//        for (Entry<String, Object> entry : result.entrySet()) {
//            if("printLabelKey".equals(entry.getKey())) {
//            	printLabelKey = (String) entry.getValue();
//            	TestDataPropertyReaderAndWriter.setProperty("PrintLabelKey", printLabelKey);
//            }
//        }

        
        //Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_cost_amount.feature", args, true);
    }
}