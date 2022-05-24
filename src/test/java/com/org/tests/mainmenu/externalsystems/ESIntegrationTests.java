package com.org.tests.mainmenu.externalsystems;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.smoketests.Map_Facility_To_User;

public class ESIntegrationTests extends BaseTest {
	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };
	String externalSystem, externalSystemUpdated, itemID, facilityOnWFAScreen, dispenseCode, dosageCode,
			externalSystemMappedToFacility,itemName, ISAName, dataEnteredCode, dataEnteredDescription, dataEnteredSort;
	String app_url;
	int lenCode, lenSort;
	String facilityCode, facilityName;
	String rxLicenseId, deaLicenseId, faxNumber;

	String[] preferredContactMethodList = { "Select", "Fax", "Phone", "Email" };

	Map_Facility_To_User map = new Map_Facility_To_User();

	@BeforeClass
	public void Open_Browser_Window() {
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
	}

	@Test(priority = 1, description = "External system is created by providing a system type, name and suitable time zone.")
	public void Test01_1052994() {
		test.landingPageActions.navigateToFeature("External Systems");
		test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		test.siteConfigurationAction.clickOnAddButtonToAddParticular("Add External System");

		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("externalSystemSystemType", 0);
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemTimeZone",
				getData("ExternalSystem.TimeZone"));

		externalSystem = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", "ES" + System.currentTimeMillis());

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		// test.supportDataActions.verifyNewlyAddedRecordNameInList(externalSystem);
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(externalSystem);
		TestDataPropertyReaderAndWriter.setProperty("ExternalSystemName", externalSystem);

		

	}

	@Test(priority = 2, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When a new External System is added,"
			+ "the same External System name should get populated in External System dropdown on Add/Edit Facility page")
	public void Test02_1106988() throws ParseException {
		test.landingPageActions.navigateToMenu("Main Menu");
		/*
		 * test.landingPageActions.navigateToFeature("Facilities");
		 * //test.supportDataActions.verifyLabelIsPresent("Facilities");
		 * 
		 * test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		 * 
		 * test.siteConfigurationAction.selectValueFromDropDown(
		 * "pharmacyInformationSystemKey", externalSystem);
		 */

		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				"Code" + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				"Fac" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", "Select");

		/* ===TC: 1106988===== */

		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey",
				externalSystem);

		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		rxLicenseId = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.clickTab("Contact");
		faxNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("postalCode",
				getData("Facility.postalCode"));
		test.siteConfigurationAction.clickTab("Cycle Counts");
		test.siteConfigurationAction.selectCheckbox("autoGenCycleCountFlag", true);
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("FacilityName", facilityName);
		// test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);
		TestDataPropertyReaderAndWriter.setProperty("FacilityName", facilityName);
		
		map.getfacilityToken();
		map.getOrganizationResourceFacilityID();
		map.getRoleToken();
		map.getRoleID();
		map.getUserID();
		map.getUserMapToken();
		map.PostUserFacilityMapping();

	}

	@Test(priority = 3, description = "VPLX: Manage Healthcare System: [UI]: When a new External System is added, the same External system name is populated under External System"
			+ " dropdown on Add/Edit General Ledger screen")
	public void Test03_1130490() {

		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(),
				TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("General Ledger");

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger Account");

		test.siteConfigurationAction.selectValueFromDropDown("externalSystemKey", externalSystem);
		test.supportDataActions.clickButton("cancel");

	}

	@Test(priority = 4, description = "VPLX: Manage Healthcare System: [UI]: When a new External System is added,"
			+ "the same External System name should get populated in External System dropdown on Therapeutic Class screen")
	public void Test04_1130489() {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Therapeutic Classes");
		test.siteConfigurationAction.selectValueFromDropDown("Therapeutic", externalSystem);

	}

	@Test(priority = 5, description = "VPLX: Manage Healthcare System: [UI]: When a new External System is added,"
			+ "the same External System name should get populated in External System dropdown on Medication Class screen")
	public void Test05_1130488() {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Medication Classes");
		test.siteConfigurationAction.selectValueFromDropDown("medication", externalSystem);

		/*
		 * =======CREATE MEDICATION CLASS for Item
		 * Creation========================
		 */

		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		test.supportDataActions.verifyAllDropdownElementsMedicatonClass("medicationClassOrderInternalCode");
		/*
		 * lenCode = test.supportDataActions.verifyMaxLengthOfAnSearchField(
		 * "medicationClassCode"); Assert.assertEquals(lenCode, 20);
		 */
		/*
		 * lenSort = test.supportDataActions.verifyMaxLengthOfAnSearchField(
		 * "medicationClassSortOrder"); Assert.assertEquals(lenSort, 4);
		 */
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassCode", "2");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode", 3);
		/*
		 * dataEnteredDescription =
		 * test.supportDataActions.enterValueOnDescriptionMedicationPage(
		 * "medicationClassDescription", "UI_Medication_Desc_"); dataEnteredSort
		 * = test.supportDataActions.enterValueOnAddDistributorPage(
		 * "medicationClassSortOrder", "3");
		 * test.siteConfigurationAction.verifyPickRoutingRuleCancelButton(
		 * "saveAdd");
		 */
		// test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("save");
		// test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("cancel");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);

	}

	@Test(priority = 6, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When a new External System is added,"
			+ " the same External system name is populated under External System drop-down on View Dispense Unit screen")
	public void Test06_1106973() {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Dispense Units");
		test.siteConfigurationAction.selectValueFromDropDown("externalSystems", externalSystem);

	}

	@Test(priority = 7, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When a new External System is added,"
			+ " the same External system name is populated under External System dropdown on Add/Edit Dispense Unit screen")
	public void Test07_1106972() {

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		dispenseCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.EnterValueInInputField("sortValue", "2");

		test.siteConfigurationAction.selectValueFromDropDown("externalSystemKey", externalSystem);

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));

	}

	@Test(priority = 8, description = "VPLX: Manage Healthcare System: [UI]:[Integration]:  Newly added External system name"
			+ " is populated under External System drop-down on View Dosage Form screen")
	public void Test08_1106971() {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Dosage Forms");
		test.siteConfigurationAction.selectValueFromDropDown("externalSystems", externalSystem);

	}

	@Test(priority = 9, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: Newly added External system name"
			+ " is populated under External System drop-down on Add/Edit Dosage Form screen")
	public void Test09_1106970() {

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");

		dosageCode = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		test.supportDataActions.EnterValueInInputField("sortValue", "9998");
		test.siteConfigurationAction.selectValueFromDropDown("externalSystemKey", externalSystem);
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));

	}

	@Test(priority = 10, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When an existing External System is added,the updated External System name"
			+ "  gets populated in External System drop-down on Item Management screen")
	public void Test10_1106968() {

		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(),
				TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(externalSystem);

	}

	@Test(priority = 11, description = "VPLX: Manage Healthcare System: [UI] [Integration]: Allow Pharmacy Formulary Edit is unchecked"
			+ " user wont be able to edit the formulary details")
	public void Test11_1117234() {

		// test.siteConfigurationAction.enterRandomValueInRichInputField(externalSystemMappedToFacility);
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");

		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");

		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);

		itemName=test.siteConfigurationAction.enterRandomValueInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterRandomValueInInputField("itemId",
				"ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		test.siteConfigurationAction.clickButton("cancel");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		// test.siteConfigurationAction.verifyInputFieldIsDisabled("genericName");
		// test.siteConfigurationAction.verifyInputFieldIsDisabled("itemId");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldForItemEditIsEnabledOrDisabled("genericName"),
				"[ASSERTION FAILED]: Name field is enabled.");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldForItemEditIsEnabledOrDisabled("itemId"),
				"[ASSERTION FAILED]: ItemID field is enabled.");
		Assert.assertFalse(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("medicationClassKey"));
		Assert.assertFalse(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("dispensingFormKey"));
		Assert.assertFalse(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("dispensingUnitKey"));
	}

	/*
	 * ======================External System -
	 * UPDATE==================================
	 */

	@Test(priority = 12, description = "VPLX: Manage Healthcare System: [UI]: When an External Sytem name is updated, it gets populated on Therapeutic Class screen")
	public void Test12_1106970() {

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

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("External Systems");

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToAddedRecord(externalSystem, externalSystem);
		externalSystemUpdated = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", "ES" + System.currentTimeMillis());
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("allowPISItemEditFlag");
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(externalSystemUpdated);

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Therapeutic Classes");
		test.siteConfigurationAction.selectValueFromDropDown("Therapeutic", externalSystemUpdated);

	}

	@Test(priority = 13, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When a new External System is added,"
			+ "the same External System name should get populated in External System dropdown on Add/Edit Facility page")
	public void Test13_1106990() {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");

		test.siteConfigurationAction.clickOnAddButtonToAddFacility();

		test.siteConfigurationAction.selectValueFromDropDown("pharmacyInformationSystemKey", externalSystemUpdated);
	}

	@Test(priority = 14, description = "VPLX: Manage Healthcare System: [UI]: When an External Sytem name is updated,"
			+ " it gets populated on Medication Class screen")
	public void Test14_1130492() {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Medication Classes");
		test.siteConfigurationAction.selectValueFromDropDown("medication", externalSystemUpdated);

	}

	@Test(priority = 15, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When an existing External System is updated,"
			+ " the updated External system name is populated under External System drop-down on View Dispense Unit screen")
	public void Test15_1106997() {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Dispense Units");
		test.siteConfigurationAction.selectValueFromDropDown("externalSystems", externalSystemUpdated);

	}

	@Test(priority = 16, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When a new External System is updated,"
			+ " the same External system name is populated under External System dropdown on Add/Edit Dispense Unit screen")
	public void Test16_1106996() {

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		test.siteConfigurationAction.selectValueFromDropDown("externalSystemKey", externalSystemUpdated);
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
	}

	@Test(priority = 17, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When an existing External System name is updated,"
			+ " it is populated under External System dropdown on View Dosage Form screen")
	public void Test17_1106995() {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Dosage Forms");
		test.siteConfigurationAction.selectValueFromDropDown("externalSystems", externalSystemUpdated);

	}

	@Test(priority = 18, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When an exisitng External System is updated,"
			+ " the updated External system name is populated under External System dropdown on Add/Edit Dosage Form screen")
	public void Test18_1106993() {

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		test.siteConfigurationAction.selectValueFromDropDown("externalSystemKey", externalSystemUpdated);
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
	}

	@Test(priority = 19, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When an existing External System is updated,"
			+ "the updated External System name  gets populated in External System drop-down on Item Management screen")
	public void Test19_1106991() {

		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(),
				TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(externalSystemUpdated);

	}

	@Test(priority = 20, description = "VPLX : Manage Healthcare System: [UI] [Integration]: Allow Pharmacy Formulary Edit is checked "
			+ "user is able to edit the formulary details")
	public void Test20_1117237() {

		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		Assert.assertFalse(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("genericName"));
		Assert.assertFalse(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("itemId"));
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("medicationClassKey"));
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("dispensingFormKey"));
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("dispensingUnitKey"));
	}

	@Test(priority = 21, description = "VPLX: Manage Healthcare System: [UI]:When an External System is made inactive, "
			+ "it is not displayed under External System dropdown on Therapeutic Class screen")
	public void Test21_1130495() throws Throwable {

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

		test.landingPageActions.navigateToFeature("External Systems");

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToAddedRecord(externalSystemUpdated,
				externalSystemUpdated);
		test.siteConfigurationAction.verifyToggleIsActive("isActive");
		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.verifyToggleIsInActive("isActive");

		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyToggleIsInActive("toggle");
		test.siteConfigurationAction.clickActiveToggle("Show Inactive");
		test.siteConfigurationAction.verifyToggleIsActive("toggle");
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(externalSystemUpdated);

		test.closeBrowserSession();

		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
				getData("Auth.passwordSupportUser").trim(),
				TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		// test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Therapeutic Classes");

		Assert.assertTrue(
				test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("Therapeutic", externalSystemUpdated),
				"[ASSERTION FAILED]: Value exist in dropdown");

	}

	@Test(priority = 22, description = "VPLX: Manage Healthcare System: [UI]:When an External System is made inactive,"
			+ " it is not displayed under External System dropdown on Medication Class screen")
	public void Test22_1130494() {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Medication Classes");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("medication", externalSystemUpdated),
				"[ASSERTION FAILED]: Value exist in dropdown");

	}

	@Test(priority = 23, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When an External System is made inactive,"
			+ " it is not displayed in External System dropdown on Add/Edit Facility page")
	public void Test23_1106990() {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");

		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("pharmacyInformationSystemKey",
				externalSystemUpdated), "[ASSERTION FAILED]: Value exist in dropdown");

	}

	@Test(priority = 24, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When an External System is made inactive,"
			+ " it is not displayed under External System dropdown on View Dispense Unit screen")
	public void Test24_1106983() {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Dispense Units");

		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("externalSystems",
				externalSystemUpdated), "[ASSERTION FAILED]: Value exist in dropdown");

	}

	@Test(priority = 25, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When an External System is made inactive,"
			+ " it is not displayed under External System dropdown on Add/Edit Dispense Unit screen")
	public void Test25_1106986() {

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("externalSystemKey",
				externalSystemUpdated), "[ASSERTION FAILED]: Value exist in dropdown");

	}

	@Test(priority = 26, description = "VPLX: Manage Healthcare System: [UI]:[Integration]: When an External System is made inactive,"
			+ " it is not displayed under External System dropdown on View Dosage Form screen")
	public void Test26_1106978() {
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Dosage Forms");
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("externalSystems",
				externalSystemUpdated), "[ASSERTION FAILED]: Value exist in dropdown");

	}

	@Test(priority = 27, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When an exisitng External System is made inactive,"
			+ " then it is not populated under External System dropdown on Add/Edit Dosage Form screen")
	public void Test27_1106980() {

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		Assert.assertTrue(test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("externalSystemKey",
				externalSystemUpdated), "[ASSERTION FAILED]: Value exist in dropdown");

	}

	@Test(priority = 28, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When an External System is made inactive,"
			+ " the user is  allowed to view Select ISA screen when logged in from a workstation mapped to it")
	public void Test28_1108694() {
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(),
				TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());

		ISAName = test.storageAreaAction.getISANameOnWFAScreen();

		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	}

	@Test(priority = 29, description = "VPLX: Manage Healthcare System: [UI]: [Integration]: When an External System is made inactive,"
			+ " it is not displayed in External System dropdown on Item Management page")
	public void Test29_1106975() {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Management");
		Assert.assertEquals(test.siteConfigurationAction.enterExternalSystemValueDropdownField(externalSystemUpdated),
				"No matches found");
		/*
		 * Assert.assertTrue(test.siteConfigurationAction.
		 * verifyExternalSystemDoesNotExistfromRichTextbox(externalSystemUpdated
		 * ), "[ASSERTION FAILED]: Value exist in dropdown");
		 */
	}
}
