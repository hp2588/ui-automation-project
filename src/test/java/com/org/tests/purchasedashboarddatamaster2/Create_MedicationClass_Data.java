package com.org.tests.purchasedashboarddatamaster2;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Create_MedicationClass_Data extends BaseTest {

String dataEnteredCode, dataEnteredDescription, dataEnteredSort, external_System;
	
	@Test(priority = 1, description = "VPLX:Medication Classes:UI:Verify User is able to add new medication class")
	public void Test01_MedicationClass_Test(Method method) {
		test.landingPageActions.navigateToFeature("Medication Classes");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Medication Classes"));
		test.siteConfigurationAction.selectDropdownDispenseExternal(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"), "medication");
		test.supportDataActions.verifyButtonOnPage("largeDropdown");
		test.supportDataActions.clickAddButtonOnDistributor("largeDropdown");
		test.supportDataActions.verifyAndClickContactTab("Add Medication Class");
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("medicationClassCode", "2");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("medicationClassOrderInternalCode",1);
		dataEnteredDescription = test.supportDataActions.enterValueOnDescriptionMedicationPage("medicationClassDescription",
				"CII Med Class");
		dataEnteredSort = test.supportDataActions.enterValueOnAddDistributorPage("medicationClassSortOrder", "10");
		test.siteConfigurationAction.clickPickRoutingRuleButton("save");
		//test.supportDataActions.verifyNewlyAddedRecordNameInList(dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassCode1", dataEnteredCode);
		TestDataPropertyReaderAndWriter.setProperty("MedClassDescription1", dataEnteredDescription);
	}

	
	
}
