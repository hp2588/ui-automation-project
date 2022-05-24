/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.automation.utils;

import static com.org.automation.utils.ConfigPropertyReader.getProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

import org.testng.Reporter;
import org.yaml.snakeyaml.Yaml;

import com.org.automation.getpageobjects.Tiers;

@SuppressWarnings("unchecked")
public class YamlReader {
	public static String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "resources" + File.separator + "testdata" + File.separator;
	public static String yamlFilePath = "";
	public static String newFilePath = "";
	public static String commonFilePath = "";

	public static String setYamlFilePath() {
		commonFilePath = filePath + "Common_TestData.yml";
		String tier = "";
		try {
			if (System.getProperty("tier").contains("defaultTier") || System.getProperty("tier").isEmpty())
				tier = Tiers.valueOf(getProperty("Config.properties", "tier")).toString();
			else {
				tier = System.getProperty("tier");
			}
		} catch (NullPointerException e) {
			tier = Tiers.valueOf(getProperty("Config.properties", "tier")).toString();

		}
		if (tier.equalsIgnoreCase("Stag") || tier.equalsIgnoreCase("STAG") || tier.equalsIgnoreCase("stag")) {
			yamlFilePath = filePath + "Stag_TestData.yml";
		} else if (tier.equalsIgnoreCase("aut") || tier.equalsIgnoreCase("AUT")
				|| tier.equalsIgnoreCase("Automation")) {
			yamlFilePath = filePath + "Aut_TestData.yml";
		} else if (tier.equalsIgnoreCase("bdqa") || tier.equalsIgnoreCase("BDQA")) {
			yamlFilePath = filePath + "BDQA_TestData.yml";
		} else if (tier.equalsIgnoreCase("UAT")) {
			yamlFilePath = filePath + "UAT_TestData.yml";
		} else if (tier.equalsIgnoreCase("UATB")) {
			yamlFilePath = filePath + "UATB_TestData.yml";
		} else if (tier.equalsIgnoreCase("UATA")) {
			yamlFilePath = filePath + "UATA_TestData.yml";
		} else if (tier.equalsIgnoreCase("demo") || tier.equalsIgnoreCase("DEMO") || tier.equalsIgnoreCase("Demo")) {
			yamlFilePath = filePath + "Demo_TestData.yml";
		} else if (tier.equalsIgnoreCase("dev") || tier.equalsIgnoreCase("DEV") || tier.equalsIgnoreCase("Dev")) {
			yamlFilePath = filePath + "Dev_TestData.yml";
		} else if (tier.equalsIgnoreCase("QA") || tier.equalsIgnoreCase("qa") || tier.equalsIgnoreCase("Qa")) {
			yamlFilePath = filePath + "QA_TestData.yml";
		} else if (tier.equalsIgnoreCase("PreProd") || tier.equalsIgnoreCase("PREPROD")
				|| tier.equalsIgnoreCase("preprod")) {
			yamlFilePath = filePath + "PREPROD_TestData.yml";
		} else if (tier.equalsIgnoreCase("Prod") || tier.equalsIgnoreCase("PROD")
				|| tier.equalsIgnoreCase("Production")) {
			yamlFilePath = filePath + "PROD_TestData.yml";
		} else if (tier.equalsIgnoreCase("Perf") || tier.equalsIgnoreCase("perf")
				|| tier.equalsIgnoreCase("Automation")) {
			yamlFilePath = filePath + "PERF_TestData.yml";
		} else {
			Reporter.log("YOU HAVE PROVIDED WRONG TIER IN CONFIG!!! using dev test data", true);
		}

		return yamlFilePath;
	}

	public static String getYamlValue(String token) {
		try {
			return getValue(token);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public static String getData(String token) {
		return getYamlValue(token);
	}

	public static Map<String, Object> getYamlValues(String token) {

		Reader doc = null;
		Yaml yaml;
		Map<String, Object> object;
		try {
			try {
				doc = new FileReader(commonFilePath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			yaml = new Yaml();
			object = (Map<String, Object>) yaml.load(doc);
			return parseMap(object, token + ".");
		} catch (NullPointerException e) {
			try {
				doc = new FileReader(yamlFilePath);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			yaml = new Yaml();
			object = (Map<String, Object>) yaml.load(doc);
			return parseMap(object, token + ".");
		}
		/*
		 * try { doc = new FileReader(yamlFilePath); } catch
		 * (FileNotFoundException ex) {
		 * System.out.println("File not valid or missing!!!");
		 * ex.printStackTrace(); return null; } Yaml yaml = new Yaml(); // TODO:
		 * check the type casting of object into the Map and create // instance
		 * in one place Map<String, Object> object = (Map<String, Object>)
		 * yaml.load(doc);
		 */
		// return parseMap(object, token + ".");
		/*
		 * Map<String, Object> object = (Map<String, Object>) yaml.load(doc);
		 * return parseMap(object, token + ".");
		 */

	}

	private static String getValue(String token) throws FileNotFoundException {
		Reader doc;
		Yaml yaml;
		Map<String, Object> object;
		try {
			doc = new FileReader(commonFilePath);
			yaml = new Yaml();
			object = (Map<String, Object>) yaml.load(doc);
			return getMapValue(object, token);
		} catch (NullPointerException e) {
			doc = new FileReader(yamlFilePath);
			yaml = new Yaml();
			object = (Map<String, Object>) yaml.load(doc);
			return getMapValue(object, token);
		}
	}

	public static String getMapValue(Map<String, Object> object, String token) {
		// TODO: check for proper yaml token string based on presence of '.'
		String[] st = token.split("\\.");
		return parseMap(object, token).get(st[st.length - 1]).toString().trim();
	}

	private static Map<String, Object> parseMap(Map<String, Object> object, String token) {
		if (token.contains(".")) {
			String[] st = token.split("\\.");
			object = parseMap((Map<String, Object>) object.get(st[0]), token.replace(st[0] + ".", ""));
		}
		return object;
	}

	public static int generateRandomNumber(int MinRange, int MaxRange) {
		int randomNumber = MinRange + (int) (Math.random() * ((MaxRange - MinRange) + 1));
		return randomNumber;
	}

}
