@Smoke @customer @CustomerCart

  Feature: Customer cart Functionality

    Scenario: Customer Confirm items in the cart
      Given user is logged in as a "customer"
      When customer in the cart
      And customer click confirm cart
      Then customer should see the "order successfully" Message
      And click ok button
      And  asser the cart is empty now


