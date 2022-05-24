package com.org.data.api;

import static com.org.automation.utils.YamlReader.getData;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageProductId {
	String productId;
	String productIdKey;
	String formularyKey;

	@Test
	public void createProductId() {
		System.out.println("Starting to execute createProductId from java class..");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("AdminUserOpaqueToken"));
        args.put("formularyKey", TestDataPropertyReaderAndWriter.getProperty("FormularyKey"));
        args.put("tenantKey", getData("IDM.tenantKey"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_productid.feature", args, true);
    }
}