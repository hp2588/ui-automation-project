package com.org.tests.manualInvoiceTagging;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Add_ISA extends BaseTest {

	String facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber, ipAddress,
			portNumber, carouselConnectionResetTime, app_url;
	
	@Test(priority = 1, enabled=true, description = "VPLX: Manage ISAs: [UI]: Add and View ISA")
	public void Test01_1026541_And_1129303_And_1129302_And_1129266(Method method) throws InterruptedException {
		
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");
		
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue", "30");
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
		Thread.sleep(3000);
		test.siteConfigurationAction.clickTab("Display Settings");
		
		test.siteConfigurationAction.clickTab("Approved Computers");
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
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		
		test.storageAreaAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ISAName", name);
		TestDataPropertyReaderAndWriter.setProperty("ShortName", shortName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(name);
		
	}
	
	@Test(priority = 2, description = "VPLX: Manage ISAs: [UI]: Add and View ISA For Electronic Order")
	public void Test02(Method method) throws InterruptedException {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue", "30");
		
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
		Thread.sleep(3000);
		
		test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.siteConfigurationAction.selectValueForDropDown("Computer",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		test.siteConfigurationAction.selectValueForDropDown("printer",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		
		test.storageAreaAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ISAName_Electronic", name);
		TestDataPropertyReaderAndWriter.setProperty("ShortName_Electronic", shortName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(name);
	}
}
