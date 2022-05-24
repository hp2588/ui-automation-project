package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class ISAFeature extends BaseTest {

	String facility, facilityOnISA, name, shortName, defaultComputer,facilityOnWFAScreen, defaultPrinter, computerName,type, deviceNumber, ipAddress,
			portNumber, carouselConnectionResetTime;

	String[] columnHeaders = { "Name", "Facility", "Type", "Carousel", "Status" };

	/* ========================AUTOMATED=============================== */

	@Test(priority = 1, description = "VPLX: Manage ISAs: [UI]: Name text field is non-editable when adding for the first time for non PLX ISA")
	public void Test01_1026689(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage ISAs: [UI]: Name text field is non-editable when adding for the first time for non PLX ISA");
	/*====================================================Pick Facility from WFA Screen at Runtime=======================================================*/
		
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertNotNull(
				test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		
		
		facilityOnWFAScreen=test.siteConfigurationAction.getFacilityFromISAScreen();
		
		//ISAName=test.storageAreaAction.getISANameOnWFAScreen();

		test.siteConfigurationAction.clickActionbutton("Cancel");
		
		/*====================================================Create computer for ISA=======================================================*/
		
		//test.landingPageActions.navigateToMenu("Main Menu");
		
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFields();
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		
		test.siteConfigurationAction.selectValueFromDropDown("defaultFacilityKey", facilityOnWFAScreen);
		computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("computerName",
				"AutomationUI-Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("useScanFixFlag");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("controlCaraouselISA");
		/*test.siteConfigurationAction.EnterValueInMACAddressField("macaddress_text",
				getData("ComputerDetails.MACAddress"));*/
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(computerName);

		test.landingPageActions.navigateToMenu("Main Menu");

		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.verifyLabelIsPresent("ISAs");
		test.supportDataActions.verifyRecordList();
	}

	@Test(priority = 2, description = "VPLX:Manage ISAs:[UI] -User is able to view all active ISAs by default when selecting a facility from drop down.")
	public void Test02_1030688(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage ISAs: [UI]: Name text field is non-editable when adding for the first time for non PLX ISA");

		test.siteConfigurationAction.verifySearchResults("Active", "5");
	}
	
	@Test(priority = 3, description = "VPLX:Manage ISAs:[UI] -User is able to view the list of ISAs corresponsing to a facility selected.")
	public void Test03_1030678(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is able to view the list of ISAs corresponsing to a facility selected.");
	test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown", 1);
	test.siteConfigurationAction.verifySearchResults("Active", "5");
	}	
	
	/* ========================AUTOMATED=============================== */
	
	@Test(priority = 4, description = "VPLX: Manage ISAs: [UI]:  Name text field is displayed when adding for the first time for  PLX ISA [Internal]")
	public void Test04_1026685(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage ISAs: [UI]:  Name text field is displayed when adding for the first time for  PLX ISA [Internal]");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		
	}
	
	
	@Test(priority = 5, description = "VPLX: Manage ISAs: [UI]: Verify Carousel settings tab if static radio button is selected")
	public void Test05_1026560(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify Carousel settings tab if static radio button is selected");
		
		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");

	}
	
	@Test(priority = 6, description = "VPLX: Manage ISAs: [UI]: Verify Carousel settings tab if carousel radio button is selected")
	public void Test06_1026561(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify Carousel settings tab if carousel radio button is selected");
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");

	}

	@Test(priority = 7, description = "VPLX: Manage ISAs: [UI]: Verify Short Description text field when adding for the first time for PLX ISA")
	public void Test07_1026782(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify Short Description text field when adding for the first time for PLX ISA");
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
		test.supportDataActions.verifyTabIsDisplayed("ISA Configuration");
		test.supportDataActions.verifyTabIsDisplayed("Display Settings");
		test.supportDataActions.verifyTabIsDisplayed("Approved Computers");

		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("ShortDescription"),
				"[ASSERTION FAILED]: input field Name is not mandatory");
	}
	
	@Test(priority = 8, description = "VPLX: Manage ISAs: [UI]: Verify Default computer is mandatory drop down")
	public void Test08_1027303(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify Default computer is mandatory drop down");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("workstationComputerKey"),
				"[ASSERTION FAILED]: Default computer is not mandatory");
	}
	
	@Test(priority = 9, description = "VPLX: Manage ISAs: [UI]: Verify  default computer drop down available only for PLX ISAs")
	public void Test09_1027339(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify  default computer drop down available only for PLX ISAs");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("workstationComputerKey");

	}

	@Test(priority = 10, description = "VPLX: Manage ISAs: [UI]: Verify Default Printer is a mandatory drop down")
	public void Test10_1027398(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify Default Printer is a mandatory drop down");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("logisticsLabelPrinterKey"),
				"[ASSERTION FAILED]: Default Printer is not mandatory");

	}
	
	@Test(priority = 11, description = "VPLX: Manage ISAs: [UI]:  'Transaction queue lock expiration(minutes)' is a textfield")
	public void Test11_1027440(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]:  'Transaction queue lock expiration(minutes)' is a textfield");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");

	}
	
	@Test(priority = 12, description = "VPLX: Manage ISAs: [UI]: Verify Require lot/expiration on Restock check box unchecked by default")
	public void Test12_1027430(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify Require lot/expiration on Restock check box unchecked by default");

		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("reqRestockLotInfoFlag"),
				"[ASSERTION FAILED]: Require lot/expiration on Restock check box checked by default");

	}
	
	@Test(priority = 13, description = "VPLX: Manage ISAs: [UI]: Verify Device Number text field under Carousel settings tab")
	public void Test13_Test14_1027466_1027467(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify Device Number text field under Carousel settings tab");
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facilityOnWFAScreen);

		name = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());

		test.siteConfigurationAction.selectValueFromDropDownByIndex("workstationComputerKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("logisticsLabelPrinterKey", 1);
		test.siteConfigurationAction.clickTab("Carousel Settings");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("devicenumber");
		deviceNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("devicenumber",
				getData("AddISA.Device"));
	}

	@Test(priority = 15, description = "VPLX: Manage ISAs: [UI]: Verify IP Address text field under Carousel settings tab")
	public void Test15_1027468(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify IP Address text field under Carousel settings tab");

		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("ipAddressValue"),
				"[ASSERTION FAILED]: input field IP Address is not mandatory");
		ipAddress = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddressValue",
				getData("AddISA.IPAddress"));
	}
	
