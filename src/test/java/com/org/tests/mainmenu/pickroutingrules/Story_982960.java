package com.org.tests.mainmenu.pickroutingrules;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982960 extends BaseTest {
	
	String existing_name, searched_name, new_name, routingRuleName;
	
	
	@Test(priority = 1, description = "VPLX: Manage Routing Rules:[UI] - Check that a confirmation popup "
			+ "is appear when user click on delete button")
	public void Test01_1047866(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:[UI] - Check that a confirmation popup is appear when user click on delete button");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.selectFacilityDropdownForDestination(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		routingRuleName = test.siteConfigurationAction
				.enterRoutingRuleName("RoutingRule_@#" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		
		test.supportDataActions.refreshPage();
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Routing Rules");
		
		test.siteConfigurationAction.selectFacilityDropdownForDestination(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(routingRuleName, "search");
		test.siteConfigurationAction.verifyRoutingRuleDetails(routingRuleName);
		test.siteConfigurationAction.verifyDeleteLinkUnderActionColumn();
		
		existing_name = test.siteConfigurationAction.getColumnFirstData("3");
		test.siteConfigurationAction.clickPickRoutingRuleButton("delete");
		test.siteConfigurationAction.verifyPopupMessageRoutingRule("Are you sure that you want to delete this rule?");	
	}
	
	
	@Test(priority = 2, description = "VPLX: Manage Routing Rules: [UI] -  Check that "
			+ "if user click on No button of delete confirmation popup then, user redirected back to same page")
	public void Test02_1047898(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules: [UI] -  Check that if user click on No button of delete confirmation popup then, user redirected back to same page");
		test.siteConfigurationAction.clickPickRoutingRuleButton("secondary");
		test.siteConfigurationAction.VerifyAndSearchText(existing_name);
		searched_name = test.siteConfigurationAction.getColumnFirstData("3");
		Assert.assertEquals(existing_name, searched_name);
		test.supportDataActions.resetSearch();
		test.siteConfigurationAction.headerRoutingRuleDisplayed();
	}
	
	
	@Test(priority = 3, description = "VPLX:Manage Routing Rules:[UI] -  Check that when user "
			+ "click on Delete button that particular Rule gets deleted")
	public void Test03_1047845(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI] -  Check that when user click on Delete button that particular Rule gets deleted");
		
		test.siteConfigurationAction.enterSearchTermInSearchField(routingRuleName, "search");
		test.siteConfigurationAction.clickPickRoutingRuleButton("delete");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.verifySuccessMessageOnRoutingRule("Selected routing rule has been deleted successfully");	
	}
	
	
	@Test(priority = 4, description = "VPLX: Manage Routing Rules:[UI] -  Check user not able to delete "
			+ "routing rule from  Add rule  (Priority, Destination and schedule) table")
	public void Test04_1047931(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:[UI] -  Check user not able to delete routing rule from  Add rule  (Priority, Destination and schedule) table");
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.siteConfigurationAction.verifyPickRoutingRuleButtonDisabled("delete");
	}
	

}
