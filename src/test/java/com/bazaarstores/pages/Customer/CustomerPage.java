package com.bazaarstores.pages.Customer;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
   // private final By customerCart = By.xpath("//i[@class='fas fa-shopping-cart']");
   private final By customerCart = By.cssSelector(".cart-icon, .fa-shopping-cart");
    private final By viewCart = By.xpath("//a[@class='cart-button view-cart']");
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
        int productCount = findElements(productCards).size();
        for (int i = 0; i < productCount; i++) {
            try {
                List<WebElement> refreshed = findElements(productCards);
                for (WebElement element : refreshed) {
                    System.out.println(element.getText());
                }
                WebElement product = refreshed.get(i);
                scrollToElement(product);
                product.click();
                Thread.sleep(800);

                WebElement name = Driver.getDriver().findElement(By.cssSelector(".product-name"));
                WebElement price = Driver.getDriver().findElement(By.cssSelector(".product-price"));
                WebElement desc = Driver.getDriver().findElement(By.cssSelector(".product-description"));
                WebElement image = Driver.getDriver().findElement(By.cssSelector(".product-image"));

                Assert.assertTrue("Product name is missing!", name.isDisplayed());
                Assert.assertTrue("Product price is missing!", price.isDisplayed());
                Assert.assertTrue("Product image is missing!", image.isDisplayed());

                System.out.println(" Product " + (i + 1) + " details verified successfully.");

               // Driver.getDriver().navigate().back();
                WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCards));

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
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                    firstProduct.findElement(By.xpath(".//button[contains(@class,'add-to-cart')]"))
            ));
            scrollToElement(addToCartButton);
            addToCartButton.click();
        }
    }

    public boolean isAddToCartSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }



    public boolean isCartItemCountUpdated() throws InterruptedException {
        Thread.sleep(3000);
        hoverOverCartIcon();
        String itemCountText = getText(cartItemCount);
        try {
            int itemCount = Integer.parseInt(itemCountText);
            System.out.println(itemCount);
            return itemCount > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean itemCountUpdated() {
        hoverOverCartIcon();
        String itemCountText = findElement(By.xpath("//span[@class='cart-count']")).getText();
        try {
            int itemCount = Integer.parseInt(itemCountText);
            return itemCount == 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean ensureCartHasItems() {
        hoverOverCartIcon();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        try {
            return wait.until(driver -> !driver.findElements(By.cssSelector(".cart-item")).isEmpty());
        } catch (TimeoutException e) {
            System.out.println("Cart has no items after waiting: " + e.getMessage());
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

    public void clickX() throws InterruptedException {
        List<WebElement> removeButtons = findElements(removeButton);
        while (!removeButtons.isEmpty()) {
            removeButtons.get(0).click();
            Thread.sleep(1000);
            hoverOverCartIcon();
            Thread.sleep(1000);
            removeButtons = findElements(removeButton);
        }
    }


    public boolean SuccessMessage() {
        return isDisplayed(successMessage);
    }
}

