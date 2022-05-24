package com.org.tests.mainmenu.wastereasons;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997997 extends BaseTest {
	
	String wasteReasonName;
	String wasteReasonName_old;
	String generatedString;
	@Test(priority = 1, description = "VPLX:Waste Reason:[UI]-User edits the waste reason")
	public void Test01_1035180(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User edits the waste reason");
		//test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Waste Reasons");
		test.supportDataActions.verifyLabelIsPresent("Waste Reasons");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Waste Reason");
		wasteReasonName = test.supportDataActions.EnterRandomValueInInputField("wasteReason",
				getData("WasteReasonDetails.WasteReasonName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName);
		test.supportDataActions.clickEditLinkInTableWasteReason(wasteReasonName);
		//test.supportDataActions.clickButton("edit");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(
				getData("ToggleValue.WasteReason")), "[ASSERTION FAILED]: Toggle Button is Not Present");
		wasteReasonName_old=test.supportDataActions.EnterRandomValueInWasteReasonInputField("wasteReason",
				getData("WasteReasonDetails.WasteReasonName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName_old);
	}

	@Test(priority = 2, description = "VPLX:Waste Reason:[UI]-User edits the waste reason and change status to Inactive and save the records.")
	public void Test02_1035262(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User edits the waste reason and change status to Inactive and save the records.");
		test.supportDataActions.enterSearchTermInSearchField(wasteReasonName_old, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName_old);
		test.supportDataActions.clickButton("edit");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.WasteReason"));
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(wasteReasonName_old, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName_old);
		test.supportDataActions.verifyNewlyAddedHoldReasonStatus(wasteReasonName_old, "Inactive");
	}

	@Test(priority = 3, description = "VPLX:Waste Reason:[UI]-User edits the waste reason and change status to Active and save the records.")
	public void Test03_1035264(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User edits the waste reason and change status to Active and save the records.");
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName_old);
		//test.supportDataActions.verifyNewlyAddedHoldReasonStatus(wasteReasonName_old, "Inactive");
		test.supportDataActions.clickButton("edit");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.WasteReason"));
		//test.supportDataActions.EnterRandomValueInWasteReasonInputField("wasteReason", wasteReasonName_old);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(wasteReasonName_old, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName_old);
		test.supportDataActions.verifyNewlyAddedHoldReasonStatus(wasteReasonName_old, "Active");
	}

	@Test(priority = 4, description = "VPLX:Waste Reason:[UI]-User edits the waste reason and insert the data greater than 250  characters in text box Name.")
	public void Test04_1035278(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User edits the waste reason and insert the data greater than 250  characters in text box Name.");
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName_old);
		//test.supportDataActions.verifyNewlyAddedHoldReasonStatus(wasteReasonName_old, "Inactive");
		test.supportDataActions.clickButton("edit");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(
				getData("ToggleValue.WasteReason")), "[ASSERTION FAILED]: Toggle Button is Not Present");
		Assert.assertTrue(test.supportDataActions.verifyEditWasteReasonPopupInputFieldsArePresent("wasteReason"),
				"[ASSERTION FAILED]: Input Fields are Not Present");
		generatedString=test.supportDataActions.generatingRandomStringForWasteReasonName(250);
		wasteReasonName=test.supportDataActions.EnterRandomValueInWasteReasonInputField("wasteReason",getData("WasteReasonDetails.WasteReasonName")+generatedString);
		System.out.println("Length of Waste reason name: "+wasteReasonName.length());//264
		//int inputFieldLength=test.supportDataActions.verifyLengthOfWasteReasonInputField("wasteReason");
		Assert.assertEquals(test.supportDataActions.verifyLengthOfWasteReasonInputField("wasteReason"), 250,
				"[ASSERTION FAILED]: Length for input field Waste Reason Name is greater than 250 characters");
		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.verifyWasteReasonNameIsNotAvailableInWasteReasonList(wasteReasonName);
		
	}
	
	@Test(priority = 5, description = "VPLX:Waste Reason:[UI]-User edits the waste reason and insert the data upto 250  characters in text box Name.")
	public void Test05_1035281(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User edits the waste reason and insert the data upto 250  characters in text box Name.");
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName_old);
		//test.supportDataActions.verifyNewlyAddedHoldReasonStatus(wasteReasonName_old, "Inactive");
		test.supportDataActions.clickButton("edit");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(
				getData("ToggleValue.WasteReason")), "[ASSERTION FAILED]: Toggle Button is Not Present");
		Assert.assertTrue(test.supportDataActions.verifyEditWasteReasonPopupInputFieldsArePresent("wasteReason"),
				"[ASSERTION FAILED]: Input Fields are Not Present");
		generatedString=test.supportDataActions.generatingRandomStringForWasteReasonName(250);
		wasteReasonName=test.supportDataActions.EnterRandomValueInWasteReasonInputField("wasteReason",generatedString);
		//int inputFieldLength=test.supportDataActions.verifyLengthOfWasteReasonInputField("wasteReason");
		Assert.assertEquals(test.supportDataActions.verifyLengthOfWasteReasonInputField("wasteReason"), 250,
				"[ASSERTION FAILED]: Length for input field Waste Reason Name is not 250 characters");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clearSearchBoxField("search");
