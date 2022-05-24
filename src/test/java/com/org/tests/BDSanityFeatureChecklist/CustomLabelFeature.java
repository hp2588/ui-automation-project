package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class CustomLabelFeature extends BaseTest {
	
String labelName;
	
	@Test(priority = 1, description = "VPLX:Ad-Hoc Label Design and Printing:UI:Verify on clicking add button add  add-adhoc labels pop-up opened")
	public void Test01_1085982(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:UI:Verify on clicking add button add  add-adhoc labels pop-up opened");
		
		test.landingPageActions.navigateToFeature("Custom Labels");
		test.supportDataActions.verifyLabelIsPresent("Custom Labels");
        test.siteConfigurationAction.verifyButtonIsDisabled("add");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Custom Label");
	}
	
	@Test(priority = 2, description = "VPLX:Ad-Hoc Label Design and Printing:UI:Verify Name and stock as mandatory field for add ad-hoc labels pop-up")
	public void Test02_1085984(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:UI:Verify Name and stock as mandatory field for add ad-hoc labels pop-up");
		Assert.assertTrue(test.siteConfigurationAction.verifyMandatoryField("Name"),
				"[ASSERTION FAILED]: Name is not mandatory field.");

		Assert.assertTrue(test.siteConfigurationAction.verifyMandatoryField("Stock"),
				"[ASSERTION FAILED]: Stock is not mandatory field.");
	}
	
	@Test(priority = 3, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:On adding a new Ad-Hoc label , when the user click on the Design button this show a success message that 'Your data has been saved successfully'")
	public void Test03_1086004(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:On adding a new Ad-Hoc label , when the user click on the Design button this show a success message that 'Your data has been saved successfully'");
		
		labelName = test.siteConfigurationAction.enterRandomValueInInputField("labelName",
				"Name" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		//test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("stock", 1);
		Assert.assertFalse(test.siteConfigurationAction.verifyButtonIsDisabled("save"));
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditAdhocLabel"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(labelName);
		
	}
	
	@Test(priority = 4, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:User is successfully print the label for adhoc label from given printer")
	 public void Test04_1101814(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:User is successfully print the label for adhoc label from given printer");
		//test.supportDataActions.clickButton("edit");
		test.supportDataActions.clickOnEditLinkCorresspondingToCustomLabel(labelName, labelName);
		//test.supportDataActions.verifyLabelIsPresent("Edit Ad-Hoc Labels");
		test.siteConfigurationAction.clickButton("printLabel");
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("SelectPrinter", 1);
		test.supportDataActions.clickButtonWithOutAnyWait("print");
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader("This record has been sent for printing.");
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");
		test.supportDataActions.verifyLabelIsPresent("Custom Labels");
	}
	
	@Test(priority = 5, description = "VPLX:Ad-Hoc Label Design and Printing:UI:Verify on clicking Cancel button it close the current popup and again land the user into the listing page.")
	public void Test05_1086038(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:UI:Verify on clicking Cancel button it close the current popup and again land the user into the listing page.");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Custom Label");
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");		
		test.supportDataActions.verifyLabelIsPresent("Custom Labels");
		
	}
	
	@Test(priority = 6, description = "VPLX:Ad-Hoc Label Design and Printing:UI:Verify enabled active/inactive toggle button present on the ad-hoc label screen")
	public void Test06_1092693(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:UI:Verify enabled active/inactive toggle button present on the ad-hoc label screen");
		
		test.siteConfigurationAction.verifyToggleIsInActive("toggle");
		
	}
	
	@Test(priority = 7, description = "VPLX:Ad-Hoc Label Design and Printing:UI:Verify when toggle button is inactive only active labels displayed as list on the home screen of ad-hoc label.")
	public void Test07_1092699(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:UI:Verify when toggle button is inactive only active labels displayed as list on the home screen of ad-hoc label.");
	
		test.siteConfigurationAction.verifySearchResults("Active", "4");
	
	}
	
	@Test(priority = 8, description = "VPLX:Ad-Hoc Label Design and Printing:UI:Verify when toggle button is active all active and inactive labels displayed as list on the home screen of ad-hoc label.")
	public void Test08_1092696(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:UI:Verify when toggle button is active all active and inactive labels displayed as list on the home screen of ad-hoc label.");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Custom Label");
		labelName = test.siteConfigurationAction.enterRandomValueInInputField("labelName",
				"Name" + System.currentTimeMillis());
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("stock", 1);
		Assert.assertFalse(test.siteConfigurationAction.verifyButtonIsDisabled("save"));
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditAdhocLabel"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(labelName);
		
		//test.supportDataActions.clickButton("edit");
		//test.supportDataActions.verifyLabelIsPresent("Edit Ad-Hoc Labels");
		test.supportDataActions.clickOnEditLinkCorresspondingToCustomLabel(labelName, labelName);
		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.verifyToggleIsInActive("isActive");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditAdhocLabel"));
		
		test.supportDataActions.verifyLabelIsPresent("Custom Labels");
		
		test.siteConfigurationAction.clickActiveToggle("Show Inactive");
		test.siteConfigurationAction.verifyToggleIsActive("toggle");
		
		test.siteConfigurationAction.verifyActiveAndInactiveResults("Inactive", "Active", "4");
		
	}
}

