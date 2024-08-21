@inventory-sort
Feature: Sort inventory items

  Scenario Outline: Sort inventory items on all inventory page
    Given I am on the "<inventoryPage>" inventory page
    When the inventory has loaded
    And I select the "sort" icon on the "<column>" column
    Then "<column>" rows should be in "<order>" order

    Examples:
      | inventoryPage | column      | order |
      | all           | Brand       | desc  |
      | category 4    | Description | asc   |
      | warehouse 1   | Price       | asc   |
