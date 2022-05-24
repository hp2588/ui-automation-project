package com.org.tests.integration;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class IntRoutingRuleTest extends BaseTest {
	
	String routingRuleName;

	@Test(priority = 1, description = "VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets populated in Facility dropdown on View Routing Rule  for a user having access to that Facility")
	public void Test01_1130463(Method method) {
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickOnAddButtonToAddRoutingRule();
		routingRuleName = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName", routingRuleName);
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName", routingRuleName);
	}
	
	@Test(priority = 2, description = "VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets populated in Facility dropdown on View Routing Rule  for a user having access to that Facility")
	public void Test02_RoutingRule(Method method) {
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickOnAddButtonToAddRoutingRule();
		routingRuleName = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName1", routingRuleName);
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName1", routingRuleName);
		test.siteConfigurationAction.enterSearchTermInSearchField(routingRuleName, "search");
		test.siteConfigurationAction.clickPickRoutingRuleButton("delete");
		test.siteConfigurationAction.verifyPopupMessageRoutingRule("Are you sure that you want to delete this rule?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.verifySuccessMessageOnRoutingRule("Selected routing rule has been deleted successfully");
	}
	
	@Test(priority = 3, description = "VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets populated in Facility dropdown on View Routing Rule  for a user having access to that Facility")
	public void Test03_RoutingRule(Method method) {
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickOnAddButtonToAddRoutingRule();
		routingRuleName = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName2", routingRuleName);
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName2", routingRuleName);
	}

}
