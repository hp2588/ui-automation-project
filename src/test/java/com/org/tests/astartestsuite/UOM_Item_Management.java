package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class UOM_Item_Management extends BaseTest {

	String itemID, externalSystem=TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim(),facility=TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
	@Test(priority = 1, description = "System provides a list of all existing and active UOMs")
	public void Test01_1121601(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"System provides a list of all existing and active UOMs");
		test.landingPageActions.navigateToFeature("Units of Measure");
		//test.siteConfigurationAction.verifyuomeditbuttonsDisabled();
		//test.siteConfigurationAction.verifyuomeditbuttonisEnabled();
		Assert.assertTrue(test.siteConfigurationAction.verifyActiveExternalSystems("Active", "3"));
		Assert.assertFalse(test.siteConfigurationAction.verifyActiveExternalSystems("Inactive", "3"));
}

	@Test(priority = 2, description = "VPLX : UOM codes in dropdown lists are displayed in alphabetical order.")
	public void Test02_1121631(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : UOM codes in dropdown lists are displayed in alphabetical order.");
		
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
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("External Systems");
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(externalSystem, "search");

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToAddedRecord("Edit External System", externalSystem);
		/*externalSystemUpdated = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", "ES" + System.currentTimeMillis());*/
		test.siteConfigurationAction.uncheckCheckBox("allowPISItemEditFlag");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("allowPISItemEditFlag");
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(externalSystem, "search");
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(externalSystem);
		
		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(),
				TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		//test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputField(externalSystem);
		
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(facility);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		

		test.siteConfigurationAction.selectValueFromDropDown("strengthUnitOfMessureKey",getData("ExternalSystem.StrengthUOMsort"));
		test.siteConfigurationAction.selectValueFromDropDown("strengthUnitOfMessureKey",getData("ExternalSystem.StrengthUOMalpha"));
		
		test.siteConfigurationAction.selectValueFromDropDown("concentrationVolumeUnitOfMessureKey",getData("ExternalSystem.Concentrationvolumesort"));
		test.siteConfigurationAction.selectValueFromDropDown("concentrationVolumeUnitOfMessureKey",getData("ExternalSystem.Concentrationvolumealpha"));
		
		test.siteConfigurationAction.selectValueFromDropDown("totalVolumeUnitOfMessureKey",getData("ExternalSystem.Totalvolumesort"));
		test.siteConfigurationAction.selectValueFromDropDown("totalVolumeUnitOfMessureKey",getData("ExternalSystem.Totalvolumealpha"));
}

}