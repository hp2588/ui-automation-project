package com.org.tests.mainmenu.wastereasons;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997994 extends BaseTest {

	ArrayList<String> previous_data, sorted_data;
	String[] columnHeaders = { "Name", "Status" };

	@Test(priority = 1, description = "VPLX:Waste Reason:[UI]-User verifies the view waste reason page on load.")
	public void Test01_1031454(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User verifies the view waste reason page on load.");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Waste Reasons");
		test.supportDataActions.verifyLabelIsPresent("Waste Reason");

		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.Carousel")),
				"[ASSERTION FAILED]: Toggle Button is Not Present");		
      	previous_data = test.supportDataActions.captureDataForParticularColumn(getData("WasteReasonColumnNumber.Name")); 
      	sorted_data = test.supportDataActions.sortDataForParticularColumnInAscendingOrder(getData("WasteReasonColumnNumber.Name")); 
      	Assert.assertEquals(previous_data,sorted_data,"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
		
      	Assert.assertTrue(test.supportDataActions.verifyWasteReasonStatusAsActive());

	}

	@Test(priority = 2, description = "VPLX:Waste Reason:[UI]-User verifies the columns on the waste reason list page")
	public void Test02_1031460(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User verifies the columns on the waste reason list page");

		Assert.assertTrue(test.supportDataActions.verifyWasteReasonColumnHeaders(columnHeaders),
				"[ASSERTION FAILED]: Column Headers are Not Present");

	}

	@Test(priority = 3, description = "VPLX:Waste Reason:[UI]-User verifies the toggle -Show inactive as ON")
	public void Test03_1031466(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User verifies the toggle -Show inactive as ON");

		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyWasteReasonStatus());

		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("WasteReasonColumnNumber.Name"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder(getData("WasteReasonColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
	}

	@Test(priority = 4, description = "VPLX:Waste Reason:[UI]-User verifies the toggle -Show inactive as OFF")
	public void Test04_1031469(Method method) throws InterruptedException {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User verifies the toggle -Show inactive as OFF");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyWasteReasonStatusAsActive());

		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("WasteReasonColumnNumber.Name"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder(getData("WasteReasonColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
	}

	
	@Test(priority = 5, description = "VPLX:Waste Reason:[UI]-Sorting on the view page is working")
	public void Test05_1031472(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(), "VPLX:Waste Reason:[UI]-Sorting on the view page is working");
		test.supportDataActions.verifyAndClickSortIconWasteReasonName(getData("WasteReasonColumnName.Name"));
		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("WasteReasonColumnNumber.Name"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInDescendingOrder(getData("WasteReasonColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in descending order");
		Assert.assertTrue(test.supportDataActions.verifyWasteReasonStatus());

	}
}
