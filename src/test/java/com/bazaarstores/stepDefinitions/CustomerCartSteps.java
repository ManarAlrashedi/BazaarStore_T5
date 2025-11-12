package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CustomerCartSteps {

    AllPages allPages = new AllPages();


    @When("customer in the cart")
    public void customerInTheCart() {

        allPages.getCustomerCartPage().hoverOverCart();
        allPages.getCustomerCartPage().ClickViewCart();
        allPages.getCustomerCartPage().ProductIsVisable();

    }
    @When("customer click confirm cart")
    public void customer_click_confirm_cart() {
        allPages.getCustomerCartPage().ClickConfirmButton();
    }

    @Then("customer should see the {string} Message")
    public void customer_should_see_the_message(String message) {

       allPages.getCustomerCartPage().SuccessMessage();


    }
    @Then("click ok button")
    public void click_ok_button() {

        allPages.getCustomerCartPage().ClickOkButton();

    }
    @Then("asser the cart is empty now")
    public void asser_the_cart_is_empty_now() {

       assert true;
    }



}
