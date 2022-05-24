package com.org.tests.mainmenu.locassmanagingstoragebinoptions;


import static com.org.automation.utils.YamlReader.getData;

import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1088980 extends BaseTest{

	String name, shortName;
	
	@Test(priority = 1,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] -User is able to click print all bin labels for a shelf.")
	public void Test01_1088980_1125308() 	{
		
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		Assert.assertTrue(test.siteConfigurationAction.clickEditItemAction());
		test.siteConfigurationAction.clickBinActions();
		test.siteConfigurationAction.verifyGroupAction("Print Bin Labels");
		
	}
	
	
	@Test(priority = 2,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] -User is able to view the option for print label for grouped bins.")
	public void Test02_1088980_1125313() {		
		
		test.siteConfigurationAction.verifyGroupAction("Print Bin Labels");
		
	}
	
	
	// TODO - yugal - requires refactoring - Dec 30 - use clickBinActions to click Shelf 1 - Print All Labels
	@Test(priority = 3,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): ["
			+ "UI] -User is able to click print all bin labels for a shelf.")
	public void Test03_1088980_1125310() {	
		
	//	Assert.assertTrue(test.siteConfigurationAction.clickShelfOptions());
		test.siteConfigurationAction.hoverOverShelf("Shelf 1");
		test.siteConfigurationAction.clickShelf1();
		test.siteConfigurationAction.clickShelfDots();
		test.siteConfigurationAction.verifyPrintLabelOnShelf();
		
	}
	
	
	@Test(priority = 4,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] -User is able to view the option for print slot label.")
	public void Test04_1088980_1125312() {
		
		Assert.assertTrue(test.siteConfigurationAction.clickSlotOptions());
		Assert.assertTrue(test.siteConfigurationAction.clickPrintSlotBinLables());
		// Assert.assertEquals(test.siteConfigurationAction.getPopupText(), "Slot labels printed sucessfully.");
		
	}
	
	
}				
