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

public class Story_1061960 extends BaseTest {

	@Test(priority = 1, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User gets an option to add rack on clicking on three dots icon mentioned above the dropdown.")
	public void Test01_1076659(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User gets an option to add rack on clicking on three dots icon mentioned above the dropdown.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.addRack(), "User is not  able to add rack.");

	}

	@Test(priority = 2, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User lands on Edit Location screen when click on Item edit.")
	public void Test02_1076600(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User lands on Edit Location screen when click on Item edit.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.landingOnEditScreen(),
				"User is not  able to land on edit screen.");

	}

	@Test(priority = 3, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to add bin")
	public void Test03_1076600(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to add bin.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.addSelf(), "User is not  able to shelf.");

	}

	@Test(priority = 4, description = "VPLX:Location Assignment - Managing Storage Area.: [UI]: User is allowed to add Racks")
	public void Test04_1076666(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area.: [UI]: User is allowed to add Racks");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.addRack(), "User is not  able to add rack.");

	}

	@Test(priority = 5, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to add bin")
	public void Test05_1076716(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to add bin");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.addBin(), "User is not  able to add bin.");

	}

	@Test(priority = 6, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User gets an option to add rack on clicking on three dots icon mentioned above the dropdown")
	public void Test06_1076659(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User gets an option to add rack on clicking on three dots icon mentioned above the dropdown");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.addRack(), "User is not  able to add rack");

	}

	@Test(priority = 7, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User lands on Edit Location screen when click on Item edit.")
	public void Test07_1076600(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User lands on Edit Location screen when click on Item edit.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.clickOnEditLinkCorresspondingToAssignedItem(),
				"User is not  able to land on edit screen");

	}
	
	@Test(priority = 8, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: Go to carousel shelf option is not visible on shelf actions for static ISA..")
	public void Test08_1151522(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: Go to carousel shelf option is not visible on shelf actions for static ISA.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyPrintBinOptionsonSelfAction(),
				"User is not  able to see the required options");

	}
	
	@Test(priority = 9, description = "VPLX: Location Assignment -Verify message if user make any changes in fields under \"edit item location\" and try to navigate to any of the screen without saving the data.")
	public void Test09_1152000(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Verify message if user make any changes in fields under \"edit item location\" and try to navigate to any of the screen without saving the data");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.editItemLocation(),
				"User is not  able to edit item location");

	}
	@Test(priority = 10, description = "VPLX: Location Assignment -Verify pop message if user make any changes in fields under \"edit item location\" and try to unassign location to the item")
	public void Test10_1152006(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Verify pop message if user make any changes in fields under \"edit item location\" and try to unassign location to the item");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.clickOnEditLinkCorresspondingToAssignedItem(),
				"User is not  able to edit item location");

	}
	
	
	@Test(priority = 11, description = "VPLX: Location Assignment -Verify message if user make any changes in fields under \"edit item location\" and try to Re-assign location to the item")
	public void Test11_1152008(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Verify message if user make any changes in fields under \"edit item location\" and try to Re-assign location to the item");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.enterQOHDetails(),
				"User is not  able to edit location details");

	}
	
	@Test(priority = 12, description = "VPLX: Location Assignment -Verify UI changes on \"edit item location\" as per plan")
	public void Test12_1152019(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Verify UI changes on \"edit item location\" as per plan");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.editItemLocation(),
				"User is not  able to edit location details");

	}
	
	@Test(priority = 13, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -The system enforces the condition that at least one location must have a reorder point of ‘Distributor’ when editing one or more CII Safe locations for item in facility")
	public void Test13_1152080(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -The system enforces the condition that at least one location must have a reorder point of ‘Distributor’ when editing one or more CII Safe locations for item in facility");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.replenishAsDistributor(),
				"User is not  able to use replenish function");

	}
	
	@Test(priority = 14, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -System automatically assigns 'Distributor' as default Reorder/Replenishment point of an item assigned to a CIISafe non-VPLX ISA.")
	public void Test14_1152082(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -System automatically assigns 'Distributor' as default Reorder/Replenishment point of an item assigned to a CIISafe non-VPLX ISA.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.editReplenish(),
				"User is not  able to edit replenish function");

	}
	
	@Test(priority = 15, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Default value for Replenish is set as Distributor for Cato ISA for single location.")
	public void Test15_1152088(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Default value for Replenish is set as Distributor for Cato ISA for single location");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.RulesSetToNone(),
				"User is not  able to set rules as default");

	}
	
	@Test(priority = 16, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - All Cato ISAs can be assigned distributor as replenishment option when item is assigned to multiple Cato  locations.")
	public void Test16_1152091(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - All Cato ISAs can be assigned distributor as replenishment option when item is assigned to multiple Cato  locations.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.rulesForSpecficLocation(),
				"User is not  able to set rules for specific location");

	}
	
	
	@Test(priority = 17, description = "VPLX: Location Assignment -Verify message if user make any changes in fields under \"edit item location\" and refresh the page without saving changes.")
	public void Test17_1152094(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Verify message if user make any changes in fields under \"edit item location\" and refresh the page without saving changes");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.editItemLocation(),
				"User is not  able to edit specific location");

	}
	
	@Test(priority = 18, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - None is displayed in Replenish when  its minimum quantity is reached for that location.")
	public void Test18_1152095(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - None is displayed in Replenish when  its minimum quantity is reached for that location");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.MinQtyvsMaxQty(),
				"User is not  able to set quantity");

	}
	
	@Test(priority = 19, description = "VPLX: Location Assignment -Verify message if user make any changes in fields under \"edit item location\" and close the browser without saving data")
	public void Test19_1152096(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Verify message if user make any changes in fields under \"edit item location\" and close the browser without saving data");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.editItemLocation(),
				"User is not  able to edit location");

	}
	@Test(priority = 20, description = "VPLX: Location Assignment -Validation message should be displayed if user try to unassign an item that is associated to one or more destinations")
	public void Test20_1152637(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Validation message should be displayed if user try to unassign an item that is associated to one or more destinations");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.editItemLocation(),
				"User is not  able to edit location");
	}
	
	@Test(priority = 21, description = "VPLX: Location Assignment - Unassignation of an item that is not associated to one or more destinations is successfull.")
	public void Test21_1152645(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment - Unassignation of an item that is not associated to one or more destinations is successfull.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyItemsID_Facility_QOH(),
				"User is not  able to unassign successfully");
	}
	

}
