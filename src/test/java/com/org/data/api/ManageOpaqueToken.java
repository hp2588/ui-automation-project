package com.org.data.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageOpaqueToken {;
	
	@Test
    public void getCode() {
        Map<String, Object> args = new HashMap<String, Object>();
        
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/opaque_token2.feature", args, true);
    }
}