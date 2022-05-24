package com.org.data.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageLocationAssignment {
	String storageSpaceKey;
	
	@Test(priority=1)
    public void getLayOut() throws InterruptedException {
		Thread.sleep(10000);
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("AdminUserOpaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKey"));
        args.put("isaKey", TestDataPropertyReaderAndWriter.getProperty("IsaKeyProv"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_loc_assign_getlayout.feature", args, true);

        for(Entry<String, Object> entry : result.entrySet()) {
            if("storageSpaceKey".equals(entry.getKey())) {
            	storageSpaceKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("StorageSpaceKey", storageSpaceKey);
            }
        }
    }
	
	@Test(priority=2)
    public void assignFormularyLocation() throws InterruptedException {
	    Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("AdminUserOpaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKey"));
        args.put("isaKey", TestDataPropertyReaderAndWriter.getProperty("IsaKey"));
        args.put("storageSpaceKey", TestDataPropertyReaderAndWriter.getProperty("StorageSpaceKey"));
        args.put("itemId", TestDataPropertyReaderAndWriter.getProperty("ItemId"));
        args.put("formularyKey", TestDataPropertyReaderAndWriter.getProperty("FormularyKey"));
        
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_loc_assign_assign_formulary_location.feature", args, true);

        for(Entry<String, Object> entry : result.entrySet()) {
            if("storageSpaceKey".equals(entry.getKey())) {
            	storageSpaceKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("StorageSpaceKey", storageSpaceKey);
            }
        }        
    }
	
	@Test(priority=3)
    public void getStorageDetails() throws InterruptedException {
		Thread.sleep(10000);
		Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("AdminUserOpaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKey"));
        args.put("isaKey", TestDataPropertyReaderAndWriter.getProperty("IsaKey"));
        args.put("formularyKey", TestDataPropertyReaderAndWriter.getProperty("FormularyKey"));
        
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_loc_get_srorage_details.feature", args, true);

        for(Entry<String, Object> entry : result.entrySet()) {
            if("storageSpaceInventoryKey".equals(entry.getKey())) {
            	TestDataPropertyReaderAndWriter.setProperty("StorageSpaceInventoryKey", (String) entry.getValue());
            }
            if("storageSpaceItemKey".equals(entry.getKey())) {
            	TestDataPropertyReaderAndWriter.setProperty("StorageSpaceItemKey", (String) entry.getValue());
            }
            if("location".equals(entry.getKey())) {
            	TestDataPropertyReaderAndWriter.setProperty("ItemLocation", (String) entry.getValue());
            }
            if("medItemName".equals(entry.getKey())) {
            	TestDataPropertyReaderAndWriter.setProperty("MedItemName", (String) entry.getValue());
            }
        }
    }
	
	@Test(priority=4)
    public void updateStorageDetails() throws InterruptedException {
		Thread.sleep(10000);
		Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("AdminUserOpaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKey"));
        args.put("isaKey", TestDataPropertyReaderAndWriter.getProperty("IsaKey"));
        args.put("formularyKey", TestDataPropertyReaderAndWriter.getProperty("FormularyKey"));
        args.put("storageSpaceKey", TestDataPropertyReaderAndWriter.getProperty("StorageSpaceKey"));
        args.put("storageSpaceItemKey", TestDataPropertyReaderAndWriter.getProperty("StorageSpaceItemKey"));
        args.put("storageSpaceInventoryKey", TestDataPropertyReaderAndWriter.getProperty("StorageSpaceInventoryKey"));
        args.put("itemLocation", TestDataPropertyReaderAndWriter.getProperty("ItemLocation"));
        
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_loc_assign_update_storage_details.feature", args, true);

        for(Entry<String, Object> entry : result.entrySet()) {
            if("storageSpaceKey".equals(entry.getKey())) {
            	storageSpaceKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("StorageSpaceKey", storageSpaceKey);
            }
        }
    }

}