@AdminUser
Feature: Add a new user as an Admin
Background:
  Given user is logged in as a "admin"
  Scenario: Admin adds a valid new user
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "lamma@sda.com","Pasword.12345","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be added successfully
    And verify the user in list

  Scenario: Admin adds a new user with empty name
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "", "lamma@sda.com","Pasword.12345","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message
#added another story with,usersteps,userpage