package com.org.automation.getpageobjects;

import static com.org.automation.getpageobjects.ObjectFileReader.getELementFromFile;
import static com.org.automation.utils.ConfigPropertyReader.getProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.org.automation.utils.ConfigPropertyReader;

public class GetPage extends BaseUi {

	protected WebDriver webdriver;
	String pageName;
	static int count = 0;

	public GetPage(WebDriver driver, String pageName) {
		super(driver, pageName);
		this.webdriver = driver;
		this.pageName = pageName;
	}

	protected void verifyElementTextContentCaseInSensitive(String elementName, String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		assertThat("ASSERT FAILED : Element '" + elementName + "' Text is not as expected: ",
				org.apache.commons.lang3.StringUtils
						.containsIgnoreCase(element(elementName).getAttribute("textContent"), expectedText));
		logMessage("ASSERT PASSED : Element " + elementName + " is visible and Text is " + expectedText);
	}

	protected boolean checkIfElementIsThere(String eleString) {
		timeOut = Integer.parseInt(getProperty("Config.properties", "timeout"));
		hiddenFieldTimeOut = Integer.parseInt(getProperty("Config.properties", "hiddenFieldTimeOut"));

		boolean flag = false;
		try {
			wait.resetImplicitTimeout(0);
			wait.resetExplicitTimeout(hiddenFieldTimeOut);
			if (webdriver.findElement(getLocator(eleString)).isDisplayed()) {
				flag = true;
				wait.resetImplicitTimeout(timeOut);
				wait.resetExplicitTimeout(timeOut);
			} else {
				wait.resetImplicitTimeout(timeOut);
				wait.resetExplicitTimeout(timeOut);
				flag = false;
			}
		} catch (NoSuchElementException ex) {
			wait.resetImplicitTimeout(timeOut);
			wait.resetExplicitTimeout(timeOut);
			flag = false;
		}
		return flag;
	}

