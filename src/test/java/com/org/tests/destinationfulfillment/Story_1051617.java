package com.org.tests.destinationfulfillment;

import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1051617 extends BaseTest {

	@Test(priority = 1, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : Invoice details are present after user selects the facility and Invoice in Destination fullfillment")

	public void Test01_1121957(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : Invoice details are present after user selects the facility and Invoice in Destination fullfillment");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();

		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceReportAppeared(),
				"Invoice report of select facility is not available.");

	}

	@Test(priority = 2, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : View report hyperlink button is present in front of the every invoice and is clickable")

	public void Test02_1121960(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : View report hyperlink button is present in front of the every invoice and is clickable");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();

		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceReportAppeared(),
				"Invoice report of select facility is not available.");

	}

	@Test(priority = 3, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : User is directed to new page after clicking to View Report")

	public void Test03_1121963(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : User is directed to new page after clicking to View Report");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();

		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceReportAppeared(),
				"Invoice report of select facility is not available.");

	}

	@Test(priority = 4, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Quantity (Ordered/Received)")

	public void Test04_1124793(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Quantity (Ordered/Received)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceReportDetailsAvailable(),
				"Invoice reports details are not available.");

	}

	@Test(priority = 5, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Expiration Date (Blank)")

	public void Test05_1122112(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Expiration Date (Blank)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceExpiryDate(),
				"Invoice reports details with expiry date are not available.");

	}

	@Test(priority = 6, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Lot # (Blank)")
	public void Test06_1122111(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Lot # (Blank)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceRow_Blank(),
				"Invoice reports details with blank row date date are not available.");

	}

	@Test(priority = 7, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row NDC")
	public void Test07_1122109(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row NDC");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceRow_NDC(),
				"Invoice reports details with NDC row are not available.");

	}

	@Test(priority = 8, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Ext Cost (Cost * quantity)")
	public void Test08_1122108(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Ext Cost (Cost * quantity)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceRow_ExtCost(),
				"Invoice reports details with NDC row are not available.");

	}

	@Test(priority = 9, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Cost (Each)")
	public void Test09_1122107(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Cost (Each)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceRow_Blank(),
				"Invoice reports details with NDC row are not available.");

	}

	@Test(priority = 10, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Quantity")
	public void Test10_1122106(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Quantity");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceRowColumn(),
				"Invoice reports details with  row coulmn are not available.");

	}

	@Test(priority = 11, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Dosage Form")
	public void Test11_1122105(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Dosage Form");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceDosageFormColumn(),
				"Invoice reports details with  dosage form coulmn are not available.");
	}

	@Test(priority = 12, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Item description")
	public void Test12_1122104(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Item description");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceRowitemDescription(),
				"Invoice reports details with  Row item coulmn are not available.");
	}

	@Test(priority = 13, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Item ID")
	public void Test13_1122103(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has row Item ID");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceRowitemID(),
				"Invoice reports details with  item ID coulmn are not available.");

	}

	@Test(priority = 14, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with DEA License(Facility)")
	public void Test14_1122101(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with DEA License(Facility)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceDeaLicence(),
				"Invoice reports details with  DEa Licence coulmn are not available.");

	}

	@Test(priority = 15, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with RX License(Facility)")
	public void Test15_1122100(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with RX License(Facility))");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceRXLicence(),
				"Invoice reports details with  DEa Licence coulmn are not available.");

	}

	@Test(priority = 16, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with ZIP(Facility)")
	public void Test16_1122097(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with ZIP(Facility)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceZipFcility(),
				"Invoice reports details with  ZIP facility coulmn are not available.");

	}

	@Test(priority = 17, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with State(Facility)")
	public void Test17_1122079(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with State(Facility)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceState(),
				"Invoice reports details with  sate coulmn are not available.");

	}

	@Test(priority = 18, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : Remote Orders that have been replenished by Pharmacy (Pick only orders) will not have any associated PO Numbers or Invoice Numbers; and display blank in those columns.")
	public void Test18_1122078(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : Remote Orders that have been replenished by Pharmacy (Pick only orders) will not have any associated PO Numbers or Invoice Numbers; and display blank in those columns.");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceReportDetailsAvailable(),
				"Invoice reports details with  coulmn are not available.");

	}

	@Test(priority = 19, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with City(Facility)")
	public void Test19_1122077(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with City(Facility).");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceState(),
				"Invoice reports details with  city are not available.");

	}

	@Test(priority = 20, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with Address(Facility)")
	public void Test20_1122076(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with Address(Facility)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.addressFacility(),
				"Address facility details with  city are not available.");

	}

	@Test(priority = 21, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with Distributor or \"Pharmacy\"")
	public void Test21_1122074(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with Distributor or \"Pharmacy\"");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.distributorPharmacy(),
				"Distributor details with  distributor are not available.");

	}

	@Test(priority = 22, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with P.O#(If available)")
	public void Test22_1122073(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with P.O#(If available)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.distributorPharmacy(),
				"Distributor details with  pharmacy are not available.");

	}

	@Test(priority = 23, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with Date Received")
	public void Test23_1122072(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has Second column with Date Received");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.verifyDateField(),
				"Distributor details with  date are not available.");

	}

	@Test(priority = 24, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with DEA License(Destination)")
	public void Test24_1122071(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with DEA License(Destination)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.DeaLiccence(),
				"Distributor details with  DeaLiccence are not available.");

	}

	@Test(priority = 25, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with RX License(Destination)")
	public void Test25_1122070(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with RX License(Destination)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceRXLicence(),
				"Distributor details with  invoiceRXLicence are not available.");

	}

	@Test(priority = 26, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with Zip(Destination)")
	public void Test26_1122069(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with Zip(Destination)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceZipFcility(),
				"Distributor details with  invoiceZipFcility are not available.");

	}

	@Test(priority = 27, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with State(Destination)")
	public void Test27_1122066(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with State(Destination)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceState(),
				"Distributor details with  state destination are not available.");

	}

	@Test(priority = 28, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with City(Destination)")
	public void Test28_1122065(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with City(Destination)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.cityDestination(),
				"Invoice details with  cityDestination  are not available.");

	}

	@Test(priority = 29, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with Address(Destination)")
	public void Test29_1122064(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with Address(Destination)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.addressFacility(),
				"Invoice details with  addressFacility  are not available.");

	}

	@Test(priority = 30, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with Destination")
	public void Test30_1122063(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with Destination");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.destinationColumn(),
				"Invoice details with  destinationColumn  are not available.");

	}
	
	@Test(priority = 31, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with Order Date eg. 12/11/2020 02:12:56 PM")
	public void Test31_1122061(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with Order Date eg. 12/11/2020 02:12:56 PM");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.orderDate(),
				"Invoice details with  orderDate  are not available.");

	}
	
	@Test(priority = 32, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with Order ID")
	public void Test32_1122060(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with Order ID");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.orderID(),
				"Invoice details with  orderID  are not available.");

	}
	
	@Test(priority = 33, description = "VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with Invoice# (If Available)")
	public void Test33_1122064(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX :Destination Fulfillment- Invoice and Discrepancy: [UI] : The invoice has First column with Invoice# (If Available)");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.invoiceColumn(),
				"Invoice details with  invoiceColumn  are not available.");

	}
	
	@Test(priority = 34, description = "VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: Fields which are present on Discrepancy report.")
	public void Test34_1121424(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Destination Fulfillment- Invoice and Discrepancy:[UI]: Fields which are present on Discrepancy report.");
		test.landingPageActions.navigateToFeature("Destination Fulfillment");
		test.destinationFulfillmentActions.selectAnyFacility();
		test.destinationFulfillmentActions.selectReportAs_Invoices();
		Assert.assertTrue(test.destinationFulfillmentActions.Discrepency(),
				"Invoice details with  Discrepency  are not available.");

	}

}
