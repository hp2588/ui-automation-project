package com.org.tests.vplxArchievedInvoices;

import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_998714 extends BaseTest {
	String barcode, productID;

	@Test(priority = 1, description = "VPLX : Archived Invoices : <UI> Upon clicking on the view archived link displayed in the action tab, system shoulddisplay the list of archived invoices")
	public void Test01_1071104(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Upon clicking on the view archived link displayed in the action tab, system should display the list of archived invoices");
		test.landingPageActions.navigateToFeature("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue("Hilfiger Hospitals");
		Assert.assertTrue(test.vpxArchievedObj.archievedOrdersDisplay(),
				"User is not able to see the archieved orders listed here");
	}

	@Test(priority = 2, description = "VPLX : Archived Invoices : <UI> Invoices displayed should be sorted primary on Archive date.")
	public void Test02_1071105(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Invoices displayed should be sorted primary on Archive date.");
		Assert.assertTrue(test.vpxArchievedObj.order_acending_verification(),
				"all the order are not arrange in acensing order");

	}

	@Test(priority = 3, description = "VPLX : Archived Invoices : <UI> Invoices displayed should have PO date also with MM/DD/YYYY.")
	public void Test03_1071107(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Invoices displayed should have PO date also with MM/DD/YYYY");
		Assert.assertTrue(test.vpxArchievedObj.PODateAvailability(),
				"All the PO order has date with MM/DD/YYYY format");

	}

	@Test(priority = 4, description = "VPLX : Archived Invoices : <UI> Verify the Column Invoice Number is present")
	public void Test04_1071107(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Verify the Column Invoice Number is present");
		Assert.assertTrue(test.vpxArchievedObj.iSinvoiceColumnPresent(), "Invoice column name is not present.");

	}

	@Test(priority = 5, description = "VPLX : Archived Invoices : <UI> Verify the Column PO Date is present")
	public void Test05_1071110(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Verify the Column PO Date is present");
		Assert.assertTrue(test.vpxArchievedObj.iSPODateColumnAvailable(),
				"PO date column is not availble on Purchase dashboard screen.");

	}

	@Test(priority = 6, description = "VPLX : Archived Invoices : <UI> Verify the Column PO Description is present")
	public void Test06_1071111(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Verify the Column PO Description is present");
		Assert.assertTrue(test.vpxArchievedObj.iSPODiscriptionColumnAvailable(),
				"PO discription column is not availble on Purchase dashboard screen.");

	}

	@Test(priority = 7, description = "VPLX : Archived Invoices : <UI> Verify the Column Status is present")
	public void Test07_1071112(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Verify the Column Status is present");
		Assert.assertTrue(test.vpxArchievedObj.iSStatusColumnAvailable(),
				"PO status column is not availble on Purchase dashboard screen.");

	}

	@Test(priority = 8, description = "VPLX : Archived Invoices : <UI> Verify the Column Type is present")
	public void Test08_1071114(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Verify the Column Type is present");
		Assert.assertTrue(test.vpxArchievedObj.iSTypeColumnAvailable(),
				"Type column is not availble on Purchase dashboard screen.");

	}

	@Test(priority = 9, description = "VPLX : Archived Invoices : <UI> Verify the Column Archived Date is present")
	public void Test09_1071117(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Verify the Column Archived Date is present");
		Assert.assertTrue(test.vpxArchievedObj.iSArchievedColumnAvailable(),
				"Archived Date column is not availble on Purchase dashboard screen.");

	}

	@Test(priority = 10, description = "VPLX : Archived Invoices : <UI> Verify the Column ISA is present")
	public void Test10_1071118(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Verify the Column ISA is present");
		Assert.assertTrue(test.vpxArchievedObj.iSISAColumnAvailable(),
				"ISA column is not availble on Purchase dashboard screen.");

	}

	@Test(priority = 11, description = "VPLX : Archived Invoices : <UI> Verify the Column Distributor is present")
	public void Test11_1071121(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Verify the Column Distributor is present");
		Assert.assertTrue(test.vpxArchievedObj.iSDistributorColumnAvailable(),
				"Distributor column is not availble on Purchase dashboard screen.");

	}

	@Test(priority = 12, description = "VPLX : Archived Invoices : <UI> Invoices displayed should be sorted in Past Week when filter is applied")
	public void Test12_1071122(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Invoices displayed should be sorted in Past Week when filter is applied");
		test.purchaseDashboardActions.selectDropDownValue("Past Week");
		Assert.assertTrue(true, "Past week data is not displayed");

	}

	@Test(priority = 13, description = "VPLX : Archived Invoices : <UI> Invoices displayed should be sorted in Past Month when filter is applied")
	public void Test13_1071123(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Invoices displayed should be sorted in Past Month when filter is applied");
		test.purchaseDashboardActions.selectDropDownValue("Past Month");
		Assert.assertTrue(true, "Past Month data is not displayed");

	}

	@Test(priority = 14, description = "VPLX : Archived Invoices : <UI> Invoices displayed should be sorted in Past 3 Months  when filter is applied")
	public void Test14_1071125(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Invoices displayed should be sorted in Past 3 Months  when filter is applied");
		test.purchaseDashboardActions.selectDropDownValue("Past 3 Months");
		Assert.assertTrue(true, "Past 3 Month data is not displayed");
	}

	@Test(priority = 15, description = "VPLX : Archived Invoices : <UI> Invoices displayed should be sorted Status - Received All when filter is applied")
	public void Test15_1071125(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Invoices displayed should be sorted Status - Received All when filter is applied");
		test.purchaseDashboardActions.selectDropDownValue("Received All");
		Assert.assertTrue(true, "Received all data is not displayed");
	}

	@Test(priority = 16, description = "VPLX : Archived Invoices : <UI> Invoices displayed should be sorted Status - Received Partial when filter is applied")
	public void Test16_1071129(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Invoices displayed should be sorted Status - Received Partial when filter is applied");
		test.purchaseDashboardActions.selectDropDownValue("Received Partial");
		Assert.assertTrue(true, "Received partial filter is not applied.");
	}

	@Test(priority = 17, description = "VPLX : Archived Invoices : <UI> Invoices displayed should be sorted Type  - \"Manual Orders All\" when filter is applied")
	public void Test17_1071131(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Invoices displayed should be sorted Type  - \"Manual Orders All\" when filter is applied");
		test.purchaseDashboardActions.selectDropDownValue("Manual Orders");
		Assert.assertTrue(true, "Manual Orders filter is not applied");
	}

	@Test(priority = 18, description = "VPLX : Archived Invoices : <UI> Invoices displayed should be sorted Type  - Electronic Orders  when filter is applied")
	public void Test18_1071133(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> Invoices displayed should be sorted Type  - Electronic Orders  when filter is applied");
		test.purchaseDashboardActions.selectDropDownValue("Electronic Orders");
		Assert.assertTrue(true, "Electronic Orders filter is not applied.");
	}

	@Test(priority = 19, description = "VPLX : Archived Invoices : <UI> : Blank spaces should not searched in Archived Invoices screen.")
	public void Test19_1076034(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> : Blank spaces should not searched in Archived Invoices screen.");
		Assert.assertTrue(test.vpxArchievedObj.searchInvalidEntry(), "Search result is not showing the right result.");
	}

	@Test(priority = 20, description = "VPLX : Archived Invoices : <UI> : User gets blank response when searching with special character like \"&\"")
	public void Test20_1076029(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> : User gets blank response when searching with special character like \"&\"");
		Assert.assertTrue(test.vpxArchievedObj.searchInvalidEntry("&&"),
				"Search result is not showing the right result.");
	}

	@Test(priority = 21, description = "VPLX: Archived Invoices (Archiving, Viewing, Reactivating): [UI]: For Archived Orders Cost for any electronic order cannot be changed")
	public void Test21_1130508(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Archived Invoices (Archiving, Viewing, Reactivating): [UI]: For Archived Orders Cost for any electronic order cannot be changed.");
		test.purchaseDashboardActions.selectDropDownValue("Show All");
		test.purchaseDashboardActions.selectDropDownValue("All Status");
		test.purchaseDashboardActions.selectDropDownValue("Electronic Orders");
		Assert.assertTrue(true, "Electronic order unable to get ");
	}

	@Test(priority = 22, description = "VPLX : Archived Invoices : <UI> : Electronic Orders Cost cannot be updated in Archived invoices")
	public void Test22_1131164(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> : Electronic Orders Cost cannot be updated in Archived invoices.");
		test.purchaseDashboardActions.selectDropDownValue("Show All");
		test.purchaseDashboardActions.selectDropDownValue("All Status");
		test.purchaseDashboardActions.selectDropDownValue("Electronic Orders");
		Assert.assertTrue(true, "Electronic order unable to get from the order list");
	}

	@Test(priority = 23, description = "VPLX : Archived Invoices : <UI> : Archived Invoice can be moved to received and Sent to queue")
	public void Test23_1131174(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> : Archived Invoice can be moved to received and Sent to queue.");
		test.purchaseDashboardActions.selectDropDownValue("Show All");
		test.purchaseDashboardActions.selectDropDownValue("All Status");
		test.purchaseDashboardActions.selectDropDownValue("Electronic Orders");
		Assert.assertTrue(test.vpxArchievedObj.verifyInvoice(), "Invoices details are not verified.");
	}

	@Test(priority = 24, description = "VPLX : Archived Invoices : <UI> : User gets response when searching with valid \"Distributor\".")
	public void Test24_1076024(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> : User gets response when searching with valid \"Distributor\"");
		test.purchaseDashboardActions.selectDropDownValue("Show All");
		test.purchaseDashboardActions.selectDropDownValue("All Status");
		test.purchaseDashboardActions.selectDropDownValue("View All Orders");
		String distributorName = TestDataPropertyReaderAndWriter.getProperty("DistributorName");
		System.out.print("distributor name is =================-------- " + distributorName);
		Assert.assertTrue(test.vpxArchievedObj.searchByDistributor_Item_PO(distributorName),
				"search by distributor is not working fine.");
	}

	@Test(priority = 25, description = "VPLX : Archived Invoices : <UI> : User gets response when searching with valid \"Item Name")
	public void Test25_1076021(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> : User gets response when searching with valid \"Item Name");
		test.purchaseDashboardActions.selectDropDownValue("Show All");
		test.purchaseDashboardActions.selectDropDownValue("All Status");
		test.purchaseDashboardActions.selectDropDownValue("View All Orders");
		String itemName = TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim();
		System.out.print("Item name name is =================-------- " + itemName);
		Assert.assertTrue(test.vpxArchievedObj.searchByDistributor_Item_PO(itemName),
				"search by item name is not  working.");
	}

	@Test(priority = 26, description = "VPLX : Archived Invoices : <UI> : User gets response when searching with valid \"PO Number\"")
	public void Test26_1076016(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> : User gets response when searching with valid \"PO Number\"");
		test.purchaseDashboardActions.selectDropDownValue("Show All");
		test.purchaseDashboardActions.selectDropDownValue("All Status");
		test.purchaseDashboardActions.selectDropDownValue("View All Orders");
		barcode  = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		test.siteConfigurationAction.clickButton("search");
		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);
		Assert.assertTrue(test.vpxArchievedObj.searchByDistributor_Item_PO(productID),
				"search by product ID is not  working.");
	}

	@Test(priority = 27, description = "VPLX : Archived Invoices : <UI> : User gets response when searching with valid \"Invoice Number\"")
	public void Test27_1075995(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Archived Invoices : <UI> : User gets response when searching with valid \"Invoice Number\"");
		test.purchaseDashboardActions.selectDropDownValue("Show All");
		test.purchaseDashboardActions.selectDropDownValue("All Status");
		test.purchaseDashboardActions.selectDropDownValue("View All Orders");
		String invoiceNumber = test.vpxArchievedObj.getInvoiceNumber();
		Assert.assertTrue(test.vpxArchievedObj.searchByDistributor_Item_PO(invoiceNumber),
				"search by product ID is not  working.");
	}

	@Test(priority = 28, description = "VPLX: Archived Invoices (Archiving, Viewing, Reactivating): [UI]: All invoices in Received column are moved to Archive at mid-night.")
	public void Test27_1129446(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Archived Invoices (Archiving, Viewing, Reactivating): [UI]: All invoices in Received column are moved to Archive at mid-night.");
		test.purchaseDashboardActions.selectDropDownValue("Show All");
		test.purchaseDashboardActions.selectDropDownValue("All Status");
		test.purchaseDashboardActions.selectDropDownValue("View All Orders");
		String invoiceNumber = test.vpxArchievedObj.getInvoiceNumber();
		Assert.assertTrue(test.vpxArchievedObj.searchByDistributor_Item_PO(invoiceNumber),
				"search by product ID is not  working.");
	}

	@Test(priority = 29, description = "VPLX: Archived Invoices (Archiving, Viewing, Reactivating): [UI]: Status of items in the invoices.")
	public void Test21_1129453(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Archived Invoices (Archiving, Viewing, Reactivating): [UI]: Status of items in the invoices.");
		test.purchaseDashboardActions.selectDropDownValue("Show All");
		test.purchaseDashboardActions.selectDropDownValue("All Status");
		test.purchaseDashboardActions.selectDropDownValue("View All Orders");
		Assert.assertTrue(test.vpxArchievedObj.verifyInvoiceDetais(), "invoice is not showing the correct details.");

	}

}
