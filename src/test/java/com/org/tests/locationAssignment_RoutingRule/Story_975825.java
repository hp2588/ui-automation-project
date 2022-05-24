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

public class Story_975825 extends BaseTest {

	@Test(priority = 1, description = "VPLX: Item Setup (System and Facility): [Integration] [UI]: -Prepack Quantity and Pre pack fix quantity(Two text fields) is displayed on location assignment screen for item having enabled Prepack flag.")
	public void Test01_1117206(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [Integration] [UI]: -Prepack Quantity and Pre pack fix quantity(Two text fields) is displayed on location assignment screen for item having enabled Prepack flag.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.enterQOHDetails(),
				"User is unable to view the schedule rules information");

	}

	@Test(priority = 2, description = "VPLX:Location Assignment-Managing Storage Area: [UI]: Applying left offset  will copy tickbar left offset properties on all shelves (Bin Width).")
	public void Test02_1124291(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment-Managing Storage Area: [UI]: Applying left offset  will copy tickbar left offset properties on all shelves (Bin Width).");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.MinQtyvsMaxQty(), "User is unable to set offset quantity.");

	}

	@Test(priority = 3, description = "VPLX:Location Assignment-Managing Storage Area: [UI]: When user clicks on rack Set Next Rack Cycle Count Date option is visible.")
	public void Test03_1124292(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment-Managing Storage Area: [UI]: When user clicks on rack Set Next Rack Cycle Count Date option is visible.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyPrintBinOptionsonSelfAction(),
				"User is unable to set rack.");

	}
	
	@Test(priority = 4, description = "VPLX:Location Assignment-Managing Storage Area:[UI]: When user clicks on shelf  Set Next shelf Cycle Count Date option is visible..")
	public void Test04_1124293(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment-Managing Storage Area:[UI]: When user clicks on shelf  Set Next shelf Cycle Count Date option is visible..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.addBin(),
				"User is unable to see the date option on bin add screen..");

	}
	
	@Test(priority = 5, description = "VPLX:Location Assignment-Managing Storage Area: [UI]: User is able to set the date for next shelf cycle count..")
	public void Test05_1124294(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment-Managing Storage Area: [UI]: User is able to set the date for next shelf cycle count..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.addSelf(),
				"User is unable to see the date option on self screen..");

	}
	
	@Test(priority = 6, description = "VPLX:Location Assignment-Managing Storage Area:[UI]: User is able to set the date for next rack cycle count..")
	public void Test06_1124295(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment-Managing Storage Area:[UI]: User is able to set the date for next rack cycle count.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.addRack(),
				"User is unable to see the date option on rack screen..");

	}

}
