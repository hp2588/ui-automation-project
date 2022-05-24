package com.org.tests.mainmenu.customlabels;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1058046 extends BaseTest {
	
	String labelName,stockName,firstData, parsed_stockName_array[],parsed_stockName;
	List<String> actualData, expectedData;
	
	@Test(priority = 1, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Edit link is present at the home screen of Custom label for each labels")
	public void Test01_1092600(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Edit link is present at the home screen of Custom label for each labels");
		test.landingPageActions.navigateToFeature("Custom Labels");
		test.supportDataActions.verifyLabelIsPresent("Custom Labels");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Custom Label");
		labelName = test.siteConfigurationAction.enterRandomValueInInputField("labelName",
				"Name" + System.currentTimeMillis());
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("facility", 1);
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		 stockName=test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("stock", 1);
		 parsed_stockName_array=stockName.split(" ");
		 parsed_stockName=parsed_stockName_array[0];
		
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditAdhocLabel"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(labelName);
		//Assert.assertTrue(test.supportDataActions.verifyEditLinkUnderActionColumnForAdhocLabel("Edit"));
		
	}
	
	@Test(priority = 2, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:When clicking Edit link, Edit Custom Label page is getting opened and user unable to edit Facility Name.")
	public void Test02_1092603(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:When clicking Edit link, Edit Custom Label page is getting opened and user unable to edit Facility Name.");
		//test.supportDataActions.clickButton("edit");
		//test.supportDataActions.verifyLabelIsPresent("Edit Ad-Hoc Labels");
		test.supportDataActions.clickOnEditLinkCorresspondingToCustomLabel(labelName, labelName);
	}
	
	@Test(priority = 3, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Cancel,save,Edit Design,Print Label and Toggle button for Inactive/Active is present on the home page od Edit Custom label")
	public void Test03_1092605(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:UI:Verify cancel,save,Edit Design,Print Label and Toggle button for Inactive/Active button present on the home page od Edit Adhoc label");
		test.siteConfigurationAction.verifyLinkText("cancel");
		test.siteConfigurationAction.verifyLinkText("save");
		test.siteConfigurationAction.verifyLinkText("designLabel");
		test.siteConfigurationAction.verifyLinkText("printLabel");
		test.siteConfigurationAction.verifyToggleIsActive("isActive");
	}
	
	@Test(priority = 4, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:User is able to edit name for Custom label")
	public void Test04_1092609(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:User is able to edit name for Custom label");
	    
		labelName=test.siteConfigurationAction.enterRandomValueInInputField("labelName", "Name"+System.currentTimeMillis());
		
	}
	
	@Test(priority = 5, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:User is able to edit stock for Custom label")
	public void Test05_1092610(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:UI:Verify user can edit stock for Adhoc label whhic is required field");
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("stock", 1);
	}
	
	@Test(priority = 6, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:User is able to edit Custom label and make any labels active/inactive via toggle button.")
	public void Test06_1092620(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:User is able to edit Custom label and make any labels active/inactive via toggle button.");
	test.siteConfigurationAction.verifyToggleIsActive("isActive");
	test.siteConfigurationAction.clickActiveToggle("Active");
	test.siteConfigurationAction.verifyToggleIsInActive("isActive");
	test.siteConfigurationAction.clickActiveToggle("Active");
	
	
	}
	
	@Test(priority = 7, description = "VPLX:Ad-Hoc Label Design and Printing:UI:Verify label updated with a message 'Label name  updated and saved successfully'.")
	public void Test07_1092618(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:UI:Verify label updated with a message 'Label name  updated and saved successfully'.");
	test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditAdhocLabel"));
	test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(labelName);
	
	}
}
