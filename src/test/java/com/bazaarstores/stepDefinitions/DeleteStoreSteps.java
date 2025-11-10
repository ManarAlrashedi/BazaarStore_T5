package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class DeleteStoreSteps {

    AllPages allPages = new AllPages();

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
}
