package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class ProductsSteps {

    AllPages allPages = new AllPages();

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


    @When("the Store Manager clicks the delete button for a specific item")
    public void the_store_manager_clicks_the_button_for_a_specific_item() {
        allPages.getProductsPage().clickDeleteButton();

    }

    @When("confirms the deletion")
    public void confirms_the_deletion() {

        allPages.getProductsPage().clickConfirmDeleteButton();
    }

    @Then("a success message should appear")
    public void a_success_message_should_appear() {
       allPages.getProductsPage().SuccessDeleteMessage();

    }

    @And("clicks the cancel button")
    public void clicksTheButton(String cancel) {

    }

    @Then("the product should still exist in the catalog")
    public void theProductShouldStillExistInTheCatalog() {

    }

    @When("they search for the deleted proof {string}")
    public void theySearchForTheDeletedProof() {

    }

    @Then("{string} should not appear in the catalog list")
    public void shouldNotAppearInTheCatalogList() {

    }

    @And("the catalog should reflect the updated state")
    public void theCatalogShouldReflectTheUpdatedState() {

    }
}
