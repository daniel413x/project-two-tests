@screen-reader-product-categories
Feature: Product categories shown via screen reader

  Scenario:
    Given I am on the product categories page
    And a screen reader is enabled
    When the product categories load
    Then the screen reader should announce the page title as "Products - Crag Supply Co."
