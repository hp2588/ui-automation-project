package com.org.tests.mainmenu.locassmanagingstoragebinoptions;


import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1062135 extends BaseTest{

	String itemName1,itemID1,name1,shortName;
	@Test(priority = 1,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] - User is able to see the add vertical divider option on Action on bins.")
	public void Test01_1124228() throws InterruptedException {
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		Assert.assertTrue(test.siteConfigurationAction.clickEditItemAction());
		test.siteConfigurationAction.clickBinActions();
		test.siteConfigurationAction.verifyAddHorizontalDivider();
		test.siteConfigurationAction.verifyAddVerticalDivider();

	}
	
	
	@Test(priority = 2,enabled = true, description = "VPLX:Location-Assignment-Managing Storage Area(Bin Actions): "
			+ "[UI] - User is able to see the add horizontal divider option on Action on bins.")
	public void Test02_1124229() {	
		
		test.siteConfigurationAction.verifyAddHorizontalDivider();
		
	}
	
	
	
	// TODO - Automation - Yugal
	/*
	@Test(priority = 3,enabled = true, description = "To verify User is not allowed to remove the bin  if not the last bin and empty")
	public void Test01_1062135_1124233() {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.siteConfigurationAction.selectDropDownValue(getData("ProcessInvoices.FacilityName"));
		Assert.assertTrue(test.siteConfigurationAction.clickEditISAItemAction());
		Assert.assertTrue(test.siteConfigurationAction.clickISAConfiguration());
		Assert.assertTrue(test.siteConfigurationAction.sendKeysVerticalDividers("3"));
	}
	*/
	
	
	@Test(priority = 4,enabled = true, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): "
			+ "[UI] - User is allowed to add same no of  horizontal/vertical divider to the selected bin as ISA setting.")
	public void Test03_1124234() {	
		test.landingPageActions.navigateToMenu("Main Menu");
		
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber", "8");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersVerticalNumber", "8");
		test.storageAreaAction.clickSaveButton();
        
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		Assert.assertTrue(test.siteConfigurationAction.clickEditItemAction());
		
		test.siteConfigurationAction.clickBinActions();
		test.siteConfigurationAction.clickAddVerticalDivider();
		test.siteConfigurationAction.verifySuccessMessageOnAddDivider("Successfully added Divider.");
		test.siteConfigurationAction.clickBinActions();
		test.siteConfigurationAction.clickAddHorizontalDivider();
		test.siteConfigurationAction.verifySuccessMessageOnAddDivider("Successfully added Divider.");
		
	}
	
	
	@Test(priority = 5,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): [UI] - User is allowed to add upto 8 horizontal dividers .")
	public void Test04_1124237() {
		for (int i = 0; i < 7;i++)
		{
			test.siteConfigurationAction.clickBinActions();
			test.siteConfigurationAction.clickAddHorizontalDivider();
			test.siteConfigurationAction.verifySuccessMessageOnAddDivider("Successfully added Divider.");
		}	
	}
	
	
	@Test(priority = 6,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): [UI] - User is allowed to add upto 8 vertical dividers .")
	public void Test05_1124238() {	
		for (int i = 0; i < 7;i++)
		{
			test.siteConfigurationAction.clickBinActions();
			test.siteConfigurationAction.clickAddVerticalDivider();
			test.siteConfigurationAction.verifySuccessMessageOnAddDivider("Successfully added Divider.");
		}
	} 

}
