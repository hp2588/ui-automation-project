package com.org.tests.mainmenu.labelsizedefinitions;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975752 extends BaseTest {
	
	String placeHolder, name, labelWidth, labelHeight, firstData;
	ArrayList<String> previous_data, sorted_data;
	
	@Test(priority = 1, description = "VPLX:Define Label Stock:[UI]:All active/inactive stocks is getting displayed of stock definition when the toggle button is turned on")
	public void Test01_1039104(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:[UI]:All active/inactive stocks is getting displayed of stock definition when the toggle button is turned on");
		test.landingPageActions.navigateToFeature("Label Size Definitions");
		test.supportDataActions.verifyAddButtonOnPage();
		test.supportDataActions.clickAddButtonOnDistributor("add");
		name = test.supportDataActions.enterValueOnAddDistributorPage("stockLabelName",
				"Stock" + System.currentTimeMillis());
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.HoldReason"));
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelWidth", "4.5");
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelHeight", "10");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Size Definitions"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyGlStatus());
	}
	
	@Test(priority = 2, description = "VPLX:Define Label Stock:[UI]:All active stocks is getting displayed of stock definition when the toggle button is turned off")
	public void Test02_1039106(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:[UI]:All active stocks is getting displayed of stock definition when the toggle button is turned off");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyGlStatusAsActive());
	}
	
	@Test(priority = 3, description = "VPLX: Define label stock: [UI]: Feature testing -User is able to sort on all the columns of Stock Definition view and search page.")
	public void Test03_1045442(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Define label stock: [UI]: Feature testing -User is able to sort on all the columns of Stock Definition view and search page.");
		test.supportDataActions.verifyAndClickSortIcon(getData("LabelColumnName.Name"));
		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("LabelColumnNumber.Name"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder(getData("LabelColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
		test.supportDataActions.verifyAndClickSortIcon(getData("LabelColumnName.Label"));
		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("LabelColumnNumber.Label"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder(getData("LabelColumnNumber.Label"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Label column data is not sorted in ascending order");
		test.supportDataActions.verifyAndClickSortIcon(getData("LabelColumnName.Status"));
		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("LabelColumnNumber.Status"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder(getData("LabelColumnNumber.Status"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Status column data is not sorted in ascending order");
	}

}
