@Smoke @Store
Feature: Admin Store Functionality

  Scenario: Store Features
    Given user is logged in as a "admin"
    When admin user navigates to store management page
    Then a list of stores should be displayed
    And each store entry should contain a name, description, admin name, and action buttons
    And The ADDSTORE button should be visible
    Then Admin click ADDSTORE button and in create store page
    And clicks Submit Button
    Then message from all the requireds field displayed
    When admin fills the form except the Name field
    And clicks Submit Button
    Then A validation message for the Name field should be displayed
    When admin fills the form except the Location field
    And clicks Submit Button
    Then A validation message for the Location field should be displayed
    And admin fills the form except the Description field
    And clicks Submit Button
    Then A validation message for the Description field should be displayed
    When  Admin fills required fields with valid data except select admin
    And clicks Submit Button
    And failing message appear
    When admin user navigates to store management page
    Then Admin click ADDSTORE button and in create store page
    When the Admin fills in all required fields with valid data
    And clicks Submit Button
    Then admin see Success Message
    Then new store should be visible in the store list
    #And assert the store Adding via API
    When user clicks edit button
    And user clears name
    And user clicks the submit button
    Then user should see name is required error message
    And assert the negative editing via API
    When admin user navigates to store management page
    When user clicks edit button
    And user edits name
    And user edits description
    And user edits location
    #And user edits admins
    Then user clicks the submit button
    Then user should see a success message for the update
    Then assert Changes reflect in the stores list
    Then assert the updated data via API
    When admin user navigates to store management page
    And verify at least one store exists in the system
    And admin user clicks delete button for a "booksStore"
    And admin user cancel the deletion
    Then the store "booksStore" should still be present in the store list
    And assert the store deletion via API
    And admin user clicks delete button for a "booksStore"
    And admin user confirms the deletion
    Then the store "booksStore" should be removed from the store list
    And assert the store deletion via API

