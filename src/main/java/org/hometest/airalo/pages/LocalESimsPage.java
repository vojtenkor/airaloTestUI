package org.hometest.airalo.pages;

import org.openqa.selenium.By;

public class LocalESimsPage extends AbstractPage {

    private final By localESimsTabLocator = By.xpath("//a[@data-testid='scenes.store.local-tab']");
    private final By firstByNowButtonLocator = By.xpath("//a[@href='/japan-esim/moshi-moshi-7days-1gb']//button");

    @Override
    public boolean isPageLoaded() {
        return isElementVisible(localESimsTabLocator);
    }

    public PackageDetailsPage clickFirstByNowButton() {
        getElement(firstByNowButtonLocator).click();
        PackageDetailsPage packageDetailsPage = new PackageDetailsPage();
        packageDetailsPage.waitForPageToLoad();
        return packageDetailsPage;
    }
}
