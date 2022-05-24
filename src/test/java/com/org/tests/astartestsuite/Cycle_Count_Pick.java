package com.org.tests.astartestsuite;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Cycle_Count_Pick extends BaseTest {

	
	@Test(priority = 1, description = "VPLX : Cycle count Pick -The system requires the user to enter the earliest expiration date\r\n" + 
			"when earliest expiration date is enabled for the formulary item and enable for Facility cycle\r\n" + 
			"count.")
	public void Test01_1117225_1117227(Method method) {
		
	ExtentTestManager.startTest(method.getName(),"VPLX : Cycle count Pick -The system requires the user to enter the earliest expiration date\r\n" + 
			"when earliest expiration date is enabled for the formulary item and enable for Facility cycle\r\n" + 
			"count.");
	test.landingPageActions.navigateToFeature("Facilities");
	test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	test.siteConfigurationAction.clickTab("Cycle Counts");
	//test.siteConfigurationAction.clickCheckboxForEarliestExpiration("enableOldestExpirationDateFlag");
	test.siteConfigurationAction.clickSaveButton();
	}
	
	
}
