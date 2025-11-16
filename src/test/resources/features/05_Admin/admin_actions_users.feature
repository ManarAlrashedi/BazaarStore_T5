@Smoke
Feature: Delete Users as an Admin

  Background:
    Given user is logged in as a "admin"

  @US14_TC001 @ViewAllUsers @no
  Scenario: Admin can view all users
    When admin user navigates to users management page
    Then Admin should see all registered users with Name and Email

  @US14_TC002 @SearchValidEmail
  Scenario:Search functionality with valid email
    When admin user navigates to users management page
    And Admin enters "maryam.schinner@yahoo.com" in search field and clicks search
    Then Only the user with matching email is displayed

  @US14_TC003 @SearchInvalidEmail
  Scenario: Search functionality with non-existing email
    When admin user navigates to users management page
    And Admin enters "Nouf4@test.com" in search field and clicks search
    Then A message No users found. is displayed

    @US15_TC001 @AddNewUser
  Scenario: Admin adds a valid new user
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "lammma@sda.com","Pasword.12345","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be added successfully

    @US15_TC002 @AddNewUserNegative
  Scenario: Admin adds a new user with empty name
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "", "lamma@sda.com","Pasword.12345","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message

   @US15_TC003  @AddNewUserNegative
  Scenario: Admin adds a new user with empty email
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "","Pasword.12345","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message

    @US15_TC004  @AddNewUserNegative
  Scenario: Admin adds a new user with empty password
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "lamma@sda.com","","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message

    @US15_TC005  @AddNewUserNegative
  Scenario: Admin adds a new user with empty password confirmation
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "lamma@sda.com","Pasword.12345","" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message

    @US15_TC006  @AddNewUserNegative
  Scenario: Admin adds a new user with mismatched password confirmation
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "lamma@sda.com","Pasword.12345","wrongPass.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message

    @US15_TC007  @AddNewUserNegative
  Scenario: Admin adds a new user with password less than 6 characters
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "lamma@sda.com","1234","1234" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message

    @US15_TC008  @AddNewUserNegative
  Scenario: Admin adds a new user with an email that already exists
    When admin user navigates to users management page
    And admin user click add users button
    And  admin user enters valid "lama", "lamma@sda.com","Pasword.12345","Pasword.12345" and "Customer"
    And admin user clicks Submit button
    Then the user should be see error message

  @US16_TC001 @EditUser
  Scenario: Validate user edit functionality as an Admin
    When admin user navigates to users management page
    And admin user clicks edit button for a user "lama"
    And admin user updates user details with valid "Sara" and "sara@sda.com"
    And admin user clicks Save button
    Then the user details should be updated successfully

  @US16_TC002 @EditUserNegative
  Scenario: Validate user edit functionality with invalid data as an Admin
    When admin user navigates to users management page
    And admin user clicks edit button for a user "lama"
    And admin user updates user details with valid "Sara" and ""
    And admin user clicks Save button
    Then the user should be see error message

  @DeleteUserHappyPath
  Scenario: Delete User Happy Path
    When admin user navigates to users management page
    And admin user should see the users list with name, email and actions columns
    And verify at least one user exists in the system
    And admin user clicks delete button for a user "sara@sda.com"
    And admin user confirms the deletion
    Then the user "sara@sda.com" it does not deleted due to a bug
    And assert the user deletion via API

  @DeleteUserNegative
  Scenario: Delete User Negative Path
    When admin user navigates to users management page
    And admin user should see the users list with name, email and actions columns
    And verify at least one user exists in the system
    And admin user clicks delete button for a user "sara@sda.com"
    And admin user cancel the deletion
    Then the user "sara@sda.com" should still be present in the users list
    And assert the user deletion via API