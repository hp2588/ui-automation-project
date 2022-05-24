package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Med_Multiple_Location_Rules extends BaseTest {

	String routingRuleName,facility;

	@Test(priority = 1, description = "VPLX Same Med Multiple Location - Rules - System requires the user to use the default routing rule if a single location exists within a facility for an item.")
	public void Test01_1117265(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX Same Med Multiple Location - Rules - System requires the user to use the default routing rule if a single location exists within a facility for an item.");
/*Create Routing Rule*/
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		facility=test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("FacilityDropdown", 1);
		routingRuleName = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("1", "routingRuleTranPriority");
		// test.siteConfigurationAction.clickRoutingRuleRadioButton("1",
		// "routingRuleDestinations");
		// test.siteConfigurationAction.clickRoutingRuleRadioButton("1",
		// "routingRuleSchedules");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		//TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName", routingRuleName);
		test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SuccessMessages.SuccessMessage"));
		test.siteConfigurationAction.enterSearchTermInSearchField(routingRuleName, "search");
		test.siteConfigurationAction.verifyRoutingRuleDetails(routingRuleName);
		//TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName", routingRuleName);
	}
	
	@Test(priority = 2, description = "VPLX Same Med Multiple Location - Rules - System allow user to set 'Default' routing rule for only one location when more than one locations exist for an item in a facility.")
	public void Test02_1117268(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX Same Med Multiple Location - Rules - System allow user to set 'Default' routing rule for only one location when more than one locations exist for an item in a facility.");

	}
	
	@Test(priority = 3, description = "VPLX-Same Med Multiple Location-Rules-System displays all routing rules that have been configured for facility(A) while viewing multiple locations for an item in facility(A).")
	public void Test03_1117278(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX-Same Med Multiple Location-Rules-System displays all routing rules that have been configured for facility(A) while viewing multiple locations for an item in facility(A).");

	}
	
	@Test(priority = 4, description = "VPLX-Same Med Multiple Location-Rules-All pick requests are routed to the default location when more than one locations exists for an item within a facility and pick request does not meets the criteria of a routing rule applied to a location for the item.")
	public void Test04_1117281(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX-Same Med Multiple Location-Rules-All pick requests are routed to the default location when more than one locations exists for an item within a facility and pick request does not meets the criteria of a routing rule applied to a location for the item.");

	}
	
}
