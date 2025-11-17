package com.bazaarstores.stepDefinitions.Customer;


import com.bazaarstores.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FavoritePageSteps {

    private static String selectedProductName;
    private static String removedProductName;

    // ================= add prodect to Favorite =================
    @When("the user clicks a random heart icon")
    public void the_user_clicks_a_random_heart_icon() {
        List<WebElement> hearts = Driver.getDriver().findElements(By.cssSelector(".favorite-icon"));
        Random rand = new Random();

        WebElement heart;
        int attempts = 0;
        do {
            heart = hearts.get(rand.nextInt(hearts.size()));
            attempts++;
            if (attempts > hearts.size() * 2) break;
        } while (heart.getAttribute("class").contains("active"));

        heart.click();

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(heart, "class", "active"));

        WebElement productCard = heart.findElement(By.xpath("./ancestor::div[@class='product-card']"));
        selectedProductName = productCard.findElement(By.cssSelector(".product-name")).getText();

        System.out.println("Selected product: " + selectedProductName);
    }

    @Then("the heart icon should become active")
    public void the_heart_icon_should_become_active() {
        List<WebElement> hearts = Driver.getDriver().findElements(By.cssSelector(".favorite-icon.active"));
        boolean activeFound = hearts.stream()
                .anyMatch(h -> {
                    WebElement product = h.findElement(By.xpath("./ancestor::div[@class='product-card']"));
                    String name = product.findElement(By.cssSelector(".product-name")).getText();
                    return name.equalsIgnoreCase(selectedProductName);
                });
        Assert.assertTrue("Heart icon is not active for product: " + selectedProductName, activeFound);
    }

    @Then("the selected product should appear in the My Favorites list")
    public void the_selected_product_should_appear_in_the_My_Favorites_list() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        Driver.getDriver().findElement(By.linkText("My Favorites")).click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-card")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".no-favorites-message"))
        ));

        List<WebElement> favorites = Driver.getDriver().findElements(By.cssSelector(".product-card .product-name"));
        boolean found = favorites.stream()
                .anyMatch(el -> el.getText().equalsIgnoreCase(selectedProductName));

        Assert.assertTrue("Favorited product not found in My Favorites: " + selectedProductName, found);
    }

    // =================removed =================


    @When("the user clicks on the My Favorites link")
    public void the_user_clicks_on_the_my_favorites_link() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));


        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".swal2-container")));


        Driver.getDriver().findElement(By.linkText("My Favorites")).click();


        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-card")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".no-favorites-message"))
        ));
    }

    @Then("all favorited products should be displayed with correct details")
    public void all_favorited_products_should_be_displayed_with_correct_details() {
        List<WebElement> favorites = Driver.getDriver().findElements(By.cssSelector(".product-card .product-name"));
        Assert.assertTrue("No products found in My Favorites!", favorites.size() > 0);


        favorites.forEach(p -> System.out.println("Favorite product: " + p.getText()));
    }

    @When("the user clicks the heart icon again on a favorited product")
    public void the_user_clicks_the_heart_icon_again_on_a_favorited_product() {
        List<WebElement> hearts = Driver.getDriver().findElements(By.cssSelector(".favorite-icon.active"));
        Assert.assertTrue("No active favorite products to remove!", hearts.size() > 0);

        WebElement heart = hearts.get(0);
        WebElement productCard = heart.findElement(By.xpath("./ancestor::div[@class='product-card']"));
        removedProductName = productCard.findElement(By.cssSelector(".product-name")).getText();


        heart.click();

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.stalenessOf(productCard));
    }

    @Then("the product should be removed from the list")
    public void the_product_should_be_removed_from_the_list() {
        List<WebElement> favoritesAfter = Driver.getDriver().findElements(By.cssSelector(".product-card .product-name"));

        boolean found = favoritesAfter.stream()
                .anyMatch(el -> el.getText().equalsIgnoreCase(removedProductName));
        Assert.assertFalse("Removed product is still in My Favorites: " + removedProductName, found);
    }

    @Then("the heart icon should return to inactive state")
    public void the_heart_icon_should_return_to_inactive_state() {
        List<WebElement> hearts = Driver.getDriver().findElements(By.cssSelector(".favorite-icon"));
        boolean anyActive = hearts.stream()
                .anyMatch(h -> {
                    WebElement productCard = h.findElement(By.xpath("./ancestor::div[@class='product-card']"));
                    String name = productCard.findElement(By.cssSelector(".product-name")).getText();
                    return name.equalsIgnoreCase(removedProductName) && h.getAttribute("class").contains("active");
                });

        Assert.assertFalse("Heart icon did not return to inactive state for product: " + removedProductName, anyActive);
    }
}
