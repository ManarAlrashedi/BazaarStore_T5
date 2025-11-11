Feature: Delete Store as an Admin

  Background:
    Given user is logged in as a "admin"

  @DeleteStoreHappyPath
  Scenario: Delete Store Happy Path
    When admin user navigates to store management page
    And verify at least one store exists in the system
    And admin user clicks delete button for a "TestDelete"
    And admin user confirms the deletion
    Then the store should be removed from the store list
    #And assert the store deletion via API

  @DeleteStoreNegative
  Scenario: Delete Store Negative Path
    When admin user navigates to store management page
    And verify at least one store exists in the system
    And admin user clicks delete button for a "TestDelete"
    And admin user cancel the deletion
    Then the store should still be present in the store list
    #And assert the store deletion via API