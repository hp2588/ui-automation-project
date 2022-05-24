package com.org.tests.unableToOrderItems;

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

public class Story_1078968 extends BaseTest {

	int DashboardCount,listPage,dashboardCountnew;
	String issue;
	String itemID, itemName, barcode, productID, itemID1, itemName1, barcode1, productID1,item;
	ArrayList<String> previous_data, sorted_data;
	
	
	@Test(priority = 1, description = "VPLX: Unable to Order (Item Ordering) : [UI]: User Successfully redirect to Issue List page after clicking on \"Resolve Now\" link")
	public void Test01_1101759(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unable to Order (Item Ordering) : [UI]: User Successfully redirect to Issue List page after clicking on Resolve Now link");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
			test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName_UnableToOrder"));			
			Assert.assertTrue(test.purchaseDashboardActions.verifyResolveNowIsPresent("Resolve Now", getData("PurchaseOrderData.ResolveNowMsg")));


	    DashboardCount=test.purchaseDashboardActions.countofItemsbeforeResolve();
	    test.purchaseDashboardActions.clickResolveNow();
	    test.purchaseDashboardActions.verifyIssueListingPage();

	}
	
	@Test(priority = 2, description = "VPLX: Unable to Order(Item Ordering) - Listing: [UI]: Sort the items as per sorting order on Issue Listing page.")
	public void Test02_1106567(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
			"VPLX: Unable to Order(Item Ordering) - Listing: [UI]: Sort the items as per sorting order on Issue Listing page. 	");
		
	    listPage= test.purchaseDashboardActions.verifycountOnListPage();
	    
		test.supportDataActions.verifyAndClickSortIcon("Issue");
	    previous_data = test.supportDataActions.captureDataForParticularColumnDosageForm("1");
		System.out.println("Previous data :  " + previous_data);
		sorted_data = test.supportDataActions.sortDataForParticularColumnInAscendingOrderDosageForm("1");
		System.out.println("Sorted data :  " + sorted_data);
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Code column data is not sorted in ascending order");
		
		
		test.supportDataActions.verifyAndClickSortIcon("Min Qty");
		previous_data = test.supportDataActions.captureDataForUnableToOrder("1");
			System.out.println("Previous data :  " + previous_data);
			sorted_data = test.supportDataActions.captureDataForUnableToOrder("1");
			System.out.println("Sorted data :  " + sorted_data);
			Assert.assertEquals(previous_data, sorted_data,
					"[ASSERTION FAILED] : Code column data is not sorted in ascending order");
			
			
		test.supportDataActions.verifyAndClickSortIcon("Max Qty");
		previous_data = test.supportDataActions.captureDataForUnableToOrder("2");
		System.out.println("Previous data :  " + previous_data);
		sorted_data = test.supportDataActions.captureDataForUnableToOrder("2");
		System.out.println("Sorted data :  " + sorted_data);
		Assert.assertEquals(previous_data, sorted_data,
						"[ASSERTION FAILED] : Code column data is not sorted in ascending order");
		
		
		test.supportDataActions.verifyAndClickSortIcon("Adj On Hand");
		previous_data = test.supportDataActions.captureDataForUnableToOrder("3");
		System.out.println("Previous data :  " + previous_data);
		sorted_data = test.supportDataActions.captureDataForUnableToOrder("3");
		System.out.println("Sorted data :  " + sorted_data);
		Assert.assertEquals(previous_data, sorted_data,
						"[ASSERTION FAILED] : Code column data is not sorted in ascending order");
			
		
		test.supportDataActions.verifyAndClickSortIcon("Pkg Size");
		previous_data = test.supportDataActions.captureDataForUnableToOrder("4");
			System.out.println("Previous data :  " + previous_data);
			sorted_data = test.supportDataActions.captureDataForUnableToOrder("4");
			System.out.println("Sorted data :  " + sorted_data);
			Assert.assertEquals(previous_data, sorted_data,
					"[ASSERTION FAILED] : Code column data is not sorted in ascending order");
			
	
	
	
		test.supportDataActions.verifyAndClickSortIcon("Order Qty ");
		previous_data = test.supportDataActions.captureDataForUnableToOrder("5");
		System.out.println("Previous data :  " + previous_data);
		sorted_data = test.supportDataActions.captureDataForUnableToOrder("5");
		System.out.println("Sorted data :  " + sorted_data);
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Code column data is not sorted in ascending order");
		
		
}

	@Test(priority = 3, description = "VPLX: Unable to Order(Item Ordering) - Listing: [UI]: Issue display and clickable for each item in Issue Listing page.")
	public void Test03_1106556(Method method) throws Throwable {
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Unable to Order(Item Ordering) - Listing: [UI]: Issue display and clickable for each item in Issue Listing page.");
			
			item= test.purchaseDashboardActions.getItemName();
			issue=   test.purchaseDashboardActions.clickIssueLink();
		
	}
	
	
	@Test(priority = 4, description = "VPLX: Unable to Order(Item Ordering) - Listing: [UI]: Item details are editable when user lands on the page to fix the issue")
	public void Test04_1101765(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
			"VPLX: Unable to Order(Item Ordering) - Listing: [UI]: Item details are editable when user lands on the page to fix the issue");
		
/*		item= test.purchaseDashboardActions.getItemName();
		System.out.println("The Item Name is: "+item);
		issue=   test.purchaseDashboardActions.clickIssueLink(); */
		
	   if(issue.equalsIgnoreCase("No preferred NDC"))
	   {
		   test.siteConfigurationAction.verifyAndClickProductID("Product ID");
			test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
			test.siteConfigurationAction.verifyHeader("Barcodes");
			barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
					"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
			
			
			test.siteConfigurationAction.clickButton("search");
			productID1 = test.siteConfigurationAction.getParsedProductID();
			System.out.println("productID=" + productID1);
			test.siteConfigurationAction.clickButton("link");
			test.siteConfigurationAction.verifyAddedProductID(productID1);
			test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
			test.siteConfigurationAction.clickLink("Add Preferred Distributor");
			
			test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
			
		   test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName_UnableToOrder"));
		
			
		//	test.siteConfigurationAction.clickOnDistributorInfo("19JuneM");
			
			long itemCode=System.currentTimeMillis();
			
			
			test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName_UnableToOrder"),Long.toString(itemCode));
			test.siteConfigurationAction.clickButton("primary");
	   }
	   
	   else if(issue.equalsIgnoreCase("No preferred distributor"))
	   {
		    test.siteConfigurationAction.clickLink("Add Preferred Distributor");
			test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
			long itemCode=System.currentTimeMillis();
			
			test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName_UnableToOrder"));
			test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName_UnableToOrder"),Long.toString(itemCode));
			test.siteConfigurationAction.clickButton("primary");
	   }
	   
	   else
	   {
		   test.siteConfigurationAction.selectValueForDropDown("facility",
					TestDataPropertyReaderAndWriter.getProperty("FacilityName_UnableToOrder").trim());
			test.siteConfigurationAction.selectValueForDropDown("isa",
					TestDataPropertyReaderAndWriter.getProperty("ISAName3"));
			test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
			test.siteConfigurationAction.clickAssignLocationButton();
			test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
			test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
			test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		   
		   
	   }
	  
	}

}
