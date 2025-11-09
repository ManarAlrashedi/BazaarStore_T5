package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {

    private final By pageTitle = By.tagName("h3");
    private final By productsTable = By.id("table-bordered");
    private final By productsRows = By.xpath("//div[@class='table-responsive']");
    private final By deletebutton = By.xpath("//*[@id=\"table-bordered\"]//table//tbody//tr//td[6]/button[2]");
    private final By confirmdeletebutton = By.xpath("/html/body/div[2]/div/div[6]/button[1]");
    private final By cancelbutton = By.xpath("/html/body/div[2]/div/div[6]/button[3]");

    public boolean isProductsPageDisplayed() {

        return isDisplayed(pageTitle) && getText(pageTitle).equals("Products");
    }

    public boolean isProductsTableDisplayed() {
        return isDisplayed(productsTable);
    }

    public int getProductsCount() {
        List<WebElement> rows = findElements(productsRows);
        return rows.size();
    }

    public String getProductName(int index) {
        By productNameLocator = By.xpath("(//td[@class='py-2 px-4 border-b'])[" + index + "]");
        return getText(productNameLocator);
    }

    public String getProductPrice(int index) {
        By productPriceLocator = By.xpath("(//td[@class='py-2 px-4 border-b'])[" + index + "]");
        return getText(productPriceLocator);
    }

    public String getProductStoke(int index) {
        By productStokeLocator = By.xpath("(//td[@class='py-2 px-4 border-b'])[" + index + "]");
        return getText(productStokeLocator);
    }

    public String getProductCategory(int index) {
        By productCategoryLocator = By.xpath("(//td[@class='py-2 px-4 border-b'])[" + index + "]");
        return getText(productCategoryLocator);
    }

    public String getProductImage(int index) {
        By productImageLocator = By.xpath("(//td[@class='py-2 px-4 border-b'])[" + index + "]");
        return getText(productImageLocator);
    }

    public String getProductAction(int index) {
        By productActionLocator = By.xpath("(//td[@class='py-2 px-4 border-b'])[" + index + "]");
        return getText(productActionLocator);
    }


    public boolean areProductDetailsDisplayed() {
        int count = getProductsCount();
        for (int i = 1; i <= count; i++) {
            String name = getProductName(i);
            String price = getProductPrice(i);
            String stoke = getProductStoke(i);
            String category = getProductCategory(i);
            String image = getProductImage(i);
            String action = getProductAction(i);
            if (name.isEmpty() || price.isEmpty() || stoke.isEmpty() || category.isEmpty() || image.isEmpty() || action.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    public ProductsPage clickDeleteButton() {
        Driver.getDriver().findElement(this.deletebutton).click();
        return this;
    }
       public ProductsPage clickCancelButton() {
        Driver.getDriver().findElement(this.cancelbutton).click();
        return this;
    }    public ProductsPage clickConfirmDeleteButton() {
        Driver.getDriver().findElement(this.confirmdeletebutton).click();
        return this;
    }



}
