package com.org.tests.manageisa;

import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_973314 extends BaseTest {

	String ISAName, facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
			ipAddress, portNumber, carouselConnectionResetTime;

	@Test(priority = 1, description = "VPLX:Manage ISAs:[UI] -User is able to select the Display settings tab during Add/Edit operation of ISA")
	public void Test01_1033531(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is able to select the Display settings tab during Add/Edit operation of ISA");
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		ISAName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());

		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("workstationComputerKey", 1);
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("logisticsLabelPrinterKey", 1);
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		test.siteConfigurationAction.clickTab("Carousel Settings");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("carouselKey", 1);
		deviceNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("devicenumber",
				"123456");
		ipAddress = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddressValue",
				"10.11.22.34");
		portNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("portNumber", "46345");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("connectionResetMinutes", "20");
		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.siteConfigurationAction.clickTab("Display Settings");	
	}

	@Test(priority = 2, description = "VPLX:Manage ISAs:[UI] -Max columns field is non -mandatory and editable under Display settings")
	public void Test02_1033536(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Max columns field is non -mandatory and editable under Display settings");
		test.siteConfigurationAction.enterDataInInputField("maximumDisplayColumnsNumber",
				getData("ISADisplaySettings.MaxDisplayColumn"));

	}

	@Test(priority = 3, description = "VPLX:Manage ISAs:[UI] -Light display is not handled by ISA controller is unchecked by default")
	public void Test03_1034097(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Light display is not handled by ISA controller is unchecked by default");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("displayAttachedFlag"));

	}

	/* ==========================AUTOMATED============================== */

	@Test(priority = 4, description = "VPLX:Manage ISAs:[UI] -User is able to select the left and right radio button for horizontal carousels only.")
	public void Test04_1034077(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is able to select the left and right radio button for horizontal carousels only.");
		test.siteConfigurationAction.selectRadioOption("displayArrowDirection-Left");
		test.siteConfigurationAction.selectRadioOption("displayArrowDirection-Right");
	}

	@Test(priority = 5, description = "VPLX:Manage ISAs:[UI] -IP address field is mandatory and editable under Display settings")
	public void Test05_1033540(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -IP address field is mandatory and editable under Display settings");
		test.siteConfigurationAction.selectCheckboxCorresspondingToISAField("displayAttachedFlag", true);
		test.siteConfigurationAction.enterDataInInputField("displayIPAddressValue",
				getData("ISADisplaySettings.DisplayIPAddress"));

	}

	@Test(priority = 6, description = "VPLX:Manage ISAs:[UI] - Port Number field is mandatory and editable under Display settings")
	public void Test06_1033542(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] - Port Number field is mandatory and editable under Display settings");
		test.siteConfigurationAction.enterDataInInputField("displayPortNumber",
				getData("ISADisplaySettings.DisplayPortNumber"));

	}

	@Test(priority = 7, description = "VPLX:Manage ISAs:[UI] - Display type field is mandatory and user is able to select from dropdown box")
	public void Test07_1033544(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] - Display type field is mandatory and user is able to select from dropdown box");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("ticBarDisplayTypeKey");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("ticBarDisplayTypeKey", 1);

	}

	@Test(priority = 8, description = "VPLX:Manage ISAs:[UI] -Columns per inch field is non mandatory and editable under Display Settings tab")
	public void Test08_1033547(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Columns per inch field is non mandatory and editable under Display Settings tab");
		test.siteConfigurationAction.enterDataInInputField("displayColumnsPerInchValue",
				getData("ISADisplaySettings.DisplayColumnsPerInch"));
	}

	@Test(priority = 9, description = "VPLX:Manage ISAs:[UI] -Columns per inch field is non mandatory and editable under Display Settings tab")
	public void Test09_1033547(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Columns per inch field is non mandatory and editable under Display Settings tab");
		test.siteConfigurationAction.enterDataInInputField("displayColumnsPerInchValue", "555");
	}

	@Test(priority = 10, description = "VPLX:Manage ISAs:[UI] -Simple bin light display (not a tic bar) is unchecked by default")
	public void Test10_1034086(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Simple bin light display (not a tic bar) is unchecked by default");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("simpleLightBinDisplayFlag"));

	}

	@Test(priority = 11, description = "VPLX:Manage ISAs:[UI] -Display type ,IP Address and Port fields gets enabled when Light display is not handled by ISA controller is checked")
	public void Test11_1034101(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Display type ,IP Address and Port fields gets enabled when Light display is not handled by ISA controller is checked");
		test.siteConfigurationAction.selectCheckboxCorresspondingToISAField("displayAttachedFlag", false);
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("displayIPAddressValue"));
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("displayPortNumber"));
		test.siteConfigurationAction.selectCheckboxCorresspondingToISAField("displayAttachedFlag", true);
		Assert.assertFalse(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("displayIPAddressValue"));
		Assert.assertFalse(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("displayPortNumber"));

	}
}