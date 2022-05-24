package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class ManageComputerFeatureTest extends BaseTest{

	String computerName; 
	
	@Test(priority = 1, description = "New computers can be added to any facility providing the Facility Name, Computer Name, and the IP Address.")
	public void Test01_1129311(Method method) {
		String IPAddress = DateUtil.getRandomIPAddress();
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFields();
		computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				IPAddress);
		test.siteConfigurationAction.EnterValueInMACAddressField("macaddress_text",
				getData("ComputerDetails.MACAddress"));
		test.siteConfigurationAction.selectValueForDropDown("facilityModelKey",TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ComputerName", computerName);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress", IPAddress);
	}
	
	@Test(priority = 2, description = "In addition, users may search for computers by name")
	public void Test02_1016938(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"In addition, users may search for computers by name");
		test.supportDataActions.enterSearchTermInSearchField(computerName, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName);
	}
	
	@Test(priority = 3, description = "View list of computers in specific or all facilities that the user has access to.")
	public void Test03_1129306(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"View list of computers in specific or all facilities that the user has access to.");
		test.supportDataActions.enterSearchTermInSearchField(computerName, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName);
	}
	
	@Test(priority = 4, description = "Configuring computers is a pre-requisite for creating new Inventory Storage Areas (ISAs).")
	public void Test03_1129303(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Configuring computers is a pre-requisite for creating new Inventory Storage Areas (ISAs).");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add Inventory Storage Area (ISAs)");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");

		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
		test.supportDataActions.verifyTabIsDisplayed("ISA Configuration");
		test.supportDataActions.verifyTabIsDisplayed("Display Settings");
		test.supportDataActions.verifyTabIsDisplayed("Approved Computers");

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");

		
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		String name = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		String shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());

		test.siteConfigurationAction.selectValueForDropDown("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName").trim());
		test.siteConfigurationAction.selectValueForDropDown("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());

		test.siteConfigurationAction.clickTab("ISA Configuration");
	}
	
	@Test(priority = 5, description = "The Computers section enables user to configure PCs that can control designated ISAs and process Pick, Restock, and Return transactions.")
	public void Test05_1129302(Method method) {
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
	}
	
	
	
}
