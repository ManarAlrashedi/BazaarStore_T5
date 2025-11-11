package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import com.github.javafaker.Faker;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class UsersSteps {
    AllPages allPages = new AllPages();
    Faker faker = new Faker();

    @When("admin user navigates to users management page")
    public void admin_user_navigates_to_users_management_page() {
        allPages.getDashboardPage().clickUsersLink();
        Assert.assertTrue("Users Page Should be Displayed"
                ,allPages.getUsersPage().isUsersPageDisplayed());
    }
    @When("admin user click add users button")
    public void admin_user_click_add_users_button() {
        allPages.getUsersPage().clickAddUser();
        Assert.assertTrue("Add Users Page Should be Displayed"
                ,allPages.getCreateUserPage().isAddUsersPageDisplayed());
    }
    @And("admin user enters valid {string}, {string},{string},{string} and {string}")
    public void adminUserEntersValidAnd(String name , String email, String password, String passwordConf, String role) {
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

    @Then("verify the user in list")
    public void verify_the_user_in_list() {
        Assert.assertTrue("Admin Should be see user"
                ,allPages.getUsersPage().isNameAdded());
    }

}
