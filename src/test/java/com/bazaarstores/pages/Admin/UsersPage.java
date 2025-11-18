package com.bazaarstores.pages.Admin;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UsersPage extends BasePage {

    WebDriver driver;
    public UsersPage() {
        this.driver = Driver.getDriver();
    }

    // Locators
    private final By usersTableRows = By.cssSelector("table tbody tr");
    By searchField = By.cssSelector("div.input-group input[name='email']");
    By searchButton = By.cssSelector("div.input-group button[type='submit']");
    private final By nextPageButton = By.xpath("//li//a[@rel='next']");
    private final By noUsersMessage = By.xpath("//td[@colspan]");
    private final By pageTitle = By.tagName("h3");
    private final By nameColumn = By.xpath("//thead//th[.='NAME']");
    private final By emailColumn = By.xpath("//thead//th[.='EMAIL']");
    private final By actionsColumn = By.xpath("//thead//th[.='ACTIONS']");
    private final By usersRows = By.xpath("//div[@class='table-responsive']//tbody//tr");
    private final String deleteButtons = "//tr[td[contains(.,'%s')]]//i[@class='bi bi-trash3']";
    private final By confirmationDialog = By.xpath("//div[@role='dialog']");
    private final String userName = "//tr[td[contains(.,'%s')]]";
    private final By addUserButton = By.cssSelector("button.btn.btn-outline-primary.no-hover.float-start.float-lg-end");
    private final By successMessage = By.xpath("//div[@class='toast-title']");
    private final String editButton = "//tr[td[contains(.,'%s')]]//i[@class='bi bi-pencil-square']";


    public List<WebElement> getAllUsers() {
        return findElements(usersTableRows);
    }
    public void enterSearchEmail(String email) {
        WebElement field = findElement(searchField);
        field.clear();
        field.sendKeys(email);
    }

    public void clickSearch() {
        findElement(searchButton).click();
    }

    public boolean isNoUsersMessageDisplayed() {

        return isDisplayed(noUsersMessage);
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
        //scrollToBottom();
        //clickWithJS(nextPageButton);
        clickWithJS(By.xpath(String.format(deleteButtons, nameUser)));
        return this;
    }

    public boolean isDeleteConfirmationDialogDisplayed() {
        return isDisplayed(confirmationDialog);
    }

    public boolean isUserStillPresentInListBug(String nameUser) {
        //scrollToBottom();
       // clickWithJS(nextPageButton);
        return isDisplayed(By.xpath(String.format(userName, nameUser)));
    }
    public boolean isUserStillPresentInList(String nameUser) {
        return isDisplayed(By.xpath(String.format(userName, nameUser)));
    }

    public void clickAddUser() {
        click(addUserButton);
    }

    public UsersPage successMessage(){
        assertEquals("Success",getText(successMessage));
        return this;
    }

    public void clickEditUser(String name) {
        //scrollToBottom();
        //clickWithJS(nextPageButton);
        clickWithJS(By.xpath(String.format(editButton, name)));
    }
}
