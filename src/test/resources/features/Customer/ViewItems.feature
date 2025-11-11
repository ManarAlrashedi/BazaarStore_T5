@CustomerCart
Feature: View items in the cart as a customer
Background: 
  Given user is logged in as a "customer"
  Scenario: Customer views and removed items in their cart
    Given the customer has items in the cart
    When the customer hovers over the cart icon and click view cart button
    Then the cart page should display all items with prices and total cost
    When the customer clicks the "Remove" button
    Then the product should be removed from the cart
    And the cart should update to reflect the new total cost and item count
#customersteps , customerpage ,customercartpage