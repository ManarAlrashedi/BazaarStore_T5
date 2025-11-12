package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.CustomerPage;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CustomerSteps {

    CustomerPage customerPage = new CustomerPage();

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        // نفتح الرابط من configuration.properties
        Driver.getDriver().get(ConfigReader.getProperty("customerPageUrl"));
        // يمكن إضافة login steps إذا لم يكن مسجل دخول مسبقًا
    }

    @Given("the user navigates to the Customer Page")
    public void the_user_navigates_to_the_customer_page() {
        // في حالة وجود روابط داخل BasePage
        Driver.getDriver().get(ConfigReader.getProperty("customerPageUrl"));
    }

    @Then("all products should be displayed with Name, Price, Description, and Images")
    public void all_products_should_be_displayed_with_name_price_description_and_images() {
        Assert.assertTrue("No products displayed on the Customer Page", customerPage.areProductsDisplayed());
        Assert.assertTrue("Some product details are missing", customerPage.areProductDetailsDisplayed());

        // طباعة أسماء المنتجات للتأكيد
        List<WebElement> products = customerPage.productList();
        for (WebElement product : products) {
            System.out.println("Product: " + product.findElement(By.cssSelector(".product-name")).getText());
        }
    }

    @Then("products should load quickly with all details displayed correctly")
    public void products_should_load_quickly_with_all_details_displayed_correctly() {
        long startTime = System.currentTimeMillis();

        // تحقق من عرض المنتجات
        Assert.assertTrue("Products did not load", customerPage.areProductsDisplayed());
        Assert.assertTrue("Product details missing", customerPage.areProductDetailsDisplayed());

        long endTime = System.currentTimeMillis();
        long loadTime = endTime - startTime;

        System.out.println("Products loaded in " + loadTime + " ms");

        // يمكن تحديد شرط سرعة التحميل حسب متطلبات المشروع
        Assert.assertTrue("Product loading is too slow", loadTime < 3000); // مثال: أقل من 3 ثواني
    }
}
