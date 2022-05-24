package com.org.tests.standardlabels.systemlabels.configurelabels.general;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1018211 extends BaseTest{
	String systemLabelName;
	String labelName,stockName,firstData, parsed_stockName_array[],parsed_stockName;
	
	
	
	
	/*
	 * @Test(priority = 2, description =
	 * "VPLX:Configure Labels- General:[UI]:On clicking import label button page directed to downloaded section of system"
	 * ) public void Test02_1071162(Method method) {
	 * ExtentTestManager.startTest(method.getName(),
	 * "VPLX:Configure Labels- General:[UI]:On clicking import label button page directed to downloaded section of system"
	 * );
	 * 
	 * test.siteConfigurationAction.clickImportButton();
	 * Assert.assertTrue(test.siteConfigurationAction.verifyImportWindowPopup()); }
	 */
	
	@Test(priority = 1, description = "VPLX:Configure Labels- General:[UI]:Import label button is getting displayed on Add system label page.")
	public void Test01_1071142(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels- General:[UI]:Import label button is getting displayed on Add system label page.");
		
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add a new Standard Label");
		String systemLabelName_1 = test.siteConfigurationAction.enterDataInInputField("labelName",
				getData("SystemLabel.LabelName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.selectValueFromDropDown("category", "Auto Dispensing Cabinet");
		test.siteConfigurationAction.verifyImportButton();
		
	}
	 
	
	
}
