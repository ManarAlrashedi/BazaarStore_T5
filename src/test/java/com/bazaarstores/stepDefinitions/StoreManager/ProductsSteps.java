package com.bazaarstores.stepDefinitions.StoreManager;

import com.bazaarstores.pages.AllPages;
import com.github.javafaker.Faker;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.junit.Assert;

import static com.bazaarstores.utilities.Driver.setImplicitWait;


public class ProductsSteps {

    AllPages allPages = new AllPages();
    public static String price= Faker.instance().number().digits(2).toString();
    public static String catalog;
    public static String name;

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
    @And("clicks the cancel button")
    public void clicksTheButton() {
        allPages.getProductsPage().clickCancelButton();
    }
    @Then("the product should still exist in the catalog")
    public void theProductShouldStillExistInTheCatalog() {
        Assert.assertTrue("This product still exist ! ",
        allPages.getProductsPage().isProductsDisplayed());
    }
    @When("confirms the deletion")
    public void confirms_the_deletion() {
        allPages.getProductsPage().clickConfirmDeleteButton();
    }
    @Then("a success message should appear")
    public void a_success_message_should_appear() {
        allPages.getProductsPage().SuccessDeleteMessage();
        setImplicitWait();
    }
    @And("the catalog should reflect the updated state")
    public void theCatalogShouldReflectTheUpdatedState() {
        Assert.assertFalse(allPages.getProductsPage().isProductsDisplayed());
    }

    @And("user goes to the products page")
    public void user_goes_to_the_products_page() {
        allPages.getDashboardPage().clickProductsLink();
    }
    @When("user clicks the edit button")
    public void user_clicks_the_edit_button()
    {allPages.getProductsPage().edit();}
    @When("user  Edit the price")
    public void user_edit_the_price() {
        allPages.getProductsPage().price(price);
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
    public void user_clicks_the_submit_button() {
        allPages.getProductsPage().submit();}
    @Then("user should see a success message for the update")
    public void user_should_see_a_success_message_for_the_update() {
        allPages.getProductsPage().successMessage();
    }
    @Then("user should see a error message for required field")
    public void user_should_see_a_error_message_for_required_field() {
        allPages.getProductsPage().missingRequiredFieldMessage();
    }

    @When("the Store Manager clicks the Add New Product button")
    public void the_store_manager_clicks_the_button() {
        allPages.getProductsPage().clickAddNewProductButton();
    }
    @When("fills in the product details")
    public void fills_in_the_product_details() {
        allPages.getProductsPage().enterNameProduct("Harry Potter")
                .enterPriceProduct(49.99)
                .enterStockProduct(100)
                .enterSKUProduct("HP1001")
                .selectCategory("Books");
    }
    @When("submits the new product form")
    public void submits_the_new_product_form() {
        allPages.getProductsPage().submit();
    }
    @Then("should see a success message for the addition and the product display in list")
    public void should_see_a_success_message_for_the_addition_and_the_product_display_in_list() {
        allPages.getProductsPage().successMessage();
        Assert.assertTrue("New product should be displayed in the list",
                allPages.getProductsPage().isNewProductDisplayed("Harry Potter"));
    }
    @And("fills in the product details with missing required fields")
    public void fillsInTheProductDetailsWithMissingRequiredFields() {
        allPages.getProductsPage().enterNameProduct("Harry Potter")
                .enterPriceProduct(49.99)
                .enterSKUProduct("HP1002")
                .selectCategory("Books");
    }
    @Then("should see a error message for the addition")
    public void shouldSeeAErrorMessageForTheAddition() {
        allPages.getProductsPage().missingRequiredFieldMessage();
    }
    @And("fills in the product details with duplicate product SKU")
    public void fillsInTheProductDetailsWithDuplicateProductSKU() {
        allPages.getProductsPage().enterNameProduct("Harry Potter")
                .enterPriceProduct(49.99)
                .enterStockProduct(100)
                .enterSKUProduct("HP1001")
                .selectCategory("Books");
    }
    @Then("should see a error message for the duplication")
    public void shouldSeeAErrorMessageForTheDuplication() {
        allPages.getProductsPage().duplicateSKUMessage();
    }
}
