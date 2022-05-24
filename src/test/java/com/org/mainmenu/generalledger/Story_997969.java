package com.org.mainmenu.generalledger;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997969 extends BaseTest {
	String old_data, new_data;
	String glAccountName, glAccountNumber; 

	@Test(priority = 1, description = "VPLX: GL Account: [UI] : Record is displaying when user search the data on search screen")
	public void Test01_1038440(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account: [UI] : Record is displaying when user search the data on search screen");
		test.landingPageActions.navigateToFeature("General Ledger");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		glAccountName = test.supportDataActions.EnterRandomValueInInputField("description",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		glAccountNumber = test.supportDataActions.EnterRandomValueInInputField("number",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.selectValueFromDropDownForPISSystem("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(glAccountName, getData("SearchFieldValue.GlAccount"));
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(glAccountName, new_data);
	}

	@Test(priority = 2, description = "VPLX: Manage GL Account: [UI] : User verifies the search message when no records are found")
	public void Test02_1038457(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage GL Account: [UI] : User verifies the search message when no records are found");
		test.supportDataActions.enterSearchTermInSearchFieldGl(DateUtil.getTommorrowsDate(), "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Matching Results.");
		test.supportDataActions.resetSearch();
	}

	@Test(priority = 3, description = "VPLX: GL Account [UI]: GL Account-Search: Search is in sync with Inactive toggle")
	public void Test03_1045884(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: GL Account [UI]: GL Account-Search: Search is in sync with Inactive toggle");
		test.supportDataActions.pageRefresh();
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		glAccountName = test.supportDataActions.EnterRandomValueInInputField("description",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		glAccountNumber = test.supportDataActions.EnterRandomValueInInputField("number",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.GLAccount"));
		test.supportDataActions.selectValueFromDropDownForPISSystem("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(glAccountName, getData("SearchFieldValue.GlAccount"));
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(glAccountName, new_data);
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyRecordListEmpty());
		test.supportDataActions.resetSearch();
	}

	@Test(priority = 4, description = "VPLX: Manage GL Account: [UI] : User verifies the validation on Search page for search field on General Ledger Account")
	public void Test04_1038455(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage GL Account: [UI] : User verifies the validation on Search page for search field on General Ledger Account");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add General Ledger");
		glAccountName = test.supportDataActions.EnterRandomValueInInputField("description",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis() + "@#$");
		glAccountNumber = test.supportDataActions.EnterRandomValueInInputField("number",
				getData("GLAccountDetails.GLAccountDetailsName") + System.currentTimeMillis());
		test.supportDataActions.selectValueFromDropDownForPISSystem("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.clickButton("save");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnSearchField("search"), 50,
				"[ASSERTION FAILED]: Max Length for search field of Gl Account is not 50");
		test.supportDataActions.enterSearchTermInSearchFieldGl(glAccountName, getData("SearchFieldValue.GlAccount"));
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(glAccountName, new_data);
	}

}
