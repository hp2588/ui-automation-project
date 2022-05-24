package com.org.tests.integration;

import static com.org.automation.utils.YamlReader.getData;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

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

public class Map_Facility_To_User extends BaseTest {

	@BeforeClass
	public void Open_Browser_Window() {

		test = new TestSessionInitiator(this.getClass().getSimpleName());
	}

	@Test(priority = 1, description = "Generate Facility Token")
	public void getfacilityToken() throws JSONException {

		//String baseURL = getData("IDM.base_url");
		//String basePath = getData("IDM.base_path");
		

		String baseURL = "https://idm-test-uswest2-idmsts.azurewebsites.net";
		String basePath = "/ids/connect/token";

		TestDataPropertyReaderAndWriter.setProperty("base_url", baseURL);
		TestDataPropertyReaderAndWriter.setProperty("base_path", basePath);

		RestAssured.baseURI = TestDataPropertyReaderAndWriter.getProperty("base_url");
		RestAssured.basePath = TestDataPropertyReaderAndWriter.getProperty("base_path");

		Response response = given().auth().preemptive().basic(getData("IDM.client_name"), getData("IDM.client_secret"))
				.contentType("application/x-www-form-urlencoded").formParam("grant_type", "client_credentials")
				.formParam("scope", getData("IDM.scope-facility")).when().post();

		Assert.assertEquals(response.getStatusCode(), 200);

		response.prettyPrint();

		JSONObject jsonObject = new JSONObject(response.getBody().asString());
		String accessToken = jsonObject.get("access_token").toString();
		System.out.println(accessToken);
		TestDataPropertyReaderAndWriter.setProperty("access_token", accessToken);

	}

	@Test(priority = 2, description = "GET Facility ID", enabled = true)
	public void getOrganizationResourceFacilityID() throws ParseException {

		String facilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		Object facilityID = null;

		RestAssured.baseURI = "https://idm-test-uswest2-idmauthzapi.azurewebsites.net";
		RestAssured.basePath = "/api/organizational-resources";

		Response response = given().auth().oauth2(TestDataPropertyReaderAndWriter.getProperty("access_token"))
				.contentType("application/json\r\n")
				.header("tenantCode", getData("IDM.tenantCode"))
				.header("client_id", getData("IDM.client_id"))
				.get("https://idm-test-uswest2-idmauthzapi.azurewebsites.net/api/organizational-resources");

		response.prettyPrint();

		HashMap<Object, Object> hashmap = new HashMap<Object, Object>();
// String resp=response.asString();

		JSONObject jObj = new JSONObject(response.asString());
		Object obj = jObj.get("data");

		JSONArray jsArray = new JSONArray(obj.toString());
		for (int i = 0; i < jsArray.length(); i++) {
			JSONObject jsObj1 = new JSONObject(jsArray.get(i).toString());
			hashmap.put(jsObj1.get("name"), jsObj1.get("id"));

		}
		for (Map.Entry<Object, Object> entry : hashmap.entrySet())
			if (entry.getKey().equals(facilityName)) {
				facilityID = entry.getValue();
				break;
			}

		TestDataPropertyReaderAndWriter.setProperty("facilityID", facilityID.toString());
	}

	@Test(priority = 3, description = "Generate Role Token", enabled = true)
	public void getRoleToken() throws JSONException {

		RestAssured.baseURI = TestDataPropertyReaderAndWriter.getProperty("base_url");
		RestAssured.basePath = TestDataPropertyReaderAndWriter.getProperty("base_path");

		Response response = given().auth().preemptive()
				.basic(getData("IDM.client_name"),
						getData("IDM.client_secret"))
				.contentType("application/x-www-form-urlencoded").formParam("grant_type", "client_credentials")
				.formParam("scope", getData("IDM.scope-role")).when().post();

		Assert.assertEquals(response.getStatusCode(), 200);

		response.prettyPrint();

		JSONObject jsonObject = new JSONObject(response.getBody().asString());
		String accessToken = jsonObject.get("access_token").toString();
		System.out.println(accessToken);
		TestDataPropertyReaderAndWriter.setProperty("access_token", accessToken);

	}

	@Test(priority = 4, description = "GET Role ID",enabled = true)
	public void getRoleID() throws ParseException {

		String RoleName = getData("IDM.roleName");
		Object roleID = null;

		RestAssured.baseURI = "https://idm-test-uswest2-idmauthzapi.azurewebsites.net";
		RestAssured.basePath = "/api/roles";

		Response response = given().auth().oauth2(TestDataPropertyReaderAndWriter.getProperty("access_token"))
				.contentType("application/json\r\n")
				.header("tenantCode", getData("IDM.tenantCode"))
				.header("client_id", getData("IDM.client_id")).get();

		response.prettyPrint();

		HashMap<Object, Object> hashmap = new HashMap<Object, Object>(); // String

		JSONObject jObj = new JSONObject(response.asString());
		Object obj = jObj.get("data");

		JSONArray jsArray = new JSONArray(obj.toString());
		for (int i = 0; i < jsArray.length(); i++) {
			JSONObject jsObj1 = new JSONObject(jsArray.get(i).toString());
			hashmap.put(jsObj1.get("name"), jsObj1.get("id"));

		}
		for (Map.Entry<Object, Object> entry : hashmap.entrySet())
			if (entry.getKey().equals(RoleName)) {
				roleID = entry.getValue();
				break;
			}

		TestDataPropertyReaderAndWriter.setProperty("roleID", roleID.toString());

	}

