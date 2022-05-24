package com.org.automation.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class NetworkLogger {

	private static String testDataConfigFile = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "Network.log";

	public static void printLog(WebDriver driver,String testName) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(testDataConfigFile));

		List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();

		String currentURL = driver.getCurrentUrl();

		int status = -1;

		writer.write("\n######## NETWORK LOGS ##########\n");
		writer.write("\nLogs for test :::::"); 
		writer.write(testName);
		writer.write("\n");
		for (Iterator<LogEntry> it = entries.iterator(); it.hasNext();) {
			LogEntry entry = it.next();
			writer.write("\n");
			try {
				JSONObject json = new JSONObject(entry.getMessage());

				writer.write(json.toString());

				// System.out.println(json.toString());

				JSONObject message = json.getJSONObject("message");
				String method = message.getString("method");

				if (method != null && "Network.responseReceived".equals(method)) {
					JSONObject params = message.getJSONObject("params");

					JSONObject response = params.getJSONObject("response");
					String messageUrl = response.getString("url");
					//System.out.println("Before MessageURL: " + messageUrl);
					//if (currentURL.equals(messageUrl)) {
					//	System.out.println("Message URL: " + messageUrl);
						status = response.getInt("status");
						writer.write("\n");
						writer.write(status);
						// System.out.println( "Returned response for " + messageUrl + ": " + status);

						writer.write(response.get("headers").toString());
						writer.write("\n");
						/*
						 * System.out.println( "headers: " + response.get("headers"));
						 */
					//}

				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writer.close();
		System.out.println("\nstatus code: " + status);

	}

}
