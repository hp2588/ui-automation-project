package com.org.tests.mainmenu.holdreasons;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_998002 extends BaseTest {
	
	String holdReasonName;
	String holdReasonName_old;  
	
	@Test(priority = 1, description = "VPLX: Hold Reason UI: Add: User is able to add a Hold Reason "
			+ "with a Code and Description of up to 50 characters")
	public void Test01_1027512(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - User is able to add Hold Reason with value of Code and Description upto 50 characters");
		
		test.landingPageActions.navigateToFeature("Hold Reasons");
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		test.supportDataActions.verifyInputFieldOnAddPopup("descriptionText");
		test.supportDataActions.verifyInputFieldOnAddPopup("holdReasonSummaryText");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(
				getData("ToggleValue.HoldReason")), "[ASSERTION FAILED]: Toggle Button is Not Present");
		Assert.assertTrue(test.supportDataActions.verifyAddHoldReasonPopupButtonsArePresent(),
				"[ASSERTION FAILED]: Buttons are Not Present");
		Assert.assertTrue(test.supportDataActions.verifyAddHoldReasonPopupCrossButtonIsPresent(),
				"[ASSERTION FAILED]: Cross Button is Not Present");
//		test.supportDataActions.verifyLabelIsPresent(getData("HoldReasonDetails.Add_label").trim());
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("descriptionText"), 50,
				"[ASSERTION FAILED]: Max Length for input field Hold Reason Name is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("descriptionText", DateUtil.getAlphaNumericString(51),50);
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("holdReasonSummaryText"), 50,
				"[ASSERTION FAILED]: Max Length for input field Hold Reason Description is not 50");
		test.supportDataActions.verifyMaxLengthOfAnInputField("holdReasonSummaryText", DateUtil.getAlphaNumericString(51),50);
		test.supportDataActions.clickButton("cancel");
	}
	
	
	@Test(priority = 2, description = "VPLX: Hold Reason UI: Add: The user is able to create a new Hold Reason")
	public void Test02_1027527(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason:  UI - Create a new Hold Reason with valid details.");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
	}
	
	
	@Test(priority = 3, description = "VPLX: Hold Reason UI: Add: Hold Reason is not added "
			+ "when clicking the cancel button.")
	public void Test03_1027533(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - Hold Reason is not getting added on clicking cancel button.");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.verifyHoldReasonNameIsNotAvailableInHoldReasonList(holdReasonName);
	}
	
	
	@Test(priority = 4, description = "VPLX: Hold Reason UI: Add: The user is unable to add "
			+ "a Hold Reason with more than 50 characters in the Name and Description fields")
	public void Test04_1027554(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - Hold Reason is not created on passing with more than 50 character length in Name and Description");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("descriptionText"), 50,
				"[ASSERTION FAILED]: Max Length for input field Hold Reason Name is not 50");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("holdReasonSummaryText"), 50,
				"[ASSERTION FAILED]: Max Length for input field Hold Reason Description is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("descriptionText", DateUtil.getAlphaNumericString(51),50);
		test.supportDataActions.verifyMaxLengthOfAnInputField("holdReasonSummaryText", DateUtil.getAlphaNumericString(51),50);
		
		test.supportDataActions.clickButton("cancel");
	}
	
	
	@Test(priority = 5, description = "VPLX: Hold Reason UI: Add: The Hold Reason field 'Names' is mandatory"
			+ "\n&\n"
			+ "VPLX: Hold Reason UI: Add: The Description field is a required field")
	public void Test05_Test06_1027558_1155419(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX:Hold Reason: UI - Create a Hold Reason by passing null value in mandatory fields");
		test.landingPageActions.navigateToFeature("Hold Reasons");		
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		
		Assert.assertTrue(test.supportDataActions.verifyFieldIsMandatory("descriptionText"),
				"[ASSERTION FAILED]: Field HoldReason is not mandatory");
		Assert.assertTrue(test.supportDataActions.verifyFieldIsMandatory("holdReasonSummaryText"),
				"[ASSERTION FAILED]: Field HoldReason Description is not mandatory");
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabledWithoutWait("save"));
		
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabledWithoutWait("save"));
		test.supportDataActions.clearInputField("descriptionText", "descriptionText");
		
		test.supportDataActions.EnterTextInFieldByJavascript("holdReasonSummaryText", 
				"Hold Reason Description " + System.currentTimeMillis());
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabledWithoutWait("save"));
		test.supportDataActions.clearInputField("holdReasonSummaryText", "holdReasonSummaryText");
		
		Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabledWithoutWait("save"));
	}
	
	
	@Test(priority = 6, description = "VPLX: Hold Reason UI: Add: The Hold Reason is "
			+ "created with valid details and an inactive status")
	public void Test07_1027561(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - Create a new Hold Reason with valid details and active state of toggle button");
		
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.HoldReason"));
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
		test.supportDataActions.verifyNewlyAddedHoldReasonStatus(holdReasonName, "Active");
	}
	
	
	@Test(priority = 7, description = "VPLX: Hold Reason UI: Add: The Hold Reason is created with valid details and an active status")
	public void Test08_1027563(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - Create a new Hold Reason with valid details and inactive state of toggle button");
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		holdReasonName_old = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.HoldReason"));
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
//		test.supportDataActions.enterSearchTermInSearchField(holdReasonName_old, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName_old);
		test.supportDataActions.verifyNewlyAddedHoldReasonStatus(holdReasonName_old, "Inactive");
	}
	
	
	@Test(priority = 8, description = "VPLX: Hold Reason UI: Add: The user can not create "
			+ "a Hold Reason using a duplicate Name or Description")
	public void Test09_1027585(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - Create a duplicate Hold Reason with valid details.");
		test.supportDataActions.clickOnAddButtonToAddNewRecord(getData("HoldReasonDetails.Add_label").trim());
		test.supportDataActions.EnterDuplicateValueInInputField("descriptionText", holdReasonName_old);
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText", 
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.HoldReason"));
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForDuplicateName(getData("HoldReasonDetails.ErrorMsg_DuplicateHoldReasonName"));
	}

}

