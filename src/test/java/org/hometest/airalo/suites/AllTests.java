package org.hometest.airalo.suites;

import org.hometest.airalo.tests.api.ApiTests;
import org.hometest.airalo.tests.ui.ValidateESimOptions;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApiTests.class,
        ValidateESimOptions.class
})

public class AllTests {
}
