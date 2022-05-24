package com.org.tests.mainmenu.carousel;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997952 extends BaseTest {

	String old_data, new_data, data1;
	
	@Test(priority = 0, description = "VPLX: Carousel [UI]: Carousel-Edit: Edit Carousel Popup is displayed when user click on Edit button")
	public void Test00_1129326(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: Carousel-Edit: Edit Carousel Popup is displayed when user click on Edit button");
		test.landingPageActions.navigateToFeature("Carousels");
		test.supportDataActions.clickButton("edit");
		test.supportDataActions.verifyElementsOnEditCarousel(getData("ToggleValue.HoldReason"));
		
	}

	@Test(priority = 1, description = "VPLX: Carousel [UI]: Carousel-Edit: an option to Edit an existing carousel is present under Action column for each carousel.")
	public void Test01_1031623(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: Carousel-Edit: an option to Edit an existing carousel is present under Action column for each carousel.");
		Assert.assertTrue(test.supportDataActions.verifyEditLinkOnCarousel());
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

	@Test(priority = 3, description = "VPLX: Carousel [UI]: Carousel-Edit: user is able to edit carousel successfully")
	public void Test03_1080518(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: Carousel-Edit: user is able to edit carousel successfully");
		test.supportDataActions.clickButton("edit");
		test.supportDataActions.generatingRandomString();
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.EditCarousel"));
	}

	@Test(priority = 4, description = "VPLX: Carousel [UI]: Carousel-Edit: user is able to save with existing carousel name by entering more than 1 space in between the words")
	public void Test04_1033361(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: Carousel-Edit: user is able to save with existing carousel name by entering more than 1 space in between the words");
		test.supportDataActions.clickButton("edit");
		test.supportDataActions.generatingRandomStringWithSpace();
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.EditCarousel"));
	}

	@Test(priority = 5, description = "VPLX: Carousel [UI]: Carousel-Edit: validation message appears for unique Name")
	public void Test05_1032111(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: Carousel-Edit: validation message appears for unique Name");
		old_data = test.supportDataActions.captureDataForParticularColumnAndRow(getData("CarouselColumnNumber.Name"),2);
		test.supportDataActions.clickButton("edit");
		test.supportDataActions.EnterValueInNameFieldInEditCarouselPopup(old_data);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(
				"This Carousel Name already exists. Please provide a unique Carousel Name .");
		test.supportDataActions.clickButton("cancel");
	}

	@Test(priority = 6, description = "VPLX: Carousel [UI]: Carousel-Edit: validation message appears editting carousel with Name=null")
	public void Test06_1032112(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: Carousel-Edit: validation message appears editting carousel with Name=null");
		test.supportDataActions.clickButton("edit");
		test.supportDataActions.clear();
		test.supportDataActions.verifyErrorMessageonAlert("Name can't be empty", "carouselName");
		test.supportDataActions.clickButton("cancel");
	}

	@Test(priority = 7, description = "VPLX: Carousel [UI]: Carousel-Edit: validation message appears for unique Name on editting carousel in Upper case with an existing carousel Name.")
	public void Test07_1032113(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: Carousel-Edit: validation message appears for unique Name on editting carousel in Upper case with an existing carousel Name.");
		test.supportDataActions.clickButton("edit");
		old_data = old_data.toUpperCase();
		test.supportDataActions.EnterValueInNameFieldInEditCarouselPopup(old_data);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(
				"This Carousel Name already exists. Please provide a unique Carousel Name .");
	}

	@Test(priority = 8, description = "VPLX: Carousel [UI]: Carousel-Edit: validation message appears on editting carousel Name with space character only")
	public void Test08_1032119(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: Carousel-Edit: validation message appears on editting carousel Name with space character only");
		test.supportDataActions.clickButton("edit");
		test.supportDataActions.EnterValueInNameFieldInEditCarouselPopup("    ");
		test.supportDataActions.verifyErrorMessageonAlert("Name can't be empty", "carouselName");
		test.supportDataActions.clickButton("save");
	}

	@Test(priority = 9, description = "VPLX: Carousel [UI]: Carousel-Edit: Model field is not editable.")
	public void Test09_1032122(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: Carousel-Edit: Model field is not editable.");
		test.supportDataActions.clickButton("edit");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("carouselModel",2);
		//test.supportDataActions.verifyModelIsDisabled();
	}

}
