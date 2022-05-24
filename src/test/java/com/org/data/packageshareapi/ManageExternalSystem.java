package com.org.data.packageshareapi;

import static com.org.automation.utils.ConfigPropertyReader.getProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

//import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageExternalSystem extends OpaqueAccessToken_SupportUser{
	Map<String, Object> opaqueTokenMap;
	String opaqueToken;
	String externalSystemKey;
	String externalSystemName;
	
	/*
	 * @BeforeClass public void generateSupportUserOpaqueToken() { Map<String,
	 * Object> args = new HashMap<String, Object>(); args.put("for_user",
	 * "support"); opaqueTokenMap =
	 * Runner.runFeature("classpath:com/bd/data/api/feature/opaque_token.feature",
	 * args, true);
	 * 
	 * for (Entry<String, Object> entry : opaqueTokenMap.entrySet()) {
	 * if("opaqueToken".equals(entry.getKey())) { opaqueToken = (String)
	 * entry.getValue(); TestDataPropertyReaderAndWriter.setProperty("opaqueToken",
	 * opaqueToken); } } }
	 */

	@Test
    public void createExternalSystem() throws InterruptedException {		
		Map<String, String> config = new HashMap<String, String>();
		if (System.getProperty("tier").isEmpty())
			config.put("karate.env", getProperty("./Config.properties", "tier").trim());
		else
			config.put("karate.env", System.getProperty("tier").trim());
	
		System.setProperty("karate.env", config.get("karate.env") );
		 Map<String, Object> args = new HashMap<String, Object>();
    //    args.put("opaqueToken", opaqueToken);
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/manage_external_system.feature", args, true);

        for (Entry<String, Object> entry : result.entrySet()) {
            if("externalSystemKey".equals(entry.getKey())) {
            	externalSystemKey = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("ExternalSystemKey", externalSystemKey);
            }
            else if("externalSystemName".equals(entry.getKey())) {
            	externalSystemName = (String) entry.getValue();
            	TestDataPropertyReaderAndWriter.setProperty("ExternalSystemName", externalSystemName);
            }
        }

    	Thread.sleep(5000);
    } 
}