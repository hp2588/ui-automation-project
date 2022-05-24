package com.org.tests.integration;

import static com.org.automation.utils.YamlReader.getData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class IntPOTest extends BaseTest {

	String medItem;

	@Test(priority = 1, description = "VPLX: Specific Facility Settings [UI]: When a new Facility is added,the same gets populated in a dropdown on PO Dashboard page for a user having access to that facility"
			+ "&"
			+ "VPLX: Manage Distributors: [Integration]: Distributor code is displayed in PO on Buyer dashboard in case Distributor Code check box is checked."
			+ "&"
			+ "VPLX: Manage Distributors: [Integration]: Current date is displayed in PO on Buyer dashboard in case Current date check box is checked."
			+ "&"
			+ "VPLX: Manage Distributors: [Integration]: ISA Name is displayed in PO on Buyer dashboard in case ISA Name check box is checked."
			+ "&"
			+ "VPLX: Manage Distributors: [Integration]: Med Class is displayed in PO on Buyer dashboard in case Med Class check box is checked.")
	public void Test01_1130469_1125029_1125034_1125041_1125046() {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DistributorName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderShortCodeUsedFlag");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderISANameUsedFlag");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderMedClassUsedFlag");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderDateUsedFlag");
		test.supportDataActions.clickSaveButtonOnDistributor("save");

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"), true);
		test.supportDataActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		medItem = test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);

		test.supportDataActions.openPurchaseOrderManualcard();
		Assert.assertTrue(test.siteConfigurationAction.checkDistributorNamePresentOrNotOnPOCard(
				TestDataPropertyReaderAndWriter.getProperty("DistributorCode"),
				TestDataPropertyReaderAndWriter.getProperty("ISAName")));
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
	}

	@Test(priority = 2, description = "VPLX: Manage Distributors: [Integration]: ISA Name is not displayed in PO on Buyer dashboard in case ISA Name check box is unchecked.")
	public void Test02_1125040() {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DistributorName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderShortCodeUsedFlag");
		test.supportDataActions.uncheckRadioButtonOnDistributor("purchaseOrderISANameUsedFlag");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderMedClassUsedFlag");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderDateUsedFlag");
		test.supportDataActions.clickSaveButtonOnDistributor("save");

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"), true);
		test.supportDataActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		medItem = test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		test.supportDataActions.openPurchaseOrderManualcard();
		Assert.assertTrue(test.siteConfigurationAction
				.valueNotPresentOnPO(TestDataPropertyReaderAndWriter.getProperty("ISAName")));
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
	}

	@Test(priority = 3, description = "VPLX: Manage Distributors: [Integration]: Med Class is not displayed in PO on Buyer dashboard in case Med Class check box is unchecked.")
	public void Test03_1125044() {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DistributorName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderShortCodeUsedFlag");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderISANameUsedFlag");
		test.supportDataActions.uncheckRadioButtonOnDistributor("purchaseOrderMedClassUsedFlag");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderDateUsedFlag");
		test.supportDataActions.clickSaveButtonOnDistributor("save");

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"), true);
		test.supportDataActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		medItem = test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		test.supportDataActions.openPurchaseOrderManualcard();
		Assert.assertTrue(test.siteConfigurationAction.valueNotPresentOnPO("ControlledTwo"));
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
	}

	@Test(priority = 4, description = "VPLX: Manage Distributors: [Integration]: Distributor code is not displayed in PO on Buyer dashboard in case Distributor Code check box is unchecked.")
	public void Test04_1125027() {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DistributorName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.supportDataActions.uncheckRadioButtonOnDistributor("purchaseOrderShortCodeUsedFlag");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderISANameUsedFlag");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderMedClassUsedFlag");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderDateUsedFlag");
		test.supportDataActions.clickSaveButtonOnDistributor("save");

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"), true);
		test.supportDataActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		medItem = test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		test.supportDataActions.openPurchaseOrderManualcard();
		Assert.assertTrue(test.siteConfigurationAction
				.valueNotPresentOnPO(TestDataPropertyReaderAndWriter.getProperty("DistributorCode")));
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
	}

	@Test(priority = 5, description = "VPLX: Manage Distributors: [Integration]: Current Date is not displayed in PO on Buyer dashboard in case Current date check box is unchecked.")
	public void Test05_1125030() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String date1 = dateFormat.format(date);

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DistributorName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderShortCodeUsedFlag");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderISANameUsedFlag");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderMedClassUsedFlag");
		test.supportDataActions.uncheckRadioButtonOnDistributor("purchaseOrderDateUsedFlag");
		test.supportDataActions.clickSaveButtonOnDistributor("save");

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"), true);
		test.supportDataActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		medItem = test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		test.supportDataActions.openPurchaseOrderManualcard();
		Assert.assertTrue(test.siteConfigurationAction.valueNotPresentOnPO(date1));
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
	}
}
