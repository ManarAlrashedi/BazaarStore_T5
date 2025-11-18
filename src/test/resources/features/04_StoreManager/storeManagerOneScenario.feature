@Smoke @StoreManager
Feature: Store Manager Functionality

  Scenario: Store Manager Features
    Given user is logged in as a "store manager"
    When store manager navigates to the products page
    Then verify the list of products displayed
    And each product should have a name, price, stoke, category, image, and action buttons
    And assert the products catalog via API
    When the Store Manager clicks the Add New Product button
    And fills in the product details with missing required fields
    And submits the new product form
    Then should see a error message for the addition
    And fills in the product details
    And submits the new product form
    Then should see a success message for the addition and the product display in list
    And assert the new product in the catalog via API
    When store manager navigates to the products page
    When the Store Manager clicks the Add New Product button
    And fills in the product details with duplicate product SKU
    And submits the new product form
    Then should see a error message for the duplication
    When store manager navigates to the products page
    When user clicks the edit button
    And user clear the stock
    Then user clicks the submit button
    Then user should see a error message for required field
    And user  Edit the price
    Then user clicks the submit button
    Then user should see a success message for the update
    #Then assert the price catalog via API
    When user clicks the edit button
    And user  Edit the catalog
    Then user clicks the submit button
    Then user should see a success message for the update
    Then assert the products catalog via API
    When the Store Manager clicks the delete button for a specific item
    And clicks the cancel button
    Then the product should still exist in the catalog
    When the Store Manager clicks the delete button for a specific item
    And confirms the deletion
    Then a success message should appear
    And the catalog should reflect the updated state
    Then assert the product been removed via API