package com.org.tests.itemsmanagement;

import static com.org.automation.utils.YamlReader.getData;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.tests.tq.batchpicks.BatchPickAPIModel;
import com.org.tests.tq.batchpicks.OrderComponent;
import com.org.tests.tq.batchpicks.PatientInformation;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Story_975825_IntegrationTests extends BaseTest {

	String priorityName, code, External = TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim(),
			itemName, itemID, brandName, randomUUIDString, patientName, first_Name,
			facilityOnWFAScreen = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();

	int pickQuantity;

	public void postBatchPickTransaction() throws JSONException {
		String batchPickJsonFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator + "BatchPick.json";
		BatchPickAPIModel batchPick = new BatchPickAPIModel();
		String requestBody = new String();

		UUID uuid = UUID.randomUUID();
		randomUUIDString = uuid.toString();

		try {
			System.out.println("Going to hit  : ");
			ObjectMapper mapper = new ObjectMapper();
			batchPick = mapper.readValue(new File(batchPickJsonFilePath), BatchPickAPIModel.class);
			batchPick.getData().setFacilityCode(TestDataPropertyReaderAndWriter.getProperty("FacilityCode"));
			batchPick.getData()
					.setExternalSystemName(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
			batchPick.getData().setTransactionPriorityCode(TestDataPropertyReaderAndWriter.getProperty("PriorityCode"));
			batchPick.getData()
					.setDeliveryDestinationName(TestDataPropertyReaderAndWriter.getProperty("DestinationCode"));
			batchPick.getHeader().setFacilityCode(TestDataPropertyReaderAndWriter.getProperty("FacilityCode"));
			batchPick.getHeader()
					.setExternalSystemName(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
			batchPick.getHeader().setTenantKey(getData("IDM.tenantKey"));
			batchPick.getHeader().setRequestID(randomUUIDString);
			setOrderComponentProperties(batchPick);
			setPatientInformation(batchPick);
			first_Name = getFirstName(batchPick);

			requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(batchPick);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		RestAssured.baseURI = getData("IDM.cce_proxy_url");
		RestAssured.basePath = getData("IDM.cce_proxy_path");

		Response response = given().auth().oauth2(TestDataPropertyReaderAndWriter.getProperty("opaque_access_token"))
				.contentType("application/json\r\n").body(requestBody).log().all().post();

		if (response.getStatusCode() == 200) {
			System.out.println("Batch data is created successfully");
		} else if (response.getStatusCode() == 401) {
			System.out.println("Expired or Invalid access token");
		} else if (response.getStatusCode() == 403) {
			System.out.println("Access Forbidden!!! Please use valid access token or check endpoint");
		} else if (response.getStatusCode() == 400) {
			System.out.println("One or more validation errors occurred.");
		}
		System.out.println("Response received for  : ");
		response.prettyPrint();
	}

	/*
	 * This is a helper method which is used to set Order Component properties
	 * in BatchPickAPIModel.
	 */

	private void setOrderComponentProperties(BatchPickAPIModel batchPick) {
		Iterator<OrderComponent> orderComponetIter = batchPick.getData().getOrderComponents().iterator();
		List<String> itemIds = Arrays.asList("ItemID", "ItemID1", "ItemID2", "ItemID3");
		Iterator<String> itemIdsItr = itemIds.iterator();
		List<String> itemNames = Arrays.asList("ItemName", "ItemName1", "ItemName2", "ItemName3");
		Iterator<String> itemNamesItr = itemNames.iterator();
		while (orderComponetIter.hasNext() && itemIdsItr.hasNext() && itemNamesItr.hasNext()) {
			OrderComponent oc = orderComponetIter.next();
			oc.setItemID(TestDataPropertyReaderAndWriter.getProperty(itemIdsItr.next()));
			oc.setItemName(TestDataPropertyReaderAndWriter.getProperty(itemNamesItr.next()));
			oc.setPickQuantity(10);
			pickQuantity = oc.getPickQuantity();
		}
	}

	private void setPatientInformation(BatchPickAPIModel batchPick) {
		String randomName=test.transactionQueueActions.getAlphaNumericString(4);
		batchPick.getData().getPatientInformation().setFirstName("UI_"+randomName);

	}

	private String getFirstName(BatchPickAPIModel batchPick) {
		String PatientfirstName = batchPick.getData().getPatientInformation().getFirstName();
		System.out.println("First Name= " + PatientfirstName);
		return PatientfirstName;

	}

	@Test(priority = 1, description = "VPLX: Item Setup (System and Facility): [UI]  : When 'ADC Quantity Rounding' Flag"
			+ " is set to False and ISADC Flag=False,The Quantity of transaction does not get rounded off when an incoming request is received for same item and priority")

	public void Test01_1138238() throws InterruptedException {

		/* ============Add Priority with ADC=UnChecked====================== */
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name@#" + System.currentTimeMillis());

		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);

		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		// test.siteConfigurationAction.selectPriorityColor("#F44E3B");
		Assert.assertTrue(test.siteConfigurationAction.checkCheckboxOfSystemFlagNewRecordTP());
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		/*
		 * test.siteConfigurationAction.
		 * clickCheckBoxOfNewlyCreatedTransactionPriority("adcFlag");
		 * Assert.assertTrue(test.siteConfigurationAction.
		 * verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("adcFlag"),
		 * "[Assertion Failed]: CheckBox Is ADC is Unchecked");
		 */
		// test.siteConfigurationAction.selectDropdownDispenseExternal("PatientName",
		// "groupBy");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

		/*
		 * ==================Navigate to Item Management======================
		 */

		test.landingPageActions.navigateToFeature("Item Management");

		test.siteConfigurationAction.enterRandomValueInRichInputField(External);
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		itemName = test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());

		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand100");

		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(facilityOnWFAScreen);

		test.supportDataActions.clickButtonWithOutAnyWait("save");

		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

		/*
		 * test.landingPageActions.navigateToFeature("Item Management");
		 * 
		 * test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList
		 * (itemID);
		 * 
		 * test.supportDataActions.enterSearchTermInSearchFieldGl(itemID,
		 * "search");
		 * test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		 * 
		 * test.siteConfigurationAction.verifyAndClickItemFacility(
		 * facilityOnWFAScreen);
		 */

		/*
		 * test.siteConfigurationAction.clickCheckboxTransactionPriorities(
		 * "admQuantityRoundingFlag");
		 */

		/*
		 * test.siteConfigurationAction.
		 * clickCheckBoxOfNewlyCreatedTransactionPriority("adcFlag");
		 * Assert.assertTrue(test.siteConfigurationAction.
		 * verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("adcFlag"),
		 * "[Assertion Failed]: CheckBox Is ADC is Unchecked");
		 */
		postBatchPickTransaction();
		System.out.println("Going to sleep for 2 min 10 sec");
		Thread.sleep(130000);

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		Assert.assertEquals(test.transactionQueueActions.verifyQuantityOfNewlyCreatedTransaction(first_Name),
				pickQuantity);
	}

	@Test(priority = 2, description = "VPLX: Item Setup (System and Facility): [UI]  : When 'ADC Quantity Rounding' Flag is set to True"
			+ " and ISADC Flag=False,The Quantity of transaction does not get rounded off when an incoming request is received"
			+ " for same item and priority")

	public void Test02_1138237() throws InterruptedException {
		
		/*========Check checkbox ADC Quantity Rounding for the Item===============*/

		test.landingPageActions.navigateToFeature("Item Management");

		test.siteConfigurationAction.enterRandomValueInRichInputField(External);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);

		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("admQuantityRoundingFlag");
		test.siteConfigurationAction.clickSaveButton();

		postBatchPickTransaction();
		System.out.println("Going to sleep for 2 min 10 sec");
		Thread.sleep(130000);

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		Assert.assertEquals(test.transactionQueueActions.verifyQuantityOfNewlyCreatedTransaction(first_Name),
				pickQuantity);

	}

	@Test(priority = 3, description = "VPLX: Item Setup (System and Facility): [UI]  : When ADC Quantity Rounding flag at Item=False,"
			+ " Destination=False and Facility=False,"
			+ " the incoming request Quantity does not get rounded off for a priority whose ISADC=True")

	public void Test03_1138236() throws InterruptedException {
		/*
		 * ===================Check ISADC from
		 * Priorities===========================
		 */
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority(priorityName);
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("adcFlag");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("adcFlag"),
				"[Assertion Failed]: CheckBox Is ADC is Unchecked");

		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		
		/*======Edit Facility for ADC=False===========*/
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
        
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityOnWFAScreen, "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.uncheckCheckBox("admQuantityRoundingFlag");
		test.siteConfigurationAction.clickSaveButton();
		
		/*===========Uncheck ADC Rounding checkbox================*/

		test.landingPageActions.navigateToFeature("Item Management");

		test.siteConfigurationAction.enterRandomValueInRichInputField(External);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);

		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.uncheckCheckBox("admQuantityRoundingFlag");
		test.siteConfigurationAction.clickSaveButton();

		/*======Edit Destination for ADC=False===========*/
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.supportDataActions.verifyLabelIsPresent("Destinations");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		Assert.assertTrue(test.siteConfigurationAction.checkCheckBoxDestination("admQuantiyRoundingFlag"));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clickButton("primary");

		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());

		postBatchPickTransaction();
		System.out.println("Going to sleep for 2 min 10 sec");
		Thread.sleep(130000);

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		Assert.assertEquals(test.transactionQueueActions.verifyQuantityOfNewlyCreatedTransaction(first_Name),
				pickQuantity);
		
		
	}
	
	@Test(priority = 4, description = "VPLX: Item Setup (System and Facility): [UI]  : When ADC Quantity Rounding flag is enabled at Item,"
			+ " Destination and Facility level,"
			+ " the incoming request Quantity gets rounded off for a priority whose ISADC=True")

	public void Test04_1138235() throws InterruptedException {
		
		/*======Edit Facility for ADC=True===========*/
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
        
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityOnWFAScreen, "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("admQuantityRoundingFlag");

		//test.siteConfigurationAction.uncheckCheckBox("admQuantityRoundingFlag");
		
		test.siteConfigurationAction.clickSaveButton();
		
		/*===========Check ADC Rounding checkbox of the Item================*/

		test.landingPageActions.navigateToFeature("Item Management");

		test.siteConfigurationAction.enterRandomValueInRichInputField(External);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);

		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("admQuantityRoundingFlag");
		test.siteConfigurationAction.clickSaveButton();

		/*======Edit Destination for ADC=True===========*/
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.supportDataActions.verifyLabelIsPresent("Destinations");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("admQuantityRoundingFlag");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clickButton("primary");

		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());

		postBatchPickTransaction();
		System.out.println("Going to sleep for 2 min 10 sec");
		Thread.sleep(130000);

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		Assert.assertEquals(test.transactionQueueActions.verifyQuantityOfNewlyCreatedTransaction(first_Name),
				pickQuantity);

	}
	
	@Test(priority = 5, description = "VPLX: Item Setup (System and Facility): [UI] - If 'Ignore ADC Critical Low' flag is disabled for an item and ISADC=False for PYXISCRITLOW, any incoming request for 'CRITLOW' priority will not be ignored for that item")

	public void Test05_1138234() throws InterruptedException {
		
		/*=====Uncheck ADC checkbox for priority="PYXISCRITLOW"===============================*/
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.clickEditBaseOnPriorityCode("PYXISCRITLOW");
		test.siteConfigurationAction.uncheckCheckBoxAsPerPriorityCode("PYXISCRITLOW");
		test.supportDataActions.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", "PYXISCRITLOW");
		postBatchPickTransaction();
		System.out.println("Going to sleep for 2 min 10 sec");
		Thread.sleep(130000);
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions.verifyTransactionWithPriority("PYXISCRITLOW", first_Name);	
		
	}
	
	@Test(priority = 6, description = "VPLX: Item Setup (System and Facility): [UI] - If 'Ignore ADC Critical Low' flag is disabled for an item and ISADC=False for PYXISCRITLOW, any incoming request for 'CRITLOW' priority will not be ignored for that item")

	public void Test06_1138233() throws InterruptedException {
		
		/*===========Check ADC Critical Low checkbox of the Facility================*/
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityOnWFAScreen, "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("admIgnoreCriticalLowFlag");
		//test.siteConfigurationAction.uncheckCheckBox("admQuantityRoundingFlag");
		
		test.siteConfigurationAction.clickSaveButton();
		
		/*===========Check ADC Critical Low checkbox of the Item================*/

		test.landingPageActions.navigateToFeature("Item Management");

		test.siteConfigurationAction.enterRandomValueInRichInputField(External);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);

		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("admIgnoreCriticalLowFlag");
		test.siteConfigurationAction.clickSaveButton();

		postBatchPickTransaction();
		System.out.println("Going to sleep for 2 min 10 sec");
		Thread.sleep(130000);
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions.verifyTransactionWithPriority("PYXISCRITLOW", first_Name);	


	}

	@Test(priority = 7, description = "VPLX: Item Setup (System and Facility): [UI] -When ADC Critlow flag is enabled at Item, Destination and Facility level,"
			+ " the incoming request for gets ignored for Critlow priority having ISADC Flag=True")

	public void Test07_1138231() throws InterruptedException {
	
	/*======Edit Destination for ADC Critlow Flag=True===========*/
	
	test.landingPageActions.navigateToMenu("Main Menu");
	test.landingPageActions.navigateToFeature("Destinations");
	test.supportDataActions.verifyLabelIsPresent("Destinations");
	test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
	test.siteConfigurationAction.clickCheckboxTransactionPriorities("admIgnoreCriticalLowFlag");
	test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

	test.supportDataActions.clickButton("cancel");
	test.supportDataActions.clickButton("primary");

	test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());

	postBatchPickTransaction();
	System.out.println("Going to sleep for 2 min 10 sec");
	Thread.sleep(130000);
	test.landingPageActions.navigateToMenu("Transaction Queue");
	test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

	test.transactionQueueActions
			.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());

	Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
			"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

	Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
	test.storageAreaAction.verifyStartWorkButtonAndClick();
	test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	
	Assert.assertFalse(test.siteConfigurationAction.verifyItemIsMappedForRO(first_Name));
		
	}
	
	@Test(priority = 8, description = "VPLX: Item Setup (System and Facility): [UI] - When ADC critlow flag is disabled at Item,"
			+ " Destination and Facility level, the incoming request does not get ignored for Critlow priority having ISADC Flag=True")

	public void Test08_1138231() throws InterruptedException {
		
/*===========UnCheck ADC Critical Low checkbox of the Facility================*/
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityOnWFAScreen, "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		
		test.siteConfigurationAction.uncheckCheckBox("admIgnoreCriticalLowFlag");
		/*test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("admIgnoreCriticalLowFlag");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("admIgnoreCriticalLowFlag"),
				"[Assertion Failed]: CheckBox ADC Quantity Rounding Off is Unchecked");*/

		//test.siteConfigurationAction.uncheckCheckBox("admQuantityRoundingFlag");
		
		test.siteConfigurationAction.clickSaveButton();
		
		/*======Edit Destination for ADC Critlow Flag=False===========*/
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.supportDataActions.verifyLabelIsPresent("Destinations");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.siteConfigurationAction.uncheckCheckBox("admIgnoreCriticalLowFlag");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clickButton("primary");

		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());

		/*===========UnCheck ADC Critical Low checkbox of the Item================*/

		test.landingPageActions.navigateToFeature("Item Management");

		test.siteConfigurationAction.enterRandomValueInRichInputField(External);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);

		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.uncheckCheckBox("admIgnoreCriticalLowFlag");
		test.siteConfigurationAction.clickSaveButton();

		postBatchPickTransaction();
		System.out.println("Going to sleep for 2 min 10 sec");
		Thread.sleep(130000);
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions.verifyTransactionWithPriority("PYXISCRITLOW", first_Name);	
	}
	
	@Test(priority = 9, description = "VPLX: Item Setup (System and Facility): [UI] - If 'Ignore ADC Stockout' flag is disabled"
			+ " for an item,Facility,Destination level and ISADC=False for PYXISSTOCKOUT , any incoming request for 'Stockout' priority will not be ")

	public void Test09_1138230() throws InterruptedException {
		
		/*Considering the fact 'Ignore ADC Stockout' flag  is disabled by Default for Item,Facility,Destination and ISADC=False by default for Priority PYXISSTOCKOUT*/
		
		/*Writing Priority=PYXISSTOCKOUT in TestData.properties*/
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", "PYXISSTOCKOUT");
		
		postBatchPickTransaction();
		System.out.println("Going to sleep for 2 min 10 sec");
		Thread.sleep(130000);
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions.verifyTransactionWithPriority("PYXISSTOCKOUT", first_Name);	
		
		
	}
	
	
	@Test(priority = 10, description = "VPLX: Item Setup (System and Facility): [UI] - When ADC Stockout flag"
			+ " at Item=False, Destination=False and Facility=False, the incoming request does not get ignored "
			+ "for Stockout priority having ISADC Flag=True")

	public void Test10_1138228() throws InterruptedException {
		
		/*---Check checkbox ADC=tRUE FOR PYXISSTOCKOUT */
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.verifyAndClickEditLinkTransactionPriority(priorityName);
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("adcFlag");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyCheckboxOfNewlyCreatedTransactionPriorityIsChecked("adcFlag"),
				"[Assertion Failed]: CheckBox Is ADC is Unchecked");

		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		
		postBatchPickTransaction();
		System.out.println("Going to sleep for 2 min 10 sec");
		Thread.sleep(130000);
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions.verifyTransactionWithPriority("PYXISSTOCKOUT", first_Name);	
		
		
	}
	
	
	@Test(priority = 11, description = "VPLX: Item Setup (System and Facility): [UI] - If 'Ignore ADC Stockout' flag is enabled"
			+ " for an item, Facility, Destination and ISADC=True for PYXISSTOCKOUT , any incoming request for 'Stockout' priority will not be ")

	public void Test11_1138227() throws InterruptedException {
		
		
		
/*===========Check Ignore ADC StockOut checkbox of the Facility================*/
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityOnWFAScreen, "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("admIgnoreStockOutFlag");
		//test.siteConfigurationAction.uncheckCheckBox("admQuantityRoundingFlag");
		
		test.siteConfigurationAction.clickSaveButton();
		
		/*===========Check ADC Critical Low checkbox of the Item================*/

		test.landingPageActions.navigateToFeature("Item Management");

		test.siteConfigurationAction.enterRandomValueInRichInputField(External);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);

		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("admIgnoreStockOutFlag");
		test.siteConfigurationAction.clickSaveButton();

		
		/*===========Check Ignore ADC StockOut Flag checkbox of the Item================*/

		test.landingPageActions.navigateToFeature("Item Management");

		test.siteConfigurationAction.enterRandomValueInRichInputField(External);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);

		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("admIgnoreStockOutFlag");
		test.siteConfigurationAction.clickSaveButton();

