package com.org.tests.mainmenu.manufacturer;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997982 extends BaseTest{

	String manufacturerName;
	String manufacturerName_old;
	
	@Test(priority = 1, description = "VPLX: Manufacturer  : [UI] Verification of default state of Show inactive field")
	public void Test01_1078801(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manufacturer  : [UI] Verification of default state of Show inactive field");
		
		//test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Manufacturers");
		test.supportDataActions.verifyLabelIsPresent("Manufacturers");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.Carousel")), "[ASSERTION FAILED]: Toggle Button is Not Present");
		Assert.assertTrue(test.supportDataActions.verifyManufacturerStatusAsActive());
	}
	
	@Test(priority = 2, description = "VPLX: Manufacturer  : [UI] All Active and inactive manufacturer displayed when show inactive field is on.")
	public void Test02_1078802(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manufacturer  : [UI] All Active and inactive manufacturer displayed when show inactive field is on.");
		// Add code to create inactive manufacturer
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(
				getData("ToggleValue.Carousel")), "[ASSERTION FAILED]: Toggle Button is Not Present");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyManufacturerStatus());
	}
	
	@Test(priority = 3, description = "VPLX: Manufacturer  : [UI] User can edit manufacturer after clicking on the Edit button.")
	public void Test03_1078803(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manufacturer  : [UI] User can edit manufacturer after clicking on the Edit button.");
		
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(
				getData("ToggleValue.Carousel")), "[ASSERTION FAILED]: Toggle Button is Not Present");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyManufacturerStatus());
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Manufacturer");
		manufacturerName = test.supportDataActions.EnterRandomValueInManufacturerInputField("manufacturer",
				getData("ManufacturerDetails.ManufacturerName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(manufacturerName);
		test.supportDataActions.enterSearchTermInSearchField(manufacturerName, "search");

		test.supportDataActions.clickEditLinkInTableWasteReason(manufacturerName);
		
//		test.supportDataActions.clickButton("edit");
		
		test.supportDataActions.verifyEditPopUpOnManufacturer(manufacturerName);
	}
	
	@Test(priority = 4, description = "VPLX: Manufacturer  : [UI] Verification of fields on the Edit popup .")
	public void Test04_1078807(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manufacturer  : [UI] Verification of fields on the Edit popup .");
		Assert.assertTrue(test.supportDataActions.verifyRequiredFieldOnEditManufacturer(getData("ManufacturerDetails.RequiredFieldMsg")),
				"[ASSERTION FAILED]: Required Field Label is Not Present");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(
				getData("ToggleValue.Manufacturer")), "[ASSERTION FAILED]: Toggle Button is Not Present");
		Assert.assertTrue(test.supportDataActions.verifyEditWasteReasonPopupInputFieldsArePresent("manufacturer"),
				"[ASSERTION FAILED]: Input Fields are Not Present");
	}
	
	@Test(priority = 5, description = "VPLX: Manufacturer  : [UI] User can make active manufacturer to inactive while editing the manufacturer .")
	public void Test05_1078810(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manufacturer  : [UI] User can make active manufacturer to inactive while editing the manufacturer .");
		
		
		//test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Manufacturer");
		manufacturerName_old = test.supportDataActions.EnterRandomValueInManufacturerInputField("manufacturer",getData("ManufacturerDetails.ManufacturerName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clearSearchBoxField("search");

		test.supportDataActions.enterSearchTermInSearchField(manufacturerName_old, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(manufacturerName_old);
		
		test.supportDataActions.clickEditLinkInTableWasteReason(manufacturerName_old);

		//test.supportDataActions.clickButton("edit");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Manufacturer"));
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clearSearchBoxField("search");
		//test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(manufacturerName_old);
		test.supportDataActions.enterSearchTermInSearchField(manufacturerName_old, "search");
		test.supportDataActions.verifyNewlyAddedManufacturerStatus(manufacturerName_old, "Inactive");
		//test.supportDataActions.clearSearchBoxField("search");
	}
	
	@Test(priority = 6, description = "VPLX: Manufacturer  : [UI] User can Edit the name of  manufacturer while editing the manufacturer .")
	public void Test06_1078813(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manufacturer  : [UI] User can Edit the name of  manufacturer while editing the manufacturer .");
		
//		test.supportDataActions.enterSearchTermInSearchField(manufacturerName_old, "search");
		
		test.supportDataActions.clickEditLinkInTableWasteReason(manufacturerName_old);
		
		//test.supportDataActions.clickButton("edit");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(
				getData("ToggleValue.Manufacturer")), "[ASSERTION FAILED]: Toggle Button is Not Present");
		manufacturerName=test.supportDataActions.EnterRandomValueInWasteReasonInputField("manufacturer",
				getData("ManufacturerDetails.ManufacturerName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.enterSearchTermInSearchField(manufacturerName, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(manufacturerName);
	}
	
	@Test(priority = 7, description = "VPLX: Manufacturer  : [UI] User can Edit the name of  manufacturer with max 255 char.")
	public void Test07_1070990(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manufacturer  : [UI] User can Edit the name of  manufacturer with max 255 char.");
		//test.supportDataActions.enterSearchTermInSearchField(manufacturerName, "search");
		
		test.supportDataActions.clickEditLinkInTableWasteReason(manufacturerName);
		
		//test.supportDataActions.clickButton("edit");
		manufacturerName =  test.supportDataActions.EnterRandomValueInManufacturerInputField("manufacturer",
				test.supportDataActions.generatingRandomStringForManufacturerName(240));
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(manufacturerName);	
	}
}
