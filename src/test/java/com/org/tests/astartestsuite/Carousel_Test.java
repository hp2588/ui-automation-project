package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Carousel_Test extends BaseTest {
	@Test(priority = 1, description = "To verify Carousel Description with a unique name is acceptable upon saving.")
	public void Test01_1031623(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"To verify Carousel Description with a unique name is acceptable upon saving.");
	
	test.landingPageActions.navigateToFeature("Carousels");
	Assert.assertTrue(test.supportDataActions.verifyEditLinkOnCarousel());
	test.supportDataActions.clickButton("edit");
	test.supportDataActions.generatingRandomString();
	test.supportDataActions.clickButton("save");
	test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.EditCarousel"));

	}
}
