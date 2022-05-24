package com.org.tests.barcodelinkingunlinking;

import static com.org.automation.utils.YamlReader.getData;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_999999 extends BaseTest{

	String medItem_one;
	String barcode;
	
	@Test(priority = 1,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] Unlink option is available on the details page for linked item .")
	public void Test01_1070145()
	{
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.supportDataActions.verifyNewlyAddedRecordNameInList(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.siteConfigurationAction.verifySearchBox("itemSearch");
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.clickButton("ScanAnother");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.verifybuttonOnBarcodeScreen("Unlink");
		test.siteConfigurationAction.clickButton("ScanAnother");

	}

	@Test(priority = 2,enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking : [UI] User can search on the basis of item id for selected ES")
	public void Test02_1069400() 
	
	{
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		barcode = "abc";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim(),"itemSearch");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());

	}
	
	@Test(priority = 3,enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking : [UI] Detail page get opened after the linking is done with the item fr selected ES")
	public void Test03_1069444() 
	
	{
	
		test.landingPageActions.navigateToFeature("Barcodes");
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.clickButton("link");
		long date_time=System.currentTimeMillis();
		test.siteConfigurationAction.clickButton("ScanAnother");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyBarcodeDetails("Linked Item").contains(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim()));
		Assert.assertTrue(test.siteConfigurationAction.verifyBarcodeDetails("Linked By").contains(getData("Auth.user").trim()+" "+date_time));
		Assert.assertTrue(test.siteConfigurationAction.verifyBarcodeDetails("Matched On").contains("Raw scan code"));
		Assert.assertTrue(test.siteConfigurationAction.verifyBarcodeDetails("Link Source").contains("Local system (BD Pyxis TM Logistics)"));

	}
	
	@Test(priority = 4, enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking  : [UI] User can Sort on the basis of item id")
	public void Test04_1069429() 
	
	{
	
		test.landingPageActions.navigateToFeature("Barcodes");
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertEquals(test.siteConfigurationAction.captureDataForParticularColumnForBarcode("1"),test.siteConfigurationAction.sortDataForParticularColumnInDescendingOrderForBarcode("1"));
		test.siteConfigurationAction.verifyAndClickSortIcon("Item ID");
		Assert.assertEquals(test.siteConfigurationAction.captureDataForParticularColumnForBarcode("1"),test.siteConfigurationAction.sortDataForParticularColumnInAscendingOrderForBarcode("1"));
		
	}
	
	@Test(priority = 5, enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking : [UI] User can Sort on the basis of Generic name")
	public void Test05_1069431() 
	
	{
	
		test.landingPageActions.navigateToFeature("Barcodes");
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.verifySearchBox("itemSearch");
		Assert.assertEquals(test.siteConfigurationAction.captureDataForParticularColumnForBarcode("2"),test.siteConfigurationAction.sortDataForParticularColumnInDescendingOrderForBarcode("2"));
		test.siteConfigurationAction.verifyAndClickSortIcon("Item");
		Assert.assertEquals(test.siteConfigurationAction.captureDataForParticularColumnForBarcode("2"),test.siteConfigurationAction.sortDataForParticularColumnInAscendingOrderForBarcode("2"));
		
	}
	
	@Test(priority = 5, enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking: [UI] User cannot Sort on the basis of Actions column.")
	public void Test05_1069432() 
	
	{
	
		test.landingPageActions.navigateToFeature("Barcodes");
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.verifySearchBox("itemSearch");
		test.siteConfigurationAction.verifySortIconIsUnavailable("Action");	
	}
}
