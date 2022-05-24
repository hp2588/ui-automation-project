package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1060111 extends BaseTest {

	@Test(priority = 1, description = "VPLX: Buyer Dashboard Updates: [UI]: Display of new accordion after date removal")
	public void Test01_1070503_1070505(Method method) {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.verifyNewStateAll();
	}
	
	@Test(priority = 2, description = "VPLX: Buyer Dashboard Updates: [UI]: Display of Purchase Orders under New Column")
	public void Test02_1070507(Method method) {
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.verifyPurchaseOrderManualCardisPresent(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
	}
	
	@Test(priority = 3, description = "VPLX: Buyer Dashboard Updates: [UI]:  Minimize and Maximize of New column")
	public void Test03_1070508(Method method) {

		test.purchaseDashboardActions.minimizeMaximizeNewStateAccordian("New");	
	}
}
