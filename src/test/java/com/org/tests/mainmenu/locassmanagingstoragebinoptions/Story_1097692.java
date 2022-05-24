package com.org.tests.mainmenu.locassmanagingstoragebinoptions;


import java.util.Random;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1097692 extends BaseTest{
	
	Random rand = new Random();
	String name,name2,labelName;
	@Test(priority = 1,enabled = true, description = "VPLX:Configure Labels- General:[UI]:Assign priority drop down list "
			+ "is not getting displayed while selecting category as bin label for any new Standard Label.")
	public void Test01_1097692_1106911() { 			
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		Assert.assertTrue(test.siteConfigurationAction.clickAddButtonForLables());
		Assert.assertTrue(test.siteConfigurationAction.sendStandardLablesName("ABC4384"+ rand.nextInt()));
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		Assert.assertTrue(test.siteConfigurationAction.verifyAssignPrioritiesIsVisible());
		test.siteConfigurationAction.selectDropDownCategoriesValue("Bin Label");
		Assert.assertFalse(test.siteConfigurationAction.assignPropertiesNotVisible());	
		Assert.assertTrue(test.siteConfigurationAction.clickPrefferedPrintLableBox());
		
		test.siteConfigurationAction.clickSaveButton();
		
	}
	
	
	@Test(priority = 2,enabled = true, description = "VPLX:Configure Labels- General:[UI]:Preferred print Label check box "
			+ "is displayed while selecting category as bin label for any new System Label.")
	public void Test02_1097692_1108672() {				
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		Assert.assertTrue(test.siteConfigurationAction.clickAddButtonForLables());
		test.siteConfigurationAction.sendStandardLablesName("ABC4384"+ rand.nextInt());
		
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		Assert.assertTrue(test.siteConfigurationAction.verifyAssignPrioritiesIsVisible());
		test.siteConfigurationAction.selectDropDownCategoriesValue("Bin Label");
		Assert.assertTrue(test.siteConfigurationAction.verifyPrefferedPrintLablesBoxVisible());
		
	}
	
	
	@Test(priority = 3,enabled = true, description = "VPLX:Configure Labels- General:[UI]:Show confirmation dialog box pop up "
			+ "is getting open on clicking of checkbox,If another bin label is already set to be the preferred print label.")
	public void Test03_1097692_1108677() {				
		
		Assert.assertTrue(test.siteConfigurationAction.clickPrefferedPrintLableBox());
		Assert.assertTrue(test.siteConfigurationAction.verifyPopUpForAlreadySetBinLabel());
		
	}
	
	
	@Test(priority = 4,enabled = true, description = "VPLX:Configure Labels- General:[UI]:Show confirmation dialog "
			+ "contains message Bin label 1 is already set as the preferred print label. Are you sure you want to set this new label as the preferred? with yes or no button.")
	public void Test04_1097692_1108680() {				
		
		Assert.assertTrue(test.siteConfigurationAction.clickPrefferedPrintLableBox());
		Assert.assertTrue(test.siteConfigurationAction.verifyPopUpForAlreadySetBinLabel());
		
	}
	
	
	@Test(priority = 5,enabled = true, description = "VPLX:Configure Labels- General:[UI]:On selecting 'No' on confirm dialog box "
			+ "hide the confirm dialog box and show the preferred print label check box as unselected.")
	public void Test05_1097692_1108711() {				
		
		Assert.assertTrue(test.siteConfigurationAction.clickPrefferedPrintLableBox());
		Assert.assertTrue(test.siteConfigurationAction.verifyPopUpForAlreadySetBinLabel());
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckBoxIsSelected());
		
	}
	
	
	@Test(priority = 6,enabled = true, description = "VPLX:Configure Labels- General:[UI]:On selecting yes on confirm dialog box "
			+ "hide the confirm dialog box and show the preferred print label check box as selected.")
	public void Test06_1097692_1108710() {	
		
		Assert.assertTrue(test.siteConfigurationAction.clickPrefferedPrintLableBox());
		Assert.assertTrue(test.siteConfigurationAction.ClickYesOnPopUpForSetBinLabel());
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckBoxIsSelected());
		Assert.assertTrue(test.siteConfigurationAction.clickcancelButtonOnEditLables());
		Assert.assertTrue(test.siteConfigurationAction.clickYesCancelButtonOnEditLables());
		
	}
	
	
	@Test(priority = 7,enabled = true, description = "VPLX:Configure Labels- General:UI:Verify Assign priority drop down list "
			+ "not displayed while selecting category as bin label for any new System Label.")
	public void Test07_1097692_1112400() { 	
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		Assert.assertTrue(test.siteConfigurationAction.clickAddButtonForLables());
	    labelName=test.siteConfigurationAction.enterDataInInputField("labelName", ("ABC4384"+ rand.nextInt()));
	     
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		Assert.assertTrue(test.siteConfigurationAction.verifyAssignPrioritiesIsVisible());
		test.siteConfigurationAction.selectDropDownCategoriesValue("Bin Label");
		Assert.assertFalse(test.siteConfigurationAction.assignPropertiesNotVisible());	
		
		test.siteConfigurationAction.clickSaveButton();

		/*
		Assert.assertTrue(test.siteConfigurationAction.clickcancelButtonOnEditLables());
		Assert.assertTrue(test.siteConfigurationAction.clickYesCancelButtonOnEditLables());
		*/
	}
	
	
	@Test(priority = 8,enabled = true, description = "VPLX:Configure Labels- General:[UI]:Preffered Print Label Checkbox "
			+ "is displayed on clicking Edit link from the list of System label having category as Bin label on Edit page of System label")
	public void Test08_1097692_1108715() { 	
		test.landingPageActions.navigateToMenu("Main Menu");
		
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(labelName);
		
		Assert.assertTrue(test.siteConfigurationAction.clickPrefferedPrintLableBox());
		
		test.siteConfigurationAction.backToPreviousWindow();	
		
	}
	
	
	/*	
 	// Combit related
	@Test(priority = 9,enabled = true, description = "VPLX:Location-Assignment-Managing Storage Area(Bin Action):[UI]:Bin location Printed,When designed  Standard Label(Active) in combit with placeholder which is mapped, with Bin Label Category.")
	public void Test07_1097692_1131279() 
	{ 	Assert.assertTrue(test.siteConfigurationAction.clickEditStandardLables());
		Assert.assertTrue(test.siteConfigurationAction.verifyCategory());
	} 
	*/
	
}				
