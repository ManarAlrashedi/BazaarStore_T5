package com.bazaarstores.pages.Customer;

import com.bazaarstores.pages.BasePage;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class CartPage extends BasePage {


    private final By confirmbutton = By.id("clear-all");
    private final By successmessage = By.id("swal2-html-container");
    private final By okbutton = By.cssSelector("button[class='swal2-confirm swal2-styled']");
    private final By customerCart = By.xpath("/html[1]/body[1]/header[1]/div[1]/div[2]/div[1]/div[1]/span[1]");
    private final By viewCart = By.xpath("/html/body/header/div/div[2]/div[1]/div[2]/div[3]/a");
    private final By ProductInTheCart = By.id("/html/body/main/div/div[1]");

    public boolean ProductIsVisable() {
        return isDisplayed(ProductInTheCart);
    }
    public CartPage hoverOverCart() {
        hoverOver(this.customerCart);
        return this;
    }
    public CartPage ClickViewCart() {
        click(this.viewCart);
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
