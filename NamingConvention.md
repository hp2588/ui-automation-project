### Naming Convention Guidelines:
Please follow following guidelines for naming convention:
#1)	Class Names:  Class names should start with Uppercase followed by ‘Title Casing ‘, such as: 
-	TestSessionInitiator
-	Login_Page_Actions

#2)	Method Names: Method names should start with lowercase followed by ‘Title Casing ‘, such as:
-	navigateToSelectISAPage
-	verifyUserIsOnBDLoginPage

#3)	Test Names: All test methods should be annotated with @Test having priority and description, such as:
-	@Test(priority = 0, description = "Verify User's Navigate To  BD Login Page On Launching The BD Application")
public void Test01_Verify_User_Is_On_BD_Login_Page_On_Launching_BD_Application(Method method)
#4)	Variables: Variables name should start with lowercase followed by ‘Title Casing ‘, such as:
-	pageName
-	hiddenFieldTimeOut

#5)	Constants: Constants should be declared with all uppercase characters, such as:
-	MAX_UNITS
-	DAYS_IN_WEEK

#6)	Object Repository: Locators be to defined in separate object repo using lowercase variable names, such as:
-	btn_actions:xpath: .//*[contains(text(), 'Actions')]


 
###Framework Details:
Below structure is being followed to maintain codebase for UI Automation framework. 
1.	com.bd.actions: Page object model is being followed and this package will contain classes for each UI page having  actions related to that particular page. 
2.	com.bd.automation: Multi-browser support is being provided and this package will contain web driver initialization code.
3.	com.bd.automation.getpageobjects:  Basic library functions are provided in the framework and this package will have basic test related properties and functions
4.	com.bd.automation.utils: Basic utility drivers are created and provided in this package
5.	com.bd.extentmanagers: Extent report is supported in the project and this package will contain extent test manager and extent manager classes, such as YAML reader, Screenshots related methods.
6.	com.bd.tests.featuretest: This package will contain all test classes with methods names annotated with @Test
7.	Resources:  This directory will contain common browser drivers, test data and  basic locators in spec file.
 

