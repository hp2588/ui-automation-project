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
public class Story_1061986 extends BaseTest{
	
	@Test(priority = 1, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: All -Print All labels is available within shelf actions.")
	public void Test01_1135182(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: All -Print All labels is available within shelf actions..");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyPrintBinOptionsonSelfAction(),
				"User is not  able to see the print bin option in self action.");

	}

	
	@Test(priority = 2, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: Print Bin labels is available on shelf actions..")
	public void Test02_1135181(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: Print Bin labels is available on shelf actions.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyPrintBinOptionsonSelfAction(),
				"User is not  able to view the print bin option in self action");

	}
	
	
	
	@Test(priority = 3, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to remove a Bin if there are no items assigned to the Rack.")
	public void Test03_1077209(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to remove a Bin if there are no items assigned to the Rack.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.removebin(),
				"User is not  able to remove bin");

	}
	
	@Test(priority = 4, description = "VPLX:Location Assignment - Managing Storage Area: [UI]:  User is not allowed to remove a Rack if there are items assigned to the Rack.")
	public void Test04_1151348(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]:  User is not allowed to remove a Rack if there are items assigned to the Rack.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.removeRack(),
				"User is not  able to remove rack");

	}
	
	@Test(priority = 5, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is not allowed to remove a Shelf if items assigned to the shelf")
	public void Test05_1151349(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User is not allowed to remove a Shelf if items assigned to the shelf.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.removeShelf(),
				"User is not  able to remove shelf");

	}
	
	@Test(priority = 6, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is not allowed to remove a Bin if there are items assigned to the Rack.")
	public void Test06_1151350(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User is not allowed to remove a Bin if there are items assigned to the Rack..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.setLocationAndVerify(),
				"User is not  able to remove bin if location has been assigned");
	}
	
	
	

	

}
