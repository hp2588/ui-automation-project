package com.org.apiDataCreationSuite;

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
import com.org.automation.tokengenerator.OpaqueAccessToken_SupportUser;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateExternalSystem extends OpaqueAccessToken_SupportUser {

	/*
	 * This method is used to create External System.
	 */

	public void postexternalSystemTransaction() throws JSONException {
		String externalSystemJsonFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
				+ "test" + File.separator + "java" + File.separator + "com" + File.separator + "bd" + File.separator
				+ "apiDataCreationSuite" + File.separator + "ExternalSystem.json";
		String externalSystemName = "ES_" + System.currentTimeMillis();
		ExternalSystem externalSystem = new ExternalSystem();
		String requestBody = new String();

		try {
			ObjectMapper mapper = new ObjectMapper();
			externalSystem = mapper.readValue(new File(externalSystemJsonFilePath), ExternalSystem.class);
			externalSystem.setExternalSystemName(externalSystemName);
			requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(externalSystem);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		RestAssured.baseURI = "https://plx.qa.logistics.bdservices.io";
		RestAssured.basePath = "/formulary/api/v1/externalsystems";

		Response response = given().auth().oauth2(TestDataPropertyReaderAndWriter.getProperty("opaque_access_token"))
				.contentType("application/json\r\n").body(requestBody).log().all().post();

		if (response.getStatusCode() == 201) {
			JSONObject jsonObject = new JSONObject(response.getBody().asString());
			String externalSystemKey = jsonObject.toString();
			System.out.println("External System Key :" + externalSystemKey);
			TestDataPropertyReaderAndWriter.setProperty("ExternalSystemKey", externalSystemKey);
			TestDataPropertyReaderAndWriter.setProperty("ExternalSystemName", externalSystemName);
			System.out.println("External System is created successfully");
		} else if (response.getStatusCode() == 401) {
			System.out.println("Expired or Invalid access token");
		} else if (response.getStatusCode() == 403) {
			System.out.println("Access Forbidden!!! Please use valid access token or check endpoint");
		} else if (response.getStatusCode() == 400) {
			System.out.println("One or more validation errors occurred.");
		}
		response.prettyPrint();
	}

	@Test(priority = 1, description = "CreateExternal System")
	public void postExternalSystem() throws InterruptedException {
		postexternalSystemTransaction();
	}
}
