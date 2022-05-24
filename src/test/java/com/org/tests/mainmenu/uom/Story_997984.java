package com.org.tests.mainmenu.uom;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997984 extends BaseTest {

	String unit_code, unit_Description;
	List<String> sorted_data, sorted_data1;
	List<String> previous_data;

	@Test(priority = 1, description = "VPLX: Unit of Measure - Internal UOM  : [UI] List of Columns on the unit of measure screen .")
	public void Test01_1084413(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] List of Columns on the unit of measure screen .");
		test.landingPageActions.navigateToFeature("Units of Measure");
		test.supportDataActions.clickOnAddButtonToAddNewUOM("Add Unit of Measure");
		
		
		unit_code = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
				"Code" + System.currentTimeMillis());
		unit_Description = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Des" + System.currentTimeMillis());
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		//test.siteConfigurationAction.clickCheckboxfacilityitemlevel2("1","Medication Strength");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.codeListDosageForms("1");
		test.supportDataActions.descriptionFormDosageForms("2");
		test.supportDataActions.verifyDosageFormsStatusAsActive();
		//test.supportDataActions.codeListDosageForms("4");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonOnDosageForm("toggle"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Code"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Description"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Sort Order"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Status"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Predefined"));
		Assert.assertTrue(test.supportDataActions.verifyColumnHeaderOnDosageForm("Role"));
	

	}

	@Test(priority = 2, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Show inactive toggle default state is Off .")
	public void Test02_1084442(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Show inactive toggle default state is Off .");
		test.siteConfigurationAction.verifyToggleIsInActive("toggle");
	}

	@Test(priority = 3, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Get the List of predefined UOM's.")
	public void Test03_1084410(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Get the List of predefined UOM's.");
		test.supportDataActions.verifyLabelIsPresent("Units of Measure");
		test.supportDataActions.clickToggleButton("true", "toggle");
		test.supportDataActions.verifyDosageFormsStatusAsActive();
	}

	@Test(priority = 4, description = "VPLX: Unit of Measure - Internal UOM  : [UI] User is not able to edit the predefined UOM")
	public void Test04_1084411(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] User is not able to edit the predefined UOM");
		test.siteConfigurationAction.verifyButtonIsDisabledUOM("puff");

	}

	@Test(priority = 5, description = "VPLX: Unit of Measure - Internal UOM  : [UI] Show inactive toggle shows both  active and inactive UOM's when toggle is on")
	public void Test05_1084449(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] Show inactive toggle shows both  active and inactive UOM's when toggle is on");
		test.supportDataActions.clickToggleButton("true", "toggle");
		test.supportDataActions.verifyDosageFormsStatusAsActive();
	}

	@Test(priority = 6, description = "VPLX: Unit of Measure - Internal UOM  : [UI] User navigate to Add UOM screen after clicking on the Add button .")
	public void Test06_1084456(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] User navigate to Add UOM screen after clicking on the Add button .");
		test.supportDataActions.clickOnAddButtonToAddNewUOM("Add Unit of Measure");
		//test.supportDataActions.verifyLabelIsPresentUOM(unit_code);

	}

	@Test(priority = 7, description = "VPLX: Unit of Measure - Internal UOM  : [UI] User gets navigate to Edit UOM screen after clicking on the Edit button .")
	public void Test07_1084461(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unit of Measure - Internal UOM  : [UI] User gets navigate to Edit UOM screen after clicking on the Edit button .");
		unit_code = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
				"Code" + System.currentTimeMillis());
		unit_Description = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Des" + System.currentTimeMillis());
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(unit_code, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(unit_code);
		test.supportDataActions.verifyLabelIsPresentUOM(unit_code);
	}

}
