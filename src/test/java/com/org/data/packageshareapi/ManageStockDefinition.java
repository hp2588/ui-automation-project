package com.org.data.packageshareapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageStockDefinition {
	String stockDefinitionKey;
	
	@Test
    public void createStockDefinition() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/packagesharefeature/stock_definition.feature", args, true);

        for(Entry<String, Object> entry : result.entrySet()) {
            if("stockDefinitionKey".equals(entry.getKey())) {
            	stockDefinitionKey = (String) entry.getValue();
            }
        }
        
        TestDataPropertyReaderAndWriter.setProperty("StockDefinitionKey", stockDefinitionKey);
    }
}