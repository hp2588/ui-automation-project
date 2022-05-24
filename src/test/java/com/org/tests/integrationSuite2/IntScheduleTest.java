package com.org.tests.integrationSuite2;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class IntScheduleTest extends BaseTest {
	
	String scheduleName;

	@Test(priority = 1, description = "VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets populated in Facility dropdown on Add/Edit Schedule page  for a user having access to that Facility")
	public void Test01_1130464(Method method) {
		test.landingPageActions.navigateToFeature("Schedules");
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
		TestDataPropertyReaderAndWriter.setProperty("ScheduleName", scheduleName);
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
		TestDataPropertyReaderAndWriter.setProperty("ScheduleName", scheduleName);
	}
}
