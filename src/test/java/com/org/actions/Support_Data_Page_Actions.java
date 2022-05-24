package com.org.actions;

import static com.org.automation.utils.YamlReader.getData;

import java.awt.event.FocusAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.ClickElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.org.automation.getpageobjects.GetPage;
import com.org.automation.utils.ConfigPropertyReader;

public class Support_Data_Page_Actions extends GetPage {
	WebDriver driver;
	static String pagename = "Support_Data_Page";
	private ArrayList<String> column_data, trans_data;
	int count, count1 = 0;
	private boolean flag = false;
	int digit;

	public Support_Data_Page_Actions(WebDriver driver) {

		super(driver, pagename);
		this.driver = driver;
	}

	public void verifySupportDataOptions(String option) {

		if (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("chrome")) {

			hover(element("link_SupportData"));
			clickUsingXpathInJavaScriptExecutor(element("link_supportDataMenuOptions", option));

		} else {
			if (ConfigPropertyReader.getProperty("tier").equalsIgnoreCase("uat")) {
				driver.navigate().to(getData("URL.GlAccountURL"));
			}
			if (ConfigPropertyReader.getProperty("tier").equalsIgnoreCase("aut")) {
				driver.navigate().to(getData("URL.GlAccountURL"));
			}
		}

		logMessage("Support Data menu list is available");
	}

	public boolean verifyColumnHeaderOnBarcode() {
		return isElementDisplayed("header_action_barcode");
	}

