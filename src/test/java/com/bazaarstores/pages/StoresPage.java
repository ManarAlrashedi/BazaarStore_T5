package com.bazaarstores.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StoresPage extends BasePage {

    private final By pageTitle = By.tagName("h3");
    private final By storeName = By.xpath("//tr[td[contains(.,'%s')]]");
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

    public String gitStoreName() {

        return getText(storeName);
    }

    public boolean isStoreRemoveFromList() {

        return !isDisplayed(storeName);
    }

    public void cancelDeleteStore() {
        clickWithJS(cancelButton);
        //waitForElementToDisappear(cancelButton);
    }

    public boolean isStoreStillPresentInList() {

        return isDisplayed(storeName);
    }

    public boolean isAddStoreButtonVisible() {
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

    private final By storeList = By.cssSelector(".store-item");

    public int getNumberOfStores() {
        return findElements(storeList).size();
    }


/*
    private final By storesTable = By.id("table-bordered");
    private final By storesTableRows = By.xpath("//*[@id='table-bordered']//table/tbody/tr");

    public boolean isStoresTableVisible() {
        return isDisplayed(storesTable);
    }

    public int getVisibleStoresCount() {
        List<WebElement> rows = findElements(storesTableRows);
        return rows.size();
    }

    public String getVisibleStoreName(int index) {
        By locator = By.xpath("//*[@id='table-bordered']//table/tbody/tr[" + index + "]/td[1]");
        return getText(locator);
    }

    public String getVisibleStoreDescription(int index) {
        By locator = By.xpath("//*[@id='table-bordered']//table/tbody/tr[" + index + "]/td[2]");
        return getText(locator);
    }

    public String getVisibleStoreAdminName(int index) {
        By locator = By.xpath("//*[@id='table-bordered']//table/tbody/tr[" + index + "]/td[3]");
        return getText(locator);
    }

    public String getVisibleStoreAction(int index) {
        By locator = By.xpath("//*[@id='table-bordered']//table/tbody/tr[" + index + "]/td[4]");
        return getText(locator);
    }

    public boolean areStoreDetailsDisplayed() {
        int count = getStoresCount();
        for (int i = 1; i <= count; i++) {
            String name = getStoreName(i);
            String description = getStoreDescription(i);
            String admin = getStoreAdminName(i);
            String action = getStoreAction(i);
            if (name.isEmpty() || description.isEmpty() || admin.isEmpty() || action.isEmpty()) {
                return false;
            }
        }
        return true;
    }


    public String getStoreName(int index) {
        By getStoreNameLocator = By.xpath("//*[@id='table-bordered']//table/tbody/tr[1]/td[1][" + index + "]");
        return getText(storeNameLocator);
    }
    private String getStoreDescription(int i) {
    }
 */
}


