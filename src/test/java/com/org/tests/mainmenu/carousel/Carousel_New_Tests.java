package com.org.tests.mainmenu.carousel;

import java.lang.reflect.Method;
import java.util.ArrayList;
//import java.util.List;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Carousel_New_Tests extends BaseTest {
	ArrayList<String> crouserList;
	String c_name, crouser;
	List<String> actualData, expectedData;

	@Test(priority = 1, description = " VPLX: Carousel [UI]: The carousel search is applied on the change")
	public void Test01_1030893(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: The user is able to search on the Carousel description field");
		test.landingPageActions.navigateToFeature("Carousels");
		crouser = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.clearSearchBoxField("searchFilter");
		c_name = crouser.substring(0, 6);
		test.supportDataActions.enterSearchTermInSearchFieldGl(c_name, "searchFilter");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(c_name, "1"));
	}

	@Test(priority = 2, description = "VPLX: Carousel [UI]: The user is able to search Carousel description by typing spaces.")
	public void Test02_1032134(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: The user is able to search Carousel description by typing spaces.");

		test.supportDataActions.clearSearchBoxField("searchFilter");
		test.supportDataActions.enterSearchTermInSearchFieldGl("", "searchFilter");
	}

	@Test(priority = 3, description = "VPLX: Carousel [UI]: The user is able to search on the Carousel description field by pasting text in the search box")
	public void Test03_1032141(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: The user is able to search on the Carousel description field by pasting text in the search box");

		crouser = test.supportDataActions.getColumnFirstData("1");
		System.out.println(crouser);
		c_name = crouser.substring(0, 6);
		test.supportDataActions.enterSearchTermInSearchFieldGl(c_name, "searchFilter");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(c_name, "1"));
	}

	// Story marked Obsolete

	/*
	 * @Test(priority = 4, description =
	 * "VPLX: Carousel [UI]: Carousel-Edit: user is not able to select/deselect radio-button options."
	 * ) public void Test04_1032130(Method method) {
	 * ExtentTestManager.startTest(method.getName(),
	 * "VPLX: Carousel [UI]: Carousel-Edit: user is not able to select/deselect radio-button options."
	 * );
	 * 
	 * test.supportDataActions.clickButton("edit");
	 * Assert.assertTrue(test.supportDataActions.
	 * verifyelementisdisabled("form-group mb-8 disabled disabled-radio"),
	 * "[ASSERTION FAILED]: Radio Button is Enabled.");
	 * Assert.assertTrue(test.supportDataActions.
	 * verifyelementisdisabled("form-group disabled disabled-radio"),
	 * "[ASSERTION FAILED]: Radio Button is Enabled.");
	 * 
	 * 
	 * }
	 * 
	 * @Test(priority = 5, description =
	 * "	VPLX: Carousel [UI]: Carousel-Edit: user is not able to edit carousel by copy & pasting the existing carousel name"
	 * ) public void Test05_1035087(Method method) {
	 * ExtentTestManager.startTest(method.getName(),
	 * "VPLX: Carousel [UI]: Carousel-Edit: user is not able to edit carousel by copy & pasting the existing carousel name"
	 * );
	 * 
	 * crouserList = test.supportDataActions.captureDataForParticularColumn("5");
	 * c_name = crouserList.get(2); System.out.println(crouser);
	 * test.supportDataActions.clickButton("edit");
	 * test.supportDataActions.EnterValueInNameFieldInEditCarouselPopup(c_name);
	 * test.supportDataActions.clickButton("save");
	 * test.supportDataActions.verifyErrorMessageForAlreadyExistingName(
	 * "This Carousel Name already exists. Please provide a unique Carousel Name ."
	 * ); test.supportDataActions.clickButton("cancel");
	 * 
	 * 
	 * 
	 * }
	 */

	// marked obsolete

	/*
	 * @Test(priority = 6, description =
	 * "	VPLX:Carousel [UI] - Feature Testing- Sorting is not done when only active data is present."
	 * ) public void Test06_1041292() {
	 * 
	 * =======Sort icon not clicked================
	 * 
	 * actualData = test.siteConfigurationAction.getColumnData("5"); expectedData =
	 * test.siteConfigurationAction.getColumnData("5");
	 * Collections.sort(expectedData); Assert.assertEquals(actualData,
	 * expectedData);
	 * 
	 * }
	 */
}
