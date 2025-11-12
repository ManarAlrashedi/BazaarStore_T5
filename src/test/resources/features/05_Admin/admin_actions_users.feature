
@Smoke
Feature: Delete Users as an Admin

  Background:
    Given user is logged in as a "admin"

  @DeleteUserHappyPath
  Scenario: Delete User Happy Path
    When admin user navigates to user management page
    And admin user should see the users list with name, email and actions columns
    And verify at least one user exists in the system
    And admin user clicks delete button for a user "sra@mail.com"
    And admin user confirms the deletion
    Then the user "sra@mail.com" it does not deleted due to a bug
    And assert the user deletion via API

  @DeleteUserNegative
  Scenario: Delete User Negative Path
    When admin user navigates to user management page
    And admin user should see the users list with name, email and actions columns
    And verify at least one user exists in the system
    And admin user clicks delete button for a user "sra@mail.com"
    And admin user cancel the deletion
    Then the user "sra@mail.com" should still be present in the users list
    And assert the user deletion via API