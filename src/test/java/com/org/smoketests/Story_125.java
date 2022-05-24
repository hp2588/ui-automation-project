package com.org.smoketests;

import static com.org.automation.utils.YamlReader.getData;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_125 extends BaseTest {
	
	// TODO
	@Test(priority = 1, description = "VPLX:Navigation from VPLX to Reports: [UI]:Support/Admin user "
			+ "has access for Report section"
			+ "\n&\n"
			+ "VPLX:Navigation from VPLX to Reports: [UI]:Support/Admin is navigated to the url in new tab."
			+ "\n&\n"
			+ "VPLX:Navigation from VPLX to Reports: [UI]:User is able to see the facility "
			+ "in the drop down once navigated to reports url")
	public void Test01_1128170_1128187_1153863() {
		
		test.landingPageActions.navigateToMenu("Reports");
		test.landingPageActions.navigateToMenu("Reports");
		test.supportDataActions.switchToReportTab(1);
		test.supportDataActions.switchToFrame("mainFrame");
		//test.supportDataActions.verifyFailureText("Unexpected error occurred, please try again later.");
		
		// User is able to redirect to the url in new tab:https://dev-rpt21.bdx-cloud.com/ReportComposer/Sso
		
		Assert.assertTrue(test.supportDataActions.verifyDropdownOnReportingTab());
		
	}
	
}
