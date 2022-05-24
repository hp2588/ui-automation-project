package com.org.tests.mainmenu.printers;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_999434 extends BaseTest {

	ArrayList<String> previous_data, sorted_data;

	@Test(priority = 1, description = "VPLX:Manage Printers:User clicks on sort arrows on the column Name and records is displaying in ascending order")
	public void Test01_1013315(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User clicks on sort arrows on the column Name and records is displaying in sorting order");
		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.verifyAndClickSortIcon(getData("PrinterColumnName.Name"));
		previous_data = test.siteConfigurationAction
				.captureDataForNameColumn(getData("PrinterColumnNumber.Name"));
		sorted_data = test.siteConfigurationAction
				.sortDataForNameColumnInAscendingOrder(getData("PrinterColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
	}

	@Test(priority = 2, description = "VPLX:Manage Printers:User clicks on sort arrows twice on the column Name and records is displaying in descending order")
	public void Test02_1013319(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User clicks on sort arrows twice on the column Name and records is displaying in descending order");
		test.siteConfigurationAction.verifyAndClickSortIcon(getData("PrinterColumnName.Name"));
		test.siteConfigurationAction.verifyAndClickSortIcon(getData("PrinterColumnName.Name"));
		previous_data = test.siteConfigurationAction
				.captureDataForNameColumn(getData("PrinterColumnNumber.Name"));
		sorted_data = test.siteConfigurationAction
				.sortDataForNameColumnInAscendingOrder(getData("PrinterColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in descending order");
	}

	@Test(priority = 3, description = "VPLX:Manage Printers:User clicks on sort arrows on the column Status and records is displaying in ascending order")
	public void Test03_1013321(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User clicks on sort arrows on the column Status and records is displaying in ascending order");
		test.siteConfigurationAction.clickActiveToggle("Show Inactive");
		test.siteConfigurationAction.verifyAndClickSortIcon(getData("PrinterColumnName.Status"));
		previous_data = test.siteConfigurationAction
				.captureDataForParticularColumn(getData("PrinterColumnNumber.Status"));
		sorted_data = test.siteConfigurationAction
				.sortDataForParticularColumnInAscendingOrder(getData("PrinterColumnNumber.Status"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Status column data is not sorted in ascending order");
	}

	@Test(priority = 4, description = "VPLX:Manage Printers:User clicks on sort arrows twice on the column Status and records is displaying in descending order")
	public void Test04_1013315(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User clicks on sort arrows twice on the column Status and records is displaying in descending order");
		//test.siteConfigurationAction.clickActiveToggle();
		test.siteConfigurationAction.verifyAndClickSortIcon(getData("PrinterColumnName.Status"));
		test.siteConfigurationAction.verifyAndClickSortIcon(getData("PrinterColumnName.Status"));
		previous_data = test.siteConfigurationAction
				.captureDataForParticularColumn(getData("PrinterColumnNumber.Status"));
		sorted_data = test.siteConfigurationAction
				.sortDataForParticularColumnInDescendingOrder(getData("PrinterColumnNumber.Status"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Status column data is not sorted in descending order");
	}

}
