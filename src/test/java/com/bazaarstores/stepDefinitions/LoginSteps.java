package com.bazaarstores.stepDefinitions;


import com.bazaarstores.pages.AllPages;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class LoginSteps {
    
    AllPages allPages = new AllPages();

    @When("user enters email {string} and password {string}")
    public void user_enters_email_and_password(String email, String password) {
        allPages.getLoginPage().enterEmail(email).enterPassword(password);
    }

    @When("user clicks login button")
    public void user_clicks_login_button() {
        allPages.getLoginPage().clickLoginButton();
    }

    @Then("user should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        Assert.assertTrue("Dashboard should be displayed", 
                         allPages.getDashboardPage().isDashboardPageDisplayed());
    }

    @Then("user should see error message")
    public void user_should_see_error_message() {
        Assert.assertTrue("Error message should be displayed", 
                         allPages.getLoginPage().isErrorMessageDisplayed());
    }

    @Then("user should remain on login page")
    public void user_should_remain_on_login_page() {
        Assert.assertTrue("Should remain on login page", 
                         allPages.getLoginPage().isLoginPageDisplayed());
    }

    @Then("user should see empty {string} error message")
    public void userShouldSeeEmptyErrorMessage(String field) {
        allPages.getLoginPage().isValidationMessageDisplayed(field);
    }

    @Then("user should see invalid {string} error message")
    public void userShouldSeeInvalidErrorMessage(String field) {
        allPages.getLoginPage().isValidationMessageDisplayed(field);
    }

    @Then("admin should be logged in successfully")
    public void adminShouldBeLoggedInSuccessfully() {
        Assert.assertTrue("Profile visit chart should be displayed",
                allPages.getDashboardPage().isProfileVisitChartDisplayed());
}
}