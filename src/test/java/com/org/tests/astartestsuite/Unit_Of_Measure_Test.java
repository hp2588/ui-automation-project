package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Unit_Of_Measure_Test extends BaseTest {
	
	String unit_code, unit_Description, sortorder, unit_code1, unit_Description1, baseunit, conversion_factor;
	
	@Test(priority = 1, description = "To verify Inventory Unit of Measure with a unique name is acceptable upon saving.")
	public void Test01_1114524(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"To verify Inventory Unit of Measure with a unique name is acceptable upon saving.");
		test.landingPageActions.navigateToFeature("Units of Measure");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add Unit of Measure");
		unit_code = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
				"Code" + System.currentTimeMillis());
		unit_Description = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Des" + System.currentTimeMillis());
		test.siteConfigurationAction.clickCheckboxRoleofUOM("1");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchFieldGl(unit_code, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(unit_code);
		unit_code1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("displayCode",
				"Codea" + System.currentTimeMillis());
		unit_Description1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Desa" + System.currentTimeMillis());
		conversion_factor = test.siteConfigurationAction.enterDataInInputField("conversionAmount",
				"1234567891.12345678912345");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
	}

}
