package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.CustomerPage;
import com.bazaarstores.pages.LoginPage;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.By.cssSelector;

public class CustomerPageSteps {

    LoginPage loginPage = new LoginPage();
    public CustomerPage customerPage = new CustomerPage();

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ================= US04 =================
    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        Driver.getDriver().get(ConfigReader.getBaseUrl());
        loginPage.enterEmail(ConfigReader.getCustomerEmail())
                .enterPassword(ConfigReader.getDefaultPassword())
                .clickLoginButton();
        sleep(1000);
    }

    @Given("the user navigates to the Customer Page")
    public void the_user_navigates_to_the_customer_page() {
        customerPage.openCustomerPage();
        sleep(1000);
    }

    @When("the user observes the displayed products")
    public void the_user_observes_the_displayed_products() {

        List<WebElement> products = Driver.getDriver().findElements(By.cssSelector(".product-card"));


        System.out.println("Number of products displayed: " + products.size());


        Assert.assertTrue("No products are displayed on the page!", products.size() > 0);
    }


    @When("the user scrolls to load all products")
    public void the_user_scrolls_to_load_all_products() throws InterruptedException {
        int oldCount = 0;
        int newCount = customerPage.findElements(customerPage.productCards).size();

        while (newCount > oldCount) {
            oldCount = newCount;
            customerPage.scrollToBottom();
            Thread.sleep(1000);
            newCount = customerPage.findElements(customerPage.productCards).size();
        }
    }

    @Then("the products should be visible on the page")
    public void the_products_should_be_visible_on_the_page() {
        Assert.assertTrue("Products should be visible on the page",
                customerPage.areProductsVisible());
        sleep(500);
    }

    @Then("each product should show its Name, Price, Description, and Image")
    public void each_product_should_show_its_details() {
        Assert.assertTrue("Each product should display all required details",
                customerPage.areProductDetailsDisplayed());
        sleep(500);
    }

    @When("the user refreshes the Customer Page")
    public void the_user_refreshes_the_customer_page() {
        customerPage.refreshPage();
        sleep(1000);
    }

    @Then("the products should load quickly and display complete details")
    public void the_products_should_load_quickly_and_display_complete_details() {
        Assert.assertTrue("Page load time is too long!",
                customerPage.isPageLoadedQuickly());
        Assert.assertTrue("Products are missing details!",
                customerPage.areProductDetailsDisplayed());
        sleep(500);
    }
    @When("the user clicks on each product in the grid")
    public void the_user_clicks_on_each_product_in_the_grid() {
        customerPage.clickEachProductAndCheckDetails();
    }

    @Then("the product details \\(Name, Price, Description, Image\\) should be displayed")
    public void the_product_details_name_price_description_image_should_be_displayed() {
        List<WebElement> products = Driver.getDriver().findElements(By.cssSelector(".product-card"));

        for (int i = 0; i < products.size(); i++) {
            WebElement product = products.get(i);

            WebElement name = product.findElement(By.cssSelector(".product-name"));
            WebElement desc = product.findElement(By.cssSelector(".product-description"));
            WebElement price = product.findElement(By.cssSelector(".current-price"));
            WebElement image = product.findElement(By.cssSelector(".product-image"));

            Assert.assertTrue("Product name missing for product " + (i+1), name.isDisplayed());
            Assert.assertTrue("Product description missing for product " + (i+1), desc.isDisplayed());
            Assert.assertTrue("Product price missing for product " + (i+1), price.isDisplayed());
            Assert.assertTrue("Product image missing for product " + (i+1), image.isDisplayed());

            System.out.println("✅ Product " + (i+1) + " details are displayed correctly.");
        }
    }


}








