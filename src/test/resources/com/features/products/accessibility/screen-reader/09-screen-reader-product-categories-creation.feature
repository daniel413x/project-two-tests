@screen-reader-product-categories-creation
Feature: Product categories creation via screen reader

  Scenario: A new product category is created via screen reader
    Given I am on the product categories page
    And a screen reader is enabled
    When the product categories load
    When I focus and activate the "Add Category" button
    Then I focus, activate, and enter valid input for a new product category into the modal form
    And I focus and activate the "Create" button
    Then the screen reader should confirm the new category was created