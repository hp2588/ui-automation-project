package com.org.tests.tq.hold;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class IntRoutingRuleTest extends BaseTest {
	
	String routingRuleName;

	@Test(priority = 1, description = "VPLX:Manage Routing Rules:[UI]-Verify User is able to add new routing rule")
	public void Test01_Add_RoutingRule_Test_1130463(Method method) {
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickOnAddButtonToAddRoutingRule();
		routingRuleName = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName", routingRuleName);
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SuccessMessages.SuccessMessage"));
		//test.siteConfigurationAction.enterSearchTermInSearchField(routingRuleName, "search");
		//test.siteConfigurationAction.verifyRoutingRuleDetails(routingRuleName);
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName", routingRuleName);
	}

}
