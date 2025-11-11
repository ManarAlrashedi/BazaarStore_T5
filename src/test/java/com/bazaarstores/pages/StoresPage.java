package com.bazaarstores.pages;

import com.bazaarstores.stepDefinitions.StoreSteps;
import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import static org.junit.Assert.assertEquals;

public class StoresPage extends BasePage{

    private final By pageTitle = By.tagName("h3");
    private final By lastStoreName = By.xpath("//tbody//tr[last()]");
    private final By storesRows = By.xpath("//div[@class='table-responsive']//tbody//tr");
    private final By deleteButtons = By.xpath("(//button[@type='button'])[last()]");
    private final By confirmationDialog = By.xpath("//div[@role='dialog']");
    private final By confirmButton = By.cssSelector("button.swal2-confirm.swal2-styled.swal2-default-outline");
    private final By deletedMessage = By.xpath("//div[@class='toast-title']");
    private final By edit = By.xpath("//table//tbody//tr[td[contains(text(),'name')]]//button[1]");
    private final By name = By.id("first-name-column");
    private final By location = By.id("location-id-column");
    private final By description = By.xpath("//*[@id='tinymce']//p");
    private final By missingRequiredFieldMessage = By.xpath("//li[.='The name field is required.']");
    private final By successMessage = By.xpath("//div[@class='toast-title']");


    public boolean isStoresPageDisplayed() {
        return isDisplayed(pageTitle) && getText(pageTitle).equals("STORES");
    }

    public int getStoresCount() {
        return findElements(storesRows).size();
    }

    public String gitLastStoreName() {
        return getText(lastStoreName);
    }

    public StoresPage clickDeleteStore() {
        clickWithJS(deleteButtons);
        return this;
    }

    public boolean isDeleteConfirmationDialogDisplayed() {
        return isDisplayed(confirmationDialog);
    }

    public void confirmDeleteStore() {
        click(confirmButton);
    }

    public StoresPage edit() {
        Driver.getDriver().findElement(edit).click();
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

    public StoresPage admins(String admin) {
        WebElement categoryDropdown = Driver.getDriver().findElement(By.id("admin-column"));
        Select select = new Select(categoryDropdown);
        select.selectByVisibleText(admin);
        StoreSteps.admin=select.getFirstSelectedOption().getText();
        return this; }

    public StoresPage clearName() {
        StoreSteps.originalName = Driver.getDriver().findElement(name).getAttribute("value");
        Driver.getDriver().findElement(name).clear();
        return this;
    }

    public boolean isStoreDeletedMessageDisplayed() {
        return isDisplayed(deletedMessage);
    }

    public boolean isStorePresentInList() {
        String storeName = gitLastStoreName();
        return !storeName.isEmpty();
    }

    public StoresPage missingRequiredFieldMessage() {
        assertEquals(
                "The name field is required.",
                Driver.getDriver().findElement(missingRequiredFieldMessage).getText()
        );
        return this;
    }

    public StoresPage successMessage() {
        assertEquals("Success",
                Driver.getDriver().findElement(successMessage).getText()
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
}
