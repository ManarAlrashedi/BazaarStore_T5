package com.bazaarstores.pages.Admin;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;

import static org.junit.Assert.assertEquals;

public class CreateStorePage extends BasePage {


    private final By NameField = By.id("first-name-column");
    private final By LocationField = By.id("location-id-column");
    private final By AdminSelector = By.id("admin-column");
    private final By DescriptionField = By.xpath("//*[@id=\"tinymce\"]");
    private final By SubmitButton = By.xpath("//button[@type='submit']");
    private final By nameRequiredFieldMessage = By.xpath("//li[.='The name field is required.']");
    private final By DescriptionRequiredFieldMessage = By.xpath("//li[.='The description field is required.']");
    private final By LocationRequiredFieldMessage = By.xpath("//li[.='The location field is required.']");

    public static String storeName;
    public static String Location;
    public static String Descrption;

    public CreateStorePage enterName(String name) {
        storeName = name;
        sendKeys(NameField,name);
        return this;
    }

    public CreateStorePage enterLocation(String location) {
        Location=location;
        sendKeys(LocationField,location);
        return this;
    }

    public CreateStorePage selectAdmin(String adminName) {
        selectByVisibleText(AdminSelector, adminName);
        return this;

    }
    public CreateStorePage enterDescription(String descrption) {
        Descrption=descrption;
        Driver.getDriver().switchTo().frame("default_ifr");
        //scrollToElement(DescriptionField);
        sendKeys(DescriptionField,descrption);
        Driver.getDriver().switchTo().defaultContent();
        return this;


    }
    public CreateStorePage clickSubmit() {
        scrollToElement(SubmitButton);
        try {
            click(SubmitButton);
        } catch (ElementClickInterceptedException e) {
            clickWithJS(SubmitButton); // fallback
        }
        return this;
    }



    public CreateStorePage missingName ()  {
        assertEquals(
                "The name field is required.",
                getText(nameRequiredFieldMessage)
        );
        return this;
    }

        public CreateStorePage missingDescription () {
        assertEquals(
                "The description field is required.",
                getText(DescriptionRequiredFieldMessage)
        );
        return this;
    }
    public CreateStorePage missingLocation () {
        assertEquals(
                "The location field is required.",
                getText(LocationRequiredFieldMessage)
        );
        return this;
    }


}