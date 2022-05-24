package com.org.actions;

import org.junit.Assert;
import org.openqa.selenium.ElementClickInterceptedException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.org.automation.getpageobjects.BaseUi;
import com.org.automation.getpageobjects.GetPage;

public class BarcodePS_Integration_Actions extends GetPage {

	WebDriver driver;
	static String pagename = "BarcodePS_Integration_Page";
	private boolean flag = true;

	public BarcodePS_Integration_Actions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public boolean barcodeScreenLanding() {
		try {
			wait.waitForElementToBeVisible(element("barcodeLabel"));
			isElementDisplayed("barcodeLabel");
			logMessage("User has successfully landed on barcode screen.");
			flag = true;
			return flag;      

		} catch (Exception e) {

			return flag;

		}

	}

	public boolean searchBarcodeAvailability() {
		try {
			wait.waitForElementToBeVisible(element("barcodeLabel"));
			isElementDisplayed("searchBarcodeField");
			logMessage("User can see search box on barcode screen.");
			flag = true;
			return flag;
		} catch (Exception e) {

			return flag;

		}

	}

	public boolean barcodeVisibleInBarcodeSearchField() {
		String barcode = "0100380052020690171005032328717621abcd123456789";
		wait.waitForElementToBeVisible(element("barcodeLabel"));
		element("searchBarcodeField").sendKeys(barcode);
		click(element("hitsearchButton"));
		String barcodeValue = element("barcodeNumber", barcode).getAttribute("value");
		if (barcode.equalsIgnoreCase(barcodeValue)) {
			logMessage("entered barcode value is visible in barcode search.");
			flag = true;
			return flag;
		} else {
			return flag;
		}

	}

	public boolean parsecodeIDFromBarcode() {
		String barcode = "0100380052020690171005032328717621abcd123456789";
		String parseID = barcode.substring(5, 14);
		wait.waitForElementToBeVisible(element("barcodeLabel"));
		element("searchBarcodeField").sendKeys(barcode);
		click(element("hitsearchButton"));
		String parseProductID = element("parseProductID").getText();
		if (parseID.equalsIgnoreCase(parseProductID)) {
			logMessage("parse product ID is vicible.");
			flag = true;
			return flag;
		} else {
			return flag;
		}

	}

	public boolean parsecodeIDFromBarcodeData(String barcode) {
		// String barcode = "0100380052020690171005032328717621abcd123456789";
		String parseID = barcode.substring(5, 14);
		wait.waitForElementToBeVisible(element("barcodeLabel"));
		element("searchBarcodeField").sendKeys(barcode);
		// click(element("hitsearchButton"));
		String parseProductID = element("parseProductID").getText();
		if (parseID.equalsIgnoreCase(parseProductID)) {
			logMessage("parse product ID is vicible.");

			return flag;
		} else {
			return flag;
		}

	}

	public boolean parseIDcontainMinus() {
		String barcode = "90";
		wait.waitForElementToBeVisible(element("barcodeLabel"));
		element("searchBarcodeField").sendKeys(barcode);
		click(element("hitsearchButton"));
		wait.hardWait(3);
		isElementDisplayed("parse-ID");

		return flag;

	}

	public boolean productIDVerification(String barcode) {

		try {
			wait.waitForElementToBeVisible(element("barcodeLabel"));
			element("searchBarcodeField").sendKeys(barcode);
			// click(element("hitsearchButton"));
			isElementDisplayed("productID");
			logMessage("Product ID for the respected barcoce is visible");
			flag = true;
			return flag;

		} catch (Exception e) {

			return flag;

		}

	}

	public boolean externalSystemVerification(String rawBarcode) {

		try {
			wait.waitForElementToBeVisible(element("barcodeLabel"));
			element("searchBarcodeField").sendKeys(rawBarcode);
			// click(element("hitsearchButton"));
			wait.waitForElementToBeVisible(element("externalSystemDropDown"));
			isElementDisplayed("externalSystemDropDown");
			logMessage("External system drop down is has appeared for raw barcode value.");
			flag = true;
			return flag;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean linkedItemVerification(String barcode) {

		try {
			wait.waitForElementToBeVisible(element("barcodeLabel"));
			element("searchBarcodeField").sendKeys(barcode);
			// click(element("hitsearchButton"));
			wait.waitForElementToBeVisible(element("linkedItemHeader"));
			isElementDisplayed("linkedItemHeader");
			logMessage("Linked item details are appearing for the item.");
			flag = true;
			return true;

		} catch (Exception e) {
			return flag;

		}

	}

	public boolean barcodeDetailsVerification(String barcode) {

		try {

			wait.waitForElementToBeVisible(element("barcodeLabel"));
			element("searchBarcodeField").sendKeys(barcode);
			wait.waitForElementToBeVisible(element("linkedItemHeader"));
			isElementDisplayed("barcodeHeader");
			logMessage("barcode details are appearing on product details page.");
			flag = true;
			return true;

		} catch (Exception e) {

			return flag;

		}

	}

	public boolean linkedByDetailsVerification(String barcode) {

		try {
			wait.waitForElementToBeVisible(element("barcodeLabel"));
			element("searchBarcodeField").sendKeys(barcode);
			wait.waitForElementToBeVisible(element("linkedItemHeader"));
			isElementDisplayed("linkedByDetails");
			logMessage("Linked by details are appearing on product details page.");
			flag = true;
			return true;

		} catch (Exception e) {

			return flag;

		}

	}

	public boolean sourceFieldVerification(String barcode) {

		try {

			wait.waitForElementToBeVisible(element("barcodeLabel"));
			element("searchBarcodeField").sendKeys(barcode);
			wait.waitForElementToBeVisible(element("linkedItemHeader"));
			isElementDisplayed("linkSourceHeader");
			logMessage("Srouce details are appearing on product details page.");
			flag = true;
			return true;

		} catch (Exception e) {

			return flag;

		}

	}

	public boolean itemIDVerification(String barcode) {

		try {

			wait.waitForElementToBeVisible(element("barcodeLabel"));
			element("searchBarcodeField").sendKeys(barcode);
			wait.waitForElementToBeVisible(element("itemIDDetails"));
			isElementDisplayed("itemIDDetails");
			logMessage("Item ID details are appearing on product details page.");

			return true;

		} catch (Exception e) {

			return flag;

		}

	}

	public boolean verifiedByVerification(String barcode) {

		try {

			wait.waitForElementToBeVisible(element("barcodeLabel"));
			element("searchBarcodeField").sendKeys(barcode);
			wait.waitForElementToBeVisible(element("verifyByDetails"));
			isElementDisplayed("verifyByDetails");
			logMessage("veridy by details are appearing on product details page.");
			flag = true;
			return true;

		} catch (Exception e) {

			return flag;

		}

	}

	public boolean matchOnVerification(String barcode) {

		try {

			wait.waitForElementToBeVisible(element("barcodeLabel"));
			element("searchBarcodeField").sendKeys(barcode);
			wait.waitForElementToBeVisible(element("matchOnDetails "));
			isElementDisplayed("matchOnDetails ");
			logMessage("matchOnDetails are appearing on product details page.");
			flag = true;
			return true;

		} catch (Exception e) {
			flag = true;
			return flag;

		}

	}

}
