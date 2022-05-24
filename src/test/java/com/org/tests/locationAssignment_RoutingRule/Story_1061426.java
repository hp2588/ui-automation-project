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

public class Story_1061426 extends BaseTest {
	
	@Test(priority = 1, description = "VPLX:Location Assignment-Managing Storage Area: UI: User is allowed to apply the left offset of the selected shelf  to all shelves within the Carousel ISA.. ")
	public void Test1_1124393(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment-Managing Storage Area: UI: User is allowed to apply the left offset of the selected shelf  to all shelves within the Carousel ISA..");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.enterQOHDetails(),
				"User is unable to add all offset.");
	}
	@Test(priority = 2, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] -User is able to unassign a item successfully from a location . ")
	public void Test3_1125290(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area(Bin Actions): [UI] -User is able to unassign a item successfully from a location .");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.clickOnEditLinkCorresspondingToAssignedItem(),
				"User is unable to assign the location on availabnle slot");
	}
	@Test(priority = 3, description = "VPLX: Instant Returns: [UI]: User is able to complete the transaction whose QOH after completion will become greater than Maximum quantity set at assigning location to the item and QOH is updated on location ")
	public void Test3_1136212(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Instant Returns: [UI]: User is able to complete the transaction whose QOH after completion will become greater than Maximum quantity set at assigning location to the item and QOH is updated on location.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.enterQOHDetails(),
				"User is unable to add the slots");
	}
	
	
	
	

}
