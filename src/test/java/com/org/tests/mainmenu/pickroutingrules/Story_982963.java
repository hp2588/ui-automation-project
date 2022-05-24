package com.org.tests.mainmenu.pickroutingrules;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982963 extends BaseTest {
	String[] mandatoryFields = { "routingRuleName" };

	String searched_name,new_name;
	int beforeCount, afterCount;

	@Test(priority = 1, description = "VPLX: Manage Routing Rules:[UI] - Check that Edit button is available corresponding to each rule name")
	public void Test01_1048327(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:[UI] - Check that Edit button is available corresponding to each rule name");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.verifyHeader("Routing Rules");
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//Assert.assertTrue(test.siteConfigurationAction.verifyEditLinkUnderActionColumn());
		beforeCount = test.siteConfigurationAction.getEditLinkCount();
	}

	@Test(priority = 2, description = "VPLX : Manage Routing Rules:[UI] - Check that Priority, Destination and schedule options are available for editing through toggle button")
	public void Test02_1048330(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Manage Routing Rules:[UI] - Check that Priority, Destination and schedule options are available for editing through toggle button");
		test.siteConfigurationAction.clickEditRoutingRuleButton();
		test.siteConfigurationAction.verifyRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.verifyRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.verifyRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.verifyToggleHeader("All Priorities");
		test.siteConfigurationAction.verifyToggleHeader("All Destinations");
		test.siteConfigurationAction.verifyToggleHeader("All Schedules");
	}

	@Test(priority = 3, description = "VPLX: Manage Routing Rules:[UI] -  Check that validation message is appear if user forgot to enter Rule name and click on save button  while editing")
	public void Test03_1048335(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:[UI] -  Check that validation message is appear if user forgot to enter Rule name and click on save button  while editing");
		test.siteConfigurationAction.clearText("inputboxRoutingRule");
		test.siteConfigurationAction.verifyErrorMessageForIncorrectFieldsComputer(Arrays.asList(mandatoryFields),
				getData("RoutingRules.Error"));
		test.siteConfigurationAction.verifyErrorMessageFoRoutingRule(Arrays.asList(mandatoryFields));
		test.siteConfigurationAction.clickButtonUsingId("back-btn");
		test.siteConfigurationAction.clickButtonUsingId("primary");
		// test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		// test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
	}
	
	@Test(priority = 4, description = "VPLX: Manage Routing Rules:[UI] - Check that same  Rule name is saved "
			+ "when user  Edit in either Priority, Destination and Schedule table "
			+ "and click on save  button on edit page")
	public void Test04_1050411(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:[UI] - Check that same  Rule name is saved when user  Edit in either Priority, Destination and Schedule table and click on save  button on edit page");
		
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.verifyHeader("Routing Rules");
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickEditRoutingRuleButton();
		String currentRulename = test.siteConfigurationAction.getRoutingRuleName();
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(currentRulename);
		/*
		test.siteConfigurationAction.VerifyAndSearchText(currentRulename);
		searched_name = test.siteConfigurationAction.getColumnFirstData("3");
		Assert.assertEquals(currentRulename, searched_name);
		test.siteConfigurationAction.clearText("search_routing_rule");
		*/
	}
	
	@Test(priority = 5, description = "VPLX: Manage Routing Rules: [UI] - Count of Rule name not be increased after Editing")
	public void Test05_1048357(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules: [UI] - Count of Rule name not be increased after Editing");
		afterCount = test.siteConfigurationAction.getEditLinkCount();
		
		Assert.assertEquals(afterCount, beforeCount, 
				"[ASSERTION FAILED]: Expected "+ beforeCount + " rules on screen, but found " + afterCount);
	}

	@Test(priority = 6, description = "VPLX: Manage Routing Rules: [UI] -  Check that if user click on Cancel button of edit rule name, "
			+ "rule name not get changed"
			+ "Test Case 1047620:VPLX: Manage Routing Rules:[UI] - Check that user navigates to main page if user click on cancel button on edit screen")
	public void Test06_Test07_1048346_1047620(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules: [UI] -  Check that if user click on Cancel button of edit rule name, rule name not get changed");
		
		test.siteConfigurationAction.clickEditRoutingRuleButton();
		String currentRulename = test.siteConfigurationAction.getRoutingRuleName();
		test.siteConfigurationAction.enterRoutingRuleName("NewRoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.VerifyAndSearchText(currentRulename);
		searched_name = test.siteConfigurationAction.getColumnFirstData("3");
		Assert.assertEquals(currentRulename, searched_name);
		test.siteConfigurationAction.clearText("search_routing_rule");
		
	}	
	
	// TODO - Yugal - TC steps needs to be updated
	@Test(priority = 7, description = "VPLX: Manage Routing Rules:[UI] - Validation message is displayed if "
			+ "any option from tables(Priority, destination and schedule) is not selected and user "
			+ "try to save new routing rule")
	public void Test08_1047617(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:[UI] - Validation message is displayed if any option from tables(Priority, destination and schedule) is not selected and user try to save new routing rule");
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		new_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(new_name);
		
		test.siteConfigurationAction.clickEditRoutingRuleButton();
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("SaveText"));
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		
	}
	
	@Test(priority = 8, description = "VPLX:Manage Routing Rules:[UI]-Editing a routing rule and update through footer."
			+ "VPLX:Manage Routing Rules:[UI]- Edit a complete routing rule name.")
	public void Test09_Test10_1153105_1153106(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI]-Editing a routing rule and update through footer. 	");
		test.siteConfigurationAction.clickEditRoutingRuleButton();
		test.siteConfigurationAction.clearText("inputboxRoutingRule");
		new_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextPriority");
		test.siteConfigurationAction
				.verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Priority’?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("cancel");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		test.siteConfigurationAction.headerRoutingRuleDisplayed();	
		test.supportDataActions.verifyNewlyAddedRecordNameInList(new_name);
	}
}
