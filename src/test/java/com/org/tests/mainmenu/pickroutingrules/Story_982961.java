package com.org.tests.mainmenu.pickroutingrules;

import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982961 extends BaseTest {
	String routingRuleName, searched_name, routingRule_Priority, routingRule_Dest, routingRule_Schedule;

	@Test(priority = 1, description = "VPLX: Manage Routing Rules:[UI] - All details of Rule name are display when user enter Rule name in search text box and press enter")
	public void Test01_1048495(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:[UI] - All details of Rule name are display when user enter Rule name in search text box and press enter");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.verifyHeader("Routing Rules");
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		// test.siteConfigurationAction.selectValueFromDropDownByIndex("FacilityDropdown",
		// 1);
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		routingRuleName = test.siteConfigurationAction
				.enterRoutingRuleName("RoutingRule_@#" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(routingRuleName, "search");
		// test.siteConfigurationAction.verifyRoutingRuleDetails(routingRuleName,routingRule_Priority,routingRule_Dest,routingRule_Schedule);
		test.siteConfigurationAction.verifyRoutingRuleDetails(routingRuleName);
	}

	@Test(priority = 2, description = "VPLX: Mange Routing Rules:[UI] - Validation  message is display if user enter number in search text box and press enter")
	public void Test02_1048528(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Mange Routing Rules:[UI] - Validation  message is display if user enter number in search text box and press enter");
//		test.siteConfigurationAction.refreshPage();
//		test.siteConfigurationAction.verifyHeader("Routing Rules");
		test.supportDataActions.resetSearch();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField("1",
				"search");
		test.supportDataActions.pressKeyUsingAction(Keys.ENTER);
		//Assert.assertEquals(test.siteConfigurationAction.getNoDataText(), getData("RoutingRule.ErrorMsg_NoData"));
		test.siteConfigurationAction.verifyRoutingRuleDetails(routingRuleName);
		test.siteConfigurationAction.refreshPage();
	}

	@Test(priority = 3, description = "VPLX: Manage Routing Rules:[UI] - Check user enter maximum 50 characters in search text box")
	public void Test03_1048540(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:[UI] - Check user enter maximum 50 characters in search text box");
		test.siteConfigurationAction.verifyHeader("Routing Rules");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfSearchField("search"), 50,
				"[ASSERTION FAILED]: Max Length for search field is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("scheduleSearch", DateUtil.getAlphaNumericString(51),50);


	}

	@Test(priority = 4, description = "VPLX: Manage Routing Rules:[UI]- Check that error message is display if user press space bar in search text box and press enter")
	public void Test04_1048619(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:[UI]- Check that error message is display if user press space bar in search text box and press enter");
		test.supportDataActions.resetSearch();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(Keys.SPACE, routingRuleName, "search");
		Assert.assertEquals(test.siteConfigurationAction.getNoDataText(), getData("RoutingRule.ErrorMsg_NoData"));
		// test.siteConfigurationAction.refreshPage();
		test.supportDataActions.resetSearch();
	}

	@Test(priority = 5, description = "VPLX:Manage Routing Rules:[UI]- Check the reset option in search text box")
	public void Test05_1048641(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI]- Check the reset option in search text box");
		test.siteConfigurationAction.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(routingRuleName, "search");
		test.siteConfigurationAction.clearText("search");
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(routingRuleName, "search");
		test.supportDataActions.resetSearch();
	}

	@Test(priority = 6, description = "VPLX: Manage Routing Rules:[UI] - Check that search result is display according to the value enter in search text box")
	public void Test06_1048654_1048683_1040227(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:[UI] - Check that search result is display according to the value enter in search text box");
		test.siteConfigurationAction.enterSearchTermInSearchField(routingRuleName, "search");
		test.siteConfigurationAction.verifyRoutingRuleDetails(routingRuleName);
		test.supportDataActions.resetSearch();
	}
}
