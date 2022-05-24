package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class UnitofMeasureFeature extends BaseTest{

	String code,Description,sortorder,code1;
	String unit_code1,unit_code,unit_Description1,unit_Description,unit_code2,unit_Description2,conversion_factor;
	
	@Test(priority = 1, description = "VPLX: Unit of Measure - Internal UOM : [UI] Code  accepts  alphanumeric value within  the 100 char")
	public void Test01_1085780__AND_1089731(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM : [UI] Code  accepts  alphanumeric value within  the 100 char");
		test.landingPageActions.navigateToFeature("Units of Measure");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add Unit of Measure");
		code = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode","Code" + System.currentTimeMillis());
		Description = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("descriptionText","Des" + System.currentTimeMillis());
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		test.siteConfigurationAction.enterDataInInputField("conversionAmount", "1234567891.12345678912345");
		test.siteConfigurationAction.clickButton("save");
		}
	
	@Test(priority = 2, description = "VPLX: Unit of Measure - Internal UOM : [UI] User can edit the Strength and Volume both .")
	public void Test02_1089733_AND_1089738(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM : [UI] User can edit the Strength and Volume both .");
		test.siteConfigurationAction.refreshPage();
		test.supportDataActions.enterSearchTermInSearchFieldGl(code, "search");
		test.supportDataActions.clickToggleButton("true", "toggle");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(code, "1"));
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(code);
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxesUnderRoleField("Medication Strength"),
				"[ASSERTION Passed]: checkbox is present by default");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxesUnderRoleField("Medication Volume"),
				"[ASSERTION Passed]: checkbox is present by default");
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		test.siteConfigurationAction.clickCheckboxRoleofUOM("2");
		test.siteConfigurationAction.clickCheckboxRoleofUOM("2");
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		

	}
	
	@Test(priority = 3, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Listing of Predefined values of base unit , if medication strength Check box is selected.")
	public void Test03_1085794_AND_1085795(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Listing of Predefined values of base unit , if medication strength Check box is selected.");
		{
			test.supportDataActions.clickOnAddButtonToAddNewISA("Add Unit of Measure");
			test.siteConfigurationAction.verifyCheckboxesUnderRoleField("Medication Strength");
			test.siteConfigurationAction.verifyCheckboxesUnderRoleField("Medication Volume");
			test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
			test.siteConfigurationAction.clickCheckboxRoleofUOM("2");
			test.siteConfigurationAction.selectValueFromDropDownByIndex("baseUOMKey",1);
	
		}
	}
	
	
	@Test(priority = 4, description = "VPLX: Unit of Measure - Internal UOM : [UI] Code accept alphanumeric value within  the 100 char while editing .")
	public void Test04_1089731_AND_1089737(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM : [UI] Code accept alphanumeric value within  the 100 char while editing .");
		test.siteConfigurationAction.clickButton("cancel");
		test.supportDataActions.clearSearchBox("search");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add Unit of Measure");
		unit_code = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
				"Code" + System.currentTimeMillis());
		unit_Description = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Des" + System.currentTimeMillis());
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.refreshPage();
		test.supportDataActions.enterSearchTermInSearchFieldGl(unit_code, "search");
		test.supportDataActions.clickToggleButton("true", "toggle");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(unit_code, "1"));
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(unit_code);
		unit_code1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
				"Codea" + System.currentTimeMillis());
		unit_Description1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Desa" + System.currentTimeMillis());
		conversion_factor = test.siteConfigurationAction.enterDataInInputField("conversionAmount",
				"1234567891.12345678912345");
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
	}
	
	@Test(priority = 5, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Sort order field accepts the value within the range of 1-9999.")
	public void Test05_1085776(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Sort order field  accepts the value within the range of 1-9999.");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add Unit of Measure");
		sortorder=test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("sortValue","87878");
		test.supportDataActions.verifyErrorMessageonAlert(getData("UOMDetails.Error_msg_sort_order"), "sortValue");
		test.siteConfigurationAction.clickButton("cancel");
}
	

	@Test(priority = 6, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Error message throws if Save without entering the values in mandatory fields.")
	public void Test06_1085793(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Error message throws if Save without entering the values in mandatory fields.");
		{
			test.supportDataActions.clickOnAddButtonToAddNewISA("Add Unit of Measure");
			test.siteConfigurationAction.verifyButtonIsDisabled("save");
			
		}
	}
		@Test(priority = 6, description = "VPLX: Unit of Measure - Internal UOM  : [UI]: When a UOM (not mapped to any item) is inactivated, it is not displayed in UOM dropdown on Add/Edit Item screen")
		public void Test06_1112293(Method method) throws Throwable {
			ExtentTestManager.startTest(method.getName(),
					"VPLX: Unit of Measure - Internal UOM  : [UI]: When a UOM (not mapped to any item) is inactivated, it is not displayed in UOM dropdown on Add/Edit Item screen");
			test.supportDataActions.clickOnAddButtonToAddNewISA("Add Unit of Measure");
			unit_code2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
					"Code" + System.currentTimeMillis());
			unit_Description2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
					"descriptionText", "Des" + System.currentTimeMillis());
			test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
			test.siteConfigurationAction.clickButton("save");
			
			test.siteConfigurationAction.refreshPage();
			
			test.supportDataActions.enterSearchTermInSearchFieldGl(unit_code2, "search");
			test.supportDataActions.clickToggleButton("true", "toggle");
			test.supportDataActions.clickOnEditLinkCorresspondingToItem(unit_code2);
			test.supportDataActions.clickToggleButton("true", "toggle");
			test.siteConfigurationAction.clickButton("save");
			
			test.landingPageActions.navigateToFeature("Main Menu"); 
			test.landingPageActions.navigateToItemManagementFeature("Item Management");
			test.siteConfigurationAction.enterRandomValueInRichInputField(getData("ExternalSystem.Name7"));
			
			test.supportDataActions.enterSearchTermInSearchFieldGl(getData("ExternalSystem.SearchItem"), "search");
			test.supportDataActions.clickOnEditLinkCorresspondingToItem(getData("ExternalSystem.SearchItem"));
			
			Assert.assertFalse(test.siteConfigurationAction.selectValueForStrengthUOMDropDownonItemScreen("strengthUnitOfMessureKey", "unit_code2"));
		 
			test.siteConfigurationAction.clickActionbutton("Cancel");
			test.landingPageActions.navigateToFeature("Main Menu"); 
			test.landingPageActions.navigateToFeature("Units of Measure");
			
			test.siteConfigurationAction.refreshPage();
			test.supportDataActions.enterSearchTermInSearchFieldGl(unit_code2, "search");
			
			test.supportDataActions.clickToggleButton("true", "toggle");
			Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(code, "1"));
			test.supportDataActions.clickOnEditLinkCorresspondingToItem(unit_code2);
		
			test.siteConfigurationAction.verifyToggleIsInActive("toggle");
			
			
		
}

}
