package com.bazaarstores.stepDefinitions.Admin;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class AdminActionsForUsersSteps {

    AllPages allPages = new AllPages();

    //---------------------------------- View User Steps ------------------------Nouf
    @When("admin user navigates to users management page")
    public void admin_user_navigates_to_users_management_page() {
        allPages.getDashboardPage().clickUsersLink();
        Assert.assertTrue("Users Page Should be Displayed"
                ,allPages.getUsersPage().isUsersPageDisplayed());
    }
    @When("Admin enters {string} in search field and clicks search")
    public void admin_enters_in_search_field_and_clicks_search(String email) {
        allPages.getUsersPage().enterSearchEmail(email);
        allPages.getUsersPage().clickSearch();
    }
    @Then("Admin should see all registered users with Name and Email")
    public void admin_should_see_all_registered_users_with_name_and_email() {
        int totalUsers = allPages.getUsersPage().getAllUsers().size();
        Assert.assertTrue("Expected at least one user to be displayed, but found none",
                totalUsers > 0);
    }
    @Then("Only the user with matching email is displayed")
    public void only_the_user_with_matching_email_is_displayed() {
        Assert.assertEquals(1, allPages.getUsersPage().getAllUsers().size());
    }
    @Then("A message {string} is displayed")
    public void a_message_is_displayed(String message) {
        Assert.assertTrue(allPages.getUsersPage().isNoUsersMessageDisplayed());
    }

//---------------------------------- Add User Steps ------------------------Hajar
    @When("admin user click add users button")
    public void admin_user_click_add_users_button() {
        allPages.getUsersPage().clickAddUser();
    }
    @And("admin user enters valid {string}, {string},{string},{string} and {string}")
    public void adminUserEntersValidAnd(String name , String email, String password, String passwordConf, String role) {
        allPages.getUsersPage().refreshPage();
        allPages.getCreateUserPage().enterName(name)
                .enterEmail(email)
                .enterPassword(password)
                .enterPasswordConf(passwordConf)
                .enterRole(role);
    }
    @When("admin user clicks Submit button")
    public void admin_user_clicks_submit_button() {
        allPages.getCreateUserPage().clickSubmit();
    }
    @Then("the user should be added successfully")
    public void the_user_should_be_added_successfully() {
        allPages.getUsersPage().successMessage();
    }
    @Then("the user should be see error message")
    public void theUserShouldBeSeeErrorMessage() {
        allPages.getCreateUserPage().errorMessage();
    }

    //---------------------------------- Edit User Steps ------------------------Abdulrahman
    @When("admin user clicks edit button for a user {string}")
    public void admin_user_clicks_edit_button_for_a_user(String name) {
        allPages.getUsersPage().clickEditUser(name);
    }
    @When("admin user updates user details with valid {string} and {string}")
    public void admin_user_updates_user_details_with_valid(String updatedName, String updatedEmail) {
        allPages.getCreateUserPage().enterName(updatedName)
                .enterEmail(updatedEmail)
                .enterPassword("Pasword.12345")
                .enterPasswordConf("Pasword.12345");
    }
    @When("admin user clicks Save button")
    public void admin_user_clicks_save_button() {
        allPages.getCreateUserPage().clickSubmit();
    }
    @Then("the user details should be updated successfully")
    public void the_user_details_should_be_updated_successfully() {
        allPages.getUsersPage().successMessage();
    }

    //---------------------------------- Delete User Steps ------------------------Norah
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
                allPages.getUsersPage().isUserStillPresentInListBug(nameUser));
    }
    @Then("the user {string} should still be present in the users list")
    public void the_user_should_still_be_present_in_the_users_list(String nameUser) {
        Assert.assertTrue("User should still be present in the list",
                allPages.getUsersPage().isUserStillPresentInList(nameUser));
    }
}
