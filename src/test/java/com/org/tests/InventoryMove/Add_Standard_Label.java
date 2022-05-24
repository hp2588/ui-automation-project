package com.org.tests.InventoryMove;


import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Add_Standard_Label extends BaseTest {

String systemLabelName;

	
	@Test(priority = 3, description = "VPLX:Configure Labels:UI:Verify user have option to edit active/inactive for labels on edit system screen via toggle button")
	public void Test03_1048537(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels:UI:Verify user have option to edit active/inactive for labels on edit system screen via toggle button");
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add a new Standard Label");
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDown("facility",TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");

		test.siteConfigurationAction.selectPriority("STAT Order", true);
		test.siteConfigurationAction.selectPriority("Inventory Move", true);
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);

}
}

