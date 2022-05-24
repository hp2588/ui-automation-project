package com.org.tests.siteconfiguration;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;


public class Story_992148 extends BaseTest{
	
	
	@Test(priority = 1, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test01_1060086(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up");
		test.landingPageActions.navigateToFeature("Item Locations per ISA");
		test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItem("REOP2INJ");
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		
		}
	
	@Test(priority = 2, description = "VPLX:Location-Storage Area: UI:Verify item id and External  displayed on the top")
	public void Test02_1059552(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify item id and External  displayed on the top");
	//	test.siteConfigurationAction.verifyItemDetailsEditLocation("REOP2INJ", "My Facility");
		Assert.assertFalse(test.siteConfigurationAction.verifyAssignedLocation());

		
		}
	/*
	@Test(priority = 3, description = "VPLX:Location-Storage Area: UI:Verify Assign location popup is displayed after clicking on Assign button")
	public void Test03_1059555(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify Assign location popup is displayed after clicking on Assign button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.verifyAssignLocationPopup(getData("EditItemLocation.Header"));
		
		}
	
	@Test(priority = 4, description = "VPLX:Location-Storage Area: UI:Verify facility and ISA displayed as drop down in assign location pop-up")
	public void Test04_1059560(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify facility and ISA displayed as drop down in assign location pop-up");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewFacility("facility");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewFacility("isa");
		
		}
	

	@Test(priority = 5, description = "VPLX:Location-Storage Area: UI:Verify cancel and save button displayed on Assign location pop-up")
	public void Test05_1059563(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify cancel and save button displayed on Assign location pop-up");
		test.siteConfigurationAction.verifyButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.verifyButtonOnEditLocation("cancel_button_edit_location");

		
		}
	
	@Test(priority = 7, description = "VPLX:Location-Storage Area: UI:Verify user lands on screen with ISA configuration after clicking on Save button on Assign location popup")
	public void Test07_1059574(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify user lands on screen with ISA configuration after clicking on Save button on Assign location popup");
		//test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", getData("EditItemLocation.FacilityName"));
		test.siteConfigurationAction.selectValueForDropDown("isa", getData("EditItemLocation.ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.verifyUserOnEditLocationAssignmentPage();
		
		
		}  
	
	/*
	@Test(priority = 6, description = "VPLX:Location-Storage Area: UI:Verify when click on cancel user navigated on the back screen page")
	public void Test06_1060098(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify when click on cancel user navigated on the back screen page");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", getData("EditItemLocation.FacilityName"));
		test.siteConfigurationAction.selectValueForDropDown("isa", getData("EditItemLocation.ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickButtonOnEditLocation("btn_cancel");
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");


	}*/
	
	/*@Test(priority = 8, description = "VPLX:Location-Storage Area: UI:Verify when click on save ,location of the item saved by default as mentioned in ISA")
	public void Test08_1060153(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area: UI:Verify when click on save ,location of the item saved by default as mentioned in ISA");
		//test.siteConfigurationAction.clickSlotAssignLocation();
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "1");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "1000");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "1000");
		test.siteConfigurationAction.clickSaveButton();
		
		
		//test.siteConfigurationAction.clickButtonOnEditLocation("btn_cancel");
		//test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		
		}
	
	@Test(priority = 9, description = "VPLX:Location-Storage Area-Edit Location: UI:Verify System displays list of assigned location(s) for each Facility")
	public void Test09_1071039(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location-Storage Area-Edit Location: UI:Verify System displays list of assigned location(s) for each Facility");
		Assert.assertTrue(test.siteConfigurationAction.verifyAssignedLocation());
		
		}*/
	
}
