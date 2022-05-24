package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_116 extends BaseTest {
	String routingRuleName;

	@Test(priority = 1, description = "VPLX:Manage Routing Rules:[UI]-Verify User is able to add new routing rule")
	public void Test01_Add_RoutingRule_Test(Method method) {
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.clickOnAddButtonToAddRoutingRule();
		routingRuleName = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		//test.siteConfigurationAction.clickRoutingRuleRadioButton("0", "routingRuleTranPriority");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName", routingRuleName);
		test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SuccessMessages.SuccessMessage"));
		test.siteConfigurationAction.enterSearchTermInSearchField(routingRuleName, "search");
		test.siteConfigurationAction.verifyRoutingRuleDetails(routingRuleName);
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName", routingRuleName);
	}
}
