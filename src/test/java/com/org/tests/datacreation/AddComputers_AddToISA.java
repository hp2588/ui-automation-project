package com.org.tests.datacreation;

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

public class AddComputers_AddToISA extends BaseTest {
	
	@Test(priority = 1, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test01_Add_New_Computer(Method method) {
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
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey", 
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("ComputerName2", computerName);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress2", IPAddress);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName);
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.verifyLabelIsPresent("ISAs");
		test.siteConfigurationAction.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.storageAreaAction.clickTab("Approved Computers");
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		
		test.siteConfigurationAction.selectValueForDropDown("Computer", computerName);
		test.siteConfigurationAction.selectValueForDropDown("printer", 
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(computerName);
		test.storageAreaAction.clickSaveButton();
		
	}
	
	@Test(priority = 2, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test02_Add_New_Computer(Method method) {
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
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey", 
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("ComputerName3", computerName);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress3", IPAddress);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName);
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.verifyLabelIsPresent("ISAs");
		test.siteConfigurationAction.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.storageAreaAction.clickTab("Approved Computers");
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		
		test.siteConfigurationAction.selectValueForDropDown("Computer", computerName);
		test.siteConfigurationAction.selectValueForDropDown("printer", 
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(computerName);
		test.storageAreaAction.clickSaveButton();
		
	}
	
}
