package com.org.tests.tq.multipartbatchpicks;

import static com.org.automation.utils.YamlReader.getData;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.tokengenerator.OpaqueAccessToken;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BatchPick_Data extends OpaqueAccessToken {

	/*
	 * This method is used to create Batch Pick transactions.
	 */

	public void postBatchPickTransaction() throws JSONException {
		String batchPickJsonFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator + "MultiPartBatchPick.json";
		BatchPickAPIModel batchPick = new BatchPickAPIModel();
		String requestBody = new String();
	
		for (int i = 0; i < 5; i++) {
			UUID uuid = UUID.randomUUID();
			String randomUUIDString = uuid.toString();

			try {
				System.out.println("Going to hit  : "+i);
				ObjectMapper mapper = new ObjectMapper();
				batchPick = mapper.readValue(new File(batchPickJsonFilePath), BatchPickAPIModel.class);
				batchPick.getData().setFacilityCode(TestDataPropertyReaderAndWriter.getProperty("FacilityCode"));
				batchPick.getData()
						.setExternalSystemName(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
				batchPick.getData()
						.setTransactionPriorityCode(TestDataPropertyReaderAndWriter.getProperty("PriorityCode"));
				batchPick.getData()
						.setDeliveryDestinationName(TestDataPropertyReaderAndWriter.getProperty("DestinationCode"));
				batchPick.getHeader().setFacilityCode(TestDataPropertyReaderAndWriter.getProperty("FacilityCode"));
				batchPick.getHeader()
						.setExternalSystemName(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
				batchPick.getHeader().setTenantKey(getData("IDM.tenantKey"));
				batchPick.getHeader().setRequestID(randomUUIDString);
				setOrderComponentProperties(batchPick);
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

			Response response = given().auth()
					.oauth2(TestDataPropertyReaderAndWriter.getProperty("opaque_access_token"))
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
			System.out.println("Response received for  : "+i);
			response.prettyPrint();
		}
	}

	/*
	 * This is a helper method which is used to set Order Component properties in
	 * BatchPickAPIModel.
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
		}
		
	}

	/*
	 * This method is used to create Multiple Batch Pick transactions.
	 */
	
	@Test(priority = 1, description = "Create Multiple Batch Pick Transactions", invocationCount = 5)
	public void multipleBatchPickTransaction() throws InterruptedException {
		postBatchPickTransaction();
		System.out.println("Going to sleep for 2 min 10 sec");
		Thread.sleep(130000);
	}

}