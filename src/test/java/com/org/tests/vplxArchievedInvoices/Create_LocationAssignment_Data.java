package com.org.tests.vplxArchievedInvoices;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Create_LocationAssignment_Data extends BaseTest {

	String itemID;
	String ISAName, facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
			ipAddress, portNumber, carouselConnectionResetTime;
	ArrayList<String> previous_data, sorted_data;

	@Test(priority = 1, description = "VPLX:Location-Storage Area: UI:Verify when click on assign button user land on assign location pop-up")
	public void Test01_1060153(Method method) throws InterruptedException {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.supportDataActions.resetSearch();
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1"),
				"search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName1"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		Thread.sleep(5000);
	}
	
	
	
}