/*=================AUTOMATED==========================*/
	@Test(priority = 16, description = "VPLX:Manage ISAs:Feature Testing:[UI] - Carousel Connection Reset Interval field under carousel settings tab during add/edit for ISAs.")
	public void Test16_1046606(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Manage ISAs:Feature Testing:[UI] - Carousel Connection Reset Interval field under carousel settings tab during add/edit for ISAs.");
	
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("connectionResetMinutes",
				"1444");
		test.siteConfigurationAction.verifyErrorMessageForValidRange("Range must be 0 - 1440");
		test.siteConfigurationAction.clearInputBox("connectionResetMinutes");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("connectionResetMinutes",
				"2");
		
	}
	
	/*=================AUTOMATED==========================*/	
	
	@Test(priority = 17, description = "VPLX:Manage ISAs:Feature Testing:[UI] -User is able to move to the Returns a status response & Disconnect on idle checkboxes under carousel settings while pressing tab key from keyboard on add/edit for ISAs.")
	public void Test17_1046515(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Manage ISAs:Feature Testing:[UI] -User is able to move to the Returns a status response & Disconnect on idle checkboxes under carousel settings while pressing tab key from keyboard on add/edit for ISAs.");
	test.storageAreaAction.clickCheckboxTransactionPriorities("returnStatusFlag");
	test.storageAreaAction.clickCheckboxTransactionPriorities("disconnectOnIdleFlag");
	
	
	}
	
	@Test(priority = 18, description = "VPLX:Manage ISAs:[UI] -Max racks field under Configuration tab of Add/Edit ISA is mandatory  and editable.")
	public void Test18_1033426(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Max racks field under Configuration tab of Add/Edit ISA is mandatory  and editable.");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("carouselKey"),
				"[ASSERTION FAILED]: Type drop down is not mandatory");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("carouselKey", 1);
		portNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("portNumber",
				getData("AddISA.Port"));
		
		test.storageAreaAction.clickTab("ISA Configuration");
		
		test.storageAreaAction.verifyPageTitleContains("ISA Configuration".trim());
		test.storageAreaAction.verifyInputField("maxRackNumber");
		test.storageAreaAction.enterDataInInputField("maxRackNumber", getData("ISAConfigurationTab.maxRacks"));
	}
	
	@Test(priority = 19, description = "VPLX:Manage ISAs:[UI] - Default bin width field under Configuration tab of Add/Edit ISA is mandatory and editable.")
	public void Test19_1033445(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] - Default bin width field under Configuration tab of Add/Edit ISA is mandatory and editable.");
		test.storageAreaAction.verifyInputField("defaultBinWidthValue");
		test.storageAreaAction.enterDataInInputField("defaultBinWidthValue",
				getData("ISAConfigurationTab.defaultBinWidthValue"));

	}
	
	@Test(priority = 20, description = "VPLX:Manage ISAs:[UI] -Shelf Width field under Configuration tab of Add/Edit ISA is mandatory  and editable.")
	public void Test20_1033439(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Shelf Width field under Configuration tab of Add/Edit ISA is mandatory  and editable.");
		test.storageAreaAction.verifyInputField("shelfWidthValue");
		test.storageAreaAction.enterDataInInputField("shelfWidthValue", getData("ISAConfigurationTab.shelfWidthValue"));

	}
	
	@Test(priority = 21, description = "VPLX:Manage ISAs:[UI] - Max Bins field under Configuration tab of Add/Edit ISA is mandatory and editable.")
	public void Test21_1033441_1033483(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] - Max Bins field under Configuration tab of Add/Edit ISA is mandatory and editable.");
		test.storageAreaAction.verifyInputField("maxBinNumber");
		test.storageAreaAction.enterDataInInputField("maxBinNumber", getData("ISAConfigurationTab.maxBinNumber"));

	}
	
	@Test(priority = 22, description = "VPLX:Manage ISAs:[UI] -  Horizontal dividers field under Configuration tab of Add/Edit ISA is mandatory and editable.")
	public void Test22_1033469(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -  Horizontal dividers field under Configuration tab of Add/Edit ISA is mandatory and editable.");
		test.storageAreaAction.verifyInputField("defaultBinDividersHorizontalNumber");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber",
				getData("ISAConfigurationTab.defaultBinDividersHorizontalNumber"));

	}
	
	@Test(priority = 23, description = "VPLX:Manage ISAs:[UI] -Max columns field is non -mandatory and editable under Display settings")
	public void Test23_1033536(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Max columns field is non -mandatory and editable under Display settings");
		test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.enterDataInInputField("maximumDisplayColumnsNumber",
				getData("ISADisplaySettings.MaxDisplayColumn"));

	}
	
	@Test(priority = 24, description = "VPLX:Manage ISAs:[UI] -Light display is not handled by ISA controller is unchecked by default")
	public void Test24_1034097(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Light display is not handled by ISA controller is unchecked by default");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("displayAttachedFlag"));

	}
	
/*==========================AUTOMATED==============================*/
	
	@Test(priority = 25, description = "VPLX:Manage ISAs:[UI] -User is able to select the left and right radio button for horizontal carousels only.")
	public void Test25_1034077(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is able to select the left and right radio button for horizontal carousels only.");
	test.siteConfigurationAction.selectRadioOption("displayArrowDirection-Left");
	test.siteConfigurationAction.selectRadioOption("displayArrowDirection-Right");
	}
	
	@Test(priority = 26, description = "VPLX:Manage ISAs:[UI] -User is allowed to add Computers and printers from the approved computers tab for static ISA.")
	public void Test26_1039693(Method method) {
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
	 
	
	@Test(priority = 27, description = "VPLX:Manage ISAs:[UI] -User is allowed to add Computers and printers from the approved computers tab for Carousel ISA.")
	public void Test27_1039713(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is allowed to add Computers and printers from the approved computers tab for Carousel ISA.");
		
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
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
		
	}


	

}
