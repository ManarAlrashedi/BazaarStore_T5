package com.bazaarstores.stepDefinitions;

import io.cucumber.java.en.*;

public class UsersSteps {

    @Given("the admin is on the Add User page")
    public void the_admin_is_on_the_add_user_page() {
        System.out.println("Admin is on Add User page");
    }

    @When("the admin enters valid Name, Email, Password, and Role")
    public void the_admin_enters_valid_name_email_password_and_role() {
        System.out.println("Admin enters valid user details");
    }

    @When("clicks Save")
    public void clicks_save() {
        System.out.println("Admin clicks save");
    }

    @Then("the user should be added successfully")
    public void the_user_should_be_added_successfully() {
        System.out.println("User added successfully");
    }

    @Then("appear in the user list")
    public void appear_in_the_user_list() {
        System.out.println("User appears in the user list");
    }
}
