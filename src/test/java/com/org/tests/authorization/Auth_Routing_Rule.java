package com.org.tests.authorization;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Auth_Routing_Rule extends BaseTest {
	
	String routingRuleName, existing_name;
	
	@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
				getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
	}

	
	@Test(priority = 1, description = "VPLX: Authorization: [UI] : User is able to access Routing Rules and add rule which has Permissions to Manage Distributor  and Manage Rules.")
	public void Test01_1133520(Method method) {
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.clickOnAddButtonToAddRoutingRule();
		routingRuleName = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName", routingRuleName);
		
		//test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SuccessMessages.SuccessMessage"));
		test.siteConfigurationAction.enterSearchTermInSearchField(routingRuleName, "search");
		test.siteConfigurationAction.verifyRoutingRuleDetails(routingRuleName);
		TestDataPropertyReaderAndWriter.setProperty("RoutingRuleName", routingRuleName);
	}
	
	@Test(priority = 2, description = "VPLX: Authorization: [UI]: Support user is allowed to click on View Rules and view the rules in dropdown .")
	public void Test02_1151663(Method method) {
		test.siteConfigurationAction.verifyRoutingRuleDetails(routingRuleName);
	}
	
	
	@Test(priority = 3, description = "VPLX: Authorization: [UI] : User is able to edit Routing Rule when user permission of Manage Rules, Manage Distributor and Manage Schedules")
	public void Test03_1133550_1133521(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI]-Editing a routing rule and update through footer. 	");
		test.siteConfigurationAction.clickEditRoutingRuleButton();
		test.siteConfigurationAction.clearText("inputboxRoutingRule");
		String new_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextPriority");
		test.siteConfigurationAction
				.verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Priority’?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("cancel");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		test.siteConfigurationAction.headerRoutingRuleDisplayed();	
		test.supportDataActions.verifyNewlyAddedRecordNameInListRoutingRule(new_name);
		
		existing_name = test.siteConfigurationAction.getColumnFirstData("3");
		test.siteConfigurationAction.clickPickRoutingRuleButton("delete");
		test.siteConfigurationAction.verifyPopupMessageRoutingRule("Are you sure that you want to delete this rule?");
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.verifySuccessMessageOnRoutingRule("Selected routing rule has been deleted successfully");
	}
	
	@Test(priority = 4, description = "VPLX: Authorization: [UI]: Support user is able to delete routing rules.")
	public void Test04_1151499(Method method) {
		existing_name = test.siteConfigurationAction.getColumnFirstData("3");
		test.siteConfigurationAction.clickPickRoutingRuleButton("delete");
		test.siteConfigurationAction.verifyPopupMessageRoutingRule("Are you sure that you want to delete this rule?");
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.verifySuccessMessageOnRoutingRule("Selected routing rule has been deleted successfully");
	}

}
