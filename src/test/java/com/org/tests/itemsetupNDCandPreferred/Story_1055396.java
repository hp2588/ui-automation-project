package com.org.tests.itemsetupNDCandPreferred;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

@Listeners(com.org.listeners.TestListener.class)

public class Story_1055396 extends BaseTest {

	String itemID, barcode, productID, productID2,ItemName;

	@Test(priority = 1, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User validates the mandatory fields of attributes tab at system level.")
	public void Test01_1078800() {
		/*test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		facilityOnWFAScreen = test.siteConfigurationAction.getFacilityFromISAScreen();
		test.siteConfigurationAction.clickActionbutton("Cancel");
*/
		test.landingPageActions.navigateToFeature("Item Management");

		//test.siteConfigurationAction.enterRandomValueInRichInputField(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		// test.siteConfigurationAction.clickCheckboxfacilityitemlevel(facilityOnWFAScreen);

		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");

		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);

	}

	@Test(priority = 2, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User fills all the details in the attributes tab,so ProductID tab is enabled.")
	public void Test02_1078805() {

		ItemName  = test.siteConfigurationAction.enterRandomValueInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterRandomValueInInputField("itemId",
				"ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));

		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
	}

	@Test(priority = 3, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User clicks on Add ProductID link and enters a productId of length 10 digits.")
	public void Test03_1078900() {

		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
	}

	@Test(priority = 4, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User creates only one productId for a item at system level."
			+ "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User checks the productID attributes under productID tab on Add item screen."
			+ "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User checks the productID field under ProuctId table on Add item screen.")
	public void Test04_Test05_Test06_1078910_1078938_1078952() {
		barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + System.currentTimeMillis()+ "0171005032328717621abcd123456789");

		//test.siteConfigurationAction.clickButton("search");

		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);

		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		// test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.clickToAddPreferredDistributor("Add Preferred Distributor");
		// test.siteConfigurationAction.verifyAddedProductID(productID);

	}

	@Test(priority = 5, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User is able to click on AddPrefrreddistributor and select a distributor.")
	public void Test07_1078963() {

		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "1");
	}

	@Test(priority = 6, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: When user clicks on save button preferred distributor is saved in productID table.")
	public void Test08_1079546() {

		// test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorCode"));
		test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "1", "" + System.currentTimeMillis());

		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);

	}

	@Test(priority = 7, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI]: User clicks on delete button and is able to delete the productId mapped with a item.")
	public void Test09_1079614() {

		test.supportDataActions.clickOnEditLinkCorresspondingToItem(ItemName); 
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");

		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForRO("barcodeValue",
				"01003" + System.currentTimeMillis()+ "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID2 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID2);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID2);

		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "8");
		// test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.clickToAddPreferredDistributor("Add Preferred Distributor");
		// test.siteConfigurationAction.verifyAddedProductID(productID2);
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		// test.siteConfigurationAction.clickOnDistributorInfo(distributorNew);
		test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "2");
		// test.siteConfigurationAction.enterDistributorItemCode(distributorNew,
		// "" + System.currentTimeMillis());
		test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "2", "" + System.currentTimeMillis());
		// test.siteConfigurationAction.enterDistributorInfo("distributorItemCode",
		// "2", "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(ItemName);
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");

		//test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");

		test.siteConfigurationAction.clickToDeleteAndVerifyProductID(productID2);

	}

}
