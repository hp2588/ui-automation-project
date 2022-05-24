package com.org.tests.therapeuticclasses;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1087092 extends BaseTest {
	
	String Code, Code2,Code3,Code4,description, changed_Code,sortOrder,Code1, description1, sortOrder1,itemID;
	String class11,class22,class33,itemName;
	@Test(priority = 1, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : On main home page link is present of \"Therapeutic Class\"")
	public void Test01_1108936(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : On main home page link is present of \"Therapeutic Class\"");
		//test.siteConfigurationAction.verifytherapeuticclassIsPresentOnItemScreen("Therapeutic Classes");
		test.landingPageActions.navigateToFeature("Therapeutic Classes");
	
	}
	
	@Test(priority = 2, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  \"Add therapeutic class\" is present on clicking action button")
	public void Test02_1108937(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  \\\"Add therapeutic class\\\" is present on clicking action button");
		 test.siteConfigurationAction.selectValueFromDropDown("Therapeutic",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewClassonTherapeuticClass("Add Therapeutic Class");
}
	
	@Test(priority = 3, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : User is able to click add therapeutic class where three columns are present Code , Description and Sort order")
	public void Test03_1108938(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : User is able to click add therapeutic class where three columns are present Code , Description and Sort order");
	test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("therapeuticClassCode");
	test.siteConfigurationAction.verifydescriptionInputField("therapeuticClassDescription");
	test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("therapeuticClassSortOrder");
	
	}
	
	@Test(priority = 4, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : User is able to view Edit and Delete options for Therapeutic class under Actions."
			+ ""
			+ "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  Description Column is present and can accept any character value"
			+ ""
			+ "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  Sort Order field is present and saved and is sorted in Item management accordingly")
	public void Test04_Test05_Test06_1108939_AND_1109052_AND_1109053(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : User is able to view Edit and Delete options for Therapeutic class under Actions.");
	Code = test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode",
			"code" + System.currentTimeMillis());
	description = test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
			"des" + System.currentTimeMillis());
	sortOrder = test.siteConfigurationAction.enterDataInInputField("therapeuticClassSortOrder",
			"5");
	test.storageAreaAction.clickSaveButton();
	test.siteConfigurationAction.verifyNewlyAddedRecordNameInList("Code");
	//test.siteConfigurationAction.verifyButtonIsPresent("Edit");
	test.siteConfigurationAction.verifyButtonIsPresent("Delete");

}
	
	@Test(priority = 7, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  User is allowed to enter only Unique Entries for Therauptic Class."
			+ ""
			+ "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  'Save and add another class' button is present")
	public void Test07_Test08_Test09_Test10_Test11_1108940_AND_1109058_AND_1109059_AND_1109060_AND_1110207(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  User is allowed to enter only Unique Entries for Therauptic Class.");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewClassonTherapeuticClass("Add Therapeutic Class");
		test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode",Code);
		description = test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
				"des" + System.currentTimeMillis());
		sortOrder = test.siteConfigurationAction.enterDataInInputField("therapeuticClassSortOrder",
				"5");
		test.siteConfigurationAction.verifyButtonIsPresent("Save & Add Another");
		test.siteConfigurationAction.verifyButtonIsPresent("Cancel");		
		test.storageAreaAction.clickSaveButton();
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName("This Therapeutic Class Code already exists. Please provide a unique Therapeutic Class Code .");
		
}
	@Test(priority = 12, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  Code field will accept only 20 Alpha-Numeric characters.")
	public void Test12_1109048(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  Code field will accept only 20 Alpha-Numeric characters.");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("therapeuticClassCode"), 20);
		
	}
	
	@Test(priority = 13, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  Sort Order Column accepts only numeric and upto four digits only.")
	public void Test13_1109057(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  Sort Order Column accepts only numeric and upto four digits only.");
		test.siteConfigurationAction.verifyInputField("therapeuticClassSortOrder");
		Assert.assertEquals(test.supportDataActions.verifyMaxLengthOfAnInputField("therapeuticClassSortOrder"), 4);
	}
	
	
	
	
	@Test(priority = 14, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  User is able to edit any of the fields Code , Description or Sort Number and updated Class is visible drop-down on Add/Edit Item page.")
	public void Test014_1110206(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  User is able to edit any of the fields Code , Description or Sort Number and updated Class is visible drop-down on Add/Edit Item page.");
		test.siteConfigurationAction.clickButton("cancel");	
		test.supportDataActions.enterSearchTermInSearchFieldGl(Code, "searchFilter");
		test.supportDataActions.clickOnEditLinkCorresspondingToTherapeutic(Code);
		changed_Code = test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode",
				"code" + System.currentTimeMillis());
		description = test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
				"des" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(changed_Code);
		
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		
		test.siteConfigurationAction.clickTherapeuticdropdownonItemScreen("therapeuticClass_trigger");
		test.siteConfigurationAction.clickCheckboxTherapeuticClassitemlevel(changed_Code);
}
	@Test(priority = 15, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : An Therapeutic class cannot be deleted if used in system , warning message appears if  tried deleted")
	public void Test15_1130478(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : An Therapeutic class cannot be deleted if used in system , warning message appears if  tried deleted");
		

		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		//test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());

		itemName = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		
		test.landingPageActions.navigateToFeature("Therapeutic Classes");
		test.supportDataActions.clearSearchBoxField("searchFilter");
		test.supportDataActions.enterSearchTermInSearchFieldGl(changed_Code, "searchFilter");
		test.siteConfigurationAction.clickDeleteTherapeuticLink(changed_Code,"Delete");
		test.siteConfigurationAction.verifyDeleteMessage("Are You sure you want to Delete the Selected Entry ?");
		test.siteConfigurationAction.clickButton("primary");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName("This Therapeutic Class is associated with an Item. You cannot modify this Therapeutic Class.");
		TestDataPropertyReaderAndWriter.setProperty("TherapeuticClass", changed_Code);
	}
	@Test(priority = 16, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  A therapeutic class (Not mapped to item) when deleted, is not displayed on Add/Edit Item Management screen")
	public void Test16_1109063(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  Deleting the selected therapeutic class is deleted with a confirmation Pop-up is displayed on Therapeutic Classes screen.");
		test.landingPageActions.navigateToFeature("Therapeutic Classes");
		
		test.siteConfigurationAction.selectValueFromDropDown("Therapeutic",TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
	
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewClassonTherapeuticClass("Add Therapeutic Class");
		
		class11=test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode",
				"code" + System.currentTimeMillis());
		 test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
				"des" + System.currentTimeMillis());
		 test.siteConfigurationAction.enterDataInInputField("therapeuticClassSortOrder",
				"11");
		test.supportDataActions.clickButton("save");
		
		/* class22=test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode",
				"code" + System.currentTimeMillis());
		test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
				"des" + System.currentTimeMillis());
		 test.siteConfigurationAction.enterDataInInputField("therapeuticClassSortOrder",
				"22");
		 test.supportDataActions.clickButton("saveAdd");
		 class33=test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode",
					"code" + System.currentTimeMillis());
			test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
					"des" + System.currentTimeMillis());
			 test.siteConfigurationAction.enterDataInInputField("therapeuticClassSortOrder",
					"33");
			 test.supportDataActions.clickButton("saveAdd");*/
		 test.supportDataActions.enterSearchTermInSearchFieldGl(class11, "searchFilter");
		test.siteConfigurationAction.clickDeleteTherapeuticLink(class11,"Delete");
		test.siteConfigurationAction.verifyDeleteMessage("Are You sure you want to Delete the Selected Entry ?");
		test.siteConfigurationAction.clickButton("primary");
		 test.supportDataActions.enterSearchTermInSearchFieldGl(class11, "searchFilter");
		 Assert.assertEquals(test.supportDataActions.getNoDataText(), "No Matching Results.");
		 //test.supportDataActions.clearSearchBox("search");
		 test.landingPageActions.navigateToFeature("Item Management");
			test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
			test.siteConfigurationAction.clickActionbutton("Actions");
			test.siteConfigurationAction.clickActionbutton("Add New Item");
			test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
			
			test.siteConfigurationAction.clickTherapeuticdropdownonItemScreen("therapeuticClass_trigger");
			Assert.assertFalse(test.siteConfigurationAction.TherapeuticClassitemlevelIsDisplayed(class11),
					"[ASSERTION FAILED]:Deleted Therapetic class is displayed on Add/Edit Item screen.");
		 
	}
	@Test(priority = 17, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : Once user deletes multiple therapeutic class the selected one should be deleted with confirmation pop-up with details of that Classes.")
	public void Test17_1109122(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : Once user deletes multiple therapeutic class the selected one should be deleted with confirmation pop-up with details of that Classes.");
		test.landingPageActions.navigateToFeature("Therapeutic Classes"); 
		test.siteConfigurationAction.clickAllCheckboxesonTherapeuticclass("checkboxALL_CHECKBOX_KEY");
		 test.siteConfigurationAction.clickActionbutton("Actions");
		 test.siteConfigurationAction.clickAddNewClassonTherapeuticClass("Delete Selected");
		 test.supportDataActions.verifyPopupDeleteMessageonThearpeuticclass("Are You sure you want to Delete the Selected Entry ?");
		 test.siteConfigurationAction.confirmDeletePopup();
		
}
}