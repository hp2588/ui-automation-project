package com.org.data.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.intuit.karate.Runner;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManageSchedule {
	String scheduleKey;
	String scheduleName;
	
	@Test
    public void createSchedule() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("opaqueToken", TestDataPropertyReaderAndWriter.getProperty("opaqueToken"));
        args.put("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityKey"));
        Map<String, Object> result = Runner.runFeature("classpath:com/bd/data/api/feature/manage_schedule.feature", args, true);

        for(Entry<String, Object> entry : result.entrySet()) {
            if("scheduleKey".equals(entry.getKey())) {
            	scheduleKey = (String) entry.getValue();
            }
            else if("scheduleName".equals(entry.getKey())) {
            	scheduleName = (String) entry.getValue();
            }
        }
        TestDataPropertyReaderAndWriter.setProperty("ScheduleKey", scheduleKey);
        TestDataPropertyReaderAndWriter.setProperty("ScheduleName", scheduleName);
    }
}