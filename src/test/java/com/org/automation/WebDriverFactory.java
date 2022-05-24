/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.automation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;


public class WebDriverFactory {
	private static String browser;
	String seleniumServer;
	
	static String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "resources"	+ File.separator + "Drivers" + File.separator;
	
	
	private static DesiredCapabilities capabilities = new DesiredCapabilities();
	//public static BrowserMobProxyServer server;

	public WebDriver getDriver(Map<String, String> seleniumconfig) {
		
		seleniumServer = seleniumconfig.get("seleniumserver");

		browser = seleniumconfig.get("browser");

		if (seleniumServer.equalsIgnoreCase("local")) {
			if ((browser.equalsIgnoreCase("Edge")) || (browser.equalsIgnoreCase("IEEdge"))) {
				return getIEEdgeDriver(filePath + "msedgedriver.exe");
			} else if (browser.equalsIgnoreCase("chrome")) {
				if (System.getProperty("os.name").equals("Linux")) {
					System.out.println(System.getProperty("os.name"));
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--headless");
					options.addArguments("--disable-gpu");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-dev-shm-usage");
					options.addArguments("window-size=1366,768");
					return getChromeDriver(filePath + "chromedriver",options);
				} else if (System.getProperty("os.name").contains("Windows")) {
					ChromeOptions options = new ChromeOptions();
					//DesiredCapabilities caps = DesiredCapabilities.chrome();
					DesiredCapabilities cap = DesiredCapabilities.chrome();
					           cap.setCapability(ChromeOptions.CAPABILITY, options);
					LoggingPreferences logPrefs = new LoggingPreferences();
					logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
					//options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
					options.setCapability("goog:loggingPrefs", logPrefs);
					options.setCapability(ChromeOptions.CAPABILITY, options);

					options.setCapability("download.default_directory",System.getProperty("user.dir") + "\\OrderDetails.pdf");
					options.setCapability("download.prompt_for_download","false");
					options.setCapability("directory_upgrade","true");
					options.setCapability("plugins.plugins_disabled","Chrome PDF Viewer");
					//options.addArguments("--headless");
					options.setExperimentalOption("w3c", false);
					options.addArguments("disable-notifications");
					options.addArguments("--no-sandbox");
					options.addArguments("--test-type");
					options.addArguments("ignore-certificate-errors");
					options.addArguments("--kiosk-printing");
					
					// do not comment and commit below three lines
					
					/*
					 * options.addArguments("force-device-scale-factor=0.50");
					 * options.addArguments("high-dpi-support=0.50");
					 * options.addArguments("window-size=1280,720");
					 */
					
					return getChromeDriver(filePath + "chromedriver.exe",options);
				}
				
			} else if (browser.equalsIgnoreCase("Safari")) {
				return getSafariDriver();
			} else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer"))
					|| (browser.equalsIgnoreCase("internet explorer")) || (browser.equalsIgnoreCase("ie11"))) {
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability("ie.ensureCleanSession", true);
				capabilities.setJavascriptEnabled(true);
				capabilities.setCapability("ignoreZoomSetting", true);
				capabilities.setCapability("requireWindowFocus", false);
				// capabilities.setCapability("nativeEvents",false);
				capabilities.setCapability("unexpectedAlertBehaviour", "accept");
				capabilities.setCapability("disable-popup-blocking", true);
				capabilities.setCapability("enablePersistentHover", true);
				capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);

				return getInternetExplorerDriver(filePath + "IEDriverServer.exe");
			}
		}
		if (seleniumServer.equalsIgnoreCase("remote")) {
			return setRemoteDriver(seleniumconfig);
		}
		return new ChromeDriver();
	}

	private static WebDriver getIEEdgeDriver(String driverPath) {
		System.setProperty("webdriver.edge.driver", driverPath);
		EdgeOptions options = new EdgeOptions();
		options.setPageLoadStrategy("eager");
		return new EdgeDriver(options);
	}

	private WebDriver setRemoteDriver(Map<String, String> selConfig) {
		DesiredCapabilities caps = null;
		ChromeOptions options = null;
		InternetExplorerOptions options1 = null;
		browser = selConfig.get("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", filePath + "chromedriver.exe");
			caps = DesiredCapabilities.chrome();
			options = new ChromeOptions();
			options.addArguments("disable-infobars");
			caps.setCapability(ChromeOptions.CAPABILITY, options);
		} else if ((browser.equalsIgnoreCase("Edge")) || (browser.equalsIgnoreCase("IEEdge"))) {
			caps = DesiredCapabilities.edge();
		} else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer"))
				|| (browser.equalsIgnoreCase("internet explorer"))) {
			caps = DesiredCapabilities.internetExplorer();

		}

		String seleniumhubaddress = selConfig.get("seleniumserverhost");
		System.out.println("remote url:" + seleniumhubaddress);
		URL selserverhost = null;
		try {
			selserverhost = new URL(seleniumhubaddress);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		// cap.setJavascriptEnabled(true);
		return new RemoteWebDriver(selserverhost, caps);
	}


	@SuppressWarnings("deprecation")
	private static WebDriver getChromeDriver(String driverpath) {
		
		
		System.setProperty("webdriver.chrome.driver", driverpath);
		
		
		DesiredCapabilities d = DesiredCapabilities.chrome();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		d.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		
		
		
		 // ChromeOptions options = new ChromeOptions(); 
		  
		 /*  HashMap<String, Object>	chromePrefs = new HashMap<String, Object>();
		 * 
		 * chromePrefs.put("download.prompt_for_download", false);
		 * chromePrefs.put("credentials_enable_service", false);
		 * chromePrefs.put("profile.password_manager_enabled", false);
		 * 
		 * // disable flash and the PDF viewer
		 * chromePrefs.put("plugins.plugins_disabled", new String[] {
		 * "Adobe Flash Player", "Chrome PDF Viewer",
		 * "plugins.always_open_pdf_externally" }); // ((Object) //
		 * options).AddUserProfilePreference(
		 * "plugins.always_open_pdf_externally", // true);
		 * chromePrefs.put("profile.default_content_settings.popups", 0);
		 * chromePrefs.put("download.default_directory", downloadFilePath);
		 * chromePrefs.put("credentials_enable_service", false);
		 * chromePrefs.put("profile.password_manager_enabled", false);
		 * options.setExperimentalOption("prefs", chromePrefs);
		 * options.addArguments("--disable-extensions");
		 * options.addArguments("test-type");
		 * options.addArguments("--disable-impl-side-painting");
		 * 
		 * DesiredCapabilities cap = DesiredCapabilities.chrome();
		 * cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		 * cap.setCapability(ChromeOptions.CAPABILITY, options);
		 */
		
		return new ChromeDriver();
		
	}

	private static WebDriver getChromeDriver(String driverpath, ChromeOptions options) {
	
		System.setProperty("webdriver.chrome.driver", driverpath);
//		System.setProperty("webdriver.chrome.logfile", System.getProperty("user.dir")+"\\chromedriver.log");
//		System.setProperty("webdriver.chrome.verboseLogging", "true");
		return new ChromeDriver(options);
	}
	
	private static WebDriver getInternetExplorerDriver(String driverpath) {
		System.setProperty("webdriver.ie.driver", driverpath);

		capabilities.setCapability("requireWindowFocus", true);  
		//capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
		capabilities.setCapability("ie.ensureCleanSession", true);


		capabilities.setCapability("nativeEvents",
		 false);
		 capabilities.setJavascriptEnabled(true);
		 capabilities.setCapability("ignoreZoomSetting", true);
		 //capabilities.setCapability("requireWindowFocus", false);
		 capabilities.setCapability(CapabilityType.ForSeleniumServer.
		 ENSURING_CLEAN_SESSION, true); 
		 capabilities.setCapability("nativeEvents",
		 false);
		 capabilities.setCapability("unexpectedAlertBehaviour", "accept");
		 capabilities.setCapability("disable-popup-blocking", true);
		 capabilities.setCapability("enablePersistentHover", true);
		 capabilities.setCapability(InternetExplorerDriver.
		 ENABLE_ELEMENT_CACHE_CLEANUP, true);
		 capabilities.setCapability(InternetExplorerDriver.
		 INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		return new InternetExplorerDriver(capabilities);
	}

	private static WebDriver getSafariDriver() {
		return new SafariDriver();
	}
}
