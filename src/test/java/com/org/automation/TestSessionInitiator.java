package com.org.automation;

import static com.org.automation.utils.ConfigPropertyReader.getProperty;
import static com.org.automation.utils.YamlReader.setYamlFilePath;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import com.org.actions.BarcodePS_Integration_Actions;
import com.org.actions.DestinationFulfillmentPage_Actions;
import com.org.actions.LandingPage_Actions;
import com.org.actions.LocationAssignmentSettings_Actions;
import com.org.actions.Login_Page_Actions;
import com.org.actions.Purchase_Dashboard_Actions;
import com.org.actions.Remote_Web_Order_Actions;
import com.org.actions.Site_Configuration_Page_Actions;
import com.org.actions.Storage_Area_Actions;
import com.org.actions.Support_Data_Page_Actions;
import com.org.actions.Tenant_Management_Actions;
import com.org.actions.TransactionQueue_Actions;
import com.org.actions.VplxArchievedInvoices_Actions;
import com.org.automation.utils.ConfigPropertyReader;
import com.org.automation.utils.TakeScreenshot;
import com.org.automation.utils.YamlReader;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class TestSessionInitiator {

	protected static WebDriver driver;
	private final WebDriverFactory wdfactory;
	String browser;
	String seleniumserver;
	String seleniumserverhost;
	String appbaseurl;
	String applicationpath;
	String chromedriverpath;
	String datafileloc = "";
	static int timeout;
	Map<String, Object> chromeOptions = null;
	DesiredCapabilities capabilities;

	
	/**
	 * Initiating all the page objects
	 */
	public Login_Page_Actions loginPageAction;
	public Storage_Area_Actions storageAreaAction;
	public Site_Configuration_Page_Actions siteConfigurationAction;
	public TransactionQueue_Actions transactionQueueActions;
	public Support_Data_Page_Actions supportDataActions;
	public LandingPage_Actions landingPageActions;
	public TakeScreenshot takescreenshot;
	public Remote_Web_Order_Actions remoteWebOrderActions;
	public Purchase_Dashboard_Actions purchaseDashboardActions;
	public DestinationFulfillmentPage_Actions destinationFulfillmentActions;
	public BarcodePS_Integration_Actions barcodeActions;
	public LocationAssignmentSettings_Actions locationAssignmentSettings;
	public Tenant_Management_Actions tenantManagementActions;
	public VplxArchievedInvoices_Actions vpxArchievedObj;
	


	public WebDriver getDriver() {
	
		return this.driver;
	}

	/**
	 * Page object Initiation done
	 */
	private void _initPage() {
		loginPageAction = new Login_Page_Actions(driver);
		storageAreaAction = new Storage_Area_Actions(driver);
		siteConfigurationAction = new Site_Configuration_Page_Actions(driver);
		transactionQueueActions = new TransactionQueue_Actions(driver);
		supportDataActions = new Support_Data_Page_Actions(driver);
		landingPageActions = new LandingPage_Actions(driver);
		remoteWebOrderActions= new Remote_Web_Order_Actions(driver);
		purchaseDashboardActions= new Purchase_Dashboard_Actions(driver);
		destinationFulfillmentActions = new DestinationFulfillmentPage_Actions(driver);
		barcodeActions  = new BarcodePS_Integration_Actions(driver);
		locationAssignmentSettings = new LocationAssignmentSettings_Actions(driver);
		tenantManagementActions = new Tenant_Management_Actions(driver);
		vpxArchievedObj = new VplxArchievedInvoices_Actions(driver);
	}

	public TestSessionInitiator(String testname) {
		capabilities = new DesiredCapabilities();
		wdfactory = new WebDriverFactory();
		testInitiator(testname);
	}

	private void testInitiator(String testname) {
		setYamlFilePath();
		_configureBrowser();
		_initPage();
		takescreenshot = new TakeScreenshot(testname, this.driver);
	}
	

	/**
	 * This method is to configure the browsers Setting the implicit wait
	 */
	private void _configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(getProperty("timeout")), TimeUnit.SECONDS);
	}

	public static Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "seleniumserver", "seleniumserverhost", "timeout"};
		Map<String, String> config = new HashMap<String, String>();
		for (String string : configKeys) {
			try {
				if (System.getProperty(string).isEmpty())
					config.put(string, getProperty("./Config.properties", string).trim());
				else
					config.put(string, System.getProperty(string).trim());
			} catch (NullPointerException e) {
				config.put(string, getProperty("./Config.properties", string));
			}
		}
		return config;
	}

	/**
	 * This method is to close all opened browser windows
	 */
	public void closeBrowserSession() {
		driver.quit(); 
		/*
		 * try { Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe"); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
  } 

	/**
	 * This method is to delete the cookies
	 */
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	/**
	 * This method is to close the current browser instance
	 */
	public void closeBrowserWindow() {
		driver.close();
	}

	public static boolean closeAllOtherWindows(String openWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);
				driver.close();
			}
		}

		driver.switchTo().window(openWindowHandle);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	/**
	 * This method is to open a new tab in opened browser window
	 * 
	 * @param baseURL
	 */
	public void openApplicationInNewTab(String baseURL) {
		Robot robot;
		try {
			robot = new Robot();
			robot.delay(2000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_T);
			// String base = driver.getWindowHandle();
			//
			// Set<String> set = driver.getWindowHandles();
			for (String s : driver.getWindowHandles()) {
				driver.switchTo().window(s);
			}
			// set.remove(base);
			// assert set.size() == 1;
			// driver.switchTo().window((String) set.toArray()[0]);
			driver.navigate().to(baseURL);
			Reporter.log("\nThe application url is :- " + baseURL, true);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is to display the test/method name in generated testNG report
	 * 
	 * @param testName
	 */
	public void printMethodName(String testName) {
		Reporter.log("\nMethod Name :- " + testName.toUpperCase() + "\n", true);
	}

	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public boolean isBrowser(String browserName) {
		if (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase(browserName)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is launch application url in browser window
	 * 
	 * @param bd_url
	 */
	public void launchApplication(String bd_url) {
		deleteAllCookies();
		driver.get(bd_url);
		System.out.println("Step: Launched " + bd_url + "\n");
	}
	
	

	
}
