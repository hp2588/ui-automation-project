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

public class Story_1071249 extends BaseTest {

	@Test(priority = 1, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -Rule is set as default  which is none for CII safe ISAs..")
	public void Test1_1078752(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] -Rule is set as default  which is none for CII safe ISAs..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyDefaultRulesAreAvailable(),
				"Rules are not set rules as default.");
	}

	@Test(priority = 2, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User is able to add  and view a CII Safe ISA on edit location screen..")
	public void Test2_1078766(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User is able to add  and view a CII Safe ISA on edit location screen..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyEditButtonFunction(),
				"Edit button has been successfully clicked");
		Assert.assertTrue(test.locationAssignmentSettings.VerifyReplenish(),
				"Edit button has been successfully clicked");

	}

	@Test(priority = 3, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Default value for Replenish is set as Distributor for CII Safe ISA.")
	public void Test3_1078757(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - Default value for Replenish is set as Distributor for CII Safe ISA..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.replenishAsDistributor(),
				"Replenish default value is not found as distributor.");

	}

}
