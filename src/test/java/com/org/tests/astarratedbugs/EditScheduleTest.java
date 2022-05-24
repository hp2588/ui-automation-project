package com.org.tests.astarratedbugs;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class EditScheduleTest extends BaseTest {
	
	String scheduleName;

	@Test(priority = 1, description = "VPLX : Warning message is displayed to the user after editing schedules which is already associated to a rule.")
	public void Test01_1121620(Method method) {
		test.landingPageActions.navigateToFeature("Schedules");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToScheduleName(TestDataPropertyReaderAndWriter.getProperty("ScheduleName"));
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyWarningMessageOnEditingAScheduleWhichIsAssociatedToRoutingRule();
	}

}
