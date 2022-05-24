package com.org.tests.mainmenu.uom;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997988  extends BaseTest{

	String code,Description,sortorder,code1;
	
	@Test(priority = 1, description = "VPLX: Unit of Measure - Internal UOM : [UI] Visibility  of default state of Active field")
	public void Test01_1085775(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM : [UI] Visibility  of default state of Active field");
		test.landingPageActions.navigateToFeature("Units of Measure");
		test.supportDataActions.clickOnAddButtonToAddNewUOM("Add Unit of Measure");
		test.siteConfigurationAction.verifyDestinationActiveInactiveStatus();
		test.siteConfigurationAction.verifyToggleIsActive("activeFlag");
		
}
	
	@Test(priority = 2, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Check box for  Medication strength and medication volume are available under the Role.")
	public void Test02_1085787(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Check box for  Medication strength and medication volume are available under the Role.");
		{
			test.siteConfigurationAction.verifyCheckboxesUnderRoleField("Medication Strength");
			test.siteConfigurationAction.verifyCheckboxesUnderRoleField("Medication Volume");
		}
	}
	
	@Test(priority = 3, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Listing of Predefined values of base unit , if medication strength Check box is selected.")
	public void Test03_1085794_AND_1085795(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Listing of Predefined values of base unit , if medication strength Check box is selected.");
		{
			test.siteConfigurationAction.verifyCheckboxesUnderRoleField("Medication Strength");
			test.siteConfigurationAction.verifyCheckboxesUnderRoleField("Medication Volume");
			test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
//			test.siteConfigurationAction.selectValueFromDropDownByIndex("baseUOMKey",1);
			test.siteConfigurationAction.selectValueFromDropdownUOM("baseUOMKey", "%");
	
		}
	}
	
	@Test(priority = 4, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Sort order field accepts the value within the range of 1-9999.")
	public void Test04_1085776(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Sort order field  accepts the value within the range of 1-9999.");
		sortorder=test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("sortValue","87878");
		test.supportDataActions.verifyErrorMessageonAlert(getData("UOMDetails.Error_msg_sort_order"), "sortValue");
}
	
	@Test(priority = 5, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Error message throws if enter Conversion factor beyond the 24 digit .")
	public void Test05_1085801(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Error message throws if enter Conversion factor beyond the 24 digit .");
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("conversionAmount", "12345671.1234567891234556");
		test.supportDataActions.verifyErrorMessageonAlert("1 to 10 digits before, and 1 to 14 digits after the decimal point are allowed", "conversionAmount");

		// 1 to 10 digits before, and 1 to 14 digits after the decimal point are allowed

	
	}
	@Test(priority = 6, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Error message throws when Adds the description as blank.")
	public void Test06_1085785(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Error message throws when Adds the description as blank.");
		test.siteConfigurationAction.verifyInputFieldIsBlank("descriptionText");
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("descriptionText","Des189");
		test.siteConfigurationAction.clearInputBox("descriptionText");
		test.supportDataActions.verifyErrorMessageonAlert(getData("UOMDetails.Error_msg_description"), "descriptionText");
		}
	
	@Test(priority = 7, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Error message throws when adds the description as blank.")
	public void Test07_1085781(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Error message throws when adds the description as blank.");
		test.siteConfigurationAction.verifyInputFieldIsBlank("displayCode");
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode","code189");
		test.siteConfigurationAction.clearInputBox("displayCode");
		test.supportDataActions.verifyErrorMessageonAlert(getData("UOMDetails.Error_msg_code"), "displayCode");
		test.supportDataActions.clickButton("cancel");
	
}
	
	@Test(priority = 8, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Error message throws if Save without entering the values in mandatory fields.")
	public void Test08_1085793(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Error message throws if Save without entering the values in mandatory fields.");
		{
			test.supportDataActions.clickOnAddButtonToAddNewUOM("Add Unit of Measure");
			test.siteConfigurationAction.verifyButtonIsDisabled("save");
			
		}
	}
	
	@Test(priority = 9, description = "VPLX: Unit of Measure - Internal UOM : [UI] Code  accepts  alphanumeric value within  the 100 char")
	public void Test09_1085780__AND_1085799(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM : [UI] Code  accepts  alphanumeric value within  the 100 char");
		test.supportDataActions.clickOnAddButtonToAddNewUOM("Add Unit of Measure");
		code = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode","Code" + System.currentTimeMillis());
		Description = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("descriptionText","Des" + System.currentTimeMillis());
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		test.siteConfigurationAction.enterDataInInputField("conversionAmount", "1234567891.12345678912345");
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("sortValue","878");
		test.siteConfigurationAction.clickButton("save");
		}
	
	
	@Test(priority = 10, description = "VPLX: Unit of Measure - Internal UOM  : [UI] A UOM gets added with the alphanumeric description within the 250 char.")
	public void Test10_1085782(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] A UOM gets added with the alphanumeric description within the 250 char.");
		test.supportDataActions.enterSearchTermInSearchFieldGl(Description, "search");
		test.supportDataActions.clickToggleButton("true", "toggle");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(Description, "2"));
	}
	
	
}
	
		
		