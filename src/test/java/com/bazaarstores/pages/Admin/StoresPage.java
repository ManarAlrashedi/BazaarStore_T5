package com.bazaarstores.pages.Admin;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.stepDefinitions.Admin.StoreSteps;
import com.bazaarstores.utilities.Driver;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class StoresPage extends BasePage {

    private final By pageTitle = By.tagName("h3");
    private final String storeName = "//tr[td[contains(.,'%s')]]";
    private final By storesRows = By.xpath("//div[@class='table-responsive']//tbody//tr");
    private final String deleteButtons = "//tr[td[contains(.,'%s')]]//i[@class='bi bi-trash3']";
    private final By confirmationDialog = By.xpath("//div[@role='dialog']");
    private final By confirmButton = By.cssSelector("button.swal2-confirm.swal2-styled.swal2-default-outline");
    private final By deletedMessage = By.xpath("//div[@class='toast-title']");
    private final By cancelButton = By.cssSelector("button.swal2-cancel.swal2-styled.swal2-default-outline");
    private final By addStoreButton = By.cssSelector("a[href='https://bazaarstores.com/store/create']");
    private final By successMessage = By.xpath("//div[@class='toast-title']");
    private final By failMessage = By.xpath("//div[@class='toast-title']");
    private final By newStore = By.xpath("//tr[td[contains(.,'TeamFive')]]//i[@class='bi bi-trash3']");
    private final By edit = By.xpath("//tr[td[contains(.,'TeamFive')]]//i[@class='bi bi-pencil-square']");
    private final By name = By.id("first-name-column");
    private final By location = By.id("location-id-column");
    private final By description = By.xpath("//*[@id='tinymce']//p");
    private final By missingRequiredFieldMessage = By.xpath("//li[.='The name field is required.']");

    public boolean isStoresPageDisplayed() {

        return isDisplayed(pageTitle) && getText(pageTitle).equals("STORES");
    }

    public int getStoresCount() {//verify at least one store exists

        return findElements(storesRows).size();
    }

    public StoresPage clickDeleteStore(String storeName) {
        clickWithJS(By.xpath(String.format(deleteButtons, storeName)));
        return this;
    }

    public boolean isDeleteConfirmationDialogDisplayed() {

        return isDisplayed(confirmationDialog);
    }

    public void confirmDeleteStore() {

        click(confirmButton);
    }

    public boolean isStoreDeletedMessageDisplayed() {

        return isDisplayed(deletedMessage);
    }

    public boolean isStoreRemoveFromList(String name) {

        return !isDisplayed(By.xpath(String.format(storeName, name)));
    }

    public void cancelDeleteStore() {
        clickWithJS(cancelButton);
        //waitForElementToDisappear(cancelButton);
    }

    public  boolean isStoreStillPresentInList(String name) {

        return isDisplayed(By.xpath(String.format(storeName, name)));
    }

    public  boolean isAddStoreButtonVisible() {
        return isDisplayed(addStoreButton);

    }

    public StoresPage ClickAddStoreButton() {

                click(addStoreButton);
        return this;
    }

    public StoresPage successMessage() {
        assertEquals(
                "Success",
                getText(this.successMessage));
        return this;
    }

    public StoresPage FailMessage() {
        assertEquals(
                "Error",
                getText(this.failMessage));
        return this;
    }

    public boolean NewStore() {

        return isDisplayed(newStore);
    }

    public StoresPage edit() {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click()",
                Driver.getDriver().findElement(edit));
       // Driver.getDriver().findElement(edit).click();
        return this;
    }

    public StoresPage location(String location) {
        Driver.getDriver().findElement(this.location).clear();
        Driver.getDriver().findElement(this.location).sendKeys(location);
        return this;
    }

    public StoresPage description(String description) {
        Driver.getDriver().switchTo().frame("default_ifr");
        Driver.getDriver().findElement(this.description).clear();
        Driver.getDriver().findElement(this.description).sendKeys(description);
        Driver.getDriver().switchTo().defaultContent();
        return this;
    }

    public StoresPage name(String name) {
        Driver.getDriver().findElement(this.name).clear();
        Driver.getDriver().findElement(this.name).sendKeys(name);
        return this;
    }

    public StoresPage admins() {
        WebElement categoryDropdown = Driver.getDriver().findElement(By.id("admin-column"));
        Select select = new Select(categoryDropdown);
        select.selectByIndex(Faker.instance().number().numberBetween(1,4));
        StoreSteps.admin=select.getFirstSelectedOption().getText();
        return this; }

    public StoresPage clearName() {
        StoreSteps.originalName = Driver.getDriver().findElement(name).getAttribute("value");
        Driver.getDriver().findElement(name).clear();
        return this;
    }


    public StoresPage missingRequiredFieldMessage() {
        assertEquals(
                "The name field is required.",
                Driver.getDriver().findElement(missingRequiredFieldMessage).getText()
        );
        return this;
    }

    public boolean isUpdated(String expectedName, String expectedLocation, String expectedDescription){

        WebElement row = Driver.getDriver().findElement(By.xpath("//tbody//tr[1]"));
        String actualName = row.findElement(By.xpath("./td[1]")).getText().trim();
        String actualDescription = row.findElement(By.xpath("./td[2]")).getText().trim();
        String actualAdmin = row.findElement(By.xpath("./td[3]")).getText().trim();

        return actualName.equals(expectedName)
                && actualDescription.equals(expectedDescription)
                && actualAdmin.equals(actualAdmin);
    }

    public boolean isStoresListDisplayed() {
        return isDisplayed(storesRows);
    }

    public boolean areStoreEntriesComplete() {
        for (WebElement row : findElements(storesRows)) {
            String name = row.findElement(By.xpath("./td[1]")).getText().trim();
            String description = row.findElement(By.xpath("./td[2]")).getText().trim();
            String adminName = row.findElement(By.xpath("./td[3]")).getText().trim();
            WebElement actionButtons = row.findElement(By.xpath("./td[4]"));

            if (name.isEmpty() || description.isEmpty() || adminName.isEmpty() || actionButtons == null) {
                return false;
            }
        }
        return true;
    }
}

