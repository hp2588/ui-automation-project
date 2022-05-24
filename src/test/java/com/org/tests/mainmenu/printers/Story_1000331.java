package com.org.tests.mainmenu.printers;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1000331 extends BaseTest {

	String printerName;
	String scheduleName;

	@Test(priority = 1, description = "VPLX:Manage Printers:User verifies the message on Add schedule page on save")
	public void Test01_1004979(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the message on Add schedule page on save");
		test.landingPageActions.navigateToFeature("Schedules");
		test.siteConfigurationAction.clickOnAddButtonToAddSchedulePick();
		test.siteConfigurationAction.clickToSetDays();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("startTime");
		test.siteConfigurationAction.selectValueForDropDown("startTime", getData("SchedulePicksDetails.StartHour"));
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("scheduleName");

		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());

		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
	}

	@Test(priority = 2, description = "VPLX:Manage Printers:User verifies the message on Edit schedule page on save")
	public void Test02_1004995(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the message on Edit schedule page on save");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToScheduleName(scheduleName);
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("scheduleName");
		scheduleName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("scheduleName",
				getData("SchedulePicksDetails.ScheduleName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(scheduleName);
	}

	@Test(priority = 3, description = "VPLX:Manage Printers:User verifies the message on Delete schedule page on deletion of any schedule")
	public void Test03_1005133(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the message on Delete schedule page on deletion of any schedule");
		test.siteConfigurationAction.clickOnDeleteLinkCorresspondingToScheduleName(scheduleName);
		test.siteConfigurationAction.confirmDeletePopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader("The schedule " + scheduleName + " has been deleted successfully");
		/*test.siteConfigurationAction.verifySuccessMessageOnDeleteSchedule(
				"The schedule " + scheduleName + " has been deleted successfully");*/
		test.siteConfigurationAction.verifyDeletedScheduleInScheduleList(scheduleName);
	}

	@Test(priority = 4, description = "VPLX:Manage Printers:User verifies the message on Add printer page on save")
	public void Test04_1004855_And_1129271(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the message on Add printer page on save");
		/*
		 * test.siteConfigurationAction.verifySiteConfigurationOptions(getData(
		 * "SiteConfigurationList.Item6"));
		 */
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		String serverPrinterName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", getData("PrinterDetails.ServerPrinterName") + System.currentTimeMillis());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");

		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);

		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				getData("PrinterDetails.PrinterName") + System.currentTimeMillis());

		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("printerModelKey");
		test.siteConfigurationAction.selectValueForDropDown("printerModelKey", getData("PrinterDetails.Model"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				getData("PrinterDetails.IPaddress"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort",
				getData("PrinterDetails.PortNumber"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("labelBarcode",
				DateUtil.getAlphaNumericString(1000));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaWidth",
				getData("PrinterDetails.PaperWidth"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaHeight",
				getData("PrinterDetails.PaperHeight"));
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName);
	}

	@Test(priority = 5, description = "VPLX:Manage Printers:User verifies the message on Edit printer page on save")
	public void Test05_1004958(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Printers:User verifies the message on Edit printer page on save");

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToPrinterName(printerName);
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");
		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				getData("PrinterDetails.PrinterName") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(printerName);

	}
}