/*======Edit Destination for Ignore ADC StockOut=True===========*/
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.supportDataActions.verifyLabelIsPresent("Destinations");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("admIgnoreStockOutFlag");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clickButton("primary");

		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());

		postBatchPickTransaction();
		System.out.println("Going to sleep for 2 min 10 sec");
		Thread.sleep(130000);
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		Assert.assertFalse(test.siteConfigurationAction.verifyItemIsMappedForRO(first_Name));
		
	}
	
	@Test(priority = 12, description = "VPLX: Item Setup (System and Facility): [UI] - If 'Ignore ADC Stockout' flag at"
			+ " Item=True, Destination=False , Facility=False, ISADC=True for PYXISSTOCKOUT ,"
			+ " any incoming request for 'Stockout' priority will  be ignored for that item")

	public void Test12_1138229() throws InterruptedException {
/*===========UnCheck Ignore ADC StockOut checkbox of the Facility================*/
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityOnWFAScreen, "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		
		test.siteConfigurationAction.uncheckCheckBox("admIgnoreStockOutFlag");
		
		test.siteConfigurationAction.clickSaveButton();
		
/*======Edit Destination for Ignore ADC StockOut=False===========*/
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.supportDataActions.verifyLabelIsPresent("Destinations");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.siteConfigurationAction.uncheckCheckBox("admIgnoreStockOutFlag");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clickButton("primary");

		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());

		postBatchPickTransaction();
		System.out.println("Going to sleep for 2 min 10 sec");
		Thread.sleep(130000);
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());

		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		Assert.assertFalse(test.siteConfigurationAction.verifyItemIsMappedForRO(first_Name));
		
	}
	
	/*@Test(priority = 13, description = "VPLX:[Integration]:Item Setup (System and Facility): [UI]: Cycle count interval days are created"
			+ " after given interval of time for the approved item.")

	public void Test13_1117820() throws InterruptedException {
		
		
	}*/
	
	
	
}
