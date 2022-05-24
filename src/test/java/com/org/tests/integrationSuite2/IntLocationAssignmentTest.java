package com.org.tests.integrationSuite2;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class IntLocationAssignmentTest extends BaseTest {

	@Test(priority = 1, description = "Assign Location")
	public void Test01_AssignLocation(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
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
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.navigationButtonOnItemManagement("editCancel");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.selectRoutingRule("pickRoutingRuleKey_1", TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName"));
		test.siteConfigurationAction.navigationButtonOnItemManagement("editSave");
	}
	
	@Test(priority = 2, description = "VPLX: Manage Routing Rules:[Integration]:[UI]-Routing Rule is added in multiple locations.")
	public void Test02_1153157(Method method) {
		//test.landingPageActions.navigateToFeature("Main Menu");
		//test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName1"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ItemName1"));
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
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.navigationButtonOnItemManagement("editCancel");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName1"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ItemName1"));
		test.supportDataActions.selectRoutingRule("pickRoutingRuleKey_1", TestDataPropertyReaderAndWriter.getProperty("RoutingRuleName"));
		test.siteConfigurationAction.navigationButtonOnItemManagement("editSave");
	}
}
