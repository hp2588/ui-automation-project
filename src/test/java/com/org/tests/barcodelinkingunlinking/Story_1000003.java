package com.org.tests.barcodelinkingunlinking;


import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1000003 extends BaseTest{

	String barcode;
	
	@Test(priority = 1,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI]  Relink option is available  on the details page for linked item .")
	public void Test01_999993_1070548() 
	
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
		test.siteConfigurationAction.verifybuttonOnBarcodeScreen("Relink");
	

	}
	
	@Test(priority = 2,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking: [UI] User get a message for confirmation when Relinking the item.")
	public void Test01_999993_1070549() 
	
	{	
		test.siteConfigurationAction.clickRelinkButton();
		test.siteConfigurationAction.verifyConfirmationMessageOnBarcodeRelinking("Relinking will remove the barcode from the system.","Do you want to continue ");
		
		
	}
	
	
	@Test(priority = 3,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI]  Bar code get remain linked to item if user cancel the confirmation message .")
	public void Test01_999993_1070554() 
	
	{	
		test.siteConfigurationAction.clickButton("secondary");
		test.siteConfigurationAction.verifybuttonOnBarcodeScreen("Scan Another");
		test.siteConfigurationAction.verifybuttonOnBarcodeScreen("Relink");
	}
	
	@Test(priority = 4,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] item  get Relinked when user click Continue the conformation message .")
	public void Test01_999993_1070564() 
	
	{	
		test.landingPageActions.navigateToFeature("Barcodes");
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifybuttonOnBarcodeScreen("Relink");
		test.siteConfigurationAction.clickButton("Relink");
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName2").trim());
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.clickButton("link");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.supportDataActions.refreshPage();
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.clickButton("link");
		
		
	}
	
	/*@Test(priority = 5,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] Rejection message  shows , if user want to relink item linked via external system.")
	public void Test01_999993_1070566() 
	
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		
		
	}*/
	
	@Test(priority = 4,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] Rejection message shows , if user want to relink item linked which is Preferred NDC..")
	public void Test01_999993_1070567() 
	
	{	
		
		
	}
	
}