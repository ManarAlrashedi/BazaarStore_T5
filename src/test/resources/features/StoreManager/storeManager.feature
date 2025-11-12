@UpdateProduct
Feature: Update Products Feature

  Background:
    Given user is logged in as a "store manager"
    And user goes to the products page

  @HappyPath
  Scenario: Can update any product details
    When user clicks the edit button
    And user  Edit the price
    Then user clicks the submit button
    Then user should see a success message for the update
    Then assert the price updated via API

  @HappyPath
  Scenario: Changes reflect in the catalog after saving
    When user clicks the edit button
    And user  Edit the catalog
    Then user clicks the submit button
    Then user should see a success message for the update
    Then assert the products catalog via API

  @NegativePath
  Scenario: An error is shown for invalid or missing inputs
    When user clicks the edit button
    And user clear the stock
    Then user clicks the submit button
    Then user should see a error message for required field