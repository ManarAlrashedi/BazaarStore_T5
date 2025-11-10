Feature: Store Manager Functionality

  @ManagerDeleteProduct
  Scenario: Verify that Store Manager can delete any item
    Given user is logged in as a "store manager"
    When the Store Manager clicks the delete button for a specific item
    And confirms the deletion
    Then a success message should appear
    And assert the product been removed via API

  @CancelDelete
  Scenario: Verify Store Manager cancels deletion from confirmation dialog
    Given user is logged in as a "store manager"
    When the Store Manager clicks the delete button for a specific item
    And clicks the cancel button
    Then the product should still exist in the catalog

  @DeletionProof
  Scenario: Verify deleted proof is not listed in catalog
    Given user is logged in as a "store manager"
    When they search for the deleted proof "@example"
    Then "@example" should not appear in the catalog list
    And the catalog should reflect the updated state
