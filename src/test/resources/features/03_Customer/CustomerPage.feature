@Smoke
Feature: Customer Page - Product Display and Performance

  Background:
    Given the user is logged in
    And the user navigates to the Customer Page

  @US04TC01 @DisplayProducts
  Scenario: Verify that products are displayed correctly on the Customer Page
    When the user observes the displayed products
    Then each product should show its Name, Price, Description, and Image

  @US04TC02 @PagePerformance
  Scenario: Verify that products load quickly and details are displayed after page refresh
    When the user refreshes the Customer Page
    Then the products should load quickly and display complete details

  @US0401 @DisplayProductDetails
  Scenario: Verify that clicking on each product shows its details
    And the user navigates to the Customer Page
    When the user clicks on each product in the grid
    Then the product details (Name, Price, Description, Image) should be displayed

    @US0701 @FavoriteProducts
  Scenario: Mark a product as favorite
      When the user clicks a random heart icon
      Then the heart icon should become active
      And the selected product should appear in the My Favorites list

    @US0702 @RemoveFavoriteProducts
  Scenario: View and remove favorite products
    When the user clicks on the My Favorites link
    Then all favorited products should be displayed with correct details
    When the user clicks the heart icon again on a favorited product
    Then the product should be removed from the list
    And the heart icon should return to inactive state

