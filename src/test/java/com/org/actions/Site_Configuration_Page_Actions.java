package com.org.actions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.org.automation.getpageobjects.GetPage;
import com.org.automation.utils.ConfigPropertyReader;

public class Site_Configuration_Page_Actions extends GetPage {
	WebDriver driver;
	static String pagename = "Site_Configuration_Page";
	private boolean flag = false;
	private ArrayList<String> column_data, dest_item;
	int count, count1 = 0;
	String selected_item_details;
	String firstItem;
	int total_records, digit;

	public Site_Configuration_Page_Actions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public void clickOnAddButtonToAddDestination1() {
		// isElementDisplayed("btn_add");
		// wait.loadingWait(driver, element("loader"));
		wait.hardWait(12);
		wait.waitForElementToBeClickable(element("btn_add"));
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("btn_add")).click().build();
		// .keyDown(element("active_toggle_label"),
		// Keys.CONTROL).sendKeys(Keys.SPACE)
		seriesOfAction.perform();
		logMessage("[STEP]: Clicked on Add button");
		wait.waitForElementToBeVisible(element("popup_add_schedule"));
		Assert.assertTrue(element("popup_add_schedule").getText().trim().contains("Add Destination"));
		logMessage("[ASSERTION PASSED]: Verified Add Destination header on clicking add button");

	}

	public void clickCheckboxOfNewRecord(String string) {
		/*
		 * String attribute=element("") ele.addAll(elements("txn_priority_checkboxes"));
		 */
	}
	
	
	public void logMessageLocal(String msg) {
		logMessage(msg);
	}
	
	
	public void verifyErrorMessageForAlreadyExistingRecord(String message) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		isElementDisplayed("error_schedule_msg");
		logMessage("Actual error message: '" + element("error_schedule_msg").getText().trim() + "'");
		logMessage("Expected error message: '" + message + "'");
		Assert.assertTrue(element("error_schedule_msg").getText().trim().equalsIgnoreCase(message.trim()));
	}

	public void deselectDays() {
		for (WebElement ele : elements("schedule_day_all_selected")) {
			ele.click();
		}

//		for (WebElement ele : elements("schedule_day_all")) {
//			if (ele.isSelected()) {
//				continue;
//			} else {
//				ele.click();
//			}
//		}
	}

	/*
	 * public boolean selectCheckBoxOfNewlyCreatedTransactionPriority(String
	 * fieldName){ selectCheckbox(fieldName, b); return flag; }
	 */

	public boolean verifyPrinterStatus() {
		List<WebElement> elements = elements("list_printerStatus");
		for (WebElement ele : elements) {
			if (!(ele.getText().trim().equalsIgnoreCase("Active"))) {
				return false;
			}
		}
		return true;
	}

	public void verifyUserIsAbleToSelectToggleButton(String toggle, boolean b) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		if (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("edge")) {
			isElementDisplayed("active_toggle_label", toggle);
			if (b) {
				if (verifyToggleButtonIsActiveOrNot(toggle).contains("true")) {
					Assert.assertTrue(verifyToggleButtonIsActiveOrNot(toggle).contains("true"));
				} else {
					try {
						element("active_toggle_label", toggle).click();
					} catch (Exception e) {
						element("active_toggle_label", toggle).click();
					}

					Assert.assertTrue(verifyToggleButtonIsActiveOrNot(toggle).contains("true"));
				}
			} else {
				if (verifyToggleButtonIsActiveOrNot(toggle).contains("false")) {
					Assert.assertTrue(verifyToggleButtonIsActiveOrNot(toggle).contains("false"));
				} else {
					try {
						element("active_toggle_label", toggle).click();
					} catch (Exception e) {
						element("active_toggle_label", toggle).click();
					}
					Assert.assertTrue(verifyToggleButtonIsActiveOrNot(toggle).contains("false"));
				}
			}
		} else {
			isElementDisplayed("active_toggle_label", toggle);
			if (b) {
				if (verifyToggleButtonIsActiveOrNot(toggle).contains("true")) {
					Assert.assertTrue(verifyToggleButtonIsActiveOrNot(toggle).contains("true"));
				} else {
					clickUsingXpathInJavaScriptExecutorSingleClick(element("active_toggle_label", toggle));
					Assert.assertTrue(verifyToggleButtonIsActiveOrNot(toggle).contains("true"));
				}
			} else {
				if (verifyToggleButtonIsActiveOrNot(toggle).contains("false")) {
					Assert.assertTrue(verifyToggleButtonIsActiveOrNot(toggle).contains("false"));
				} else {
					clickUsingXpathInJavaScriptExecutorSingleClick(element("active_toggle_label", toggle));
					Assert.assertTrue(verifyToggleButtonIsActiveOrNot(toggle).contains("false"));
				}
			}
		}
	}

	/*
	 * public boolean verifySortingForParticularColumn(ArrayList<String>
	 * previous_data, ArrayList<String> sorted_data) {
	 * 
	 * int flag = 0; int count = previous_data.size();
	 * 
	 * for (String item : previous_data) { for (String item1 : sorted_data) { if
	 * (item.equalsIgnoreCase(item1)) { flag++; } } } if (flag == count) {
	 * logMessage("[ASSERTION PASSED] : Coulmn data is sorted in provided order" );
	 * return true; } else {
	 * logMessage("[ASSERTION FAILED] : Coulmn data is not sorted in provided order"
	 * ); return false; }
	 * 
	 * }
	 */

	public boolean verifyColumnHeaders(List<String> sortColumns) {
		int count = 0;
		for (String sortColumn : sortColumns) {
			isElementDisplayed("list_columnHeaders", sortColumn);
			logMessage("Column - '" + sortColumn + "' is displayed");
			count++;
		}
		if (count == sortColumns.size()) {
			return true;
		}
		return false;
	}

	public void verifyLinkHeader(List<String> sortColumns) {
		for (String sortColumn : sortColumns) {
			isElementDisplayed("list_link_text", sortColumn);
			logMessage("Items " + sortColumn + " are displayed");
		}
	}

	public void verifyButtons(List<String> Buttons) {
		for (String Button : Buttons) {
			isElementDisplayed("link_Buttons", Button);
			logMessage("Buttons are getting displayed");
		}
	}

	public boolean verifyPrinterActiveInactiveStatus() {
		List<WebElement> elements = elements("list_printerStatus");
		for (WebElement ele : elements) {
			if (!(ele.getText().trim().equalsIgnoreCase("Active")
					|| ele.getText().trim().equalsIgnoreCase("Inactive"))) {
				return false;
			}
		}
		return true;
	}

	public void VerifyAndSearchText(String option) {
		wait.hardWait(5);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		isElementDisplayed("input_searchPrinter");
		enterTextInField(element("input_searchPrinter"), option);
	}

	public void VerifyAndSearchTextMedicationClass(String option) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		isElementDisplayed("input_medicationClass");
		enterTextInField(element("input_medicationClass"), option);
	}

	public void clickOnAddButtonToAddPrinter() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForElementToBeClickable(element("btn_add"));
		isElementDisplayed("btn_add");
		clickUsingXpathInJavaScriptExecutor(element("btn_add"));
		logMessage("[STEP]: Clicked on Add button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForElementToBeVisible(element("popup_for_add"));
		Assert.assertTrue(element("popup_for_add").getText().trim().contains("Add Printer"));
		logMessage("[ASSERTION PASSED]: Verified Add Printer pop up on clicking add button");
	}
	
	
	public void clickOnAddButtonToAddRoutingRule() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForElementToBeClickable(element("btn_add"));
		isElementDisplayed("btn_add");
		clickUsingXpathInJavaScriptExecutor(element("btn_add"));
		logMessage("[STEP]: Clicked on Add button");
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForElementToBeVisible(element("add_routing_rule_label"));
		Assert.assertTrue(element("add_routing_rule_label").getText().trim().contains("Add Routing Rule"));
		logMessage("[ASSERTION PASSED]: Verified Add Routing Rule Screen on clicking add button");
	}
	
	
	public void clickOnAddButtonToAddDestination() {
		wait.waitForLoaderToBeInvisible(getLocator("loading"), 140);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 140);
		isElementDisplayed("btn_add");
		wait.waitForElementToBeClickable(element("btn_add"));
		clickUsingXpathInJavaScriptExecutor(element("btn_add"));
		logMessage("[STEP]: Clicked on Add button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

		wait.waitForElementToBeVisible(element("page_heading_label"));
		Assert.assertTrue(element("page_heading_label").getText().trim().contains("Add Destination"));
		logMessage("[ASSERTION PASSED]: Verified Add Destination header on clicking add button");

	}

	public void verifyInputFieldOnAddNewPrinterPopup(String fieldName) {
		isElementDisplayed("inp_field_printer", fieldName);
		logMessage("[ASSERTION PASSED]: Verified input field for " + fieldName);
	}

	public void verifydescriptionInputField(String fieldName) {
		isElementDisplayed("textarea_field", fieldName);
		logMessage("[ASSERTION PASSED]: Verified input field for " + fieldName);
	}

	public void verifyDropDownFieldOnAddNewPrinterPopup(String fieldName) {
		isElementDisplayed("dropdown_printer", fieldName);
		logMessage("[ASSERTION PASSED]: Verified dropdown field for " + fieldName);
	}

	public void verifyDropDownOnAddNewExternalSystem(String fieldName) {
		isElementDisplayed("dropdowns_externalSystem", fieldName);
		logMessage("[ASSERTION PASSED]: Verified dropdown field for " + fieldName);
	}

	public void verifyCheckboxFieldOnAddNewPrinterPopup(String fieldName) {
		isElementDisplayed("parent_checkbox_printer", fieldName);
		Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName),
				"[ASSERTION FAILED]: Checkbox for " + fieldName + "is checked by default");
		logMessage("[ASSERTION PASSED]: Verified checkbox field for " + fieldName + " and is not checked");
	}

	public void verifyCheckboxFieldOnAddNewComputerPopup(String fieldName) {
		isElementDisplayed("chkbox_computer", fieldName);
		Assert.assertFalse(element("chkbox_computer", fieldName).isSelected(),
				"[ASSERTION FAILED]: Checkbox for " + fieldName + "is checked by default");
		logMessage("[ASSERTION PASSED]: Verified checkbox field for " + fieldName + " and is not checked");
	}

	public boolean verifyCheckboxIsEnabledOrDisabled(String id) {
		boolean flag = checkCheckboxIsEnabledOrDisabledUsingJavaScript(id);
		return flag;
	}

	public boolean verifyCheckboxIsEnabledOrDisabledRoutingRule(String index, String type) {
		String id = element("btn_radio_routing", index, type).getAttribute("id");
		return checkCheckboxIsEnabledOrDisabled(id);
	}

	public boolean verifyCheckboxIsEnabledOrDisabledMEdicationClass() {
		isElementDisplayed("checkbox_medicationClass");
		String id = element("checkbox_medicationClass").getAttribute("id");
		return checkCheckboxIsEnabledOrDisabled(id);
	}

	public boolean verifyAddPrinterPopupGetsClosedOnClickingCancelButton() {
		isElementDisplayed("btn_cancel");
		element("btn_cancel").click();
		logMessage("Clicked on cancel button on Add Printer pop up");
		return isElementNotDisplayed("popup_add_printer");
	}

	public void verifyDestinationPopupForClosingProcessOnClickingCancelButton(String header) throws Throwable {

		try {
			isElementDisplayed("btn_cancel");
			clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_cancel"));
			// element("btn_cancel").click();
			logMessage("Clicked on cancel button");
			isElementDisplayed("popup_add_printer");
			Assert.assertTrue(element("popup_add_printer").getText().trim().contains("Warning"));
			isElementDisplayed("confirm_delete_button");
			element("confirm_delete_button").click();
			logMessage("[STEP]: Confirmed cancel popup.");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		} catch (Exception e) {
			isElementDisplayed("confirm_delete_button");
			element("confirm_delete_button").click();
			logMessage("[STEP]: Confirmed cancel popup.");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		}
	}

	/*
	 * public void addPrinterDetails(String printer_detail,int i, String
	 * printerName){ String printerNameUpdated = printerName +
	 * System.currentTimeMillis();
	 * isElementDisplayed("inp_field_printer","serverPrinterName");
	 * element("inp_field_printer").sendKeys(printerNameUpdated);
	 * isElementDisplayed("inp_field_printer","printerName");
	 * element("inp_field_printer").sendKeys(printerNameUpdated);
	 * selectProvidedTextFromDropDown("", "QL-420 Plus");
	 * 
	 * }
	 */

	public void clickOnEditLinkCorresspondingToPrinterName(String printerName) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);
		isElementDisplayed("link_edit", printerName);
		clickUsingXpathInJavaScriptExecutor(element("link_edit", printerName));
		logMessage("Clicked on Edit link corressponding to printer " + printerName);
		wait.waitForElementToBeVisible(element("popup_add_printer"));
		Assert.assertTrue(element("popup_add_printer").getText().trim().contains(printerName));
		logMessage("[ASSERTION PASSED]: Verified Edit a Printer pop up");
	}

	public void verifyToggleOptionToActiveAndInactiveThePrinterOnEditPrinterPopUp() {
		isElementDisplayed("toggle_active");
		logMessage(
				"[ASSERTION PASSED]: Verfied toggle option to active and inactive the printer on edit printer pop up");
	}

	public int verifyMaxLengthOfAnInputField(String fieldName) {
		return Integer.parseInt(element("inp_field_printer", fieldName).getAttribute("maxlength").trim());
	}

	public int verifyMaxLengthOfAnInputFieldItemCode(String fieldName, String num) {

		return Integer.parseInt(element("preffered_distributor_info", fieldName, num).getAttribute("maxlength").trim());

	}

	public int verifyMaxLengthOfAnInputFieldOnTransactionPriorities(String fieldName) {
		return element("input_field", fieldName).getAttribute("value").length();
	}

	public boolean verifyMaxLengthOfAnInputFieldAddFacility() {
		int length = Integer.parseInt(element("textbox_accountNumberAddFacility").getAttribute("maxlength").trim());
		if (length > 20) {
			return false;
		}
		return true;
	}

	public boolean enterOnlyIntegerInAccountNumberField(String fieldName, String data) {
		String value = null;
		if (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("chrome")) {
			enterTextInField(element("textbox_accountNumberAddFacility", fieldName), data);
			value = element("textbox_accountNumberAddFacility", fieldName).getAttribute("value");
			System.out.println("Value of data " + value);
			if (value.matches("[0-9]+")) {
				return true;
			}
			return false;
		} else {
			element("textbox_accountNumberAddFacility").clear();
			enterTextInField(element("textbox_accountNumberAddFacility"), data);
			isElementDisplayed("error_class");
			element("textbox_accountNumberAddFacility").sendKeys("1234");
			value = element("textbox_accountNumberAddFacility").getAttribute("value");
			if (value.matches("[0-9]+")) {
				return true;
			}
			return false;
		}
	}

	public boolean enterOnlyIntegerInAccountNumberFieldAndVerifyLength(String fieldName, String data, int maxLength) {
		String value = null;
		if (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("chrome")) {
			enterTextInField(element("textbox_accountNumberAddFacility", fieldName), data);
			value = element("textbox_accountNumberAddFacility", fieldName).getAttribute("value");
			System.out.println("Value of data " + value);
			if (value.matches("[0-9]+")) {
				Assert.assertEquals(value.length(), maxLength);
				return true;
			}
			return false;
		} else {
			element("textbox_accountNumberAddFacility").clear();
			enterTextInField(element("textbox_accountNumberAddFacility"), data);
			isElementDisplayed("error_class");
			element("textbox_accountNumberAddFacility").sendKeys("1234");
			value = element("textbox_accountNumberAddFacility").getAttribute("value");
			if (value.matches("[0-9]+")) {
				Assert.assertEquals(value.length(), maxLength);
				return true;
			}
			return false;
		}
	}

	public String EnterValueInInputFieldOnAddNewPrinterPopup(String fieldName, String data) {
		enterTextInField(element("inp_field_printer", fieldName), data);
		if (fieldName.equalsIgnoreCase("labelBarcode")) {
			Assert.assertEquals(element("inp_field_printer", fieldName).getAttribute("value").trim().length(), 1000);
			logMessage("[ASSERTION PASSED]: Verfied labelBarcode field can contain only 1000 character value ");

		} else if (fieldName.equalsIgnoreCase("sortValue") && data.equalsIgnoreCase("10000")) {
			Assert.assertEquals(element("inp_field_printer", fieldName).getAttribute("value").trim().length(), 4);

		} else if (fieldName.equalsIgnoreCase("sortValue") && data.equalsIgnoreCase("10000")) {
			String s = element("inp_field_printer", fieldName).getAttribute("value").trim();
			System.out.println("Total Character=========" + s.length());
			System.out.println("Actual Value=========" + s);
			Assert.assertEquals(s, "1000");

			logMessage("[ASSERTION PASSED]: Verfied sortValue field can contain only 4 character value ");
		} else {
			if (element("inp_field_printer", fieldName).getAttribute("value").contains(data)) {
				Assert.assertTrue(element("inp_field_printer", fieldName).getAttribute("value").contains(data));
			} else {
				enterTextInField(element("inp_field_printer", fieldName), data);
			}
		}
		return data;

	}

	public String enterDataInInputField(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);
		isElementDisplayed("input_field", fieldName);
		enterTextInField(element("input_field", fieldName), data);
		Assert.assertTrue(element("input_field", fieldName).getAttribute("value").trim().equals(data));
		logMessage("[STEP]: Data entered in Input field '" + fieldName + "'");
		wait.hardWait(3);
		return data;
	}

	public String enterDataInInputFieldWithoutWait(String fieldName, String data) {

		isElementDisplayed("input_field", fieldName);
		enterTextInField(element("input_field", fieldName), data);
		Assert.assertTrue(element("input_field", fieldName).getAttribute("value").trim().equals(data));
		logMessage("[STEP]: Data entered in Input field.");
		return data;
	}

	public String enterDataInTextAreaField(String fieldName, String data) {
		isElementDisplayed("textarea_field", fieldName);
		enterTextInField(element("textarea_field", fieldName), data);
		Assert.assertTrue(element("textarea_field", fieldName).getAttribute("value").trim().equals(data));
		logMessage("[STEP]: Data entered in Input field.");
		wait.hardWait(3);

		return data;
	}

	public void enterDataInPriority() {
		isElementDisplayed("dropdown_labelPriority");
		element("dropdown_labelPriority").click();
		element("value_labelPriority").click();
		// Assert.assertTrue(element("input_field").getAttribute("value").trim().equals(data));
		// logMessage("[STEP]: Data entered in Input field.");
		wait.hardWait(3);
		// return data;

	}

	public void enterDataInPriorityNew(String priorityName) {
		isElementDisplayed("dropdown_labelPriority");
		element("dropdown_labelPriority").click();
		element("value_labelPriority_new", priorityName).click();
		// Assert.assertTrue(element("input_field").getAttribute("value").trim().equals(data));
		// logMessage("[STEP]: Data entered in Input field.");
		wait.hardWait(3);
		// return data;

	}

	public String selectValueForDropDown(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		try {
			isElementDisplayed("dropdown_printer", fieldName);
			wait.waitForElementToBeClickable(element("dropdown_printer", fieldName));
			selectProvidedTextFromDropDown(element("dropdown_printer", fieldName), data);
			String selectedOption = getSelectedTextFromDropDown(element("dropdown_printer", fieldName));
			Assert.assertEquals(selectedOption, data, 
					"[ASSERTION FAILED]: Selected option: " + selectedOption + ", while expected is: " + data);
			logMessage("[ASSERTION PASSED]: Selected option is: " + data);
		} catch (Exception e) {
			isElementDisplayed("second_sort", fieldName);
			
			wait.waitForElementToBeClickable(element("second_sort", fieldName));
			selectProvidedTextFromDropDown(element("second_sort", fieldName), data);
			String selectedOption = getSelectedTextFromDropDown(element("second_sort", fieldName));
			Assert.assertEquals(selectedOption, data,
					"[ASSERTION FAILED]: Selected option: " + selectedOption + ", while expected is: " + data);
			logMessage("[ASSERTION PASSED]: Selected option is: " + data);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		}
		
		return data;
	}

	public boolean selectValueForStrengthUOMDropDownonItemScreen(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		boolean flag = false;
		try {
			isElementDisplayed("dropdown_printer", fieldName);
			wait.waitForElementToBeClickable(element("dropdown_printer", fieldName));
			selectProvidedTextFromDropDown(element("dropdown_printer", fieldName), data);
			flag = true;

		} catch (Exception e) {
			isElementDisplayed("dropdown_printer", fieldName);
			wait.waitForElementToBeClickable(element("dropdown_printer", fieldName));
			selectProvidedTextFromDropDown(element("dropdown_printer", fieldName), data);
			Assert.assertEquals(getSelectedTextFromDropDown(element("dropdown_printer", fieldName)), data);

		}
		return false;

	}

	public void selectValueFromDropDown(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		
		isElementDisplayed("second_sort", fieldName);
		wait.waitForElementToBeClickable(element("second_sort", fieldName));
		selectProvidedTextFromDropDown(element("second_sort", fieldName), data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("second_sort", fieldName)), data);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}

	public boolean verifyRackIsRemoved(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.hardWait(2);
		isElementNotDisplayed("second_sort", fieldName);
		try {
			wait.hardWait(2);
			selectProvidedTextFromDropDown(element("second_sort", fieldName), data);
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	public String selectFacilityForDestinationDropDown(String fieldName, String data) {
		wait.hardWait(10);
		isElementDisplayed("dropdown_destination", fieldName);
		wait.waitForElementToBeClickable(element("dropdown_destination", fieldName));
		selectProvidedTextFromDropDown(element("dropdown_destination", fieldName), data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("dropdown_destination", fieldName)), data);
		return data;

	}

	public boolean verifyValueinDropDownDoesNotExist(String fieldName, String data) {
		isElementDisplayed("second_sort", fieldName);
		wait.waitForElementToBeClickable(element("second_sort", fieldName));
		try {
			selectProvidedTextFromDropDown(element("second_sort", fieldName), data);
		} catch (Exception e) {

			return true;
		}
		return false;
	}
	
	
	public boolean verifyValueinISADropDownDoesNotExist(String fieldName, String data) {
		isElementDisplayed("isa_label_location_assignment", fieldName);
		//wait.waitForElementToBeClickable(element("second_sort", fieldName));
		try {
			//selectProvidedTextFromDropDown(element("second_sort", fieldName), data);
			clickUsingXpathInJavaScriptExecutorSingleClick(element("isa_dropdown_location_assignment"));
			Select sel = new Select(element("isa_dropdown_location_assignment"));
			sel.selectByVisibleText(data);
		} catch (Exception e) {

			return true;
		}
		return false;
	}


	public void verifyValueExistinDropDown(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("second_sort", fieldName);
		wait.waitForElementToBeClickable(element("second_sort", fieldName));
		selectProvidedTextFromDropDown(element("second_sort", fieldName), data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("second_sort", fieldName)), data);
	}

	public void clickOnSaveButtonForAddPrinterPopup() {
		scrollUp();
		isElementDisplayed("save_button", "save");
		scrollUp();
		try {
			element("save_button", "save").click();
		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("save_button", "save"));
		}
		logMessage("Clicked on Save button");
	}

	public boolean verifySuccessMessageOnAddPrinter(String successMessage) {
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		// wait.hardWait(3);
		wait.applyFluentWait(getLocator("popup_message"), 120, 500);
		// isElementDisplayed("popup_message");
		// wait.waitForElementToBeClickable(element("popup_message"));
		Assert.assertTrue(element("popup_message").getText().trim().contains(successMessage));
		logMessage("[ASSERTION PASSED]: Verified Add a new Printer pop up on clicking add button");
		wait.hardWait(3);
		return flag;

	}

	public boolean verifyButtonsOnPrinterPage() {
		flag = false;
		if (isElementDisplayed("btn_add_printer")) {
			flag = true;
		}
		return flag;
	}

	public ArrayList<String> captureDataForParticularColumn(String column) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		List<WebElement> elements = elements("text_column", column);
		column_data = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			String data = elements.get(i).getText();
			// System.out.print("Value of quantity: " + data);
			column_data.add(data);
		}
		return column_data;
	}

	public ArrayList<String> captureDataForParticularColumnOnSchedule(String columnNum) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		List<WebElement> elements = elements("column_by_number", columnNum);
		column_data = new ArrayList<String>();

		for (int i = 0; i < elements.size(); i++) {
			String data = elements.get(i).getText();
			column_data.add(data);
		}

		return column_data;
	}

	public ArrayList<String> sortDataForParticularColumnInAscendingOrder(String coulmn) {

		ArrayList<String> data_compare = captureDataForParticularColumn(coulmn);
		Collections.sort(data_compare, String.CASE_INSENSITIVE_ORDER);
		return data_compare;

	}

	public ArrayList<String> sortDataForParticularColumnInAscendingOrderOnSchedule(String columnNum) {

		ArrayList<String> data_compare = captureDataForParticularColumnOnSchedule(columnNum);
		Collections.sort(data_compare, String.CASE_INSENSITIVE_ORDER);
		return data_compare;

	}

	public void verifyAndClickSortIcon(String coulmn) {
		isElementDisplayed("sort_icon", coulmn);
		wait.hardWait(2);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("sort_icon", coulmn));
		wait.hardWait(2);

	}

	public boolean verifyFieldIsMandatory(String fieldName) {
		isElementDisplayed("icon_mandatory", fieldName);
		if (element("icon_mandatory", fieldName).getText().trim().contains("*")) {
			return true;
		}
		return false;
	}

	public boolean verifyISAFieldIsMandatory(String fieldName) {
		try {
			isElementDisplayed("icon_mandatory", fieldName);
			return element("icon_mandatory", fieldName).getText().trim().contains("*");
		} catch (Exception e) {

			return false;
		}
	}

	public void selectDropdown(String elem, String data) {
		wait.hardWait(5);
		List<WebElement> ele = getAllOptionsFromDropDown(element("dropdown_printer", elem));
		for (WebElement e : ele) {
			if (e.getText().equalsIgnoreCase(data)) {
				e.click();
			}

		}
	}

	public void selectDropdownDispenseExternal(String data, String type) {
		wait.hardWait(8);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		List<WebElement> ele = getAllOptionsFromDropDown(element("dispense_ext_dropdown", type));
		for (WebElement e : ele) {
			if (e.getText().equalsIgnoreCase(data)) {
				e.click();
			}

		}
	}

	public void selectDropdownDefaultValue(String elem) {
		wait.hardWait(5);
		List<WebElement> ele = getAllOptionsFromDropDown(element("dropdown_printer" + elem));
		element(elem).sendKeys("SE");

		/*
		 * for (WebElement e : ele) { System.out.println(e.getText()); if
		 * (e.getText().equalsIgnoreCase(data)) { e.click(); }
		 * 
		 * }
		 */
	}

	public void selectValueFromDropDownByIndex(String elementTextReplace, Integer Index) {

		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
			isElementDisplayed("dropdowns_externalSystem", elementTextReplace);
			Select selectValue = new Select(element("dropdowns_externalSystem", elementTextReplace));
			selectValue.selectByIndex(Index);
			logMessage("[STEP]: Value is selected from dropdown By index.");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		} catch (Exception e) {
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
			isElementDisplayed("second_sort", elementTextReplace);
			Select selectValue = new Select(element("second_sort", elementTextReplace));
			selectValue.selectByIndex(Index);
			logMessage("[STEP]: Value is selected from dropdown By index.");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);

		}
	}

	public void selectValueFromDropDownByIndexWithoutWait(String elementTextReplace, Integer Index) {
		isElementDisplayed("second_sort", elementTextReplace);
		Select selectValue = new Select(element("second_sort", elementTextReplace));
		selectValue.selectByIndex(Index);
		logMessage("[STEP]: Value is selected from dropdown By index.");

	}

	public void selectValueFromDropdownByIndexForDestination(Integer Index) {
		isElementDisplayed("destination_dropdown");
		Select selectValue = new Select(element("destination_dropdown"));
		selectValue.selectByIndex(Index);
		logMessage("[STEP]: Value is selected from dropdown By index.");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);

	}

	public void compareFirstComputerName(String addedComputer, String firstComputer) {
		Assert.assertEquals(addedComputer, firstComputer);
		logMessage("[ASSERTION PASSED]: Verified the latest added computer on the rop of View Computer screen");
	}

	public void clickRadioComputerButton() {
		isElementDisplayed("computer_radio_button");
		clickUsingXpathInJavaScriptExecutor(element("computer_radio_button"));
		logMessage("Clicked on Computer Radio Button");
	}

	public void selectRadioOption(String fieldName) {
		isElementDisplayed("radio_button_destination", fieldName);
		if (!(checkboxIsSelectedUsingJavascript(fieldName)))
			wait.elementHighlight(element("radio_for_click", fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("radio_for_click", fieldName));
		Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
		logMessage("Clicked on Radio button " + fieldName);
	}

	public void verifyandClickAddComputerButton() {
		wait.waitForPageToLoadCompletely();
		wait.hardWait(12);
		isElementDisplayed("add_computer_button");
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("add_computer_button")).click().build();

		seriesOfAction.perform();

		// clickUsingXpathInJavaScriptExecutor(element("add_computer_button"));
		logMessage("[STEP]: Clicked on Add Computer button");

	}

	public void verifyAddComputerPopup(String str) {
		isElementDisplayed("text_add_Computer", str);
	}

	public void verifyInputFieldOnAddingSpaces(String fieldName, String data) {
		isElementDisplayed("text_error_msg");
		if (element("inp_field_printer", fieldName).getAttribute("value").contains(" ")) {
			Assert.assertTrue(element("text_error_msg").getText().trim().equalsIgnoreCase(data));
		}

	}

	public void verifyValidationMessageOnField(String fieldName, String fieldNumber, String data) {
		isElementDisplayed("text_error_msg_destination", fieldName, fieldNumber);

		Assert.assertTrue(
				element("text_error_msg_destination", fieldName, fieldNumber).getText().trim().equalsIgnoreCase(data));
		logMessage("[ASSERTION PASSED]: Validation message:" + data + " has been verified successfully.");

	}

	public void verifyErrorMessageForMandatoryFields(List<String> asList) {
		int i = 0;
		for (WebElement element : elements("text_error_msg")) {
			System.out.println("Message" + element.getText());
			Assert.assertTrue(element.getText().trim().contains("cannot be empty"));

			// Assert.assertTrue(element.getText().trim().contains(asList.get(i)
			// + "cannot
			// be empty"));
			i++;
		}
	}

	public void verifyErrorMessageFoRoutingRule(List<String> asList) {
		int i = 0;
		for (WebElement element : elements("text_error_msg")) {
			System.out.println("Error message:" + element.getText().trim());
			Assert.assertTrue(element.getText().trim().contains("cannot be empty."));
			i++;

		}
	}

	public void verifyFields() {
		isElementDisplayed("ipAddress");
		isElementDisplayed("computerName");
	}

	public void verifyFieldsNew() {
		isElementDisplayed("ipAddress");
		isElementDisplayed("computerName");
	}

	public void clearText(WebElement ele) {
		clickUsingXpathInJavaScriptExecutor(ele);
		wait.hardWait(3);
		ele.clear();

	}

	public List<String> verifyOptionsForDropdownOnAddPrinterPopup(String fieldName) {
		List<String> modelList = new ArrayList<String>();
		wait.hardWait(2);
		for (WebElement element : elements("dropdownoption_printer", fieldName)) {
			System.out.println(element.getText().trim());
			modelList.add(element.getText().trim());
		}
		return modelList;
	}

	public boolean VerifySearchedPrinterDetails(String input, String expectedPrinterName) {
		boolean flag = false;
		if (isElementDisplayed("inp_field_printer", input)) {
			wait.hardWait(4);
			if (element("inp_field_printer", input).getAttribute("value").equalsIgnoreCase(expectedPrinterName)) {
				flag = true;
			}
		}

		return flag;

	}

	/*
	 * public boolean verifySortingForParticularColumn(ArrayList<String>
	 * previous_data, ArrayList<String> sorted_data) {
	 * 
	 * int flag = 0; int count = previous_data.size();
	 * 
	 * for (String item : previous_data) { for (String item1 : sorted_data) { if
	 * (item.equalsIgnoreCase(item1)) { flag++; } } } if (flag == count) {
	 * logMessage("[ASSERTION PASSED] : Coulmn data is sorted in provided order" );
	 * return true; } else {
	 * logMessage("[ASSERTION FAILED] : Coulmn data is not sorted in provided order"
	 * ); return false; }
	 * 
	 * }
	 */

	public void clickOnAddButtonToAddSchedulePick() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		// isElementDisplayed();
		wait.waitForElementToBeClickable(element("btn_add"));
		// element("btn_add").click();
		clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_add"));
		logMessage("[STEP]: Clicked on Add button");
		wait.waitForElementToBeVisible(element("popup_add_schedule"));
		try {
			Assert.assertTrue(element("popup_add_schedule").getText().trim().contains("Add Schedule"));
		} catch (NoSuchElementException e) {
			Assert.assertTrue(element("popup_add_schedule_old").getText().trim().contains("Add Schedule"));
		}

		logMessage("[ASSERTION PASSED]: Verified Add a new Pick Schedule pop up on clicking add button");
	}

	public void clickOnAddButtonToAddExternalSystem() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForElementToBeClickable(element("btn_add"));
		isElementDisplayed("btn_add");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_add"));
		logMessage("[STEP]: Clicked on Add External System");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForElementToBeVisible(element("popup_add_external_system"));
		try {
			Assert.assertTrue(element("popup_add_external_system").getText().trim().contains("Add External System"));
		} catch (NoSuchElementException e) {
			Assert.assertTrue(
					element("popup_add_external_system_old").getText().trim().contains("Add External System"));
		}
		Assert.assertTrue(element("popup_add_external_system").getText().trim().contains("Add External System"));
		logMessage("[ASSERTION PASSED]: Verified Add a new External System pop up on clicking add button");
	}

	public void clearText(String field) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		clickUsingXpathInJavaScriptExecutor(element(field));
		element(field).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 210);
		Assert.assertTrue(element(field).getAttribute("value").equals(""));
		logMessage("Text Cleared");
	}

	public void clickActiveComputerToggle() {
		isElementDisplayed("active_toggle_button");
		wait.waitForElementToBeClickable(element("active_toggle_button"));
		// element("active_toggle_button").click();
		// clickUsingXpathInJavaScriptExecutor(element("active_toggle_button"));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("active_toggle_button"));
		logMessage("Toggle Clicked..Computer is made inactive");
		wait.hardWait(3);
	}

	public void selectValueFromDropdownUOM(String elementTextReplace, String value) {
		isElementDisplayed("base_unit", elementTextReplace);
		WebElement element = element("base_unit");

		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		wait.hardWait(10);
		WebElement dropdownValue = element("base_unit_value", value);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", dropdownValue);
	}

	public String getFacilityNameUOM(String fieldName) {
		isElementDisplayed("base_unit", fieldName);
		WebElement element = element("base_unit");
		logMessage("Facility Name has been extracted from Facilty Key DropDown.");

		return element.getText();
	}

	public void verifyDefaultValueInDropDownOnAddNewDestinationUOM(String fieldName, String text) {
		isElementDisplayed("base_unit", fieldName);
		WebElement element = element("base_unit");

		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();

		WebElement dropdownMenu = element("base_unit_dropdown");

		System.out.println("VALUE@@@@@@@@\n" + dropdownMenu.getText());
		Assert.assertTrue(dropdownMenu.getText().contains(text), "[ASSERTION FAILED]: Text is not verified");
		logMessage("ASSERT PASSED : " + text + " is verified which is selected \n");

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
	}

	public String EnterValueInInputFieldOnAddNewComputerPopup(String fieldName, String fieldNumber, String data) {

		isElementDisplayed("inp_field_computer", fieldName, fieldNumber);
		enterTextInField(element("inp_field_computer", fieldName, fieldNumber), data);

		return data;
	}

	/*
	 * public String enterRandomValueInRichInputField() {
	 * wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	 * isElementDisplayed("dropdown_itemManagement"); List<WebElement> list;
	 * clickUsingXpathInJavaScriptExecutorSingleClick(element(
	 * "dropdown_itemManagement"));
	 * //enterTextInField(element("dropdown_itemManagement"), data);
	 * List<WebElement> elements = elements("dropdown_itemManagement_options");
	 * wait.waitForElementsToBeVisible(elements);
	 * 
	 * int size = elements.size(); try{
	 * 
	 * for (int i = 0; i <= size - 1; i++) {
	 * 
	 * 
	 * elements.get(i).click();
	 * 
	 * logMessage("Option" + elements.get(i) + "is selected");
	 * wait.waitForLoaderToBeInvisible(getLocator("loader"), 60); list =
	 * elements("item_result"); wait.waitForElementsToBeVisible(list);
	 * if(!list.isEmpty()){ String data=list.get(i).getText(); return data; }else{
	 * continue; }
	 * 
	 * }
	 * 
	 * } catch(Exception e){ for (int i = 0; i <= size - 1; i++) {
	 * elements.get(i).click();
	 * 
	 * logMessage("Option" + elements.get(i) + "is selected");
	 * wait.waitForLoaderToBeInvisible(getLocator("loader"), 60); list =
	 * elements("item_result"); wait.waitForElementsToBeVisible(list);
	 * if(!list.isEmpty()){ String data=list.get(i).getText(); return data; }else{
	 * continue; } } } return data;
	 * 
	 * 
	 * }
	 */
	public void clickRadioMobileButton() {
		isElementDisplayed("mobile_radio_button");
		clickUsingXpathInJavaScriptExecutor(element("mobile_radio_button"));
		logMessage("Clicked on Mobile Radio Button");
	}

	public void verifyErrorMessageForIncorrectFields(List<String> asList) {
		int i = 0;
		for (WebElement element : elements("text_error_msg")) {
			Assert.assertTrue(element.getText().trim().contains("Please enter"));
			logMessage(

					"[ASSERTION PASSED]: Verified Error message on Add Computer screen for field:" + element.getText());

		}
	}

	public void verifyErrorMessageForIncorrectFieldsComputer(List<String> asList, String message) {
		int i = 0;
		for (WebElement element : elements("text_error_msg")) {
			Assert.assertTrue(element.getText().trim().contains(message));
			logMessage(
					"[ASSERTION PASSED]: Verified Error message on Add Computer screen for field:" + element.getText());

		}
	}

	public String EnterValueInMACAddressField(String fieldName, String data) {

		isElementDisplayed("macaddress_text");
		enterTextInField(element("macaddress_text"), data);
		return data;
	}

	public String EnterRandomValueInInputFieldOnAddNewExternalSystem(String data) {

		// data =data + System.currentTimeMillis();
		// System.out.print("data entered :" +data );
		isElementDisplayed("inp_external_system");
		enterTextInField(element("inp_external_system"), data);
		// element("inp_field_printer", fieldName).clear();
		// System.out.println("DATA ENTERED AS"+ data);
		// element("inp_field_printer",fieldName).sendKeys("test");
		Assert.assertEquals(element("inp_external_system").getAttribute("value").trim(), data);
		return data;
	}

	public String getColumnFirstData(String col) {
		wait.hardWait(3);
		isElementDisplayed("first_record_of_column", col);
		String value = element("first_record_of_column", col).getText().trim();
		return value;

	}

	public String getFirstRecordOfColumn(String col) {
		isElementDisplayed("first_record_of_column", col);
		String value = element("first_record_of_column", col).getText();
		return value;
	}

	public static String getAlphaNumericString(int n) {

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			int index = (int) (AlphaNumericString.length() * Math.random());

			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public void clickButton(String action) {
		try {
			isElementDisplayed("action_button", action);
			hover(element("action_button", action));
			wait.waitForElementToBeClickable(element("action_button", action));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button", action));

			logMessage("Clicked on '" + action + "' button");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);

		} catch (Exception e) {
			Actions act = new Actions(driver);
			act.moveToElement(element("action_button", action)).click().build().perform();

			logMessage("Clicked on '" + action + "' button");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);
		}
	}

	public void clickButtonUsingId(String id) {
		try {
			wait.hardWait(8);
			isElementDisplayed("button_with_id", id);
			hover(element("button_with_id", id));
			wait.waitForElementToBeClickable(element("button_with_id", id));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("button_with_id", id));

			logMessage("Clicked on " + id + " button");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);

		} catch (Exception e) {
			Actions act = new Actions(driver);
			act.moveToElement(element("button_with_id", id)).click().build().perform();

			logMessage("Clicked on " + id + " button");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);

		}
	}

	public void clickButtonUsingId_withoutwait(String id) {
		try {
			wait.hardWait(8);
			isElementDisplayed("button_with_id", id);
			hover(element("button_with_id", id));
			wait.waitForElementToBeClickable(element("button_with_id", id));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("button_with_id", id));

			logMessage("Clicked on " + id + " button");
		} catch (Exception e) {
			Actions act = new Actions(driver);
			act.moveToElement(element("button_with_id", id)).click().build().perform();

			logMessage("Clicked on " + id + " button");
		}
	}

	public void clickButton_UsingNameAndCount(String name, String count) {
		try {
			wait.hardWait(8);
			isElementDisplayed("button_with_name_count", name, count);
			hover(element("button_with_name_count", name, count));
			wait.waitForElementToBeClickable(element("button_with_name_count", name, count));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("button_with_name_count", name, count));

			logMessage("Clicked on " + name + " button " + count);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);

		} catch (Exception e) {
			Actions act = new Actions(driver);
			act.moveToElement(element("button_with_name_count", name, count)).click().build().perform();

			logMessage("Clicked on " + name + " button " + count);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);

		}
	}

	public void clickButtonByTextWithoutWait(String text) {
		isElementDisplayed("link_edit_of_added_record", text);
		element("link_edit_of_added_record", text).click();
	}

	public boolean isButtonEnabled_UsingNameAndCount(String name, String count) {
		WebElement btn = element("button_with_name_count", name, count);
		if (!btn.isEnabled()) {
			return false;
		} else if (btn.getAttribute("class").contains("disabled")) {
			return false;
		}
		return true;
	}

	public void deleteFilter() {
		isElementDisplayed("delete_filter_btn");
		hover(element("delete_filter_btn"));
		wait.waitForElementToBeClickable(element("delete_filter_btn"));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("delete_filter_btn"));
		logMessage("Clicked on button to delete");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
	}

	public void clickInputButton(String filedName) {
		isElementDisplayed("input_field_by_name", filedName);
		hover(element("input_field_by_name", filedName));
		wait.waitForElementToBeClickable(element("input_field_by_name", filedName));
		clickUsingXpathInJavaScriptExecutor(element("input_field_by_name", filedName));
		logMessage("Clicked on" + filedName + "button to add hold reason");

	}

	public void clickSaveButtonForISA() {
		scrollUp();
		isElementDisplayed("save_button");
		wait.waitForElementToBeClickable(element("save_button"));
		clickUsingXpathInJavaScriptExecutor(element("save_button", "save"));
		wait.hardWait(5);
		logMessage("Clicked on Save button to add printer");
	}

	public void clickSaveButton() {
		scrollUp();
		isElementDisplayed("save_button");
		
		try {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("save_button"));
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		} catch(Exception e) {
			element("save_button").click();
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		}
		
		logMessage("Clicked on Save button");
	}

	public void clickSaveButtonWithoutWait() {
		scrollUp();
		isElementDisplayed("save_button");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("save_button"));
	}

	public void clickCancelButton() {
		wait.hardWait(3);
		scrollUp();
		isElementDisplayed("cancel_button");
		// wait.waitForJStoLoad();
		element("cancel_button").click();

		// clickUsingXpathInJavaScriptExecutor(element("cancel_button"));
		clickUsingXpathInJavaScriptExecutor(element("yes_button"));

		logMessage("Clicked on Cancel button to add printer");
	}

	public void clickToSetDays() {
		isElementDisplayed("schedule_day");
		// clickUsingXpathInJavaScriptExecutor(element("schedule_day"));
		element("schedule_day").click();
		logMessage("[STEP]: Schedule Day has been set");

	}

	public String clickToSetNewDays() {
		isElementDisplayed("schedule_day");
		// clickUsingXpathInJavaScriptExecutor(element("schedule_day"));
		element("schedule_day_new").click();
		logMessage("Schedule Day has been set");
		return element("schedule_day_new").getText();

	}

	public void verifyNewlyAddedHoldReasonStatus(String computerName, String status) {
		hardWaitForChromeBrowser(5);
		isElementDisplayed("added_computer_name", computerName);
		String computerStatus = getColumnFirstData("4");

		Assert.assertEquals(computerStatus, status, "[ASSERTION FAILED]: Status is not verified");
		// Assert.assertTrue(element("added_computer_name", computerName,
		// status).isDisplayed());
		logMessage("[ASSERTION PASSED]: Newly added Computer : " + computerName + " with status " + status
				+ " is displayed List");

	}

	public void verifyNewComputerNameInList(String computerName) {
		wait.hardWait(6);
		isElementDisplayed("added_computer_name", computerName);
		Assert.assertTrue(element("added_computer_name", computerName).isDisplayed());
	}

	public void verifyAndClickInactiveToggle() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 140);
		isElementDisplayed("show_inactive_toggle");
		clickToggleUsingXpathInJavaScriptExecutor(element("show_inactive_toggle"));
		logMessage("Inactive Toggle Clicked");
		wait.hardWait(5);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 30);
	}

	public void verifyNewlyAddedPrinterNameInPrinterList(String printerName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 140);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 140); // don't remove this, reqd for external systems
		
		try {
			isElementDisplayed("link_edit_of_added_record", printerName);
			Assert.assertTrue(element("link_edit_of_added_record", printerName).isDisplayed());
			logMessage(
					"[ASSERTION PASSED]: Newly added printer : " + printerName + " is displayed in the Printer List");
		} catch (Exception e) {

			isElementDisplayed("added_record_div", printerName);
			Assert.assertTrue(element("added_record_div", printerName).isDisplayed());
			logMessage(
					"[ASSERTION PASSED]: Newly added printer : " + printerName + " is displayed in the Printer List");
		}
	}

	public void verifyNewlySelectedDayInList(String scheduleName, String day) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		isElementDisplayed("updated_schedule_day", scheduleName);
		// Assert.assertTrue(element("updated_schedule_day",
		// scheduleName).getText().contains(day));
		logMessage("[ASSERTION PASSED]: Newly added printer : " + scheduleName + " is displayed in the Printer List");

	}

	public void clickOnEditLinkCorresspondingToScheduleName(String scheduleName) {
		isElementDisplayed("link_edit", scheduleName);
		clickUsingXpathInJavaScriptExecutor(element("link_edit", scheduleName));
		logMessage("Clicked on Edit link corressponding to printer " + scheduleName);
		wait.waitForElementToBeVisible(element("popup_add_printer"));
		Assert.assertTrue(element("popup_add_printer").getText().trim().contains(scheduleName));
		logMessage("[ASSERTION PASSED]: Verified Edit a Schedule pop up");
	}

	public void clickOnEditLinkCorresspondingToAddedRecord(String PageTitle, String fieldName) {
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
			isElementDisplayed("link_edit_of_added_record", fieldName);
			clickUsingXpathInJavaScriptExecutor(element("link_edit_of_added_record", fieldName));
			logMessage("Clicked on Edit link corressponding to added record " + fieldName);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
			// wait.waitForElementToBeVisible(element("page_title"));
			// Assert.assertTrue(element("page_title").getText().trim().contains(PageTitle));
			logMessage("[ASSERTION PASSED]: Verified Edit a Schedule pop up");
		} catch (Exception e) {

			wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
			isElementDisplayed("link_edit_external_system", fieldName);
			clickUsingXpathInJavaScriptExecutor(element("link_edit_external_system", fieldName));
			logMessage("Clicked on Edit link corressponding to added record " + fieldName);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
			wait.waitForElementToBeVisible(element("popup_assign_location"));
			Assert.assertTrue(element("popup_assign_location").getText().trim().contains(PageTitle));
			logMessage("[ASSERTION PASSED]: Verified Edit a Record pop up");

		}

	}

	public void selectValueByTextUsingIdField(String idfield, String text) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("itemlocation_dropdown", idfield);
		(new Select(element("itemlocation_dropdown", idfield))).selectByVisibleText(text);
		Assert.assertEquals(getSelectedTextFromDropDown(element("itemlocation_dropdown", idfield)), text);

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
	}

	public boolean isElementEnabled_UsingId(String id) {

		isElementDisplayed("button_with_id", id);
		return element("button_with_id", id).isEnabled();

	}

	public boolean isButtonDisplayedUsingId(String id) {
		return isElementNotDisplayed("button_with_id", id);
	}

	public void verifyEditLinkCorrespondingToAddedRecord(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("link_edit_of_added_record", fieldName);
	}

	public void clickEditLinkCorrespondingToAddedRecord(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		try {
			element("link_edit_of_added_record", fieldName).click();
		} catch (Exception e) {
			isElementDisplayed("link_edit_distination", fieldName);
			clickUsingXpathInJavaScriptExecutor(element("link_edit_distination", fieldName));
			logMessage("Clicked on Edit link corressponding to added record " + fieldName);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		}
	}

	public void verifyAndClickEditLinkTransactionPriority(String priority) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		scrollDown(element("button_edit_transactionPriority", priority));
		// scrollUp();
		isElementDisplayed("button_edit_transactionPriority", priority);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("button_edit_transactionPriority", priority));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}

	public void enterMaxHoldMins(String value, int maxLength) {
		isElementDisplayed("input_maxHoldMin");
		enterTextInField(element("input_maxHoldMin"), value);
		Assert.assertEquals(element("input_maxHoldMin").getAttribute("value").length(), maxLength);
	}

	public boolean verifyMaxHoldMins() {
		isElementDisplayed("input_maxHoldMin");
		String val = element("input_maxHoldMin").getAttribute("value");
		if (val.equalsIgnoreCase("0")) {
			return true;
		} else {
			return false;
		}
	}

	public String getMaxHoldMinsData(String priorityName) {
		isElementDisplayed("input_maxHoldMin");
		String value = element("input_maxHoldMin").getAttribute("value");
		return value;
	}

	public void clickOnEditLinkCorresspondingToExternalSystemName(String externalSystem) {
		isElementDisplayed("link_edit_external_system", externalSystem);
		clickUsingXpathInJavaScriptExecutor(element("link_edit_external_system", externalSystem));
		logMessage("Clicked on Edit link corressponding to External System " + externalSystem);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForElementToBeVisible(element("popup_add_external_system"));
		Assert.assertTrue(element("popup_add_external_system").getText().trim().contains(externalSystem));
		logMessage("[ASSERTION PASSED]: Verified Edit an External System pop up");
	}

	public void clickOnDeleteLinkCorresspondingToScheduleName(String scheduleName) {
		isElementDisplayed("link_delete", scheduleName);
		element("link_delete", scheduleName).click();
		// clickUsingXpathInJavaScriptExecutor(element("link_delete",
		// scheduleName));
		logMessage("Clicked on Delete link corressponding to schedule " + scheduleName);
		wait.waitForElementToBeVisible(element("popup_delete_schedule"));
		Assert.assertTrue(element("popup_delete_schedule").getText().trim()
				.contains("Are you sure you want to delete the schedule?"));
		logMessage("[ASSERTION PASSED]: Verified Delete a Schedule pop up");
	}

	public void confirmDeletePopup() {
		isElementDisplayed("confirm_delete_button");
		clickUsingXpathInJavaScriptExecutor(element("confirm_delete_button"));
		logMessage("[ASSERTION PASSED]: Confirmed Delete a Schedule pop up");

	}

	public void verifySuccessMessageOnDeleteSchedule(String successMessage) {
		wait.applyFluentWait(getLocator("popup_message"), 120, 500);
		String actualMsg = element("popup_message").getText();
		Assert.assertTrue(actualMsg.contains(successMessage.trim()), "[ASSERTION FAILED]: Actual message: \""
				+ actualMsg + "\"\n" + "Expected message: \"" + successMessage + "\"");
		logMessage("[ASSERTION PASSED]: Verified popup message: \"" + successMessage + "\"");
	}

	public void verifyDeletedScheduleInScheduleList(String scheduleName) {
		isElementNotDisplayed("link_edit_of_added_record", scheduleName);
		logMessage("[ASSERTION PASSED]: Record " + scheduleName + " is not displayed in the list");
	}

	/*
	 * public void clickActiveToggle() { isElementDisplayed("active_toggle_label");
	 * Actions action = new Actions(driver); Action seriesOfAction = (Action)
	 * action.moveToElement(element("active_toggle_label")).click()
	 * .keyDown(element("active_toggle_label"),
	 * Keys.CONTROL).sendKeys(Keys.SPACE).build();
	 * 
	 * seriesOfAction.perform();
	 * 
	 * logMessage("Active Toggle is Clicked...!!!!!");
	 * 
	 * }
	 */

	public void clickToggleButton(String action, String toggle) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		isElementDisplayed("action_toggle_button", toggle);

		if (element("action_toggle_button", toggle).getAttribute("value").equals(action)) {
			logMessage("Toggle button value is already - " + action);
		} else {
			wait.waitForElementToBeClickable(element("action_toggle_button_1", toggle));
			element("action_toggle_button_1", toggle).click();
			logMessage("Toggle button with name - '" + toggle + "' is clicked");
		}
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);
		// hard wait, because toggles take some time to load
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickActiveToggle(String data) throws Throwable {

		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
			// isElementDisplayed("active_toggle_label", data);
			wait.waitForElementToBeClickable((element("active_toggle_label", data)));
			// element("active_toggle_label", data).click();
			clickUsingXpathInJavaScriptExecutorSingleClick(element("active_toggle_label", data));
			logMessage("Active Toggle is Clicked again...!!!!!");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);

		} catch (Exception e) {

			e.printStackTrace();

			Actions action = new Actions(driver);
			Action seriesOfAction = (Action) action.moveToElement(element("active_toggle_label", data)).click()
					.keyDown(element("active_toggle_label", data), Keys.CONTROL).sendKeys(Keys.SPACE).build();

			seriesOfAction.perform();

			logMessage("Active Toggle is Clicked...!!!!!");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		}

	}

	public void verifyToggleIsActive(String id) {
		isElementDisplayed("active_toggle");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Assert.assertTrue((boolean) jse.executeScript("return document.getElementById('" + id + "').checked;"));
		logMessage("[ASSERTION PASSED]: Toggle is Active.");

	}

	public boolean verifyToggleIsActiveForSystemLabel(String id) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("edit_table_toggle", id);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		logMessage("[ASSERTION PASSED]: Toggle is Active.");
		return (boolean) jse.executeScript("return document.getElementById('" + id + "').checked;");

	}

	public void verifyToggleOption(String value) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.loadingWait(getLocator("loader"));
		isElementDisplayed("active_toggle_label", value);

	}

	public void verifyToggleIsInActive(String id) {
		isElementDisplayed("active_toggle");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Assert.assertFalse((boolean) jse.executeScript("return document.getElementById('" + id + "').checked;"));
		logMessage("[ASSERTION PASSED]: Toggle is InActive.");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}

	public boolean selectAllCheckboxesOfExternalSystems() {
		wait.waitForPageToLoadCompletely();
		if (isElementDisplayed("checkboxes_external_system")) {

			List<WebElement> elements = elements("checkboxes_external_system");
			wait.hardWait(10);

			int size = elements.size();
			for (int i = 0; i <= size - 1; i++) {
				// if(!(elements.get(i).isSelected())){
				Actions action = new Actions(driver);
				action.moveToElement(elements.get(i)).click().build().perform();
				// elements.get(i).click();
				// Assert.assertTrue(elements.get(i).isSelected());
				logMessage("Checkbox" + elements.get(i) + "is selected");

				// }
			}

			logMessage("User has clicked on all checkbox in Pick Queue");
			flag = true;
		}

		return flag;
	}

	/*
	 * public boolean selectCheckBoxOfNewlyCreatedTransactionPriority(String
	 * fieldName){ selectCheckbox(fieldName, b); return flag; }
	 */

	public String verifyToggleButtonIsActiveOrNot(String text) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		isElementDisplayed("active_toggle", text);
		logMessage("[ASSERTION PASSED]: Verified Toggle button on screen");
		String ele = element("active_toggle", text).getAttribute("value").trim();
		System.out.println("Value of box is  " + ele);
		return element("active_toggle", text).getAttribute("value").trim();
	}

	public String verifyToggleButtonIsActiveOrNotOnLabel(String text) {
		wait.hardWait(5);
		isElementDisplayed("toggle_defineLabel", text);
		logMessage("[ASSERTION PASSED]: Verified Toggle button on screen");
		return element("toggle_defineLabel", text).getAttribute("value").trim();
	}

	public void verifyDefaultValueInDropDown(String fieldName, String text) {
		try {
			isElementDisplayed("dropdown_printer", fieldName);
			verifySelectedTextFromDropDown(element("dropdown_printer", fieldName), text);
		} catch (Exception e) {
			isElementDisplayed("second_sort", fieldName);
			verifySelectedTextFromDropDown(element("second_sort", fieldName), text);

		}
	}

	public void verifyDefaultValueInFacilityLevelDropDowns(String fieldName, String text) {
		try {
			isElementDisplayed("facility_level_dropdowns", fieldName);
			verifySelectedTextFromDropDown(element("facility_level_dropdowns", fieldName), text);
		} catch (Exception e) {
			isElementDisplayed("facility_level_dropdowns", fieldName);
			verifySelectedTextFromDropDown(element("facility_level_dropdowns", fieldName), text);

		}
	}

	public void verifyDefaultValueInDropDownOnAddNewExternalSystem(String fieldName, String text) {
		isElementDisplayed("dropdowns_externalSystem", fieldName);
		verifySelectedTextFromDropDown(element("dropdowns_externalSystem", fieldName), text);
	}

	public void verifyDefaultValueInDropDownOnAddNewDestination(String fieldName, String text) {
		isElementDisplayed("dropdown_destination", fieldName);
		System.out.println("VALUE@@@@@@@@" + element("dropdown_destination", fieldName).getText());
		verifySelectedTextIsContainedInDropDown(element("dropdown_destination", fieldName), text);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
	}

	public void verifyAllCheckboxesOfExternalSystemsEnabled(String[] external_sys_checkboxes_id) {

		wait.waitForPageToLoadCompletely();
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);

		for (String string : external_sys_checkboxes_id) {
			Assert.assertTrue(checkboxIsSelectedUsingJavascript(string));
		}

	}

	public void verifyAllCheckboxesOfExternalSystemsDisabled(String[] external_sys_checkboxes_id) {

		wait.waitForPageToLoadCompletely();
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);

		for (String string : external_sys_checkboxes_id) {
			Assert.assertFalse(checkboxIsSelectedUsingJavascript(string));
		}

	}

	public void verifyErrorMessageForAlreadyExistingName(String message) {
		isElementDisplayed("div_alert");
		String text = element("div_alert").getText().trim();
		System.out.println(text);
		Assert.assertTrue(text.contains("This " + message + " already exists. Please provide a unique " + message));
	}

	public void verifyErrorMessageForValidInput(String field) {
		isElementDisplayed("text_error_msg");
		Assert.assertTrue(element("text_error_msg").getText().trim().contains("Please enter a valid " + field));
	}

	public void verifyErrorMessageForValidRange(String message) {
		isElementDisplayed("text_error_msg");
		Assert.assertTrue(element("text_error_msg").getText().trim().contains(message));
	}

	public void verifyErrorMessageUnderFields(String message, String fieldNumber) {
		isElementDisplayed("text_error_msg__field_no", fieldNumber);
		Assert.assertTrue(element("text_error_msg__field_no", fieldNumber).getText().trim().contains(message));
	}

	public String selectValueFromDropDownForExternalSystem(String fieldName, String data) {
		wait.waitForElementToBeClickable(element("dropdowns_externalSystem", fieldName));
		selectProvidedTextFromDropDown(element("dropdowns_externalSystem", fieldName), data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("dropdowns_externalSystem", fieldName)), data);
		return data;
	}

	public String selectAndReturnValueFromDropDownByIndex(String elementTextReplace, Integer Index) {
		try {
			isElementDisplayed("dropdowns_externalSystem", elementTextReplace);
			Select selectValue = new Select(element("dropdowns_externalSystem", elementTextReplace));
			selectValue.selectByIndex(Index);
			selectProvidedTextFromDropDownUsingIndex(element("dropdowns_externalSystem", elementTextReplace), Index);
			String data = getSelectedTextFromDropDown(element("dropdowns_externalSystem", elementTextReplace));
			logMessage("[STEP]: Value is selected from dropdown By index.");
			return data;
		} catch (Exception e) {
			isElementDisplayed("second_sort", elementTextReplace);
			Select selectValue = new Select(element("second_sort", elementTextReplace));
			selectValue.selectByIndex(Index);
			selectProvidedTextFromDropDownUsingIndex(element("second_sort", elementTextReplace), Index);
			String data = getSelectedTextFromDropDown(element("second_sort", elementTextReplace));
			logMessage("[STEP]: Value is selected from dropdown By index.");
			return data;

		}
	}

	public String selectValueFromDropDownForManufacturer(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForElementToBeClickable(element("second_sort", fieldName));
		selectProvidedTextFromDropDown(element("second_sort", fieldName), data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("second_sort", fieldName)), data);
		return data;
	}

	public String selectAndReturnValueFromDropDownByIndexForDestination(Integer Index) {

		isElementDisplayed("destination_dropdown");
		Select selectValue = new Select(element("destination_dropdown"));
		selectValue.selectByIndex(Index);
		selectProvidedTextFromDropDownUsingIndex(element("destination_dropdown"), Index);
		String facilityName = getSelectedTextFromDropDown(element("destination_dropdown"));
		// String data = element("destination_dropdown").getText();
		logMessage("[STEP]: Value is selected from dropdown By index.");
		return facilityName;

	}

	public void verifyAllCheckboxesOfExternalSystemsAreUnchecked(String[] external_sys_checkboxes_id) {
		wait.waitForPageToLoadCompletely();
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);

		for (String string : external_sys_checkboxes_id) {
			Assert.assertFalse(checkboxIsSelectedUsingJavascript(string));
		}

	}

	public boolean verifyInputFieldIsBlankforComputer(String fieldName) {
		if (element(fieldName).getText().trim().isEmpty()) {
			System.out.println("Input field is empty");
			return true;
		} else
			System.out.println("Input field is not empty");
		return false;
	}

	public boolean verifyInputFieldContainsCopy(String fieldName) {
		if (element("inp_field_printer", fieldName).getAttribute("value").contains("- Copy")) {
			logMessage("[Assertion Passed]: Input field contains Copy.");
			return true;
		} else
			logMessage("[Assertion Failed]: Input field does not contain Copy.");
		return false;
	}

	public void selectControlCarouselCheckboxForComputer() {

		isElementDisplayed("control_carousel_checkbox");
		wait.hardWait(5);
		wait.waitForElementToBeClickable(element("control_carousel_checkbox"));
		element("control_carousel_checkbox").click();
		logMessage("Control Carousel Checkbox is selected");

	}

	public void selectUseScanCheckboxForComputer() {

		isElementDisplayed("use_Scan_checkbox");
		wait.hardWait(5);
		wait.waitForElementToBeClickable(element("use_Scan_checkbox"));
		element("use_Scan_checkbox").click();
		logMessage("Use Scan Checkbox is selected");

	}

	public void verifyErrorMessageForAlreadyExistingExternalSystem(String Message) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("div_alert");
		Assert.assertTrue(element("div_alert").getText().trim().contains(Message));
	}

	public boolean verifyTabOnAddDestinationPageIsEnableOrNot(String tab) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("tab_link", tab);
		if (element("tab_link", tab).getAttribute("class").trim().contains("active")
				|| !(element("tab_link", tab).getAttribute("class").trim().contains("disabled"))) {
			return true;
		} else {
			Assert.assertTrue(element("tab_link", tab).getAttribute("class").trim().contains("disabled"));
			return false;
		}
	}

	public boolean verifyComputerStatusInactive() {
		List<WebElement> elements = elements("list_computerStatus");
		for (WebElement ele : elements) {
			if ((ele.getText().trim().equals("Inactive"))) {
				count1++;
				System.out.println("Value of count:  " + count);
			}
		}
		if (count1 == 0) {
			logMessage("All Records are Active");
			return true;
		} else {
			logMessage("Inactive Computers are displayed");
			return false;
		}
	}

	public void verifyRequiredFieldIndicator(String text) {
		isElementDisplayed("required_field_label");
		Assert.assertTrue(element("required_field_label").getText().contains(text));
		logMessage("[ASSERTION PASSED]: Label is present");

	}

	public String getFacilityName(String fieldName) {
		isElementDisplayed("dropdown_printer", fieldName);
		String facilityName = getSelectedTextFromDropDown(element("dropdown_printer", fieldName));

		logMessage("Facility Name has been extracted from Facilty Key DropDown.");
		return facilityName;
	}

	public String getPrinterValue(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("dropdowns_printers", fieldName);
		String printerName = getSelectedTextFromDropDown(element("dropdowns_printers", fieldName));

		logMessage("Printer Name has been extracted from " + printerName + " Printer DropDown");
		return printerName;
	}

	public String getFacilityFromISAScreen() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("facility_on_ISA_Screen");
		wait.waitForElementToBeClickable(element("facility_on_ISA_Screen"));
		String facilityName = element("facility_on_ISA_Screen").getText().substring(11);

		logMessage("Facility Name " + facilityName + " has been extracted from ISA Screen.");
		return facilityName;
	}

	public String getExternalSystemMappedToFacility(String facilityOnWFAScreen) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("ES_mapped_to_facility", facilityOnWFAScreen);
		// wait.waitForElementToBeClickable(element("ES_mapped_to_facility"));
		String externalSystemName = element("ES_mapped_to_facility", facilityOnWFAScreen).getText();

		logMessage("External System" + externalSystemName + " has been extracted from Facilities Screen.");
		return externalSystemName;
	}

	public boolean verifyDropDownIsEnabledOrDisabled(String string) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		try {
			isElementDisplayed("dropdown_printer", string);
			return checkCheckboxIsEnabledOrDisabledUsingJavaScript(string);
		} catch (Exception e) {
			isElementDisplayed("second_sort", string);
			boolean value = element("second_sort", string).isEnabled();
			return value;

		}
	}

	public void verifyDropDownFieldOnAddNewFacility(String fieldName) {
		isElementDisplayed("second_sort", fieldName);
		logMessage("[ASSERTION PASSED]: Verified dropdown field for " + fieldName);
	}

	public void verifyRecordNotPresentInDropDownOptions(List<String> facilities, String facilityName) {
		wait.waitForPageToLoadCompletely();
		for (String element : facilities) {
			Assert.assertNotEquals(element, facilityName);
			logMessage("[ASSERTION PASSED]: Facility dropdown does not contain the facility selected from ISA");
		}
	}

	public boolean verifyInputFieldIsEnabledOrDisabled(String fieldName) {
		String id = element("add_item_fields", fieldName).getAttribute("id");
		return checkCheckboxIsEnabledOrDisabledUsingJavaScript(id);
	}

	public boolean verifyInputFieldForItemEditIsEnabledOrDisabled(String fieldName) {
		String disabled = element("add_item_fields", fieldName).getAttribute("readonly");
		System.out.println("disabled= " + disabled);
		if (disabled.equalsIgnoreCase("true")) {

			return true;
		} else {
			return false;
		}
	}

	public void verifyInputFieldIsDisabled(String fieldName) {
		isElementDisplayed("inp_field_printer", fieldName);
		String value = element("inp_field_printer", fieldName).getAttribute("disabled");
		Assert.assertEquals(value, "true");
		logMessage("[ASSERTION PASSED]: Input field " + fieldName + " is disabled");

	}

	public void verifyIntegerValueIsAllowed(String fieldNumber) {

		Assert.assertFalse(element("text_error_msg_destination", fieldNumber).isDisplayed());
		logMessage("[ASSERTION PASSED]: Integer values is allowed.");

	}

	public void clickTab(String fieldName) {
		
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);

			isElementDisplayed("tab_link", fieldName);
			clickUsingXpathInJavaScriptExecutorSingleClick(element("tab_link", fieldName));
		} catch (ElementClickInterceptedException e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("tab_link", fieldName));
		} catch (Exception e) {
			element("tab_link", fieldName).click();
		}
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("loading"), 60);
		logMessage("[STEP]: Tab '" + fieldName + "' is clicked");
	}

	public void clickTabWithoutWait(String fieldName) {
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
			isElementDisplayed("tab_link", fieldName);
			wait.waitForElementToBeClickable(element("tab_link", fieldName));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("tab_link", fieldName));
		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("tab_link", fieldName));
		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		logMessage("Tab - " + fieldName + " is clicked");
	}

	public boolean verifyEmailFormatValidationMessageAppearOrNot(String data) {
		if (element("text_error_msg").getText().trim().equalsIgnoreCase(data)) {
			return true;
		} else {
			return false;
		}
	}

	public String verifyPlaceHolderValueForAnInputField(String fieldName) {
		return element("inp_field_printer", fieldName).getAttribute("placeholder").trim();
	}

	public boolean verifyPrefilledValueForAnInputField(String fieldName) {
		String value = element("inp_field_printer", fieldName).getAttribute("value").trim();
		if (value != null && !value.isEmpty())
			return true;
		else
			return false;
	}

	public void verifyInputFieldAfterEdit(String fieldName, String data) {
		isElementDisplayed("inp_field_printer", fieldName);
		Assert.assertTrue(element("inp_field_printer", fieldName).getAttribute("value").trim().equalsIgnoreCase(data));
		logMessage("[ASSERTION PASSED]: Data in input field " + fieldName + "after editing is preserved");
	}

	public boolean checkCheckBoxDestination(String forValue) {
		isElementDisplayed("checkbox_destination", forValue);
		wait.waitForElementToBeClickable(element("checkbox_destination", forValue));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("checkbox_destination", forValue));
		return checkboxIsSelectedUsingJavascript(forValue);
	}

	public void selectCheckboxCorresspondingToField(String fieldName, boolean b) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("parent_checkbox_printer", fieldName);
		if (b) {
			if (checkboxIsSelectedUsingJavascript(fieldName)) {
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
			} else {
				try {
					element("parent_checkbox_printer", fieldName).click();
				} catch (Exception e) {
					clickUsingXpathInJavaScriptExecutorSingleClick(element("parent_checkbox_printer", fieldName));
				}
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
			}
			logMessage("Checkbox " + fieldName + " is checked");
		} else {
			if (checkboxIsSelectedUsingJavascript(fieldName)) {
				try {
					element("parent_checkbox_printer", fieldName).click();
				} catch (Exception e) {
					clickUsingXpathInJavaScriptExecutorSingleClick(element("parent_checkbox_printer", fieldName));
				}
				Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
			} else {
				Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
			}
			logMessage("Checkbox" + fieldName + " is unchecked");
		}
	}

	public void selectCheckboxPackageSharing(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		try {
			element("parent_checkbox_printer", fieldName).click();
		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("parent_checkbox_printer", fieldName));
		}

	}

	public void verifyPackageShareAlert(String expected) throws InterruptedException {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		Thread.sleep(5000);
		try {
			wait.applyFluentWait(getLocator("popup_for_add"), 120, 500);
		//	wait.waitForElementToBeVisible(element("popup_for_add"));
			Assert.assertTrue(element("popup_for_add").getText().trim().contains("Alert"));
			isElementDisplayed("packageShare_modalBody");
			String actual = element("packageShare_modalBody").getText();
			Assert.assertEquals(actual, expected);
			element("packageShare_modalBody").click();
		} catch (Exception e) {
			wait.applyFluentWait(getLocator("popup_for_add"), 120, 500);
		//	wait.waitForElementToBeVisible(element("popup_for_add"));
			Assert.assertTrue(element("popup_for_add").getText().trim().contains("Alert"));
			isElementDisplayed("packageShare_modalBody");
			String actual = element("packageShare_modalBody").getText();
			Assert.assertEquals(actual, expected);
			element("packageShare_modalBody").click();
		}
	}

	public void selectCheckboxCorresspondingToISAField(String fieldName, boolean b) {
		if (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("edge")) {

			isElementDisplayed("parent_checkbox_printer", fieldName);
			if (b) {
				if (checkboxIsSelectedUsingJavascript(fieldName)) {
					Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
				} else {

					element("parent_checkbox_printer", fieldName).click();

					Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
				}
				logMessage("[ASSERTION PASSED]: Checkbox" + fieldName + " is checked");
			} else {
				if (checkboxIsSelectedUsingJavascript(fieldName)) {
					element("parent_checkbox_printer", fieldName).click();
					Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
				} else {
					Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
				}
				logMessage("Checkbox" + fieldName + " is unchecked");
			}
		}

		else

		// if
		// (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("chrome"))

		{
			isElementDisplayed("parent_checkbox_printer", fieldName);

			{
				isElementDisplayed("parent_checkbox_printer", fieldName);

				if (b) {
					if (checkboxIsSelectedUsingJavascript(fieldName)) {
						Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
					} else {
						element("parent_checkbox_printer", fieldName).click();

						Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
					}
					logMessage("Checkbox" + fieldName + " is checked");
				} else {
					if (checkboxIsSelectedUsingJavascript(fieldName)) {
						try {
							element("parent_checkbox_printer", fieldName).click();

						} catch (Exception e) {
							clickUsingXpathInJavaScriptExecutorSingleClick(
									element("parent_checkbox_printer", fieldName));
						}
						Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
					} else {
						Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
					}
					logMessage("Checkbox" + fieldName + " is unchecked");
				}
			}
		}

	}

	public void verifyRadioButtonIsEnabled(String fieldName) {
		isElementDisplayed("radio_button_destination", fieldName);
		String value = element("radio_button_destination", fieldName).getAttribute("disabled");
		Assert.assertEquals(value, "false");
		logMessage("[ASSERTION PASSED]: Radio Button field " + fieldName + " is enabled");
	}

	public void verifyErrorMessageForPhoneNumberAndFaxFieldIsDisplayedTillThe10thNumberIsEntered(String fieldName,
			String errorMessage) {
		String s = "";
		for (int i = 0; i < 9; i++) {
			s = s + i;
			EnterValueInInputFieldOnAddNewPrinterPopup(fieldName, s);
			verifyErrorMessageForValidInput(errorMessage);
		}
		EnterValueInInputFieldOnAddNewPrinterPopup(fieldName, s + 9);
		Assert.assertFalse(isElementNotDisplayed("text_error_msg"));
	}

	public boolean verifyDropDownIsEnabledOrDisabledForPrinterDestinationLabel(String string) {
		isElementDisplayed("dest_dropdown", string);
		boolean value = element("dest_dropdown", string).isEnabled();
		return value;
	}
	
	public void clickAddItemButtonOnDestinationScreen() {
		wait.waitForPageToLoadCompletely();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("add_item_btn_destination");
		logMessage("[STEP]: Verified Add Button on Items Tab");
		wait.waitForElementToBeClickable(element("add_item_btn_destination"));
		
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("add_item_btn_destination")).click().build();
		seriesOfAction.perform();
		// clickUsingXpathInJavaScriptExecutor(element("add_item_btn_destination"));
		logMessage("[STEP]: Clicked Add Button on Items Tab");
	}
	
	public void verifyAddItemPopup() {
		isElementDisplayed("add_item_popup_destination");
		logMessage("ASSERT PASSED :Verified Add Items popup after clicking Add button");
	}

	public String enterItemNameForDestinationItem(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.waitForElementToBeClickable(element("search_formulary_destination"));
		isElementDisplayed("search_formulary_destination");
		enterTextInField(element("search_formulary_destination"), data);
		logMessage("Entered Item name in Search" + data);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		return data;
	}
	
	

	public void verifyItemSearchResult() {
		wait.hardWait(60);
		isElementDisplayed("search_item_result");
		logMessage("ASSERT PASSED :Searched item found");

	}

	public void getSearchedItemDetails() {
		List<WebElement> elements = elements("search_item_result");
		selected_item_details = elements.get(0).getText().replaceAll("\\s", "");
		logMessage(" Search Result:" + selected_item_details);

	}

	public void selectCheckboxForItem() {
		isElementDisplayed("item_checkbox");
		logMessage("[ASSERTION PASSED]:+ Checkbox found");

		element("item_checkbox").click();
		logMessage("[ASSERTION PASSED]Checkbox selected for Item");
	}

	public void selectCheckboxForFacilityItem() {

		isElementDisplayed("facility_item_checkbox");
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("facility_item_checkbox")).click().build();
		seriesOfAction.perform();

		// element("facility_item_checkbox").click();
		// clickUsingXpathInJavaScriptExecutor(element("facility_item_checkbox"));
		logMessage("ASSERTION PASSED: Clicked checkbox");
	}

	public String getItemDetailsonItemsTab(int rownum) {
		String added_item_details = null;

		isElementDisplayed("added_item_details_destination");
		logMessage("[ASSERTION PASSED]:The selected Item is present on Items tab");
		isElementDisplayed("first_item");
		List<WebElement> elements = elements("first_item");

		for (int i = 0; i <= rownum; i++) {
			if (i == rownum) {
				added_item_details = elements.get(i).getText().replaceAll("\\s", "");
				logMessage("Added Item Details: " + added_item_details);
			}
		}

		return added_item_details;

	}

	public boolean verifyAddedItemOnDestinationPage2(String addeditem, String searcheditem) {

		if (addeditem.contains(searcheditem)) {
			Assert.assertTrue(addeditem.contains(searcheditem));
			logMessage("[ASSERTION PASSED]:Item matched");
			return true;
		}

		else

			return false;
	}

	public boolean verifyAddedItemOnDestinationPage1(int rownum) {
		String added_item_details = null;

		isElementDisplayed("added_item_details_destination");
		logMessage("ASSERT PASSED :The selected Item is present on Items tab");
		isElementDisplayed("first_item");

		List<WebElement> elements = elements("first_item");
		// System.out.println("Array size: "+elements.size());

		for (int i = 0; i <= rownum; i++) {
			// System.out.println("Item details.."+elements.get(i).getText());
			if (i == rownum) {
				added_item_details = elements.get(i).getText().substring(0, 3);
				System.out.println("Added Item Details: " + added_item_details);
			}
			// added_item_details = elements.get(i).getText().replaceAll("\\s",
			// "");
			// System.out.println("Added Item Details: "+added_item_details);
			// dest_item.add(added_item_details);
		}

		System.out.println("Selected Item Details: " + selected_item_details);

		if (selected_item_details.contains(added_item_details) == true) {

			Assert.assertTrue(selected_item_details.contains(added_item_details), "Verified the details of Added Item");
			logMessage("[ASSERTION PASSED] :Verified the Details of added Item on Destination Page");
			return true;
		}

		else {
			logMessage("The item is not present on Items tab");
			return false;
		}

		// return dest_item;
	}

	public void verifyMaxOrderQty(String value) {

		isElementDisplayed("max_order_qty");
		Assert.assertTrue(element("max_order_qty").getText().equalsIgnoreCase(value));
		logMessage("[ASSERTION PASSED] : Value MaxOrderQuantityField is null verified for field");
	}

	public void verifyColumnsonItemTab(String expectedColumns) {
		isElementDisplayed("item_columns");
		List<WebElement> elements = elements("item_columns");
		String item_tab_columns = elements.get(0).getText().replaceAll("\\s", "");
		Assert.assertTrue(item_tab_columns.contentEquals(expectedColumns), "Columns verified on Item Tab");
		logMessage("[ASSERTION PASSED]Columns verified on Item Tab");
	}

	public void verifyShowItemsField() {
		isElementDisplayed("show_items");
		logMessage("ASSERTION PASSED: Verified Show items checkbox");
	}

	public void isCheckboxDisplayedItemsTab(String element) {
		wait.hardWait(5);
		isElementDisplayed(element);
		logMessage("[ASSERTION PASSED] :Checkbox" + element + "is Displayed");
		Assert.assertFalse(element(element).isSelected());

	}

	public void selectCheckboxForShowItems(String fieldName, boolean b) {
		if (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("edge")) {

			isElementDisplayed("checkbox_item_tab", fieldName);
			if (b) {
				if (checkboxIsSelectedUsingJavascript(fieldName)) {
					Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
				} else {

					element("checkbox_item_tab", fieldName).click();
					Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
				}
				logMessage("[ASSERTION PASSED]: Checkbox" + fieldName + " is checked");
			} else {
				if (checkboxIsSelectedUsingJavascript(fieldName)) {
					Actions action = new Actions(driver);
					Action seriesOfAction = (Action) action.moveToElement(element("checkbox_item_tab", fieldName))
							.click().build();
					seriesOfAction.perform();

					Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
				} else {
					Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
				}
				logMessage("Checkbox" + fieldName + " is unchecked");
			}

		}

	}

	public void selectCheckbox(String fieldName, boolean b) {
		isElementDisplayed("checkbox_item_tab", fieldName);
		wait.waitForElementToBeClickable(element("checkbox_item_tab", fieldName));
		if (b) {
			if (checkboxIsSelectedUsingJavascript(fieldName)) {
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
			} else {

				Actions action = new Actions(driver);
				Action seriesOfAction = (Action) action.moveToElement(element("checkbox_item_tab", fieldName)).click()
						.build();
				seriesOfAction.perform();
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
			}
			logMessage("[ASSERTION PASSED]: Checkbox" + fieldName + " is checked");
		} else {
			if (checkboxIsSelectedUsingJavascript(fieldName)) {
				Actions action = new Actions(driver);
				Action seriesOfAction = (Action) action.moveToElement(element("checkbox_item_tab", fieldName)).click()
						.build();
				seriesOfAction.perform();

				Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
			} else {
				Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
			}
			logMessage("Checkbox" + fieldName + " is unchecked");
		}

	}

	public void selectCheckboxVerifyQty(String fieldName, boolean b) {
		isElementDisplayed("checkbox_item_tab", fieldName);
		if (b) {
			if (checkboxIsSelectedUsingJavascript(fieldName)) {
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
			} else {

				/*
				 * Actions action = new Actions(driver); Action seriesOfAction = (Action)
				 * action.moveToElement(element("checkbox_item_tab", fieldName))
				 * .click().build(); seriesOfAction.perform();
				 */

				// clickUsingXpathInJavaScriptExecutor(element("checkbox_item_tab",
				// fieldName));

				element("checkbox_item_tab", fieldName).click();
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));

			}
			logMessage("[ASSERTION PASSED]: Checkbox" + fieldName + " is checked");
		} else {
			if (checkboxIsSelectedUsingJavascript(fieldName)) {
				clickUsingXpathInJavaScriptExecutor(element("checkbox_item_tab", fieldName));

				Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
			} else {
				Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
			}
			logMessage("Checkbox" + fieldName + " is unchecked");

		}
		wait.hardWait(3);

	}

	public void enterLimitOrderQtyValue(String data) {
		isElementDisplayed("max_order_qty_text");
		enterTextInField(element("max_order_qty_text"), data);
		logMessage("[ASSERTION PASSED]: Max order Quantity field is editable after selecting checkbox");
	}

	public void clickOnAddButtonToAddComputer() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.waitForElementToBeClickable(element("btn_add"));
		isElementDisplayed("btn_add");
		clickUsingXpathInJavaScriptExecutor(element("btn_add"));
		logMessage("[STEP]: Clicked on Add button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForElementToBeVisible(element("popup_for_add"));
		Assert.assertTrue(element("popup_for_add").getText().trim().contains("Add Computer"));
		logMessage("[ASSERTION PASSED]: Verified Add a New Computer header on clicking add button");
	}

	public void verifyInputFieldvalue(String fieldname, String data) {

		System.out.println("The value is : " + element(fieldname).getAttribute("value"));
		Assert.assertTrue(element(fieldname).getAttribute("value").equalsIgnoreCase(data));
		logMessage("[ASSERTION PASSED]: The input field :" + fieldname + "contains value"
				+ element(fieldname).getAttribute("value"));
	}

	public boolean verifyPackageSizeFieldisGrayedOrNot() {
		isElementDisplayed("package_size");
		if (!element("package_size").getAttribute("class").trim().contains("gray")) {
			logMessage("The field Package Size is Enabled");
			return true;
		}

		else {

			Assert.assertTrue(element("package_size").getAttribute("class").trim().contains("gray"));
			logMessage("[ASSERTION PASSED]:Verified the Package Size field is Disabled");
			return false;
		}

	}

	public void clickSaveCloseORCancelORSaveAddButton(String element, String field) {
		// wait.hardWait(3);
		// isElementDisplayed(element, field);
		wait.waitForElementToBeClickable(element(element, field));

		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element(element, field)).click().build();
		seriesOfAction.perform();
	}

	public void clickCancelButtonOnItemPopup() {
		isElementDisplayed("cancel_btn_item");
		wait.waitForElementToBeClickable(element("cancel_btn_item"));
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("cancel_btn_item")).click().build();
		seriesOfAction.perform();
	}

	public void verifyAndClickRemoveButton() {
		isElementDisplayed("remove_button");
		logMessage("[ASSERTION PASSED]:Verified Remove button");
		clickUsingXpathInJavaScriptExecutor(element("remove_button"));
		logMessage("[ASSERTION PASSED]:Clicked on Remove Button");
	}

	public String getFirstItem() {
		List<WebElement> elements = elements("first_item");
		firstItem = elements.get(0).getText();

		System.out.println("Item Name is:" + firstItem);

		/*
		 * ( isElementDisplayed("item_checkbox");
		 * logMessage("ASSERT PASSED :+ Checkbox found");
		 * element("item_checkbox").click(); logMessage("Checkbox selected for Item");
		 */
		return firstItem;
	}

	public ArrayList<String> captureDataForParticularColumnDestination(String coulmn) {
		List<WebElement> elements = elements("text_column_destination", coulmn);

		for (int i = 0; i < elements.size(); i++) {

			String data = elements.get(i).getText();
			column_data = new ArrayList<String>();
			// System.out.print("Value of quantity: " + data);
			column_data.add(data);
		}
		return column_data;
	}

	public ArrayList<String> sortDataForParticularColumnInAscendingOrderDestination(String coulmn) {
		ArrayList<String> data_compare = captureDataForParticularColumnDestination(coulmn);
		Collections.sort(data_compare, String.CASE_INSENSITIVE_ORDER);
		return data_compare;
	}

	public boolean verifyDisabledItems() {
		if (isElementNotDisplayed("disabled_items_note") == true) {
			logMessage("[ASSERTION PASSED]: Disabled Items Text is visible");
			return true;

		}

		else {
			logMessage("[ASSRTION PASSED]:Disabled Items text is not visible");
			return false;
		}

	}

	public void verifyPickRoutingRulePlaceholder() {
		Assert.assertEquals(element("inputboxRoutingRule").getAttribute("placeholder").trim(), "Rule Name");
	}

	public void enterRandomRoutingRuleName() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		enterTextInField(element("inputboxRoutingRule"), "Routingrule_" + System.currentTimeMillis());
	}

	public boolean verifyEditSelectedbutton() {

		if (isElementNotDisplayed("edit_selected_button")) {
			logMessage("[ASSERTION PASSED]: button not visible by default");
			return true;
		} else
			return false;
	}

	public String enterRoutingRuleName(String name) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 50);
		enterTextInField(element("inputboxRoutingRule"), name);
		return name;
	}

	public void verifyPickRoutingRuleFooterLink(String footerLink) {
		Assert.assertTrue(isElementDisplayed("link_footer_routingRule", footerLink));
	}

	public void clickPickRoutingRuleFooterLink(String footerLink) {
		Assert.assertTrue(isElementDisplayed("link_footer_routingRule", footerLink));
		click(element("link_footer_routingRule", footerLink));
	}

	public void verifyPickRoutingRuleButton(String button) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		Assert.assertTrue(isElementDisplayed("button_routingRule", button));
	}

	public void verifyPickRoutingRuleButtonDisabled(String button) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		Assert.assertFalse(isElementNotDisplayed("button_routingRule", button));
	}

	public void verifyPickRoutingRuleCancelButton(String button) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		Assert.assertTrue(isElementDisplayed("button_routingRule", button));
	}

	public void verifyPickRoutingRuleLabel(String label) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		Assert.assertTrue(isElementDisplayed("label_routingRule", label));
	}

	public void clickPickRoutingRuleButton(String button) {
		wait.waitForLoaderToBeInvisible(getLocator("loading"), 200);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		Assert.assertTrue(isElementDisplayed("button_routingRule", button));
		
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		jse2.executeScript("arguments[0].click()", element("button_routingRule", button));
		logMessage("Clicked on button: " + button);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 300); // please do not comment this line
	}
	
	public void clickPickRoutingRuleCancelButton(String button) {
		Assert.assertTrue(isElementDisplayed("button_cancel_rotingRule", button));
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		jse2.executeScript("arguments[0].click()", element("button_cancel_rotingRule", button));
		logMessage("Clicked on button: " + button);
		wait.loadingWait(getLocator("loader"));
	}
	
	public void clickRoutingRuleRadioButton(String index, String type) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 50);
		Assert.assertTrue(isElementDisplayed("btn_radio_routingrule", index, type));
		// click(element("btn_radio_routingrule", index, type));
		element("btn_radio_routingrule", index, type).click();

	}

	public void clickRoutingRuleRadioButtonSchedule() {
		Assert.assertTrue(isElementDisplayed("first_toggle_scheduleRoutingRule"));
		// click(element("btn_radio_routingrule", index, type));
		element("first_toggle_scheduleRoutingRule").click();

	}

	public void clickExpanderButton(String routingRule) {
		clickUsingXpathInJavaScriptExecutorSingleClick(element("routingrule_expander_btn", routingRule));
	}

	public void verifyRoutingRuleRadioButton(String index, String type) {
		wait.hardWait(8);
		Assert.assertTrue(isElementDisplayed("btn_radio_routingrule", index, type));

	}

	public void verifyToggleHeader(String text) {
		Assert.assertTrue(isElementDisplayed("text_toggleHeader", text));

	}

	public void verifySuccessMessageOnViewPageWithLoader(String successMessage, int timeOut, int pollingTime) {
		try {
			wait.applyFluentWait(getLocator("popup_message"), timeOut, pollingTime);
			isElementDisplayed("popup_message");
		} catch (Exception e) {
			wait.applyFluentWait(getLocator("popup_message"), timeOut, pollingTime);
			isElementDisplayed("popup_message");
		}
		Assert.assertTrue(element("popup_message").getText().trim().contains(successMessage));
		logMessage("[ASSERTION PASSED]: Verified data is Added");

	}

	public void verifySuccessMessageOnViewPage(String successMessage) {
		wait.waitForElementToBeVisible(element("popup_message"));
		isElementDisplayed("popup_message");
		Assert.assertTrue(element("popup_message").getText().trim().contains(successMessage));
		logMessage("[ASSERTION PASSED]: Verified data is Added");
		wait.hardWait(3);
	}
	
	
	public void verifySuccessMessageOnRoutingRule(String successMessage) {
		isElementDisplayed("popup_message_routingrule");
		Assert.assertTrue(element("popup_message_routingrule").getText().trim().contains(successMessage));
		logMessage("[ASSERTION PASSED]: Verified data is Added");
		wait.hardWait(3);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 210);
	}
	
	
	public void verifyErrorMessageOnLocationPage(String message) {

		isElementDisplayed("popup_error");
		logMessage("ASSERTION PASSED:Verified Error message");
		Assert.assertTrue(element("popup_error").getText().trim().contains(message));
	}

	public void verifyErrorMessageRoutingRule(String message) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.hardWait(5);
		String spanMessage = element("error_message_routingrule", message).getText();
		System.out.println("Value of message:  " + spanMessage);
		Assert.assertEquals(spanMessage, message);

	}

	public void verifyErrorMessageFacility(String message) {
		wait.hardWait(7);
		String spanMessage = element("message_field", message).getText();
		System.out.println("Value of message:  " + spanMessage);
		Assert.assertEquals(spanMessage, message);
	}

	public String getPageHeader() {
		return element("page_header_generic").getText().trim();
	}

	public void headerRoutingRuleDisplayed() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		Assert.assertTrue(isElementDisplayed("header_title_routingRule"));
	}

	public void clickBreadCrumbRoutingRule() {
		click(element("link_breadcrumb_routingRule"));
	}

	public void verifyPopupMessageRoutingRule(String text) {
		wait.hardWait(2);
		Assert.assertTrue(isElementDisplayed("text_popup_routingRule", text));

	}

	public Set<String> verifyRecordListOfTransactionPriorities() {
		wait.hardWait(5);
		Set<String> transactionPrioritiesList = new HashSet<String>();
		List<WebElement> elements = elements("txn_priorities_results");
		wait.waitForElementsToBeVisible(elements);
		for (WebElement ele : elements) {
			transactionPrioritiesList.add(ele.getText().trim());

		}
		Assert.assertTrue(!transactionPrioritiesList.isEmpty());
		logMessage("[ASSERTION PASSED]: Verified List of Transaction Priorities is Present on UI");
		return transactionPrioritiesList;
	}

	public boolean verifySearchResultsOfTransactionPriorities(String searchedData) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		List<WebElement> elements = elements("txn_priorities_results");
		wait.waitForElementsToBeVisible(elements);
		try {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().startsWith(searchedData)))

				{
					return false;
				} else {
					continue;
				}
			}

		} catch (Exception e) {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().startsWith(searchedData)))

				{
					return false;
				} else {
					continue;
				}
			}

		}

		return true;
	}

	public boolean verifyActiveAndInactiveResults(String searchedData1, String searchedData2, String colNumber) {
		wait.hardWait(5);
		List<WebElement> elements = elements("results", colNumber);
		wait.hardWait(7);

		for (int i = 0; i < elements.size(); i++) {
			if (!(elements.get(i).getText().startsWith(searchedData1)))

			{
				if (!(elements.get(i).getText().startsWith(searchedData2))) {
					return false;
				} else {
					continue;
				}

			} else {
				continue;
			}

		}
		return true;
	}

	public boolean verifyActiveExternalSystems(String searchedData, String colNumber) {
		wait.hardWait(5);
		List<WebElement> elements = elements("active_external_systems", colNumber);
		wait.hardWait(7);

		try {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().startsWith(searchedData)))

				{
					return false;
				} else {
					continue;
				}
			}

		} catch (Exception e) {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().startsWith(searchedData)))

				{
					return false;
				} else {
					continue;
				}
			}

		}

		return true;
	}

	public boolean verifySearchResults(String searchedData, String colNumber) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		List<WebElement> elements = elements("link_edit", colNumber);
		wait.hardWait(7);

		try {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().startsWith(searchedData)))

				{
					return false;
				} else {
					continue;
				}
			}

		} catch (Exception e) {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().startsWith(searchedData)))

				{
					return false;
				} else {
					continue;
				}
			}

		}

		return true;
	}

	public boolean verifySearchResultsForSchedules(String searchedData, String colNumber) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		List<WebElement> elements = elements("column_by_number", colNumber);
		wait.hardWait(7);

		try {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().startsWith(searchedData))) {
					return false;
				} else {
					continue;
				}
			}

		} catch (Exception e) {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().startsWith(searchedData))) {
					return false;
				} else {
					continue;
				}
			}

		}

		return true;
	}

	public boolean verifySearchResultsForSortOrder(String searchedData, String colNumber) {
		List<WebElement> elements = elements("sort_results", colNumber);
		wait.waitForElementsToBeVisible(elements);

		try {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().startsWith(searchedData)))

				{
					return false;
				} else {
					continue;
				}
			}

		} catch (Exception e) {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().startsWith(searchedData)))

				{
					return false;
				} else {
					continue;
				}
			}

		}

		return true;
	}
	/*
	 * }
	 * 
	 * for (WebElement ele : elements) { searchResults.add(ele.getText().trim());
	 * System.out.println(searchResults); } for (WebElement s : elements) {
	 * System.out.println("s=" +s.getText());
	 * 
	 * if (!s.getText().contains(searchedData)) { return false; } else { continue; }
	 * } return true;
	 */

	public boolean verifySearchResultsForDistributorAccountNumbers(String searchedData) {
		wait.hardWait(5);
		List<String> searchResults = new ArrayList<String>();
		List<WebElement> elements = elements("distributor_account_nos");
		wait.hardWait(5);
		for (WebElement ele : elements) {
			searchResults.add(ele.getAttribute("value").trim());
		}
		for (String s : searchResults) {
			if (!s.contains(searchedData)) {
				return false;
			} else {
				continue;
			}
		}
		return true;

	}

	public void verifyPopupMessage(String data) {
		wait.waitForElementToBeVisible(element("toggle_popup"));
		Assert.assertTrue(element("toggle_popup").getText().equalsIgnoreCase(data));
		logMessage("[ASSERTION PASSED]: Toggle popup message is verified successfully.");

	}

	public String getPopupText() {
		wait.applyFluentWait(getLocator("popup_message"), 120, 500);
		return element("popup_message").getText().trim();
	}

	public boolean verifydestinationNamesContainsOnlyAlphanumericCharacter() {
		int count = 0;
		List<String> destinationList = new ArrayList<String>();
		List<WebElement> elements = elements("list_destinationNames");
		for (WebElement ele : elements) {
			destinationList.add(ele.getText().trim());
		}
		for (String s : destinationList) {
			String pattern = "[a-zA-Z0-9\\-#\\.\\(\\)\\/%&\\s]{0,19}";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(s);
			if (m.find()) {
				count++;
			}

			else {
				count = 0;
				break;
			}
		}
		System.out.println("val of count" + count);

		if (count != 0)
			return true;

		else
			return false;
	}

	public boolean verifydestinationfacilityNamesContainsOnlyAlphanumericCharacter() {
		int count = 0;
		List<String> destinationList = new ArrayList<String>();
		List<WebElement> elements = elements("List_destinationfacilityNames");
		for (WebElement ele : elements) {
			destinationList.add(ele.getText().trim());
		}
		for (String s : destinationList) {
			String pattern = "[a-zA-Z0-9\\-#\\.\\(\\)\\/%&\\s]{0,19}";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(s);
			if (m.find()) {
				count++;
			}

			else {
				count = 0;
				break;
			}
		}
		System.out.println("val of count" + count);

		if (count != 0)
			return true;

		else
			return false;
	}

	public boolean verifydestinationStatus() {
		List<WebElement> elements = elements("List_destinationStatus");
		for (WebElement ele : elements) {
			if (!(ele.getText().trim().equalsIgnoreCase("Active"))) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyUserIsAbleToSelectDestinationShowInactive() {
		isElementDisplayed("option_destinationshowInactive");
		clickUsingXpathInJavaScriptExecutor(element("option_destinationshowInactive"));
		return element("option_destinationshowInactive").isEnabled();
	}

	public boolean verifyDestinationActiveInactiveStatus() {
		List<WebElement> elements = elements("List_destinationStatus");
		for (WebElement ele : elements) {
			if (!(ele.getText().trim().equalsIgnoreCase("Active")
					|| ele.getText().trim().equalsIgnoreCase("Inactive"))) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyUserIsAbleToClickSearchDestinationTextbox() {
		isElementDisplayed("input_searchDestination");
		clickUsingXpathInJavaScriptExecutor(element("input_searchDestination"));
		return element("input_searchDestination").isEnabled();
	}

	public boolean verifyAddButtonOnDestinationPage() {
		flag = false;
		if (isElementDisplayed("btn_add_destination")) {
			flag = true;
		}
		return flag;
	}

	public boolean verifyEditButtonOnDestinationPage() {
		isElementNotDisplayed("link_edit_destination");
		flag = true;
		return flag;
	}

	public void clickEditButtonOnISAPage() {
		isElementDisplayed("link_edit_destination");
		element("link_edit_destination").click();

	}

	public boolean verifyFacilitydropdownonDestinationPage() {
		flag = false;
		if (isElementDisplayed("facilitydropdown_destination")) {
			flag = true;
			clickUsingXpathInJavaScriptExecutor(element("facilitydropdown_destination"));
		}
		return flag;
	}

	public boolean verifyColumnNameonDestinationPage() {
		flag = false;
		if (isElementDisplayed("ColumnName_destination")) {
			flag = true;
		}
		return flag;
	}

	public boolean verifyColumnFacilityonDestinationPage() {
		flag = false;
		if (isElementDisplayed("ColumnFacility_destination")) {
			flag = true;
		}
		return flag;
	}

	public boolean verifyColumnStatusonDestinationPage() {
		flag = false;
		if (isElementDisplayed("ColumnStatus_destination")) {
			flag = true;
		}
		return flag;
	}

	public void VerifyAndSearchByDestinationName(String option) {
		isElementDisplayed("input_searchDestination");
		enterTextInField(element("input_searchDestination"), option);
	}

	public void VerifyAndSearchDestinationByFacilityName(String option) {
		isElementDisplayed("input_searchDestination");
		enterTextInField(element("input_searchDestination"), option);
	}

	public void enterSearchTermInSearchField(String data, String field) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		// wait.loadingWait(getLocator("loader"));
		isElementDisplayed("search_box", field);
		enterTextInField(element("search_box", field), data);
		Assert.assertEquals(element("search_box", field).getAttribute("value").trim(), data);
		logMessage("[ASSERTION PASSED]: Search Term as : " + data + " is displayed in the Search Field");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}

	public String returnTransactionPriorityName() {
		wait.loadingWait(getLocator("loader"));
		String firstValue = element("text_TransactionPriority").getText();
		return firstValue;
	}

	public void checkTransactionPriorityList() {
		ArrayList<String> transactionList = new ArrayList<String>();
		List<WebElement> elements = elements("text_TransactionPriority");
		for (WebElement ele : elements) {
			transactionList.add(ele.getText().trim());
		}
		Assert.assertTrue(!transactionList.isEmpty());
		logMessage("[ASSERTION PASSED]: Verified Transation Priority list on Add Facility UI");
	}
	
	public void enterSearchTermInSearchField(Keys key, String data, String field) {
		wait.loadingWait(getLocator("loader"));
		isElementDisplayed("search_box", field);
		enterTextInField(element("search_box", field), data + key);
		Assert.assertEquals(element("search_box", field).getAttribute("value").trim(), data);
		logMessage("[ASSERTION PASSED]: Search Term as : " + data + " is displayed in the Search Field");
		// wait.loadingWait(getLocator("loader"));
	}
	
	public boolean verifyMessageWhenToggelIsOnOrOff(String text) {
		try {
			return element("before_message", text).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public boolean verifyLabelHeaderOnUserTab(String header) {
		return isElementDisplayed("text_msg", header);
	}

	public void verifyTableIsDisplayedWhenToggleIsOn() {
		Assert.assertTrue(elements("div_table").size() != 0);
	}

	public void verifyCheckboxIsAssociatedWithEachUserName(List<String> userNames) {
		for (String name : userNames) {
			isElementDisplayed("checbox_userName", name);
		}
	}

	public ArrayList<String> captureDistributorDataForParticularColumn(String coulmn) {
		List<WebElement> elements = elements("text_column_distributor", coulmn);
		column_data = new ArrayList<String>();

		for (int i = 0; i < elements.size(); i++) {

			String data = elements.get(i).getText();
			column_data = new ArrayList<String>();
			// System.out.print("Value of quantity: " + data);
			column_data.add(data);
		}
		return column_data;

	}

	public void verifySetOfDistributorAccordingToDestination(String coulmn) {
		assertTrue(!captureDistributorDataForParticularColumn(coulmn).isEmpty(),
				"[ASSERTION FAILED]: Set of Distributors is not available for partcular destination");

	}

	public void verifyAccountNumberFieldIsBlank(String coulmn) {
		List<WebElement> elements = elements("text_column_distributor", coulmn);
		for (WebElement ele : elements) {
			String newVal = ele.getAttribute("value");
			assertEquals(newVal, null, "[ASSERTION FAILED]: Account Numbers are not blank by-default");
		}

	}

	public void verifyAccountNumberFieldIsBlank() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		List<WebElement> elements = elements("text_column_distributor");
		for (WebElement ele : elements) {
			String newVal = ele.getAttribute("value");
			assertEquals(newVal, "", "[ASSERTION FAILED]: Account Numbers are not blank by-default");
		}

	}

	public void verifyAccountNumberFieldIsDisabled(String coulmn) {
		List<WebElement> elements = elements("text_column_distributor", coulmn);
		logMessage("Account Number List is loaded");
		for (WebElement ele : elements) {
			// String value = ele.getAttribute("disabled");
			Assert.assertEquals(ele.isEnabled(), false,
					"[ASSERTION FAILED]: Account Numbers are not disabled by-default");
		}

	}

	public boolean verifyColumnHeader(String[] columnHeaders) {
		flag = false;
		int count = 0;
		for (String col : columnHeaders) {
			if (isElementDisplayed("sort_icon", col)) {
				count++;
			}
		}

		if (count == 2) {
			flag = true;
		}
		return flag;
	}

	public boolean verifyColumnHeaderForDosageForm(String[] columnHeaders) {
		flag = false;
		int count = 0;
		for (String col : columnHeaders) {
			if (isElementDisplayed("sort_icon", col)) {
				count++;
			}
		}

		if (count == 4) {
			flag = true;
		}
		return flag;
	}

	public void verifyCheckboxIsAvailableInFrontOfDistributorName(String fieldName) {
		for (int i = 0; i < elements("text_column_distributor", "1").size(); i++) {
			assertTrue(checkCheckboxIsEnabledOrDisabledUsingJavaScript(fieldName + -i), "checkbox" + i + "is missing");

		}

	}

	public void EnterRandomValueInAccountNumberField(String fieldName, String data) {
		isElementDisplayed("input_field_account_number", fieldName);
		enterTextInField(element("input_field_account_number", fieldName), data);
		// Assert.assertEquals(element("input_field_account_number",
		// fieldName).getAttribute("value").trim(), data);
	}

	public void clearRandomValueInAccountNumberField(String fieldName) {
		isElementDisplayed("input_field_account_number", fieldName);
		element("input_field_account_number", fieldName).clear();
		// Assert.assertEquals(element("input_field_account_number",
		// fieldName).getAttribute("value").trim(), data);
	}

	public boolean verifyErrorMessage(String mesg) {
		return isElementDisplayed("message_field", mesg);
	}

	public void verifyErrorMessageonItemscreen(String message) {

		Assert.assertTrue(isElementDisplayed("error_message_routingrule"),
				"[ASSERTION Passed]: Validation message for mandatory field is visible" + message);
		logMessage("[ASSERTION PASSED]: Validation message for mandatory field visible : " + message);
	}

	public void clickEditLink(String destination) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("link_edit_dest", destination);
		element("link_edit_dest", destination).click();
		// Assert.assertTrue(element("page_heading", "Edit a
		// Destination").isDisplayed());
	}

	public void clickPopup(String command) {
		wait.hardWait(12);
		wait.applyFluentWait(getLocator("popup_destinations"), 60, 500);
		isElementDisplayed("popup_destinations", command);
		element("popup_destinations", command).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 210);
	}

	public boolean verifyDistributorIsNotAddedForParticularDestination(String data) {

		if (!isElementNotDisplayed("added_distributor_name", data)) {
			logMessage("[ASSERTION PASSED]: Newly added record : " + data + " is not displayed in the List");
		}

		return isElementNotDisplayed("added_distributor_name", data);
	}

	public void verifyDistributorIsAddedForParticularDestination(String data) {

	}

	public int verifyMaxLengthForAccountNumberField(String id) {
		isElementDisplayed("input_field_account_number", id);
		int len = len = Integer.parseInt(element("input_field_account_number", id).getAttribute("maxlength").trim());
		return len;
	}

	public void clickSiteConfigBreadCrumbonDestination() {
		isElementDisplayed("siteconfig_breadcrumb_destiantion");
		clickUsingXpathInJavaScriptExecutor(element("siteconfig_breadcrumb_destiantion"));
		logMessage("Clicked on site configuration breadcrumb on view destinations screen");

	}

	public String selectValueForFacilityDropDown(String fieldName, String data) {
		selectProvidedTextFromDropDown(element("facilitydropdown_destination", fieldName), data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("facilitydropdown_destination", fieldName)), data);

		return data;
	}

	public List<String> getListOfAvailableUsers() {
		List<String> listUserName = new ArrayList<String>();
		for (WebElement ele : elements("list_userName")) {
			listUserName.add(ele.getText().trim());
		}
		return listUserName;
	}

	public void clickOnSortedIcon(String colName, String order) {
		isElementDisplayed("table_column", colName);
		isElementDisplayed("parent_column", colName);
		if (element("parent_column", colName).getAttribute("class").contains(order)) {
			Assert.assertTrue(element("parent_column", colName).getAttribute("class").contains(order));
		} else {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("table_column", colName));
			Assert.assertTrue(element("parent_column", colName).getAttribute("class").contains(order));
		}

		logMessage("[Step]: Clicked on Sorted Icon for column " + colName + "in " + order + " order");
	}

	public void selectCheckboxCorresspondingToName(String name, boolean b) {
		isElementDisplayed("label_chkbox_userName", name);
		String id = element("label_chkbox_userName", name).getAttribute("for").trim();
		// isElementDisplayed("inp_chkbox_userName", name);
		if (b) {
			if (checkboxIsSelectedUsingJavascript(id)) {
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(id));
			} else {
				element("label_chkbox_userName", name).click();
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(id));
			}
			logMessage("Checkbox for user name" + name + " is checked");
		} else {
			if (checkboxIsSelectedUsingJavascript(id)) {
				Assert.assertFalse(checkboxIsSelectedUsingJavascript(id));
			} else {
				element("label_chkbox_userName", name).click();
				Assert.assertFalse(checkboxIsSelectedUsingJavascript(id));
			}
			logMessage("Checkbox for user name" + name + " is unchecked");
		}

	}

	public void verifyNewlyAddedRecordNameInList(String recordName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		try {
			isElementDisplayed("added_hold_reason_name", recordName);
			Assert.assertTrue(element("added_hold_reason_name", recordName).isDisplayed());
			logMessage("[ASSERTION PASSED]: Newly added record : " + recordName + " is displayed in the List");
		} catch (Exception e) {
			isElementDisplayed("list_columnHeaders", recordName);
			Assert.assertTrue(element("list_columnHeaders", recordName).isDisplayed());
			logMessage("[ASSERTION PASSED]: Newly added record : " + recordName + " is displayed in the List");

		}

	}
	
	public void verifynoresultsfound(String recordName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);		
		Assert.assertTrue(element("no_item_found", recordName).isDisplayed(),"Element found");
		logMessage("[ASSERTION PASSED]: Newly added incomplete item is not displayed in the List");
		
	}


	public boolean isRecordVisible(String text) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		return isElementDisplayed("record_name", text);
	}

	public boolean verifyRecordName(String recordName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		return isElementDisplayed("list_columnHeaders", recordName);
	}

	public boolean verifyInactiveAddedRecordNameInList(String holdReasonName) {
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);

			isElementDisplayed("added_hold_reason_name", holdReasonName);
			return element("added_hold_reason_name", holdReasonName).isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public void verifyNewlyAddedPriorityInList(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		// wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);

		isElementDisplayed("added_priority", fieldName);
		Assert.assertTrue(element("added_priority", fieldName).isDisplayed());
		logMessage("[ASSERTION PASSED]: Newly added record : " + fieldName + " is displayed in the List");

	}

	public String getRuleNameTextBoxValue() {
		String name = element("inputboxRoutingRule").getAttribute("value");
		return name;

	}

	public boolean verifyAllRadioButtonIsUncheckedOrChecked(String type) {
		List<WebElement> elements = elements("checkbox_allRadioButton", type);
		String id = null;
		boolean flag = true;
		for (WebElement ele : elements) {
			id = element("checkbox_allRadioButton", type).getAttribute("id");
			boolean value = checkCheckboxIsEnabledOrDisabled(id);
			if (!value) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public boolean verifyRadioButtonIsChecked(String id) {

		return checkCheckboxIsEnabledOrDisabled(id);
	}

	public boolean verifyRadioButtonIsEnabledOrDisabled(String id) {
		return checkRadioButtonIsEnabledOrDisabledUsingJavaScript(id);
	}

	public void verifyHeader(String option) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		wait.waitForElementToBeVisible(element("header_page", option));
		Assert.assertTrue(isElementDisplayed("header_page", option));

	}

	public void verifyHeaderWithoutWait(String option) {

		wait.waitForElementToBeVisible(element("header_page", option));
		Assert.assertTrue(isElementDisplayed("header_page", option));

	}

	public void verifyoptionHeaderonItemscreen(String option) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		wait.waitForElementToBeVisible(element("header_dist_account", option));
		Assert.assertTrue(isElementDisplayed("header_dist_account", option));

	}

	public void verifyFacilityListonItemScreen() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		wait.waitForElementToBeVisible(element("copy_facility_list"));
		Assert.assertTrue(isElementDisplayed("copy_facility_list"));

	}

	public String getNoDataText() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("text_no_data");
		return element("text_no_data").getText();
	}

	public void verifyToolTip(String colNumber) {
		wait.loadingWait(getLocator("loader"));
		wait.waitForElementsToBeVisible(elements("columns_first_row", colNumber));
		String data = element("columns_first_row", colNumber).getAttribute("title");
		System.out.println("columns_first_row: " + data);
		String verificationText = element("columns_first_row", colNumber).getText();
		System.out.println("verification text: " + verificationText);
		Assert.assertEquals(data, verificationText);
		logMessage("[ASSERTION PASSED]: ToolTip text has been verified.");

	}

	public void verifyCheckboxLabel(String fieldName, String labelText) {
		isElementDisplayed("parent_checkbox_printer", fieldName);
		Assert.assertTrue(element("parent_checkbox_printer", fieldName).getText().trim().contains(labelText));
		logMessage("[ASSERTION PASSED]: Verified label text " + labelText + " for checkbox " + fieldName);
	}

	public void verifyRoutingRuleDetails(String routingRuleName, String priority, String destination, String schedule) {
		wait.hardWait(5);

		Assert.assertTrue(isElementDisplayed("routing_rule_name", routingRuleName));
		logMessage("[ASSERTION PASSED]: " + routingRuleName + " is displayed in the List");
		Assert.assertTrue(
				elements("routing_rule_details", routingRuleName).get(0).getText().equalsIgnoreCase(priority));
		logMessage("[ASSERTION PASSED]: " + routingRuleName + " is displayed in the List");
		Assert.assertTrue(
				elements("routing_rule_details", routingRuleName).get(1).getText().equalsIgnoreCase(destination));
		logMessage("[ASSERTION PASSED]: " + routingRuleName + " is displayed in the List");
		Assert.assertTrue(
				elements("routing_rule_details", routingRuleName).get(2).getText().equalsIgnoreCase(schedule));
		logMessage("[ASSERTION PASSED]: " + routingRuleName + " is displayed in the List");

	}

	public void clickEditRoutingRuleButton() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 210);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 210);
		isElementDisplayed("edit_routing_rule");
		logMessage("ASSERTION PASSED: Verified EDIT Button");
		clickUsingXpathInJavaScriptExecutor(element("edit_routing_rule"));
		logMessage("Clicked on Edit");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 210);
	}

	public String getRoutingRuleName() {
		wait.hardWait(8);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		String ruleName = element("inputboxRoutingRule").getAttribute("value");
		System.out.println("Routing Rule name is: " + element("inputboxRoutingRule").getAttribute("value"));
		return ruleName;
	}

	public boolean verifyEditLinkUnderActionColumn() {
		wait.hardWait(20);
		List<WebElement> elements = elements("list_editAction");
		for (WebElement ele : elements) {
			if (ele.getText().trim().equalsIgnoreCase("Edit")) {
				return true;
			}

		}
		return false;
	}

	public int getEditLinkCount() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);

		List<WebElement> elements = elements("list_editAction");
		return elements.size();
	}

	public void compareEditLinkCount(int before, int after) {
		Assert.assertEquals(after, before);
		logMessage("ASSERTION PASSED: The Routing rule count did not change after Editing Rule");

	}

	public void verifyRoutingRuleDetails(String routingRuleName) {
		wait.hardWait(5);

		Assert.assertTrue(isElementDisplayed("routing_rule_name", routingRuleName));
		logMessage("[ASSERTION PASSED]: " + routingRuleName + " is displayed in the List");
	}

	public int verifyMaxLengthOfSearchField(String fieldName) {
		return Integer.parseInt(element("search").getAttribute("maxlength").trim());
	}

	public void verifyFieldIsReset(String fieldName) {
		Assert.assertTrue(element("search_box", fieldName).getAttribute("value").isEmpty(),
				"[ASSERT FAILED] Search box has text");

	}

	public boolean verifyDeleteLinkUnderActionColumn() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		List<WebElement> elements = elements("list_deleteAction");
		for (WebElement ele : elements) {
			if (ele.getText().trim().equalsIgnoreCase("Delete")) {
				return true;
			}
		}
		return false;
	}

	public void refreshPage() {
		pageRefresh();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}

	public void verifyDescriptionOnPriorities() {
		try {
			isElementDisplayed("routing_priorities");
			hover(element("routing_priorities"));
			Assert.assertTrue(element("routing_priorities_tooltip").isDisplayed());
			logMessage("[ASSERTION PASSED]: Description Text:" + element("routing_priorities_tooltip").getText()
					+ " is displayed.");
		} catch (Exception e) {
			logMessage("[ERROR MESSAGE]: Data creation required for Priorities");
		}
	}

	public boolean getRoutingRuleDetails() {
		wait.hardWait(3);
		List<WebElement> elements = elements("first_item");
		if (elements.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public void verifyDescriptionOnDestinations() {
		try {
			isElementDisplayed("routing_destinations");
			hover(element("routing_destinations"));
			Assert.assertTrue(element("routing_destinations_tooltip").isDisplayed());
			logMessage("[ASSERTION PASSED]: Description Text:" + element("routing_destinations_tooltip").getText()
					+ " is displayed.");
		} catch (Exception e) {
			logMessage("[ERROR MESSAGE]: Data creation required for Destinations");
		}
	}

	public void enterSearchTerminRoutingRule(String option) {
		wait.hardWait(7);
		isElementDisplayed("search_routing_rule");
		enterTextInField(element("search_routing_rule"), option);
	}

	public void selectCheckboxCorresspondingToFieldInDistributorAccount(String fieldName, boolean b, int size) {
		// System.out.println("CHECK SIZE" +
		// elements("text_column_distributor").size());
		for (int i = 0; i < size; i++) {

			if (b) {
				if (checkboxIsSelectedUsingJavascript(fieldName + "-" + i)) {
					Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName + -i));
				}

				else {
					try {
						element("parent_checkbox_printer", fieldName + "-" + i).click();
						// Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName
						// + "-" + i));
						System.out.println("IN TRYYYY");
					} catch (Exception e) {
						clickUsingXpathInJavaScriptExecutor(element("parent_checkbox_printer", fieldName + "-" + i));
						System.out.println("IN CATCH");
					}
					logMessage("Checkbox" + fieldName + "-" + i + " is checked");
				}

			} else {

				if (checkboxIsSelectedUsingJavascript(fieldName + "-" + i)) {
					try {
						element("text_column_distributor").click();
						// Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName
						// + "-" + i));

					} catch (Exception e) {
						clickUsingXpathInJavaScriptExecutor(element("parent_checkbox_printer", fieldName + "-" + i));
					}

				} else {
					Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName + "-" + i));
					logMessage("Checkbox" + fieldName + "-" + i + " is unchecked");
				}
			}
		}
	}

	public void clickOnAddButtonToAddFacility() {
		try {
			wait.applyFluentWait(getLocator("btn_add"), 60, 500);
			isElementDisplayed("btn_add");
			clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_add"));
			wait.applyFluentWait(getLocator("popup_add_facility"), 60, 500);
			Assert.assertTrue(element("popup_add_facility").getText().trim().contains("Add Facility"));
			logMessage("[ASSERTION PASSED]: Verified Add Facility header on clicking add button");

			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		} catch (Exception e) {
			wait.applyFluentWait(getLocator("btn_add"), 60, 500);
			isElementDisplayed("btn_add");
			clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_add"));
			wait.applyFluentWait(getLocator("popup_add_facility"), 60, 500);
			Assert.assertTrue(element("popup_add_facility").getText().trim().contains("Add Facility"));
			logMessage("[ASSERTION PASSED]: Verified Add Facility header on clicking add button");

			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		}

	}

	public void verifyInputFieldOnAddNewFacility(String fieldName) {

		isElementDisplayed("inp_field_printer", fieldName);
		logMessage("[ASSERTION PASSED]: Verified input field for - " + fieldName);

	}

	public boolean verifyDropDownIsEnabledOrDisabledForFacility(String string) {
		isElementDisplayed("externalsys_facility_dropdown", string);
		boolean value = element("externalsys_facility_dropdown", string).isEnabled();
		return value;
	}

	public String EnterValueInInputFieldOnAddNewFacility(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.loadingWait(getLocator("loader"));

		enterTextInField(element("inp_field_printer", fieldName), data);
		if (fieldName.equalsIgnoreCase("labelBarcode")) {
			Assert.assertEquals(element("inp_field_printer", fieldName).getAttribute("value").trim().length(), 1000);
			logMessage("[ASSERTION PASSED]: Verfied labelBarcode field can contain only 1000 character value ");
		} else {
			if (element("inp_field_printer", fieldName).getAttribute("value").contains(data)) {
				Assert.assertTrue(element("inp_field_printer", fieldName).getAttribute("value").contains(data));
			} else {
				enterTextInField(element("inp_field_printer", fieldName), data);
			}
		}

		return data;
	}

	public String verifyInputFieldIsAutopopulated(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 6);
		System.out.print("value is %%%%%%%5" + element("inp_field_printer", fieldName).getAttribute("value"));
		return element("inp_field_printer", fieldName).getAttribute("value");

	}

	public List<String> getAllDataFromDropDown(String fieldName) {
		List<String> list = new ArrayList<String>();
		try {
			for (WebElement ele : elements("dropdownoption_printer", fieldName)) {
				list.add(ele.getText().trim());
			}
			System.out.println("LIST RETURNED AS " + list);
			return list;
		} catch (Exception e) {
			for (WebElement ele : elements("dropdownoption_printer", fieldName)) {
				list.add(ele.getText().trim());
			}
			System.out.println("LIST RETURNED AS " + list);
			return list;
		}
	}

	public void clearInputBox(String fieldname) {

		clickUsingXpathInJavaScriptExecutor(element("inp_field_printer", fieldname));
		wait.hardWait(3);
		element("inp_field_printer", fieldname).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		wait.hardWait(5);

	}

	public void clearInputBoxTransactionPriority(String fieldname) {

		clickUsingXpathInJavaScriptExecutor(element("input_field", fieldname));
		wait.hardWait(3);
		element("input_field", fieldname).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		wait.hardWait(3);

	}

	public void clickOnEditLinkCorresspondingToFacilityName(String printerName) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 80);
		try {
			scrollDown(element("link_edit_sanity", printerName));
			isElementDisplayed("link_edit_sanity", printerName);
			// if (isBrowser("edge") || isBrowser("Edge"))
			clickUsingXpathInJavaScriptExecutorSingleClick(element("link_edit_sanity", printerName));

			logMessage("Clicked on edit link corressponding to facility " + printerName);

			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
			wait.waitForElementToBeVisible(element("popup_add_facility"));
			Assert.assertTrue(element("popup_add_facility").getText().trim().contains(printerName));
			logMessage("[ASSERTION PASSED]: Verified heading on edit facility pop up");

		} catch (Exception e) {
			if (isBrowser("edge") || isBrowser("Edge")) {
				clickUsingXpathInJavaScriptExecutor(element("link_edit_sanity", printerName));
			} else {
				(element("link_edit_sanity", printerName)).click();
			}
			logMessage("Clicked on edit link corressponding to facility " + printerName);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
			// wait.waitForElementToBeVisible(element("popup_add_facility"));
			Assert.assertTrue(element("popup_add_facility").getText().trim().contains(printerName));
			logMessage("[ASSERTION PASSED]: Verified heading on edit facility pop up");
		}
	}

	public void clickOnEditLinkCorresspondingToFacilityName(String printerName, String PopupMsg) {
		try {
			isElementDisplayed("search_box", "search");
			enterTextInField(element("search_box", "search"), printerName);
			Assert.assertEquals(element("search_box", "search").getAttribute("value").trim(), printerName);
			logMessage("[ASSERTION PASSED]: Search Term as : " + printerName + " is displayed in the Search Field");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
			isElementDisplayed("link_edit_sanity", printerName);
			clickUsingXpathInJavaScriptExecutor(element("link_edit_sanity", printerName));
			logMessage("Clicked on Edit link corressponding to facility " + printerName);
			// wait.waitForElementToBeVisible(element("popup_add_facility"));
			wait.hardWait(5);
			Assert.assertTrue(element("popup_add_facility").getText().trim().contains(printerName));
			logMessage("[ASSERTION PASSED]: Verified Edit a Facility pop up");
		} catch (Exception e) {
			wait.hardWait(10);
			clickUsingXpathInJavaScriptExecutor(element("link_edit_sanity", printerName));
			logMessage("Clicked on Edit link corressponding to facility " + printerName);
			wait.hardWait(5);
			// wait.waitForElementToBeVisible(element("popup_add_facility"));
			Assert.assertTrue(element("popup_add_facility").getText().trim().contains(printerName));
			logMessage("[ASSERTION PASSED]: Verified Edit a Facility pop up");
		}
	}

	public void navigateToFcailityBreadcrumb() {
		isElementDisplayed("breadcrumb_facility");
		clickUsingXpathInJavaScriptExecutor(element("breadcrumb_facility"));
		logMessage("[ASSERTION PASSED]: Navigated to Add Facility");

	}

	public void verifyTransactionQueueOptionsareVisible() {
		isElementDisplayed("facilities_tq_options_header");
		logMessage("[ASSERTION PASSED]: Transaction Queue options are available on Settings tab");

	}

	public void verifyCheckboxForTQFields(String checkboxField, String fieldName) {
		isElementDisplayed("checkbox_tq_options", checkboxField, fieldName);
		logMessage("[ASSERTION PASSED]:Checkbox is available corresponding to field: " + fieldName);
	}

	public void verifyCheckboxTQFields(String fieldName) {
		isElementDisplayed("checkbox_tq_options1", fieldName);
		logMessage("[ASSERTION PASSED]:Checkbox is available corresponding to field: " + fieldName);
	}

	public void verifyAllButtonIsSelected() {
		List<WebElement> elements = elements("am_buttons");
		List<WebElement> elements1 = elements("pm_buttons");

		boolean allAmSelected = true;
		boolean allPmSelected = true;

		for (WebElement ele : elements) {
//			if ((ele).getAttribute("class").trim().contains("enable")) {
//				logMessage("ASSERTION PASSED]:All AM buttons are selected");
//			}
			allAmSelected = allAmSelected && (ele).getAttribute("class").trim().contains("enable");
		}

		for (WebElement elem : elements1) {
//			if ((elem).getAttribute("class").trim().contains("enable")) {
//				logMessage("ASSERTION PASSED]:All PM buttons are selected");
//			}
			allPmSelected = allPmSelected && (elem).getAttribute("class").trim().contains("enable");
		}

		Assert.assertTrue(allAmSelected, "ASSERTION FAILED: All AM buttons are not enabled");
		logMessage("ASSERTION PASSED]:All AM buttons are selected");
		Assert.assertTrue(allPmSelected, "ASSERTION FAILED: All PM buttons are not enabled");
		logMessage("ASSERTION PASSED]:All PM buttons are selected");
	}

	public void getButtonColor(String color, String day) {
		int i;
		System.out.println("COLOR FOR UI" + element("btn", day).getCssValue("color"));
		String col = element("btn", day).getCssValue("color").replaceAll("\\s", "").replace("rgba", "").replace("(", "")
				.replace(")", "");
		i = col.indexOf(',', 1 + col.indexOf(',', 1 + col.indexOf(',')));
		if (isBrowser("edge")) {
			System.out.println("COLUMN DATA" + col.replace("rgb", ""));
			i = col.indexOf(',', col.indexOf(',', col.indexOf(',')));
			Assert.assertEquals(col.replace("rgb", ""), color);
		} else {

			String btn_color = col.substring(0, i);
			System.out.println("COLOR FOR DATA" + color);
			Assert.assertEquals(btn_color, color);
			logMessage("[ASSERTION PASSED]: Verified the color of button");
		}
	}

	public void clickScheduleButton(String day) {

		Actions action = new Actions(driver);
		System.out.println("day" + day);
		Action seriesOfAction = (Action) action.moveToElement(element("btn", day)).click().build();
		seriesOfAction.perform();
		logMessage("Schedule button clicked");

	}

	public void verifyCycleCountMessage(String message) {
		isElementDisplayed("cycle_Count_heading");
		logMessage("Verified message on page:" + element("cycle_Count_heading").getText());
		isElementDisplayed("msg_cycle_count");
		logMessage("msgg" + element("msg_cycle_count").getText().replaceAll("\\s", ""));
		Assert.assertEquals(element("msg_cycle_count").getText().replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", ""),
				message);
		logMessage("Verified message on page:" + element("msg_cycle_count").getText());

	}

	public boolean verifyButtonIsDisabled(String id) {
		isElementDisplayed("button", id);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		System.out.println("return document.getElementById('" + id + "').disabled;");
		return (boolean) executor.executeScript("return document.getElementById('" + id + "').disabled;");
	}

	public boolean verifyButtonIsDisabledUOM(String id) {
		isElementDisplayed("button_predfined_uom", id);
		return verifybuttonisDisabled(id);
	}

	public void verifyRowbuttonsDisabled(String day) {
		isElementDisplayed("row_buttons", day);
		List<WebElement> elements = elements("row_buttons", day);
		for (WebElement ele : elements) {
			(ele).getAttribute("class").trim().contains("disable");
		}
		logMessage("ASSERTION PASSED: All Buttons are disabled");

		/*
		 * isElementDisplayed("row_buttons_am",day); List<WebElement> elements =
		 * elements("row_buttons_am",day); for (WebElement ele : elements) {
		 * (ele).getAttribute("class").trim().contains("disable"); }
		 * logMessage("ASSERTION PASSED: All AM Buttons are disabled");
		 * 
		 * isElementDisplayed("row_buttons_pm",day); List<WebElement> elements_pm =
		 * elements("row_buttons_pm",day); for (WebElement ele : elements_pm) {
		 * (ele).getAttribute("class").trim().contains("disable"); }
		 * logMessage("ASSERTION PASSED: All PM Buttons are disabled");
		 */
	}

	public void verifyCheckboxFieldOnCycleCount(String fieldName) {
		isElementDisplayed("parent_checkbox_cyclecount", fieldName);
		Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName),
				"[ASSERTION FAILED]: Checkbox for " + fieldName + "is checked by default");
		logMessage("[ASSERTION PASSED]: Verified checkbox field: for" + fieldName + " and is not checked");
	}
	
	public void clickCheckboxFieldOnMedicationClass(String fieldName) {
		isElementDisplayed("parent_checkbox_cyclecount", fieldName);
		element("parent_checkbox_cyclecount", fieldName).click();
	}

	public void verifyStartTime(String time) {
		isElementDisplayed("start_am_button");
		Assert.assertEquals(element("start_am_button").getText(), time);
		logMessage("[ASSERTION PASSED]: Verified Start Time for Cycle Count" + element("start_am_button").getText());

	}

	public void verifyOtherfieldsOptionsareVisible() {
		isElementDisplayed("facilities_otherfields_options_header");
		logMessage("[ASSERTION PASSED]: Other fields options are available on Settings tab");
	}

	public void verifyDropDownFieldOnEditRestockPrinterPopup(String fieldName) {
		isElementDisplayed("dropdown_printer", fieldName);
		logMessage("[ASSERTION PASSED]: Verified dropdown field for " + fieldName);
	}

	public void clickOnEditLinkCorresspondingToISAName(String ISAName) {
		isElementDisplayed("link_ISAedit", ISAName);
		clickUsingXpathInJavaScriptExecutor(element("link_ISAedit", ISAName));
		logMessage("Clicked on Edit link corressponding to ISA " + ISAName);

	}

	public void clickCycleCountCalendar() {

		String[] strMonths = new String[] { "January", "February", "March", "April", "May", "Jun", "Jul", "Aug", "Sep",
				"Oct", "Nov", "Dec" };
		isElementDisplayed("cyclecount_calendar");
		// clickUsingXpathInJavaScriptExecutor(element("cyclecount_calendar"));
		// element("cyclecount_calendar").click();
		wait.waitForElementToBeClickable(element("cyclecount_calendar"));
		wait.hardWait(3);
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("cyclecount_calendar")).click().build();
		seriesOfAction.perform();

		logMessage("Clicked on Calendar icon");
		wait.hardWait(5);
		// System.out.println("Data is:"+LocalDate.now());
		Calendar now = Calendar.getInstance();

		int year = now.get(Calendar.YEAR);

		String month = strMonths[now.get(Calendar.MONTH)];
		int date = now.get(Calendar.DATE);
		System.out.println(year);
		System.out.println(month);
		System.out.println(Integer.toString(date));

		System.out.println("Current month is : " + strMonths[now.get(Calendar.MONTH)]);

	}

	public int getCurrentDate() {
		Calendar now = Calendar.getInstance();
		int date = now.get(Calendar.DATE);
		return date;
	}

	public String getMonth() {
		String[] strMonths = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };
		Calendar now = Calendar.getInstance();
		String month = strMonths[now.get(Calendar.MONTH)];
		return month;

	}

	public String getCurrentMonth_Year() {
		String[] strMonths = new String[] { "January", "February", "March", "April", "May", "Jun", "Jul", "Aug", "Sep",
				"Oct", "Nov", "Dec" };
		String[] daysOfWeek = new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
				"Sunday" };
		Calendar now = Calendar.getInstance();

		String month = strMonths[now.get(Calendar.MONTH)];
		int year = now.get(Calendar.YEAR);
		String currentyear = Integer.toString(year);
		int date = now.get(Calendar.DATE);
		String currentdate = Integer.toString(date);
		int currentDay = now.get(Calendar.DAY_OF_WEEK);
		String current = month + " " + currentdate + " " + currentyear + " , " + daysOfWeek[currentDay];
		return current;

	}

	public void ClickCurrentDate(String date) {
		int a = getCurrentDate();
		wait.hardWait(8);
		clickUsingXpathInJavaScriptExecutor(element("today_date", Integer.toString(a)));
		logMessage("ASSERTION PASSED: Clicked on Date from calendar");

	}

	public void clickAddButton() {
		isElementDisplayed("add_btn_cyclecount");
		clickUsingXpathInJavaScriptExecutor(element("add_btn_cyclecount"));
		logMessage("ASSERT PASSED: User clicks on Add button");
		wait.hardWait(3);
	}

	public boolean verifyDisableDate(String date) {
		if (isElementNotDisplayed("disabled_list", date) == true) {
			logMessage("ASSERT PASSED: The selected Date is displayed under the disabled Dates list");

			return false;
		}

		else {
			logMessage("ASSERT PASSED: The Date is not displayed under the disabled Dates list");

			return true;
		}
	}

	public void verifyRemoveButton() {
		isElementDisplayed("remove_btn_cyclecount");
		logMessage("ASSERT PASSED: Verified Remove button against the Disabled dates");

	}

	public void clickRemoveButton() {
		isElementDisplayed("remove_btn_cyclecount");
		clickUsingXpathInJavaScriptExecutor(element("remove_btn_cyclecount"));
		logMessage("ASSERT PASSED: Clicked on Remove button");
		wait.hardWait(2);

	}
	
	public void clickRemovebuttonitem(String value)
	{		
		isElementDisplayed("remove_item_from_listofitem",value);		
		clickUsingXpathInJavaScriptExecutor(element("remove_item_from_listofitem",value));
		logMessage("ASSERT PASSED: Clicked on Remove button");
	}

	public void verifyPlaceHolderForCycleCount(String value) {

		System.out.println(element("cyclecount_calendar").getAttribute("placeholder").trim());
		Assert.assertTrue(element("cyclecount_calendar").getAttribute("placeholder").trim().equalsIgnoreCase(value));

	}

	public void verifyEndTime(String time) {
		isElementDisplayed("end_pm_button");
		Assert.assertEquals(element("end_pm_button").getText(), time);
		logMessage("[ASSERTION PASSED]: Verified End Time for Cycle Count" + element("end_pm_button").getText());

	}

	public boolean verifySuccessMessageOnAddFacility(String successMessage) {
		isElementDisplayed("popup_message");

		Assert.assertTrue(element("popup_message").getText().trim().contains(successMessage));
		logMessage("[ASSERTION PASSED]: Verified Add a new Facility pop up on clicking add button");
		wait.hardWait(3);
		flag = true;
		return flag;

	}

	public void verifyNewlyAddedFacilityNameInFacilityMgtList(String recordName) {
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
			isElementDisplayed("link_edit_of_added_record", recordName);
			Assert.assertTrue(element("link_edit_of_added_record", recordName).isDisplayed());
			logMessage("[ASSERTION PASSED]: Newly added record : " + recordName + " is displayed in the list");
		} catch (Exception e) {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
			isElementDisplayed("link_edit_of_added_record", recordName);
			Assert.assertTrue(element("link_edit_of_added_record", recordName).isDisplayed());
			logMessage("[ASSERTION PASSED]: Newly added record : " + recordName + " is displayed in the list");
		}
	}

	public void clickCheckboxTransactionPriorities(String flagname) {
		try {
			isElementDisplayed("label_TransactionPriorities", flagname);
			wait.waitForElementToBeClickable(element("label_TransactionPriorities", flagname));
			wait.elementHighlight(element("label_TransactionPriorities", flagname));
			// click(element("label_TransactionPriorities", flagname));
			if (!checkboxIsSelectedUsingJavascript(flagname)) {
				clickUsingXpathInJavaScriptExecutorSingleClick(element("label_TransactionPriorities", flagname));
			}
		} catch (Exception e) {
			isElementDisplayed("distributor_account_checkbox", flagname);
			// click(element("label_TransactionPriorities", flagname));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("distributor_account_checkbox", flagname));
		}
	}

	public void clickCheckboxBatchTransactionPriorities(String flagname) {
		wait.hardWait(20);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("checkbox_batchEditFacility", flagname);
		wait.waitForElementToBeClickable(element("checkbox_batchEditFacility", flagname));
		wait.elementHighlight(element("checkbox_batchEditFacility", flagname));
		// if (!checkboxIsSelectedUsingJavascript(flagname)) {
		clickUsingXpathInJavaScriptExecutorSingleClick(element("checkbox_batchEditFacility", flagname));
		// }
	}

	public void clickCheckboxAutoReleasTransactionPriorities(String flagname) {
		wait.hardWait(20);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("checkbox_autoReleaseEditFacility", flagname);
		wait.waitForElementToBeClickable(element("checkbox_autoReleaseEditFacility", flagname));
		wait.elementHighlight(element("checkbox_autoReleaseEditFacility", flagname));
		// if (!checkboxIsSelectedUsingJavascript(flagname)) {
		clickUsingXpathInJavaScriptExecutorSingleClick(element("checkbox_autoReleaseEditFacility", flagname));
		// }
	}

	public void editInputBoxBatchTransactionPriorities() {
		wait.hardWait(20);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("inputBox_batchEditFacility");
		enterTextInField(element("inputBox_batchEditFacility"), "0");
		isElementDisplayed("error_class");
		enterTextInField(element("inputBox_batchEditFacility"), "2");

	}

	public void uncheckCheckBox(String flagname) {

		try {
			isElementDisplayed("label_TransactionPriorities", flagname);
			wait.waitForElementToBeClickable(element("label_TransactionPriorities", flagname));
			wait.elementHighlight(element("label_TransactionPriorities", flagname));
			// click(element("label_TransactionPriorities", flagname));
			if (checkboxIsSelectedUsingJavascript(flagname)) {
				clickUsingXpathInJavaScriptExecutorSingleClick(element("label_TransactionPriorities", flagname));
			}
		} catch (Exception e) {
			isElementDisplayed("distributor_account_checkbox", flagname);
			// click(element("label_TransactionPriorities", flagname));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("distributor_account_checkbox", flagname));
		}
	}

	public void uncheckCheckBoxAsPerPriorityCode(String priorityName) {
		try {
			isElementDisplayed("adc_as_per_prioritycode", priorityName);
			wait.waitForElementToBeClickable(element("adc_as_per_prioritycode", priorityName));
			wait.elementHighlight(element("adc_as_per_prioritycode", priorityName));
			// click(element("label_TransactionPriorities", flagname));
			String flagname = element("adc_as_per_prioritycode", priorityName).getAttribute("for");
			if (checkboxIsSelectedUsingJavascript(flagname)) {
				clickUsingXpathInJavaScriptExecutorSingleClick(element("adc_as_per_prioritycode", priorityName));
			}
		} catch (Exception e) {
			isElementDisplayed("adc_as_per_prioritycode", priorityName);
			// click(element("label_TransactionPriorities", flagname));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("adc_as_per_prioritycode", priorityName));
		}
	}

	public void clickCheckboxDistributorOptions(String facility, String flagname) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		try {
			isElementDisplayed("distributorOptions_facility_checkbox", facility, flagname);
			// click(element("label_TransactionPriorities", flagname));
			clickUsingXpathInJavaScriptExecutorSingleClick(
					element("distributorOptions_facility_checkbox", facility, flagname));
		} catch (Exception e) {
			isElementDisplayed("distributorOptions_facility_checkbox", facility, flagname);
			// click(element("label_TransactionPriorities", flagname));
			clickUsingXpathInJavaScriptExecutorSingleClick(
					element("distributorOptions_facility_checkbox", facility, flagname));
		}
	}

	public boolean verifyCheckboxIsCheckedTransactionPriorities(String name) {
		String id = element("label_TransactionPriorities", name).getAttribute("for").trim();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		System.out.println("return document.getElementById('" + id + "').checked;");
		return (boolean) executor.executeScript("return document.getElementById('" + id + "').checked;");

	}

	public boolean verifyCheckboxIsCheckedDistributorOptions(String facility, String flagname) {
		String id = element("label_TransactionPriorities", facility, flagname).getAttribute("for").trim();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		System.out.println("return document.getElementById('" + id + "').checked;");
		return (boolean) executor.executeScript("return document.getElementById('" + id + "').checked;");

	}

	public boolean verifyCheckboxIsCheckedDestinationUsersTab(String name) {
		String id = element("label_TransactionPriorities", name).getAttribute("for").trim();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		System.out.println("return document.getElementById('" + id + "').checked;");
		return (boolean) executor.executeScript("return document.getElementById('" + id + "').checked;");

	}

	public boolean verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked(String name) {
		wait.hardWait(5);
		String rowNumber = Integer.toString(total_records);
		isElementDisplayed("checkbox_new", name, rowNumber);
		wait.elementHighlight(element("checkbox_new", name, rowNumber));
		String id = element("checkbox_new", name, rowNumber).getAttribute("for").trim();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		System.out.println("return document.getElementById('" + id + "').checked;");
		return (boolean) executor.executeScript("return document.getElementById('" + id + "').checked;");

	}

	public boolean verifyColumnHeaderOnFacilityTransactionPriorities(String columnName) {
		return isElementDisplayed("header_FacilityTransactionPriority", columnName);
	}

	public boolean verifydefaultValueOfBatchWait() {
		List<WebElement> ele = elements("list_batchwait");
		boolean result = true;
		for (int i = 0; i < ele.size(); i++) {
			int value = Integer.parseInt(ele.get(i).getAttribute("value"));
			System.out.println("Batch wait value " + (i + 1) + ": " + value);
			result = result && (value == 2);
//			if (value == 2) {
//				return true;
//			}
		}
		return result;
	}

	/*
	 * public void addPrinterDetails(String printer_detail,int i, String
	 * printerName){ String printerNameUpdated = printerName +
	 * System.currentTimeMillis();
	 * isElementDisplayed("inp_field_printer","serverPrinterName");
	 * element("inp_field_printer").sendKeys(printerNameUpdated);
	 * isElementDisplayed("inp_field_printer","printerName");
	 * element("inp_field_printer").sendKeys(printerNameUpdated);
	 * selectProvidedTextFromDropDown("", "QL-420 Plus");
	 * 
	 * }
	 */

	public ArrayList<String> sortDataForParticularColumnInDescendingOrder(String column) {
		ArrayList<String> data_compare = captureDataForParticularColumn(column);
		Collections.sort(data_compare, String.CASE_INSENSITIVE_ORDER);
		Collections.sort(data_compare, Collections.reverseOrder());
		return data_compare;
	}

	public ArrayList<String> sortDataForParticularColumnInDescendingOrderOnSchedule(String columnNum) {

		ArrayList<String> data_capture = captureDataForParticularColumnOnSchedule(columnNum);
		Collections.sort(data_capture, String.CASE_INSENSITIVE_ORDER);

		ArrayList<String> result = new ArrayList<String>();
		for (int i = data_capture.size() - 1; i >= 0; i--) {
			result.add(data_capture.get(i));
		}
		return result;

	}

	public boolean verifyFieldIsNotMandatory(String fieldName) {
		return isElementNotDisplayed("icon_mandatory", fieldName);
	}

	public boolean verifyInputFieldIsBlank(String fieldName) {
		if (element("inp_field_printer", fieldName).getText().trim().isEmpty()) {
			return true;
		}
		return false;
	}

	/*
	 * public boolean verifySortingForParticularColumn(ArrayList<String>
	 * previous_data, ArrayList<String> sorted_data) {
	 * 
	 * int flag = 0; int count = previous_data.size();
	 * 
	 * for (String item : previous_data) { for (String item1 : sorted_data) { if
	 * (item.equalsIgnoreCase(item1)) { flag++; } } } if (flag == count) {
	 * logMessage("[ASSERTION PASSED] : Coulmn data is sorted in provided order" );
	 * return true; } else {
	 * logMessage("[ASSERTION FAILED] : Coulmn data is not sorted in provided order"
	 * ); return false; }
	 * 
	 * }
	 */

	/*
	 * public String enterRandomValueInRichInputField() {
	 * wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	 * isElementDisplayed("dropdown_itemManagement"); List<WebElement> list;
	 * clickUsingXpathInJavaScriptExecutorSingleClick(element(
	 * "dropdown_itemManagement"));
	 * //enterTextInField(element("dropdown_itemManagement"), data);
	 * List<WebElement> elements = elements("dropdown_itemManagement_options");
	 * wait.waitForElementsToBeVisible(elements);
	 * 
	 * int size = elements.size(); try{
	 * 
	 * for (int i = 0; i <= size - 1; i++) {
	 * 
	 * 
	 * elements.get(i).click();
	 * 
	 * logMessage("Option" + elements.get(i) + "is selected");
	 * wait.waitForLoaderToBeInvisible(getLocator("loader"), 60); list =
	 * elements("item_result"); wait.waitForElementsToBeVisible(list);
	 * if(!list.isEmpty()){ String data=list.get(i).getText(); return data; }else{
	 * continue; }
	 * 
	 * }
	 * 
	 * } catch(Exception e){ for (int i = 0; i <= size - 1; i++) {
	 * elements.get(i).click();
	 * 
	 * logMessage("Option" + elements.get(i) + "is selected");
	 * wait.waitForLoaderToBeInvisible(getLocator("loader"), 60); list =
	 * elements("item_result"); wait.waitForElementsToBeVisible(list);
	 * if(!list.isEmpty()){ String data=list.get(i).getText(); return data; }else{
	 * continue; } } } return data;
	 * 
	 * 
	 * }
	 */

	/*
	 * public void clickActiveToggle() { isElementDisplayed("active_toggle_label");
	 * Actions action = new Actions(driver); Action seriesOfAction = (Action)
	 * action.moveToElement(element("active_toggle_label")).click()
	 * .keyDown(element("active_toggle_label"),
	 * Keys.CONTROL).sendKeys(Keys.SPACE).build();
	 * 
	 * seriesOfAction.perform();
	 * 
	 * logMessage("Active Toggle is Clicked...!!!!!");
	 * 
	 * }
	 */

	/*
	 * public boolean selectCheckBoxOfNewlyCreatedTransactionPriority(String
	 * fieldName){ selectCheckbox(fieldName, b); return flag; }
	 */

	public void verifyErrorMessageonAlert(String message) {
		wait.hardWait(7);
		String spanMessage = element("text_error_msg").getText();
		System.out.println("Value of message:  " + spanMessage);
		Assert.assertEquals(spanMessage, message);

	}

	/*
	 * }
	 * 
	 * for (WebElement ele : elements) { searchResults.add(ele.getText().trim());
	 * System.out.println(searchResults); } for (WebElement s : elements) {
	 * System.out.println("s=" +s.getText());
	 * 
	 * if (!s.getText().contains(searchedData)) { return false; } else { continue; }
	 * } return true;
	 */

	public void clickCheckbox(String toggle) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		try {
			wait.waitForElementToBeClickable(element("action_toggle_button_1", toggle));
			wait.elementHighlight(element("action_toggle_button_1", toggle));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("action_toggle_button_1", toggle));

		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("action_toggle_button_1", toggle));
		}
	}

	public void selectCheckboxItemsTab(String fieldName, boolean b) {
		System.out.println("Field name: " + fieldName + "; boolean: " + b);
		if (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("edge")) {
			isElementDisplayed("checkbox_item_tab", fieldName);

			if (fieldName.equals("printCompositLabelFlag") || fieldName.equals("scanVerifyFlag")) {
				scrollDown(element("checkbox_item_tab", fieldName));
			} else {
				scrollUp();
			}
			if (b) {
				if (checkboxIsSelectedUsingJavascript(fieldName)) {
					Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
				} else {
					element("checkbox_item_tab", fieldName).click();
					Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
				}

				logMessage("[ASSERTION PASSED]: Checkbox '" + fieldName + "' is checked");

			} else {
				if (checkboxIsSelectedUsingJavascript(fieldName)) {
					element("checkbox_item_tab", fieldName).click();
					Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
				} else {
					Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
				}
				logMessage("[ASSERTION PASSED]: Checkbox '" + fieldName + "' is unchecked");
			}

		} else {

			if (fieldName.equals("printCompositLabelFlag") || fieldName.equals("scanVerifyFlag")) {
				scrollDown(element("checkbox_item_tab", fieldName));
			} else {
				scrollUp();
			}
			if (b) {
				isElementDisplayed("checkbox_item_tab", fieldName);
				if (checkboxIsSelectedUsingJavascript(fieldName)) {
					Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
				} else {
					isElementDisplayed("checkbox_item_tab", fieldName);
					element("action_toggle_button_1", fieldName).click();

					Assert.assertTrue(checkboxIsSelectedUsingJavascript(fieldName));
					logMessage("[ASSERTION PASSED]: Checkbox '" + fieldName + "' is checked");
				}
			} else {
				if (checkboxIsSelectedUsingJavascript(fieldName)) {
					isElementDisplayed("action_toggle_button_1", fieldName);
					wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
					element("action_toggle_button_1", fieldName).click();
					Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
				} else {
					isElementDisplayed("checkbox_item_tab", fieldName);
					Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName));
				}
				logMessage("[ASSERTION PASSED]: Checkbox '" + fieldName + "' is unchecked");
			}
		}
	}

	public void verifyLinkText(String fieldName) {
		isElementDisplayed("link_footer_routingRule", fieldName);

	}

	public void verifyAddSchedulePopup(String popupText) {

		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
			wait.applyFluentWait(getLocator("add_schedule_popup"), 60, 500);
			Assert.assertTrue(element("add_schedule_popup", popupText).isDisplayed());
			logMessage("[ASSERTION PASSED]: Verified 'Add Schedule' pop up on clicking add button");
		} catch (Exception e) {
			wait.applyFluentWait(getLocator("add_schedule_popup"), 60, 500);
			Assert.assertTrue(element("add_schedule_popup", popupText).isDisplayed());
			logMessage("[ASSERTION PASSED]: Verified 'Add Schedule' pop up on clicking add button");
		}

	}

	public boolean verifyPopupGetsClosed() {
		return isElementNotDisplayed("add_popup");
	}

	public boolean verifySearchFieldonPickSchedulePage() {
		return isElementDisplayed("search");
	}

	public void verifyFieldIsMandatory(String string, String type) {
		isElementDisplayed("icon_mandatory_schedule", type);
		Assert.assertTrue(element("icon_mandatory_schedule", type).getText().trim().equalsIgnoreCase("*"));
	}

	public void clickActionbutton(String action) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		try {
			isElementDisplayed("action_button1", action);
			hover(element("action_button1", action));
			wait.waitForElementToBeClickable(element("action_button1", action));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button1", action));

			logMessage("[STEP]: Clicked on  '" + action + "' button");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		} catch (Exception e) {
			wait.waitForElementToBeClickable(element("action_button1", action));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button1", action));
			logMessage("[STEP]: Clicked on '" + action + "' button");
		}

	}

	
	public void clickCancelButton(String action) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		try {
			isElementDisplayed("action_button3", action);
			hover(element("action_button3", action));
			wait.waitForElementToBeClickable(element("action_button3", action));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button3", action));

			logMessage("Clicked on  '" + action + "' button");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		} catch (Exception e) {
			wait.waitForElementToBeClickable(element("action_button3", action));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button3", action));
			logMessage("Clicked on '" + action + "' button");
		}

	}

	
	
	public void actionButtonIsDisabled(String action) {
		isElementNotDisplayed("action_button1", action);
	}

	public boolean packagesizeIsDisabled()
	{
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		if(element("package_size").isEnabled())
		{
			return false;
		}
	return true;	
		
	}
	
	public boolean packagesizeIsenabled()
	{
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		if(element("package_size").isEnabled())
		{
			return true;
		}
	return false;	
		
	}
	public void clickAddNewItemPOP(String command) {
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("Add_new_item_actions", command);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("Add_new_item_actions", command));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}

	public void clickfacilityLevel(String command) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("facility_level", command);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("facility_level", command));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}

	public void clickAddNewClassonTherapeuticClass(String command) {
		isElementDisplayed("Add_new_item_actions1", command);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("Add_new_item_actions1", command));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);

	}

	// wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	// isElementDisplayed("Add_new_item_actions1", command);
	// clickUsingXpathInJavaScriptExecutorSingleClick(element("Add_new_item_actions1",
	// command));
	// Assert.assertTrue(element("Add_new_item_actions1",
	// command).isDisplayed());
	// wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);

	public void clickTherapeuticdropdownonItemScreen(String command) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("therapeutic_dropdown_item_screen", command);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("therapeutic_dropdown_item_screen", command));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}

	public void clickAllCheckboxesonTherapeuticclass(String command) {
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("all_checkboxes_therapeutic_class", command);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("all_checkboxes_therapeutic_class", command));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}

	public boolean verifyFieldsNotMandatoryOnItemscreen(String fieldName) {
		return isElementNotDisplayed("add_item_fields", fieldName);
	}

	public boolean verifydropdownsNotMandatoryOnItemscreen(String fieldName) {
		isElementDisplayed("add_item_dropdowns", fieldName);
		return isElementNotDisplayed("dropdown_icon_mandatory", fieldName);
	}

	public void verifyButtonIsPresent(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("added_button", fieldName);
		logMessage("[ASSERTION PASSED]: Verified button is present - " + fieldName);
	}

	public void verifyfieldIsPresent(String fieldName) {
		isElementDisplayed("active_toggle_label", fieldName);
		logMessage("[ASSERTION PASSED]: Verified field is present - " + fieldName);
	}

	public void verifyAddFromPISIsPresent(String fieldName) {
		isElementDisplayed("Add_new_item_actions", fieldName);
		logMessage("[ASSERTION PASSED]: Verified field is present - " + fieldName);
	}

	public void verifyAddFromPIScolumnsIsPresent(String fieldName) {
		isElementDisplayed("add_from_pis_columns", fieldName);
		logMessage("[ASSERTION PASSED]: Verified columns are present - " + fieldName);
	}

	public boolean editLinkTP(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		List<WebElement> ele = elements("save_button_enabled", data);
		if (ele.isEmpty())
			return false;
		else
			return true;
	}

	public boolean priorityListFacility() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		List<WebElement> ele = elements("prioritylist_Facility");
		if (ele.isEmpty())
			return false;
		else
			return true;
	}

	public void verifyButtonIsPresent(String fieldName, String elementText) {
		isElementDisplayed("btn_add", elementText);
		logMessage("[ASSERTION PASSED]: Verified button is present " + fieldName);
	}

	public void verifyInputWithTypeIsPresent(String type) {
		isElementDisplayed("input_with_type", type);
		logMessage("[ASSERTION PASSED]: Verified input of type " + type + "is present");
	}

	public boolean verifybuttonisDisabled(String day) {
		if ((element("btn", day)).getAttribute("class").trim().contains("disable")) {
			logMessage("ASSERTION PASSED]:The clicked button is disabled.");
			return true;
		}
		return false;
	}

	public void verifyItemIsPresentonDestinationUsersTab(String fieldName) {
		isElementDisplayed("verify_item_name_destination_users", fieldName);
		logMessage("[ASSERTION PASSED]: Verified item is present " + fieldName);
	}

	public boolean verifytherapeuticclassIsPresentOnItemScreen(String fieldName) {
		boolean flag = false;
		try {
			isElementDisplayed("link_siteConfigMenuOptions", fieldName);
			logMessage("[ASSERTION PASSED]: Verified therapeutic class  is present " + fieldName);
			flag = true;
		} catch (Exception e) {
			isElementDisplayed("link_siteConfigMenuOptions", fieldName);
		}
		return false;

	}

	public void clickFilterButton() {
		isElementDisplayed("filter_btn_item");
		logMessage("ASSERTION PASSED: Filter Option is available on Item Management Screen");
		// clickUsingXpathInJavaScriptExecutor(element("filter_btn_item"));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("filter_btn_item"));
		logMessage("ASSERTION PASSED: Clicked on Filter Button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);

	}

	public void verifyFilterItemsPopup() {

		// Assert.assertTrue(element("filter_items_heading").isDisplayed());
		isElementDisplayed("filter_items_heading");
		logMessage("ASSERTION PASSED: User is on Filter Items Popup");

	}

	public void verifyDefaultValueinFiltersPopuponItemScreen(String fieldName, String text) {
		isElementDisplayed("filter_dropdown", fieldName);
		System.out.println("VALUE@@@@@@@@" + element("filter_dropdown", fieldName).getText());
		verifySelectedTextIsContainedInDropDown(element("filter_dropdown", fieldName), text);

	}

	public void verifycountIsPresentonFiltericon() {
		wait.hardWait(15);
		isElementDisplayed("count_on_filtericon");
		logMessage("[ASSERTION PASSED]: Verified count is present ");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
	}

	public void verifycountIsPresentonFiltericononLocation() {
		wait.hardWait(15);
		isElementDisplayed("count_on_filter_location");
		logMessage("[ASSERTION PASSED]: Verified count is present ");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
	}

	public void clickcountonfilterButton() {
		isElementDisplayed("count_on_filtericon");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("count_on_filtericon"));
		// clickUsingXpathInJavaScriptExecutor(element("count_on_filtericon"));
		logMessage("ASSERTTION PASSED: Clicked on filter button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
	}

	public void verifyFilterItemIcon() {

		isElementDisplayed("filter_btn_item");
		logMessage("ASSERTION PASSED: User verifies the Filter Items icon");

	}

	public void clickCrossIcon() {
		// wait.hardWait(20);
		// wait.elementHighlight(element("cross_icon"));
		// element("cross_icon").click();
		isElementDisplayed("cross_icon");
		wait.waitForElementToBeClickable(element("cross_icon"));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("cross_icon"));
		// clickUsingXpathInJavaScriptExecutor(element("cross_icon"));
		logMessage("[STEP]: Cross Icon is clicked");

	}

	public String selectValueFromDropDownForFilterItem(String fieldName, String data) {
		selectProvidedTextFromDropDown(element("filter_dropdown", fieldName), data);
		Assert.assertTrue(getSelectedTextFromDropDown(element("filter_dropdown", fieldName)).equalsIgnoreCase(data));
		return data;
	}

	public String selectValueFromDropDownForFilterItemFields(String fieldName, String row, String data) {
		selectProvidedTextFromDropDown(element("filter_dropdown_item", fieldName, row), data);
		Assert.assertTrue(
				getSelectedTextFromDropDown(element("filter_dropdown_item", fieldName, row)).equalsIgnoreCase(data));
		return data;
	}

	public void selectValueFromDropDownForFilterItemByIndex(String fieldName, String row, Integer Index) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("filter_dropdown_item", fieldName, row);
		Select selectValue = new Select(element("filter_dropdown_item", fieldName, row));
		selectValue.selectByIndex(Index);
		logMessage("[STEP]: Value is selected from dropdown By index.");

	}

	public String selectValueFromDropDownForMultipleFilterItemFields(String fieldName, String data) {
		selectProvidedTextFromDropDown(element("multiple_filter", fieldName), data);
		Assert.assertTrue(getSelectedTextFromDropDown(element("multiple_filter", fieldName)).equalsIgnoreCase(data));
		return data;
	}

	public void selectValueForValueFromFilterDropdown(String fieldName, int index) {
		selectDropDownValue(element("filter_dropdown", fieldName), index);
	}

	public void verifyButtonIsEnabled(String name) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		Assert.assertTrue(element("button_SystemLabel", name).isEnabled());
		logMessage("Button - '" + name + "' is enabled");

	}

	public void clickAddButtonToAddNewSystemLabel(String label) {
		try {

			wait.applyFluentWait(getLocator("btn_add"), 60, 500);
			isElementDisplayed("btn_add");
			clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_add"));
			wait.applyFluentWait(getLocator("popup_add_systemlabel"), 60, 500);
			Assert.assertTrue(element("popup_add_systemlabel").getText().trim().contains(label));
			logMessage("[ASSERTION PASSED]: Verified Add Facility header on clicking add button");
		} catch (Exception e) {
			wait.applyFluentWait(getLocator("btn_add"), 60, 500);
			isElementDisplayed("btn_add");
			clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_add"));
			wait.applyFluentWait(getLocator("popup_add_systemlabel"), 60, 500);
			Assert.assertTrue(element("popup_add_systemlabel").getText().trim().contains(label));
			logMessage("[ASSERTION PASSED]: Verified Add System Label header on clicking add button");
		}

	}

	public void verifyState(String systemLabelName, String state) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		Assert.assertTrue(isElementDisplayed("newly_added_record_status", systemLabelName, state));
	}

	public void verifyStandardLabelState(String systemLabelName, String state) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		Assert.assertTrue(isElementDisplayed("standard_label_status", systemLabelName, state));
	}

	public void selectPriority(String priority, boolean b) {
		element("priority_trigger").click();
		isElementDisplayed("priority_select_label", priority);
		String id = element("priority_select_label_for", priority).getAttribute("for").trim();
		// isElementDisplayed("inp_chkbox_userName", name);
		if (b) {
			if (checkboxIsSelectedUsingJavascript(id)) {
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(id));
			} else {
				element("priority_select_label", priority).click();
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(id));
			}
			logMessage("Checkbox for name '" + priority + "' is checked");
		} else {
			if (checkboxIsSelectedUsingJavascript(id)) {
				Assert.assertFalse(checkboxIsSelectedUsingJavascript(id));
			} else {
				element("priority_select_label", priority).click();
				Assert.assertFalse(checkboxIsSelectedUsingJavascript(id));
			}
			logMessage("Checkbox for name '" + priority + "' is unchecked");
		}
	}

	public void selectPriorityInLabelEdit(String priority, boolean checkBoxValue) {
		isElementDisplayed("priority_toggle_label", priority);
		String id = element("priority_toggle_label", priority).getAttribute("for").trim();
		String s = checkBoxValue ? "checked" : "unchecked";

		if (checkBoxValue == checkboxIsSelectedUsingJavascript(id)) {
			logMessage("Checkbox for priority " + priority + " is already" + s);
		} else {
			element("priority_toggle_label", priority).click();
			logMessage("Checkbox for priority " + priority + " is" + s);
		}
	}

	public List<String> getPrinterListFromPrinterPage() {
		List<String> printerList = new ArrayList<String>();
		List<WebElement> elements = elements("list_printerNames");
		for (WebElement ele : elements) {
			printerList.add(ele.getText().trim());
		}
		Collections.sort(printerList);
		return printerList;
	}

	public void clickEditScheduleLink(String scheduleName) {
		isElementDisplayed("link_edit_schedule", scheduleName);
		element("link_edit_schedule", scheduleName).click();
		Assert.assertTrue(element("page_heading", "Edit Schedule").isDisplayed());
	}

	public boolean verifyPrinterNamesContainsOnlyAlphanumericCharacter() {
		int count = 0;
		List<String> printerList = new ArrayList<String>();
		List<WebElement> elements = elements("list_printerNames");
		for (WebElement ele : elements) {
			printerList.add(ele.getText().trim());
		}
		for (String s : printerList) {
			String pattern = "[a-zA-Z0-9\\-#\\.\\(\\)\\/%&\\s]{0,19}";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(s);
			if (m.find()) {
				count++;
			}

			else {
				count = 0;
				break;
			}
		}
		System.out.println("val of count" + count);

		if (count != 0)
			return true;

		else
			return false;
	}

	public List<String> getColumnData(String colNumber) {
		List<String> columnData = new ArrayList<String>();
		List<WebElement> elements = elements("results", colNumber);
		for (WebElement ele : elements) {
			columnData.add(ele.getText().trim());
		}
		// Collections.sort(columnData);
		return columnData;
	}

	public List<String> getColumnDataOfItems(String colNumber) {
		List<String> columnData = new ArrayList<String>();
		List<WebElement> elements = elements("results_item", colNumber);
		for (WebElement ele : elements) {
			columnData.add(ele.getText().trim());
		}
		// Collections.sort(columnData);
		return columnData;
	}

	public List<String> getStatusColumnDataofExternalSystem(String colNumber) {
		List<String> columnData = new ArrayList<String>();
		List<WebElement> elements = elements("active_external_systems", colNumber);
		for (WebElement ele : elements) {
			columnData.add(ele.getText().trim());
		}
		// Collections.sort(columnData);
		return columnData;
	}

	public void clickCheckboxOfNewRecord() {

		List<String> checkboxes = new ArrayList<String>();
		List<WebElement> elements = elements("txn_priority_active_checkboxes");
		wait.waitForElementsToBeVisible(elements);
		for (WebElement ele : elements) {
			checkboxes.add(ele.getText().trim());
		}

		total_records = checkboxes.size();
		String s = Integer.toString(total_records);

		isElementDisplayed("new_txn_priority_record_active_checkbox", s);

		try {
			wait.elementHighlight(element("new_txn_priority_record_active_checkbox", s));
			element("new_txn_priority_record_active_checkbox", s).click();
			logMessage("[STEP]: Active checkbox of New record is clicked");
		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("new_txn_priority_record_active_checkbox", s));
			logMessage("[STEP]: Active checkbox of New record is clicked");

		}
	}

	public void clickCheckboxOfExistingRecord() {

		List<String> checkboxes = new ArrayList<String>();
		List<WebElement> elements = elements("txn_priority_active_checkboxes");
		wait.waitForElementsToBeVisible(elements);
		for (WebElement ele : elements) {
			checkboxes.add(ele.getText().trim());
		}

		total_records = checkboxes.size();
		String s = Integer.toString(total_records);

		isElementDisplayed("new_txn_priority_record_active_checkbox", s);

		try {
			element("new_txn_priority_record_active_checkbox", s).click();
			logMessage("[STEP]: Active checkbox of New record is clicked");
		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("new_txn_priority_record_active_checkbox", s));
			logMessage("[STEP]: Active checkbox of New record is clicked");

		}
	}

	public boolean checkCheckboxOfSystemFlagNewRecordTP() {

		List<String> checkboxes = new ArrayList<String>();
		List<WebElement> elements = elements("txn_priority_system_checkboxes");
		wait.waitForElementsToBeVisible(elements);
		for (WebElement ele : elements) {
			checkboxes.add(ele.getText().trim());
		}

		total_records = checkboxes.size() - 1;
		String s = Integer.toString(total_records);

		isElementDisplayed("new_txn_priority_record_system_checkbox", s);

		String id = "systemFlag_" + s;
		return checkCheckboxIsEnabledOrDisabledUsingJavaScript(id);

	}

	public void dragAndDropTransactionPriority(String priorityName, String priorityName2) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("routing_rule_name", priorityName2);
		isElementDisplayed("routing_rule_name", priorityName);
		Actions act = new Actions(driver);
		/*
		 * act.dragAndDrop(element("text_popup_routingRule", priorityName2),
		 * element("text_popup_routingRule", priorityName)).build().perform();
		 */

		act.clickAndHold(element("routing_rule_name", priorityName2))
				.moveToElement(element("routing_rule_name", priorityName))
				.release(element("routing_rule_name", priorityName2)).build().perform();

	}

	public void dragAndDropTransactionPriorityatSystemLevel(String priorityName, String priorityName2) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("priority_2", priorityName2);
		wait.elementHighlight(element("priority_2", priorityName2));
		isElementDisplayed("priority_2", priorityName);
		wait.elementHighlight(element("priority_2", priorityName));
		Actions act = new Actions(driver);

		act.dragAndDrop(element("priority_2", priorityName2), element("priority_2", priorityName)).build().perform();

		/*
		 * act.clickAndHold(element("priority_2", priorityName2))
		 * .moveToElement(element("priority_2", priorityName)) \
		 * .release(element("priority_2", priorityName2)).build(); wait.hardWait(5);
		 * act.perform(); f4a4b671757dd3f10f4257cc06565efd77943f07
		 */

	}

	public int checkCountOfPriorityName(String priority) {
		wait.hardWait(6);
		int count = 0;
		List<WebElement> list = elements("list_priorityName");
		for (int i = 0; i < list.size(); i++) {
			String priorityName = list.get(i).getText();
			if (priority.equalsIgnoreCase(priorityName)) {
				count++;
				System.out.println("Value of count " + count);
			}
		}
		return count;

	}

	public int returnPositionOfPriorityName(String priority) {
		List<WebElement> list = elements("list_priorityName");
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			String priorityName = list.get(i).getText();
			if (priority.equalsIgnoreCase(priorityName)) {

				System.out.println("Position of Transaction Priority : " + count);
				return count;
			} else {
				count++;
				continue;
			}
		}
		return count;

	}

	public void verifyRecordIsNotPresent(String fieldName) {
		isElementNotDisplayed("input_field", fieldName);
	}

	public void selectPriorityColor(String colorCode) {
		wait.waitForElementToBeClickable(element("priority_color", colorCode));
		click(element("priority_color", colorCode));
		logMessage("[STEP]: Priority has been selected");

	}

	public void clickCheckBoxOfNewlyCreatedTransactionPriority(String fieldName) {
		String rowNumber = Integer.toString(total_records);
		isElementDisplayed("checkbox_new", fieldName, rowNumber);
		wait.waitForElementToBeClickable(element("checkbox_new", fieldName, rowNumber));
		// element("checkbox_new", fieldName,rowNumber).click();
		wait.elementHighlight(element("checkbox_new", fieldName, rowNumber));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("checkbox_new", fieldName, rowNumber));

	}

	public boolean verifySystemCheckBoxIsEnabledOrDisabled() {
		String s = Integer.toString(total_records);

		isElementDisplayed("new_txn_priority_record_system_checkbox", s);
		wait.elementHighlight(element("new_txn_priority_record_system_checkbox", s));

		String checkbox = element("new_txn_priority_record_system_checkbox", s).getAttribute("for");
		return checkCheckboxIsEnabledOrDisabledUsingJavaScript(checkbox);

	}

	public boolean verifySystemCheckBoxIsChecked() {
		String s = Integer.toString(total_records);

		isElementDisplayed("new_txn_priority_record_system_checkbox", s);
		wait.elementHighlight(element("new_txn_priority_record_system_checkbox", s));

		String checkbox = element("new_txn_priority_record_system_checkbox", s).getAttribute("for");
		return checkboxIsSelectedUsingJavascript(checkbox);

	}

	public void clickEditBaseOnPriorityCode(String priorityCode) {
		isElementDisplayed("edit_button_priorityCode", priorityCode);
		wait.waitForElementToBeClickable(element("edit_button_priorityCode", priorityCode));
		wait.elementHighlight(element("edit_button_priorityCode", priorityCode));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("edit_button_priorityCode", priorityCode));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

	}

	public void verifyCheckboxOfNewlyCreatedTransactionPriorityIsPlacedAtTheBottom(String fieldName) {
		String rowNumber = Integer.toString(total_records - 1);
		isElementDisplayed("input_field", fieldName);
		wait.elementHighlight(element("input_field", fieldName));
		String id = element("input_field", fieldName).getAttribute("id").substring(24);
		System.out.print("id=" + id + " and rownum= " + rowNumber);
		Assert.assertEquals(id, rowNumber);

	}

	public void verifyInputField(String fieldName) {
		isElementDisplayed("input_field", fieldName);

	}

	public void verifyInputFormularyField(String fieldName) {
		isElementDisplayed("input_field_Formulary_details", fieldName);

	}
	
	public String verifyDefaultInputField(String fieldName) {
		isElementDisplayed("max_Quantity_default", fieldName);
		String maxquanity = element("max_Quantity_default", fieldName).getText();
		return maxquanity;

	}
	
	public void clickCheckboxfacilityitemlevel(String flagname) {
		isElementDisplayed("click_checkbox_facilityitem", flagname);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_checkbox_facilityitem", flagname));

	}

	public void clickCheckboxfacilityitemlevel1(String flagname) {
		isElementDisplayed("click_checkbox_facilityitem", flagname);
		clickUsingXpathInJavaScriptExecutorSingleClick1(element("click_checkbox_facilityitem", flagname));

	}

	public void clickCheckboxfacilityitemlevel2(String field, String flagname) {
		isElementDisplayed("click_checkbox_facilityitem", field, flagname);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_checkbox_facilityitem", field, flagname));

	}

	public void clicklocationnumberOnItemScreen() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		isElementDisplayed("button_location_number");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("button_location_number"));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
	}

	public void clickManageLinkOnItemScreen() {
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("manage_link");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("manage_link"));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}

	public void clickCheckboxRoleofUOM(String flagname) {
		isElementDisplayed("click_checkbox_role_uom", flagname);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_checkbox_role_uom", flagname));

	}

	public String clickCheckboxTherapeuticClassitemlevel(String flagname) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("click_checkbox_Therapeuticclass_itemscreen", flagname);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_checkbox_Therapeuticclass_itemscreen", flagname));
		return flagname;

	}

	public boolean TherapeuticClassitemlevelIsDisplayed(String flagname) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		try {
			return element("click_checkbox_Therapeuticclass_itemscreen", flagname).isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public void clickCheckboxOfRichInputTextBox(String flagname) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		// isElementDisplayed("inp_field_printer", flagname);
		wait.waitForElementToBeClickable(element("inp_field_printer", flagname));
		if (!checkboxIsSelectedUsingJavascript(flagname)) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("inp_field_printer", flagname));
		}

	}

	public void clickCheckboxfacilityitemlevel_sanity(String flagname) {
		isElementDisplayed("click_checkbox_facilityitem_sanity", flagname);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_checkbox_facilityitem_sanity", flagname));

	}

	public void clickCheckboxSetParticipatingFacilty() {
		isElementDisplayed("click_checkbox_setparticipatingfacility");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_checkbox_setparticipatingfacility"));

	}

	public void clickCheckboxfacilityitemlevel1() {
		isElementDisplayed("click_checkbox_facilityitem1");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_checkbox_facilityitem1"));
		isElementDisplayed("click_checkbox_facilityitem2");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_checkbox_facilityitem2"));
	}

	public void clickfacilityonEditItem(String rownum) {
		wait.waitForElementToBeClickable(element("facility_item_level", rownum));
		Assert.assertTrue(isElementDisplayed("facility_item_level", rownum));
		element("facility_item_level", rownum).click();
		logMessage("ASSERT PASSED: User clicks on facility item level");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);

	}

	public void scrollHorizontal() {
		String rowNumber = Integer.toString(total_records);
		scrollDown(element("last_element", rowNumber));

	}

	public boolean verifyInactiveTransactionPriorities(String string) {
		List<String> checkboxes = new ArrayList<String>();
		List<WebElement> elements = elements("txn_priority_active_checkboxes");

		try {
			// wait.waitForElementsToBeVisible(elements);
			for (WebElement ele : elements) {
				checkboxes.add(ele.getText().trim());
			}
		} catch (Exception ex) {
			// wait.waitForElementsToBeVisible(elements);
			for (WebElement ele : elements) {
				checkboxes.add(ele.getText().trim());
			}
		}
		total_records = checkboxes.size();
		String s = Integer.toString(total_records);

		isElementDisplayed("new_txn_priority_record_active_checkbox", s);
		String checkbox = element("new_txn_priority_record_system_checkbox", s).getAttribute("for");
		return checkboxIsSelectedUsingJavascript(checkbox);

	}

	public void clickBulkItemButton() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.waitForElementToBeClickable(element("btn_setbulkItem"));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_setbulkItem"));
		logMessage("ASSERT PASSED: User clicks on bulk item button");

	}

	public void verifyBulkItemPopup() {
		wait.waitForElementToBeVisible(element("bulkItem_popUP"));
		logMessage("ASSERT PASSED :Verified set bulk item popup after clicking bulk item button");
	}

	public void verifymappedlocationonItemScreen() {
		wait.waitForElementToBeVisible(element("mapped_locations"));
		logMessage("ASSERT PASSED :Verified mapped locations with location number");
	}

	public void verifyEditLinkAgainstSystemLabels(String linkName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		Assert.assertTrue(isElementDisplayed("edit_link_system_label", linkName));
	}

	public void verifyUSerIsOnLocationManagementPage() {
		wait.hardWait(15);
		isElementDisplayed("header_location_management");
		logMessage("ASSERTION PASSED : Verified user is on Location Management page");
	}

	public void verifyUSerIsOnEditLocationManagementPage() {
		wait.hardWait(5);
		isElementDisplayed("product_id_button");
		logMessage("ASSERTION PASSED : Verified user is on Edit Location Management page");
	}

	public void verifyButtonOnEditLocation(String element) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed(element);
		logMessage("ASSERTION PASSED : Verified button on Edit Location page" + element);
	}

	public void clickButtonOnEditLocation(String button) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed(button);
		logMessage("ASSERTION PASSED : Verified user is on Location Management page");
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element(button)).click().build();
		seriesOfAction.perform();
		logMessage("Clicked on button: " + button);
	}

	public void clickEditLocation(String fielName, String rownum) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("button_unassign_location", fielName, rownum);
		logMessage("ASSERTION PASSED : Verified user is on Location Management page");
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("button_unassign_location", fielName, rownum))
				.click().build();

		seriesOfAction.perform();
		logMessage("clicked on button");
	}

	public void clickButtonOnEditLocation1(String button) {
		wait.hardWait(30);
		isElementDisplayed(button);
		logMessage("ASSERTION PASSED : Verified user is on Location Management page");
		clickUsingXpathInJavaScriptExecutor(element(button));
		logMessage("clicked on button");
	}

	public void verifyPageNumberOnLocationManagement() {
		wait.hardWait(15);
		isElementDisplayed("page_text");
		logMessage("ASSERTION PASSED : Verified The Page Number text on Location Management screen");
		isElementDisplayed("pages_location");
		logMessage("ASSERTION PASSED : Verified The Page Number Options on Location Management screen");
	}

	public void enterSearchTextForLocation(String searchtext) {
		isElementDisplayed("search_location");
		enterTextInField(element("search_location"), searchtext);
		logMessage("ASSERTION PASSED: Entered text in Search box" + searchtext);
	}

	public boolean VerifySearchedItemDetails(String input, String expectedPrinterName) {
		boolean flag = false;
		if (isElementDisplayed("inp_field_printer", input)) {
			wait.hardWait(4);
			if (element("inp_field_printer", input).getAttribute("value").equalsIgnoreCase(expectedPrinterName)) {
				flag = true;
			}
		}

		return flag;

	}

	public void verifyAttributeOptinsFromDropdown(String data) {
		wait.hardWait(5);
		List<WebElement> ele = getAllOptionsFromDropDown(element("facility_dropdown_edit_location"));
		for (WebElement e : ele) {
			if (e.getText().equalsIgnoreCase(data)) {
				logMessage("ASSERT PASSED: The Dropdown contains value: " + data);
			}

			else {
				logMessage("No data found");
			}

		}

	}

	public boolean verifyItemDetailsEditLocation(String item) {
		wait.hardWait(20);
		boolean flag = true;
		String s = element("item_id_location").getAttribute("innerHTML");
		System.out.println(s);
		// s=s.replaceAll("<span class=\"robotoBold\">Item ID: </span>", "");
		if (s.contains(item)) {
			return true;
		} else {
			return false;
		}
	}
	// System.out.println(element("item_id_location").getText());
	// Assert.assertEquals(element("item_id_location").getText(), item);
	// logMessage("ASSERT PASSED: Verified Item ID Details on Edit Location");

	public void verifyAssignLocationPopup(String header) {
		isElementDisplayed("popup_assign_location");
		logMessage("ASSERT PASSED: User is on Assign Location Popup");
		Assert.assertEquals(element("popup_assign_location").getText(), header);
	}

	public void verifyUserOnEditLocationAssignmentPage()

	{
		wait.hardWait(10);
		isElementDisplayed("assign_location_page");
		logMessage("ASSERT PASSED: User is on Assign Location Page");
		isElementDisplayed("assign_location_btn");
		logMessage("ASSERT PASSED: Verified Assign Location Button");

	}

	public void clickSlotAssignLocation() {
		wait.hardWait(6);
		hover(element("routing_priorities"));
		isElementDisplayed("slot_location");
		clickUsingXpathInJavaScriptExecutor(element("slot_location"));
		logMessage("ASSERT PASSED: Clicked on Slot");
	}

	public void clickAssignLocationButton() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("generic_loader"), 300);
		isElementDisplayed("assign_location_btn");
		Actions action = new Actions(driver);
		// Action seriesOfAction = (Action)
		// action.moveToElement(element("assign_location_btn")).click().build();
		// seriesOfAction.perform();
		clickUsingXpathInJavaScriptExecutor(element("assign_location_btn"));

		logMessage("ASSERT PASSED: Clicked on Assign Location button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}

	public void enterValueOnAssignLocationFields(String field, String value) {
		isElementDisplayed("quantity", field);
		enterTextInField(element("quantity", field), value);

	}

	public void clickEditSystemLabelLink(String systemLabelName, String popupMsg, String action) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("link_edit_duplicate_schedule", systemLabelName, action);
		element("link_edit_duplicate_schedule", systemLabelName, action).click();
		// Assert.assertTrue(element("page_heading_label",
		// popupMsg).isDisplayed());
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);

		// Assert.assertTrue(element("page_heading_label",
		// popupMsg).isDisplayed());

	}

	public void clickStandardLabelLink(String recordName, String popupMsg, String action) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		try {
			isElementDisplayed("link_duplicate_label", recordName, action);
			element("link_duplicate_label", recordName, action).click();
		} catch (Exception e) {
			// label_action
			isElementDisplayed("label_action", recordName, action);
			element("label_action", recordName, action).click();
		}

		wait.hardWait(5);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		Assert.assertTrue(element("popup_add_printer").getText().trim().contains(popupMsg));

	}

	public int getTabCount(String item) {

		return Integer.parseInt(element("count_assigned_priority", item).getText().replaceAll("\\(|\\)", ""));

	}

	public void AssignOrUnAssignTransactionPriority(String checkbox_transaction_priority_system_label, boolean b) {

		isElementDisplayed("checkbox_transaction_priority_system_label", checkbox_transaction_priority_system_label);
		String checkbox = element("checkbox_transaction_priority_system_label_for",
				checkbox_transaction_priority_system_label).getAttribute("for");
		if (b) {
			if (checkboxIsSelectedUsingJavascript(checkbox)) {
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(checkbox));
			} else {
				element("checkbox_transaction_priority_system_label_for").click();

				// clickUsingXpathInJavaScriptExecutor(element("parent_checkbox_printer",
				// fieldName));
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(checkbox));
			}
			logMessage("Checkbox" + checkbox_transaction_priority_system_label + " is checked");
		} else {
			if (checkboxIsSelectedUsingJavascript(checkbox)) {
				element("checkbox_transaction_priority_system_label_for").click();
				Assert.assertFalse(checkboxIsSelectedUsingJavascript(checkbox));
			} else {
				Assert.assertFalse(checkboxIsSelectedUsingJavascript(checkbox));
			}
			logMessage("Checkbox" + checkbox_transaction_priority_system_label + " is unchecked");
		}
	}

	public void clickOnAddButtonToAddParticular(String string) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForElementToBeClickable(element("btn_add"));
		isElementDisplayed("btn_add");
		clickUsingXpathInJavaScriptExecutor(element("btn_add"));
		logMessage("[STEP]: Clicked on Add button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForElementToBeVisible(element("popup_for_add"));
		Assert.assertTrue(element("popup_for_add").getText().trim().contains(string));
		logMessage("[ASSERTION PASSED]: Verified Add Printer pop up on clicking add button");
	}

	public void verifyPopupMessageSystemLabelPage(String data) {
		wait.waitForElementToBeVisible(element("system_label_popup"));
		Assert.assertTrue(element("system_label_popup").getText().trim().equalsIgnoreCase(data));
		logMessage("[ASSERTION PASSED]: Toggle popup message is verified successfully.");
	}

	public void verifyCheckboxForPickQuantityOnSystemLabel(String fieldName) {
		isElementDisplayed("parent_checkbox_cyclecount", fieldName);
		Assert.assertFalse(checkboxIsSelectedUsingJavascript(fieldName),
				"[ASSERTION FAILED]: Checkbox for " + fieldName + "is checked by default");
		logMessage("[ASSERTION PASSED]: Verified checkbox field: for" + fieldName + " and is not checked");
	}

	public void verifyDropDownFieldOnAddSytemLabel(String fieldName) {

		isElementDisplayed("second_sort", fieldName);
		logMessage("[ASSERTION PASSED]: Verified dropdown field for " + fieldName);

	}

	public boolean verifySystemLabelStatus(String columnNumber, String state) {
		ArrayList<String> status = new ArrayList<String>();
		List<WebElement> elements = elements("list_sysLabels", columnNumber);

		boolean flag = false;
		if (state.equalsIgnoreCase("Active")) {
			for (WebElement ele : elements) {
				status.add(ele.getText().trim());
				if (!(ele.getText().trim().equalsIgnoreCase("Active"))) {
					// Assert.assertTrue(!status.isEmpty());
					return flag;
				}
			}
		} else {
			flag = true;
			for (WebElement ele : elements) {
				status.add(ele.getText().trim());
				if ((ele.getText().trim().equalsIgnoreCase("Inactive"))) {
					// Assert.assertTrue(!status.isEmpty());
					return flag;
				}
			}
		}

		Assert.assertTrue(!status.isEmpty(), "[ASSERTION PASSED]: Standard Label list is empty");
		// logMessage("[ASSERTION PASSED]: Verified status list on System Label UI");
		return !flag;

	}

	public boolean verifyContainsSearch(String colName, String searchedLabelName) {
		hardWaitForChromeBrowser(5);
		Assert.assertTrue(isElementDisplayed("text_column_systemLabel", searchedLabelName));
		return isElementDisplayed("text_column_systemLabel", searchedLabelName);
	}

	public void selectPrinterForSystemLabel(String elem, String data) {
		List<WebElement> ele = getAllOptionsFromDropDown(element("dropdown_printer_systemLabel" + elem));
		for (WebElement e : ele) {
			if (e.getText().equalsIgnoreCase(data)) {
				e.click();
			}
		}
	}

	public void verifyToolTipForStock(String data) {
		isElementDisplayed("tool_tip");
		Actions action = new Actions(driver);
		action.moveToElement(element("tool_tip")).build().perform();
		Assert.assertTrue(element("tool_tip_text").getText().equalsIgnoreCase(data));
		logMessage("[ASSERTION PASSED]: Tool tip is displayed");

	}

	public boolean verifyMandatoryField(String string) {
		if (isElementDisplayed("icon_mandatory_schedule", string)) {
			Assert.assertTrue(element("icon_mandatory_schedule", string).getText().equalsIgnoreCase("*"));
			logMessage("[Assertion Passed]: Field is Mandatory");
			return true;
		} else {
			return false;
		}

	}

	public boolean verifyCheckboxesUnderRoleField(String fieldName) {
		if (isElementDisplayed("label_routingRule", fieldName)) {
			logMessage("[ASSERTION PASSED]: Verified checkboxes are available for " + fieldName);
			return true;
		}
		return false;
	}

	public void enterSearchTermInSearchField(String data) {
		wait.loadingWait(getLocator("loader"));
		isElementDisplayed("input_searchPrinter");
		enterTextInField(element("input_searchPrinter"), data);
		Assert.assertEquals(element("input_searchPrinter").getAttribute("value").trim(), data);
		logMessage("[ASSERTION PASSED]: Search Term as : " + data + " is displayed in the Search Field");
		// wait.loadingWait(getLocator("loader"));
	}

	public String EnterRandomValueInInputFieldOnAddNewPrinterPopup(String fieldName, String data) {
		isElementDisplayed("inp_field_printer", fieldName);
		
		enterTextInField(element("inp_field_printer", fieldName), data);
		Assert.assertEquals(element("inp_field_printer", fieldName).getAttribute("value").trim(), data);
		logMessage("ASSERTION PASSED: Value entered - " + data);
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		return data;
	}

	public String EnterRandomValueInInputFieldOnAddNewPrinterPopup(String fieldName, String fieldNumber, String data) {
		data = data + System.currentTimeMillis();
		System.out.println(data);
		enterTextInField(element("inp_field_computer", fieldName, fieldNumber), data);
		return data;
	}

	public String enterRandomValueInInputField(String fieldName, String data) {
		isElementDisplayed("input_field", fieldName);
		enterTextInField(element("input_field", fieldName), data);
		Assert.assertEquals(element("input_field", fieldName).getAttribute("value"), data);
		logMessage("[STEP]: Entered data '" + data + "' in input field '" + fieldName + "'");
		return data;
	}
	

	public void verifyValueInInputField(String fieldName, String expected) {
		isElementDisplayed("input_field", fieldName);
		String actual = element("input_field", fieldName).getAttribute("value");
		Assert.assertEquals(actual, expected);
	}

	public void codeNotEditable(String fieldName) {
		Assert.assertFalse(isElementNotDisplayed("input_field", fieldName));
	}

	public boolean verifyDefaultValueMaxLock(String fieldName) {
		isElementDisplayed("input_field", fieldName);
		String value = element("input_field", fieldName).getAttribute("value");
		if (value.equalsIgnoreCase("600"))
			return true;
		else
			return false;
	}

	public boolean verifyDefaultValueInInputField(String fieldName, String defaultValue) {
		isElementDisplayed("input_field", fieldName);
		String value = element("input_field", fieldName).getAttribute("value");
		if (value.equalsIgnoreCase(defaultValue))
			return true;
		else
			return false;
	}

	public String enterRandomValueInRichInputField(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		isElementDisplayed("dropdown_itemManagement");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("dropdown_itemManagement"));
		enterTextInField(element("dropdown_itemManagement"), data);
		try {
			wait.waitForElementsToBeVisible(elements("dropdown_itemManagement_options"));
			List<WebElement> elements = elements("dropdown_itemManagement_options");
			elements.get(0).click();
			logMessage("[STEP]: RichInput text Option is selected.");
		} catch (Exception e) {
			List<WebElement> elements = elements("dropdown_itemManagement_options");
			elements.get(0).click();
			logMessage("[STEP]: RichInput text Option is selected.");
		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		List<WebElement> list = elements("item_result");
		wait.waitForElementsToBeVisible(list);
		if (!list.isEmpty()) {
			System.out.println("Item selected is" + list.get(0).getText());

		}
		return list.get(0).getText();

	}

	public String enterRandomValueofMyFacilityInRichInputField(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		isElementDisplayed("dropdown_itemManagement");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("dropdown_itemManagement"));
		enterTextInField(element("dropdown_itemManagement"), data);
		try {
			wait.waitForElementsToBeVisible(elements("dropdown_itemManagement_options"));
			List<WebElement> elements = elements("dropdown_itemManagement_options");
			elements.get(1).click();
			logMessage("[STEP]: RichInput text Option is selected.");
		} catch (Exception e) {
			List<WebElement> elements = elements("dropdown_itemManagement_options");
			elements.get(1).click();
			logMessage("[STEP]: RichInput text Option is selected.");
		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		List<WebElement> list = elements("item_result");
		wait.waitForElementsToBeVisible(list);
		if (!list.isEmpty()) {
			System.out.println("Item selected is" + list.get(0).getText());

		}
		return list.get(0).getText();

	}

	public String enterRandomValueofMyFacilitiesInRichInputField(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		isElementDisplayed("dropdown_itemManagement");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("dropdown_itemManagement"));
		enterTextInField(element("dropdown_itemManagement"), data);
		try {
			wait.waitForElementsToBeVisible(elements("dropdown_MyFacilities_options"));

			List<WebElement> elements = elements("dropdown_MyFacilities_options");
			elements.get(0).click();
			logMessage("[STEP]: RichInput text Option is selected.");
		} catch (Exception e) {
			List<WebElement> elements = elements("dropdown_MyFacilities_options");
			elements.get(0).click();
			logMessage("[STEP]: RichInput text Option is selected.");
		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		List<WebElement> list = elements("item_result");
		wait.waitForElementsToBeVisible(list);
		if (!list.isEmpty()) {
			System.out.println("Item selected is" + list.get(0).getText());

		}
		return list.get(0).getText();

	}

	public String enterRandomValueoffacilityInRichInputField(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("dropdown_itemManagement");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("dropdown_itemManagement"));
		enterTextInField(element("dropdown_itemManagement"), data);
		try {
			wait.waitForElementsToBeVisible(elements("dropdown_itemManagement_options"));

			List<WebElement> elements = elements("dropdown_itemManagement_options");
			elements.get(1).click();
			logMessage("[STEP]: RichInput text Option is selected.");
		} catch (Exception e) {
			List<WebElement> elements = elements("dropdown_itemManagement_options");
			elements.get(1).click();
			// clickUsingXpathInJavaScriptExecutorSingleClick(elements.get(1));
			logMessage("[STEP]: RichInput text Option is selected.");
		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		List<WebElement> list = elements("item_result");
		wait.waitForElementsToBeVisible(list);
		if (!list.isEmpty()) {
			System.out.println("Item selected is" + list.get(0).getText());

		}
		return list.get(0).getText();

	}

	public boolean verifyValueIsNotPresentInLabelTagDropDown(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("dropdown_itemManagement");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("dropdown_itemManagement"));
		enterTextInField(element("dropdown_itemManagement"), data);
		List<WebElement> elements = elements("dropdown_labeltag_nomatches");
		System.out.println(elements);
		if (elements.isEmpty())
			return true;
		else
			return false;
	}

	public void verifyValueIsPresentInLabelTagDropDown(String data, String expected) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("dropdown_itemManagement");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("dropdown_itemManagement"));
		enterTextInField(element("dropdown_itemManagement"), data);
		try {
			wait.waitForElementsToBeVisible(elements("dropdown_itemManagement_options"));
			List<WebElement> elements = elements("dropdown_itemManagement_options");
			String actual = elements.get(0).getText();
			Assert.assertEquals(actual, expected);
			elements.get(0).click();
			logMessage("Value is present");
		} catch (Exception e) {
			List<WebElement> elements = elements("dropdown_itemManagement_options");
			String actual = elements.get(0).getText();
			Assert.assertEquals(actual, expected);
			elements.get(0).click();
			logMessage("Value is present");
		}
	}

	public void enterRandomValueInRichInputFieldForExternalSystem(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		isElementDisplayed("dropdown_itemManagement");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("dropdown_itemManagement"));
		enterTextInField(element("dropdown_itemManagement"), data);
		try {
			wait.waitForElementsToBeVisible(elements("dropdown_itemManagement_options"));

			List<WebElement> elements = elements("dropdown_itemManagement_options");
			elements.get(0).click();
			logMessage("[STEP]: RichInput text Option is selected.");
		} catch (Exception e) {
			List<WebElement> elements = elements("dropdown_itemManagement_options");
			elements.get(0).click();
			logMessage("[STEP]: RichInput text Option is selected.");
		}

	}

	public String verifyDefaultValueinSystemLabel(String fieldName) {
		isElementDisplayed("filter_dropdown", fieldName);
		return element("filter_dropdown", fieldName).getText();
		// verifySelectedTextIsContainedInDropDown(element("filter_dropdown",
		// fieldName), text);

	}

	public void enterValueInQuantityFieldOnLocationScreen(String id, String data) {
		isElementDisplayed("quantity", id);
		enterTextInField(element("quantity", id), "");
		enterTextInField(element("quantity", id), data);
		logMessage("ASSERTION PASSED: Entered value in field");

	}

	public String enterAndReturnValueInQuantityFieldOnLocationScreen(String field, String data) {
		isElementDisplayed("quantity", field);
		enterTextInField(element("quantity", field), data);
		logMessage("ASSERTION PASSED: Entered value in field");
		return data;

	}

	public String getCurrentValueOfInputField(String id) {
		isElementDisplayed("input_field", id);
		return getValUsingXpathInJavaScriptExecutor(element("input_field", id)).trim();
	}

	public void verifyUnassignButton() {
		wait.hardWait(10);
		isElementDisplayed("unassign_btn");
		logMessage("ASSERTION PASSED: Verified Unassigned link for an item");
	}

	public void verifyAndClickEditButtonOnItemLocation() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("edit_button_locationAssignement");
		element("edit_button_locationAssignement").click();

	}

	public void confirmPopupOnUnassign(String header) {
		isElementDisplayed("unassign_location_popup");
		logMessage("ASSERTION PASSED: Verified Popup for Unassign Location");
		isElementDisplayed("unassign_popup_header");
		Assert.assertEquals(header, element("unassign_popup_header").getText());

	}

	public void verifyAddedLocation() {
		isElementDisplayed("location_row");
		logMessage("ASSERTION PASSED: The Location added successfully");
	}

	public String verifyFieldIsNotEditable(String fieldnName, String id) {

		return element("label_edit_system_label", fieldnName, id).getTagName().toString();
	}

	public boolean verifyAssignedLocation() {
		wait.hardWait(6);
		if (isElementNotDisplayed("noLocation_message") == true) {
			logMessage("ASSERTION PASSED: No location assigned to item");
			return false;
		}

		else {
			isElementDisplayed("assigned_location_value");
			logMessage("ASSERTION PASSED: Verified the location details for an assigned item");
			return true;
		}
	}

	public void verifyFieldsOnLocationManagement() {
		wait.hardWait(15);
		isElementDisplayed("myFacility_droddown");
		logMessage("ASSERTION PASSED: Verified My Facility dropdown on Location Management Screen");
		isElementDisplayed("search_location");
		logMessage("ASSERTION PASSED: Verified Search Location field on Location Management Screen");
		isElementDisplayed("filter_location_btn");
		logMessage("ASSERTION PASSED: Verified Filter button on Location Management Screen");

	}

	public void selectFacilityDropdown(String data) {
		wait.hardWait(5);
		List<WebElement> ele = getAllOptionsFromDropDown(element("dropdown_facility"));
		for (WebElement e : ele) {
			if (e.getText().equalsIgnoreCase(data)) {
				e.click();
			}

		}
	}

	public void selectFacilityDropdownForDestination(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		List<WebElement> ele = getAllOptionsFromDropDown(element("facility_dropdown_edit_location"));
		for (WebElement e : ele) {
			if (e.getText().equalsIgnoreCase(data)) {
				e.click();
			}
		}
		logMessage("[STEP]: Selected  option '" + data + "' from dropdown");
	}

	public void selectReceivingFacilityForDestination(String data) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		List<WebElement> ele = getAllOptionsFromDropDown(element("receivingfacility_dropdown"));
		for (WebElement e : ele) {
			if (e.getText().equalsIgnoreCase(data)) {
				e.click();
			}
		}
	}

	public void verifyErrorMessagemandaRoutingRule(String message) {

		String spanMessage = element("error_message_routingrule", message).getText();
		System.out.println("Value of message:  " + spanMessage);
		Assert.assertEquals(spanMessage, message);

	}

	public void verifyPaginationButtonIsPresent(String fieldName) {
		isElementDisplayed("button_SystemLabel", fieldName);
		logMessage("[ASSERTION PASSED]: Verified button is present " + fieldName);
	}

	public void verifyDefaultValueofShowRowsDropDownOnItemScreen(String fieldName, String text) {
		isElementDisplayed("dropdown_showrows_item", fieldName);
		verifySelectedTextFromDropDown(element("dropdown_showrows_item", fieldName), text);
	}

	public void ClickOnExternalSystemRichTextbox() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("dropdown_itemManagement");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("dropdown_itemManagement"));
	}

	public String selectExternalSystemfromRichTextbox(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 130);
		isElementDisplayed("link_siteConfigMenuOptions", data);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_siteConfigMenuOptions", data));
		return data;
	}

	public boolean verifyExternalSystemDoesNotExistfromRichTextbox(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 130);
		try {
			isElementDisplayed("link_siteConfigMenuOptions", data);
			clickUsingXpathInJavaScriptExecutorSingleClick(element("link_siteConfigMenuOptions", data));
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public String ClickOndropdownIcononexternalsystem(String data, String data1) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 130);
		wait.waitForElementToBeClickable(element("dropdown_icon_item_external", data));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("dropdown_icon_item_external", data));
		System.out.println("icon clicked");

		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.waitForElementToBeClickable(element("link_siteConfigMenuOptions", data1));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_siteConfigMenuOptions", data1));
		return data1;
	}

	public void verifyDefaultValueShowRowsItemDropDown(String fieldName, String text) {
		isElementDisplayed("dropdown_showrows_item", fieldName);
		verifySelectedTextFromDropDown(element("dropdown_showrows_item", fieldName), text);
	}

	public String ClickOnExternalSystemRichTextboxdropdown(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForElementToBeClickable(element("link_siteConfigMenuOptions", data));
		// isElementDisplayed("link_siteConfigMenuOptions",data);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_siteConfigMenuOptions", data));
		return data;
	}

	public void verifyAndClickProductID(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		isElementDisplayed("product_id_button", fieldName);
		try {
			element("product_id_button", fieldName).click();

		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("product_id_button", fieldName));
		}
		logMessage("[STEP]: ProductID is enabled and clicked");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 180);
	}
	
	
	public void verifyAndClickItemFacility(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("select_item_facility", fieldName);
		try {
			element("select_item_facility", fieldName).click();

		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("select_item_facility", fieldName));
		}
		logMessage("[STEP]: ProductID is enabled and clicked");

	}

	public void verifyItemFacility(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		Assert.assertTrue(isElementDisplayed("select_item_facility", fieldName));
	}

	public void verifyAndClickItemFacility1(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("select_item_facility1", fieldName);
		try {
			element("select_item_facility1", fieldName).click();

		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("select_item_facility1", fieldName));
		}
		logMessage("[STEP]: ProductID is enabled and clicked");

	}

	public void verifyAndClickItemFacility2(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("select_item_facility2", fieldName);
		try {
			element("select_item_facility2", fieldName).click();

		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("select_item_facility2", fieldName));
		}
		logMessage("[STEP]: ProductID is enabled and clicked");

	}

	public void verifyAndClickAddProductID(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		isElementDisplayed("button_SystemLabel", fieldName);
		try {
			element("button_SystemLabel", fieldName).click();

		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("button_SystemLabel", fieldName));
		}
		logMessage("[STEP]: Add ProductID is clicked");

	}

	public void enterBarcode(String fieldName) {
		isElementDisplayed("inp_field_printer", fieldName);

	}

	public void verifyPageHeader(String className, String pageTitle) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		try {
			isElementDisplayed("manage_distributor_header", className);
			Assert.assertTrue(
					element("manage_distributor_header", className).getText().trim().equalsIgnoreCase(pageTitle));
			logMessage("[STEP]: Page Header is verified successfully.");
		} catch (Exception e) {

			isElementDisplayed("item_management_header", className);
			Assert.assertTrue(
					element("item_management_header", className).getText().trim().equalsIgnoreCase(pageTitle));
			logMessage("[STEP]: Page Header is verified successfully.");
		}
	}

	public void clickDistributorInfo(String fieldName, String rowNum) {
		wait.waitForElementToBeClickable(element("preffered_distributor_info", fieldName, rowNum));
		element("preffered_distributor_info", fieldName, rowNum).click();
		logMessage("[STEP]:" + fieldName + " is provided.");

	}

	public String getSelectedDistributorName(String fieldName, String rowNum) {
		isElementDisplayed("preferred_distributor_on_manage_distributor_screen", fieldName, rowNum);
		return element("preferred_distributor_on_manage_distributor_screen", fieldName, rowNum).getText();
	}

	public void enterDistributorInfo(String fieldName, String rowNum, String data) {
		wait.waitForElementToBeClickable(element("preffered_distributor_info", fieldName, rowNum));
		enterTextInField(element("preffered_distributor_info", fieldName, rowNum), data);

	}

	public String getDistributorInfo(String fieldName, String rowNum) {
		isElementDisplayed("preffered_distributor_info", fieldName, rowNum);
		return element("preffered_distributor_info", fieldName, rowNum).getAttribute("value");
	}

	public String getParsedProductID() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		isElementDisplayed("productID");
		logMessage("productID is " + element("productID").getText());
		return element("productID").getText();
	}

	public String verifyAddedProductID(String productID) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		try {
			isElementDisplayed("added_record_div", productID);
			return element("added_record_div", productID).getText();
		} catch (Exception e) {
			isElementDisplayed("link_edit_of_added_record", productID);
			return element("link_edit_of_added_record", productID).getText();
		}
	}

	public void verifyAddedPreferdDistributorName(String productID) {
		isElementDisplayed("button_SystemLabel", productID);
		logMessage("[ASSERTION PASSED]: Verified added prefered distributor.");

	}

	public int verifyDistributorAccountListIsNonEmpty() {
		return elements("list_dist_sccount").size();

	}

	public boolean verifyDistributorAccountListIsEmpty() {
		return elements("list_dist_sccount").isEmpty();

	}

	public void clickEditScheduleLink(String scheduleName, String action) {

		isElementDisplayed("link_edit_duplicate_schedule", scheduleName, action);
		// element("link_edit_duplicate_schedule", scheduleName,
		// action).click();
		Actions action1 = new Actions(driver);
		action1.moveToElement(element("link_edit_duplicate_schedule", scheduleName, action)).click().build().perform();

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		// Assert.assertTrue(element("page_heading_new", "Edit
		// Schedule").isDisplayed());

	}

	public void clickRecordNameToEdit(String recordName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 20);

		isElementDisplayed("link_edit_of_added_record", recordName);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_edit_of_added_record", recordName));
		logMessage("[STEP]: Clicked on " + recordName + " button to edit record");
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		try {
			isElementDisplayed("popup_add_printer");
			Assert.assertTrue(element("popup_add_printer").getText().trim().contains(recordName));
		} catch (Exception a) {
			try {
				isElementDisplayed("header_edit_dest");
				Assert.assertTrue(element("header_edit_dest").getText().trim().contains(recordName));
			} catch (Exception b) {
				try {
					isElementDisplayed("edit_screen_heading");
					Assert.assertTrue(element("edit_screen_heading").getText().trim().contains(recordName));
				} catch (Exception e) {
					isElementDisplayed("hold_reason_name");
					Assert.assertTrue(element("hold_reason_name").getText().trim().contains(recordName));
				}
			}
		}

	}

	public void clickRecordNameToEdit_Generic(String recordName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 30);

		isElementDisplayed("record_name_btn", recordName);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("record_name_btn", recordName));
		logMessage("Clicked on " + recordName + " button to edit record");

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 300);
		try {
			isElementDisplayed("popup_add_printer");
			Assert.assertTrue(element("popup_add_printer").getText().trim().contains(recordName));
		} catch (Exception e) {
			try {
				isElementDisplayed("edit_screen_heading");
				Assert.assertTrue(element("edit_screen_heading").getText().trim().contains(recordName));
			} catch (Exception j) {
				isElementDisplayed("hold_reason_name");
				Assert.assertTrue(element("hold_reason_name").getText().trim().contains(recordName));
			}
		}

		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
	}

	public int getNumberOfButtons_UsingId(String id) {
		try {
			return elements("action_button2", id, id).size();
		} catch (Exception e) {
			return 0;
		}
	}

	public boolean isParaWithTextVisible(String text) {
		return isElementNotDisplayed("text_msg", text);
	}

	public void clickItemNameToEdit(String itemName) {
		isElementDisplayed("link_edit_of_added_record", itemName);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_edit_of_added_record", itemName));
		logMessage("Clicked on " + itemName + " button to edit record");

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("header_edit_item", itemName);
		// Assert.assertTrue(element("header_edit_item").getText().trim().contains(itemName));
	}

	public void verifyAccountNumberFieldIsDisabled() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		List<WebElement> elements = elements("text_column_distributor");
		logMessage("Account Number List is loaded");
		for (WebElement ele : elements) {
			Assert.assertEquals(ele.isEnabled(), false,
					"[ASSERTION FAILED]: Account Numbers are not disabled by-default");
		}

	}

	public void verifyAccountNumberFieldIsEnabled() {
		Assert.assertTrue(element("input_field_account_number").isEnabled());

	}

	public List<WebElement> verifySystemLabelList() {

		return elements("system_label_list");

	}

	public void SelectCheckbox(String fieldName) {

		try {
			wait.hardWait(10);
			element("search").sendKeys(Keys.TAB);
			wait.hardWait(10);
			element("item_checkbox").sendKeys(Keys.TAB);
			wait.hardWait(10);
			element("item_checkbox").sendKeys(Keys.SPACE);
		} catch (Exception e) {
			element("item_checkbox").click();

		}
	}

	public String selectValueDosageDropDown(String fieldName, String data) {

		wait.hardWait(10);
		isElementDisplayed("select_dosage_form", fieldName);
		wait.waitForElementToBeClickable(element("select_dosage_form", fieldName));
		selectProvidedTextFromDropDown(element("select_dosage_form", fieldName), data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("select_dosage_form", fieldName)), data);
		return data;

	}

	public String enterExternalSystemValueDropdownField(String data) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		isElementDisplayed("dropdown_itemManagement");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("dropdown_itemManagement"));
		enterTextInField(element("dropdown_itemManagement"), data);
		wait.hardWait(5);
		int indx = 0;
		try {
			wait.waitForElementsToBeVisible(elements("dropdown_itemManagement_options"));
			List<WebElement> elements = elements("dropdown_itemManagement_options");
			for (indx = 0; indx < elements.size(); indx++) {
				if (elements.get(indx).getText().contains(data.trim())) {
					break;
				}
			}
			elements.get(indx).click();
			logMessage("[STEP]: RichInput text Option is selected.");
		} catch (Exception e) {
			try {
				List<WebElement> elements = elements("dropdown_itemManagement_options");
				for (indx = 0; indx < elements.size(); indx++) {
					if (elements.get(indx).getText().contains(data.trim())) {
						break;
					}
				}
				elements.get(indx).click();
				logMessage("[STEP]: RichInput text Option is selected.");
			} catch (Exception j) {
				isElementDisplayed("no_match_found");
				logMessage("Result for the searched text" + element("no_match_found").getText());
				return element("no_match_found").getText();
			}
		}

		return data;

	}

	public String enterExternalSystemValueDropdownFieldNew(String data, String facility) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		isElementDisplayed("dropdown_itemManagement");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("dropdown_itemManagement"));
		enterTextInField(element("dropdown_itemManagement"), data);
		wait.hardWait(5);
		try {
			wait.waitForElementsToBeVisible(elements("dropdown_itemManagement_options"));

			List<WebElement> elements = elements("dropdown_itemManagement_options");
			String facilityName = elements.get(2).getText();
			System.out.println("Actual facility value = " + facilityName);
			Assert.assertEquals(facilityName, facility);
			elements.get(0).click();
			logMessage("[STEP]: RichInput text Option is selected.");
		} catch (Exception e) {
			try {
				List<WebElement> elements = elements("dropdown_itemManagement_options");
				elements.get(0).click();
				logMessage("[STEP]: RichInput text Option is selected.");
			} catch (Exception j) {
				isElementDisplayed("no_match_found");
				logMessage("Result for the searched text" + element("no_match_found").getText());
				return element("no_match_found").getText();
			}
		}

		return data;

	}

	public String enterFaciltyValueDropdownField(String data) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		isElementDisplayed("dropdown_itemManagement");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("dropdown_itemManagement"));
		enterTextInField(element("dropdown_itemManagement"), data);
		wait.hardWait(5);
		try {
			wait.waitForElementsToBeVisible(elements("dropdown_itemManagement_options"));
			List<WebElement> elements = elements("dropdown_itemManagement_options");
			elements.get(2).click();
			logMessage("[STEP]: RichInput text Option is selected.");
		} catch (Exception e) {
			try {
				List<WebElement> elements = elements("dropdown_itemManagement_options");
				elements.get(2).click();
				logMessage("[STEP]: RichInput text Option is selected.");
			} catch (Exception j) {
				isElementDisplayed("no_match_found");
				logMessage("Result for the searched text" + element("no_match_found").getText());
				return element("no_match_found").getText();
			}
		}

		return data;

	}

	public void clickOnEditLinkCorresspondingToDestinationName(String destinationName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.waitForElementToBeClickable(element("btn_add"));
		isElementDisplayed("btn_add");
		isElementDisplayed("link_edit_dest", destinationName);
		clickUsingXpathInJavaScriptExecutor(element("link_edit_dest", destinationName));
		logMessage("Clicked on Edit link corressponding to " + destinationName);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForElementToBeVisible(element("header_edit_dest"));
		Assert.assertTrue(element("header_edit_dest").getText().trim().contains(destinationName));
		logMessage("[ASSERTION PASSED]: Verified Edit a Destination header");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
	}

	public void clickCheckboxForDistributorAccount(String toggle) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		element("action_toggle_button_1", toggle).click();
	}

	public void clickCheckboxForUsers() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		List<WebElement> ele = elements("checkbox_userDestination");
		ele.get(0).click();
	}

	public void clickCheckboxForItemUserstab() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		element("checkbox_destination_users_tab").click();
	}

	public void verifyErrorMessageForBlankField(String data) {

		wait.hardWait(10);
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Expected message - '" + data + "'");
		logMessage("Actual message - '" + element("message_field_blank_schedule").getText().trim() + "'");
		Assert.assertTrue(element("message_field_blank_schedule").getText().trim().equalsIgnoreCase(data.trim()));

	}

	public void verifyErrorMessageForBlankFieldForFacility(String data) {

		wait.hardWait(10);
		element("message_field_blank_facility").getText().equalsIgnoreCase(data);

	}

	public void clickOnCancelButton() {
		wait.hardWait(3);
		scrollUp();
		isElementDisplayed("cancel_button");
		// wait.waitForJStoLoad();
		element("cancel_button").click();
	}

	public void clickOnDistributorInfo(String fieldName) {
		wait.waitForElementToBeClickable(element("preffered_distributor_radiobutton", fieldName));
		element("preffered_distributor_radiobutton", fieldName).click();
		logMessage("[STEP]:" + fieldName + " is provided.");
	}

	public void enterDistributorItemCode(String fieldName, String data) {
		wait.waitForElementToBeClickable(element("preferred_distributer_item_code", fieldName));
		enterTextInField(element("preferred_distributer_item_code", fieldName), data);

	}

	public String enterDistributorItemCode1(String fieldName, String data) {
		wait.waitForElementToBeClickable(element("preferred_distributer_item_code", fieldName));
		enterTextInField(element("preferred_distributer_item_code", fieldName), data);
		return data;

	}

	public void enterCostValue(String field, String value) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		isElementDisplayed("item_cost", field);
		enterTextInField(element("item_cost", field), value);

	}

	public boolean verifyUpdatedItemDescription(String itemName, String dosageForm) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		String value = element("action_button1", itemName).getText();
		System.out.println("Value of Item  " + value);
		System.out.println("Value of name  " + itemName);
		System.out.println("Value of dosage  " + dosageForm);
		return value.contains(itemName) && value.contains(dosageForm);
	}

	public void verifyDefaultValueInDropDownOnAddNewFacility(String fieldName, String text) {

		try {
			isElementDisplayed("dropdowns_externalSystem", fieldName);

			System.out.println("VALUE@@@@@@@@" + element("dropdowns_externalSystem", fieldName).getText());
			verifySelectedTextIsContainedInDropDown(element("dropdowns_externalSystem", fieldName), text);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		} catch (Exception ex) {
			isElementDisplayed("second_sort", fieldName);

			System.out.println("VALUE@@@@@@@@" + element("second_sort", fieldName).getText());
			verifySelectedTextIsContainedInDropDown(element("second_sort", fieldName), text);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		}
	}

	public void clickOnCheckboxForDistributorToMapWithFacility(String name) {
		isElementDisplayed("chkbox_dist", name);
		element("chkbox_dist", name).click();
	}

	public void checkDistributorIsAvailabelOrNot(String name) {
		isElementDisplayed("chkbox_dist", name);
	}

	public void checkDistributorIsInactiveOnDestination(String name) {
		Assert.assertFalse(isElementNotDisplayed("chkbox_dist", name));
	}

	public String clickFacilityEditLink(String facilityName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		isElementDisplayed("link_edit_of_added_record", facilityName);
		element("link_edit_of_added_record", facilityName).click();

		logMessage("Clicked on " + facilityName + " button to edit record");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

		return element("header_edit_dest").getText().trim();
	}

	public void clickHoldReasonCheckbox(String id) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		isElementDisplayed("settings_flag_facility", id);
		if (!checkboxIsSelectedUsingJavascript(id)) {
			element("settings_flag_facility", id).click();
			clickSaveButton();
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		}
	}

	public void unCheckHoldReasonCheckbox(String id) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		isElementDisplayed("settings_flag_facility", id);
		if (checkboxIsSelectedUsingJavascript(id)) {
			element("settings_flag_facility", id).click();
			clickSaveButton();
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		}
	}

	public boolean clickCheckBoxUsingId(String id) {
		isElementDisplayed("settings_flag_facility", id);
		element("settings_flag_facility", id).click();
		return checkSystemFlagIsChecked(id);
	}

	public boolean checkSystemFlagIsChecked(String id) {
		// isElementDisplayed("action_toggle_button_1", id);
		return checkboxIsSelectedUsingJavascript(id);

	}

	public void clickISAEditLink(String isaName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("button_isaEdit", isaName);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("button_isaEdit", isaName));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
	}

	public void clickOnPrepackCheckBox(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("prepack_checkbox", fieldName);
		Actions action = new Actions(driver);
		action.moveToElement(element("prepack_checkbox", fieldName)).click().build().perform();
		// element("prepack_checkbox", fieldName).click();
	}

	public void checkBulkItemIDIsMandatory(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 5);
		isElementDisplayed("bulk_item_id_mandatory", fieldName);
		Assert.assertTrue(element("bulk_item_id_mandatory", fieldName).getText().trim().contains("* Bulk Item ID"));
		logMessage("[ASSERTION PASSED]: Verified Bulk Item ID is mandatory after Prepack is checked");
	}

	public void checkPrepackConversionFactorIsMandatory(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 5);
		isElementDisplayed("prepack_conversion_factor", fieldName);
		Assert.assertTrue(element("prepack_conversion_factor", fieldName).getText().trim()
				.contains("* Prepack Conversion Factor"));
		logMessage("[ASSERTION PASSED]: Verified Prepack conversion factor is mandatory after Prepack is checked");
	}

	public void clickCheckboxForPrepackItems(String toggle) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		element("action_toggle_button_1", toggle).click();
	}

	public void verifyWebOrderCheckboxIsDisabled(String fieldName) {
		Assert.assertTrue(
				element("action_toggle_button_1", fieldName).getAttribute("class").trim().contains("disabled"));
	}

	public String enterItemNameForUsersOnDestination(String data) {
		isElementDisplayed("search_users_dest");
		enterTextInField(element("search_users_dest"), data);
		logMessage("Entered Item name in Search" + data);
		return data;
	}

	public void selectCheckboxForUsers(String toggle) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		element("checkbox_users", toggle).click();
	}

	public void selectCheckboxForCarouselISA() {
		element("checkbox_ControlCarouselISA").click();
	}

	public void selectDropdownForRO(String elem, String data) {
		wait.hardWait(5);
		List<WebElement> ele = getAllOptionsFromDropDown(element("dropdown_printer", elem));
		for (WebElement e : ele) {
			if (e.getText().equalsIgnoreCase(data)) {
				e.click();
			}

		}
	}

	public void verifyOrderInPODashboard(String type, String orderID) {

		Assert.assertTrue(element("items_PO_dashboard", type, orderID).isDisplayed());

	}

	public void setBulkItemId(String property) {
		wait.waitForElementToBeVisible(element("bulkItem_popUP"));

		isElementDisplayed("text_ox_bulk_item_id");
		element("text_box_bulk_item_id").sendKeys(property);
		Assert.assertTrue(element("text_box_bulk_item_id").getAttribute("value").trim().equalsIgnoreCase(property));

	}

	public void navaigateToSelectedShelf() {

		Actions actions = new Actions(driver);
		actions.moveToElement(element("shelf_location")).perform();
	}

	public void clickShelfOption(String item) {

		for (WebElement ele : elements("list_options_location")) {
			if (ele.getText().equalsIgnoreCase(item)) {
				ele.click();

			} else {
				continue;
			}
		}
	}

	public String enterItemPackageSize(String fieldName, String data) {
		isElementDisplayed("item_package_size", fieldName);
		enterTextInField(element("item_package_size", fieldName), data);

		return data;

	}

	public void selectFacilityDropDownOnItemManagement(String facility) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.hardWait(7);
		isElementDisplayed("dropdown_itemManagement");
		Actions action = new Actions(driver);
		Action buildAction = action.moveToElement(element("dropdown_itemManagement")).click()
				.sendKeys(element("dropdown_itemManagement"), facility).build();
		buildAction.perform();
		wait.hardWait(4);
		isElementDisplayed("span_facilityName", facility);
		element("span_facilityName", facility).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 45);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 45);
	}

	public void clickEditLinkItemLocation(String itemLocation) {
		isElementDisplayed("edit_link_item_location", itemLocation);
		element("edit_link_item_location", itemLocation).click();
		Assert.assertTrue(element("page_heading_edit_location").isDisplayed());
	}

	public String enterBulkItemNameInSearch(String data) {
		isElementDisplayed("bulk_item_search");
		enterTextInField(element("bulk_item_search"), data);
		logMessage("Entered Item name in Search" + data);
		return data;
	}

	public void clickOnSearchedBulkItem() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		element("bulk_item_click").click();
	}

	public String enterItemNameUnverifiedLinks(String data) {
		isElementDisplayed("unverified_item_search");
		enterTextInField(element("unverified_item_search"), data);
		logMessage("Entered Item name in Search" + data);
		return data;
	}

	public void clickCheckboxForEarliestExpiration(String toggle) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		element("action_toggle_button_1", toggle).click();
	}

	public void verifyAccountNumberFieldIsEnabled(String fieldName) {

		Assert.assertTrue(element("input_field_account_number", fieldName).isEnabled());

	}

	public boolean verifyColumnHeader(String columnHeaders) {
		/*
		 * flag = false; int count = 0; for (String col : columnHeaders) { if
		 * (isElementDisplayed("sort_icon", col)) { count++; } }x
		 * 
		 * if (count == 2) { flag = true; } return flag;
		 */

		return isElementDisplayed("header_dist_account", columnHeaders);
	}

	public String enterFixedValueInInputField(String fieldName, String data) {
		isElementDisplayed("input_field", fieldName);
		enterTextInField(element("input_field", fieldName), data);
		return data;
	}

	public String selectFacilityValueForDropDown(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		try {
			isElementDisplayed("facilityModelKey");
			wait.waitForElementToBeClickable(element("facilityModelKey"));
			selectProvidedTextFromDropDown(element("facilityModelKey"), data);
			Assert.assertEquals(getSelectedTextFromDropDown(element("facilityModelKey")), data);
		} catch (Exception e) {
			isElementDisplayed("facilityModelKey");

			wait.waitForElementToBeClickable(element("facilityModelKey"));
			selectProvidedTextFromDropDown(element("facilityModelKey"), data);
			Assert.assertEquals(getSelectedTextFromDropDown(element("facilityModelKey")), data);

		}
		return data;

	}

	public void verifyErrorMessageForBlankDistributorField(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		element("message_field_blank_distaccount").getText().equalsIgnoreCase(data);
	}

	public void clickEditLinkOnItemManagement(String itemID) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("button_editItemManagement", itemID);
		element("button_editItemManagement", itemID).click();
	}

	public void clickOnItemManagementFacility(String facility) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		isElementDisplayed("link_facilityItem", facility);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_facilityItem", facility));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
	}

	public boolean enterOnlyIntegerInAccountNumberFieldForSanity(String fieldName, String data) {
		String value = null;
		if (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("chrome")) {
			enterTextInField(element("textbox_accountNumberAddFacility_Sanity", fieldName), data);
			value = element("textbox_accountNumberAddFacility_Sanity", fieldName).getAttribute("value");
			System.out.println("Value of data " + value);
			if (value.matches("[0-9]+")) {
				return true;
			}
			return false;
		} else {
			element("textbox_accountNumberAddFacility").clear();
			enterTextInField(element("textbox_accountNumberAddFacility_Sanity"), data);
			isElementDisplayed("error_class");
			element("textbox_accountNumberAddFacility_Sanity").sendKeys("1234");
			value = element("textbox_accountNumberAddFacility_Sanity").getAttribute("value");
			if (value.matches("[0-9]+")) {
				return true;
			}
			return false;
		}
	}

	public boolean enterOnlyIntegerInAccountNumberFieldOnDestination(String fieldName, String data) {
		String value = null;
		if (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("chrome")) {
			enterTextInField(element("textbox_accountNumberAddDestination", fieldName), data);
			value = element("textbox_accountNumberAddDestination", fieldName).getAttribute("value");
			System.out.println("Value of data " + value);
			if (value.matches("[0-9]+")) {
				return true;
			}
			return false;
		} else {
			element("textbox_accountNumberAddDestination").clear();
			enterTextInField(element("textbox_accountNumberAddDestination"), data);
			isElementDisplayed("error_class");
			element("textbox_accountNumberAddDestination").sendKeys("1234");
			value = element("textbox_accountNumberAddDestination").getAttribute("value");
			if (value.matches("[0-9]+")) {
				return true;
			}
			return false;
		}
	}

	public void verifySuccessMessageOnSystemLabel(String successMessage) {
		wait.applyFluentWait(getLocator("popup_message_sys_label", successMessage), 120, 500);
		Assert.assertTrue(isElementDisplayed("popup_message_sys_label", successMessage));// .getText().trim().contains(successMessage));
		logMessage("[ASSERTION PASSED]: Verified Add a new Printer pop up on clicking add button");
	}

	public void verifyuomeditbuttonsDisabled() {
		isElementDisplayed("uom_list_editbuttondisabled");
		List<WebElement> elements = elements("uom_list_editbuttondisabled");
		for (WebElement ele : elements) {
			(ele).getAttribute("class").trim().contains("disable");
		}
		logMessage("ASSERTION PASSED: All Buttons are disabled");
	}

	public boolean verifyuomeditbuttonisEnabled() {
		isElementDisplayed("uom_list_editbuttonenabled");
		boolean value = element("uom_list_editbuttonenabled").isEnabled();
		logMessage("ASSERTION PASSED: All Buttons are enabled");
		return value;
	}

	public void clickLocationRackButton() {
		try {
			wait.hardWait(5);
			// wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
			isElementDisplayed("location_rack_button");
			hover(element("location_rack_button"));
			wait.waitForElementToBeClickable(element("location_rack_button"));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("location_rack_button"));
			logMessage("Clicked on" + "button to check rack status date");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		} catch (Exception e) {
			element("location_rack_button").click();
			logMessage("Clicked on" + "button to check rack status date");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		}
	}

	public void clickLocationRackButton1(String action) {
		try {
			wait.hardWait(5);
			isElementDisplayed("location_rack_button", action);
			hover(element("location_rack_button", action));
			wait.waitForElementToBeClickable(element("location_rack_button", action));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("location_rack_button", action));
			logMessage("Clicked on" + action + "button to check rack status date");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		} catch (Exception e) {
			element("location_rack_button", action).click();
			logMessage("Clicked on" + action + "button to check rack status date");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		}
	}

	public void clickLocationRackcyclecountdateButton() {
		try {
			wait.hardWait(5);
			isElementDisplayed("location_rack_cyclecountdate_button");
			hover(element("location_rack_cyclecountdate_button"));
			wait.waitForElementToBeClickable(element("location_rack_cyclecountdate_button"));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("location_rack_cyclecountdate_button"));
			logMessage("Clicked on" + "button to check self date");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		} catch (Exception e) {
			element("action_button").click();
			logMessage("Clicked on" + "button to check self daten");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		}
	}

	public void clickLocationRackcyclecountdateButton1(String action) {
		try {
			wait.hardWait(5);
			isElementDisplayed("location_rack_cyclecountdate_button", action);
			hover(element("location_rack_cyclecountdate_button", action));
			wait.waitForElementToBeClickable(element("location_rack_cyclecountdate_button", action));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("location_rack_cyclecountdate_button", action));
			logMessage("Clicked on" + action + "button to check self date");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		} catch (Exception e) {
			element("action_button").click();
			logMessage("Clicked on" + action + "button to check self daten");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		}
	}

	public void clickLocationcalenderbutton(String action) {
		try {
			wait.hardWait(5);
			isElementDisplayed("location_calender_button", action);
			hover(element("location_calender_button", action));
			wait.waitForElementToBeClickable(element("location_calender_button", action));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("location_calender_button", action));
			logMessage("Clicked on" + action + "button to check self date");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		} catch (Exception e) {
			element("action_button", action).click();
			logMessage("Clicked on" + action + "button to check self daten");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		}
	}

	public void ClicklocationCurrentDate(String field, String date) {
		int a = getCurrentDate();
		isElementDisplayed("location_date_value", field, date);
		wait.waitForElementToBeClickable(element("location_date_value", field, date));
		clickUsingXpathInJavaScriptExecutor(element("location_date_value", field, Integer.toString(a)));
		logMessage("ASSERTION PASSED: Clicked on Date from calendar");

	}

	public void ClicklocationCurrentDate1(String date) {
		int a = getCurrentDate();
		isElementDisplayed("location_date_value", date);
		wait.waitForElementToBeClickable(element("location_date_value", date));
		clickUsingXpathInJavaScriptExecutor(element("location_date_value", Integer.toString(a)));
		logMessage("ASSERTION PASSED: Clicked on Date from calendar");

	}

	public void clickLocationshelfButton() {
		try {
			wait.hardWait(5);
			isElementDisplayed("location_shelf_button");
			hover(element("location_shelf_button"));
			wait.waitForElementToBeClickable(element("location_shelf_button"));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("location_shelf_button"));
			logMessage("Clicked on" + "button to check shelf status button");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		} catch (Exception e) {
			element("location_shelf_button").click();
			logMessage("Clicked on" + "button to check rack status button");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		}
	}

	public void clickLocationshelfButton1() {
		try {
			wait.hardWait(5);
			isElementDisplayed("location_shelf_button1");
			hover(element("location_shelf_button1"));
			wait.waitForElementToBeClickable(element("location_shelf_button1"));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("location_shelf_button1"));
			logMessage("Clicked on" + "button to check shelf status button");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		} catch (Exception e) {
			element("action_button").click();
			logMessage("Clicked on" + "button to check rack status button");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		}
	}

	public void clickLocationshelfcyclecountdateButton() {
		try {
			wait.hardWait(5);
			isElementDisplayed("location_shelf_cyclecountdate_button");
			hover(element("location_shelf_cyclecountdate_button"));
			wait.waitForElementToBeClickable(element("location_shelf_cyclecountdate_button"));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("location_shelf_cyclecountdate_button"));
			logMessage("Clicked on" + "button to check self cycle count date");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		} catch (Exception e) {
			element("action_button").click();
			logMessage("Clicked on" + "button to check self daten");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		}
	}

	public String verifyprfilledvalueoflocationdatefield() {
		isElementDisplayed("location_date_field_value");
		// System.out.println("VALUE@@@@@@@@" +
		// element("location_date_field_value", fieldvalue).getTe());
		String value = element("location_date_field_value").getAttribute("value");
		System.out.println(value);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("location_date_field_value"));
		// hover(element("location_frame_hover"));
		return value;
	}

	public void selectValueFromRackDropdownByIndexForitemlocation(Integer Index) {
		isElementDisplayed("location_rack_dropdown");
		Select selectValue = new Select(element("location_rack_dropdown"));
		selectValue.selectByIndex(Index);
		logMessage("[STEP]: Value is selected from dropdown By index.");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);

	}

	/*
	 * public void selectValueFromrackDropDownonItemLocation(String data) {
	 * isElementDisplayed("location_rack_dropdown");
	 * wait.waitForElementToBeClickable(element("location_rack_dropdown"));
	 * clickUsingXpathInJavaScriptExecutorSingleClick(element(
	 * "location_rack_dropdown"));
	 * selectProvidedTextFromDropDown(element("location_rack_dropdown_option"),
	 * data); Assert.assertEquals(getSelectedTextFromDropDown(element(
	 * "location_rack_dropdown_option")), data); }
	 */
	/*
	 * public void selectValueFromrackDropDownonItemLocation(String fieldName,
	 * String data) { isElementDisplayed("location_rack_dropdown");
	 * wait.waitForElementToBeClickable(element("location_rack_dropdown"));
	 * clickUsingXpathInJavaScriptExecutorSingleClick(element(
	 * "location_rack_dropdown"));
	 * selectProvidedTextFromDropDown(element("location_rack_dropdown_option",
	 * fieldName), data); Assert.assertEquals(getSelectedTextFromDropDown(element(
	 * "location_rack_dropdown_option", fieldName)), data); }
	 */
	public void clickLocationshelfsfield(String action) {
		try {
			wait.hardWait(5);
			isElementDisplayed("location_shelfs_field", action);
			hover(element("location_shelfs_field", action));
			wait.waitForElementToBeClickable(element("location_shelfs_field", action));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("location_shelfs_field", action));
			logMessage("Clicked on" + action + "button to check shelf field");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		} catch (Exception e) {
			element("location_shelf_button", action).click();
			logMessage("Clicked on" + action + "button to check rack status button");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		}
	}

	public void clickLocationshelfsmenu(String action) {
		try {
			wait.hardWait(5);
			isElementDisplayed("location_shelfs_menu", action);
			hover(element("location_shelfs_menu", action));
			wait.waitForElementToBeClickable(element("location_shelfs_menu", action));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("location_shelfs_menu", action));
			logMessage("Clicked on" + action + "button to check shelfs menu");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		} catch (Exception e) {
			element("action_button", action).click();
			logMessage("Clicked on" + action + "button to check rack status button");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		}
	}

	public void verifyErrorMessageSchedule(String mesg) {
		isElementDisplayed("message_field_sched", mesg);

	}

	public void verifyFieldsOnEditLocation(String element) {
		isElementDisplayed("edit_location_field", element);
		logMessage("ASSERTION PASSED : Verified field on Edit Location page" + element);
	}

	public void verifyValueEditLocation(String element, String value) {
		isElementDisplayed("value_edit_location", element);
		Assert.assertTrue(element("value_edit_location", element).getAttribute("value").equalsIgnoreCase(value));
		logMessage("ASSERTION PASSED:Verified the value for Field" + element);

	}

	public void verifyUserIsOnLayoutPage() {
		wait.hardWait(10);
		isElementDisplayed("location_layout");
		logMessage("ASSERTION PASSED: User is on Layout page");

	}

	public void verifyPageHeader_Sanity(String pageTitle) {
		try {
			isElementDisplayed("header_page");
			Assert.assertTrue(element("header_page").getText().trim().equalsIgnoreCase(pageTitle));
			logMessage("[STEP]: Page Header is verified successfully.");
		} catch (Exception e) {

			isElementDisplayed("header_page");
			Assert.assertTrue(element("header_page").getText().trim().equalsIgnoreCase(pageTitle));
			logMessage("[STEP]: Page Header is verified successfully.");
		}
	}

	public void selectUserForRemoteOrder(String data) {

		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 80);
		isElementDisplayed("user_for_remoteorder", data);
		element("user_for_remoteorder", data).click();

	}

	public void verifyUserAvailableInList() {

		isElementDisplayed("ro_uers_list");
		Assert.assertFalse(elements("ro_uers_list").isEmpty());
		logMessage("[ASSERTION PASSED]: User list is available in Users Tab");

	}

	public String getItemNameHavingLocationAssigned() {
		isElementDisplayed("item_with_Assigned_location");
		return element("item_with_Assigned_location").getText();
	}

	public void verifyWarningMessageOnEditingAScheduleWhichIsAssociatedToRoutingRule() {
		Assert.assertTrue(switchToAlert().getText().trim()
				.contains("The schedule is currently in use. Do you want to continue?"));
		switchToAlert().accept();
	}

	public void clickOnADMRoundingQuantityFlag(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		isElementDisplayed("adm_rounding_flag", fieldName);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("adm_rounding_flag", fieldName));
	}

	public void verifyTransactionPrioritiesAreDisplayed() {
		List<WebElement> elements = elements("txn_priorities_results");
		wait.waitForElementsToBeVisible(elements);
		int size = elements.size();
		logMessage("Priorities count= " + size);
	}

	public boolean verifyPriorities(ArrayList<String> priorities) {

		List<String> priority = new ArrayList<String>();
		List<WebElement> tabsData = elements("txn_priorities_results");

		for (int i = 0; i < tabsData.size(); i++) {
			priority.add(tabsData.get(i).getText());
		}

		// List<String> priority=elements.add(elements.toString());
		for (int i = 0; i < priorities.size(); i++) {

			if (priority.contains(priorities.get(i))) {
				continue;

			} else {
				logMessage("Transaction Priority " + priorities.get(i) + " is not listed on the screen.");
				return false;
			}
		}
		logMessage("All System generated Transaction Priorities are listed on the screen.");
		return true;

	}

	public boolean verifyNewlyAddedPriorityInTheList(String priorityName) {
		try {
			return element("button_edit_transactionPriority", priorityName).isDisplayed();

		} catch (Exception e) {
			return false;
		}
	}

	public void verifypriorityOrder(String priorityName) {
		List<String> priority = new ArrayList<String>();
		List<WebElement> tabsData = elements("txn_priorities_results");

		for (int i = 0; i < tabsData.size(); i++) {
			priority.add(tabsData.get(i).getText());
		}

		// List<String> priority=elements.add(elements.toString());
		for (int i = 0; i < priority.size(); i++) {

			if (priority.get(i).equals(priorityName)) {
				System.out.print("Priority " + priorityName + "found at position=" + i);
				logMessage("Priority " + priorityName + "found at position=" + i);

			} else {
				continue;
			}
		}

	}

	public boolean verifyFieldIsNotEditable(String fieldName) {
		try {
			return isElementDisplayed("input_field", fieldName);

		} catch (Exception e) {
			return false;
		}
	}

	public void clickCheckboxRO(String toggle) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		element("action_toggle_button_ro", toggle).click();
	}

	public String enterRandomValueInInputFieldRO(String fieldName, String data) {
		isElementDisplayed("input_field_ro", fieldName);
		enterTextInField(element("input_field_ro", fieldName), data);

		return data;
	}

	public void clickOnEditLinkCorresspondingToAssignedItem(String Location) {
		isElementDisplayed("link_edit", Location);
		clickUsingXpathInJavaScriptExecutor(element("link_edit", Location));
		logMessage("Clicked on Edit link corressponding to assigned item " + Location);

	}

	public void verifyRackDropdownOnLocationPage() {
		isElementDisplayed("rack_dropdown");
		logMessage("ASSERTION PASSED: Verified Dropdown for Racks");
	}

	public void verifyDots(String element) {
		scrollUp();
		isElementDisplayed("rack_dots", element);
		logMessage("ASSERTION PASSED: Verified three Dots");

	}

	public void clickDotsOnItemLocation(String element) {
		scrollUp();
		isElementDisplayed("rack_dots", element);
		logMessage("ASSERTION PASSED: Verified three Dots");
		Actions action = new Actions(driver);
		wait.hardWait(10);
		Action seriesOfAction = (Action) action.moveToElement(element("rack_dots", element)).click().build();
		seriesOfAction.perform();
		logMessage("ASSERTION PASSED: Clicked on dots");
		/*
		 * wait.hardWait(3); isElementDisplayed("add_rack",element);
		 * logMessage("ASSERTION PASSED: Verified 'Add Rack' option");
		 */

	}

	public void clickBinDotsOnItemLocation(String element, String num) {
		scrollUp();
		isElementDisplayed("bin_dots", element, num);
		logMessage("ASSERTION PASSED: Verified three Dots");
		Actions action = new Actions(driver);
		wait.hardWait(10);
		Action seriesOfAction = (Action) action.moveToElement(element("bin_dots", element, num)).click().build();
		seriesOfAction.perform();
		logMessage("ASSERTION PASSED: Clicked on dots");
		/*
		 * wait.hardWait(3); isElementDisplayed("add_rack",element);
		 * logMessage("ASSERTION PASSED: Verified 'Add Rack' option");
		 */

	}

	public void clickVerifySelectedSlot() {
		wait.hardWait(3);
		isElementDisplayed("empty_unassigned_slot");
		hover(element("empty_unassigned_slot"));
		// wait.waitForElementToBeClickable(element("add_rack"));
		clickUsingXpathInJavaScriptExecutor(element("empty_unassigned_slot"));
		// element("add_rack").click();
		logMessage("Clicked on one slot per bin when it is empty & unassigned & adjacent slot");

	}

	public void clickemptyslot(String element) {
		wait.hardWait(3);
		isElementDisplayed("empty_slot", element);
		hover(element("empty_slot", element));
		// wait.waitForElementToBeClickable(element("add_rack"));
		clickUsingXpathInJavaScriptExecutor(element("empty_slot", element));
		// element("add_rack").click();
		logMessage("Clicked the element & grouped with bin");

	}

	public void clickverifygroupwithBin(String element) {
		wait.hardWait(3);
		isElementDisplayed("group_with_rightleftbin", element);
		hover(element("group_with_rightleftbin", element));
		// wait.waitForElementToBeClickable(element("add_rack"));
		clickUsingXpathInJavaScriptExecutor(element("group_with_rightleftbin", element));
		// element("add_rack").click();
		logMessage("Clicked the element & grouped with bin");

	}

	public void clickAddRacksOrShelfOrBin(String element) {

		wait.hardWait(3);
		isElementDisplayed("add_rack", element);
		hover(element("add_rack", element));
		// wait.waitForElementToBeClickable(element("add_rack"));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("add_rack", element));
		// element("add_rack").click();
		logMessage("Clicked the element");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.hardWait(2);

	}

	public void verifyErrorMessageForRacks(String message) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		isElementDisplayed("racks_error_msg");
		logMessage("ASSERTION PASSED: Verified Error message for Racks");
		Assert.assertTrue(element("racks_error_msg").getText().equalsIgnoreCase(message));
	}

	public void verifySuccessMessageOnAddRack() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		isElementDisplayed("success_message_add_rack");
		logMessage("ASSERTION PASSED: Verified Success message on Add Rack");
	}

	public void hoverOverShelf(String field) {
		isElementDisplayed("shelf", field);
		hover(element("shelf", field));
	}

	public void verifyBins() {
		isElementDisplayed("bin_area");
		logMessage("ASSERTION PASSED: Verified Bins for the selected shelf");
	}

	public void performActionOnShelf(String action) {
		wait.hardWait(3);
		isElementDisplayed("shelf_actions", action);
		logMessage("ASSERTION PASSED: Vefified action");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("shelf_actions", action));
		logMessage("Clicked the element");

	}

	public void clickSaveButtonForOffset() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("save_btn_applyoffset");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("save_btn_applyoffset"));
		logMessage("Clicked on Save button");
	}

	public void clickcopyfacilitylist() {
		isElementDisplayed("copy_facility_list");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("copy_facility_list"));
		logMessage("selct facility from list");
	}

	public void verifyActionsOnDots(String action) {

		try {
			wait.hardWait(3);
			isElementDisplayed("shelf_actions", action);
			logMessage("ASSERTION PASSED: Vefified action");
		} catch (Exception e) {
			isElementDisplayed("shelf_actions_text", action);
			logMessage("ASSERTION PASSED: Vefified action");
		}

	}

	public void verifygroupwithBin(String action) {
		wait.hardWait(3);
		isElementDisplayed("group_with_rightleftbin", action);
		logMessage("ASSERTION PASSED: Vefified action");

	}

	public void clickShelf() {
		isElementDisplayed("click_shelf");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_shelf"));
		logMessage("Clicked over shelf 2");
	}

	public void verifyShelfDot() {
		isElementDisplayed("shelf_dot");
		logMessage("VErified dots");
	}

	public void clickShelfDot() {

		isElementDisplayed("shelf_dot");
		logMessage("VErified dots");
		Actions action = new Actions(driver);
		wait.hardWait(10);
		Action seriesOfAction = (Action) action.moveToElement(element("shelf_dot")).click().build();
		seriesOfAction.perform();

	}

	public void clickDeleteScheduleLink(String scheduleName, String string) {
		isElementDisplayed("link_edit_duplicate_schedule", scheduleName, string);
		// element("link_delete", scheduleName).click();
		Actions action = new Actions(driver);
		action.moveToElement(element("link_edit_duplicate_schedule", scheduleName, string)).click().build().perform();

	}

	public void clickDeleteTherapeuticLink(String scheduleName, String string) {
		isElementDisplayed("delete_link_therapeutic", scheduleName);
		// element("link_delete", scheduleName).click();
		Actions action = new Actions(driver);
		action.moveToElement(element("delete_link_therapeutic", scheduleName)).click().build().perform();

	}

	public void verifyDeleteMessage(String string) {
		isElementDisplayed("popup_msg_delete_schedule", string);

	}

	public boolean verifyScheduleIsNotPressent(String recordName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		Assert.assertFalse(isElementNotDisplayed("link_edit_of_added_record", recordName),
				"[ASSERTION FAILED] : Record " + recordName + " is displayed");
		return isElementNotDisplayed("link_edit_of_added_record", recordName);
	}

	public void verifyDropdownFieldIsDisabled(String fieldName) {
		isElementDisplayed("dropdown_printer", fieldName);
		boolean value = element("dropdown_printer", fieldName).isEnabled();
		Assert.assertEquals(value, false);
		logMessage("[ASSERTION PASSED]: Input field " + fieldName + " is disabled");
	}

	public void clickToDeleteAndVerifyProductID(String productID) {
		isElementDisplayed("delete_productID", productID);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("delete_productID", productID));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);

		Assert.assertFalse(isElementNotDisplayed("delete_productID", productID),
				"[ASSERTION FAILED]: Product ID is not deleted.");
		logMessage("[ASSERTION PASSED]: Product ID deleted successfully.");

	}

	public boolean searhNonExistedFacility(String NoExsistedfacilityName) {
		wait.waitForElementToBeVisible(element("searchFieldFacility"));
		isElementDisplayed("searchFieldFacility");
		element("searchFieldFacility").click();
		element("searchFieldFacility").sendKeys(NoExsistedfacilityName);
		wait.hardWait(5);
		wait.waitForElementToBeVisible(element("noMatchingresult"));
		boolean stat = isElementDisplayed("noMatchingresult");
		logMessage("No matching text is displayed successfully");
		flag = stat;
		return flag;

	}

	public boolean searhNonExistedPIS(String NoExsistedPIS) {

		wait.waitForElementToBeVisible(element("searchFieldFacility"));
		isElementDisplayed("searchFieldFacility");
		element("searchFieldFacility").click();
		element("searchFieldFacility").sendKeys(NoExsistedPIS);
		wait.hardWait(5);
		wait.waitForElementToBeVisible(element("noMatchingresult"));
		boolean stat = isElementDisplayed("noMatchingresult");
		logMessage("No matching text is displayed successfully");
		flag = stat;
		return flag;
	}

	public boolean verifyName_PIS_status(String facility) {
		try {
			wait.waitForElementToBeVisible(element("searchFieldFacility"));
			element("searchFieldFacility").sendKeys(facility);
			wait.hardWait(5);
			isElementDisplayed("searchResultByFacility", facility);
			isElementDisplayed("activeStatus");
			logMessage("User can see the facility column and status column.");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}

	public boolean sortByFcility_PIS_Status() {
		try {
			wait.waitForElementToBeVisible(element("searchFieldFacility"));
			element("facilityNameColumn").click();
			wait.hardWait(2);
			element("PISColumn").click();
			element("statusColumn").click();
			logMessage("Sorting button are working fine for name, statud and PIS.");
			flag = true;
			return flag;

		} catch (Exception e) {
			flag = true;
			return flag;

		}

	}

	public boolean ToggleFunction() {

		try {
			wait.waitForElementToBeVisible(element("searchFieldFacility"));
			String toggleStatus = element("toggleValue").getAttribute("value");
			if (toggleStatus.contains("false")) {
				logMessage("All the active facilities are showing on the screen.");
				flag = true;
				return flag;
			}

		} catch (Exception e) {
			element("toggleButton").click();
			String toggleStatus = element("toggleValue").getAttribute("value");
			if (toggleStatus.contains("true")) {
				logMessage("All the Inactive facilities are showing on the screen.");
				flag = true;
				return flag;
			}
		}
		return flag;

	}

	public boolean verifyActiveFacilityByDefault(String Facility) {

		try {
			wait.waitForElementToBeVisible(element("searchFieldFacility"));
			element("searchFieldFacility").sendKeys(Facility);
			wait.hardWait(5);
			isElementDisplayed("searchResultByFacility", Facility);
			isElementDisplayed("activeStatus");
			logMessage("User can see the status as active by default.");
			flag = true;
			return flag;
		} catch (Exception e) {
			// flag = true;
			return flag;

		}

	}

	public boolean searchByFacilityName(String favilityName) {
		try {
			wait.waitForElementToBeVisible(element("searchFieldFacility"));
			element("searchFieldFacility").sendKeys(favilityName);
			wait.hardWait(5);
			isElementDisplayed("searchResultByFacility", favilityName);
			logMessage("User is able to search the facility based on text entered.");
			flag = true;
			return flag;
		} catch (Exception e) {
			// flag = true;
			return flag;

		}

	}

	public boolean searchByPIS(String facilityName) {
		String PISNumber;
		try {

			wait.waitForElementToBeVisible(element("searchFieldFacility"));
			element("searchFieldFacility").sendKeys(facilityName);
			wait.hardWait(5);
			isElementDisplayed("searchResultByFacility", facilityName);
			PISNumber = element("PISResult").getText();
			wait.hardWait(2);
			element("searchFieldFacility").clear();
			element("searchFieldFacility").sendKeys(PISNumber);
			wait.hardWait(3);
			isElementDisplayed("PISResult");
			flag = true;
			return true;
		} catch (Exception e) {

			return true;

		}

	}

	public void clickTabEvent(String flagname) {
		Actions action = new Actions(driver);

		Action seriesOfAction = (Action) action.moveToElement(element("label_TransactionPriorities", flagname)).build();
		wait.elementHighlight(element("label_TransactionPriorities", flagname));
		seriesOfAction.perform();

	}

	public void clickEditLinkOfApprovedComputer(String fieldName, String pageTitle) {
		isElementDisplayed("edit_approved_computer", fieldName);
		element("edit_approved_computer", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("edit_approved_comp_modal", pageTitle);

	}

	public void verifyScheduleDisplayedOnBasisOfLastUpdate(String colNum, String scheduleName) {

		Assert.assertEquals(element("first_record_of_column", colNum).getText(), scheduleName);

	}

	public String getcolor(String fieldName) {
		isElementDisplayed("list_link_text", fieldName);
		String color = element("list_link_text", fieldName).getCssValue("color");
		logMessage("Color of the WebElement is " + color);
		return color;
	}

	public boolean verifySettingsPageHeader(String Header) {
		try {
			wait.elementHighlight(element("settings_header", Header));
			return isElementDisplayed("settings_header", Header);

		} catch (Exception e) {

			return false;
		}

	}

	public boolean verifyItemsTabonItemscreen(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		boolean flag = isElementDisplayed("items_on_itemsTab", fieldName);
		logMessage("[ASSERTION PASSED]: Verified items on Items tab " + fieldName);
		return flag;
	}

	public boolean verifyPendingApprovalTabonItemscreen(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		try {
			return isElementNotDisplayed("items_on_itemsTab", fieldName);
		} catch (Exception e) {

			return false;
		}
	}

	public boolean verifyItemsonItemscreen(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		try {
			return isElementDisplayed("link_edit_of_added_record", fieldName);
		} catch (Exception e) {
			return false;
		}
	}

	public void clickPendingApprovalTabonItemScreen(String itemid) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("items_on_itemsTab", itemid);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("items_on_itemsTab", itemid));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);

	}

	public void verifycheckboxispresent(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		isElementDisplayed("checkbox_item_tab", fieldName);
		logMessage("[ASSERTION PASSED]: Verified checkbox is present " + fieldName);
	}

	public void verifycheckbox1ispresent(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		isElementDisplayed("checkbox_item_tab1", fieldName);
		logMessage("[ASSERTION PASSED]: Verified checkbox1 is present " + fieldName);
	}

	public boolean selectAllCheckboxesonItemScreen() {
		// wait.waitForPageToLoadCompletely();
		flag = false;
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		try {
			if (isElementDisplayed("checkbox_pendingApprovalTab")) {
				element("checkbox_pendingApprovalTab").click();
				System.out.println("clicked on All checkbox!!!!");
				logMessage("User has clicked on all checkbox  pending approval tab.");
				flag = true;
			}
		} catch (Exception e) {
			if (isElementDisplayed("checkbox_pendingApprovalTab")) {
				clickUsingXpathInJavaScriptExecutorSingleClick(element("checkbox_pendingApprovalTab"));
				System.out.println("clicked on All checkbox!!!!");
				logMessage("User has clicked on all checkbox in pending approval tab.");
				flag = true;
			}
		}
		return flag;
	}

	public boolean selectPreferredDistributor(String productID) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("preferred_product_id", productID);
		try {
			wait.waitForElementToBeClickable(element("preferred_product_id", productID));
			wait.elementHighlight(element("preferred_product_id", productID));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("preferred_product_id", productID));
			return element("preferred_product_id", productID).isSelected();

		} catch (Exception e) {
			element("preferred_product_id", productID).click();
			return element("preferred_product_id", productID).isSelected();
		}

	}

	public boolean verifyChecboxOfParticipatingFacilityIsChecked(String facilityName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("checkbox_participating_facility", facilityName);
		String id = element("checkbox_participating_facility", facilityName).getAttribute("for");
		return checkboxIsSelectedUsingJavascript(id);
		/*
		 * if(!checkboxIsSelectedUsingJavascript(id)){
		 * wait.elementHighlight(element("checkbox_participating_facility",
		 * facilityName)); clickUsingXpathInJavaScriptExecutorSingleClick(element(
		 * "checkbox_participating_facility",facilityName)); }
		 */
	}

	public void SelectChecboxOfParticipatingFacility(String facilityName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("checkbox_participating_facility", facilityName);
		String id = element("checkbox_participating_facility", facilityName).getAttribute("for");

		if (!checkboxIsSelectedUsingJavascript(id)) {
			wait.elementHighlight(element("checkbox_participating_facility", facilityName));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("checkbox_participating_facility", facilityName));
		}
	}

	public void clickToAddPreferredDistributorName(String productID) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("add_distributor_As_per_productID", productID);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("add_distributor_As_per_productID", productID));

	}

	public void clickToAddPreferredDistributorNameAtFacilityLevel(String productID) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("add_distributor_as_per_productID_at_facility_level", productID);
		clickUsingXpathInJavaScriptExecutorSingleClick(
				element("add_distributor_as_per_productID_at_facility_level", productID));
	}

	public boolean checkDistributorNamePresentOrNotOnPOCard(String distributor, String isa) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.hardWait(10);
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		System.out.println("Value of date = " + date1);
		System.out.println("Value of distributor = " + distributor);
		System.out.println("Value of isa = " + isa);
		String value = element("distributorname_POCard").getAttribute("value");
		System.out.println("Value of value = " + value);
		return value.contains(distributor) && value.contains(isa) && value.contains("ControlledTwo")
				&& value.contains(date1);
	}

	public boolean valueNotPresentOnPO(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.hardWait(10);
		String value = element("distributorname_POCard").getAttribute("value");
		System.out.println("Value of value = " + value);
		return !value.contains(data);
	}

	/*
	 * public void selectPreferredDistributor(String productID) {
	 * isElementDisplayed("preferred_product_id", productID); }
	 */

	public void clickOnAddButtonToAddMigration() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForElementToBeClickable(element("btn_add"));
		isElementDisplayed("btn_add");
		clickUsingXpathInJavaScriptExecutor(element("btn_add"));
		logMessage("[STEP]: Clicked on Add button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForElementToBeVisible(element("popup_for_add"));
		Assert.assertTrue(element("popup_for_add").getText().trim().contains("New Migration"));
		logMessage("[ASSERTION PASSED]: Verified New Migration pop up on clicking add button");
	}

	public void clickStartMigrationButton(String migName) {
		isElementDisplayed("save_button", "save");
		// wait.waitForJStoLoad();
		clickUsingXpathInJavaScriptExecutorSingleClick(element("save_button", "save"));
		// wait.hardWait(5);
		logMessage("Clicked on Save button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 140);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 140); // don't remove this, reqd for external systems

		isElementDisplayed("migration_name_col", migName);
		Assert.assertTrue(element("migration_name_col", migName).isDisplayed());
		isElementDisplayed("migration_status", migName, "InProgress");
		logMessage("[ASSERTION PASSED]: Newly added migration : " + migName + " is displayed in List");

		// wait for 2 min to get the migration from In Progress to Completed State
		try {
			Thread.sleep(120 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void verifyCompletedStatusForMigrationAfterTwoMin(String migrationName, String status) {
		driver.navigate().refresh();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 140);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 140); // don't remove this, reqd for external systems

		isElementDisplayed("migration_name_col", migrationName);
		Assert.assertTrue(element("migration_name_col", migrationName).isDisplayed());
		isElementDisplayed("migration_status", migrationName, status);
	}

	public boolean verifyUserIsMappedForRO(String user) {

		return isElementNotDisplayed("item_in_destnation", user);
	}

	public boolean verifyItemIsMappedForRO(String itemName) {

		return isElementNotDisplayed("item_in_destnation", itemName);

	}

	public String enterRandomValueInInputField_RO(String fieldName, String data) {
		isElementDisplayed("input_field", fieldName);
		wait.hardWait(5);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("input_field", fieldName));
		EnterTextInFieldByJavascript(fieldName, data);
		wait.hardWait(10);
		return data;
	}

	public void verifyPopupMessageSystemLabel(String data) {
		wait.waitForElementToBeVisible(element("popup_cancel_systemlabel"));
		Assert.assertTrue(element("popup_cancel_systemlabel").getText().trim().equalsIgnoreCase(data));
		logMessage("[ASSERTION PASSED]: Toggle popup message is verified successfully.");
	}

	public boolean showActiveISA(String facility) {
		wait.waitForElementToBeVisible(element("searchFieldFacility"));
		element("searchFieldFacility").sendKeys(facility);
		wait.hardWait(5);
		isElementDisplayed("searchResultByFacility", facility);
		isElementDisplayed("activeStatus");
		logMessage("User can see the active status of selected facility.");
		flag = true;
		return flag;

	}

	public boolean sortByFacilityName() {

		try {
			wait.waitForElementToBeVisible(element("searchFieldFacility"));
			click(element("FacilityHeader"));
			wait.hardWait(3);
			String sortVlue = element("facilityHeaderSort").getAttribute("class");
			if (sortVlue.contains("sort-asc")) {
				logMessage("Facility has been sorted by asecending order by facility name");
				flag = true;
				return flag;
			}
		} catch (Exception e) {
			click(element("FacilityHeader"));
			wait.hardWait(3);
			String sortVlue = element("facilityHeaderSort").getAttribute("class");
			if (sortVlue.contains("sort-desc")) {
				logMessage("Facility has been sorted by decending order by facility name");
				flag = true;
				return flag;
			}

		}
		return flag;

	}

	public boolean keyBoardTab() {
		element("searchFieldFacility").sendKeys(Keys.TAB);
		element("addButtonFacility").sendKeys(Keys.TAB);
		logMessage("User can move on the facility screen using keyboard function.");
		flag = true;
		return flag;

	}

	public boolean sortByPISName() {

		try {
			wait.waitForElementToBeVisible(element("searchFieldFacility"));
			click(element("PISHeader"));
			wait.hardWait(3);
			String sortVlue = element("facilityHeaderSort").getAttribute("class");
			if (sortVlue.contains("sort-asc")) {
				logMessage("PIS column has been sorted in asecending order by PIS by PIS Name");
				flag = true;
				return flag;
			}
		} catch (Exception e) {
			click(element("PISHeader"));
			wait.hardWait(3);
			String sortVlue = element("facilityHeaderSort").getAttribute("class");
			if (sortVlue.contains("sort-desc")) {
				logMessage("PIS column has been sorted in decending order by PIS Name");
				flag = true;
				return flag;
			}

		}
		return flag;

	}

	public boolean sortByStatus() {

		try {
			wait.waitForElementToBeVisible(element("searchFieldFacility"));
			click(element("StatusHeader"));
			wait.hardWait(3);
			String sortVlue = element("facilityHeaderSort").getAttribute("class");
			if (sortVlue.contains("sort-asc")) {
				logMessage("Status column has been sorted in ascending order.");
				flag = true;
				return flag;
			}
		} catch (Exception e) {
			click(element("StatusHeader"));
			wait.hardWait(3);
			String sortVlue = element("facilityHeaderSort").getAttribute("class");
			if (sortVlue.contains("sort-desc")) {
				logMessage("Status column has been sorted in decending order.");
				flag = true;
				return flag;
			}

		}
		return flag;

	}

	public boolean sendKeysBarcode(String key) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("barCodevalueTextbox").isDisplayed();
		element("barCodevalueTextbox").clear();
		wait.hardWait(2);

		for (int i = 0, n = key.length(); i < n; i++) {
			element("barCodevalueTextbox").sendKeys(Character.toString(key.charAt(i)));
		}
		logMessage("BarCode: " + key + " typed successfully");
		return bool;
	}

	public boolean sendKeysBarcodeAll() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("barCodevalueTextbox").click();
		boolean bool = element("barCodevalueTextbox").isDisplayed();
		element("barCodevalueTextbox").clear();
		wait.hardWait(3);
		element("barCodevalueTextbox").sendKeys("01003");
		wait.hardWait(3);
		element("barCodevalueTextbox").sendKeys("80042");
		wait.hardWait(3);
		element("barCodevalueTextbox").sendKeys("02001");
		wait.hardWait(3);
		element("barCodevalueTextbox").sendKeys("01710");
		wait.hardWait(3);
		element("barCodevalueTextbox").sendKeys("050");
		wait.hardWait(3);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("BarCode Value Typed Successfully");
		return bool;
	}

	public boolean clickOnSearchButton() {

		boolean bool = element("barCodeSearchClick").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("barCodeSearchClick").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		logMessage("BarCode Search Clicked Successfully");
		return bool;
	}

	public boolean clickOnSortArrowItemID() {

		boolean bool = element("sortingCursor").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Sorting Button Based Upon ItemID is Clickable");
		return bool;
	}

	public boolean clickOnSortArrowItem() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("sortingCursor").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Sorting Button Based Upon Item is Clickable");
		return bool;
	}

	public boolean cannotSortOnBasisOfActions() throws NoSuchElementException

	{
		wait.hardWait(8);
		try {
			boolean bool = driver.findElement(By.xpath("sortingCursonItemAction")).isDisplayed();
			return bool;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean verifyScanAnother() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("scanAnotherButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Scan Another Button is Clickable");
		return bool;
	}

	public boolean verifyUnlinkButton() {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 50);
		wait.hardWait(5);
		boolean bool = element("unlinkButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Unlinking Button is Visible");
		return bool;
	}

	public boolean clickUnlinkButton() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		boolean bool = element("unlinkButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("unlinkButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 75);
		logMessage("Unlinking Button is clicked");
		return bool;
	}

	public boolean verifyUnlinkMessage() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("unlinkButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Unlinking Button Message Verified");
		return bool;
	}

	public boolean clickContinueButton() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		boolean bool = element("continueButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		element("continueButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		logMessage("Continue Button is Clicked");
		return bool;
	}

	public boolean clickLinkHomeButton() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		boolean bool = element("linkButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		element("linkButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		logMessage("Link Button is Clicked");
		return bool;
	}

	public boolean clickRelinkButton() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		boolean bool = element("relinkButton").isDisplayed();
		// element("relinkButton").click();
		clickUsingXpathInJavaScriptExecutorSingleClick(element("relinkButton"));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 75);
		logMessage("Relink Button is Clicked");
		return bool;

	}

	public boolean clickResetButton() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		boolean bool = element("resetButton").isDisplayed();
		element("resetButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		logMessage("Reset Button is Clicked");
		return bool;
	}

	public boolean verifyBarcodeStillAssociated() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("barcode").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Unlinking Button Message Verified");
		return bool;
	}

	public boolean verifyScanAnotherBarcode() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("scanAnother").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Scan Another Button is Clickable");
		return bool;
	}

	public boolean verifyCantUnlinkMessage() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 5);
		boolean bool = element("popup_message").isDisplayed();
		// String msgString = "Error : Cannot unlink preferred Product ID.
		// Please\r\n" +
		// "re-select preferred Product ID for item and try again.";
		String msgString = "Error : Cannot unlink preferred Product ID";
		Assert.assertTrue(element("popup_message").getText().toLowerCase().contains(msgString.toLowerCase()));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Can't Unlink Message Verified Successfully");
		return bool;
	}

	// ======================Bin Options================

	public void selectDropDownValue(String value) {
		wait.hardWait(15);
		WebElement element = driver.findElement(By.xpath("//select/option[text()='" + value + "']"));
		element.click();
		logMessage("STEP : " + value + " is selected in drop down");
		wait.hardWait(15);
	}

	public boolean clickEditItemAction() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("editButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("editButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Edit Button is Clicked");
		return bool;
	}

	public boolean clickEditISAItemAction() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("editISAButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("editISAButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Edit Button is Clicked");
		return bool;
	}

	public boolean clickAssignButtonForLocation() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("assignButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("assignButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Assign Button is Clicked");
		return bool;
	}

	public void selectDropDownValueISA(String value) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		WebElement element = driver.findElement(By.xpath("//select/option[text()='" + value + "']"));
		element.click();
		logMessage("STEP : " + value + " is selected in drop down");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
	}

	public boolean clickSaveAssignButtonForLocation() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("saveAssignedLocation").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("saveAssignedLocation").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Save Assign Button is Clicked");
		return bool;
	}

	public boolean verifyPurpleColor(String color) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("assignedColorPurple").isDisplayed();
		String col = element("assigned_slot").getCssValue("background-color").replaceAll("\\s", "").replace("rgba", "")
				.replace("(", "").replace(")", "");
		Assert.assertEquals(col, color);
		logMessage("ASSERTION PASSED: Purple Color Verified");

		return bool;
	}

	public boolean verifyTotalInHandQuantity() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("totalInHandQuantity").isDisplayed();
		logMessage("Total In Hand Quantity Verified");
		return bool;
	}

	public boolean verifyGreenColor(String color) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);

		List<WebElement> elements = elements("available_slot");
		for (int i = 0; i < elements.size(); i++) {
			String col = element("available_slot").getCssValue("background-color").replaceAll("\\s", "")
					.replace("rgba", "").replace("(", "").replace(")", "");
			Assert.assertEquals(col, color);
		}
		logMessage("ASSERTION PASSED: Green Color Verified");
		return true;
	}
	
	
	public boolean verifyGreyColor(String color) {
		wait.hardWait(10);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		boolean bool = element("other_item_assigned").isDisplayed();
		String col = element("other_item_assigned").getCssValue("background-color").replaceAll("\\s", "")
				.replace("rgba", "").replace("(", "").replace(")", "");
		Assert.assertEquals(col, color);
		logMessage("ASSERTION PASSED: Grey Color Verified");
		return bool;
	}
	
	
	public boolean clickISAConfiguration() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("clickISAConfiguration").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("clickISAConfiguration").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Click ISA Config Tab");
		return bool;
	}

	public boolean sendKeysVerticalDividers(String key) {

		boolean bool = element("verticalDividerNumber").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		element("verticalDividerNumber").clear();
		element("verticalDividerNumber").sendKeys(key);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Vertical Dividers Value Updated successfully");
		return bool;
	}

	public boolean sendKeysHorizontalDividers(String key) {

		boolean bool = element("horizontalDividerNumber").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		element("horizontalDividerNumber").clear();
		element("horizontalDividerNumber").sendKeys(key);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Horizontal Dividers Value Updated successfully");
		return bool;
	}

	public boolean verifyInvalidvalueMessage() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("invalidMessage").isDisplayed();
		logMessage("Invalid Value Displayed Message Verified");
		return bool;
	}

	public boolean clickBinOptions() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("clickBinOptions").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("clickBinOptions").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Bin Options Clicked");
		return bool;
	}

	public boolean clickBinOptionsLeftMost() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("binOptionsLeftMost").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("binOptionsLeftMost").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Bin Options Left Most Clicked");
		return bool;
	}

	public boolean verifyRemoveBin() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("removeBin").isDisplayed();
		logMessage("Verified Bin Remove Option is Clickable");
		return bool;
	}

	public boolean verifyBinProperties() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("binProperties").isDisplayed();
		logMessage("Verified Bin Properties Option is Clickable");
		return bool;
	}

	public boolean clickBinProperties() {

		boolean bool = element("binProperties").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("binProperties").click();
		logMessage("Verified Bin Properties Option is Clickable");
		return bool;
	}

	public boolean clickPrintBinLablesOptions() {

		boolean bool = element("binOptionsLeftMost").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("binOptionsLeftMost").click();
		logMessage("Print Bin Label Option is Clicked");
		return bool;
	}

	public boolean mergeBins() {

		boolean bool = element("binOptionsLeftMost").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("binOptionsLeftMost").click();
		logMessage("Bins Merged SUcessfully");
		return bool;
	}

	public boolean clickNewAssignedLocation() {

		boolean bool = element("newAssignedLocaton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("newAssignedLocaton").click();
		logMessage("Print Bin Label Option is Clicked");
		return bool;
	}

	public boolean clickOnEditButton() {

		boolean bool = element("editLocationButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("editLocationButton").click();
		logMessage("Edit Button is Clicked");
		return bool;
	}

	public boolean clickYesOnLocationChangeSave() {

		boolean bool = element("clickYesOnLocationChangeSave").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("clickYesOnLocationChangeSave").click();
		logMessage("Yes button on clicking save is Clicked");
		return bool;
	}

	public boolean clickShelfOptions() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		element("shelf1").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		boolean bool = element("clickShelfOptions").isEnabled();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		element("clickShelfOptions").click();
		logMessage("Shelf Actions Button Clicked");
		return bool;
	}

	public boolean clickPrintShelfBinLables() {

		boolean bool = element("binOptionsLeftMost").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("binOptionsLeftMost").click();
		logMessage("Print Shelf Label Option is Clicked");
		return bool;
	}

	public boolean clickSlotOptions() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		boolean bool = element("slotOptions").isEnabled();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		hover(element("slotOptions"));
		element("slotOptions").click();
		logMessage("Slot Actions Button Clicked");
		return bool;
	}

	public boolean clickPrintSlotBinLables() {
		boolean elementVisibilty;
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		try {
			elementVisibilty = isElementDisplayed("div_with_text", "Print Slot Label");
			clickUsingXpathInJavaScriptExecutorSingleClick(element("div_with_text", "Print Slot Label"));
		} catch (NoSuchElementException e) {
			logMessage(e.getMessage());
			elementVisibilty = isElementDisplayed("binOptionsLeftMost");
			element("binOptionsLeftMost").click();
			// element("div_with_text", "Print Slot Label").click();
		}
		logMessage("Print Slot Label Option is Clicked");
		return elementVisibilty;
	}

	public boolean clickMergedBinOptions() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		boolean bool = element("binOptionsLeftMost").isEnabled();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		element("binOptionsLeftMost").click();
		logMessage("Merged Bins Action Button Clicked");
		return bool;
	}

	public boolean clickPrintMultipleBinLables() {

		boolean bool = element("binOptionsLeftMost").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("binOptionsLeftMost").click();
		logMessage("Print Merged Bins Label Option is Clicked");
		return bool;
	}

	public boolean verifyTickbarLeftProperties() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("verifyTickbarLeft").isDisplayed();
		logMessage("Verified Tick Bar Left Offset Option is Clickable");
		return bool;
	}

	public boolean verifyMergedBinNumbers() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("mergedBinNumberA").isDisplayed();
		bool = element("mergedBinNumberB").isDisplayed();
		logMessage("Merged Bin Numbers are visible as previous numbers");
		return bool;
	}

	public boolean clickTickbarLeftProperties() {
		boolean bool = element("verifyTickbarLeft").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("verifyTickbarLeft").click();
		logMessage("Verified Tick Bar Left Offset Option is Clickable");
		return bool;
	}

	public void verifyISANameBinProperties(String name) {

		isElementDisplayed("bin_properties");
		logMessage("ASSERTION PASSED: Bin Properties is displayed");
		isElementDisplayed("bin_properties_isa_name");
		element("bin_properties_isa_name").getText().equalsIgnoreCase(name);
		logMessage("ASSERTION PASSED: ISA Name is displayed correctly");

	}

	public boolean verifyRackBinProperties() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("rack_info").isDisplayed();
		logMessage("Verified User Can See Rack Values");
		return bool;
	}

	public boolean verifyShelfBinProperties() {

		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("shelf_info").isDisplayed();
		logMessage("Verified User Can See Shelf Values");
		return bool;
	}

	public boolean verifyAssignPrioritiesIsVisible() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("assignPriorities").isDisplayed();
		logMessage("User Can See Assign Priorities Before Selecting Bin Lables Options in Category");
		return bool;
	}

	public boolean editBinWidthBinProperties(String key) {

		boolean bool = element("binWidthBinProperties").isDisplayed();
		element("binWidthBinProperties").clear();
		element("binWidthBinProperties").sendKeys(key);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Bin Width Updated successfully");
		return bool;
	}

	public void quantityAtHand(String key) {
		element("quantityAtHand").clear();
		element("quantityAtHand").sendKeys(key);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Quantity At hand Updated successfully");
	}

	public boolean clickOnLastmostBinSection() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		// boolean bool = element("leftMostBinSection").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("lastMostBinSection").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Left Most Bin Clicked");
		return true;
	}

	public boolean clickOnLeftmostBinSection() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		// boolean bool = element("leftMostBinSection").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("leftMostBin").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Left Most Bin Clicked");
		return true;
	}

	public boolean clickOnAssignLocationButton() {

		boolean bool = element("assignLocationButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("assignLocationButton").click();
		logMessage("Assign Location Button Clicked");
		return bool;
	}

	public boolean clickAddButtonForLables() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("addStandardLables").isDisplayed();
		clickUsingXpathInJavaScriptExecutor(element("addStandardLables"));
		// element("addStandardLables").click();
		logMessage("Assign Location Button Clicked");
		return bool;
	}

	public boolean clickOnSaveUnassigned() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("unassignedYesButton").isDisplayed();
		element("unassignedYesButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Unassigned Save Button Clicked");
		return bool;
	}

	public boolean clickOnSaveButton() {

		WebElement elem = driver.findElement(By.xpath("//button[contains(@class,'btn btn-primary w-88')]"));

		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		jse2.executeScript("arguments[0].scrollIntoView()", elem);
		boolean bool = element("saveButton").isEnabled();
		jse2.executeScript("arguments[0].click();", elem);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Save Button Clicked");
		return bool;
	}

	public boolean clickOnUnAssignButton() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("unassignButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("unassignButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("UnAssign Button Clicked");
		return bool;
	}

	public boolean verifyBinPropertiesNotAvailable() {
		boolean bool = isElementNotDisplayed("binProperties");
		logMessage("Bin Options is not displayed For Static ISA's");
		return bool;
	}

	public boolean sendStandardLablesName(String key) {
		boolean bool = element("standardLabelName").isEnabled();
		element("standardLabelName").clear();
		element("standardLabelName").sendKeys(key);
		logMessage("Standard Lable Names is Visible and Editable");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		return bool;

	}

	public void selectDropDownFacilitesValue(String value) {
		wait.hardWait(15);
		WebElement element = driver
				.findElement(By.xpath("//select[contains(@name,'facility')]/option[text()='" + value + "']"));
		element.click();
		logMessage("STEP : " + value + " is selected in drop down");
		wait.hardWait(15);
	}

	public void selectDropDownStockValue(String value) {
		wait.hardWait(15);
		WebElement element = driver.findElement(By.xpath("//select/option[text()='" + value + "']"));
		element.click();
		logMessage("STEP : " + value + " is selected in drop down");
		wait.hardWait(15);
	}

	public void selectDropDownCategoriesValue(String value) {
		wait.hardWait(15);
		WebElement element = driver.findElement(By.xpath("//select/option[text()='" + value + "']"));
		element.click();
		logMessage("STEP : " + value + " is selected in drop down");
		wait.hardWait(15);
	}

	public boolean assignPropertiesNotVisible() throws NoSuchElementException

	{
		wait.hardWait(8);
		try {
			boolean bool = driver.findElement(By.xpath("assignPriorities")).isDisplayed();
			return bool;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean verifyPrefferedPrintLablesBoxVisible() throws NoSuchElementException

	{
		wait.hardWait(8);
		try {
			boolean bool = driver.findElement(By.xpath("//label[contains(@class,'form-check-label')]")).isDisplayed();
			return bool;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean clickPrefferedPrintLableBox() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("prefferedPrintLableBox").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("prefferedPrintLableBox").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Preferred Print Lable CheckBox Clicked");
		return bool;
	}

	public boolean verifyPopUpForAlreadySetBinLabel() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = element("noButtonForPrefferedBinLableIfPreexists").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("noButtonForPrefferedBinLableIfPreexists").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("No Button is Clicked for Preferred Print Lable CheckBox Clicked");
		return bool;
	}

	public boolean verifyCheckBoxIsSelected() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		WebElement webElement = driver.findElement(By.xpath("//label[contains(@class,'form-check-label')]"));
		logMessage("Preferred Print Lable CheckBox Is Selected Or Not Verified");
		return webElement.isSelected();
	}

	public boolean ClickYesOnPopUpForSetBinLabel() {

		boolean bool = element("yesButtonForPrefferedBinLablesIfPreExists").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("yesButtonForPrefferedBinLablesIfPreExists").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Yes Button is Clicked for Preferred Print Lable CheckBox Clicked");
		return bool;
	}

	public boolean clickEditStandardLables() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		boolean bool = element("editLablesBin").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("editLablesBin").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Click Edit Lables Button");
		return bool;
	}

	public boolean verifyPreferredPrintLables() {

		boolean bool = element("editPrefferedPrintLable").isDisplayed();
		logMessage("Preferred Pring Lables Checkbox Visible");
		return bool;
	}

	public boolean verifyCategory() {

		boolean bool = element("verifyCategory").isDisplayed();
		logMessage("Preferred Pring Lables Checkbox Visible");
		return bool;
	}

	public boolean clickcancelButtonOnEditLables() {

		boolean bool = element("cancelButtonOnEditLables").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("cancelButtonOnEditLables").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Click Cancel Button On Edit Lables Button");
		return bool;
	}

	public boolean clickYesCancelButtonOnEditLables() {

		boolean bool = element("cancelYesButtonOnEditLables").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("cancelYesButtonOnEditLables").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Click Yes for Cancel Button On Edit Lables Button");
		return bool;
	}

	public boolean checkPrePackFlag() {

		boolean bool = element("prePackCheckBox").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("prePackCheckBox").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Clicked On Select PrePack Flag");
		return bool;
	}

	public boolean clickSetBulkItem() {
		boolean bool = element("setBulkItem").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("setBulkItem").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Clicked On Set Bulk Item");
		return bool;
	}

	public boolean clickSelectBulkItemAndSave() {
		boolean bool = element("selectBulkItem").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("selectBulkItem").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Selected the Required Set Bulk Item");

		bool = element("clickSaveOnSelectBulkItem").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("clickSaveOnSelectBulkItem").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Clicked On Set Bulk Item");
		return bool;
	}

	public boolean uncheckPrePackPopUpNoButton() {
		boolean bool = element("uncheckPrePackPopUpNoButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("uncheckPrePackPopUpNoButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Verify PopUp When Unchecking Pre Pack Pop Up");
		return bool;
	}

	public void sendKeysPrePackConversionFactorTextBox(String key) {
		element("PrePackConversionFactorTextBox").clear();
		element("PrePackConversionFactorTextBox").sendKeys(key);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Value Passed Valid Or Invalid");
	}

	public boolean invalidMessageDisplayed() {
		boolean bool = element("invalidStringMessage").isDisplayed();
		logMessage("Message Displayed");
		return bool;
	}

	public boolean uncheckPrePackPopUpYesButton() {
		boolean bool = element("uncheckPrePackPopUpYesButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("uncheckPrePackPopUpYesButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Verify PopUp When Unchecking Pre Pack Pop Up");
		return bool;
	}

	public boolean saveItemButtonPrePackDiabled() {
		boolean bool = element("saveItemButtonPrePack").isEnabled();
		logMessage("Message Displayed");
		return bool;
	}

	public boolean clickCancelButtonOnItem() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		boolean bool = element("cancelButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("cancelButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Clicked On Cancel Button");
		return bool;
	}
	
	public void ClickremoveButton(String fieldName)
	{
		element("remove_Button",fieldName).click();
	}

	public void searchBulkItemInputBox(String key) {
		element("searchBulkItem").clear();
		element("searchBulkItem").sendKeys(key);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Value Passed Valid Or Invalid");
	}
	
	public void ClearInputField(String fieldName) {
	 element("input_field",fieldName).clear();	 
	 //String eletext = element("max_daily_Quantity").getText();
	 //return eletext;
		
	}
	
	

	public boolean errorPopUp() {
		boolean bool = element("nDCandDistributorPopup").isDisplayed();
		logMessage("Pop Up Displayed");

		return bool;
	}

	public boolean clickokButtonOnPopUp() {
		boolean bool = element("okButton").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("okButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Clicked On Cancel Button");
		return bool;
	}

	public boolean verifyScheduleExist(String scheduleName) {
		try {
			return isElementDisplayed("added_record_div", scheduleName);
		} catch (Exception e) {
			return isElementDisplayed("link_edit_of_added_record", scheduleName);
		}
	}

	public String getTopPriority() {
		List<String> priority = new ArrayList<String>();
		List<WebElement> tabsData = elements("txn_priorities_results");
		for (int i = 0; i < tabsData.size(); i++) {
			priority.add(tabsData.get(i).getText());
		}
		return priority.get(0);
	}

	public void clickLink(String linkName) {
		try {
			isElementDisplayed("link_preferdis", linkName);
			clickUsingXpathInJavaScriptExecutorSingleClick(element("link_preferdis", linkName));
			logMessage("[STEP]: Clicked on link " + linkName);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		} catch (Exception e) {
			element("link_preferdis", linkName).click();
			logMessage("[STEP]: Clicked on link " + linkName);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		}
	}

	public void verifyButtonIsPresentOnWFAScreen(String fieldName) {

		isElementDisplayed("action_btn_isa", fieldName);
		logMessage("[ASSERTION PASSED]: Verified button is present " + fieldName);

	}

	public boolean verifyOptionToScanitem(String className, String LabelText) {
		isElementDisplayed("scan_option", className);
		return element("scan_option", className).getText().contains(LabelText);

	}

	public void clickToAddPreferredDistributor(String linkText) {
		isElementDisplayed("add_preferred_distributor", linkText);
		wait.waitForElementToBeClickable(element("add_preferred_distributor", linkText));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("add_preferred_distributor", linkText));

	}

	public void selectNewPreferredDistributorAtFacilityLevel(String newDistributor) {

		wait.waitForElementToBeClickable(element("new_distributor_radiobutton_at_facilitylevel", newDistributor));
		element("new_distributor_radiobutton_at_facilitylevel", newDistributor).click();
		logMessage("[STEP]:" + newDistributor + " is provided.");

	}

	public void clickToggleOfAddedRecord(String destinationName) {
		isElementDisplayed("toggle_for_added_record", destinationName);
		wait.waitForElementToBeClickable(element("toggle_for_added_record", destinationName));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("toggle_for_added_record", destinationName));
	}

	public void clickCancelButtonOnProdID() {
		isElementDisplayed("cancel_prod");
		clickUsingXpathInJavaScriptExecutor(element("cancel_prod"));
		logMessage("ASSERTION PASSED: Clicked Cancel button on Product ID screen");
	}

	public boolean verifyUpdatedScheduleTimeFieldOnRoutingRuleScreen(String scheduleName, String startTime,
			String endTime) {
		isElementDisplayed("updated_timeschedule_on_routingRuleScreen", scheduleName);
		if (element("updated_timeschedule_on_routingRuleScreen", scheduleName).getText().contains(startTime)
				&& element("updated_timeschedule_on_routingRuleScreen", scheduleName).getText().contains(endTime)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyUpdatedScheduleDayFieldOnRoutingRuleScreen(String scheduleName, String day) {
		isElementDisplayed("updated_dayschedule_on_routingRuleScreen", scheduleName);
		if (element("updated_dayschedule_on_routingRuleScreen", scheduleName).getText().contains(day)) {
			return true;
		} else {
			return false;
		}
	}

	public void verifyFeatureIsPresentUnderCategory(String category, String feature) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		isElementDisplayed("main_menu_features_option", category, feature);

	}

	public void verifyMessageOnComputerPopup(String fieldName, String msg) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		Assert.assertTrue(isElementDisplayed("message_on_computer_popup", msg));
	}

	public ArrayList<String> captureDataForParticularColumnTQ(String coulmn) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		List<WebElement> elements = elements("txt_item_descriptions", coulmn);
		for (int i = 0; i < elements.size(); i++) {
			String data = elements.get(i).getText();
			column_data = new ArrayList<String>();
			// System.out.print("Value of quantity: " + data);
			column_data.add(data);
		}
		return column_data;
	}

	public void selectCheckboxComputers(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		isElementDisplayed("checkbox_computer", fieldName);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("checkbox_computer", fieldName));

	}

	public void verifyMessageOnComputerPopupNotDisplayed(String fieldName, String msg) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		Assert.assertFalse(isElementNotDisplayed("main_menu_features_option", fieldName));

	}
	
	public List<String> getRecordListOfTransactionPriorities() {
		ArrayList<String> txnPriorityList = new ArrayList<String>();
		for (WebElement ele : elements("txn_priorities_results")) {
			txnPriorityList.add(ele.getText().trim());
		}
		Assert.assertTrue(!txnPriorityList.isEmpty());
		return txnPriorityList;
	}
	
	public boolean verifyADCFlags(String flag) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);

		return isElementDisplayed("flags_on_destination", flag);
	}

	public void backToPreviousWindow() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 150);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		System.out.println("Going to the previous window");
		executor.executeScript("window.history.go(-1)");
	}

	public boolean clickResetButtonOnBarcode() {
		boolean bool = element("resetButtonBarcode").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("resetButtonBarcode").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Clicked On Cancel Button");
		return bool;
	}

	public void verifyImportButton() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);

		Assert.assertTrue(isElementDisplayed("import_button"));

	}

	public void clickImportButton() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 70);
		Assert.assertTrue(isElementDisplayed("import_button"));
		element("import_button_file").click();

	}

	public boolean verifyImportWindowPopup() {

		boolean flag = false;
		/*
		 * for(String windowHandle: driver.getWindowHandles())
		 * 
		 * { driver.switchTo().window(windowHandle); String winTitle=driver.getTitle();
		 * 
		 * if(winTitle.equalsIgnoreCase("Open"))
		 * 
		 * {
		 * 
		 * flag = true; }
		 * 
		 * } return flag;
		 * 
		 * }
		 */

		if (isElementDisplayed("import_button")) {
			flag = true;
		}
		return flag;
	}

	public boolean verifyAndClickAddProductIDOnPage() {
		boolean bool = element("addProductIDOnPage").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("addProductIDOnPage").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Clicked On Add Product ID Button");
		return bool;
	}

	public void verifyAndClickFacilityNameOnItemPage(String facilityName) {

		//WebElement element = driver.findElement(
				//By.xpath("//div[@class='d-flex justify-content-between'][contains(.,'" + facilityName + "')]'"));
		element("itemlevel_facility",facilityName ).isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		element("itemlevel_facility",facilityName ).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Clicked On Facility Name Tab Button");		
	}

	public void clickCancelButtonFormularyItems(String ID) {
		isElementDisplayed("cancel_formulary", ID);
		wait.waitForElementToBeClickable(element("cancel_formulary", ID));
		element("cancel_formulary", ID).click();

	}

	public void navigationButtonOnItemManagement(String id) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		isElementDisplayed("action_button", id);
		element("action_button", id).click();
	}

	public void verifyMacAddressFieldIsNotVisibleOnUI(String string) {
		isElementNotDisplayed("macaddress_text");
		logMessage("[Assertion Passed]: Verified Mac Address field is not visible on Add/Edit Computer screen");
	}

	public String verifyToggleButtonIsActiveOrNotPriceTagging(String text) {
		wait.hardWait(5);
		isElementDisplayed("active_toggle_price_tagging", text);
		logMessage("[ASSERTION PASSED]: Verified Toggle button on screen");
		String ele = element("active_toggle_price_tagging", text).getAttribute("value").trim();
		System.out.println("Value of box is  " + ele);
		return ele;

	}

	public void verifyFieldText(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		Assert.assertTrue(
				element("inp_field_printer", fieldName).getAttribute("value").trim().equalsIgnoreCase(data.trim()));
		logMessage("[ASSERTION PASSED]: Verified Add Destination header on clicking add button");

	}

	/*
	 * public boolean verifyColumnNameOnBarlinkPage() {
	 * wait.waitForLoaderToBeInvisible(getLocator("loader"), 100); WebElement
	 * elementItem = driver.findElement(By.xpath(
	 * "(//div[@class='rt-resizable-header-content'][contains(.,'Item')])[1]"));
	 * boolean bool = elementItem.isDisplayed(); WebElement elementItemID =
	 * driver.findElement(By.
	 * xpath("//div[@class='rt-resizable-header-content'][contains(.,'Item ID')]"
	 * )); bool = elementItem.isDisplayed(); WebElement elementAction =
	 * driver.findElement(By.xpath(
	 * "//div[@class='rt-resizable-header-content'][contains(.,'Action')]")); bool =
	 * elementItem.isDisplayed();
	 * wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
	 * logMessage("All Columns on the Barcode Page Verified"); return bool; }
	 * 
	 * 
	 * public boolean clickOnEditButtonItemManagement() {
	 * 
	 * wait.waitForLoaderToBeInvisible(getLocator("loader"), 100); boolean bool =
	 * element("editButtonOnItemManagementSearch").isDisplayed();
	 * wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
	 * element("editButtonOnItemManagementSearch").click();
	 * wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
	 * logMessage("Edit Button is Clicked"); return bool; }
	 * 
	 * >>>>>>> 9fa2dcbd3a71a6fd5bd4b06ab896c201d50658f7
	 */

	public String verifyMaxLengthOfAnInputField(String fieldName, String data, int maxlength) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		wait.loadingWait(getLocator("loader"));
		// sendKeysUsingXpathInJavaScriptExecutor(element("inp_field_printer",
		// fieldName), data);
		enterTextInField(element("inp_field_printer", fieldName), data);
		if (fieldName.equalsIgnoreCase("vendorContactPhoneNumberText")
				|| fieldName.equalsIgnoreCase("vendorContactFaxNumberText")) {
			Assert.assertEquals(element("inp_field_printer", fieldName).getAttribute("value").length(), maxlength);
			System.out.println(element("inp_field_printer", fieldName).getAttribute("value").replaceAll("(", "")
					.replaceAll(")", "").length());

			System.out.println(element("inp_field_printer", fieldName).getAttribute("value").replaceAll("(", "")
					.replaceAll(")", ""));
		}
		
		Assert.assertEquals(element("inp_field_printer", fieldName).getAttribute("value").length(), maxlength);
		return element("inp_field_printer", fieldName).getAttribute("value");
	}

	public String verifyMaxLengthOfAnInputFieldLabelTag(String fieldName, String data, int maxlength) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		enterTextInField(element("inp_field_printer", fieldName), data);
		Assert.assertEquals(element("inp_field_printer", fieldName).getAttribute("value").length(), maxlength);
		return element("inp_field_printer", fieldName).getAttribute("value");

	}

	public boolean verifydropdownsNotMandatory(String fieldName) {
		return isElementNotDisplayed("add_item_dropdowns", fieldName);
	}

	public void clickToggleButtonItemMgt(String action, String toggle) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.hardWait(5);
		isElementDisplayed("action_toggle_button", toggle);
		if (element("action_toggle_button", toggle).getAttribute("value").equals(action)) {
			logMessage("Toggle button is Active");

		} else {
			try {
				element("toggle_itemManagement", toggle).click(); // please do
																	// not
																	// comment
																	// this
																	// line.
			} catch (Exception e) {
				clickUsingXpathInJavaScriptExecutorSingleClick(element("toggle_itemManagement_1", toggle));

			}

		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		wait.hardWait(6);
	}

	public void verifyContainsSearchNew(String colName, String searchedLabelName) {

		Assert.assertTrue(element("text_column_systemLabel").getText().trim().contains(searchedLabelName));

	}

	public void verifyDrodownIsNotDisplayed(String string) {
		isElementNotDisplayed("dest_dropdown", string);
		logMessage("Verified dropdown for " + string + " is not displayed now.");
	}

	public void clickOnEditLinkCorresspondingToFacilityName_Sanity(String printerName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		try {
			scrollDown(element("link_edit", printerName));
			isElementDisplayed("link_edit", printerName);
			if (isBrowser("edge") || isBrowser("Edge")) {
				clickUsingXpathInJavaScriptExecutor(element("link_edit_sanity", printerName));
			} else {
				(element("link_edit_sanity", printerName)).click();
			}
			logMessage("Clicked on Edit link corressponding to facility " + printerName);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
			wait.waitForElementToBeVisible(element("popup_add_facility"));
			Assert.assertTrue(element("popup_add_facility").getText().trim().contains(printerName));
			logMessage("[ASSERTION PASSED]: Verified Edit a Facility pop up");
		} catch (Exception e) {

			if (isBrowser("edge") || isBrowser("Edge")) {
				clickUsingXpathInJavaScriptExecutor(element("link_edit", printerName));
			} else {
				(element("link_edit", printerName)).click();
			}
			logMessage("Clicked on Edit link corressponding to facility " + printerName);
			wait.hardWait(5);
			// wait.waitForElementToBeVisible(element("popup_add_facility"));
			Assert.assertTrue(element("popup_add_facility").getText().trim().contains(printerName));
			logMessage("[ASSERTION PASSED]: Verified Edit a Facility pop up");
		}
	}

	public void verifyMaxLengthOfAnInputFieldDescriptionText(String fieldName, String data, int maxlength) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		wait.loadingWait(getLocator("loader"));
		// sendKeysUsingXpathInJavaScriptExecutor(element("inp_field_printer",
		// fieldName), data);
		enterTextInField(element("textarea_field", fieldName), data);
		if (fieldName.equalsIgnoreCase("vendorContactPhoneNumberText")
				|| fieldName.equalsIgnoreCase("vendorContactFaxNumberText")) {
			Assert.assertEquals(element("textarea_field", fieldName).getAttribute("value").length(), maxlength);
			System.out.println(element("textarea_field", fieldName).getAttribute("value").replaceAll("(", "")
					.replaceAll(")", "").length());

			System.out.println(
					element("textarea_field", fieldName).getAttribute("value").replaceAll("(", "").replaceAll(")", ""));

		}

		Assert.assertEquals(element("textarea_field", fieldName).getAttribute("value").length(), maxlength);

	}

	public void selectAllCheckBox(String fieldName) {
		wait.waitForElementToBeClickable(element("all_checkbox", fieldName));
		wait.elementHighlight(element("all_checkbox", fieldName));
		if (!checkboxIsSelectedUsingJavascript(fieldName)) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("all_checkbox", fieldName));
			logMessage("[STEP]: All checkbox is clicked...!!!");

		}

	}

	public String enterRandomValueInInputFieldForBarCode(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("input_field", fieldName);
		wait.waitForElementToBeClickable(element("input_field", fieldName));
		try {
			//
			sendKeysUsingXpathInJavaScriptExecutor(element("input_field", fieldName), data);
			/*
			 * Action seriesOfAction1 = (Action) action.moveToElement(element("input_field",
			 * fieldName)).sendKeys(data) .build();
			 */
			// seriesOfAction1.perform();

			element("input_field", fieldName).sendKeys(Keys.ENTER);
			// wait.hardWait(10);
			if (element("input_field", fieldName).getAttribute("value").trim().contains(data)) {
				return data;
			}
		} catch (Exception e) {
			try {
				Actions action = new Actions(driver);
				sendKeysUsingXpathInJavaScriptExecutor(element("input_field", fieldName), data);
				Action seriesOfAction1 = (Action) action.moveToElement(element("input_field", fieldName)).sendKeys(data)
						.build();
				seriesOfAction1.perform();
				element("input_field", fieldName).sendKeys(Keys.ENTER);
				wait.hardWait(3);
				if (element("input_field", fieldName).getAttribute("value").trim().contains(data)) {
					return data;
				}
			} catch (Exception j) {
				element("input_field", fieldName).sendKeys(data);
				wait.hardWait(3);
				Assert.assertTrue(element("input_field", fieldName).getAttribute("value").trim().contains(data));
			}
		}
		return data;
	}

	public void enterRandomValueInReplacementCostInput(String id, String data) {
		isElementDisplayed("replacement_cost_facility_level", id);
		sendKeysUsingXpathInJavaScriptExecutor(element("replacement_cost_facility_level", id), data);
		Assert.assertTrue(element("replacement_cost_facility_level", id).getAttribute("value").equals(data));

	}

	public void clickBinActions() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("bin_actions");
		Actions action = new Actions(driver);
		wait.hardWait(10);
		Action seriesOfAction = (Action) action.moveToElement(element("bin_actions")).click().build();
		seriesOfAction.perform();
		logMessage("ASSERTION PASSED: Clicked on Bin Actions");
	}

	public void clickSecondBinActions() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		wait.hardWait(5);
		isElementDisplayed("second_bin");
		Actions action = new Actions(driver);
		wait.hardWait(10);
		Action seriesOfAction = (Action) action.moveToElement(element("second_bin")).click().build();
		seriesOfAction.perform();
		logMessage("ASSERTION PASSED: Clicked on Bin Actions");
	}

	public void clickThirdBinAction() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("third_bin_actions");
		Actions action = new Actions(driver);
		wait.hardWait(10);
		Action seriesOfAction = (Action) action.moveToElement(element("third_bin_actions")).click().build();
		seriesOfAction.perform();
		logMessage("ASSERTION PASSED: Clicked on Bin Actions");

	}

	public void clickLastBinAction() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("last_bin_actions");
		Actions action = new Actions(driver);
		wait.hardWait(10);
		Action seriesOfAction = (Action) action.moveToElement(element("last_bin_actions")).click().build();
		seriesOfAction.perform();
		logMessage("ASSERTION PASSED: Clicked on Last Bin Actions");

	}

	public void verifyAddHorizontalDivider() {
		isElementDisplayed("add_hor_divider");
		logMessage("ASSERTION PASSED: Add Horizontal Dividers is displayed");

	}

	public void verifyAddVerticalDivider() {
		isElementDisplayed("add_vertical_divider");
		logMessage("ASSERTION PASSED: Add Vertical Dividers is displayed");
	}

	public void verifyErrorMessageOnWFA(String message) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		isElementDisplayed("error_on_WFA", message);

	}

	public void clickAddVerticalDivider() {
		isElementDisplayed("add_vertical_divider");
		logMessage("ASSERTION PASSED: Add Vertical Dividers is displayed");
		Actions action = new Actions(driver);
		wait.hardWait(10);
		Action seriesOfAction = (Action) action.moveToElement(element("add_vertical_divider")).click().build();
		seriesOfAction.perform();
		logMessage("ASSERTION PASSED: Clicked on Add Vertical Divider");
	}

	public void clickAddHorizontalDivider() {
		isElementDisplayed("add_hor_divider");
		logMessage("ASSERTION PASSED: Add Horizontal Dividers is displayed");
		Actions action = new Actions(driver);
		wait.hardWait(10);
		Action seriesOfAction = (Action) action.moveToElement(element("add_hor_divider")).click().build();
		seriesOfAction.perform();
		logMessage("ASSERTION PASSED: Clicked on Add Horizontal Divider");
	}

	public void verifyErrorMessageForAddDivider(String message) {
		isElementDisplayed("error_msg_divider");
		Assert.assertEquals(element("error_msg_divider").getText(), message);
		logMessage("ASSERTION PASSED: Verified Error message");

	}
	
	
	public void verifySuccessMessageOnAddDivider(String message) {
		wait.applyFluentWait(getLocator("popup_message"), 140, 1000);
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		// isElementDisplayed("popup_message");
		String popupMessage = element("popup_message").getText().trim();
		Assert.assertTrue(popupMessage.contains(message), "[ASSERTION FAILED]: Actual message: " + popupMessage 
				+ "\nExpected message: " + message);
	}
	
	
	public void verifyTextOnFacilityDropdown() {
		isElementDisplayed("text_FacilityDropdown");

	}

	public void verifyTextOnPackageShareFacilityDropdown() {
		isElementDisplayed("text_packageShareFacilityDropdown");

	}

	public boolean verifyADMRoundingQuantityFlag(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		return isElementNotDisplayed("adm_rounding_flag", fieldName);
	}

	public void verifyGroupAction(String element) {
		isElementDisplayed("group_action", element);
		logMessage("ASSERTION PASSED: Action is displayed");

	}

	public void clickBinOption(String element) {
		isElementDisplayed("group_action", element);
		logMessage("ASSERTION PASSED: Action" + element + " is displayed");
		Actions action = new Actions(driver);
		wait.hardWait(10);
		Action seriesOfAction = (Action) action.moveToElement(element("group_action", element)).click().build();
		seriesOfAction.perform();
		logMessage("ASSERTION PASSED: Clicked on " + element);
	}

	public boolean verifyBinNumber() {
		List<WebElement> elements = elements("bin_number");
		for (int i = 0; i <= elements.size(); i++) {
			System.out.println("Bin number is :" + elements.get(i).getText());
			if (!(elements.get(i).getText().trim().equals(i)))
				return false;
		}
		return true;
	}

	public void clickOtherLocation() {
		isElementDisplayed("other_location");
		logMessage("ASSERTION PASSED: Another location found");
		clickUsingXpathInJavaScriptExecutor(element("other_location"));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("other_location"));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		element("other_location").click();
		logMessage("Clicked other location");
	}

	public void verifyQuantity(String field, String value) {
		isElementDisplayed("quantity", field);
		element("quantity", field).getText().equalsIgnoreCase(value);
		logMessage("ASSERTION PASSED: Values are retained");

	}

	public void enterValueInTestTicbar() {
		isElementDisplayed("test_ticbar_input");
		logMessage("ASSERTION PASSED: Test Ticbar is displayed");
		enterTextInField(element("test_ticbar_input"), "10");
		logMessage("Entered value in Test Ticbar field");

	}

	public void clickApplyChangesButton() {
		isElementDisplayed("save_button_edit_location");
		clickUsingXpathInJavaScriptExecutor(element("save_button_edit_location"));
		logMessage("ASSERTION PASSED: Clicked Apply changes button");
	}

	public void verifyBinPropertiesNotAvailable(String element) {
		isElementNotDisplayed("group_action", element);
		logMessage("ASSERTION PASSED: Bin Properties not displayed for Static ISA");
	}

	public String verifyBarcodeDetails(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		isElementDisplayed("barcode_details", fieldName);
		return element("barcode_details", fieldName).getText();
	}

	public ArrayList<String> captureDataForParticularColumnForBarcode(String coulmn) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		List<WebElement> elements = elements("text_column_barcode", coulmn);
		for (int i = 0; i < elements.size(); i++) {
			String data = elements.get(i).getText();
			column_data = new ArrayList<String>();
			// System.out.print("Value of quantity: " + data);
			column_data.add(data);
		}
		return column_data;
	}

	public ArrayList<String> sortDataForParticularColumnInAscendingOrderForBarcode(String coulmn) {
		ArrayList<String> data_compare = captureDataForParticularColumnForBarcode(coulmn);
		Collections.sort(data_compare, String.CASE_INSENSITIVE_ORDER);
		return data_compare;

	}

	public ArrayList<String> sortDataForParticularColumnInDescendingOrderForBarcode(String coulmn) {
		ArrayList<String> data_compare = captureDataForParticularColumnForBarcode(coulmn);
		Collections.sort(data_compare, String.CASE_INSENSITIVE_ORDER);
		Collections.sort(data_compare, Collections.reverseOrder());
		return data_compare;

	}

	public void verifySortIconIsUnavailable(String coulmn) {
		Assert.assertFalse(isElementNotDisplayed("sort_icon", coulmn));

	}

	public void verifySearchBox(String field) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 50);
		Assert.assertTrue(isElementDisplayed("search_box", field));
	}

	public void verifybuttonOnBarcodeScreen(String action) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 50);
		Assert.assertTrue(element("barcode_button", action).isDisplayed());
		logMessage("Unlinking Button is Visible");
	}

	public void verifyConfirmationMessageOnBarcodeRelinking(String Message1, String Message2) {

		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 50);
		isElementDisplayed("barcode_rinlinking_msg");
		Assert.assertTrue(elements("barcode_rinlinking_msg").get(0).getText().equals(Message1));
		Assert.assertTrue(elements("barcode_rinlinking_msg").get(0).getText().equals(Message2));

	}

	public void clickShelf1() {
		isElementDisplayed("click_shelf1");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_shelf1"));
	}

	public void verifyPrintLabelOnShelf() {
		isElementDisplayed("shelf_print");
		logMessage("ASSERTION PASSED: Verified Print Label option");
		clickUsingXpathInJavaScriptExecutor(element("shelf_print"));
		logMessage("ASSERTION PASSED: Clicked Print bin options");
	}

	public void clickShelfDots() {

		isElementDisplayed("shelf_dots");
		logMessage("VErified dots");
		Actions action = new Actions(driver);
		wait.hardWait(10);
		Action seriesOfAction = (Action) action.moveToElement(element("shelf_dots")).click().build();
		seriesOfAction.perform();

	}

	public void clickRemoveBin() {
		isElementDisplayed("remove_bin");
		logMessage("ASSERTION PASSED: Remove Bin is displayed");
		Actions action = new Actions(driver);
		wait.hardWait(10);
		Action seriesOfAction = (Action) action.moveToElement(element("remove_bin")).click().build();
		seriesOfAction.perform();
		logMessage("ASSERTION PASSED:Remove Bin is clicked");
	}

	public void verifyRemoveBinNotDisplayed() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		boolean bool = isElementNotDisplayed("removeBin");
		logMessage("Verified Bin Remove Option is Not displayed");

	}

	/////// Added by BD Team/////////////
	// If multiple items are there in order, we need to add limit value to
	/////// multiple
	// items
	public void enterLimitOrderQtyValue(String data, int index) {
		isElementDisplayed("max_order_qty_value", String.valueOf(index));
		enterTextInField(element("max_order_qty_value", String.valueOf(index)), data);
		logMessage("[ASSERTION PASSED]: Max order Quantity field is editable after selecting checkbox");
	}

	public boolean verifySearchResults_dispenseunit(String searchedData, String colNumber) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		List<WebElement> elements = elements("results_dispense", colNumber);
		wait.hardWait(7);

		try {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().startsWith(searchedData)))

				{
					return false;
				} else {
					continue;
				}
			}

		} catch (Exception e) {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().startsWith(searchedData)))

				{
					return false;
				} else {
					continue;
				}
			}

		}

		return true;
	}

	public void clickOnHelpIcon() {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		Assert.assertTrue(isElementDisplayed("online_help"));
		element("online_help").click();
		Assert.assertTrue(isElementDisplayed("online_help_item"));
		element("online_help_item").click();

	}

	public void verifyOnlineHelpPageIsOpened() {

		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		Assert.assertTrue(isElementDisplayed("online_help_header"));

	}

	public void verifyOnlineHelpPageIcon() {

		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		Assert.assertTrue(isElementDisplayed("online_help"));

	}

	public void clickOnNameLink(String computerName) {
		try {
			wait.hardWait(8);
			isElementDisplayed("link_edit_of_added_record", computerName);
			hover(element("link_edit_of_added_record", computerName));
			wait.waitForElementToBeClickable(element("link_edit_of_added_record", computerName));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("link_edit_of_added_record", computerName));

			logMessage("Clicked on" + computerName + "button to add hold reason");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);

		} catch (Exception e) {
			Actions act = new Actions(driver);
			act.moveToElement(element("link_edit_of_added_record", computerName)).click().build().perform();

			logMessage("Clicked on" + computerName + "button to add hold reason");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);

		}
	}

	public ArrayList<String> captureDataForNameColumn(String column) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		List<WebElement> elements = elements("name_column", column);
		for (int i = 0; i < elements.size(); i++) {
			String data = elements.get(i).getText();
			column_data = new ArrayList<String>();
			// System.out.print("Value of quantity: " + data);
			column_data.add(data);
		}
		return column_data;
	}

	public ArrayList<String> sortDataForNameColumnInAscendingOrder(String coulmn) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		List<WebElement> elements = elements("name_column", coulmn);
		for (int i = 0; i < elements.size(); i++) {
			String data = elements.get(i).getText();
			column_data = new ArrayList<String>();
			// System.out.print("Value of quantity: " + data);
			column_data.add(data);
		}
		return column_data;
	}

	public boolean verifyAddDispenseUnitPopupGetsClosedOnClickingCancelButton() {
		isElementDisplayed("btn_cancel");
		element("btn_cancel").click();
		logMessage("Clicked on cancel button on Add Dispense Unit pop up");
		return isElementNotDisplayed("popup_add_printer");
	}

	public int verifyMaxDailyQuantity() {		
		return Integer.parseInt(element("maxlength_daily_Quantity").getAttribute("maxlength").trim());
	}

	public String getItemName(String value) {
		isElementDisplayed("get_item_name",value);
		logMessage("Item "+value+ " is displayed");
		return element("get_item_name",value).getText();			
		
	}

	public String getMaxdailyQuantity(String itemName) {
		isElementDisplayed("get_Max_Daily_quantity",itemName);
		return element("get_Max_Daily_quantity",itemName).getText().toString();
    		
	}

	public void editISAName(String value) {    
	isElementDisplayed("edit_Isa_Name");	
	clickUsingXpathInJavaScriptExecutor(element("edit_Isa_Name", value));
	element("edit_Isa_Name", value).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));	 
	element("edit_Isa_Name").sendKeys(value);
	logMessage("ASSERTION PASSED: ISA Name is updated");
    }

	

}
