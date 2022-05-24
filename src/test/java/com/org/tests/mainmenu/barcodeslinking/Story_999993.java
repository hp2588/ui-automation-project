package com.org.tests.mainmenu.barcodeslinking;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


@Listeners(com.org.listeners.TestListener.class)

public class Story_999993 extends BaseTest{

	String medItem_one;
	String medItem_two;
	
	// TODO - refactoring required - Yugal
	@Test(priority = 1,enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking  : [UI] User can search on the basis of generic Name of item.")
	public void Test011069396() 
	{
		
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode("Meme"));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		
	}
	
	// TODO - refactoring required - Yugal
	@Test(priority = 2,enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking : [UI] User can search on the basis of item id .")
	public void Test02_1069400() 
	{	
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode("01003800420200101710050"));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		
	}
	
	// TODO - refactoring required - Yugal
	@Test(priority = 3,enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking : [UI] User cannot  search on the basis of Actions.")
	public void Test03_1069408() 
	{
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode("link"));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		
	}
	
	// TODO - refactoring required - Yugal
	@Test(priority = 4,enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking  : [UI] User can Sort on the basis of item id .")
	public void Test04_1069429() 
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode("2009631436"));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickOnSortArrowItemID());
		
	}
	
	// TODO - refactoring required - Yugal
	@Test(priority = 5,enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking  : [UI] User can Sort on the basis of Item Name .")
	public void Test05_1069431() 
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode("2009631436"));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickOnSortArrowItem());
		
	}
	
	// TODO - refactoring required - Yugal
	@Test(priority = 6,enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking: [UI] User cannot Sort on the basis of Actions column.")
	public void Test06_1130516() throws InterruptedException 
	{
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode("2009631436"));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickOnSortArrowItem());
		Assert.assertFalse(test.siteConfigurationAction.cannotSortOnBasisOfActions());
		
	}	  
	
	// TODO - refactoring required - Yugal	
	@Test(priority = 7,enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking : [UI]  Detail page get opened after the linking is done with the item.")
	public void Test07_1069444() 		
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(
				TestDataPropertyReaderAndWriter.getProperty("ProductID")));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyScanAnother());
		
	}
	  
}
