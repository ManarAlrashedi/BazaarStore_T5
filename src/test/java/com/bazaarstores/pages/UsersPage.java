package com.bazaarstores.pages;

import com.bazaarstores.stepDefinitions.UsersSteps;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;

import static com.bazaarstores.utilities.Driver.getDriver;
import static org.junit.Assert.assertEquals;


public class UsersPage extends BasePage{
    private final By pagetitle = By.tagName("h3");
    private final By addUserButton = By.cssSelector("button.btn.btn-outline-primary.no-hover.float-start.float-lg-end");
    private final By successMessage = By.xpath("//div[@class='toast-title']");
    private final By nameAdded = By.xpath("//tr[td[contains(.,'lama')]]");



    public boolean isUsersPageDisplayed() {
        return isDisplayed(pagetitle) && getText(pagetitle).equals("USERS");
    }

    public void clickAddUser() {
        click(addUserButton);
    }

public UsersPage successMessage(){
        assertEquals("Success",getText(successMessage));
        return this;
}
public boolean isNameAdded(){
  return isDisplayed(nameAdded) ;
}

}
