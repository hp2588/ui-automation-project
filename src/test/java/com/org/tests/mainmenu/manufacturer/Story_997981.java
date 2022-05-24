package com.org.tests.mainmenu.manufacturer;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;

@Listeners(com.org.listeners.TestReporter.class)

public class Story_997981 extends BaseTest {

	String manufacturerName;
	String manufacturerName_old;

	@Test(priority = 1, description = "VPLX: Manufacturer  : [UI] Add manufacturer option is available under the Support data .")
	public void Test01_1070968() {

		// test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Manufacturers");
		test.supportDataActions.verifyLabelIsPresent("Manufacturers");
		test.supportDataActions.verifyAddButtonOnPage();
	}

	@Test(priority = 2, description = "VPLX: Manufacturer  : [UI] Verify a Popup will open after clicking on the Add button.")
	public void Test02_1070977() {

		test.supportDataActions.verifyAddButtonOnManageDistributor();
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Manufacturer");
		test.supportDataActions.verifyEditWasteReasonPopupInputFieldsArePresent("manufacturer");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.Manufacturer")),
				"[ASSERTION FAILED]: Toggle Button is Not Present");
		test.supportDataActions.verifyEditHoldReasonPopupButtonsArePresent();
		test.supportDataActions.clickButton("cancel");

	}

	@Test(priority = 3, description = "VPLX: Manufacturer  : [UI] Verify Default state of toggle button is Active  on the Manufacturer popup.")
	public void Test03_1070980() {

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Manufacturer");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.Manufacturer")),
				"[ASSERTION FAILED]: Toggle Button is Not Present");

	}

	@Test(priority = 4, description = "VPLX: Manufacturer  : [UI] System prompts error message when user trying to add manufacturer by entering blank in description field.")
	public void Test04_1070982() {

		// test.supportDataActions.EnterValueInInputField("manufacturer", "a");
		// test.supportDataActions.clickButton("save");
		// test.supportDataActions.verifyErrorMessageForAlreadyExistingManufacturerName(getData("ManufacturerDetails.ErrorMsg_InvalidName"));
		test.supportDataActions.EnterValueInInputField("manufacturer", "a");
		test.supportDataActions.clearInputBox("manufacturer");
		test.supportDataActions.verifyErrorMessageonAlert(getData("ManufacturerDetails.ValidationMsg"));
		test.supportDataActions.clickButton("cancel");
	}

	@Test(priority = 5, description = "VPLX: Manufacturer  : [UI] Verify user can enter the special char in the Name")
	public void Test05_1070987() {

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Manufacturer");
		test.supportDataActions.EnterRandomValueInInputField("manufacturer",
				test.supportDataActions.generatingRandomSpecialCharacterString(10));
		test.supportDataActions.clickButton("cancel");
	}

	@Test(priority = 6, description = "VPLX: Manufacturer  : [UI] Verify user can add only 255 char in the Name")
	public void Test06_1070988() {

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Manufacturer");
		manufacturerName = test.supportDataActions.EnterRandomValueInManufacturerInputField("manufacturer",
				test.supportDataActions.generatingRandomStringForWasteReasonName(260));
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfWasteReasonInputField("manufacturer"), 255,
				"[ASSERTION FAILED]: Max Length for input field Manufacturer Name is not 255");
		test.supportDataActions.clickButton("cancel");
	}

	@Test(priority = 7, description = "VPLX: Manufacturer  : [UI] Verify user can save after entering the valid Name")
	public void Test07_1070990() {

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Manufacturer");
		manufacturerName = test.supportDataActions.EnterRandomValueInManufacturerInputField("manufacturer",
				getData("ManufacturerDetails.ManufacturerName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(manufacturerName);
	}

	@Test(priority = 8, description = "VPLX: Manufacturer  : [UI] Verify user can Cancel after entering the valid Name")
	public void Test08_1070992() {

		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Manufacturer");
		manufacturerName = test.supportDataActions.EnterRandomValueInManufacturerInputField("manufacturer",
				getData("ManufacturerDetails.ManufacturerName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.verifyWasteReasonNameIsNotAvailableInWasteReasonList(manufacturerName);

	}
}
