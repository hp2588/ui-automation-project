package com.org.tests.locationmanagement;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_981973 extends BaseTest{
	
	ArrayList<String> previous_data, sorted_data;
	
	@Test(priority = 1, description = "VPLX:Location-Storage Area: UI:Verify my facility dropdown list and for search text box is present with filter functionality")
	public void Test01_1057894(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify my facility dropdown list and for search text box is present with filter functionality");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.siteConfigurationAction.verifyButtonOnEditLocation("facility_dropdown_edit_location");
		test.siteConfigurationAction.verifyButtonOnEditLocation("search_location");
		test.siteConfigurationAction.verifyButtonOnEditLocation("filter_location_btn");
	}
	/*
	@Test(priority = 6, description = "VPLX:Location-Storage Area: UI:Verify  user should search on the basis of Description, Brand Name , Facility Name,Item Id")
	public void Test02_1057896(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify  user should search on the basis of Description, Brand Name , Facility Name,Item Id");
		test.supportDataActions.enterSearchTermInSearchFieldGl(getData("ExternalSystem.itemId1"),"search");
		test.siteConfigurationAction.verifySearchResults(getData("ExternalSystem.itemId1"), "1");
	}
	*/
	@Test(priority = 3, description = "VPLX:Location-Storage Area: UI:Verify filter icon and edit link should be present on the location management section")
	public void Test03_1057977(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify filter icon and edit link should be present on the location management section");
		test.siteConfigurationAction.verifyButtonOnEditLocation("filter_location_btn");
		test.siteConfigurationAction.verifyEditLinkAgainstSystemLabels("Edit");
		
		
		
	}
	
	@Test(priority = 4, description = "VPLX:Location-Storage Area: UI:Verify page number on location management section should be present")
	public void Test04_1058054(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify page number on location management section should be present");
		test.siteConfigurationAction.verifyPageNumberOnLocationManagement();
		
	}
	

}
