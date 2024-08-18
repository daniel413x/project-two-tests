@inventory-delete
Feature: Inventory delete

  Scenario Outline: Delete an inventory item for the all inventory, warehouse inventory, and product category inventory pages
    Given I am on the "<inventoryPage>" inventory page
    And I have triggered the delete inventory popover
    When I click "Delete"
    Then the inventory should not appear in the inventory for "<inventoryPage>"

    Examples:
      | inventoryPage |
      | all           |
      | category 4    |
      | warehouse 1   |