//		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
//		test.supportDataActions.enterSearchTermInSearchField(wasteReasonName, "search");
//		test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName);
	}
	
	@Test(priority = 6, description = "VPLX:Waste Reason:[UI]-User removes the Name and clicks on save.")
	public void Test06_1035291(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX:Waste Reason:[UI]-User removes the Name and clicks on save.");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Waste Reason");
		wasteReasonName_old = test.supportDataActions.EnterRandomValueInInputField("wasteReason",
				getData("WasteReasonDetails.WasteReasonName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchField(wasteReasonName_old, "search");
		test.supportDataActions.clickEditLinkInTableWasteReason(wasteReasonName_old);
		test.supportDataActions.verifyInputFieldOnAddPopup("wasteReason");
		test.supportDataActions.clearInputBox("wasteReason");
		test.supportDataActions.EnterValueInInputField("wasteReason", "E");
		test.supportDataActions.SendActionKeysWasteReason("wasteReason");
		//test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageonAlert("Name cannot be empty","wasteReason");
		test.supportDataActions.clickButton("cancel");
	}
	
	@Test(priority = 7, description = "VPLX:Waste Reason:[UI]-User is not  able to edit the Name which is there in db.")
	public void Test07_1036331(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User is not  able to edit the Name which is there in db.");

		//wasteReasonName_old = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Waste Reason");
		wasteReasonName = test.supportDataActions.EnterRandomValueInInputField("wasteReason",
				getData("WasteReasonDetails.WasteReasonName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchField(wasteReasonName, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName);
		test.supportDataActions.clickEditLinkInTableWasteReason(wasteReasonName);
		test.supportDataActions.EnterValueInInputField("wasteReason", wasteReasonName_old);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingWasteReasonName(getData("WasteReasonDetails.ErrorMsg_DuplicateWasteReasonName"));
		//test.supportDataActions.clickButton("cancel");
	}
	
	@Test(priority = 8, description = "VPLX:Waste Reason:[UI]-User is not able to edit the name for predefined waste reason")
	public void Test08_1035294(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User is not able to edit the name for predefined waste reason");
		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchField(getData("WasteReasonDetails.WasteReasonPredefined"), "search");
		test.supportDataActions.clickButton("edit");
		wasteReasonName=test.supportDataActions.getValueOfWasteReasonEditPopUpInputField("wasteReason");
		Assert.assertEquals(wasteReasonName, getData("WasteReasonDetails.WasteReasonPredefined"),
				"[ASSERTION FAILED]: Predefined Waste Reason "+ wasteReasonName + " does not match") ;
		test.supportDataActions.verifyEditPopUpInputFieldIsDisabled("wasteReason");
		
	}
	
	@Test(priority = 9, description = "VPLX:Waste Reason:[UI]-User is able to edit the status for the predefined waste reason")
	public void Test09_1035296(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Waste Reason:[UI]-User is able to edit the status for the predefined waste reason");

		//test.supportDataActions.enterSearchTermInSearchField(getData("WasteReasonDetails.WasteReasonPredefined"), "search");
		//test.supportDataActions.clickButton("edit");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.WasteReason"));
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clearSearchBoxField("search");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(getData("WasteReasonDetails.WasteReasonPredefined"), "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(getData("WasteReasonDetails.WasteReasonPredefined"));
		test.supportDataActions.verifyNewlyAddedHoldReasonStatus(getData("WasteReasonDetails.WasteReasonPredefined"), "Inactive");
		test.supportDataActions.enterSearchTermInSearchField(getData("WasteReasonDetails.WasteReasonPredefined"), "search");
		test.supportDataActions.clickButton("edit");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.WasteReason"));
		test.supportDataActions.clickButton("save");
	}
}
