Feature: Delete Users as an Admin

  Background:
    Given user is logged in as a "admin"

  @DeleteUserHappyPath
  Scenario: Delete User Happy Path
    When admin user navigates to user management page
    And verify at least one user exists in the system
    And admin user clicks delete button for a user
    And admin user confirms the deletion
    Then the user should be removed from the store list
    #And assert the store deletion via API