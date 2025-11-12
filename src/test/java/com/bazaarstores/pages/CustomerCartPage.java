package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class CustomerCartPage extends BasePage{


    private final By confirmbutton = By.id("clear-all");
    private final By successmessage = By.id("swal2-html-container");
    private final By okbutton = By.cssSelector("button[class='swal2-confirm swal2-styled']");
    private final By customerCart = By.xpath("/html[1]/body[1]/header[1]/div[1]/div[2]/div[1]/div[1]/span[1]");
    private final By viewCart = By.xpath("/html/body/header/div/div[2]/div[1]/div[2]/div[3]/a");
    private final By emptyMessage = By.xpath("/html/body/header/div/div[2]/div[1]/div[2]/div[2]");

    public CustomerCartPage hoverOverCart() {
        hoverOver(this.customerCart);
        return this;
    }
    public CustomerCartPage ClickViewCart() {
        click(this.viewCart);
        return this;
    }

    public CustomerCartPage ClickConfirmButton() {
        click(this.confirmbutton);
        return this;
    }

    public CustomerCartPage SuccessMessage() {
        assertEquals(
                "Your order has been received successfully.",
                Driver.getDriver().findElement(successmessage).getText()
        );
        return this;
    }

    public CustomerCartPage ClickOkButton() {
        click(this.okbutton);
        return this;
    }
    public boolean EmptyCartMessage() {
        return isDisplayed(emptyMessage);
    }

}
