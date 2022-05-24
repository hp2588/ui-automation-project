package com.org.actions;

import java.util.List;

import org.openqa.selenium.ElementClickInterceptedException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.org.automation.getpageobjects.GetPage;

public class LocationAssignmentSettings_Actions extends GetPage {

	WebDriver driver;
	static String pagename = "locationAssignment_page";
	private boolean flag = true;

	public LocationAssignmentSettings_Actions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
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

	public void verifyDropdownFieldAreDisabled(String fieldName) {
		isElementDisplayed("dropdown_printer", fieldName);
		boolean value = element("dropdown_printer", fieldName).isEnabled();
		Assert.assertEquals(value, false);
		logMessage("[ASSERTION PASSED]: Input field " + fieldName + " is disabled");

	}

	public void clickToDelete_VerifyProductID(String productID) {
		isElementDisplayed("delete_productID", productID);
		clickUsingXpathInJavaScriptExecutorSingleClick(element("delete_productID", productID));
		wait.waitForLoaderToBeInvisible(getLocator("loader"), 30);
		Assert.assertFalse(isElementDisplayed("delete_productID", productID));
	}

	public boolean editLocation() {

		try {

			isElementDisplayed("editButton");
			logMessage("ASSERTION PASSED : Verified user is on Location Management page");
			Actions action = new Actions(driver);
			Action seriesOfAction = (Action) action.moveToElement(element("editButton")).click().build();
			seriesOfAction.perform();
			logMessage("Location has been eddited.");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}
	}

