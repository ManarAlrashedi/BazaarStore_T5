package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class StoresSteps {

    AllPages allPages = new AllPages();

    @Given("the admin is logged in")
    public void the_admin_is_logged_in() {
        allPages.getLoginPage()
                .enterEmail("admin@example.com")  // تأكدي من استخدام بيانات صحيحة
                .enterPassword("AdminPassword123")
                .clickLoginButton();

        Assert.assertTrue("Dashboard should be displayed",
                allPages.getDashboardPage().isDashboardPageDisplayed());
    }



/*
    @When("admin navigates to the stores page")
    public void admin_navigates_to_the_stores_page() {
        allPages.getDashboardPage().clickStoreLink();
    }

    @Then("verify the list of stores displayed")
    public void verify_the_list_of_stores_displayed() {
        Assert.assertTrue("Stores page should be displayed",
                allPages.getStoresPage().isStoresPageDisplayed());
        Assert.assertTrue("Stores table should be displayed",
                allPages.getStoresPage().isStoresTableVisible());
    }

    @Then("each store should have a name, description, admin name, and action button")
    public void each_store_should_have_a_name_description_admin_name_and_action_button() {
        Assert.assertTrue("Each store should have name, description, admin name, and action button",
                allPages.getStoresPage().areStoreDetailsDisplayed());
    }
*/

}