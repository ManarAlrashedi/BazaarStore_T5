@AdminStore
Feature: View all stores in the system as an Admin
  Background:
    Given user is logged in as an "admin"
  Scenario: Admin views all stores
    When the admin navigates to the Stores page
    Then a list of stores should be displayed
    And each store entry should contain a name, description, admin name, and action button


