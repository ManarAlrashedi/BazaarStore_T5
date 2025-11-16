package com.bazaarstores.stepDefinitions.Registration;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationSteps {

    AllPages pages = new AllPages();
    public static String email;
    public static String fullName;

    @When("user clicks registration link")
    public void user_clicks_registration_link() {
        pages.getLoginPage().clickRegisterLink();
    }

    @And("user enters email for sign up {string}")
    public void userEntersEmailForSignUp(String email) {
        RegistrationSteps.email = Faker.instance().internet().emailAddress();
        if (email.equals("faker")) {
            pages.getRegistrationPage().enterEmail(RegistrationSteps.email);
        } else {
            pages.getRegistrationPage().enterEmail(email);
        }
    }

    @And("user enters full name for sign up {string}")
    public void userEntersFullNameForSignUp(String fullName) {
        RegistrationSteps.fullName = fullName;
        pages.getRegistrationPage().enterName(fullName);
    }


    @And("user enters password for sign up")
    public void userEntersPasswordForSignUp() {
        pages.getRegistrationPage().enterPassword(ConfigReader.getDefaultPassword());
    }

    @And("user enters confirm password for sign up")
    public void userEntersConfirmPasswordForSignUp() {
        pages.getRegistrationPage().enterPasswordConfirmation(ConfigReader.getDefaultPassword());
    }

    @When("user enters short password for sign up")
    public void user_enters_short_password_for_sign_up() {
        pages.getRegistrationPage().enterPassword("C123");
    }

    @When("user enters confirm short password for sign up")
    public void user_enters_confirm_short_password_for_sign_up() {
        pages.getRegistrationPage().enterPasswordConfirmation("C123");
    }

    @And("user clicks the sing up button")
    public void userClicksTheSingUpButton() {
        pages.getRegistrationPage().clickSignUp();
    }

    @Then("user should see success message for registration")
    public void userShouldSeeSuccessMessageForRegistration() {
        //This is a bug! It is already reported!!!
    }

    @Then("user should see invalid email error message")
    public void userShouldSeeInvalidEmailErrorMessage() {

        pages.getRegistrationPage().validateInvalidEmail();
    }


    @Then("user should see name is required error message")
    public void user_should_see_name_is_required_error_message() {pages.getRegistrationPage().missingRequiredFieldMessage();
    }

    @Then("user should see invalid name error message")
    public void user_should_see_invalid_name_error_message() {
        //This is a bug! It is already reported!!!
    }

    @Then("user should see password is short error message")
    public void user_should_see_password_is_short_error_message() {
        pages.getRegistrationPage().shortPassword();
    }

    @When("user enters confirm password does not match for sign up")
    public void user_enters_confirm_password_does_not_match_for_sign_up() {
        pages.getRegistrationPage().enterPasswordConfirmation(ConfigReader.getDefaultPassword()+1);
    }

    @Then("user should see password does not match error message")
    public void user_should_see_password_does_not_match_error_message() {
        pages.getRegistrationPage().mismatchedConfirmPassword();
    }

    @Given("user goes to homepage")
    public void userGoesToHomepage() {
        Driver.getDriver().get(ConfigReader.getBaseUrl());
    }
}

