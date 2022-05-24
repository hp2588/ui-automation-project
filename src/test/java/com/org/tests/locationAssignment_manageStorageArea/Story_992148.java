package com.org.tests.locationAssignment_manageStorageArea;

import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_992148 extends BaseTest {

	@Test(priority = 1, description = "VPLX:Location Assignment - Managing Storage Area: [UI]:Validation Error does not occur when user enters QOH more than or equal to max quantity.")
	public void Test01_1136902(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]:Validation Error does not occur when user enters QOH more than or equal to max quantity.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyErrorMessageForRacks(),
				"User is not able to see the required error messsage.");

	}

	@Test(priority = 2, description = "VPLX:Location Assignment - Managing Storage Area: [UI]:Validation Error does not occur when user enters QOH less than or equal to min quantity.")
	public void Test02_1136900(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]:Validation Error does not occur when user enters QOH less than or equal to min quantity.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.clickOnEditLinkCorresspondingToAssignedItem();
		Assert.assertTrue(test.locationAssignmentSettings.enterQOHDetails(),
				"User is not able to see the required error messsage.");

	}

	@Test(priority = 3, description = "VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System prevents a user from manually removing a Non-PLX location.")
	public void Test03_1071097(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System prevents a user from manually removing a Non-PLX location.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.setLocationAndVerify(),
				"User is not able to to assign location");

	}

	@Test(priority = 4, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User edits the min quantity on edit location screen.")
	public void Test04_1136899(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User edits the min quantity on edit location screen.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.clickOnEditLinkCorresspondingToAssignedItem();
		Assert.assertTrue(test.locationAssignmentSettings.MinQtyvsMaxQty(),
				"User is not able to enter min qty less than max qty.");

	}

	@Test(priority = 5, description = "VPLX:Location Assignment - Managing Storage Area : [UI]: User edits max quantity on edit location screen.")
	public void Test05_1136898(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area : [UI]: User edits max quantity on edit location screen.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.clickOnEditLinkCorresspondingToAssignedItem();
		Assert.assertTrue(test.locationAssignmentSettings.MinQtyvsMaxQty(),
				"User is not able to enter maximum quantity more than min qty.");

	}

	@Test(priority = 6, description = "VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System prevents a user from manually removing a Non-PLX location.")
	public void Test06_1071116(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System prevents a user from manually removing a Non-PLX location.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.setLocationAndVerify(),
				"User is not able to to assign location");

	}

	@Test(priority = 7, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is able to edit the quantity on hand on edit location screen.")
	public void Test07_1136897(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User is able to edit the quantity on hand on edit location screen..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.clickOnEditLinkCorresspondingToAssignedItem();
		Assert.assertTrue(test.locationAssignmentSettings.editQOH(), "User is not able to edit the QOH");

	}

	@Test(priority = 8, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is able to edit the quantity on hand on edit location screen.")
	public void Test08_1071095(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User is able to edit the quantity on hand on edit location screen..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.setLocationAndVerify(),
				"User is not able to to assign location");
	}

	@Test(priority = 9, description = "VPLX:Location Assignment - Managing Storage Area-Edit Location: UI:System displays all the fields for each Non PLX location:")
	public void Test09_1071046(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area-Edit Location: UI:System displays all the fields for each Non PLX location:.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.landingOnEditScreen();
		Assert.assertTrue(test.locationAssignmentSettings.verifyItemsID_Facility_QOH(),
				"item id, facility and QOH details are not displaying");

	}

	@Test(priority = 10, description = "VPLX:Location Assignment - Managing Storage Area-Edit Location: UI:System displays list of assigned location(s) for each Facility")
	public void Test10_1071039(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area-Edit Location: UI:System displays list of assigned location(s) for each Facility");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.landingOnEditScreen();
		Assert.assertTrue(test.locationAssignmentSettings.setLocationAndVerify(), "User is not able to set location");

	}

	@Test(priority = 11, description = "VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System prevents a user from manually removing a Non-PLX location.")
	public void Test11_1071116(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System prevents a user from manually removing a Non-PLX location.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.landingOnEditScreen();

	}

	@Test(priority = 12, description = "VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System prevents a user from manually removing a Non-PLX location..")
	public void Test12_1071116(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System prevents a user from manually removing a Non-PLX location.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.landingOnEditScreen();

	}

	@Test(priority = 13, description = "VPLX: Location Assignment - Managing Storage Area: [UI]: User is able to enter QOH Value(0-999999) less than Min Quantity.")
	public void Test13_1150988(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment - Managing Storage Area: [UI]: User is able to enter QOH Value(0-999999) less than Min Quantity.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.landingOnEditScreen();
		test.locationAssignmentSettings.editQOH();

	}

	@Test(priority = 14, description = "VPLX: Location Assignment - Managing Storage Area: [UI]: User is able to enter QOH Value(0-999999) greater than Max Quantity")
	public void Test14_1150989(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment - Managing Storage Area: [UI]: User is able to enter QOH Value(0-999999) greater than Max Quantity");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.landingOnEditScreen();
		test.locationAssignmentSettings.editQOH();

	}

	@Test(priority = 15, description = "VPLX: Location Assignment - Managing Storage Area : [UI]-When Generic name and Strength fields updated for an item,  gets displayed in Item locations listing page")
	public void Test15_1151203(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment - Managing Storage Area : [UI]-When Generic name and Strength fields updated for an item,  gets displayed in Item locations listing page");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.landingOnEditScreen();
		test.locationAssignmentSettings.verifyGenericName();

	}

	@Test(priority = 16, description = "VPLXLocation Assignment - Managing Storage Area : [UI]-If Strength UOM updated for an item, gets displayed in Item locations listing page")
	public void Test16_1151204(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLXLocation Assignment - Managing Storage Area : [UI]-If Strength UOM updated for an item, gets displayed in Item locations listing page");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.landingOnEditScreen();
		test.locationAssignmentSettings.verifyGenericName();
	}

}
