package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_106 extends BaseTest {

	String facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber, ipAddress,
			portNumber, carouselConnectionResetTime, app_url;

	@Test(priority = 1, description = "VPLX: Manage ISAs: [UI]: Add and View ISA")
	public void Test01_1026541(Method method) throws InterruptedException {

		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add Inventory Storage Area (ISAs)");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");

		// test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");

		// test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		// test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");

		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
		test.supportDataActions.verifyTabIsDisplayed("ISA Configuration");
		test.supportDataActions.verifyTabIsDisplayed("Display Settings");
		test.supportDataActions.verifyTabIsDisplayed("Approved Computers");

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");

		// test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		// test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
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

		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("maxBinNumber","1");
		test.storageAreaAction.enterDataInInputField("maxRackNumber", "2");
		test.storageAreaAction.enterDataInInputField("maxShelvesNumber", "2");
		
		Thread.sleep(3000);
		//test.storageAreaAction.enterDataInInputField("maxBinNumber","1");
		/*
		 * Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory(
		 * "carouselKey"), "[ASSERTION FAILED]: Type drop down is not mandatory");
		 * test.siteConfigurationAction.selectValueFromDropDownByIndex("carouselKey",
		 * 1); test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup(
		 * "devicenumber"); deviceNumber =
		 * test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup(
		 * "devicenumber", getData("AddISA.Device"));
		 * 
		 * ipAddress =
		 * test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup(
		 * "ipAddressValue", getData("AddISA.IPAddress")); portNumber =
		 * test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup(
		 * "portNumber", getData("AddISA.Port"));
		 */
		// test.siteConfigurationAction.clickTab("ISA Configuration");
		test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		System.out.println("Got THE DATA " + getData("ISAApprovedComputers.ComputerStatic"));
		String ComputerStatic = test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		test.siteConfigurationAction.selectValueForDropDown("Computer",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		test.siteConfigurationAction.selectValueForDropDown("printer",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(ComputerStatic);

		test.storageAreaAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ISAName", name);
	}
}
