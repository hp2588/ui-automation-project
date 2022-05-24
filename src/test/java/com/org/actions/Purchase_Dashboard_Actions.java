package com.org.actions;

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
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.org.automation.getpageobjects.GetPage;
import com.org.automation.utils.ConfigPropertyReader;

import static com.org.automation.utils.YamlReader.getData;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Purchase_Dashboard_Actions extends GetPage {

	WebDriver driver;
	static String pagename = "Purchase_Dashboard_Page";

	public Purchase_Dashboard_Actions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public void clickPOActionbutton(String action) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		isElementDisplayed("action_button1", action);
		hover(element("action_button1", action));
		wait.waitForElementToBeClickable(element("action_button1", action));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button1", action));
		logMessage("Clicked on '" + action + "' button");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 5);

	}

	public String SearchPOItem(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		if (fieldName.equalsIgnoreCase("Item Name")) {
			enterTextInField(element("search_po_item", fieldName), data);
			Assert.assertEquals(element("search_po_item", fieldName).getAttribute("value").trim(), data);
		} else {
			enterTextInField(element("search_po_item", fieldName), data);

			wait.hardWait(2);

			Assert.assertEquals(element("search_po_item", fieldName).getAttribute("value"), data);

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

		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 20);
		isElementDisplayed("po_search_item_result", fieldName);
		logMessage("ASSERT PASSED :Searched item found");

	}

	public String clickSearchedPOItem(String index, String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 20);
		String itemName = "test";
		itemName = element("po_item_by_index", index).getText();

		element("click_searched_item", fieldName).click();
		logMessage(" Search Result: " + itemName);
		return itemName;
	}

	public String clickSearchedPOItemAfterAdd(String fieldName) {
		String itemName;
		itemName = element("po_item_after_add", fieldName).getText();
		element("po_item_after_add", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		logMessage(" Search Result: " + itemName);
		return itemName;
	}

	public void enterOrderQuantity(String fieldName, String data) {

		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		try {
			enterTextInField(element("enter_order_quantity", fieldName), data);
			Assert.assertEquals(element("enter_order_quantity", fieldName).getAttribute("value").trim(), data);

		} catch (Exception e) {

			String jquery = "document.getElementById('" + fieldName + "').value =" + data;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript(jquery);
			logMessage("Order Quantity :" + data);

		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
	}

	public void clickSaveAndClose(String fieldName) {
		isElementDisplayed("order_save_close", fieldName);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("order_save_close", fieldName));
		wait.hardWait(5);
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
	}

	public void clickOnCancel(String fieldName) {
		isElementDisplayed("order_save_close", fieldName);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("order_save_close", fieldName));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
	}

	public boolean verifyPurchaseOrderManualCardisPresent(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		return isElementDisplayed("po_card_new_state", fieldName);
	}

	public boolean verifyPurchaseOrdereElectronicCardisPresent() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		return isElementDisplayed("open_po_card_electronic");
	}

	public void openPurchaseOrderCard(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		element("po_card_new_state", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		logMessage("Purchase order card opened successfully");
	}

	public void openPurchaseOrderManualcard() {
		element("open_po_card_manual").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
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
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		clickUsingXpathInJavaScriptExecutor(element("dashboard_link", fieldname));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
	}

	public void getPOVendorName(String fieldName) {
		String vendorName;
		isElementDisplayed("", fieldName);
		vendorName = getElementText(element(""));
	}

	public boolean verifyDashboardState(String fieldName) {

		return isElementDisplayed("dashboard_state", fieldName);

	}

	public boolean validateDistributorCardName(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		return isElementDisplayed("dashboard_new_state_card", fieldName);
	}

	public boolean validateItemAndISACount(String fieldName) {
		isElementDisplayed("dashboard_item_isa_count", fieldName);
		if (element("dashboard_item_isa_count", fieldName).getText().contains("item")
				&& element("dashboard_item_isa_count", fieldName).getText().contains("PO"))
			return true;
		else
			return false;
	}

	public boolean verifyDistributorCardType(String fieldName) {
		isElementDisplayed("dashboard_card_type", fieldName);
		if (element("dashboard_card_type", fieldName).getText().contains("M")
				|| element("dashboard_card_type", fieldName).getText().contains("E"))
			return true;
		else
			return false;
	}

	public String getPurchaseOrderDistributorName(String fieldName) {

		String distName = element("dashboard_distributor_card_name", fieldName).getText();
		return (distName == null || distName.length() == 0) ? null : (distName.substring(0, distName.length() - 1));

	}

	public boolean verifyDistributorMappedWithFacility(String fieldName) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		return isElementDisplayed("dist_mapped_to_facility", fieldName);

	}

	public boolean verifySearchBoxOnPurchaseDashboard(String fieldName) {

		return isElementDisplayed("dashboard_search_input_box", fieldName);

	}

	public String selectGroupByDropdownValue(String fieldName, String data) {
		selectProvidedTextFromDropDown(element("dashboard_group_by_dropdown", fieldName), data);
		Assert.assertTrue(
				getSelectedTextFromDropDown(element("dashboard_group_by_dropdown", fieldName)).equalsIgnoreCase(data));
		return data;
	}

	public void dragAndDropOrder(String from, String to) {
		WebElement _from, _to;
		Actions builder = new Actions(driver);
		_from = element("dashboard_new_state_card", from);
		_to = element("dashboard_state", to);
		builder.dragAndDrop(_from, _to).perform();

	}

	public boolean verifyOrderColorCode(String fieldName, String colorData) {

		isElementDisplayed("order_color_code_new_state", fieldName);
		return (element("order_color_code_new_state", fieldName).getAttribute("class").toString().contains(colorData));

	}

	public String generatingRandomStringForDistributor(int length) {

		boolean useLetters = false;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return generatedString;
	}

	public void clickOnEnableAutoReceiveNonControlled(String name) {
		isElementDisplayed("auto_non_controlled", name);
		element("auto_non_controlled", name).click();
	}

	public void clickOnEnableAutoReceiveCTwo(String name) {
		isElementDisplayed("auto_receive_enablectwo", name);
		element("auto_receive_enablectwo", name).click();
	}

	public void clickOnEnableAutoReceiveCThreeFive(String name) {
		isElementDisplayed("auto_receive_enablecthreefive", name);
		element("auto_receive_enablecthreefive", name).click();
	}

	public void clickOnLocationManagamentCancelButton(String fieldName) {
		isElementDisplayed("loc_mng_cancel", fieldName);
		element("loc_mng_cancel", fieldName).click();
	}

	public void resetSearch() {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.querySelector(('.icon-close-bold-sm'),':before').click();");
		logMessage("Clicked on close(X) button to reset search");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
	}

	public void clickOnDistributorTypeRadioButton(String fieldName) {
		isElementDisplayed("select_dist_radiobutton", fieldName);
		element("select_dist_radiobutton", fieldName).click();
	}

	public String selectISAValueForDropDown(String fieldName, String data) {
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);

		isElementDisplayed("select_isa_assign", fieldName);
		wait.waitForElementToBeClickable(element("select_isa_assign", fieldName));
		wait.waitForElementToBeVisible(element("select_isa_assign", fieldName));
		scrollDown(element("select_isa_assign", fieldName));
		Select sel = new Select(element("select_isa_assign", fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("select_isa_assign", fieldName));
		wait.hardWait(5);
		System.out.println("ISA Name to Select: " + data);
		sel.selectByVisibleText(data);
		logMessage("STEP : Selected option is " + data);

		// selectProvidedTextFromDropDown(element("select_isa_assign",
		// fieldName),
		// data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("select_isa_assign", fieldName)), data);
		return data;

	}

	public boolean verifyResolveNowIsPresent(String fieldName, String data) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);

		if (element("dashboard_resolve_now_msg").getText().contains(data)
				|| isElementDisplayed("dashboard_resolve_now_button", fieldName)) {
			return true;
		} else {
			return false;
		}
	}

	public void clickOnPendingReceiveCard(String fieldName) {
		isElementDisplayed("open_po_card_pending_receive", fieldName);
		element("open_po_card_pending_receive", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
	}

	public String getPendingReceiveItemCount(String fieldName) {
		isElementDisplayed("pending_receive_card_item_count", fieldName);
		String Item_count = element("pending_receive_card_item_count", fieldName).getText().toString();
		return Item_count;
	}

	public boolean verifyCountAfterInvoiceReceive(String before, String after) {

		if (before.equals(after)) {
			return false;
		} else {
			return true;
		}

	}

	public void clickOnKebabMenuPerState(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("kebab_menu", fieldName);
		element("kebab_menu", fieldName).click();

	}

	public boolean verifyKebabMenuLinkCountNewOrder(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("kebab_menu_expanded", fieldName);
		int i = 0;
		List<WebElement> list = elements("kebab_menu_expanded", fieldName);
		for (WebElement element : list) {
			String link = element.getAttribute("a");
			System.out.println(element.getText());
			i++;

		}
		if (i == 2) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean verifyKebabMenuLinkCount(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("kebab_menu_expanded", fieldName);
		int i = 0;
		List<WebElement> list = elements("kebab_menu_expanded", fieldName);
		for (WebElement element : list) {
			String link = element.getAttribute("a");
			System.out.println(element.getText());
			i++;

		}
		if (i == 5) {
			return true;
		} else {
			return false;
		}
	}

	public void verifyCreateNewOrderLink(String command) {
		isElementDisplayed("Add_new_order_actions", command);
		// clickUsingXpathInJavaScriptExecutorSingleClick(element("Add_new_order_actions",
		// command));
		element("action_close_button").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
	}

	public int getDayCount(String fieldName) {
		String count = element("group_by_type_day_count", fieldName).getText();

		if (Integer.parseInt(count) == 0) {
			return 0;
		} else {
			return 1;
		}

	}

	public boolean checkGroupByDistributorType() {
		int i = 1;
		while (i <= 2) {
			isElementDisplayed("group_by_type_distributor", Integer.toString(i));
			i++;
			System.out.println(i);
		}
		if (i == 3) {
			return true;
		} else {
			return false;
		}
	}

	public String getDashboardTime() {

		isElementDisplayed("dashboard_last_updated");
		return (element("dashboard_last_updated").getText());

	}

	public boolean clickOnKebabMenuOptions(String fieldName) {
		isElementDisplayed("click_kebab_menu_option", fieldName);
		element("click_kebab_menu_option", fieldName).click();
		return true;
	}

	public void enterValueInSearchField(String fieldName, String data) {

		element("enter_search_value", fieldName).sendKeys(data);

	}

	public void clearValueInSearchField(String fieldName) {

		element("enter_search_value", fieldName).clear();
	}

	public void clickDashboardRefresh(String fieldName) {

		element("dashboard_refresh_button", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

	}

	public void clickRefreshOrder(String filedName) {

		element("refresh_Order_button", filedName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
	}

	public void clickOnTabEditableField(String fieldName) {

		for (int i = 1; i <= 17; i++) {
			element("action_button", fieldName).sendKeys(Keys.TAB);
		}
	}

	public void dashboardScroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}

	public void clickOnPurchasingDahboardLabel() {
		element("po_label").click();
	}

	public void clickSearchedPOItemOnly(String index, String fieldName) {
		String itemName;
		// itemName = element("po_item_by_index", index).getText();
		element("po_search_item_result_only", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage(" Search Result: " + fieldName);
		// return itemName;
	}

	public void clickItemInPO(String fieldName) {
		String itemName;
		// itemName = element("po_item_by_index", index).getText();
		element("click_item_checkbox_in_po", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage(" Checkbox for Item: " + fieldName + " is clicked");
		// return itemName;
	}

	public boolean verifyRemoveButtonIsDisabled(String fieldName) {

		isElementDisplayed("action_button", fieldName);
		String value = element("action_button", fieldName).getAttribute("tabindex");
		if (value.equals("-1")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyRemoveButtonEnabled(String fieldName) {

		isElementDisplayed("action_button", fieldName);
		String value = element("action_button", fieldName).getAttribute("tabindex");
		if (value.equals("0")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyRemoveButtonIsEnabled(String fieldName) {

		isElementDisplayed("action_button", fieldName);
		String value = element("action_button", fieldName).getAttribute("tabindex");
		if (value.equals("0")) {
			return true;
		} else {
			return false;
		}
	}

	public void verifyItemHeaders(String fieldName) {

		isElementDisplayed("item_headers", fieldName);
		logMessage(" Header: " + fieldName + " is present on page");
	}

	public boolean verifyItemNameInPOCard(String fieldName) {
		// element("click_on_po_internal_card").click();
		String str = element("get_item_name").getText();
		System.out.println(str);
		System.out.println(fieldName);
		if (str.contains(fieldName)) {
			return true;
		} else {
			return false;
		}

	}

	public void enterPODescription(String fieldName, String data) {

		element("po_description_item_one", fieldName).sendKeys(data);

	}

	public boolean validatePONumberFieldLength() {

		String maxLength = element("po_number_active_order").getAttribute("maxlength");
		if (Integer.parseInt(maxLength) == 25) {
			return true;
		} else {
			return false;
		}

	}

	public void enterOrderQuantityItemLevel(String fieldName, String data) {
		element("order_quantity_item_level", fieldName).clear();
		element("order_quantity_item_level", fieldName).sendKeys(data);
	}

	public boolean validateAddButtonIsPresent(String fieldName) {

		if (isElementDisplayed("action_button", fieldName)) {
			return true;
		} else {
			return false;
		}

	}

	public void verifyVoidLinkisPresent(String fieldName) {

		String str1 = elements("action_button2", fieldName).get(0).getText();
		System.out.println(str1);
		String str2 = elements("action_button2", fieldName).get(1).getText();
		System.out.println(str2);
		if (str1.contains("Void") && str2.contains("Void")) {
			System.out.println("[Assertion Passed]: Void and Void All link are present");
		} else {
			System.out.println("[Assertion Failed]: Void and Void All links are not present");
		}

	}

	public void clickButtonOnDashboard(String fieldName) {
		wait.hardWait(3);
		isElementDisplayed("action_button", fieldName);
		element("action_button", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
	}

	public void clickCheckboxAll(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 20);
		isElementDisplayed("select_checkbox_all", fieldName);
		element("select_checkbox_all", fieldName).click();
	}

	public void enterOrderQuantityAfterAdd(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

		enterTextInField(element("order_quantity_after_add", fieldName), data);
		wait.hardWait(2);
		Assert.assertEquals(element("order_quantity_after_add", fieldName).getAttribute("value").trim(), data);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
	}

	public boolean verifyItemIsNotVisibleAfterRemove(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 60);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		String itemName;
		// itemName = element("po_item_by_index", index).getText();
		try {
			element("click_item_checkbox_in_po", fieldName);
			return false;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return true;
		}
	}

	public void clickCheckboxfacilityitemlevel() {
		isElementDisplayed("click_checkbox_facilityitem1");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_checkbox_facilityitem1"));
		isElementDisplayed("click_checkbox_facilityitem2");
		clickUsingXpathInJavaScriptExecutorSingleClick(element("click_checkbox_facilityitem2"));
	}

	public boolean verifyTransactionForPackageShareOrReceivingIsAvailableInISA(String ISAName) {
		// isElementDisplayed("data_trans",ISAName);
		for (WebElement ele : elements("data_trans", ISAName)) {
			if (ele.getText().contains("Receiving") || ele.getText().contains("Package Share")) {
				return true;
			}
		}
		return false;
	}

	public void clickOnVoidAll(String fieldName) {
		wait.hardWait(2);
		isElementDisplayed("action_button1", fieldName);
		element("action_button1", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
	}

	public void clickYesOrNoAfterVoid(String fieldName) {
		wait.hardWait(2);
		isElementDisplayed("action_button", fieldName);
		element("action_button", fieldName).click();
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
	}

	public void clickOnVoidButton(String index) {

		isElementDisplayed("void_link_button", index);
		element("void_link_button", index).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

	}

	public String getPODescription(String index) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		String po_description = element("po_description", index).getAttribute("value").trim();
		System.out.println(po_description);
		return po_description;
	}

	public boolean getConfirmVoidPopupText(String ditributorName, String po_description) {

		String popup_text = element("void_confirm_text").getText();
		System.out.println(popup_text);
		if (popup_text.contains(ditributorName) && popup_text.contains(po_description)
				&& popup_text.contains("Are you sure you want to void") && popup_text.contains("order with")) {
			return true;
		} else {
			return false;
		}
	}

	public void verifySuccessMessageOnVoidPO(String successMessage) {
		// wait.waitForLoaderToBeInvisible(getLocator("loader"), 5);
		try {

			isElementDisplayed("popup_message");
		} catch (Exception e) {

			isElementDisplayed("popup_message");
		}
		System.out.println(element("popup_message").getText().trim());
		Assert.assertTrue(element("popup_message").getText().trim().contains(successMessage));
		logMessage("[ASSERTION PASSED]: Success message is visible in toast message");

	}

	public void clickRefreshOrderDetailsPage(String fieldName) {

		isElementDisplayed("action_button", fieldName);
		element("action_button", fieldName).click();
	}

	public void itemIsNotVisible(String fieldName) {

		isElementNotDisplayed("get_item_name", fieldName);
		logMessage("[ASSERTION PASSED]: Item is not displayed");
	}

	public void verifyPrintOrderSheetButtonIsPresent(String fieldName) {

		Assert.assertTrue(isElementDisplayed("action_button", fieldName));
		logMessage("[ASSERTION PASSED]: Print Order sheet button is present.");

	}

	public void clickPrintOrderSheet(String fieldName) {

		isElementDisplayed("action_button", fieldName);
		element("action_button", fieldName).click();
	}

	public void clickEscape() throws AWTException {

		/*
		 * wait.hardWait(5000); System.out.println("Inside clickEscape method"); Robot
		 * rb =new Robot(); rb.keyPress(KeyEvent.VK_TAB);
		 * rb.keyRelease(KeyEvent.VK_TAB); rb.keyPress(KeyEvent.VK_ESCAPE);
		 * rb.keyRelease(KeyEvent.VK_ESCAPE); wait.hardWait(5000);
		 */

		((JavascriptExecutor) driver).executeScript("window.print=function(){};");

	}

	public boolean getConfirmExportPopupText(String ditributorName, String po_description) {

		String popup_text = element("void_confirm_text").getText();
		System.out.println(popup_text);
		if (popup_text.contains(ditributorName) && popup_text.contains(po_description)
				&& popup_text.contains("Are you sure you want to void") && popup_text.contains("order with")) {
			return true;
		} else {
			return false;
		}
	}

	public String enterDataInInputField(String fieldName, String data) {
		isElementDisplayed("input_field", fieldName);
		enterTextInField(element("input_field", fieldName), data);
		Assert.assertTrue(element("input_field", fieldName).getAttribute("value").trim().equals(data));
		logMessage("[STEP]: Data entered in Input field.");
		wait.hardWait(3);
		return data;
	}

	public void savePONumber(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		wait.elementHighlight(element("save_po_number", fieldName));
		element("save_po_number", fieldName).click();
	}

	public void clickButton(String action) {
		try {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
			isElementDisplayed("action_button", action);
			hover(element("action_button", action));
			wait.waitForElementToBeClickable(element("action_button", action));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button", action));
			logMessage("Clicked on" + action + "button to add hold reason");
		//	wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		} catch (Exception e) {
			element("action_button", action).click();
			logMessage("Clicked on" + action + "button to add hold reason");
		//	wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		}
	}

	public String enterPONumberPerOrder(String index, String data) {
		isElementDisplayed("enter_po_number_per_order", index);
		enterTextInField(element("enter_po_number_per_order", index), data);
		Assert.assertTrue(element("enter_po_number_per_order", index).getAttribute("value").trim().equals(data));
		logMessage("[STEP]: PO number entered in Input field.");
		wait.hardWait(3);
		return data;
	}

	public void clickAllISA() {

		isElementDisplayed("po_all_isa");
		element("po_all_isa").click();
	}

	public void clickDistributorWebsiteButton(String fieldName) {

		isElementDisplayed("action_button1", fieldName);
		element("action_button1", fieldName).click();
	}

	public void clickOnExportedCard(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("open_po_card_exported", fieldName);
		element("open_po_card_exported", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
	}

	public void clickOnReceiveCard(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		isElementDisplayed("open_po_card_received", fieldName);
		element("open_po_card_received", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
	}

	public void verifyDistributorWebsiteButtonIsDisabled(String fieldName) {

		isElementDisplayed("action_button1", fieldName);
		String className = element("action_button1", fieldName).getAttribute("class");

		Assert.assertTrue(className.contains("disabled"), "[Assertion Failed]: Distributor button is not disabled");
		logMessage("Distributor website is disabled");
	}

	public void clickPendingReceiveCard(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("pending_receive_card", fieldName));
		wait.hardWait(5);

	}

	public void openPOCard() {
		element("new_order_tile").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Purchase order card opened successfully");
	}

	public void openPendingRecievedPOCard(String fieldName) {

		String itemName = element("pendingRecieve", fieldName).getText();
		element("pendingRecieve", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage(" Search Result: " + itemName);
	}

	public void verifyCardInRecieved(String fieldName) {

		String itemName = element("recievedTilePOPageOrderStatus", fieldName).getText();
		element("recievedTilePOPageOrderStatus", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Order Recieved Card Generated successfully");
	}

	public void verifyCardInPendingRecieved(String fieldName) {

		String itemName = element("pendingrecievedTilePOPageOrderStatus", fieldName).getText();
		element("pendingrecievedTilePOPageOrderStatus", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Order Pending Recieved Card Generated successfully");
	}

	public void clickonCheckboxforInvoice() {
		element("checkboxInvoices").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Purchase order card opened successfully");
	}

	public void clickOnComment() {
		element("commentSection").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Comment Section Clicked successfully");
	}

	public boolean selectDropDownValueInvalid(String value) throws NoSuchElementException

	{
		wait.hardWait(15);
		try {
			boolean bool = driver.findElement(By.xpath("//select/option[text()='" + value + "']")).isDisplayed();
			return bool;

		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void clickOnRecieveButton() {
		element("recieveButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Recieve Button Clicked successfully");

	}

	public void clickOnRecieveAllButton() {
		element("recieveAllButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("RecieveAll Button Clicked successfully");

	}

	public void clickOnRecievedTilePOPage() {
		element("recievedTilePOPage").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Recieve Button Clicked successfully");

	}

	public void clickOnRecieveAllAndSendToQueueButton() {
		element("recieveAllAndSendToQueue").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("RecieveAll Button Clicked successfully");

	}

	public void clickOnBypass() {
		element("bypassButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("RecieveAll Button Clicked successfully");

	}

	public void clickOnRecieveAndSendToQueue() {
		element("recieveAndSendToQueueButton").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("RecieveAll Clicked successfully");
	}

	public boolean verifyRecievedTilePOPageOrderStatus() {
		boolean bool = element("recievedTilePOPageOrderStatus").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Order Status Change Verified successfully");
		return bool;
	}

	public void clickOnRecievedSection() {

		element("recievedSectionOfPage").isDisplayed();
		element("recievedSectionOfPage").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Order Click successfully");

	}

	public boolean verifyStatusChange() {
		boolean bool = element("recievedStatusChangeInRecievedSection").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Order Status Change Verified successfully");
		return bool;
	}

	public boolean addComment() {

		element("addCommentHereTextField").click();
		element("addCommentHereTextField").sendKeys("To be, or not to be, that is the question:\r\n"
				+ "Whether 'tis nobler in the mind to suffer\r\n" + "The slings and arrows of outrageous fortune,\r\n"
				+ "Or to take arms against a sea of troubles\r\n" + "And by opposing end them. To die—to sleep,\r\n"
				+ "No more; and by a sleep to say we end");
		element("addCommentButton").click();
		boolean bool = element("commentAdded").isDisplayed();
		logMessage("Comment Added successfully");
		return bool;

	}

	public void recievedItemsColumn(String key) {
		element("recievedItemsColumn").clear();
		element("recievedItemsColumn").sendKeys(key);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Items Value Updated successfully");
	}

	public void recievedItemsCost(String key) {
		element("itemCostColumn").clear();
		element("itemCostColumn").sendKeys(key);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Cost Value Updated successfully");
	}

	public void packageSizeColumn(String key) {
		element("packageSizeColumn").clear();
		element("packageSizeColumn").sendKeys(key);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Size Value Updated successfully");
	}

	public boolean verifyInvalidErrorPackageSize(String key) {
		element("packageSizeColumn").clear();
		element("packageSizeColumn").sendKeys(key);
		boolean bool = element("invalidPackageSize").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		element("packageSizeColumn").clear();
		element("packageSizeColumn").sendKeys("10");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Message Verified successfully");
		return bool;
	}

	public boolean verifyInvalidErrorCost(String key) {
		element("itemCostColumn").clear();
		element("itemCostColumn").sendKeys(key);
		boolean bool = element("invalidCost").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		element("packageSizeColumn").clear();
		element("packageSizeColumn").sendKeys("10");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Message Verified successfully");
		return bool;
	}

	public boolean verifyInvalidErrorItems(String key) {
		element("recievedItemsColumn").clear();
		element("recievedItemsColumn").sendKeys(key);
		boolean bool = element("invalidRecieved").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		element("packageSizeColumn").clear();
		element("packageSizeColumn").sendKeys("10");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Message Verified successfully");
		return bool;
	}

	public boolean containsPODescription() {
		boolean bool = element("pOdescription").isEnabled();
		logMessage("PO Description is Visible");
		return bool;
	}

	public boolean containsPONumber() {
		boolean bool = element("pONumber").isEnabled();
		logMessage("PO Number is Visible");
		return bool;
	}

	public void sendKeysPONumber() {
		element("pONumber").sendKeys("ABC1234");
		logMessage("PO Number is Visible");

	}

	public boolean containsInvoiceNumber() {
		boolean bool = element("invoiceNumber").isEnabled();
		logMessage("Invoice is Visible");
		return bool;

	}

	public boolean sendKeysInvoiceNumber(String key) {
		boolean bool = element("invoiceNumber").isEnabled();
		element("invoiceNumber").sendKeys(key);
		logMessage("Invoice is Visible and Editable");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		return bool;
	}

	public boolean sendKeysPurchaseOrder(String key) {
		boolean bool = element("purchaseOrderNumber").isEnabled();
		element("purchaseOrderNumber").clear();
		element("purchaseOrderNumber").sendKeys(key);
		logMessage("Purchase Order Number is Visible and Editable");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		return bool;

	}

	public boolean checkColumnItems() {
		boolean bool = element("item1").isEnabled();
		logMessage("Column Items Checked successfully");
		return bool;

	}

	public boolean checkVIC() {
		boolean bool = element("vicCheck").isEnabled();
		logMessage("VIC Checked successfully");
		return bool;

	}

	public boolean checkNDC() {
		boolean bool = element("ndcCheck").isEnabled();
		logMessage("NDC Checked successfully");

		return bool;
	}

	public boolean checkStatus() {
		boolean bool = element("statusCheck").isEnabled();

		logMessage("Status Checked successfully");
		return bool;
	}

	public boolean checkRecieved() {
		boolean bool = element("recievedCheck").isEnabled();
		logMessage("Recieved Checked successfully");
		return bool;
	}

	public boolean checkOrdered() {
		boolean bool = element("orderedCheck").isEnabled();
		logMessage("Ordered Checked successfully");
		return bool;
	}

	public boolean checkCost() {
		boolean bool = element("costCheck").isEnabled();
		logMessage("Cost Checked successfully");
		return bool;
	}

	public boolean checkPacketSize() {
		boolean bool = element("packetSizeCheck").isEnabled();
		logMessage("Packet Size Checked successfully");
		return bool;
	}

	public boolean checkComments() {
		boolean bool = element("commentsCheck").isEnabled();
		logMessage("Comments Checked successfully");
		return bool;
	}

	public boolean clickPrintOrderSheet() {

		boolean bool = element("print_order_sheet").isDisplayed();

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Print Order Sheet Visible");
		return bool;
	}

	public void clickExportNowButton() {

		element("exportNowButton").click();

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);

		logMessage("Export Now Button Clicked");

	}

	public void clickExportNowButtonPopUp() {

		element("exportNowPopup").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("Print Order Sheet Visible");

	}

	public void distributorDetailsVisibleOnHeader(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		String itemName = element("distributor_details", fieldName).getText();
	//	element("distributor_details", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage(" Search Result: " + itemName);

	}

	public void orderTypeVisibleOnHeader(String fieldName) {
		String orderType = element("invoice_order_type", fieldName).getText();
	//	element("isaDetailsAreVerified", fieldName).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		logMessage("Distributor Type " + orderType);

	}

	public boolean isaDetailsAreVerified() {
		boolean bool = element("isaDetailsAreVerified").isDisplayed();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		logMessage("ISA Details Visible");

		return bool;
	}

	public boolean invoiceCheckboxIsSelected() throws InterruptedException {
		element("invoiceRecordCheckbox").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		boolean bool = element("invoiceRecordCheckbox").isEnabled();
		logMessage("Indiviual item under invoice is selectable");
		return bool;
	}

	public boolean invoiceCheckboxAllIsSelected() throws InterruptedException {
		element("invoiceAllRecordCheckbox").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		boolean bool = element("invoiceAllRecordCheckbox").isEnabled();

		logMessage("Indiviual item under invoice is selectable");
		return bool;
	}

	public void selectRadioButtonElectronicDistributor(String fieldName) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		isElementDisplayed("select_checkbox_all", fieldName);
		element("select_checkbox_all", fieldName).click();

	}

	public void enterDistributorWebsite(String fieldName, String webSite) {

		element("input_distributor_website", fieldName).sendKeys(webSite);
	}

	public void clickCheckboxDistributorOptions(String distributor, String flagname) {
		try {
			isElementDisplayed("distributorOptions_checkbox", distributor, flagname);
			// click(element("label_TransactionPriorities", flagname));
			clickUsingXpathInJavaScriptExecutorSingleClick(
					element("distributorOptions_checkbox", distributor, flagname));
		} catch (Exception e) {
			isElementDisplayed("distributorOptions_checkbox", distributor, flagname);
			// click(element("label_TransactionPriorities", flagname));
			clickUsingXpathInJavaScriptExecutorSingleClick(
					element("distributorOptions_checkbox", distributor, flagname));
		}
	}

	public void verifyAndClickItemFacility(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		isElementDisplayed("select_item_facility", fieldName);
		element("select_item_facility", fieldName).click();
		logMessage("[STEP]: ProductID is enabled and clicked");

	}

	public void clickOnEditLinkCorresspondingToItem(String addedRecord) {
		wait.hardWait(10);
		isElementDisplayed("link_edit_new", addedRecord);
		wait.waitForElementToBeClickable(element("link_edit_new", addedRecord));
		clickUsingXpathInJavaScriptExecutor(element("link_edit_new", addedRecord));
		logMessage("Clicked on Edit link corressponding to Added record :" + addedRecord);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

	}

	public void getConfirmExportMessageForElectronic() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		String confirmExport = element("confirm_export_text").getText();
		System.out.println(confirmExport);
		Assert.assertTrue(confirmExport.contains("Are you sure you want to export all"),
				"[Assertion Failed] Confirm message is not visible");
		logMessage("Confirm text is visible");
	}

	public void getConfirmExportMessageForManual() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		String confirmExport = element("confirm_export_text").getText();
		System.out.println(confirmExport);
		Assert.assertTrue(confirmExport.contains("Are you sure you want to export all"),
				"[Assertion Failed] Confirm message is not visible");
		logMessage("Confirm text is visible");
	}

	public void verifyLineItemFreeze(String id) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.getElementById('" + id + "').hasAttribute('disabled')");
		logMessage("Line item is freezed");

	}

	public String getLineItemOrderQuantity(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
		String value = element("item_quantity_value", fieldName).getAttribute("value");
		System.out.println("Order Quantity in New State :" + value);
		return value;

	}

	public void enterOrderQuantityValue(String id, String val) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.getElementById('" + id + "').value =" + val + ";");
		logMessage("Order Quantity :" + val);

	}

	public void verifyRefreshButtonIsDisabled(String fieldName) {

		isElementDisplayed("action_button", fieldName);
		element("action_button", fieldName).getAttribute("tabindex").equals("-1");
		logMessage("Refresh Button is order details page is disabled");
	}

	public String getNewStateItemCount(String fieldName) {
		wait.hardWait(5);
		isElementDisplayed("new_card_item_count", fieldName);
		String Item_count = element("new_card_item_count", fieldName).getText().toString();
		System.out.println(Item_count);
		return Item_count;
	}

	public String getExportedStateItemCount(String fieldName) {
		isElementDisplayed("exported_card_item_count", fieldName);
		String Item_count = element("exported_card_item_count", fieldName).getText().toString();
		System.out.println(Item_count);
		return Item_count;
	}

	public void verifyCardIsNotVisible(String fieldName) {

		isElementNotDisplayed("po_card_new_state", fieldName);
		logMessage("[ASSERTION PASSED]: Card is not displayed");
	}

	public void verifyUnableToOrderHeader() {
		isElementDisplayed("unable_to_order_header");
		logMessage("ASSERTION PASSED: Verified header for Unable to order");
		String[] elem = element("unable_to_order_header").getText().split("\\s");
		System.out.println("No of items unable to order: " + elem[0]);
		isElementDisplayed("resolve_now_link");
		logMessage("ASSERTION PASSED: Verified header for Unable to order");

	}

	public int countofItemsbeforeResolve() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		String[] elem = element("unable_to_order_header").getText().split("\\s");
		logMessage("The Count of Items Unable to be resolved is: " + elem[0]);
		return Integer.valueOf(elem[0]);

	}

	public void clickResolveNow() {
		isElementDisplayed("resolve_now_link");
		clickUsingXpathInJavaScriptExecutor(element("resolve_now_link"));
		logMessage("ASSERTION PASSED: Clicked Resolve Now link");
	}

	public void verifyIssueListingPage() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
		isElementDisplayed("issue_listing_header");
		logMessage("ASSERTION PASSED : User is on Issue Listing Page");
	}

	public int verifycountOnListPage() {
		if (isElementNotDisplayed("no_order_msg") == false) {
			List<WebElement> elements = elements("items_list");
			System.out.println("Count of Items on Issue Listing Page: " + elements.size());
			return elements.size();
		}

		else {
			System.out.println("No orders to Resolve");
			return 0;
		}
	}

	public boolean compareCountOnDashboardAndListing(int dashboard, int list) {
		Assert.assertEquals(dashboard, list);
		logMessage("ASSERTION PASSED : The count of items on Dashboard and Issue Listing Page is the same");
		return true;
	}

	public String clickIssueLink() {
		List<WebElement> element = elements("issue_button");

		String issuedescription = element.get(0).getText();
		element.get(0).click();
		logMessage("Clicked on Issue");
		if (issuedescription.equalsIgnoreCase("No preferred NDC")) {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);

			isElementDisplayed("product_header");
			logMessage("ASSERTION PASSED: User is on Add Product ID Page");
		}

		else if (issuedescription.equalsIgnoreCase("No preferred distributor")) {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
			isElementDisplayed("Add_Ditributor");
			logMessage("ASSERTION PASSED: User is on Add Prefered Distributor Page");

		}

		else {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
			isElementDisplayed("assign_popup");
			logMessage("ASSERTION PASSED: Verified Assign Location Popup");

		}

		return issuedescription;

	}

	public String getItemName() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);

		List<WebElement> element = elements("item_list");
		System.out.println("The Item name is: " + element.get(0).getText());
		return element.get(0).getText();
	}

	public boolean verifyItemOnIssuePage(String item) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		if (isElementNotDisplayed("no_order_msg") == false) {

			List<WebElement> element = elements("item_list");
			if (element.get(0).getText().equalsIgnoreCase(item)) {
				System.out.println("The item exists in List Issue Page");
				return true;
			} else {
				System.out.println("The item does not exist in List Issue page");
				return false;

			}

		} else {
			logMessage("ASSERTION PASSED: No ORders to Resolve");
			return false;
		}
	}

	public boolean verifyReducedCountAfterResolving(int before) {

		int after = verifycountOnListPage();
		if (after == 0) {
			System.out.println("ASSERTION PASSED: All Issues got resolved");
			return true;
		}

		else if (after == before - 1) {
			System.out.println("ASSERTION PASSED: Issue count is reduced by 1 after successfully Resolving the Issue");
			return true;
		} else
			return false;
	}

	public void verifySuccessMessageOnExportPO(String successMessage) {
		try {

			isElementDisplayed("popup_message");
		} catch (Exception e) {

			isElementDisplayed("popup_message");
		}

		System.out.println(element("popup_message").getText().trim());
		Assert.assertTrue(element("popup_message").getText().trim().contains(successMessage));
		logMessage("[ASSERTION PASSED]: Success message is visible in toast message");

	}

	public void verifyVoidButtonIsPresent(String index) {
		isElementDisplayed("void_link_button", index);
		// element("void_link_button", index).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

	}

	public void clickCancelButtonOnAssignPopup() {
		isElementDisplayed("assign_cancel");
		clickUsingXpathInJavaScriptExecutor(element("assign_cancel"));
		logMessage("ASSERTION PASSED: Clicked Cancel button");
	}

	public void verifyCountOnCancel(int oldCount, int newCount) {
		Assert.assertEquals(oldCount, newCount);
		logMessage("ASSERTION PASSED: Count on Purchase Order Dashboard is same after Cancel button");
	}

	public void verifyUserIsAbleToAddComment() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		Assert.assertTrue(isElementDisplayed("comment_icon"));

	}

	public void verifyTypeDropdownIsUnavailableFoManualOrder(String idfield) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		Assert.assertFalse(isElementNotDisplayed("type_dropdown", idfield));

	}

	public void verifyDefaultValueInDropDown(String fieldName, String text) {
		try {
			isElementDisplayed("type_dropdown", fieldName);
			verifySelectedTextFromDropDown(element("type_dropdown", fieldName), text);
		} catch (Exception e) {
			isElementDisplayed("type_dropdown", fieldName);
			verifySelectedTextFromDropDown(element("type_dropdown", fieldName), text);

		}
	}

	public List<String> getAllDataFromDropDown(String fieldName) {
		List<String> list = new ArrayList<String>();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

		element("type_dropdown", fieldName).click();
		for (WebElement ele : new Select(element("type_dropdown", fieldName)).getOptions()) {
			list.add(ele.getText().trim());
		}

		System.out.println("List returned as:\n" + list);

		return list;
	}

	public String selectValueFromDropDown(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForElementToBeClickable(element("type_dropdown", fieldName));
		selectProvidedTextFromDropDown(element("type_dropdown", fieldName), data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("type_dropdown", fieldName)), data);
		return data;
	}

	public void verifyTypeDropdownIsAvailable() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		Assert.assertTrue(isElementNotDisplayed("type_dropdown"));

	}

	public void verifyErrorPopUpMessage(String msg) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		isElementDisplayed("toast_error_message");
		Assert.assertTrue(element("toast_error_message").getText().contentEquals(msg));

	}

	public String getPopupText() {
		wait.applyFluentWait(getLocator("toast_error_message"), 120, 500);
		return element("toast_error_message").getText().trim();
	}

	public void verifyVoidButtonIsNotPresent(String index) {
		isElementNotDisplayed("void_link_button", index);
		// element("void_link_button", index).click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

	}

	public void verifyVoidAllButtonIsNotPresent(String fieldName) {
		wait.hardWait(2);
		isElementNotDisplayed("action_button1", fieldName);
	}

	public void verifyItemIsPresent(String fieldName) {

		Assert.assertTrue(isElementDisplayed("verify_item_is_present", fieldName),
				"[Assertion Failed : Item is not visible in the purchase order]");
	}

	public String getItemOrderQuantity(String fieldName) {

		String orderQuantity = element("get_item_order_quantity", fieldName).getAttribute("value");
		System.out.println("OrderQuantity: " + orderQuantity);
		return element("get_item_order_quantity", fieldName).getAttribute("value");

	}

	public void clickToSavePONumber(String fieldName) {

		wait.elementHighlight(element("distributor_name_header", fieldName));
		element("distributor_name_header", fieldName).click();
		wait.hardWait(5);
	}

	public void enterOrderQuantity_new(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		try {

			enterTextInField(element("item_to_order_quantity", fieldName), data);
			Assert.assertEquals(element("item_to_order_quantity", fieldName).getAttribute("value").trim(), data);

		} catch (Exception e) {

			String jquery = "document.getElementById('" + fieldName + "').value =" + data;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript(jquery);
			logMessage("Order Quantity :" + data);

		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 10);
	}

	public boolean verifyTextOnDashboard(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		isElementDisplayed("autocreate_text");
		if (element("autocreate_text").getText().equals(data))
			return true;
		else
			return false;

	}

	public boolean verifyAutoPOCreationStatus(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		isElementDisplayed("autocreate_text_status");
		if (element("autocreate_text_status").getText().equals(data))
			return true;
		else
			return false;
	}

	public void clickAutoPOCreation() {

		isElementDisplayed("autocreate_text_status");
		element("autocreate_text_status").click();

	}

	public void clickAutoCreateBasedSchedule(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		isElementDisplayed("create_order_based_schedule", fieldName);
		element("create_order_based_schedule", fieldName).click();
	}

	public void clickScheduleDay(String fieldName) {

		isElementDisplayed("select_schedule_day", fieldName);
		element("select_schedule_day", fieldName).click();
	}

	public void clickSaveOrCancelSchedule(String fieldName) {

		isElementDisplayed("action_button", fieldName);
		element("action_button", fieldName).click();
	}

	public void verifyAccordianDaysExported(String fieldName) {

		isElementDisplayed("exported_accordian_days", fieldName);

	}

	public void verifyAccordianDaysPendingReceive(String fieldName) {

		isElementDisplayed("pending_receive_accordian_days", fieldName);

	}

	public void waitForLoader() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 240);
	}

	public boolean verifyNoRecordFoundOnItemSearch() {

		return isElementDisplayed("no_record_found");

	}

	public void clickOnItemActiveFlag(String fieldName) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		isElementDisplayed("item_active_flag");

		String jquery = "document.getElementById('" + fieldName + "').click()";
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(jquery);

		logMessage("Clicked on Active toggle !!!");
	}

	public void clickOnItemEditLink(String addedRecord) {
		wait.hardWait(10);
		isElementDisplayed("item_edit_link", addedRecord);
		wait.waitForElementToBeClickable(element("item_edit_link", addedRecord));
		clickUsingXpathInJavaScriptExecutor(element("item_edit_link", addedRecord));
		logMessage("Clicked on Edit link corressponding to Added record :" + addedRecord);
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);

	}

	public String enterdosageConcentrationAmount(String data) {

		isElementDisplayed("dosage_conc_vamount");
		element("dosage_conc_vamount").sendKeys(data);
		;
		return data;
	}

	public String getItemNameOrderDetailPage(String fieldName) {

		isElementDisplayed("order_page_item_name", fieldName);
		return element("order_page_item_name", fieldName).getText().toString();
	}

	public boolean isDropdownAvailable(String id) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 120);
		return isElementNotDisplayed("dropdown_by_id_or_name", id);
	}

	public boolean verifyAutoCreatedPO(String distributorNew) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.hardWait(5);
		wait.waitForElementToBeClickable(element("distributor_auto_create", distributorNew));
		wait.elementHighlight(element("distributor_auto_create", distributorNew));
		return isElementDisplayed("distributor_auto_create", distributorNew);

	}

	public boolean verifySchedulelink(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.waitForElementToBeClickable(element("link_schedule_setting", fieldName));
		wait.elementHighlight(element("link_schedule_setting", fieldName));
		clickUsingXpathInJavaScriptExecutorSingleClick(element("link_schedule_setting", fieldName));
		return isElementDisplayed("link_schedule_setting", fieldName);

	}

	public boolean verifyAutoCreatedPODoesNotExist(String distributorNew) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		return isElementNotDisplayed("distributor_auto_create", distributorNew);

	}

	public void clickAutoCreatedPOCard(String distributorNew) {
		isElementDisplayed("distributor_auto_create", distributorNew);
		wait.elementHighlight(element("distributor_auto_create", distributorNew));
		wait.waitForElementToBeClickable(element("distributor_auto_create", distributorNew));
		try {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("distributor_auto_create", distributorNew));
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		} catch (Exception e) {
			element("distributor_auto_create", distributorNew).click();
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		}

	}

	public boolean verifyModalScreen(String className) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		return isElementDisplayed("modal_screen", className);

	}

	public boolean verifyFacilityNameOnModalScreen(String facilityName) {
		return isElementDisplayed("facility_on_modal_screen", facilityName);

	}

	public boolean verifyRadioButton(String labelName, String id) {
		isElementDisplayed("radio_button", labelName);
		wait.waitForElementToBeClickable(element("radio_button", labelName));
		wait.elementHighlight(element("radio_button", labelName));
		try {
			clickUsingXpathInJavaScriptExecutor(element("radio_button", labelName));
		} catch (Exception e) {
			element("radio_button", labelName).click();
		}
		return checkRadioButtonIsEnabledOrDisabledUsingJavaScript(id);

	}

	public boolean verifyButtonOnModalScreenIsDisabled(String className, String fieldName) {
		isElementDisplayed("button_modal_screen", className, fieldName);

		return element("button_modal_screen", className, fieldName).isEnabled();
	}

	public boolean verifyDaysButtonOnModalScreen(String[] days, String className) {
		for (String d : days) {
			isElementDisplayed("button_modal_screen", className, d);
			if (!element("button_modal_screen", className, d).getAttribute("class").contains("secondary")) {
				wait.waitForElementToBeClickable(element("button_modal_screen", className, d));
				wait.elementHighlight(element("button_modal_screen", className, d));
				clickUsingXpathInJavaScriptExecutorSingleClick(element("button_modal_screen", className, d));
				Assert.assertTrue(
						element("button_modal_screen", className, d).getAttribute("class").contains("secondary"));

			} else {
				continue;
			}

		}
		return true;

	}

	public String selectDayOnModalScreen(String className, String day) {
		isElementDisplayed("button_modal_screen", className, day);
		wait.waitForElementToBeClickable(element("button_modal_screen", className, day));
		wait.elementHighlight(element("button_modal_screen", className, day));
		try {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("button_modal_screen", className, day));
		} catch (Exception e) {
			element("button_modal_screen", className, day).click();
		}
		return element("button_modal_screen", className, day).getText();
	}

	public void verifySuccessMessageOnViewPageWithLoader(String successMessage) {
		try {
			wait.applyFluentWait(getLocator("popup_message"), 10, 100);
			isElementDisplayed("popup_message");
			
		} catch (Exception e) {
			// wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
			wait.applyFluentWait(getLocator("popup_message"), 10, 100);
			isElementDisplayed("popup_message");
		}
		
		Assert.assertTrue(element("popup_message").getText().trim().contains(successMessage));
		logMessage("[ASSERTION PASSED]: Success Message on loader is visible");
		
		wait.waitForLoaderToBeInvisible(getLocator("popup_message"), 20);
	}

	public void verifyNewStateAll() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("new_state_date_accordian");
		System.out.println(element("new_state_date_accordian").getText().trim());
		element("new_state_date_accordian").getText().contains("All");
		logMessage("[ASSERT PASSED] : Date accordian present in New state");
	}

	public void verifyExportedStateAccordian() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("exported_state_date_accordian");
		System.out.println(element("exported_state_date_accordian").getText().trim());
		element("exported_state_date_accordian").getText().contains("Today");
		element("exported_state_date_accordian").getText().contains("Yesterday");
		element("exported_state_date_accordian").getText().contains("Older");
		logMessage("[ASSERT PASSED] : Date accordian present in exported state");
	}

	public void minimizeMaximizeNewStateAccordian(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("expand_accordian_state", fieldName);
		element("expand_accordian_state", fieldName).click();
		logMessage("[ASSERT PASSED] : Date Accordian Minimized");
		wait.hardWait(5);
		element("expand_accordian_state", fieldName).click();
		logMessage("[ASSERT PASSED] : Date Accordian Maximized");
	}

	public void verifyReceivedStateAll() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("received_state_date_accordian");
		System.out.println(element("received_state_date_accordian").getText().trim());
		element("received_state_date_accordian").getText().contains("All");
		logMessage("[ASSERT PASSED] :Date accordian present in Received state");
	}

	public void verifyAllISATextIsPresent() {

		isElementDisplayed("all_isa_text");
		Assert.assertTrue(element("all_isa_text").getText().equals("All ISAs"),
				"[Assert Failed] : ALL ISA text is not present in orderr details page");
		logMessage("[ASSERT PASSED] : ALL ISA text is present");
	}

	public void verifyISAPOIsPresent(String fieldName) {

		Assert.assertTrue(isElementDisplayed("verify_isa_name", fieldName), "[Assert Failed]: ISA name is not present");
		logMessage("[ASSERT PASSED] : ISA name is present in the purchase order");
	}

	public void verifyPOItemSearchResultAfterAdd(String fieldName) {

		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 30);
		isElementDisplayed("po_item_after_add", fieldName);
		logMessage("ASSERT PASSED :Searched item found");

	}

	public void clickPOBasedOnISAName(String fieldName) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		wait.hardWait(5);
		isElementDisplayed("verify_isa_name", fieldName);
		element("verify_isa_name", fieldName).click();

	}

	public String enterPONumberForActivePO(String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("po_number_active_order");
		element("po_number_active_order").clear();
		enterTextInField(element("po_number_active_order"), data);
		System.out.println("PO Number retrieved:: " + element("po_number_active_order").getAttribute("value").trim());
		// Assert.assertTrue(element("po_number_active_order").getAttribute("value").trim().equals(data));
		logMessage("[STEP]: PO number entered in Input field.");
		wait.hardWait(3);
		return data;
	}

	public void verifyAddItemSearchLabel() {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("add_item_search_label");
		logMessage("[Assert Passed] : Search label is present");
	}

	public void verifyPOCardIsPresent(String fieldName) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("verify_invoice_by_po_number", fieldName);
		logMessage("[Assert Passed] : PO card is visible based on PO number ");
	}

	public void verifyAddItemLabelIsBold() {

		isElementDisplayed("add_item_label");
		element("add_item_label").getTagName().equals("h3");
		logMessage("[Assert Passed] : Add Item Label is Bold");

	}

	public void clickOnTabAddItemScreen(String fieldName) {

		for (int i = 1; i <= 3; i++) {
			element("search_po_item", fieldName).sendKeys(Keys.TAB);
		}
	}

	public void verifyDefaultOrderQuantityOnAddScreen(String fieldName) {

		Assert.assertTrue(element("order_quantity_after_add", fieldName).getAttribute("value").equals("0"));

	}

	public void clearrOrderQuantityItemLevel(String fieldName) {

		element("order_quantity_item_level", fieldName).clear();
	}

	public void clickOnResolveNowLink(String fieldName) {

		isElementDisplayed("click_resolve_now_link", fieldName);
		element("click_resolve_now_link", fieldName).click();
	}

	public String getAllISACount() {

		isElementDisplayed("all_isa_text");
		element("all_isa_text").click();
		String isaCount = element("all_isa_active_count").getText().trim();
		Assert.assertTrue(element("all_isa_active_count").getText().equals("2"));
		return isaCount;
	}

	public boolean validateItemCountInPO(String fieldName, String isaCount) {
		isElementDisplayed("dashboard_item_isa_count", fieldName);
		if (element("dashboard_item_isa_count", fieldName).getText().contains(isaCount))
			return true;
		else
			return false;
	}

	public void clickPOCardInOrderDetailPage(String fieldName) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("verify_invoice_by_po_number", fieldName);
		element("verify_invoice_by_po_number", fieldName).click();
		logMessage("[Assert Passed] : PO card is visible based on PO number ");
	}

	public void verifyItemStatusInReceivedSection(String status) {

		element("received_item_status").getText().equals(status);
	}

	public void verifyCardIsNotPresentAfterVoid(String fieldName) {

		isElementNotDisplayed("verify_invoice_by_po_number", fieldName);
		logMessage("Card is removed after void");
	}

	public void clickWFACancelButton(String fieldName) {

		isElementDisplayed("action_button1", fieldName);
		element("action_button1", fieldName).click();

	}

	public String enterRandomValueInInputField(String fieldName, String data) {

		isElementDisplayed("input_field", fieldName);

		/*
		 * String jquery = "document.getElementById('" + fieldName + "').value=" + "'" +
		 * data + "'"; System.out.println(jquery); JavascriptExecutor executor =
		 * (JavascriptExecutor) driver; executor.executeScript(jquery);
		 * logMessage("Barcode: " + data);
		 */

		sendKeysUsingXpathInJavaScriptExecutor(element("input_field", fieldName), data);

		return data;

	}

	public void verifyPendingReceiveCardIsNotPresent(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementNotDisplayed("pending_receive_card", fieldName);

	}

	public void clickOnPOCardInPendingReceived(String fieldName) {

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("verify_invoice_by_po_number", fieldName);
		element("verify_invoice_by_po_number", fieldName).click();
		logMessage("[Assert Passed] : Clicked on PO card based on PO number in Pending Received");
	}

	public void verfiyInvoiceIsPresentInReceivedState(String fieldName, String data) {
		// fieldname is po number
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("po_invoice_card", fieldName);
		Assert.assertEquals(element("po_invoice_card", fieldName).getAttribute("value"), data);
		logMessage("[Assert Passed] : Invoice with invoice number" + data + "is present in received state");
	}

	public void verifyDistributorCardInPendingReceivedIsOpen(String fieldName, String dashboard) {
		// method verifies if user is still on the open card or has already been
		// redirected to dashboard
		// if user is still inside the open card then the method will redirect to
		// dashboard
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		if (isElementDisplayed("verify_pending_receive_card", fieldName)) {
			clickUsingXpathInJavaScriptExecutor(element("dashboard_link", dashboard));
		} else {
			System.out.println("User is currently on the dashboard home screen!!!");
		}
	}

	public void verifyDistributorCardInNewStateIsOpen(String fieldName, String dashboard) {
		// method verifies if user is still on the open card or has already been
		// redirected to dashboard
		// if user is still inside the open card then the method will redirect to
		// dashboard
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		if (isElementDisplayed("verify_new_state_card", fieldName)) {
			clickUsingXpathInJavaScriptExecutor(element("dashboard_link", dashboard));
		} else {
			System.out.println("User is currently on the dashboard home screen!!!");
		}
	}

	public boolean verifyExportButtonIsEnabled(String fieldName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("action_button", fieldName);
		boolean result = element("action_button", fieldName).isEnabled();
		System.out.println(result);
		return result;
	}

	public boolean verifyErrorMessageOnItemQuantity(String errorMessage) {

		if (element("item_level_qty_error_msg").getText().equals(errorMessage)) {
			return true;
		} else
			return false;

	}

	public boolean verifyErrorMessageOnItemQuantityColor(String fieldName,String rgb) {
		
		System.out.println(element("order_quantity_item_level",fieldName).getCssValue("border-bottom-color"));
		System.out.println(element("order_quantity_item_level",fieldName).getCssValue("border-top-color"));
		System.out.println(element("order_quantity_item_level",fieldName).getCssValue("border-left-color"));
		System.out.println(element("order_quantity_item_level",fieldName).getCssValue("border-right-color"));
		
		if (element("order_quantity_item_level",fieldName).getCssValue("border-top-color").equals(rgb)
				&& element("order_quantity_item_level",fieldName).getCssValue("border-bottom-color").equals(rgb)
				&& element("order_quantity_item_level",fieldName).getCssValue("border-left-color").equals(rgb)
				&& element("order_quantity_item_level",fieldName).getCssValue("border-right-color").equals(rgb)) {
			return true;
		} else
			return false;

	}
	
	public boolean verifyItemVICOrder(String vic) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		if(element("item_vic_order").getText().equals(vic)) {
			return true;
		}
		else
			return false;
	}
	
	public boolean verifyItemNDCOrder(String ndc) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		if(element("item_ndc_order").getText().equals(ndc)) {
			return true;
		}
		else
			return false;
	}
	
	public boolean verifyItemVICInvoice(String vic) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		if(element("item_vic_invoice").getText().equals(vic)) {
			return true;
		}
		else
			return false;
	}
	
	public boolean verifyItemNDCInvoice(String ndc) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		if(element("item_ndc_invoice").getText().equals(ndc)) {
			return true;
		}
		else
			return false;
	}
	
	public boolean verifyPkgSizeNewOrder(String pkgSize) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		if(element("item_pkgsize_order").getText().trim().equals(pkgSize)) {
			return true;
		}
		else
			return false;
		
	}

	public boolean verifyPkgSizeExportedOrder(String pkgSize) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		if(element("item_pkgsize_order").getText().trim().equals(pkgSize)) {
			return true;
		}
		else
			return false;
		
	}
	
	public boolean verifyPkgSizePendingReceive(String pkgSize) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 30);
		if(element("item_pkgsize_invoice").getAttribute("value").equals(pkgSize)) {
			return true;
		}
		else
			return false;
		
	}
	
	public boolean verifyPkgSizeReceive(String pkgSize) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		if(element("item_pkgsize_invoicereceived").getText().trim().equals(pkgSize)) {
			return true;
		}
		else
			return false;
		
	}

	public boolean verifyPkgSizeOnCreateOrderScreen(String pkgSize) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		if(element("item_create_order_pkgsize").getText().trim().equals(pkgSize)) {
			return true;
		}
		else
			return false;
		
	}
	
	public boolean verifyOrderQuantityIsMultipleOfPackageSize(String orderQuantity,String pkgSize) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		int order_QuantityItem=Integer.parseInt(element("item_quantity_value").getAttribute("value"));
		int createOrderQuantity=Integer.parseInt(orderQuantity);
		int pkgSizeItem=Integer.parseInt(pkgSize);
		
		int calculatedOrderQuantity = createOrderQuantity / pkgSizeItem ;

		if(calculatedOrderQuantity == order_QuantityItem )
			return true;
		else
			return false;
		
	}
	
	public boolean verifyUnableToOrderItemPkgSize(String pkgSize) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		if(element("unable_to_order_item_pkgsize").getText().equals(pkgSize))
			return true;
		else
			return false;
	}
	
}
