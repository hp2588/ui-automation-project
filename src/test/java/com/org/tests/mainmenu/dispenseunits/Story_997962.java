package com.org.tests.mainmenu.dispenseunits;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997962 extends BaseTest {

	String dispenseCode, dispenseCode1, description, sortOrder, Inactive_code, dropDown_external_system;

	@Test(priority = 1, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Search: The user is able to search Dispense Unit on basis of columns- Code ,Description Form,Sort Order,Status")
	public void Test01_1047034(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Search: The user is able to search Dispense Unit on basis of columns- Code ,Description Form,Sort Order,Status");
		test.landingPageActions.navigateToFeature("Dispense Units");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		dispenseCode = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"NewCode" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "9999");

		test.supportDataActions.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults_dispenseunit(dispenseCode, "1"));

		test.supportDataActions.clearSearchBox("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(description, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(description, "2"));

		test.supportDataActions.clearSearchBox("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(sortOrder, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults_dispenseunit(sortOrder, "3"));
		test.supportDataActions.clearSearchBox("search");
		
	}

	@Test(priority = 2, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit search displays the message \"No matches found\" if the search does not return any results")
	public void Test02_1047049(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit search displays the message \"No matches found\" if the search does not return any results");
		test.supportDataActions.clearSearchBox("search");
		test.supportDataActions.enterSearchTermInSearchField(DateUtil.getTommorrowsDate(), "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Matching Results.");
	}

	@Test(priority = 3, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Search: The option to reset Dispense Unit search box is displayed when user types the text")
	public void Test03_1047038(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Search: The option to reset Dispense Unit search box is displayed when user types the text");
		Assert.assertTrue(test.supportDataActions.verifySearchCrossIcon());
	}

	@Test(priority = 4, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit search is reset on clicking 'x' inside search box")
	public void Test04_1047040(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit search is reset on clicking 'x' inside search box");
		test.supportDataActions.clearSearchBox("search");
	}

	@Test(priority = 5, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit list get updated on clicking toggle to exclude Inactive records provided the searched text is present in the search box")
	public void Test05_1047041(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit list get updated on clicking toggle to exclude Inactive records provided the searched text is present in the search box");
		test.supportDataActions.refreshPage();
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", false);
		dispenseCode = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"NewCode" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "9999");
		test.supportDataActions.clickButton("save");
		
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.verifyAndClickSortIcon(getData("CarouselColumnName.Status"));
		Inactive_code = test.supportDataActions.getFirstInactiveRecord();
		test.supportDataActions.enterSearchTermInSearchFieldGl(Inactive_code, "search");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Data Available.");

	}
	

	@Test(priority = 6, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit typed search works on basis of ShowInactive toggle button and option selected from the drop down")
	public void Test06_1047064(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit typed search works on basis of ShowInactive toggle button and option selected from the drop down");
		test.supportDataActions.refreshPage();
		dropDown_external_system = test.supportDataActions.getDropDownValue("externalSystems");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode, "search");
		test.siteConfigurationAction.verifyToggleIsInActive("toggle");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord_dispenseUnit(dispenseCode, "Edit Dispense Unit");
		
		test.siteConfigurationAction.enterSearchTermInSearchField(dispenseCode, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewDestination("externalSystemKey",dropDown_external_system);
		test.siteConfigurationAction.clickButton("cancel");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
	}
	

	@Test(priority = 7, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit search is applied on the change")
	public void Test07_1047036(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Search: The Dispense Unit search is applied on the change");
		test.supportDataActions.refreshPage();
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		dispenseCode1 = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code1" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description1" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "9998");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(dispenseCode, "1"));
		test.supportDataActions.clearSearchBox("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode1, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults_dispenseunit(dispenseCode1, "1"));
	}
	
	@Test(priority = 8, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Search: Sort icon disappears when no records are found as per searched text")
	public void Test08_1047053(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Search: Sort icon disappears when no records are found as per searched text");
		test.supportDataActions.enterSearchTermInSearchField(DateUtil.getTommorrowsDate(), "search");
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("CarouselColumnNumber.Name")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("CarouselColumnNumber.Model")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("CarouselColumnNumber.Type")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("CarouselColumnNumber.Access")));
		Assert.assertFalse(test.supportDataActions.VerifySortIconIsDisabled(getData("CarouselColumnNumber.Status")));

	}

}
