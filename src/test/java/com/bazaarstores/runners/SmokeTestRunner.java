package com.bazaarstores.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/registration.feature",
                "src/test/resources/features/login.feature",
                "src/test/resources/features/Customer.feature",
                "src/test/resources/features/storeManager.feature",
                "src/test/resources/features/admin_actions_stores.feature",
                "src/test/resources/features/admin_actions_users.feature",
                "src/test/resources/features/stores.feature"
        },
        tags = "@customer",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class SmokeTestRunner {
}