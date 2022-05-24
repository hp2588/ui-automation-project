package com.org.tests.mainmenu.customlabels;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1058052 extends BaseTest {

	@Test(priority = 1, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Enabled active/inactive toggle button is present on the Custom label page")
	public void Test01_1092693(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Enabled active/inactive toggle button is present on the Custom label page");
	
		test.landingPageActions.navigateToFeature("Custom Labels");
		test.supportDataActions.verifyLabelIsPresent("Custom Labels");
		
		test.siteConfigurationAction.verifyToggleIsInActive("toggle");
		/*test.siteConfigurationAction.clickActiveToggle("Show Inactive");
		test.siteConfigurationAction.verifyToggleIsActive("toggle");
		*/
	}
	
	@Test(priority = 2, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:When toggle button is inactive only active labels displayed as list on the home screen of Custom label.")
	public void Test02_1092699(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:When toggle button is inactive only active labels displayed as list on the home screen of Custom label.");
	
		test.siteConfigurationAction.verifySearchResults("Active", "4");
	
	}
	
	@Test(priority = 3, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:When toggle button is active all active and inactive labels displayed as list on the home screen of Custom label.")
	public void Test03_1092696(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:When toggle button is active all active and inactive labels displayed as list on the home screen of Custom label.");
		test.siteConfigurationAction.clickActiveToggle("Show Inactive");
		test.siteConfigurationAction.verifyToggleIsActive("toggle");
		
		test.siteConfigurationAction.verifyActiveAndInactiveResults("Inactive","Active", "4");
		
	}
	
}
