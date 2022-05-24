package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class CarouselFeature extends BaseTest {
	
	@Test(priority = 1, description = "VPLX: Carousel [UI]: Carousel-Edit: user has ability to toggle and mark the carousel as Active or Inactive")
	public void Test01_1129326(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: Carousel-Edit: user has ability to toggle and mark the carousel as Active or Inactive");
		test.landingPageActions.navigateToFeature("Carousels");
		test.supportDataActions.clickButton("edit");
		test.supportDataActions.verifyElementsOnEditCarousel(getData("ToggleValue.HoldReason"));
		
	}	
	
	@Test(priority = 2, description = "VPLX: Carousel [UI]: Carousel-Edit: user has ability to toggle and mark the carousel as Active or Inactive")
	public void Test02_1031629(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: Carousel-Edit: user has ability to toggle and mark the carousel as Active or Inactive");
		test.supportDataActions.clickButton("edit");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.HoldReason"));
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.EditCarousel"));
	}

}
