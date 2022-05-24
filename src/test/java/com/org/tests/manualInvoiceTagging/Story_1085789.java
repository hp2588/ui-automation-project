package com.org.tests.manualInvoiceTagging;

import static com.org.automation.utils.YamlReader.getData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1085789 extends BaseTest {
	String purchaseOrderDistributorName;
	ArrayList<String> priceTaggingList = new ArrayList<String>(Arrays.asList("Select", "WAC","GPO", "340B")); 
	String customTag1 = "tagg4";
	String customTag2 = "tagg5";
	
	@Test(priority = 1, description = ":VPLX: Price Update and Invoice Tagging (of facility items): [UI]: "
			+ "Minimum one tag is selected when \"Enable Price Tagging\" is selected.\r\n")
	public void Test01_1111023() {
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		
		test.siteConfigurationAction.clickTabWithoutWait("Settings");
		test.siteConfigurationAction.selectCheckboxItemsTab("enablePriceTagging", true);
		
		// disable all 
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox0", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox1", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox2", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox3", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox4", false);
		
		test.siteConfigurationAction.clickSaveButton();
		
		// verify that we are still on same page - that is we can't save without selecting at least one tag
		test.siteConfigurationAction.verifycheckboxispresent("enablePriceTagging");
		// select one tag and save
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox0", true);
		Assert.assertEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("toggle0"), "true");
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickTabWithoutWait("Settings");
	}
	
	
	@Test(priority = 2, description = "VPLX: Price Update and Invoice Tagging (of facility items): [UI]: "
			+ "Five flags to be added to the Settings tab of the Facility Add/Edit screen, under \"Enable Price Tagging\".\r\n")
	public void Test02_1111022() {
		
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox0", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox1", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox2", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox3", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox4", true);
		
		// makes sure that the two input boxes are enabled 
		test.siteConfigurationAction.enterRandomValueInInputField("customText3", customTag1);
		test.siteConfigurationAction.enterRandomValueInInputField("customText4", customTag2);
		
	}
	
	
	@Test(priority = 3, description = "VPLX: Price Update and Invoice Tagging (of facility items): [UI]: "
			+ "Maximum of 5 alphanumeric+special characters for the custom tag.\r\n")
	public void Test03_1111025() {
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("customText3"), 5);
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("customText4"), 5);
		
		test.siteConfigurationAction.enterRandomValueInInputField("customText3", customTag1);
		test.siteConfigurationAction.enterRandomValueInInputField("customText4", customTag2);
		
		priceTaggingList.add(customTag1);
		priceTaggingList.add(customTag2);
	}
	
	
	@Test(priority = 4, description = "VPLX: Price Update and Invoice Tagging (of facility items): [UI]: "
			+ "Update price tag is active when corresponding tag is selected..")
	public void Test04_1111024() {
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox0", true);
		Assert.assertEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("toggle0"), "true");
		
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox1", true);
		Assert.assertEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("toggle1"), "true");
		
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox2", true);
		Assert.assertEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("toggle2"), "true");
		
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox3", true);
		Assert.assertEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("toggle3"), "true");
		
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox4", true);
		Assert.assertEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("toggle4"), "true");
	}
	
	
	@Test(priority = 5, description = "VPLX: Price Update and Invoice Tagging (of facility items): [UI]: ON and OFF of price update switch.\r\n" )
	public void Test05_1111040() {
		test.siteConfigurationAction.selectCheckboxItemsTab("toggle0", false);
		Assert.assertEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("toggle0"), "false");
		test.siteConfigurationAction.selectCheckboxItemsTab("toggle0", true);
		
		test.siteConfigurationAction.selectCheckboxItemsTab("toggle1", false);
		Assert.assertEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("toggle1"), "false");
		test.siteConfigurationAction.selectCheckboxItemsTab("toggle1", true);
		
		test.siteConfigurationAction.selectCheckboxItemsTab("toggle2", false);
		Assert.assertEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("toggle2"), "false");
		test.siteConfigurationAction.selectCheckboxItemsTab("toggle2", true);
		
		test.siteConfigurationAction.selectCheckboxItemsTab("toggle3", false);
		Assert.assertEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("toggle3"), "false");
		test.siteConfigurationAction.selectCheckboxItemsTab("toggle3", true);
		
		test.siteConfigurationAction.selectCheckboxItemsTab("toggle4", false);
		Assert.assertEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("toggle4"), "false");
		test.siteConfigurationAction.selectCheckboxItemsTab("toggle4", true);
		
		test.siteConfigurationAction.clickSaveButton();
	}
	
	
	// Obsolete
	// @Test(priority = 4, description = "VPLX: Price Update and Invoice Tagging (of facility items): [UI]: Maximum price Update switches can be turned ON.")
	public void TestX_1111027() {
		Assert.assertEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("checkbox0"), "true");
		Assert.assertEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("toggle0"), "true");
		Assert.assertNotEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("checkbox1"), "true");
		Assert.assertNotEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("checkbox2"), "true");
		Assert.assertNotEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("checkbox3"), "true");
		Assert.assertNotEquals(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNotPriceTagging("checkbox4"), "true");	
	}
	
	@Test(priority = 6, description = "VPLX: Price Update and Invoice Tagging (of facility items): [UI]: Tags drop down for electronic orders on Invoice receive screen.")
	public void Test06_1111041() {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.openPurchaseOrderElectroniccard();
		
		List<String> priceTagOptions = test.purchaseDashboardActions.getAllDataFromDropDown("priceTagName");
		Collections.sort(priceTagOptions);
		Collections.sort(priceTaggingList);
		
		Assert.assertEquals(priceTagOptions, priceTaggingList);
	}
	
	
	@Test(priority = 7, description = "VPLX: Price Update and Invoice Tagging (of facility items): [UI]: Tags drop down for manual orders.\r\n")
	public void Test07_1111041() {
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", "5");
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		test.supportDataActions.openPurchaseOrderManualcard();
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		
		test.supportDataActions.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		
		test.purchaseDashboardActions.verifyTypeDropdownIsUnavailableFoManualOrder("priceTagName");
	}
	
}
