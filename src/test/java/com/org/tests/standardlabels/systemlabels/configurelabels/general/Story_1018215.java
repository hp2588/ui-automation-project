package com.org.tests.standardlabels.systemlabels.configurelabels.general;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1018215 extends BaseTest {
	String systemLabelName, FacilityName;
	String addStandardLabelTitle = "Add Standard Label";

	@Test(priority = 1, description = "VPLX: Configure Labels - General: UI: Verify "
			+ "	enabled Print Label button displayed on Edit label name page")
	public void Test01_1076732(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Configure Labels - General: UI: Verify enabled Print Label button displayed on Edit label name page");
		
		test.landingPageActions.navigateToFeature("Printers");
		FacilityName = test.supportDataActions.getColumnFirstData("2");
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel(addStandardLabelTitle);
		systemLabelName = test.siteConfigurationAction.enterDataInInputField("labelName",
				getData("SystemLabel.LabelName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDown("facility", FacilityName);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("category", 1);
		test.siteConfigurationAction.clickSaveButton();
		
		//test.siteConfigurationAction.verifySuccessMessageOnSystemLabel(getData("SystemLabel.SuccessMessage"));
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.clickRecordNameToEdit(systemLabelName);
		test.supportDataActions.verifyButtons("printLabel");
		
	}
	
	@Test(priority = 2, description = "VPLX:Configure Labels - General:UI: Verify Print Test Label pop-up open on clicking print Label button")
	public void Test02_1076735(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Configure Labels - General:UI: Verify Print Test Label pop-up open on clicking print Label button");
		test.siteConfigurationAction.clickButton("printLabel");
		test.siteConfigurationAction.verifyPopupMessageSystemLabelPage("Print Test Label");
		
	}

	@Test(priority = 3, description = "VPLX: Configure Labels - General: UI: Verify select printer come as dropdown in Print Test label pop-up")
	public void Test03_1076758(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Configure Labels - General: UI: Verify select printer come as dropdown in Print Test label pop-up");
		
		test.siteConfigurationAction.verifyDropDownFieldOnAddSytemLabel("SelectPrinter");
		
	}

	@Test(priority = 4, description = "VPLX: Configure Labels - General: UI: Verify cancel and print button displayed on bottom of print Test label pop up")
	public void Test04_1077279(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Configure Labels - General: UI: Verify cancel and print button displayed on bottom of print Test label pop up");
		
		test.supportDataActions.verifyButtons("printcancel");
		
	}

	@Test(priority = 5, description = "VPLX: Configure Labels - General: UI: Verify print button be enabled on selecting printer from select printer drop down.")
	public void Test05_1077280(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Configure Labels - General: UI: Verify print button be enabled on selecting printer from select printer drop down.");
		
		test.siteConfigurationAction.selectValueFromDropDownByIndex("SelectPrinter", 1);
		test.siteConfigurationAction.verifyButtonIsEnabled("Print");
		
	}

	@Test(priority = 6, description = "VPLX: Configure Labels - General: UI: Verify on selecting a printer and click on print button a successfull message displayed:Label has been sent for printing.")
	public void Test06_1077285(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Configure Labels - General: UI: Verify on selecting a printer and click on print button a successfull message displayed:Label has been sent for printing..");
		
		test.siteConfigurationAction.clickButtonUsingId_withoutwait("print");
		test.siteConfigurationAction.verifySuccessMessageOnSystemLabel(getData("SystemLabel.PrinterMessage"));
			
	}

}
