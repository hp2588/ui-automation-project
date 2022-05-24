package com.org.tests.mainmenu.barcodegladded;


import static com.org.automation.utils.YamlReader.getData;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1075337 extends BaseTest{

	String barcode;
	
	@Test(priority = 1,enabled = true, description = "External system Link rejection and unlink table is read only")
	public void Test01_1075337_1091122() 
	
	{
		test.landingPageActions.navigateToFeature("Barcodes");
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.selectDropDownValue(getData("ProcessInvoices.ExternalSystemName"));
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		
	}
	
	@Test(priority = 2,enabled = true, description = "External system Link rejection and unlink table can store max 20 records in last come first serve basis")
	public void Test02_1075337_1091125() 
	
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode("0100306977757070171005032328717621abcd123456789"));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.selectDropDownValue(getData("ProcessInvoices.ExternalSystemName"));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSortArrowItemID());
		Assert.assertFalse(test.siteConfigurationAction.cannotSortOnBasisOfActions());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
	}
	
	@Test(priority = 3,enabled = true, description = "System notifies user that link is rejected if user try to unlink the item linked via PIS")
	public void Test03_1075337_1091142() 
	
	{	test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode("0100306977757070171005032328717621abcd123456789"));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		test.siteConfigurationAction.clickPickRoutingRuleButton("Unlink");
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());	
		
		}
	
	@Test(priority = 4,enabled = true, description = "System notifies user that link is rejected if user try to unlink the item which is preferred NDC")
	public void Test04_1075337_1091150() 
	
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.selectDropDownValue(getData("ProcessInvoices.ExternalSystemName"));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSortArrowItemID());
		Assert.assertFalse(test.siteConfigurationAction.cannotSortOnBasisOfActions());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		test.siteConfigurationAction.clickPickRoutingRuleButton("Unlink");
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());	
		
		}
	
	@Test(priority = 5,enabled = true, description = "System notifies user that link is rejected if user try to Link a barcode which is already linked with another item.")
	public void Test05_1075337_1091153() 
	
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode("01003800420200101710050"));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.selectDropDownValue(getData("ProcessInvoices.ExternalSystemName"));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSortArrowItemID());
		Assert.assertFalse(test.siteConfigurationAction.cannotSortOnBasisOfActions());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		test.siteConfigurationAction.clickPickRoutingRuleButton("Unlink");
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());	
	}

	  @Test(priority = 6,enabled = true, description = "System notifies user that link is Accepted if user try to unlink a barcode which is neither PIS nor preferred NDC.")
	public void Test06_1075337_1091156() throws InterruptedException {
		  
		  	test.landingPageActions.navigateToMenu("Main Menu");
			test.landingPageActions.navigateToFeature("Barcodes");
			Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode("2009631436"));
			Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
			test.siteConfigurationAction.selectDropDownValue(getData("ProcessInvoices.ExternalSystemName"));
			Assert.assertTrue(test.siteConfigurationAction.clickOnSortArrowItem());
			Assert.assertFalse(test.siteConfigurationAction.cannotSortOnBasisOfActions());
			  }	
	  
}
