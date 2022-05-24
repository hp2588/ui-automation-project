package com.org.tests.mainmenu.wastereasons;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997996 extends BaseTest {
	

	String wasteReasonName;
	String wasteReasonName_old;

	@Test(priority = 1, description = "VPLX:Waste Reason:[UI]-User creates a new Waste Reason.")
	public void Test01_1030686(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User creates a new Waste Reason.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Waste Reasons");
		test.supportDataActions.verifyLabelIsPresent("Waste Reason");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Waste Reason");
		wasteReasonName_old = test.supportDataActions.EnterRandomValueInWasteReasonInputField("wasteReason",
				getData("WasteReasonDetails.WasteReasonName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName_old);
	}

	@Test(priority = 2, description = "VPLX:Waste Reason:[UI]-User is not able to create a Waste reason with more than 250 characters.")
	public void Test02_1030718(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User is not able to create a Waste reason with more than 250 characters.");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Waste Reason");
		wasteReasonName = test.supportDataActions.EnterRandomValueInWasteReasonInputField("wasteReason",
				getData("WasteReasonDetails.WasteReasonMaxLengthName") + System.currentTimeMillis());
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfWasteReasonInputField("wasteReason"), 250,
				"[ASSERTION FAILED]: Max Length for input field Waste Reason Name is not 250");	
		test.supportDataActions.clickButton("cancel");
	}
	
	@Test(priority = 3, description = "VPLX:Waste Reason:[UI]-User creates a new Waste Reason which already exists in db.")
	public void Test03_1030723(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User creates a new Waste Reason which already exists in db.");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Waste Reason");
		wasteReasonName = test.supportDataActions.EnterRandomValueInWasteReasonInputField("wasteReason", wasteReasonName_old);
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.WasteReason"));
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingWasteReasonName(getData("WasteReasonDetails.ErrorMsg_DuplicateWasteReasonName"));

	}
	
	@Test(priority = 4, description = "VPLX:Waste Reason:[UI]-User creates a new Waste Reason which is inactive")
	public void Test04_1030735(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User creates a new Waste Reason which is inactive");
		//test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Waste Reason");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.WasteReason"));
		wasteReasonName_old = test.supportDataActions.EnterRandomValueInInputField("wasteReason",
				getData("WasteReasonDetails.WasteReasonName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(wasteReasonName_old, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName_old);
		test.supportDataActions.verifyNewlyAddedHoldReasonStatus(wasteReasonName_old, "Inactive");
	}
	
	@Test(priority = 5, description = "VPLX:Waste Reason:[UI]-User is able to cancel the changes made when user clicks on Cancel button")
	public void Test05_1030740(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User is able to cancel the changes made when user clicks on Cancel button");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Waste Reason");
		wasteReasonName = test.supportDataActions.EnterRandomValueInInputField("wasteReason",
				getData("WasteReasonDetails.WasteReasonName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.resetSearch();
		test.supportDataActions.verifyWasteReasonNameIsNotAvailableInWasteReasonList(wasteReasonName);
	}
	
	@Test(priority = 6, description = "VPLX:Waste Reason:[UI]-User is able to close the pop up box when user clicks on close icon")
	public void Test06_1030751(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User is able to close the pop up box when user clicks on close icon");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Waste Reason");
		Assert.assertTrue(test.supportDataActions.verifyEditHoldReasonPopupCrossButtonIsPresent(),
				"[ASSERTION FAILED]: Add WasteReason pop-up Cross Button is not present");
		test.supportDataActions.clickOnWasteReasonCrossButton();
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Waste Reason"),
				"[ASSERTION FAILED]: Label Waste Reason is not present");
	}
	
	@Test(priority = 7, description = "VPLX:Waste Reason:[UI]-Application is displaying the message when user doesn't enter the waste reason and clicks on save.")
	public void Test07_1030758_1129479(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-Application is displaying the message when user doesn't enter the waste reason and clicks on save.");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Waste Reason");
		test.supportDataActions.EnterValueInInputField("wasteReason", "E");
		test.supportDataActions.SendActionKeysWasteReason("wasteReason");
		//test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageonAlert(getData("WasteReasonDetails.ValidationMsg"));
	}
}
