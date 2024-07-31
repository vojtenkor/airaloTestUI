package org.hometest.airalo.pages;

import org.openqa.selenium.By;

public class PackageDetailsPage extends AbstractPage {
    
    private final By packageNameFieldLocator = By.xpath("//div[@data-testid='package-detail']");
    private final By simDetailOperatorTitleTextLocator = By.xpath("//div[@data-testid='sim-detail-operator-title']/p");
    private final By coverageValueLocator = By.xpath("//p[@data-testid='COVERAGE-value']");
    private final By dataValueLocator = By.xpath("//div[@data-testid='sim-detail-header']//p[@data-testid='DATA-value']");
    private final By validityValueLocator = By.xpath("//div[@data-testid='sim-detail-header']//p[@data-testid='VALIDITY-value']");
    private final By priceValueLocator = By.xpath("//div[@data-testid='sim-detail-header']//p[@data-testid='PRICE-value']");
    
    @Override
    public boolean isPageLoaded() {
        return isElementVisible(packageNameFieldLocator);
    }
    
    public String getPackageTitle() {
        return getElement(simDetailOperatorTitleTextLocator).getText();
    }

    public String getCoverageValue() {
        return getElement(coverageValueLocator).getText();
    }
    
    public String getDataValue() {
        return getElement(dataValueLocator).getText();
    }

    public String getValidityValue() {
        return getElement(validityValueLocator).getText();
    }

    public String getPriceValue() {
        return getElement(priceValueLocator).getText();
    }
}
