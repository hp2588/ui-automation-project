package com.org.tests.manageisa;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_977313 extends BaseTest {

	String ISAName, facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber,
			ipAddress, portNumber, carouselConnectionResetTime;

	@Test(priority = 1, description = "VPLX:Manage ISAs:[UI] -User is able to select the Configuration tab during Add/Edit operation of ISA.")
	public void Test01_1033413(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is able to select the Configuration tab during Add/Edit operation of ISA.");
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		//test.storageAreaAction.clickButton("Edit");
		test.siteConfigurationAction
		.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ISANameNew"), "search");

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToAddedRecord(TestDataPropertyReaderAndWriter.getProperty("ISANameNew"), TestDataPropertyReaderAndWriter.getProperty("ISANameNew"));
		test.storageAreaAction.clickTab("ISA Configuration".trim());
		test.storageAreaAction.verifyPageTitleContains("ISA Configuration".trim());

	}

	@Test(priority = 2, description = "VPLX:Manage ISAs:[UI] -Max racks field under Configuration tab of Add/Edit ISA is mandatory  and editable.")
	public void Test02_1033426(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Max racks field under Configuration tab of Add/Edit ISA is mandatory  and editable.");
		test.storageAreaAction.verifyInputField("maxRackNumber");
		test.storageAreaAction.verifyDefaultValueofInputField("maxRackNumber","1");
		test.storageAreaAction.enterDataInInputField("maxRackNumber", getData("ISAConfigurationTab.maxRacks"));
	}

	@Test(priority = 3, description = "VPLX:Manage ISAs:[UI] -Max shelves field under Configuration tab of Add/Edit ISA is mandatory  and editable.")
	public void Test03_1033436(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Max shelves field under Configuration tab of Add/Edit ISA is mandatory  and editable.");

		test.storageAreaAction.verifyInputField("maxShelvesNumber");
		test.storageAreaAction.verifyDefaultValueofInputField("maxShelvesNumber","1");
		test.storageAreaAction.enterDataInInputField("maxShelvesNumber",
				getData("ISAConfigurationTab.maxShelvesNumber"));

	}

	@Test(priority = 4, description = "VPLX:Manage ISAs:[UI] -Shelf Width field under Configuration tab of Add/Edit ISA is mandatory  and editable.")
	public void Test04_1033439(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Shelf Width field under Configuration tab of Add/Edit ISA is mandatory  and editable.");
		test.storageAreaAction.verifyInputField("shelfWidthValue");
		test.storageAreaAction.verifyDefaultValueofInputField("shelfWidthValue","3");
		test.storageAreaAction.enterDataInInputField("shelfWidthValue", getData("ISAConfigurationTab.shelfWidthValue"));

	}

	@Test(priority = 5, description = "VPLX:Manage ISAs:[UI] - Max Bins field under Configuration tab of Add/Edit ISA is mandatory and editable.")
	public void Test05_1033441(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] - Max Bins field under Configuration tab of Add/Edit ISA is mandatory and editable.");
		test.storageAreaAction.verifyInputField("maxBinNumber");
		test.storageAreaAction.verifyDefaultValueofInputField("maxBinNumber","1");
		test.storageAreaAction.enterDataInInputField("maxBinNumber", getData("ISAConfigurationTab.maxBinNumber"));

	}

	@Test(priority = 6, description = "VPLX:Manage ISAs:[UI] - Default bin width field under Configuration tab of Add/Edit ISA is mandatory and editable.")
	public void Test06_1033445(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] - Default bin width field under Configuration tab of Add/Edit ISA is mandatory and editable.");
		test.storageAreaAction.verifyInputField("defaultBinWidthValue");
		test.storageAreaAction.verifyDefaultValueofInputField("defaultBinWidthValue","3");

		test.storageAreaAction.enterDataInInputField("defaultBinWidthValue",
				getData("ISAConfigurationTab.defaultBinWidthValue"));

	}

	@Test(priority = 7, description = "VPLX:Manage ISAs:[UI] -  Horizontal dividers field under Configuration tab of Add/Edit ISA is mandatory and editable.")
	public void Test07_1033469(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -  Horizontal dividers field under Configuration tab of Add/Edit ISA is mandatory and editable.");
		test.storageAreaAction.verifyInputField("defaultBinDividersHorizontalNumber");
		test.storageAreaAction.verifyDefaultValueofInputField("defaultBinDividersHorizontalNumber","1");

		test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber",
				getData("ISAConfigurationTab.defaultBinDividersHorizontalNumber"));

	}

	@Test(priority = 8, description = "VPLX:Manage ISAs:[UI] -  Vertical dividers field under Configuration tab of Add/Edit ISA is mandatory and editable.")
	public void Test08_1033476(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -  Vertical dividers field under Configuration tab of Add/Edit ISA is mandatory and editable.");

		test.storageAreaAction.verifyInputField("defaultBinDividersVerticalNumber");
		test.storageAreaAction.verifyDefaultValueofInputField("defaultBinDividersVerticalNumber","1");

		test.storageAreaAction.enterDataInInputField("defaultBinDividersVerticalNumber",
				getData("ISAConfigurationTab.defaultBinDividersVerticalNumber"));

	}

	@Test(priority = 10, description = "VPLX:Manage ISAs:[UI] - User is able to save all the information on the Configuration tab .")
	public void Test09_1033483(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] - User is able to save all the information on the Configuration tab .");
		test.storageAreaAction.clickSaveButton();
		/*
		 * test.storageAreaAction.verifyNewlyAddedISAName(ISAName);
		 * test.storageAreaAction.refreshPage();
		 */ }

	@Test(priority = 9, description = "VPLX:Manage ISAs:[UI] - User is not allowed to save the page without entering the mandatory fields.")
	public void Test10_1033489(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] - User is not allowed to save the page without entering the mandatory fields.");
		/*
		 * test.storageAreaAction.clickEditLink(ISAName);
		 * test.storageAreaAction.clickTab("ISA Configuration".trim());
		 * test.storageAreaAction.verifyPageTitleContains("ISA Configuration".trim());
		 */
		test.storageAreaAction.enterDataInInputField("maxRackNumber", getData("ISAConfigurationTab.maxRacks"));
		test.storageAreaAction.verifyFieldIsMandatory("maxRackNumber");
		test.storageAreaAction.clearInputBox("maxRackNumber");
		test.storageAreaAction.verifyErrorMessageonAlert(getData("ISAConfigurationTab.ErrorMsg_BlankmaxRackNumber"));
		test.storageAreaAction.verifyModelIsDisabled();
		test.storageAreaAction.enterDataInInputField("maxRackNumber", getData("ISAConfigurationTab.maxRacks"));

		test.storageAreaAction.enterDataInInputField("maxShelvesNumber",
				getData("ISAConfigurationTab.maxShelvesNumber"));
		test.storageAreaAction.verifyFieldIsMandatory("maxShelvesNumber");
		test.storageAreaAction.clearInputBox("maxShelvesNumber");
		test.storageAreaAction.verifyErrorMessageonAlert(getData("ISAConfigurationTab.ErrorMsg_BlankmaxShelvesNumber"));
		test.storageAreaAction.verifyModelIsDisabled();
		test.storageAreaAction.enterDataInInputField("maxShelvesNumber",
				getData("ISAConfigurationTab.maxShelvesNumber"));

		test.storageAreaAction.enterDataInInputField("shelfWidthValue", getData("ISAConfigurationTab.shelfWidthValue"));
		//test.storageAreaAction.verifyFieldIsMandatory("shelfWidthValue");
		test.storageAreaAction.clearInputBox("shelfWidthValue");
		test.storageAreaAction.verifyErrorMessageonAlert("Please enter a value between 0 and 8");
		test.storageAreaAction.verifyModelIsDisabled();
		test.storageAreaAction.enterDataInInputField("shelfWidthValue", getData("ISAConfigurationTab.shelfWidthValue"));

		test.storageAreaAction.enterDataInInputField("maxBinNumber", getData("ISAConfigurationTab.maxBinNumber"));
		test.storageAreaAction.verifyFieldIsMandatory("maxBinNumber");
		test.storageAreaAction.clearInputBox("maxBinNumber");
		test.storageAreaAction.verifyErrorMessageonAlert(getData("ISAConfigurationTab.ErrorMsg_BlankmaxBinNumber"));
		test.storageAreaAction.verifyModelIsDisabled();
		test.storageAreaAction.enterDataInInputField("maxBinNumber", getData("ISAConfigurationTab.maxBinNumber"));

		test.storageAreaAction.enterDataInInputField("defaultBinWidthValue",
				getData("ISAConfigurationTab.defaultBinWidthValue"));
		test.storageAreaAction.verifyFieldIsMandatory("defaultBinWidthValue");
		test.storageAreaAction.clearInputBox("defaultBinWidthValue");
		test.storageAreaAction
				.verifyErrorMessageonAlert(getData("ISAConfigurationTab.ErrorMsg_BlankdefaultBinWidthValue"));
		test.storageAreaAction.verifyModelIsDisabled();
		test.storageAreaAction.enterDataInInputField("defaultBinWidthValue",
				getData("ISAConfigurationTab.defaultBinWidthValue"));

		test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber",
				getData("ISAConfigurationTab.defaultBinDividersHorizontalNumber"));
		test.storageAreaAction.verifyFieldIsMandatory("defaultBinDividersHorizontalNumber");
		test.storageAreaAction.clearInputBox("defaultBinDividersHorizontalNumber");
		test.storageAreaAction.verifyErrorMessageonAlert(
				getData("ISAConfigurationTab.ErrorMsg_BlankdefaultBinDividersHorizontalNumber"));
		test.storageAreaAction.verifyModelIsDisabled();
		test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber",
				getData("ISAConfigurationTab.defaultBinDividersHorizontalNumber"));

		test.storageAreaAction.enterDataInInputField("defaultBinDividersVerticalNumber",
				getData("ISAConfigurationTab.defaultBinDividersVerticalNumber"));
		test.storageAreaAction.verifyFieldIsMandatory("defaultBinDividersVerticalNumber");
		test.storageAreaAction.clearInputBox("defaultBinDividersVerticalNumber");
		test.storageAreaAction.verifyErrorMessageonAlert(
				getData("ISAConfigurationTab.ErrorMsg_BlankdefaultBinDividersVerticalNumber"));
		test.storageAreaAction.verifyModelIsDisabled();
		test.storageAreaAction.enterDataInInputField("defaultBinDividersVerticalNumber",
				getData("ISAConfigurationTab.defaultBinDividersVerticalNumber"));

	}

	/*
	 * @Test(priority = 11, description =
	 * "VPLX:Manage ISAs:[UI] - User is not allowed to switch the tabs on the ISA screen without entering the mandatory fields."
	 * ) public void Test11_1033524(Method method) {
	 * ExtentTestManager.startTest(method.getName(),
	 * "VPLX:Manage ISAs:[UI] - User is not allowed to switch the tabs on the ISA screen without entering the mandatory fields."
	 * );
	 * 
	 * test.storageAreaAction.enterDataInInputField("maxRackNumber",
	 * getData("ISAConfigurationTab.maxRacks"));
	 * test.storageAreaAction.verifyFieldIsMandatory("maxRackNumber");
	 * test.storageAreaAction.clearInputBox("maxRackNumber");
	 * test.storageAreaAction.verifyErrorMessageonAlert(getData(
	 * "ISAConfigurationTab.ErrorMsg_BlankmaxRackNumber"));
	 * test.storageAreaAction.verifyTabIsDisabled("Display Settings");
	 * test.storageAreaAction.enterDataInInputField("maxRackNumber",
	 * getData("ISAConfigurationTab.maxRacks"));
	 * 
	 * test.storageAreaAction.enterDataInInputField("maxShelvesNumber",
	 * getData("ISAConfigurationTab.maxShelvesNumber"));
	 * test.storageAreaAction.verifyFieldIsMandatory("maxShelvesNumber");
	 * test.storageAreaAction.clearInputBox("maxShelvesNumber");
	 * test.storageAreaAction.verifyErrorMessageonAlert(getData(
	 * "ISAConfigurationTab.ErrorMsg_BlankmaxShelvesNumber"));
	 * test.storageAreaAction.verifyTabIsDisabled("Display Settings");
	 * test.storageAreaAction.enterDataInInputField("maxShelvesNumber",
	 * getData("ISAConfigurationTab.maxShelvesNumber"));
	 * 
	 * test.storageAreaAction.enterDataInInputField("shelfWidthValue",
	 * getData("ISAConfigurationTab.shelfWidthValue"));
	 * test.storageAreaAction.verifyFieldIsMandatory("shelfWidthValue");
	 * test.storageAreaAction.clearInputBox("shelfWidthValue");
	 * test.storageAreaAction.verifyErrorMessageonAlert(getData(
	 * "ISAConfigurationTab.ErrorMsg_BlanshelfWidthValue"));
	 * test.storageAreaAction.verifyTabIsDisabled("Display Settings");
	 * test.storageAreaAction.enterDataInInputField("shelfWidthValue",
	 * getData("ISAConfigurationTab.shelfWidthValue"));
	 * 
	 * test.storageAreaAction.enterDataInInputField("maxBinNumber",
	 * getData("ISAConfigurationTab.maxBinNumber"));
	 * test.storageAreaAction.verifyFieldIsMandatory("maxBinNumber");
	 * test.storageAreaAction.clearInputBox("maxBinNumber");
	 * test.storageAreaAction.verifyErrorMessageonAlert(getData(
	 * "ISAConfigurationTab.ErrorMsg_BlankmaxBinNumber"));
	 * test.storageAreaAction.verifyTabIsDisabled("Display Settings");
	 * test.storageAreaAction.enterDataInInputField("maxBinNumber",
	 * getData("ISAConfigurationTab.maxBinNumber"));
	 * 
	 * test.storageAreaAction.enterDataInInputField("defaultBinWidthValue",
	 * getData("ISAConfigurationTab.defaultBinWidthValue"));
	 * test.storageAreaAction.verifyFieldIsMandatory("defaultBinWidthValue");
	 * test.storageAreaAction.clearInputBox("defaultBinWidthValue");
	 * test.storageAreaAction.verifyErrorMessageonAlert(getData(
	 * "ISAConfigurationTab.ErrorMsg_BlankdefaultBinWidthValue"));
	 * test.storageAreaAction.verifyTabIsDisabled("Display Settings");
	 * test.storageAreaAction.enterDataInInputField("defaultBinWidthValue",
	 * getData("ISAConfigurationTab.defaultBinWidthValue"));
	 * 
	 * test.storageAreaAction.enterDataInInputField(
	 * "defaultBinDividersHorizontalNumber",
	 * getData("ISAConfigurationTab.defaultBinDividersHorizontalNumber"));
	 * test.storageAreaAction.verifyFieldIsMandatory(
	 * "defaultBinDividersHorizontalNumber");
	 * test.storageAreaAction.clearInputBox("defaultBinDividersHorizontalNumber");
	 * test.storageAreaAction .verifyErrorMessageonAlert(getData(
	 * "ISAConfigurationTab.ErrorMsg_BlankdefaultBinDividersHorizontalNumber"));
	 * test.storageAreaAction.verifyTabIsDisabled("Display Settings");
	 * test.storageAreaAction.enterDataInInputField(
	 * "defaultBinDividersHorizontalNumber",
	 * getData("ISAConfigurationTab.defaultBinDividersHorizontalNumber"));
	 * 
	 * test.storageAreaAction.enterDataInInputField(
	 * "defaultBinDividersVerticalNumber",
	 * getData("ISAConfigurationTab.defaultBinDividersVerticalNumber"));
	 * test.storageAreaAction.verifyFieldIsMandatory(
	 * "defaultBinDividersVerticalNumber");
	 * test.storageAreaAction.clearInputBox("defaultBinDividersVerticalNumber");
	 * test.storageAreaAction .verifyErrorMessageonAlert(getData(
	 * "ISAConfigurationTab.ErrorMsg_BlankdefaultBinDividersVerticalNumber"));
	 * test.storageAreaAction.verifyTabIsDisabled("Display Settings");
	 * test.storageAreaAction.enterDataInInputField(
	 * "defaultBinDividersVerticalNumber",
	 * getData("ISAConfigurationTab.defaultBinDividersVerticalNumber"));
	 * 
	 * }
	 */

}
