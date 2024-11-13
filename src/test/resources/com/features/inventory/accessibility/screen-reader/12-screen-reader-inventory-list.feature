@screen-reader-inventory-list
Feature: Inventory lists via screen reader

  Scenario Outline: View lists of inventory corresponding to the all inventory, warehouse inventory, and product category inventory pages via screen reader
    Given I am on the "<inventoryPage>" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    Then the screen reader should announce the page title as "Inventory - Crag Supply Co."

    Examples:
      | inventoryPage |
      | all           |
      | category 4    |
      | warehouse 1   |
