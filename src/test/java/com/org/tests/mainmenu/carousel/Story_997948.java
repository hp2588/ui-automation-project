package com.org.tests.mainmenu.carousel;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997948 extends BaseTest {

	ArrayList<String> previous_data, sorted_data;

	@Test(priority = 1, description = "VPLX: Carousel [UI]: The user is able to view the list of carousel models")
	public void Test01_1030148(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: The user is able to view the list of carousel models");
		test.landingPageActions.navigateToFeature("Carousels");
		test.supportDataActions.verifyRecordList();
		Assert.assertTrue(test.supportDataActions.verifyCarouselStatusAsActive());
	}

	@Test(priority = 2, description = "VPLX: Carousel [UI]: The  list of carousel models is sorted alphanumerically in ascending order of the field Description")
	public void Test02_1030160(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: The user is able to view the list of carousel models");
		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("CarouselColumnNumber.Name"));
		System.out.println("Previous data :  " + previous_data);
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder(getData("CarouselColumnNumber.Name"));
		System.out.println("Sorted data :  " + sorted_data);
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");

	}

	@Test(priority = 3, description = "VPLX: Carousel [UI]: The list of carousel models displays only the active ones by default")
	public void Test03_1030164(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: The list of carousel models displays only the active ones by default");
		Assert.assertTrue(test.supportDataActions.verifyCarouselStatusAsActive());
	}
	
	//CR changes, marked Obsolete

	/*
	 * @Test(priority = 4, description =
	 * "VPLX: Carousel [UI]: The user is able to toggle to exclude the inactive carousels in the listing"
	 * ) public void Test04_1030180(Method method) {
	 * ExtentTestManager.startTest(method.getName(),
	 * "VPLX: Carousel [UI]: The user is able to toggle to exclude the inactive carousels in the listing"
	 * ); test.supportDataActions.clickToggleButton("true",
	 * getData("ToggleValue.Carousel"));
	 * test.supportDataActions.clickToggleButton("false",
	 * getData("ToggleValue.Carousel"));
	 * Assert.assertTrue(test.supportDataActions.verifyCarouselStatusAsActive()); }
	 */

	/*
	 * @Test(priority = 5, description =
	 * "VPLX: Carousel [UI]: The user is able to toggle and include the inactive carousels in the listing"
	 * ) public void Test05_1030173(Method method) {
	 * ExtentTestManager.startTest(method.getName(),
	 * "VPLX: Carousel [UI]: The user is able to toggle and include the inactive carousels in the listing"
	 * ); test.supportDataActions.clickToggleButton("true",
	 * getData("ToggleValue.Carousel"));
	 * Assert.assertTrue(test.supportDataActions.verifyCarouselStatus()); }
	 */

	@Test(priority = 6, description = "VPLX: Carousel [UI]: The combined list of active & inactive carousels is displayed in alphanumeric order")
	public void Test06_1030183(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: The combined list of active & inactive carousels is displayed in alphanumeric order"); 
		// test.supportDataActions.clickToggleButton("true",getData("ToggleValue.Carousel"));																														
		// test.supportDataActions.verifyCarouselStatus();
		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("CarouselColumnNumber.Name"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder(getData("CarouselColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
	}

	@Test(priority = 7, description = "VPLX: Carousel [UI]: The user is able to sort each column of list of carousels")
	public void Test07_1030225(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: The user is able to sort each column of list of carousels"); 
		// test.supportDataActions.clickToggleButton("true",getData("ToggleValue.Carousel"));
		// test.supportDataActions.verifyCarouselStatus();
		test.supportDataActions.verifyAndClickSortIcon(getData("CarouselColumnName.Name"));
		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("CarouselColumnNumber.Name"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInDescendingOrder(getData("CarouselColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
		test.supportDataActions.verifyAndClickSortIcon(getData("CarouselColumnName.Model"));
		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("CarouselColumnNumber.Model"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder(getData("CarouselColumnNumber.Model"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Model column data is not sorted in ascending order");
		test.supportDataActions.verifyAndClickSortIcon(getData("CarouselColumnName.Type"));
		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("CarouselColumnNumber.Type"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInDescendingOrder(getData("CarouselColumnNumber.Type"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Type column data is not sorted in ascending order");
		test.supportDataActions.verifyAndClickSortIcon(getData("CarouselColumnName.Access"));
		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("CarouselColumnNumber.Access"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInDescendingOrder(getData("CarouselColumnNumber.Access"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Access column data is not sorted in ascending order");

	}
	
	//CR changes, marked Obsolete

	/*
	 * @Test(priority = 8, description =
	 * "VPLX: Carousel [UI]: Sorting action is not performed for Status column when only active data is present."
	 * ) public void Test08_1076528(Method method) {
	 * ExtentTestManager.startTest(method.getName(),
	 * "VPLX: Carousel [UI]: Sorting action is not performed for Status column when only active data is present."
	 * ); test.supportDataActions.clickToggleButton("false",
	 * getData("ToggleValue.Carousel"));
	 * test.supportDataActions.verifyCarouselStatusAsActive();
	 * Assert.assertTrue(test.supportDataActions.verifySortIcon(getData("Actions")))
	 * ;
	 * 
	 * }
	 */

	@Test(priority = 9, description = "VPLX: Carousel [UI]: The user is able to view the list of carousel models of length not greater than 50 characters")
	public void Test09_1032157(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: The user is able to view the list of carousel models of length not greater than 50 characters");
		Assert.assertTrue(
				test.supportDataActions.verifyLengthForParticularColumn(getData("CarouselColumnNumber.Name")));
		Assert.assertTrue(
				test.supportDataActions.verifyLengthForParticularColumn(getData("CarouselColumnNumber.Model")));

	}
	
	//CR changes, marked Obsolete

	/*
	 * @Test(priority = 10, description =
	 * "VPLX: Carousel [UI]: Text Inactive is displayed as 'Inactive'.") public void
	 * Test10_1076530(Method method) { ExtentTestManager.startTest(method.getName(),
	 * "VPLX: Carousel [UI]: Text Inactive is displayed as 'Inactive'.");
	 * test.supportDataActions.clickToggleButton("true",
	 * getData("ToggleValue.Carousel"));
	 * Assert.assertTrue(test.supportDataActions.verifyCarouselStatusInactive()); }
	 */

	@Test(priority = 11, description = "VPLX: Carousel [UI]: sort icon disappears when no records are found as per searched test")
	public void Test11_1032150(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: sort icon disappears when no records are found as per searched test");
		test.supportDataActions.refreshPage();
		test.supportDataActions.enterSearchTermInSearchField(DateUtil.getTommorrowsDate(), "searchFilter");
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("DosageFormColumnNumber.Code")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("DosageFormColumnNumber.Description")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("DosageFormColumnNumber.SortOrder")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("DosageFormColumnNumber.Staus")));
	}

}
