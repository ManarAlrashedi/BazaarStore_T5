package com.bazaarstores.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

public class UsersPage extends BasePage{

    private final By pageTitle = By.tagName("h3");
    private final By nameColumn = By.xpath("//thead//th[.='NAME']");
    private final By emailColumn = By.xpath("//thead//th[.='EMAIL']");
    private final By actionsColumn = By.xpath("//thead//th[.='ACTIONS']");
    private final By usersRows = By.xpath("//div[@class='table-responsive']//tbody//tr");
    private final String deleteButtons = "//tr[td[contains(.,'%s')]]//i[@class='bi bi-trash3']";
    private final By confirmationDialog = By.xpath("//div[@role='dialog']");
    private final String userName = "//tr[td[contains(.,'%s')]]";


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
}
