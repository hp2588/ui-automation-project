package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class TherapeuticClasses_Feature extends BaseTest {

	String Code, Code2, Code3, Code4, description, sortOrder, Code1, description1, sortOrder1, itemID,
			ExternalSystem = TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim();

	@Test(priority = 1, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : User is able to click add therapeutic class where three columns are present Code , Description and Sort order")
	public void Test01_1108938(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : User is able to click add therapeutic class where three columns are present Code , Description and Sort order");
		test.landingPageActions.navigateToFeature("Therapeutic Classes");
		//test.siteConfigurationAction.selectValueFromDropDown("Therapeutic", ExternalSystem);
		test.siteConfigurationAction.selectValueFromDropDown("Therapeutic", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewClassonTherapeuticClass("Add Therapeutic Class");

		Code = test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode",
				"code" + System.currentTimeMillis());
		description = test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
				"des" + System.currentTimeMillis());
		sortOrder = test.siteConfigurationAction.enterDataInInputField("therapeuticClassSortOrder", "5");
		//test.supportDataActions.clickButton("save");
		test.storageAreaAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(Code);

	}

	@Test(priority = 2, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  In Item managemnt user can save multiple Therapeutic class")
	public void Test02_1129706(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  In Item managemnt user can save multiple Therapeutic class");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewClassonTherapeuticClass("Add Therapeutic Class");

		Code1 = test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode",
				"code" + System.currentTimeMillis());
		description = test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
				"des" + System.currentTimeMillis());
		sortOrder = test.siteConfigurationAction.enterDataInInputField("therapeuticClassSortOrder", "7");
		test.supportDataActions.clickButton("saveAdd");
		Code2 = test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode",
				"code" + System.currentTimeMillis());
		description1 = test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
				"des" + System.currentTimeMillis());
		sortOrder1 = test.siteConfigurationAction.enterDataInInputField("therapeuticClassSortOrder", "8");
		//test.supportDataActions.clickButton("save");
		test.storageAreaAction.clickSaveButton();
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
//		test.siteConfigurationAction.enterRandomValueInRichInputField(ExternalSystem);
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.siteConfigurationAction.clickTherapeuticdropdownonItemScreen("therapeuticClass_trigger");
		test.siteConfigurationAction.verifytherapeuticclassIsPresentOnItemScreen(Code1);
		test.siteConfigurationAction.verifytherapeuticclassIsPresentOnItemScreen(Code2);

	}

	@Test(priority = 3, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  Clicking on save button therapeutic class is saved in selected PIS and Visible on Add/Edit Item screen")
	public void Test03_1109061(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  Clicking on save button therapeutic class is saved in selected PIS and Visible on Add/Edit Item screen");

		test.siteConfigurationAction.verifytherapeuticclassIsPresentOnItemScreen(Code1);
		test.siteConfigurationAction.clickActionbutton("Cancel");
	}

	@Test(priority = 4, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : An Therapeutic class already saved in the Item managemet cannot be used again for same item")
	public void Test04_1129708(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] : An Therapeutic class already saved in the Item managemet cannot be used again for same item");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Therapeutic Classes");
		//test.siteConfigurationAction.selectValueFromDropDown("Therapeutic", ExternalSystem);
		test.siteConfigurationAction.selectValueFromDropDown("Therapeutic", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewClassonTherapeuticClass("Add Therapeutic Class");

		Code3 = test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode",
				"code" + System.currentTimeMillis());
		description = test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
				"des" + System.currentTimeMillis());
		sortOrder = test.siteConfigurationAction.enterDataInInputField("therapeuticClassSortOrder", "5");
		test.supportDataActions.clickButton("saveAdd");
		Code4 = test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode",
				"code" + System.currentTimeMillis());
		description1 = test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
				"des" + System.currentTimeMillis());
		sortOrder1 = test.siteConfigurationAction.enterDataInInputField("therapeuticClassSortOrder", "8");
		//test.supportDataActions.clickButton("save");
		test.storageAreaAction.clickSaveButton();
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		//test.siteConfigurationAction.enterRandomValueInRichInputField(ExternalSystem);
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");

		test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickTherapeuticdropdownonItemScreen("therapeuticClass_trigger");
		test.siteConfigurationAction.clickCheckboxTherapeuticClassitemlevel(Code3);

		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickTherapeuticdropdownonItemScreen("therapeuticClass_trigger");
		// test.siteConfigurationAction.verifytherapeuticclassIsPresentOnItemScreen(Code3);
		test.siteConfigurationAction.clickActionbutton("Cancel");

	}

	@Test(priority = 5, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  Selecting multiple therapeutic class delete button deletes selected classes.")
	public void Test05_1109121(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Item Setup - ES Parity (Formulary Reference Data) Therapeutic Class : [UI] :  Selecting multiple therapeutic class delete button deletes selected classes.");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Therapeutic Classes");
		//test.siteConfigurationAction.selectValueFromDropDown("Therapeutic", getData("ExternalSystem.Name10"));
		test.siteConfigurationAction.selectValueFromDropDown("Therapeutic", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewClassonTherapeuticClass("Add Therapeutic Class");

		test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode", "code" + System.currentTimeMillis());
		test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
				"des" + System.currentTimeMillis());
		test.siteConfigurationAction.enterDataInInputField("therapeuticClassSortOrder", "11");
		test.supportDataActions.clickButton("saveAdd");
		test.siteConfigurationAction.enterDataInInputField("therapeuticClassCode", "code" + System.currentTimeMillis());
		test.siteConfigurationAction.enterDataInTextAreaField("therapeuticClassDescription",
				"des" + System.currentTimeMillis());
		test.siteConfigurationAction.enterDataInInputField("therapeuticClassSortOrder", "12");
		//test.supportDataActions.clickButton("save");
		test.storageAreaAction.clickSaveButton();

		test.siteConfigurationAction.clickAllCheckboxesonTherapeuticclass("checkboxALL_CHECKBOX_KEY");
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewClassonTherapeuticClass("Delete Selected");
		test.supportDataActions
				.verifyPopupDeleteMessageonThearpeuticclass("Are You sure you want to Delete the Selected Entry ?");
		test.siteConfigurationAction.confirmDeletePopup();

	}
}
