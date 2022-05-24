package com.org.tests.locationAssignment_manageStorageArea;

import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1138088 extends BaseTest {

	@Test(priority = 1, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is able to click on Go to carousel shelf  visible on shelf actions only for Carousel ISA")
	public void Test01_1135180(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User is able to click on Go to carousel shelf visible on shelf actions only for Carousel ISA");
		test.landingPageActions.navigateToFeature("Item Locations");
		Assert.assertTrue(test.locationAssignmentSettings.verifyCarouselSelf(),
				"User is not  able to go on catouselSelf");
	}
	
}
