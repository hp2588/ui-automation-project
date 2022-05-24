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

public class Story_1061989 extends BaseTest {

	@Test(priority = 1, description = "VPLX:Location Assignment - Managing Storage Area: [UI]:When a user clicks on \"Go to Carousel,\" the carousel should move.")
	public void Test01_1138088(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]:When a user clicks on \"Go to Carousel,\" the carousel should move.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.verifyCarouselSelf();
		Assert.assertTrue(test.locationAssignmentSettings.verifyCarouselSelf(),
				"User is not  able to view carousel self.");

	}

	@Test(priority = 2, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: Go to carousel shelf is visible on shelf actions..")
	public void Test02_1135179(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: Go to carousel shelf is visible on shelf actions.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.verifyCarouselSelf();
		Assert.assertTrue(test.locationAssignmentSettings.verifyCarouselSelf(),
				"User is not  able to view carousel self.");

	}

	@Test(priority = 3, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to remove a Bin if there are no items assigned to the Rack.")
	public void Test03_1077209(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: Go to carousel shelf is visible on shelf actions.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.locationAssignmentSettings.addRack();
		Assert.assertTrue(test.locationAssignmentSettings.removebin(), "User is not able to remove the bin option.");

	}

}
