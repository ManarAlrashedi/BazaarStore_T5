@Registration
Feature: Registration Feature

  @HappyPathRegistration
  Scenario: Registration Happy Path
    Given user goes to homepage
    When user clicks registration link
    And user enters email for sign up "faker"
    And user enters full name for sign up "John Doe"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sing up button
    Then user should see success message for registration
    And assert the registration via API

  @NegativeRegistration
  Scenario: Registration Negative with invalid name
    Given user goes to homepage
    When user clicks registration link
    And user enters email for sign up "faker"
    And user enters full name for sign up "John@12Doe"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sing up button
    Then user should see invalid name error message
    And assert the negative registration via API using email "faker"

  @NegativeRegistration
  Scenario: Registration Negative with invalid email
    Given user goes to homepage
    When user clicks registration link
    And user enters email for sign up "invalid_email.com"
    And user enters full name for sign up "John Doe"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sing up button
    Then user should see invalid email error message
    And assert the negative registration via API using email "invalid_email.com"

  @NegativeRegistration
  Scenario: Registration Negative with missing name
    Given user goes to homepage
    When user clicks registration link
    And user enters email for sign up "faker"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sing up button
    Then user should see name is required error message
    And assert the negative registration via API using email "faker"

  @NegativeRegistration
  Scenario: Registration Negative with password's length is less
    Given user goes to homepage
    When user clicks registration link
    And user enters email for sign up "faker"
    And user enters full name for sign up "John Doe"
    And user enters short password for sign up
    And user enters confirm short password for sign up
    And user clicks the sing up button
    Then user should see password is short error message
    And assert the negative registration via API using email "faker"


  @NegativeRegistration
  Scenario: Registration Negative with Confirm Password field does not match
    Given user goes to homepage
    When user clicks registration link
    And user enters email for sign up "faker"
    And user enters full name for sign up "John Doe"
    And user enters password for sign up
    And user enters confirm password does not match for sign up
    And user clicks the sing up button
    Then user should see password does not match error message
    And assert the negative registration via API using email "faker"


