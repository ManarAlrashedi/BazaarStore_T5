package com.bazaarstores.pages;

import org.openqa.selenium.By;

public class StoresPage extends BasePage {

    private final By pageTitle = By.tagName("h3");
    private final By storeName = By.xpath("//tr[td[contains(.,'%s')]]");
    private final By storesRows = By.xpath("//div[@class='table-responsive']//tbody//tr");
    private final String deleteButtons = "//tr[td[contains(.,'%s')]]//i[@class='bi bi-trash3']";
    private final By confirmationDialog = By.xpath("//div[@role='dialog']");
    private final By confirmButton = By.cssSelector("button.swal2-confirm.swal2-styled.swal2-default-outline");
    private final By deletedMessage = By.xpath("//div[@class='toast-title']");
    private final By cancelButton = By.cssSelector("button.swal2-cancel.swal2-styled.swal2-default-outline");

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
}
