package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1061261 extends BaseTest {

	@Test(priority = 1, description = "VPLX: Remove items from a Single PO (Individual ISA): [UI]: Deletion of single item from PO with multiple items under Export PO")
	public void Test01_1076568(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remove items from a Single PO (Individual ISA): [UI]: Deletion of single item from PO with multiple items under Export PO");
		
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
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName5").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName5").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName5").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName5").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.clickItemInPO(TestDataPropertyReaderAndWriter.getProperty("ManualItemName5").trim());
		test.purchaseDashboardActions.clickButtonOnDashboard("ignore");
		test.purchaseDashboardActions.clickButtonOnDashboard("primary");
		Assert.assertTrue(test.purchaseDashboardActions.verifyItemIsNotVisibleAfterRemove(TestDataPropertyReaderAndWriter.getProperty("ManualItemName5").trim()));
	}
	
	@Test(priority = 2, description = "VPLX: Remove items from a Single PO (Individual ISA): [UI]: Deletion of all items from ISA under Export PO")
	public void Test02_1076573(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remove items from a Single PO (Individual ISA): [UI]: Deletion of all items from ISA under Export PO");
		
		test.purchaseDashboardActions.clickButtonOnDashboard("add");
		test.purchaseDashboardActions.SearchPOItem("Search Item", TestDataPropertyReaderAndWriter.getProperty("ManualItemName5").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName5").trim());
		test.purchaseDashboardActions.clickSearchedPOItemAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName5").trim());
		test.purchaseDashboardActions.enterOrderQuantityAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName5").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.clickCheckboxAll("checkboxALL");
		test.purchaseDashboardActions.clickButtonOnDashboard("ignore");
		test.purchaseDashboardActions.clickButtonOnDashboard("primary");
	}
	
	@Test(priority = 3, description = "VPLX: Remove items from a Single PO (Individual ISA): [UI]: Deletion of single item under ISA on Export PO")
	public void Test03_1076574(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remove items from a Single PO (Individual ISA): [UI]: Deletion of single item under ISA on Export PO");
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.clickItemInPO(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickButtonOnDashboard("ignore");
		test.purchaseDashboardActions.clickButtonOnDashboard("primary");
		
	}
	
	@Test(priority = 4, description = "VPLX: Remove items from a Single PO (Individual ISA): [UI]: Deletion of single item of single PO present under distributor under Export PO")
	public void Test04_1076576(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remove items from a Single PO (Individual ISA): [UI]: Deletion of single item of single PO present under distributor under Export PO");
		
		test.purchaseDashboardActions.clickItemInPO(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.clickButtonOnDashboard("ignore");
		test.purchaseDashboardActions.clickButtonOnDashboard("primary");
	}
	
	@Test(priority = 5, description = "VPLX: Remove items from a Single PO (Individual ISA): [UI]: Deletion of item and refreshing the export screen")
	public void Test05_1076578(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remove items from a Single PO (Individual ISA): [UI]: Deletion of item and refreshing the export screen");
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.clickItemInPO(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickButtonOnDashboard("ignore");
		test.purchaseDashboardActions.clickButtonOnDashboard("primary");
		test.purchaseDashboardActions.clickRefreshOrderDetailsPage("#");
		Assert.assertTrue(test.purchaseDashboardActions.verifyItemIsNotVisibleAfterRemove(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim()));
	}
}