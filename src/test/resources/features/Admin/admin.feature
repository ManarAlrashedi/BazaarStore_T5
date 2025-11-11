@UpdateStoreDetails
Feature: Update store details Feature

  Background:
    Given user is logged in as a "admin"
    And user is on the stores page

  @HappyPath1
  Scenario: Admin can update Name, Description, Location and Admins
    When user clicks edit button
    And user edits name
    And user edits description
    And user edits location
    And user edits admins
    Then user clicks the submit button
    Then user should see a success message for the update
    Then assert Changes reflect in the stores list

  @NegativePath
  Scenario: An error appears for invalid or missing inputs
    When user clicks edit button
    And user clears name
    And user clicks the submit button
    Then user should see name is required error message
    And assert the negative editing via API
