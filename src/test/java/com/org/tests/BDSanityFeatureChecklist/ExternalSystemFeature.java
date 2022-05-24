package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;

public class ExternalSystemFeature extends BaseTest{
	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };
	String externalSystem, externalSystemUpdated,itemID,facilityOnWFAScreen,externalSystemMappedToFacility;
	String app_url;

	
	/*@BeforeClass
	public void Open_Browser_Window() {
	test = new TestSessionInitiator(this.getClass().getSimpleName());
	String app_url = getYamlValue("app_url");
	test.launchApplication(app_url);
	test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
	getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
	test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
	test.loginPageAction.clickNextButton();
	test.landingPageActions.navigateToMenu("Main Menu");
	Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
	"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
	}
	
	@Test(priority = 1, description = "External system is created by providing a system type, name and suitable time zone.")
	public void Test01_1052994() {
		test.landingPageActions.navigateToFeature("External Systems");
		test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		test.siteConfigurationAction.clickOnAddButtonToAddParticular("Add External System");

		test.supportDataActions.selectValueFromDropdownByIndex("externalSystemSystemType", 1);
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		test.siteConfigurationAction.verifyAllCheckboxesOfExternalSystemsDisabled(external_sys_checkboxes_id);

		test.siteConfigurationAction.verifyDropDownOnAddNewExternalSystem("externalSystemTimeZone");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemTimeZone",
				getData("ExternalSystem.TimeZone"));

		externalSystem = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", "UI-ES" + System.currentTimeMillis());
		
		test.siteConfigurationAction.clickSaveButton();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(externalSystem);
		
		
	}
	*/
	
	
	@Test(priority = 2, description = "VPLX: Manage Healthcare System: [UI] [Integration]: Allow Pharmacy Formulary Edit is unchecked user wont be able to edit the formulary details")
	public void Test02_1117234() {
		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(), getData("Auth.ip").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertNotNull(
				test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		facilityOnWFAScreen=test.siteConfigurationAction.getFacilityFromISAScreen();
		
		

		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.supportDataActions.verifyLabelIsPresent("Facilities");
		externalSystemMappedToFacility=test.siteConfigurationAction.getExternalSystemMappedToFacility(facilityOnWFAScreen);
		
test.closeBrowserSession();
		
		/*===============Login with Support User=================================*/
		
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
		getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
		"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		
		test.landingPageActions.navigateToFeature("External Systems");
		test.supportDataActions.verifyLabelIsPresent("Manage External Systems");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToAddedRecord("Edit External System", externalSystem);
		
		
		test.siteConfigurationAction.uncheckCheckBox("allowPISItemEditFlag");
		test.supportDataActions.clickButton("save");
		
		
		test.landingPageActions.navigateToFeature("Item Management");

		test.siteConfigurationAction.enterRandomValueInRichInputField(externalSystemMappedToFacility);
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
		
		test.siteConfigurationAction.enterRandomValueInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterRandomValueInInputField("itemId",
				"ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
test.siteConfigurationAction.clickButton("cancel");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.verifyInputFieldIsDisabled("genericName");
		test.siteConfigurationAction.verifyInputFieldIsDisabled("itemId");
		Assert.assertFalse(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("medicationClassKey"));
		Assert.assertFalse(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("dispensingFormKey"));
		Assert.assertFalse(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("dispensingUnitKey"));
	}
	
	
		
		
	
	@Test(priority = 3, description = "VPLX : Manage Healthcare System: [UI] [Integration]: Allow Pharmacy Formulary Edit is checked user is able to edit the formulary details")
	public void Test03_1117237() {
		
		test.closeBrowserSession();
		
		/*===============Login with Support User=================================*/
		
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
		getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
		"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		test.landingPageActions.navigateToFeature("External Systems");
		test.supportDataActions.verifyLabelIsPresent("Manage External Systems");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToAddedRecord("Edit External System", externalSystem);
		
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("allowPISItemEditFlag");
		test.supportDataActions.clickButton("save");
		
		
		
		test.closeBrowserSession();
		
		/*=========Login with Admin User=================*/
		
		
		
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(), getData("Auth.ip").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		test.landingPageActions.navigateToFeature("Item Management");

		test.siteConfigurationAction.enterRandomValueInRichInputField(externalSystemMappedToFacility);
		
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		Assert.assertFalse(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("genericName"));
		Assert.assertFalse(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("itemId"));
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("medicationClassKey"));
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("dispensingFormKey"));
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("dispensingUnitKey"));

	}
}
