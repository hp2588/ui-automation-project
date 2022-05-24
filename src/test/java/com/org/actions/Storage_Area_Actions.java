
package com.org.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.remote.server.handler.interactions.DoubleClickInSession;
import org.testng.Assert;

import com.aventstack.extentreports.externalconfig.model.Config;
import com.org.automation.getpageobjects.GetPage;
import com.org.automation.utils.ConfigPropertyReader;

public class Storage_Area_Actions extends GetPage {
	WebDriver driver;
	static String pagename = "Storage_Area_Popup_Page";
	private boolean flag = false;
	
	public Storage_Area_Actions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}
	
	
	public boolean verifyUserIsOnStorageAreaPopupPage() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 200);
		wait.waitForElementToBeVisible(element("div_modal_body"));
		isElementDisplayed("div_modal_body");
		verifyElementTextContains("storage_area_text", "Select ISAs (Inventory Storage Areas)");
		flag = true;
		return flag;
	}
	
	
	public boolean selectMultipleISA() {
		List<WebElement> elements = elements("storage_area_checkbox");
		for (WebElement ele : elements) {
			checkCheckbox(ele);
			flag = true;
		}
		return flag;
	}
	
	
	public boolean navigateToSelectISAPage() {
		flag = false;
		selectProvidedTextFromDropDown(element("printer_list"), "tester 123");
		if (selectStartWork()) {
			if (selectActions())
				navigateToChooseISA();
			wait.waitForElementToBeVisible(element("storage_area_text", "Select Storage Areas in"));
			verifyElementTextContains("storage_area_text", "Select Storage Areas in");
			logMessage("User is navigated back to Select ISA Page");
			flag = true;
		} else
			flag = false;
		return flag;
	}
	
	
	private boolean navigateToChooseISA() {
		if (isElementDisplayed("link_chooseISA")) {
			logMessage("Verified choose_ISA link on Transaction Queue Page");
			clickUsingXpathInJavaScriptExecutor(element("link_chooseISA"));
			logMessage("Clicked on choose_ISA link");
			flag = true;
		} else
			flag = false;
		return flag;
	}
	
	
	public boolean selectActions() {
		if (isElementDisplayed("btn_actions")) {
			logMessage("Verified Actions button on Transaction Queue Page");
			clickUsingXpathInJavaScriptExecutor(element("btn_actions"));
			logMessage("Clicked on Actions button");
			flag = true;
		} else
			flag = false;

		return flag;

	}

	public boolean selectStartWork() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 50);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		if (isElementDisplayed("btn_startwork")) {
			logMessage("Verified Start Work button on BD Home Page");
			clickUsingXpathInJavaScriptExecutor(element("btn_startwork"));
			logMessage("Clicked on Start Work button");
			flag = true;
		}

		else
			flag = false;

		return flag;
	}

	public boolean transactionCountIsVisibleOnUI(String isaname, String priority) {
		List<WebElement> ele = elements("isa_name");
		StringBuilder priorityList = null;
		for (WebElement e : ele) {
			priorityList.append(e.getText());
		}
		if (priorityList.equals(priority))
			flag = true;
		else
			flag = false;
		return flag;

	}

	public int verifyListOfAvailableISAsOnStorageAreaPage() {
		isElementDisplayed("list_isa");
		return elements("list_isa").size();
	}

	public String verifyFacilityNameOnStorageAreaPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("txt_facility");
		return element("txt_facility").getText().trim();
	}
	
	public String getFacilityFromWFAScreen() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("facility_on_WFA");
		String facilityName = element("facility_on_WFA").getText().substring(11);
		
		logMessage("Facility Name " + facilityName + " has been extracted from ISA Screen.");
		return facilityName;
	}

	public boolean verifyCheckboxesForEachISAsOnStorageAreaPage() {
		List<WebElement> listChkboxIsa = elements("list_chkbox_isa");
		for (WebElement chkboxIsa : listChkboxIsa) {
			if (!(chkboxIsa.isDisplayed()))
				return false;
		}
		return true;
	}

	public void verifyUserIsAbleToSelectCheckboxesForAvailableISAs() {
		int i = 0;
		List<WebElement> listChkboxIsa = elements("list_chkbox_isa");
		for (WebElement chkboxIsa : listChkboxIsa) {
			if (checkboxIsSelectedUsingJavascript(i + "-isaCheckbox")) {
				System.out.println(i + " check box is already selected");
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(i + "-isaCheckbox"));
			} else {
				try {
					chkboxIsa.click();
				} catch (ElementClickInterceptedException e) {
					chkboxIsa.click();
				} catch (Exception e) {
					chkboxIsa.click();
				}
				System.out.println("Seleted " + i + " checkbox");
				Assert.assertTrue(checkboxIsSelectedUsingJavascript(i + "-isaCheckbox"));
			}
			i++;
		}
		logMessage(
				"[ASSERTION PASSED]: Verified User is able to select all checkboxes for available ISA on Storage Area Pop up");
	}

	public boolean verifyUserIsNotAbleToSelectCheckboxForCarouselIfUsedByAnotherUser(String isaName) {
		isElementDisplayed("chkbox_isa", isaName);
		if (element("chkbox_isa", isaName).isEnabled()) {
			return false;
		}
		return true;
	}

	public boolean UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 50);
		int i = 0;
		List<WebElement> listChkboxIsa = elements("list_chkbox_isa");
		for (WebElement chkboxIsa : listChkboxIsa) {
			if (checkboxIsSelectedUsingJavascript(i + "-isaCheckbox")) {
				try {
					chkboxIsa.click();
				} catch (ElementClickInterceptedException e) {
					chkboxIsa.click();
				} catch (Exception e) {
					chkboxIsa.click();
				}
				hardWaitForIEBrowser(3);
				Assert.assertFalse(checkboxIsSelectedUsingJavascript(i + "-isaCheckbox"));
			} else {
				Assert.assertFalse(checkboxIsSelectedUsingJavascript(i + "-isaCheckbox"));
			}
			i++;
		}
		logMessage("Unchecked all the checkboxes for available ISA");
		isElementDisplayed("btn_startWork");
		if (element("btn_startWork").isEnabled()) {
			return false;
		}
		return true;
	}
	
	public void verifyTransactionCountForTopFourTransactionPrioritiesForEachAvailableISA(Integer totalISA) {
		for (int i = 1; i <= totalISA; i++) {
			for (WebElement ele : elements("list_attribute_isa", i + "")) {
				Assert.assertTrue(ele.isDisplayed());
				Assert.assertTrue(ele.getText().trim() != null);
			}
		}
	}
	
	public int getCountofISAs() {
		// elements("list_isa")
		return elements("list_isa").size();
	}

	public boolean verifyCheckboxSelectedForISA(int i) {
		return checkboxIsSelectedUsingJavascript(i + "-isaCheckbox");
	}

	public void verifyManualUseOptionForNonStaticISA(String isa_name) {
		isElementDisplayed("text_manual_use", isa_name);
		boolean expected = true;
		boolean result1 = element("text_manual_use", isa_name).isDisplayed();
		System.out.println("value of result1 : " + element("text_manual_use", isa_name).isDisplayed());
		Assert.assertEquals(result1, expected);
		logMessage("[ASSERTION PASSED] : Verified Manual Use option for non-static" + isa_name);
	}

	public String verifyMappedPrinterCorresspondingToISA(String isaName) {
		isElementDisplayed("drpdown_printer", isaName);
		return getSelectedTextFromDropDown(element("drpdown_printer", isaName));
	}

	public void clickOnStartWorkButton() {
		isElementDisplayed("btn_startWork");
		element("btn_startWork").click();
		logMessage("Clicked on Start Work button");
	}
	
	public List<String> getTheTopFourPriorityListValueFromGridViewForAllAvailableISA(ArrayList<String> priorityList,
			Integer totalISA) {
		List<String> gridViewList = new ArrayList<String>();
		
		for (int i = 1; i <= totalISA; i++) {
			List<WebElement> priorityTxnCount =   elements("list_attribute_isa", String.valueOf(i));
			System.out.println("ISA "+ i + "; priority count: " + priorityTxnCount.size());
			
			List<WebElement> prioritiesOnISACard = elements("grid_isa_priority_txn_count", String.valueOf(i));
			for(WebElement elem : prioritiesOnISACard) {
				System.out.println(elem.getText());
			}
			
			if(prioritiesOnISACard.size() > 0 && prioritiesOnISACard.get(0).getText().contains(priorityList.get(0))) {
				gridViewList.add(priorityTxnCount.get(0).getText().trim());
			} else {
				gridViewList.add("0");
			}
			if(prioritiesOnISACard.size() > 1 && prioritiesOnISACard.get(1).getText().contains(priorityList.get(1))) {
				gridViewList.add(priorityTxnCount.get(1).getText().trim());
			} else {
				gridViewList.add("0");
			}
			if(prioritiesOnISACard.size() > 2 && prioritiesOnISACard.get(2).getText().contains(priorityList.get(2))) {
				gridViewList.add(priorityTxnCount.get(2).getText().trim());
			} else {
				gridViewList.add("0");
			}
			if(prioritiesOnISACard.size() > 3 && prioritiesOnISACard.get(3).getText().contains(priorityList.get(3))) {
				gridViewList.add(priorityTxnCount.get(3).getText().trim());
			} else {
				gridViewList.add("0");
			}
		}
		
		return gridViewList;
	}
	
	
	public void switchToListViewOfISA() {
		isElementDisplayed("div_by_id", "list");
		element("div_by_id", "list").click();
		Assert.assertTrue(element("list_view_option").getAttribute("class").contains("selected"));
		Assert.assertTrue(element("div_list").getAttribute("class").contains("ListWhiteImg"));
	}
	
	
	public WebElement getDivById(String id) {
		return element("div_by_id", id);
	}
	
	
	public List<String> verifyTransactionCountForTransactionIsMatchingInGridAndListView(
			ArrayList<String> priorityList, Integer totalISA) {
		List<String> listViewList = new ArrayList<String>();
		System.out.println("List View: ");
		
		// verify that all four priorities are shown in list headers  
		int i = 0;
		for(WebElement ele : elements("popup_header_columns")) {
			System.out.println("Header: " + ele.getText());
			if(ele.getText().contains(priorityList.get(i)))  
				i++;
			if(i == priorityList.size())  
				break;
		}
		Assert.assertEquals(i, priorityList.size());
		
		for (i = 1; i <= totalISA; i++) {
				for(WebElement elem : elements("data_list_view", String.valueOf(i))) {
					listViewList.add(elem.getText().trim());
				}
		}
		
		return listViewList;
	}
	
	public boolean verifyUserIsAbleToSelectManualUseOptionForISA(String isaName) {
		isElementDisplayed("option_manualUse", isaName);
		click(element("click_manualUse", isaName));
		// clickUsingXpathInJavaScriptExecutor(element("click_manualUse", isaName));
		return element("option_manualUse", isaName).isEnabled();
	}

	public void verifyManualUseOptionForISANotAvailable(String isaName) {
		 isElementNotDisplayed("option_manualUse", isaName);
	}
	
	public void selectCheckboxForISA(String isa_name, int i) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 75);
		
		isElementDisplayed("chkbox_isa", isa_name);
		wait.waitForElementToBeClickable(element("chkbox_isa", isa_name));
		if (checkboxIsSelectedUsingJavascript(i + "-isaCheckbox")) {
			Assert.assertTrue(checkboxIsSelectedUsingJavascript(i + "-isaCheckbox"));
		} else {
			try {
				element("chkbox_isa", isa_name).click();
			} catch (ElementClickInterceptedException e) {
				clickUsingXpathInJavaScriptExecutor(element("chkbox_isa", isa_name));
			} catch (Exception e) {
				clickUsingXpathInJavaScriptExecutor(element("chkbox_isa", isa_name));
			}
			Assert.assertTrue(checkboxIsSelectedUsingJavascript(i + "-isaCheckbox"));
		}
		logMessage("ISA " + isa_name + " is checked");
	}
	
	public boolean isCheckboxSelected(int i) {
		return checkboxIsSelectedUsingJavascript(i + "-isaCheckbox");
	}
	
	public boolean isCheckboxSelected(String isaShortName) {
		isElementDisplayed("chkbox_isa", isaShortName);
		int isaIdx = Integer.parseInt(element("chkbox_isa", isaShortName).getAttribute("for").split("-")[0]);
		System.out.println("index of ISA " + isaShortName + " is " + isaIdx);
		return isCheckboxSelected(isaIdx);
	}
	
	public void clickISACheckbox(String isaShortName) {
		isElementDisplayed("chkbox_isa", isaShortName);
		try {
			element("chkbox_isa", isaShortName).click();
		} catch(Exception e) {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
			clickUsingXpathInJavaScriptExecutorSingleClick(element("chkbox_isa", isaShortName));
		}
	}
	
	public void clickISACheckbox(String isaShortName, boolean b) {
		isElementDisplayed("chkbox_isa", isaShortName);
		try {
			element("chkbox_isa", isaShortName).click();
		} catch(Exception e) {
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
			clickUsingXpathInJavaScriptExecutorSingleClick(element("chkbox_isa", isaShortName));
		}
	}
	
	public String getISACardBackgroundColor(int i) {
		isElementDisplayed("chkbox_isa_background", String.valueOf(i+1));
		String bgColor = element("chkbox_isa_background", String.valueOf(i+1)).getCssValue("background-color");
		return bgColor;
	}
	
	public boolean verifyStaticISA(String isa_name) {
		
		return isElementNotDisplayed("text_manual_use", isa_name);
		
	}

	public void verifyToggleButtonEnabledForNonStaticISA(String isa_name) {
		boolean expected = true;
		boolean result2 = element("text_manual_use", isa_name).isSelected();
		System.out.println("value of result2 : " + element("text_manual_use", isa_name).isSelected());
		Assert.assertEquals(result2, expected);
		logMessage("[ASSERTION PASSED] : Verified Manual Use option and toggle button is enabled for non-static"
				+ isa_name);
	}

	public void verifyStartWorkButtonAndClick() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 90);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 200);
		isElementDisplayed("btn_startWork");
		wait.waitForElementToBeClickable(element("btn_startWork"));
		logMessage("Start work button is displayed");
		if(isBrowser("ie11") || isBrowser("IE11") || isBrowser("IE") || isBrowser("edge"))
		{
			Actions action=new Actions(driver);
			wait.hardWait(10);
			action.moveToElement(element("btn_startWork")).doubleClick().build().perform();
		} else {
			clickUsingXpathInJavaScriptExecutorSingleClick(element("btn_startWork"));
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 100);
			logMessage("Clicked on Start work button");
		}
	}

	public void switchToGridViewOfISA() {
		isElementDisplayed("grid_view_option");
		element("grid_view_option").click();
		Assert.assertTrue(element("grid_view_option").getAttribute("class").contains("selected"));
		Assert.assertTrue(element("div_grid").getAttribute("class").contains("GridWhiteImg"));
	}

	public List<String> getListOfPrintersCorresspondingToISA(String isaName) {
		List<String> printerList = new ArrayList<String>();
		List<WebElement> listPrinter = getAllOptionsFromDropDown(element("drpdown_printer", isaName));
		for (WebElement ele : listPrinter) {
			printerList.add(ele.getText().trim());
		}
		Collections.sort(printerList);
		return printerList;
	}
	
	public String getSelectedPrinterOfISA(String isaName) {
		List<String> printerList = new ArrayList<String>();
		List<WebElement> listPrinter = getAllOptionsFromDropDown(element("drpdown_printer", isaName));
		for (WebElement ele : listPrinter) {
			if(ele.isSelected()) {
				return ele.getText().trim();
			}
		}
		return null;
	}

	public void selectPrinterForSelectedISA(String data2) {
		isElementDisplayed("dropdown_printerList");
		selectProvidedTextFromDropDown(element("dropdown_printerList"), data2);
	}

	public void verifyModelIsDisabled() {

		String value = element("save_button").getAttribute("disabled");
		Assert.assertEquals(value, "true");
	}

	public void verifyTabIsDisabled(String tabName) {
		String value = element("tab_link",tabName).getAttribute("disabled");
		Assert.assertEquals(value, "true");
	}

	public void clickEditLink(String isa) {
		wait.loadingWait(getLocator("loader"));
		isElementDisplayed("link_edit_isa", isa);
		element("link_edit_isa", isa).click();
		// Assert.assertTrue(element("page_heading", "Edit Inventory Storage
		// Area").isDisplayed());
	}
	
	public void clickApprovedComputerLink(String approvedComputer, String modelName, String action) {
		wait.loadingWait(getLocator("loader"));

		if (action.equalsIgnoreCase("Edit"))
		{
			isElementDisplayed("link_edit_approvedComputer", approvedComputer,action);
			element("link_edit_approvedComputer", approvedComputer,action).click();
		}
		
		if (action.equalsIgnoreCase("Delete"))
		{
			isElementDisplayed("link_edit_approvedComputer", approvedComputer,action);
			clickUsingXpathInJavaScriptExecutorSingleClick(element("link_edit_approvedComputer", approvedComputer,action));
		}
		
		
	}

	public void clickTab(String fieldName) {
		wait.loadingWait(getLocator("loader"));
		wait.hardWait(5);
		isElementDisplayed("tab_link", fieldName);
		clickUsingXpathInJavaScriptExecutor(element("tab_link", fieldName));
		logMessage("Tab " + fieldName + "is clicked");

	}

	public void verifyInputField(String fieldName) {
		isElementDisplayed("inp_field_isa", fieldName);
		logMessage("[ASSERTION PASSED]: Verified input field for " + fieldName);
	}

	public String enterDataInInputField(String fieldName, String data) {

		isElementDisplayed("inp_field_isa", fieldName);
		enterTextInField(element("inp_field_isa", fieldName), data);
		Assert.assertTrue(element("inp_field_isa", fieldName).getAttribute("value").trim().equals(data));
		logMessage("[STEP]: Data entered in Input field.");

		return data;
	}

	public void verifyNewlyAddedISAName(String printerName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		isElementDisplayed("added_isa_name", printerName);
		//Assert.assertTrue(element("added_isa_name", printerName).isDisplayed());
		logMessage("[ASSERTION PASSED]: Newly added ISA : " + printerName + " is displayed in the List");
	}

	public void clickSaveButton() {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		scrollUp();
		
		isElementDisplayed("save_button");
		element("save_button").click();
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 180);
		
		logMessage("Clicked on Save button ");
	}

	public void clearInputBox(String fieldname) {

		clickUsingXpathInJavaScriptExecutor(element("inp_field_isa", fieldname));
		element("inp_field_isa", fieldname).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		
	}

	public boolean verifyFieldIsMandatory(String fieldName) {
		isElementDisplayed("icon_mandatory", fieldName);
		if (element("icon_mandatory", fieldName).getText().trim().contains("*")) {
			return true;
		}
		return false;
	}

	public void verifyErrorMessageonAlert(String message) {
		wait.hardWait(7);
		String spanMessage = element("message_field").getText();
		System.out.println("Value of message:  " + spanMessage);
		Assert.assertEquals(spanMessage, message);

	}

	public void refreshPage() {
		pageRefresh();
		wait.loadingWait(getLocator("loader"));
	}

	public boolean verifyCheckboxIsCheckedApprovedComputer(String name)
	{
		isElementDisplayed("label_approvedComputers", name);
		wait.waitForElementToBeClickable(element("label_approvedComputers", name));
		String id = element("label_approvedComputers", name).getAttribute("for").trim();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		System.out.println("return document.getElementById('" + id + "').checked;");
		return (boolean) executor.executeScript("return document.getElementById('" + id + "').checked;");
	}
	
	
	public void clickCheckboxTransactionPriorities(String flagname) {
		 isElementDisplayed("label_approvedComputers", flagname);
		 //click(element("label_TransactionPriorities", flagname));
		 clickUsingXpathInJavaScriptExecutorSingleClick(element("label_approvedComputers", flagname));
	}
	
	
	public void verifyButtonIsEnabled(String text) {
		Assert.assertTrue(element("button_by_text", text).isEnabled());
		logMessage("Button" + text + "is enabled");		
	}
	
	
	public boolean isButtonWithTextEnabled(String text) {
		return element("button_by_text", text).isEnabled();
	}
	
	
	public boolean isButtonWithTextDisplayed(String text) {
		return isElementNotDisplayed("button_by_text", text);
	}
	
	
	public void clickButtonOnAddPopUp(String name) {
		isElementDisplayed("modal_approvedComputer",name);
		element("modal_approvedComputer",name).click();
		logMessage("Button " + name + "  is clicked");
	}
	
	
	public void clickRadioLabelByText(String text) {
		isElementDisplayed("label_radio_by_text", text);
		element("label_radio_by_text", text).click();
		logMessage("Radio button with text '" + text + "' is clicked");
	}
	
	
	public void selectDefaultValueFromDropDown(String fieldName, String data) {
		isElementDisplayed("dropdown_approvedComputer", fieldName);
		selectProvidedTextFromDropDown(element("dropdown_approvedComputer",fieldName), data);
		logMessage("Vale" +data+ "is chosen");
		Assert.assertEquals(getSelectedTextFromDropDown(element("dropdown_approvedComputer", fieldName)), data);
	}
	
	public String getTitleForTheISACard(String shortISAName) {
		return element("isa_card_by_name", shortISAName).getAttribute("title").trim();
	}
	
	public void addApprovedComputersByClickingonPopup(String action) {
		
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 50);
		wait.waitForLoaderToBeInvisible(getLocator("mini_loader"), 100);
		isElementDisplayed("modal_approvedComputer",action);
		element("modal_approvedComputer",action).click();
		logMessage("Button '" +action+ "' is clicked");
		
	}
	
	
	public boolean verifyDropDownIsEnabledOrDisabled(String string) {
		isElementDisplayed("dropdown_approvedComputer", string);
		boolean value = element("dropdown_approvedComputer", string).isEnabled();
		return value;
	}
	
	
	public void verifyNewlyAddedRecordNameInList(String computerName, String printerName) {
		isElementDisplayed("added_approved_computer", computerName);
		Assert.assertTrue(element("added_approved_computer", computerName).getText().equalsIgnoreCase(computerName));
		logMessage("[ASSERTION PASSED]: Newly added record : " + computerName + " is displayed in the List");
	}
	
	

	public void verifyApprovedComputerPopupPage(String popupText) {
		wait.loadingWait(getLocator("loader"));
		wait.waitForElementToBeVisible(element("modal_approvedComputerPopup"));
		isElementDisplayed("modal_approvedComputerPopup");
		Assert.assertTrue(element("modal_approvedComputerPopup").getText().equalsIgnoreCase(popupText));

	}
	
	public void verifyRecordNameIsNotAvailableInTheList(String computerName) 
	{
		Assert.assertFalse(isElementNotDisplayed("added_approved_computer", computerName));

	}
	
	public void verifyRecordNameIsAvailableInTheList(String computerName) 
	{
		Assert.assertTrue(isElementDisplayed("added_approved_computer", computerName));

	}

	public void clickButtonOnApprovedComputerPage(String buttonName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		//wait.hardWait(10000);
		isElementDisplayed("button_by_text",buttonName);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("button_by_text",buttonName));

		logMessage("Button" +buttonName+ "is clicked");
		wait.hardWait(5);
	}

	

	public String getExternalISAName() {

		isElementDisplayed("first_external_isa");
		return element("first_external_isa").getText().trim();
	}
	
	public void clickButton(String buttonName) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 120);
		isElementDisplayed("button_by_text", buttonName);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("button_by_text",buttonName));
		
		logMessage("Button " + buttonName + " is clicked");
	}

	public String getISANameOnWFAScreen() {
		isElementDisplayed("ISA_nam_on_WFA_screen");
		logMessage("ISA on WFA Screen is : "+element("ISA_nam_on_WFA_screen").getText());
		return element("ISA_nam_on_WFA_screen").getText();
	}

	public boolean verifyTransactionCountForReceivingTransactionForAvailableISA(String ISAName) {
		//isElementDisplayed("data_trans",ISAName);
		for (WebElement ele : elements("data_trans",ISAName)) {
			if(ele.getText().contains("Receiving")) {
				return true;
			}
		}
		return false;
	}

	public boolean verifyTransactionCountForCycleCountTransactionForAvailableISA(String ISAName) {
		//isElementDisplayed("data_trans",ISAName);
		for (WebElement ele : elements("data_trans",ISAName)) {
			if(ele.getText().contains("Cycle Count")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean verifyPackageShareTransactionForAvailableISA(String ISAName) {
		//isElementDisplayed("data_trans",ISAName);
		for (WebElement ele : elements("data_trans",ISAName)) {
			if(ele.getText().contains("Package Share")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean verifyReceivingTransactionForAvailableISA(String ISAName) {
		//isElementDisplayed("data_trans",ISAName);
		for (WebElement ele : elements("data_trans",ISAName)) {
			if(ele.getText().contains("Receiving")) {
				return true;
			}
		}
		return false;
	}
	
	public void verifyButtonIsDisplayed(String btnTxt) {
		isElementDisplayed("button_by_text", btnTxt);
		logMessage("[ASSERTION PASSED]: Button containing text " + btnTxt + " is displayed");
	}

	public int verifyTableOfAvailableISAsOnStorageAreaPage() {
		isElementDisplayed("row_isa");
		return elements("row_isa").size();
	}

	public List<Integer> getTheCountOfVisiblePrioritiesFromGridViewForAllAvailableISA(Integer totalISA) {
		List<Integer> gridViewList = new ArrayList<Integer>();
		for (int i = 1; i <= totalISA; i++) {
			System.out.println("Size for " + i + " ISA " + elements("list_attribute_isa", i + "").size());
			gridViewList.add(elements("list_attribute_isa", i + "").size());
		}

		return gridViewList;
	}	

	public ArrayList<ArrayList<String>> getTheVisiblePrioritiesFromGridViewForAllAvailableISA(Integer totalISA) {
		ArrayList<ArrayList<String>> gridViewList = new ArrayList<ArrayList<String>>();
		
		// grid_isa_priority_txn_count
		for (int i = 1; i <= totalISA; i++) {
			System.out.println(" ISA " + String.valueOf(i) + " - priority count: " + elements("grid_isa_priority_txn_count", i + "").size());
			
			ArrayList<String> priorityList = new ArrayList<String>();
			for(WebElement ele: elements("grid_isa_priority_txn_count", String.valueOf(i))) {
				priorityList.add(ele.getText().trim().replaceAll("^\\d+(?!$)", ""));
			}
			System.out.println("ISA " + String.valueOf(i) + " priorities: " + priorityList);
			gridViewList.add(priorityList);
		}
		
		return gridViewList;
	}
	
	public void verifyNoPicKErrorMessage(int isaNumber, String errorMsg) {
		isElementDisplayed("no_pick_error", "" + isaNumber, errorMsg);
	}

	public String verifyDefaultValueofInputField(String fieldname, String string2) {
		isElementDisplayed("inp_field_isa",fieldname);
		return element("inp_field_isa",fieldname).getAttribute("value").trim();
	}
	
	public boolean verifyNoReceivingTransactionForAvailableISA(String ISAName) {
		//isElementDisplayed("data_trans",ISAName);
		for (WebElement ele : elements("data_trans",ISAName)) {
			if(ele.getText().contains("Receiving")) {
				return false;
			}
		}
		return true;
	}

	public String selectPrinterUsingKeyboard(String isaName, String data) {
		isElementDisplayed("printer_dropdown_for_isa", isaName);
		element("printer_dropdown_for_isa", isaName).sendKeys(data);
		element("printer_dropdown_for_isa", isaName).sendKeys(Keys.ENTER);
		return getSelectedTextFromDropDown(element("printer_dropdown_for_isa", isaName));
	}
	
	public void verifyValueExistinDropDown(String ISAShortName, String data) {
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 60);
		isElementDisplayed("drpdown_printer", ISAShortName);
		wait.waitForElementToBeClickable(element("drpdown_printer", ISAShortName));
		selectProvidedTextFromDropDown(element("drpdown_printer", ISAShortName), data);
		Assert.assertEquals(getSelectedTextFromDropDown(element("drpdown_printer", ISAShortName)), data);
	}
	
	public boolean verifyCarousalISAIsDisabledInGridView(String fieldName) {
		
		isElementDisplayed("check_disabled_isa_gridview",fieldName);
		return element("check_disabled_isa_gridview",fieldName).getAttribute("class").contains("checkedDataGrey");
	}
	
	public boolean verifyCarousalISAIsDisabledInListView(String fieldName) {
			isElementDisplayed("list_view_option");
			element("list_view_option").click();
			
			Assert.assertTrue(element("list_view_option").getAttribute("class").contains("selected"));
			return element("check_disabled_isa_listview",fieldName).getAttribute("class").contains("checkedDataGrey");
	}
	
	
}

