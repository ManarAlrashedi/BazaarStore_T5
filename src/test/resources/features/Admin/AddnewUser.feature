@AdminUser
Feature: Add a new user as an Admin
Background:
  Given user is logged in as a "admin"
@test
  Scenario: Admin adds a valid new user
  When admin user navigates to users management page
  And admin user click add users button
    And  admin user enters valid "lama", "lammma@sda.com","Pasword.12345","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be added successfully

  Scenario: Admin adds a new user with empty name
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "", "lamma@sda.com","Pasword.12345","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message

  Scenario: Admin adds a new user with empty email
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "","Pasword.12345","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message

  Scenario: Admin adds a new user with empty password
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "lamma@sda.com","","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message

  Scenario: Admin adds a new user with empty password confirmation
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "lamma@sda.com","Pasword.12345","" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message

  Scenario: Admin adds a new user with empty role
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "lamma@sda.com","Pasword.12345","Pasword.12345" and ""
    And admin user clicks Submit button
    Then the user should be see error message

  Scenario: Admin adds a new user with mismatched password confirmation
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "lamma@sda.com","Pasword.12345","wrongPass.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should see error message "Passwords do not match"

  Scenario: Admin adds a new user with password less than 6 characters
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "lamma@sda.com","1234","1234" and "Customer"
    And admin user clicks Submit button
    Then the user should see error message "Password must be at least 6 characters"


Scenario: Admin adds a new user with an email that already exists
    When admin user navigates to users management page
    And admin user clicks add users button
    And  admin user enters valid "lama", "lamma@sda.com","Pasword.12345","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should see error message "Email already exists"




