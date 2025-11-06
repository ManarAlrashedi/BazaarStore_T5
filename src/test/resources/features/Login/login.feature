@Regression @Login
Feature: Login Functionality

  @Smoke @Customer
  Scenario: Successful login with valid credentials
    When user enters email "customer@sda.com" and password "Password.12345"
    And user clicks login button
    Then user should be logged in successfully
    #And assert the successful login via API

  @Negative
  Scenario: Login with empty email
    When user enters email "" and password "Password.12345"
    And user clicks login button
    Then user should see empty "email" error message

  @Negative
  Scenario: Login with empty password
    When user enters email "customer@sda.com" and password ""
    And user clicks login button
    Then user should see empty "password" error message

  @Negative
  Scenario: Login with empty email and password
    When user enters email "" and password ""
    And user clicks login button
    Then user should see empty "email" error message

  @Negative
  Scenario: Login with email not available
    When user enters email "invalid@test.com" and password "WrongPassword"
    And user clicks login button
    Then user should see error message
    And user should remain on login page

  @Negative
  Scenario: Login without @ symbol
    When user enters email "customersda.com" and password "Password.12345"
    And user clicks login button
    Then user should see invalid "email" error message
    And user should remain on login page

  @Negative
  Scenario: Login with an incorrect password
    When user enters email "customer@sda.com" and password "WrongPassword"
    And user clicks login button
    Then user should see error message
    And user should remain on login page

  @Admin
  Scenario: Successful login as Admin
    When user enters email "admin@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully

  @StoreManager
  Scenario: Successful login as Store Manager
    When user enters email "storemanager@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully