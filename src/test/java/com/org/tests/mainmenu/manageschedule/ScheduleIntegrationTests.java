package com.org.tests.mainmenu.manageschedule;

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

public class ScheduleIntegrationTests extends BaseTest {

	String scheduleName, scheduleNameUpdated, facilityOnWFAScreen, routingRule, startTime, endTime, day;
	String ruleName;
	String scheduleName2, scheduleNameUpdated2;
	
	// TODO - Yugal - Schedule on View Routing rule
	@Test(priority = 1, description = "VPLX: Manage Schedules: [UI]: [Integration]: When a Schedule is added, "
			+ "the same gets reflected on Add/Edit/View Routing screen")
	public void Test01_1106953() {
		
		facilityOnWFAScreen =
				  TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		test.landingPageActions.navigateToFeature("Schedules");
		test.supportDataActions.verifyLabelIsPresent("Schedules");
		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facilityOnWFAScreen);
		
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.selectValueFromDropDown("defaultFacilityKey", facilityOnWFAScreen);
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.supportDataActions.verifyLabelIsPresent("Routing Rules");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.supportDataActions.verifyLabelIsPresent("Add Routing Rule");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyScheduleExist(scheduleName));
		
		ruleName = test.siteConfigurationAction.enterRoutingRuleName("RoutingRule_" + System.currentTimeMillis());
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleTranPriority", "allTransaction");
		test.siteConfigurationAction.clickRoutingRuleRadioButton("routingRuleSchedules", "allDays");
		test.siteConfigurationAction.clickPickRoutingRuleButton("SaveText");
		
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(facilityOnWFAScreen);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(ruleName);
		test.siteConfigurationAction.clickRecordNameToEdit(ruleName);
		
		Assert.assertTrue(test.siteConfigurationAction.verifyScheduleExist(scheduleName));
	}
	
	
	@Test(priority = 3, description = "VPLX: Manage Schedules: [UI]: [Integration]: When an existing Schedule "
			+ "is edited, the updated Schedule details gets reflected on Add/Edit Routing screen"
			+ "\n"
			+ "VPLX: Manage Schedules: [UI]: [Integration]: When a schedule name  "
			+ "which is not mapped to any Routing Rule is updated, the same gets updated on Routing Rule screen")
	public void Test02_Test03_1106957_1111441() {
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Schedules");
		
		// existing schedule updated
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleName);
		Assert.assertTrue(test.supportDataActions.verifyButtons("cancel"));
		Assert.assertTrue(test.supportDataActions.verifyButtons("save"));
		scheduleNameUpdated = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"scheduleName", getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		startTime = test.siteConfigurationAction.selectValueForDropDown("startTime",
				getData("SchedulePicksDetails.StartHour"));
		endTime = test.siteConfigurationAction.selectValueForDropDown("endTime",
				getData("SchedulePicksDetails.EndHour"));
		day = test.siteConfigurationAction.clickToSetNewDays();
		
		test.siteConfigurationAction.clickSaveButton();
		if(test.siteConfigurationAction.isButtonDisplayedUsingId("primary")) {
			test.siteConfigurationAction.clickButton("primary");
		}
		
		Assert.assertFalse(test.siteConfigurationAction.verifyPopupGetsClosed());
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleNameUpdated);
		
		// new schedule - 2
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.selectValueFromDropDown("defaultFacilityKey", facilityOnWFAScreen);
		scheduleName2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName2);
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.pageRefresh();
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		
		test.siteConfigurationAction.clickRecordNameToEdit(ruleName);
		// updated schedule exist
		Assert.assertTrue(test.siteConfigurationAction.verifyScheduleExist(scheduleNameUpdated));
		// new schedule exist
		Assert.assertTrue(test.siteConfigurationAction.verifyScheduleExist(scheduleName2));
		
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.headerRoutingRuleDisplayed();
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.supportDataActions.verifyLabelIsPresent("Add Routing Rule");
		
		// updated schedule exist
		Assert.assertTrue(test.siteConfigurationAction.verifyScheduleExist(scheduleNameUpdated));
		// new schedule exist
		Assert.assertTrue(test.siteConfigurationAction.verifyScheduleExist(scheduleName2));
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Schedules");
		
		
		// new schedule(2) updated
		test.siteConfigurationAction.clickRecordNameToEdit(scheduleName2);
		scheduleNameUpdated2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"scheduleName", getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		startTime = test.siteConfigurationAction.selectValueForDropDown("startTime",
				getData("SchedulePicksDetails.StartHour"));
		endTime = test.siteConfigurationAction.selectValueForDropDown("endTime",
				getData("SchedulePicksDetails.EndHour"));
		day = test.siteConfigurationAction.clickToSetNewDays();
		test.siteConfigurationAction.clickSaveButton();
		if(test.siteConfigurationAction.isButtonDisplayedUsingId("primary")) {
			test.siteConfigurationAction.clickButton("primary");
		}
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleNameUpdated2);
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.siteConfigurationAction.pageRefresh();
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		
		test.siteConfigurationAction.clickRecordNameToEdit(ruleName);
		// updated schedule exist (the one which is not mapped)
		Assert.assertTrue(test.siteConfigurationAction.verifyScheduleExist(scheduleNameUpdated2));
		
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.headerRoutingRuleDisplayed();
		
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		// updated schedule exist (the one which is not mapped)
		Assert.assertTrue(test.siteConfigurationAction.verifyScheduleExist(scheduleNameUpdated2));
		
		test.siteConfigurationAction.clickPickRoutingRuleCancelButton("back-btn");
		test.siteConfigurationAction.clickPickRoutingRuleButton("primary");
		test.siteConfigurationAction.headerRoutingRuleDisplayed();
	}
	
	
	@Test(priority = 4, description = "VPLX: Manage Schedules: [UI]: [Integration]: When a schedule "
			+ "days or time is updated "
			+ "which is not mapped to any Routing Rule is updated, the same gets updated on Routing Rule screen")
	public void Test04_1111443() {
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		
		Assert.assertTrue(test.siteConfigurationAction
				.verifyUpdatedScheduleTimeFieldOnRoutingRuleScreen(scheduleNameUpdated2, startTime, endTime),
				"[ASSERTION FAILED]: Updated time is not present on Routing rule screen.");
		
		Assert.assertTrue(
				test.siteConfigurationAction.verifyUpdatedScheduleDayFieldOnRoutingRuleScreen(
						scheduleNameUpdated2, "Tue"), 
				"[ASSERTION FAILED]: Updated day is not present on Routing rule screen.");
	}
	
	
	@Test(priority = 5, description = "VPLX: Manage Schedules: [UI]: [Integration]: When a schedule name "
			+ "which is mapped to any Routing Rule is updated,the same gets updated on Routing Rule screen")
	public void Test05_1111442() {
		test.siteConfigurationAction.pageRefresh();
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		// test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		routingRule = test.siteConfigurationAction.enterRoutingRuleName(
				"RoutingRule" + System.currentTimeMillis());
		
		test.siteConfigurationAction.clickToggleOfAddedRecord(scheduleNameUpdated);
		test.supportDataActions.clickButtonWithOutAnyWait("SaveText");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(routingRule, "search");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyScheduleExist(scheduleNameUpdated));
	}
	
	
	@Test(priority = 6, description = "VPLX: Manage Schedules: [UI]: [Integration]: "
			+ "When a new Schedule(which is  mapped to Routing Rule) is deleted then user get a message")
	public void Test06_1111565() {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Schedules");
		test.supportDataActions.verifyLabelIsPresent("Schedules");
		
		test.siteConfigurationAction.clickDeleteScheduleLink(scheduleNameUpdated, "Delete");
		test.siteConfigurationAction.verifyDeleteMessage("Are you sure you want to delete the schedule?");
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.verifyErrorMessageForAlreadyExistingExternalSystem("The schedule is currently in use and cannot be deleted.");
	}
	
	
	@Test(priority = 7, description = "VPLX: Manage Schedules: [UI]: [Integration]: "
			+ "When a new Schedule(which is not mapped to Routing Rule) is deleted then user get a message")
	public void Test07_1111566() {
		
		// Add a new schedule
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.selectValueFromDropDown("defaultFacilityKey", facilityOnWFAScreen);
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
		
		// confirm that newly added Schedule shows on 'Add Routing Rule' screen
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.supportDataActions.verifyLabelIsPresent("Routing Rules");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.supportDataActions.verifyLabelIsPresent("Add Routing Rule");
		Assert.assertTrue(test.siteConfigurationAction.verifyScheduleExist(scheduleName));
		
		// delete newly added schedule
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Schedules");
		test.supportDataActions.verifyLabelIsPresent("Schedules");
		test.siteConfigurationAction.clickDeleteScheduleLink(scheduleName, "Delete");
		test.siteConfigurationAction.verifyDeleteMessage("Are you sure you want to delete the schedule?");
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.verifyScheduleIsNotPressent(scheduleName);
		
		// verify that deleted schedule is not there on 'Add Routing Rule' screen
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.supportDataActions.verifyLabelIsPresent("Routing Rules");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.supportDataActions.verifyLabelIsPresent("Add Routing Rule");
		Assert.assertFalse(test.siteConfigurationAction.verifyDistributorIsNotAddedForParticularDestination(scheduleName));
		
	}
	
}
