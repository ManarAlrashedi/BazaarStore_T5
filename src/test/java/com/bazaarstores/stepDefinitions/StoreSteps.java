package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import com.github.javafaker.Faker;
import com.sun.source.tree.AssertTree;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class StoreSteps {

    AllPages allPages = new AllPages();

    public static String name=Faker.instance().name().name();
    public static String description =Faker.instance().lorem().sentence();
    public static String loaction=Faker.instance().address().country();
    public static String admin=Faker.instance().name().name();
    public static String originalName;


    @When("admin user navigates to store management page")
    public void admin_user_navigates_to_store_management_page() {
        allPages.getDashboardPage().clickStoreLink();
        Assert.assertTrue("Stores Page Should be Displayed"
                ,allPages.getStoresPage().isStoresPageDisplayed());
    }

    @When("verify at least one store exists in the system")
    public void at_least_one_store_exists_in_the_system() {
        Assert.assertTrue("At least one store should exist",
                allPages.getStoresPage().getStoresCount() > 0);
    }

    @When("admin user clicks delete button for a store")
    public void admin_user_clicks_delete_button_for_a_store() {
        allPages.getStoresPage().clickDeleteStore();
        Assert.assertTrue("Confirmation dialog should be displayed",
                allPages.getStoresPage().isDeleteConfirmationDialogDisplayed());
    }

    @When("admin user confirms the deletion")
    public void admin_user_confirms_the_deletion() {
        allPages.getStoresPage().confirmDeleteStore();
        Assert.assertTrue("Store should be deleted successfully",
                allPages.getStoresPage().isStoreDeletedMessageDisplayed());
    }

    @Then("the store should be removed from the store list")
    public void the_store_should_be_removed_from_the_store_list() {
        Assert.assertTrue("Store should be removed from the list",
                allPages.getStoresPage().isStorePresentInList());
    }

    @Given("user is on the stores page")
    public void user_is_on_the_stores_page() {
        allPages.getDashboardPage().clickStoreLink();
    }

    @When("user clicks edit button")
    public void user_clicks_edit_button() {allPages.getStoresPage().edit();}

    @When("user edits name")
    public void user_edits_name() {
        allPages.getStoresPage().name(StoreSteps.name);
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
       allPages.getStoresPage().admins(StoreSteps.admin);
    }


    @Then("assert Changes reflect in the stores list")
    public void assert_changes_reflect_in_the_stores_list() {
        allPages.getStoresPage().isUpdated(StoreSteps.name,StoreSteps.description,StoreSteps.admin);
    }


}
