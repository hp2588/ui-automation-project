package com.org.automation.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWait {

	static WebDriver driver;
	WebDriverWait wait;

	int timeout;

	public SeleniumWait(WebDriver driver, int timeout) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, timeout);
		this.timeout = timeout;
	}

	/**
	 * Returns webElement found by the locator if element is visible
	 * 
	 * @param locator
	 * @return
	 */

	public void waitForWindowsToDisappear() {
		int i = 0;
		resetImplicitTimeout(8);
		int size = driver.getWindowHandles().size();
		try {
			while (size != 1 && i <= timeout) {
				System.out.println("Size of the window" + size);
				size = driver.getWindowHandles().size();

				i++;
			}
		} catch (Exception e) {
			System.out.println("INFO : Window is not appeared\n");
		}
		resetImplicitTimeout(timeout);
	}

	public WebElement getWhenVisible(By locator) {
		WebElement element;
		element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	public WebElement getWhenClickable(By locator) {
		WebElement element;
		element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	public boolean waitForPageTitleToBeExact(String expectedPagetitle) {
		return wait.until(ExpectedConditions.titleIs(expectedPagetitle)) != null;
	}

	public boolean waitForPageTitleToContain(String expectedPagetitle) {
		return wait.until(ExpectedConditions.titleContains(expectedPagetitle)) != null;
	}
	
	public WebElement waitForElementToBeVisible(WebElement element) throws NoSuchElementException {
		WebElement webElement = (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
		// elementHighlight(webElement);
		return webElement;
	}

	public void waitForFrameToBeAvailableAndSwitchToIt(By locator) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
		List<WebElement> webElements = (List<WebElement>) wait
				.until(ExpectedConditions.visibilityOfAllElements(elements));
		return webElements;
	}

	public boolean waitForElementToBeInVisible(By locator) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)) != null;
	}

	public WebElement waitForElementToBeClickable(WebElement element) {
		return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void clickWhenReady(By locator) {
		WebElement element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public void waitForMsgToastToDisappear() {
		int i = 0;
		resetImplicitTimeout(1);
		try {
			while (driver.findElement(By.className("toast-message")).isDisplayed() && i <= timeout) {

				i++;
			}
		} catch (Exception e) {
		}
		resetImplicitTimeout(timeout);
	}

	public void waitForElementToDisappear(WebElement element) {
		int i = 0;
		resetImplicitTimeout(8);
		try {
			while (element.isDisplayed() && i <= timeout) {

				i++;
			}
		} catch (Exception e) {
			System.out.println("INFO : " + element + " is not appeared\n");
		}
		resetImplicitTimeout(timeout);
	}

	public WebElement getWhenVisible(By locator, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	public void resetImplicitTimeout(int newTimeOut) {
		try {
			driver.manage().timeouts().implicitlyWait(newTimeOut, TimeUnit.SECONDS);
		} catch (Exception e) {
		}
	}

	public void resetExplicitTimeout(int newTimeOut) {
		try {
			this.wait = new WebDriverWait(driver, newTimeOut);
		} catch (Exception e) {
		}
	}

	// Implement Wait for page load for page synchronizations
	public void waitForPageToLoadCompletely() {
		if (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("internetexplorer")
				|| ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("ie")
				|| ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("IE")) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		} else {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*")));
		}

	}

	public void loadingWait(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 40);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			System.out.println("Loader has loaded the records.");
		} catch (Exception e) {
			System.out.println("Loader taking too Long to Load.");
		}
	}

	public static boolean ElementIsDisplayed(By element) {
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));

		} catch (Exception e) {
			return false;
		}
		return true;

	}

	public static boolean successPopupIsDisplayed(By element) {
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 150);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static void waitForSuccessMessage(By element, int timeout) {
		if (successPopupIsDisplayed(element)) {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(element));
		}
	}

	public static void waitForLoaderToBeInvisible(By element, int timeout) {
		if (ElementIsDisplayed(element)) {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(element));
		}
		
	}
	
	public void hardWait(int seconds) {
		
	}

	public void elementHighlight(WebElement element) {
		/*
		 * if (ConfigPropertyReader.getProperty("mode").equalsIgnoreCase("debug") ||
		 * ConfigPropertyReader.getProperty("mode").equalsIgnoreCase("Debug") ||
		 * ConfigPropertyReader.getProperty("mode").equalsIgnoreCase("DEBUG")) {
		 */
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, " border: 1px solid red;");
		// js.executeScript(
		// "arguments[0].setAttribute('style', arguments[1]);",
		// element, "");color: red;
		// }

	}

	public void applyFluentWait(By locator, int timeOut, int pollingTime) {
		@SuppressWarnings("deprecation")
		Wait wait = new FluentWait<>(this.driver).withTimeout(timeOut, TimeUnit.SECONDS)
				.pollingEvery(pollingTime, TimeUnit.MILLISECONDS).ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void applyFluentWait(By locator, int pollingTime) {
		int timeOut = Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));
		@SuppressWarnings("deprecation")
		Wait wait = new FluentWait<>(this.driver).withTimeout(timeOut, TimeUnit.SECONDS)
				.pollingEvery(pollingTime, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}

	public WebElement waitForElementToBeClickableAfterRefresh(WebElement element) {
		return (WebElement) wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	}
	
}
