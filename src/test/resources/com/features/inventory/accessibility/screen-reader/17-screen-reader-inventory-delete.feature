@screen-reader-inventory-delete
Feature: Inventory delete

  Scenario Outline: Delete an inventory item for the all inventory, warehouse inventory, and product category inventory pages
    Given I am on the "<inventoryPage>" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I focus and activate the "Delete" link on a row
    Then I focus and activate the Delete button in the popover
    Then the screen reader should not announce that the inventory item row is displayed

    Examples:
      | inventoryPage |
      | all           |
      | category 4    |
      | warehouse 1   |
