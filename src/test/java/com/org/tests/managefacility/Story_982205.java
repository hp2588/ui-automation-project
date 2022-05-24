package com.org.tests.managefacility;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982205 extends BaseTest {

	String facilityName,printerdropdownName,external_System,rxLicenseId,faxNumber,serverPrinterName,printerName;
	
	@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
				getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"), 
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
	}
	
	
	@Test(priority = 1, description = "VPLX:Specific facility Settings:[UI]:To verify that the "
			+ "User is able to see Active printers mapped to Facility in dropdown "
			+ "under Settings tab while editing on Edit Facility screen.")
	public void Test01_1047793(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User is able to see printers dropdown under Settings tab while editing on Edit Facility screen");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("pharmacyInformationSystemKey", 2);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		rxLicenseId = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		// needed in next story - 06
		TestDataPropertyReaderAndWriter.setProperty("FacilityName", facilityName);
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.hardWaitForChromeBrowser(6);
		test.siteConfigurationAction.selectValueFromDropDown("defaultFacilityKey",facilityName);
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		serverPrinterName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", "Automation-UI-ServerPrinter" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");
		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				"Automation-UI-Printer" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("printerModelKey");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("printerModelKey", 1);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort",
				getData("PrinterDetails.PortNumber"));

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaWidth",
				getData("PrinterDetails.PaperWidth"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaHeight",
				getData("PrinterDetails.PaperHeight"));
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickRecordNameToEdit(facilityName);
		
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.verifyDropDownFieldOnEditRestockPrinterPopup("manualRestockPrinterKey");
		test.siteConfigurationAction.verifyDropDownFieldOnEditRestockPrinterPopup("receivingPrinterKey");
		test.siteConfigurationAction.verifyDropDownFieldOnEditRestockPrinterPopup("exceptionPrinterKey");
		test.siteConfigurationAction.verifyDropDownFieldOnEditRestockPrinterPopup("binLabelPrinterKey");	
	}
	
	
	@Test(priority = 2, description = "VPLX: Specific facility Settings:[UI]: To verify that "
			+ "the Placeholders values of printers under settings tab is valid on Edit Facility screen.")
	public void Test02_1048035 (Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific facility Settings:[UI]:Placeholders values of printers under settings tab with visual design while editing on Edit Facility screen");
		
		printerdropdownName=test.siteConfigurationAction.getPrinterValue("manualRestockPrinterKey");
		System.out.println("Default value in printer dropdown :" + printerdropdownName);
		test.supportDataActions.verifyDefaultValueInPrinterDropDownOnfacilitySettingsTab(
				"manualRestockPrinterKey", printerdropdownName);
	}
	
	
	@Test(priority = 3, description = "VPLX:Specific facility Settings:[UI]: To verify that the "
			+ "Restock/Return printer drop-down is displayed under Settings tab while editing Facility.")
	public void Test03_1048017(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the Restock/Return printer dropdown under Settings tab while editing on Edit Facility screen");
		
		test.siteConfigurationAction.verifydropdownsNotMandatory("manualRestockPrinterKey");
		test.siteConfigurationAction.selectValueForDropDown("manualRestockPrinterKey", printerName);
	}
	
	
	@Test(priority = 4, description = "VPLX:Specific facility Settings:[UI]: To verify that "
			+ "the Receiving printer drop-down is displayed under Settings tab while editing Facility")
	public void Test04_1048028(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the Receving printer dropdown under Settings tab while editing on Edit Facility screen");
		
		test.siteConfigurationAction.verifydropdownsNotMandatory("receivingPrinterKey");
		test.siteConfigurationAction.selectValueForDropDown("receivingPrinterKey", printerName);
	}
	
	
	@Test(priority = 5, description = "VPLX:Specific facility Settings:[UI]: To verify that "
			+ "the Exception printer drop-down is displayed under Settings tab while editing Facility.")
	public void Test05_1048030(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the Exception printer dropdown under Settings tab while editing on Edit Facility screen");
		
		test.siteConfigurationAction.verifydropdownsNotMandatory("exceptionPrinterKey");
		test.siteConfigurationAction.selectValueForDropDown("exceptionPrinterKey", printerName);
	}
	
	
	@Test(priority = 6, description = "VPLX: Specific facility Settings:[UI]: To verify that "
			+ "the Bin Label printer drop-down is displayed under Settings tab while editing Facility.")
	public void Test06_1048031(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific facility Settings:[UI]:User verifies the Bin Label printer dropdown under Settings tab while editing on Edit Facility screen");
		
		test.siteConfigurationAction.verifydropdownsNotMandatory("binLabelPrinterKey");
		test.siteConfigurationAction.selectValueForDropDown("binLabelPrinterKey", printerName);
	}
	
}
