package com.org.tests.mainmenu.dosageforms;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997955 extends BaseTest {

	String dosageCode,InactivedosageCode, description, sortOrder, Inactive_code, dropDown_external_system;

	@Test(priority = 1, description = "VPLX: Dosage Form [UI]: Dosage Form-Search: The user is able to search Dosage Forms on basis of columns- Code ,Description Form,Sort Order,Status")
	public void Test01_1038398(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Search: The user is able to search Dosage Forms on basis of columns- Code ,Description Form,Sort Order,Status");

		test.landingPageActions.navigateToFeature("Dosage Forms");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");
		dosageCode = test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "9998");
		
		test.supportDataActions.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dosageCode, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(dosageCode, "1"));
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(description, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(description, "2"));
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(sortOrder, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(sortOrder, "3"));
		//Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsForSortOrder(sortOrder, "1"));
		test.supportDataActions.clearSearchBoxField("search");
	}

	@Test(priority = 2, description = "VPLX: Dosage Form [UI]: Dosage Form-Search: The Dosage Form search displays the message 'No matches found' if the search does not return any results")
	public void Test02_1038551(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Search: The Dosage Form search displays the message 'No matches found' if the search does not return any results");
		test.supportDataActions.enterSearchTermInSearchField(DateUtil.getTommorrowsDate(), "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Matching Results.");
	}

	@Test(priority = 3, description = "VPLX: Dosage Form [UI]: Dosage Form-Search: The option to reset Dosage Form search box is displayed when user types the text")
	public void Test03_1031623(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Search: The option to reset Dosage Form search box is displayed when user types the text");
		Assert.assertTrue(test.supportDataActions.verifySearchCrossIcon());
	}

	@Test(priority = 4, description = "VPLX: Dosage Form [UI]: Dosage Form-Search: The Dosage Form search is reset on clicking 'x' inside search box")
	public void Test04_1038544(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Search: The Dosage Form search is reset on clicking 'x' inside search box");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.verifyRecordList();
	}

	@Test(priority = 5, description = "VPLX: Dosage Form [UI]: Dosage Form-Search: The Dosage Form list get updated on clicking toggle to exclude Inactive records provided the searched text is present in the search box")
	public void Test05_1038549(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Search: The Dosage Form list get updated on clicking toggle to exclude Inactive records provided the searched text is present in the search box");
		test.supportDataActions.refreshPage();
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dosage Form");

		InactivedosageCode=test.supportDataActions.EnterRandomValueInInputField("dosageFormCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		 test.supportDataActions.EnterValueInInputField("sortValue", "9998");
		 test.siteConfigurationAction.verifyToggleIsActive("activeFlag");
			test.siteConfigurationAction.clickActiveToggle("Active");
			
			test.siteConfigurationAction.verifyToggleIsInActive("activeFlag");
		
		test.supportDataActions.clickButton("save");
		
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.verifyAndClickSortIcon(getData("CarouselColumnName.Status"));
		 test.supportDataActions.verifyAndClickSortIcon(getData("CarouselColumnName.Status"));
		Inactive_code = test.supportDataActions.getFirstInactiveRecord();
		test.supportDataActions.enterSearchTermInSearchBoxUsingJavascriptExecutor(Inactive_code,"search");
		//test.supportDataActions.enterSearchTermInSearchFieldGl(Inactive_code, "search");
		//Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(InactivedosageCode, "1"));
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		//Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Data Available.");
Assert.assertFalse(test.siteConfigurationAction.verifyInactiveAddedRecordNameInList(Inactive_code), "[Assertion Failed]: Inactive record is displayed though Inacitve toggle is off.");
	}

	@Test(priority = 6, description = "VPLX: Dosage Form [UI]: Dosage Form-Search: The user is able to use spaces while typing in the search box.")
	public void Test06_1038553(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Search: The user is able to use spaces while typing in the search box.");
		test.supportDataActions.clearSearchBox("search");
		test.supportDataActions.enterSearchTermInSearchField("", "search");

	}

	@Test(priority = 7, description = "VPLX: Dosage Form [UI]: Dosage Form-Search: The Dosage Form typed search works on basis of ShowInactive toggle button and option selected from the drop down ")
	public void Test07_1038602(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Search: The Dosage Form typed search works on basis of ShowInactive toggle button and option selected from the drop down ");
		test.supportDataActions.refreshPage();
		dropDown_external_system = test.supportDataActions.getDropDownValue("externalSystems");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dosageCode, "search");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToPrinterName(dosageCode);
		test.siteConfigurationAction.verifyToggleIsInActive("toggle");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewFacility("externalSystemKey",
				dropDown_external_system);

	}
	
	@Test(priority = 8, description = "VPLX: Dosage Form [UI]: Dosage Form-Search: sort icon disappears when no records are found as per searched text")
	public void Test08_1038586(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dosage Form-Search: sort icon disappears when no records are found as per searched text");
		test.supportDataActions.refreshPage();
		test.supportDataActions.enterSearchTermInSearchField(DateUtil.getTommorrowsDate(), "search");
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("DosageFormColumnNumber.Code")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("DosageFormColumnNumber.Description")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("DosageFormColumnNumber.SortOrder")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("DosageFormColumnNumber.Staus")));
	}

}
