package com.org.data.packageshareapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class GetVendorKey {

	
	String vendorKey;
	
	@Test
    public void getVendorKey() throws InterruptedException {
        Map<String, Object> args = new HashMap<String, Object>();
        
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("FacilityName", TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"));
      
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/getVendorKey.feature", args, true);

        
		/*
		 * for (Entry<String, Object> entry : result.entrySet()) {
		 * if("vendorKey".equals(entry.getKey())) { vendorKey = (String)
		 * entry.getValue();
		 * TestDataPropertyReaderAndWriter.setProperty("VendorKeyProviding", vendorKey);
		 * }
		 * 
		 * }
		 */
        
    	Thread.sleep(5000);
    }
	
}
