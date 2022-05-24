package com.org.actions;

import org.junit.Assert;
import org.openqa.selenium.ElementClickInterceptedException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.org.automation.getpageobjects.BaseUi;
import com.org.automation.getpageobjects.GetPage;

public class DestinationFulfillmentPage_Actions extends GetPage {

	WebDriver driver;
	static String pagename = "Destination_Fulfillment";
	private boolean flag = false;

	public DestinationFulfillmentPage_Actions(WebDriver driver) {
		super(driver, pagename);
		this.driver = driver;
	}

	public boolean verifyDestinationFulfillmentHeading() {
		wait.waitForElementToBeVisible(element("destinationFulfillmentHeading"));
		try {
			isElementDisplayed("destinationFulfillmentHeading");
			flag = true;
			return flag;

		} catch (Exception e) {
			System.err.println("verifyDestinationFulfillmentHeading method getting Exception as " + e);
			flag = false;
			return flag;

		}

	}

	public boolean verifyFacilitySectionOnDestinationFulfillment() {
		wait.waitForElementToBeVisible(element("facilitySection"));
		try {
			isElementDisplayed("facilitySection");
			flag = true;
			return flag;

		} catch (Exception e) {
			System.err.println("verifyFacilitySectionOnDestinationFulfillment method getting Exception as " + e);
			flag = false;
			return flag;

		}

	}

	public boolean verifyReportSectionOnOnDerstinationFulfillment() {
		wait.waitForElementToBeVisible(element("reportSection"));
		try {
			isElementDisplayed("reportSection");
			flag = true;
			return flag;

		} catch (Exception e) {
			System.err.println("verifyReportSectionOnOnDerstinationFulfillment method getting Exception as " + e);
			flag = false;
			return flag;

		}

	}

	public void selectAnyFacility() {

		wait.waitForElementToBeVisible(element("facilitySection"));
		try {
			selectDropDownValue(element("facilitySection"), 1);
			// selectDropDownValue("RO_Fac_1");
		} catch (Exception e) {
			System.err.println("Exception in method -selectAnyFacility :  " + e);
		}
		logMessage("User has been successfully selected a facility from Destination Fulfillment.");

	}

	public void selectReportAs_Discrepancies() {
		wait.waitForElementToBeVisible(element("reportSection"));
		try {
			selectDropDownValue("Discrepancies");

		} catch (Exception e) {
			System.err.println("Exception in method -selectReportAs_Discrepancies :  " + e);

		}
		logMessage("User has been successfully selected Discrepancies report from Destination Fulfillmen.");
	}

	public void selectReportAs_Invoices() {
		wait.waitForElementToBeVisible(element("reportSection"));
		try {
			selectDropDownValue("Invoices");

		} catch (Exception e) {
			System.err.println("Exception in method -selectReportAs_Discrepancies :  " + e);

		}
		logMessage("User has been successfully selected Discrepancies report from Destination Fulfillmen.");
	}

	public boolean verifyDateFieldAppear() {
		wait.waitForElementToBeVisible(element("destinationFulfillmentHeading"));
		try {
			wait.waitForElementToBeVisible(element("dateField"));
			isElementDisplayed("dateField");
			flag = true;
			logMessage("Date field has been appeard on Destination Fulfillmen.");
			return flag;

		} catch (Exception e) {
			flag = false;
			return flag;

		}

	}

	public boolean verifyViewReportButton() {
		wait.waitForElementToBeVisible(element("dateField"));
		try {
			isElementDisplayed("viewReportButton");
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;

		}

	}

	public boolean reportvViewButtonEnableCheck() {
		wait.waitForElementToBeVisible(element("dateField"));
		try {
			isElementEnabled(element("viewReportButton"), true);
			logMessage("view report button get enabled Destination Fulfillmen.");
			flag = true;
			return flag;

		} catch (Exception e) {
			flag = false;
			return flag;

		}

	}

	public boolean getDiscrepanciesReport() {
		wait.waitForElementToBeVisible(element("destinationFulfillmentHeading"));
		try {
			if (isElementDisplayed("viewReportButton") || isElementEnabled(element("viewReportButton"), true)) {
				click(element("viewReportButton"));
				flag = true;
				return flag;
			} else {
				flag = true;
				logMessage("Correct data for report is not found.");
				return flag;
			}

		} catch (Exception e) {
			logMessage("Correct data for report is not found.");
			return flag;

		}

	}
	// viewInvoiceReportLink

