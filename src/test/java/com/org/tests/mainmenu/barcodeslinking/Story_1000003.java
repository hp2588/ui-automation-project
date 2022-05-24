package com.org.tests.mainmenu.barcodeslinking;


import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1000003 extends BaseTest{

	String barcode;
	
	@Test(priority = 1,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] Relink option is available "
			+ "on the details page for linked item .")
	public void Test01_1070548() 
	{
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.clickRelinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		Assert.assertTrue(test.siteConfigurationAction.clickResetButton());
		
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());	
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		
	}
	
	@Test(priority = 2,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking: [UI] User get a message for confirmation when Relinking the item.")
	public void Test02_1070549() 
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.clickRelinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		Assert.assertTrue(test.siteConfigurationAction.clickResetButton());
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		
	}
	
	
	@Test(priority = 3,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI]  Bar code get remain linked to item if user cancel the confirmation message .")
	public void Test03_1070554() 
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkMessage());
		
		Assert.assertTrue(test.siteConfigurationAction.verifyBarcodeStillAssociated());
		
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
	}
	
	
	@Test(priority = 4,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] item  get Relinked when user click Continue the conformation message .")
	public void Test04_1070564() 
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.clickRelinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		Assert.assertTrue(test.siteConfigurationAction.clickResetButton());
		
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		
	}
	
	
	@Test(priority = 5,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] Rejection message  shows , if user want to relink item linked via external system.")
	public void Test05_1070566() 
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
			
	}
	
	
	@Test(priority = 6,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] Rejection message  shows , if user want to relink item linked via  Preferred NDC.")
	public void Test06_1070567() 
	{		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + TestDataPropertyReaderAndWriter.getProperty("ProductID") 
			+ "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkMessage());
		Assert.assertTrue(test.siteConfigurationAction.verifyBarcodeStillAssociated());
		Assert.assertTrue(test.siteConfigurationAction.verifyScanAnotherBarcode());
		
	}
	
	
	@Test(priority = 7,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] Item which has not preferred NDC gets relinked .")
	public void Test07_1070570() 
	{		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		// not needed in case of unlinking
		// Assert.assertTrue(test.siteConfigurationAction.clickResetButton());
		
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.clickRelinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		
	}
	
	
	@Test(priority = 8,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking: [UI] Item with Raw bar code gets relinked")
	public void Test08_1070571() 
	{		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621";
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.clickRelinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		
	}

}
