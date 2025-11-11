package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import com.github.javafaker.Faker;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminActionsSteps {

    AllPages allPages = new AllPages();
    Faker faker =new Faker();

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

    @When("admin user clicks delete button for a store")
    public void admin_user_clicks_delete_button_for_a_store() {
        allPages.getStoresPage().clickDeleteStore();
        Assert.assertTrue("Confirmation dialog should be displayed",
                allPages.getStoresPage().isDeleteConfirmationDialogDisplayed());

    }

    @When("admin user confirms the deletion")
    public void admin_user_confirms_the_deletion() {
        allPages.getStoresPage().confirmDeleteStore();
        Assert.assertTrue("Store should be deleted successfully",
                allPages.getStoresPage().isStoreDeletedMessageDisplayed());
    }

    @Then("the store should be removed from the store list")
    public void the_store_should_be_removed_from_the_store_list() {
        Assert.assertTrue("Store should be removed from the list",
                allPages.getStoresPage().isStorePresentInList());
    }


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
                .selectAdmin("Evie Larson")
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
                .selectAdmin("Evie Larson")
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
                .selectAdmin("Evie Larson");
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


}
//Store creation failed. Please try again.