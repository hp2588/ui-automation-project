package com.org.tests.mainmenu.uom;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997985 extends BaseTest {

	String code,description,sortorder,code1,uom_old;
	
	@Test(priority = 1, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Search textbox is available on the UOM screen .")
	public void Test01_1084497(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Search textbox is available on the UOM screen .");
		test.landingPageActions.navigateToFeature("Units of Measure");
		test.siteConfigurationAction.verifySearchFieldonPickSchedulePage();
	}
	
	@Test(priority = 2, description = "VPLX: Unit of Measure - Internal UOM  : [UI] User is able to search on the basis of code .")
	public void Test02_1084500(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] User is able to search on the basis of code .");
		test.supportDataActions.clickOnAddButtonToAddNewUOM("Add Unit of Measure");
		code = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode","Code" + System.currentTimeMillis());
		description = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("descriptionText","Des11" + System.currentTimeMillis());
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		sortorder=test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("sortValue","777");
		test.supportDataActions.clickToggleButton("true","activeFlag");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(code, "search");
		test.siteConfigurationAction.clickActiveToggle("Show Inactive");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(code, "1"));
	}
	
	@Test(priority = 3, description = "VPLX: Unit of Measure - Internal UOM  : [UI] User is able to search on the basis of description field  .")
	public void Test03_1084503(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] User is able to search on the basis of description field  .");
		test.supportDataActions.clearSearchBox("search");
		test.supportDataActions.enterSearchTermInSearchFieldGl(description, "search");
		test.siteConfigurationAction.clickActiveToggle("Show Inactive");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(description, "2"));
	}
	
	@Test(priority = 4, description = "VPLX: Unit of Measure - Internal UOM  : [UI] User is not  able to search Other than description and Code field .")
	public void Test04_1084505_AND_1084511(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] User is not  able to search Other than description and Code field .");
		test.siteConfigurationAction.clearInputBox("scheduleSearch");
		test.supportDataActions.enterSearchTermInSearchFieldGl(sortorder, "search");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Matching Results.");
	}
	
	@Test(priority = 5, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Search result gets display after clicking on the enter button .")
	public void Test05_1084510(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Search result gets display after clicking on the enter button .");
		test.supportDataActions.clearSearchBox("search");
		test.siteConfigurationAction.verifySearchFieldonPickSchedulePage();
		test.supportDataActions.enterSearchTermInSearchFieldGl(code, "search");
		test.siteConfigurationAction.clickActiveToggle("Show Inactive");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(code, "1"));
	}

	@Test(priority = 6, description = "VPLX: Unit of Measure - Internal UOM  : [UI] User is able to Reset on clicking 'X' inside search box.")
	public void Test06_1084506(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] User is able to Reset on clicking 'X' inside search box.");
	test.supportDataActions.clearSearchBox("search");
    test.supportDataActions.verifyRecordList();
    
	}
	
}