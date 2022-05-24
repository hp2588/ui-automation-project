package com.org.tests.mainmenu.barcodegladded;

import static com.org.automation.utils.YamlReader.getData;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1064279 extends BaseTest{

	String barcode;
	
	@Test(priority = 1,enabled = true, description = "Error get removed for invalid bar code after clicking on the reset button")
	public void Test01_1064279_1085769() 
	
	{

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode("!&*@&**#*#"));
		Assert.assertTrue(test.siteConfigurationAction.clickResetButtonOnBarcode());
	}
	
	@Test(priority = 2,enabled = true, description = "Unlinking not perform if user click on reset after clicking on the relink.")
	public void Test02_1064279_1085774() 
	
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.selectDropDownValue(getData("ProcessInvoices.ExternalSystemName"));	
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		test.siteConfigurationAction.clickPickRoutingRuleButton("ScanAnother");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Barcodes"));
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		}
	@Test(priority = 3,enabled = true, description = "VPLX:Barcode Management: Link History  : [UI] A text area is available  on the Link Detail page")
	public void Test03_1064279_1091110() 
	
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.selectDropDownValue(getData("ProcessInvoices.ExternalSystemName"));	
		
	}
	
	@Test(priority = 4,enabled = true, description = "VPLX:Barcode Management: Link History  : [UI] A text area is available  on the Link Detail page")
	public void Test04_1064279_1091113() 
	
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.selectDropDownValue(getData("ProcessInvoices.ExternalSystemName"));
		//Assert.assertTrue(test.siteConfigurationAction.verifyColumnNameOnBarlinkPage());
	}
	
	@Test(priority = 5,enabled = true, description = "VPLX:Barcode Management: Link History  : [UI]  External system Link rejection and unlink table is also available when we open link detail page via the unverified links .")
	public void Test05_1064279_1091135() 
	
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		//Assert.assertTrue(test.siteConfigurationAction.verifyColumnNameOnBarlinkPage());
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.selectDropDownValue(getData("ProcessInvoices.ExternalSystemName"));	
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());
		test.siteConfigurationAction.clickPickRoutingRuleButton("ScanAnother");
	}
	
	@Test(priority = 6,enabled = true, description = "VPLX:Barcode Management: Link History  : [UI]  System notifies user that link is Accepted if user try to Link a already unlinked bar code with a valid item.")
	public void Test06_1064279_1091158() 
	
	{	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Barcodes");
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		test.siteConfigurationAction.selectDropDownValue(getData("ProcessInvoices.ExternalSystemName"));	
		//Assert.assertTrue(test.siteConfigurationAction.verifyColumnNameOnBarlinkPage());
		Assert.assertTrue(test.siteConfigurationAction.clickLinkHomeButton());	
		Assert.assertTrue(test.siteConfigurationAction.clickRelinkButton());
		Assert.assertTrue(test.siteConfigurationAction.clickContinueButton());	
		}
}
