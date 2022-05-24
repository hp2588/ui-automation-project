package com.org.tests.mainmenu.dispenseunits;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_997964 extends BaseTest {
	
	String dispenseCode, description, sortOrder, dispenseCode1, description1,dispenseUsedElseWhere,itemID,facilityOnWFAScreen;

	@Test(priority = 1, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: User is able to toggle to set a record as Inactive while editing Dispense Unit")
	public void Test01_1047780(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: User is able to toggle to set a record as Inactive while editting Dispense Unit");
		test.landingPageActions.navigateToFeature("Dispense Units");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		dispenseCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "2");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		//test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode, "search");
		//test.supportDataActions.refreshPage();
		
		test.siteConfigurationAction.enterSearchTermInSearchField(dispenseCode, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		
		//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord_dispenseUnit(dispenseCode, "Edit Dispense Unit");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", false);
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("false"));

	}

	

	@Test(priority = 2, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Code greater than length 20 is not allowed")
	public void Test02_1047783(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Code greater than length 20 is not allowed");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("dispenseUnitCode"), 20);
	}

	@Test(priority = 3, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Description greater than length 100 is not allowed")
	public void Test03_1047792(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Description greater than length 100 is not allowed");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnTextAreaField("descriptionText"), 100);
	}

	@Test(priority = 4, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Sort Order greater than 9999 is not allowed")
	public void Test04_1047833(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Sort Order greater than 9999 is not allowed");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("sortValue"), 4);
		description = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
	}

	@Test(priority = 5, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Sort Order less than 1 is not allowed")
	public void Test05_1047835(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Sort Order less than 1 is not allowed");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", "Code" + System.currentTimeMillis());
		test.supportDataActions.EnterRandomValueInInputField("sortValue", "-2");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName("Enter numeric value only");
		test.supportDataActions.clickButton("cancel");
	}

	@Test(priority = 6, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: User edits Dispense Unit with  Mandatory unique Code upto 20 characters successfully")
	public void Test06_1047790(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: User edits Dispense Unit with  Mandatory unique Code upto 20 characters successfully");
		//test.supportDataActions.refreshPage();
		
		test.siteConfigurationAction.enterSearchTermInSearchField(dispenseCode, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		
		//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord_dispenseUnit(dispenseCode, "Edit Dispense Unit");
		dispenseCode = test.siteConfigurationAction.enterRandomValueInInputField("dispenseUnitCode",
				"DisCode" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.enterSearchTermInSearchField(dispenseCode, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dispenseCode);
		
	}

	@Test(priority = 7, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: user is able to edit PIS from drop-down while editing Dispense Unit",enabled=false)
	public void Test07_1047840(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: user is able to edit PIS from drop-down while editing Dispense Unit");
		test.supportDataActions.refreshPage();
		//test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode, "search");
		//Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(dispenseCode, "1"));
		test.supportDataActions.clickEditLink(dispenseCode);
		/*test.supportDataActions.selectValueFromDropDownForDosagePISSystem("SelectPISType1",
				getData("DosageForm.SelectPISType2"));
*/		test.supportDataActions.selectValueFromDropdownByIndex("externalSystemKey", 2);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
	}

	@Test(priority = 8, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: User edits Dispense Unit with unique Description upto 100 characters successfully")
	public void Test08_1047800(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: User edits Dispense Unit with unique Description upto 100 characters successfully");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		dispenseCode = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description2348247023482730492420349238420934830980830459348039830594583045984098098090" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "2");
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode, "search");
		
		test.siteConfigurationAction.enterSearchTermInSearchField(dispenseCode, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		
		//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord_dispenseUnit(dispenseCode, "Edit Dispense Unit");
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description1" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));

	}

	@Test(priority = 9, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: user is able to edit Dispense Unit without providing Description")
	public void Test09_1047830(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: user is able to edit Dispense Unit without providing Description");
		//test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode, "search");
		
		test.siteConfigurationAction.enterSearchTermInSearchField(dispenseCode, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		
		//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord_dispenseUnit(dispenseCode, "Edit Dispense Unit");
		dispenseCode = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.clearDosageDescriptionBox("descriptionText");
		test.supportDataActions.clickButton("save");
		
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

	}


	@Test(priority=10,description="VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears if user tries to edit Dispense Unit by deleting existing Code")
	public void Test10_1047781(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears if user tries to edit Dispense Unit by deleting existing Code");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		dispenseCode = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		description = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "2");
		test.supportDataActions.clickButton("save");
		//test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode, "search");
		//test.supportDataActions.refreshPage();
		
		test.siteConfigurationAction.enterSearchTermInSearchField(dispenseCode, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		
		//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord_dispenseUnit(dispenseCode, "Edit Dispense Unit");
		test.supportDataActions.clearDosagecodeInputBox("dispenseUnitCode");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.Code"));
		
		test.supportDataActions.clickButton("cancel");
	}

	// Bug-1109091

	@Test(priority = 11, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears on editting Dispense Unit with already existing Code")
	public void Test11_1047786(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears on editting Dispense Unit with already existing Code");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Dispense Unit");
		dispenseCode1 = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		description1 = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", getData("DosageForm.SortOrder"));
		//test.supportDataActions.selectValueFromDropDownForDosagePISSystem("SelectPISType1",
		//		getData("DosageForm.SelectPISType2"));
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(dispenseCode1);
		//test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode, "search");
		//Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(dispenseCode, "1"));
		
		test.siteConfigurationAction.enterSearchTermInSearchField(dispenseCode, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		
		//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord_dispenseUnit(dispenseCode, "Edit Dispense Unit");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", dispenseCode1);
		test.supportDataActions.clickButton("save");
		//Thread.sleep(10000);
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.CodeAlreadyExist"));
	}

	// Bug-1109091

	@Test(priority = 12, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears on editing Dispense Unit with already existing Code in upper case")
	public void Test12_1047787(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears on editing Dispense Unit with already existing Code in upper case");
		test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", dispenseCode1.toUpperCase());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.CodeAlreadyExist"));
	}


	@Test(priority = 13, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears on editting Dispense Unit with already existing Description")
	public void Test13_1047796(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears on editting Dispense Unit with already existing Description");
		test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText", description1);
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.CodeAlreadyExist"));
	}
	
	// Bug-1109091
	
	@Test(priority = 14, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears on editing Dispense Unit with already existing Code in upper case")
	public void Test14_1047798(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Error message appears on editing Dispense Unit with already existing Code in upper case");
		test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText", description1.toUpperCase());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(getData("ErrorMessage.CodeAlreadyExist"));
		test.supportDataActions.clickButton("cancel");
	}

	
	@Test(priority = 16, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Sort Order does not edits with decimal value Shaurya Nigam Design")
	public void Test16_1047839(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: Sort Order does not edits with decimal value Shaurya Nigam Design");
		
		test.siteConfigurationAction.enterSearchTermInSearchField(dispenseCode, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		
		//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord_dispenseUnit(dispenseCode, "Edit Dispense Unit");
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "2.4");
		test.supportDataActions.verifyErrorMessageDosageCode("Enter numeric value only");
		test.supportDataActions.clickButton("cancel");
	}
		
	@Test(priority = 17, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: User edits Dispense Unit without providing Sort Order Shaurya Nigam Design")
	public void Test17_1047851(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-Edit: User edits Dispense Unit without providing Sort Order Shaurya Nigam Design");
		
		test.siteConfigurationAction.enterSearchTermInSearchField(dispenseCode, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		
		//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord_dispenseUnit(dispenseCode, "Edit Dispense Unit");
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "");
		test.supportDataActions.clickButton("save");
	}
	
	@Test(priority = 18, description = "Dispense Unit [UI]: Dispense Unit-Edit: Dispense Unit  edits with existing Sort Order")
	public void Test18_1047837(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"Dispense Unit [UI]: Dispense Unit-Edit: Dispense Unit  edits with existing Sort Order");
		
		test.siteConfigurationAction.enterSearchTermInSearchField(dispenseCode, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		
		//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord_dispenseUnit(dispenseCode, "Edit Dispense Unit");
		sortOrder = test.supportDataActions.EnterValueInInputField("sortValue", "2");
		test.supportDataActions.clickButton("save");
	}

	/*@Test(priority = 15, description = "VPLX: Dispense Unit [UI]: Dispense Unit-Edit: User is not able to toggle to set a record as Inactive while editting Dispense Unit if it is being used elsewhere in the system")
	public void Test15_1047849(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dosage Form [UI]: Dispense Unit-Edit: User is not able to toggle to set a record as Inactive while editting Dosage Form if it is being used elsewhere in the system");
		test.supportDataActions.clickButton("cancel");

		test.supportDataActions.enterSearchTermInSearchFieldGl(dispenseCode, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(dispenseCode, "1"));
		test.supportDataActions.clickEditLink(dispenseCode);

		test.siteConfigurationAction.enterRandomValueInInputField("dispenseUnitCode", dispenseCode1);
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Active", false);
		test.supportDataActions.clickButton("save");

		//test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode", dispenseCode1);
	}*/

}
