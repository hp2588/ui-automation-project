package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Add_custom_Label_Test extends BaseTest {
	
	String labelName;
	
	@Test(priority = 1, description = "VPLX : Custom Labels : User can create a custom label")
	public void Test01_1114325(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Custom Labels : User can create a custom label");
		
		test.landingPageActions.navigateToFeature("Custom Labels");
		test.supportDataActions.verifyLabelIsPresent("Ad-Hoc Labels");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Custom Label");
		//test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Ad-Hoc Label");
		labelName = test.siteConfigurationAction.enterRandomValueInInputField("labelName",
				"Name" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditAdhocLabel"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(labelName);
		
	}
}