	protected boolean checkIfElementIsThere(String eleString, String replacementEleString) {
		boolean flag = false;
		wait.resetImplicitTimeout(8);
		wait.resetExplicitTimeout(10);
		try {
			if (webdriver.findElement(getLocator(eleString, replacementEleString)).isDisplayed()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (NoSuchElementException ex) {
			flag = false;
		}
		return flag;
	}

	protected boolean checkIfElementIsThere(String eleString, String replacementEleString1,
			String replacementEleString2) {
		boolean flag = false;
		wait.resetImplicitTimeout(2);
		wait.resetExplicitTimeout(10);
		try {
			if (webdriver.findElement(getLocator(eleString, replacementEleString1, replacementEleString2))
					.isDisplayed()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (NoSuchElementException ex) {
			flag = false;
		}
		return flag;
	}

	protected boolean checkIfElementIsThere(String eleString, String replacementEleString1,
			String replacementEleString2, String replacementEleString3) {
		boolean flag = false;
		wait.resetImplicitTimeout(2);
		wait.resetExplicitTimeout(10);
		try {
			if (webdriver
					.findElement(
							getLocator(eleString, replacementEleString1, replacementEleString2, replacementEleString3))
					.isDisplayed()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (NoSuchElementException ex) {
			flag = false;
		}
		return flag;
	}

	protected WebElement element(String elementToken) throws NoSuchElementException {
		return element(elementToken, "");
	}

	protected WebElement element(String elementToken, String replacement) throws NoSuchElementException {
		WebElement elem = null;
		By locator = getLocator(elementToken, replacement);
		try {
			elem = wait.waitForElementToBeVisible(webdriver.findElement(locator));
		} catch (TimeoutException excp) {
			throw new NoSuchElementException("Element " + elementToken + " with locator "
					+ locator.toString().substring(2) + " not found on the " + this.pageName + " !!!");
		}
		return elem;
	}

	protected WebElement element(String elementToken, String replacement1, String replacement2)
			throws NoSuchElementException {
		WebElement elem = null;
		By locator = getLocator(elementToken, replacement1, replacement2);
		System.out.println("locator is:" + locator);
		try {
			elem = wait.waitForElementToBeVisible(webdriver.findElement(locator));
		} catch (TimeoutException excp) {
			throw new NoSuchElementException("Element " + elementToken + " with locator "
					+ locator.toString().substring(2) + " not found on the " + this.pageName + " !!!");
		}
		return elem;
	}

	protected WebElement element(String elementToken, String replacement1, String replacement2, String replacement3)
			throws NoSuchElementException {
		WebElement elem = null;
		By locator = getLocator(elementToken, replacement1, replacement2, replacement3);
		try {
			elem = wait.waitForElementToBeVisible(webdriver.findElement(locator));
		} catch (TimeoutException excp) {
			throw new NoSuchElementException("Element " + elementToken + " with locator "
					+ locator.toString().substring(2) + " not found on the " + this.pageName + " !!!");
		}
		return elem;
	}

	static String getAlphaNumericString(int n) {

		String AlphaNumericString = "BD-Automation" + "0123456789";

		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	protected List<WebElement> elements(String elementToken, String replacement) {
		wait.hardWait(25);
		return webdriver.findElements(getLocator(elementToken, replacement));
//		return wait.waitForElementsToBeVisible(webdriver.findElements(getLocator(elementToken, replacement)));
	}

	protected List<WebElement> elements(String elementToken, String replacement1, String replacement2) {
		return wait.waitForElementsToBeVisible(
				webdriver.findElements(getLocator(elementToken, replacement1, replacement2)));
	}

	protected List<WebElement> elements(String elementToken) {
		return elements(elementToken, "");
	}
	
	protected boolean isElementDisplayed(String elementName, String elementTextReplace) {
		wait.applyFluentWait(getLocator(elementName,elementTextReplace), 2);
		boolean result = element(elementName, elementTextReplace).isDisplayed();
		assertTrue(result,
				"ASSERT FAILED : Element '" + elementName + " with text " + elementTextReplace + "' is not displayed.");
		logMessage("ASSERT PASSED : Element " + elementName + " with text " + elementTextReplace + " is displayed.");
		return result;
	}
	
	protected boolean isElementNotDisplayed(String elementName, String elementTextReplace) {
		try {
			element(elementName, elementTextReplace);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	/*protected boolean isElementNotDisplayed(String elementName, String elementTextReplace) {
		try {
			element(elementName, elementTextReplace);
			return false;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return true;
		}
	}*/

	protected boolean isElementNotDisplayed(String elementName, String elementTextReplace1,
			String elementTextReplace2) {
		try {

			element(elementName, elementTextReplace1, elementTextReplace2);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	protected boolean isElementNotDisplayed(String elementName) {
		try {
			element(elementName);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	protected boolean isElementDisplayed(String elementName, String elementTextReplace1, String elementTextReplace2,
			String elementTextReplace3) {
		wait.applyFluentWait(getLocator(elementName,elementTextReplace1,elementTextReplace2, elementTextReplace3), 2);
		boolean result = element(elementName, elementTextReplace1, elementTextReplace2, elementTextReplace3)
				.isDisplayed();
		assertTrue(result, "ASSERT FAILED : Element '" + elementName + " with text " + elementTextReplace1
				+ elementTextReplace2 + elementTextReplace3 + "' is not displayed.");
		logMessage("ASSERT PASSED : Element " + elementName + " with text " + elementTextReplace1 + elementTextReplace2
				+ elementTextReplace3 + " is displayed.");
		return result;
	}

	protected boolean isElementDisplayed(String elementName, String elementTextReplace1, String elementTextReplace2) {
		wait.applyFluentWait(getLocator(elementName,elementTextReplace1,elementTextReplace2), 2);
		boolean result = element(elementName, elementTextReplace1, elementTextReplace2).isDisplayed();
		assertTrue(result, "ASSERT FAILED : Element '" + elementName + "with text " + elementTextReplace1
				+ elementTextReplace2 + "' is not displayed.");
		logMessage("ASSERT PASSED : Element " + elementName + " with text " + elementTextReplace1 + ","
				+ elementTextReplace2 + " is displayed.");
		return result;
	}

	protected void verifyElementText(String elementName, String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		assertEquals(element(elementName).getText().trim(), expectedText,
				"ASSERT FAILED : Element '" + elementName + "' Text is not as expected: ");
		logMessage("ASSERT PASSED: Element " + elementName + " is visible and Text is " + expectedText);
	}

	protected void verifyElementTextContains(String elementName, String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		assertThat("ASSERT FAILED : Element '" + elementName + "' Text is not as expected: ",
				element(elementName).getText(), containsString(expectedText));
		logMessage("ASSERT PASSED : Element " + elementName + " is visible and Text is " + expectedText);
	}
	
	protected boolean isElementDisplayed(String elementName) throws NoSuchElementException, AssertionError {
		wait.applyFluentWait(getLocator(elementName), 2);
		boolean result = wait.waitForElementToBeVisible(element(elementName)).isDisplayed();
		assertTrue(result, "ASSERT FAILED : Element '" + elementName + "' is not displayed.");
		logMessage("ASSERT PASSED : Element " + elementName + " is displayed.");
		return result;
	}
	
	protected WebElement activeElement() throws NoSuchElementException, AssertionError {
		logMessage("Focused Element is: " +driver.switchTo().activeElement());
		return driver.switchTo().activeElement();
	}
	
	protected boolean isElementEnabled(WebElement elementName, boolean expected) {
		wait.waitForElementToBeVisible(elementName);
		boolean result = expected && elementName.isEnabled();
		System.out.println("Value of result " + result);
		assertTrue(result, "ASSERT FAILED : Element '" + elementName + "' is  ENABLED :- " + !expected);
		logMessage("ASSERT PASSED : Element " + elementName + " is enabled :- " + expected);
		return result;
	}

	protected By getLocator(String elementToken) {
		return getLocator(elementToken, "");
	}

	protected By getLocator(String elementToken, String replacement) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
		System.out.println("locator:" + locator[2]);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	protected By getLocator(String elementToken, String replacement1, String replacement2) {

		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceFirst("\\$\\{.+?\\}", replacement1);
		locator[2] = locator[2].replaceFirst("\\%\\{.+?\\}", replacement2);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	protected By getLocator(String elementToken, String replacement1, String replacement2, String replacement3) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceFirst("\\$\\{.+?\\}", replacement1);
		locator[2] = locator[2].replaceFirst("\\%\\{.+?\\}", replacement2);
		locator[2] = locator[2].replaceFirst("\\#\\{.+?\\}", replacement3);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	private By getBy(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)) {
		case id:
			return By.id(locatorValue);
		case xpath:
			return By.xpath(locatorValue);
		case css:
			return By.cssSelector(locatorValue);
		case name:
			return By.name(locatorValue);
		case classname:
			return By.className(locatorValue);
		case linktext:
			return By.linkText(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}

	public void scriptExecutionController() {
		// if
		// (ConfigPropertyReader.getProperty("mode").equalsIgnoreCase("debug"))
		// {
		// wait.hardWait(1);
		// }
	}

	protected void verifyElementTextContent(String elementName, String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		assertThat("ASSERT FAILED : Element '" + elementName + "' Text is not as expected: ",
				element(elementName).getAttribute("textContent"), containsString(expectedText));
		logMessage("ASSERT PASSED : Element " + elementName + " is visible and Text is " + expectedText);
	}

	public static <T, E> T getMapFirstMatchedKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (Objects.equals(value, entry.getValue())) {
				System.out.println(entry.getKey());
				System.out.println(value);
				return entry.getKey();
			}
		}
		return null;
	}

	public void dynamicWait(int timeout, String element, String replacement) {
		try {
			if (count < timeout) {
				wait.resetImplicitTimeout(2);
				isElementDisplayed(element, replacement);
			}
		} catch (NoSuchElementException e) {
			count++;
			dynamicWait(timeout, element, replacement);
		}
		{
			int deafultTm = Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));
			wait.resetImplicitTimeout(deafultTm);
		}
	}

	protected void verifyElementTextContentCaseSensitive(String elementName, String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		// org.apache.commons.lang3.StringUtils.containsIgnoreCase(element(elementName).getAttribute("textContent"),expectedText);
		assertThat("ASSERT FAILED : Element '" + elementName + "' Text is not as expected: ",
				org.apache.commons.lang3.StringUtils
						.containsIgnoreCase(element(elementName).getAttribute("textContent"), expectedText));
		logMessage("ASSERT PASSED : Element " + elementName + " is visible and Text is " + expectedText);
	}

	// TODO: put this in right place, create dedicated class for frame and
	// window handlers
	protected void switchToNestedFrames(String frameNames) {
		switchToDefaultContent();
		String[] frameIdentifiers = frameNames.split(":");
		for (String frameId : frameIdentifiers) {
			wait.waitForFrameToBeAvailableAndSwitchToIt(getLocator(frameId.trim()));
		}
	}
	
}
