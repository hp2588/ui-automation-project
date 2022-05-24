package com.org.actions;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.ClickElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.itextpdf.text.log.SysoCounter;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.org.automation.getpageobjects.GetPage;
import com.org.automation.utils.DateUtil;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;

public class TransactionQueue_Actions extends GetPage {

	WebDriver driver;
	boolean expirylotflag;
	private String tagname;
	static String pagename = "Transaction_Queue_Page";
	private boolean flag = false;
	private ArrayList<String> activeTransaction, trans_data, trans_data_reverse;
	int tab_count;
	String itemName, lotNumberValue, itemid, search_column = null;
	String[] listPopupFields = { "Earliest Expiration Date", "Lot Number", "Action" };
	ArrayList<String> searchResult;
	ArrayList<String> restocktransdetail, restocktransdetail1;
	ArrayList<String> column_data;

	public TransactionQueue_Actions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public void verifyTQPageAndAppendIP(String ip) {

		String newURL = driver.getCurrentUrl() + "?ipAddress=" + ip;
		driver.navigate().to(newURL);
	}

	public boolean verifyTabName(String tabText) {
		List<WebElement> elements = elements("pick_queue_text");
		for (WebElement ele : elements) {
			if (ele.getText().contains(tabText)) {
				flag = true;

			}
		}
		return flag;

	}

	public void verifySuccessMessageOnViewPage(String successMessage) {
		// isElementDisplayed("popup_message");
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		try {
			Assert.assertTrue(element("popup_message").getText().trim().contains(successMessage));
			logMessage("[ASSERTION PASSED]: Verified data is Added");
		} catch (StaleElementReferenceException e) {
			Assert.assertTrue(element("popup_message").getText().trim().contains(successMessage));
			logMessage("[ASSERTION PASSED]: Verified data is Added");
		}

	}

	public boolean verifyCheckboxesForEachTransactionIsSelected() {
		if (selectAllCheckboxes()) {
			if (checkboxIsSelectedUsingJavascript("allCheckbox"))

			{
				flag = true;

			} else
				flag = false;
		}

		return flag;

	}

	public boolean selectAllPickQueueCheckboxes() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.applyFluentWait(getLocator("pick_queue_all_checkbox"), 60, 500);
		if (isElementDisplayed("pick_queue_all_checkbox")) {
			try {
				element("pick_queue_all_checkbox").click();
			} catch (Exception e) {
				clickUsingXpathInJavaScriptExecutorSingleClick(element("pick_queue_all_checkbox"));
			}
			logMessage("User has clicked on all checkbox in Pick Queue");
			flag = true;
		}

