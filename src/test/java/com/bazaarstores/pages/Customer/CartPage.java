package com.bazaarstores.pages.Customer;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class CartPage extends BasePage {


    private final By confirmbutton = By.id("clear-all");
    private final By successmessage = By.id("swal2-html-container");
    private final By okbutton = By.cssSelector("button[class='swal2-confirm swal2-styled']");
   // private final By customerCart = By.xpath("//i[@class='fas fa-shopping-cart']");
    private final By customerCart = By.cssSelector(".cart-icon, .fa-shopping-cart");
    private final By viewCart = By.xpath("/html/body/header/div/div[2]/div[1]/div[2]/div[3]/a");
    private final By ProductInTheCart = By.xpath("/html/body/main/div/div[1]");

    public boolean ProductIsVisable() {
        return isDisplayed(ProductInTheCart);
    }
    public CartPage hoverOverCart() {
        hoverOver(this.customerCart);
        return this;
    }
    public CartPage ClickViewCart() {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            WebElement viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(viewCart));
            viewCartButton.click();
            return this;
        }

    public CartPage ClickConfirmButton() {
        click(this.confirmbutton);
        return this;
    }

    public boolean SuccessMessage() {
      /*  assertEquals(
                "Your order has been received successfully.",
                Driver.getDriver().findElement(successmessage).getText()
        );
        return this;*/
        return isDisplayed(successmessage);
    }

    public CartPage ClickOkButton() {
        click(this.okbutton);
        return this;
    }

}
