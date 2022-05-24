package com.org.extentmanagers;

import java.io.File;
import java.lang.reflect.Method;

import com.org.automation.utils.ConfigPropertyReader;
import com.relevantcodes.extentreports.ExtentReports;


public class ExtentManager {

	private static ExtentReports extent;

	private static String reportFileName = "Test-Automation-Report" + ".html";
	private static String fileSeperator = System.getProperty("file.separator");
	private static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "ExtentTestReport";
	private static String reportFileLocation = reportFilepath + fileSeperator + reportFileName;

	public synchronized static ExtentReports getReporter() {
		if (extent == null)
			createInstance();
		return extent;
	}

	// Create an extent report instance
	public static ExtentReports createInstance() {
		extent = new ExtentReports(reportFileLocation, true);
		extent.loadConfig(new File(System.getProperty("user.dir") + fileSeperator + "extent-config.xml"));
		extent.addSystemInfo("Time Zone", System.getProperty("user.timezone"));
		extent.addSystemInfo("Selenium Version", "3.14.0");
		extent.addSystemInfo("Maven Version", "3.5.4");
		extent.addSystemInfo("Java Version", "1.8.0");
		extent.addSystemInfo("Browser",ConfigPropertyReader.getProperty("browser"));
		return extent;
	}

}
