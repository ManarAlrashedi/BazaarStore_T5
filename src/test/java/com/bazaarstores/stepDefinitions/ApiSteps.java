package com.bazaarstores.stepDefinitions;


import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


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
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @And("assert the product been removed via API")
    public void assertTheProductBeenRemovedViaAPI() {
        Response response = given(spec()).get("/products");
        JsonPath jsonPath = response.jsonPath();
        assertNull(jsonPath.getString(
                "find{it.email=='" + email + "'}.catalog "
                        +"find{it.email=='" + email + "'}.NAME"
                        + "find{it.email=='" + email + "'}.PRICE"
                        + "find{it.email=='" + email + "'}.STOCK"
                        + "find{it.email=='" + email + "'}.CATEGORY"

        ));
    }
}
