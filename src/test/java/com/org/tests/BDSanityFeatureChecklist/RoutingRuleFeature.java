package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class RoutingRuleFeature extends BaseTest {

	String old_name, routingRuleName, searched_name, new_name, priorityName, code, destinationName, destinationCode, scheduleName;

	@Test(priority = 1, description = "VPLX : Manage Routing Rules:[UI] - To verify Authorized user "
			+ "can have access to Routing Rules."
			+ "\n&\n"
			+ "VPLX : Manage Routing Rules:[UI] - To verify User is able to add/edit/view routing rule "
			+ "for any facility selected.")
	public void Test01_Test02_1129242_1129244(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules:[UI] - Check that new routing rule get save and user redirected to appropriate page(Priority, destination and schedule)  when click on save button of popup message.");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
		
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.verifyHeader("Routing Rules");
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		old_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		test.supportDataActions.refreshPage();
		
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterSearchTermInSearchField(old_name, "search");
		test.siteConfigurationAction.verifyRoutingRuleDetails(old_name);
	
		test.siteConfigurationAction.clickRecordNameToEdit_Generic(old_name);
		new_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
			
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(new_name);
		
	}
	
	
	@Test(priority = 2, description = "Obsolete: VPLX: Manage Routing Rules:[UI] - Validation message "
			+ "is displayed if any option from tables(Priority, destination and schedule) "
			+ "is not selected and user try to save new routing rule")
	public void Test03_1047617(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Routing Rules: [UI] - check that validation message is display if  any option from tables(Priority, destination and schedule) is not be selected and user try to save new routing rule.");
		
		test.siteConfigurationAction.headerRoutingRuleDisplayed();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.siteConfigurationAction.enterRandomRoutingRuleName();
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("SaveText"));
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.headerRoutingRuleDisplayed();
	}
	
	
	@Test(priority = 3, description = "VPLX: Manage Routing Rules:[UI] -To verify user can Edit "
			+ "the rule name with Alphanumeric text upto 50 characters.")
	public void Test04_1048328(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Routing Rules:[UI] - Check that user can Edit the rule name");
		
		test.siteConfigurationAction.clickEditRoutingRuleButton();
		new_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		test.supportDataActions.refreshPage();
		
		test.siteConfigurationAction.headerRoutingRuleDisplayed();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.VerifyAndSearchText(new_name);
		searched_name = test.siteConfigurationAction.getColumnFirstData("3");
		Assert.assertEquals(new_name, searched_name);
		test.supportDataActions.resetSearch();
	}
	
	
	@Test(priority = 4, description = "VPLX : Manage Routing Rules:[UI] -To verify user "
			+ "has the ability to delete a routing rule if there is no association.")
	public void Test05_1129243(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Manage Routing Rules:[UI] - Add the ability to delete a routing rule if there is no association.");
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		new_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		test.supportDataActions.refreshPage();
		
		test.siteConfigurationAction.headerRoutingRuleDisplayed();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.VerifyAndSearchText(new_name);
		test.siteConfigurationAction.clickPickRoutingRuleButton("delete");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction
				.verifySuccessMessageOnRoutingRule("Selected routing rule has been deleted successfully");	
	}
	
	
	// TODO - refactoring required
	@Test(priority = 5, description = "VPLX : Manage Routing Rules:[UI] - To verify Multiple priorities "
			+ "can be chosen to be routed to multiple Destinations.")
	public void Test06_1129246(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Manage Routing Rules:[UI] - Multiple priorities can be chosen to be routed to multiple Destinations for defined schedules.");
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		new_name = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleDestinations", "allDestinations");
		test.siteConfigurationAction.clickRoutingRuleRadioButtonSchedule();
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		
		test.siteConfigurationAction.pageRefresh();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(new_name);
	}
	
	
	@Test(priority = 6, description = "VPLX : Manage Routing Rules:[UI] - To verify If the user has "
			+ "required permissions,Additional Schedules can also be created for Routing rule.")
	public void Test07_1129245(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"To verify If the user has required permissions,Additional Schedules can also be created for Routing rule.");
		test.supportDataActions.refreshPage();
		
		test.siteConfigurationAction.verifyHeader("Routing Rules");
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextPriority");
		
		test.siteConfigurationAction.verifyHeader("Priorities");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.siteConfigurationAction.enterRandomValueInInputField("maxHoldMinutes", "20");
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", "3600");
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(priorityName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities(priorityName));
		
		//test.landingPageActions.navigateToMenu("Main Menu");
		//test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextDestination");
		
		test.siteConfigurationAction.verifyHeader("Destinations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.General")));
		Assert.assertTrue(test.siteConfigurationAction.toggleIsActiveOrNot("activeFlag"),
				"[ASSERTION FAILED]: Toggle is inactive in General Tab on Add destination screen");
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Distributor_Accounts")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Users")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Items")));
		test.siteConfigurationAction.selectFacilityForDestinationDropDown("facilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		
		
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.selectFacilityDropdownForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.siteConfigurationAction.clickPickRoutingRuleFooterLink("popupTextSchedule");
		
		test.siteConfigurationAction.verifyHeader("Schedules");
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("facilityModelKey");
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey", 
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("scheduleName");
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				"Schedule" + System.currentTimeMillis());
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
		
	}

}
