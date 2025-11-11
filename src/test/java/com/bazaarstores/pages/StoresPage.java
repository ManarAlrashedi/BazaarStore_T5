package com.bazaarstores.pages;

import org.openqa.selenium.By;

public class StoresPage extends BasePage {

    private final By pageTitle = By.tagName("h3");
    private final By lastStoreName = By.xpath("//tbody//tr[last()]");
    private final By storesRows = By.xpath("//div[@class='table-responsive']//tbody//tr");
    private final By deleteButtons = By.xpath("(//button[@type='button'])[last()]");
    private final By confirmationDialog = By.xpath("//div[@role='dialog']");
    private final By confirmButton = By.cssSelector("button.swal2-confirm.swal2-styled.swal2-default-outline");
    private final By deletedMessage = By.xpath("//div[@class='toast-title']");
    private final By addStoreButton = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/button");
    private final By NewStoreSuccessMessage = By.xpath("//div[@class='toast-title']");
    private final By NewStoreFailMessage = By.xpath("//div[@class='toast-title']");
    private final By NewStore = By.xpath("//tr[td[contains(.,'TeamFive')]]//i[@class='bi bi-trash3']");

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

    public boolean isStoreDeletedMessageDisplayed() {
        return isDisplayed(deletedMessage);
    }

    public boolean isStorePresentInList() {
        String storeName = gitLastStoreName();
        return !storeName.isEmpty();
    }

    public boolean AddStoreButton() {
        return isDisplayed(addStoreButton);
    }
    public void ClickAddStoreButton() {
       click(addStoreButton);
    }

    public StoresPage successMessage() {
        isDisplayed(NewStoreSuccessMessage);
        return this;
    }
    public StoresPage FailMessage() {
        isDisplayed(NewStoreFailMessage);
        return this;
    }
    public StoresPage NewStore() {
        isDisplayed(NewStore);
        return this;
    }
}