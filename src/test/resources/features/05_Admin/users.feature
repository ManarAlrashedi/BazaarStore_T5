@Smoke @Users
Feature: Admin User Functionality

  Scenario: User Feature Setup
    Given user is logged in as a "admin"
    When admin user navigates to users management page
    Then Admin should see all registered users with Name and Email
    And Admin enters "maryam.schinner@yahoo.com" in search field and clicks search
    Then Only the user with matching email is displayed
    And Admin enters "Nouf4@test.com" in search field and clicks search
    Then A message "No users found." is displayed
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "lamma3@sda.com","Pasword.12345","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be added successfully
    When admin user navigates to users management page
    When admin user click add users button
    And  admin user enters valid "", "lamma@sda.com","Pasword.12345","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message
    And  admin user enters valid "lama", "","Pasword.12345","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message
    And  admin user enters valid "lama", "lamma@sda.com","","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message
    And  admin user enters valid "lama", "lamma@sda.com","Pasword.12345","" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message
    When admin user navigates to users management page
    And admin user clicks edit button for a user "lama"
    And admin user updates user details with valid "Lea" and "Lea@sda.com"
    And admin user clicks Save button
    Then the user details should be updated successfully
    And admin user clicks edit button for a user "Lea"
    And admin user updates user details with valid "Lea" and ""
    And admin user clicks Save button
    Then the user should be see error message
    When admin user navigates to users management page
    And admin user should see the users list with name, email and actions columns
    And verify at least one user exists in the system
    And admin user clicks delete button for a user "Lea@sda.com"
    And admin user confirms the deletion
    Then the user "Lea@sda.com" it does not deleted due to a bug
    And assert the user deletion via API
    And admin user clicks delete button for a user "Lea@sda.com"
    And admin user cancel the deletion
    Then the user "Lea@sda.com" should still be present in the users list
    And assert the user deletion via API