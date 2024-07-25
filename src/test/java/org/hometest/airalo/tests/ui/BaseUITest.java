package org.hometest.airalo.tests.ui;

import org.hometest.airalo.BaseTest;
import org.hometest.airalo.pages.HomePage;
import org.junit.After;

public class BaseUITest extends BaseTest {

    @After
    public void cleanUp() {
        try {
            HomePage webApp = new HomePage();
            webApp.close();
        } catch (Exception ex) {
            System.out.println("Already closed");
        }

    }
}
