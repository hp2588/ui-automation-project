package com.org.tests.mainmenu.customlabels;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1061338 extends BaseTest {

	String labelName,stockName,parsed_stockName_array[],parsed_stockName;
	
	@Test(priority = 1, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Enabled duplicate link present on Custom Label Page")
	public void Test01_1100847(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Enabled duplicate link present on Custom Label Page");
	
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
		
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditAdhocLabel"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(labelName);
		Assert.assertTrue(test.supportDataActions.verifyEditLinkUnderActionColumnForAdhocLabel("Duplicate"));
	}
	
	@Test(priority = 2, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Duplicate Custom label Pop up is getting open using duplicate link present on Custom Label Page")
	public void Test02_1100852(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Duplicate Custom label Pop up is getting open using duplicate link present on Custom Label Page");
		test.supportDataActions.clickOnDuplicateLinkCorresspondingToAddedRecord(labelName, "Duplicate");
		//test.supportDataActions.clickButton("Duplicate");
		//test.supportDataActions.verifyLabelIsPresent("Duplicate Label");
		test.supportDataActions.verifyCustomlabelModelPopUpwindow("Duplicate Label");
		
	}
	
	@Test(priority = 3, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Name as text box as mandatory field and stock as drop down as mandatory field is displayed on Custom label pop up")
	public void Test03_1100861(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Name as text box as mandatory field and stock as drop down as mandatory field is displayed on Custom label pop up");
	
		Assert.assertTrue(test.siteConfigurationAction.verifyMandatoryField("Name"),
				"[ASSERTION FAILED]: Name is not mandatory field.");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyMandatoryField("Stock"),
				"[ASSERTION FAILED]: Stock is not mandatory field.");
		
	}
	
	@Test(priority = 4, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Pre-filled name as label name -copy and pre-filled stock displayed on Duplicate Custom label pop after clicking Duplicate Link")
	public void Test04_1100864(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Pre-filled name as label name -copy and pre-filled stock displayed on Duplicate Custom label pop after clicking Duplicate Link");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldContainsCopy("labelName"),
				"[ASSERTION FAILED]: Label field does not contains Copy.");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewFacility("stock", parsed_stockName);
		
	}
	
	@Test(priority = 5, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Duplicate label for Custom-label  successfully added in listing section of Custom label")
	public void Test05_1101801(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Duplicate label for Custom-label  successfully added in listing section of Custom label");
		
		labelName=test.siteConfigurationAction.enterRandomValueInInputField("labelName", "Name"+System.currentTimeMillis());
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditAdhocLabel"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(labelName);
		
	}
}
