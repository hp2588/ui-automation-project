
package com.org.tests.mainmenu.carousel;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997949 extends BaseTest {

	String old_data, new_data,carousel_updated;

	@Test(priority = 1, description = "VPLX: Carousel [UI]: The user is able to search on the Carousel description field")
	public void Test01_1030874(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: The user is able to search on the Carousel description field");
		test.landingPageActions.navigateToFeature("Carousels");
		old_data = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.enterSearchTermInSearchField(old_data, getData("SearchFieldValue.Carousel"));
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(old_data, new_data);
	}
	
	@Test(priority = 2, description = "VPLX: Carousel [UI]: The option to reset carousel search box is displayed when user types the text")
	public void Test02_1030896(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: The option to reset carousel search box is displayed when user types the text");
		test.supportDataActions.refreshPage();
		old_data = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.enterSearchTermInSearchField(old_data, getData("SearchFieldValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifySearchCrossIcon());

	}

	@Test(priority = 3, description = "VPLX: Carousel [UI]: The carousel search is reset on clicking option to reset carousel search box")
	public void Test03_1031276(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: The user is able to search Carousel description by typing spaces.");
		test.supportDataActions.refreshPage();
		old_data = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.enterSearchTermInSearchField(old_data, getData("SearchFieldValue.Carousel"));
		test.supportDataActions.resetSearch();
	}
	
	//CR changes, marked Obsolete

	/*
	 * @Test(priority = 4, description =
	 * "VPLX: Carousel [UI]: The carousel list get updated on clicking toggle to exclude Inactive records provided the searched text is present in the search box"
	 * ) public void Test04_1031403(Method method) {
	 * ExtentTestManager.startTest(method.getName(),
	 * "VPLX: Carousel [UI]: The carousel list get updated on clicking toggle to exclude Inactive records provided the searched text is present in the search box"
	 * ); test.supportDataActions.refreshPage();
	 * test.supportDataActions.clickToggleButton("true",
	 * getData("ToggleValue.Carousel"));
	 * //test.supportDataActions.verifyAndClickSortIcon(getData(
	 * "CarouselColumnName.Status")); old_data =
	 * test.supportDataActions.getColumnFirstInactiveData();
	 * test.supportDataActions.enterSearchTermInSearchField(old_data,
	 * getData("SearchFieldValue.Carousel"));
	 * test.supportDataActions.clickToggleButton("false",
	 * getData("ToggleValue.Carousel"));
	 * Assert.assertTrue(test.supportDataActions.verifyRecordListEmpty()); }
	 */

	@Test(priority = 5, description = "VPLX: Carousel [UI]: The carousel search displays the message 'No matches found' if the search does not return any results")
	public void Test05_1031407(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]: The carousel search displays the message 'No matches found' if the search does not return any results");
		test.supportDataActions.enterSearchTermInSearchField(DateUtil.getTommorrowsDate(), "searchFilter");
		Assert.assertTrue(test.supportDataActions.verifyRecordListEmpty());
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Matching Results.");
	}
	@Test(priority = 6, description = "VPLX: Carousel [UI]:  sort icon disappears when no records are found as per searched text")
	public void Test06_1032155(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Carousel [UI]:  sort icon disappears when no records are found as per searched text");
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("CarouselColumnNumber.Name")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("CarouselColumnNumber.Model")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("CarouselColumnNumber.Type")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("CarouselColumnNumber.Access")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("CarouselColumnNumber.Status")));
	}
}
