package com.org.tests.mainmenu.distributors;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_976056 extends BaseTest {
	
	ArrayList<String> previous_data, sorted_data;
	String firstData, dataEnteredName, dataEnteredCode;
	
	@Test(priority = 1, description = "VPLX:Manage Distributors:[UI]:User verifies the visual design for the page distributors.")
	public void Test01_1031499(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the visual design for the page distributors.");
		
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.verifyAddButtonOnManageDistributor();
		test.supportDataActions.verifySearchBoxOnDistributor("search");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Name");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Status");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Type");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Actions");
		
	}
	
	@Test(priority = 2, description = "VPLX:Manage Distributors:[UI]:User verifies the list of distributors "
			+ "by last updated time.")
	public void Test02_1031782(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the list of distributors by last updated time.");
		
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "sc1@#");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "sc1@#");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.GLAccount"));
		test.supportDataActions.clickAddButtonOnDistributor("save");
		
		firstData = test.supportDataActions.firstDataOnDistributor("1");
		Assert.assertEquals(firstData, dataEnteredName);
		
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		
	}
	
	@Test(priority = 3, description = "VPLX:Manage Distributors:[UI]:User verifies the list of active distributors on distributors page.")
	public void Test03_1031572(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the list of active distributors on distributors page.");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "sc1@#");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "sc1@#");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyGlStatusAsActive());
		
	}
	
	@Test(priority = 4, description = "VPLX:Manage Distributors:[UI]:User verifies the Add button on distributors page.")
	public void Test04_1031579(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Add button on distributors page.");
		test.supportDataActions.verifyAddButtonOnManageDistributor();
	}
	
	@Test(priority = 5, description = "VPLX:Manage Distributors:[UI]:User verifies the combined list of active & Inactive distributors.")
	public void Test05_1031512(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the combined list of active & Inactive distributors.");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyGlStatus());
	}
	
	@Test(priority = 6, description = "VPLX:Manage Distributors:[UI]:User verifies the column Name on distributors page.")
	public void Test06_1031785(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the column Name on distributors page.");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Name");
		test.supportDataActions.columnDataOnManageDistributor("3");
	}
	
	@Test(priority = 7, description = "VPLX:Manage Distributors:[UI]:User verifies the column Type on distributors page.")
	public void Test07_1031786(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the column Type on distributors page.");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Type");
		test.supportDataActions.columnDataOnManageDistributor("2");
	}
	
	@Test(priority = 8, description = "VPLX:Manage Distributors:[UI]:User verifies the column Status on distributors page.")
	public void Test08_1031787(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the column Status on distributors page.");
		test.supportDataActions.verifyColumnHeaderOnDosageForm("Status");
		test.supportDataActions.columnDataOnManageDistributor("1");
	}
	
	@Test(priority = 9, description = "VPLX:Manage Distributors:[UI]:User clicks on sort arrows on the column 'Name' ,'Type' and 'Status' on distributor page.")
	public void Test09_1031789(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User clicks on sort arrows on the column 'Name' ,'Type' and 'Status' on distributor page.");
		test.supportDataActions.verifyAndClickSortIcon("Name");
		previous_data = test.supportDataActions.captureDataForParticularColumn("3");
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder("3");
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
		test.supportDataActions.verifyAndClickSortIcon("Type");
		previous_data = test.supportDataActions.captureDataForParticularColumn("2");
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder("2");
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Type column data not is sorted in ascending order");
		test.supportDataActions.verifyAndClickSortIcon("Status");
		previous_data = test.supportDataActions.captureDataForParticularColumn("1");
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder("1");
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Status column data is not sorted in ascending order");
	}
	
}
