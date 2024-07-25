package org.hometest.airalo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;


public abstract class AbstractPage {

    protected static WebDriver driver = new FirefoxDriver();
    Wait<WebDriver> wait =
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(60))
                    .pollingEvery(Duration.ofMillis(300))
                    .ignoring(ElementNotInteractableException.class, NoSuchElementException.class);

    public abstract boolean isPageLoaded();

    public void waitForPageToLoad() {
        wait.until(d -> isPageLoaded());
    }

    public void waitForElementPresent(final By locator) {
        wait.until(d -> isElementPresent(locator));
    }

    public void waitForElementVisible(final By locator) {
        waitForElementPresent(locator);
        wait.until(d -> isElementVisible(locator));
    }

    public boolean isElementPresent(final By by) {
        try {
            getElement(by);
            return true;
        } catch (ElementNotInteractableException e) {
            return false;
        }
    }

    public boolean isElementVisible(final By by) {
        try {
            WebElement webElement = getElement(by);
            return webElement.isDisplayed() && webElement.isEnabled();
        } catch (ElementNotInteractableException e) {
            return false;
        }
    }

    public WebElement getElement(final By by) {
        return driver.findElement(by);
    }

    public void lunchApplication(String url) {
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }

    public void close() {
        try {
            driver.close();
        } catch (Exception e) {
            driver.quit();
        }
    }
}
    
    

