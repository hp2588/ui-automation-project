package com.org.tests.mainmenu.barcodeslinking;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


@Listeners(com.org.listeners.TestListener.class)

public class Story_999999 extends BaseTest{

	String medItem_one;
	String barcode;
	
	@Test(priority = 1,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking  : [UI]  Unlink option is available  on the details page for linked item ")
	public void Test01_1070145() 
	{
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.clickButton("link");
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());	
		
	}
	
	@Test(priority = 2,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking  : [UI] User get a message for confirmation when Unlinking the item.")
	public void Test02_1070157() 
	{	
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.clickButton("link");
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkMessage());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		
	}
	
	@Test(priority = 3,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] bar code  remain linked to item if user cancel the confirmation message .")
	public void Test03_1070163() 
	{	
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.clickButton("link");
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkMessage());
		Assert.assertTrue(test.siteConfigurationAction.verifyBarcodeStillAssociated());
		
	}
	
	@Test(priority = 4,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking  : [UI] item  get unlinked when user click ok Continue the conformation message .")
	public void Test04_1070165() 
	{	
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.clickButton("link");
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		
	}
	
	@Test(priority = 5,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking  : [UI]  Item which get unlinked via unlinked button are visible in unlinked item list .")
	public void Test05_1070166() 
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
		
	}
	
	@Test(priority = 6,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking  : [UI] User  get a message that you cant unlink if item is linked from external system.")
	public void Test06_1070167() 
	
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.clickButton("link");
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkMessage());
		Assert.assertTrue(test.siteConfigurationAction.verifyBarcodeStillAssociated());
		
	}
	
	@Test(priority = 7,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] User can cancel the Rejection message for unlink and scan another.")
	public void Test07_1070535() 
	
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.clickButton("link");
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkMessage());
		Assert.assertTrue(test.siteConfigurationAction.verifyBarcodeStillAssociated());
		Assert.assertTrue(test.siteConfigurationAction.verifyScanAnotherBarcode());
		
	}
	
	@Test(priority = 8,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] User gets a message that you cant unlink preferred NDC.")
	public void Test08_1070539() 
	{	
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(
				TestDataPropertyReaderAndWriter.getProperty("ProductID")));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		/// Assert.assertTrue(test.siteConfigurationAction.verifyCantUnlinkMessage());
		Assert.assertTrue(test.siteConfigurationAction.verifyScanAnotherBarcode());
	}
	
	@Test(priority = 9,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] User cancels the Rejection message for the Unlinking of preferred NDC.")
	public void Test09_1070541() 
	{	
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(
				TestDataPropertyReaderAndWriter.getProperty("ProductID")));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		//Assert.assertTrue(test.siteConfigurationAction.verifyCantUnlinkMessage());
		Assert.assertTrue(test.siteConfigurationAction.verifyScanAnotherBarcode());
		
	}
	
	@Test(priority = 10,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking  : [UI] After clicking on rejection message of  NDC user can click on scan another.")
	public void Test10_1070543() 
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(
				TestDataPropertyReaderAndWriter.getProperty("ProductID")));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkMessage());
		Assert.assertTrue(test.siteConfigurationAction.verifyBarcodeStillAssociated());
		Assert.assertTrue(test.siteConfigurationAction.verifyScanAnotherBarcode());
		
	}
	
	
	@Test(priority = 11,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] Item gets unlinked when product id is not preferred")
	public void Test11_1070546() 
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.clickButton("link");
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		// not needed on unlink
		// Assert.assertTrue(test.siteConfigurationAction.clickResetButton());
		
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.clickRelinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
	
	}
	
	@Test(priority = 12,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI] Item gets unlinked for Raw bar code.")
	public void Test12_1070547() 
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.selectValueForDropDown("pisDropdown", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.clickButton("link");
		Assert.assertTrue(test.siteConfigurationAction.verifyUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickUnlinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		
		//Assert.assertTrue(test.siteConfigurationAction.clickResetButton());
		
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		Assert.assertTrue(test.siteConfigurationAction.clickRelinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());
		
	}
	
}
