package com.org.tests.manageisa;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_977312 extends BaseTest {
	String ISAName, facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber,facilityOnWFAScreen=TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),
			ipAddress, portNumber, carouselConnectionResetTime;

	@Test(priority = 1, description = "VPLX:Manage ISAs:[UI] -User is able to check the checkbox for Restrict the control of this ISA to the computers listed below .")
	public void Test01_1039666(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is able to check the checkbox for Restrict the control of this ISA to the computers listed below .");
		
		/*test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertNotNull(
				test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		
		
		facilityOnWFAScreen=test.siteConfigurationAction.getFacilityFromISAScreen();
		
		ISAName=test.storageAreaAction.getISANameOnWFAScreen();
		test.siteConfigurationAction.clickActionbutton("Cancel");*/
		
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");
		
		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
		
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
		test.supportDataActions.verifyTabIsDisplayed("ISA Configuration");
		test.supportDataActions.verifyTabIsDisplayed("Display Settings");
		test.supportDataActions.verifyTabIsDisplayed("Approved Computers");
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");
		
		
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		// test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("FacilityDropdown", 1);
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facilityOnWFAScreen);
		 ISAName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());

		// test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("workstationComputerKey",1);
		//test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("logisticsLabelPrinterKey",1);

		test.siteConfigurationAction.clickTab("Carousel Settings");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("carouselKey"),
				"[ASSERTION FAILED]: Type drop down is not mandatory");
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("carouselKey", 1);
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("devicenumber");
		deviceNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("devicenumber",getData("AddISA.Device")
				);
		
		ipAddress = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddressValue",
				getData("AddISA.IPAddress"));

		portNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("portNumber", getData("AddISA.Port"));
		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.siteConfigurationAction.clickTab("Display Settings");

		test.storageAreaAction.clickTab("Approved Computers");
		test.storageAreaAction.verifyPageTitleContains("Approved Computers".trim());
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		test.storageAreaAction.verifyButtonIsEnabled("Add");

	}

	
	@Test(priority = 2, description = " VPLX:Manage ISAs:[UI] -Default Computer and Printer fields gets disabled when Restrict the control of this ISA to the computers listed below checkbox is checked."
			+ ""
	+ "VPLX:Manage ISAs:[UI] -User is allowed to add Computers and printers from the approved computers tab for static ISA.")
	
	public void Test02_04_1039672_1039693(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Default Computer and Printer fields gets disabled when Restrict the control of this ISA to the computers listed below checkbox is checked.");
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");

		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");		
		//test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		//System.out.println("Got THE DATA " + getData("ISAApprovedComputers.ComputerStatic"));
		String ComputerStatic= test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("Computer",1);
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("printer", 1);
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(ComputerStatic);
		test.siteConfigurationAction.clickTab("General Settings".trim());
		Assert.assertFalse(test.storageAreaAction.verifyDropDownIsEnabledOrDisabled("workstationComputerKey"));
		Assert.assertFalse(test.storageAreaAction.verifyDropDownIsEnabledOrDisabled("logisticsLabelPrinterKey"));
	}

	@Test(priority = 3, description = "VPLX:Manage ISAs:[UI] -Default Computer and Printer fields is  enabled when Restrict the control of this ISA to the computers listed below checkbox is unchecked.")
	public void Test03_1039686(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Default Computer and Printer fields is  enabled when Restrict the control of this ISA to the computers listed below checkbox is unchecked.");
		test.storageAreaAction.clickTab("Approved Computers");
		/*test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		String ComputerStatic= test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("Computer",1);
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("printer", 1);
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");*/
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("Computer",2);
		if (test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag")) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertFalse(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
	
		test.storageAreaAction.clickTab("General Settings".trim());
		Assert.assertTrue(test.storageAreaAction.verifyDropDownIsEnabledOrDisabled("workstationComputerKey"));
		Assert.assertTrue(test.storageAreaAction.verifyDropDownIsEnabledOrDisabled("logisticsLabelPrinterKey"));

	}

//	@Test(priority = 4, description = "VPLX:Manage ISAs:[UI] -User is allowed to add Computers and printers from the approved computers tab for static ISA.")
	public void Test04_1039693(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is allowed to add Computers and printers from the approved computers tab for static ISA."); 
		test.storageAreaAction.clickTab("Approved Computers");

		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		if (test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag")) {
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		else
		{
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");

		}		
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		String ComputerStatic= test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		String PrinterStatic =test.siteConfigurationAction.getAllDataFromDropDown("printer").get(1);
		test.storageAreaAction.selectDefaultValueFromDropDown("Computer",ComputerStatic);
		test.storageAreaAction.selectDefaultValueFromDropDown("printer",PrinterStatic);
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyNewlyAddedRecordNameInList(ComputerStatic,	PrinterStatic);
	}
	 
	@Test(priority = 5, description = "VPLX:Manage ISAs:[UI] -User is allowed to add Computers and printers from the approved computers tab for Carousel ISA." +""
	     +	"VPLX:Manage ISAs:[UI] -User is able to add offset values for Add Approved Computers .")
	public void Test05_1039713_Test09_1040220(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is allowed to add Computers and printers from the approved computers tab for Carousel ISA.");
		
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.storageAreaAction.clickTab("Approved Computers");
		if ((test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		else
		{
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");

		}
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		String ComputerStatic= test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		String PrinterStatic =test.siteConfigurationAction.getAllDataFromDropDown("printer").get(1);
		test.storageAreaAction.selectDefaultValueFromDropDown("Computer",ComputerStatic);
		test.storageAreaAction.selectDefaultValueFromDropDown("printer",PrinterStatic);
		test.storageAreaAction.enterDataInInputField("displayBaseLeftOffsetQuantity",
				getData("ISAApprovedComputers.DiplayBaseLeftOffset"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyNewlyAddedRecordNameInList(ComputerStatic,	PrinterStatic);
		//test.storageAreaAction.clickButtonOnApprovedComputerPage("Save");
		//test.storageAreaAction.clickTab("General Settings".trim());
		//Assert.assertFalse(test.storageAreaAction.verifyDropDownIsEnabledOrDisabled("workstationComputerKey"));
		//Assert.assertFalse(test.storageAreaAction.verifyDropDownIsEnabledOrDisabled("logisticsLabelPrinterKey"));

	}
	
	
	@Test(priority = 6, description = "VPLX:Manage ISAs:[UI] -User is able to edit the list of computers present in the Approved Computers tab.")
	public void Test06_1039737(Method method) {
		if ((test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		else
		{
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");

		}
		test.siteConfigurationAction.clickEditLinkOfApprovedComputer("Edit","Edit Approved Computer");
		String ComputerStatic= test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		String PrinterStatic =test.siteConfigurationAction.getAllDataFromDropDown("printer").get(1);
		test.storageAreaAction.selectDefaultValueFromDropDown("Computer",ComputerStatic);
		test.storageAreaAction.selectDefaultValueFromDropDown("printer",PrinterStatic);
		test.storageAreaAction.enterDataInInputField("displayBaseLeftOffsetQuantity",
				getData("ISAApprovedComputers.DiplayBaseLeftOffset"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Save");
		test.storageAreaAction.verifyNewlyAddedRecordNameInList(ComputerStatic,	PrinterStatic);
	}
	 
	@Test(priority = 7, description = "VPLX:Manage ISAs:[UI] -User is able to delete the list of computers present in the Approved Computers tab.")
	public void Test07_1039745(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is able to delete the list of computers present in the Approved Computers tab.");
		
		String outsideComputer = test.supportDataActions.getColumnFirstData("1");
		//test.storageAreaAction.clickApprovedComputerLink(outsideComputer,"Approved Computer", "Delete");
		test.siteConfigurationAction.clickEditLinkOfApprovedComputer("Delete","Edit Approved Computer");
		test.storageAreaAction.verifyRecordNameIsNotAvailableInTheList(outsideComputer);

	}

	//@Test(priority = 9, description = "VPLX:Manage ISAs:[UI] -User is able to add one inside computer and outside computer for dual access carousels.")
	public void Test08_1040203(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is able to add one inside computer and outside computer for dual access carousels.");
		
		if ((test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
			System.out.println("IN IF");
		}
		else
		{
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			System.out.println("IN ELSE");

		}

		/*
		 * test.storageAreaAction.clickButtonOnApprovedComputerPage("Add"); String
		 * ComputerStatic=
		 * test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		 * String PrinterStatic
		 * =test.siteConfigurationAction.getAllDataFromDropDown("printer").get(1);
		 * test.storageAreaAction.selectDefaultValueFromDropDown("Computer",
		 * ComputerStatic);
		 * test.storageAreaAction.selectDefaultValueFromDropDown("printer",PrinterStatic
		 * );
		 * test.storageAreaAction.enterDataInInputField("displayBaseLeftOffsetQuantity",
		 * getData("ISAApprovedComputers.DiplayBaseLeftOffset"));
		 * test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		 */
		//test.storageAreaAction.verifyRecordNameIsAvailableInTheList(ComputerStatic);
	

	}

	

}
