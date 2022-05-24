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

public class Story_1078863 extends BaseTest {
	
	public class UnableToOrder extends BaseTest {
		int DashboardCount,listPage;
		String issue;
		String itemID, itemName, barcode, productID, itemID1, itemName1, barcode1, productID1,item;
		ArrayList<String> previous_data, sorted_data;


		@Test(priority = 1, description = "VPLX: Unable to Order (Item Ordering) : [UI]: No of Items are displaying after choose any facility from dropdown.")
		public void Test01_1093144(Method method) throws Throwable {

			ExtentTestManager.startTest(method.getName(),
					"VPLX: Unable to Order (Item Ordering) : [UI]: No of Items are displaying after choose any facility from dropdown.");
			test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName_UnableToOrder"));
			
		//	test.purchaseDashboardActions.selectDropDownValue("12JuneRO_Fac");
			
			Assert.assertTrue(test.purchaseDashboardActions.verifyResolveNowIsPresent("Resolve Now", getData("PurchaseOrderData.ResolveNowMsg")));


		}
		

		@Test(priority = 2, description = "VPLX: Unable to Order (Item Ordering) : [UI]: Display of of Item card field on purchase order screen.")
		public void Test02_1098391(Method method) throws Throwable {

			ExtentTestManager.startTest(method.getName(),
					"VPLX: Unable to Order (Item Ordering) : [UI]: Display of of Item card field on purchase order screen.");
			
			Assert.assertTrue(test.purchaseDashboardActions.verifyResolveNowIsPresent("Resolve Now", getData("PurchaseOrderData.ResolveNowMsg")));
			Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("New"),
					"[Assertion Failed]: New state is not present on the Dashboard");
			Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Exported"),
					"[Assertion Failed]: Exported state is not present on the Dashboard");
			Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Pending Receive"),
					"[Assertion Failed]: Pending Receive state is not present on the Dashboard");
			Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Received"),
					"[Assertion Failed]: Received state is not present on the Dashboard");

		}
		
		@Test(priority = 3, description = "VPLX: Unable to Order (Item Ordering) : [UI]: User Successfully redirect to Issue List page after clicking on \"Resolve Now\" link")
		public void Test03_1093139__1098395(Method method) throws Throwable {

			ExtentTestManager.startTest(method.getName(),
					"VPLX: Unable to Order (Item Ordering) : [UI]: User Successfully redirect to Issue List page after clicking on Resolve Now link");
			
		     DashboardCount=test.purchaseDashboardActions.countofItemsbeforeResolve();
		    test.purchaseDashboardActions.clickResolveNow();
		    test.purchaseDashboardActions.verifyIssueListingPage();

		}
		
		@Test(priority = 4, description = "VPLX: Unable to Order (Item Ordering) : [UI]: Number of the items are same on Purchase Order screen and Items listing screen")
		public void Test04_1093146(Method method) throws Throwable {

			ExtentTestManager.startTest(method.getName(),
					"VPLX: Unable to Order (Item Ordering) : [UI]: User Successfully redirect to Issue List page after clicking on Resolve Now link");
			
		    listPage= test.purchaseDashboardActions.verifycountOnListPage();
		     test.purchaseDashboardActions.compareCountOnDashboardAndListing(DashboardCount,listPage);

		}
		
		@Test(priority = 5, description = "VPLX: Unable to Order (Item Ordering): [UI]: Display of Item card field on Issue Listing screen is correct.")
		public void Test05_1098395(Method method) throws Throwable {

			ExtentTestManager.startTest(method.getName(),
				"VPLX: Unable to Order (Item Ordering): [UI]: Display of Item card field on Issue Listing screen is correct");
			
		    listPage= test.purchaseDashboardActions.verifycountOnListPage();
		  
		}
		
	}
}
