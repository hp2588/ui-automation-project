package com.org.tests.astarPurchaseDashboard;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_104 extends BaseTest {

	/*
	 * @BeforeClass public void Open_Browser_Window() { test = new
	 * TestSessionInitiator(this.getClass().getSimpleName()); String app_url =
	 * getYamlValue("app_url"); test.launchApplication(app_url);
	 * test.loginPageAction.LoginToTheBDApplication(getData(
	 * "Auth.userNameSupportUser").trim(),
	 * getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
	 * test.landingPageActions.navigateToMenu("Main Menu");
	 * Assert.assertTrue(test.landingPageActions.
	 * verifyUserIsOnLandingPage("Key Destinations"),
	 * "[ASSERTION FAILED]: User is not able to Navigate to Landing Page"); }
	 */  
	@Test(priority = 1, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test01_Add_Computer(Method method) {
		String IPAddress = DateUtil.getRandomIPAddress();
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.clickRadioComputerButton();
		String computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				IPAddress);
		//test.siteConfigurationAction.EnterValueInMACAddressField("macaddress_text",getData("ComputerDetails.MACAddress"));
		//test.siteConfigurationAction.selectValueForDropDown("facilityModelKey",TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ComputerName", computerName);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress", IPAddress);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName);
		TestDataPropertyReaderAndWriter.setProperty("ComputerName", computerName);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress", IPAddress);
	}
}
