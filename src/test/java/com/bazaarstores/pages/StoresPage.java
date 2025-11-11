package com.bazaarstores.pages;

import org.openqa.selenium.By;

public class StoresPage extends BasePage{

    private final By pageTitle = By.tagName("h3");
    private final By lastStoreName = By.xpath("//tbody//tr[last()]");
    private final By storesRows = By.xpath("//div[@class='table-responsive']//tbody//tr");
    private final By deleteButtons = By.xpath("(//button[@type='button'])[last()]");
    private final By confirmationDialog = By.xpath("//div[@role='dialog']");
    private final By confirmButton = By.cssSelector("button.swal2-confirm.swal2-styled.swal2-default-outline");
    private final By deletedMessage = By.xpath("//div[@class='toast-title']");
    private final By cancelButton = By.cssSelector("button.swal2-cancel.swal2-styled.swal2-default-outline");

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

    public void cancelDeleteStore() {
        click(cancelButton);
    }

        private final By storeList = By.cssSelector(".store-item");

        public int getNumberOfStores() {
            return findElements(storeList).size();
        }

        public boolean isStoresListDisplayed() {
            return isDisplayed(storeList);
        }
    }

