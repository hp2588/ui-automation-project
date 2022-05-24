package com.org.tests.itemsetupNDCandPreferred;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1055401 extends BaseTest {
	String itemID, barcode,itemName,
			productID, productID2, distributorNew, productID_at_systemLevel;
	
	//@Test(priority = 1, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] - User validates the Package size field  and its  default value as 1 on product ID tab at pharmacy Item")

	public void Test01_1125204() {

		test.landingPageActions.navigateToFeature("Item Management");
		//test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		// test.siteConfigurationAction.enterRandomValueInRichInputField(facilityOnWFAScreen);
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

		 /*itemName = test.siteConfigurationAction.enterRandomValueInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterRandomValueInInputField("itemId",
				"ItemID" + System.currentTimeMillis());*/
		itemID ="ItemID1611316175652";	
		itemName ="ItemName1611316154752";
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);

		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
	//	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);

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
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		// test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.clickToAddPreferredDistributor("Add Preferred Distributor");
		// test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "1");
		test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "1", "" + System.currentTimeMillis());
		// test.siteConfigurationAction.enterDistributorInfo("distributorItemCode",
		// "2", "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		//test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		//test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		//test.siteConfigurationAction.clickRecordNameToEdit(itemName);
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		productID_at_systemLevel = test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "6");

	}

	//@Test(priority = 2, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] - User validates the manufacturer,Brand Name field on product ID tab at facility level.")

	public void Test02_1125203() {

		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("manufacturerKey", 1);
		// test.siteConfigurationAction.enterRandomValueInInputField("tradeName",
		// "RanBaxy");

	}

	//@Test(priority = 3, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to view the productIds added at system level at facility level.")

	public void Test03_1125194() {

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.verifyAddedProductID(productID_at_systemLevel);

	}

	//@Test(priority = 4, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -First product ID will be set as preferred automatically.")
	public void Test04_1129982() {

		Assert.assertTrue(test.siteConfigurationAction.verifyRadioButtonIsChecked("preferred"), "false");

	}

	//@Test(priority = 5, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] - User validates the Replacement cost field on product ID tab at facility level.")
	public void Test05_1125206() {

		// test.siteConfigurationAction.enterRandomValueInInputField("replacementCost",
		// "666666.22");
		/*
		 * test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		 * test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(
		 * getData("SuccessMessages.AddHoldReason"));
		 */
		
		test.siteConfigurationAction.enterRandomValueInReplacementCostInput("replacementCost","999999.99");
		  test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		/*  test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(
		  getData("SuccessMessages.AddHoldReason"));*/
	}

	@Test(priority = 6, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to edit the productId at facility level and click on save button."
			+ "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is able to change the preferred ProductID at facility level if it is not checked in 'Set Participating facilities'")
	public void Test06_Test07_1125198_1125201() {
		
		itemID ="ItemID1611316175652";	
		itemName ="ItemName1611316154752";
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Distributor");
		distributorNew = test.siteConfigurationAction.enterRandomValueInInputField("descriptionText",
				"Distributor" + System.currentTimeMillis());

		test.siteConfigurationAction.enterRandomValueInInputField("shortCode", "Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

		test.landingPageActions.navigateToFeature("Item Management");
		//test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());

		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		productID_at_systemLevel = test.siteConfigurationAction.verifyAddedProductID(productID);

		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
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

		test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "2");
		test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "2", "" + System.currentTimeMillis());

		// test.siteConfigurationAction.clickOnDistributorInfo(distributorNew);
		// test.siteConfigurationAction.clickDistributorInfo("preferredDistributor",
		// "2");
		test.siteConfigurationAction.enterDistributorItemCode(distributorNew, "" + System.currentTimeMillis());
		// test.siteConfigurationAction.enterDistributorInfo("distributorItemCode",
		// "2", "" + System.currentTimeMillis());
		// test.siteConfigurationAction.enterDistributorInfo("distributorItemCode",
		// "2", "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		Assert.assertTrue(test.siteConfigurationAction.selectPreferredDistributor(productID2));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		
		/*===============Login with SUPPORT USER===============*/
		
		test.closeBrowserSession();

		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
				getData("Auth.passwordSupportUser").trim(),
				TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Set Participating Facilities");
		Assert.assertFalse(
				test.siteConfigurationAction.verifyChecboxOfParticipatingFacilityIsChecked(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim()),
				"[Assertion Failed]: Checkbox of Participating Facility is Checked.");

	}

	@Test(priority = 8, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User will get a toast message if distributor is not mapped to a facility .")
	public void Test08_1125195() {

		test.supportDataActions.clickButton("cancel");

		// test.landingPageActions.navigateToFeature("Item Management");
		// test.siteConfigurationAction.enterRandomValueInRichInputField(facilityOnWFAScreen);

		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");

		test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

		test.siteConfigurationAction.clickToAddPreferredDistributorNameAtFacilityLevel(productID2);

		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");

		test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "1");
		test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "2");
		// test.siteConfigurationAction.clickOnDistributorInfo(distributorNew);
		test.siteConfigurationAction.selectNewPreferredDistributorAtFacilityLevel(distributorNew);

		test.supportDataActions.clickButtonWithOutAnyWait("primary");

		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(
				"This distributor is not mapped to this facility, order for this item will not be generated.");

	}

	@Test(priority = 9, description = "VPLX: Item Setup - Management of NDCs and setting Preferred: [UI] -User is not able to change the preferred ProductID at facility level if it is checked in 'Set Participating facilities'")
	public void Test09_1125207() {

		test.supportDataActions.clickButton("cancel");

		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Set Participating Facilities");

		test.siteConfigurationAction.SelectChecboxOfParticipatingFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(
				"Facilities are successfuly saved as a participating facilities.");

		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		Assert.assertFalse(test.siteConfigurationAction.selectPreferredDistributor(productID));

	}

}