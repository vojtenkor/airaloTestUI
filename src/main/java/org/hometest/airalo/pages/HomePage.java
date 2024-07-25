package org.hometest.airalo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends AbstractPage {

    private final By searchDataPacksLocator = By.xpath("//input[@data-testid='search-input']");
    private final By popupLocator = By.id("ten-million-users-modal___BV_modal_content_");
    private final By popupCloseButtonLocator = By.xpath("//button[descendant::span[@data-testid='close-button']]");

    @Override
    public boolean isPageLoaded() {
        try {
            waitForElementVisible(popupLocator);
            getElement(popupCloseButtonLocator).click();
            return isElementPresent(searchDataPacksLocator);
        } catch (Exception e) {
            return isElementVisible(searchDataPacksLocator);
        }
    }

    public void searchDataPacks(String countryName) {
        WebElement searchDataPacksField = getElement(searchDataPacksLocator);
        waitForElementVisible(searchDataPacksLocator);
        searchDataPacksField.clear();
        searchDataPacksField.sendKeys(countryName);
        By countrySelectorLocator = By.xpath("//li[descendant::span[@data-testid='" + countryName + "-name']]");
        waitForElementVisible(countrySelectorLocator);
    }

    public void selectLocalCountry(String countryName) {
        By countrySelectorLocator = By.xpath("//span[@data-testid='" + countryName + "-name']");
        WebElement countrySelector = getElement(countrySelectorLocator);
        countrySelector.click();
        waitForElementVisible(By.xpath("//h2[@id='store-title' and contains(text(),'" + countryName + "')]"));
    }
}
