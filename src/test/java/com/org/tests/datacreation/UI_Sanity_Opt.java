package com.org.tests.datacreation;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class UI_Sanity_Opt extends BaseTest {
	Map_Facility_To_User map = new Map_Facility_To_User();
	
	int maxHoldMinutes = 120;
	int maxLockedSeconds = 7200;
	
	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };
	String externalSystem;
	String app_url;
	String migrationName, idnKey;
	String facilityCode, facilityName;
	String rxLicenseId, deaLicenseId, faxNumber;
	
	String[] preferredContactMethodList = { "Select", "Fax", "Phone", "Email" };
	String external_System;

	String printerName, serverPrinterName;

	String facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber, ipAddress,
			portNumber, carouselConnectionResetTime;
	String itemID, itemName, barcode, productID;
	String codeValue, descriptionForm;
	String DispenseUnitCode, sortOrder;
	String dataEnteredName, new_data;
	String dataEnteredCode, dataEnteredDescription, dataEnteredSort, scheduleName;
	String destinationName, destinationCode, streetName, city, zipCode, country, state, emailID, phone, fax,
			systemLabelName;
	String priorityName, code, priorityNameRestock, priorityCodeRestock, priorityNameReturn, routingRuleName,
			priorityCodeReturn;
	String firstname, destination, priority, medItem;
	String OrderName_1, OrderName_2, FacilityName, DestinationName, ItemCode, DistributorName, ExternalSystemName,
			IPAddress, ISAName;
	
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
	
	@Test(priority = 1, description = "VPLX: Manage Heathcare System [UI]: External Systems-Add")
	public void Test01_Add_External_System(Method method) throws IOException {
		TestDataPropertyReaderAndWriter.clearPropertyFile();
		
		test.landingPageActions.navigateToFeature("External Systems");
		test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		
		test.supportDataActions.selectValueFromDropdownByIndex("externalSystemSystemType", 0); 
		// test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		// test.siteConfigurationAction.verifyAllCheckboxesOfExternalSystemsDisabled(external_sys_checkboxes_id);
		test.siteConfigurationAction.verifyDropDownOnAddNewExternalSystem("externalSystemTimeZone");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemTimeZone",
				"(UTC) Coordinated Universal Time");
		externalSystem = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", "Ext" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ExternalSystemName", externalSystem);
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(externalSystem);
		
	}
	
	@Test(priority = 2, description = "VPLX:Add Faciity")
	public void Test02_AddFacility(Method method) {
		test.landingPageActions.pageRefresh();
		
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		
		facilityCode = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("facilityCode",
				"Code" + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("facilityName",
				"Fac" + System.currentTimeMillis());
		
		TestDataPropertyReaderAndWriter.setProperty("FacilityName", facilityName);
		TestDataPropertyReaderAndWriter.setProperty("FacilityCode", facilityCode);
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("pharmacyInformationSystemKey",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("timeZoneID", getData("Facility.timeZoneId"));
		rxLicenseId = test.supportDataActions.EnterValueInInputField("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickTabWithoutWait("Contact");
		faxNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("vendorContactFaxNumberText",
				getData("Facility.FaxNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("postalCode",
				getData("Facility.postalCode"));
		
		// not adding Cycle Count
		/*
		test.siteConfigurationAction.clickTabWithoutWait("Cycle Counts");
		test.siteConfigurationAction.selectCheckbox("autoGenCycleCountFlag", true);
		*/
		
		test.siteConfigurationAction.clickSaveButton();
		
		
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);
		
	}
	
	@Test(priority = 3, description = "VPLX:Navigation from VPLX to Reports: [UI]:Support user has access for Report section")
	public void Test03_View_Reports() {
		
		test.landingPageActions.navigateToMenu("Reports");
		test.landingPageActions.navigateToMenu("Reports");
		test.supportDataActions.switchToReportTab(1);
		test.supportDataActions.switchToFrame("mainFrame");
		//test.supportDataActions.verifyFailureText("Unexpected error occurred, please try again later.");
		Assert.assertTrue(test.supportDataActions.verifyDropdownOnReportingTab());
	}
	
	@Test(priority = 4, description = "VPLX:Map Facility to the user")
	public void Test03_Map_Facility_to_the_user(Method method) throws ParseException {
		
		map.getfacilityToken();
		map.getOrganizationResourceFacilityID();
		map.getRoleToken();
		map.getRoleID();
		map.getUserID();
		map.getUserMapToken();
		map.PostUserFacilityMapping();
		
	}
	
	@Test(priority = 5, description = "Admin User Login Page Test")
	public void Test05_Admin_Login_Test() {
		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(),DateUtil.getRandomIPAddress());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		// TestDataPropertyReaderAndWriter.clearPropertyFile();
		
	}
	
	
	@Test(priority = 6, description = "VPLX:Manage Computers:Verify User is able to add computer")
	public void Test06_Add_Computer(Method method) {
		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(),
				DateUtil.getRandomIPAddress().trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		String IPAddress = DateUtil.getRandomIPAddress();
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		
		test.siteConfigurationAction.clickRadioComputerButton();
		String computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"computerName", "Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress", IPAddress);
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("defaultFacilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("ComputerName", computerName);
		TestDataPropertyReaderAndWriter.setProperty("IPAddress", IPAddress);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(computerName);
		
	}
	
	
	@Test(priority = 7, description = "VPLX:Manage Printers:Verify User is able to add new printer")
	public void Test07_Add_Printer_Test() {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Printers");
		
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("defaultFacilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		
		serverPrinterName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", "ServerPrinter" + System.currentTimeMillis());
		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				"Printer" + System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("printerModelKey", getData("PrinterDetails.Model"));
		test.siteConfigurationAction.selectValueFromDropDownByIndexWithoutWait("printerModelKey", 1);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort",
				getData("PrinterDetails.PortNumber"));
		
		test.siteConfigurationAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("PrinterName", printerName);
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName);
		
	}
	
	
	@Test(priority = 8, description = "VPLX: Manage ISAs: [UI]: Add and View ISA")
	public void Test08_Add_ISA(Method method) throws InterruptedException {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");

		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue", "30");

		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		name = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName").trim());
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());
		
		test.siteConfigurationAction.clickTabWithoutWait("ISA Configuration");
		Thread.sleep(3000);
		
		test.siteConfigurationAction.clickTabWithoutWait("Display Settings");
		test.siteConfigurationAction.clickTabWithoutWait("Approved Computers");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		
		String ComputerStatic = test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(ComputerStatic);
		
		test.storageAreaAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ISAName", name);
		TestDataPropertyReaderAndWriter.setProperty("ShortName", shortName);
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(name);
		
	}

	@Test(priority = 9, description = "VPLX: Dosage Form [UI]: Dosage Form-Add: User is able add and view dosage form")
	public void Test09_Add_Dosage_Form(Method method) {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Dosage Forms");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		
		String dosageFormCode = "Code" + System.currentTimeMillis();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("dosageFormCode", dosageFormCode);
		test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("sortValue", "3");
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("externalSystemKey",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.storageAreaAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("DosageFormCode", dosageFormCode);
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("externalSystems",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dosageFormCode);
		
	}

	@Test(priority = 10, description = "VPLX: Dispense Unit [UI]:Verify User is able to add new dispense unit")
	public void Test10_Add_DispenseUnit_Test(Method method) {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Dispense Units");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord1("Add Dispense Unit");
		DispenseUnitCode = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("dispenseUnitCode",
				"UI_Code" + System.currentTimeMillis());
		/*descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"UI_Description" + System.currentTimeMillis());*/
		sortOrder = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("sortValue", "3");
		test.siteConfigurationAction.selectValueForDropDown("externalSystemKey",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		//test.supportDataActions.clickButton("save");
		test.storageAreaAction.clickSaveButton();
		
		// test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		TestDataPropertyReaderAndWriter.setProperty("DispenseUnitCode", DispenseUnitCode);
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("externalSystems",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		 test.supportDataActions.verifyNewlyAddedRecordNameInList(DispenseUnitCode);
		// TestDataPropertyReaderAndWriter.setProperty("DispenseUnitDesc", descriptionForm);
		// TestDataPropertyReaderAndWriter.setProperty("DispenseUnitCode", DispenseUnitCode);
		
	}

	@Test(priority = 11, description = "VPLX:Medication Classes:UI:Verify User is able to add new medication class")
	public void Test11_MedicationClass_Test(Method method) {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Medication Classes");
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("medication", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		test.supportDataActions.verifyAllDropdownElementsMedicatonClass("medicationClassOrderInternalCode");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassCode", "2");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode", 3);
		test.storageAreaAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("MedClassCode", dataEnteredCode);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);	
	}
	
	
	@Test(priority = 12, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new distributor")
	public void Test12_Add_Distributor_Test(Method method) {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("descriptionText",
				"dis" + System.currentTimeMillis());
		dataEnteredCode = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("shortCode",
				"UI" + System.currentTimeMillis());

		// test.siteConfigurationAction.selectCheckboxCorresspondingToField("internalFlag",
		// true);
		// test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
//		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		
		/* test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		* test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		* new_data = test.supportDataActions.getColumnFirstData("1");
		* Assert.assertEquals(dataEnteredName, new_data);
		*/
		
		TestDataPropertyReaderAndWriter.setProperty("DistributorName", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode", dataEnteredCode);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredName);
		
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction
				.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
		//test.supportDataActions.clickAddButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		Assert.assertEquals(test.siteConfigurationAction.clickFacilityEditLink(TestDataPropertyReaderAndWriter.getProperty("FacilityName")), 
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.siteConfigurationAction.clickTabWithoutWait("Distributor Accounts");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("false"));
		
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show Inactive", true);
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("true"));
		
		/* * Assert.assertFalse(test.siteConfigurationAction.
		 * verifyMessageWhenToggelIsOnOrOff(
		 * "No distributors have been selected for this facility. Select Show Inactive to see all available distributors."
		 * ));*/
		
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName"));

		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim(), "12345"));
		
		test.siteConfigurationAction.clickSaveButton();
		// test.siteConfigurationAction.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"),50,500);
		test.siteConfigurationAction
				.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
		//test.supportDataActions.clickAddButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		/* * test.siteConfigurationAction.
		 * clickOnEditLinkCorresspondingToFacilityName_Sanity(
		 * TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		 */
		
		test.siteConfigurationAction.clickTabWithoutWait("Distributor Accounts");
		Assert.assertTrue(
				test.purchaseDashboardActions.verifyDistributorMappedWithFacility(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim()),
				"[Assertion Failed]: Distributor not listed in Facility");
	}
	
	
	@Test(priority = 13, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test013_Add_MedItem(Method method) {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToMenu("Item Management");
			
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		
		itemName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("genericName",
				"ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("itemId", 
				"ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		
		TestDataPropertyReaderAndWriter.setProperty("ItemName", itemName);
		TestDataPropertyReaderAndWriter.setProperty("ItemCode", itemID);
		
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeaderWithoutWait("Barcodes");
		
		barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickOnSearchButton();
		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("Product ID: " + productID);
		
		test.supportDataActions.clickButtonWithOutAnyWait("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDistributorItemCode(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName"), "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		
		TestDataPropertyReaderAndWriter.setProperty("BarCode", barcode);
		TestDataPropertyReaderAndWriter.setProperty("ProductID", productID);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(itemName);
		
	}

	@Test(priority = 14, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test14_Assign_Item_Location(Method method) {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Item Locations");
		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"), "search");
		
		// test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
		test.siteConfigurationAction.clickSaveButton();
		
	}
	
	
	@Test(priority = 15, description = "VPLX:Manage Printers:Verify User is able to add schedule")
	public void Test15_Add_Schedule_Test(Method method) {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Schedules");
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		//test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("facilityModelKey");
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("defaultFacilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("scheduleName");
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				"Schedule" + System.currentTimeMillis());
		test.siteConfigurationAction.clickToSetDays();
		//test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ScheduleName", scheduleName);
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
		TestDataPropertyReaderAndWriter.setProperty("ScheduleName", scheduleName);
	}
	
	
	@Test(priority = 16, description = "VPLX:Manage Destinations-General:[UI]:Verify User Is able to add destinations")
	public void Test16_Add_Destination() {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.General")));
		Assert.assertTrue(test.siteConfigurationAction.toggleIsActiveOrNot("activeFlag"),
				"[ASSERTION FAILED]: Toggle is inactive in General Tab on Add destination screen");
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Distributor_Accounts")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Users")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Items")));
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		TestDataPropertyReaderAndWriter.setProperty("DestinationName", destinationName);
		TestDataPropertyReaderAndWriter.setProperty("DestinationCode", destinationCode);
		
	}
	
	@Test(priority = 17, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a transaction priority")
	public void Test17_Add_Transaction_Priority(Method method) {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Priorities");
		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.siteConfigurationAction.enterRandomValueInInputField("maxHoldMinutes", String.valueOf(maxHoldMinutes));
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", String.valueOf(maxLockedSeconds));
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualPickFlag");
		// test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualRestockFlag");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		
		TestDataPropertyReaderAndWriter.setProperty("PriorityName", priorityName);
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);
		// TestDataPropertyReaderAndWriter.setProperty("priorityNameRestock", priorityName);
		// TestDataPropertyReaderAndWriter.setProperty("priorityCodeRestock", code);
		
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(priorityName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities(priorityName));
		
		TestDataPropertyReaderAndWriter.setProperty("PriorityName", priorityName);
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);
		// TestDataPropertyReaderAndWriter.setProperty("priorityNameRestock", priorityName);
		// TestDataPropertyReaderAndWriter.setProperty("priorityCodeRestock", code);
	}
	
	@Test(priority = 18, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a transaction priority- Restock")
	public void Test17_Add_Restock_Priority(Method method) {
		test.landingPageActions.pageRefresh();
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		priorityNameRestock = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		priorityCodeRestock = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.siteConfigurationAction.enterRandomValueInInputField("maxHoldMinutes", String.valueOf(maxHoldMinutes));
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", String.valueOf(maxLockedSeconds));
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualRestockFlag");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		
		TestDataPropertyReaderAndWriter.setProperty("priorityNameRestock", priorityNameRestock);
		TestDataPropertyReaderAndWriter.setProperty("priorityCodeRestock", priorityCodeRestock);
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		
		test.siteConfigurationAction.clearInputBox("Search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(priorityNameRestock, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities(priorityNameRestock));
		// TestDataPropertyReaderAndWriter.setProperty("priorityNameRestock", priorityNameRestock);
		// TestDataPropertyReaderAndWriter.setProperty("priorityCodeRestock", priorityCodeRestock);
	}
	
	
	@Test(priority = 19, description = "VPLX:Configure Labels:UI:Verify user have option to edit active/inactive for labels on edit system screen via toggle button")
	public void Test18_Add_Standard_Label(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels:UI:Verify user have option to edit active/inactive for labels on edit system screen via toggle button");
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Standard Labels");
		
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add Standard Label");
		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName",
				getData("SystemLabel.LabelName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.selectValueFromDropDown("category", "Auto Dispensing Cabinet");
		test.siteConfigurationAction.selectPriority(TestDataPropertyReaderAndWriter.getProperty("PriorityName"), true);
		test.siteConfigurationAction.selectPriority("Destination Orders", true);
		test.siteConfigurationAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("LabelName", systemLabelName);
		
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		// test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName);
		// test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		

	}
	
	@Test(priority = 20, description = "VPLX:Manage Routing Rules:[UI]-Verify User is able to add new routing rule")
	public void Test19_Add_RoutingRule_Test(Method method) {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction
				.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickOnAddButtonToAddRoutingRule();
		routingRuleName = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName", routingRuleName);

		// test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SuccessMessages.SuccessMessage"));
		test.siteConfigurationAction.enterSearchTermInSearchField(routingRuleName, "search");
		test.siteConfigurationAction.verifyRoutingRuleDetails(routingRuleName);
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(routingRuleName);
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName", routingRuleName);
	}
	
	@Test(priority = 21, description = "Create and View Cycle Count Transaction", testName = "Create and View Cycle Count Transaction")
	public void Test20_Create_View_Cycle_Count(Method method) {
		
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		// Assert.assertTrue(test.storageAreaAction.verifyTransactionCountForCycleCountTransactionForAvailableISA(TestDataPropertyReaderAndWriter.getProperty("ISAName")));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyCycleCountTransactionInTQ();
		
	}
	
	
	@Test(priority = 22, description = "Create and View Pick Transaction", testName = "Create and View Pick Transaction")
	public void Test21_Create_View_Pick(Method method) {
		/*test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();*/
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		// test.transactionQueueActions.verifyActiveTransactionBox();
		
		test.transactionQueueActions
				.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
	}
	
	
	@Test(priority = 23, description = "Process Pick Transaction")
	public void Test22_Process_Pick(Method method) {
		// test.transactionQueueActions.selectPickTransaction_Sanity(firstname);
		test.transactionQueueActions.clickOnPickNow_Sanity(firstname);
		
		/* * Assert.assertTrue(test.transactionQueueActions.
		 * verifyDestinationInCurrentPickWindow(firstname),"" +
		 * "[ASSERTION FAILED]: Patient Name is not displayed on Current pick Window"
		 * );*/
		 
		Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),
				"[Assertion Failed]: Error while processing transaction");
		// Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBoxSanity(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim()));
		// test.transactionQueueActions.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		Assert.assertFalse(test.transactionQueueActions.verifyPatientNameInPickQueue(firstname),
				"[ASSERTION FAILED]: Patient is displayed in Pick Queue");
	}

	@Test(priority = 24, description = "Create and View Restock Transaction")
	public void Test23_Create_View_Restock(Method method) {
		//test.landingPageActions.pageRefresh();
		/*test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();*/
		test.transactionQueueActions.verifyActionButtonAndClick();	
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		Assert.assertTrue(test.transactionQueueActions.getTransactionTableDataCount() > 0);
		// test.transactionQueueActions.selectRestockTransaction_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		Assert.assertTrue(
				test.transactionQueueActions.verifyReturnTransactionInRestockQueue(
						TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim()),
				"[ASSERTION FAILED]: Return Transaction is not found in Restock Queue.");
		
	}

	@Test(priority = 25, description = "Process Restock Transaction")
	public void Test24_Process_Restock(Method method) {
		
		test.transactionQueueActions
				.clickOnRestockNow_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),
				"[Assertion Failed]: Error while processing transaction");
		Assert.assertFalse(
				test.transactionQueueActions.verifyReturnTransactionInRestockQueue(
						TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim()),
				"[ASSERTION FAILED]: Return Transaction is not found in Restock Queue.");
		
	}

	@Test(priority = 26, description = "VPLX:Transaction queue actions-Hold selected: Putting a single transaction from Retock tab on Hold")
	public void Test25_Hold_Pick_Transaction(Method method) {
		
		/* * test.landingPageActions.navigateToMenu("Transaction Queue");
		 * test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		 * test.transactionQueueActions.verifyTQPageAndAppendIP(
		 * TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		 * Assert.assertTrue(test.storageAreaAction.
		 * verifyUserIsOnStorageAreaPopupPage(),
		 * "\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		 * test.storageAreaAction.selectCheckboxForISA(
		 * TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		 * test.storageAreaAction.selectPrinterForSelectedISA(
		 * TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		 * test.storageAreaAction.verifyStartWorkButtonAndClick();
		 * test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();*/
		 
		test.landingPageActions.pageRefresh();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.selectRestockTransaction_Sanity(
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickHoldButton_Sanity();

		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		test.transactionQueueActions.selectRestockTransaction_Sanity(
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions
				.clickRelease_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.selectRestockTransaction_Sanity(
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		Assert.assertTrue(
				test.transactionQueueActions.verifyRestockTransactionAfterRelease(
						TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim()),
				"[Assertion Failed] Transaction not present in Restocks transaction queue after release");
	}

	@Test(priority = 27, description = "VPLX:Transaction queue actions-Delete selected:-Deletion of single transaction from the Picks tab")
	public void Test26_DeleteTransaction(Method method) {
		
		/* * test.landingPageActions.navigateToMenu("Transaction Queue");
		 * test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		 * test.transactionQueueActions.verifyTQPageAndAppendIP(
		 * TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		 * Assert.assertTrue(test.storageAreaAction.
		 * verifyUserIsOnStorageAreaPopupPage(),
		 * "\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		 * test.storageAreaAction.selectCheckboxForISA(
		 * TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		 * test.storageAreaAction.selectPrinterForSelectedISA(
		 * TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		 * test.storageAreaAction.verifyStartWorkButtonAndClick();
		 * test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		 * //test.transactionQueueActions.verifyActionButtonAndClick();
		 * test.transactionQueueActions.verifyTabOnTQAndClick("Restock");*/
		 
		// test.transactionQueueActions.clickRestockButton_Sanity();
		// test.transactionQueueActions.clickDelete_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
		test.landingPageActions.pageRefresh();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions
				.Deleteransaction_SanityNew(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());

		test.transactionQueueActions.enterDeleteReason("deleteReason", "delete transaction");
		test.transactionQueueActions.clickConfirmToDeleteButton("Confirm");

		// Assert.assertTrue(test.transactionQueueActions.verifyTransactionListIsEmpty());
		Assert.assertFalse(
				test.transactionQueueActions.verifyReturnTransactionInRestockQueue(
						TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim()),
				"[ASSERTION FAILED]: Return Transaction is not found in Restock Queue.");

	}

	@Test(priority = 28, description = "VPLX:Manual Pick[UI]-Verify Add Return Transaction")
	public void Test27_Add_Return_Trxn(Method method) {

		
		/* * test.landingPageActions.navigateToMenu("Transaction Queue");
		 * test.transactionQueueActions.verifyTQPageAndAppendIP(
		 * TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		 * Assert.assertTrue(test.storageAreaAction.
		 * verifyUserIsOnStorageAreaPopupPage(),
		 * "\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		 * test.storageAreaAction.selectCheckboxForISA(
		 * TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		 * test.storageAreaAction.selectPrinterForSelectedISA(
		 * TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		 * test.storageAreaAction.verifyStartWorkButtonAndClick();
		 * test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();*/
		 
		test.landingPageActions.pageRefresh();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", "Return");
		// location=test.transactionQueueActions.selectDropdownForAddPick("Returned
		// From",TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		// test.siteConfigurationAction.selectValueForDropDown("sourceLocation",
		// TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		// test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"),getData("AddPick.Quantity"));
		// test.transactionQueueActions.selectRestockTransaction_Sanity("Return");
		Assert.assertTrue(test.transactionQueueActions.verifyReturnTransactionInRestockQueue("Return"),
				"[ASSERTION FAILED]: Return Transaction is not found in Restock Queue.");
	}

	@Test(priority = 29, description = "VPLX: Create POs : [UI] Order is being placed by clicking Save&Close button with order quantity given")
	public void Test28_PO_Dashboard() {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"), true);
		
		test.supportDataActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		medItem = test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");	
		
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		// test.supportDataActions.openPurchaseOrderManualcard();
		test.purchaseDashboardActions
				.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim());
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber", "PO" + System.currentTimeMillis());
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		
		test.supportDataActions.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.savePONumber("PendingReceive");
		// test.supportDataActions.selectItemtoRecieve(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.clickButton("ReceivedandSent");

		// Go to TQ WFA screen and check Receiving transaction is created
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		Assert.assertTrue(test.storageAreaAction.verifyTransactionCountForReceivingTransactionForAvailableISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName")));
		test.storageAreaAction.clickButton("Cancel");
		
	}
	
	@Test(priority = 30, description = "VPLX: Destinations: Users are available in users tab")
	public void Test29_RO_Destination() {

		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),getData("Auth.passwordAdminUser").trim(),"");
		test.landingPageActions.navigateToMenu("Main Menu");
		itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName").trim();
		FacilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
		DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim();
		
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.enterSearchTermInSearchField(DestinationName);
		test.siteConfigurationAction.clickEditLink(DestinationName);
		test.siteConfigurationAction.clickCheckbox("verifyRemoteOrderFlag");
		
		test.siteConfigurationAction.clickTabWithoutWait("Items");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.enterItemNameForDestinationItem(itemName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
		test.siteConfigurationAction.clickCheckbox("activeFlag-0");
		test.siteConfigurationAction.clickActionbutton("Save & Close");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
		test.siteConfigurationAction.clickCheckbox("limitedOrderQuantity-0");
		test.siteConfigurationAction.enterRandomValueInInputField("maximumOrderQuantity-0",
				getData("RemoteWebOrder.MaxDailyQuantity"));
		test.siteConfigurationAction.clickCheckbox("enableReceiveNSend");
		
		test.siteConfigurationAction.clickTabWithoutWait("Users");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyUserAvailableInList();
		test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.UserName"));
		test.siteConfigurationAction.clickButton("save");
		
	}
	
	@Test(priority = 31, dependsOnMethods={"Test29_RO_Destination"},description = "VPLX: Verify Destination is available in RO Dropdown")
	public void Test30_RO_All_Orders() {
		itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName").trim();
		FacilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
		DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim();
		test.closeBrowserSession();
		
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(),
				getData("Auth.passwordWebOrderUser").trim(), getData("Auth.ip").trim());
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",
				getData("RemoteWebOrder.OrderName") + System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		
		/**********************
		 * Validate a Remote Web Order
		 **************************/
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName, getData("RemoteWebOrder.PendingState"));
		
	}
	
	
	@Test(priority = 32, description = "VPLX: HSDM Test")
	public void Test31_HSDM_Test(Method method) {
		
		test.closeBrowserSession();

		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
				getData("Auth.passwordSupportUser").trim(),
				TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		test.landingPageActions.navigateToFeature("Data setup");
		test.siteConfigurationAction.clickOnAddButtonToAddMigration();
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("pisKey",
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("formularyKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		//test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("migrationName");
		migrationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("migrationName",
				"AutoMig" + System.currentTimeMillis());
		//test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("idnKey");
		idnKey = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("idnKey",
				getData("HSDM.idnKey").trim());
		test.siteConfigurationAction.clickStartMigrationButton(migrationName);
		
		TestDataPropertyReaderAndWriter.setProperty("MigrationName", migrationName);
		test.siteConfigurationAction.verifyCompletedStatusForMigrationAfterTwoMin(
				TestDataPropertyReaderAndWriter.getProperty("MigrationName").trim(), "Completed");
	}
	
}
