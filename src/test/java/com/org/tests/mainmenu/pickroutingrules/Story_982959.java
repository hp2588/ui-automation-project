package com.org.tests.mainmenu.pickroutingrules;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982959 extends BaseTest {
	
	String existing_name, new_name, searched_name, old_name, compare_name;
	
	@Test(priority = 1, description = "VPLX:Manage Routing Rules:[UI] - Check that new routing rule get save and user redirected to appropriate page(Priority, destination and schedule)  when click on save button of popup message.")
	public void Test01_1047639(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI] - Check that new routing rule get save and user redirected to appropriate page(Priority, destination and schedule)  when click on save button of popup message.");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		
		old_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextPriority");
		test.siteConfigurationAction
				.verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Priority’?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		// test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SuccessMessages.SuccessMessage"));
		
		test.siteConfigurationAction.verifyHeader("Priorities");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(old_name);
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		
		old_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextDestination");
		test.siteConfigurationAction
				.verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Destination’?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		// test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SuccessMessages.SuccessMessage"));
		
		test.siteConfigurationAction.verifyHeader("Destinations");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(old_name);
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		
		old_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextSchedule");
		test.siteConfigurationAction
				.verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Schedule’?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		// test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SuccessMessages.SuccessMessage"));
		test.siteConfigurationAction.verifyHeader("Schedules");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(old_name);
		
	}
	
	
	@Test(priority = 2, description = "VPLX:Manage Routing Rules:[UI] - Check that user will redirected to priority, destination and schedule page if user click on don't save button of popup message but new routing rule not be saved.")
	public void Test02_1047624(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI] - Check that user will redirected to priority, destination and schedule page if user click on don't save button of popup message but new routing rule not be saved.");
		test.siteConfigurationAction.pageRefresh();
		
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		old_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextPriority");
		test.siteConfigurationAction
				.verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Priority’?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("Dsave");
		test.siteConfigurationAction.verifyHeader("Priorities");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Routing Rules");
		//  test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.VerifyAndSearchText(old_name);
		Assert.assertEquals(test.siteConfigurationAction.getNoDataText(), "No Matching Results.");
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		
		old_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextDestination");
		test.siteConfigurationAction
				.verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Destination’?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("Dsave");
		test.siteConfigurationAction.verifyHeader("Destinations");
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		test.landingPageActions.navigateToFeature("Routing Rules");
		// test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.VerifyAndSearchText(old_name);
		Assert.assertEquals(test.siteConfigurationAction.getNoDataText(), "No Matching Results.");
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		
		old_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextSchedule");
		test.siteConfigurationAction
				.verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Schedule’?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("Dsave");
		test.siteConfigurationAction.verifyHeader("Schedules");
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Routing Rules");
		// test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.VerifyAndSearchText(old_name);
		Assert.assertEquals(test.siteConfigurationAction.getNoDataText(), "No Matching Results.");
		
	}
	
	
	@Test(priority = 3, description = "VPLX:Manage Routing Rule:[UI] - User is navigating to Pick Routing rules screen on clicking Pick routing rules link from Add Rule."
			+ "VPLX:Manage Routing Rules: [UI] - User is navigating to Pick Routing rules screen on clicking Pick Routing Rules")
	public void Test03_Test04_1053734_1053740(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rule:[UI] - User is navigating to Pick Routing rules screen on clicking Pick routing rules link from Add Rule.");
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.siteConfigurationAction.clickBreadCrumbRoutingRule();
		test.siteConfigurationAction.headerRoutingRuleDisplayed();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
	}
	
	
	@Test(priority = 4, description = "VPLX:Manage Routing Rules: [UI]- "
			+ "Default UI of ADD Routing Rule as per the VD provided.")
	public void Test05_1039757(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules: [UI]- Default UI of ADD Routing Rule as per the VD provided.");
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.verifyPickRoutingRuleButton("SaveText");
		
		test.siteConfigurationAction.verifyPickRoutingRulePlaceholder();
		test.siteConfigurationAction.verifyPickRoutingRuleFooterLink("popupTextPriority");
		test.siteConfigurationAction.verifyPickRoutingRuleFooterLink("popupTextDestination");
		test.siteConfigurationAction.verifyPickRoutingRuleFooterLink("popupTextSchedule");
		test.siteConfigurationAction.verifyPickRoutingRuleLabel("Send picks for these priorities");
		test.siteConfigurationAction.verifyPickRoutingRuleLabel("Going to these destinations");
		test.siteConfigurationAction.verifyPickRoutingRuleLabel("During these times");
		
	}

	@Test(priority = 5, description = "VPLX:Mange Routing Rules:[UI]- Validate that Schedule, "
			+ "priority and Destination table are available while creating Routing rule.")
	public void Test06_1041001(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Mange Routing Rules:[UI]- Validate that Schedule, priority and Destination table are available while creating Routing rule.");
		
		test.siteConfigurationAction.verifyRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.verifyRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.verifyRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.verifyToggleHeader("All Priorities");
		test.siteConfigurationAction.verifyToggleHeader("All Destinations");
		test.siteConfigurationAction.verifyToggleHeader("All Schedules");
		
	}

	@Test(priority = 6, description = "VPLX:Manage Routing Rules:[UI] - Check that there are Toggle buttons"
			+ " in each table for select,deselect and multi select (Priority, destination and schedule)of Add rule" 
			+ "\n"
			+ "VPLX:Manage Routing Rules:[UI] -  Check that there are Toggle buttons in each table "
			+ "(Priority, destination and schedule)of Add rule.")
	public void Test07_Test08_1046594_1047566(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI] -  Check that there are Toggle buttons in each table (Priority, destination and schedule)of Add rule.");
		
		test.siteConfigurationAction.verifyRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.verifyRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.verifyRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		
	}
	
	// TODO - Yugal - TC script needs to be updated
	@Test(priority = 7, description = "VPLX: Manage Routing Rules:{UI] -  Check the value of priority , destination and schedule added in pick routing rule.")
	public void Test09_1050465(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:{UI] -  Check the value of priority , destination and schedule added in pick routing rule.");
		
		new_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		// test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SuccessMessages.SuccessMessage"));
		
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(new_name);	
	}
	
	
	@Test(priority = 8, description = "VPLX:Manage Routing Rules:[UI]- Validate the POPUP message "
			+ "when user click on Priority in the footer of the application"
			+ "\n"
			+ "Test Case 1047618:VPLX: Manage Routing Rules:[UI]- Check that a POPUP (\"Do you want to save the rule before proceeding to "
			+ "'Add Schedule'?\")message is appear when user click on the links in the footer of Priority, Destination and schedule table")
	public void Test10_Test11_1041008_1047618(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI]- Validate the POPUP message when user click on Priority in the footer of the application.");
		
		test.siteConfigurationAction.pageRefresh();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		old_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		System.out.println("Value of input field: " + old_name);
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextPriority");
		test.siteConfigurationAction
				.verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Priority’?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("cancel");
		
	}

	@Test(priority = 9, description = "VPLX:Manage Routing Rules:[UI]- Validate the POPUP message when user click on Add Destination in the footer of the application.")
	public void Test12_1041015(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI]- Validate the POPUP message when user click on Add Destination in the footer of the application.");
		
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextDestination");
		test.siteConfigurationAction
				.verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Destination’?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("cancel");
		
	}

	@Test(priority = 10, description = "VPLX:Manage Routing Rules:[UI]- Validate POPUP message for Add Schedule in the footer of the Application.")
	public void Test13_1041017(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI]- Validate POPUP message for Add Schedule in the footer of the Application.");
		
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextSchedule");
		test.siteConfigurationAction
				.verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Schedule’?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("cancel");
		
		compare_name = test.siteConfigurationAction.getRuleNameTextBoxValue();
		System.out.println("Value of old data is : " + old_name);
		System.out.println("Value of compared data is : " + compare_name);
		Assert.assertEquals(old_name, compare_name);
		
		Assert.assertTrue(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleTranPriority", "allTransaction"));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleDestinations", "allDestinations"));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleSchedules", "allDays"));
		
	}

	@Test(priority = 12, description = "VPLX: Manage Routing Rules:[UI] -  Check that user will move to pick routing rule view page when pop-up of footer link cancel button is clicked")
	public void Test14_1097203(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:[UI] -  Check that user will move to pick routing rule view page when pop-up of footer link cancel button is clicked");
		
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.headerRoutingRuleDisplayed();
	}
	
	@Test(priority = 13, description = "VPLX:Manage Routing Rules :[UI]- click on Save button and information get save successfully."
			+ "VPLX:Manage Routing Rules:[UI]- User Add New Routing Rule by inserting name in Rule Name text box" 
			+ "VPLX:Manage Routing Rules:[UI]- Validate POPUP message for Save button"
			+ "VPLX:Manage Routing Rules:[UI] - New Routing rule name is created if user enter less or equal to than 50 characters in Rule Name text box")
	public void Test15_Test16_Test17_Test18_1040238_1040864_1040973_1042355(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules :[UI]- click on Save button and information get save successfully.");
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		String ruleName = test.siteConfigurationAction.verifyMaxLengthOfAnInputField("routingRuleName",
				DateUtil.getAlphaNumericString(51), 50);
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SuccessMessages.SuccessMessage"));	
		test.supportDataActions.verifyNewlyAddedRecordNameInList(ruleName);
		
	}
	
	
	@Test(priority = 14, description = "VPLX:Manage Routing Rules:[UI]- Validation message if user try to Add duplicate Routing Rule name."
			+ "Test Case 1047651:VPLX: Manage Routing Rules:[UI] - Validation message is displayed if user try to save routing rule, that already exists")
	public void Test19_Test20_1040871_1047651(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI]- Validation message if user try to Add duplicate Routing Rule name.");
		
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.siteConfigurationAction.enterRoutingRuleName(new_name);
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		test.siteConfigurationAction.verifyErrorMessageRoutingRule(
				"This Routing Rule Name already exists. Please provide a unique Routing Rule Name .");
	}
	
	
	@Test(priority = 15, description = "VPLX:Manage Routing Rules:[UI] - Validate that user Add Alphanumeric Routing Rule name.")
	public void Test21_1040876(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI] - Validate that user Add Alphanumeric Routing Rule name.");
		
		test.siteConfigurationAction.enterRoutingRuleName("@#$RoutingRule" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SuccessMessages.SuccessMessage"));
	}
	
	
	@Test(priority = 16, description = "VPLX:Manage Routing Rules:[UI] - Message is displayed if user left Rule name text box empty.")
	public void Test22_1040950(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI] - Message is displayed if user left Rule name text box empty.");
		
		test.siteConfigurationAction.pageRefresh();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("SaveText"));	
	}
	
	
	@Test(priority = 17, description = "VPLX:Manage Routing Rules: [UI] - check that validation message is display if  any option from tables(Priority, destination and schedule) is not be selected and user try to save new routing rule.")
	public void Test23_1046905(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules: [UI] - check that validation message is display if  any option from tables(Priority, destination and schedule) is not be selected and user try to save new routing rule.");
		
		test.siteConfigurationAction.enterRandomRoutingRuleName();
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("SaveText"));
		
	}

	@Test(priority = 18, description = "VPLX:Manage Routing Rules:[UI]- Validate user can cancel Routing name while creation new routing Rule name."
			+ "Test Case 1040967:VPLX:Manage Routing Rules:[UI]- Validate the POPUP message for cancel button")
	public void Test24_Test25_1040960_1040967(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI]- Validate user can cancel Routing name while creation new routing Rule name.");
		test.siteConfigurationAction.enterRandomRoutingRuleName();
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.headerRoutingRuleDisplayed();
	}
	
	// TODO - refactoring required - yugal
	@Test(priority = 19, description = "VPLX:Manage Routing Rules:[UI] -  check that All toggle buttons are activate and "
			+ "inactivate when we click on  All priority ,All destination and All schedule toggle button. "
			+ "Test Case 1046701:VPLX: Manage Routing Rules: [UI] - check that All toggle buttons are active when we "
			+ "click on All priority ,All destination and All schedule toggle button in table")
	public void Test26_Test27_1047575_1046701(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI] -  check that All toggle buttons are activate and inactivate when we click on  All priority ,All destination and All schedule toggle button. ");
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		Assert.assertTrue(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleTranPriority", "allTransaction"));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleDestinations", "allDestinations"));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleSchedules", "allDays"));
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		Assert.assertFalse(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleTranPriority", "allTransaction"));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleDestinations", "allDestinations"));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleSchedules", "allDays"));
		
	}
	
	
	@Test(priority = 20, description = "VPLX:Manage Routing Rules: [UI] -  Check that user can Inactivate and Activate option using toggle button inside"
			+ " the tables(Priority , destination and schedule)."
			+ "Test Case 1047581:VPLX: Manage Routing Rules:[UI] - User can Inactive and Active any option by click on toggle "
			+ "button inside the tables(Priority , destination and schedule)")
	public void Test28_Test29_1046900_1047581(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules: [UI] -  Check that user can Inactivate and Activate option using toggle button inside the tables(Priority , destination and schedule).");
		test.siteConfigurationAction.pageRefresh();
		
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		Assert.assertTrue(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleTranPriority", "allTransaction"));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleDestinations", "allDestinations"));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleSchedules", "allDays"));
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		Assert.assertFalse(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleTranPriority", "allTransaction"));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleDestinations", "allDestinations"));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyCheckboxIsEnabledOrDisabledRoutingRule("routingRuleSchedules", "allDays"));
	}
	
	
	@Test(priority = 21, description = "VPLX:Manage Routing Rules:[UI]-Click on cancel button on Admin page at top right corner.")
	public void Test30_1047644(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI]-Click on cancel button on Admin page at top right corner.");
		test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.headerRoutingRuleDisplayed();
	}
	
	
	// TODO - Yugal - refactoring
	@Test(priority = 22, description = "VPLX:Manage Routing Rules:[UI]-Add a routing rule and update through footer."
			+ "Test Case 1129242:VPLX : Manage Routing Rules:[UI] - Authorized user can have access to Routing Rules.")
	public void Test31_Test32_1153108_1129242(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI]-Add a routing rule and update through footer.");
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		old_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextSchedule");
		test.siteConfigurationAction
				.verifyPopupMessageRoutingRule("Do you want to save the rule before proceeding to ‘Add Schedule’?");
		test.siteConfigurationAction.clickPickRoutingRuleButton("cancel");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		test.siteConfigurationAction.headerRoutingRuleDisplayed();
		test.supportDataActions.verifyNewlyAddedRecordNameInList(old_name);
	}
	
}
