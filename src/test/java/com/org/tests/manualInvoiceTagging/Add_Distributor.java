package com.org.tests.manualInvoiceTagging;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Add_Distributor extends BaseTest {
	
	String dataEnteredName, dataEnteredCode, new_data;
	
	@Test(priority = 1, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new distributor")
	public void Test01_Add_Distributor_Test(Method method) {
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText", 
				"dis" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode", 
				"UI" + System.currentTimeMillis()); 
		
		//test.siteConfigurationAction.selectCheckboxCorresspondingToField("internalFlag", true);
        //test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
        test.supportDataActions.clickAddButtonOnDistributor("save");
        
        TestDataPropertyReaderAndWriter.setProperty("DistributorName", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode", dataEnteredCode);
		
        Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredName);
	}
	
	
	@Test(priority = 2, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new electronic distributor")
	public void Test02_Add_Electronic_Distributor_Test(Method method) {
		test.siteConfigurationAction.pageRefresh();
		test.landingPageActions.navigateToMenu("Main Menu");
		
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		test.purchaseDashboardActions.selectRadioButtonElectronicDistributor("electronicDistributorFlag");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText", 
				"DisElecON"+System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode", 
				"UI"+System.currentTimeMillis());
        test.supportDataActions.clickAddButtonOnDistributor("save");
        
        TestDataPropertyReaderAndWriter.setProperty("DistributorName_Electronic", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode_Electronic", dataEnteredCode);
		
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredName);	
	}
	
	
	@Test(priority = 3, description = "VPLX:Manage Distributors:[UI]:Map distributors to facility")
	public void Test03_Map_Distributors_To_Facility(Method method) {
		test.siteConfigurationAction.pageRefresh();
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("false"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show Inactive", true);
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("true"));
		
		//Manual Distributor 
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName"), RandomStringUtils.random(6, false, true)));
		
		//Electronic Distributor
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName_Electronic"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName_Electronic"), RandomStringUtils.random(6, false, true)));
		
		test.siteConfigurationAction.clickSaveButton();
	}

}
