package com.bazaarstores.pages.StoreManager;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.stepDefinitions.StoreManager.ProductsSteps;
import com.bazaarstores.utilities.Driver;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import static org.junit.Assert.assertEquals;


public class ProductsPage extends BasePage {

    private final By pageTitle = By.tagName("h3");
    private final By productsTable = By.id("table-bordered");
    private final By productsRows = By.xpath("//div[@class='table-responsive']");
    private final By deletebutton = By.xpath("//tr[td[contains(.,'Hayatesting')]]//i[@class='bi bi-trash3']");
    private final By confirmdeletebutton = By.xpath("/html/body/div[2]/div/div[6]/button[1]");
    private final By cancelbutton = By.xpath("/html/body/div[2]/div/div[6]/button[3]");
    private final By successMessage = By.xpath("//div[@class='toast-title']");
    private final By edit = By.xpath("//table//tbody//tr[1]//td[6]//button[1]");
    private final By price = By.id("price-column");
    private final By submit = By.cssSelector("button[type='submit']");
    private final By stock = By.id("stock-column");
    private final By name = By.id("name-column");
    private final By sku = By.id("sku-column");
    private final By category = By.id("category-column");
    private final By missingRequiredFieldMessage = By.cssSelector(".alert.alert-danger");
    private final By ProductToBeDeleted = By.xpath("//table//tr//td[contains(text(),'Hayatesting')]");
    private final By addNewProductButton = By.cssSelector("button.btn.btn-outline-primary.no-hover.float-start.float-lg-end");

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
        click(this.deletebutton);
        return this;
    }

       public ProductsPage clickCancelButton() {
           click(this.cancelbutton);
        return this;
    }
    public ProductsPage clickConfirmDeleteButton() {
        click(this.confirmdeletebutton);
        return this;
    }

    public ProductsPage SuccessDeleteMessage() {
        assertEquals(
                "Success",
                getText(this.successMessage));
        return this;
    }
    public boolean isProductsDisplayed() {

        return isDisplayed(ProductToBeDeleted);
    }



    public ProductsPage edit() {
        Driver.getDriver().findElement(edit).click();
        return this;
    }

    public ProductsPage price(String price) {
        ProductsSteps.name=Driver.getDriver().findElement(name).getAttribute("value");
        Driver.getDriver().findElement(this.price).clear();
        Driver.getDriver().findElement(this.price).sendKeys(price);
        return this;
    }

    public ProductsPage catalog() {
        WebElement categoryDropdown = Driver.getDriver().findElement(By.id("category-column"));
        Select select = new Select(categoryDropdown);
        select.selectByIndex(Faker.instance().number().numberBetween(1,3));
        ProductsSteps.name=Driver.getDriver().findElement(name).getAttribute("value");
        return this; }

    public ProductsPage stock() {
        Driver.getDriver().findElement(stock).clear();
        return this;
    }

    public ProductsPage submit() {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click()",
                Driver.getDriver().findElement(submit));
        return this;
    }

    public ProductsPage missingRequiredFieldMessage() {
        assertEquals(
                "The stock field is required.",
                Driver.getDriver().findElement(missingRequiredFieldMessage).getText()
        );
        return this;
    }

    public ProductsPage successMessage() {
        assertEquals("Success",
                Driver.getDriver().findElement(successMessage).getText()
        );
        return this;
    }

//-------------US11-----------------Abdulrahman
    public void clickAddNewProductButton() {
        click(addNewProductButton);
    }
    public ProductsPage enterNameProduct(String name) {
        sendKeys(this.name, name);
        return this;
    }

    public ProductsPage enterPriceProduct(double price) {
        sendKeys(this.price, String.valueOf(price));
        return this;
    }

    public ProductsPage enterStockProduct(int stock) {
        sendKeys(this.stock, String.valueOf(stock));
        return this;
    }

    public ProductsPage enterSKUProduct(String sku) {
        sendKeys(this.sku, sku);
        return this;
    }

    public ProductsPage selectCategory(String category) {
        Select select = new Select(findElement(this.category));
        select.selectByVisibleText(category);
        return this;
    }

    public void clickSubmit() {
        click(submit);
    }

    public boolean isNewProductDisplayed(String name) {
        By newProductLocator = By.xpath("//td[contains(text(),'" + name + "')]");
        return isDisplayed(newProductLocator);
    }

    public void duplicateSKUMessage() {
        assertEquals("The sku has already been taken.",
                Driver.getDriver().findElement(missingRequiredFieldMessage).getText()
        );
    }
}
