package com.org.tests.purchasedashboarddata;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;
import com.org.smoketests.Map_Facility_To_User;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.parser.ParseException;

public class Create_PO_Data_Opt extends BaseTest {
	
	
	Map_Facility_To_User map = new Map_Facility_To_User();
	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };
	String externalSystem;
	String app_url;
	String facilityCode, facilityName, facilityNameReceiving, facilityNameProviding, facilityCodeReceiving, facilityCodeProviding;
	String rxLicenseId, deaLicenseId, faxNumber;
	String[] preferredContactMethodList = { "Select", "Fax", "Phone", "Email" };
	String external_System;
	String printerName, serverPrinterName, serverPrinterName1, printerName1, serverPrinterName2, printerName2;
	String facility, facilityOnISA, name1, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
	ipAddress, portNumber, carouselConnectionResetTime, resetInterval, name2;
	String codeValue, descriptionForm,dosageFormCode;
	String dataEnteredCode, dataEnteredDescription, dataEnteredSort;
	String dataEnteredName, new_data;
	String itemID, itemName, barcode, productID, itemID1, itemName1, barcode1, productID1;
	ArrayList<String> previous_data, sorted_data;
	String DispenseUnitCode, sortOrder;

	// login via support user to create external system and facility
	@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
				getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

	}

	// CREATE EXTERNAL SYSTEM
	
	@Test(priority = 1, description = "VPLX: Manage Heathcare System [UI]: External Systems-Add")
	public void Test01_Add_External_System(Method method) throws IOException {
		test.landingPageActions.navigateToFeature("External Systems");
		test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		//test.siteConfigurationAction.clickOnAddButtonToAddParticular("Add External System");
		test.supportDataActions.selectValueFromDropdownByIndex("externalSystemSystemType", 2);
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		//test.siteConfigurationAction.verifyAllCheckboxesOfExternalSystemsDisabled(external_sys_checkboxes_id);
		test.siteConfigurationAction.verifyDropDownOnAddNewExternalSystem("externalSystemTimeZone");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemTimeZone",
				"(UTC) Coordinated Universal Time");
		externalSystem = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", "ES-PO" + RandomStringUtils.randomAlphanumeric(8)); 
		test.siteConfigurationAction.clickSaveButton();
		//TestDataPropertyReaderAndWriter.setProperty("ExternalSystemName", externalSystem);
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader("Success! Changes have been saved.");
		TestDataPropertyReaderAndWriter.clearPropertyFile();
		TestDataPropertyReaderAndWriter.setProperty("ExternalSystemName", externalSystem);
	}
	
	//CREATE FACILITY
	
	@Test(priority = 2, description = "VPLX:Add Faciity")
	public void Test02_AddFacility(Method method) {
		
		test.landingPageActions.pageRefresh();	
	
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		facilityCode = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				"Code" + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				"FacPO" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", "Select");
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		rxLicenseId = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.clickTab("Contact");
		faxNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("postalCode",
				getData("Facility.postalCode"));
		test.siteConfigurationAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("FacilityName", facilityName);
		TestDataPropertyReaderAndWriter.setProperty("FacilityCode", facilityCode);
	}

	@Test(priority = 3, description = "VPLX:Map Facility to the user")
	public void Test03_Map_Facility_to_the_user(Method method) throws ParseException {

		map.getfacilityToken();
		map.getOrganizationResourceFacilityID();
		map.getRoleToken();
		map.getRoleID();
		map.getUserID();
		map.getUserMapToken();
		map.PostUserFacilityMapping();

	}
	
	@Test(priority = 4, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test04_Add_Computer(Method method) {
		
		//login via admin 
		
		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(),getData("Auth.ip").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		String IPAddress = DateUtil.getRandomIPAddress();
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFields();
		String computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress", IPAddress);
		//test.siteConfigurationAction.EnterValueInMACAddressField("macaddress_text",getData("ComputerDetails.MACAddress"));
		test.siteConfigurationAction.selectValueForDropDown("facilityModelKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ComputerName", computerName);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress", IPAddress);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName);
		TestDataPropertyReaderAndWriter.setProperty("ComputerName", computerName);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress", IPAddress);
	}
	
	@Test(priority = 5, description = "VPLX:Manage Printers:Verify User is able to add new printer")
	public void Test05_Add_Printer_Test() {
		
		test.landingPageActions.pageRefresh();
		
		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.selectValueForDropDown("facilityModelKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		serverPrinterName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", "ServerPrinter" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");
		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				"Printer" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("printerModelKey");
		test.siteConfigurationAction.selectValueForDropDown("printerModelKey", getData("PrinterDetails.Model"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("printerModelKey", 1);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort",
				getData("PrinterDetails.PortNumber"));

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaWidth",
				getData("PrinterDetails.PaperWidth"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaHeight",
				getData("PrinterDetails.PaperHeight"));
		//test.siteConfigurationAction.selectCheckboxCorresspondingToField("labelPrinterFlag", true);
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("PrinterName", printerName);
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName);
		TestDataPropertyReaderAndWriter.setProperty("PrinterName", printerName);
	}

	@Test(priority = 6, description = "VPLX: Manage ISAs: [UI]: Add and View ISA-1")
	public void Test06_ISA_1(Method method) throws InterruptedException {
		
		test.landingPageActions.pageRefresh();
		
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
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
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
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
		TestDataPropertyReaderAndWriter.setProperty("ShortName1", shortName);
	}
	
	@Test(priority = 7, description = "VPLX: Manage ISAs: [UI]: Add and View ISA-2")
	public void Test07_ISA_2(Method method) throws InterruptedException {
		
		test.landingPageActions.pageRefresh();
		
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
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
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
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
		TestDataPropertyReaderAndWriter.setProperty("ShortName2", shortName);
	}
	

	@Test(priority = 8, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: User is able add and view dosage form")
	public void Test08_Add_Dosage(Method method) {
		
		test.landingPageActions.pageRefresh();
		
		test.landingPageActions.navigateToItemManagementFeature("Dosage Forms");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.GLAccount")),
				"[ASSERTION FAILED]: Toggle Button is Not Present on Add Dosage Form");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		//String dosageFormCode = "Code" + System.currentTimeMillis();
		dosageFormCode=test.supportDataActions.EnterRandomValueInInputField("dosageFormCode", "mg");
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"mg");
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.siteConfigurationAction.selectValueForDropDown("externalSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		TestDataPropertyReaderAndWriter.setProperty("DosageFormDesc", descriptionForm);
		TestDataPropertyReaderAndWriter.setProperty("DosageFormCode", dosageFormCode);
	}
	
	@Test(priority = 9, description = "VPLX: Dispense Unit [UI]:Verify User is able to add new dispense unit")
	public void Test09_Add_DispenseUnit(Method method) {
		
		test.landingPageActions.pageRefresh();
		
		test.landingPageActions.navigateToFeature("Dispense Units");
		test.supportDataActions.clickOnAddButtonToAddNewRecord1("Add Dispense Unit");
		DispenseUnitCode = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"ml");
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"ml");
		sortOrder = test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.siteConfigurationAction.selectValueForDropDown("externalSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);
		TestDataPropertyReaderAndWriter.setProperty("DispenseUnitDesc", descriptionForm);
		TestDataPropertyReaderAndWriter.setProperty("DispenseUnitCode", DispenseUnitCode);
	}

	
	@Test(priority = 10, description = "VPLX:Medication Classes:UI:Verify User is able to add new medication class code 2")
	public void Test10_MedicationClass_1(Method method) {
		
		test.landingPageActions.pageRefresh();
		
		test.landingPageActions.navigateToFeature("Medication Classes");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Medication Classes"));
		test.siteConfigurationAction.selectDropdownDispenseExternal(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"), "medication");
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("medicationClassCode", "2");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode",1);
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription",
				"CII Med Class");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "10");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassCode1", dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassDescription1", dataEnteredDescription);
	}

	@Test(priority = 11, description = "VPLX:Medication Classes:UI:Verify User is able to add new medication class code 3")
	public void Test11_MedicationClass_2(Method method) {
		
		test.landingPageActions.pageRefresh();
		
		test.siteConfigurationAction.selectDropdownDispenseExternal(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"), "medication");
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("medicationClassCode", "3");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode",2);
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription",
				"CIII Med Class");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "20");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassCode2", dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassDescription2", dataEnteredDescription);
	}
	

	@Test(priority = 12, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new Manual distributor auto receive OFF")
	public void Test12_Add_Distributor_Man_Off(Method method) {
		
		test.landingPageActions.pageRefresh();
		
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"DisManOFF" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());

		// test.siteConfigurationAction.selectCheckboxCorresspondingToField("internalFlag",
		// true);
		// test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("DistributorName1", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode1", dataEnteredCode);

	}

	@Test(priority = 13, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new Manual distributor auto receive ON")
	public void Test13_Add_Distributor_Man_On(Method method) {

		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"DisManON" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());

		// test.siteConfigurationAction.selectCheckboxCorresspondingToField(
		// "internalFlag", true);
		// test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("DistributorName2", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode2", dataEnteredCode);

	}

	@Test(priority = 14, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new Electronic distributor auto receive OFF")
	public void Test14_Add_Distributor_Elec_Off(Method method) {

		test.supportDataActions.clickAddButtonOnDistributor("add");
		test.purchaseDashboardActions.selectRadioButtonElectronicDistributor("electronicDistributorFlag");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"DisElecOFF" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());
		test.purchaseDashboardActions.enterDistributorWebsite("vendorContactWebsiteAddressValue",
				"https://www.cardinalhealth.com/");

		// test.siteConfigurationAction.selectCheckboxCorresspondingToField("internalFlag",
		// true);
		// test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("DistributorName3", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode3", dataEnteredCode);

	}

	@Test(priority = 15, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new Electronic distributor auto receive ON")
	public void Test15_Add_Distributor_Elec_On(Method method) {

		test.supportDataActions.clickAddButtonOnDistributor("add");
		test.purchaseDashboardActions.selectRadioButtonElectronicDistributor("electronicDistributorFlag");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"DisElecON" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());
		test.purchaseDashboardActions.enterDistributorWebsite("vendorContactWebsiteAddressValue",
				"https://www.sunpharma.com/");

		// test.siteConfigurationAction.selectCheckboxCorresspondingToField("internalFlag",
		// true);
		// test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",
		// TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("DistributorName4", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode4", dataEnteredCode);

	}

	@Test(priority = 16, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new Manual distributor auto receive OFF")
	public void Test16_Add_Distributor_Man_Off_2(Method method) {

		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"Dis2ManOFF" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());

		// test.siteConfigurationAction.selectCheckboxCorresspondingToField(
		// "internalFlag", true);
		// test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",
		// TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("DistributorName5", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode5", dataEnteredCode);

	}

	@Test(priority = 17, description = "VPLX:Manage Distributors:[UI]:Add AutoCreate distributor")
	public void Test17_Add_Distributor_Auto_Create(Method method) {

		test.supportDataActions.clickAddButtonOnDistributor("add");
		test.purchaseDashboardActions.selectRadioButtonElectronicDistributor("electronicDistributorFlag");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"DistAutoCr" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());

		// test.siteConfigurationAction.selectCheckboxCorresspondingToField("internalFlag",
		// true);
		// test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",
		// TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("AutoCreateDistributor", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("AutoCreateID", dataEnteredCode);

	}
	
	@Test(priority = 18, description = "VPLX:Manage Distributors:[UI]:Map distributors to facility")
	public void Test18_Map_Distributors_To_Facility(Method method) {
		
		test.landingPageActions.pageRefresh();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction
				.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");

		/*
		 * test.siteConfigurationAction.
		 * clickOnEditLinkCorresspondingToFacilityName_Sanity(
		 * TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		 */

		test.siteConfigurationAction.clickTab("Distributor Accounts");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("false"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show Inactive", true);
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("true"));
	}
	
	@Test(priority = 19, description = "VPLX:Manage Distributors:[UI]:Map distributors to facility")
	public void Test19_Map_Man_Off_Distributors_To_Facility(Method method) {
	
		// Manual Distributor Auto Receive OFF
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1"),
				RandomStringUtils.random(6, false, true)));
	}
	
	@Test(priority = 20, description = "VPLX:Manage Distributors:[UI]:Map distributors to facility")
	public void Test20_Map_Man_2_Off_Distributors_To_Facility(Method method) {
		// Manual Distributor 2 Auto Receive OFF
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName5"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName5"),
				RandomStringUtils.random(6, false, true)));
	}
	
	@Test(priority = 21, description = "VPLX:Manage Distributors:[UI]:Map distributors to facility")
	public void Test21_Map_Man_On_Distributors_To_Facility(Method method) {
		// Manual Distributor Auto Receive ON
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName2"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName2"),
				RandomStringUtils.random(6, false, true)));
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName2"), "enableAutoReceiveNonControlledFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName2"), "enableAutoReceiveControlledTwoFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName2"),
				"enableAutoReceiveControlledThreeToFiveFlag");
	}
	
	@Test(priority = 22, description = "VPLX:Manage Distributors:[UI]:Map distributors to facility")
	public void Test22_Map_Elec_Off_Distributors_To_Facility(Method method) {
		// Electronic Distributor Auto Receive OFF
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName3"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName3"),
				RandomStringUtils.random(6, false, true)));
	}
	
	@Test(priority = 23, description = "VPLX:Manage Distributors:[UI]:Map distributors to facility")
	public void Test23_Map_Elec_On_Distributors_To_Facility(Method method) {
	
		// Electronic Distributor Auto Receive ON
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName4"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName4"),
				RandomStringUtils.random(6, false, true)));
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName4"), "enableAutoReceiveNonControlledFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName4"), "enableAutoReceiveControlledTwoFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName4"),
				"enableAutoReceiveControlledThreeToFiveFlag");
	}
	
	@Test(priority = 24, description = "VPLX:Manage Distributors:[UI]:Map distributors to facility")
	public void Test24_Map_Auto_Create_Distributors_To_Facility(Method method) {
		
		// Auto Create distributor Auto Receive ON

		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor"),
				RandomStringUtils.random(6, false, true)));
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor"),
				"enableAutoReceiveNonControlledFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor"),
				"enableAutoReceiveControlledTwoFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor"),
				"enableAutoReceiveControlledThreeToFiveFlag");
	}
	
	
	@Test(priority = 25, description = "VPLX:Manage Distributors:[UI]:Map distributors to facility")
	public void Test25_Click_Save_After_Distributor_Mapping(Method method) {
		
		test.siteConfigurationAction.clickSaveButton();
	}

	// CREATE MEDITEM
	
	@Test(priority = 26, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test26_Navigate_To_Item_Management(Method method) {
	test.landingPageActions.pageRefresh();
	
	test.landingPageActions.navigateToItemManagementFeature("Item Management");
	test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
	
	}
	
	@Test(priority = 27, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test27_ManualItemName1(Method method) {
				
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode1").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID1);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		
		//test.siteConfigurationAction.clickButton("add");
		//test.siteConfigurationAction.verifyAddedProductID(productID1);
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		long itemCode=System.currentTimeMillis();
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"),Long.toString(itemCode));
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
	//	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID1);
		TestDataPropertyReaderAndWriter.setProperty("PckSize","5");
		TestDataPropertyReaderAndWriter.setProperty("NDCItem1",productID1);
		TestDataPropertyReaderAndWriter.setProperty("VICItem1",Long.toString(itemCode));
		TestDataPropertyReaderAndWriter.setProperty("ItemID1",itemID1);
		TestDataPropertyReaderAndWriter.setProperty("ManualItemName1",itemName1);
	}
	
	@Test(priority = 28, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test28_ManualItemName2(Method method) {
	
	//	test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode1").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID1);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		//test.siteConfigurationAction.clickButton("add");
		//test.siteConfigurationAction.verifyAddedProductID(productID1);
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		long itemCode=System.currentTimeMillis();
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"),Long.toString(itemCode));
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
	//	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID1);
		TestDataPropertyReaderAndWriter.setProperty("NDCItem2",productID1);
		TestDataPropertyReaderAndWriter.setProperty("VICItem2",Long.toString(itemCode));
		TestDataPropertyReaderAndWriter.setProperty("ItemID2",itemID1);
		TestDataPropertyReaderAndWriter.setProperty("ManualItemName2",itemName1);
	}
	
	@Test(priority = 29, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test29_ManualItemName3(Method method) {
	
	//	test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode1").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID1);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		//test.siteConfigurationAction.clickButton("add");
		//test.siteConfigurationAction.verifyAddedProductID(productID1);
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName2"));
		long itemCode=System.currentTimeMillis();
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName2"),Long.toString(itemCode));
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
	//	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID1);
		TestDataPropertyReaderAndWriter.setProperty("NDCItem3",productID1);
		TestDataPropertyReaderAndWriter.setProperty("VICItem3",Long.toString(itemCode));
		TestDataPropertyReaderAndWriter.setProperty("ItemID3",itemID1);
		TestDataPropertyReaderAndWriter.setProperty("ManualItemName3",itemName1);
	}
	
	@Test(priority = 30, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test30_ManualItemName4(Method method) {
	
	//	test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode1").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID1);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		//test.siteConfigurationAction.clickButton("add");
		//test.siteConfigurationAction.verifyAddedProductID(productID1);
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName2"));
		long itemCode=System.currentTimeMillis();
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName2"),Long.toString(itemCode));
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
	//	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID1);
		TestDataPropertyReaderAndWriter.setProperty("NDCItem4",productID1);
		TestDataPropertyReaderAndWriter.setProperty("VICItem4",Long.toString(itemCode));
		TestDataPropertyReaderAndWriter.setProperty("ItemID4",itemID1);
		TestDataPropertyReaderAndWriter.setProperty("ManualItemName4",itemName1);
	}
	
	@Test(priority = 31, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test31_ManualItemName5(Method method) {
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode1").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID1);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		
		//test.siteConfigurationAction.clickButton("add");
		//test.siteConfigurationAction.verifyAddedProductID(productID1);
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		long itemCode=System.currentTimeMillis();
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"),Long.toString(itemCode));
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
	//	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID1);
		TestDataPropertyReaderAndWriter.setProperty("PckSize","5");
		TestDataPropertyReaderAndWriter.setProperty("NDCItem5",productID1);
		TestDataPropertyReaderAndWriter.setProperty("VICItem5",Long.toString(itemCode));
		TestDataPropertyReaderAndWriter.setProperty("ItemID5",itemID1);
		TestDataPropertyReaderAndWriter.setProperty("ManualItemName5",itemName1);
	}
	
	@Test(priority = 32, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test32_ElecItemName1(Method method) {
	
	//	test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode1").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID1);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		//test.siteConfigurationAction.clickButton("add");
		//test.siteConfigurationAction.verifyAddedProductID(productID1);
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName3"));
		long itemCode=System.currentTimeMillis();
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName3"),Long.toString(itemCode));
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
	//	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID1);
		TestDataPropertyReaderAndWriter.setProperty("NDCItem6",productID1);
		TestDataPropertyReaderAndWriter.setProperty("VICItem6",Long.toString(itemCode));
		TestDataPropertyReaderAndWriter.setProperty("ItemID6",itemID1);
		TestDataPropertyReaderAndWriter.setProperty("ElecItemName1",itemName1);
	}
	
	@Test(priority = 33, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test33_ElecItemName2(Method method) {
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
	
	//	test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode1").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID1);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		//test.siteConfigurationAction.clickButton("add");
		//test.siteConfigurationAction.verifyAddedProductID(productID1);
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName3"));
		long itemCode=System.currentTimeMillis();
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName3"),Long.toString(itemCode));
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
	//	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID1);
		TestDataPropertyReaderAndWriter.setProperty("NDCItem7",productID1);
		TestDataPropertyReaderAndWriter.setProperty("VICItem7",Long.toString(itemCode));
		TestDataPropertyReaderAndWriter.setProperty("ItemID7",itemID1);
		TestDataPropertyReaderAndWriter.setProperty("ElecItemName2",itemName1);
	}
	
	@Test(priority = 34, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test34_ElecItemName3(Method method) {

	//	test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode1").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID1);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		//test.siteConfigurationAction.clickButton("add");
		//test.siteConfigurationAction.verifyAddedProductID(productID1);
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName4"));
		long itemCode=System.currentTimeMillis();
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName4"),Long.toString(itemCode));
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
	//	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID1);
		TestDataPropertyReaderAndWriter.setProperty("NDCItem8",productID1);
		TestDataPropertyReaderAndWriter.setProperty("VICItem8",Long.toString(itemCode));
		TestDataPropertyReaderAndWriter.setProperty("ItemID8",itemID1);
		TestDataPropertyReaderAndWriter.setProperty("ElecItemName3",itemName1);
	}
	
	@Test(priority = 35, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test35_ElecItemName4(Method method) {
	
	//	test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode1").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID1);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		//test.siteConfigurationAction.clickButton("add");
		//test.siteConfigurationAction.verifyAddedProductID(productID1);
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName4"));
		long itemCode=System.currentTimeMillis();
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName4"),Long.toString(itemCode));
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
	//	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID1);
		TestDataPropertyReaderAndWriter.setProperty("NDCItem9",productID1);
		TestDataPropertyReaderAndWriter.setProperty("VICItem9",Long.toString(itemCode));
		TestDataPropertyReaderAndWriter.setProperty("ItemID9",itemID1);
		TestDataPropertyReaderAndWriter.setProperty("ElecItemName4",itemName1);
	}
	
	@Test(priority = 36, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test36_ManualItemMultiISA(Method method) {
	
	//	test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode2").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID1);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		//test.siteConfigurationAction.clickButton("add");
		//test.siteConfigurationAction.verifyAddedProductID(productID1);
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName5"));
		long itemCode=System.currentTimeMillis();
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName5"),Long.toString(itemCode));
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
	//	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID1);
		TestDataPropertyReaderAndWriter.setProperty("NDCItem10",productID1);
		TestDataPropertyReaderAndWriter.setProperty("VICItem10",Long.toString(itemCode));
		TestDataPropertyReaderAndWriter.setProperty("ItemID10",itemID1);
		TestDataPropertyReaderAndWriter.setProperty("ManualItemMultiISA",itemName1);
	}
	
	@Test(priority = 37, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test37_AutoCreateItem(Method method) {
	
//		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
			test.siteConfigurationAction.clickActionbutton("Actions");
			test.siteConfigurationAction.clickActionbutton("Add New Item");
			test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
			Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
					"[ASSERTION FAILED]: input field is not mandatory");
			Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
					"[ASSERTION FAILED]: input field is not mandatory");
			Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
					"[ASSERTION FAILED]: dropdown is not mandatory");
			test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
			test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
			itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","AutoCreate" + System.currentTimeMillis());
			itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
			test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode1").trim());
			test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
			test.siteConfigurationAction.clickButton("save");
			test.siteConfigurationAction.verifyAndClickProductID("Product ID");
			test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
			test.siteConfigurationAction.verifyHeader("Barcodes");
			barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
					"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
			//test.siteConfigurationAction.clickButton("search");
			productID1 = test.siteConfigurationAction.getParsedProductID();
			System.out.println("productID=" + productID1);
			test.siteConfigurationAction.clickButton("link");
			test.siteConfigurationAction.verifyAddedProductID(productID1);
			test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
			//test.siteConfigurationAction.clickButton("add");
			//test.siteConfigurationAction.verifyAddedProductID(productID1);
			
			test.siteConfigurationAction.clickLink("Add Preferred Distributor");
			
			test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
			test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor"));
			long itemCode=System.currentTimeMillis();
			test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor"),Long.toString(itemCode));
			test.siteConfigurationAction.clickButton("primary");
			test.siteConfigurationAction.clickSaveButtonForISA();
		//	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
			test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID1);
			TestDataPropertyReaderAndWriter.setProperty("NDCItem11",productID1);
			TestDataPropertyReaderAndWriter.setProperty("VICItem11",Long.toString(itemCode));
			TestDataPropertyReaderAndWriter.setProperty("ItemID11",itemID1);
			TestDataPropertyReaderAndWriter.setProperty("AutoCreateItem",itemName1);
	
	}
	
	@Test(priority = 38, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem-Unable to Order")
	public void Test38_UnableToOrderItem(Method method) {
	
	//	test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode1").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID1);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.clickSaveButtonForISA();
		
		TestDataPropertyReaderAndWriter.setProperty("ItemID10",itemID1);
		TestDataPropertyReaderAndWriter.setProperty("UnableToOrderItem",itemName1);
		
		
	}
	
	// ASSIGN LOCATION
	
	@Test(priority = 39, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test39_Assign_Location_ManualItemName1(Method method) throws InterruptedException {
		
		test.landingPageActions.pageRefresh();
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName1"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		Thread.sleep(5000);
	}

	@Test(priority = 40, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test40_Assign_Location_ManualItemName2(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName2"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName2"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
	}
	
	@Test(priority = 41, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test41_Assign_Location_ManualItemName5(Method method) throws InterruptedException {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ManualItemName5"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName5"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName1"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		Thread.sleep(5000);
	}
	
	@Test(priority = 42, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test42_Assign_Location_ManualItemName3(Method method) throws InterruptedException {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ManualItemName3"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName3"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName1"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		Thread.sleep(5000);
	}

	@Test(priority = 43, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test43_Assign_Location_ManualItemName4(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ManualItemName4"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName4"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName2"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
	}

	@Test(priority = 44, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test44_Assign_Location_ElecItemName1(Method method) throws InterruptedException {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName1"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		Thread.sleep(5000);
	}

	@Test(priority = 45, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test45_Assign_Location_ElecItemName2(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ElecItemName2"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName2"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName2"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
	}
	
	@Test(priority = 46, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test46_Assign_Location_ElecItemName3(Method method) throws InterruptedException {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ElecItemName3"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName3"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName1"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		Thread.sleep(5000);
	}

	@Test(priority = 47, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test47_Assign_Location_ElecItemName4(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ElecItemName4"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName4"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName2"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
	}
	
	@Test(priority = 48, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test48_Assign_Location_ManualItemMultiISA(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ManualItemMultiISA"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemMultiISA"));
		
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName1"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName2"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
	}
	
	@Test(priority = 49, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test49_Assign_Location_AutoCreateItem(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("AutoCreateItem"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("AutoCreateItem"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName1"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "80");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "10");
		test.siteConfigurationAction.clickSaveButton();
	}

	
}
