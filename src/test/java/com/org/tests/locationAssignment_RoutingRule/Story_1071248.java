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

public class Story_1071248 extends BaseTest {

	@Test(priority = 1, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User verifies the replenishment options when item is a slowmover prepack item.")
	public void Test1_1125279(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User verifies the replenishment options when item is a slowmover prepack item.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.VerifyReplenish(),
				"Replenish options are not available for the facility.");
	}
	
	
	@Test(priority = 2, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User verifies the replenishment options when a regular item is assigned to a CATO ISA ..")
	public void Test2_1125277(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User verifies the replenishment options when a regular item is assigned to a CATO ISA ..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.chooseReplenishOptions(),
				"Replenish options are not available for the facility.");
	}
	
	

	@Test(priority = 3, description = "VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User verifies the replenishment options when item is a standard prepack item.")
	public void Test3_1125280(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Location Assignment -Settings Rules and Reorder/Replenishment Location: [UI] - User verifies the replenishment options when item is a standard prepack item..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.removebin(),
				"User  is not able to delete the locaiton set.");
	}
	
	
	

}
