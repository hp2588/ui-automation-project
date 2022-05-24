package com.org.tests.astarratedbugs;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class LocationAssignmentTest extends BaseTest {

	@Test(priority = 2, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test02_AssignLocation(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName1"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ItemName1"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName2"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
	}

	@Test(priority = 3, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test03_AssignLocation(Method method) {
		//test.landingPageActions.navigateToFeature("Main Menu");
		//test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.navigationButtonOnItemManagement("editCancel");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName1"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ItemName1"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName3"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
	}

}
