package com.org.tests.mainmenu.holdreasons;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_998004 extends BaseTest {
	String holdReasonName, holdReasonName_old;
	
	@Test(priority = 1, description = "VPLX: Hold Reason UI: Edit: Verify the Hold Reason Edit pop-up fields")
	public void Test01_1031401(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - Hold Reason EDIT pop-up is visible as per VD.");
		
		test.landingPageActions.navigateToFeature("Hold Reasons");
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");	
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.HoldReason"));
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
		
		test.supportDataActions.clickRecordNameToEdit(holdReasonName);
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(
				getData("ToggleValue.HoldReason")), "[ASSERTION FAILED]: Toggle Button is Not Present");
		Assert.assertTrue(test.supportDataActions.verifyEditHoldReasonPopupInputFieldsArePresent(),
				"[ASSERTION FAILED]: Input Fields are Not Present");
		Assert.assertTrue(test.supportDataActions.verifyEditHoldReasonPopupButtonsArePresent(),
				"[ASSERTION FAILED]: Buttons are Not Present");
		Assert.assertTrue(test.supportDataActions.verifyEditHoldReasonPopupCrossButtonIsPresent(),
				"[ASSERTION FAILED]: Cross Button is Not Present");
		Assert.assertTrue(test.supportDataActions.verifyEditHoldReasonPopupRequiredText(),
				"[ASSERTION FAILED]: Required Text Message Button is Not Present");
		Assert.assertFalse(test.supportDataActions.getValueOfInputField("descriptionText").isEmpty(), 
				"[ASSERTION FAILED]: Hold Reason Name field is empty\"");
		Assert.assertFalse(test.supportDataActions.getValueOfTextareaField("holdReasonSummaryText").isEmpty(), 
				"[ASSERTION FAILED]: Hold Reason Description field is empty\"");
		test.supportDataActions.clickButton("cancel");
	}
	
	
	@Test(priority = 2, description = "VPLX: Hold Reason UI: Edit: An error message is displayed "
			+ "when editting a hold reason using duplicate details")
	public void Test02_1031402(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"To Verify, Error message is displayed on edit hold reason with duplicate details");
		holdReasonName_old = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText", 
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
		
		test.supportDataActions.clickRecordNameToEdit(holdReasonName);
		test.supportDataActions.EnterValueInInputField("descriptionText", holdReasonName_old);
		test.supportDataActions.EnterValueInInputField("holdReasonSummaryText", holdReasonName_old);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForDuplicateName(getData("HoldReasonDetails.ErrorMsg_DuplicateHoldReasonName"));
	}
	
	
	@Test(priority = 3, description = "VPLX: Hold Reason UI: Edit: The Hold Reason is successfully updated "
			+ "with valid details")
	public void Test03_1031405(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"To Verify, Hold Reason is successfully updetsd with valid details and active state of toggle button.");

		holdReasonName= test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.HoldReason"));
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
	}
	
	
	@Test(priority = 4, description = "VPLX:Hold Reason: UI - To verify Hold Reason is successfully "
			+ "updated with valid details and inactive state of toggle button.")
	public void Test04_1031408(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - Hold Reason is successfully updated with valid details and inactive state of toggle button.");
		test.supportDataActions.clickRecordNameToEdit(holdReasonName);
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName")+ System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.HoldReason"));
		test.supportDataActions.clickButton("save");
		
//		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
//		test.supportDataActions.enterSearchTermInSearchField(holdReasonName, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
		test.supportDataActions.verifyNewlyAddedHoldReasonStatus(holdReasonName, "Inactive");
	}
	
	
	@Test(priority = 5, description = "VPLX: Hold Reason UI: Edit: The Hold Reason Name and Description "
			+ "fields do not accept more than 50 characters each")
	public void Test05_1031418(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - Hold Reason is not updated  with mora than 50 character length of Name and Description value.");
		test.supportDataActions.refreshPage();
		holdReasonName = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.clickRecordNameToEdit(holdReasonName);
		
		test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName")+System.currentTimeMillis(),"Hold Reason");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("descriptionText"), 50,
				"[ASSERTION FAILED]: Max Length for input field Hold Reason Name is not 50");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName")+System.currentTimeMillis(),"Hold Reason");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("holdReasonSummaryText"), 50,
				"[ASSERTION FAILED]: Max Length for input field Hold Reason Description is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("descriptionText", DateUtil.getAlphaNumericString(51),50);
		test.supportDataActions.verifyMaxLengthOfAnInputField("holdReasonSummaryText", DateUtil.getAlphaNumericString(51),50);
		test.supportDataActions.clickButton("cancel");
	}
	
	
	@Test(priority = 6, description = "VPLX: Hold Reason UI: Edit: The Save button is enabled only "
			+ "when the required fields Name and Description are populated")
	public void Test06_1031423(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - Edit a Hold Reason without providing Name and Description value.");
		holdReasonName = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.clickRecordNameToEdit(holdReasonName);
		
		test.supportDataActions.verifyInputFieldOnAddPopup("descriptionText");
		test.supportDataActions.clearInputBox("descriptionText");
		test.supportDataActions.verifyInputFieldOnAddPopup("holdReasonSummaryText");
		test.supportDataActions.clearInputBox("holdReasonSummaryText");
		test.supportDataActions.EnterValueInInputField("descriptionText", " ");
//		test.supportDataActions.SendActionKeys("descriptionText", "backspace");
		test.supportDataActions.EnterValueInInputField("holdReasonSummaryText", " ");
//		test.supportDataActions.SendActionKeys("holdReasonSummaryText", "backspace");
//		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageonAlert("Hold Reason name cannot be empty","descriptionText");
		test.supportDataActions.verifyErrorMessageOnTextArea("Hold Reason description cannot be empty", "holdReasonSummaryText");
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("save"));
	}
	
	
	@Test(priority = 7, description = "VPLX: Hold Reason UI: Edit: The edit hold reason dialog is closed "
			+ "by clicking the cancel button or the \"X\" icon")
	public void Test07_1031425(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - Edit pop up gets closed on click on cancel button or cross icon.");
		
		test.supportDataActions.clickButton("cancel");
		Assert.assertFalse(test.supportDataActions.verifyEditHoldReasonPopupGetsClosedOnClickingCancelButton());
		
		holdReasonName = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.clickRecordNameToEdit(holdReasonName);
		test.supportDataActions.clickCrossButton();
		Assert.assertFalse(test.supportDataActions.verifyEditHoldReasonPopupGetsClosedOnClickingCancelButton());
	}
	
	
	@Test(priority = 8, description = "Test Case 1076533:OBSOLETE VPLX: Hold Reason UI: Edit: "
			+ "The hold reason name and description fields are reset when updated data is provided "
			+ "while editing a hold reason")
	public void Test08_1076533(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: HoldReason: Feature Testing- Hold reason name and description fields are reset when updated data is provided while editing a hold reason");
		holdReasonName_old = test.supportDataActions.getColumnFirstData("1");
		test.supportDataActions.clickRecordNameToEdit(holdReasonName_old);
		
		test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName")+System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName")+System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyOldNameIsNotAvailableInInHoldReasonList(holdReasonName_old);
	}
	
}

