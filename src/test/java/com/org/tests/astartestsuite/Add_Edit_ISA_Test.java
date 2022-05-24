package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Add_Edit_ISA_Test extends BaseTest {
	/*
	 * Dependent on Printer created with all checkboxes checked on Add Printer
	 * screen, and Two computers with checkbox 'controlled by Carousel'
	 * checked
	 */

	String facility, facilityOnISA, name, editedName, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
			ipAddress, portNumber, carouselConnectionResetTime, app_url;

	@Test(priority = 1, description = "To verify a user can add a new ISA")
	public void Test01_1114505(Method method) throws InterruptedException {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"To verify a user can add a new ISA");
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Manage ISAs:[UI] User is able to view the list of ISAs corresponsing to a facility selected.");
		test.landingPageActions.navigateToFeature("Inventory Storage Areas (ISAs)");

		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
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
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", "Seattle Grace");

		name = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"StaticISA" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());

		test.supportDataActions.selectValueFromDropdownByIndex("workstationComputerKey", 1);
		test.supportDataActions.selectValueFromDropdownByIndex("logisticsLabelPrinterKey", 1);

		test.siteConfigurationAction.clickTab("ISA Configuration");
		Thread.sleep(3000);
		/*
		 * Assert.assertTrue(test.siteConfigurationAction.
		 * verifyFieldIsMandatory( "carouselKey"),
		 * "[ASSERTION FAILED]: Type drop down is not mandatory");
		 * test.siteConfigurationAction.selectValueFromDropDownByIndex(
		 * "carouselKey", 1);
		 * test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup(
		 * "devicenumber"); deviceNumber = test.siteConfigurationAction.
		 * EnterValueInInputFieldOnAddNewPrinterPopup( "devicenumber",
		 * getData("AddISA.Device"));
		 * 
		 * ipAddress = test.siteConfigurationAction.
		 * EnterValueInInputFieldOnAddNewPrinterPopup( "ipAddressValue",
		 * getData("AddISA.IPAddress")); portNumber =
		 * test.siteConfigurationAction.
		 * EnterValueInInputFieldOnAddNewPrinterPopup( "portNumber",
		 * getData("AddISA.Port"));
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
		test.supportDataActions.selectValueFromDropdownByIndex("Computer", 1);
		test.supportDataActions.selectValueFromDropdownByIndex("printer", 1);
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(ComputerStatic);

		test.storageAreaAction.clickSaveButton();
		// TestDataPropertyReaderAndWriter.setProperty("ISAName", name);
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(name);
	}

	@Test(priority = 2, description = "To verify authorized user can Edit an ISA.")
	public void Test02_1114508(Method method) throws InterruptedException {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"To verify authorized user can Edit an ISA.");
		{
			test.siteConfigurationAction.clickOnEditLinkCorresspondingToAddedRecord("Edit ISA", name);
			editedName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
					"StaticISA_edited" + System.currentTimeMillis());
			test.siteConfigurationAction.clickSaveButton();
			test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddPrinter"));

			test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(editedName);

		}
	}

}
