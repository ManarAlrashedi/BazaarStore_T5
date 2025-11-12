package com.bazaarstores.pages;

import org.openqa.selenium.By;

public class CreateUserPage extends BasePage {
    private final By pagetitle = By.tagName("h3");
    private final By nameInput = By.xpath("//input[@id='first-name-column']");
    private final By emailInput = By.xpath("//input[@id='email-id-column']");
    private final By roleInput = By.xpath("//select[@id='role-id-column']");
    private final By passwordInput = By.xpath("//input[@placeholder='Password']");
    private final By passwordConfInput = By.xpath("//input[@placeholder='Password Confirmation']");
    private final By submitButton = By.xpath("//button[@type='submit']");


    public boolean isAddUsersPageDisplayed() {
        return isDisplayed(pagetitle) && getText(pagetitle).equals("USERS");
    }
    public CreateUserPage enterName(String name){
        sendKeys(nameInput,name);
        return this;
    }
    public CreateUserPage enterEmail(String email){
        sendKeys(emailInput,email);
        return this;
    }
    public CreateUserPage enterRole(String role){
        selectByVisibleText(roleInput,role);
        return this;
    }
    public CreateUserPage enterPassword(String password){
        sendKeys(passwordInput,password);
        return this;
    }
    public CreateUserPage enterPasswordConf(String passwordConf){
        sendKeys(passwordConfInput,passwordConf);
        return this;
    }
    public void clickSubmit() {
        click(submitButton);
    }
}
