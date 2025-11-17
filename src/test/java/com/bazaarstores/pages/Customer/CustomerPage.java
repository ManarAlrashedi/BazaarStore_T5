package com.bazaarstores.pages.Customer;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class CustomerPage extends BasePage {
    // ================= Locators =================
    public final By productCards = By.cssSelector(".product-card, .product-item, .product");
    public final By productName = By.cssSelector(".product-name, h2, h3");
    private final By productPrice = By.cssSelector(".product-price, .price");
    private final By productDescription = By.cssSelector(".product-description, p");
    private final By productImage = By.cssSelector("img");
    private final By successMessage = By.id("swal2-html-container");
    private final By customerCart = By.xpath("/html[1]/body[1]/header[1]/div[1]/div[2]/div[1]/div[1]/span[1]");
    private final By viewCart = By.xpath("/html/body/header/div/div[2]/div[1]/div[2]/div[3]/a");
    private final By cartItemCount = By.xpath("//span[@class='cart-count']");
    private final By emptyCartMessage = By.cssSelector(".empty-cart-message");
    private final By totalCost = By.xpath("//div[@class='cart-subtotal']//span[last()]");
    private final By removeButton = By.cssSelector(".remove-item");

    // ================= Navigation =================
    public void openCustomerPage() {
        navigateToUrl("https://bazaarstores.com/customer");
        waitForElementToBeVisible(productCards);
    }

    // ================= Product Visibility =================
    public boolean areProductDetailsDisplayed() {
        List<WebElement> products = findElements(productCards);
        if (products.isEmpty()) return false;
        for (WebElement product : products) {
            boolean hasName = !product.findElements(productName).isEmpty();
            boolean hasPrice = !product.findElements(productPrice).isEmpty();
            boolean hasDescription = !product.findElements(productDescription).isEmpty();
            boolean hasImage = !product.findElements(productImage).isEmpty();
            if (!(hasName && hasPrice && hasDescription && hasImage)) {
                System.out.println("Missing detail in product: " + product.getText());
                return false;
            }
        }
        return true;
    }

    // ================= Performance Check =================
    public boolean isPageLoadedQuickly() {
        long start = System.currentTimeMillis();
        Driver.getDriver().navigate().refresh();
        long end = System.currentTimeMillis();
        long loadTime = end - start;
        System.out.println("⏱ Page load time: " + loadTime + " ms");
        return loadTime < 5000;
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("window.scrollBy(0, -150);"); // تصحيح الـ offset
    }

    //US05
    public void clickEachProductAndCheckDetails() {
        List<WebElement> products = findElements(productCards);
        for (int i = 0; i < products.size(); i++) {
            WebElement product = products.get(i);
            scrollToElement(product);
            try {
                product.click();
                Thread.sleep(800);
                WebElement name = product.findElement(By.cssSelector(".product-name"));
                WebElement price = product.findElement(By.cssSelector(".product-price"));
                WebElement desc = product.findElement(By.cssSelector(".product-description"));
                WebElement image = product.findElement(By.cssSelector(".product-image"));
                Assert.assertTrue("Product name is missing!", name.isDisplayed());
                Assert.assertTrue("Product price is missing!", price.isDisplayed());
                Assert.assertTrue("Product description is missing!", desc.isDisplayed());
                Assert.assertTrue("Product image is missing!", image.isDisplayed());
                System.out.println(" Product " + (i + 1) + " details verified successfully.");
            } catch (Exception e) {
                System.out.println(" Failed to verify product " + (i + 1) + ": " + e.getMessage());
            }
        }
    }

    // ================= (US06) =================
    public void clickAddToCartOnFirstProduct() {
        List<WebElement> products = findElements(productCards);
        if (!products.isEmpty()) {
            WebElement firstProduct = products.get(0);
            WebElement addToCartButton = firstProduct.findElement(By.xpath("//button[@class='add-to-cart']"));
            scrollToElement(addToCartButton);
            addToCartButton.click();
        }
    }

    public boolean isAddToCartSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public boolean isCartItemCountUpdated() {
        WebElement cartIcon = Driver.getDriver().findElement(By.cssSelector(".fas.fa-shopping-cart"));
        String itemCountText = cartIcon.findElement(By.xpath("//span[@class='cart-count']")).getText();
        try {
            int itemCount = Integer.parseInt(itemCountText);
            return itemCount > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean ensureCartHasItems() {
        WebElement cartIcon = Driver.getDriver().findElement(By.cssSelector(".fas.fa-shopping-cart"));
        String itemCountText = cartIcon.findElement(By.xpath("//span[@class='cart-count']")).getText();
        try {
            int itemCount = Integer.parseInt(itemCountText);
            return itemCount > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void hoverOverCartIcon() {
        hoverOver(customerCart);
    }

    public void clickViewCartButton() {
        click(viewCart);
    }

    public boolean ensureCartIsEmpty() {
        String itemCountText = getText(cartItemCount);
        try {
            int itemCount = Integer.parseInt(itemCountText);
            return itemCount == 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isEmptyCartMessageDisplayed() {
        String expectedMessage = "Your cart is empty";
        String actualMessage = getText(emptyCartMessage);
        return expectedMessage.equals(actualMessage);
    }

    public boolean isViewCartButtonDisplayed() {
        return isDisplayed(viewCart);
    }

    public boolean isAllItemsDisplayedInCart() {
        return !findElements(By.cssSelector(".cart-item")).isEmpty();
    }

    public boolean isTotalCostDisplayed() {
        return isDisplayed(totalCost);
    }

    public void clickX() {
        click(removeButton);
    }

    public boolean SuccessMessage() {
        return isDisplayed(successMessage);
    }
}

