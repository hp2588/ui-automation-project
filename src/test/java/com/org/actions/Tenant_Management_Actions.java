package com.org.actions;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.itextpdf.text.log.SysoCounter;
import com.org.automation.getpageobjects.GetPage;

public class Tenant_Management_Actions extends GetPage {

	WebDriver driver;
	static String pagename = "Tenant_Management_Page";

	public Tenant_Management_Actions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public void verifyDropDownFieldOnViewAllOrderPage(String fieldName) {
		isElementDisplayed("dropdown_destination", fieldName);
		logMessage("[ASSERTION PASSED]: Verified dropdown field for " + fieldName);
	}

	public void navigateToTab(String tab) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 180);
		isElementDisplayed("tab_header_order", tab);
		wait.hardWait(5);
		element("tab_header_order", tab).click();
		logMessage("[ASSERTION PASSED]: User navigated to " + " " + tab);

	}

	public void selectDropdownForRO(String fieldName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		wait.hardWait(10);
		isElementDisplayed("dropdown_destination", fieldName);
		wait.waitForElementToBeClickable(element("dropdown_destination", fieldName));
		selectProvidedTextFromDropDown(element("dropdown_destination", fieldName), data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("dropdown_destination", fieldName)), data);
	}

	public void verifyColorOfOrder(String order, String bgColor, String type) {
		if (type.equalsIgnoreCase("item")) {
			String NewbgColor = "#" + bgColor;
			hover(element("RO_item", order));
			Assert.assertFalse(element("RO_item", order).getCssValue("background-color").equals(NewbgColor));

		} else {
			String NewbgColor = "#" + bgColor;
			hover(element("order_data", order));
			Assert.assertFalse(element("order_data", order).getCssValue("background-color").equals(NewbgColor));
		}

	}

	public void clickAvailableItemOnRO(String itemID, String itemName) {

		isElementDisplayed("RO_item", itemName);
		try {
			element("RO_item", itemName).click();
		} catch (Exception e) {
			clickUsingXpathInJavaScriptExecutor(element("RO_item", itemName));

		}
		Assert.assertTrue(element("items_RO_card").getText().trim().equalsIgnoreCase(itemName));

	}

	public String enterItemQuantityOnROCard(String data) {
		element("items_RO_quantity").clear();
		isElementDisplayed("items_RO_quantity");
		element("items_RO_quantity").sendKeys(data);
		Assert.assertTrue(element("items_RO_quantity").getAttribute("value").trim().equalsIgnoreCase(data));
		return data;
	}

	public void clickButton(String action) {
		try {
			wait.hardWait(5);
			isElementDisplayed("action_button", action);
			hover(element("action_button", action));
			wait.waitForElementToBeClickable(element("action_button", action));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("action_button", action));
			logMessage("Clicked on" + action);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		} catch (Exception e) {
			element("action_button", action).click();
			logMessage("Clicked on" + action);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		}
	}

	public String getOrderName(String fieldName) {
		isElementDisplayed("order_name", fieldName);
		return element("order_name", fieldName).getAttribute("value").trim();
	}

	public void verifyNewlyAddedOrderInTheList(String orderName, String status) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("order_data", orderName, status);
		Assert.assertTrue(element("order_data", orderName, status).isDisplayed());
		logMessage("[ASSERTION PASSED]: Newly added orderName : " + orderName + "is displayed in the List");

	}

	public String EnterRandomValueInInputFieldOnAddTenantMgtPage(String fieldName, String data) {
		isElementDisplayed("order_name", fieldName);
		enterTextInField(element("order_name", fieldName), data);
		Assert.assertEquals(element("order_name", fieldName).getAttribute("value").trim(), data);
		logMessage("ASSERTTION PASSED: Entered value");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		return data;
	}

	public void clickCheckbox(String toggle) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		element("action_toggle_button_1", toggle).click();
	}
	
	public void enterSearchTermInSearchField(String data, String field) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		isElementDisplayed("search_box", field);
		element("search_box", field).clear();
		// sendKeysUsingXpathInJavaScriptExecutor(element("search_box", field),
		// data);
		// enterTextInField(element("search_box", field), data);
		Actions action = new Actions(driver);

		Action buildAction = action.moveToElement(element("search_box", field)).click()
				.sendKeys(element("search_box", field), data).build();
		buildAction.perform();
		wait.hardWait(6);

		System.out.println("search...." + element("search_box", field).getAttribute("value").trim());
		// Assert.assertEquals(element("search_box", field).getAttribute("value"),
		// data);
		logMessage("[ASSERTION PASSED]: Search Term as : " + data + " is displayed in the Search Field");

	}

	public void verifyDropDownFieldOnWebOrderPage(String fieldName) {
		isElementDisplayed("dropdown_destination", fieldName);
		logMessage("[ASSERTION PASSED]: Verified dropdown field for " + fieldName);
	}
	
	public void selectDropdownForROWithIndex(String elementTextReplace, Integer Index) {

		try{
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		isElementDisplayed("dropdown_destination", elementTextReplace);
		Select selectValue = new Select(element("dropdown_destination", elementTextReplace));
		selectValue.selectByIndex(Index);
		logMessage("[STEP]: Value is selected from dropdown By index.");
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		}catch(Exception e){
			isElementDisplayed("second_sort", elementTextReplace);
			Select selectValue = new Select(element("dropdown_destination", elementTextReplace));
			selectValue.selectByIndex(Index);
			logMessage("[STEP]: Value is selected from dropdown By index.");
			wait.waitForLoaderToBeInvisible(getLocator("dropdown_destination"), 30);
			
		}
	}

	public void verifyButtonIsEnabled(String button) {

		isElementDisplayed("action_button",button);
		logMessage(button+ "is available on the RO Screen");
		Assert.assertTrue(element("action_button",button).isEnabled());
		
	}

	public void verifyPopupMessage(String mesg) {

		Assert.assertTrue(isElementDisplayed("popup_cancel_ro",mesg));

	}

	public List<String> verifyOrderList(String type) {
		List<String> data_list = new ArrayList<String>();
		if (type.equalsIgnoreCase("order")) {
			for (WebElement ele : elements("order_item_list")) {
				data_list.add(ele.getText());
			}
			return data_list;
		} else {
			for (WebElement ele : elements("item_list")) {
				data_list.add(ele.getText());
			}
		}

		return data_list;
	}
	

	public boolean verifyColumnHeader(String[] columnHeaders, int size) {
		boolean flag = false;
		int count = 0;
		for (String col : columnHeaders) {
			if (isElementDisplayed("sort_icon", col)) {
				count++;
			}
		}

		if (count == size) {
			flag = true;
		}
		return flag;
	}
	
	public ArrayList<String> sortDataForParticularColumnInAscendingOrder() {
		ArrayList<String> data_compare = captureDataForParticularColumn();
		Collections.sort(data_compare, String.CASE_INSENSITIVE_ORDER);
		return data_compare;
	}
	
	public ArrayList<String> captureDataForParticularColumn() {
		List<WebElement> elements = elements("text_column");
		 ArrayList<String> column_data = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			String data = elements.get(i).getText();
			column_data.add(data);
		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		return column_data;
	}
	
	public void verifyDefaultValueInRODropDown(String fieldName, String text) {
		isElementDisplayed("dropdown_destination", fieldName);
		verifySelectedTextFromDropDown(element("dropdown_destination", fieldName), text);
	}

	public boolean verifyOrderListIsEmpty() {
		// TODO Auto-generated method stub
		return isElementNotDisplayed("order_item_list");
	}

	public boolean verifyItemListIsAvailable() {
		
		
		return isElementNotDisplayed("item_list");
	}

	public boolean verifyItem(String itemName, String quantity) {
		
		return isElementDisplayed("item_details",itemName,quantity);
	}
	
	public String getNoDataText() {
		try {
			isElementDisplayed("text_no_data");

			System.out.println("text_no_data");
			return element("text_no_data").getText();
		} catch (Exception e) {
			isElementDisplayed("text_no_data_new");

			System.out.println("text_no_data_new");
			return element("text_no_data_new").getText();
		}
	}

	public void verifyValidationMessageIsNotDisplayed(String mesg) {
		Assert.assertFalse(isElementNotDisplayed("popup_cancel_ro",mesg));
		
	}
	
	public ArrayList<String> captureDataForParticularColumn(String j) {
		List<WebElement> elements = elements("column_path",j);
		 ArrayList<String> column_data = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			String data = elements.get(i).getText();
			column_data.add(data);
		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		return column_data;
	}

	public ArrayList<String> verifydataUniqueness(String string) {
		ArrayList<String> data_compare = captureDataForParticularColumn(string);
		ArrayList<String> new_list = new ArrayList<String>();
		Set<String> set = new HashSet<String>(data_compare);
		for (String s : set) {
			new_list.add(s);
		}
		return new_list;

	}

	public void clickCossButton() {
		isElementDisplayed("cross_button");
		element("cross_button").click();
		logMessage("Cross button is clicked");
		
	}

	public void clickPageFooter(String data) {
		isElementDisplayed("page_link",data);
		element("page_link",data).click();
	
		
	}

	public boolean verifyDestination(String destinationName) {
		
		return isElementDisplayed("destination_name_ro",destinationName);
	}
	
	public void verifyErrorMessage(String data) {
		isElementDisplayed("error_msg",data);
		//Assert.assertTrue(element("error_msg").getText().trim().equalsIgnoreCase(data));
	}

	public void verifyFocusIsHighlightedOnQuantity(String id) {
		Assert.assertEquals(element("text_quantity",id).getAttribute("id"),(driver.switchTo().activeElement().getAttribute("id")));

		
	}

	public String verifyDefaultItemQuantityOnROCard(String itemName) {
		
		
		isElementDisplayed("text_quantity",itemName);
		return element("text_quantity",itemName).getAttribute("value").trim();
		
	}

	public int getOrderCount(String itemName)
	{
		isElementDisplayed("ordred_quanity",itemName);
		return Integer.parseInt(element("ordred_quanity",itemName).getText().trim());
	}

	public void VerifyTabIsWorking(String code) {
	
		element("text_quantity",code).sendKeys(Keys.TAB);
		Assert.assertTrue(element("cross_button").isEnabled());
		element("cross_button").sendKeys(Keys.chord(Keys.SHIFT, Keys.TAB));
		Assert.assertTrue(element("text_quantity",code).isEnabled());
		
		
		
	}

	public int getItemCount() {

		isElementDisplayed("item_pagination");
		String count = element("item_pagination").getText().trim().replaceAll("Items", "");
		return Integer.parseInt(count.trim());
	}
	

	public void verifyVerticalScroll() {

	Assert.assertTrue(isElementDisplayed("vertical_scroll"));
		
	}

	public void verifyMessageWhenNoDestinationIsSelected() {
		Assert.assertTrue(isElementDisplayed("no_destination"));		
	}
	
	public void verifyAndClickSortIcon(String column) {
		isElementDisplayed("sort_icon", column);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("sort_icon", column));

		wait.waitForLoaderToBeInvisible(getLocator("loader"), 40);
	}
	public boolean verifySortIcon(String coulmn) {
		wait.hardWait(2);
		// Assert.assertTrue(isElementDisplayed("sort_icon", coulmn));
		return isElementDisplayed("sort_icon", coulmn);
	}
	

	public String getColumnFirstData(String col) {
		wait.hardWait(5);
		isElementDisplayed("first_col_data", col);
		return element("first_col_data", col).getText();
	}

	public void verifyNewlyAddedOrderInTheList(String orderName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("order_data_view_order", orderName);
		Assert.assertTrue(element("order_data_view_order", orderName).isDisplayed());
		logMessage("[ASSERTION PASSED]: Newly added orderName : " + orderName + "is displayed in the List");
		
	}
	
	public void verifyButtonIsDisabled(String button) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
		isElementDisplayed("action_button",button);
		Assert.assertFalse(element("action_button", button).isEnabled());
		
	}

	public void verifyTabs(String tab) {
		
		isElementDisplayed("tab_header_order", tab);
		logMessage("[ASSERTION PASSED]: User navigated to " + " " + tab);
		
	}
	
	public void verifySelectDestinationIsADropdown(String fieldName) {
		Assert.assertTrue(element("dropdown_destination", fieldName).getTagName().equalsIgnoreCase("Select"));
		logMessage("[ASSERTION PASSED]: Verified dropdown field for " + fieldName);
	}
	
	public ArrayList<String> sortDataForParticularColumnInDescendingOrder(String coulmn) {
		ArrayList<String> data_compare = captureDataForParticularColumn(coulmn);
		Collections.sort(data_compare, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
		return data_compare;
	}

	public void verifyDestinationNameFromPOCard(String DestinationName) {
	
		isElementDisplayed("destination_name");
		for(WebElement ele:elements("destination_name"))
		{
			Assert.assertTrue(ele.getText().trim().equalsIgnoreCase("Destination:"+DestinationName));
			
		}
		
	}


	public void clearInputField(String fieldname) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		clickUsingXpathInJavaScriptExecutor(element("order_name",fieldname));
		element("order_name",fieldname).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		wait.hardWait(3);
		logMessage("Text Cleared");
	}

	public int verifyMaxLengthOfAnInputField(String fieldName) {
		return Integer.parseInt(element("order_name", fieldName).getAttribute("maxlength").trim());
	}
	
	public void verifyOrderFields(String OrderName) {
		
		
		
	}

	public void clickCheckboxPO(String itemName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("item_checkbox",itemName);
		clickUsingXpathInJavaScriptExecutor(element("item_checkbox",itemName));
	}

	public void verifySuccessMessage(String string) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("success_mesg",string);
		
	}
	
	public void updateOrderQuantity(String itemName, String quantity) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("item_quantity_po",itemName);
		element("item_quantity_po",itemName).sendKeys(quantity);
		Assert.assertTrue(element("item_quantity_po",itemName).getText().trim().equals(quantity));
	}

	public void verifySelectedItemInPO(String itemName) {
		for(WebElement ele:elements("item_list_po",itemName))
		{
			Assert.assertTrue(ele.getText().trim().equalsIgnoreCase(itemName));
		}
		
		
	}

	public String getItemDetails(String itemName, String index) {
		return  element("order_quantity_multiples",itemName,index).getText().trim();
	}

	public int enterItemQuantityInRnSOrder(String package_size) {
		int count = Integer.parseInt(package_size);
		int new_count =count * 3 ;
		
		element("items_RO_quantity").clear();
		isElementDisplayed("items_RO_quantity");
		element("items_RO_quantity").sendKeys(String.valueOf(new_count));
		Assert.assertTrue(element("items_RO_quantity").getAttribute("value").trim().equalsIgnoreCase(String.valueOf(new_count)));
		return new_count;
		
	}

	public void verifyOrderDetails(String string, String itemName, String index) {
		
		Assert.assertTrue(isElementDisplayed("item_details_orderpage",itemName,index));
		
	}
	
	public void verifyOrderDetails(String string, String itemName) {
		
		Assert.assertTrue(isElementDisplayed("item_desc",itemName));
	}

	public void verifyPage(String page_heading) {
		isElementDisplayed("page_title");
		Assert.assertTrue(element("page_title").getText().equals(page_heading));		
	}

	public boolean verifyRightPanelIsEmpty() {
		
		return isElementDisplayed("right_panel");
	}

	public boolean verifyItem(String itemName) {
		// TODO Auto-generated method stub
		isElementDisplayed("RO_item",itemName);
		return element("RO_item",itemName).getText().matches("^[a-zA-Z0-9]*$");
	}

	
	
	public ArrayList<String> captureDataForParticularColumnOrderPage(String j) {
		List<WebElement> elements = elements("order_page_colum_data",j);
		 ArrayList<String> column_data = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			String data = elements.get(i).getText();
			column_data.add(data);
		}
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 80);
		return column_data;
	}
	
	public void verifyColorOfOrder(String order, String bgColor, String type, String status) {
		if (type.equalsIgnoreCase("item")) {
			String NewbgColor = "#" + bgColor;
			hover(element("RO_item", order));
			Assert.assertFalse(element("RO_item", order).getCssValue("background-color").equals(NewbgColor));

		} else {
			String NewbgColor = "#" + bgColor;
			hover(element("order_data", order,status));
			Assert.assertFalse(element("order_data", order,status).getCssValue("background-color").equals(NewbgColor));
		}

	}

	public void NavigateToMultitenancyPortal(String url) {
		//String newURL = driver.getCurrentUrl() + "/" +url;
		driver.navigate().to(url);
	}

	public void clickButtonWithText(String action) {
		try {
			wait.hardWait(5);
			isElementDisplayed("text_button", action);
			hover(element("text_button", action));
			wait.waitForElementToBeClickable(element("text_button", action));
			clickUsingXpathInJavaScriptExecutorSingleClick(element("text_button", action));
			logMessage("Clicked on" + action);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		} catch (Exception e) {
			element("text_button", action).click();
			logMessage("Clicked on" + action);
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		}
		
	}
	
	public void verifyTQisNotAvailable(String featureName) {
		
		Assert.assertFalse(isElementNotDisplayed("link_features_name", featureName.trim()));
		

	}

}
