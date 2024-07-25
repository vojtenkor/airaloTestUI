package org.hometest.airalo.tests;

import org.hometest.airalo.BaseTest;
import org.hometest.airalo.pages.HomePage;
import org.hometest.airalo.pages.LocalESimsPage;
import org.hometest.airalo.pages.PackageDetailsPage;
import org.junit.Test;

public class ValidateESimOptions extends BaseTest {

    @Test
    public void validateJapaneseOptionOne() {
        HomePage homePage = new HomePage();
        homePage.lunchApplication(prop.getProperty("app.url"));
        homePage.waitForPageToLoad();
        homePage.searchDataPacks("Japan");
        homePage.selectLocalCountry("Japan");
        LocalESimsPage eSimsPage = new LocalESimsPage();
        eSimsPage.waitForPageToLoad();
        PackageDetailsPage packageDetailsPage = eSimsPage.clickFirstByNowButton();
        String packageTitle = packageDetailsPage.getPackageTitle();
        softAssertions.assertThat(packageTitle)
                .as(String.format("Package title '%s' is not equal to '%s'", packageTitle, "Moshi Moshi"))
                .isEqualTo("Moshi Moshi");
        String coverageValue = packageDetailsPage.getCoverageValue();
        softAssertions.assertThat(coverageValue)
                .as(String.format("Coverage value '%s' is not equal to '%s'", coverageValue, "Japan"))
                .isEqualTo("Japan");
        String dataValue = packageDetailsPage.getDataValue();
        softAssertions.assertThat(dataValue)
                .as(String.format("Data value '%s' is not equal to '%s'", coverageValue, "1 GB"))
                .isEqualTo("1 GB");
        String validityValue = packageDetailsPage.getValidityValue();
        softAssertions.assertThat(validityValue)
                .as(String.format("Validity value '%s' is not equal to '%s'", validityValue, "7 Days"))
                .isEqualTo("7 Days");
        String priceValue = packageDetailsPage.getPriceValue();
        softAssertions.assertThat(priceValue)
                .as(String.format("Price value '%s' is not equal to '%s'", priceValue, "$4.50 USD"))
                .isEqualTo("$4.50 USD");
        homePage.close();
        softAssertions.assertAll();
    }
}
