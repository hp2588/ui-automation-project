package com.org.data.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageRoutingRule {
	String routingRuleKey;
	String routingRuleName;
	
	@Test
    public void createRoutingRule() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKey"));
        args.put("scheduleKey", TestDataPropertyReaderAndWriter.getProperty("ScheduleKey"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_routing_rule.feature", args, true);

        for(Entry<String, Object> entry : result.entrySet()) {
            if("routingRuleKey".equals(entry.getKey())) {
            	routingRuleKey = (String) entry.getValue();
            }
            else if("routingRuleName".equals(entry.getKey())) {
            	routingRuleName = (String) entry.getValue();
            }
        }
        TestDataPropertyReaderAndWriter.setProperty("RoutingRuleKey", routingRuleKey);
        TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName", routingRuleName);
    }
}