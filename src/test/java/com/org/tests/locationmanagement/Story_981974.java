package com.org.tests.locationmanagement;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_981974 extends BaseTest{
	
	@Test(priority = 1, description = "VPLX:Location-Storage Area: UI:Pagination is implemented on Item Locations with Default value of 25 rows")
	public void Test01_1058054(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Pagination is implemented on Item Locations with Default value of 25 rows");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.siteConfigurationAction.verifyPageNumberOnLocationManagement();
		
	}
	

}
