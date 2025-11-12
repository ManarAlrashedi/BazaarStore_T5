package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UsersPage extends BasePage {

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
    private final By pageTitle = By.tagName("h3");
    private final By nameColumn = By.xpath("//thead//th[.='NAME']");
    private final By emailColumn = By.xpath("//thead//th[.='EMAIL']");
    private final By actionsColumn = By.xpath("//thead//th[.='ACTIONS']");
    private final By usersRows = By.xpath("//div[@class='table-responsive']//tbody//tr");
    private final String deleteButtons = "//tr[td[contains(.,'%s')]]//i[@class='bi bi-trash3']";
    private final By confirmationDialog = By.xpath("//div[@role='dialog']");
    private final String userName = "//tr[td[contains(.,'%s')]]";


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

    public boolean isUsersPageDisplayed() {
        return isDisplayed(pageTitle) && getText(pageTitle).equals("USERS");
    }

    public boolean areUserColumnsDisplayed() {
        return isDisplayed(nameColumn) && isDisplayed(emailColumn) && isDisplayed(actionsColumn);
    }

    public int getUsersCount() {//verify at least one user exists

        return findElements(usersRows).size();
    }

    public UsersPage clickDeleteUser(String nameUser) {
        clickWithJS(By.xpath(String.format(deleteButtons, nameUser)));
        return this;
    }

    public boolean isDeleteConfirmationDialogDisplayed() {
        return isDisplayed(confirmationDialog);
    }

    public boolean isUserStillPresentInList(String nameUser) {
        return isDisplayed(By.xpath(String.format(userName, nameUser)));
    }
    private final By addUserButton = By.cssSelector("button.btn.btn-outline-primary.no-hover.float-start.float-lg-end");
    private final By successMessage = By.xpath("//div[@class='toast-title']");
    private final By nameAdded = By.xpath("//tr[td[contains(.,'lama')]]");



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
