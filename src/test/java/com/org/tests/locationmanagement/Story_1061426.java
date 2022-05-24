package com.org.tests.locationmanagement;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1061426 extends BaseTest{
	
	String itemID;
	String itemName=TestDataPropertyReaderAndWriter.getProperty("ItemName").trim(),facility=TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),isaName=TestDataPropertyReaderAndWriter.getProperty("ISAName").trim();

	@Test(priority = 1, description = "VPLX:Location Assignment-Managing storage area:[UI]: User selects shelf 1 and corresponding bins will be displayed on the right hand side."
)
	public void Test01_1124286(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment-Managing storage area:[UI]: User selects shelf 1 and corresponding bins will be displayed on the right hand side.");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",facility);		
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", facility);
		test.siteConfigurationAction.selectValueForDropDown("isa", isaName);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.verifyUserIsOnLayoutPage();
	//	test.siteConfigurationAction.hoverOverShelf("Shelf 1");
		test.siteConfigurationAction.verifyBins();
		
		}
	
	@Test(priority = 2, description ="VPLX:Location Assignment-Managing Storage Area: [UI]: User selects shelf 2 and corresponding bins will be displayed on the right hand side."
)
	public void Test02_1124287(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment-Managing Storage Area: [UI]: User selects shelf 2 and corresponding bins will be displayed on the right hand side.");
		//test.siteConfigurationAction.clickShelf();
		test.siteConfigurationAction.clickDotsOnItemLocation("rack_actions");
		test.siteConfigurationAction.clickAddRacksOrShelfOrBin("Add Shelf");
		test.siteConfigurationAction.hoverOverShelf("Shelf 2");
		test.siteConfigurationAction.verifyBins();
		
		}

	@Test(priority = 3, description = "VPLX:Location Assignment-Managing Storage Area:[UI]: User selects the shelf and three dots are visible")
	public void Test03_1124288(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment-Managing Storage Area:[UI]: User selects the shelf and three dots are visible");
     	test.siteConfigurationAction.clickShelf();
     	test.siteConfigurationAction.verifyShelfDot();
		//test.siteConfigurationAction.hoverOverShelf("Shelf 2");
		//test.siteConfigurationAction.verifyDots("shelf_actions");
		
		}
	
	@Test(priority = 4, description = "VPLX:Location Assignment-Managing Storage Area: [UI]: User clicks on three dots and is able to view the option Apply left offset to other shelves"
)
	public void Test04_1124289(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment-Managing Storage Area: [UI]: User clicks on three dots and is able to view the option Apply left offset to other shelves");
	
		test.siteConfigurationAction.clickShelfDot();
	   // test.siteConfigurationAction.hoverOverShelf("Shelf 2");
		//test.siteConfigurationAction.clickDotsOnItemLocation("shelf_actions");
		test.siteConfigurationAction.performActionOnShelf("Apply Left Offset to Other Shelves");

		}
	
	@Test(priority = 5, description = "VPLX:Location Assignment-Managing Storage Area: [UI]: User is allowed to apply the left offset of the selected shelf  to all shelves within the ISA"
)
	public void Test05_1124290(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment-Managing Storage Area: [UI]: User is allowed to apply the left offset of the selected shelf  to all shelves within the ISA");
	
		test.siteConfigurationAction.selectCheckboxForShowItems("allCheckbox2",true);
		test.siteConfigurationAction.clickSaveButtonForOffset();
		

		}
}

