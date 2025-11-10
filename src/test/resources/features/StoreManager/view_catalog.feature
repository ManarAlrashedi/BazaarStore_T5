Feature: Products Catalog View Feature

  @ViewCatalog
  Scenario: Store Manager Views Products Catalog
    Given user is logged in as a "store manager"
    When store manager navigates to the products page
    Then verify the list of products displayed
    And each product should have a name, price, stoke, category, image, and action buttons
    And assert the products catalog via API