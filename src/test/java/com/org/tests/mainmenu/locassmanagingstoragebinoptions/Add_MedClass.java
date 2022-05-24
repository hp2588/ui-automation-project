package com.org.tests.mainmenu.locassmanagingstoragebinoptions;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Add_MedClass extends BaseTest {

String dataEnteredCode, dataEnteredDescription, dataEnteredSort, external_System;
	
	@Test(priority = 2, description = "VPLX:Medication Classes:UI:Verify User is able to add new medication class")
	public void Test02_MedicationClass_Test(Method method) {
		test.landingPageActions.navigateToFeature("Medication Classes");
		
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Medication Classes"));
		test.siteConfigurationAction.selectDropdownDispenseExternal(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"), 
				"medication");
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		
		test.supportDataActions.verifyAllDropdownElementsMedicatonClass("medicationClassOrderInternalCode");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassCode", "2");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode", 3);
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "3");
		test.storageAreaAction.clickSaveButton();
		
		TestDataPropertyReaderAndWriter.setProperty("MedClassCode", dataEnteredCode);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);
		
	}

}