	@Test(priority = 5,description = "GET User ID", enabled = true)
	public void getUserID() throws ParseException {

		String userName = getData("Auth.userNameAdminUser");
		Object userID = null;

		RestAssured.baseURI = "https://idm-test-uswest2-idmauthzapi.azurewebsites.net";
		RestAssured.basePath = "/api/roles";

		Response response = given().auth().oauth2(TestDataPropertyReaderAndWriter.getProperty("access_token"))
				.contentType("application/json\r\n")
				.header("tenantCode", getData("IDM.tenantCode"))
				.header("client_id", getData("IDM.client_id"))
				.get("https://idm-test-uswest2-idmauthzapi.azurewebsites.net/api/roles/"
						+ TestDataPropertyReaderAndWriter.getProperty("roleID") + "/users");

		response.prettyPrint();

		HashMap<Object, Object> hashmap = new HashMap<Object, Object>(); // String

		JSONObject jObj = new JSONObject(response.asString());
		Object obj = jObj.get("data");

		JSONArray jsArray = new JSONArray(obj.toString());
		for (int i = 0; i < jsArray.length(); i++) {
			JSONObject jsObj1 = new JSONObject(jsArray.get(i).toString());
			hashmap.put(jsObj1.get("email"), jsObj1.get("id"));

		}
		for (Map.Entry<Object, Object> entry : hashmap.entrySet())
			if (entry.getKey().equals(userName)) {
				userID = entry.getValue();
				break;
			}

		TestDataPropertyReaderAndWriter.setProperty("userID", userID.toString());

	}

	@Test(priority = 6,description = "Generate User Permission Token", enabled = true)
	public void getUserMapToken() throws JSONException {

		RestAssured.baseURI = TestDataPropertyReaderAndWriter.getProperty("base_url");
		RestAssured.basePath = TestDataPropertyReaderAndWriter.getProperty("base_path");

		Response response = given().auth().preemptive()
				.basic(getData("IDM.client_name"),
						getData("IDM.client_secret"))
				.contentType("application/x-www-form-urlencoded").formParam("grant_type", "client_credentials")
				.formParam("scope", getData("IDM.scope-userperm")).when().post();

		Assert.assertEquals(response.getStatusCode(), 200);

		response.prettyPrint();

		JSONObject jsonObject = new JSONObject(response.getBody().asString());
		String accessToken = jsonObject.get("access_token").toString();
		System.out.println(accessToken);
		TestDataPropertyReaderAndWriter.setProperty("access_token", accessToken);

	}

	@Test(priority = 7,description = "POST User Permission", enabled = true)
	public void PostUserFacilityMapping() throws JSONException {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONArray arr1 = new JSONArray();

		obj.put("userAccount", TestDataPropertyReaderAndWriter.getProperty("userID"));
		obj.put("roleId", TestDataPropertyReaderAndWriter.getProperty("roleID"));

		JSONObject obj2 = new JSONObject();
		obj2.put("facility", TestDataPropertyReaderAndWriter.getProperty("facilityID"));
		obj2.put("sectors", arr);
		arr1.put(obj2);
		obj.put("facilities", arr1);

		System.out.println("Response:" + obj);

		RestAssured.baseURI = "https://idm-test-uswest2-idmauthzapi.azurewebsites.net";
		RestAssured.basePath = "/api/user-permissions";

		Response response = given().auth().oauth2(TestDataPropertyReaderAndWriter.getProperty("access_token"))
				.contentType("application/json\r\n")
				.header("tenantCode", getData("IDM.tenantCode"))
				.header("client_id", getData("IDM.client_id")).log().body()
				.body(obj.toString()).post();

		if (response.getStatusCode() == 201) {
			System.out.println(
					"Facility is mapped successfully to user " + getData("Auth.userNameAdminUser").trim());
		} else if (response.getStatusCode() == 409) {
			System.out.println("Facility is already mapped to the user!!");
		} else if (response.getStatusCode() == 403) {
			System.out.println("Access Forbidden!!! Please use valid access token or check endpoint");
		}

// Assert.assertEquals(response.getStatusCode(),201);
		response.prettyPrint();

	}
}