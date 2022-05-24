package com.org.tests.mainmenu.uom;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997989 extends BaseTest {

	String unit_code, unit_Description, sortorder, unit_code1, unit_Description1, baseunit, conversion_factor;

	@Test(priority = 1, description = "VPLX: Unit of Measure - Internal UOM : [UI] Code accept alphanumeric value within  the 100 char while editing .")
	public void Test01_1089727_AND_1089731_AND_1089737(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM : [UI] Code accept alphanumeric value within  the 100 char while editing .");
		test.landingPageActions.navigateToFeature("Units of Measure");
		test.supportDataActions.clickOnAddButtonToAddNewUOM("Add Unit of Measure");
		unit_code = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
				"Code" + System.currentTimeMillis());
		unit_Description = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Des" + System.currentTimeMillis());
		//test.siteConfigurationAction.clickCheckboxfacilityitemlevel2("1","Medication Strength");
	    test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(unit_code, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(unit_code);
		unit_code1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
				"Codea" + System.currentTimeMillis());
		unit_Description1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Desa" + System.currentTimeMillis());
		conversion_factor = test.siteConfigurationAction.enterDataInInputField("conversionAmount",
				"1234567891.12345678912345");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
	}

	@Test(priority = 2, description = "VPLX: Unit of Measure - Internal UOM : [UI] User can edit the Strength and Volume both .")
	public void Test02_1089733_AND_1089738(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM : [UI] User can edit the Strength and Volume both .");
		test.siteConfigurationAction.refreshPage();
		test.supportDataActions.enterSearchTermInSearchFieldGl(unit_code1, "search");
		test.supportDataActions.clickToggleButton("true", "toggle");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(unit_code1, "1"));
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(unit_code1);
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxesUnderRoleField("Medication Strength"),
				"[ASSERTION Passed]: checkbox is present by default");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxesUnderRoleField("Medication Volume"),
				"[ASSERTION Passed]: checkbox is present by default");
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		test.siteConfigurationAction.clickCheckboxRoleofUOM("2");
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		test.siteConfigurationAction.clickCheckboxRoleofUOM("2");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

	}

	@Test(priority = 3, description = "VPLX: Unit of Measure - Internal UOM : [UI] Base UOM field default value is Select")
	public void Test03_1089736(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM : [UI] Base UOM field default value is Select");
		test.siteConfigurationAction.refreshPage();
		test.supportDataActions.enterSearchTermInSearchFieldGl(unit_code1, "search");
		test.supportDataActions.clickToggleButton("true", "toggle");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(unit_code1, "1"));
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(unit_code1);
//		baseunit = test.siteConfigurationAction.getFacilityName("baseUOMKey");
//		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewDestination("baseUOMKey", "Select");
		baseunit = test.siteConfigurationAction.getFacilityNameUOM("baseUOMKey");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewDestinationUOM("baseUOMKey", "Select");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

	}

	
	@Test(priority = 4, description = "VPLX: Unit of Measure - Internal UOM : [UI] Error message throws if user  deselect both  the Strength and Volume")
	public void Test04_1089734(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM : [UI] Error message throws if user  deselect both  the Strength and Volume");
		test.siteConfigurationAction.refreshPage();
		test.supportDataActions.enterSearchTermInSearchFieldGl(unit_code1, "search");
		test.supportDataActions.clickToggleButton("true", "toggle");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(unit_code1, "1"));
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(unit_code1);
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		test.siteConfigurationAction.verifyButtonIsDisabled("save");
		
	}

}