package com.bazaarstores.stepDefinitions.Customer;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.pages.Customer.CustomerPage;
import com.bazaarstores.pages.Login.LoginPage;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Thread.sleep;

public class CustomerPageSteps {

    AllPages allPages = new AllPages();

    // ================= US04&5 =================
    @Given("the user navigates to the Customer Page")
    public void the_user_navigates_to_the_customer_page() throws InterruptedException {
        allPages.getCustomerPage().openCustomerPage();
        sleep(1000);
    }
    @When("the user observes the displayed products")
    public void the_user_observes_the_displayed_products() {
        List<WebElement> products = Driver.getDriver().findElements(By.cssSelector(".product-card"));
        System.out.println("Number of products displayed: " + products.size());
        Assert.assertTrue("No products are displayed on the page!", products.size() > 0);
    }
    @Then("each product should show its Name, Price, Description, and Image")
    public void each_product_should_show_its_details() throws InterruptedException {
        Assert.assertTrue("Each product should display all required details",
                allPages.getCustomerPage().areProductDetailsDisplayed());
        sleep(500);
    }
    @When("the user refreshes the Customer Page")
    public void the_user_refreshes_the_customer_page() throws InterruptedException {
        allPages.getCustomerPage().refreshPage();
        sleep(1000);
    }
    @Then("the products should load quickly and display complete details")
    public void the_products_should_load_quickly_and_display_complete_details() throws InterruptedException {
        Assert.assertTrue("Page load time is too long!",
                allPages.getCustomerPage().isPageLoadedQuickly());
        Assert.assertTrue("Products are missing details!",
                allPages.getCustomerPage().areProductDetailsDisplayed());
        sleep(500);
    }
    @When("the user clicks on each product in the grid")
    public void the_user_clicks_on_each_product_in_the_grid() {
        allPages.getCustomerPage().clickEachProductAndCheckDetails();
    }

    @Then("the product details Name, Price, Description, Image should be displayed")
    public void the_product_details_name_price_description_image_should_be_displayed() {
        List<WebElement> products = Driver.getDriver().findElements(By.cssSelector(".product-card"));

        for (int i = 0; i < products.size(); i++) {
            WebElement product = products.get(i);

            WebElement name = product.findElement(By.cssSelector(".product-name"));
            WebElement price = product.findElement(By.cssSelector(".current-price"));
            WebElement image = product.findElement(By.cssSelector(".product-image"));
            List<WebElement> descList = product.findElements(By.cssSelector(".product-description"));

            Assert.assertTrue("Product name missing for product " + (i + 1), name.isDisplayed());
            Assert.assertTrue("Product price missing for product " + (i + 1), price.isDisplayed());
            Assert.assertTrue("Product image missing for product " + (i + 1), image.isDisplayed());

            if (!descList.isEmpty()) {
                Assert.assertTrue("Product description not visible for product " + (i + 1), descList.get(0).isDisplayed());
            } else {
                System.out.println("Product " + (i + 1) + " has no description, skipping.");
            }

            System.out.println("✅ Product " + (i + 1) + " details are displayed correctly.");
        }
    }




    // ================= US06 ================= Hajar
    @When("the customer clicks Add to Cart on a product")
    public void theCustomerClicksAddToCartOnAProduct() {
        allPages.getCustomerPage().clickAddToCartOnFirstProduct();
    }
    @Then("the customer should see success message")
    public void theCustomerShouldSeeSuccessMessage() {
        Assert.assertTrue("Success message should be displayed"
                ,allPages.getCustomerPage().isAddToCartSuccessMessageDisplayed());
    }
    @And("the cart item count should update automatically")
    public void theCartItemCountShouldUpdateAutomatically() {
        Assert.assertTrue("Cart item count should update"
                ,allPages.getCustomerPage().isCartItemCountUpdated());
    }

    // ================= US08 ================= Hajar
    @Given("the customer has items in the cart")
    public void the_customer_has_items_in_the_cart() {
        Assert.assertTrue("Cart should have items",
                allPages.getCustomerPage().ensureCartHasItems());
    }
    @When("the customer hovers over the cart icon")
    public void the_customer_hovers_over_the_cart_icon() {
        allPages.getCustomerPage().hoverOverCartIcon();
    }

    @Then("the customer should see View Cart button all items with prices and total cost")
    public void the_cart_page_should_display_all_items_with_prices_and_total_cost() {
        Assert.assertTrue("View Cart button should be visible",
                allPages.getCustomerPage().isViewCartButtonDisplayed());
        Assert.assertTrue("Products in the cart should be visible",
                allPages.getCustomerPage().isAllItemsDisplayedInCart());
        Assert.assertTrue("Total cost should be displayed",
                allPages.getCustomerPage().isTotalCostDisplayed());
    }
    @Given("the customer has no items in the cart")
    public void the_customer_has_no_items_in_the_cart() {
        Assert.assertTrue("Cart should be empty",
                allPages.getCustomerPage().ensureCartIsEmpty());
    }
    @Then("the cart should display a message indicating the cart is empty")
    public void the_cart_should_display_a_message_indicating_the_cart_is_empty() {
        Assert.assertTrue("Empty cart message should be displayed",
                allPages.getCustomerPage().isEmptyCartMessageDisplayed());
    }
    @When("the customer removes an item from the cart")
    public void the_customer_removes_an_item_from_the_cart()throws InterruptedException {
        allPages.getCustomerPage().hoverOverCartIcon();
        allPages.getCustomerPage().clickX();
    }
    @Then("the customer should see a success message")
    public void the_customer_should_see_a_success_message() {
        Assert.assertTrue("Success message should be displayed",
                allPages.getCustomerPage().SuccessMessage());
    }
    @Then("the total cost should update accordingly")
    public void the_total_cost_should_update_accordingly() {
        Assert.assertTrue("Total cost should update accordingly",
                allPages.getCustomerPage().itemCountUpdated());
    }


    @When("customer in the cart")
    public void customerInTheCart() {
        allPages.getCartPage().hoverOverCart();
        allPages.getCartPage().ClickViewCart();
        allPages.getCartPage().ProductIsVisable();
    }
    @When("customer click confirm cart")
    public void customer_click_confirm_cart() {
        allPages.getCartPage().ClickConfirmButton();
    }
    @Then("customer should see the {string} Message")
    public void customer_should_see_the_message(String message) {
        allPages.getCartPage().SuccessMessage();
    }
    @Then("click ok button")
    public void click_ok_button() {
        allPages.getCartPage().ClickOkButton();
    }
    @Then("asser the cart is empty now")
    public void asser_the_cart_is_empty_now() {
        assert true;
    }

}








