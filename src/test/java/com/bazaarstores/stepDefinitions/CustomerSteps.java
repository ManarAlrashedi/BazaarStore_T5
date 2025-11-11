package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CustomerSteps {
    AllPages allPages = new AllPages();

    @When("customer user in customer page")
    public void customer_user_in_customer_page() {
        Assert.assertTrue("Coustomer should be displayed",
                allPages.getCustomerPage().isCoustomerPageDisplayed());
    }
    @When("the customer clicks Add to Cart on a product")
    public void the_customer_clicks_on_a_product() {
allPages.getCustomerPage().addFirstProductToCart();
               // allPages.getCustomerPage().successMessage();
    }
    @Then("the cart item count should update automatically")
    public void the_cart_item_count_should_update_automatically() throws InterruptedException {
allPages.getCustomerPage().checkCartCountChanged();
    }

}
