Feature: Admin Functionality

  Background:
    Given user is logged in as a "admin"


  @AddButtonVisible
  Scenario: admin can see the add user button
    When admin user navigates to store management page
    And The ADDSTORE button should be visible
    Then Admin click ADDSTORE button and in create store page

  @NegativeAll
  Scenario:Validation error when the form is empty
    When admin user navigates to store management page
    And Admin click ADDSTORE button and in create store page
    And clicks Submit Button
    Then message from all the requireds field displayed


  @NameEmpty
  Scenario: Validation error when Name is empty
    When admin user navigates to store management page
    And Admin click ADDSTORE button and in create store page
    When admin fills the form except the Name field
    And clicks Submit Button
    Then A validation message for the Name field should be displayed

  @LocationEmpty
  Scenario: Validation error when Location is empty
    When admin user navigates to store management page
  And Admin click ADDSTORE button and in create store page
    When admin fills the form except the Location field
    And clicks Submit Button
    Then A validation message for the Location field should be displayed

  @DescriptionEmpty
  Scenario: Validation error when Description is empty
    When admin user navigates to store management page
    And Admin click ADDSTORE button and in create store page
     And admin fills the form except the Description field
    And clicks Submit Button
    Then A validation message for the Description field should be displayed

  @AddedStoreNegativePath
  Scenario: Verify store creation with valid input
    When admin user navigates to store management page
    And Admin click ADDSTORE button and in create store page
    When  Admin fills required fields with valid data except select admin
    And clicks Submit Button
    And failing message appear

  @AddedStoreHappyPath
  Scenario: Verify store creation with valid input
    When admin user navigates to store management page
    And Admin click ADDSTORE button and in create store page
    When the Admin fills in all required fields with valid data
    And clicks Submit Button
    Then admin see Success Message



  @ValidateNewStore
  Scenario: New store appears in the list
    When admin user navigates to store management page
    Then new store should be visible in the store list
    #And assert the store Adding via API



  @DeleteStoreHappyPath
  Scenario: Delete Store Happy Path
    When admin user navigates to store management page
    And verify at least one store exists in the system
    And admin user clicks delete button for a "TestDelete"
    And admin user confirms the deletion
    Then the store "TestDelete" should be removed from the store list
    And assert the store deletion via API

  @DeleteStoreNegative
  Scenario: Delete Store Negative Path
    When admin user navigates to store management page
    And verify at least one store exists in the system
    And admin user clicks delete button for a "TestDelete"
    And admin user cancel the deletion
    Then the store "TestDelete" should still be present in the store list
    And assert the store deletion via API
