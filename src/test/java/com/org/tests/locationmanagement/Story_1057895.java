package com.org.tests.locationmanagement;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

import junit.framework.Assert;

public class Story_1057895 extends BaseTest {

	String itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName").trim(),
			facility = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),
			isaName = TestDataPropertyReaderAndWriter.getProperty("ISAName").trim();

	@Test(priority = 1, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User gets an option to remove a rack/shelf by clicking on three dots if no item is assigned.")
	public void Test01_1077204(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User gets an option to remove a rack");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facility);
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", facility);
		test.siteConfigurationAction.selectValueForDropDown("isa", isaName);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.verifyUserIsOnLayoutPage();
// Add Rack
		test.siteConfigurationAction.clickDotsOnItemLocation("isa_actions");
		test.siteConfigurationAction.clickAddRacksOrShelfOrBin("Add Rack");
		
		test.siteConfigurationAction.selectValueFromDropDown("selectedRack", "Rack 2");
		test.siteConfigurationAction.clickDotsOnItemLocation("rack_actions");
		test.siteConfigurationAction.verifyActionsOnDots("Remove rack");

	}

	@Test(priority = 2, description = "VPLX:Location Assignment - Managing Storage Area: [UI]:  User is allowed to remove a Rack if there are no items assigned to the Rack.")
	public void Test02_1077207(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]:  User is allowed to remove a Rack if there are no items assigned to the Rack");
		test.siteConfigurationAction.clickAddRacksOrShelfOrBin("Remove rack");
		Assert.assertTrue(test.siteConfigurationAction.verifyRackIsRemoved("selectedRack", "Rack 2"));

	}
}
