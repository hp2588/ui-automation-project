package com.org.tests.mainmenu.pickroutingrules;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982957 extends BaseTest {
	String existing_name, new_name, searched_name, old_name, compare_name;

	@Test(priority = 1, description = "VPLX: Manage Routing Rules:[UI] - Check that  when Rule name list is already expanded then list is not display if user hover mouse on priority and destination.")
	public void Test01_1050136(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:[UI] - Check that  when Rule name list is already expanded then list is not display if user hover mouse on priority and destination.");

		test.landingPageActions.navigateToFeature("Routing Rules");
//		test.supportDataActions.verifyLabelIsPresent("Routing Rules");
		test.siteConfigurationAction.verifyHeader("Routing Rules");
		/*
		 * test.siteConfigurationAction.selectFacilityDropdownForDestination(getData(
		 * "RoutingRules.FacilityName"));
		 * //test.siteConfigurationAction.selectValueFromDropDownByIndex(
		 * "FacilityDropdown", 1);
		 * test.siteConfigurationAction.clickPickRoutingRuleButton("add"); old_name =
		 * test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" +
		 * System.currentTimeMillis());
		 * test.siteConfigurationAction.clickRoutingRuleRadioButton("1",
		 * "routingRuleTranPriority");
		 * test.siteConfigurationAction.clickRoutingRuleRadioButton("1",
		 * "routingRuleDestinations");
		 * test.siteConfigurationAction.clickRoutingRuleRadioButton("1",
		 * "routingRuleSchedules");
		 * test.siteConfigurationAction.clickPickRoutingRuleFooterLink(
		 * "popupTextPriority"); test.siteConfigurationAction
		 * .verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Priority’?"
		 * ); test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		 * test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData(
		 * "SuccessMessages.SuccessMessage"));
		 * test.siteConfigurationAction.verifyHeader("Priorities");
		 * test.landingPageActions.navigateToMenu("Main Menu");
		 * test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations");
		 * test.landingPageActions.navigateToFeature("Routing Rules");
		 * test.supportDataActions.verifyNewlyAddedRecordNameInList(old_name);
		 * test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		 * 
		 * old_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" +
		 * System.currentTimeMillis());
		 * test.siteConfigurationAction.clickRoutingRuleRadioButton("1",
		 * "routingRuleTranPriority");
		 * test.siteConfigurationAction.clickRoutingRuleRadioButton("1",
		 * "routingRuleDestinations");
		 * test.siteConfigurationAction.clickRoutingRuleRadioButton("1",
		 * "routingRuleSchedules");
		 * test.siteConfigurationAction.clickPickRoutingRuleFooterLink(
		 * "popupTextDestination"); test.siteConfigurationAction
		 * .verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Destination’?"
		 * ); test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		 * test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData(
		 * "SuccessMessages.SuccessMessage"));
		 * test.siteConfigurationAction.verifyHeader("Destinations");
		 * test.landingPageActions.navigateToMenu("Main Menu");
		 * test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations");
		 * test.landingPageActions.navigateToFeature("Routing Rules");
		 * test.supportDataActions.verifyNewlyAddedRecordNameInList(old_name);
		 * test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		 * 
		 * old_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" +
		 * System.currentTimeMillis());
		 * test.siteConfigurationAction.clickRoutingRuleRadioButton("1",
		 * "routingRuleTranPriority");
		 * test.siteConfigurationAction.clickRoutingRuleRadioButton("1",
		 * "routingRuleDestinations");
		 * test.siteConfigurationAction.clickRoutingRuleRadioButton("1",
		 * "routingRuleSchedules");
		 * test.siteConfigurationAction.clickPickRoutingRuleFooterLink(
		 * "popupTextSchedule"); test.siteConfigurationAction
		 * .verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Schedule’?"
		 * ); test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		 * test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData(
		 * "SuccessMessages.SuccessMessage"));
		 * test.siteConfigurationAction.verifyHeader("Schedules");
		 * test.landingPageActions.navigateToMenu("Main Menu");
		 * test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations");
		 * test.landingPageActions.navigateToFeature("Routing Rules");
		 * test.supportDataActions.verifyNewlyAddedRecordNameInList(old_name);
		 */
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
//		test.siteConfigurationAction.selectFacilityDropdownForDestination("Fac1586455969698");

		test.siteConfigurationAction.verifyDescriptionOnPriorities();
		test.siteConfigurationAction.verifyDescriptionOnDestinations();

	}

	@Test(priority = 2, description = "VPLX: Manage Routing Rules: [UI]: The Routing Rules are displayed on screen when user lands on Routing Rule page")
	public void Test02_1110856(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules: [UI]: The Routing Rules are displayed on screen when user lands on Routing Rule page");
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.getRoutingRuleDetails();
	}

}
