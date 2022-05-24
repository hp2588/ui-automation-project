package com.org.tests.purchasedashboarddata;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Create_2_LocationAssignment extends BaseTest {

	String itemID;
	String ISAName, facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
			ipAddress, portNumber, carouselConnectionResetTime;
	ArrayList<String> previous_data, sorted_data;

	@Test(priority = 1, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test01_1060153(Method method) throws InterruptedException {
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
	@Test(priority = 6, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test06_1060153(Method method) throws InterruptedException {
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
	}
	
}
