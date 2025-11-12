package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class AdminActionsForUsersSteps {

    AllPages allPages = new AllPages();

    @When("admin user navigates to user management page")
    public void admin_user_navigates_to_user_management_page() {
        allPages.getDashboardPage().clickUsersLink();
        Assert.assertTrue("Users Page Should be Displayed"
                ,allPages.getUsersPage().isUsersPageDisplayed());
    }
    @When("admin user should see the users list with name, email and actions columns")
    public void admin_user_should_see_the_users_list_with_name_email_role_and_actions_columns() {
        Assert.assertTrue("User Columns should be displayed"
                ,allPages.getUsersPage().areUserColumnsDisplayed());
    }
    @When("verify at least one user exists in the system")
    public void verify_at_least_one_user_exists_in_the_system() {
        Assert.assertTrue("At least one user should exist",
                allPages.getUsersPage().getUsersCount() > 0);
    }
    @When("admin user clicks delete button for a user {string}")
    public void admin_user_clicks_delete_button_for_a_user(String nameUser) {
        allPages.getUsersPage().clickDeleteUser(nameUser);
        Assert.assertTrue("Confirmation dialog should be displayed",
                allPages.getUsersPage().isDeleteConfirmationDialogDisplayed());
    }
    @Then("the user {string} it does not deleted due to a bug")
    public void the_user_it_does_not_deleted_due_to_a_bug(String nameUser) {
        Assert.assertTrue("User wasn't removed from the list",
                allPages.getUsersPage().isUserStillPresentInList(nameUser));
    }
    @Then("the user {string} should still be present in the users list")
    public void the_user_should_still_be_present_in_the_users_list(String nameUser) {
        Assert.assertTrue("User should still be present in the list",
                allPages.getUsersPage().isUserStillPresentInList(nameUser));
    }

}
