package com.org.smoketests;

import static com.org.automation.utils.YamlReader.getYamlValue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.XlsReader;
import com.org.extentmanagers.ExtentTestManager;

public class Authorization extends BaseTest {

	public static String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "resources" + File.separator + "testdata" + File.separator;
	String fileLocation = filePath + "authorization-matrix.xlsx";
	String sheetName = "user-roles-permissions";
	String perm, cred;
	String app_url;

	@Override
	@BeforeClass
	public void Open_Browser_Window() {

	}

	@Test(priority = 1, description = "VPLX:Authorization: Verify Roles Matrix")
	public void Test01_verifyRolesAndFeatures(Method method) throws IOException {
		ExtentTestManager.startTest(method.getName(), "VPLX:Authorization: Verify Roles Matrix");

		System.out.println(XlsReader.readExcelData(fileLocation).keySet());
		Set<String> keys = XlsReader.readExcelData(fileLocation).keySet();
		for (String key : keys) {

			if (key.equals("")) {
				continue;
			}
			String userName = XlsReader.readExcelData(fileLocation).get(key).get(0);
			String password = XlsReader.readExcelData(fileLocation).get(key).get(1);
			String[] perm = XlsReader.readExcelData(fileLocation).get(key).get(2).split("\\r?\\n");

			System.out.println("credentials" + userName + password);
			test = new TestSessionInitiator(this.getClass().getSimpleName());
			app_url = getYamlValue("app_url");
			test.launchApplication(app_url);
			test.loginPageAction.verifyUserIsOnBDLoginPage();
			test.loginPageAction.LoginToTheBDApplication(userName, password, "");
			test.landingPageActions.verifyUserPermissions(perm, key);
			System.out.println("Permissions verified for Role" + " " + key);
			test.closeBrowserSession();

		}

	}

}
