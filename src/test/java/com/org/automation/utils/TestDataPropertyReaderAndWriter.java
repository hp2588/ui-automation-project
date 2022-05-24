package com.org.automation.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Properties;

public class TestDataPropertyReaderAndWriter {

	// private static String testDataConfigFile = System.getProperty("user.dir") +
	// File.separator + "src" + File.separator
	// + "resources" + File.separator + "testData" + File.separator +
	// "TestData.properties";

	private static String testDataConfigFile = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "TestData.properties";

	public TestDataPropertyReaderAndWriter() {
	}

	public static void setProperty(String propFile, String propertyKey, String propertyValue) {
		try {
			Properties prop = ResourceLoader.loadProperties(propFile);
			prop.setProperty(propertyKey, propertyValue);

			FileOutputStream fos = new FileOutputStream(propFile);
			prop.store(fos, "Properties file generated from Java program");
			fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void setProperty(String propertyName, String propertyValue) {
		setProperty(testDataConfigFile, propertyName, propertyValue);
	}

	public static String getProperty(String propFile, String Property) {
		try {
			Properties prop = ResourceLoader.loadProperties(propFile);
			return prop.getProperty(Property);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String getProperty(String property) {
		return getProperty(testDataConfigFile, property);
	}

	public static String clearPropertyFile() {

		try {
			Properties prop = ResourceLoader.loadProperties(testDataConfigFile);

			Enumeration e = prop.propertyNames();

			while (e.hasMoreElements()) {

				String key = (String) e.nextElement();
				prop.remove(key);
			}
			prop.store(new FileOutputStream(testDataConfigFile), "Empty filestore");
			if (prop.isEmpty()) {
				System.out.println("TestData properties file has been cleaned");
			} else {
				System.out.println("TestData properties file could not be cleaned");
			}
			return "TestData properties file has been cleaned";
		}

		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}