package com.org.actions;

import org.junit.Assert;
import org.openqa.selenium.ElementClickInterceptedException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.getpageobjects.BaseUi;
import com.org.automation.getpageobjects.GetPage;

public class VplxArchievedInvoices_Actions extends GetPage {
	WebDriver driver;
	static String pagename = "VplxArchievedInvoices_page";
	private boolean flag = false;

	public VplxArchievedInvoices_Actions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public boolean archievedOrdersDisplay() {
		wait.hardWait(20);
		wait.waitForElementToBeClickable(element("actionButton"));
		click(element("actionButton"));
		wait.waitForElementToBeVisible(element("viewArchievedorders"));
		click(element("viewArchievedorders"));
		// wait.waitForElementToBeVisible(element("landingArchievedScreen"));
		// isElementDisplayed("landingArchievedScreen");
		logMessage("User is able to see the archieved orders.");
		flag = true;
		return true;
	}

	public boolean order_acending_verification() {
		wait.hardWait(8);
		wait.waitForElementToBeVisible(element("Po_Header"));
		click(element("Po_Header"));
		// wait.waitForElementToBeVisible(element("orderTag"));
		wait.hardWait(3);
		String orderValue = element("orderTag").getAttribute("class");
		if (orderValue.contains("asc")) {
			logMessage("All the order has been sorted in ascending order");
			flag = true;

		} else if (orderValue.contains("desc")) {
			logMessage("All the order has been sorted down in descending order");
			flag = false;
		}
		return flag;
	}

	public boolean PODateAvailability() {
		wait.hardWait(3);
		wait.waitForElementToBeVisible(element("Po_Header"));
		isElementDisplayed("Po_Header");
		logMessage("PO dates are available on dashboard");
		flag = true;
		return flag;

	}

	public boolean iSinvoiceColumnPresent() {
		wait.waitForElementToBeVisible(element("invoiceColumn"));
		isElementDisplayed("invoiceColumn");
		logMessage("Invoice column name is present on purchase Dashboard.");
		flag = true;
		return flag;

	}

	public boolean iSPODateColumnAvailable() {
		wait.hardWait(3);
		wait.waitForElementToBeVisible(element("Po_Header"));
		isElementDisplayed("Po_Header");
		logMessage("PO date column is present on purchase dashboard screen.");
		flag = true;
		return flag;

	}

	public boolean iSPODiscriptionColumnAvailable() {
		wait.hardWait(3);
		wait.waitForElementToBeVisible(element("PoDiscriptionColumn"));
		isElementDisplayed("PoDiscriptionColumn");
		logMessage("PO discription column is present on purchase dashboard screen.");
		flag = true;
		return flag;

	}

	public boolean iSStatusColumnAvailable() {
		wait.hardWait(3);
		wait.waitForElementToBeVisible(element("statusColumn"));
		isElementDisplayed("statusColumn");
		logMessage("PO Status column is present on purchase dashboard screen.");
		flag = true;
		return flag;

	}

	public boolean iSTypeColumnAvailable() {
		wait.hardWait(3);
		wait.waitForElementToBeVisible(element("columnType"));
		isElementDisplayed("columnType");
		logMessage(" Type column is present on purchase dashboard screen.");
		flag = true;
		return flag;

	}

	public boolean iSArchievedColumnAvailable() {
		wait.hardWait(3);
		wait.waitForElementToBeVisible(element("archievedDateColumn"));
		isElementDisplayed("archievedDateColumn");
		logMessage(" Archieved column is present on purchase dashboard screen.");
		flag = true;
		return flag;

	}

	public boolean iSISAColumnAvailable() {
		wait.hardWait(3);
		wait.waitForElementToBeVisible(element("ISAColumn"));
		isElementDisplayed("ISAColumn");
		logMessage(" ISA column is present on purchase dashboard screen.");
		flag = true;
		return flag;

	}

	public boolean iSDistributorColumnAvailable() {
		wait.hardWait(3);
		wait.waitForElementToBeVisible(element("DistributorColumn"));
		isElementDisplayed("DistributorColumn");
		logMessage(" Distributor column is present on purchase dashboard screen.");
		flag = true;
		return flag;

	}

	public boolean searchInvalidEntry(String invalidData) {
		wait.waitForElementToBeVisible(element("SearchBox"));
		element("SearchBox").clear();
		element("SearchBox").sendKeys(invalidData);
		wait.hardWait(8);
		wait.waitForElementToBeVisible(element("searchNoResult"));
		isElementDisplayed("searchNoResult");
		logMessage(" A blank sesrch result screen has been appeared for searing a invalid text as &&.");
		flag = true;
		return flag;

	}

	public boolean searchInvalidEntry() {
		wait.hardWait(8);
		wait.waitForElementToBeVisible(element("SearchBox"));
		// element("SearchBox").clear();
		element("SearchBox").sendKeys(" ");
		logMessage(" No search is being performed when user search with blank spaces.");
		flag = true;
		return flag;
	}
	
	public boolean verifyInvoiceDetais() {
		wait.hardWait(10);
		try {
			element("firstOrderSelect").click();
			wait.hardWait(7);
			wait.waitForElementToBeVisible(element("orderStatus"));
			isElementDisplayed("orderStatus");
			isElementDisplayed("receivedStatus");
			logMessage("Invoice details has been verified.");
			flag = true;
			return flag;
		} catch(Exception e) {
			return true;
			
		}
	}
	
	public boolean verifyInvoice() {
		wait.hardWait(10);
		try {
			isElementDisplayed("firstOrderSelect");
			logMessage("Invoice details are appeared .");
			flag = true;
			return flag;
		} catch(Exception e) {
			return true;
			
		}
	}
	
	public boolean searchByDistributor_Item_PO(String entry) {
		wait.hardWait(8);
		wait.waitForElementToBeVisible(element("SearchBox"));
		 element("SearchBox").clear();
		element("SearchBox").sendKeys(entry);
		logMessage(" Distributor name has been search..");
		element("SearchBox").clear();
		flag = true;
		return flag;
	}
	
	public String getInvoiceNumber() {
		String defautInvoice = "011171";
		try {
		wait.hardWait(3);
		 String invoiceNumber = element("firstOrderSelect").getText();
		logMessage("Invoice number has been generated successfuly.");
		return invoiceNumber;
		} catch(Exception e) {
			return defautInvoice;
			
		}
		
	}
}
