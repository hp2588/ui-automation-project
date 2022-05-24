package com.org.astarsupportdatatest;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Edit_Schedule_Test extends BaseTest {

String scheduleName, scheduleNameUpdated, facilityOnWFAScreen, routingRule, startTime, endTime, day;
	
	

	
	@Test(priority = 1, description = "VPLX : System update information of the schedule associated to a Rule when user edits schedule.")
	public void Test01_1121595() {
		facilityOnWFAScreen =
				  TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		test.landingPageActions.navigateToFeature("Schedules");
		test.supportDataActions.verifyLabelIsPresent("Schedules");
		// test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facilityOnWFAScreen);

		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		// test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("defaultFacilityKey");
		test.siteConfigurationAction.selectValueFromDropDown("defaultFacilityKey", facilityOnWFAScreen);
		// test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("scheduleName");
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickToSetDays();
		// test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));

		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.supportDataActions.verifyLabelIsPresent("Routing Rules");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.supportDataActions.verifyLabelIsPresent("Add Routing Rule");

		test.siteConfigurationAction.verifyScheduleExist(scheduleName);

	}

	@Test(priority = 3, description = "VPLX : Warning message is displayed to the user after editing schedules which is already associated to a rule.")
	public void Test02_1121620() {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Schedules");
		test.supportDataActions.verifyLabelIsPresent("Schedules");

		test.siteConfigurationAction.clickEditScheduleLink(scheduleName, "Edit");
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
		Assert.assertFalse(test.siteConfigurationAction.verifyPopupGetsClosed());
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleNameUpdated);

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Routing Rules");
		test.supportDataActions.verifyLabelIsPresent("Routing Rules");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		test.siteConfigurationAction.clickPickRoutingRuleButton("add");
		test.supportDataActions.verifyLabelIsPresent("Add Routing Rule");

		test.siteConfigurationAction.verifyScheduleExist(scheduleNameUpdated);

	}
	

}
