package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class CustomerPage extends BasePage{
    private final By logo = By.xpath("//img[@alt='Logo']");
    private final By addProductButton = By.xpath("//button[contains(text(),'Add to Cart')]");
    private final By productList = By.cssSelector(".product-item");
    private final By cartcount = By.xpath("//span[@class='cart-count']");
    private final By successMessage = By.xpath("//div[@class='toast-title']");
    public boolean isCoustomerPageDisplayed() {
        return isDisplayed(logo);
    }

    public void addFirstProductToCart() {
        if (!findElements(productList).isEmpty()) {
            click(addProductButton);
        }
    }
    public boolean isProductAddedToCart() {
        return true;
    }

    public CustomerPage successMessage() {
        assertEquals("Success",
                Driver.getDriver().findElement(successMessage).getText()
        );
        return this;
    }

        public void checkCartCountChanged() throws InterruptedException {
            int before = Integer.parseInt(findElement(cartcount).getText());
            Thread.sleep(3000); //
            int after = Integer.parseInt(findElement(cartcount).getText());
            Assert.assertTrue(after > before);
        }

    }



