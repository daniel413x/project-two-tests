@screen-reader-inventory-sort
Feature: Sort inventory items via screen reader

# Last step not implemented
  Scenario Outline: Sort inventory items on all inventory page via screen reader
    Given I am on the "<inventoryPage>" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I focus and activate the "sort" icon on the "<column>" column
    Then the screen reader should confirm "<column> column sorted <order>"

    Examples:
      | inventoryPage | column      | order      |
      | all           | Brand       | descending |
      | category 4    | Description | ascending  |
      | warehouse 1   | Price       | ascending  |
