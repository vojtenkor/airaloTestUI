package org.hometest.airalo;

import org.assertj.core.api.SoftAssertions;
import org.hometest.airalo.pages.HomePage;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class BaseTest {
    protected Properties prop = new Properties();
    protected SoftAssertions softAssertions = new SoftAssertions();

    @Before
    public void loadProperties() {
        try (InputStream input = Files.newInputStream(Paths.get("src/test/java/resources/test.properties"))) {

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("app.url"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

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
