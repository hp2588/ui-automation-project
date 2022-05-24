package com.org.tests.mainmenu.barcodegladded;


import static com.org.automation.utils.YamlReader.getData;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1059102 extends BaseTest{

	String barcode;
	
	@Test(priority = 1,enabled = true, description = "User will get redirect to Unverified Links screen after clicking on the Unlink button on verify linked item screen.")
	public void Test01_1059102_1077945() 
	
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
		}
	
	@Test(priority = 2,enabled = true, description = "User get redirect to Bar Code management screen after clicking on the Relink button on verify linked item screen.")
	public void Test02_1059102_1077947() 
	
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
		test.siteConfigurationAction.verifyPickRoutingRuleCancelButton("unverified");
		test.siteConfigurationAction.clickPickRoutingRuleButton("unverified");	
		test.supportDataActions.clickButton("edit");
		test.siteConfigurationAction.clickPickRoutingRuleButton("Relink");		
	}
		}