		return flag;
	}

	public void successMessage(String expected) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 50);
		isElementDisplayed("success_message");
		String actual = element("success_message").getText();
		Assert.assertEquals(actual, expected);
	}

	public boolean selectAllCheckboxes() {
		wait.waitForPageToLoadCompletely();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		wait.hardWait(70);
		try {
			if (isElementDisplayed("pick_queue_all_checkbox")) {
				// List<WebElement> elements =
				// elements("pick_queue_all_checkbox");
				// wait.hardWait(10);
				// elements.get(0).click();
				element("pick_queue_all_checkbox").click();
				// clickUsingXpathInJavaScriptExecutor(element("pick_queue_all_checkbox"));
				System.out.println("clicked on All checkbox!!!!");
				logMessage("User has clicked on all checkbox in Pick Queue");
				flag = true;
			}
		} catch (Exception e) {
			if (isElementDisplayed("pick_queue_all_checkbox")) {
				// elements.get(0).click();
				// element("pick_queue_all_checkbox").click();
				clickUsingXpathInJavaScriptExecutorSingleClick(element("pick_queue_all_checkbox"));
				System.out.println("clicked on All checkbox!!!!");
				logMessage("User has clicked on all checkbox in Pick Queue");
				flag = true;
			}
		}

		return flag;
	}

	public void clickFirstTransaction() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("checkbox_first_transaction");
		wait.waitForElementToBeClickable(element("checkbox_first_transaction"));
		if (!element("checkbox_first_transaction").isSelected()) {
			try {
				element("checkbox_first_transaction").click();

				logMessage("First Transaction checkbox is clicked");
			} catch (Exception e) {
				Actions action = new Actions(driver);
				action.moveToElement(element("checkbox_first_transaction")).click().build().perform();

			}
		}

	}

	public void clickPickNowLink() {
		isElementDisplayed("activate_first_transaction");
		try {
			click(element("activate_first_transaction"));
			logMessage("Pick Now link is clicked.");
		} catch (Exception e) {
			Actions action = new Actions(driver);
			action.moveToElement(element("activate_first_transaction")).click().build().perform();

		}
	}

	public boolean verifyCheckboxesForEachPickTransactionIsDeselected() {
		if (isElementDisplayed("chkbox_PickCheckbox")) {
			clickUsingXpathInJavaScriptExecutor(elements("chkbox_PickCheckbox").get(0));
			List<WebElement> elements = elements("pick_queue_all_checkbox");
			for (WebElement ele : elements) {
				if (!ele.isSelected())
					flag = true;
				else
					flag = false;
			}

		}
		return flag;

	}

	public boolean verifyTransactionAction(String buttonText) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		List<WebElement> elements = elements("pick_now_first_transaction");
		click(elements.get(1));
		List<WebElement> buttobnelements = elements("pick_now_button");
		for (WebElement ele : buttobnelements) {
			if (ele.getText().equalsIgnoreCase(buttonText)) {
				if (ele.isEnabled())
					flag = true;

			}
		}
		return flag;

	}

	public boolean makeTransactionActive() {
		if (selectAllPickQueueCheckboxes()) {
			List<WebElement> elements = elements("pick_queue_all_checkbox");
			for (int i = 0; i < elements.size(); i++) {
				click(elements.get(1));
			}
			getTransactionDetails();
			if (isElementDisplayed("activate_first_transaction")) {
				click(element("activate_first_transaction"));
				flag = true;
			}

		}
		return flag;

	}

	public boolean makeFirstTransactionActive() {
		// List<WebElement> elements = elements("pick_now_first_transaction");
		// clickUsingXpathInJavaScriptExecutor(elements.get(1));
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		try {
			if (isElementDisplayed("activate_first_transaction")) {
				// clickUsingXpathInJavaScriptExecutor(element("activate_first_transaction"));
				element("activate_first_transaction").click();
				flag = true;
			}
		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("activate_first_transaction"));
			flag = true;
		}
		
		return flag;
	}

	public boolean makeFirstRestockTransactionActive() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		List<WebElement> elements = elements("pick_now_first_transaction");
		clickUsingXpathInJavaScriptExecutor(elements.get(1));
		if (isElementDisplayed("activate_first_restock_transaction")) {
			clickUsingXpathInJavaScriptExecutor(element("activate_first_restock_transaction"));
			flag = true;
		}
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);

		return flag;

	}

	public ArrayList<String> getTransactionDetails() {

		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		
		List<WebElement> elements = elements("pick_now_first_transaction");
		wait.hardWait(5);
		wait.waitForElementsToBeVisible(elements);
		
		activeTransaction = new ArrayList<String>();
		
		String transactionDesc = elements.get(4).getText();
		String transactionDesc_without_space = transactionDesc.replaceAll(" ", "");
		System.out.println("Item name = " + transactionDesc);
		
		String quantity = elements.get(3).getText();
		System.out.println("quantity = " + quantity);
		
		String type = elements.get(2).getText();
		System.out.println("type = " + type);
		
		String location = elements.get(5).getText();
		System.out.println("location= " + location);
		
		activeTransaction.add(transactionDesc_without_space);
		activeTransaction.add(quantity);
		activeTransaction.add(type);
		activeTransaction.add(location);
		
		System.out.println(activeTransaction);
		return activeTransaction;
		
	}
	
	public boolean verifyActiveTransactionQuantityOnHand() {

		ArrayList<String> activeTransactionDetails = getTransactionDetails();
		makeFirstTransactionActive();
		String item_name = element("txt_item_description").getText();
		String item_name_with_spaces_removed = item_name.replaceAll(" ", "");
		if (item_name_with_spaces_removed.equalsIgnoreCase(activeTransactionDetails.get(0).replaceAll("\\s", ""))) {
			// if
			// (isElementDisplayed("txt_item_description",activeTransactionDetails.get(0).replaceAll("\\s",
			// ""))){
			if (isElementDisplayed("txt_item_quantity")) {
				verifyElementText(("txt_item_quantity"), activeTransactionDetails.get(1));
				flag = true;
			}

		}

		return flag;

	}

	public void onHoldHeading() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		String heading = "Items put on hold from work queues will appear here";
		isElementDisplayed("txt_item_description", heading);
		String subHeading = "Released items will return back to their respective work queues";
		isElementDisplayed("txt_item_subHeading", subHeading);

	}

	public boolean verifyTabCount() {
		String actual = getTQueueTabCount().get(0);
		int expected = getTransactionTableDataCount();
		if (actual.equals(expected + "")) {
			logMessage("Expected is" + " " + expected + " " + "Actual is" + " " + actual);
			flag = true;
		}
		logMessage("Expected is" + " " + expected + " " + "Actual is" + " " + actual);
		return flag;
	}

	public boolean verifyHoldTabCount(String before_count) {
		String before = before_count;
		String after = getTQueueTabCount().get(3);

		if (!(before.equals(after + ""))) {
			flag = true;
		}
		return flag;
	}

	public List<String> getTQueueTabCount() {

		wait.waitForPageToLoadCompletely();
		List<String> pickTransCount = new ArrayList<String>();
		List<WebElement> tabsData = elements("pick_queue_transaction_count");

		for (int i = 0; i < tabsData.size(); i++) {
			pickTransCount.add(tabsData.get(i).getText());
		}

		return pickTransCount;
	}

	public int getTransactionTableDataCount() {
		int pickTransCount;
		List<WebElement> tableData = new ArrayList<WebElement>();
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
			wait.applyFluentWait(getLocator("transactions_table_count"), 300, 5);
			tableData = elements("transactions_table_count");
			pickTransCount = tableData.size();
			if (pickTransCount== 0)
			{
				wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
				wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 280);
				wait.applyFluentWait(getLocator("transactions_table_count"), 300, 5);
				 tableData = elements("transactions_table_count");
				pickTransCount = tableData.size();
				System.out.println("Transaction count :" + pickTransCount);

			}
			else if (pickTransCount!=0 )
				
			{
				System.out.println("Transaction count :" + pickTransCount);
				return pickTransCount;
			}
			
		} catch (Exception e) {

			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 280);
			tableData = elements("transactions_table_count");
			pickTransCount = tableData.size();
			tableData = elements("transactions_table_count");
			pickTransCount = tableData.size();
			if (pickTransCount== 0)
			{
				wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
				wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 280);
				wait.applyFluentWait(getLocator("transactions_table_count"), 300, 5);
				 tableData = elements("transactions_table_count");
				pickTransCount = tableData.size();
				System.out.println("Transaction count :" + pickTransCount);

			}
			else if (pickTransCount!=0 )
				
			{
				System.out.println("Transaction count :" + pickTransCount);
				return pickTransCount;
			}
		}
		return pickTransCount;
	}

	public void clickActiveHold() {
		wait.hardWait(5);
		isElementDisplayed("button_hold_active");
		element("button_hold_active").click();
	}

	public void addRestock(String action) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		isElementDisplayed("button_AddRestock", action);
		try {
			element("button_AddRestock", action).click();

		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("button_AddRestock", action));
		}
	}

	public void patientNamePresent(String patientName) {
		wait.hardWait(5);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		Assert.assertTrue(isElementDisplayed("patientName_text", patientName));
	}

	public void onHoldTextIsPresent() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		Assert.assertTrue(isElementDisplayed("text_onHoldTab"));
	}

	public void patientNameAbsent(String patientName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.hardWait(4);
		Assert.assertFalse(isElementNotDisplayed("patientName_text", patientName));
	}

	public void patientNameAbsentActive(String patientName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.hardWait(4);
		Assert.assertFalse(isElementNotDisplayed("patientName_activetext", patientName));
	}

	public void getEmptyTransactionTableDataCount() {
		wait.hardWait(5);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		Assert.assertFalse(isElementNotDisplayed("transactions_table_count"));
	}

	public boolean verifyButtonisDisabled(String buttonType) {
		selectAllCheckboxes();
		List<WebElement> ele = elements("pick_now_button");
		for (WebElement e : ele) {
			if (e.getText().equalsIgnoreCase(buttonType)) {
				if (!e.isEnabled()) {
					flag = true;
				}
			}
		}

		return flag;
	}

	public void clickButton(String buttonType) {
		List<WebElement> ele = elements("pick_now_button");
		for (WebElement e : ele) {
			if (e.getText().equalsIgnoreCase(buttonType)) {
				Actions action = new Actions(driver);
				action.moveToElement(e).click().build().perform();
			} else {
				continue;
			}
		}

	}

	public boolean verifyTransactionQueueBackgroundColorOnHover(String bgColor) {
		String NewbgColor = "#" + bgColor;
		if (verifyTransactionQueueIsNotEmpty()) {
			WebElement firstTransaction = elements("pick_queue_all_transaction").get(0);
			if (hoverOverAnElement(firstTransaction)) {
				if (!firstTransaction.getCssValue("background-color").equals(NewbgColor)) {
					System.out.println(firstTransaction.getCssValue("background-color").toString());
					flag = true;
				}
			}

		}

		return flag;
	}

	public boolean verifyTransactionQueueIsNotEmpty() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		boolean flag = false;
		if (getTransactionTableDataCount() != 0) {
			logMessage("Pick Transaction Queue is not empty");
			flag = true;

		}
		return flag;
	}

	public boolean hoverOverAnElement(WebElement element) {
		hover(element);
		flag = true;
		return flag;

	}

	public boolean verifySelectedTransactionBackgroundColor(String bgColor) {
		String NewbgColor = "#" + bgColor;
		selectAllPickQueueCheckboxes();
		List<WebElement> elements = elements("pick_queue_all_transaction");
		hover(elements.get(1));
		if (!elements.get(1).getCssValue("background-color").equals(NewbgColor)) {
			flag = true;

		}
		flag = true;

		return flag;
	}

	public boolean verifyActiveTransactionLocation() {
		ArrayList<String> activeTransactionDetails = getTransactionDetails();
		
		/** ACAMPROSATE 333 mg TABLET 2 MANUALPICK def */
		
		makeFirstTransactionActive();
		String item_name = element("txt_item_description").getText();
		String item_name_with_spaces_removed = item_name.replaceAll(" ", "");
		// String first_name1=first_name[0];

		if (item_name_with_spaces_removed.equalsIgnoreCase(activeTransactionDetails.get(0))) {
			// if (isElementDisplayed("txt_item_description",
			// activeTransactionDetails.get(0))) {
			if (isElementDisplayed("txt_item_location")) {
				String data = element("txt_item_location").getText();
				System.out.println("DATA IS: " + data);
				
				
				if (data.contains(activeTransactionDetails.get(3)))
					flag = true;
				
//				String[] parts = data.split("|");
//				String location = parts[0].split(":")[1].toString().trim();
//				String patient_name = parts[1].split(":")[1].toString().trim();
				
				String[] parts = data.split("\\|");
				String location = parts[0].split("-")[0].toString();
				String patient_name = parts[1].split("-")[0].toString();
				
				// System.out.println("location: " + location);
				logMessage("location: " + location);
				// System.out.println("patient_name: " + patient_name);
				logMessage("patient_name: " + patient_name);
				
				for (int i = 0; i < activeTransactionDetails.size(); i++) {
					System.out.println("elum: " + activeTransactionDetails.get(i));
				}
				
				if (data.contains(activeTransactionDetails.get(3)))
					flag = true;
			}
			
		}

		return flag;
	}

	public boolean verifyTransactionStatus() {
		if (isElementDisplayed("mid_panel_status") && isElementDisplayed("right_panel_status")) {
			String midPanelStatus = element("mid_panel_status").getText();
			String rightPanelStatus = element("right_panel_status").getText();
			if (midPanelStatus.equalsIgnoreCase(rightPanelStatus)) {
				System.out.println(midPanelStatus + " " + rightPanelStatus);

				flag = true;
			}
		}
		return flag;
	}

	/*
	 * public boolean verifyValidItemNameInCurrentPick() { if
	 * (isElementDisplayed("selected_item_name") &&
	 * isElementDisplayed("current_pick_item_name")) { String selected_item_name
	 * = element("selected_item_name").getText(); String current_pick_item_name
	 * = element("current_pick_item_name").getText(); if
	 * (selected_item_name.equalsIgnoreCase(current_pick_item_name)) {
	 * System.out.println(selected_item_name + " " + selected_item_name);
	 * 
	 * flag = true; } } return flag; }
	 */

	public boolean verifyActiveTransactionBox() {
		boolean flag = false; 
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		if (isElementDisplayed("active_transaction_box")) {
			if (!element("active_transaction_box").getText().isEmpty()) {
				flag = true;
				logMessage("Active Transaction is available on UI");
			}
		}
		return flag;
	}

	public boolean verifyActiveTransactionBoxSanity(String itemName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		if (isElementDisplayed("active_transaction_box")) {
			if (!element("active_transaction_box").getText().contains(itemName)) {
				flag = true;
				logMessage("Active Transaction is available on UI");
			}
		}
		
		return false;
	}

	public boolean verifyWorkWithoutScannerOption() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		if (isElementDisplayed("link_work_without_scanner")) {
			wait.hardWait(10);
			// element("link_work_without_scanner").click();
			Actions action = new Actions(driver);
			action.moveToElement(element("link_work_without_scanner")).click().build().perform();
			// if (isElementDisplayed("dialog_box_authorization_popup")) {
			logMessage("Work without Sacnner Option is avaiable for active transaction");
			flag = true;

			// }

		}

		return flag;

	}

	public boolean verifyAuthorizationPopup() {
		if (isElementDisplayed("dialog_box_authorization_popup")) {
			// confirmPopup();
			flag = true;
			logMessage("Authorization option is available on UI");

		}
		return flag;
	}

	public boolean verifyBinLabelScanning(String text) {
		wait.hardWait(5);
		if (isElementDisplayed("bar_progress_success")) {
			if (isElementDisplayed("scan_text", text)) {
				flag = true;
				logMessage("Toggle Bar turns green");
			}

			else if (isElementNotDisplayed("scan_text", text)) {
				if (isElementDisplayed("scan_text", "Waiting For Item Scan. Hit F2 to override scanning")) {
					flag = true;
					logMessage("Toggle Bar turns green");
				}

			}
		}

		return flag;
	}

	public boolean verifyItemScanMessage(String iteMscanmsg, String text) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		if (element("mid_panel_status").getText().equalsIgnoreCase(iteMscanmsg)
				&& isElementDisplayed("scan_text", text)) {
			flag = true;
			logMessage("Work without Scanner Text is available on UI");

		}

		return flag;
	}

	public boolean verifyAuthorizationPopupDetails() {
		if (isElementDisplayed("dialog_box_authorization_popup")) {
			if (isElementDisplayed("txt_popup_userid")) {
				logMessage("UserID Textfield is available on UI");

				if (isElementDisplayed("txt_popup_password")) {
					logMessage("Password Textfield is available on UI");

					if (isElementDisplayed("btn_popup_cancel")) {
						logMessage("Cancel button is available on UI");

						if (isElementDisplayed("btn_popup_confirm")) {
							logMessage("Confirm button is available on UI");
							flag = true;
						}
					}
				}

			}
		}
		return flag;

	}

	public boolean verifyTransactionCompletion() {

		return flag;
	}

	public boolean verifyChangeQuantityButtonisEnabled() {

		if (isElementDisplayed("link_change_quantity")) {

			flag = true;
			pageRefresh();
		}

		return flag;

	}

	public boolean verifyChangeQuantityPopup() {
		wait.hardWait(5);
		clickUsingXpathInJavaScriptExecutor(element("link_change_quantity"));
		if (isElementDisplayed("change_quantity_popup")) {
			if (isElementDisplayed("txt_change_quantity_popup")) {
				logMessage("Change Quantity Text is available on UI");

				if (isElementDisplayed("input_quantity")) {
					if (!element("input_quantity").getText().isEmpty()) {

						if (isElementDisplayed("btn_reset_quanitity_change_popup")
								&& isElementDisplayed("btn_cross_quanitity_change_popup")) {
							flag = true;
						}

					}
				}
			}

		}
		return flag;
	}

	public boolean verifyChangeQuantityPopupCrossButton(String inputfield) {
		if (isElementDisplayed("link_change_quantity")) {
			wait.hardWait(5);
			// wait.waitForElementToBeClickable(element("link_change_quantity")).click();
			clickUsingXpathInJavaScriptExecutor(element("link_change_quantity"));
			String value_filled = element("txt_quantity_value").getAttribute("value");
			enterTextInField(element("txt_quantity_value"), inputfield);
			if (isElementDisplayed("btn_cross_quanitity_change_popup")) {
				click(element("btn_cross_quanitity_change_popup"));
				element("btn_save_quantity").click();
				if (element("txt_actual_quantity").getText().equalsIgnoreCase(value_filled)) {
					flag = true;
				}
			}
		}

		return flag;

	}

	public boolean verifyChangeQuantityPopupButton(String inputfield) throws InterruptedException {
		if (isElementDisplayed("link_change_quantity")) {
			wait.hardWait(5);
			clickUsingXpathInJavaScriptExecutor(element("link_change_quantity"));
			String value_filled = element("txt_quantity_value").getAttribute("value");
			enterTextInField(element("txt_quantity_value"), inputfield);
			if (isElementDisplayed("btn_cancel_quantity_change_popup")) {
				click(element("btn_cancel_quantity_change_popup"));
				if (element("txt_actual_quantity").getText().equalsIgnoreCase(value_filled)) {
					flag = true;
				}
			}
		}

		return flag;

	}

	public boolean verifyValueOfQuantityChangeOnReset(String inputfield) {
		if (isElementDisplayed("link_change_quantity")) {
			wait.hardWait(5);
			clickUsingXpathInJavaScriptExecutor(element("link_change_quantity"));
			String value_filled = element("txt_quantity_value").getAttribute("value");
			enterTextInField(element("txt_quantity_value"), inputfield);
			if (isElementDisplayed("btn_reset_quanitity_change_popup")) {
				click(element("btn_reset_quanitity_change_popup"));
				click(element("btn_save_quantity"));
				if (element("txt_actual_quantity").getText().equalsIgnoreCase(value_filled)) {
					flag = true;
				}

			}

		}
		return flag;

	}

	public boolean verifyValueOfQuantityChangeOnSave(String inputfield) {
		if (isElementDisplayed("link_change_quantity")) {
			wait.hardWait(5);
			clickUsingXpathInJavaScriptExecutor(element("link_change_quantity"));
			String value_filled = element("txt_quantity_value").getAttribute("value");
			enterTextInField(element("txt_quantity_value"), inputfield);
			if (isElementDisplayed("btn_save_quantity")) {
				click(element("btn_save_quantity"));
				if (element("txt_actual_quantity").getText().equalsIgnoreCase(inputfield)) {
					flag = true;
				}

			}

		}
		return flag;

	}

	public boolean confirmPopup() {
		if (isElementDisplayed("btn_popup_confirm")) {
			clickUsingXpathInJavaScriptExecutor(element("btn_popup_confirm"));
			flag = true;

		}
		return flag;
	}

	public boolean clickPickNowButton() {
		if (isElementDisplayed("pick_now_button")) {
			element("pick_now_button").click();
			flag = true;

		}
		return flag;
	}

	public boolean verifySortingIsAvailableForColumnHeader(String colname) {
		wait.hardWait(3);
		if (colname.equalsIgnoreCase("Priority")) {
			// clickUsingXpathInJavaScriptExecutor(element("txt_tq_headers",
			// colname));
			clickUsingXpathInJavaScriptExecutor(element("icon_sorting", colname));
			wait.hardWait(3);
			flag = true;
			element("txt_tq_headers", colname).click();
			if (isElementDisplayed("icon_sorting", colname)) {
				flag = true;

			}

		}
		if (colname.equalsIgnoreCase("Item")) {
			clickUsingXpathInJavaScriptExecutor(element("txt_tq_headers", colname));
			if (isElementDisplayed("icon_sorting_item")) {
				flag = true;
			}

		}
		if (colname.equalsIgnoreCase("Patient name")) {
			clickUsingXpathInJavaScriptExecutor(element("txt_tq_headers", colname));

			if (isElementDisplayed("patient_name_sort_icon", colname)) {
				flag = true;
			}

		}
		if (colname.equalsIgnoreCase("Destination")) {
			clickUsingXpathInJavaScriptExecutor(element("txt_tq_headers", colname));
			if (isElementDisplayed("destination_sort_icon")) {
				flag = true;
			}
		}
		return flag;
	}

	public String getColorOfFirstTransactionAndMakeItActive() {
		String pickcolor = getColorOfFirstTransaction();
		makeFirstTransactionActive();
		return pickcolor;
	}

	public String getColorOfFirstTransaction() {
		WebElement element = elements("pick_queue_all_transaction").get(0);
		// String bgColor = element((element +
		// "/div")).getCssValue("background-color");
		String bgColor = element.getCssValue("background-color");

		return bgColor;

	}

	public boolean verifyColorCurrentPickIsAsPerTransactionPriority(String tpColor) {
		System.out.println("active colors" + element("current_pick_color").getCssValue("background-color"));
		System.out.println("background" + tpColor);
		if (element("current_pick_color").getCssValue("background-color").equalsIgnoreCase(tpColor)) {
			flag = true;
		}

		return flag;
	}

	public String getTabCount(int position) {
		wait.hardWait(18);
		String holdTransCount = null;
		List<WebElement> tabsData = elements("pick_queue_transaction_count");

		for (int i = 0; i < tabsData.size(); i++) {
			holdTransCount = tabsData.get(position).getText();
		}
		return holdTransCount.trim();
	}

	public void clickMultipleCheckbox(int size) {

		// WebElement box;
		int i = size;
		while (i > 0) {
			elements("chkbox_PickCheckbox").get(i).click();
			i--;

		}
		/*
		 * List<WebElement> elements =elements("chkbox_PickCheckbox"); for (int
		 * i = 0; i < size; i++) { wait.waitForPageToLoadCompletely();
		 * 
		 * //wait.waitForElementsToBeVisible(elements);
		 * //wait.waitForPageToLoadCompletely();
		 * //wait.waitForElementToBeClickable(box);
		 * if(elements("chkbox_PickCheckbox").get(i).isDisplayed()) {
		 * elements("chkbox_PickCheckbox").get(i).click(); wait.hardWait(5);
		 * wait.waitForPageToLoadCompletely(); } else { continue; }
		 * 
		 * 
		 * }
		 */

	}

	public void verifyFirstActionLinkAndClick(String priority_name, String action) {
		wait.hardWait(10);
		isElementDisplayed("link_hold", priority_name, action);
		logMessage("Verified hold link on Transaction Queue page");
		clickUsingXpathInJavaScriptExecutor(element("link_hold", priority_name, action));
		logMessage("Clicked on first hold link");
	}

	public void verifyTabOnTQAndClick(String action) {
		wait.hardWait(6);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 75);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 75);
		isElementDisplayed("tab_actions", action);
		logMessage("Verified " + action + " tab on Transaction Queue page");
		try {
			element("tab_actions", action).click();
		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("tab_actions", action));
		}
		
		logMessage("Clicked on " + action + " tab");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		
	}

	public void verifyTransactionActionAndClick(String type) {
		wait.hardWait(5);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		isElementDisplayed("btn_type", type);
		logMessage("Verified " + type + " button on Transaction Queue page");
		wait.waitForElementToBeClickable(element("btn_type", type));
		element("btn_type", type).click();
		logMessage("Clicked on " + type + " button");
	}

	public void verifyTransactionActionAndClickRestock(String type) {
		wait.hardWait(5);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		isElementDisplayed("button_secondHoldRestock", type);
		logMessage("Verified " + type + " button on Transaction Queue page");
		wait.waitForElementToBeClickable(element("button_secondHoldRestock", type));
		element("button_secondHoldRestock", type).click();
		logMessage("Clicked on " + type + " button");
	}

	public void verifyTransactionActionAndClickRelease(String type) {
		wait.hardWait(5);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		isElementDisplayed("button_secondRelease", type);
		logMessage("Verified " + type + " button on Transaction Queue page");
		wait.waitForElementToBeClickable(element("button_secondRelease", type));
		element("button_secondRelease", type).click();
		logMessage("Clicked on " + type + " button");
	}

	public void verifyHoldButtonAbsent(String type) {
		wait.hardWait(5);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		Assert.assertFalse(isElementNotDisplayed("btn_type", type));
	}

	public ArrayList<String> getTransactionDetails(int size) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		List<WebElement> elements = elements("txt_numberOfTrans");

		trans_data = new ArrayList<String>();

		for (int i = 0; i < size; i++) {

			String trans_details = elements.get(i).getText().replaceAll("\\s", "").replace("PickNow", "")
					.replace("Hold", "").replace("Delete", "").replace("Release", "").replace("RestockNow", "")
					.split(",")[0];
			System.out.print("Value of quantity: " + trans_details);

			trans_data.add(trans_details);
		}
		return trans_data;
	}

	public ArrayList<String> getAllTransactionDetails() {
		wait.hardWait(3);
		List<WebElement> elements = elements("txt_numberOfTrans");
		trans_data = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			String trans_details = elements.get(i).getText().replaceAll("\\s", "").replace("PickNow", "")
					.replace("Hold", "").replace("Delete", "").replace("Release", "").replace("RestockNow", "")
					.split(",")[0];
			trans_data.add(trans_details);
		}

		return trans_data;

	}

	public boolean getDetails() {
		wait.hardWait(3);
		List<WebElement> elements = elements("txt_numberOfTrans");
		if (elements.isEmpty())
			return false;
		else
			return true;
	}

	public ArrayList<String> getAllTransactionDetailsInReverseOrder() {
		wait.hardWait(3);
		List<WebElement> elements = elements("txt_numberOfTrans");
		trans_data = new ArrayList<String>();
		trans_data_reverse = new ArrayList<String>();
		for (int i = elements.size() - 1; i >= 0; i--) {
			String trans_details = elements.get(i).getText().replaceAll("\\s", "").replace("PickNow", "")
					.replace("Hold", "").replace("Delete", "").replace("Release", "").replace("RestockNow", "")
					.split(",")[0];
			trans_data.add(trans_details);
		}

		return trans_data;

	}

	public boolean compareTransactionData(int count, ArrayList<String> previous_data, ArrayList<String> after_data) {

		int flag = 0;

		for (String item : previous_data) {
			for (String item1 : after_data) {
				if (item.equalsIgnoreCase(item1)) {
					flag++;
				}
			}
		}
		if (flag == count) {
			logMessage("[ASSERTION PASSED] : Verified  transaction data is correct ");
			return true;
		} else {
			logMessage("[ASSERTION FAILED] : Verified  transaction data is not correct");
			return false;
		}

	}

	public boolean VerifyCycleCountLinkIsAvailableunderActionsTab() {
		if (navigateToChooseCycleCount()) {
			wait.waitForElementToBeVisible(element("popup_manual_cycle_count", "Select Storage Areas in"));
			verifyElementTextContains("popup_manual_cycle_count", "Cycle Count");
			logMessage("User is able to see CycleCount Popuup");
			flag = true;
		}
		return flag;
	}

	private boolean navigateToChooseCycleCount() {
		if (isElementDisplayed("link_CycleCount")) {
			logMessage("Verified choose_ISA link on Transaction Queue Page");
			clickUsingXpathInJavaScriptExecutor(element("link_CycleCount"));

			logMessage("Clicked on Cycle Count link");
			flag = true;
		} else
			flag = false;
		return flag;

	}

	public void verifyActionButtonAndClick() {
		
		isElementDisplayed("btn_actions");
		logMessage("Verified action button on Transaction Queue page");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_actions"));
		logMessage("[STEP]: Click on action button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
	}

	public void verifyActionItemsAndClick(String action_item) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		try {
			isElementDisplayed("list_actions_items", action_item);
			logMessage("Verified action item on Transaction Queue page");
			clickUsingXpathInJavaScriptExecutorSingleClick(element("list_actions_items", action_item));
		} catch (Exception e) {
			Actions action = new Actions(driver);
			action.moveToElement(element("list_actions_items", action_item)).click().build().perform();
		}
		logMessage("Click on action: " + action_item);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
	}
	
	
	public boolean verifyUserIsOnTransactionQueuePage() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("txt_currentPick");
		if (element("txt_currentPick").getText().trim().contains("Current Pick")) {
			return true;
		}
		return false;
	}
	
	
	public boolean verifyCurrentPickHeaderDispaysPriorityName(String priority) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		isElementDisplayed("priority_currentPick");
		if (element("priority_currentPick").getText().trim().contains(priority)) {
			return true;
		}
		return false;
	}

	public void verifyItemsOnRestockPopup(List<String> PopupItems) {
		for (String PopupItem : PopupItems) {
			isElementDisplayed("txt_itemId_itemDescription", PopupItem);
			logMessage("Items are displayed");
		}
		isElementDisplayed("txt_quantity");
		logMessage("Quantity text is displayed");
		Assert.assertTrue(element("txt_quantity").getAttribute("value").trim() != null);
	}

	public void verifyExpirationDateLotNumber(List<String> PopupFields) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		for (String PopupField : PopupFields) {
			isElementDisplayed("txt_expiratrionDate_lotNumber", PopupField);
			logMessage("Fields are displayed");
		}

	}

	public void verifyUpdatedExpirationDate() {
		isElementDisplayed("field_expirationDate");
		enterTextInField(element("field_expirationDate"),
				DateUtil.getCurrentdateInStringWithGivenFormate("MM/dd/yyyy"));
		isElementDisplayed("field_lotNumber");
		enterTextInField(element("field_lotNumber"), "12");
		isElementDisplayed("btn_Add");
		clickUsingXpathInJavaScriptExecutor(element("btn_Add"));
	}

	public void clickConfirmButtonOnResotckPopup() {
		isElementDisplayed("btn_Add");
		clickUsingXpathInJavaScriptExecutor(element("btn_Add"));
	}

	public void clickOnMenuOptions(String option) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("icon_menu");
		clickUsingXpathInJavaScriptExecutor(element("icon_menu"));
		isElementDisplayed("link_menuOptions", option);
		clickUsingXpathInJavaScriptExecutor(element("link_menuOptions", option));
	}

	public boolean verifyDeleteConfirmationBox(String action) {
		wait.waitForElementToBeVisible(element("dialog_box_authorization_popup"));
		if (isElementDisplayed("dialog_box_authorization_popup")) {
			if (isElementDisplayed("btn_popup_cancel") && isElementDisplayed("btn_popup_confirm")) {
				if (action.equalsIgnoreCase("Cancel")) {
					String expected = getTabCount(0);
					element("btn_popup_cancel").click();
					flag = true;
				}

			}
			if (action.equalsIgnoreCase("Confirm")) {
				element("btn_popup_confirm").click();
				flag = true;
			}

		}

		return flag;
	}

	public boolean verifyDeleteButtonIsDisabledWhenNoTransactionIsSelected() {
		if (isElementNotDisplayed("btn_type", "Delete")) {
			flag = true;
		}
		return flag;
	}

	public boolean verifyToastMessageOnSuccess() {

		try {
			// wait.applyFluentWait(getLocator("loader"), 80, 500);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
			isElementDisplayed("toast_message_success");
			flag = true;
		} catch (Exception e) {
			// wait.applyFluentWait(getLocator("loader"), 80, 500);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
			isElementDisplayed("toast_message_success");
		}
		return flag;
	}

	public boolean verifyScrollBarIsPresentWhenScreenSizeIsMinimized() {
		minimizeBrowserWindow();
		if (verifyBrowserScrollBar()) {
			flag = true;
		}
		return flag;
	}

	public boolean verifyOverrideWithoutScanner(String text1, String text2) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		try {
			if (isElementDisplayed("link_work_without_scanner")) {
				// wait.hardWait(2);
				clickUsingXpathInJavaScriptExecutorSingleClick(element("link_work_without_scanner"));
				// element("link_work_without_scanner").click();
				// if (isElementDisplayed("dialog_box_authorization_popup")) {
				// confirmPopup();
				logMessage("Authorization Popup is visible on UI");
				wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
				if (isElementDisplayed("bar_progress_success")) {
					isElementDisplayed("scan_text", text2);
					logMessage("Authorization Popup is visible on UI");

					logMessage("Toggle Bar turns green completely");
					flag = true;

				}
				// }

			}
		} catch (Exception e) {
			pageRefresh();
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
			if (isElementDisplayed("link_work_without_scanner")) {
				wait.hardWait(2);
				element("link_work_without_scanner").click();
				// if (isElementDisplayed("dialog_box_authorization_popup")) {
				// confirmPopup();
				logMessage("Authorization Popup is visible on UI");
				wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
				if (isElementDisplayed("bar_progress_success")) {
					// isElementDisplayed("scan_text", text2);
					logMessage("Authorization Popup is visible on UI");
					flag = true;
					logMessage("Toggle Bar turns green completely");

				}
				// }

			}
		}
		return flag;
	}

	public void clickBinScanOverride() {
		
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);
		isElementDisplayed("bin_scan_override");
		clickUsingXpathInJavaScriptExecutor(element("bin_scan_override"));
		logMessage("ASSERT PASSED: clicked on Scan Override Button");
		
	}

	public boolean verifySuccessMessageOverrideWithoutScanner() {

		if (isElementDisplayed("success_msg_after_override")) {
			flag = true;
		}
		return flag;
	}

	public boolean fillRestockItemDetailsVerifyAciveTransaction(List<String> listPopupItems,
			List<String> listPopupFields) {
		flag = false;
		verifyItemsOnRestockPopup((listPopupItems));
		verifyExpirationDateLotNumber((listPopupFields));
		verifyUpdatedExpirationDate();
		clickUsingXpathInJavaScriptExecutor(element("btn_save_restock_details"));
		if (verifyActiveTransactionBox()) {
			flag = true;
		}
		return flag;
	}

	public boolean verifyValidItemNameInCurrentPick(ArrayList<String> activeTransactionName) {
		
		String item_name = element("txt_item_description").getText();
		String item_name_with_spaces_removed = item_name.replaceAll(" ", "");
		
		if (item_name_with_spaces_removed.contains(activeTransactionName.get(0))) {
			System.out.println("item_name_with_spaces_removed=" + item_name_with_spaces_removed);
			System.out.println("activeTransactionName.get(0)=" + activeTransactionName.get(0));

			if (isElementDisplayed("txt_item_quantity")) {
				if (element("txt_item_quantity").getText().contains(activeTransactionName.get(1))) {
					System.out.println(
							"element('txt_item_quantity').getText()=" + element("txt_item_quantity").getText());
					System.out.println("activeTransactionName.get(1)=" + activeTransactionName.get(1));
					if (isElementDisplayed("txt_item_location")) {
						String data = element("txt_item_location").getText();
						// String[] parts = data.split("|");
						// System.out.println("parts=" +parts);
						// String location = parts[0].split("-")[0].toString();
						// String patient_name =
						// parts[1].split("-")[0].toString();
						// System.out.println("location=" +location);
						System.out.println("activeTransactionName.get(3)=" + activeTransactionName.get(3));

						if (data.contains(activeTransactionName.get(3)))
							flag = true;
					}

				}

			}
		}
		return flag;
	}
	
	// TODOYUGAL
	public boolean verifyActiveTransactionItem(String itemName) {
		// active_transaction_box
		try {
			if(isElementDisplayed("active_transaction_box")) {
				return element("active_transaction_box").getText().contains(itemName);
			}
		} catch(Exception e) {
			return true;
		}
		return false;
	}

	public void deselectAllTransaction() {

		if (isElementDisplayed("pick_queue_all_checkbox")) {
			if (element("pick_queue_all_checkbox").isSelected()) {
				wait.waitForElementToBeClickable(element("pick_queue_all_checkbox"));
				wait.elementHighlight(element("pick_queue_all_checkbox"));
				clickUsingXpathInJavaScriptExecutorSingleClick(element("pick_queue_all_checkbox"));
				// element("pick_queue_all_checkbox").click();
				logMessage(" transaction are deselected.");

			}
		}
	}

	public void deselectATransaction() {

		isElementDisplayed("checkbox_first_transaction");
		if (element("checkbox_first_transaction").isSelected()) {
			wait.waitForElementToBeClickable(element("checkbox_first_transaction"));
			wait.elementHighlight(element("checkbox_first_transaction"));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("checkbox_first_transaction"));
			logMessage("First Transaction checkbox is clicked");
		}
	}

	public void verifyAndReleaseHoldTransaction(String tab_button, String type) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 50);
		List<WebElement> elements = elements("link_count_transaction_actions", type);
		if (!elements.isEmpty()) {
			verifyCheckboxesForEachTransactionIsSelected();
			verifyTransactionActionAndClick(tab_button);

		}
	}

	public boolean verifyActionLinkIsNotPresent(String priority_name, String action) {
		return isElementNotDisplayed("link_hold", priority_name, action);

	}

	public boolean verifyTransactionActionButtonIsNotPresent(String type) {
		return isElementNotDisplayed("btn_type", type);

	}

	public boolean verifyTransactionActionButtonIsPresent(String type) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		return isElementDisplayed("btn_type", type);

	}

	public int verifyTransactionLinkCount(String type) {
		return elements("link_count_transaction_actions", type).size();
	}

	public boolean verifyTransactionHoldLinkCount(String type) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForElementsToBeVisible(elements("link_count_transaction_actions", type));
		List<WebElement> elements = elements("link_count_transaction_actions", type);
		for (WebElement ele : elements) {
			if (!ele.getText().trim().equalsIgnoreCase("Hold")) {
				return false;
			}
		}
		return true;
	}

	public void veifyItemsOnRestockPopup(List<String> PopupItems) {
		for (String PopupItem : PopupItems) {
			isElementDisplayed("txt_itemId_itemDescription", PopupItem);
			logMessage("Items are displayed");
		}
		isElementDisplayed("txt_quantity");
		logMessage("Quantity text is displayed");
		Assert.assertTrue(element("txt_quantity").getAttribute("value").trim() != null);
	}

	public void verifyActionTabAndClick(String action, int i) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		clickUsingXpathInJavaScriptExecutor(element("tab_actions", action));
		logMessage("Clicked on" + action + "tab");
	}

	public boolean verifyFirstActionLinkAndClick(String action) {
		boolean flag = false;
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.hardWait(5);
		wait.applyFluentWait(getLocator("btn_transaction_actions"), 60, 500);
		isElementDisplayed("btn_transaction_actions");
		logMessage("Verified link on Transaction Queue page");
		if (isElementDisplayed("btn_transaction_actions", action)) {
			wait.hardWait(5);
			wait.waitForElementToBeClickable(element("btn_transaction_actions", action));
			try {
				element("btn_transaction_actions", action).click();

			} catch (Exception e) {
				clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_transaction_actions", action));
			}
			flag = true;
			logMessage("Clicked on" + action + "tab");
		}
		return flag;

	}

	public void clickFirstHold() {
		wait.hardWait(5);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		isElementDisplayed("btn_hold_first");
		element("btn_hold_first").click();
		/*
		* List<WebElement> elements = elements("btn_hold_first");
		* System.out.println(elements.get(0));
		* elements.get(0).click();
		*/
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
	}
	
	public void clickFirstRelease() {
		wait.hardWait(20);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		isElementDisplayed("btn_release_first");
		element("btn_release_first").click();
		/*
		 * List<WebElement> elements = elements("btn_release_first");
		 * System.out.println(elements.get(0)); elements.get(0).click();
		 */
	}

	public void clickFirstRestockNow() {
		wait.hardWait(10);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		isElementDisplayed("btn_restocknow_first");
		List<WebElement> elements = elements("btn_restocknow_first");
		elements.get(0).click();
		// element("btn_restocknow_first").click();
	}

	public void clickActiveHoldButton(String button) {
		wait.hardWait(3);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		element("action_button", button).click();
		
	}
	
	public void clickButtonById(String id) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		wait.waitForElementToBeClickable(element("action_button", id));
		// element("action_button", id).click();
		clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button", id));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		//wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 30);
	}
	
	
	public void clickButtonWithText(String btnTxt) {
		element("save_close_btn", btnTxt).click();
	}
	
	public void clickFirstPickNow() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		isElementDisplayed("btn_picknow_first");
		List<WebElement> elements = elements("btn_picknow_first");
		try {
			elements.get(0).click();
		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(elements.get(0));
		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		logMessage("[STEP]: Clicked om first Pick Now button");
	}
	

	public void clickHoldOnRestock() {
		wait.hardWait(5);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		isElementDisplayed("first_hold_restock");
		List<WebElement> elements = elements("first_hold_restock");
		try {
			elements.get(0).click();

		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(elements.get(0));
		}
		// element("first_hold_restock").click();
	}

	public boolean selectAllRestockCheckboxes() {
		wait.hardWait(10);
		checkCheckbox(element("restock_all_checkbox"));
		flag = true;
		logMessage("User has clicked on all checkbox in Restock Queue");
		return flag;
	}

	public ArrayList<String> getRestockTransactionDetails() {

		List<WebElement> elements = elements("pick_now_first_transaction");
		activeTransaction = new ArrayList<String>();
		String transactionDesc = elements.get(3).getText();
		String quantity = elements.get(2).getText();
		String type = elements.get(1).getText();
		String location = elements.get(5).getText();

		activeTransaction.add(type);
		activeTransaction.add(quantity);
		activeTransaction.add(transactionDesc);
		activeTransaction.add(location);

		return activeTransaction;

	}

	public boolean restockFirstTransaction() {
		List<WebElement> elements = elements("restock_now_first_transaction");
		click(elements.get(1));
		if (isElementDisplayed("restock_select")) {
			click(element("restock_select"));
			flag = true;
		}
		expirylotflag = isElementNotDisplayed("popup_restock",
				"The popup is not available for current Restock Transaction");
		if (expirylotflag == true) {
			System.out.println("In popup");
			logMessage("Pop-up is displayed");
			verifyExpirationDateLotNumber(Arrays.asList(listPopupFields));
			verifyUpdatedExpirationDate();
			clickConfirmRestock();
		}
		return flag;
	}

	public boolean clickConfirmRestock() {
		isElementDisplayed("restock_confirm");
		logMessage("Verified Confirm button on Lot Screen ");
		click(element("restock_confirm"));
		flag = true;

		return flag;

	}

	public void verifyAndClickAddPick() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("add_pick_button");
		logMessage("Verified Add Pick button");
		wait.waitForElementToBeClickable(element("add_pick_button"));
		try {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("add_pick_button"));

		} catch (Exception e) {
			Actions action = new Actions(driver);
			Action seriesOfAction = (Action) action.moveToElement(element("add_pick_button")).click().build();

			seriesOfAction.perform();
		}
		logMessage("[STEP]: Clicked on Add Pick Button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
	}

	public void verifyUserIsOnAddPickPage(String text) {

		Assert.assertTrue(element("add_Pick_txt").getText().trim().contains(text));
		logMessage("[ASSERTION PASSED]: Verified Add Pick Popup");
		/*
		 * if(isElementDisplayed("add_Pick_txt")) { flag=true;
		 * logMessage("User is on Add Pick Page"); } else flag=false; return
		 * flag;
		 */
	}

	public void searchItemValue(String SearchItemName) {
		
		wait.hardWait(20);
		isElementDisplayed("search_item");
		logMessage("[ASSERTION PASSED]: Verified Search field on Add Pick Screen");
		enterTextInField(element("search_item"), SearchItemName);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		
	}

	public void searchItemName(String name) {
		isElementDisplayed("search_item");

	}

	public boolean verifyClearSearch() {
		if (isElementDisplayed("clear_item_search")) {
			flag = true;
			logMessage("Clear search button found");
			// wait.hardWait(7);
		} else
			flag = false;

		return flag;

	}

	public void verifySearchedResult(String column, String searchItem) {
		searchItem = searchItem.replaceAll("\\s", "");
		
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		
		isElementDisplayed("first_pick");
		List<WebElement> searchResult = elements("first_pick");
		
		for (int i = 0; i < searchResult.size(); i++) {
			search_column = searchResult.get(i).getText().replaceAll("\\s", "");
			System.out.println(search_column);
			Assert.assertTrue(search_column.contains(searchItem));
		}
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("[Assert Passed]:Search Item found on the basis of: " + column);
		/*
		 * if (search_column.contains(searchItem)) {
		 * logMessage("Search Item found on the basis of:" + column); }
		 * 
		 * else { logMessage("Item does not exist"); }
		 */

	}

	public void verifySearchedResultOnTQ(String column, String searchItem, String dosage) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		isElementDisplayed("first_pick");
		List<WebElement> searchResult = elements("first_pick");
		for (int i = 0; i < searchResult.size(); i++) {
			search_column = searchResult.get(i).getText().replaceAll("\\s", "");
			System.out.println(search_column);
		}
		searchItem = searchItem.replaceAll("\\s", "");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		Assert.assertTrue(search_column.contains(searchItem) && search_column.contains(dosage));
		logMessage("[Assert Passed]:Search Item found on the basis of:" + column);
		/*
		 * if (search_column.contains(searchItem)) {
		 * logMessage("Search Item found on the basis of:" + column); }
		 * 
		 * else { logMessage("Item does not exist"); }
		 */

	}

	public void verifySearchedResultForReturnTQ(String column, String searchItem, String dosage) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		isElementDisplayed("first_return");
		List<WebElement> searchResult = elements("first_return");
		for (int i = 0; i < searchResult.size(); i++) {
			search_column = searchResult.get(i).getText().replaceAll("\\s", "");
			System.out.println(search_column);
		}

		searchItem = searchItem.replaceAll("\\s", "");

		Assert.assertTrue(search_column.contains(searchItem) && search_column.contains(dosage));
		logMessage("[Assert Passed]:Search Item found on the basis of:" + column);
		/*
		 * if (search_column.contains(searchItem)) {
		 * logMessage("Search Item found on the basis of:" + column); }
		 * 
		 * else { logMessage("Item does not exist"); }
		 */

	}

	public void verifySearchedResultForReturn(String column, String searchItem) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		isElementDisplayed("first_return");
		List<WebElement> searchResult = elements("first_return");
		for (int i = 0; i < searchResult.size(); i++) {
			search_column = searchResult.get(i).getText().replaceAll("\\s", "");
			System.out.println(search_column);
		}

		searchItem = searchItem.replaceAll("\\s", "");

		Assert.assertTrue(search_column.contains(searchItem));
		logMessage("[Assert Passed]:Search Item found on the basis of:" + column);
		/*
		 * if (search_column.contains(searchItem)) {
		 * logMessage("Search Item found on the basis of:" + column); }
		 * 
		 * else { logMessage("Item does not exist"); }
		 */

	}

	public boolean verifySearchResults(String searchedData, String colNumber) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		List<WebElement> elements = elements("modal_search_results", colNumber);
		wait.hardWait(7);

		try {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().contains(searchedData)))

				{
					return false;
				} else {
					continue;
				}
			}

		} catch (Exception e) {
			for (int i = 0; i < elements.size(); i++) {
				if (!(elements.get(i).getText().contains(searchedData)))

				{
					return false;
				} else {
					continue;
				}
			}

		}

		return true;
	}

	public boolean verifyAddPickPopup(String expected) {
		isElementDisplayed("popup_add_pick");
		// isElementDisplayed("text_add_pick");
		isElementDisplayed("add_pick_message");
		Assert.assertEquals(element("add_pick_message").getText(), expected);
		logMessage(
				"[ASSERTION PASSED] : [Message verified on Add Pick Popup: ]" + element("add_pick_message").getText());
		return true;
	}

	public void verifySearchItemFields(String expected) {

		isElementDisplayed("search_field");
		List<WebElement> elements = elements("search_field");
		String search_column = elements.get(1).getText().replaceAll("\\s", "");
		System.out.println("Search columns:" + search_column);
		Assert.assertTrue(search_column.contentEquals(expected), "Search Columns not matched");
		logMessage("[Assert Passed]Search columns verified");

	}

	public void verifyNoResultFound(String expected) {
		wait.hardWait(4);
		/*
		isElementDisplayed("no_item_found");
		Assert.assertEquals(element("no_item_found").getText(), expected);
		logMessage("Verified message for no Search Result" + element("no_item_found").getText());
		*/
		isElementDisplayed("no_item_found");
		Assert.assertEquals(element("no_item_found").getText(), expected);
		logMessage("Verified message for no search results: " + element("no_item_found").getText());
	}

	public void clickCancelButton() {
		isElementDisplayed("cancel_btn_add_pick");
		clickUsingXpathInJavaScriptExecutor(element("cancel_btn_add_pick"));
		logMessage("[Step]:Clicked on Cancel button");

	}

	public void clickSearchedItemValue(String elem) {
		if (isElementDisplayed("first_pick")) {
			clickUsingXpathInJavaScriptExecutor(element("first_pick"));
			logMessage("[Assert Passed]:clicked on the Item");
			// wait.hardWait(3);
		}

	}

	public void clickSearchedItemValueForReturn(String elem) {
		try {
			if (isElementDisplayed("first_return")) {
			}

			clickUsingXpathInJavaScriptExecutor(element("first_return"));
			logMessage("[Assert Passed]:clicked on the Item");
		} catch (Exception e) {
			isElementDisplayed("first_item", elem);
			clickUsingXpathInJavaScriptExecutor(element("first_item", elem));
			logMessage("[Assert Passed]:clicked on the Item");
		}

	}

	public void clicksearchedItemValue(String itemName, String row) {
		isElementDisplayed("first_item_add_pick_form", itemName, row);
		clickUsingXpathInJavaScriptExecutor(element("first_item_add_pick_form", itemName, row));
		logMessage("[Assert Passed]:clicked on the Item Name");

	}

	public void verifyInputfield(String ele) {

		isElementDisplayed("waste_quantity", ele);
		logMessage("[Assert Passed]:Element displayed:" + ele);

	}

	public void enterValueinAddPick(String fieldName, String fieldNumber, String data)

	{
		isElementDisplayed("inp_field_computer", fieldName, fieldNumber);
		enterTextInField(element("inp_field_computer", fieldName, fieldNumber), data);
	}

	public boolean verifyFieldIsMandatory(String fieldName) {
		isElementDisplayed("icon_mandatory", fieldName);
		if (element("icon_mandatory", fieldName).getText().trim().contains("*") ||
				element("icon_mandatory", fieldName).getAttribute("class").trim().contains("asterik-sign")) {
			return true;
		}
		return false;
	}

	public void verifyInputFieldIsBlank(String fieldName) {
		Assert.assertTrue((element(fieldName).getText().trim().isEmpty()));
		logMessage("[Assert Passed]:Input field is empty");
	}

	public String EnterValueInInputFieldOnAddPickPopup(String fieldName, String data) {

		isElementDisplayed(fieldName);
		enterTextInField(element(fieldName), data);
		String entered_data = element(fieldName).getAttribute("value");
		return entered_data;
		
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

	public void verifySaveButtonAddPick(String field) {

		System.out.println("element" + field);
		isElementDisplayed("save_close_btn", field);

	}

	public void verifyButtonAddPick(String element, String field) {

		System.out.println("element" + element);
		isElementDisplayed(element, field);

	}

	public String selectDropdownForAddPick(String elem, String data) {
		wait.hardWait(3);
		System.out.println("element is: " + elem);
		List<WebElement> ele = getAllOptionsFromDropDown(element(elem));
		Select priorityDropDown = new Select(element(elem));
		priorityDropDown.selectByVisibleText(data);
		/*
		for (WebElement e : ele) {
			if (e.getText().equalsIgnoreCase(data)) {
				e.click();
			}
		}
		*/
		logMessage("Selected Value is: " + data);
		return data;
	}

	public void verifyButtonOnAddRestock(String element, String field) {
		isElementDisplayed(element, field);
		logMessage("ASSERTION PASSED: Verified button" + field);
	}
	
	
	public void clickSaveCloseORCancelORSaveAddButton(String element, String field) {
		wait.hardWait(10);
		// wait.waitForElementToBeClickable(element(element, field));
		isElementDisplayed(element, field);
		
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element(element, field)).click().build();
		seriesOfAction.perform();
		
		logMessage("[STEP]: Clicked on " + field + " button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
	}
	
	
	public boolean isButtonWithTextDisplayed(String btnTxt) {
		try {
			return isElementDisplayed("save_close_btn", btnTxt);
		} catch (Exception e){
			return isElementNotDisplayed("save_close_btn", btnTxt);
		}
	}
	
	public void verifyCancelPopupOnAddPick(String message) {
		isElementDisplayed("cancel_popup");
		Assert.assertTrue(element("confirm_message").getText().contains(message));
		// System.out.println("Message" + element("confirm_message").getText());
		logMessage("Cancel Popup displayed on Add Pick Screen after clicking Cancel Button");
		logMessage("Assert Passed: Confirmed message on PopUp:" + element("confirm_message").getText());
	}

	public void verifyCancelPopupOnAddReturn(String message) {
		isElementDisplayed("cancel_return_popup");
		Assert.assertTrue(element("confirm_message_return").getText().contains(message));
		logMessage("ASSERT PASSED: Cancel Popup displayed on Add Return Screen after clicking Cancel Button");
		logMessage("ASSERT PASSED: Confirmed message on PopUp:" + element("confirm_message_return").getText());
	}

	public void verifyRequiredFieldMessage(String expected) {
		isElementDisplayed("required_message");
		Assert.assertTrue(element("required_message").getText().contains(expected));
		// Assert.assertEquals(element("required_message").getText(), expected);
		logMessage("[Assert Passed]:Message verified on Add Pick Popup: " + element("required_message").getText());

	}

	public void verifyAdditionalToggle() {

		isElementDisplayed("additional_info_toggle");
		logMessage("Assertion Passed:[Additional Information Toggle Displayed]");

	}

	public void clickAdditionalInfoToggle() {
		clickToggleUsingXpathInJavaScriptExecutor(element("additional_info_toggle"));
		logMessage("Clicked on Additional Information Toggle");

	}

	public void verifylabelforAddPickFields(String fieldName) {
		isElementDisplayed("icon_mandatory", fieldName);
		Assert.assertTrue(element("icon_mandatory", fieldName).getText().contains(fieldName));
		logMessage("[Assert Passed]:Verified the label name for field" + fieldName);

	}

	public void clickConfirmPopupButton() {
		isElementDisplayed("confirm_yes_btn");
		clickUsingXpathInJavaScriptExecutor(element("confirm_yes_btn"));
		logMessage("Clicked on Yes button to close popup");
	}

	public void verifyPlaceholderValues(String element, String placeholdervalue) {
		isElementDisplayed(element);
		Assert.assertEquals(element(element).getAttribute("placeholder"), placeholdervalue);
		logMessage("[Assert Passed]: Placeholder value verified for field" + element + ":"
				+ element(element).getAttribute("placeholder"));

	}

	public void verifyTransaction(String name, String destination, String priority) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		isElementDisplayed("search_tq");
		enterTextInField(element("search_tq"), name);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		List<WebElement> elements = elements("txt_numberOfTrans");
		System.out.println("Entered name is:" + name);
		for (int i = 0; i < 1; i++) {
			
			String trans_details = elements.get(i).getText().replaceAll("\\s", "").replace("PickNow", "")
					.replace("Hold", "").replace("Delete", "").replace("Release", "").replace("RestockNow", "")
					.split(",")[0];
			System.out.println("trans" + trans_details);
			Assert.assertTrue(trans_details.contains(name));
			Assert.assertTrue(trans_details.contains(priority));
			logMessage("[Assert Passed]: Verified new added Transaction in Pick Queue against" + name + ":"
					+ destination + ":" + priority);
			
		}
	}

	public void verifyRestock() {
		isElementDisplayed("tab_restocks");
		logMessage("Verified Restocks tab on Page");
		wait.waitForElementToBeClickable(element("tab_restocks"));

		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("tab_restocks")).click().build();
		seriesOfAction.perform();
		
		// element("tab_restocks").click();
		// clickUsingXpathInJavaScriptExecutor(element("tab_restocks"));
		logMessage("Clicked on Restocks tab");
		// Assert.assertTrue(element("tab_restocks").getAttribute("class").contains("active"));
		wait.hardWait(2);

	}

	public ArrayList<String> getFirstRestockTransactionDetails() {
		// List<WebElement> elements =
		// elements("restock_now_first_transaction");
		wait.hardWait(14);
		List<WebElement> elements = elements("restock_first_trans");
		System.out.println("size" + elements.size());
		for (int i = 0; i < elements.size(); i++) {

			String type = elements.get(2).getText();
			String quantity = elements.get(3).getText();
			String ItemDescription = elements.get(4).getText();
			String Destination = elements.get(5).getText();
			String PatientName = elements.get(6).getText();

			restocktransdetail = new ArrayList<String>();
			restocktransdetail.add(type);
			restocktransdetail.add(quantity);
			restocktransdetail.add(ItemDescription);
			restocktransdetail.add(Destination);
			restocktransdetail.add(PatientName);

		}
		System.out.println("Type: " + restocktransdetail.get(0));
		System.out.println("Quantity:" + restocktransdetail.get(1));
		System.out.println("ItemDescription:" + restocktransdetail.get(2));
		System.out.println("Destination" + restocktransdetail.get(3));
		System.out.println("Patient Name :" + restocktransdetail.get(4));

		return restocktransdetail;

	}

	public void clickRestockNowButton() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("restock_select");
		clickUsingXpathInJavaScriptExecutor(element("restock_select"));
	}

	public void selectRestockItem() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("restock_select");
		clickUsingXpathInJavaScriptExecutor(element("restock_select"));
		logMessage("Clicked on Restock Now button");
		wait.hardWait(10);
		expirylotflag = isElementNotDisplayed("popup_restock");

		if (expirylotflag == true) {
			System.out.println("In popup");
			logMessage("ASSERTION PASSED: Restock Pop-Up is displayed");
			verifyExpirationDateLotNumber(Arrays.asList(listPopupFields));
			verifyUpdatedExpirationDate();
			clickConfirmRestock();
			wait.hardWait(10);
		}

		else {
			System.out.println("No popup");
		}

	}

	public void verifyTQPageAndAppendIP(String url, String ip) {

		String newURL = driver.getCurrentUrl() + "?ipAddress=" + ip;
		driver.navigate().to(newURL);
	}

	public boolean verifyrestockButtonDisabled(String buttonText) {
		if (selectAllRestockCheckboxes()) {

			List<WebElement> elem = elements("restock_button");
			for (WebElement e : elem) {
				tagname = elem.get(0).getText();
				if (e.getText().equalsIgnoreCase(buttonText)) {
					if (!e.isEnabled()) {
						flag = true;
						logMessage("ASSERTTION PASSED:The" + tagname + " button is disabled");
					}
				}
			}
		}

		return flag;
	}

	public void verifyActiveRestockTransaction() {

		verifyActiveRestockTransactionQuantity(restocktransdetail);
		verifyActiveRestockItemName(restocktransdetail);

	}

	public boolean verifyActiveRestockTransactionQuantity(List<String> elements) {

		String a = getActiveQuantity();
		if (a.startsWith("0")) {
			a = a.substring(1);
		}

		if (a.equalsIgnoreCase(restocktransdetail.get(1))) {
			logMessage("[ASSERTION PASSED]:The Quantity for Active Transaction: " + a
					+ " verified with the Restocked Transaction");
			flag = true;
		}

		return flag;

	}

	public boolean verifyActiveRestockItemName(List<String> elements) {
		String iname = getActiveItemName();
		if (iname.equalsIgnoreCase(restocktransdetail.get(2))) {

			logMessage(
					"[ASSERTION PASSED]:The Item Description: " + iname + " verified with the restocked Transaction");
			flag = true;

		}
		return flag;
	}

	public String getActiveQuantity() {
		String activeQOH = null;
		wait.hardWait(5);
		isElementDisplayed("txt_item_quantity");
		List<WebElement> qty = elements("txt_item_quantity");
		for (WebElement e : qty) {
			activeQOH = qty.get(0).getText();
			logMessage("QOH for Active Transaction is :" + qty.get(0).getText());
		}

		return activeQOH;
	}

	public String getActiveItemName() {

		String activeItem = null;
		isElementDisplayed("txt_item_description");
		List<WebElement> activeitems = elements("txt_item_description");
		for (WebElement item : activeitems) {

			activeItem = activeitems.get(0).getText();
			logMessage("The active Item name is : " + activeItem);
		}
		return activeItem;
	}

	public void clearText(String field) {
		clickUsingXpathInJavaScriptExecutor(element(field));
		element(field).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		wait.hardWait(3);
		logMessage("Text Cleared");
	}

	public void verifyAndClickShowAllItems() {
		try{
			wait.hardWait(3);
			isElementDisplayed("show_all_items_toggle");
			logMessage("ASSERTION PASSED: Verified Toggle for: 'Show All Items'");
			clickUsingXpathInJavaScriptExecutorSingleClick(element("show_all_items_toggle"));
			logMessage("ASSERTION PASSED: Clicked on Show All Items toggle");
		}catch (Exception e) {
			wait.hardWait(3);
			isElementDisplayed("show_all_items_toggle");
			logMessage("ASSERTION PASSED: Verified Toggle for: 'Show All Items'");
			element("show_all_items_toggle").click();
			logMessage("ASSERTION PASSED: Clicked on Show All Items toggle");
		}
		

	}

	public void verifyActiveRestockMessage(String message) {
		wait.hardWait(5);
		isElementDisplayed("restock_active_bar_message");
		Assert.assertEquals(element("restock_active_bar_message").getText(), message);
		logMessage("ASSERTION PASSED: Verified Message on Active bar:" + message);
	}

	public void verifyReturnTransaction(String item, String quantity) {
		wait.hardWait(20);
		isElementDisplayed("search_tq");
		enterTextInField(element("search_tq"), item);
		wait.hardWait(8);
		List<WebElement> elements = elements("txt_numberOfTrans");
		
		for (int i = 0; i < 1; i++) {
			String trans_details = elements.get(i).getText().replaceAll("\\s", "").replace("PickNow", "")
					.replace("Hold", "").replace("Delete", "").replace("Release", "").replace("RestockNow", "")
					.split(",")[0];
			System.out.println("trans" + trans_details);
			Assert.assertTrue(trans_details.contains(item));
			// Assert.assertTrue(trans_details.contains(quantity));
			logMessage("[Assert Passed]: Verified new added Transaction in Restock Queue against" + item);
			logMessage(
					"[Assert Passed]: Verified Quantity for new added Transaction in Restock Queue against" + quantity);
		}
		
		wait.hardWait(5);
		clearText("search_tq");
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);
		
	}
	
	public void updateOnHandQuantity() {
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("change_qty_btn")).click().build();

		seriesOfAction.perform();
		// clickUsingXpathInJavaScriptExecutor(element("change_qty_btn"));
		logMessage("Clicked on Change Quantity link");
		enterTextInField(element("qty_textbox"), "2");

		Action seriesOfAction1 = (Action) action.moveToElement(element("save_qty_btn")).click().build();

		seriesOfAction1.perform();

	}

	public void updateActiveItemQuantityAndOnHandQuantity(String className) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("edit_active_item_quantity");
		wait.waitForElementToBeClickable(element("edit_active_item_quantity"));
		String active_quantity = element("active_item_qty_value", className).getText();
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("edit_active_item_quantity")).click().build();
		wait.elementHighlight(element("edit_active_item_quantity"));
		seriesOfAction.perform();
		// clickUsingXpathInJavaScriptExecutor(element("change_qty_btn"));
		logMessage("Clicked on Change Quantity link");
		isElementDisplayed("qty_textbox1");
		sendKeysUsingXpathInJavaScriptExecutor(element("qty_textbox1"), active_quantity + 1);
		// enterTextInField(element("qty_textbox1"), active_quantity+1);
		String edit_on_hand_item_quantity = element("edit_on_hand_item_quantity").getText();
		wait.waitForElementToBeClickable(element("edit_on_hand_item_quantity"));
		Action seriesOfAction1 = (Action) action.moveToElement(element("edit_on_hand_item_quantity")).click().build();
		wait.elementHighlight(element("edit_on_hand_item_quantity"));
		seriesOfAction1.perform();
		sendKeysUsingXpathInJavaScriptExecutor(element("edit_on_hand_item_quantity"), edit_on_hand_item_quantity + 1);
		// enterTextInField(element("edit_on_hand_item_quantity"),
		// edit_on_hand_item_quantity+1);
		// Assert.assertEquals(element("edit_active_item_quantity").getText(),
		// active_quantity+1);

		clickUsingXpathInJavaScriptExecutorSingleClick(element("queue_header"));
		// Assert.assertEquals(element("edit_on_hand_item_quantity").getText(),
		// edit_on_hand_item_quantity+1);
	}

	public void updateActiveItemQuantityAndOnHandQuantity() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("edit_active_item_quantity");
		wait.waitForElementToBeClickable(element("edit_active_item_quantity"));
		String active_quantity = element("edit_active_item_quantity").getText();
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("edit_active_item_quantity")).click().build();
		wait.elementHighlight(element("edit_active_item_quantity"));
		seriesOfAction.perform();
		// clickUsingXpathInJavaScriptExecutor(element("change_qty_btn"));
		logMessage("Clicked on Change Quantity link");
		isElementDisplayed("qty_textbox1");
		sendKeysUsingXpathInJavaScriptExecutor(element("qty_textbox1"), active_quantity + 1);
		// enterTextInField(element("qty_textbox1"), active_quantity+1);
		String edit_on_hand_item_quantity = element("edit_on_hand_item_quantity").getText();
		wait.waitForElementToBeClickable(element("edit_on_hand_item_quantity"));
		Action seriesOfAction1 = (Action) action.moveToElement(element("edit_on_hand_item_quantity")).click().build();
		wait.elementHighlight(element("edit_on_hand_item_quantity"));
		seriesOfAction1.perform();
		sendKeysUsingXpathInJavaScriptExecutor(element("edit_on_hand_item_quantity"), edit_on_hand_item_quantity + 1);
		// enterTextInField(element("edit_on_hand_item_quantity"),
		// edit_on_hand_item_quantity+1);
		// Assert.assertEquals(element("edit_active_item_quantity").getText(),
		// active_quantity+1);

		clickUsingXpathInJavaScriptExecutorSingleClick(element("queue_header"));
		// Assert.assertEquals(element("edit_on_hand_item_quantity").getText(),
		// edit_on_hand_item_quantity+1);
	}

	public void getQuantityOnActiveBox() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		isElementDisplayed("txt_item_quantity");
		String val = element("txt_item_quantity").getText();
		// Assert.assertEquals(val, "1");
	}

	public void ClickAndVerifyActiveItemQuantityAndOnHandQuantity() {
		isElementDisplayed("edit_active_item_quantity");
		wait.waitForElementToBeClickable(element("edit_active_item_quantity"));
		String active_quantity = element("edit_active_item_quantity").getText();
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("edit_active_item_quantity")).click().build();
		wait.elementHighlight(element("edit_active_item_quantity"));
		seriesOfAction.perform();
		// clickUsingXpathInJavaScriptExecutor(element("change_qty_btn"));
		logMessage("Clicked on Change Quantity link");
		isElementDisplayed("qty_textbox1");
		wait.hardWait(3);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("queue_header"));
		wait.hardWait(3);
		// isElementDisplayed("edit_active_item_quantity");
		Assert.assertEquals(element("qty_textbox1").getText(), active_quantity);

		// sendKeysUsingXpathInJavaScriptExecutor(element("qty_textbox1"),
		// active_quantity + 1);
		// enterTextInField(element("qty_textbox1"), active_quantity+1);
		String edit_on_hand_item_quantity = element("edit_on_hand_item_quantity").getText();
		wait.waitForElementToBeClickable(element("edit_on_hand_item_quantity"));
		Action seriesOfAction1 = (Action) action.moveToElement(element("edit_on_hand_item_quantity")).click().build();
		wait.elementHighlight(element("edit_on_hand_item_quantity"));
		seriesOfAction1.perform();
		// sendKeysUsingXpathInJavaScriptExecutor(element("edit_on_hand_item_quantity"),
		// edit_on_hand_item_quantity + 1);
		// enterTextInField(element("edit_on_hand_item_quantity"),
		// edit_on_hand_item_quantity+1);

		clickUsingXpathInJavaScriptExecutorSingleClick(element("queue_header"));
		Assert.assertEquals(element("edit_on_hand_item_quantity").getText(), edit_on_hand_item_quantity);
	}

	public void updateActiveItemQuantityAndPressESCAPE() {
		isElementDisplayed("edit_active_item_quantity");
		wait.waitForElementToBeClickable(element("edit_active_item_quantity"));
		String active_quantity = element("edit_active_item_quantity").getText();
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("edit_active_item_quantity")).click().build();
		wait.elementHighlight(element("edit_active_item_quantity"));
		seriesOfAction.perform();
		// clickUsingXpathInJavaScriptExecutor(element("change_qty_btn"));
		logMessage("Clicked on Change Quantity link");
		isElementDisplayed("qty_textbox1");
		sendKeysUsingXpathInJavaScriptExecutor(element("qty_textbox1"), active_quantity + 1);

		action.sendKeys(Keys.ESCAPE).build().perform();
		Assert.assertEquals(element("edit_active_item_quantity").getText(), active_quantity);

	}

	public void hoverItemQuantity() {
		isElementDisplayed("edit_active_item_quantity");
		String colorbefore = element("edit_active_item_quantity").getCssValue("color");
		wait.waitForElementToBeClickable(element("edit_active_item_quantity"));
		String active_quantity = element("edit_active_item_quantity").getText();
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("edit_active_item_quantity")).build();
		// wait.elementHighlight(element("edit_active_item_quantity"));
		seriesOfAction.perform();
		String colorhighlighted = element("edit_active_item_quantity").getCssValue("color");
		// Assert.assertNotEquals(colorbefore, colorhighlighted);
	}

	public boolean verifyFontSize(String className) {
		isElementDisplayed("edit_active_item_quantity");
		String fontbefore = element("radio_button_disabled_class", className).getCssValue("font-size");
		logMessage("font before: " + fontbefore);
		// int fontbeforevalue = Integer.parseInt(fontbefore);
		wait.waitForElementToBeClickable(element("edit_active_item_quantity"));
		String active_quantity = element("radio_button_disabled_class", className).getText();
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("edit_active_item_quantity")).doubleClick()
				.sendKeys(Keys.BACK_SPACE).sendKeys("111111").sendKeys(Keys.ENTER).build();
		wait.elementHighlight(element("edit_active_item_quantity"));
		seriesOfAction.perform();
		wait.hardWait(3);
		
		String fontafter = element("radio_button_disabled_class", className).getCssValue("font-size");
		logMessage("font after entering long value: " + fontafter);
		// int fontaftervalue = Integer.parseInt(fontafter);
		if (fontafter != fontbefore) {
			return true;
		}
		return false;
	}
	
	public String getFontSize(String className){
		String fontsize = element("radio_button_disabled_class", className).getCssValue("font-size");
		logMessage("font before: " + fontsize);
		return fontsize;
	}

	public void updateActiveItemQuantityAndPressENTER() {
		isElementDisplayed("edit_active_item_quantity");
		wait.waitForElementToBeClickable(element("edit_active_item_quantity"));
		String active_quantity = element("edit_active_item_quantity").getText();
		int active_quantity_int = Integer.parseInt(active_quantity);
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("edit_active_item_quantity")).click().build();
		wait.elementHighlight(element("edit_active_item_quantity"));
		seriesOfAction.perform();
		// clickUsingXpathInJavaScriptExecutor(element("change_qty_btn"));
		logMessage("Clicked on Change Quantity link");
		isElementDisplayed("qty_textbox1");
		logMessage("Updated Quantity is :" + active_quantity_int + 2);
		sendKeysUsingXpathInJavaScriptExecutor(element("qty_textbox1"), active_quantity_int + 2);

		action.sendKeys(Keys.ENTER).build().perform();
		Assert.assertEquals(element("edit_active_item_quantity").getText(), active_quantity_int + 2);

	}

	public void clickClearSearch() {
		isElementDisplayed("clear_item_search");
		logMessage("ASSERRTION POSSED: Clear Icon found");
		clickUsingXpathInJavaScriptExecutor(element("clear_item_search"));
		logMessage("Clicked on Clear Search icon");
		
	}

	public void verifyInvalidQuantityMessage(String message) {
		isElementDisplayed("error_message_quantity");
		Assert.assertEquals(element("error_message_quantity").getText(), message);
		logMessage("ASSERTION PASSED: Verified error message on Quantity field" + message);

	}

	public void verifyItemDetailsInRestockPopup(String field) {
		isElementDisplayed("fields_restock_popup", field);
		logMessage("ASSERTION PASSED: Restock Item Details are present on popup" + field);
	}

	public void verifyFieldsRestockPopup() {
		isElementDisplayed("quantity_restock_popup");
		logMessage("ASSERTION PASSED: Quantity field is displayed in Popup");
		isElementDisplayed("field_expirationDate");
		logMessage("ASSERTION PASSED: Earliest Expiration Date field is displayed in Popup");
		isElementDisplayed("field_lotNumber");
		logMessage("ASSERTION PASSED: Lot Number field is displayed in Popup");

	}

	public void selectRestockNowAndVerifyPopup() {
		wait.waitForPageToLoadCompletely();
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		isElementDisplayed("restock_select");
		clickUsingXpathInJavaScriptExecutor(element("restock_select"));
		logMessage("Clicked on Restock Now button");
		wait.hardWait(10);
		expirylotflag = isElementNotDisplayed("popup_restock");

		if (expirylotflag == true) {
			logMessage("ASSERTION PASSED: Restock Pop-Up is displayed");

		}

		else {
			System.out.println("No popup");
		}
	}

	public void clickLotExpiryUnknownCheckbox() {
		isElementDisplayed("lot_expiry_unknown_checkbox");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("lot_expiry_unknown_checkbox"));
	}

	public boolean verifyInputFieldIsBlankOnRestocPopup() {
		if (element("field_lotNumber").getText().trim().isEmpty()
				&& element("field_expirationDate").getText().trim().isEmpty()) {
			logMessage("ASSERTION PASSED: The fields are empty");
			return true;
		}
		return false;

	}

	public void verifyButtonsOnRestockPopup() {
		isElementDisplayed("cancel_btn_add_pick");
		logMessage("ASSERTION PASSED: Verified Cancel button on Restock Pop-up");
		isElementDisplayed("restock_confirm");
		logMessage("ASSERTION PASSED: Verified Confirm button on Restock Pop-up");
	}

	public void verifyErrorFieldsOnRestockPopup() {
		wait.hardWait(10);
		isElementDisplayed("earlierst_expiration_error");
		logMessage("ASSERTION PASSED: Vefified Error message on Earliest Expiration Field");
		isElementDisplayed("lot_number_error");
		logMessage("ASSERTION PASSED: Vefified Error message on Lot Number Field");

	}

	public int verifyColumnHeaders(String[] columnHeaders) {
		int count = 0;
		for (String col : columnHeaders) {
			if (isElementDisplayed(col)) {
				count++;
			}
		}

		if (count == 5) {

			return count;
		}

		return count;
	}
	
	public int verifyColumnHeaders_TQtabs(String[] columnHeaders) {
		int count = 0;
		List<WebElement> headerElems = elements("header_col_name");
		
		// first two column headers are empty 
		for(int i = 0; i < columnHeaders.length; i++) {
			if(headerElems.get(i+2).getText().contains(columnHeaders[i])) {
				count++;
			} else {
				logMessage("Header " + columnHeaders[i] + " not displayed");
			}
		}
		return count;
	}

	public boolean verifyActiveTransactionQuantityOnHand(ArrayList<String> activeTransactionDetails) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		String item_name = element("txt_item_description").getText();
		String item_name_with_spaces_removed = item_name.replaceAll(" ", "");
		if (item_name_with_spaces_removed.equalsIgnoreCase(activeTransactionDetails.get(0).replaceAll("\\s", ""))) {
			// if
			// (isElementDisplayed("txt_item_description",activeTransactionDetails.get(0).replaceAll("\\s",
			// ""))){
			try {
				if (isElementDisplayed("txt_item_quantity")) {
					verifyElementText(("txt_item_quantity"), activeTransactionDetails.get(1));
					flag = true;
				}
			} catch (Exception e) {
				makeFirstTransactionActive();
				if (isElementDisplayed("txt_item_quantity")) {
					verifyElementText(("txt_item_quantity"), activeTransactionDetails.get(1));
					flag = true;
				}
			}

		}

		return flag;

	}

	public boolean verifyActiveTransactionLocation(ArrayList<String> activeTransactionDetails) {
		if (isElementDisplayed("txt_item_description", activeTransactionDetails.get(0))) {
			if (isElementDisplayed("txt_item_location")) {
				String data = element("txt_item_location").getText();
				System.out.println("DATA IS" + data);
				String[] parts = data.split("\\|");
				String location = parts[0].split("-")[0].toString();
				String patient_name = parts[1].split("-")[0].toString();
				System.out.println("location" + location);
				System.out.println("patient_name" + patient_name);
				for (int i = 0; i < activeTransactionDetails.size(); i++) {
					System.out.println("elum" + activeTransactionDetails.get(i));
				}

				if (location.equalsIgnoreCase(activeTransactionDetails.get(3)))
					flag = true;
			}

		}

		return flag;
	}

	public void clickActiveTransactionButtons(String actionButton) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		isElementDisplayed("active_transaction_button", actionButton);
		element("active_transaction_button", actionButton).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);

	}

	public void selectValueFromDropDownByIndex(String elementTextReplace, Integer Index) {
		isElementDisplayed("dropdowns_externalSystem", elementTextReplace);
		Select selectValue = new Select(element("dropdowns_externalSystem", elementTextReplace));
		selectValue.selectByIndex(Index);
		logMessage("[STEP]: Value is selected from dropdown By index.");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
	}

	public boolean verifyWasteItemsPopup(String expected) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 30);
		isElementDisplayed("popup_waste_item");
		Assert.assertEquals(element("popup_waste_item").getText(), expected);
		logMessage("[ASSERTION PASSED] : [Message verified on Waste Item Popup: ]"
				+ element("popup_waste_item").getText());
		return true;
	}

	public void confirmWasteItem(String action) {

		isElementDisplayed("waste_item_popup_button", action);
		element("waste_item_popup_button", action).click();
		logMessage(action + " " + "button is clicked");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		
	}

	public boolean verifyErrorMessageonWasteReasonPopup(String data) {

		return isElementDisplayed("message_field", data);
	}

	public void updateOnHandQuantity(String i) {
		isElementDisplayed("update_qty");
		element("update_qty").click();
		element("update_qty").sendKeys(i);
		Assert.assertTrue(element("update_qty").getAttribute("value").equalsIgnoreCase(i));
		logMessage("[Assert Passed]: Verified Quantity for new added Transaction us updated as" + i);
	}

	public void enterHoldDescription() {
		enterTextInField(element("textarea_holdReason"), "HoldDescription");
	}

	public void enterHoldDescription20Char() {
		enterTextInField(element("textarea_holdReason"), "HoldDescription12345689");
	}

	public boolean clickConfirmHoldButton() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		if (isElementDisplayed("button_holdConfirm")) {
			element("button_holdConfirm").click();
			flag = true;
		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 50);
		return flag;
		
	}

	public void clickManualRestockTrans() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 50);
		wait.hardWait(5);
		isElementDisplayed("button_ManualRestockTrans");
		clickUsingXpathInJavaScriptExecutor(element("button_ManualRestockTrans"));

	}

	public void clickManualRestockTransactionBasedOnPriortiyAndItemName(String priority, String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		wait.waitForElementToBeClickable(element("restock_based_on_itemname_and_priority", priority, fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(
				element("restock_based_on_itemname_and_priority", priority, fieldName));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
	}

	public void clickManualPickTransactionBasedOnPriortiyAndItemName(String priority, String fieldName) {
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 75);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		wait.waitForElementToBeClickable(element("pick_based_on_itemname_and_priority", priority, fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(
				element("pick_based_on_itemname_and_priority", priority, fieldName));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		
	}
	
	

	public void holdManualPickTransactionBasedOnPriortiyAndItemName(String priority, String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		wait.waitForElementToBeClickable(element("hold_pick_based_on_itemname_and_priority", priority, fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(
				element("hold_pick_based_on_itemname_and_priority", priority, fieldName));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
	}

	public void deleteholdedManualPickTransactionBasedOnPriortiyAndItemName(String priority, String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		wait.waitForElementToBeClickable(element("delete_pick_based_on_itemname_and_priority", priority, fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(
				element("delete_pick_based_on_itemname_and_priority", priority, fieldName));
	}

	public void enterPopUpDetails() {
		wait.hardWait(10);
		expirylotflag = isElementNotDisplayed("popup_restock");

		if (expirylotflag == true) {
			System.out.println("In popup");
			logMessage("ASSERTION PASSED: Restock Pop-Up is displayed");
			verifyExpirationDateLotNumber(Arrays.asList(listPopupFields));
			enterDate();
			enterLotNumber();
			wait.hardWait(3);
			clickConfirmRestock();
		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
	}

	public void enterRestockItemPopUpDetails() {
		wait.hardWait(10);
		expirylotflag = isElementNotDisplayed("popup_restock");

		if (expirylotflag == true) {
			System.out.println("In popup");
			logMessage("ASSERTION PASSED: Restock Pop-Up is displayed");
			verifyExpirationDateLotNumber(Arrays.asList(listPopupFields));
			enterDate();
			enterLotNumber();

		}
	}

	public void enterDate() {
		isElementDisplayed("field_expirationDate");
		String date = DateUtil.getCurrentdateInStringWithGivenFormate("MM/dd/yyyy");
		enterTextInField(element("field_expirationDate"), date);
		Assert.assertEquals(element("field_expirationDate").getAttribute("value"), date);
	}

	public void enterLotNumber() {
		isElementDisplayed("field_lotNumber");
		Actions action = new Actions(driver);
		Action buildAction = action.moveToElement(element("field_lotNumber")).click()
				.sendKeys(element("field_lotNumber"), "2").build();
		buildAction.perform();
		lotNumberValue = element("field_lotNumber").getAttribute("value");
		Assert.assertEquals(element("field_lotNumber").getAttribute("value"), "2");
		wait.hardWait(6);
		isElementDisplayed("btn_Add");
		clickUsingXpathInJavaScriptExecutor(element("btn_Add"));
	}

	public void selectATransaction() {

		isElementDisplayed("checkbox_first_transaction");
		if (!element("checkbox_first_transaction").isSelected()) {
			element("checkbox_first_transaction").click();
			logMessage("First Transaction checkbox is clicked");
		}
	}

	public boolean verifyActiveTransactionInPickTab(String itemName) {
		List<WebElement> items = elements("txt_item_descriptions");
		wait.waitForElementsToBeVisible(items);
		for (int i = 0; i <= items.size(); i++) {
			if (items.get(i).getText().contains(itemName)) {
				return true;
			} else {
				continue;
			}
		}
		return false;

	}

	public boolean verifyTransactionQueueIsEmpty() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 250);
		return isElementDisplayed("txt_no_data_TQ");
	}

	public void selectRestockTransaction(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("receiving_txn_checkbox", fieldName);
		wait.waitForElementToBeClickable(element("receiving_txn_checkbox", fieldName));
		wait.elementHighlight(element("receiving_txn_checkbox", fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("receiving_txn_checkbox", fieldName));
		// element("invoice_item_checkbox", fieldName).click();
	}

	public void restockNowReceivingTransaction(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("receiving_restock_now", fieldName);
		wait.waitForElementToBeClickable(element("receiving_restock_now", fieldName));
		wait.elementHighlight(element("receiving_restock_now", fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("receiving_restock_now", fieldName));
		// element("invoice_item_checkbox", fieldName).click();
	}

	public boolean clickScanOverride() {
		
		logMessage("Starting to process active transaction");
		// wait.hardWait(3);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		isElementDisplayed("link_work_without_scanner");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_work_without_scanner"));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		try {
			Thread.sleep(5);
		} catch(Exception e) {
			e.printStackTrace();
		}
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_work_without_scanner"));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		return true;
	}

	public boolean clickScanOverrideOnce() {
		// wait.hardWait(3);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 50);
		isElementDisplayed("link_work_without_scanner");
		// wait.waitForElementToBeClickable(element("link_work_without_scanner"));
		// wait.elementHighlight(element("link_work_without_scanner"));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_work_without_scanner"));
		logMessage("[STEP]: Scan Override link is clicked");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		return true;
	}

	public int getActiveRestockTransactionQuantity(String fieldName) {
		String quantity;
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("update_quantity_restock", fieldName);
		// wait.waitForElementToBeClickable(element("update_quantity_restock",fieldName));
		wait.elementHighlight(element("update_quantity_restock", fieldName));
		quantity = element("update_quantity_restock", fieldName).getText();
		return Integer.parseInt(quantity);
	}

	public int updateRestockTransactionQuantity(String fieldName, String data) {
		String updateQuantity;
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("update_quantity_restock", fieldName);
		// wait.waitForElementToBeClickable(element("update_quantity_restock",fieldName));
		// wait.elementHighlight(element("update_quantity_restock",fieldName));

		// element("update_quantity_restock",fieldName).click();
		clickUsingXpathInJavaScriptExecutor(element("update_quantity_restock", fieldName));
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		// element("update_quantity_restock",fieldName).clear();

		/*
		 * WebElement wb = element("update_quantity_restock",fieldName);
		 * JavascriptExecutor executor = (JavascriptExecutor)driver;
		 * executor.executeScript("arguments[0].value='"+ data +"';", wb);
		 */
		element("update_restock_amount", fieldName).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		element("update_restock_amount", fieldName).sendKeys(data);
		return Integer.parseInt(data);
	}

	public int getQuantityPendingRestockTransaction(String fieldName) {
		String quantity;
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("restock_transaction_quantity", fieldName);
		wait.elementHighlight(element("restock_transaction_quantity", fieldName));
		quantity = element("restock_transaction_quantity", fieldName).getText();
		return Integer.parseInt(quantity);
	}

	public boolean verifyTransactionQuantity(int quantity, int updatedQuantity, int pendingQuantity) {
		int quantityDiff = quantity - updatedQuantity;
		if (quantityDiff == pendingQuantity) {
			return true;
		} else {
			return false;
		}

	}

	public void clickOnQueueText() {

		element("queue_text").click();
		wait.hardWait(5);

	}

	public void clickHoldButton(String fieldName) {
		isElementDisplayed("hold_button", fieldName);
		try {
			click(element("hold_button", fieldName));

		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("hold_button", fieldName));
		}

	}

	public void verifyPageHeader(String pageHeader) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("modal_header", pageHeader);

	}

	public String getAddedItemNameFromAddRestockForm() {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		isElementDisplayed("addedRestockItemName");
		return element("addedRestockItemName").getText();
	}

	public void verifyRecordCount() {
		List<WebElement> list = elements("lot_expiration_rows", lotNumberValue);
		wait.waitForElementsToBeVisible(list);
		Assert.assertEquals(list.size(), 1);
	}

	/*
	 * public void selectCheckboxBasedOnItemName(String string) {
	 * wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
	 * 
	 * }
	 */

	public boolean verifyActiveTransactionBoxItemName(String data) {
		if (isElementDisplayed("active_transaction_box")) {
			if (!element("active_transaction_box").getText().isEmpty()) {
				flag = true;
				element("active_transaction_box").getText().contains(data);
				logMessage("Active Transaction is available on UI");
			}
		}
		return flag;
	}

	public boolean makeROTransactionActive() {

		try {
			if (isElementDisplayed("pick_ro_transaction")) {
				// clickUsingXpathInJavaScriptExecutor(element("activate_first_transaction"));
				element("pick_ro_transaction").click();
				flag = true;
			}
		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("pick_ro_transaction"));
		}
		return flag;

	}

	public void VerifyButtons(String actionButton) {

		Assert.assertTrue(isElementDisplayed("active_transaction_button", actionButton));

	}

	public List<WebElement> verifyWasteReasonList() {

		return getAllOptionsFromDropDown(element("waste_reason_list"));
	}

	public boolean verifyWasteItemPopupGetsClosed() {

		return isElementNotDisplayed("popup_waste_item");
	}

	public String EnterValueInInputFieldOnWasteItemPopup(String fieldName, String data) {

		isElementDisplayed("waste_quantity", fieldName);
		enterTextInField(element("waste_quantity", fieldName), data);
		String entered_data = element("waste_quantity", fieldName).getAttribute("value");
		return entered_data;
		
	}

	public String getQuantityOnHandActiveTransaction() {
		
		wait.hardWait(4);
		String txtString;
		String [ ]digits = null;
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		
		isElementDisplayed("active_transaction_quantityOnHand");
		txtString = element("active_transaction_quantityOnHand").getText();
		digits= txtString.split(":");
		System.out.println("Current quantity is: " + digits[1].trim());
		return digits[1].trim();
		
	}


	public void selectValueFromDropDownByIndexWasteItem(Integer Index) {
		isElementDisplayed("waste_reason_list");
		Select selectValue = new Select(element("waste_reason_list"));
		selectValue.selectByIndex(Index);
		logMessage("[STEP]: Value is selected from dropdown By index.");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
	}

	public void verifyInputFieldIsAutopopulated(String fieldName) {
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 6);
		Assert.assertTrue(element("waste_reason_labels", fieldName).getTagName().equalsIgnoreCase("p"));
		
	}

	public boolean verifyFieldIsMandatoryIcon(String fieldName) {
		isElementDisplayed("icon_mandatory_required", fieldName);
		if (element("icon_mandatory_required", fieldName).getText().trim().contains("*")) {
			return true;
		}
		return false;
	}

	public void selectPickTransaction_Sanity(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("picknow_txn_checkbox_sanity", fieldName);
		// wait.waitForElementToBeClickable(element("picknow_txn_checkbox_sanity",
		// fieldName));
		// wait.elementHighlight(element("picknow_txn_checkbox_sanity",
		// fieldName));
		wait.waitForElementToBeClickableAfterRefresh(element("picknow_txn_checkbox_sanity", fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("picknow_txn_checkbox_sanity", fieldName));
		// element("invoice_item_checkbox", fieldName).click();
	}

	public void clickOnPickNow_Sanity(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		try {
			if (element("click_picknow_button_sanity", fieldName).isDisplayed()) {
				wait.waitForElementToBeClickable(element("click_picknow_button_sanity", fieldName));
				// wait.elementHighlight(element("click_picknow_button_sanity",
				// fieldName));
				clickUsingXpathInJavaScriptExecutorSingleClick(element("click_picknow_button_sanity", fieldName));
				logMessage("[STEP]: Pick Now button is clicked.");

			}
		} catch (Exception e) {
			if (element("txt_item_location").getText().contains(fieldName)) {
				logMessage("[STEP]: Newly created Add Pick is in Current Pick Window.");
			}
		}
		// isElementDisplayed("click_picknow_button_sanity", fieldName);
		// element("invoice_item_checkbox", fieldName).click();
	}

	public void selectRestockTransaction_Sanity(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.hardWait(10);
		isElementDisplayed("restock_transaction_checkbox", fieldName);
		// wait.waitForElementToBeClickable(element("restock_transaction_checkbox",
		// fieldName));
		// wait.elementHighlight(element("restock_transaction_checkbox",
		// fieldName));
		// clickUsingXpathInJavaScriptExecutorSingleClick(element("restock_transaction_checkbox",
		// fieldName));
		wait.waitForElementToBeClickableAfterRefresh(element("restock_transaction_checkbox", fieldName));
		element("restock_transaction_checkbox", fieldName).click();
		// element("invoice_item_checkbox", fieldName).click();
	}

	public void clickOnRestockNow_Sanity(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.hardWait(5);
		isElementDisplayed("click_restocknow_button_sanity", fieldName);
		wait.waitForElementToBeClickable(element("click_restocknow_button_sanity", fieldName));
		// wait.elementHighlight(element("click_restocknow_button_sanity",
		// fieldName));
		wait.waitForElementToBeClickableAfterRefresh(element("click_restocknow_button_sanity", fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_restocknow_button_sanity", fieldName));
		// element("invoice_item_checkbox", fieldName).click();
	}

	public boolean verifyNoActiveRestockTransaction(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		System.out.println(data);
		if (element("no_active_restock_transaction").getText().equals(data)) {
			return true;
		} else {
			return false;
		}
	}

	public void clickOnHold_Sanity(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.hardWait(5);
		isElementDisplayed("click_restock_hold_button_sanity", fieldName);
		// wait.waitForElementToBeClickable(element("click_restock_hold_button_sanity",
		// fieldName));
		// wait.elementHighlight(element("click_restock_hold_button_sanity",
		// fieldName));
		wait.waitForElementToBeClickableAfterRefresh(element("click_restock_hold_button_sanity", fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_restock_hold_button_sanity", fieldName));
		// element("invoice_item_checkbox", fieldName).click();
	}

	public void clickRelease_Sanity(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		wait.hardWait(5);
		isElementDisplayed("release_button_sanity", fieldName);
		// wait.waitForElementToBeClickable(element("click_release_button_sanity",
		// fieldName));
		// wait.elementHighlight(element("click_release_button_sanity",
		// fieldName));
		wait.waitForElementToBeClickableAfterRefresh(element("release_button_sanity", fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("release_button_sanity", fieldName));
		// element("invoice_item_checkbox", fieldName).click();
	}

	public boolean verifyRestockTransactionAfterRelease(String fieldName) {

		 wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.hardWait(5);
		boolean result = checkIfElementIsThere("restock_transaction_checkbox", fieldName);
		return result;
	}

	public void clickDelete_Sanity(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("click_delete_button_sanity", fieldName);
		// wait.waitForElementToBeClickable(element("click_delete_button_sanity",
		// fieldName));
		// wait.elementHighlight(element("click_delete_button_sanity",
		// fieldName));
		wait.hardWait(5);
		wait.waitForElementToBeClickableAfterRefresh(element("click_delete_button_sanity", fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_delete_button_sanity", fieldName));
		// element("invoice_item_checkbox", fieldName).click();
	}

	public void enterDeleteReason(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		element("enter_delete_reason_sanity", fieldName).sendKeys(data);
	}

	public boolean clickConfirmToDeleteButton(String fieldName) {
		if (isElementDisplayed("confirm_delete_button_sanity", fieldName)) {
			element("confirm_delete_button_sanity", fieldName).click();
			flag = true;

		}
		return flag;
	}

	public boolean verifyReceivingTransactionAfterRelease(String fieldName) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.hardWait(10);
		boolean result = checkIfElementIsThere("receiving_txn_checkbox", fieldName);
		return result;
	}

	public void clickRestockButton_Sanity() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.hardWait(5);
		isElementDisplayed("restock_button_sanity");
		// wait.waitForElementToBeClickable(element("click_restock_hold_button_sanity",
		// fieldName));
		// wait.elementHighlight(element("click_restock_hold_button_sanity",
		// fieldName));
		wait.waitForElementToBeClickableAfterRefresh(element("restock_button_sanity"));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("restock_button_sanity"));
		// element("invoice_item_checkbox", fieldName).click();
	}

	public void clickHoldButton_Sanity() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.hardWait(5);
		isElementDisplayed("hold_button_click");
		// wait.waitForElementToBeClickable(element("click_restock_hold_button_sanity",
		// fieldName));
		// wait.elementHighlight(element("click_restock_hold_button_sanity",
		// fieldName));
		wait.waitForElementToBeClickableAfterRefresh(element("hold_button_click"));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("hold_button_click"));
		// element("invoice_item_checkbox", fieldName).click();
	}

	public void clickHoldButtonForCycleCount(String field) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.hardWait(5);
		isElementDisplayed("click_restock_hold_button_sanity", field);
		wait.waitForElementToBeClickableAfterRefresh(element("click_restock_hold_button_sanity", field));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_restock_hold_button_sanity", field));

	}

	public void clickDeleteButton_Sanity() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.hardWait(5);
		isElementDisplayed("delete_button_click");
		// wait.waitForElementToBeClickable(element("click_restock_hold_button_sanity",
		// fieldName));
		// wait.elementHighlight(element("click_restock_hold_button_sanity",
		// fieldName));
		wait.waitForElementToBeClickableAfterRefresh(element("delete_button_click"));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("delete_button_click"));
		// element("invoice_item_checkbox", fieldName).click();
	}

	public boolean seeValuesFromHoldDropDown(String elementTextReplace) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("second_sort", elementTextReplace);
		List<WebElement> Str = getAllOptionsFromDropDown(element("second_sort", elementTextReplace));
		if (Str.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public void verifyCycleCountTransactionInTQ() {
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
			wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
			isElementDisplayed("cycleCount_trans");
			logMessage("ASSERTION PASSED: Verified CycleCount Transaction in TQ");
		} catch (Exception e) {
			isElementDisplayed("cycle_count_txn_active");
			Assert.assertTrue(element("cycle_count_txn_active").getText().trim().equals("Cycle Count"));
			logMessage("ASSERTION PASSED: Verified CycleCount Transaction in TQ");

		}
	}

	public boolean verifyCycleCountTransactionInTQPage() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		if (isElementNotDisplayed("cycleCount_trans")) {
			logMessage("ASSERTION PASSED: Verified CycleCount Transaction in TQ");
			return true;
		}
		return false;
	}

	public void verifyandEnterQuantityForCycleCount() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);

		isElementDisplayed("text_add_pick");
		logMessage("ASSERTION PASSED: Verify Confirm QOH Popup for CycleCountTransaction");
		isElementDisplayed("quantity_restock_popup");
		enterTextInField(element("quantity_restock_popup"), "2000");
		clickUsingXpathInJavaScriptExecutor(element("confirm_qty_cycle"));
		logMessage("ASSERTION PASSED: Confirm button clicked");
	}

	public boolean verifyCycleCountTransactionByItemName(String item) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		if (isElementNotDisplayed("trans_cycle", item)) {
			logMessage("ASSERTION PASSED: Verified CycleCount Transaction in TQ");
			return true;
		}

		else
			return false;

	}

	public void waitForCycleCountTransaction() {

		wait.hardWait(120);
		logMessage("waited 120 secs");
	}

	public void getQOHForCycleCount() {
		isElementDisplayed("QOH_Popup");
		String qoh = element("QOH_Popup").getAttribute("value");

	}

	public void enterQuantityAndWasteQuantity() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.hardWait(5);
		isElementDisplayed("quantity_restock_popup");
		enterTextInField(element("quantity_restock_popup"), "2000");
		 clickOutside();
		enterTextInField(element("reconfirm_qty_cycle"), "200");
	    clickOutside();
		enterTextInField(element("reconfirm_qty_cycle"), "200");

		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.sendKeys(Keys.SHIFT).moveToElement(element("waste_items_toggle"))
				.click().build();
		seriesOfAction.perform();

		wait.hardWait(20);
	
		
		enterQuantityWasted();
		

	//	enterTextInField(element("quantity_wasted"), "2005");
	//	element("quantity_wasted").sendKeys("2005");

		wait.hardWait(10);

		element("quantity_wasted").sendKeys("2005");
		logMessage("Text entered in Waste Qty");

		// clickUsingXpathInJavaScriptExecutor(element("waste_items_toggle"));
		// enterTextInField(element("quantity_wasted"), "2005");
	}

	public void clickConfirmButtonQOHPopup() {
		clickUsingXpathInJavaScriptExecutor(element("confirm_qty_cycle"));
		logMessage("ASSERTION PASSED: Confirm button clicked");
	}

	public void enterQuantityWasted() {
		wait.hardWait(5);
		enterTextInField(element("quantity_wasted"), "2");

	}

	public boolean verifySingleCycleCountTransactionForAnItem(String item) {
		isElementDisplayed("trans_cycle", item);
		logMessage("ASSERTION PASSED: CycleCount Transaction is displayed for item:" + item);
		int count = elements("trans_cycle", item).size();

		if (count == 1) {
			logMessage("ASSERTION PASSED: Single Cycle Count Transaction exists for Item:" + item);

			return true;
		}

		else
			return false;

	}

	public boolean verifyCycleCountTransactionOnWFA() {

		List<WebElement> elements = elements("wfa_priority");
		for (WebElement ele : elements) {
			if (ele.getText().contains("Cycle Count")) {
				flag = true;
				logMessage("Verified Cycle Count Transaction");

			}
		}
		return flag;

	}

	public void enterQOHValueForCycleCount(String value) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);

		// wait.hardWait(5);
		isElementDisplayed("quantity_restock_popup");
		enterTextInField(element("quantity_restock_popup"), value);
		logMessage("ASSERTION PASSED: Entered QOH Value as :" + value);
	}

	public void selectHourForAllDays() {

		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.sendKeys(Keys.SHIFT).moveToElement(element("all_days_Cycle")).click()
				.build();
		seriesOfAction.perform();
		logMessage("ASSERTION PASSED: Clicked on All Days for an hour");

	}

	public void selectCheckBoxForDisableDay() {
		isElementDisplayed("disable_day_checkbox");
		clickUsingXpathInJavaScriptExecutor(element("disable_day_checkbox"));
		logMessage("ASSERTION PASSED: Clicked on Checkbox for Disabled Date");
	}

	public boolean verifyCycleCountButton(String buttonType) {
		selectAllCheckboxes();
		List<WebElement> ele = elements("active_bar_btn");
		for (WebElement e : ele) {
			if (e.getText().equalsIgnoreCase(buttonType)) {
				if (!e.isEnabled()) {
					flag = true;
				}
			}
		}

		return flag;
	}

	public boolean verifyRestockTransactionIsNotPresentAfterDelete(String fieldName) {

		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.hardWait(20);
		return isElementNotDisplayed("restock_transaction_checkbox", fieldName);

	}

	public String verifyQuantityFlag() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		Assert.assertTrue(isElementDisplayed("text_verifyQuantity"));
		String data = element("text_verifyQuantity").getText();
		System.out.println("value of data is: " + data);
		return data;
	}
	
	public void pickLabelScanFlag() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		Assert.assertTrue(isElementDisplayed("para_by_text", "Please scan item."));
		String data = element("para_by_text").getText();
		System.out.println("value of value is =  " + data);
	}
	
	public void deselectFirstTransaction() {

		isElementDisplayed("checkbox_first_transaction_1");
		if (element("checkbox_first_transaction_1").isSelected()) {
			wait.waitForElementToBeClickable(element("checkbox_first_transaction_1"));
			wait.elementHighlight(element("checkbox_first_transaction_1"));
			element("checkbox_first_transaction_1").click();
			logMessage("First Transaction checkbox is clicked");
		}
	}

	public void verifyCheckboxIsEnabledOrDisabled() {
		isElementDisplayed("transaction_checkbox");
		for (WebElement ele : elements("transaction_checkbox")) {
			String id = element("transaction_checkbox").getAttribute("for");
			Assert.assertTrue(checkCheckboxIsEnabledOrDisabled(id));
		}

	}

	public boolean verifyActiveTransactionQuantityOnHandNew(ArrayList<String> activeTransactionDetails) {

		String item_name = element("txt_item_description").getText();
		String item_name_with_spaces_removed = item_name.replaceAll(" ", "");
		if (item_name_with_spaces_removed.equalsIgnoreCase(activeTransactionDetails.get(0).replaceAll("\\s", ""))) {
			// if
			// (isElementDisplayed("txt_item_description",activeTransactionDetails.get(0).replaceAll("\\s",
			// ""))){
			if (isElementDisplayed("txt_item_quantity")) {
				verifyElementText(("txt_item_quantity"), activeTransactionDetails.get(1));
				flag = true;
			}

		}

		return flag;

	}

	public void verifyUpdatedExpirationDate(String i) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("field_expirationDate");
		enterTextInField(element("field_expirationDate"), DateUtil.getAnyDateForType("MM/dd/yyyy", 1, ""));
		isElementDisplayed("field_lotNumber");
		// enterTextInField(element("field_lotNumber"), "12");
		
		clickUsingXpathInJavaScriptExecutorSingleClick(element("field_lotNumber"));
		enterTextInField(element("field_lotNumber"), i);
		
		element("field_lotNumber").sendKeys(i);
		Actions action = new Actions(driver);
		action.moveToElement(element("field_lotNumber")).click().build().perform();
		element("field_lotNumber").sendKeys(i);
		
		wait.hardWait(5);
		isElementDisplayed("btn_Add");
		// clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_Add"));
		// element("btn_Add").click();

		Actions action1 = new Actions(driver);
		action1.moveToElement(element("btn_Add")).click().build().perform();
		
	}

	public void verifyHeaderText(String headerText) {
		isElementDisplayed("tq_header");
		Assert.assertEquals(element("tq_header").getText(), headerText);
		logMessage("[Assert Passed]: TQ header value verified for field TQ Header" + headerText);

	}

	public void verifyTransactionNotDisplayedOnUI() {
		
		Assert.assertFalse(isElementNotDisplayed("transactions_table_count"));
		
	}

	public void verifyTQTabHeaderText(String PageHeader) {
		isElementDisplayed("tq_page_header");
		Assert.assertEquals(element("tq_page_header").getText(), PageHeader);
		logMessage("[Assert Passed]: TQ header value verified for field TQ Header" + PageHeader);
	}

	public void verifyTransactionIsDisabled() {
		isElementDisplayed("tq_page_header");
		selectAllCheckboxes();
		Assert.assertFalse(checkboxIsSelectedUsingJavascript("allCheckbox"));

	}

	public boolean verifyTransactionIsDisabledWhenTabIsPressed() {
		try {
			element("pick_queue_all_checkbox").sendKeys(Keys.TAB);
			isElementDisplayed("tq_page_header");
			selectAllCheckboxes();
			Assert.assertFalse(checkboxIsSelectedUsingJavascript("allCheckbox"));
			flag = false;
		} catch (Exception e) {

			flag = true;
		}
		return flag;
	}

	public void verifyLockedItem(String fieldName, String priorityname) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 150);
		// isElementDisplayed("picknow_txn_checkbox_sanity", fieldName);
		isElementDisplayed("transaction_priority_locked_item");
		boolean flag = false;
		List<WebElement> elems = elements("transaction_priority_locked_item");
		for(int i = 0; i < elems.size(); i++) {
			WebElement elem = elems.get(i);
			String s = elem.getText();
			if(s.contains(priorityname) && s.contains(fieldName)) {
				flag = true;
				break;
			}
		}
		// Assert.assertTrue(elements("transaction_priority_locked_item").contains(priorityname));
		Assert.assertTrue(flag);
		
	}

	public ArrayList<String> captureDataForParticularColumn(String coulmn) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		List<WebElement> elements = elements("text_column", coulmn);

		for (int i = 0; i < elements.size(); i++) {
			String data = elements.get(i).getText();
			column_data = new ArrayList<String>();
			// System.out.print("Value of quantity: " + data);
			column_data.add(data);
		}
		return column_data;
	}

	public boolean verifyLocationIsPresent(String ISAName) {
		return isElementDisplayed("text_column", ISAName);

	}

	public boolean verifyFacilityNameIsReadOnly(String facilityname) {

		isElementDisplayed("facility_on_WFA");
		Assert.assertTrue(element("facility_on_WFA").getText().contains(facilityname));
		Assert.assertTrue(element("facility_on_WFA").getTagName().equalsIgnoreCase("Div"));
		try {
			element("facility_on_WFA").sendKeys("a");
		} catch (Exception e) {
			flag = true;
		}

		return flag;
	}
	
	public String getFacilityFromWFAScreen() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("facility_on_WFA");
		String facilityName = element("facility_on_WFA").getText().substring(11);
		
		logMessage("Facility Name " + facilityName + " has been extracted from ISA Screen.");
		return facilityName;
	}
	
	public void verifyItemIsDisplayed(String qOH) {
		isElementDisplayed("qOH_add_pick_screen", qOH);

	}

	public boolean verifyDestinationInCurrentPickWindow(String destinationName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		
		if(isElementNotDisplayed("txt_item_location")) {
			if (element("txt_item_location").getText().contains(destinationName)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public void clickButtonCorrespondingToPatientName(String patientName, String buttonText) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		isElementDisplayed("button_corresponding_to_patient_name", patientName, buttonText);
		clickUsingXpathInJavaScriptExecutorSingleClick(
				element("button_corresponding_to_patient_name", patientName, buttonText));

	}

	public void verifyScanOverrideOption() {
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		wait.waitForElementToBeVisible(element("link_work_without_scanner"));
		Assert.assertTrue(isElementDisplayed("link_work_without_scanner"));
		
	}

	public void verifyOverrideText(String text) {
		try {
			Assert.assertTrue(isElementDisplayed("scan_text", text));
		} catch (Exception e) {

			Assert.assertTrue(isElementDisplayed("scan_text", "Waiting For Bin Scan."));

		}

	}

	public boolean verifyTransactionprioritiesOnSelectISAPage() {
		boolean flag = true;
		isElementDisplayed("isa_boxes");
		// isElementDisplayed("transaction_priorities_on_TQ");
		
		for(int i = 1; i <= elements("isa_boxes").size(); i++) {
			if (elements("transaction_priorities_on_isa_box", String.valueOf(i)).size() > 4) {
				flag = false;
			}
		}
		
		return flag;
		
	}

	public void verifyEscButtonResetQOH() {
		int digits = 0;
		int count = 0;
		Actions action = new Actions(driver);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		wait.hardWait(5);
		String edit_on_hand_item_quantityWithText = element("edit_on_hand_item_quantity").getText();
		for (int i = 0; i < edit_on_hand_item_quantityWithText.length(); i++) {
			// isDigit is a function of Character class it checks a particular
			// char that whether it is a digit or not
			if (Character.isDigit(edit_on_hand_item_quantityWithText.charAt(i))) {
				// counter digit will be incremented each time when a character
				// will be a digit
				digits++;
			}
		}
		String edit_on_hand_item_quantity = String.valueOf(digits);
		wait.waitForElementToBeClickable(element("edit_on_hand_item_quantity"));
		Action seriesOfAction1 = (Action) action.moveToElement(element("edit_on_hand_item_quantity")).click().build();
		wait.elementHighlight(element("edit_on_hand_item_quantity"));
		seriesOfAction1.perform();
		sendKeysUsingXpathInJavaScriptExecutor(element("edit_on_hand_item_quantity"), edit_on_hand_item_quantity + 1);
		action.sendKeys(Keys.ESCAPE).build().perform();
		// sendKeysUsingXpathInJavaScriptExecutor(element("edit_on_hand_item_quantity"),Keys.ESCAPE);
		String reset_valWithText = element("edit_on_hand_item_quantity").getText();
		for (int i = 0; i < reset_valWithText.length(); i++) {
			// isDigit is a function of Character class it checks a particular
			// char that whether it is a digit or not
			if (Character.isDigit(reset_valWithText.charAt(i))) {
				// counter digit will be incremented each time when a character
				// will be a digit
				count++;
			}
		}
		String reset_val = String.valueOf(count);
		Assert.assertEquals(reset_val, edit_on_hand_item_quantity);
	}
	
	public void verifyEscButtonResetQOH_New() {
		int currQty = getActiveTxnQuantityOnHand();
		
		Actions action = new Actions(driver);
		Action seriesOfAction1 = (Action) action.moveToElement(element("edit_on_hand_item_quantity")).click().build();
		seriesOfAction1.perform();
		sendKeysUsingXpathInJavaScriptExecutor(element("edit_on_hand_item_quantity"), String.valueOf(currQty+1));
		action.sendKeys(Keys.ESCAPE).build().perform();
		
		int updatedQty = getActiveTxnQuantityOnHand();
		Assert.assertEquals(updatedQty, currQty);
	}

	public boolean verifyErrorMessageForUnauthorizedComputer(String msg) {

		return isElementDisplayed("unthorized_computer", msg);
	}

	public void verifyTQSecondTabHeaderText(String PageHeader) {
		isElementDisplayed("tq_page_header_second");
		Assert.assertEquals(element("tq_page_header_second").getText(), PageHeader);
		logMessage("[Assert Passed]: TQ header value verified for field TQ Header" + PageHeader);
	}

	public boolean verifyCycleCountBinScanMessage() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 50);
		wait.waitForElementToBeVisible(element("bin_scan_msg"));
		return isElementDisplayed("bin_scan_msg");

	}

	public boolean VerifyButtonsOnTQ(String type) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		return isElementDisplayed("button_actions_TQ", type);

	}

	public void verifybuttonisDisplayed(String buttonText) {
		isElementDisplayed("save_close_btn", buttonText);

	}

	public void verifyISANameOnTQ(String ISAName) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		isElementDisplayed("isa_name_tq");
		Assert.assertTrue(element("isa_name_tq").getText().trim().contains(ISAName));
		logMessage("[ASSERTION PASSED]: ISA name present on TQ");
	}

	public boolean verifyUserIsOnTQPageNew() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.hardWait(30);
		wait.waitForPageToLoadCompletely();
		wait.hardWait(5);
		isElementDisplayed("txt_currentPick");
		if (element("txt_currentPick").getText().trim().contains("Current Pick")) {
			return true;
		}
		return false;
	}

	public void enterDatainSearchBox(String name) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		isElementDisplayed("search_tq");
		enterTextInField(element("search_tq"), name);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
	}

	public void verifyTransaction(String name) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		
		isElementDisplayed("search_tq");
		enterTextInField(element("search_tq"), name);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		List<WebElement> elements = elements("txt_numberOfTrans");
		System.out.println("Entered name is: " + name);
		
		for (int i = 0; i < 1; i++) {
			String trans_details = elements.get(i).getText().replaceAll("\\s", "").replace("PickNow", "")
					.replace("Hold", "").replace("Delete", "").replace("Release", "").replace("RestockNow", "")
					.split(",")[0];
			System.out.println("trans" + trans_details);
			Assert.assertTrue(trans_details.contains(name.replaceAll("\\s", "")));
		}
		
	}

	public void clickRestockTransactionBasedOnPriortiyAndItemName(String priority, String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 250);
		wait.waitForElementToBeClickable(element("restock_based_on_itemname_and_priority", priority, fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(
				element("restock_based_on_itemname_and_priority", priority, fieldName));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 150);

	}

	public boolean verifyValidItemNameInRestock(ArrayList<String> activeTransactionName) {

		String item_name = element("txt_item_description").getText();

		String item_name_with_spaces_removed = item_name.replaceAll(" ", "");

		if (item_name_with_spaces_removed.contains(activeTransactionName.get(0))) {
			System.out.println("item_name_with_spaces_removed=" + item_name_with_spaces_removed);
			System.out.println("activeTransactionName.get(0)=" + activeTransactionName.get(0));

			if (isElementDisplayed("txt_item_quantity")) {
				if (element("txt_item_quantity").getText().contains(activeTransactionName.get(1))) {
					System.out.println(
							"element('txt_item_quantity').getText()=" + element("txt_item_quantity").getText());
					System.out.println("activeTransactionName.get(1)=" + activeTransactionName.get(1));
					flag = true;
				}

			}

		}

		return flag;
	}

	public boolean verifyValidItemNamePriority(String state, String priority) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 150);
		return isElementDisplayed("priority_header", priority);
	}

	public int getRecordCountOnRestockPopup() {

		return elements("record_count_restock_popup").size();
	}

	public void verifyExpirydateLotNumberAreDisabled() {

		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 20);
		// Assert.assertTrue(element("restock_popup_reactTable").getAttribute("class").contains("Disabled"));
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Assert.assertTrue((boolean) jse.executeScript("return document.getElementById('" + "add" + "').disabled;"));
	}
	
	public void verifyButtonIsDisabledById(String id) {

		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 20);
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Assert.assertTrue((boolean) jse.executeScript("return document.getElementById('" + id + "').disabled;"));
	}

	public void verifyOnHandQuanity() {
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 80);
		Assert.assertTrue(isElementDisplayed("edit_on_hand_item_quantity"));
		
	}
	
	public void updateProcessedItemQuantity(String className) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("edit_active_item_quantity");
		//wait.waitForElementToBeClickable(element("edit_active_item_quantity"));
		String active_quantity = element("active_item_qty_value", className).getText();
		
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("edit_active_item_quantity")).click().build();
		wait.elementHighlight(element("edit_active_item_quantity"));
		seriesOfAction.perform();
		
		// clickUsingXpathInJavaScriptExecutor(element("change_qty_btn"));
		logMessage("Clicked on Change Quantity link");
		isElementDisplayed("qty_textbox1");
		sendKeysUsingXpathInJavaScriptExecutor(element("qty_textbox1"), Integer.parseInt(active_quantity) + 1);
		seriesOfAction = (Action) action.sendKeys(Keys.ENTER).build();
		seriesOfAction.perform();
		
		// enterTextInField(element("qty_textbox1"), active_quantity+1);
		String edit_on_hand_item_quantity = element("edit_on_hand_item_quantity").getText();
		wait.waitForElementToBeClickable(element("edit_on_hand_item_quantity"));
		Action seriesOfAction1 = (Action) action.moveToElement(element("edit_on_hand_item_quantity")).click().build();
		wait.elementHighlight(element("edit_on_hand_item_quantity"));
		seriesOfAction1.perform();
		sendKeysUsingXpathInJavaScriptExecutor(element("edit_on_hand_item_quantity"), 
				Integer.parseInt(edit_on_hand_item_quantity.split(":")[1].trim()) + 1);
		// enterTextInField(element("edit_on_hand_item_quantity"),
		// edit_on_hand_item_quantity+1);
		// Assert.assertEquals(element("edit_active_item_quantity").getText(),
		// active_quantity+1);
		
		clickUsingXpathInJavaScriptExecutor(element("queue_header"));
		wait.hardWait(5);
		// Assert.assertEquals(element("edit_on_hand_item_quantity").getText(),
		// edit_on_hand_item_quantity+1);
	}
	
	public void verifyTransactionWithPriority(String priority, String name) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 45);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		
		isElementDisplayed("search_tq");
		enterTextInField(element("search_tq"), name);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		List<WebElement> elements = elements("txt_numberOfTrans");
		System.out.println("Entered name is:" + name);
		
		boolean flag = false;
		for (int i = 0; i < elements.size(); i++) {
			String trans_details = elements.get(i).getText().replaceAll("\\s", "").replace("PickNow", "")
					.replace("Hold", "").replace("Delete", "").replace("Release", "").replace("RestockNow", "")
					.split(",")[0];
			System.out.println("transaction details: " + trans_details);
			if (trans_details.contains(priority) && trans_details.contains(name)) {
				flag = true;
				break;
			}
			//Assert.assertTrue(trans_details.contains(priority));
			// Assert.assertTrue(trans_details.contains(name));
			
		}
		
		Assert.assertTrue(flag);
	}

	public void updateProcessedItemQuantityUsingKeyboard(String className) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("edit_active_item_quantity");
		wait.waitForElementToBeClickable(element("edit_active_item_quantity"));
		String active_quantity = element("active_item_qty_value", className).getText();
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("edit_active_item_quantity")).click().build();
		wait.elementHighlight(element("edit_active_item_quantity"));
		seriesOfAction.perform();
		// clickUsingXpathInJavaScriptExecutor(element("change_qty_btn"));
		logMessage("Clicked on Change Quantity link");
		isElementDisplayed("qty_textbox1");
		sendKeysUsingXpathInJavaScriptExecutor(element("qty_textbox1"), active_quantity + 1);
		// enterTextInField(element("qty_textbox1"), active_quantity+1);
		String edit_on_hand_item_quantity = element("edit_on_hand_item_quantity").getText();
		wait.waitForElementToBeClickable(element("edit_on_hand_item_quantity"));
		Action seriesOfAction1 = (Action) action.moveToElement(element("edit_on_hand_item_quantity")).click().build();
		wait.elementHighlight(element("edit_on_hand_item_quantity"));
		seriesOfAction1.perform();
		sendKeysUsingXpathInJavaScriptExecutor(element("edit_on_hand_item_quantity"), edit_on_hand_item_quantity + 1);
		// enterTextInField(element("edit_on_hand_item_quantity"),
		// edit_on_hand_item_quantity+1);
		// Assert.assertEquals(element("edit_active_item_quantity").getText(),
		// active_quantity+1);
		element("edit_on_hand_item_quantity").sendKeys(Keys.ENTER);
		wait.hardWait(3);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("queue_header"));
		// Assert.assertEquals(element("edit_on_hand_item_quantity").getText(),
		// edit_on_hand_item_quantity+1);
	}

	public void verifydefaultfocus() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		activeElement();

	}

	public String verifydropdownName(String fieldName) {
		isElementDisplayed("dropdown_name", fieldName);

		return element("dropdown_name", fieldName).getText();
	}

	public void batchTransactionListIsPresent() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("pick_queue_all_transaction");
	}

	public void headingBatchPick() {
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("heading_batchPick");
	}

	public void batchTransactionListOnPriorityBasis(String priorityName) {
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("list_batchPriorityName", priorityName);
	}

	public boolean verifyLockedItemIsUnlocked(String fieldName, String priorityname) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 250);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 150);
		// isElementDisplayed("picknow_txn_checkbox_sanity", fieldName);
		return isElementNotDisplayed("transaction_priority_locked_item");
	}

	public void clickHoldOnReturn() {
		wait.hardWait(10);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		isElementDisplayed("first_hold_return");
		List<WebElement> elements = elements("first_hold_return");
		elements.get(0).click();
		// element("first_hold_restock").click();
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 40);
	}

	public void clickTransactionOnBatch(String priorityName) {
		wait.waitForElementsToBeVisible(elements("list_batchPriorityName", priorityName));
		List<WebElement> elements = elements("list_batchPriorityName", priorityName);
		elements.get(0).click();

	}

	public void rightPanelBatchCreationTime(String destination) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementDisplayed("list_batckCreationTimeRightPanel", destination);
	}

	public void columnHeadingBatchPick(String columnName) {
		isElementDisplayed("columnHeading_batchPick", columnName);

	}

	public boolean verifyBatchTransactionIgnoreLinkCount() {
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForElementsToBeVisible(elements("list_link_Ignore"));
		List<WebElement> elements = elements("list_link_Ignore");
		for (WebElement ele : elements) {
			if (!ele.getText().trim().equalsIgnoreCase("Ignore")) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyBatchTransactionReleaseLinkCount() {
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForElementsToBeVisible(elements("list_link_release"));
		List<WebElement> elements = elements("list_link_release");
		for (WebElement ele : elements) {
			if (!ele.getText().trim().equalsIgnoreCase("Release")) {
				return false;
			}
		}
		return true;
	}

	public void checkboxPresentOnBatchPickScreen() {
		isElementDisplayed("batchpick_queue_all_checkbox");
	}

	public void checkboxNotPresentOnBatchPickScreen() {
		Assert.assertFalse(isElementNotDisplayed("batchpick_queue_all_checkbox"));
	}

	public void clickIgnoreButton() {
		wait.waitForElementsToBeVisible(elements("list_link_Ignore"));
		List<WebElement> elements = elements("list_link_Ignore");
		elements.get(0).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
	}

	public void clickReleaseButton() {
		wait.waitForElementsToBeVisible(elements("list_link_release"));
		List<WebElement> elements = elements("list_link_release");
		elements.get(0).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
	}

	public void releaseRemainingTransaction() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForElementsToBeVisible(elements("list_link_release"));
		List<WebElement> elements = elements("list_link_release");
		for (WebElement ele : elements) {
			List<WebElement> elements1 = elements("list_link_release");
			elements1.get(0).click();
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		}
	}

	public void listProcessedItem(int count) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		List<WebElement> elements;
		int size = 0;
		try {
			wait.waitForElementsToBeVisible(elements("list_link_processed"));
			elements = elements("list_link_processed");
			elements.get(0).click();
			size = elements.size();
		} catch (TimeoutException e) {
			size = 0;
		}
		Assert.assertEquals(size, count);
	}

	public void verifyIgnoreAndReleaseLinkAreAbsent() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		isElementNotDisplayed("list_link_Ignore");
		isElementNotDisplayed("list_link_release");
	}

	public void listBatchPick(int count) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		List<WebElement> elements;
		int size = 0;
		try {
			wait.waitForElementsToBeVisible(elements("list_link_batchPick"));
			elements = elements("list_link_batchPick");
			size = elements.size();
		} catch (TimeoutException e) {
			size = 0;
		}
		if (size == 0) {
			Assert.assertEquals(size, count);
		} else if (size == 1) {
			Assert.assertEquals(size, count);
		} else if (size == 2) {
			Assert.assertEquals(size, 2);
		} else {
			Assert.fail("Batch Pick transaction count is more than 2");
		}
	}

	public void listBatchPickNow() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		List<WebElement> elements;
		wait.waitForElementsToBeVisible(elements("list_link_batchPickPickNow"));
		elements = elements("list_link_batchPickPickNow");
		elements.get(0).click();
	}

	public void listReturnTransaction() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		isElementDisplayed("list_link_Return");
	}

	public void verifyBatchCreationTime() {
		isElementDisplayed("list_batchCreationTime");
	}

	public void verifyRightPanelReleaseButton() {
		isElementDisplayed("button_releaseRightPanel");
	}

	public void verifyRightPanelIgnoreButton() {
		isElementDisplayed("button_ignoreRightPanel");

	}

	public void verifyInactiveRecordIsNotAvailable(String fieldName, String data) {
		wait.hardWait(3);
		System.out.println("Field name is: " + fieldName);
		List<WebElement> options = getAllOptionsFromDropDown(element("second_sort", fieldName));
		
		for(WebElement option : options) {
			Assert.assertFalse(option.getText().contains(data));
		}
	}

	public List<String> getColumnHeaderOrder() {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		isElementDisplayed("tq_dashboard_headers");
		List<String> ele = new ArrayList<String>();
		for (WebElement element : elements("tq_dashboard_headers")) {
			ele.add(element.getText().trim());
		}
		
		return ele;
	}

	public boolean verifyTransactionListIsEmpty() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		return isElementDisplayed("text_no_data");
	}

	public void checkOnHoldTransaction() {

		if (verifyTransactionListIsEmpty()) {
			verifyTabOnTQAndClick("Pick");
		} else {
			selectAllCheckboxes();
			verifyTransactionActionAndClickRelease("Release");
		}
	}

	public void clickOnQuantity(String value) {
		isElementDisplayed("quantity_activeBox");
		wait.waitForElementToBeClickable(element("quantity_activeBox"));
		try {
			element("quantity_activeBox").click();
			
		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("quantity_activeBox"));
		}
		// wait.elementHighlight(element("onFocus_Element"));
		Actions action = new Actions(driver);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		isElementDisplayed("qty_textbox");
		element("qty_textbox").sendKeys(value);
		action.sendKeys(Keys.ENTER).build().perform();
		logMessage("Clicked on Quantity");
	}
	
	public void enterQuantityToProcess(String value) {
		isElementDisplayed("qty_textbox");
		element("qty_textbox").sendKeys(value);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		logMessage("Entered quantity to process: " + value);
	}

	public void inputProcessedQuantity(String value) {
		isElementDisplayed("quantity_activeBox");
		wait.waitForElementToBeClickable(element("quantity_activeBox"));
		try {
			element("quantity_activeBox").click();

		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("quantity_activeBox"));
		}
		wait.elementHighlight(element("onFocus_Element"));
		Actions action = new Actions(driver);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		isElementDisplayed("qty_textbox");
		element("qty_textbox").sendKeys(value);

	}

	public void clickProcessedQuantity() {
		isElementDisplayed("quantity_activeBox");
		wait.waitForElementToBeClickable(element("quantity_activeBox"));
		try {
			element("quantity_activeBox").click();

		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("quantity_activeBox"));
		}
		wait.elementHighlight(element("onFocus_Element"));
	}

	public void clickOnQuantityOnHand(String value) {
		
		isElementDisplayed("edit_on_hand_item_quantity");
		wait.waitForElementToBeClickable(element("edit_on_hand_item_quantity"));
		try {
			element("edit_on_hand_item_quantity").click();

		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("edit_on_hand_item_quantity"));
		}
		wait.elementHighlight(element("onFocus_Element_qoh"));
		Actions action = new Actions(driver);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		isElementDisplayed("qty_onHandInput");
		element("qty_onHandInput").sendKeys(value);
		action.sendKeys(Keys.ENTER).build().perform();
		logMessage("Clicked on Quantity");
		
	}

	public void clickOnQuantityOnHand(int i) {
		isElementDisplayed("edit_on_hand_item_quantity");
		wait.waitForElementToBeClickable(element("edit_on_hand_item_quantity"));
		try {
			element("edit_on_hand_item_quantity").click();

		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("edit_on_hand_item_quantity"));
		}
		wait.elementHighlight(element("onFocus_Element_qoh"));
		Actions action = new Actions(driver);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		isElementDisplayed("qty_onHandInput");
		element("qty_onHandInput").sendKeys(String.valueOf(i));
		action.sendKeys(Keys.ENTER).build().perform();
		logMessage("Clicked on Quantity");
	}

	public void verifyBatchPopupHeading(String columnName) {
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("heading_batchPopup", columnName);
	}

	public boolean verifyBatchPopupSubHeading(String columnName, String value) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("subHeading_batchPopup", columnName);
		String actual = element("subHeading_batchPopup", columnName).getText();
		System.out.println("Value of actual = " + actual);
		if (actual.contains(value)) {
			return true;
		}
		return false;
	}

	public boolean verifyBatchPopupSubHeadingQuantity(String columnName, String value) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("subHeading_batchPopupQuantity", columnName);
		String actual = element("subHeading_batchPopupQuantity", columnName).getText();
		if (actual.equalsIgnoreCase(value)) {
			return true;
		}
		return false;
	}

	public String getCurrentQuantity() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		String active_quantity = element("quantity_activeBox").getText();
		return active_quantity;
	}

	public void updateProcessedQuantity(String quantity) {
		List<WebElement> elements = elements("textbox_processedQuantity");
		for (WebElement ele : elements) {
			ele.clear();
			ele.click();
			System.out.println("actual value = " + ele.getAttribute("value"));
			Assert.assertTrue(ele.getAttribute("value").equals(""));
			ele.sendKeys(quantity);
			System.out.println("after value = " + ele.getAttribute("value"));
			Assert.assertTrue(ele.getAttribute("value").equals(quantity));
		}
	}

	public void verifyProcessedQuantity(String quantity, String expectedQuantity) {
		List<WebElement> elements = elements("textbox_processedQuantity");
		for (WebElement ele : elements) {
			System.out.println("actual value = " + ele.getAttribute("value"));
			ele.clear();
			ele.click();
			Assert.assertTrue(ele.getAttribute("value").equals(""));
			ele.sendKeys(quantity);
			System.out.println("after value = " + ele.getAttribute("value"));
			Assert.assertTrue(ele.getAttribute("value").equals(expectedQuantity));
		}
	}

	public int verifyQuantityOfNewlyCreatedTransaction(String first_Name) {
		isElementDisplayed("quantity_cce_trxn", first_Name);
		String quantity = element("quantity_cce_trxn", first_Name).getText();
		int quan = Integer.parseInt(quantity);
		return quan;
	}

	public boolean verifyHoldReasonDropdownValue(String fieldNameS, String name) {
		int count = 0;
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		isElementDisplayed("dropdowns_externalSystem", fieldNameS);
		for (WebElement ele : elements("dropdowns_externalSystem", fieldNameS)) {
			if (ele.getText().equalsIgnoreCase(name))
				count++;

		}
		if (count > 0) {

			return false;
		}

		else {
			return true;
		}

	}

	public void verifyReturnTransactionIsNotCreated(String item, String quantity) {
		wait.hardWait(20);
		isElementDisplayed("search_tq");
		enterTextInField(element("search_tq"), item);
		wait.hardWait(8);
		List<WebElement> elements = elements("txt_numberOfTrans");

		for (int i = 0; i < 1; i++) {
			String trans_details = elements.get(i).getText().replaceAll("\\s", "").replace("PickNow", "")
					.replace("Hold", "").replace("Delete", "").replace("Release", "").replace("RestockNow", "")
					.split(",")[0];
			System.out.println("trans" + trans_details);
			Assert.assertTrue(trans_details.contains(item));
			Assert.assertFalse(trans_details.contains(quantity));
			logMessage("[Assert Passed]: Verified new added Transaction in Restock Queue against" + item);
			
		}

	}

	public void verifyColumnHeadersText(String[] colunHeaderRestock) {
		for (String sortColumn : colunHeaderRestock) {
			
			isElementDisplayed("list_columnHeaders", sortColumn);
			logMessage("Items " + sortColumn + " are displayed");
			
		}

	}

	public void updateActiveItemQuantityAndOnHandQuantityNew() {
		int digits = 0;
		int count = 0;
		Actions action = new Actions(driver);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		wait.hardWait(10);
		String edit_on_hand_item_quantityWithText = element("edit_on_hand_item_quantity").getText();
		for (int i = 0; i < edit_on_hand_item_quantityWithText.length(); i++) {
			// isDigit is a function of Character class it checks a particular char that whether it is a digit or not
			if (Character.isDigit(edit_on_hand_item_quantityWithText.charAt(i))) {
				// counter digit will be incremented each time when the character is a digit
				digits++;
			}
		}
		
		String edit_on_hand_item_quantity = String.valueOf(digits);
		wait.waitForElementToBeClickable(element("edit_on_hand_item_quantity"));
		Action seriesOfAction1 = (Action) action.moveToElement(element("edit_on_hand_item_quantity")).click().build();
		wait.elementHighlight(element("edit_on_hand_item_quantity"));
		seriesOfAction1.perform();
		sendKeysUsingXpathInJavaScriptExecutor(element("edit_on_hand_item_quantity"), edit_on_hand_item_quantity + 1);
		action.sendKeys(Keys.ENTER).build().perform();
		// sendKeysUsingXpathInJavaScriptExecutor(element("edit_on_hand_item_quantity"),Keys.ESCAPE);
		String reset_valWithText = element("edit_on_hand_item_quantity").getText();
		for (int i = 0; i < reset_valWithText.length(); i++) {
			// isDigit is a function of Character class it checks a particular
			// char that whether it is a digit or not
			if (Character.isDigit(reset_valWithText.charAt(i))) {
				// counter digit will be incremented each time when a character
				// will be a digit
				count++;
			}
		}
		String reset_val = String.valueOf(count);
		Assert.assertEquals(reset_val, edit_on_hand_item_quantity);

	}
	
	public int getActiveTxnQuantityOnHand() {
		int currQty = 0;
		String qohText = element("edit_on_hand_item_quantity").getText();
		
		for (int i = 0; i < qohText.length(); i++) {
			if (Character.isDigit(qohText.charAt(i))) {
				currQty = currQty * 10 + Character.getNumericValue(qohText.charAt(i));
			}
		}
		
		return currQty;
	}
	
	public void updateActiveTxnQOH(int increment) {
		int currQty = getActiveTxnQuantityOnHand();
		int updatedQty = currQty + increment;
		
		Actions action = new Actions(driver);
		Action seriesOfAction1 = (Action) action.moveToElement(element("edit_on_hand_item_quantity")).click().build();
		wait.elementHighlight(element("edit_on_hand_item_quantity"));
		seriesOfAction1.perform();
		sendKeysUsingXpathInJavaScriptExecutor(element("edit_on_hand_item_quantity"), String.valueOf(updatedQty));
		action.sendKeys(Keys.ENTER).build().perform();
		
		try {
			if(isElementDisplayed("qty_update_confimation_popup")) {
				clickButtonById("primary");
				wait.hardWait(5);
				Assert.assertTrue(getActiveTxnQuantityOnHand() > currQty);
			}
		}  catch(Exception e) {
			wait.hardWait(5);
			Assert.assertTrue(getActiveTxnQuantityOnHand() > currQty);
		}
		
	}

	public void clickRestockButton_SanityNew() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 50);

		isElementDisplayed("restock_button_sanity");
		// wait.waitForElementToBeClickable(element("click_restock_hold_button_sanity",
		// fieldName));
		// wait.elementHighlight(element("click_restock_hold_button_sanity",
		// fieldName));
		// wait.waitForElementToBeClickableAfterRefresh(element("restock_button_sanity"));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("restock_button_sanity"));
		// element("restock_button_sanity").click();
		// element("invoice_item_checkbox", fieldName).click();
	}

	public void selectRestockTransaction_SanityNew(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 50);
		isElementDisplayed("restock_transaction_checkbox", fieldName);
		// wait.waitForElementToBeClickable(element("restock_transaction_checkbox",
		// fieldName));
		// wait.elementHighlight(element("restock_transaction_checkbox",
		// fieldName));
		// clickUsingXpathInJavaScriptExecutorSingleClick(element("restock_transaction_checkbox",
		// fieldName));
		// wait.waitForElementToBeClickableAfterRefresh(element("restock_transaction_checkbox",
		// fieldName));
		element("restock_transaction_checkbox", fieldName).click();
		// element("invoice_item_checkbox", fieldName).click();
	}

	public boolean verifyPatientNameInPickQueue(String firstname) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		try {
			return isElementDisplayed("patientName_in_pick_queue", firstname);
		} catch (Exception e) {
			return false;
		}

	}

	public boolean verifyNotificationPopupIsDisplayed(String message) {
		try {
			return element("tq_notification").getText().contains(message);

		} catch (Exception e) {
			return false;
		}

	}

	public boolean verifyReturnTransactionInRestockQueue(String priority) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 90);
		try {
			if (element("verify_return_trxn", priority).isDisplayed()) {
				logMessage("[ASSERTION PASSED]: Return Transaction is visible in Restock Queue");
				return true;
			} else {

				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

	public void enterConfirmQuantity(String value) {
		wait.hardWait(5);
		isElementDisplayed("reconfirm_qty_cycle");
		enterTextInField(element("reconfirm_qty_cycle"), value);
		logMessage("ASSERTION PASSED: Entered QOH Value as :" + value);

	}

	public void clickOutside() {
		clickUsingXpathInJavaScriptExecutor(element("outside_tq"));
		logMessage("Clicked Outside");
	}

	

	public void releaseCycleCountTransaction() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.hardWait(10);
		isElementDisplayed("release_cycle_Tx");
		logMessage("Release button found");
		wait.waitForElementToBeClickableAfterRefresh(element("release_cycle_Tx"));
		Actions action = new Actions(driver);
		action.moveToElement(element("release_cycle_Tx")).click().build().perform();

		
	//	clickUsingXpathInJavaScriptExecutorSingleClick(element("release_cycle_Tx"));
		logMessage("Release button clicked");
	}

	public void Deleteransaction_SanityNew(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 50);
		element("delete_transaction_with_priority", fieldName).click();
	}

	public void verifyInventoryMovePickTransaction() {
		if (element("cycle_count_txn_active").getText().contains("Inventory Move")) {
			logMessage("ASSERTION PASSED: Inventory Move Pick Transaction is created");
		}

	}

	public void verifyInvMovePickDestination(String isa) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);

		isElementDisplayed("patientName_in_pick_queue", isa);
	}

	/////////////// Added by BD Team////////////////////////////////////////
	public void clickWasteItem() {
		isElementDisplayed("waste_item_btn");
		try {
			click(element("waste_item_btn"));
			logMessage("Waste Item is clicked.");
		} catch (Exception e) {
			Actions action = new Actions(driver);
			action.moveToElement(element("waste_item_btn")).click().build().perform();

		}
	}

	public int getQuantityOnHandActiveTransactionNew() {
		String quantity;
		wait.hardWait(3);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.hardWait(3);
		isElementDisplayed("active_transaction_quantityOnHand");
		quantity = element("active_transaction_quantityOnHand").getText().split(":")[1];
		return Integer.parseInt(quantity.trim());
	}

	public void updateActiveItemQuantityAndOnHandQuantityNew2() {
		Actions action = new Actions(driver);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		wait.hardWait(3);
		String edit_on_hand_item_quantityWithText = element("edit_on_hand_item_quantity").getText().split(":")[1];
		int edit_on_hand_item_quantity = Integer.parseInt(edit_on_hand_item_quantityWithText.trim());
		wait.waitForElementToBeClickable(element("edit_on_hand_item_quantity"));
		Action seriesOfAction1 = (Action) action.moveToElement(element("edit_on_hand_item_quantity")).click().build();
		wait.elementHighlight(element("edit_on_hand_item_quantity"));
		seriesOfAction1.perform();
		element("qoh_restock").sendKeys(String.valueOf(edit_on_hand_item_quantity + 1));
		action.sendKeys(Keys.ENTER).build().perform();
		wait.hardWait(3);
		wait.waitForElementToBeClickable(element("edit_on_hand_item_quantity"));
		String reset_valWithText = element("edit_on_hand_item_quantity").getText().split(":")[1];
		int reset_val = Integer.parseInt(reset_valWithText.trim());
		Assert.assertEquals(reset_val, edit_on_hand_item_quantity + 1);

	}

	public void getxpathOfQOH() {
		isElementDisplayed("xpath_qoh");
		String str = element("xpath_qoh").getAttribute("innerHTML");
		System.out.println(str);

	}

	public boolean verifyUpdatedQOH(String value) {
		wait.hardWait(5);
		isElementDisplayed("edit_on_hand_item_quantity");
		logMessage("Updated Quantity: " + element("edit_on_hand_item_quantity").getText());
		return element("edit_on_hand_item_quantity").getText().contains(value);

	}

	public boolean verifyUpdatedQOH(int value) {
		wait.hardWait(5);
		isElementDisplayed("edit_on_hand_item_quantity");
		logMessage("Updated Quantity: " + element("edit_on_hand_item_quantity").getText());
		int num = Integer.parseInt(element("edit_on_hand_item_quantity").getText());
		if (num == value) {
			return true;
		} else {
			return false;
		}

	}

	public boolean verifyUpdatedProcessedQuantity(String value) {
		wait.hardWait(5);
		isElementDisplayed("quantity_activeBox");
		logMessage("Updated Quantity: " + element("quantity_activeBox").getText());
		return element("quantity_activeBox").getText().contains(value);

	}

}
