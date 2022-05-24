package com.org.tests.mainmenu.locassmanagingstoragebinoptions;


import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1058168 extends BaseTest{

	@Test(priority = 1,enabled = true, description = "VPLX:Location Assignment - Managing Storage Area(Bin Actions): "
			+ "[UI] -User is able to manually assign a item to a available slot in PLX ISA.")
	public void Test01_1125289() {
		
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		
	}
	
	
	@Test(priority = 2,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] -User is able to view purple colour in the slot when item is already assigned.")
	public void Test02_1125284() {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		Assert.assertTrue(test.siteConfigurationAction.clickEditItemAction());
		/*Assert.assertTrue(test.siteConfigurationAction.clickAssignButtonForLocation());
		test.siteConfigurationAction.selectDropDownValueISA(getData("ProcessInvoices.ISANAme"));
		Assert.assertTrue(test.siteConfigurationAction.clickSaveAssignButtonForLocation()); */
		Assert.assertTrue(test.siteConfigurationAction.verifyPurpleColor(getData("Location.Purple")));
		
	}
	
	
	@Test(priority = 3,enabled = true, description = "VPLX:Location-Assignment(Bin Actions): "
			+ "[UI] -User is able to view the empty location on layout screen as green colour.")
	public void Test03_1125285() {	
		Assert.assertTrue(test.siteConfigurationAction.verifyGreenColor(getData("Location.Green")));
	}
	
	
	
}
