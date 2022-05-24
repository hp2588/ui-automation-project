package com.org.tests.itemsetupNDCandPreferred;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;



public class Story_1077206_1056650 extends BaseTest {
	
	String itemID, barcode, productID, facilityOnWFAScreen=TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
	String  productID2, distributorNew,productID_at_systemLevel,distributorName,itemCode,ItemName;
	
	@Test(priority = 1, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to add multiple productID at system level."
			+ ""
			+ "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to mark a product ID as preferred without adding vendors."
			+ ""
			+ "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to add a unique vendor item code on Manage Distributors pop up."
			+ ""
			+ "VPLX:Item Setup - Management of NDCs and setting Preferred: [UI] - List of all the distributors added at the system level displays when user adds it."
			+ ""
			+ "VPLX:Item Setup - Management of NDCs and setting Preferred: [UI] - User is able to enter item code alphanumeric having length 50 Characters"
			+ ""
			+ "VPLX:Item Setup - Management of NDCs and setting Preferred: [UI] - Item code corresponding to preferred distributor gets save on clicking on save button")
	public void Test01_Test02_Test03_Test04_Test05_Test06_1098513_1098669_1098681_1089033_1088984_1088982() {
		
				
		test.landingPageActions.navigateToFeature("Item Management");
		//test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(facilityOnWFAScreen);
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		//test.siteConfigurationAction.enterRandomValueInRichInputField(facilityOnWFAScreen);
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(facilityOnWFAScreen);

		ItemName = test.siteConfigurationAction.enterRandomValueInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterRandomValueInInputField("itemId",
				"ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);

		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");

		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + System.currentTimeMillis()+ "0171005032328717621abcd123456789");

		//test.siteConfigurationAction.clickButton("search");
		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);

		// test.siteConfigurationAction.selectValueFromDropDown("manufacturerKey",manufacturer);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "12");
		//test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.clickToAddPreferredDistributor("Add Preferred Distributor");
		//test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		
		
		/*========TC : 1089033===============*/
		
		Assert.assertNotEquals(test.siteConfigurationAction.verifyDistributorAccountListIsNonEmpty(),0);
		//test.siteConfigurationAction.clickOnDistributorInfo(distributorNew);
		
		/*========TC : 1098681===============*/
		
		//test.siteConfigurationAction.enterDistributorItemCode(distributorNew, "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "1");
		
		/*========TC: 1088984===============*/
		test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "1", "ItemCod"+System.currentTimeMillis());
		
	
		
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputFieldItemCode("distributorItemCode","1"), 20,
				"[ASSERTION FAILED]: Max Length for input field Item Code is not 20");
		// test.siteConfigurationAction.enterDistributorInfo("distributorItemCode",
		// "2", "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		
		
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");

		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + System.currentTimeMillis()+ "0171005032328717621abcd123456789");

		//test.siteConfigurationAction.clickButton("search");
		productID2 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID2);

		/*// test.siteConfigurationAction.selectValueFromDropDown("manufacturerKey",manufacturer);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "12");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.verifyAddedProductID(productID2);
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(distributorNew);
		test.siteConfigurationAction.enterDistributorItemCode(distributorNew, "" + System.currentTimeMillis());
		//test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "1");
		//test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "1", "" + System.currentTimeMillis());
		// test.siteConfigurationAction.enterDistributorInfo("distributorItemCode",
		// "2", "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
*/
		Assert.assertTrue(test.siteConfigurationAction.selectPreferredDistributor(productID2));
		
		
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		
		
	}
	

	@Test(priority = 7, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to change the preferrence of productIDs added at system and facility level both.")
	public void Test07_1098519() {
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(ItemName);
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		Assert.assertTrue(test.siteConfigurationAction.selectPreferredDistributor(productID));
	}
	
	@Test(priority = 8, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to edit the distributor of productid added.")
	public void Test08_1098575() {
		
		test.siteConfigurationAction.clickToAddPreferredDistributorName(productID);
		
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		
				test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "2");
				
				distributorName=test.siteConfigurationAction.getSelectedDistributorName("preferredDistributor", "2");
		
				test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "2", "" + System.currentTimeMillis());
				itemCode=test.siteConfigurationAction.getDistributorInfo("distributorItemCode", "2");
		// test.siteConfigurationAction.enterDistributorInfo("distributorItemCode",
		// "2", "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");

		
	}
	
	@Test(priority = 9, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to add and edit the Brandname of productid added at system level")
	public void Test09_1098591() {
		
		test.siteConfigurationAction.enterRandomValueInInputField("tradeName", "RanBaxy");
	}
	
	@Test(priority = 10, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to edit the manufacturer from dropdown list of productid added.")
	public void Test10_1098603() {
		
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("manufacturerKey", 1);
	}
	
	@Test(priority =11, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to edit the package size of productid added.")
	public void Test11_1098652() {
		
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "6");
	}
	
	@Test(priority = 12, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is unable to add same vendor item code with same distributor throughout the application on Manage Distributors pop up."
			+ ""
			+ "VPLX:Item Setup - Management of NDCs and setting Preferred: [UI] - Item code is unique  for each distributor with respect to item.")
	public void Test12_Test13_1098698_1089037() {
		
test.siteConfigurationAction.clickToAddPreferredDistributorName(productID2);
		
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "2");
		
		test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "2", itemCode);
		// test.siteConfigurationAction.enterDistributorInfo("distributorItemCode",
		// "2", "" + System.currentTimeMillis());
		test.supportDataActions.clickButtonWithOutAnyWait("primary");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader("Distributor of name "+distributorName+" with item code "+itemCode+" already exists.");

		
	}

}
