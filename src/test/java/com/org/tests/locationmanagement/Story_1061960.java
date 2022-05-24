package com.org.tests.locationmanagement;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1061960 extends BaseTest {

	String itemID, itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName").trim(),
			facility = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),
			isaName = TestDataPropertyReaderAndWriter.getProperty("ISAName").trim();

	@Test(priority = 1, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User lands on Edit Location screen when click on Assign button")
	public void Test01_1076594(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User lands on Edit Location screen when click on Assign button");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facility);
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", facility);
		test.siteConfigurationAction.selectValueForDropDown("isa", isaName);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.verifyUserIsOnLayoutPage();

	}

	@Test(priority = 2, description = "VPLX:Location Assignment - Managing Storage Area: [UI]:  User gets an option to choose the racks from the dropdown and corresponding shelf will also change")
	public void Test02_1076656(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]:  User gets an option to choose the racks from the dropdown and corresponding shelf will also change");
		test.siteConfigurationAction.verifyRackDropdownOnLocationPage();
		test.siteConfigurationAction.selectValueFromDropDown("selectedRack", "Rack 1");

		test.siteConfigurationAction.clickDotsOnItemLocation("isa_actions");
	}

	@Test(priority = 4, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: Validation message is displayed if ISA Setting for racks is less than Racks in Map on adding Racks")
	public void Test04_1076663(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: Validation message is displayed if ISA Setting for racks is less than Racks in Map on adding Racks");
		test.siteConfigurationAction.clickDotsOnItemLocation("isa_actions");
		test.siteConfigurationAction.clickAddRacksOrShelfOrBin("Add Rack");
		test.siteConfigurationAction
				.verifyErrorMessageForRacks("This Rack cannot be added. Maximum limit exceeded for adding Rack.");
	}

	@Test(priority = 3, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to add Racks up to the Max Rack setting in the ISA")

	public void Test03_1076664(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to add Racks up to the Max Rack setting in the ISA");
		test.siteConfigurationAction.clickAddRacksOrShelfOrBin("Add Rack");

		test.siteConfigurationAction.verifySuccessMessageOnAddRack();
	}

	@Test(priority = 5, description = "VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to add Shelf up to the Max Shelf setting in the ISA")

	public void Test05_1076674(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]: User is allowed to add Shelf up to the Max Shelf setting in the ISA");

		test.siteConfigurationAction.selectValueFromDropDown("selectedRack", "Rack 1");
		test.siteConfigurationAction.clickDotsOnItemLocation("rack_actions");
		test.siteConfigurationAction.clickAddRacksOrShelfOrBin("Add Shelf");
		test.siteConfigurationAction.verifySuccessMessageOnAddRack();
	}

	@Test(priority = 6, description = "VPLX:Location Assignment - Managing Storage Area [UI]: Validation message is displayed if ISA Setting for shelf is less than Shelf in Map on adding Shelf")

	public void Test06_1076719(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area [UI]: Validation message is displayed if ISA Setting for shelf is less than Shelf in Map on adding Shelf");
		test.siteConfigurationAction.clickDotsOnItemLocation("rack_actions");
		test.siteConfigurationAction.clickAddRacksOrShelfOrBin("Add Shelf");
		test.siteConfigurationAction
				.verifyErrorMessageForRacks("This Shelf cannot be added. Maximum limit exceeded for adding Shelf.");

	}

	@Test(priority = 7, description = "VPLX:Location Assignment - Managing Storage Area: [UI]:  User is allowed to add Bin up to the Max Bin setting in the ISA")

	public void Test07_1076721(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area: [UI]:  User is allowed to add Bin up to the Max Bin setting in the ISA");
		test.siteConfigurationAction.hoverOverShelf("Shelf 1");
		test.siteConfigurationAction.clickDotsOnItemLocation("shelf_actions");
		test.siteConfigurationAction.clickAddRacksOrShelfOrBin("Add Bin");
		test.siteConfigurationAction.verifySuccessMessageOnAddRack();

	}

	@Test(priority = 8, description = "VPLXLocation Assignment - Managing Storage Area: [UI]: Validation message is displayed if ISA Setting for bins is less than Bin in Map on adding Bin")

	public void Test08_1076673(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLXLocation Assignment - Managing Storage Area: [UI]: Validation message is displayed if ISA Setting for bins is less than Bin in Map on adding Bin");
		test.siteConfigurationAction.hoverOverShelf("Shelf 1");

		test.siteConfigurationAction.clickDotsOnItemLocation("shelf_actions");
		test.siteConfigurationAction.clickAddRacksOrShelfOrBin("Add Bin");
		test.siteConfigurationAction
				.verifyErrorMessageForRacks("This Bin cannot be added. Maximum limit exceeded for adding Bin.");

	}

}
