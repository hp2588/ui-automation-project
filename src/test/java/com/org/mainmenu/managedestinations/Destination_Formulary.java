package com.org.mainmenu.managedestinations;

import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Destination_Formulary extends BaseTest  {

	List<String> actualUserNames;
	List<String> expectedUserNames;
	String facilitydropdown,searched_item,item_detail,facility,destination_name1,ItemName,message;
	String destinationName, facility_Name, destinationCode,accountNo,DistributorAccount_old,DistributorAccount_new,accountNo_new;;
	
	
@Test(priority = 1, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify note is visible only when either one or  both  of the checkboxes 'Enable Split Orders' or 'Enable Receive-N-Send' is checked")
	public void Test01_Test02_Test03_1055240_1054649_1061962(Method method) {
		ExtentTestManager.startTest(method.getName(),	
				"Verify note is visible only when either one or  both  of the checkboxes 'Enable Split Orders' or 'Enable Receive-N-Send' is checked");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");		
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
		test.siteConfigurationAction.clickEditLink(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.clickButton("add");
		ItemName = TestDataPropertyReaderAndWriter.getProperty("ItemName");
		System.out.println(ItemName);
		test.siteConfigurationAction.enterItemNameForDestinationItem(ItemName);
		//test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(ItemName);
		test.siteConfigurationAction.clickCheckbox("activeFlag-0");
		test.siteConfigurationAction.clickActionbutton("Save & Close");
		//test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		String Maxdefault = test.siteConfigurationAction.verifyDefaultInputField("maximumOrderQuantity-0");
		System.out.println(Maxdefault.toString());
		boolean status = test.siteConfigurationAction.isButtonDisplayedUsingId("save");
		Assert.assertTrue(status, "Element is disabled");
		test.siteConfigurationAction.clickCheckbox("limitedOrderQuantity-0");
		boolean disabledstatus = test.siteConfigurationAction.isButtonDisplayedUsingId("save");
		Assert.assertTrue(disabledstatus, "Element is enabled");		
		test.siteConfigurationAction.enterRandomValueInInputField("maximumOrderQuantity-0", getData("RemoteWebOrder.MaxDailyQuantity"));
		//test.siteConfigurationAction.clickCheckbox("enableReceiveNSend");
		
	}

@Test(priority = 2, description = "VPLX: Manage Destinations - Formulary Items : [UI] verify that Package size field values is enabled after clicking on the one of the check box \"Enable Split Orders\" or \"Enable Receive-N-Send\"")
public void Test04_Test05_Test06_1054828_1054823_1054841(Method method) {
	ExtentTestManager.startTest(method.getName(),
			"VPLX: Manage Destinations - Formulary Items : [UI] verify that Package size field values is enabled after clicking on the one of the check box \"Enable Split Orders\" or \"Enable Receive-N-Send\"");
	test.siteConfigurationAction.packagesizeIsDisabled();
	test.siteConfigurationAction.clickCheckbox("enableReceiveNSend");
	test.siteConfigurationAction.packagesizeIsenabled();
}

@Test(priority = 3, description = "VPLX: Manage Destinations - Formulary Items : [UI] Verify user can add the item via clicking on the Add button")
public void Test07_Test08_Test09_1135074_1054718_1054685(Method method) {
	ExtentTestManager.startTest(method.getName(),
			"VPLX: Manage Destinations - Formulary Items : [UI] Verify user can add the item via clicking on the Add button");
	test.siteConfigurationAction.ClearInputField("maximumOrderQuantity-0");
	//test.siteConfigurationAction.enterRandomValueInInputField("maximumOrderQuantity-0", getData("RemoteWebOrder.MaxQuantity"));
	Assert.assertEquals(test.siteConfigurationAction.verifyMaxDailyQuantity(), 5,"Incorrect max length for MaxDailyQuantity");
	
	
}

@Test(priority = 4, description = "VPLX: Manage Destinations - Formulary Items : [UI] VPLX: Manage Destinations - Formulary Items : [UI] To verify that the fields 'Item, Item ID, NDC, Limit Order Quantity, Max Daily Quantity (each), Package Size and Action display under item tab.")
public void Test10_1054435(Method method) {
	ExtentTestManager.startTest(method.getName(),
			"VPLX: Manage Destinations - Formulary Items : [UI] VPLX: Manage Destinations - Formulary Items : [UI] To verify that the fields 'Item, Item ID, NDC, Limit Order Quantity, Max Daily Quantity (each), Package Size and Action display under item tab.");
	test.siteConfigurationAction.verifyInputFormularyField(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
}

@Test(priority = 5, description = "VPLX: Manage Destinations - Formulary Items : [UI] VPLX: Manage Destinations - Formulary Items : [UI] To verify that Items get removed after clicking on the Remove button for the respective Formulary.")
public void Test11_1055232(Method method) {
	ExtentTestManager.startTest(method.getName(),
			"VPLX: Manage Destinations - Formulary Items : [UI] VPLX: Manage Destinations - Formulary Items : [UI] To verify that Items get removed after clicking on the Remove button for the respective Formulary.");
	test.siteConfigurationAction.ClickremoveButton("remove");
}


@Test(priority = 6, description = "VPLX: Manage Destinations - Formulary Items : [UI] VPLX: Manage Destinations - Formulary Items : [UI] To verify that Control remain on the search window after clicking on the Save and More button.")
public void Test012_Test13_1055228_1055229(Method method) {
	ExtentTestManager.startTest(method.getName(),
			"VPLX: Manage Destinations - Formulary Items : [UI] VPLX: Manage Destinations - Formulary Items : [UI] To verify that Control remain on the search window after clicking on the Save and More button.");
	test.siteConfigurationAction.clickButton("add");
	ItemName = TestDataPropertyReaderAndWriter.getProperty("ItemName");	
	test.siteConfigurationAction.enterItemNameForDestinationItem(ItemName);
	//test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(ItemName);
	test.siteConfigurationAction.clickCheckbox("activeFlag-0");
	test.siteConfigurationAction.clickActionbutton("Save & Add More");	
	test.siteConfigurationAction.clickCancelButton("Cancel");	
	test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
}
}


