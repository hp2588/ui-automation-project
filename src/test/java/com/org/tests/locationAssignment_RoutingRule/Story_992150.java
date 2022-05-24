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

public class Story_992150 extends BaseTest {

	@Test(priority = 1, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - User is able to view the Priorties for the routing rule on the Rules pop-up box..")
	public void Test01_1071154(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - User is able to view the Priorties for the routing rule on the Rules pop-up box.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyDefaultRules(),
				"User is unable to see the default rule options.");

	}
	@Test(priority = 2, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Locations-[UI] -Rule button opens the list of rules for the specific facility..")
	public void Test02_1071134(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Locations-[UI] -Rule button opens the list of rules for the specific facility.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.rulesForSpecficLocation(),
				"User is unable to see the rules for specific location");

	}
	@Test(priority = 3, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - View Rules Pop-up gets closed when user clicks on close button..")
	public void Test03_1071161(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - View Rules Pop-up gets closed when user clicks on close button..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.closeRulesPopUP(),
				"User is unable to close the rules pop up displayed");

	}

	@Test(priority = 4, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - User is able to view the Schedules for the routing rule on the Rules pop-up box.")
	public void Test04_1071148(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - User is able to view the Schedules for the routing rule on the Rules pop-up box..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.ruleViewOpen(),
				"User is unable to view the schedule rules information");

	}

	@Test(priority = 5, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - User is able to view the rule name on the Rules pop-up box..")
	public void Test05_1071146(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] - User is able to view the rule name on the Rules pop-up box.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.ruleViewOpen(),
				"User is unable to view the schedule rules information");
		Assert.assertTrue(test.locationAssignmentSettings.verifyRulesAreViewable(),
				"User is unable to view the schedule rules information");

	}

	@Test(priority = 6, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] -User is able to select rules from edit screen where rules are listed.")
	public void Test06_1071165(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location-[UI] -User is able to select rules from edit screen where rules are listed.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.ruleViewOpen(),
				"User is unable to view the schedule rules information");
		Assert.assertTrue(test.locationAssignmentSettings.viewRuleList(),
				"User is unable to view the schedule rules information");

	}

}
