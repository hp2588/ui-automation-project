package com.org.mainmenu.generalledger;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997968 extends BaseTest {

	ArrayList<String> previous_data, sorted_data;
	String[] columnHeaders = { "Name", "Account Number", "Status" };
	String glAccountName, glAccountNumber;

	@Test(priority = 1, description = "VPLX: GL Account: [UI] : Record is displaying alphabetically on view screen for glaccount")
	public void Test01_1038482_1038413(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Record is displaying alphabetically on view screen for glaccount");
		test.landingPageActions.navigateToFeature("General Ledger");
		previous_data = test.supportDataActions.captureDataForGlParticularColumn(getData("GlColumnNumber.Name"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrderForGl(getData("GlColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
	}

	@Test(priority = 2, description = "VPLX: GL Account: [UI] : Record on GL Account page name, number and status is displayed")
	public void Test02_1038488(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Record on GL Account page name, number and status is displayed");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("General Ledger"),
				"[ASSERTION FAILED]: Genearl Leadger Accounts Label is Not Present");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.Carousel")),
				"[ASSERTION FAILED]: Toggle Button is Not Present");
		Assert.assertTrue(test.supportDataActions.verifySearchBoxIsPresent("search"),
				"[ASSERTION FAILED]: Search Box is Not Present");
		Assert.assertTrue(test.supportDataActions.verifyButtons("add"), "[ASSERTION FAILED]: Buttons Not Present");
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaders(columnHeaders),
				"[ASSERTION FAILED]: Coulum Headers are Not Present");
		Assert.assertTrue(test.supportDataActions.verifyLinksOnUI("edit"), "[ASSERTION FAILED]: Links are Present");
	}

	@Test(priority = 3, description = "VPLX: GL Account: [UI] : User verifies the records as per alphanumerically sorted in ascending order of the field GL Account number")
	public void Test03_1038501_1073667(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : User verifies the records as per alphanumerically sorted in ascending order of the field GL Account number");
		previous_data = test.supportDataActions
				.captureDataForGlParticularColumn(getData("GlColumnNumber.Name"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrderForGl(getData("GlColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Gl account number column data is not sorted in ascending order");
	}

	@Test(priority = 4, description = "VPLX: GL Account: [UI] : Record should display only the active ones by default.")
	public void Test04_1038504_1038447(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Record should display only the active ones by default.");
		Assert.assertTrue(test.supportDataActions.verifyGlStatusAsActive());
	}

	@Test(priority = 5, description = "VPLX: Manage GL Account: [UI] : User verifies the sorting on the page on view and search GL accounts")
	public void Test05_1038531(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage GL Account: [UI] : User verifies the sorting on the page on view and search GL accounts");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.verifyGlStatus();
		test.supportDataActions.verifyAndClickSortIcon(getData("GlColumnName.Name"));
		previous_data = test.supportDataActions.captureDataForGlParticularColumn(getData("GlColumnNumber.Name"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInDescendingOrderForGL(getData("GlColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in descending order");
		test.supportDataActions.verifyAndClickSortIcon(getData("GlColumnName.Status"));
		test.supportDataActions.verifyAndClickSortIcon(getData("GlColumnName.Status"));
		previous_data = test.supportDataActions.captureDataForGlParticularColumn(getData("GlColumnNumber.Status"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInDescendingOrderForGL(getData("GlColumnNumber.Status"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Status column data is not sorted in descending order");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
	}

	@Test(priority = 6, description = "VPLX: GL Account: [UI] : User is able to toggle and include/exclude the inactive GL Accounts in the listing")
	public void Test06_1038533(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : User is able to toggle and include/exclude the inactive GL Accounts in the listing");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		glAccountName = test.supportDataActions.EnterRandomValueInInputField("description",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		glAccountNumber = test.supportDataActions.EnterRandomValueInInputField("number",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.GLAccount"));
		test.supportDataActions.selectValueFromDropDownForPISSystem("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyGlStatus());
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyGlStatusAsActive());
	}
	
	@Test(priority = 7, description = "VPLX: GL Account: [UI]: GL Account-View: Sort icon gets removed on sorted column if user searches unmatched data")
	public void Test07_1045882(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI]: GL Account-View: Sort icon gets removed on sorted column if user searches unmatched data");
		test.supportDataActions.enterSearchTermInSearchFieldGl(DateUtil.getTommorrowsDate(), "search");	
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("GlColumnNumber.Name")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("GlColumnNumber.AccountNumber")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("GlColumnNumber.Status")));
	}

}
