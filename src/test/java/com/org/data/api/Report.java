package com.org.data.api;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

/**
 *
 * @author Ashis.Raj
 */
@KarateOptions(features = "classpath:com/bd/test/features", tags = "~@ignore") 
public class Report {
    
	@Test
    public void run() {
		Results results = Runner.parallel(getClass(), 1);
        generateReport(results.getReportDir());
        //assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
    }
    
    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        final List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "BD-API Automation: Test Results");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}