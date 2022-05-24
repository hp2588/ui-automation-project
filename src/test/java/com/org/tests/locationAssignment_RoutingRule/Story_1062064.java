package com.org.tests.locationAssignment_RoutingRule;

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

public class Story_1062064 extends BaseTest {
	@Test(priority = 1, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Replenish can be set as distributor for multiple locations for a regular item.")
	public void Test1_1091220(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Replenish can be set as distributor for multiple locations for a regular item.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		/*
		
		test.landingPageActions.navigateToFeature("Item Locations");
		//test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown","My Facilities");	
		test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown", 1);
		test.locationAssignmentSettings.editClickButton();
		//test.supportDataActions.clickOnEditLinkCorresspondingToItem("345", "345");
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		//test.siteConfigurationAction.selectValueForDropDown("facility", "My Facilities");
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("isa", 1);
		//test.siteConfigurationAction.selectValueForDropDown("isa", "OldISA1");
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		
		*/
		
		
		Assert.assertTrue(test.locationAssignmentSettings.VerifyReplenish(),
				"Replenish options can not be chosen by user..");
	}

	@Test(priority = 2, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - When item is a prepack slowmover item default replenishment option is Bulk Item ")
	public void Test2_1091227(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - When item is a prepack slowmover item default replenishment option is Bulk Item ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.chooseReplenishOptions(),
				"Replenish options can not be chosen by user..");
		Assert.assertTrue(test.locationAssignmentSettings.ReplenishSetToNone(),
				"Replenish seetings are not working fine.");

	}

	@Test(priority = 3, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment LocationLocation-Storage Area: [UI] - When prepack fastmover item is assigned to multiple locations default is one of other PLX ISA locations within the facility. ")
	public void Test3_1091234(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User is able to view list of other ISAs within same facility as replenishment options when item is a prepack slowmover item when bulk item location is currently in use.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.chooseReplenishOptions(),
				"Replenish options can not be chosen by user..");
	}

	@Test(priority = 4, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -Default replenish option will be distributor for single location when item is a regular item. ")
	public void Test4_1091215(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -Default replenish option will be distributor for single location when item is a regular item. ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.chooseReplenishOptions(),
				"Replenish options can not be chosen by user..");
	}

	@Test(priority = 5, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -  When item is a prepack slowmover item ,distributor is not available as replenishment options. ")
	public void Test5_1091226(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -  When item is a prepack slowmover item ,distributor is not available as replenishment options. ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyDistributorNonAvailability(),
				"Replenish options can not be chosen by user..");
	}

	@Test(priority = 6, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - When prepack fastmover item is assigned to multiple locations ,for first location it will  ")
	public void Test6_1091233(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - When prepack fastmover item is assigned to multiple locations ,for first location it will  ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.setLocationAndVerify(),
				"Setting up the location for the items are not working proper.");
	}

	@Test(priority = 7, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Replenish can be set as none for multiple locations for a regular item.  ")
	public void Test7_1091222(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Replenish can be set as none for multiple locations for a regular item. ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.ReplenishSetToNone(),
				"Replenish not able to set as non for given location.");
	}

	@Test(priority = 8, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User is able to view list of other ISAs within same facility as replenishment options when item is a prepack slowmover item when bulk item location is currently in use. ")
	public void Test8_1091229(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User is able to view list of other ISAs within same facility as replenishment options when item is a prepack slowmover item when bulk item location is currently in use. ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.getISAList(), "USer is not able to see the ISA options .");
	}

	@Test(priority = 9, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -To Verify When deleting one or more locations when item is a prefpack fastmover system enforces the condition that one location should be assigned a distributor. ")
	public void Test9_1091236(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -To Verify When deleting one or more locations when item is a prefpack fastmover system enforces the condition that one location should be assigned a distributor. ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.setLocationAndVerify(),
				"Setting up the location for the items are not working proper.");

	}

	@Test(priority = 10, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -When deleting one or more locations system enforces the condition that one location is assigned the Bulk item location for prepack slowmover item. ")
	public void Test10_1091231(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -When deleting one or more locations system enforces the condition that one location is assigned the Bulk item location for prepack slowmover item.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.setLocationAndVerify(),
				"Setting up the location for the items are not working proper.");
		Assert.assertTrue(test.locationAssignmentSettings.defaultRuleAssignmentToLocation(),
				"Setting up the location for the items are not working proper.");

	}

	@Test(priority = 11, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User is able to select none as replenishment option when item is a prepack slowmover item. ")
	public void Test11_1091240(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User is able to select none as replenishment option when item is a prepack slowmover item..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.setLocationAndVerify(),
				"Setting up the location for the items are not working proper.");
		Assert.assertTrue(test.locationAssignmentSettings.ReplenishSetToNone(),
				"User is not able to set replenish as none.");

	}

	@Test(priority = 12, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User is able to edit the replenish location for multiple locations before saving the changes for a regular item. ")
	public void Test12_1091223(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User is able to edit the replenish location for multiple locations before saving the changes for a regular item..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.setLocationAndVerify(),
				"Setting up the location for the items are not working proper.");
		Assert.assertTrue(test.locationAssignmentSettings.editLocation(), "User is not able to edit the location");

	}

	@Test(priority = 13, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - If item is assigned to only one location , replenishment is bulk item location.. ")
	public void Test13_1091230(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - If item is assigned to only one location , replenishment is bulk item location..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.VerifyReplenish(),
				"Replenish are not available in option list");
	}

	@Test(priority = 14, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -User is able to select none as replenishment option when item is  prepack fastmover .. ")
	public void Test14_1091239(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -User is able to select none as replenishment option when item is  prepack fastmover ..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.ReplenishSetToNone(),
				"Replenish are not able to set as none");

	}  
	
	@Test(priority = 15, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Replenish can be set as distributor for multiple locations for a regular item. ")
	public void Test15_1091220(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -User is able to select none as replenishment option when item is  prepack fastmover ..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.setLocationAndVerify(),
				"Replenish can not be set to multiple location");

	}
	
	@Test(priority = 16, description = "VPLX Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User cannot select replenishment option as CII safe or CATO when item is a prepack slowmover item. ")
	public void Test16_1091220(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User cannot select replenishment option as CII safe or CATO when item is a prepack slowmover item..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.setLocationAndVerify();
		Assert.assertTrue(test.locationAssignmentSettings.VerifyReplenish(),
				"Replenish can not be set for CII safe or CATO");

	}

}
