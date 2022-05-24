package com.org.data.packageshareapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageVendor {
	String vendorKey;
	long vendorShortCode;
	String vendorDesc;
	String facilityVendorOptionKey;
	
	@Test(priority=1)
    public void createVendor() throws InterruptedException {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("vendorKey", TestDataPropertyReaderAndWriter.getProperty("VendorKey"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/manage_vendor.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("vendorKey".equals(entry.getKey())) {
            	vendorKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("VendorKey", vendorKey);
            }
            else if("vendorShortCode".equals(entry.getKey())) {
            	vendorShortCode = (long) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("VendorShortCode", Long.toString(vendorShortCode));
            }
            else if("vendorDesc".equals(entry.getKey())) {
            	vendorDesc = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("VendorDesc", vendorDesc);
            }
        }
        
        Thread.sleep(5000);
    }
	
	@Test(priority=2)
    public void createFacilityVendorOption() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("vendorKey", TestDataPropertyReaderAndWriter.getProperty("VendorKey"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKey"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_facility_vendor_option.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("facilityVendorOptionKey".equals(entry.getKey())) {
            	facilityVendorOptionKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("FacilityVendorOptionKey", facilityVendorOptionKey);
            }
        }
    }
}