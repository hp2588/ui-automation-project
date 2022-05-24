package com.org.tests.mainmenu.transactionpriorities;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982971 extends BaseTest {

	String priorityName, code;

	@Test(priority = 1, description = "VPLX: Manage Transaction priorities:[UI] -  Check that user can search the priority using Priority name in manage transaction priority")
	public void Test01_1054082(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction priorities:[UI] -  Check that user can search the priority using Priority name in manage transaction priority");

		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();

		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");

		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

		test.supportDataActions.enterSearchTermInSearchFieldGl(priorityName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities(priorityName));
		test.supportDataActions.clearSearchBoxField("search");

	}

	@Test(priority = 2, description = "VPLX: Manage Transaction priorities:[UI] -  Check that Error message is display if user enter space in search text box and then press enter")
	public void Test02_1054088(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction priorities:[UI] -  Check that Error message is display if user enter space in search text box and then press enter");
		test.supportDataActions.enterSearchTermInSearchFieldGl(DateUtil.getTommorrowsDate(), "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Matching Results.");
		test.supportDataActions.clearSearchBoxField("search");

	}

	@Test(priority = 3, description = "VPLX: Manage transaction priorities:[UI] -  Check that Error message is display if user enter Numeric value in special character in search text box")
	public void Test03_1054121(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage transaction priorities:[UI] -  Check that Error message is display if user enter Numeric value in special character in search text box");
		test.supportDataActions.enterSearchTermInSearchFieldGl("@#$" +DateUtil.getTommorrowsDate(), "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Matching Results.");
		test.supportDataActions.clearSearchBoxField("search");

	}

	@Test(priority = 4, description = "VPLX: Manage Transaction Priorities:[UI] - Error message is displayed if user hit space bar and then insert text in search text box")
	public void Test04_1054121(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI] - Error message is displayed if user hit space bar and then insert text in search text box");
		test.supportDataActions.enterSearchTermInSearchFieldGl(" ", "search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(DateUtil.getTommorrowsDate(), "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Matching Results.");
		/*Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("activeFlag"),
				"[Assertion Failed]: CheckBox Is ADC is Unchecked");
		*/

	}

	@Test(priority = 5, description = "VPLX: Manage Transaction priorities:[UI] -  Check that search text box get reset when click on reset button")
	public void Test05_1054164(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction priorities:[UI] -  Check that search text box get reset when click on reset button");
		Assert.assertTrue(test.supportDataActions.verifySearchCrossIcon());
		test.supportDataActions.clearSearchBoxField("search");
		test.siteConfigurationAction.verifyRecordListOfTransactionPriorities();

	}

	@Test(priority = 6, description = "VPLX: Manage Transaction priorities:[UI] - Check that user perform searching through First name.")
	public void Test06_1054593(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction priorities:[UI] - Check that user perform searching through First name.");
		test.supportDataActions.enterSearchTermInSearchFieldGl("Name", "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities("Name"));
		test.supportDataActions.clearSearchBoxField("search");

	}

	@Test(priority = 7, description = "VPLX: Manage Transaction Priorities:[UI]: Feature testing -User is able to select the active and inactive priorties on selection of toggle button.")
	public void Test07_1060330(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction Priorities:[UI]: Feature testing -User is able to select the active and inactive priorties on selection of toggle button.");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("activeFlag"),
				"[Assertion Failed]: CheckBox Is ADC is Unchecked");
		
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();

		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		
		

		Assert.assertFalse(test.siteConfigurationAction.verifyInactiveTransactionPriorities("activeFlag"),
				"[Assertion Failed]: CheckBox Is of Newly created Inactive is checked");
		

	}

}
