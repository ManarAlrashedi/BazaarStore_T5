package com.bazaarstores.stepDefinitions;


import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import static com.bazaarstores.stepDefinitions.LoginSteps.loginEmail;
import static com.bazaarstores.stepDefinitions.ProductsSteps.catalog;
import static com.bazaarstores.stepDefinitions.ProductsSteps.price;
import static com.bazaarstores.stepDefinitions.RegistrationSteps.email;
import static com.bazaarstores.stepDefinitions.RegistrationSteps.fullName;
import static com.bazaarstores.utilities.ApiUtilities.spec;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ApiSteps {

    @When("assert the registration via API")
    public void assertTheRegistrationViaAPI() {
        Response response = given(spec()).get("/users");
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.getString("find{it.email=='" + email + "'}.name");
        String actualEmail = jsonPath.getString("find{it.email=='" + email + "'}.email");
        assertEquals(email, actualEmail);
        assertEquals(fullName, actualName);
    }

    @And("assert the negative registration via API using email {string}")
    public void assertTheNegativeRegistrationViaAPIUsingEmail(String email) {
        Response response = given(spec()).get("/users");
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        assertNull(jsonPath.getString("find{it.email=='" + email + "'}.name"));
        assertNull(jsonPath.getString("find{it.email=='" + email + "'}.email"));
    }

    @And("assert the successful login via API")
    public void assertTheSuccessfulLoginViaAPI() {

        Response response = given(spec()).get("/users");
        JsonPath jsonPath = response.jsonPath();

        String actualEmail = jsonPath.getString("find{it.email=='" + loginEmail + "'}.email");
        String actualName = jsonPath.getString("find{it.email=='" + loginEmail + "'}.name");
        String userRole = jsonPath.getString("find{it.email=='" + loginEmail + "'}.role");

        assertEquals(loginEmail, actualEmail);
        assertNotNull(actualName);
        assertNotNull(userRole);
    }

    @And("assert the negative login via API")
    public void assertTheNegativeLoginViaAPI() {

        Response response = given(spec()).get("/users");
        JsonPath jsonPath = response.jsonPath();

        String actualEmail = jsonPath.getString("find{it.email=='" + loginEmail + "'}.email");
        String actualName = jsonPath.getString("find{it.email=='" + loginEmail + "'}.name");
        String userRole = jsonPath.getString("find{it.email=='" + loginEmail + "'}.role");

        assertNull(actualEmail);
        assertNull(actualName);
        assertNull(userRole);
    }

    @And("assert the negative login via API using email {string}")
    public void assertTheNegativeLoginViaAPIUsingEmail(String email) {

        Response response = given(spec()).get("/users");
        JsonPath jsonPath = response.jsonPath();

        String actualEmail = jsonPath.getString("find{it.email=='" + email + "'}.email");
        String actualName = jsonPath.getString("find{it.email=='" + email + "'}.name");

        assertNotNull(actualEmail);
        assertNotNull(actualName);
    }

    @Then("assert the products catalog via API")
    public void assert_the_products_catalog_via_api() {
        Response response = given(spec()).get("/products");
        JsonPath jsonPath = response.jsonPath();
        String actualCatalog = jsonPath.getString("find{it.email=='" + email + "'}.catalog");
        assertEquals(catalog, actualCatalog);
    }

    @Then("assert the price catalog via API")
    public void assert_the_price_catalog_via_api() {
        Response response = given(spec()).get("/products");
        JsonPath jsonPath = response.jsonPath();
        String actualPrice = jsonPath.getString("find{it.email=='" + email + "'}.catalog");
        assertEquals(price, actualPrice);
    }

    @Then("assert the store deletion via API")
    public void assert_the_store_deletion_via_api() {
        Response response = given(spec()).get("/stores");
        JsonPath jsonPath = response.jsonPath();
        String actualStoreName = jsonPath.getString("find{it.email=='" + email + "'}.storeName");
        assertNull(actualStoreName);
    }


    @Then("assert the product been removed via API")
    public void assertTheProductBeenRemovedViaAPI() {
        Response response = given(spec()).get("/products");
        JsonPath jsonPath = response.jsonPath();
        String actualProductName = jsonPath.getString("find{it.email=='" + email + "'}.productName");
        assertNull(actualProductName);
    }

    @And("assert the user deletion via API")
    public void assertTheUserDeletionViaAPI() {
        Response response = given(spec()).get("/users");
        JsonPath jsonPath = response.jsonPath();
        String actualUserEmail = jsonPath.getString("find{it.email=='" + email + "'}.email");
        assertNull(actualUserEmail);
    }
}

