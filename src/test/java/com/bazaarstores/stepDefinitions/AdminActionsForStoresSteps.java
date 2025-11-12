package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.pages.UsersPage;
import com.bazaarstores.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminActionsForStoresSteps {

    AllPages allPages = new AllPages();
    Faker faker =new Faker();
    //public String newStore;

    @Then("The ADDSTORE button should be visible")
    public void theADDSTOREButtonShouldBeVisible() {
        allPages.getStoresPage().isAddStoreButtonVisible();
        assert true;
    }

    @Then("Admin click ADDSTORE button and in create store page")
    public void adminClickADDSTOREButtonAndInCreateStorePage() {
        allPages.getStoresPage().ClickAddStoreButton();
    }

    @Then("message from all the requireds field displayed")
    public void messageFromAllTheRequiredsFieldDisplayed() {
       allPages.getCreateStorePage()
               .missingName()
               .missingDescription()
               .missingLocation();

    }
    @When("admin fills the form except the Name field")
    public void adminFillsTheFormExceptTheNameField() {
        allPages.getCreateStorePage()
                .enterLocation(faker.address().city())
                .selectAdmin("Store Manager")
                .enterDescription(faker.lorem().paragraph());


    }

    @Then("A validation message for the Name field should be displayed")
    public void aValidationMessageForTheNameFieldShouldBeDisplayed() {
        allPages.getCreateStorePage().missingName();

    }

    @When("admin fills the form except the Location field")
    public void adminFillsTheFormExceptTheLocationField() {
        allPages.getCreateStorePage()
                .enterName("TeamFive")
                .selectAdmin("Store Manager")
                .enterDescription(faker.lorem().paragraph());

    }

    @Then("A validation message for the Location field should be displayed")
    public void aValidationMessageForTheLocationFieldShouldBeDisplayed() {
        allPages.getCreateStorePage().missingLocation();

    }

    @And("admin fills the form except the Description field")
    public void adminFillsTheFormExceptTheDescriptionField() {
        allPages.getCreateStorePage()
                .enterName("TeamFive")
                .enterLocation(faker.address().city())
                .selectAdmin("Store Manager");
    }

    @Then("A validation message for the Description field should be displayed")
    public void aValidationMessageForTheDescriptionFieldShouldBeDisplayed() {
        allPages.getCreateStorePage().missingDescription();

    }

    @When("the Admin fills in all required fields with valid data")
    public void theAdminFillsInAllRequiredFieldsWithValidData() {
        allPages.getCreateStorePage()
                .enterName("TeamFive")
                .enterLocation(faker.address().city())
                .selectAdmin("Store Manager")
                .enterDescription(faker.lorem().paragraph());


    }
    @Then("admin see Success Message")
    public void adminSeeSuccessMessage() {
        allPages.getStoresPage().successMessage();

    }

    @When("Admin fills required fields with valid data except select admin")
    public void adminFillsRequiredFieldsWithValidDataExceptSelectAdmin() {

        allPages.getCreateStorePage()
                .enterName("TeamFive")
                .enterLocation(faker.address().city())
                .enterDescription(faker.lorem().paragraph());
    }


    @Then("failing message appear")
    public void failingMessageAppear() {

      allPages.getStoresPage().FailMessage();

    }


    @Then("new store should be visible in the store list")
    public void newStoreShouldBeVisibleInTheStoreList() {

      allPages.getStoresPage().NewStore();

    }

    @And("clicks Submit Button")
    public void clicksSubmitButton() {
        allPages.getCreateStorePage().clickSubmit();

    }
    @When("admin user navigates to store management page")
    public void admin_user_navigates_to_store_management_page() {
        allPages.getDashboardPage().clickStoreLink();
        Assert.assertTrue("Stores Page Should be Displayed"
                ,allPages.getStoresPage().isStoresPageDisplayed());
    }

    @When("verify at least one store exists in the system")
    public void at_least_one_store_exists_in_the_system() {
        Assert.assertTrue("At least one store should exist",
                allPages.getStoresPage().getStoresCount() > 0);
    }

    @When("admin user clicks delete button for a {string}")
    public void admin_user_clicks_delete_button_for_a_store(String storeName) {
        allPages.getStoresPage().clickDeleteStore(storeName);
        Assert.assertTrue("Confirmation dialog should be displayed",
                allPages.getStoresPage().isDeleteConfirmationDialogDisplayed());

    }

    @When("admin user confirms the deletion")
    public void admin_user_confirms_the_deletion() {
        allPages.getStoresPage().confirmDeleteStore();
        Assert.assertTrue("Store should be deleted successfully",
                allPages.getStoresPage().isStoreDeletedMessageDisplayed());
    }

    @Then("the store {string} should be removed from the store list")
    public void the_store_should_be_removed_from_the_store_list(String name) {
        Assert.assertTrue("Store should be removed from the list",
                allPages.getStoresPage().isStoreRemoveFromList(name));
    }

    @And("admin user cancel the deletion")
    public void adminUserCancelTheDeletion() {
        allPages.getStoresPage().cancelDeleteStore();
        Assert.assertTrue("Confirmation dialog should be closed",
                allPages.getStoresPage().isDeleteConfirmationDialogDisplayed());
    }

    @Then("the store {string} should still be present in the store list")
    public void theStoreShouldStillBePresentInTheStoreList(String name) {
        Assert.assertTrue("Store should still be present in the list",
                allPages.getStoresPage().isStoreStillPresentInList(name));
    }



    @Given("Admin is logged in")
    public void admin_is_logged_in() {
        allPages.getLoginPage().loginAsAdmin();
    }

    @When("Admin navigates to Users Page")
    public void admin_navigates_to_users_page() {
        allPages.getUserPage().navigateToUsersPage();
        Assert.assertTrue("Users table should be displayed",
                allPages.getUserPage().isUsersTableDisplayed());
    }

    @When("Admin enters {string} in search field and clicks search")
    public void admin_enters_in_search_field_and_clicks_search(String email) {
        allPages.getUserPage().enterSearchEmail(email);
        allPages.getUserPage().clickSearch();
    }

    @Then("Admin should see all registered users with Name and Email")
    public void admin_should_see_all_registered_users_with_name_and_email() {

        UsersPage usersPage = allPages.getUserPage();

        // Scroll للأسفل للصفحة الحالية
        ((org.openqa.selenium.JavascriptExecutor) Driver.getDriver())
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // الانتظار البسيط للصفحة لتحميل الجدول
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        // الحصول على جميع الصفوف عبر جميع صفحات الـ Pagination
        int totalUsers = usersPage.getAllUsers().size();

        // تحقق من وجود مستخدمين
        Assert.assertTrue("Expected at least one user to be displayed, but found none", totalUsers > 0);

        System.out.println("Total users found across all pages: " + totalUsers);
    }



    @Then("Only the user with matching email is displayed")
    public void only_the_user_with_matching_email_is_displayed() {
        Assert.assertEquals(1, allPages.getUserPage().getAllUsers().size());
    }

    @Then("A message {string} is displayed")
    public void a_message_is_displayed(String message) {
        Assert.assertTrue(allPages.getUserPage().isNoUsersMessageDisplayed());
    }
}



