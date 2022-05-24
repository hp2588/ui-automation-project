package com.org.tests.mainmenu.manufacturer;

import static com.org.automation.utils.YamlReader.getData;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;

public class Story_997979 extends BaseTest{

	ArrayList<String> previous_data, sorted_data;
	String[] columnHeaders = { "Name", "Status" };
	
	String manufacturerName;
	String manufacturerName_old;
	
	
	@Test(priority = 1, description = "VPLX: Manufacturer  : [UI] Verify Listing of manufacturer is available under the  manufacturer option under the support data .")
	public void Test01_1071001_1129205() {
		
		//test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Manufacturers");
		test.supportDataActions.verifyLabelIsPresent("Manufacturers");
		test.supportDataActions.verifyManufacturerStatusAsActive();
		
		
	}
	
	@Test(priority = 2, description = "VPLX: Manufacturer  : [UI] Verify Listing of manufacturer is Sorted on the basis of Description  field .")
	public void Test02_1071002() {
				
      	previous_data = test.supportDataActions.captureDataForParticularColumn(getData("ManufacturerColumnNumber.Name")); 
      	sorted_data = test.supportDataActions.sortDataForParticularColumnInAscendingOrder(getData("ManufacturerColumnNumber.Name")); 
      	Assert.assertEquals(previous_data,sorted_data,"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
		
      	Assert.assertTrue(test.supportDataActions.verifyManufacturerStatusAsActive());
	
	}
	
	@Test(priority = 3, description = "VPLX: Manufacturer  : [UI] Verify by default only the Active manufacturers are visible .")
	public void Test03_1071004() {
		
		test.supportDataActions.verifyManufacturerStatusAsActive();
		
	}
	
	@Test(priority = 4, description = "VPLX: Manufacturer  : [UI] Verify user can see all the manufacturers if show Active toggle is on .")
	public void Test04_1071006() {
		
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyManufacturerStatus());

		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("ManufacturerColumnNumber.Name"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInAscendingOrder(getData("ManufacturerColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in ascending order");
	}
	
	@Test(priority = 5, description = "VPLX: Manufacturer  : [UI] Verify user can sort in descending order of the Manufacturer  description")
	public void Test05_1071014() {
		test.supportDataActions.verifyAndClickSortIconWasteReasonName(getData("ManufacturerColumnName.Name"));
		previous_data = test.supportDataActions.captureDataForParticularColumn(getData("ManufacturerColumnNumber.Name"));
		sorted_data = test.supportDataActions
				.sortDataForParticularColumnInDescendingOrder(getData("ManufacturerColumnNumber.Name"));
		Assert.assertEquals(previous_data, sorted_data,
				"[ASSERTION FAILED] : Name column data is not sorted in descending order");
		Assert.assertTrue(test.supportDataActions.verifyManufacturerStatus());


	}
	
	@Test(priority = 6, description = "VPLX: Manufacturer  : [UI] Verify \"No data available \" message will get displayed if manufacturer list is empty .")
	public void Test06_1071021() {
		
		
		try {
		Assert.assertEquals(test.supportDataActions.getNoDataText(),"No data available");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
