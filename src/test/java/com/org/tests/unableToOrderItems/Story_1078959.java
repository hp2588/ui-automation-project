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

public class Story_1078959 extends BaseTest{
	
	public class UnableToOrder extends BaseTest {
		int DashboardCount,listPage,DashboardCountnew;
		String issue;
		String itemID, itemName, barcode, productID, itemID1, itemName1, barcode1, productID1,item;
		ArrayList<String> previous_data, sorted_data;


		
		@Test(priority = 1, description = "VPLX: Unable to Order (Item Ordering) : [UI]: Item count remain same on purchase order screen once user add missing information but doesn't save the changes")
		public void Test01_1106703(Method method) throws Throwable {
			

			ExtentTestManager.startTest(method.getName(),
				"VPLX: Unable to Order (Item Ordering) : [UI]: Item count remain same on purchase order screen once user add missing information but doesn't save the changes.");
			
			test.landingPageActions.navigateToMenu("Purchasing Dashboard");
			test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName_UnableToOrder"));
				
				 DashboardCount=test.purchaseDashboardActions.countofItemsbeforeResolve();
				 test.purchaseDashboardActions.clickResolveNow();
				 test.purchaseDashboardActions.verifyIssueListingPage();
				    listPage= test.purchaseDashboardActions.verifycountOnListPage();

				 item= test.purchaseDashboardActions.getItemName();
				 System.out.println("The Item Name is: "+item);
				 issue=   test.purchaseDashboardActions.clickIssueLink();
			
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
					test.siteConfigurationAction.clickCancelButtonOnProdID();
			   }
			   
			   else if(issue.equalsIgnoreCase("No preferred distributor"))
			   {
				    test.siteConfigurationAction.clickLink("Add Preferred Distributor");
					test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
					test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName_UnableToOrder"));
					long itemCode=System.currentTimeMillis();
					
		            test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName_UnableToOrder"),Long.toString(itemCode));
					
					test.siteConfigurationAction.clickButton("primary");
					 test.siteConfigurationAction.clickCancelButtonOnProdID();
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
					 test.purchaseDashboardActions.clickCancelButtonOnAssignPopup();
				   
				   
			   }
			  
			test.landingPageActions.navigateToMenu("Purchasing Dashboard");
			test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName_UnableToOrder"));
			Assert.assertTrue(test.purchaseDashboardActions.verifyResolveNowIsPresent("Resolve Now", getData("PurchaseOrderData.ResolveNowMsg")));
		     DashboardCountnew=test.purchaseDashboardActions.countofItemsbeforeResolve();
		     test.purchaseDashboardActions.compareCountOnDashboardAndListing(DashboardCount,listPage);
            test.purchaseDashboardActions.verifyCountOnCancel(DashboardCount, DashboardCountnew);
			
			
		}
		
		@Test(priority = 2, description = "VPLX: Unable to Order (Item Ordering) : [UI]: Item remain in the Item Issue list once user successfully fixed the issue but doesn't save the changes.")
		public void Test02_1101796(Method method) throws Throwable {

			ExtentTestManager.startTest(method.getName(),
				"VPLX: Unable to Order(Item Ordering) - Listing: [UI]: User Successfully redirect to the desired page to fix the issue after clicking on Issue link");
			
			test.purchaseDashboardActions.clickResolveNow();
			test.purchaseDashboardActions.compareCountOnDashboardAndListing(DashboardCount,listPage);
			Assert.assertTrue(test.purchaseDashboardActions.verifyItemOnIssuePage(item));

		}
		
		@Test(priority = 3, description = "VPLX: Unable to Order (Item Ordering) : [UI]: Item removed from the Item Issue list once user successfully fixed the issue and save the changes")
		public void Test03_1101770_1106701(Method method) throws Throwable {

			ExtentTestManager.startTest(method.getName(),
				"VPLX: Unable to Order (Item Ordering) : [UI]: Item removed from the Item Issue list once user successfully fixed the issue and save the changes");
			for (int i=1 ; i<=listPage;i++)
			{
	         System.out.println("Value of list page"+listPage);
	        int currentIssueCount=test.purchaseDashboardActions.verifycountOnListPage();
	        System.out.println("Current ISsue Count is: "+currentIssueCount);
			 item= test.purchaseDashboardActions.getItemName();
			 System.out.println("The item name is: "+item);

			 issue= test.purchaseDashboardActions.clickIssueLink();
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
					long itemCode=System.currentTimeMillis();

					test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName_UnableToOrder"),Long.toString(itemCode));
					test.siteConfigurationAction.clickButton("primary");
					test.siteConfigurationAction.clickSaveButtonForISA();

			   }   
			   
			   else if(issue.equalsIgnoreCase("No preferred distributor"))
			   {
				    test.siteConfigurationAction.clickLink("Add Preferred Distributor");
					test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
					test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName_UnableToOrder"));
					long itemCode=System.currentTimeMillis();
					test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName_UnableToOrder"),Long.toString(itemCode));					
					test.siteConfigurationAction.clickButton("primary");
					test.siteConfigurationAction.clickSaveButtonForISA();

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
					test.siteConfigurationAction.clickSaveButton();
				   
				   
			   }
			 
			 
			 Assert.assertFalse(test.purchaseDashboardActions.verifyItemOnIssuePage(item));
			Assert.assertTrue(test.purchaseDashboardActions.verifyReducedCountAfterResolving(currentIssueCount));

		}
		}
		
	}

}
