package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UsersPage {

    WebDriver driver;

    public UsersPage() {
        this.driver = Driver.getDriver();
    }

    // Locators
    By usersTableRows = By.cssSelector("table tbody tr");
    By searchField = By.cssSelector("div.input-group input[name='email']");
    By searchButton = By.cssSelector("div.input-group button[type='submit']");
    By noUsersMessage = By.xpath("//*[text()='No users found']");
    By nextPageButton = By.cssSelector("ul.pagination li.page-item a[rel='next']");


    public void navigateToUsersPage() {
        driver.get("https://bazaarstores.com/users");
    }

    public List<WebElement> getAllUsers() {
        List<WebElement> allRows = new ArrayList<>(driver.findElements(usersTableRows));

        // Loop through pagination
        while (!driver.findElements(nextPageButton).isEmpty()) {
            WebElement nextBtn = driver.findElement(nextPageButton);
            if (!nextBtn.isDisplayed() || !nextBtn.isEnabled()) {
                break;
            }
            nextBtn.click();

            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            allRows.addAll(driver.findElements(usersTableRows));
        }

        return allRows;
    }

    public void enterSearchEmail(String email) {
        WebElement field = driver.findElement(searchField);
        field.clear();
        field.sendKeys(email);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public boolean isNoUsersMessageDisplayed() {
        return !driver.findElements(noUsersMessage).isEmpty();
    }

    public boolean isUsersTableDisplayed() {
        return !driver.findElements(usersTableRows).isEmpty();
    }
}
