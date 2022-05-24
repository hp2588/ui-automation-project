package com.org.tests.manageisa;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_983724 extends BaseTest {

	String facility, facilityOnISA, name, shortName, defaultComputer, defaultPrinter, type, deviceNumber, ipAddress,
			portNumber, carouselConnectionResetTime;

	String[] columnHeaders = {"Name", "Facility", "Type", "Carousel", "Status"};
	
	@Test(priority = 1, description = "VPLX:Manage ISAs:[UI] -Listing view for ISAs is having name ,type and status columns.")
	public void Test01_1030687(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -Listing view for ISAs is having name ,type and status columns.");
		
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.siteConfigurationAction.verifyColumnHeader(columnHeaders);
	}

	@Test(priority = 3, description = " VPLX:Manage ISAs:[UI] User is able to view the list of ISAs corresponsing to a facility selected."
			+ ""
			+ "VPLX:Manage ISAs:[UI] -User is able to view the list of all ISAs corresponding to My Facility selected from dropdown.")
	public void Test02_Test03_1030678_1030681(Method method) throws InterruptedException {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Manage ISAs:[UI] User is able to view the list of ISAs corresponsing to a facility selected.");
		

		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("FacilityDropdown", 1);
		
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");

		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");

		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");

		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
		test.supportDataActions.verifyTabIsDisplayed("ISA Configuration");
		test.supportDataActions.verifyTabIsDisplayed("Display Settings");
		test.supportDataActions.verifyTabIsDisplayed("Approved Computers");

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");

		test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

		name = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());

		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("workstationComputerKey", 1);
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("logisticsLabelPrinterKey", 1);

		test.siteConfigurationAction.clickTab("Carousel Settings");
		Thread.sleep(3000);
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("carouselKey"),
				"[ASSERTION FAILED]: Type drop down is not mandatory");
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("carouselKey", 1);
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("devicenumber");
		deviceNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("devicenumber",
				getData("AddISA.Device"));

		ipAddress = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddressValue",
				getData("AddISA.IPAddress"));
		portNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("portNumber",
				getData("AddISA.Port"));
		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		test.storageAreaAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(name);
	}
	
	@Test(priority = 4, description = "VPLX:Manage ISAs:[UI] -User is able to perfrom a contains search on the name field of ISA.")
	public void Test04_1030711() {
	test.supportDataActions.enterSearchTermInSearchFieldGl(name, "search");
	Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(name, "1"));
	
	}
	
	@Test(priority = 5, description = "VPLX: Manage ISAs: [UI]: User is able to clear the search text entered in the text box.")
	public void Test05_1026671() {
		test.supportDataActions.clearSearchBoxField("search");
	}
	
	
	
	@Test(priority = 6, description = "VPLX:Manage ISAs:[UI] -User is able to view the facility name corresponding to ISAs when my facility is selected.")
	public void Test06_1030754(Method method) {
		test.supportDataActions.codeListDosageForms("2");
	
	}
	

	@Test(priority = 7, description = "VPLX:Manage ISAs:[UI] -User is able to view all active ISAs by default when selecting a facility from drop down.")
	public void Test07_1030688(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is able to view all active ISAs by default when selecting a facility from drop down.");
		test.siteConfigurationAction.verifySearchResults("Active", "5");
	}

	@Test(priority = 8, description = "VPLX:Manage ISAs:[UI] -User is able to view all active and inactive ISAs for a facility when toggle button is used.")
	public void Test08_1030693(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is able to view all active and inactive ISAs for a facility when toggle button is used.");
		test.siteConfigurationAction.clickToggleButton("true", "toggle");
		//Assert.assertTrue(test.supportDataActions.verifyDosageFormsStatusAsInactive("5"));
	}

	@Test(priority = 9, description = "VPLX:Manage ISAs:[UI] -User is able to toggle and view all active ISAs for a facility when toggle button is used.")
	public void Test09_1030693(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage ISAs:[UI] -User is able to toggle and view all active ISAs for a facility when toggle button is used.");
		test.siteConfigurationAction.clickToggleButton("false", "toggle");
		//Assert.assertTrue(test.supportDataActions.verifyDosageFormsStatusAsActive("5"));
	}
}
