package com.org.automation.getpageobjects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class reads the PageObjectRepository text files. Uses buffer reader.
 */
public class ObjectFileReader {

	private static String fileSeperator = System.getProperty("file.separator");
	static String filepath = System.getProperty("user.dir") + fileSeperator + "src" + fileSeperator + "resources"
			+ fileSeperator + "PageObjectRepository" + fileSeperator;

	public static String[] getELementFromFile(String pageName, String elementName) {
		BufferedReader br = null;
		String returnElement = "";
		try {
			br = new BufferedReader(new FileReader(filepath + pageName + ".spec"));
			String line = br.readLine();

			while (line != null) {
				if (line.split(":", 3)[0].equalsIgnoreCase(elementName)) {
					returnElement = line;
					break;
				}
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return returnElement.split(":", 3);
		// TODO: Handle Arrayoutofbounds and Filenotfound exceptions gracefully.
	}

	public static String getPageTitleFromFile(String pageName) {
		BufferedReader br = null;
		String returnElement = "";
		try {
			br = new BufferedReader(new FileReader(filepath + pageName + ".spec"));
			String line = br.readLine();

			while (line != null) {
				if (line.split(":", 3)[0].equalsIgnoreCase("Page Title")
						|| line.split(":", 3)[0].equalsIgnoreCase("title")) {
					returnElement = line;
					break;
				}
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Expected Title" + returnElement.split(":", 3)[1].trim());
		return returnElement.split(":", 3)[1].trim();
	}

}
