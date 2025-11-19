#@Smoke

@StoreManager
Feature: Store Manager Functionality

  #Background:
    #Given user is logged in as a "store manager"

  @ViewCatalog
  Scenario: Store Manager Views Products Catalog
    When store manager navigates to the products page
    Then verify the list of products displayed
    And each product should have a name, price, stoke, category, image, and action buttons
    And assert the products catalog via API

  @AddNewProduct
  Scenario: Store Manager Adds a New Product
    When store manager navigates to the products page
    When the Store Manager clicks the Add New Product button
    And fills in the product details
    And submits the new product form
    Then should see a success message for the addition and the product display in list
    And assert the new product in the catalog via API

  @AddProductNegative
  Scenario: Store Manager Adds a New Product
    When store manager navigates to the products page
    When the Store Manager clicks the Add New Product button
    And fills in the product details with missing required fields
    And submits the new product form
    Then should see a error message for the addition

  @AddProductNegative
  Scenario: Store Manager Adds a New Product
    When store manager navigates to the products page
    When the Store Manager clicks the Add New Product button
    And fills in the product details with duplicate product SKU
    And submits the new product form
    Then should see a error message for the duplication

  @NegativePath
  Scenario: An error is shown for invalid or missing inputs
    And user goes to the products page
    When user clicks the edit button
    And user clear the stock
    Then user clicks the submit button
    Then user should see a error message for required field

  @HappyPath @UpdateProduct
  Scenario: Can update any product details
    And user goes to the products page
    When user clicks the edit button
    And user  Edit the price
    Then user clicks the submit button
    Then user should see a success message for the update
    #Then assert the price catalog via API

  @HappyPath
  Scenario: Changes reflect in the catalog after saving
    And user goes to the products page
    When user clicks the edit button
    And user  Edit the catalog
    Then user clicks the submit button
    Then user should see a success message for the update
    Then assert the products catalog via API

  @CancelDelete
  Scenario: Verify Store Manager cancels deletion from confirmation dialog
    When store manager navigates to the products page
    When the Store Manager clicks the delete button for a specific item
    And clicks the cancel button
    Then the product should still exist in the catalog

  @ManagerDeleteProduct
  Scenario: Verify that Store Manager can delete any item
    When store manager navigates to the products page
    When the Store Manager clicks the delete button for a specific item
    And confirms the deletion
    Then a success message should appear

  @DeletionProof
  Scenario: Verify deleted proof is not listed in catalog
    When store manager navigates to the products page
    And the catalog should reflect the updated state
    Then assert the product been removed via API

  @Smoke @HappyPath
  Scenario: Successful login with valid credentials
    When user enters email "storemanager@sda.com" and password "Password.12345"
    And user clicks login button


