package com.org.data.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;



public class ManageFormulary extends OpaqueAccessToken {
	Map<String, Object> adminUserOpaqueTokenMap;
	String adminUserOpaqueToken;
	String formularyKey;
	String facilityName;
	String itemId;
	
	/*
	 * @BeforeClass public void generateAdminUserOpaqueToken() { Map<String, Object>
	 * args = new HashMap<String, Object>(); args.put("for_user", "admin");
	 * adminUserOpaqueTokenMap =
	 * Runner.runFeature("classpath:com/bd/data/api/feature/opaque_token.feature",
	 * args, true);
	 * 
	 * for (Entry<String, Object> entry : adminUserOpaqueTokenMap.entrySet()) {
	 * if("opaqueToken".equals(entry.getKey())) { adminUserOpaqueToken = (String)
	 * entry.getValue();
	 * TestDataPropertyReaderAndWriter.setProperty("AdminUserOpaqueToken",
	 * adminUserOpaqueToken); } } }
	 */

	
	@Test(priority=1)
    public void createFormulary() throws InterruptedException {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("adminUserOpaqueToken", TestDataPropertyReaderAndWriter.getProperty("AdminUserOpaqueToken"));
		args.put("dispensingFormKey", TestDataPropertyReaderAndWriter.getProperty("DosageFormKey"));
        args.put("dispensingUnitKey", TestDataPropertyReaderAndWriter.getProperty("DispenseUnitKey"));
        args.put("medicationClassKey", TestDataPropertyReaderAndWriter.getProperty("MedicationClassKey").replaceAll("\"", ""));
        args.put("externalSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemKey"));
        
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_formulary.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("formularyKey".equals(entry.getKey())) {
            	formularyKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("FormularyKey", formularyKey);
            }
            if("itemId".equals(entry.getKey())) {
            	itemId = Long.toString((long) entry.getValue());
            	TestDataPropertyReaderAndWriter.setProperty("ItemId", itemId);
            }
        }
        
        Thread.sleep(5000);
    }
	
	@Test(priority=2)
    public void createfacilityFormulary() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("adminUserOpaqueToken", TestDataPropertyReaderAndWriter.getProperty("AdminUserOpaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKey"));
        args.put("formularyKey", TestDataPropertyReaderAndWriter.getProperty("FormularyKey"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_facility_formulary.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("formularyKey".equals(entry.getKey())) {
            	formularyKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("FormularyKey", formularyKey);
            }
        }
    }
}