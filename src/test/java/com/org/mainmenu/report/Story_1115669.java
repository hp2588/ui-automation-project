package com.org.mainmenu.report;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1115669 extends BaseTest {

	@Test(priority = 1, description = "VPLX:Navigation from VPLX to Reports: [UI]:Support/Admin user "
			+ "has access for Report section")
	public void Test01_1128170(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Navigation from VPLX to Reports: [UI]:Support user has access for Report section");
		test.landingPageActions.navigateToMenu("Reports");
		test.landingPageActions.navigateToMenu("Reports");
		test.supportDataActions.switchToReportTab(1);
		test.supportDataActions.switchToFrame("mainFrame");
		//test.supportDataActions.verifyFailureText("Unexpected error occurred, please try again later.");
		Assert.assertTrue(test.supportDataActions.verifyDropdownOnReportingTab());
	}
	
	
	// User is able to redirect to the url in new tab:https://dev-rpt21.bdx-cloud.com/ReportComposer/Sso
	@Test(priority = 2, description = "VPLX:Navigation from VPLX to Reports: [UI]:Support/Admin "
			+ "is navigated to the url in new tab.")
	public void Test02_1128187(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Navigation from VPLX to Reports: [UI]:Support/Admin is navigated to the url in new tab.");
		test.landingPageActions.navigateToMenu("Reports");
		test.landingPageActions.navigateToMenu("Reports");
		test.supportDataActions.switchToReportTab(1);
		test.supportDataActions.switchToFrame("mainFrame");
		//test.supportDataActions.verifyFailureText("Unexpected error occurred, please try again later.");
		Assert.assertTrue(test.supportDataActions.verifyDropdownOnReportingTab());
	}
	
	
	@Test(priority = 3, description = "VPLX:Navigation from VPLX to Reports: [UI]:User is able to see the "
			+ "facility in the drop down once navigated to reports url")
	public void Test03_1153863(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"User is able to see the facility in the drop down once navigated to reports url");
		
		test.supportDataActions.verifyDefaultFacilityOnReportDropdown();
	}
	
}
