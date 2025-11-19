@Regression @Login
Feature: Login Functionality

  @Smoke
  @HappyPath
  Scenario: Successful login with valid credentials
    When user enters email "customer@sda.com" and password "Password.12345"
    And user clicks login button
    Then user should be logged in successfully
    And assert the successful login via API

  @Smoke
  @Negative
  Scenario: Login with empty email
    When user enters email "" and password "Password.12345"
    And user clicks login button
    Then user should see empty "email" error message
    And assert the negative login via API

  @Smoke
  @Negative
  Scenario: Login with empty password
    When user enters email "customer@sda.com" and password ""
    And user clicks login button
    Then user should see empty "password" error message
    And assert the negative login via API using email "customer@sda.com"

  @Smoke
  @Negative
  Scenario: Login with empty email and password
    When user enters email "" and password ""
    And user clicks login button
    Then user should see empty "email" error message
    And assert the negative login via API

  @Smoke
  @Negative
  Scenario: Login with email not available
    When user enters email "invalid@test.com" and password "WrongPassword"
    And user clicks login button
    Then user should see error message
    And user should remain on login page
    And assert the negative login via API

  @Smoke
  @Negative
  Scenario: Login without @ symbol
    When user enters email "customersda.com" and password "Password.12345"
    And user clicks login button
    Then user should see invalid "email" error message
    And user should remain on login page
    And assert the negative login via API

  @Smoke
  @Negative
  Scenario: Login with an incorrect password
    When user enters email "customer@sda.com" and password "WrongPassword"
    And user clicks login button
    Then user should see error message
    And user should remain on login page
    And assert the negative login via API using email "customer@sda.com"