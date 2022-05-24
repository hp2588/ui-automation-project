package com.org.tests.mainmenu.labelsizedefinitions;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975750 extends BaseTest {
	
	String placeHolder, name, labelWidth, labelHeight, firstData;
	ArrayList<String> previous_data, sorted_data;
	
	@Test(priority = 1, description = "VPLX: Define Label Stock: [UI]: User is able to search a stock label by entering valid values in search textbox.")
	public void Test01_1036253(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Define Label Stock: [UI]: User is able to search a stock label by entering valid values in search textbox.");
		test.landingPageActions.navigateToFeature("Label Size Definitions");
		test.supportDataActions.verifyAddButtonOnPage();
		test.supportDataActions.clickAddButtonOnDistributor("add");
		name = test.supportDataActions.enterValueOnAddDistributorPage("stockLabelName",
				"Stock" + System.currentTimeMillis());
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelWidth", "4.5");
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelHeight", "10");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Size Definitions"));
		test.supportDataActions.enterSearchTermInSearchField(name, "search");
		firstData = test.supportDataActions.firstDataOnDistributor("1");
		Assert.assertEquals(name, firstData);
		test.supportDataActions.pageRefresh();
	}
	
	@Test(priority = 2, description = "VPLX: Define Label Stock: [UI]: User is able to search inactive stock labels by enabling show inactive toggle button and entering valid values in search text field.")
	public void Test02_1036254(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Define Label Stock: [UI]: User is able to search inactive stock labels by enabling show inactive toggle button and entering valid values in search text field.");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		name = test.supportDataActions.enterValueOnAddDistributorPage("stockLabelName",
				"Stock" + System.currentTimeMillis());
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelWidth", "4.5");
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelHeight", "10");
		test.supportDataActions.clickToggleButton("true", "isActive");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Size Definitions"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(name, "search");
		firstData = test.supportDataActions.firstDataOnDistributor("1");
		Assert.assertEquals(name, firstData);
		test.supportDataActions.refreshPage();
	}
	
	@Test(priority = 3, description = "VPLX: Define Label Stock: [UI]: All records are returned when land on listing page of Stock Definition")
	public void Test03_1036256(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Define Label Stock: [UI]: All records are returned when land on listing page of Stock Definition");
		test.supportDataActions.verifyRecordList();

	}
	
	@Test(priority = 4, description = "VPLX: Define Label Stock: [UI]: No records are returned when a search is triggered with invalid/non-matching search string in the search field.")
	public void Test04_1036257(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Define Label Stock: [UI]: No records are returned when a search is triggered with invalid/non-matching search string in the search field.");
		test.supportDataActions.enterSearchTermInSearchField(DateUtil.getTommorrowsDate(), "search");
		Assert.assertTrue(test.supportDataActions.verifyRecordListEmpty());
		test.supportDataActions.refreshPage();
	}
	
	@Test(priority = 5, description = "VPLX: Define Label Stock: [UI]: User is able to sort the results on the basis of name after clicking on it.")
	public void Test05_1037541(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Define Label Stock: [UI]: User is able to sort the results on the basis of name after clicking on it.");
		test.supportDataActions.verifyAndClickSortIcon(getData("CarouselColumnName.Name"));
		previous_data = test.supportDataActions.captureDataForParticularColumn("3");
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder("3");
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
	}

}
