package com.org.data.api;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class UOMTest extends BaseTest {

	String code, description, sortorder, code1, uom_old;


	@Test(priority = 1, description = "VPLX: Unit of Measure - Internal UOM  : [UI] User is able to search on the basis of code .")
	public void Test01_1084500(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] User is able to search on the basis of code .");
		test.landingPageActions.navigateToFeature("Units of Measure");
		test.siteConfigurationAction.verifySearchFieldonPickSchedulePage();
		test.supportDataActions.clickOnAddButtonToAddNewUOM("Add Unit of Measure");
		code = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
				"Code" + System.currentTimeMillis());
		description = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("descriptionText",
				"Des11" + System.currentTimeMillis());
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		sortorder = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("sortValue", "777");
		test.supportDataActions.clickToggleButton("true", "activeFlag");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(code, "search");
		test.siteConfigurationAction.clickActiveToggle("Show Inactive");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(code, "1"));
		
	}
}
