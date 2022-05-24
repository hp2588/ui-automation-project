package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class BatchPick_Data extends BaseTest {

	@BeforeClass
	public void Open_Browser_Window() {

		test = new TestSessionInitiator(this.getClass().getSimpleName());
	}

	@Test(priority = 7,description = "POST User Permission", enabled = true)
	public void PostUserFacilityMapping() throws JSONException {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		
		UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

		obj.put("facilityCode", TestDataPropertyReaderAndWriter.getProperty("FacilityCode"));
		obj.put("externalSystemName", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName"));
		obj.put("transactionPriorityCode", TestDataPropertyReaderAndWriter.getProperty("PriorityCode"));
		obj.put("deliveryDestinationName", TestDataPropertyReaderAndWriter.getProperty("DestinationCode"));
		obj.put("itemID", TestDataPropertyReaderAndWriter.getProperty("ItemID"));
		obj.put("itemName", TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		obj.put("pickQuantity", "1");
		obj.put("requestID", randomUUIDString);
		obj.put("tenantKey", "99471bdd-2159-41f0-9e8f-2009293b89fe");

		System.out.println("Response:" + obj);

		RestAssured.baseURI = "https://plx.qa.logistics.bdservices.io";
		RestAssured.basePath = "/api/v1/cceproxy/InboundIntegrationProcess";

		Response response = given().auth().oauth2(TestDataPropertyReaderAndWriter.getProperty("access_token"))
				.contentType("application/json\r\n")
				.body(obj.toString()).post();

		if (response.getStatusCode() == 200) {
			System.out.println("Batch data is created successfully");
		} else if (response.getStatusCode() == 401) {
			System.out.println("Expired access token");
		} else if (response.getStatusCode() == 403) {
			System.out.println("Access Forbidden!!! Please use valid access token or check endpoint");
		}

// Assert.assertEquals(response.getStatusCode(),201);
		response.prettyPrint();

	}
}