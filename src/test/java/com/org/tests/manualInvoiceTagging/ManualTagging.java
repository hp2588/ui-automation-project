	package com.org.tests.manualInvoiceTagging;

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
import com.org.automation.tokengenerator.OpaqueAccessToken;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.tests.manualInvoiceTagging.ManualTaggingAPIModel;
import com.org.tests.tq.batchpicks.BatchPickAPIModel;
import com.org.tests.tq.batchpicks.OrderComponent;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ManualTagging extends OpaqueAccessToken {
	
	public void postManualTaggingData() throws JSONException {
		String manualTaggingJsonFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator + "ManualTagging.json";
		ManualTaggingAPIModel manualTagging = new ManualTaggingAPIModel();
		String requestBody = new String();
		
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		String invoiceTypeCode = DateUtil.getAlphaNumericString(8);
		String invoiceID = DateUtil.getAlphaNumericString(9);
		String VendorItemCode;
		int shippedQuantity = Math.round(2);
		int packageSizeQuantity = Math.round(2);
		int itemCostAmount = Math.round(2);
		int orderedQuantity = Math.round(2);

		try {
			ObjectMapper mapper = new ObjectMapper();
			manualTagging = mapper.readValue(new File(manualTaggingJsonFilePath), ManualTaggingAPIModel.class);
			
			//setting data
			manualTagging.getData().setFacilityCode(TestDataPropertyReaderAndWriter.getProperty("FacilityCode"));
			manualTagging.getData().setPurchaseOrderID(TestDataPropertyReaderAndWriter.getProperty("purchaseOrderID"));
			manualTagging.getData().setVendorCode(TestDataPropertyReaderAndWriter.getProperty("DistributorCode_Electronic"));
			manualTagging.getData().setInvoiceDateTime("2021-04-01T00:00:00.00Z");
			manualTagging.getData().setInvoiceID(invoiceID);
			manualTagging.getData().setPurchaseOrderDate("2021-04-01T00:00:00.00Z");
			manualTagging.getData().setInvoiceTypeCode(invoiceTypeCode);
				
			//setting line items			
			manualTagging.getData().getInvoiceLineItems().get(0).setVendorItemCode(TestDataPropertyReaderAndWriter.getProperty("VIC"));
			manualTagging.getData().getInvoiceLineItems().get(0).setShippedQuantity(shippedQuantity);
			manualTagging.getData().getInvoiceLineItems().get(0).setProductID(TestDataPropertyReaderAndWriter.getProperty("ProductID_Electronic"));
			manualTagging.getData().getInvoiceLineItems().get(0).setItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName_Electronic")+" "+TestDataPropertyReaderAndWriter.getProperty("DosageFormCode"));
			manualTagging.getData().getInvoiceLineItems().get(0).setPackageSizeQuantity(packageSizeQuantity);
			manualTagging.getData().getInvoiceLineItems().get(0).setItemCostAmount(itemCostAmount);
			manualTagging.getData().getInvoiceLineItems().get(0).setOrderedQuantity(orderedQuantity);				
			
			//setting headers
			manualTagging.getHeader().setFacilityCode(TestDataPropertyReaderAndWriter.getProperty("FacilityCode"));
			manualTagging.getHeader().setTenantKey(getData("IDM.tenantKey"));
			manualTagging.getHeader().setRequestID(randomUUIDString);
			manualTagging.getHeader().setTimeStamp("s3");
			manualTagging.getHeader().setMessageType("Invoice");
			manualTagging.getHeader().setVersion("v2");
			manualTagging.getHeader().setExternalSystemName(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
			
			//setOrderComponentProperties(manualTagging);
			requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(manualTagging);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RestAssured.baseURI = getData("IDM.cce_proxy_url");
		RestAssured.basePath = getData("IDM.cce_proxy_path");
		
		Response response = given().auth()
				.oauth2(TestDataPropertyReaderAndWriter.getProperty("opaque_access_token"))
				.contentType("application/json\r\n").body(requestBody).log().all().post();
		
		if (response.getStatusCode() == 200) {
			System.out.println("Success!!");
		} else if (response.getStatusCode() == 401) {
			System.out.println("Expired or Invalid access token");
		} else if (response.getStatusCode() == 403) {
			System.out.println("Access Forbidden!!! Please use valid access token or check endpoint");
		} else if (response.getStatusCode() == 400) {
			System.out.println("One or more validation errors occurred.");
		}
		
		response.prettyPrint();
		
	}
	
	
	@Test(priority = 0, enabled=true, description = "Create an Electronic Order")
	public void createElectronicOrder() throws InterruptedException {
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName_Electronic").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName_Electronic").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName_Electronic").trim());
		test.supportDataActions.enterOrderQuantity("toOrderQuantity","5");
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		
		Assert.assertTrue(test.purchaseDashboardActions.validateDistributorCardName(
					TestDataPropertyReaderAndWriter.getProperty("DistributorName_Electronic").trim()),
				"[Assertion failed]: Distributor name is not present on present on the purchase order card");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDistributorCardType(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName_Electronic").trim()),
				"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		
		test.supportDataActions.openPurchaseOrderElectroniccard();
		
		String purchaseOrderID = test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		TestDataPropertyReaderAndWriter.setProperty("purchaseOrderID", purchaseOrderID);
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		
	}
	
	@Test(priority = 1, description = "Create Manual Tagging Data")
	public void createmanualTaggingData() throws InterruptedException {
		postManualTaggingData();
	}
	
}
