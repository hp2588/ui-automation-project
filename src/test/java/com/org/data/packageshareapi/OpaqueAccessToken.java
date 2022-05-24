package com.org.data.packageshareapi;

import static com.org.automation.utils.YamlReader.getData;
import static io.restassured.RestAssured.given;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OpaqueAccessToken extends BaseTest {
	
	@BeforeClass
	public void Open_Browser_Window() {
		getAuthorizationCode();
		getAccessToken();
		getOpaqueToken();
	}

	/*
	 * This method is used to get authorization code which will be used to request
	 * for access token. To get authorization code we need to open user authorized
	 * screen where user will enter valid credentials. After successful
	 * verification, user will be redirected to a URL with authorization code in its
	 * query params.
	 */

	private void getAuthorizationCode() {
		test = new TestSessionInitiator(this.getClass().getSimpleName()); //TODO

		String authUrl = new String();
		try {
			authUrl = new URIBuilder(getData("IDM.token_url") + getData("IDM.authorization_path"))
					.addParameter("response_type", "code").addParameter("redirect_uri", getData("IDM.redirect_uri"))
					.addParameter("client_id", getData("IDM.client_id"))
					.addParameter("scope", getData("IDM.scope-openId")).build().toURL().toString();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		System.out.println("URL value : " + authUrl);

		test.launchApplication(authUrl);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(), getData("Auth.ip").trim());
		String currentUrl = getDriver().getCurrentUrl();
		System.out.println("URL value = " + currentUrl);
		try {
			URL url = new URL(currentUrl);
			String query = url.getQuery();
			Map<String, String> map = getQueryMap(query);
			String code = map.get("code");
			TestDataPropertyReaderAndWriter.setProperty("code", code);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This is a helper method which is used to parse the query params and create a
	 * map for those.
	 */

	public Map<String, String> getQueryMap(String query) {
		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();

		for (String param : params) {
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(name, value);
		}
		return map;
	}

	/*
	 * This method is used to get access token which will be used to request for
	 * opaque token.
	 */

	private void getAccessToken() {

		RestAssured.baseURI = getData("IDM.token_url");
		RestAssured.basePath = getData("IDM.access_token_path");

		Response response = given().contentType("application/x-www-form-urlencoded")
				.formParam("grant_type", "authorization_code").formParam("redirect_uri", getData("IDM.redirect_uri"))
				.formParam("response_type", "code")
				.formParam("code", TestDataPropertyReaderAndWriter.getProperty("code"))
				.formParam("client_id", getData("IDM.client_id"))
				.formParam("client_secret", getData("IDM.client_secret")).log().all().when().post();

		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();

		JSONObject jsonObject = new JSONObject(response.getBody().asString());
		String accessToken = jsonObject.get("access_token").toString();
		System.out.println("Access Token :" + accessToken);
		TestDataPropertyReaderAndWriter.setProperty("access_token", accessToken);
	}
	
	/*
	 * This method is used to get opaque token which will be used to request for
	 * opaque token.
	 */

	private void getOpaqueToken() {

		RestAssured.baseURI = getData("IDM.opaque_token_url");
		RestAssured.basePath = getData("IDM.opaque_token_path");

		Response response = given()
				.header("Authorization", "Bearer " + TestDataPropertyReaderAndWriter.getProperty("access_token")).log()
				.all().get();

		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();

		JSONObject jsonObject = new JSONObject(response.getBody().asString());
		String opaqueAccessToken = jsonObject.get("opaqueToken").toString();
		System.out.println("Opaque Token : " + opaqueAccessToken);
		TestDataPropertyReaderAndWriter.setProperty("AdminUserOpaqueToken", opaqueAccessToken);
	}


}
