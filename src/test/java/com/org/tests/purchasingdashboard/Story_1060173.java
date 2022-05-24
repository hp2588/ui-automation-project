package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1060173 extends BaseTest{

	String successMessage,itemName,itemOrderQuantity,itemOrderQuantity1;
	
	@Test(priority = 1, description = "VPLX: Buyer Dashboard Updates: [UI]: Multiple Purchase Orders for Same distributor")
	public void Test01_1070879_1070882(Method method) {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		itemName=test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		successMessage= itemName +" has been added to existing order for " + TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()+".";
		System.out.println(successMessage);
		test.purchaseDashboardActions.verifySuccessMessageOnViewPageWithLoader(successMessage);
	
	}
	
	@Test(priority = 2, description = "VPLX: Buyer Dashboard Updates: [UI]: Display of distributor and PO under Export screen")
	public void Test02_1070880(Method method) {

		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.verifyAllISATextIsPresent();
		test.purchaseDashboardActions.verifyISAPOIsPresent(TestDataPropertyReaderAndWriter.getProperty("ISAName1").trim());
		test.purchaseDashboardActions.verifyISAPOIsPresent(TestDataPropertyReaderAndWriter.getProperty("ISAName2").trim());
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	

	@Test(priority = 3, description = "VPLX: Buyer Dashboard Updates: [UI]: Display of different  distributors and multiple PO under New column")
	public void Test03_1070881(Method method) {
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.purchaseDashboardActions.verifyPurchaseOrderManualCardisPresent(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()),true);
		Assert.assertEquals(test.purchaseDashboardActions.verifyPurchaseOrderManualCardisPresent(TestDataPropertyReaderAndWriter.getProperty("DistributorName2").trim()),true);
	}
	
	@Test(priority = 4, description = "VPLX: Buyer Dashboard Updates: [UI]:  Item already exists in an existing new PO with ISA/class that matches the item's location/class")
	public void Test04_1070883_1070884(Method method) {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		itemOrderQuantity=test.purchaseDashboardActions.getLineItemOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		itemName=test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		successMessage= itemName +" has been added to existing order for " + TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()+".";
		System.out.println(successMessage);
		test.purchaseDashboardActions.verifySuccessMessageOnViewPageWithLoader(successMessage);
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		itemOrderQuantity1=test.purchaseDashboardActions.getLineItemOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		Assert.assertTrue(Integer.parseInt(itemOrderQuantity1) > Integer.parseInt(itemOrderQuantity), "[ASSERT FAILED] : Order quantity of PO not increased");
	}
}
