@Smoke1 @Customer
Feature: Customer Functionalities

  Background:
    Given user is logged in as a "customer"

  @US04TC01 @DisplayProducts
  Scenario: Verify that products are displayed correctly on the Customer Page
    When the user navigates to the Customer Page
    And the user observes the displayed products
    Then each product should show its Name, Price, Description, and Image

  @US04TC02 @PagePerformance
  Scenario: Verify that products load quickly and details are displayed after page refresh
    When the user navigates to the Customer Page
    And the user refreshes the Customer Page
    Then the products should load quickly and display complete details

  @US0501 @DisplayProductDetails
  Scenario: Verify that clicking on each product shows its details
    And the user navigates to the Customer Page
    When the user clicks on each product in the grid
    Then the product details Name, Price, Description, Image should be displayed

  @US0601 @AddProductToCart
  Scenario: Customer adds a product to the cart successfully
    When the customer clicks Add to Cart on a product
    #Then the customer should see success message
    Then the cart item count should update automatically

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

  @US0801 @ViewCustomerCart
  Scenario: Customer views and removed items in their cart
    Given the customer has items in the cart
    When the customer hovers over the cart icon
    Then the customer should see View Cart button all items with prices and total cost

  @US0901 @CustomerCart
  Scenario: Customer Confirm items in the cart
    When the customer clicks Add to Cart on a product
    Then the cart item count should update automatically
    When customer in the cart
    And customer click confirm cart
    Then customer should see the "order successfully" Message
    And click ok button
    And  asser the cart is empty now

  @US0802 @RemoveItemFromCart
  Scenario: Customer removes an item from the cart
    When the customer clicks Add to Cart on a product
    Then the cart item count should update automatically
    Given the customer has items in the cart
    When the customer hovers over the cart icon
    And the customer removes an item from the cart
    #Then the customer should see a success message
    And the total cost should update accordingly

  @US0803 @CustomerCartEmpty
  Scenario: Customer views empty cart
    Given the customer has no items in the cart
    When the customer hovers over the cart icon
    Then the cart should display a message indicating the cart is empty


