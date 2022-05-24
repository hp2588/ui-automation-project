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

public class Story_1073923 extends BaseTest {

	@Test(priority = 1, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User checks the rules options available for selection .")
	public void Test01_1079582(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User checks the rules options available for selection .");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyDefaultRules(),
				"User is unable to see the default rule options.");
	}

	@Test(priority = 2, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - User is able to view the Destinations for the routing rule on the Rules pop-up box.")
	public void Test02_1071151(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - User is able to view the Destinations for the routing rule on the Rules pop-up box.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.viewRuleList(),
				"User is not able to see the listed rules on item locations");

	}
	@Test(priority = 3, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Replenish will be set as none for CII Safe ISA when  its minimum quantity is reached.")
	public void Test03_1078773(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Replenish will be set as none for CII Safe ISA when  its minimum quantity is reached.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.ReplenishSetToNone(),
				"User is not able to see the listed rules on item locations");

	}

	@Test(priority = 4, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -  User is able to view other PLX ISA locations within same facility in dropdown as replenish .")
	public void Test04_1091225(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -  User is able to view other PLX ISA locations within same facility in dropdown as replenish .");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.VerifyReplenish(),
				"User is not able to see the Replenishment options");

	}

	@Test(priority = 5, description = "PLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Rule is set as none for CATO ISA for all types of items.")
	public void Test05_1125275(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"PLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Rule is set as none for CATO ISA for all types of items.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyEditButtonFunction(),
				"User is not able to see the Replenishment options");

	}

	@Test(priority = 6, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - When item is a fastmover prepack item default value for replenishment option is ")
	public void Test06_1091232(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - When item is a fastmover prepack item default value for replenishment option is ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.VerifyReplenish(),
				"User is not able to see the Replenishment options");

	}

	@Test(priority = 7, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User is able to set replenish as neighbouring ISA for a regular item present in multiple locations ")
	public void Test07_1091243(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User is able to set replenish as neighbouring ISA for a regular item present in multiple locations ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.chooseReplenishOptions(),
				"User is not able to see the Replenishment options");

	}

	@Test(priority = 8, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - System allows a user to edit the rules for multiple locations for an item, before saving the changes.")
	public void Test08_1079592(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - System allows a user to edit the rules for multiple locations for an item, before saving the changes. ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verificationOfEditExisitingRules(),
				"User is not able to see the Replenishment options");

	}

	@Test(priority = 9, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - User has an option to open the routing rules assosciated to the location selected.")
	public void Test09_1071132(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - User has an option to open the routing rules assosciated to the location selected. ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyRulesAreViewable(),
				"User is not able to view the rules available.");

	}

	@Test(priority = 10, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - System displays the Routing Rules in ascending order by name.")
	public void Test10_1071159(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - System displays the Routing Rules in ascending order by name. ");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyDefaultRulesAreAvailable(),
				"All the available rules are not seeing on rule list.");

	}

	@Test(priority = 11, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Default Rule can be assigned to only one location..")
	public void Test11_1079586(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Default Rule can be assigned to only one location.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.defaultRuleAssignmentToLocation(),
				"items are not allowed to set location.");

	}

	@Test(priority = 12, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -  Value of Rule will be \"Default\" if the item is stored at single location in the facility.")
	public void Test12_1079597(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -  Value of Rule will be \"Default\" if the item is stored at single location in the facility.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.rulesForSinlgeLocation(),
				"items are not allowed to set location.");

	}

	@Test(priority = 13, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Value of Rule will be set as none for other locations in same facility if item is available at multiple locations.")
	public void Test13_1079607(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Value of Rule will be set as none for other locations in same facility if item is available at multiple locations.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.RulesSetToNone(), "items are not allowed to set location.");

	}

	@Test(priority = 14, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -Value of Rule will be \"Default\" for first location if item is available at multiple locations.")
	public void Test14_1079600(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -Value of Rule will be \"Default\" for first location if item is available at multiple locations.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyDefaultRulesAreAvailable(),
				"User is not able to set rules as default.");

	}

}
