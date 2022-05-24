package com.org.tests.purchasingdashboard;

import org.testng.annotations.Test;

import com.org.actions.Purchase_Dashboard_Actions;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;

public class Story_1070188 extends BaseTest{
	
	
	String orderQuantity,orderQuantityUpdated,dataEnteredName, dataEnteredCode, new_data,conc_amt,itemName;
	
	@Test(priority = 1, description = "VPLX: Buyer Dashboard Updates: [UI]: Create New Order from link in Actions button on Purchasing Dashboard.")
	public void Test01_1129675() {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		
		//test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("PckSize").trim());
		
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		
		//test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("PckSize").trim());
		
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),getData("PurchaseOrderDetail.orderquantity"));
		
		//test.purchaseDashboardActions.enterOrderQuantity_new("toOrderQuantity",	getData("PurchaseOrderDetail.orderquantity"));
		
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.verifyPurchaseOrderManualCardisPresent(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		String orderQuantity=test.purchaseDashboardActions.getItemOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	
	@Test(priority = 2, description = "VPLX: Buyer Dashboard Updates: [UI]: Create a order from link in Actions button on Purchasing Dashboard which is added to an existing PO.")
	public void Test02_1129676() {
	
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		
		//test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("PckSize").trim());
		
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		//test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("PckSize").trim());
		
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim(),getData("PurchaseOrderDetail.orderquantity"));
		//test.purchaseDashboardActions.enterOrderQuantity_new("toOrderQuantity",	getData("PurchaseOrderDetail.orderquantity"));
		
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.verifyItemIsPresent(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	
	@Test(priority = 3, description = "VPLX: Buyer Dashboard Updates: [UI]: Create a Order from link in Actions button on Purchasing Dashboard and selected item to be ordered is added to the order for the same already in an existing PO.")
	public void Test03_1129678() {
	
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		//test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("PckSize").trim());
		
	//	test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),	TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("PckSize").trim());
		
	//	test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.enterOrderQuantity_new("toOrderQuantity",	getData("PurchaseOrderDetail.orderquantity"));
		
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		String orderQuantityUpdated=test.purchaseDashboardActions.getItemOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		Assert.assertTrue(orderQuantity != orderQuantityUpdated,"[Assertion Failed]: Order quantity is not updated");
	}
	
	@Test(priority = 4, description = "VPLX: Buyer Dashboard Updates: [UI]: System display 'E' in blue color for Electronic Order")
	public void Test04_1072558() {
		
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
	//	test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("PckSize").trim());
		
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
	//	test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("PckSize").trim());
		
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim(),getData("PurchaseOrderDetail.orderquantity"));
	//	test.purchaseDashboardActions.enterOrderQuantity_new("toOrderQuantity",	getData("PurchaseOrderDetail.orderquantity"));
		
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");

		Assert.assertTrue(test.purchaseDashboardActions
				.verifyOrderColorCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim(), "aqua"));
		Assert.assertTrue(
				test.purchaseDashboardActions.verifyDistributorCardType(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim()),
				"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");
	}
	
	@Test(priority = 5, description = "VPLX: Buyer Dashboard Updates: [UI]: System display 'M' in purple color for Manual  Order")
	public void Test05_1072559() {
	
		Assert.assertTrue(test.purchaseDashboardActions.verifyOrderColorCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim(),
				"magenta"));
		
		Assert.assertTrue(
				test.purchaseDashboardActions.verifyDistributorCardType(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()),
				"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");
		
	}
	
	@Test(priority = 6, description = "VPLX: Buyer Dashboard Updates: [UI]: System does not display 'Type Electronic Order' in Electronic Order")
	public void Test06_1072564() {
	
		
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	
	@Test(priority = 7, description = "VPLX: Buyer Dashboard Updates: [UI]: System does not display 'Type Manual Order' in Manual Order")
	public void Test07_1072566() {
	
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	
	@Test(priority = 8, description = "VPLX: Buyer Dashboard Updates: [UI]:  Dashboard display Auto PO Creation OFF ")
	public void Test08_1072569_1072571() {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.verifyTextOnDashboard("Auto PO Creation");
		test.purchaseDashboardActions.verifyAutoPOCreationStatus("Off");
	}
	
	@Test(priority = 9, description = "VPLX: Buyer Dashboard Updates: [UI]:  Dashboard display Auto PO Creation ON ")
	public void Test09_1072568() {
		
		test.purchaseDashboardActions.clickAutoPOCreation();
		test.purchaseDashboardActions.clickAutoCreateBasedSchedule("createAutomaticallyBasedOnSchedule");
		test.purchaseDashboardActions.clickScheduleDay("W");
		test.purchaseDashboardActions.clickSaveOrCancelSchedule("primary");
		test.purchaseDashboardActions.verifyAutoPOCreationStatus("On");
		test.purchaseDashboardActions.clickAutoPOCreation();
		test.purchaseDashboardActions.clickAutoCreateBasedSchedule("createManually");
		test.purchaseDashboardActions.clickSaveOrCancelSchedule("primary");
	}
	
	@Test(priority = 10, description = "VPLX: Buyer Dashboard Updates: [UI]:  Dashboard display \"Purchasing dashboard\" with last updated time ")
	public void Test10_1072574() {
		
		
		System.out.println(test.purchaseDashboardActions.getDashboardTime());
		
	}
	
	@Test(priority = 11, description = "VPLX: Buyer Dashboard Updates: [UI]:  Dashboard display \"Today, Yesterday and Older \" on Exported.")
	public void Test11_1072577() {
		
		test.purchaseDashboardActions.verifyAccordianDaysExported("Today");
		test.purchaseDashboardActions.verifyAccordianDaysExported("Yesterday");
		test.purchaseDashboardActions.verifyAccordianDaysExported("Older");
	}
	
	@Test(priority = 12, description = "VPLX: Buyer Dashboard Updates: [UI]:  Dashboard display \"Today, Yesterday and Older \" on Exported.")
	public void Test12_1072577() {
		
		test.purchaseDashboardActions.verifyAccordianDaysPendingReceive("Today");
		test.purchaseDashboardActions.verifyAccordianDaysPendingReceive("Yesterday");
		test.purchaseDashboardActions.verifyAccordianDaysPendingReceive("Older");
	}
	
	
	
	@Test(priority = 13, description = "VPLX: Buyer Dashboard Updates: [UI]: If Location is not assigned to an item it is not populated in the search results of create new order screen.")
	public void Test13_1130254() {
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("UnableToOrderItem").trim());
		Assert.assertTrue(test.purchaseDashboardActions.verifyNoRecordFoundOnItemSearch(),"[Assertion Passed] : Item is not present when searched");
		test.purchaseDashboardActions.clickOnCancel("Cancel");
		test.purchaseDashboardActions.clickOnCancel("Yes");
	}
	
	@Test(priority = 14, description = "VPLX: Buyer Dashboard Updates: [UI]: If  location is assigned to an item order can be created for that item.")
	public void Test14_1130256_1130259() {
	
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	    test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.verifyPurchaseOrderManualCardisPresent(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		Assert.assertTrue(test.purchaseDashboardActions
				.verifyOrderColorCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim(), "magenta"));
		Assert.assertTrue(
				test.purchaseDashboardActions.verifyDistributorCardType(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()),
				"[Assertion failed]: Distributor type(Manual) is not present on the purchase order card");
		
	}
		
	@Test(priority = 15, description = "VPLX: Buyer Dashboard Updates: [UI]: If a distributor is electronic than only electronic order for that distributor can be created.")
	public void Test15_1130258_1130260() {
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
	    test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim(),getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.verifyPurchaseOrderManualCardisPresent(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		Assert.assertTrue(test.purchaseDashboardActions
				.verifyOrderColorCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim(), "aqua"));
		Assert.assertTrue(
				test.purchaseDashboardActions.verifyDistributorCardType(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim()),
				"[Assertion failed]: Distributor type(Electronic) is not present on the purchase order card");		
	}
	
	@Test(priority = 16, description = "VPLX: Buyer Dashboard Updates: [UI]: Inactive Distributor linked item is not available in search on the item search page")
	public void Test16_1130263() {
		//create a distributor and item linked to it 
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("UnableToOrderItem").trim());
		Assert.assertTrue(test.purchaseDashboardActions.verifyNoRecordFoundOnItemSearch(),"[Assertion Passed] : Item is not present when searched");
		test.purchaseDashboardActions.clickOnCancel("Cancel");
		test.purchaseDashboardActions.clickOnCancel("Yes");
		
	}
	
	@Test(priority = 17, description = "VPLX: Buyer Dashboard Updates: [UI]: If distributor name is changed, updated name is reflected name on  PO page.")
	public void Test17_1130263() {
	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim(), "Edit Distributor");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText", "DistElec"+System.currentTimeMillis());
		test.supportDataActions.clickAddButtonOnDistributor("save");
		TestDataPropertyReaderAndWriter.setProperty("DistributorName3",dataEnteredName);
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim(), "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.verifyPurchaseOrderManualCardisPresent(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		
	}
	
	@Test(priority = 18, description = "VPLX: Buyer Dashboard Updates: [UI]: If an item is marked as inactive, it will not be populated in the search results of create new order screen.")
	public void Test18_1130266() throws Throwable {
		
		//in order to make item inactive unassign location first
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ManualItemMultiISA").trim(), "search");

		test.purchaseDashboardActions.clickOnItemEditLink(TestDataPropertyReaderAndWriter.getProperty("ManualItemMultiISA").trim());
		test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.purchaseDashboardActions.clickOnItemActiveFlag("activeFlag");
		test.siteConfigurationAction.verifyToggleIsInActive("activeFlag");		
		test.siteConfigurationAction.clickButton("save");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemMultiISA").trim());
		Assert.assertTrue(test.purchaseDashboardActions.verifyNoRecordFoundOnItemSearch(),"[Assertion Passed] : Item is not present when searched");
		test.purchaseDashboardActions.clickOnCancel("Cancel");
		test.purchaseDashboardActions.clickOnCancel("Yes");
	}
	
	@Test(priority = 19, description = "VPLX: Buyer Dashboard Updates: [UI]: Updated ItemDescription is reflected on  PO page if any of the fields(Generic name/Strength amount/Strength Units/Concentration ")
	public void Test19_1130267_1130422_1150976() throws Throwable {
	
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim(), "search");
		test.purchaseDashboardActions.clickOnItemEditLink(TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		conc_amt=test.purchaseDashboardActions.enterdosageConcentrationAmount("10");
		test.siteConfigurationAction.clickButton("save");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName2").trim());
		itemName=test.purchaseDashboardActions.getItemNameOrderDetailPage(TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		Assert.assertTrue(itemName.contains(conc_amt),"[Assertion Passed] : Item name updated with concentration amount");
	}
	
	@Test(priority = 20, description = "VPLX: Buyer Dashboard Updates: [UI]: Updated ItemDescription is reflected on  PO page if any of the fields(Generic name/Strength amount/Strength Units/Concentration ")
	public void Test20_1130397() throws Throwable {
	
		test.purchaseDashboardActions.enterPONumberPerOrder("1","PO"+System.currentTimeMillis());
		test.purchaseDashboardActions.clickAllISA();
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		test.purchaseDashboardActions.clickOnReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName2").trim());
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	
	@Test(priority = 21, description = "VPLX: Buyer Dashboard Updates: [UI]: One or more POs created when user selects a another facility on the Purchasing Dashboard.")
	public void Test21_1072577() {
		
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor").trim());
		test.purchaseDashboardActions.clickOnVoidAll("Void All");
		test.purchaseDashboardActions.clickYesOrNoAfterVoid("primary");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.verifyPurchaseOrderManualCardisPresent(TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor").trim());
	}
	
	@Test(priority = 22, description = "VPLX: Buyer Dashboard Updates: [UI]: One or more POs created when user presses Refresh button on the Purchasing Dashboard.")
	public void Test22_1129483() {
		
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor").trim());
		test.purchaseDashboardActions.clickOnVoidAll("Void All");
		test.purchaseDashboardActions.clickYesOrNoAfterVoid("primary");
		test.purchaseDashboardActions.waitForLoader();
		test.purchaseDashboardActions.clickDashboardRefresh("dashboardRefreshBtn");
		test.purchaseDashboardActions.verifyPurchaseOrderManualCardisPresent(TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor").trim());
		
	}
}
