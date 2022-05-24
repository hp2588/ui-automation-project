package com.org.tests.astarratedbugs;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class CreateStandardLabelTest extends BaseTest {
	String systemLabelName, systemLabelName1;

	@Test(priority = 1, description = "VPLX:Configure Labels- General:UI:Verify by default on saving system label be in Active state")
	public void Test01_AddStandardLabel(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify by default on saving system label be in Active state");
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add Standard Label");
		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName",
				getData("SystemLabel.LabelName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.selectValueFromDropDown("category","Destination Bin Label");
		test.siteConfigurationAction.enterDataInPriority();
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);	
	}
	
	@Test(priority = 2, description = "VPLX:Configure Labels- General:UI:Verify by default on saving system label be in Active state")
	public void Test02_AddStandardLabel(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:UI:Verify by default on saving system label be in Active state");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add Standard Label");
		systemLabelName1 = test.siteConfigurationAction.enterDataInInputField("labelName",
				getData("SystemLabel.LabelName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.selectValueFromDropDown("category","Destination Bin Label");
		test.siteConfigurationAction.enterDataInPriority();
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName1);	
	}

}
