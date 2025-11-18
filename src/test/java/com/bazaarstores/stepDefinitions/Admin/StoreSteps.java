package com.bazaarstores.stepDefinitions.Admin;

import com.bazaarstores.pages.AllPages;
import com.github.javafaker.Faker;
import io.cucumber.java.en.*;

public class StoreSteps {

    AllPages allPages = new AllPages();

    public static String name;
    public static String description =Faker.instance().lorem().sentence();
    public static String loaction=Faker.instance().address().country();
    public static String admin;
    public static String originalName;

    @Given("user is on the stores page")
    public void user_is_on_the_stores_page() {
        allPages.getDashboardPage().clickStoreLink();
    }

    @When("user clicks edit button")
    public void user_clicks_edit_button() {allPages.getStoresPage().edit();}

    @When("user edits name")
    public void user_edits_name() {
        allPages.getStoresPage().name("booksStore");
    }

    @When("user clears name")
    public void user_clears_name() {
        allPages.getStoresPage().clearName();
    }

    @When("user edits description")
    public void user_edits_description() {
        allPages.getStoresPage().description(StoreSteps.description);    }

    @When("user edits location")
    public void user_edits_location() {
        allPages.getStoresPage().location(StoreSteps.loaction);
    }
    @When("user edits admins")
    public void user_edits_admins() {
        allPages.getStoresPage().admins();
    }

    @Then("assert Changes reflect in the stores list")
    public void assert_changes_reflect_in_the_stores_list() {
        allPages.getStoresPage().isUpdated(StoreSteps.name,StoreSteps.description,StoreSteps.admin);
    }


}