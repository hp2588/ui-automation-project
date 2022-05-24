package com.org.tests.selectisa;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_106 extends BaseTest {

	String facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber, ipAddress,
			portNumber, carouselConnectionResetTime, app_url, isaName2, shortName2, deviceNumber2, ipAddress2,
			portNumber2;
	
	/*
	 * Static ISA with ComputerName as default and approved computer
	 */
	@Test(priority = 1, description = "VPLX: Manage ISAs: [UI]: Add and View ISA")
	public void Test01_1026541_And_1129303_And_1129302_And_1129266(Method method) throws InterruptedException {
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");
		
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");
		
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		name = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueForDropDown("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName").trim());
		test.siteConfigurationAction.selectValueForDropDown("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
		
		test.siteConfigurationAction.clickTabWithoutWait("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("maxBinNumber", "1");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber", "2");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersVerticalNumber", "2");
		test.storageAreaAction.enterDataInInputField("maxRackNumber", "3");
		test.storageAreaAction.enterDataInInputField("maxShelvesNumber", "3");
		// Thread.sleep(3000);
		
		test.siteConfigurationAction.clickTabWithoutWait("Display Settings");
		test.siteConfigurationAction.clickTabWithoutWait("Approved Computers");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		
		test.siteConfigurationAction.selectValueForDropDown("Computer",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		test.siteConfigurationAction.selectValueForDropDown("printer",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(
				TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		
		test.storageAreaAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ISAName", name);
		TestDataPropertyReaderAndWriter.setProperty("ShortName", shortName);
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(name);
	}
	
	/*
	 * Static ISA with ComputerName as default computer and no approved computer
	 */
	@Test(priority = 2, description = "VPLX: Manage ISAs: [UI]: Add and View ISA")
	public void Test02_ISA2(Method method) {
		
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");
		
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		name = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueForDropDown("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName").trim());
		test.siteConfigurationAction.selectValueForDropDown("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
		
		test.siteConfigurationAction.clickTabWithoutWait("ISA Configuration");
		test.siteConfigurationAction.clickTabWithoutWait("Display Settings");
		test.siteConfigurationAction.clickTabWithoutWait("Approved Computers");
		test.storageAreaAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("ISAName2", name);
		TestDataPropertyReaderAndWriter.setProperty("ShortName2", shortName);
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(name);
	}
	
	/*
	 * Carousel with default computer
	 */
	@Test(priority = 3, description = "VPLX: Manage ISAs: [UI]: Add and View Carousal")
	public void Test03_ISA3(Method method) {
		
		// adding a carousel ISA - with default computer, without approved computer
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		isaName2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName2"));
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
		
		test.siteConfigurationAction.clickTabWithoutWait("Carousel Settings");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("carouselKey", 1);
		deviceNumber2 = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("devicenumber",
				"123456");
		ipAddress2 = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddressValue",
				"10.11.22.34");
		portNumber2 = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("portNumber", "46345");
		test.siteConfigurationAction.clickTabWithoutWait("ISA Configuration");
		test.siteConfigurationAction.clickTabWithoutWait("Display Settings");
		test.siteConfigurationAction.clickTabWithoutWait("Approved Computers");
		
		test.storageAreaAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("ISAName3", isaName2);
		TestDataPropertyReaderAndWriter.setProperty("ShortName3", shortName2);
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(isaName2);
	}
	
	/*
	 * Carousel without default computer
	 */
	@Test(priority = 4, description = "VPLX: Manage ISAs: [UI]: Add and View Carousal")
	public void Test04_ISA4(Method method) {
		
		// adding a carousel ISA - without any default/approved computer
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		isaName2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		
		test.siteConfigurationAction.clickTabWithoutWait("Carousel Settings");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("carouselKey", 1);
		deviceNumber2 = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("devicenumber",
				"123456");
		ipAddress2 = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddressValue",
				"10.11.22.34");
		portNumber2 = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("portNumber", "46345");
		test.siteConfigurationAction.clickTabWithoutWait("ISA Configuration");
		test.siteConfigurationAction.clickTabWithoutWait("Display Settings");
		test.siteConfigurationAction.clickTabWithoutWait("Approved Computers");
		
		test.storageAreaAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ISAName4", isaName2);
		TestDataPropertyReaderAndWriter.setProperty("ShortName4", shortName2);
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(isaName2);
		
	}
	
	/*
	 * Static ISA without default or approved computer
	 */
	@Test(priority = 5, description = "VPLX: Manage ISAs: [UI]: Add and View ISA")
	public void Test05_ISA5(Method method) {
		
		// test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");
		
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		name = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		
		test.siteConfigurationAction.clickTabWithoutWait("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("maxBinNumber", "1");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber", "2");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersVerticalNumber", "2");
		test.storageAreaAction.enterDataInInputField("maxRackNumber", "3");
		test.storageAreaAction.enterDataInInputField("maxShelvesNumber", "3");
		
		test.siteConfigurationAction.clickTabWithoutWait("Display Settings");
		test.siteConfigurationAction.clickTabWithoutWait("Approved Computers");
		test.storageAreaAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("ISAName5", name);
		TestDataPropertyReaderAndWriter.setProperty("ShortName5", shortName);
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(name);
		
	}
	
	/*
	 * Carousel with approved computer
	 */
	@Test(priority = 6, description = "VPLX: Manage ISAs: [UI]: Add and View Carousal with approved computer")
	public void Test06_ISA6(Method method) {
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		
		// adding a carousel ISA - with approved computer
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		isaName2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		
		test.siteConfigurationAction.clickTabWithoutWait("Carousel Settings");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("carouselKey", 1);
		deviceNumber2 = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("devicenumber",
				"123456");
		ipAddress2 = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddressValue",
				"10.11.22.34");
		portNumber2 = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("portNumber", "46345");
		
		test.siteConfigurationAction.clickTabWithoutWait("ISA Configuration");
		test.siteConfigurationAction.clickTabWithoutWait("Display Settings");
		
		test.siteConfigurationAction.clickTabWithoutWait("Approved Computers");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		
		// adding outside computer
		// 'Outside Computer' radio button is selected by default 
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		test.siteConfigurationAction.selectValueForDropDown("Computer",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName2"));
		test.siteConfigurationAction.selectValueForDropDown("printer",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction
				.verifyRecordNameIsAvailableInTheList(TestDataPropertyReaderAndWriter.getProperty("ComputerName2"));
		
		// adding inside computer
		// click 'Inside Computer' radio button
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		test.storageAreaAction.clickRadioLabelByText("Inside Computer");
		test.siteConfigurationAction.selectValueForDropDown("Computer",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName3"));
		test.siteConfigurationAction.selectValueForDropDown("printer",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction
				.verifyRecordNameIsAvailableInTheList(TestDataPropertyReaderAndWriter.getProperty("ComputerName3"));
		
		test.storageAreaAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("ISAName6", isaName2);
		TestDataPropertyReaderAndWriter.setProperty("ShortName6", shortName2);
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(isaName2);
	}
	
}
