package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class CustomerCartPage extends BasePage{


    private final By confirmbutton = By.id("clear-all");
    private final By successmessage = By.id("swal2-html-container");
    private final By okbutton = By.cssSelector("button[class='swal2-confirm swal2-styled']");


    public CustomerCartPage ClickConfirmButton() {
        Driver.getDriver().findElement(this.confirmbutton).click();
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
        Driver.getDriver().findElement(this.okbutton).click();
        return this;
    }

}
