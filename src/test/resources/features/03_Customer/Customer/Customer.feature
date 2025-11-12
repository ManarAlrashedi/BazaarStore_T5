@US04
Feature: Customer Page - Browse Products

  Scenario: US04_TC001 - Validate that products are displayed correctly on the Customer Page
    Given the user is logged in
    And the user navigates to the Customer Page
    Then all products should be displayed with Name, Price, Description, and Images

  Scenario: US04_TC002 - Validate product browsing and performance
    Given the user is logged in
    And the user navigates to the Customer Page
    Then products should load quickly with all details displayed correctly
