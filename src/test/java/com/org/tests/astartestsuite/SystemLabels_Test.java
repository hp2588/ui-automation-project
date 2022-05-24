package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class SystemLabels_Test extends BaseTest{
	
	@Test(priority = 1, description = "VPLX : Site Configuration -List of Transaction Priorities configured in the System")
	public void Test01_1114470(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX : Site Configuration -List of Transaction Priorities configured in the System");
		test.landingPageActions.navigateToFeature("Standard Labels");
		Assert.assertFalse(test.siteConfigurationAction.verifySystemLabelList().isEmpty(), "[ASSERTION FAILED]: System Label List is Empty");
	}

}
