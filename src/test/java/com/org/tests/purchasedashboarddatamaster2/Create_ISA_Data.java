package com.org.tests.purchasedashboarddatamaster2;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Create_ISA_Data extends BaseTest {

	String facility, facilityOnISA, name1, shortName1, shortName2, defaultComputer, defaultPrinter, type, deviceNumber,
	ipAddress, portNumber, carouselConnectionResetTime, app_url, resetInterval, name2;
	
	@Test(priority = 1, description = "VPLX: Manage ISAs: [UI]: Add and View ISA")
	public void Test01_1026541(Method method) throws InterruptedException {
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add Inventory Storage Area (ISAs)");
		//test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		//test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
		test.supportDataActions.verifyTabIsDisplayed("ISA Configuration");
		test.supportDataActions.verifyTabIsDisplayed("Display Settings");
		test.supportDataActions.verifyTabIsDisplayed("Approved Computers");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		name1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"ISA" + System.currentTimeMillis());
		shortName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName").trim());
		test.siteConfigurationAction.selectValueForDropDown("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("maxBinNumber", "10");
		Thread.sleep(3000);
		test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		String ComputerStatic = test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		test.siteConfigurationAction.selectValueForDropDown("Computer",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		test.siteConfigurationAction.selectValueForDropDown("printer",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		//test.storageAreaAction.verifyRecordNameIsAvailableInTheList(ComputerStatic);
		test.storageAreaAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ISAName1", name1);
		TestDataPropertyReaderAndWriter.setProperty("ShortName1", shortName1);
	}
	
	@Test(priority = 2, description = "VPLX: Manage ISAs: [UI]: Add and View ISA")
	public void Test02_1026541(Method method) throws InterruptedException {
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add Inventory Storage Area (ISAs)");
		//test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		//test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
		test.supportDataActions.verifyTabIsDisplayed("ISA Configuration");
		test.supportDataActions.verifyTabIsDisplayed("Display Settings");
		test.supportDataActions.verifyTabIsDisplayed("Approved Computers");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		name2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"ISA" + System.currentTimeMillis());
		shortName2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName").trim());
		test.siteConfigurationAction.selectValueForDropDown("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("maxBinNumber", "10");
		Thread.sleep(3000);
		test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		String ComputerStatic = test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		test.siteConfigurationAction.selectValueForDropDown("Computer",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		test.siteConfigurationAction.selectValueForDropDown("printer",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		//test.storageAreaAction.verifyRecordNameIsAvailableInTheList(ComputerStatic);
		test.storageAreaAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ISAName2", name2);
		TestDataPropertyReaderAndWriter.setProperty("ShortName2", shortName2);
	}

}
