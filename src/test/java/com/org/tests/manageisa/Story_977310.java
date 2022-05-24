package com.org.tests.manageisa;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_977310 extends BaseTest {

	String facility, facilityOnISA/*=TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim()*/, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber, ipAddress,
			portNumber, carouselConnectionResetTime, app_url,facilityOnWFAScreen,computerName;
	
	
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
		//test.siteConfigurationAction.clickCheckboxTransactionPriorities("useScanFixFlag");
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

	@Test(priority = 2, description = "VPLX: Manage ISAs: [UI]:Verify add button to add ISAs to the facility")
	public void Test02_1026541(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]:Verify add button to add ISAs to the facility");
		
				test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
	}

	@Test(priority = 3, description = "VPLX: Manage ISAs: [UI]:  Radio Button to choose between carousel and static type of ISAs is displayed on Add/Edit ISA screen out of which Static is selected by default")
	public void Test03_1026515(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify radio Button to choose between carousel and static type of ISAs");

		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyRadioButtonIsChecked("isStaticFlag"));

	}

	@Test(priority = 4, description = "VPLX: Manage ISAs: [UI]: Verify Carousel settings tab if static radio button is selected")
	public void Test04_1026560(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify Carousel settings tab if static radio button is selected");
		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
		test.supportDataActions.verifyTabIsDisplayed("ISA Configuration");
		test.supportDataActions.verifyTabIsDisplayed("Display Settings");
		test.supportDataActions.verifyTabIsDisplayed("Approved Computers");
		
		

	}
	
	@Test(priority = 5, description = "VPLX: Manage ISAs: [UI]: User is able to clear the search text entered in the text box.")
	public void Test05_1026671(Method method) {
		//Assert.assertTrue(test.siteConfigurationAction.verifyRadioButtonIsChecked("isStaticFlag"));
		
	}

	@Test(priority = 6, description = "VPLX: Manage ISAs: [UI]:  Carousel settings tab and other tabs are displayed if carousel radio button is selected")
	public void Test06_1026561(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify Carousel settings tab if carousel radio button is selected");
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		test.supportDataActions.verifyTabIsDisplayed("ISA Configuration");
		test.supportDataActions.verifyTabIsDisplayed("Display Settings");
		test.supportDataActions.verifyTabIsDisplayed("Approved Computers");
		

	}

	@Test(priority = 7, description = "VPLX: Manage ISAs: [UI]: Verify other tabs if static radio button is selected")
	public void Test07_1026581(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify other tabs if static radio button is selected");
		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
		test.supportDataActions.verifyTabIsDisplayed("ISA Configuration");
		test.supportDataActions.verifyTabIsDisplayed("Display Settings");
		test.supportDataActions.verifyTabIsDisplayed("Approved Computers");

	}
	

	@Test(priority = 8, description = "VPLX:Manage ISAs:[UI] - User is not allowed to switch the tabs on the ISA screen without entering the mandatory fields.")
	public void Test08_1033524() {
	
	Assert.assertFalse(test.siteConfigurationAction.verifyTabOnAddDestinationPageIsEnableOrNot("ISA Configuration"));
	Assert.assertFalse(test.siteConfigurationAction.verifyTabOnAddDestinationPageIsEnableOrNot("Display Settings"));
	Assert.assertFalse(test.siteConfigurationAction.verifyTabOnAddDestinationPageIsEnableOrNot("Approved Computers"));
	}

	@Test(priority = 9, description = "VPLX:Manage ISAs:Feature Testing:[UI] -User is able to move to the Require lot/expiration during restock field"
			+ " while pressing tab key from keyboard on add/edit for ISAs")
	public void Test09_1045418() {
	
		test.siteConfigurationAction.clickTabEvent("reqRestockLotInfoFlag");
		
	}
	
	@Test(priority = 10, description = "VPLX: Manage ISAs: [UI]: Verify user able to set ISA active and inactive through toggle button")
	public void Test10_1026652(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify user able to set ISA active and inactive through toggle button");

		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.verifyToggleIsInActive("activeFlag");
		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.verifyToggleIsActive("activeFlag");
	}

	@Test(priority = 11, description = "VPLX: Manage ISAs: [UI]:  Verify name text field is mandatory")
	public void Test11_1026695(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]:  Verify name text field is mandatory");

		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("descriptionText"),
				"[ASSERTION FAILED]: input field Name is not mandatory");

	}

	@Test(priority =12, description = "VPLX: Manage ISAs: [UI]: Verify Short Description text field when adding for the first time for PLX ISA")
	public void Test12_1026782(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify Short Description text field when adding for the first time for PLX ISA");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("ShortDescription"),
				"[ASSERTION FAILED]: input field Name is not mandatory");
	}

	@Test(priority = 13, description = "VPLX: Manage ISAs: [UI]: 'Default Computer' is a non mandatory drop down available only for PLX ISA")
	public void Test13_1027303(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: 'Default Computer' is a non mandatory drop down available only for PLX ISA");
		Assert.assertFalse(test.siteConfigurationAction.verifyISAFieldIsMandatory("workstationComputerKey"),
				"[ASSERTION FAILED]: Default computer is mandatory");
	}

	@Test(priority = 14, description = "VPLX: Manage ISAs: [UI]: Verify  default computer drop down available only for PLX ISAs")
	public void Test14_1027339(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify  default computer drop down available only for PLX ISAs");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("workstationComputerKey");

	}

	@Test(priority = 15, description = "VPLX: Manage ISAs: [UI]: Verify Default Printer is a non mandatory drop down")
	public void Test15_1027398(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify Default Printer is a non mandatory drop down");
		Assert.assertFalse(test.siteConfigurationAction.verifyISAFieldIsMandatory("logisticsLabelPrinterKey"),
				"[ASSERTION FAILED]: Default Printer is mandatory");

	}

	@Test(priority = 16, description = "VPLX: Manage ISAs: [UI]: Verify  default  printer drop down available only for PLX ISAs")
	public void Test16_1027407(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify  default  printer drop down available only for PLX ISAs");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("logisticsLabelPrinterKey");

	}

	@Test(priority = 17, description = "VPLX: Manage ISAs: [UI]: Verify Require lot/expiration on Restock check box only available for PLX ISA")
	public void Test17_1027428(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify Require lot/expiration on Restock check box only available for PLX ISA");

		test.supportDataActions.verifyCheckboxIsDisplayed("reqRestockLotInfoFlag");

	}

	@Test(priority = 18, description = "VPLX: Manage ISAs: [UI]: Verify Require lot/expiration on Restock check box unchecked by default")
	public void Test18_1027430(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify Require lot/expiration on Restock check box unchecked by default");

		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("reqRestockLotInfoFlag"),
				"[ASSERTION FAILED]: Require lot/expiration on Restock check box checked by default");

	}

	@Test(priority = 19, description = "VPLX: Manage ISAs: [UI]:  'Transaction queue lock expiration(minutes)' is a non-mandatory numeric field with Default Value as 0 for VPLX ISA")
	public void Test19_1027440(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]:  'Transaction queue lock expiration(minutes)' is a non-mandatory numeric field with Default Value as 0 for VPLX ISA");
		Assert.assertTrue(test.siteConfigurationAction.verifyDefaultValueInInputField("transactionQueueLockExpirationValue","0"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");

	}

	@Test(priority = 20, description = "VPLX: Manage ISAs: [UI]: 'Type' dropdown is a mandatory field under Carousel Settings tab and all Active PRe-defined Carousels are displayed in dropdown"
			+ "VPLX: Manage ISAs: [UI]: 'Default  printer' drop down is available only for PLX ISAs"
			+ "VPLX: Manage ISAs: [UI]:  User is able to select active printers from 'Default Printers' dropdown"
			+ "VPLX: Manage ISAs: [UI]: User can enter text value in Name Field when Adding/Editing VPLX ISA [Internal]")
	public void Test20_Test21_Test22_Test23_1027452_1027407_1027400_1026685(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: 'Type' dropdown is a mandatory field under Carousel Settings tab and all Active PRe-defined Carousels are displayed in dropdown"
				+ "VPLX: Manage ISAs: [UI]: 'Default  printer' drop down is available only for PLX ISAs"
				+ "VPLX: Manage ISAs: [UI]:  User is able to select active printers from 'Default Printers' dropdown"
				+ "VPLX: Manage ISAs: [UI]: User can enter text value in Name Field when Adding/Editing VPLX ISA [Internal]");
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		//test.supportDataActions.selectValueFromDropdownByIndex("FacilityDropdown", 1);
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facilityOnWFAScreen);

		/*==========TC: 1026685==============*/
		
		
		name = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		
		
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());

		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("workstationComputerKey", 1);
		
		/*==========TC: 1027407  AND 1027400==============*/
		
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("logisticsLabelPrinterKey", 1);

		// defaultComputer =
		// test.siteConfigurationAction.selectValueForDropDown("workstationComputerKey",getData("AddISA.Workstation"));
		// defaultPrinter =
		// test.siteConfigurationAction.selectValueForDropDown("logisticsLabelPrinterKey",getData("AddISA.Printer")
		// );

		test.siteConfigurationAction.clickTab("Carousel Settings");
		Thread.sleep(3000);
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("carouselKey"),
				"[ASSERTION FAILED]: Type drop down is not mandatory");

	}

	@Test(priority = 24, description = "VPLX: Manage ISAs: [UI]: Verify user able to select type from a list of ISA's model")
	public void Test24_1027455(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify user able to select type from a list of ISA's model");
		// type =
		// test.siteConfigurationAction.selectValueForDropDown("carouselKey",
		// getData("AddISA.CarouselKey"));
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("carouselKey", 1);
	}

	@Test(priority = 25, description = "VPLX: Manage ISAs: [UI]:  'Device Number' text field is mandatory under Carousel settings tab when Carousel Radio Button is selected")
	public void Test25_1027461(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]:  'Device Number' text field is mandatory under Carousel settings tab when Carousel Radio Button is selected");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("deviceNumber"),
				"[ASSERTION FAILED]: input field Device Number is not mandatory");

	}

	@Test(priority = 26, description = "VPLX: Manage ISAs: [UI]: User can add/edit value for Device Number for PLX ISA When Carousel Radio button is selected")
	public void Test26_Test27_1027466_1027467(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: User can add/edit value for Device Number for PLX ISA When Carousel Radio button is selected");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("devicenumber");
		deviceNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("devicenumber",
				getData("AddISA.Device"));
	}

	@Test(priority = 28, description = "VPLX: Manage ISAs: [UI]:  User can add /edit mandatory IP Address(ipV4 format) Field under Carousel Settings tab for PLX ISA when Carousel radio button is selected")
	public void Test28_1027468(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]:  User can add /edit mandatory IP Address(ipV4 format) Field under Carousel Settings tab for PLX ISA when Carousel radio button is selected");

		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("ipAddressValue"),
				"[ASSERTION FAILED]: input field IP Address is not mandatory");
		ipAddress = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddressValue",
				TestDataPropertyReaderAndWriter.getProperty("IPAddress"));
	}
	
	/*=================AUTOMATED==========================*/	
	
	@Test(priority = 29, description = "VPLX:Manage ISAs:Feature Testing:[UI] - Carousel Connection Reset Interval field under carousel settings tab is mandatory text field with Default Value as 0 during add/edit for ISAs.")
	public void Test29_1046606(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Manage ISAs:Feature Testing:[UI] - Carousel Connection Reset Interval field under carousel settings tab during add/edit for ISAs.");
	Assert.assertTrue(test.siteConfigurationAction.verifyDefaultValueInInputField("connectionResetMinutes","0"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("connectionResetMinutes",
				"1444");
		test.siteConfigurationAction.verifyErrorMessageForValidRange("Range must be 0 - 1440");
		test.siteConfigurationAction.clearInputBox("connectionResetMinutes");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("connectionResetMinutes",
				"2");
		
	}
	
/*=================AUTOMATED==========================*/	
	
	@Test(priority = 30, description = "VPLX:Manage ISAs:Feature Testing:[UI] -User is able to move to the Returns a status response & Disconnect on idle checkboxes under carousel settings while pressing tab key from keyboard on add/edit for ISAs.")
	public void Test30_1046515(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Manage ISAs:Feature Testing:[UI] -User is able to move to the Returns a status response & Disconnect on idle checkboxes under carousel settings while pressing tab key from keyboard on add/edit for ISAs.");
	test.storageAreaAction.clickCheckboxTransactionPriorities("returnStatusFlag");
	test.storageAreaAction.clickCheckboxTransactionPriorities("disconnectOnIdleFlag");
	
	
	}

	@Test(priority = 31, description = "VPLX:Manage ISAs: [UI]: Port Number field under carousel & display settings tab during add/edit for ISAs is mandatory field and accepts numeric values when 'Light Display is NOT Handled by ISA Controller' is checked")
	public void Test31_1045863(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Manage ISAs: [UI]: Port Number field under carousel & display settings tab during add/edit for ISAs is mandatory field and accepts numeric values when 'Light Display is NOT Handled by ISA Controller' is checked");

		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("portNumber"),
				"[ASSERTION FAILED]: input field Port Nmuber is not mandatory");
		portNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("portNumber",
				getData("AddISA.Port"));
		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		test.storageAreaAction.verifyPageTitleContains("Approved Computers".trim());
		// test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}

		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		test.supportDataActions.selectValueFromDropdownByIndex("Computer", 1);
		// test.storageAreaAction.selectDefaultValueFromDropDown("Computer",
		// getData("ISAApprovedComputers.ComputerNameOutsideComputer"));
		test.supportDataActions.selectValueFromDropdownByIndex("printer", 1);

		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");

		

	}
	
	@Test(priority = 32, description = "VPLX:Manage ISAs:[UI]: [Integration]: No Carousel type ISA is created with dual access if only one  workstation is added.")
	public void Test32_1117247(Method method) throws Throwable {

		test.siteConfigurationAction.verifyButtonIsDisabled("save");
	
	}
	
	@Test(priority = 33, description = "VPLX:Manage ISAs:[UI]: [Integration]: Carousel type ISA is created with dual access when one internal and one external workstation is added.")
	public void Test33_1117245(Method method) throws Throwable {
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		test.siteConfigurationAction.selectRadioOption("insideFlag");

		test.supportDataActions.selectValueFromDropdownByIndex("Computer", 1);
		test.supportDataActions.selectValueFromDropdownByIndex("printer", 1);
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		// test.storageAreaAction.verifyRecordNameIsAvailableInTheList(getData("ISAapprovedComputers.ComputerNameInsideComputer"));

		test.siteConfigurationAction.clickSaveButton();
		Thread.sleep(7000);
		TestDataPropertyReaderAndWriter.setProperty("ISANameNew", name);
		// test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		//test.siteConfigurationAction.selectValueFromDropDownForExternalSystem("facilities",
		//		getData("AddISA.FacilityList"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(name);
		
	
	}
	
	
	@Test(priority = 34, description = "VPLX: Manage ISAs: [UI]: Verify edit button associated to ISA to edit ISAs to the facility")
	public void Test34_1026549(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: Verify edit button associated to ISA to edit ISAs to the facility");

		test.siteConfigurationAction.verifyEditLinkCorrespondingToAddedRecord(name);
		
	}
	
	
	/*Integration Tests - needs update*/

	/*@Test(priority = 29, description = "VPLX: Manage ISAs: [UI]: When a new ISA is added/updated for an approved computer, the ISA is displayed on Select ISA screen when user logs in from the approved computer")
	public void Test29_1106898(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: When a new ISA is added/updated for an approved computer, the ISA is displayed on Select ISA screen when user logs in from the approved computer");
		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(), getData("Auth.passwordAdminUser").trim(),
				TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.supportDataActions.verifyAddedISAonWFAScreen(name);

	}*/
	
	@Test(priority = 35, description = "VPLX: Manage ISAs: [UI]: When ISA is made inactive, it is not displayed on Select ISA screen when user logs in from approved computer against which the ISA has been made inactive")
	public void Test35_1106899(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Manage ISAs: [UI]: When ISA is made inactive, it is not displayed on Select ISA screen when user logs in from approved computer against which the ISA has been made inactive");
		/*test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA2"), 1);
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);
*/
		/*test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage(),
				"[ASSERTION FAILED]: User is not navigated Transaction Queue page");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");*/
		//test.siteConfigurationAction.selectValueFromDropDownForExternalSystem("facilities", facilityOnISA);
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToAddedRecord(name,
				name);

		test.siteConfigurationAction.verifyToggleIsActive("activeFlag");
		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.verifyToggleIsInActive("activeFlag");
        test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
        test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddPrinter"));

		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(), getData("Auth.passwordAdminUser").trim(),
				TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.supportDataActions.verifyInactiveISAIsNotPresentOnWFAScreen(shortName);

	}
}