	public boolean verifyDefaultRules() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 1);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			isElementDisplayed("defaultRuleDropdown");
			logMessage("User can see the defaul rule options.");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}

	public void clickAssignLocationsButton() {
		wait.hardWait(15);
		isElementDisplayed("assign_location_btn");
		Actions action = new Actions(driver);
		Action seriesOfAction = (Action) action.moveToElement(element("assign_location_btn")).click().build();

		seriesOfAction.perform();
		// clickUsingXpathInJavaScriptExecutor(element("assign_location_btn"));
		logMessage("ASSERT PASSED: Clicked on Assign Location button");
	}

	public boolean viewRuleList() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 1);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			isElementDisplayed("defaultRuleDropdown");
			selectProvidedTextFromDropDownUsingIndex(element("defaultRuleDropdown"), 1);
			element("defaultRuleDropdown").getText();
			logMessage("User can see the defaul rule options.");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}

	public boolean ReplenishSetToNone() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 1);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			isElementDisplayed("defaultRuleDropdown");
			selectProvidedTextFromDropDownUsingIndex(element("defaultRuleDropdown"), 1);
			element("defaultRuleDropdown").getText();
			logMessage("User can see the defaul rule options.");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}

	public boolean VerifyReplenish() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 1);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			isElementDisplayed("ReplenishOption");
			logMessage("User can see the Replenish options on edit item screen.");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}

	public boolean verifyEditButtonFunction() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 1);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}

	public boolean chooseReplenishOptions() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 1);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			isElementDisplayed("ReplenishOption");
			selectProvidedTextFromDropDownUsingIndex(element("SelectReplenish"), 1);
			logMessage("User is able to set Replenish Options");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}

	public boolean verificationOfEditExisitingRules() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			isElementDisplayed("ReplenishOption");
			selectProvidedTextFromDropDownUsingIndex(element("SelectReplenish"), 2);
			logMessage("User is able edit the existing rules.");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}

	public boolean verifyRulesAreViewable() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			isElementDisplayed("viewRuleButton");
			click(element("viewRuleButton"));
			isElementDisplayed("rulesHeading");
			logMessage("User is able to view the rules.");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}

	public boolean verifyDefaultRulesAreAvailable() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			isElementDisplayed("viewRuleButton");
			click(element("viewRuleButton"));
			isElementDisplayed("rulesHeading");
			logMessage("User is able to view the default rules.");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}

	public boolean defaultRuleAssignmentToLocation() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			isElementDisplayed("threeDots");
			click(element("threeDots"));
			click(element("addRack"));
			logMessage("User is able to aasign the location to the item.");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}

	public boolean addRack() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			isElementDisplayed("threeDots");
			click(element("threeDots"));
			click(element("addRack"));
			logMessage("User is able to add rack");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}

	public boolean verifyDistributorNonAvailability() {

		try {
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			isElementDisplayed("threeDots");
			click(element("threeDots"));
			click(element("addRack"));
			logMessage("Distributor details are coming for the user and set accordingly.");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean setLocationAndVerify() {

		try {
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			selectProvidedTextFromDropDownUsingIndex(element("ISASelect"), 1);
			click(element("saveButton"));
			logMessage("User is able to aasign the location to the item for a facility");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean optionsVerificationInDropdown() {

		try {
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			selectProvidedTextFromDropDownUsingIndex(element("ISASelect"), 1);
			click(element("saveButton"));
			logMessage("User is able to aasign the location to the item for a facility");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean rulesForSinlgeLocation() {

		try {
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			isElementDisplayed("defaultRuleDropdown");
			logMessage("User is able to see the rule for the single location");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean rulesForSpecficLocation() {

		try {
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			isElementDisplayed("rulesLocation");
			logMessage("User is able to see the rule for the specfic location");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean closeRulesPopUP() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			isElementDisplayed("viewRuleButton");
			click(element("viewRuleButton"));
			isElementDisplayed("rulesHeading");
			click(element("viewRuleCloseButton"));
			logMessage("Rules Pop has been closed successfully");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean getISAList() {

		try {
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			Select ISA = new Select(element("ISASelect"));
			List<WebElement> list = ISA.getAllSelectedOptions();
			if (!(list.isEmpty())) {
				logMessage("ISA options are available");
				flag = true;
				return flag;
			} else {
				return flag;
			}

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean removebin() {
		try {

			hover(element("addRack"));
			clickUsingXpathInJavaScriptExecutor(element("addSelf"));
			logMessage("Clicked the element");
			hover(element("addSelf"));
			click(element("selfThreeDot"));
			click(element("removeBinIcon"));
			logMessage("User has successfully deleted the location.");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean RulesSetToNone() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 1);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			isElementDisplayed("defaultRuleDropdown");
			selectProvidedTextFromDropDownUsingIndex(element("defaultRuleDropdown"), 1);
			element("defaultRuleDropdown").getText();
			logMessage("User can see the defaul rule options.");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}
	
	
	
	
	public boolean removeShelf() {
		try {

			hover(element("addRack"));
			clickUsingXpathInJavaScriptExecutor(element("addSelf"));
			logMessage("Clicked the element");
			hover(element("addSelf"));
			click(element("selfThreeDot"));
			click(element("removeShelf"));
			logMessage("User has successfully deleted the Shelf.");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean ruleViewOpen() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			isElementDisplayed("viewRuleButton");
			click(element("viewRuleButton"));
			isElementDisplayed("rulesHeading");
			logMessage("Rules details has been opened successfully.");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean replenishAsDistributor() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			Select options = new Select(element("ReplenishDropDown"));
			options.getFirstSelectedOption();
			logMessage("Default value as distrubutor is selected.");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean verifyCarouselSelf() {

		try {
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 1);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			isElementDisplayed("threeDots");
			click(element("threeDots"));
			click(element("addRack"));
			logMessage("User is able to view the carousel option on the screen.");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean verifyErrorMessageForRacks() {
		try {

			element("editButton");
			wait.waitForLoaderToBeInvisible(getLocator("loader"), 20);
			isElementDisplayed("racks_error_msg");
			logMessage("ASSERTION PASSED: Verified Error message for Racks");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}
	}

	public boolean clickOnEditLinkCorresspondingToAssignedItem() {
		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 1);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			logMessage("Clicked on Edit link corressponding to assigned item ");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}

	public boolean enterQOHDetails() {
		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 1);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			element("minQuantity").sendKeys("10");
			element("maxQuantuty").sendKeys("10");
			if (!(isElementDisplayed("errorLog"))) {
				logMessage("QOH details has been entered and no error message are showing");
				flag = true;
				return flag;

			} else {
				return flag;
			}

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean MinQtyvsMaxQty() {
		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 1);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			element("minQuantity").sendKeys("5");
			element("maxQuantuty").sendKeys("10");
			if (!(isElementDisplayed("errorLog"))) {
				logMessage("Min qty entered less than max qty. ");
				flag = true;
				return flag;

			} else {
				return flag;
			}

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean editQOH() {
		MinQtyvsMaxQty();
		try {
			element("minQuantity").clear();
			element("maxQualtity").clear();
			element("minQuantity").sendKeys("7");
			element("maxQuantuty").sendKeys("11");
			logMessage("QOH has been editted successfully");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean verifyPrintBinOptionsonSelfAction() {

		try {
			clickOnEditLinkCorresspondingToAssignedItem();
			wait.hardWait(3);
			isElementDisplayed("addRack");
			hover(element("addRack"));
			// wait.waitForElementToBeClickable(element("add_rack"));
			clickUsingXpathInJavaScriptExecutor(element("addSelf"));
			// element("add_rack").click();
			logMessage("Clicked the element");
			hover(element("addSelf"));
			click(element("selfThreeDot"));
			isElementDisplayed("printBinOption");
			logMessage("Print bin option is available in the self.");

			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean addSelf() {
		try {

			clickOnEditLinkCorresspondingToAssignedItem();
			wait.hardWait(3);
			isElementDisplayed("addRack");
			hover(element("addRack"));
			clickUsingXpathInJavaScriptExecutor(element("addSelf"));
			logMessage("Clicked the element");
			hover(element("addSelf"));
			logMessage("Self has been added.");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean addBin() {
		try {

			clickOnEditLinkCorresspondingToAssignedItem();
			wait.hardWait(3);
			isElementDisplayed("addRack");
			hover(element("addRack"));
			clickUsingXpathInJavaScriptExecutor(element("addSelf"));
			logMessage("Clicked the element");
			hover(element("addSelf"));
			logMessage("Self has been added.");
			hover(element("selfThreeDot"));
			click(element("addBin"));
			logMessage("Bin has been added successfully");

			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean landingOnEditScreen() {
		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;
		}
	}

	public boolean verifyItemsID_Facility_QOH() {
		try {
			isElementDisplayed("itemID");
			isElementDisplayed("facility");
			click(element("assignLocationButton"));
			isElementDisplayed("QOH");
			isElementDisplayed("minQuantity");
			isElementDisplayed("maxQuantuty");
			logMessage("Item IS, facility, QOH, Min Qty and max qty has beeb displayed.");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}
	}

	public void editClickButton() {
		wait.waitForElementToBeClickable(element("editButton"));
		click(element("editButton"));
		logMessage("User is able to click on edit  button.");

	}
	
	
	public boolean verifyGenericName() {
		try {
			isElementDisplayed("itemID");
			isElementDisplayed("facility");
			click(element("assignLocationButton"));
			isElementDisplayed("QOH");
			isElementDisplayed("minQuantity");
			isElementDisplayed("maxQuantuty");
			logMessage("Generic name has been discplayed on the UI screen.");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}
	}

	
	public boolean removeRack() {
		try {

			hover(element("addRack"));
			clickUsingXpathInJavaScriptExecutor(element("addSelf"));
			logMessage("Clicked the element");
			hover(element("addSelf"));
			click(element("selfThreeDot"));
			click(element("removeBinIcon"));
			logMessage("Remove rack option has been approved.");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}
	
	public boolean editReplenish() {

		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 1);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			isElementDisplayed("defaultRuleDropdown");
			selectProvidedTextFromDropDownUsingIndex(element("defaultRuleDropdown"), 1);
			element("defaultRuleDropdown").getText();
			logMessage("user can see the edit replenish options.");
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;

		}

	}
	
	public boolean verifyPrintBinOptionson() {

		try {
			clickOnEditLinkCorresspondingToAssignedItem();
			wait.hardWait(3);
			isElementDisplayed("addRack");
			hover(element("addRack"));
			// wait.waitForElementToBeClickable(element("add_rack"));
			clickUsingXpathInJavaScriptExecutor(element("addSelf"));
			// element("add_rack").click();
			logMessage("Clicked the element");
			hover(element("addSelf"));
			click(element("selfThreeDot"));
			isElementDisplayed("printBinOption");
			logMessage("Print bin option is available in the self.");

			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}
	
	public boolean addSlotsOptions() {
		try {

			clickOnEditLinkCorresspondingToAssignedItem();
			wait.hardWait(3);
			isElementDisplayed("addRack");
			hover(element("addRack"));
			clickUsingXpathInJavaScriptExecutor(element("addSelf"));
			logMessage("Clicked the element");
			hover(element("addSelf"));
			logMessage("Self has been added.");
			hover(element("selfThreeDot"));
			click(element("addBin"));
			logMessage("slotss has been added successfully");

			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}
	

	public boolean editItemLocation() {
		try {
			wait.waitForElementToBeVisible(element("searchFacilityDropdown"));
			selectProvidedTextFromDropDownUsingIndex(element("searchFacilityDropdown"), 2);
			wait.waitForElementToBeClickable(element("editButton"));
			click(element("editButton"));
			logMessage("User is able to click on edit  button.");
			click(element("assignButton"));
			click(element("saveButton"));
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;
		}
	}

	public void selectReplenishmentValue(String name)
	{
		wait.hardWait(10);
		selectProvidedTextFromDropDownUsingIndex(element("SelectReplenish"),1);
		logMessage("ASSERTION PASSED: Select value from Replenish dropdown");
		click(element("replenish_Save"));
		logMessage("Clicked on Save button");
	}
	
	

}
