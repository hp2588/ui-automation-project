package com.org.tests.mainmenu.customlabels;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1057892 extends BaseTest {

	String labelName;

	@Test(priority = 1, description = "VPLX-Ad-Hoc Label Design and Printing:[UI]:Enabled add button diplayed on Custom labels page")
	public void Test01_1085978(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX-Ad-Hoc Label Design and Printing:[UI]:Enabled add button diplayed on Custom labels page");
		test.landingPageActions.navigateToFeature("Custom Labels");
		test.supportDataActions.verifyLabelIsPresent("Custom Labels");
		test.siteConfigurationAction.verifyButtonIsDisabled("add");
	}

	@Test(priority = 2, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:On clicking add button add Custom labels pop-up is opened")
	public void Test02_1085982(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:On clicking add button add Custom labels pop-up is opened");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Custom Label");
	}

	@Test(priority = 3, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Custom label Name  stock  and facility as mandatory field for add Custom labels Add/Edit Popup")
	public void Test03_1085984(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Custom label Name  stock  and facility as mandatory field for add Custom labels Add/Edit Popup");
		Assert.assertTrue(test.siteConfigurationAction.verifyMandatoryField("Name"),
				"[ASSERTION FAILED]: Name is not mandatory field.");

		Assert.assertTrue(test.siteConfigurationAction.verifyMandatoryField("Stock"),
				"[ASSERTION FAILED]: Stock is not mandatory field.");
	}

	@Test(priority = 4, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:Cancel and new design button is getting present on Add/Edit Custom labels pop up")
	public void Test04_1085986(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:Cancel and new design button is getting present on Add/Edit Custom labels pop up");
		test.siteConfigurationAction.verifyLinkText("cancel");
		test.siteConfigurationAction.verifyLinkText("save");
	}

	@Test(priority = 5, description = "VPLX:Ad-Hoc Label Design and Printing:UI:Verify toast message generated when user hover mouse over message information icon for stock.")
	public void Test05_1086006(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:UI:Verify toast message generated when user hover mouse over message information icon for stock.");
		test.siteConfigurationAction.verifyToolTipForStock(getData("ErrorMessage.TooltipStock"));
	}

	@Test(priority = 6, description = "VPLX:Ad-Hoc Label Design and Printing:UI:Verify on clicking Cancel button it close the current popup and again land the user into the listing page.")
	public void Test06_1085988(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:UI:Verify on clicking Cancel button it close the current popup and again land the user into the listing page.");

		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");
		test.supportDataActions.verifyLabelIsPresent("Custom Labels");

	}

	/*
	 * @Test(priority = 7, description =
	 * "VPLX:Ad-Hoc Label Design and Printing:UI:Verify on clicking the Design button combit launched and allow user to design the combit."
	 * ) public void Test07_1085991(Method method) {
	 * ExtentTestManager.startTest(method.getName(),
	 * "VPLX:Ad-Hoc Label Design and Printing:UI:Verify on clicking the Design button combit launched and allow user to design the combit."
	 * );
	 * test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Ad-Hoc Label"
	 * ); labelName =
	 * test.siteConfigurationAction.enterRandomValueInInputField("labelName",
	 * "Name" + System.currentTimeMillis());
	 * test.siteConfigurationAction.selectValueFromDropDownByIndex("facility",
	 * 1); test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",
	 * 1); //test.siteConfigurationAction.clickButton("save");
	 * 
	 * }
	 */

	@Test(priority = 7, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:New design button is enabled when all mandatory field entered for Add/Edit Custom labels pop-up")
	public void Test07_1086045(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:New design button is enabled when all mandatory field entered for Add/Edit Custom labels pop-up");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Custom Label");
		labelName = test.siteConfigurationAction.enterRandomValueInInputField("labelName",
				"Name" + System.currentTimeMillis());
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("facility", 1);
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("stock", 1);
		Assert.assertFalse(test.siteConfigurationAction.verifyButtonIsDisabled("save"));

	}

	@Test(priority = 8, description = "VPLX:Ad-Hoc Label Design and Printing:[UI]:On adding a new Custom  label , when the user click on the Design button this show a success message that ' Your data has been saved successfully'")
	public void Test08_1086004(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Ad-Hoc Label Design and Printing:[UI]:On adding a new Custom  label , when the user click on the Design button this show a success message that ' Your data has been saved successfully'");
		
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditAdhocLabel"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(labelName);

	}
}
