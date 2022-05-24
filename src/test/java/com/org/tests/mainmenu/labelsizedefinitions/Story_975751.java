package com.org.tests.mainmenu.labelsizedefinitions;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975751 extends BaseTest {

	String placeHolder, name, labelWidth, labelHeight, firstData;

	@Test(priority = 1, description = "VPLX:Define Label Stock:UI:Verify enabled add button available on Stock Definition page.")
	public void Test01_1038695(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify enabled add button available on Stock Definition page.");
		test.landingPageActions.navigateToFeature("Label Size Definitions");
		test.supportDataActions.verifyAddButtonOnPage();
	}
	
	@Test(priority = 2, description = "VPLX:Define Label Stock:UI:Verify on clicking add button a pop up generated with name Add stock definition")
	public void Test02_1038697(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify on clicking add button a pop up generated with name Add stock definition");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		//Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Add Label Size Definition"));
	}

	@Test(priority = 3, description = "VPLX:Define Label Stock:UI:Verify on clicking add button a pop up generated with name Add stock definition")
	public void Test03_1038697_1038706(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify on clicking add button a pop up generated with name Add stock definition");
		test.supportDataActions.verifyTextboxOnDistributor("stockLabelName");
		test.supportDataActions.verifyTextboxOnDistributor("stockLabelWidth");
		test.supportDataActions.verifyTextboxOnDistributor("stockLabelHeight");
	}

	@Test(priority = 4, description = "VPLX:Define Label Stock:UI:Verify Default value for Labels height be  4 inch and Label width be 1 inch")
	public void Test04_1038710(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify Default value for Labels height be  4 inch and Label width be 1 inch");
		placeHolder = test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("stockLabelWidth");
		Assert.assertEquals(placeHolder, "1.00");
		placeHolder = test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("stockLabelHeight");
		Assert.assertEquals(placeHolder, "4.00");
	}

	@Test(priority = 5, description = "VPLX:Define Label Stock:UI:Verify an error message generated while providing Label height greater than 11 inch and for Label width greater than 8.5 inch")
	public void Test05_1038724(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify an error message generated while providing Label height greater than 11 inch and for Label width greater than 8.5 inch");
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelWidth", "9.5");
		test.supportDataActions
				.verifyErrorMessageForAlreadyExistingName("Width should be between 0.5 inches and 8.5 inches");
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelWidth", "4.5");
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelHeight", "12");
		test.supportDataActions
				.verifyErrorMessageForAlreadyExistingName("Height should be between 0.5 inches and 11 inches");
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelHeight", "10");
	}

	@Test(priority = 6, description = "VPLX:Define Label Stock:UI:Verify cancel and save button displayed on add stock definition pop up")
	public void Test06_1039070_1045505(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify cancel and save button displayed on add stock definition pop up");
		Assert.assertTrue(test.supportDataActions.verifyButtons("save"));
		Assert.assertTrue(test.supportDataActions.verifyButtons("cancel"));
	}

	@Test(priority = 7, description = "VPLX:Define Label Stock:UI:Verify on saving the changes,the item must display in the list of stock definitions.")
	public void Test07_1039077_1045182_1039084(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify on saving the changes,the item must display in the list of stock definitions.");
		name = test.supportDataActions.enterValueOnAddDistributorPage("stockLabelName",
				"Stock" + System.currentTimeMillis());
		Assert.assertTrue(test.supportDataActions.verifyButtonIsEnabledOrDisabled("save"));
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Size Definitions"));
		test.supportDataActions.enterSearchTermInSearchField(name, "search");
		firstData = test.supportDataActions.firstDataOnDistributor("1");
		Assert.assertEquals(name, firstData);
	}

	@Test(priority = 8, description = "VPLX:Define Label Stock:UI:Verify an error message be generated on cancellation of stock definition")
	public void Test08_1039087(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify an error message be generated on cancellation of stock definition");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelName", "Stock" + System.currentTimeMillis());
		labelWidth = test.supportDataActions.enterValueOnAddDistributorPage("stockLabelWidth", "2");
		labelHeight = test.supportDataActions.enterValueOnAddDistributorPage("stockLabelHeight", "10");
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
		test.siteConfigurationAction.verifyPopupMessageRoutingRule("Are you sure you want to cancel?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Size Definitions"));

	}

	@Test(priority = 9, description = "VPLX:Define Label Stock:UI:Verify Stock definition name must be unique")
	public void Test09_1039079(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify Stock definition name must be unique	Rahul Shrivastava");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		test.supportDataActions.enterValueOnAddDistributorPage("stockLabelName", name);
		labelWidth = test.supportDataActions.enterValueOnAddDistributorPage("stockLabelWidth", "2");
		labelHeight = test.supportDataActions.enterValueOnAddDistributorPage("stockLabelHeight", "10");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.DispenseUnitName"));
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Label Size Definitions"));
	}

	@Test(priority = 10, description = "VPLX:Define Label Stock:UI:Verify inactive/active toggle button present on add stock definition pop up with default behaviour of toggle button active")
	public void Test10_1039074(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Define Label Stock:UI:Verify inactive/active toggle button present on add stock definition pop up with default behaviour of toggle button activeVPLX:Manage Distributors:[UI]:User verifies the functionality of Active toggle on Edit Distributor screen.");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotOnLabel("isActive").contains("true"));
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.HoldReason"));
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotOnLabel("isActive").contains("false"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.HoldReason"));
	}
	
	@Test(priority = 11, description = "VPLX: Define Label Stock: UI: Verify save button is disabled by default if all the mandatory fields are not entered.")
	public void Test11_1039082(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Define Label Stock: UI: Verify save button is disabled by default if all the mandatory fields are not entered.");
		//test.supportDataActions.clickAddButtonOnDistributor("add");
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("save"));
	}

}
