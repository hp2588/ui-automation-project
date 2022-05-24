package com.org.tests.mainmenu.customlabels;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1058100 extends BaseTest{
	String systemLabelName;
	String labelName,stockName,firstData, parsed_stockName_array[],parsed_stockName;
	
	@Test(priority = 2, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Enabled print label button is displayed on the edit adhoc label page..")
	public void Test02_1101805(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Enabled print label button is displayed on the edit adhoc label page.");
		test.landingPageActions.navigateToFeature("Custom Labels");
		test.supportDataActions.verifyLabelIsPresent("Custom Labels");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Custom Label");
		labelName = test.siteConfigurationAction.enterRandomValueInInputField("labelName",
				"Name" + System.currentTimeMillis());
		// test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectValueForDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		stockName=test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("stock", 1);
		parsed_stockName_array = stockName.split(" ");
		parsed_stockName = parsed_stockName_array[0];
		
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditAdhocLabel"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(labelName);
		test.supportDataActions.clickOnEditLinkCorresspondingToCustomLabel(labelName, labelName);
		test.siteConfigurationAction.verifyButtonIsEnabled("Print Test Label");
		test.siteConfigurationAction.clickButton("printLabel");		

		
	}
	
	@Test(priority = 3, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Print test label pop up opened on clicking Print label button.")
	public void Test03_1101806(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Print test label pop up opened on clicking Print label button.");

		test.siteConfigurationAction.verifyPopupMessageSystemLabelPage("Print Test Label");	
	}
	

	@Test(priority = 4, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Enabled cancel and print button is getting displayed on print test label pop up.")
	public void Test04_1101836(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Print test label pop up opened on clicking Print label button.");

		test.supportDataActions.verifyButtons("printcancel");
		test.supportDataActions.verifyButtons("print");

	}
	
	@Test(priority = 5, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Print button is getting enabled only after selecting printer from drop down.")
	public void Test05_1101837(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Print button is getting enabled only after selecting printer from drop down.");

		test.siteConfigurationAction.selectValueFromDropDownByIndex("SelectPrinter", 1);
		test.siteConfigurationAction.verifyButtonIsEnabled("Print Test Label");
		
	}

}
