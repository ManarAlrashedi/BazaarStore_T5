@AdminStore
Feature: View all stores in the system as an Admin

  Scenario: Admin views all stores
    Given the admin is logged in
    When the admin navigates to Stores page
    Then a list of all stores should be displayed
