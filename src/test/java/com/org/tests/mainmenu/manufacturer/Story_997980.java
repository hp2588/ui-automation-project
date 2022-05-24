package com.org.tests.mainmenu.manufacturer;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;

@Listeners(com.org.listeners.TestListener.class)

public class Story_997980 extends BaseTest{

	String manufacturerName;
	String manufacturerName_old;
	
	
	@Test(priority = 1, description = "VPLX: Manufacturer  : [UI] Search text box for search the manufacturer is available on the manufacturer page .")
	public void Test01_1078783() {
		
		//test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Manufacturers");
		test.supportDataActions.verifyLabelIsPresent("Manufacturers");
		test.supportDataActions.verifySearchBoxIsPresent("search");
	}
	
	@Test(priority = 2, description = "VPLX: Manufacturer  : [UI] Search text box for search the manufacturer is available with default placeholder \"Search for manufacturer\"")
	public void Test02_1078787() {
		
		test.supportDataActions.verifySearchBoxIsPresent("search");
		test.supportDataActions.verifySearchBoxDefaultText("search",getData("ManufacturerDetails.DefaultText"));		
	}
	
	@Test(priority = 3, description = "VPLX: Manufacturer  : [UI] User can search according to Name .")
	public void Test03_1078794() {
		
//		test.landingPageActions.navigateToFeature("Manufacturers");
//		test.supportDataActions.verifyLabelIsPresent("Manufacturers");
		
		test.supportDataActions.verifySearchBoxIsPresent("search");
		test.supportDataActions.verifySearchBoxDefaultText("search",getData("ManufacturerDetails.DefaultText"));
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Carousel"));
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Manufacturer");
		manufacturerName = test.supportDataActions.EnterRandomValueInManufacturerInputField("manufacturer",getData("ManufacturerDetails.ManufacturerName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchField(manufacturerName, "search");
		test.supportDataActions.verifyNewlyAddedManufacturerStatus(manufacturerName, "Active");
		test.supportDataActions.resetSearch();
		
	}
	
	@Test(priority = 4, description = "VPLX: Manufacturer  : [UI] User can search according to Status .")
	public void Test04_1078797() {
		
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Manufacturer");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Manufacturer"));
		manufacturerName_old = test.supportDataActions.EnterRandomValueInManufacturerInputField("manufacturer",getData("ManufacturerDetails.ManufacturerName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(manufacturerName_old);
		test.supportDataActions.enterSearchTermInSearchField(getData("ManufacturerDetails.ManufacturerStatus"), "search");
		Assert.assertTrue(test.supportDataActions.verifyManufacturerStatus());
	}
	
	@Test(priority = 5, description = "VPLX: Manufacturer  : [UI] Search will get remove after clicking on the X button .")
	public void Test05_1078798() {
		
		test.supportDataActions.enterSearchTermInSearchField(manufacturerName, "search");
		test.supportDataActions.resetSearch();
	}
	
	@Test(priority = 6, description = "VPLX: Manufacturer  : [UI] A message will get displayed when no matching search found")
	public void Test06_1078799() {
		
		test.supportDataActions.clearSearchBoxField("search");
		manufacturerName_old = getData("ManufacturerDetails.ManufacturerName") + System.currentTimeMillis();
		System.out.println(manufacturerName_old);
		test.supportDataActions.enterSearchTermInSearchField(manufacturerName_old,"search");
		Assert.assertEquals(test.siteConfigurationAction.getNoDataText(), getData("ManufacturerDetails.ErrorMsg_NoData"));
	}
	
}
