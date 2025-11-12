@AddProdecTocart

Feature: Add products to the cart as a customer
Background:
  Given user is logged in as a "customer"
  Scenario: Customer adds a product to the cart successfully
    When customer user in customer page
    When the customer clicks Add to Cart on a product
    Then the customer should see success message
    And the cart item count should update automatically


