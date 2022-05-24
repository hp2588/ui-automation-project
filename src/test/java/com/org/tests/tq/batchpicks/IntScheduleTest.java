package com.org.tests.tq.batchpicks;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class IntScheduleTest extends BaseTest {
	
	String scheduleName;

	@Test(priority = 1, description = "VPLX:Manage Printers:Verify User is able to add schedule")
	public void Test01_Add_Schedule(Method method) {
		test.landingPageActions.navigateToFeature("Schedules");
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("facilityModelKey");
		test.siteConfigurationAction.selectValueForDropDown("facilityModelKey",
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
