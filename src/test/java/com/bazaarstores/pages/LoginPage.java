package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By emailInput = By.cssSelector("input[name='email'], input[type='email'], input[placeholder*='Email']");
    private final By passwordInput = By.cssSelector("input[name='password'], input[type='password']");
    private final By loginButton = By.xpath("//button[.='Log in']");
    private final By signUp = By.linkText("Sign up");
    private final By errorMessage = By.xpath("//*[@class='toast-message']");

    public LoginPage enterEmail(String email){
        sendKeys(emailInput, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passwordInput, password);
        return this;
    }

    public DashboardPage clickLoginButton() {
        click(loginButton);
        return new DashboardPage();
    }

    public RegistrationPage clickRegisterLink() {
        click(signUp);
        return new RegistrationPage();
    }

    // Verification Methods
    public boolean isLoginPageDisplayed() {
        return isDisplayed(emailInput) && isDisplayed(passwordInput);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public boolean isValidationMessageDisplayed(String fieldName) {
        fieldName = fieldName.toLowerCase();
        By field = By.cssSelector("input[name='"+fieldName+"'], input[type='"+fieldName+"']");
        String validationMessage = getValidationMessage(field);
        return validationMessage != null && !validationMessage.isEmpty();
    }
    @Override
    public void navigateToUrl(String url) {
        Driver.getDriver().get(url);
    }

}