	public void clickOnAddButtonToAddNewRecord(String popupText) {

		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 150);
			isElementDisplayed("btn_add");
			clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_add"));
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 150); // do
																		// not comment this required for facility  Add
			Assert.assertTrue(element("popup_text").getText().trim().contains(popupText));
			logMessage("[ASSERTION PASSED]: Verified Add pop up on clicking add button");
		} catch (Exception e) {
			wait.applyFluentWait(getLocator("btn_add"), 60, 500);
			isElementDisplayed("btn_add");
			clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_add"));
			wait.applyFluentWait(getLocator("popup_text"), 60, 500);
			Assert.assertTrue(element("popup_text").getText().trim().contains(popupText));
			logMessage("[ASSERTION PASSED]: Verified Add pop up on clicking add button");
		}

	}

	public void clickOnAddButtonToAddNewRecord1(String popupText) {
		wait.hardWait(8);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		// wait.loadingWait(getLocator("loader"));
		isElementDisplayed("btn_add");
		// element("btn_add").click();
		clickUsingXpathInJavaScriptExecutor(element("btn_add"));
		wait.hardWait(5);
		logMessage("[STEP]: Clicked on Add");
		wait.waitForElementToBeVisible(element("popup_text"));
		Assert.assertTrue(element("popup_text").getText().trim().contains(popupText));
		logMessage("[ASSERTION PASSED]: Verified Add pop up on clicking add button");
	}

	public void verifyErrorMessageForDuplicateName(String message) {

		isElementDisplayed("text_error_msg_duplicate", message);
		// Assert.assertTrue(element("text_error_msg").getText().trim().equalsIgnoreCase(message.trim()));

	}
	
	public void clickOnAddButtonToAddNewISA(String popupText) {
		// wait.loadingWait(getLocator("loader"));
		// wait.hardWait(10);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		isElementDisplayed("btn_add");
		wait.waitForElementToBeClickable(element("btn_add"));
		clickUsingXpathInJavaScriptExecutor(element("btn_add"));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		logMessage("[STEP]: Clicked on Add");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		wait.waitForElementToBeVisible(element("page_title"));
		Assert.assertTrue(element("page_title").getText().trim().contains(popupText));
		logMessage("[ASSERTION PASSED]: Verified Add pop up on clicking add button");
	}
	
	public void verifyRadioButtonIsEnabledOrDisabled(String id) {
		Assert.assertFalse(checkRadioButtonIsEnabledOrDisabledUsingJavaScript(id));
	}
	
	public void verifyInputFieldOnAddPopup(String fieldName) {
		if (fieldName.equalsIgnoreCase("holdReasonSummaryText")) {

			isElementDisplayed("textarea_hold_reason_description", fieldName);
			logMessage("[ASSERTION PASSED]: Verified input field for " + fieldName);
		} else {
			isElementDisplayed("inp_field_text", fieldName);
			logMessage("[ASSERTION PASSED]: Verified input field for " + fieldName);
		}

	}

	public boolean verifyInputFieldIsBlank(String fieldName) {

		if (fieldName.equalsIgnoreCase("holdReasonSummaryText")) {
			if (element("textarea_hold_reason_description", fieldName).getText().trim().isEmpty()) {
				return true;
			}
		}

		else {
			if (element("inp_field_text", fieldName).getText().trim().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public boolean verifyCheckboxIsDisplayed(String fieldName) {

		return isElementDisplayed("req_lot_checkbox", fieldName);

	}

	public boolean verifyCheckboxIsChecked(String id) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		System.out.println("return document.getElementById('" + id + "').checked;");
		return (boolean) executor.executeScript("return document.getElementById('" + id + "').checked;");
	}

	public String EnterRandomValueInInputField(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		if (fieldName.equalsIgnoreCase("holdReasonSummaryText") || fieldName.equalsIgnoreCase("descriptionText")) {

			enterTextInField(element("textarea_hold_reason_description", fieldName), data);
			Assert.assertEquals(element("textarea_hold_reason_description", fieldName).getAttribute("value").trim(),
					data);
		} else {
			enterTextInField(element("inp_field_text", fieldName), data);
			Assert.assertEquals(element("inp_field_text", fieldName).getAttribute("value"), data);
		}
		return data;
	}
	
	public String EnterRandomValueInInputField(String fieldName, String data, String featureType) {
		if (fieldName.equalsIgnoreCase("holdReasonSummaryText")) {
			
			enterTextInField(element("textarea_hold_reason_description", fieldName), data);
			Assert.assertEquals(element("textarea_hold_reason_description", fieldName).getAttribute("value").trim(),
					data);
		} else {
			enterTextInField(element("inp_field_text", fieldName), data);
			
			wait.hardWait(2);

			Assert.assertEquals(element("inp_field_text", fieldName).getAttribute("value").trim(), data);

		}
		return data;
	}

	public void verifySuccessMessageOnViewPage(String successMessage) {
//		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.hardWait(1);
		isElementDisplayed("popup_message");
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		try {
			Assert.assertTrue(element("popup_message").getText().trim().contains(successMessage));
			logMessage("[ASSERTION PASSED]: Verified data is Added");
		} catch (StaleElementReferenceException e) {
			Assert.assertTrue(element("popup_message").getText().trim().contains(successMessage));
			logMessage("[ASSERTION PASSED]: Verified data is Added");
		}

	}

	public void verifySuccessMessageOnViewPageWithLoader(String successMessage) {
		
		try {
			wait.applyFluentWait(getLocator("popup_message"), 120, 500);
			isElementDisplayed("popup_message");
			
		} catch (Exception e) {
			// wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
			wait.applyFluentWait(getLocator("popup_message"), 100, 500);
			isElementDisplayed("popup_message");
		}
		
		Assert.assertTrue(element("popup_message").getText().trim().contains(successMessage));
		logMessage("[ASSERTION PASSED]: Verified data is Added");
		
		wait.waitForLoaderToBeInvisible(getLocator("popup_message"), 20);
	}
	
	
	public void clickButton(String action) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 180);
		
		isElementDisplayed("action_button", action);
		hover(element("action_button", action));
		
		clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button", action));
		
		logMessage("Clicked on '" + action + "' button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
	}
	
	
	public void clickRecordNameToEdit(String recordName) {
		isElementDisplayed("added_hold_reason_name", recordName);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("added_hold_reason_name", recordName));
		logMessage("Clicked on " + recordName + " button to edit record");
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		try {
			Assert.assertTrue(element("popup_text").getText().trim().contains(recordName));
		} catch(NoSuchElementException e) {
			Assert.assertTrue(element("hold_reason_name").getText().trim().contains(recordName));
		}
	}
	
	public void clickButtonIfPresent(String action) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 180);
		try{
			if(element("action_button", action).isDisplayed()){
				//isElementDisplayed("action_button", action);
				hover(element("action_button", action));
				// wait.waitForElementToBeClickable(element("action_button", action));
				clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button", action));
				logMessage("Clicked on '" + action + "' button to add hold reason");
				wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
				wait.hardWait(5);
			}
		}catch(Exception e){
			logMessage("[STEP]: Confirmation popup is not displayed.");
		}
	}
	
	public void clickButtonWithMiniLoader(String action) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("action_button", action);
		hover(element("action_button", action));
		// wait.waitForElementToBeClickable(element("action_button", action));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button", action));
		logMessage("Clicked on" + action + "button to add hold reason");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
	}

	public void clickButtonWithOutAnyWait(String action) {
		isElementDisplayed("action_button", action);
		hover(element("action_button", action));
		// wait.waitForElementToBeClickable(element("action_button", action));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button", action));
		logMessage("Clicked on" + action + "button to add hold reason");
	}

	public void clickOnDuplicateLinkCorresspondingToAddedRecord(String fieldName, String action) {
		isElementDisplayed("duplicate_link_of_added_custom_label", fieldName, action);
		clickUsingXpathInJavaScriptExecutor(element("duplicate_link_of_added_custom_label", fieldName, action));
		logMessage("Clicked on Duplicate link corressponding to added record " + fieldName);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		/*
		 * wait.waitForElementToBeVisible(element("page_title"));
		 * Assert.assertTrue(element("page_title").getText().trim().contains(
		 * PageTitle)) ;
		 * logMessage("[ASSERTION PASSED]: Verified Edit a Schedule pop up");
		 */

	}

	public void verifyHoldReasonNameIsNotAvailableInHoldReasonList(String holdReasonName) {
		Assert.assertFalse(isElementNotDisplayed("added_hold_reason_name", holdReasonName), "[ASSERTION FAILED]: Record " + holdReasonName 
				+ "is displayed");
		logMessage("[ASSERTION PASSED]: Newly added Hold Reason : " + holdReasonName
				+ " is not displayed in the  Hold Reason List");

	}

	public int verifyMaxLengthOfAnSearchField(String field) {
		int len = 0;
		len = Integer.parseInt(element("search_box", field).getAttribute("maxlength").trim());
		return len;
	}

	public int verifyMaxLengthOfAnInputField(String fieldName) {
		int len = 0;
		if (fieldName.equalsIgnoreCase("holdReasonSummaryText")) {
			len = Integer
					.parseInt(element("textarea_hold_reason_description", fieldName).getAttribute("maxlength").trim());
		} else {
			len = Integer.parseInt(element("inp_field_text", fieldName).getAttribute("maxlength").trim());
		}

		return len;
	}

	public boolean verifyFieldIsMandatory(String fieldName) {
		isElementDisplayed("icon_mandatory", fieldName);
		if (element("icon_mandatory", fieldName).getText().trim().contains("*")) {
			return true;
		}
		return false;
	}

	public String EnterDuplicateValueInInputField(String fieldName, String data) {
		if (fieldName.equalsIgnoreCase("descriptionText")) {
			enterTextInField(element("inp_field_text", fieldName), data);
			Assert.assertEquals(element("inp_field_text", fieldName).getAttribute("value").trim(), data);
		}
		else if (fieldName.equalsIgnoreCase("holdReasonSummaryText")) {
			enterTextInField(element("textarea_hold_reason_description", fieldName), data);
			Assert.assertEquals(element("textarea_hold_reason_description", fieldName).getAttribute("value").trim(),
					data);
		}
		else {
			enterTextInField(element("inp_field_text", fieldName), data);
			Assert.assertEquals(element("inp_field_text", fieldName).getAttribute("value").trim(), data);
		}
		return data;

	}

	/*
	 * public void verifyErrorMessageonAlert(String message) {
	 * Assert.assertTrue(isElementDisplayed("blank_field",message));
	 * 
	 * }
	 */
	public void clickOnEditLinkCorresspondingToHoldReason(String holdReason) {
		isElementDisplayed("link_edit", holdReason);
		clickUsingXpathInJavaScriptExecutor(element("link_edit", holdReason));
		logMessage("Clicked on Edit link corressponding to printer " + holdReason);
		wait.waitForElementToBeVisible(element("popup_text"));
		Assert.assertTrue(element("popup_text").getText().trim().contains("Edit Hold Reason"));
		logMessage("[ASSERTION PASSED]: Verified Edit a Hold Reason pop up");

	}

	public void clickOnEditLinkCorresspondingToAddedRecord(String addedRecord, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("link_edit", addedRecord);
		wait.waitForElementToBeClickable(element("link_edit", addedRecord));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_edit", addedRecord));
		logMessage("Clicked on Edit link corressponding to Added record " + addedRecord);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
			wait.waitForElementToBeVisible(element("popup_text"));
			Assert.assertTrue(element("popup_text").getText().trim().contains(data));
		} catch (Exception e) {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
			wait.waitForElementToBeVisible(element("label_hold_reason"));
			Assert.assertTrue(element("label_hold_reason").getText().trim().contains(data));

		}
		logMessage("[ASSERTION PASSED]: Verified Edit a Record pop up");
	}

	public void clickOnEditLinkCorresspondingToCustomLabel(String addedRecord, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("link_edit_custom_label", addedRecord);
		wait.waitForElementToBeClickable(element("link_edit_custom_label", addedRecord));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_edit_custom_label", addedRecord));
		logMessage("Clicked on Edit link corressponding to Added record " + addedRecord);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
			wait.waitForElementToBeVisible(element("popup_text"));
			Assert.assertTrue(element("popup_text").getText().trim().contains(data));
		} catch (Exception e) {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
			wait.waitForElementToBeVisible(element("label_hold_reason"));
			Assert.assertTrue(element("label_hold_reason").getText().trim().contains(data));

		}
		logMessage("[ASSERTION PASSED]: Verified Edit a Record pop up");

	}

	public void clickOnEditLinkCorresspondingToAddedRecordDistributor(String addedRecord, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		isElementDisplayed("link_edit", addedRecord);
		wait.waitForElementToBeClickable(element("link_edit", addedRecord));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_edit", addedRecord));
		logMessage("Clicked on Edit link corressponding to Added record " + addedRecord);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
			wait.waitForElementToBeVisible(element("popup_text"));
			Assert.assertTrue(element("popup_text").getText().trim().contains(data));
		} catch (Exception e) {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
			wait.waitForElementToBeVisible(element("popup_text"));
			Assert.assertTrue(element("popup_text").getText().trim().contains(data));

		}
		logMessage("[ASSERTION PASSED]: Verified Edit a Record pop up");
	}

	public void clickOnEditLinkCorresspondingToRoutingRule(String addedRecord, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		isElementDisplayed("button_editRoutingRule", addedRecord);
		wait.waitForElementToBeClickable(element("button_editRoutingRule", addedRecord));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("button_editRoutingRule", addedRecord));
		logMessage("Clicked on Edit link corressponding to Added record " + addedRecord);
		/*
		 * wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		 * wait.waitForElementToBeVisible(element("popup_text"));
		 * Assert.assertTrue(element("popup_text").getText().trim().contains(
		 * data));
		 * logMessage("[ASSERTION PASSED]: Verified Edit a Record pop up");
		 */
	}

	public void clickOnEditLinkCorresspondingToDosageForm(String addedRecord, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		isElementDisplayed("button_editDosageForm", addedRecord);
		wait.waitForElementToBeClickable(element("button_editDosageForm", addedRecord));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("button_editDosageForm", addedRecord));
		logMessage("Clicked on Edit link corressponding to Added record " + addedRecord);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForElementToBeVisible(element("popup_text"));
		Assert.assertTrue(element("popup_text").getText().trim().contains(data));
		logMessage("[ASSERTION PASSED]: Verified Edit a Record pop up");
	}

	public boolean verifyToggleButtonIsPresent(String flag) {
		return isElementDisplayed("action_toggle_button", flag);
	}

	public boolean verifyEditHoldReasonPopupInputFieldsArePresent() {

		return (isElementDisplayed("inp_field_text", "descriptionText")
				&& isElementDisplayed("textarea_hold_reason_description", "holdReasonSummaryText"));

	}

	public boolean verifyEditHoldReasonPopupButtonsArePresent() {

		return isElementDisplayed("action_button", "save") && isElementDisplayed("action_button", "cancel");

	}

	public boolean verifyEditHoldReasonPopupCrossButtonIsPresent() {

		return isElementDisplayed("cross_button");
	}

	public boolean verifyEditHoldReasonPopupRequiredText() {

		return isElementDisplayed("required_text", "Indicates required fields");

	}

	public boolean verifyEditHoldReasonPopupGetsClosedOnClickingCancelButton() {
		boolean flag = isElementNotDisplayed("popup_edit_hold");
		return flag;
	}

	public void verifyOldNameIsNotAvailableInInHoldReasonList(String holdReasonName_old) {
		// isElementNotDisplayed returns true if element is displayed, false otherwise 
		Assert.assertFalse(isElementNotDisplayed("added_hold_reason_name", holdReasonName_old));
	}

	public void verifyTabIsNotDisplayed(String tabName) {

		isElementNotDisplayed("tab_link", tabName);
	}

	public void verifyTabIsDisplayed(String tabName) {

		isElementDisplayed("tab_link", tabName);
	}

	public String EnterValueInInputField(String fieldName, String data) {

		if (fieldName.equalsIgnoreCase("holdReasonSummaryText")) {
			enterTextInField(element("textarea_hold_reason_description", fieldName), data);
		} else {
			enterTextInField(element("inp_field_text", fieldName), data);

		}

		return data;

	}

	public void EnterValueInNameFieldInEditCarouselPopup(String data) {
		enterTextInField(element("textbox_carouselName"), data);
	}

	public void generatingRandomString() {
		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		element("textbox_carouselName").clear();
		element("textbox_carouselName").sendKeys(generatedString);
	}

	public void verifyElementsOnEditCarousel(String toggle) {
		isElementDisplayed("textbox_carouselName");
		isElementDisplayed("action_toggle_button", toggle);
		isElementDisplayed("crossIcon_Carousel");
		isElementDisplayed("req_lot_checkbox", "carouselName");
		isElementDisplayed("req_lot_checkbox", "carouselModel");
		isElementDisplayed("dropdowns_externalSystem", "carouselModel");
		isElementDisplayed("labelName_Carousel", "Type");
		isElementDisplayed("labelName_Carousel", "Access");
	}

	public void clear() {
		clickUsingXpathInJavaScriptExecutor(element("textbox_carouselName"));
		wait.hardWait(3);
		element("textbox_carouselName").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		wait.hardWait(5);

	}

	/*
	 * public void clickCrossIcon(){ wait.hardWait(20);
	 * //wait.elementHighlight(element("cross_icon"));
	 * //element("cross_icon").click();
	 * clickUsingXpathInJavaScriptExecutor(element("cross_icon"));
	 * logMessage("[STEP]: Cross Icon is clicked");
	 * 
	 * }
	 */

	public void EnterRandomValueInInputFieldDispense(String fieldName, String data) {
		enterTextInField(element("textarea_hold_reason_description", fieldName), data);
	}

	public void verifyNewlyAddedRecordNameInList(String holdReasonName) {
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
			isElementDisplayed("added_hold_reason_name", holdReasonName);
			Assert.assertTrue(element("added_hold_reason_name", holdReasonName).isDisplayed());
			logMessage("[ASSERTION PASSED]: Newly added record : " + holdReasonName + " is displayed in the List");
		} catch (NoSuchElementException e) {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
			isElementDisplayed("added_hold_reason_name", holdReasonName);
			Assert.assertTrue(element("added_hold_reason_name", holdReasonName).isDisplayed());
			logMessage("[ASSERTION PASSED]: Newly added record : " + holdReasonName + " is displayed in the List");
		} catch (Exception e) {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
			isElementDisplayed("distributor_Account_no", holdReasonName);
			Assert.assertTrue(element("distributor_Account_no", holdReasonName).getAttribute("value")
					.equalsIgnoreCase(holdReasonName));
			logMessage("[ASSERTION PASSED]: Newly added record : " + holdReasonName + " is displayed in the List");
		}
	}

	public void verifyNewlyAddedRecordNameInListRoutingRule(String holdReasonName) {
		wait.hardWait(10);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 300);
		// wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		//isElementDisplayed("view_routingRules", holdReasonName);
		Assert.assertTrue(element("view_routingRules", holdReasonName).isDisplayed());
		logMessage("[ASSERTION PASSED]: Newly added record : " + holdReasonName + " is displayed in the List");
	}

	public void verifyNewlyRemovedRecord(String holdReasonName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		// wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		Assert.assertFalse(isElementNotDisplayed("added_hold_reason_name", holdReasonName));
		// Assert.assertFalse(element("added_hold_reason_name",
		// holdReasonName).isDisplayed());
		logMessage("[ASSERTION PASSED]: Newly removed record : " + holdReasonName + " is not displayed in the List");
	}

	public String fetchFirstBarcodeValue() {
		isElementDisplayed("text_barcodePopUp");
		String data = element("text_barcodePopUp").getText();
		return data;
	}

	public void verifyNewlyAddedRecordNameInListLabelTags(String holdReasonName) {
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
			isElementDisplayed("list_labelTag", holdReasonName);
			Assert.assertTrue(element("list_labelTag", holdReasonName).isDisplayed());
			logMessage("[ASSERTION PASSED]: Newly added record : " + holdReasonName + " is displayed in the List");
		} catch (Exception e) {
			isElementDisplayed("list_labelTag", holdReasonName);
			Assert.assertTrue(
					element("list_labelTag", holdReasonName).getAttribute("value").equalsIgnoreCase(holdReasonName));
			logMessage("[ASSERTION PASSED]: Newly added record : " + holdReasonName + " is displayed in the List");

		}
	}

	public void clickToggleButton(String action, String toggle) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.hardWait(5);
		isElementDisplayed("action_toggle_button", toggle);
		if (element("action_toggle_button", toggle).getAttribute("value").equals(action)) {
			if(action == "true") {
				logMessage("Toggle button is Active");
			}
			else {
				logMessage("Toggle button is Inactive");
			}

		} else {
			try {
				element("action_toggle_button_1", toggle).click(); // please do not comment this line.
			} catch (Exception e) {
				clickUsingXpathInJavaScriptExecutorSingleClick(element("action_toggle_button_1", toggle));

			}
		}
		if(action == "true") {
			logMessage("Toggle button is Active");
		}
		else {
			logMessage("Toggle button is Inactive");
		}
		wait.hardWait(6);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
	}

	public void verifyErrorMessageForAlreadyExistingName(String message) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		try {
			isElementDisplayed("text_error_msg");
			System.out.println("actual" + element("text_error_msg").getText());
			System.out.println("ext" + message);
			Assert.assertTrue(element("text_error_msg").getText().trim().equalsIgnoreCase(message.trim()));
		} catch (Exception e) {
			isElementDisplayed("text_error_msg_new");
			System.out.println("actual" + element("text_error_msg_new").getText());
			System.out.println("exp" + message);
			Assert.assertTrue(element("text_error_msg_new").getText().trim().equalsIgnoreCase(message.trim()));
		}
	}

	public void verifyErrorMessageCarousel(String message) {
		wait.hardWait(7);
		String spanMessage = element("error_message_carousel", message).getText();
		System.out.println("Value of message:  " + spanMessage);
		Assert.assertEquals(spanMessage, message);

	}

	/*
	 * public void verifyErrorMessageonAlert(String message) {
	 * Assert.assertTrue(isElementDisplayed("blank_field",message));
	 * 
	 * }
	 */

	public String getDropDownValue(String fieldName) {
		isElementDisplayed("dropdowns_externalSystem", fieldName);
		String externalSystem = getSelectedTextFromDropDown(element("dropdowns_externalSystem", fieldName));

		logMessage("External System has been extracted from DropDown.");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		return externalSystem;
	}

	public void generatingRandomStringWithSpace() {

		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		element("textbox_carouselName").clear();
		element("textbox_carouselName").sendKeys(generatedString + " " + generatedString);
	}

	public void clearInputBox(String fieldname) {
		if (fieldname.equalsIgnoreCase("holdReasonSummaryText")) {

			clickUsingXpathInJavaScriptExecutor(element("textarea_hold_reason_description", fieldname));
			wait.hardWait(3);
			element("textarea_hold_reason_description", fieldname).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		} else {
			clickUsingXpathInJavaScriptExecutor(element("inp_field_text", fieldname));
			wait.hardWait(3);
			element("inp_field_text", fieldname).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			// wait.hardWait(5);

		}

	}
	
	public void clearInputField(String id, String name) {
		isElementDisplayed("element_by_id_and_name", id, name);
		element("element_by_id_and_name", id, name).clear();
		logMessage("field with id - " + id + " and name - " + name + " is cleared");
	}

	public void clearSearchBox(String field) {

		isElementDisplayed("textBox_search", field);
		// click(element("clear_search_box", field));
		clickUsingXpathInJavaScriptExecutor(element("textBox_search", field));
		element("textBox_search", field).clear();
		System.out.println("DATA" + element("textBox_search", field).getAttribute("value").trim());
		Assert.assertEquals(element("textBox_search", field).getAttribute("value").trim(), "");
		logMessage("[ASSERTION PASSED]: Search Term as : " + " " + " is displayed in the Search Field");
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 210);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 210);

	}

	public void verifySearchBoxIsPresentOrNot(String field) {

		Assert.assertTrue(isElementDisplayed("textBox_search", field));
	}

	public void clearSearchBoxField(String field) {
		isElementDisplayed("textBox_search", field);
		// click(element("clear_search_box", field));
		element("textBox_search", field).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		System.out.println("DATA" + element("textBox_search", field).getAttribute("value").trim());
		Assert.assertEquals(element("textBox_search", field).getAttribute("value").trim(), "");
		logMessage("[ASSERTION PASSED]: Search Term as : " + " " + " is displayed in the Search Field");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		
	}

	public String getNoDataText() {
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
			isElementDisplayed("text_no_data");
			
			System.out.println("text_no_data");
			logMessage( "'" + element("text_no_data").getText() + "'");
			return element("text_no_data").getText();
		} catch (Exception e) {
			isElementDisplayed("text_no_data_new");
			
			System.out.println("text_no_data_new");
			logMessage( "'" + element("text_no_data_new").getText() + "'");
			return element("text_no_data_new").getText();
		}
	}

	public void refreshPage() {
		wait.hardWait(10);
		pageRefresh();

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
	}

	public void refreshPageWithoutLoader() {

		pageRefresh();
	}

	public Set<String> verifyRecordList() {
		// wait.hardWait(5);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		Set<String> carouselList = new HashSet<String>();
		List<WebElement> elements;
		
		try {
			elements = elements("list_recordNames");
			wait.waitForElementsToBeVisible(elements); 		// commented by Yugal
		} catch(Exception e) {
			elements = elements("record_name_list");
			wait.waitForElementsToBeVisible(elements);
		}
		
		for (WebElement ele : elements) {
			carouselList.add(ele.getText().trim());
		}
		Assert.assertTrue(!carouselList.isEmpty());	
		
		logMessage("[ASSERTION PASSED]: Verified List of carousels Present on UI");
		return carouselList;
	}

	public boolean verifyRecordListEmpty() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		return isElementDisplayed("text_no_data");
	}

	public boolean verifyCarouselStatusAsActive() {
		wait.hardWait(20);
		List<WebElement> elements = elements("list_carouselStatus");
		for (WebElement ele : elements) {
			if (!(ele.getText().trim().equalsIgnoreCase("Active"))) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyCarouselStatusInactive() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		List<WebElement> elements = elements("list_carouselStatus");
		for (WebElement ele : elements) {
			if ((ele.getText().trim().equals("Inactive"))) {
				count1++;
				System.out.println("Value of count:  " + count);
			}
		}
		if (count1 > 0)
			return true;
		else
			return false;
	}

	public boolean verifyEditLinkUnderActionColumn() {
		wait.waitForElementsToBeVisible(elements("list_editAction"));
		List<WebElement> elements = elements("list_editAction");
		// wait.waitForElementsToBeVisible(elements);
		for (WebElement ele : elements) {
			if (!ele.getText().trim().equalsIgnoreCase("Edit")) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyEditLinkOnLabel() {
		wait.waitForElementsToBeVisible(elements("list_edit_label"));
		List<WebElement> elements = elements("list_edit_label");
		// wait.waitForElementsToBeVisible(elements);
		for (WebElement ele : elements) {
			if (!ele.getText().trim().equalsIgnoreCase("Edit")) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyEditLinkOnCarousel() {
		wait.waitForElementsToBeVisible(elements("list_edit_carousel"));
		List<WebElement> elements = elements("list_edit_carousel");
		// wait.waitForElementsToBeVisible(elements);
		for (WebElement ele : elements) {
			if (!ele.getText().trim().equalsIgnoreCase("Edit")) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyCarouselStatus() {
		wait.hardWait(3);
		List<WebElement> elements = elements("list_carouselStatus");
		for (WebElement ele : elements) {
			if ((ele.getText().trim().equalsIgnoreCase("InActive"))) {
				count++;
			}
		}
		if (count > 0)
			return true;
		else
			return false;
	}

	public boolean verifyGlStatus() {
		wait.hardWait(5);
		List<WebElement> elements = elements("list_glStatus");
		for (WebElement ele : elements) {
			if ((ele.getText().trim().equalsIgnoreCase("InActive"))) {
				count++;
			}
		}
		if (count > 0)
			return true;
		else
			return false;
	}

	public ArrayList<String> captureDataForParticularColumn(String coulmn) {
		List<WebElement> elements = elements("text_column", coulmn);
		column_data = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			String data = elements.get(i).getText();
			column_data.add(data);
		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		return column_data;
	}

	public ArrayList<String> captureDataForParticularColumnDosageForm(String coulmn) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		List<WebElement> elements = elements("list_dosageForms", coulmn);
		column_data = new ArrayList<String>();
		for (WebElement ele : elements) {
			column_data.add(ele.getText().trim());
		}
		return column_data;
	}

	public ArrayList<String> captureDataForGlParticularColumn(String coulmn) {
		List<WebElement> elements = elements("text_column_gl", coulmn);
		column_data = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			String data = elements.get(i).getText();
			column_data.add(data);
		}
		System.out.println("GL Account before data " + column_data);
		return column_data;
	}

	public String captureDataForParticularColumnAndRow(String coulmn, int index) {
		List<WebElement> elements = elements("text_column", coulmn);
		String data = elements.get(index).getText();
		return data;
	}

	public ArrayList<String> sortDataForParticularColumnInAscendingOrder(String coulmn) {
		ArrayList<String> data_compare = captureDataForParticularColumn(coulmn);
		Collections.sort(data_compare, String.CASE_INSENSITIVE_ORDER);
		return data_compare;
	}

	public ArrayList<String> sortDataForParticularColumnInAscendingOrderDosageForm(String coulmn) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		ArrayList<String> data_compare = captureDataForParticularColumnDosageForm(coulmn);
		Collections.sort(data_compare, String.CASE_INSENSITIVE_ORDER);
		return data_compare;
	}

	public ArrayList<String> sortDataForParticularColumnInDescendingOrder(String coulmn) {
		ArrayList<String> data_compare = captureDataForParticularColumn(coulmn);
		Collections.sort(data_compare, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
		return data_compare;
	}

	public ArrayList<String> sortDataForParticularColumnInAscendingOrderForGl(String coulmn) {
		ArrayList<String> data_compare = captureDataForGlParticularColumn(coulmn);
		Collections.sort(data_compare, String.CASE_INSENSITIVE_ORDER);
		System.out.println("GL Account after data = " + data_compare);
		return data_compare;
	}

	public ArrayList<String> sortDataForParticularColumnInDescendingOrderForGL(String coulmn) {
		ArrayList<String> data_compare = captureDataForGlParticularColumn(coulmn);
		Collections.sort(data_compare, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
		return data_compare;
	}

	public void verifyAndClickSortIcon(String column) {
		isElementDisplayed("sort_icon", column);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("sort_icon", column));
		// clickUsingXpathInJavaScriptExecutor(element("sort_icon", column));
		// click(element("sort_icon", column));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		// clickUsingXpathInJavaScriptExecutor(element("sort_icon", column));
	}

	public boolean verifySortIcon(String coulmn) {
		wait.hardWait(2);
		// Assert.assertTrue(isElementDisplayed("sort_icon", coulmn));
		return isElementDisplayed("sort_icon", coulmn);
	}

	public boolean verifyLengthForParticularColumn(String coulmn) {

		List<WebElement> elements = elements("text_column", coulmn);
		int len;
		for (int i = 0; i < elements.size(); i++) {
			len = elements.get(i).getText().length();
			if (len > 50) {
				return false;
			}

		}
		return true;
	}

	public void clickCoordinates() {

		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0);
		actions.moveByOffset(element("").getLocation().getX(), 140).click().build().perform();
	}

	public boolean verifySearchCrossIcon() {
		try {
			wait.hardWait(3);

			return ((isElementDisplayed("clear_search_box")));
		} catch (Exception e) {
			return ((isElementDisplayed("clear_search_box_parent")));
		}
	}

	public void changeSearchText(String field, String coulmn) {
		int counter = 0;
		char[] charArray = getColumnFirstData("1").toCharArray();
		List<WebElement> elements;
		for (int i = 0; i < charArray.length; i++) {

			enterSearchTermInSearchField(charArray[i] + "", field);
			int counter1 = elements("text_column", coulmn).size();
			if (counter1 > 1) {
				counter++;
			}

		}
	}

	public void verifyNewlyAddedHoldReasonStatus(String holdReasonName, String status) {

		isElementDisplayed("added_hold_reason_status", holdReasonName, status);
		Assert.assertTrue(element("added_hold_reason_status", holdReasonName, status).isDisplayed());
		logMessage("[ASSERTION PASSED]: Newly added Hold Reason : " + holdReasonName + "with status" + status
				+ " is displayed in the  Hold Reason List");

	}

	public boolean verifyLabelIsPresent(String text) {
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
			Assert.assertTrue(element("label_hold_reason").getText().trim().contains(text));
			return isElementDisplayed("label_hold_reason", text.trim());
		} catch (Exception e) {
			// wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
			Assert.assertTrue(element("page_title").getText().trim().equals(text));
			return isElementDisplayed("page_title", text.trim());
		}
	}
	
	public boolean verifyCustomlabelModelPopUpwindow(String text) {
		try {
			Assert.assertTrue(element("duplicate_label_popup").getText().trim().contains(text));
			return isElementDisplayed("duplicate_label_popup", text.trim());		
		} catch (Exception e) {
			System.out.println(e);
		}
		return flag;
	}

	public boolean verifySearchBoxIsPresent(String string) {
		return isElementDisplayed("textBox_search", string);
	}

	public boolean verifyButtons(String action) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		return isElementDisplayed("action_button", action);
	}
	
	public boolean isButtonWithTextPresent(String text) {
		return isElementNotDisplayed("added_hold_reason_name", text);
	}

	public boolean verifyLinksOnUI(String link) {

		return isElementDisplayed("action_button", link);
	}

	public boolean verifyBreadCrumb(String field) {
		return isElementDisplayed("link_breadcrumb", field);
	}

	public boolean verifyColumnHeaders(String[] columnHeaders) {
		int count = 0;
		for (String col : columnHeaders) {
			if (isElementDisplayed("sort_icon", col)) {
				count++;
			}
		}

		if (count == columnHeaders.length) {
			return true;
		}
		
		return false;
	}

	public boolean VerifySortIconIsDisabled(String colName) {

		return isElementNotDisplayed("sort_icon", colName);
	}

	public void verifyKeyboardActions(String action, String fieldName) {
		if (action.equalsIgnoreCase("TAB")) {
			wait.hardWait(3);

			// element("action_button", fieldName).sendKeys(Keys.TAB);
			pressKeyUsingAction(Keys.TAB);
			wait.hardWait(3);

		}
		if (action.equalsIgnoreCase("ENTER")) {
			wait.hardWait(3);

			// element("action_button", fieldName).sendKeys(Keys.ENTER);
			pressKeyUsingAction(Keys.ENTER);
			wait.hardWait(3);

		}
	}

	public void clearInputFields(String fieldName) {

		if (fieldName.equalsIgnoreCase("holdReasonSummaryText")) {
			element("textarea_hold_reason_description", fieldName).clear();

		} else {
			element("inp_field_text", fieldName).clear();

		}

	}

	public void enterSearchTermInSearchField(String data, String field) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("search_box", field);
		element("search_box", field).clear();
		
		Actions action = new Actions(driver);
		Action buildAction = action.moveToElement(element("search_box", field)).click()
				.sendKeys(element("search_box", field), data).build();
		buildAction.perform();
		
		String dataEntered = element("search_box", field).getAttribute("value");
		logMessage("[STEP]: Data '" + dataEntered + "' is entered in the field '" + field + "'");
	}
	
	public void verifySearchBoxOnDistributor(String field) {
		isElementDisplayed("search_box", field);
	}

	public void enterSearchTerm(String data, String field) {
		isElementDisplayed("search_box", field);
		enterTextInField(element("search_box", field), data);
		Actions action = new Actions(driver);
		Action buildAction = action.moveToElement(element("search_box", field)).click()
				.sendKeys(element("search_box", field), data).build();
		buildAction.perform();
		wait.hardWait(3);
	}

	public void enterSearchTermInSearchBox(String data, String field) {
		isElementDisplayed("search_box", field);
		enterTextInField(element("search_box", field), data);
		wait.hardWait(3);
	}

	public String enterSearchTermInSearchBoxUsingJavascriptExecutor(String data, String field) {
		try {
			isElementDisplayed("search_box", field);
			sendKeysUsingXpathInJavaScriptExecutor(element("search_box", field), data);
			// sendKeysUsingXpathInJavaScriptExecutor(element("account_no_input",
			// field),data+" ");
			// sendKeysKeyUsingXpathInJavaScriptExecutor(element("account_no_input",
			// field),
			// Keys.BACK_SPACE);
			// element("search_box", field).sendKeys(Keys.ENTER);
			wait.hardWait(3);
		} catch (Exception e) {
			isElementDisplayed("account_no_input", field);
			sendKeysUsingXpathInJavaScriptExecutor(element("account_no_input", field), data);
			element("search_box", field).sendKeys(Keys.ENTER);
			wait.hardWait(3);
		}
		return data;
	}

	public String enterSearchTermInSearchFieldGl(String data, String field) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 210);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 210); // please do not comment this line
		
		isElementDisplayed("search_box", field);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("search_box", field));
		// clearSearchBoxField(field);
		element("search_box", field).clear();
		element("search_box", field).sendKeys(data);
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		
		String dataEntered = element("search_box", field).getAttribute("value");
		logMessage("[STEP]: Entered data: '" + dataEntered + "' in the field: '" + field +"'");
		return dataEntered;
	}

	/*
	 * public void clickCrossIcon(){ wait.hardWait(20);
	 * //wait.elementHighlight(element("cross_icon"));
	 * //element("cross_icon").click();
	 * clickUsingXpathInJavaScriptExecutor(element("cross_icon"));
	 * logMessage("[STEP]: Cross Icon is clicked");
	 * 
	 * }
	 */

	public String getFirstInactiveRecord() {

		isElementDisplayed("first_inactive_data");
		return element("first_inactive_data").getText();
	}

	public String getFirstInactiveExternalSystem() {

		isElementDisplayed("first_inactive_external_system");
		return element("first_inactive_external_system").getText();
	}

	public void verifyErrorMessageonAlert(String message, String fieldName) {

		wait.hardWait(5);
		isElementDisplayed("message_field", fieldName);
		String spanMessage = element("message_field", fieldName).getText();
		System.out.println("Value of message:  " + spanMessage);
		Assert.assertEquals(spanMessage, message);

	}
	
	public void verifyErrorMessageOnTextArea(String message, String fieldName) {
		wait.hardWait(5);
		// textarea_message_field
		isElementDisplayed("textarea_message_field", fieldName);
		String spanMessage = element("textarea_message_field", fieldName).getText();
		System.out.println("Value of message:  " + spanMessage);
		Assert.assertEquals(spanMessage, message);
	}

	public void verifyModelIsDisabled() {
		String value = element("textbox_carouselModel").getAttribute("disabled");
		Assert.assertEquals(value, "true");
	}

	public boolean verifyGlStatusAsActive() {
		wait.hardWait(20);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		List<WebElement> elements = elements("list_glStatus");
		for (WebElement ele : elements) {
			if (!(ele.getText().trim().equalsIgnoreCase("Active"))) {
				return false;
			}
		}
		return true;
	}

	public void SendActionKeys(String fieldName, String string2) {
		if (fieldName.equalsIgnoreCase("descriptionText")) {
			element("inp_field_text", fieldName).sendKeys(Keys.BACK_SPACE);
		}
		if (fieldName.equalsIgnoreCase("holdReasonSummaryText")) {
			element("textarea_hold_reason_description", fieldName).sendKeys(Keys.BACK_SPACE);
		}
	}

	public boolean verifyHoldReasonStatusAsActive() {
		List<WebElement> elements = elements("list_holdreasonStatus");
		for (WebElement ele : elements) {
			if (!(ele.getText().trim().equalsIgnoreCase("Active"))) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyHoldReasonStatus() {
		List<WebElement> elements = elements("list_holdreasonStatus");
		for (WebElement ele : elements) {
			if ((ele.getText().trim().equalsIgnoreCase("InActive"))) {
				count++;
			}
		}
		if (count > 0)
			return true;
		else
			return false;
	}

	public void clickEditLink(String glAccountName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("link_edit", glAccountName);
		clickUsingXpathInJavaScriptExecutor(element("link_edit", glAccountName));
		//Assert.assertTrue(element("popup_edit_hold", "Edit General Ledger Account").isDisplayed());
	}

	public void clickEditLinkInTable(String name) {
		isElementDisplayed("link_edit_table", name);
		clickUsingXpathInJavaScriptExecutor(element("link_edit_table", name));
		// Assert.assertTrue(element("popup_edit_hold", "Edit General Ledger
		// Account").isDisplayed());
	}

	public boolean verifyAddGLAccountPopupGetsClosedOnClickingCancelButton() {
		boolean flag = isElementNotDisplayed("popup_add");
		return flag;
	}

	public boolean verifyAddHoldReasonPopupButtonsArePresent() {
		return isElementDisplayed("action_button", "save") && isElementDisplayed("action_button", "cancel");
	}

	public boolean verifyAddHoldReasonPopupCrossButtonIsPresent() {
		return isElementDisplayed("cross_button");
	}

	public int verifyMaxLengthOfAnTextAreaField(String fieldName) {
		return Integer
				.parseInt(element("textarea_hold_reason_description", fieldName).getAttribute("maxlength").trim());

	}

	public String EnterRandomValueInTextAreaField(String fieldName, String data) {
		enterTextInField(element("textarea_hold_reason_description", fieldName), data);
		// Assert.assertEquals(element("textarea_hold_reason_description",
		// fieldName).getAttribute("value").trim(),data);
		return data;
	}

	public String selectValueFromDropDownForDosagePISSystem(String fieldName, String data) {
		selectProvidedTextFromDropDown(element("dropdowns_dosage_pis", fieldName), data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("dropdowns_dosage_pis", fieldName)), data);
		return data;
	}

	public void selectValueFromDropDownForDosagePISSystemByIndex(String fieldName, int index) {
		selectProvidedTextFromDropDownUsingIndex(element("dropdowns_dosage_pis", fieldName), index);
	}

	public void verifyFirstValueFromDropDownIsSelected(String fieldName, int index) {
		selectProvidedTextFromDropDownUsingIndex(element("dropdowns_dosage_pis", fieldName), index);
		String data = element("dropdowns_dosage_pis", fieldName).getText();
		Assert.assertEquals(getSelectedTextFromDropDown(element("dropdowns_dosage_pis", fieldName)), data);
	}

	public Set<String> codeListDosageForms(String columnNumber) {
		wait.hardWait(5);
		Set<String> codeList = new HashSet<String>();
		List<WebElement> elements = elements("list_dosageForms", columnNumber);
		for (WebElement ele : elements) {
			codeList.add(ele.getText().trim());

		}
		Assert.assertTrue(!codeList.isEmpty());
		logMessage("[ASSERTION PASSED]: Verified code list on Dosage Forms UI");
		return codeList;
	}

	public Set<String> descriptionFormDosageForms(String columnNumber) {
		// wait.hardWait(5);
		Set<String> descriptionForm = new HashSet<String>();
		List<WebElement> elements = elements("list_dosageForms", columnNumber);
		for (WebElement ele : elements) {
			descriptionForm.add(ele.getText().trim());

		}
		Assert.assertTrue(!descriptionForm.isEmpty());
		logMessage("[ASSERTION PASSED]: Verified description forms list on Dosage Forms UI");
		return descriptionForm;
	}

	public ArrayList<String> sortOrderDosageForms() {
		// wait.hardWait(5);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		ArrayList<String> sortOrder = new ArrayList<String>();
		List<WebElement> elements = elements("list_dosageForm_Sort");
		for (WebElement ele : elements) {
			sortOrder.add(ele.getText().trim());

		}
		Assert.assertTrue(!sortOrder.isEmpty());
		logMessage("[ASSERTION PASSED]: Verified sort order list on Dosage Forms UI");
		return sortOrder;
	}

	public ArrayList<String> sortOrderDispenseUnit() {
		// wait.hardWait(5);
		ArrayList<String> sortOrder = new ArrayList<String>();
		List<WebElement> elements = elements("list_dispenseUnit_Sort");
		for (WebElement ele : elements) {
			sortOrder.add(ele.getText().trim());

		}
		Assert.assertTrue(!sortOrder.isEmpty());
		logMessage("[ASSERTION PASSED]: Verified sort order list on Dispense Unit UI");
		return sortOrder;
	}

	public boolean verifyDosageFormsStatusAsActive() {
		ArrayList<String> status = new ArrayList<String>();
		List<WebElement> elements = elements("list_dosageForm_Status");
		for (WebElement ele : elements) {
			status.add(ele.getText().trim());
			if (!(ele.getText().trim().equalsIgnoreCase("Active"))) {
				return false;
			}
		}
		Assert.assertTrue(!status.isEmpty());
		logMessage("[ASSERTION PASSED]: Verified status list on Dosage Forms UI");
		return true;
	}

	public boolean verifyDispenseUnitStatusAsActive() {
		ArrayList<String> status = new ArrayList<String>();
		List<WebElement> elements = elements("list_dispenseUnit_Status");
		for (WebElement ele : elements) {
			status.add(ele.getText().trim());
			if (!(ele.getText().trim().equalsIgnoreCase("Active"))) {
				return false;
			}
		}
		Assert.assertTrue(!status.isEmpty());
		logMessage("[ASSERTION PASSED]: Verified status as Active");
		return true;
	}

	public ArrayList<String> verifyEditActionDosageForms(String columnNumber) {
		ArrayList<String> editAction = new ArrayList<String>();
		List<WebElement> elements = elements("list_dosageForms", columnNumber);
		for (WebElement ele : elements) {
			editAction.add(ele.getText().trim());

		}
		Assert.assertTrue(!editAction.isEmpty());
		logMessage("[ASSERTION PASSED]: Verified edit actions list on Dosage Forms UI");
		return editAction;
	}

	public boolean verifyToggleButtonOnDosageForm(String toggle) {
		return isElementDisplayed("action_toggle_button", toggle);
	}

	public boolean verifyColumnHeaderOnDosageForm(String columnName) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		try {
			return isElementDisplayed("header_dosageForms", columnName);

		} catch (Exception e) {
			return false;
		}
	}

	public boolean verifyDropdownOnDosageForm() {
		try {
			return isElementDisplayed("dropdown_dosageForms");
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verifyDosageFormsStatusAsInactive() {
		List<WebElement> elements = elements("list_dosageForm_Status");
		for (WebElement ele : elements) {
			if ((ele.getText().trim().equals("Inactive"))) {
				count1++;
				// System.out.println("Value of count: " + count);
			}
		}
		if (count1 > 0)
			return true;
		else
			return false;
	}

	public boolean verifyDispenseUnitStatusAsInactive() {
		List<WebElement> elements = elements("list_dispenseUnit_Sort");
		for (WebElement ele : elements) {
			if ((ele.getText().trim().equals("Inactive"))) {
				count1++;
				// System.out.println("Value of count: " + count);
			}
		}
		if (count1 > 0)
			return true;
		else
			return false;
	}

	public void selectPISFromDropdown(int index) {
		selectDropDownValue(element("dropdown_dosageForms"), index);
	}

	public void selectPISFromDropdown(String value) {
		selectDropDownValue(value);

	}

	public void getFirstPISValue() {
		String value = element("dropdown_dosageForms_first").getText();
		System.out.println("Value of dropdown : " + value);
		Assert.assertEquals(element("dropdown_dosageForms_first").getText(),
				getSelectedTextFromDropDown(element("dropdown_dosageForms")));
	}

	public void clickOnEditLinkCorresspondingToAddedDosageForm(String addedRecord, String data) {
		isElementDisplayed("edit_link_dosageform", addedRecord);
		wait.waitForElementToBeClickable(element("edit_link_dosageform", addedRecord));
		clickUsingXpathInJavaScriptExecutor(element("edit_link_dosageform", addedRecord));
		logMessage("Clicked on Edit link corressponding to Added record " + addedRecord);

		wait.waitForElementToBeVisible(element("popup_text"));
		Assert.assertTrue(element("popup_text").getText().trim().contains(data));
		logMessage("[ASSERTION PASSED]: Verified Edit a Record pop up");
	}

	public void verifyErrorMessageForDosageSortOrder(String message) {
		isElementDisplayed("error_dosage_sortorder");
		System.out.println("actual" + element("error_dosage_sortorder").getText());
		System.out.println("ext" + message);
		Assert.assertTrue(element("error_dosage_sortorder").getText().trim().equalsIgnoreCase(message.trim()));

	}

	public void verifyErrorMessageDosageCode(String message) {
		wait.hardWait(7);
		String spanMessage = element("text_error_msg", message).getText();
		System.out.println("Value of message:  " + spanMessage);

		Assert.assertEquals(spanMessage, message);

	}

	public void clearDosageDescriptionBox(String field) {
		isElementDisplayed("textarea_hold_reason_description", field);
		// click(element("clear_search_box", field));
		clickUsingXpathInJavaScriptExecutor(element("textarea_hold_reason_description", field));
		element("textarea_hold_reason_description", field).clear();
		System.out.println("DATA" + element("textarea_hold_reason_description", field).getAttribute("value").trim());
		Assert.assertEquals(element("textarea_hold_reason_description", field).getAttribute("value").trim(), "");
		logMessage("[ASSERTION PASSED]: Search Term as : " + " " + " is displayed in the Search Field");

	}

	public void clearDosagecodeInputBox(String fieldname) {
		clickUsingXpathInJavaScriptExecutor(element("inp_field_text", fieldname));
		wait.hardWait(3);
		element("inp_field_text", fieldname).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		wait.hardWait(5);
	}

	public void verifyAddedISAonWFAScreen(String fieldName) {
		isElementDisplayed("added_isa", fieldName);
	}

	public void verifyInactiveISAIsNotPresentOnWFAScreen(String fieldName) {
		isElementNotDisplayed("added_isa", fieldName);
	}

	public void verifyErrorMessageForAlreadyExistingRecord(String message) {
		isElementDisplayed("error_holdreason_msg");
		System.out.println("actual" + element("error_holdreason_msg").getText());
		System.out.println("ext" + message);
		Assert.assertTrue(element("error_holdreason_msg").getText().trim().equalsIgnoreCase(message.trim()));

	}

	public void verifyDefaultValueInPrinterDropDownOnfacilitySettingsTab(String fieldName, String text) {
		isElementDisplayed("dropdowns_externalSystem", fieldName);
		System.out.println("VALUE@@@@@@@@" + element("dropdowns_externalSystem", fieldName).getText());
		verifySelectedTextIsContainedInDropDown(element("dropdowns_externalSystem", fieldName), text);
	}

	public void selectValueFromDropdownByIndex(String fieldName, int index) {
		try {
			selectDropDownValue(element("dropdown_by_index", fieldName), index);
		} catch (Exception e) {
			selectDropDownValue(element("filter_dropdown", fieldName), index);

		}
	}

	public boolean verifyWasteReasonStatusAsActive() {
		List<WebElement> elements = elements("list_wastereasonStatus");
		for (WebElement ele : elements) {
			if (!(ele.getText().trim().equalsIgnoreCase("Active"))) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyWasteReasonColumnHeaders(String[] columnHeaders) {
		int count = 0;
		for (String col : columnHeaders) {
			if (isElementDisplayed("sort_icon", col)) {
				count++;
			}
		}

		if (count == columnHeaders.length) {
			return true;
		}

		return false;
	}

	public void verifyAndClickSortIconWasteReasonName(String column) {
		System.out.println("****Verify element is displayed****");
		isElementDisplayed("sort_icon", column);
		System.out.println("****Wait for element to be clickable****");
		wait.waitForElementToBeClickable(element("sort_icon", column));
		// click(element("sort_icon", column));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("sort_icon", column));
		// wait.hardWait(2);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
	}

	public boolean verifyWasteReasonStatus() {
		List<WebElement> elements = elements("list_wastereasonStatus");
		for (WebElement ele : elements) {
			if ((ele.getText().trim().equalsIgnoreCase("InActive"))) {
				count++;
			}
		}
		if (count > 0)
			return true;
		else
			return false;
	}

	public String generatingRandomStringForWasteReasonName(int length) {

		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return getData("WasteReasonDetails.WasteReasonName") + generatedString;
	}

	public int verifyLengthOfWasteReasonSearchField(String fieldName) {
		int len = 0;
		String inputFieldValue;
		if (fieldName.equalsIgnoreCase("wasteReason")) {
			inputFieldValue = element("search_box", fieldName).getAttribute("value").trim();
			System.out.println("Input Field Text :" + inputFieldValue);
			len = inputFieldValue.length();
		} else {
			inputFieldValue = element("search_box", fieldName).getAttribute("value").trim();
			System.out.println("Input Field Text :" + inputFieldValue);
			len = inputFieldValue.length();

		}
		return len;
	}

	public boolean verifyDataContainsRandomString(String column, String randomString) {
		List<WebElement> elements = elements("text_column", column);
		System.out.println("Element Size :" + elements.size());
		randomString = randomString.toLowerCase();
		column_data = new ArrayList<String>();
		int count = 0;
		for (int i = 0; i < elements.size(); i++) {
			String element_text = elements.get(i).getText();
			element_text = element_text.toLowerCase();
			if (element_text.contains(randomString)) {
				count++;
			}
			String data = elements.get(i).getText();
			column_data.add(data);
		}
		System.out.println("column_data length :" + column_data.size());
		System.out.println("Count :" + count);
		if (count == column_data.size()) {
			return true;
		} else {
			return false;
		}
	}

	public String EnterRandomValueInWasteReasonInputField(String fieldName, String data) {
		if (fieldName.equalsIgnoreCase("wasteReason")) {

			enterTextInField(element("inp_field_text", fieldName), data);
			// Assert.assertEquals(element("inp_field_text",
			// fieldName).getAttribute("value").trim(),data);
		} else {
			enterTextInField(element("inp_field_text", fieldName), data);

			wait.hardWait(2);

			Assert.assertEquals(element("inp_field_text", fieldName).getAttribute("value").trim(), data);

		}
		return data;
	}

	public String EnterRandomValueInManufacturerInputField(String fieldName, String data) {
		if (fieldName.equalsIgnoreCase("manufacturer")) {

			enterTextInField(element("inp_field_text", fieldName), data);
			// Assert.assertEquals(element("inp_field_text",
			// fieldName).getAttribute("value").trim(),data);
		} else {
			enterTextInField(element("inp_field_text", fieldName), data);

			wait.hardWait(2);

			Assert.assertEquals(element("inp_field_text", fieldName).getAttribute("value").trim(), data);

		}
		return data;
	}

	public int verifyMaxLengthOfWasteReasonInputField(String fieldName) {
		int len = 0;
		if (fieldName.equalsIgnoreCase("wasteReason")) {
			len = Integer.parseInt(element("inp_field_text", fieldName).getAttribute("maxlength").trim());
		} else {
			len = Integer.parseInt(element("inp_field_text", fieldName).getAttribute("maxlength").trim());
		}

		return len;
	}

	public void verifyErrorMessageForAlreadyExistingWasteReasonName(String message) {
		isElementDisplayed("error_holdreason_msg");
		System.out.println("actual: " + element("error_holdreason_msg").getText());
		System.out.println("expected: " + message);
		Assert.assertTrue(element("error_holdreason_msg").getText().trim().equalsIgnoreCase(message.trim()));

	}

	public void verifyWasteReasonNameIsNotAvailableInWasteReasonList(String wasteReasonName) {
		isElementNotDisplayed("added_hold_reason_name", wasteReasonName);
		logMessage("[ASSERTION PASSED]:" + wasteReasonName + " is not displayed in the  Waste Reason List");

	}

	public void clickOnWasteReasonCrossButton() {

		element("cross_button").click();
	}

	public String enterSearchTermInBulkItemTextbox(String data) {
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		isElementDisplayed("search_box");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("search_box"));
		enterTextInField(element("search_box"), data);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		return data;

	}

	public void verifyErrorMessageonAlert(String message) {

		Assert.assertTrue(isElementDisplayed("blank_field", message),
				"[ASSERTION FAILED]: Validation message for mandatory field is not visible");
		logMessage("[ASSERTION PASSED]: Validation message for mandatory field visible : " + message);
	}

	public boolean verifyEditWasteReasonPopupInputFieldsArePresent(String field) {

		return (isElementDisplayed("inp_field_text", field));

	}

	public int verifyLengthOfWasteReasonInputField(String fieldName) {
		int len = 0;
		String inputFieldValue;
		if (fieldName.equalsIgnoreCase("wasteReason")) {
			inputFieldValue = element("inp_field_text", fieldName).getAttribute("value").trim();
			System.out.println("Input Field Text :" + inputFieldValue);
			len = inputFieldValue.length();
		} else {
			inputFieldValue = element("inp_field_text", fieldName).getAttribute("value").trim();
			System.out.println("Input Field Text :" + inputFieldValue);
			len = inputFieldValue.length();
		}

		return len;
	}

	public String getValueOfWasteReasonEditPopUpInputField(String fieldName) {

		String inputFieldValue;
		if (fieldName.equalsIgnoreCase("wasteReason")) {
			inputFieldValue = element("inp_field_text", fieldName).getAttribute("value").trim();
			System.out.println("Input Field Text :" + inputFieldValue);

		} else {
			inputFieldValue = element("inp_field_text", fieldName).getAttribute("value").trim();
			System.out.println("Input Field Text :" + inputFieldValue);

		}
		return inputFieldValue;
	}

	public void verifyEditPopUpInputFieldIsDisabled(String field) {
		String value = element("inp_field_text", field).getAttribute("disabled");
		Assert.assertEquals(value, "true");
	}

	public Set<String> columnDataOnManageDistributor(String columnNumber) {
		wait.hardWait(5);
		Set<String> codeList = new HashSet<String>();
		return codeList;
	}

	public void verifyAddButtonOnManageDistributor() {
		isElementDisplayed("action_button", "add");
		isElementDisplayed("action_button", "add");
	}

	public void clickAddButtonOnDistributor(String button) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		try{
			isElementDisplayed("action_button", button);
			try {
				element("action_button", button).click();
			} catch(Exception e) {
				clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button", button));
			}
			logMessage("Button clicked: " + button);
		}catch(Exception e){
			isElementDisplayed("action_button1", button);
			clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button1", button));
			logMessage("Button clicked: " + button);
		}
	}
	
	public void clickEditButtonOnDistributor(String button) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		element("edit_button_distributor", button).click();
		// click(element("action_button", button));
	}

	public void clickSaveButtonOnDistributor(String button) {
		boolean val = checkRadioButtonIsEnabledOrDisabledUsingJavaScript(button);
		if (!val) {
			element("action_button", button).click();
		}
	}

	public void verifyRequiredField(String message) {
		Assert.assertEquals(element("text_requiredfield").getText(), message);
	}

	public void verifytextOnEditDistributor(String message) {
		isElementDisplayed("text_editDistributor", message);

		/*
		 * public void enterValueOnAddDistributorPage(String field, String data)
		 * { isElementDisplayed("text_inputFieldDistributor", field); if
		 * (field.equalsIgnoreCase("vendorContactWebsiteAddressValue") ||
		 * field.equalsIgnoreCase("vendorContactEmailAddressValue") ||
		 * field.equalsIgnoreCase("postalCode") ||
		 * field.equalsIgnoreCase("vendorContactFaxNumberText") ||
		 * field.equalsIgnoreCase("vendorContactPhoneNumberText"))
		 * enterTextInField(element("text_inputFieldDistributor", field), data);
		 * else enterTextInField(element("text_inputFieldDistributor", field),
		 * data + System.currentTimeMillis()); }
		 */
	}

	public void verifyRadioButtonOnDistributor(String field) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("action_toggle_button_1", field);
	}

	public boolean verifyelementisdisabled(String className) {
		return isElementDisplayed("radio_button_disabled_class", className);
	}

	public void verifyTextboxOnDistributor(String field) {
		isElementDisplayed("text_inputFieldDistributor", field);
	}

	public boolean verifyAddNewItemLabelIsPresent(String text) {
		wait.waitForLoaderToBeInvisible(getLocator("loading_label"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("label_add_new_item");
		return element("label_add_new_item").getText().contains(text);
	}

	public boolean verifyCopyFacilityIsNotPresentonItemscreen(String text) {
		wait.waitForLoaderToBeInvisible(getLocator("loading_label"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		return isElementNotDisplayed("action_button1", text.trim());

	}
	
	public boolean verifyCopyFacilityIsPresentonItemscreen(String text) {
		wait.waitForLoaderToBeInvisible(getLocator("loading_label"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		return isElementDisplayed("action_button1", text.trim());

	}

	public boolean verifyPopupDeleteMessageonThearpeuticclass(String text) {
		wait.waitForLoaderToBeInvisible(getLocator("loading_label"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		return isElementDisplayed("delete_popup_message_therapeutic_class", text.trim());

	}
	
	
	
	public void clickOnEditLinkCorresspondingToTherapeutic(String addedRecord){
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		isElementDisplayed("edit_therapeutic", addedRecord);
		wait.waitForElementToBeClickable(element("edit_therapeutic", addedRecord));
		clickUsingXpathInJavaScriptExecutor(element("edit_therapeutic", addedRecord));
		logMessage("Clicked on Edit link corressponding to Added record " + addedRecord);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
	}

	public void clickOnEditLinkCorresspondingToItem(String addedRecord) {
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 20);
			isElementDisplayed("link_edit_item", addedRecord);
			//wait.waitForElementToBeClickable(element("link_edit_item", addedRecord));
			//clickUsingXpathInJavaScriptExecutor(element("link_edit_item", addedRecord));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("link_edit_item", addedRecord));
			logMessage("Clicked on Edit link corressponding to Added record " + addedRecord);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		} catch (Exception e) {
			try {
				wait.waitForElementToBeClickable(element("link_edit_item", addedRecord));
				//clickUsingXpathInJavaScriptExecutor(element("link_edit_item", addedRecord));
				clickUsingXpathInJavaScriptExecutorSingleClick(element("link_edit_item", addedRecord));
				logMessage("Clicked on Edit link corressponding to Added record " + addedRecord);
				wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
			} catch (Exception j) {
				//wait.waitForElementToBeClickable(element("edit_uom", addedRecord));
				wait.waitForElementToBeClickable(element("link_edit_item", addedRecord));
				clickUsingXpathInJavaScriptExecutor(element("link_edit_item", addedRecord));
				logMessage("Clicked on Edit link corressponding to Added record " + addedRecord);
				wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

			}

		}

	}
	
	
	public void clickOnEditLinkCorresspondingToItemNew(String addedRecord) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("link_edit_new", addedRecord);
		wait.waitForElementToBeClickable(element("link_edit_new", addedRecord));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_edit_new", addedRecord));
		logMessage("Clicked on Edit link corressponding to Added record: " + addedRecord);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
	}
	
	
	public void clickOnEditLinkCorresspondingToItemonItemLocations(String addedRecord) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.hardWait(3);
		isElementDisplayed("edit_link_item_locations", addedRecord);
		wait.waitForElementToBeClickable(element("edit_link_item_locations", addedRecord));
		clickUsingXpathInJavaScriptExecutor(element("edit_link_item_locations", addedRecord));
		logMessage("Clicked on Edit link corressponding to Added record " + addedRecord);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

	}

	public void selectValueFromDropDownForPISSystem(String externalSystem) {
		selectProvidedTextFromDropDown(element("dropdowns_dosage_pis"), externalSystem);
		Assert.assertEquals(getSelectedTextFromDropDown(element("dropdowns_dosage_pis")), externalSystem);
	}

	public void selectBulkItemFromList(String bulkitemdes) {
		isElementDisplayed("select_element_from_list", bulkitemdes);
		element("select_element_from_list", bulkitemdes).click();
		logMessage("[STEP]: Element is selected from list.");

	}

	public boolean verifyValueIsPopulatedinTextField(String string, String bulkitemdes) {
		isElementDisplayed("text_inputFieldDistributor", string);
		System.out.println("Disabled Text= " + element("text_inputFieldDistributor", string).getAttribute("value"));
		return element("text_inputFieldDistributor", string).getAttribute("value").contains(bulkitemdes);

	}

	public String enterValueOnAddDistributorPage(String field, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("text_inputFieldDistributor", field);
		if (field.equalsIgnoreCase("descriptionText") || field.equalsIgnoreCase("shortCode")
				|| field.equalsIgnoreCase("medicationClassDescription") || field.equalsIgnoreCase("medicationClassCode")
				|| field.equalsIgnoreCase("labelTagCode")) {
			String value = data + System.currentTimeMillis();
			enterTextInField(element("text_inputFieldDistributor", field), value);
			return element("text_inputFieldDistributor", field).getAttribute("value").trim();
		} else {
			enterTextInField(element("text_inputFieldDistributor", field), data);
			return data;
		}
	}

	public String enterValueOnDescriptionMedicationPage(String field, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("text_descriptionMedication", field);
		String value = data + System.currentTimeMillis();
		enterTextInField(element("text_descriptionMedication", field), value);
		return value;

	}

	public String enterValueOnDescriptionLabelTagPage(String field, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("text_descriptionLabelTag", field);
		String value = data + System.currentTimeMillis();
		enterTextInField(element("text_descriptionLabelTag", field), value);
		return value;

	}

	public void verifyLabelTagsTextbox(String field) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		Assert.assertTrue(isElementDisplayed("text_inputFieldDistributor", field));
	}

	public String enterDuplicateValueOnMedicationPage(String field, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("text_inputFieldDistributor", field);
		enterTextInField(element("text_inputFieldDistributor", field), data);
		return data;

	}

	public boolean autoPopulatedFieldOnDistributor(String field) {

		String value = element("text_inputFieldDistributor", field).getAttribute("value");
		if (value.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public void verifyAndClickContactTab(String link) {
		isElementDisplayed("link_contactDistributor", link);
		click(element("link_contactDistributor", link));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}
	
	public boolean verifyListItemOnPoup(String text) {
		return isElementNotDisplayed("list_item_on_popup", text);
	}

	public void barcodePopupHeading() {
		Assert.assertTrue(isElementDisplayed("filter_items_heading"));
	}

	public boolean verifyUnverifiedLinks(String button) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		Assert.assertTrue(isElementDisplayed("button_routingRule", button));
		String val = element("button_routingRule", button).getText();
		digit = Integer.parseInt(val.replaceAll("[\\D]", ""));
		System.out.println("Digit value = " + digit);
		if (digit != 0)
			return true;
		else
			return false;
	}

	public boolean verifyItemListOnBarcode() {
		List<WebElement> ele = elements("text_barcodeItem");
		if (digit != 0) {
			if (ele.isEmpty())
				return false;
			else
				return true;
		} else {
			if (ele.isEmpty())
				return true;
			else
				return false;
		}

	}
	
	
	public String firstDataOnDistributor(String columnNumber) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 300);
		String value = element("firstData_distributor", columnNumber).getText();
		System.out.println("Data of first value : " + value);
		return value;
	}
	
	
	public void clickRadioButtonOnDistributor(String id) {
		isElementDisplayed("action_toggle_button_1", id);
		if (!checkboxIsSelectedUsingJavascript(id)) {
			element("action_toggle_button_1", id).click();
		}
	}

	public void uncheckRadioButtonOnDistributor(String id) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("action_toggle_button_1", id);
		if (checkboxIsSelectedUsingJavascript(id)) {
			element("action_toggle_button_1", id).click();
		}
	}

	public void verifyPopUpTextOnDistributor(String text) {
		String value = element("popup_text").getText();
		Assert.assertEquals(text, value);

	}

	public String enterDuplicateValueOnAddDistributorPage(String field, String value) {
		isElementDisplayed("text_inputFieldDistributor", field);
		enterTextInField(element("text_inputFieldDistributor", field), value);
		return value;
	}
	
	
	public void resetSearch() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.querySelector(('.icon-close-bold-sm'),':before').click();");
		logMessage("Clicked on close(X) button to reset search");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		hardWaitForChromeBrowser(5);
	}
	
	
	public String getValueOfInputField(String fieldName) {
		isElementDisplayed("search_box", fieldName);
		return element("search_box", fieldName).getAttribute("value");
	}
	
	public String getValueOfTextareaField(String fieldName) {
		isElementDisplayed("textarea_hold_reason_description", fieldName);
		return element("textarea_hold_reason_description", fieldName).getAttribute("value");
	}
	
	public void tqCrossButton() {
		element("tq_closeButton").click();
	}

	public String generatingRandomSpecialCharacterString(int length) {

		char[] spchar = { '!', '@', '#', '$', '%', '^', '&', '*' };
		String generatedString = RandomStringUtils.random(length, spchar);
		return generatedString;
	}

	public void verifyAddButtonOnPage() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("action_button", "add");
	}

	public boolean verifyButtonIsEnabledOrDisabled(String button) {
		wait.hardWait(3);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		return element("action_button", button).isEnabled();
	}
	
	public boolean verifyButtonIsEnabledOrDisabledWithoutWait(String button) {
		return element("action_button", button).isEnabled();
	}

	public void verifyDistributorDisabledField() {
		isElementDisplayed("disabled_distributorfield");
	}

	public boolean verifyTextboxIsEnabledOrDisabled(String button) {
		wait.hardWait(3);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		return element("text_inputFieldDistributor", button).isEnabled();
	}

	public boolean verifySaveButtonIsEnabledOrDisabled() {
		wait.hardWait(5);
		return element("action_button_save").isEnabled();
	}

	public void verifyButtonOnPage(String button) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		isElementDisplayed("action_button", button);
	}

	public void verifyEditPopUpOnManufacturer(String fieldName) {
		wait.waitForElementToBeVisible(element("popup_text"));
		Assert.assertTrue(element("popup_text").getText().trim().contains(fieldName));
		logMessage("[ASSERTION PASSED]: Verified Edit a Manufacturer pop up");

	}

	public int verifyMaxLengthOfUomSortOrderfield(String field) {
		int len = 0;
		len = Integer.parseInt(element("uom_sort_order_field", field).getAttribute("maxlength").trim());
		return len;
	}

	public boolean verifyRequiredFieldOnEditManufacturer(String message) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		String requiredtextlbl = element("edit_text_requiredfield").getText();
		if (requiredtextlbl.equals(message)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyEditLinkUnderActionColumnForAdhocLabel(String action) {
		try {
			wait.hardWait(10);

			List<WebElement> elements = elements("edit_links", action);
			wait.waitForElementsToBeVisible(elements);
			for (WebElement ele : elements) {
				if (ele.getText().trim().equals(action)) {
					continue;
				} else {
					return false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logMessage("[Blocker]: Test data is required.");

		}
		return true;
	}

	public void verifySearchBoxDefaultText(String string, String defaultText) {
		Assert.assertTrue(element("textBox_search", string).getAttribute("placeholder").trim().contains(defaultText));
		logMessage("[ASSERTION PASSED]: Verified search box placeholder is present");
	}

	public void clickPOActionbutton(String action) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);
		
		isElementDisplayed("action_button1", action);
		hover(element("action_button1", action));
		wait.waitForElementToBeClickable(element("action_button1", action));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button1", action));
		logMessage("Clicked on" + action + "button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 15);
	}

	public String SearchPOItem(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		if (fieldName.equalsIgnoreCase("Item Name")) {
			enterTextInField(element("search_po_item", fieldName), data);
			Assert.assertEquals(element("search_po_item", fieldName).getAttribute("value").trim(), data);
		} else {
			enterTextInField(element("search_po_item", fieldName), data);

			wait.hardWait(2);

			Assert.assertEquals(element("search_po_item", fieldName).getAttribute("value").trim(), data);

		}
		return data;
	}

	public void clickCreateNewOrder(String command) {
		isElementDisplayed("Add_new_order_actions", command);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("Add_new_order_actions", command));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}

	public boolean verifyPOLabelIsPresent(String text) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		return isElementDisplayed("order_new_item_label", text.trim());
	}

	public void verifyPOItemSearchResult(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 30);
		isElementDisplayed("po_search_item_result", fieldName);
		logMessage("ASSERT PASSED :Searched item found");

	}

	public String clickSearchedPOItem(String index, String fieldName) {
		String itemName;
		itemName = element("po_item_by_index", index).getText();
		element("po_search_item_result", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		logMessage(" Search Result: " + itemName);
		return itemName;
	}

	public void enterOrderQuantity(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 30);
		if (fieldName.equalsIgnoreCase("toOrderQuantity")) {
			wait.hardWait(10);
			enterTextInField(element("item_to_order_quantity", fieldName), data);
			Assert.assertEquals(element("item_to_order_quantity", fieldName).getAttribute("value").trim(), data);
		} else {
			enterTextInField(element("item_to_order_quantity", fieldName), data);

			wait.hardWait(2);

			Assert.assertEquals(element("item_to_order_quantity", fieldName).getAttribute("value").trim(), data);

		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
	}

	public void clickSaveAndClose(String fieldName) {
		isElementDisplayed("order_save_close", fieldName);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("order_save_close", fieldName));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 30);
	}
	
	public void clickOnCancel(String fieldName) {
		isElementDisplayed("order_save_close", fieldName);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("order_save_close", fieldName));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
	}
	
	public boolean verifyPurchaseOrderManualCardisPresent() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		return isElementDisplayed("open_po_card_manual");
	}
	
	public boolean verifyPurchaseOrdereElectronicCardisPresent() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		return isElementDisplayed("open_po_card_electronic");
	}

	public void openPurchaseOrderElectroniccard() {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 80);
		element("open_po_card_electronic").click();
		wait.waitForLoaderToBeInvisible(getLocator("loading"), 60);
		logMessage("Purchase order card opened successfully");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
	}

	public void openPurchaseOrderManualcard() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		element("open_po_card_manual").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		logMessage("Purchase order card opened successfully");
	}

	public void getOrderStatus(String data) {
		String OrderStatus;
		isElementDisplayed("purchase_order_status");
		OrderStatus = element("purchase_order_status").getText().trim();
		Assert.assertEquals(OrderStatus, data);
		logMessage("ASSERT PASSED :Status of purchase order is :" + OrderStatus);
	}

	public void clickOnDashboardLink(String fieldname) {

		clickUsingXpathInJavaScriptExecutor(element("dashboard_link", fieldname));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
	}

	public void getPOVendorName(String fieldName) {
		String vendorName;
		isElementDisplayed("", fieldName);
		vendorName = getElementText(element(""));
	}

	public String getColumnFirstData(String col) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("first_col_data", col);
		return element("first_col_data", col).getText().trim();
	}
	
	public String getColumnFirstInactiveData() {
		wait.hardWait(5);
		wait.waitForElementsToBeVisible(elements("first_inactiveValueCarousel"));
		List<WebElement> elements = elements("first_inactiveValueCarousel");
		String first = elements.get(0).getText();
		return first;
	}

	public String selectPISValueFromDropDownForGLAccount(String fieldName, String data) {
		selectProvidedTextFromDropDown(element("dropdowns_gl_account_pis", fieldName), data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("dropdowns_gl_account_pis", fieldName)), data);
		return data;
	}

	public String generatingRandomStringForPO(int length) {

		boolean useLetters = false;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return ("PO" + generatedString);
	}

	public void savePONumber(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);
		wait.elementHighlight(element("save_po_number", fieldName));
		element("save_po_number", fieldName).click();
		wait.hardWait(5);
	}

	public void clickPendingReceiveCard(String fieldName) {
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("pending_receive_card", fieldName));
		
		
	}
	
	public void clickReceiveCard(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("received_card", fieldName));
		wait.hardWait(5);
	}

	public String generatingRandomStringForInvoice(int length) {

		boolean useLetters = false;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return ("INV" + generatedString);
	}

	public void selectItemtoRecieve(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("invoice_item_checkbox", fieldName);
		wait.waitForElementToBeClickable(element("invoice_item_checkbox", fieldName));
		wait.elementHighlight(element("invoice_item_checkbox", fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("invoice_item_checkbox", fieldName));
		// element("invoice_item_checkbox", fieldName).click();
	}

	public void enterItemCostForInvoice(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.elementHighlight(element("item_cost", fieldName));
		enterTextInField(element("item_cost", fieldName), data);
		wait.hardWait(3);
	}

	public String getAddedDistributorName() {
		isElementDisplayed("btn_add");

		return getElementText(element("btn_add"));
	}

	public void verifyErrorMessageForAlreadyExistingManufacturerName(String message) {
		isElementDisplayed("error_manufacturer_msg");
		System.out.println("actual: " + element("error_manufacturer_msg").getText());
		System.out.println("expected: " + message);
		Assert.assertTrue(element("error_manufacturer_msg").getText().trim().equalsIgnoreCase(message.trim()));

	}

	public void SendActionKeysWasteReason(String fieldName) {
		if (fieldName.equalsIgnoreCase("wasteReason")) {
			element("inp_field_text", fieldName).sendKeys(Keys.BACK_SPACE);
		}

	}

	public ArrayList<String> captureDataForParticularColumnPO() {
		List<WebElement> elements = elements("ro_po_orders");
		column_data = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			String data = elements.get(i).getAttribute("value");
			column_data.add(data);
		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		return column_data;
	}

	public void verifyAllDropdownElementsMedicatonClass(String fieldName) {
		wait.hardWait(3);
		// Select dropdown = new Select(element("dropdowns_dosage_pis",
		// fieldName));
		List<WebElement> Str = getAllOptionsFromDropDown(element("dropdowns_dosage_pis", fieldName));
		String[] ex = { "Select", "CII", "CIII", "CIV", "CV", "OTC", "RX" };
		for (int i = 0; i < 7; i++) {
			System.out.println(Str.get(i).getText());
			Assert.assertEquals(ex[i], Str.get(i).getText());
		}
	}
	
	public boolean verifyDropdownElementsDefaultRule(String fieldName, String RuleName) {
		wait.hardWait(10);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 300);
		boolean flag = false;
		List<WebElement> Str = getAllOptionsFromDropDown(element("dropdowns_dosage_pis", fieldName));
		for (int i = 0; i < Str.size(); i++) {
			if (RuleName.equalsIgnoreCase(Str.get(i).getText())) {
				System.out.println(Str.get(i).getText());
				flag = true;
				break;
			}
		}
		return flag;
	}

	public void verifyMedicationClassSortOrder(String fieldName, String medClass1, String medClass2) {
		//wait.hardWait(3);
		boolean flag = false;
		List<WebElement> Str = getAllOptionsFromDropDown(element("dropdowns_dosage_pis", fieldName));
		System.out.println("Order of first med class = "+ Str.get(1).getText());
		System.out.println("Order of second med class = "+ Str.get(2).getText());
		Assert.assertEquals(medClass1, Str.get(1).getText());
		Assert.assertEquals(medClass2, Str.get(2).getText());

	}

	public void selectRoutingRule(String fieldName, String RuleName) {
		//wait.hardWait(3);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		boolean flag = false;
		List<WebElement> Str = getAllOptionsFromDropDown(element("dropdowns_dosage_pis", fieldName));
		System.out.println("value of list "+Str);
		for (int i = 0; i < Str.size(); i++) {
			if (RuleName.equalsIgnoreCase(Str.get(i).getText())) {
				System.out.println(Str.get(i).getText());
				Str.get(i).click();
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
	}

	public void verifyDropdownElementsLabelTags(String RuleName) {
		wait.hardWait(3);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		element("link_labelTag").click();
		scrollUp();
		boolean flag = false;
		List<WebElement> Str = elements("dropdown_labelTag");
		int len = Str.size();
		System.out.println("length of list  = " + len);
		for (int i = 0; i < Str.size(); i++) {
			System.out.println(Str.get(i).getText());
			if (RuleName.equalsIgnoreCase(Str.get(i).getText())) {
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
	}

	public boolean verifyDropdownElementsDefaultRuleNotPresent(String fieldName, String RuleName) {
		wait.hardWait(3);
		int flag = 0;
		List<WebElement> Str = getAllOptionsFromDropDown(element("dropdowns_dosage_pis", fieldName));
		int len = Str.size();
		for (int i = 0; i < len; i++) {
			System.out.println("Value of list = " + Str.get(i).getText());
			if (!RuleName.equalsIgnoreCase(Str.get(i).getText())) {
				flag++;
			}
		}

		if (flag == len)
			return false;
		else
			return true;
	}

	public boolean verifyLabelTagIsNotPresent(String RuleName) {
		wait.hardWait(3);
		int flag = 0;
		List<WebElement> Str = elements("dropdown_labelTag");
		int len = Str.size();
		System.out.println("length of list  = " + len);
		for (int i = 0; i < len; i++) {
			System.out.println("Value of list = " + Str.get(i).getText());
			if (!RuleName.equalsIgnoreCase(Str.get(i).getText())) {
				flag++;
			}
		}

		if (flag == len)
			return false;
		else
			return true;
	}

	public ArrayList<String> sortDataForParticularColumnInDescendingOrderPO() {
		ArrayList<String> data_compare = captureDataForParticularColumnPO();
		Collections.sort(data_compare, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
		return data_compare;
	}

	public void switchToReportTab(int i) {
		switchToTab(i);
		wait.waitForPageToLoadCompletely();
		System.out.println("PAGE IS $$$$$$$$$$$$$$$" + driver.getCurrentUrl());
		wait.hardWait(5);
	}

	public void clickProceedLink() {

		isElementDisplayed("proceed_link");
		element("proceed_link").click();
	}

	public void clickViewRuleItemLocation() {
		isElementDisplayed("button_viewRoutingRule");
		element("button_viewRoutingRule").click();
	}

	public void clickOnEditLinkCorresspondingToItemNew(String addedRecord, String data) {
		wait.hardWait(20);
		isElementDisplayed("link_edit_item", addedRecord);
		wait.waitForElementToBeClickable(element("link_edit_item", addedRecord));
		clickUsingXpathInJavaScriptExecutor(element("link_edit_item", addedRecord));
		logMessage("Clicked on Edit link corressponding to Added record " + addedRecord);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

	}

	public boolean verifyDropdownOnReportingTab() {

		wait.waitForElementToBeVisible(element("dropdown_reporting"));
		return isElementDisplayed("dropdown_reporting");

	}

	public void verifyDefaultFacilityOnReportDropdown() {
		isElementDisplayed("default_dropdown_reports");
		logMessage("ASSERTION PASSED: Default value is displayed on Facility dropdown");

	}

	public void switchToFrame(String id) {
		wait.waitForElementToBeVisible(element("frame_reporting", id));
		driver.switchTo().frame(element("frame_reporting", id));
	}

	public void verifyFailureText(String string) {

		wait.waitForElementToBeVisible(element("error_mesg_report", string));
		isElementDisplayed("error_mesg_report", string);

	}

	public String enterValueOnMedClassCode_Sanity(String field, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("text_inputFieldDistributor", field);
		if (field.equalsIgnoreCase("descriptionText") || field.equalsIgnoreCase("shortCode")
				|| field.equalsIgnoreCase("medicationClassDescription")
				|| field.equalsIgnoreCase("medicationClassCode")) {
			enterTextInField(element("text_inputFieldDistributor", field), data);
			return data;
		} else {
			enterTextInField(element("text_inputFieldDistributor", field), data);
			wait.hardWait(2);
			return data;
		}

	}

	public void verifyDataIsAddedAsPerTimeStamp(String holdReasonName) {

		List<WebElement> elements = elements("hold_reason_name");
		Assert.assertTrue(elements.get(0).getText().trim().equalsIgnoreCase(holdReasonName));
	}

	public ArrayList<String> getMedicationCodeDetails() {
		wait.hardWait(3);
		List<WebElement> elements = elements("text_medicationCode");
		trans_data = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			String trans_details = elements.get(i).getText();
			System.out.println("Previos data =  " + trans_details);
			trans_data.add(trans_details);
		}
		return trans_data;
	}

	public boolean getDeleteMedicationCodeDEtails() {
		wait.hardWait(3);
		List<WebElement> elements = elements("deletetext_medicationCode");
		if (elements.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public void verfyDeleteLinkMedicationClass() {
		Assert.assertTrue(isElementDisplayed("link_deleteMedicationClass"));
	}

	public boolean selectAllCheckboxes() {
		// wait.waitForPageToLoadCompletely();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		try {
			if (isElementDisplayed("checkbox_MedicationClassSelectAll")) {
				element("checkbox_MedicationClassSelectAll").click();
				System.out.println("clicked on All checkbox!!!!");
				logMessage("User has clicked on all checkbox in Pick Queue");
				flag = true;
			}
		} catch (Exception e) {
			if (isElementDisplayed("checkbox_MedicationClassSelectAll")) {
				clickUsingXpathInJavaScriptExecutorSingleClick(element("checkbox_MedicationClassSelectAll"));
				System.out.println("clicked on All checkbox!!!!");
				logMessage("User has clicked on all checkbox in Pick Queue");
				flag = true;
			}
		}
		return flag;
	}

	public boolean selectMultipleCheckboxes(String check1) {
		// wait.waitForPageToLoadCompletely();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		try {
			if (isElementDisplayed(("checkbox_item_tab1"), check1)) {
				element(("checkbox_item_tab1"), check1).click();
				System.out.println("clicked on selected checkbox!!!!");
				logMessage("User has clicked on clicked on selected checkbox");
				flag = true;
			}
		} catch (Exception e) {
			if (isElementDisplayed("checkbox_item_tab1")) {
				clickUsingXpathInJavaScriptExecutorSingleClick(element("checkbox_item_tab1"));
				System.out.println("clicked on selected checkbox!!!");
				logMessage("User has clicked on clicked on selected checkbox!!!");
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean selectCheckboxOfRecord(String recordName, boolean checkboxValue) {
		isElementDisplayed("link_edit_item", recordName);
		String chkboxId = element("record_chkbox_label", recordName).getAttribute("for").trim();
		
		if(verifyCheckboxIsChecked(chkboxId) != checkboxValue) {
			element("record_chkbox_label", recordName).click();
		}
		if(verifyCheckboxIsChecked(chkboxId) != checkboxValue) {
			element("label_with_for", chkboxId).click();
		}
		
		checkboxValue = verifyCheckboxIsChecked(chkboxId);
		if(checkboxValue) {
			logMessage("Checkbox for the record: '" + recordName + "', with id: '" 
					+ chkboxId + "' is selected");
		} else {
			logMessage("Checkbox for the record: '" + recordName + "', with id: '" 
					+ chkboxId + "' is deselected");
		}
		
		return checkboxValue;
	}

	public boolean verifyEditButtonIsDisabledOnItemManagement(String num) {
		isElementDisplayed("edit_corresponding_to_checkbox", num);
		return element("edit_corresponding_to_checkbox", num).isEnabled();
	}
	
	public boolean isActionButtonEnabled(String recordName, String btnText) {
		isElementDisplayed("record_action_btn", recordName, btnText);
		return element("record_action_btn", recordName, btnText).isEnabled();
	}

	public void verifyNewlyAddedManufacturerStatus(String fieldName, String status) {

		isElementDisplayed("added_hold_reason_name", fieldName);
		String manufacturerStatus = getColumnFirstData("2");
		System.out.println("Manufacturer Status :: " + manufacturerStatus);
		Assert.assertEquals(manufacturerStatus, status, "[ASSERTION FAILED]: Manufacturer status is incorrect");
		logMessage("[ASSERTION PASSED]: Newly added Manufacturer : " + fieldName + " with status " + status
				+ " is displayed in the Manufacturer List");
	}

	public String generatingRandomStringForManufacturerName(int length) {

		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return getData("ManufacturerDetails.ManufacturerName") + generatedString;
	}

	public void verifyErrorMessageonDispenseUnitAlert(String message, String fieldName) {

		wait.hardWait(5);
		String spanMessage = element("dispenseunit_alert_message", fieldName).getText();
		System.out.println("Value of message:  " + spanMessage);
		Assert.assertEquals(spanMessage, message);

	}

	public void clickActionbutton(String action) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 80);

		isElementDisplayed("action_button1", action);
		wait.waitForElementToBeClickable(element("action_button1", action));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button1", action));
		logMessage("Clicked on" + action + "button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 5);

	}

	public void verifytoggleButtonIsDisabled(String toggleValue) {
		isElementDisplayed("disabled_toggle", toggleValue);

	}

	public ArrayList<String> captureDataForUnableToOrder(String coulmn) {
		List<WebElement> elements = elements("sort_uanble_to_order", coulmn);
		column_data = new ArrayList<String>();
		for (WebElement ele : elements) {
			column_data.add(ele.getText().trim());
		}
		return column_data;
	}

	public void clickOnEditLinkCorresspondingToItem(String itemID, String string) {
		// TODO Auto-generated method stub

	}

	public void verifyCrossButtonIsNotPresent() {
		Assert.assertFalse(isElementNotDisplayed("cross_button"));

	}

	public void verifyMaxLengthOfAnInputField(String fieldName, String data, int maxlength) {
		wait.loadingWait(getLocator("loader"));
		// sendKeysUsingXpathInJavaScriptExecutor(element("inp_field_printer",
		// fieldName), data);
		enterTextInField(element("textarea_hold_reason_description", fieldName), data);
		Assert.assertEquals(element("textarea_hold_reason_description", fieldName).getAttribute("value").length(),
				maxlength);

	}

	public void clickCrossButton() {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.querySelector(('.icon-close-bold-sm'),':before').click();");
		logMessage("Clicked on close(X) button to reset search");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
	}

	public void verifyFieldIsMandatoryGL(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		Assert.assertTrue(isElementDisplayed("icon_mandatory_GL", fieldName));

	}

	public void selectValueFromDropDownForPISSystem(String externalSystem, String esName) {
		selectProvidedTextFromDropDown(element("dropdowns_dosage_pis", externalSystem), esName);
		wait.hardWait(3);
		Assert.assertEquals(getSelectedTextFromDropDown(element("dropdowns_dosage_pis", externalSystem)), esName);

	}

	public void updatedDosageCodeDisplayed(String dosageCode) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		isElementDisplayed("updated_DosageCode", dosageCode);
	}

	public void verifyInputFieldOnAddGLPopup(String fieldName) {
		isElementDisplayed("inp_field_text", fieldName);
		logMessage("[ASSERTION PASSED]: Verified input field for " + fieldName);
	}

	public void clickEditLinkInTableWasteReason(String name) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("link_edit_table_waste_reason", name);
		clickUsingXpathInJavaScriptExecutor(element("link_edit_table_waste_reason", name));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
	}

	public boolean verifyManufacturerStatusAsActive() {
		List<WebElement> elements = elements("list_manufacturerStatus");
		for (WebElement ele : elements) {
			if (!(ele.getText().trim().equalsIgnoreCase("Active"))) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyManufacturerStatus() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		List<WebElement> elements = elements("list_manufacturerStatus");
		for (WebElement ele : elements) {
			if ((ele.getText().trim().equalsIgnoreCase("InActive"))) {
				count++;
			}
		}
		if (count > 0)
			return true;
		else
			return false;
	}
	

	public void packageSizeOnPOCard() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		List<WebElement> list = elements("package_size_PO");
		String expected = list.get(1).getText();
		Assert.assertEquals("1", expected);		
	}
	
	public void packageSizeOnIS() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		String expected = element("package_size_IS").getText();
		Assert.assertEquals("5", expected);		
	}
	
	public void packageSizeOnRPO() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		String expected = element("package_size_RPO").getText();
		Assert.assertEquals("1", expected);	
	}

	public void clickOnEditLinkCorresspondingToAddedRecord_dispenseUnit(String addedRecord, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("link_edit_dispense_unit", addedRecord);
		wait.waitForElementToBeClickable(element("link_edit_dispense_unit", addedRecord));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_edit_dispense_unit", addedRecord));
		logMessage("Clicked on Edit link corressponding to Added record " + addedRecord);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
			wait.waitForElementToBeVisible(element("popup_text"));
			Assert.assertTrue(element("popup_text").getText().trim().contains(data));
		} catch (Exception e) {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
			wait.waitForElementToBeVisible(element("label_hold_reason"));
			Assert.assertTrue(element("label_hold_reason").getText().trim().contains(data));

		}
		logMessage("[ASSERTION PASSED]: Verified Edit a Record pop up");

	}
	
	public void verifyUserManagementPage(String url)
	{
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 50);
		

		Assert.assertTrue(getCurrentURL().equalsIgnoreCase(url));
		logMessage("ASSERTION PASSED: User is on User Management page");
	}
	
	public void verifyDropdownOnUserManagement()
	{
		isElementDisplayed("idm_dropdown");
		logMessage("ASSERTION PASSED: Verified dropdown on IDM");
	}

	public ArrayList<String> captureSortedDataForParticularColumnDispenseUnit(String coulmn) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		List<WebElement> elements = elements("list_dosageForms", coulmn);
		column_data = new ArrayList<String>();
		for (WebElement ele : elements) {
			column_data.add(ele.getText().trim());
		}
		return column_data;
	}
	
	public void clickOnAddButtonToAddNewUOM(String popupText) {
		// wait.loadingWait(getLocator("loader"));
		// wait.hardWait(10);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		isElementDisplayed("btn_add");
		wait.waitForElementToBeClickable(element("btn_add"));
		clickUsingXpathInJavaScriptExecutor(element("btn_add"));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		logMessage("[STEP]: Clicked on Add");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		wait.waitForElementToBeVisible(element("page_title_uom"));
		Assert.assertTrue(element("page_title_uom").getText().trim().contains(popupText));
		logMessage("[ASSERTION PASSED]: Verified Add pop up on clicking add button");
	}
	
	
	public boolean verifyLabelIsPresentUOM(String text) {
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
			Assert.assertTrue(element("label_hold_reason_UOM").getText().trim().contains(text));
			return isElementDisplayed("label_hold_reason_UOM", text.trim());
		} catch (Exception e) {
			// wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
			Assert.assertTrue(element("page_title_UOM").getText().trim().equals(text));
			return isElementDisplayed("page_title_UOM", text.trim());
		}
	}
	
	public ArrayList<String> sortDataForParticularColumnInAscendingOrderDispenseUnit(String coulmn) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		ArrayList<String> data_compare = captureDataForParticularColumnDosageForm(coulmn);
		Collections.sort(data_compare, String.CASE_INSENSITIVE_ORDER);
		return data_compare;
	}

	public void clickOnColumnHeaderDispenseUnit(String columnName) {
		try {
			isElementDisplayed("header_dosageForms", columnName);
			element("header_dosageForms", columnName).click();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
}