	public boolean invoiceReportAppeared() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				flag = true;
				logMessage("Invoice report of select facility has been discpayed.");
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}

		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}

	public boolean start_endDateMatch() {
		try {
			if (isElementDisplayed("viewReportButton") || isElementEnabled(element("viewReportButton"), true)) {

				String startDateValue = element("dateFieldStart").getAttribute("value");

				String endDateValue = element("dateFieldEnd").getAttribute("value");
				if (startDateValue.equals(endDateValue)) {
					logMessage("start date and end date has been match successfully.");

					flag = true;

				}
				return flag;

			} else {

				String startDateValue = element("dateFieldStart").getAttribute("value");
				String endDateValue = element("dateFieldEnd").getAttribute("value");
				if (startDateValue.equals(endDateValue)) {
					flag = true;
					flag = true;
					logMessage("start date and end date has been match successfully.");

				}
				return flag;
			}

		} catch (Exception e) {
			return flag;

		}

	}
	
	
	
	public boolean invoiceReportDetailsAvailable() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				flag = true;
				logMessage("Invoice report of select facility has been discpayed.");
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}

		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean invoiceExpiryDate() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("expiryDate");
				flag = true;
				logMessage("Expiry date column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean invoiceRow_Blank() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("date");
				flag = true;
				logMessage("Invoice containing row as blank.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	
	public boolean invoiceRow_NDC() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("NDC");
				flag = true;
				logMessage("Invoice containing NDC row is diplayed.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean invoiceRow_ExtCost() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("extCost");
				flag = true;
				logMessage("Invoice containing ext date row is diplayed.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean invoiceRowColumn() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("RowColumn");
				flag = true;
				logMessage("Row date column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean invoiceDosageFormColumn() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("DosageFormColumn");
				flag = true;
				logMessage("Row date column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	
	public boolean invoiceRowitemDescription() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("itemDescription");
				flag = true;
				logMessage("Item Description column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean invoiceRowitemID() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("itemID");
				flag = true;
				logMessage("Item ID column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean invoiceDeaLicence() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("DisLicence");
				flag = true;
				logMessage("DEA licence column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean invoiceRXLicence() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("RXLicence");
				flag = true;
				logMessage("RX licence column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean invoiceZipFcility() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("ZIPText");
				flag = true;
				logMessage("ZIP facility column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	
	public boolean invoiceState() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("stateText");
				flag = true;
				logMessage("state data column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}

	
	public boolean addressFacility() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("addressLink");
				flag = true;
				logMessage("Address facility column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean distributorPharmacy() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("pharmacydata");
				flag = true;
				logMessage("Pharmacy column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}

	public boolean verifyDateField() {
		wait.waitForElementToBeVisible(element("destinationFulfillmentHeading"));
		try {
			wait.waitForElementToBeVisible(element("dateField"));
			isElementDisplayed("dateField");
			flag = true;
			logMessage("Date field has been appeard on Destination Fulfillmen.");
			return flag;

		} catch (Exception e) {
			flag = true;
			return flag;

		}

	}
	
	public boolean DeaLiccence() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("DeaLiccence");
				flag = true;
				logMessage("DeaLiccence column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean cityDestination() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("cityText");
				flag = true;
				logMessage("City destination column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean destinationColumn() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("destinationColumn text");
				flag = true;
				logMessage("destinationColumn  column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean orderDate() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("orderDate text");
				flag = true;
				logMessage("orderDate  column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean orderID() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("orderID text");
				flag = true;
				logMessage("orderID  column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean invoiceColumn() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("invoiceColumn text");
				flag = true;
				logMessage("invoiceColumn  column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
	
	public boolean Discrepency() {

		try {

			if (element("viewInvoiceReportLink").isDisplayed()) {
				click(element("viewInvoiceReportLink"));
				isElementDisplayed("Discrepency text");
				flag = true;
				logMessage("Discrepency  column has been displayed for the invoice.");
				
				return flag;

			} else {
				logMessage("Invoice report of this facility is not available.");

				flag = true;
			}
		} catch (Exception e) {
			flag = true;

		}
		return flag;

	}
}
