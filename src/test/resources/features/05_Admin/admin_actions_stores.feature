@Smoke
Feature: Admin Store Functionality

  Background:
    Given user is logged in as a "admin"

  @ViewAllStores
  Scenario: Admin views all stores
    When admin user navigates to store management page
    Then a list of stores should be displayed
    And each store entry should contain a name, description, admin name, and action buttons

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


  @NegativePath
  Scenario: An error appears for invalid or missing inputs
    Given user is on the stores page
    When user clicks edit button
    And user clears name
    And user clicks the submit button
    Then user should see name is required error message
    And assert the negative editing via API

  @HappyPath
  Scenario: Admin can update Name, Description, Location and Admins
    Given user is on the stores page
    When user clicks edit button
    And user edits name
    And user edits description
    And user edits location
    And user edits admins
    Then user clicks the submit button
    Then user should see a success message for the update
    Then assert Changes reflect in the stores list
    Then assert the updated data via API


  @DeleteStoreNegative
  Scenario: Delete Store Negative Path
    When admin user navigates to store management page
    And verify at least one store exists in the system
    And admin user clicks delete button for a "books"
    And admin user cancel the deletion
    Then the store "books" should still be present in the store list
    And assert the store deletion via API

  @DeleteStoreHappyPath
  Scenario: Delete Store Happy Path
    When admin user navigates to store management page
    And verify at least one store exists in the system
    And admin user clicks delete button for a "books"
    And admin user confirms the deletion
    Then the store "books" should be removed from the store list
    And assert the store deletion via API