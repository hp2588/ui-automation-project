package com.org.tests.locationmanagement;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_992148 extends BaseTest {
	
	String itemID;
	String itemName=TestDataPropertyReaderAndWriter.getProperty("ItemName").trim(),facility=TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),isaName=TestDataPropertyReaderAndWriter.getProperty("ISAName").trim();

	@Test(priority = 1, description = "VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System displays all the fields for each PLX location")
	public void Test01_1071045(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: System displays all the fields for each PLX location");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",facility);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName,"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		test.siteConfigurationAction.verifyFieldsOnEditLocation("Location");
		test.siteConfigurationAction.verifyFieldsOnEditLocation("Type");
		test.siteConfigurationAction.verifyFieldsOnEditLocation("Rule");
		test.siteConfigurationAction.verifyFieldsOnEditLocation("Replenish");
		test.siteConfigurationAction.verifyFieldsOnEditLocation("Min");

		test.siteConfigurationAction.verifyFieldsOnEditLocation("On Hand");

		}
	
	@Test(priority = 1, description = "VPLX:Location Assignment - Managing Storage Area-Edit Location: UI:Quantity On Hand, Cycle Count, Min and Max values default to the values that were previously entered during the assignation on edit location screen")
	public void Test01_1071048(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area-Edit Location: UI:Quantity On Hand, Cycle Count, Min and Max values default to the values that were previously entered during the assignation on edit location screen");
		
		test.siteConfigurationAction.verifyValueEditLocation("refillPointQuantity_0", "10");
		test.siteConfigurationAction.verifyValueEditLocation("parQuantity_0", "100");
		test.siteConfigurationAction.verifyValueEditLocation("inventoryQuantity_0", "9900");
	

		}
	
	

	
	@Test(priority = 2, description = "VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: User lands on map screen when user clicks on edit button on edit location screen")
	public void Test01_1071135(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Location Assignment - Managing Storage Area-Edit Location: UI: User lands on map screen when user clicks on edit button on edit location screen");

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToAssignedItem("CAR1-1-1-1-1");
		        test.siteConfigurationAction.verifyUserIsOnLayoutPage();


		}
	
	
}
