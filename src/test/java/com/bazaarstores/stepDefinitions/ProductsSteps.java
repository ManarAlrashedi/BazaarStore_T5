package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class ProductsSteps {

    AllPages allPages = new AllPages();
    public static String price;
    public static String catalog;

    @When("store manager navigates to the products page")
    public void store_manager_navigates_to_the_products_page() {
        allPages.getDashboardPage().clickProductsLink();
    }

    @Then("verify the list of products displayed")
    public void verify_the_list_of_products_displayed() {
        Assert.assertTrue("Products page should be displayed",
                allPages.getProductsPage().isProductsPageDisplayed());
        Assert.assertTrue("Products table should be displayed",
                allPages.getProductsPage().isProductsTableDisplayed());
    }

    @Then("each product should have a name, price, stoke, category, image, and action buttons")
    public void each_product_should_have_a_name_price_stoke_category_image_and_action_buttons() {
        Assert.assertTrue("Each product should have name, price, stock, category, image, and action buttons",
                allPages.getProductsPage().areProductDetailsDisplayed());
    }

    @And("user goes to the products page")
    public void user_goes_to_the_products_page() {
        allPages.getDashboardPage().clickProductsLink();
    }

    @When("user clicks the edit button")
    public void user_clicks_the_edit_button() {allPages.getProductsPage().edit();}

    @When("user  Edit the price")
    public void user_edit_the_price() {
        allPages.getProductsPage().price("400.00");
    }

    @When("user  Edit the catalog")
    public void user_edit_the_catalog() {
        allPages.getProductsPage().catalog();
    }

    @When("user clear the stock")
    public void user_clear_the_stock() {
        allPages.getProductsPage().stock();
    }

    @Then("user clicks the submit button")
    public void user_clicks_the_submit_button() {allPages.getProductsPage().submit();}

    @Then("user should see a success message for the update")
    public void user_should_see_a_success_message_for_the_update() {
        allPages.getProductsPage().successMessage();
    }

    @Then("user should see a error message for required field")
    public void user_should_see_a_error_message_for_required_field() {allPages.getProductsPage().missingRequiredFieldMessage();}


}
