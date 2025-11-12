package com.bazaarstores.pages;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class CustomerPage extends BasePage {

    // ================= Locators =================
    public final By productCards = By.cssSelector(".product-card, .product-item, .product");
    public final By productName = By.cssSelector(".product-name, h2, h3");
    private final By productPrice = By.cssSelector(".product-price, .price");
    private final By productDescription = By.cssSelector(".product-description, p");
    private final By productImage = By.cssSelector("img");

    private final By heartIcons = By.cssSelector(".favorite-icon, .favorite");
    private final By myFavoritesLink = By.cssSelector("a[href*='favorites']");
    private final By favoriteProducts = By.cssSelector(".product-card");

    // ================= Navigation =================
    public void openCustomerPage() {
        navigateToUrl("https://bazaarstores.com/customer");
        waitForElementToBeVisible(productCards);
    }

    // ================= Product Visibility =================
    public boolean areProductsVisible() {
        List<WebElement> products = findElements(productCards);
        return !products.isEmpty();
    }

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

    public void clickAndVerifyAllProductsInGrid() throws InterruptedException {
        List<WebElement> products = findElements(By.cssSelector(".products-grid .product-card"));
        for (int i = 0; i < products.size(); i++) {

            WebElement product = findElements(By.cssSelector(".products-grid .product-card")).get(i);


            scrollToElement(product);


            product.click();
            sleep(500);


            boolean nameVisible = !product.findElements(By.cssSelector("h3.product-name")).isEmpty();
            boolean priceVisible = !product.findElements(By.cssSelector("div.product-price span.current-price")).isEmpty();
            boolean descriptionVisible = !product.findElements(By.cssSelector("p.product-description")).isEmpty();
            boolean imageVisible = !product.findElements(By.cssSelector("img.product-image")).isEmpty();

            if (!(nameVisible && priceVisible && descriptionVisible && imageVisible)) {
                throw new AssertionError("Product details are missing for product index: " + i);
            }

            sleep(300);
        }
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


    public void verifyProductDetailsDisplayed() {
        WebElement detailsSection = Driver.getDriver().findElement(By.cssSelector(".product-detail"));
        boolean hasName = detailsSection.findElement(By.cssSelector(".product-name")) != null;
        boolean hasPrice = detailsSection.findElement(By.cssSelector(".product-price")) != null;
        boolean hasDescription = detailsSection.findElement(By.cssSelector(".product-description")) != null;
        boolean hasImage = detailsSection.findElement(By.cssSelector(".product-image")) != null;

        Assert.assertTrue("Product name not found", hasName);
        Assert.assertTrue("Product price not found", hasPrice);
        Assert.assertTrue("Product description not found", hasDescription);
        Assert.assertTrue("Product image not found", hasImage);
    }



    // ================= Favorites (US07) =================
    public void clickHeartIcon(int productIndex) {
        List<WebElement> hearts = findElements(heartIcons);
        if (!hearts.isEmpty() && productIndex < hearts.size()) {
            scrollToElement(heartIcons);
            hearts.get(productIndex).click();
        }
    }

    public boolean isHeartIconActive(int productIndex) {
        List<WebElement> hearts = findElements(heartIcons);
        if (!hearts.isEmpty() && productIndex < hearts.size()) {
            String classAttr = hearts.get(productIndex).getAttribute("class");
            return classAttr.contains("active") || classAttr.contains("filled");
        }
        return false;
    }

    public void openMyFavoritesPage() {
        click(myFavoritesLink);
        waitForUrlContains("favorites");
    }

    public boolean isProductInFavorites(String name) {
        List<WebElement> favorites = findElements(favoriteProducts);
        for (WebElement product : favorites) {
            WebElement prodName = product.findElement(productName);
            if (prodName.getText().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void removeFromFavorites(int productIndex) {
        clickHeartIcon(productIndex);
    }

}

