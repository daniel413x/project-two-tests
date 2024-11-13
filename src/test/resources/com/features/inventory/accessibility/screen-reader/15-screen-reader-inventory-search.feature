@screen-reader-inventory-search
Feature: Search inventory items

  Scenario Outline: Search inventory items on all inventory page
    Given I am on the "all" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I focus and activate the "search" icon on the "<column>" column
    Then the screen reader should announce that the search modal is displayed
    Then I should input "<value>" in the search field
    And I focus and activate the "Search" button
    Then the screen reader should announce rows where the "<column>" column contains "<value>"

    Examples:
      | column      | value     |
      | Brand       | Peak      |
      | Name        | Ascend    |
      | Description | ergonomic |

  Scenario Outline: Reset search of inventory items on all inventory page
    Given I am on the "all" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I have searched "<column>" for "<value>"
    Then I focus and activate the "search" icon on the "<column>" column
    Then the screen reader should announce that the search modal is displayed
    And I focus and activate the "Reset" button
    Then the screen reader should announce all rows where the "<column>" column contains any value

    Examples:
      | column      | value     |
      | Brand       | Peak      |
      | Name        | Ascend    |
      | Description | ergonomic |

  Scenario Outline: Search inventory items on a warehouse inventory page
    Given I am on the "<inventorybyWarehousePage>" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I focus and activate the "search" icon on the "<column>" column
    Then the screen reader should announce that the search modal is displayed
    Then I should input "<value>" in the search field
    And I focus and activate the "Search" button
    Then the screen reader should announce rows where the "<column>" column contains "<value>"

    Examples:
      | inventorybyWarehousePage | column      | value              |
      | warehouse 1              | Brand       | Peak               |
      | warehouse 1              | Name        | Eagle Eye Climbing |
      | warehouse 1              | Description |          60 meters |

  Scenario Outline: Reset search of inventory items on a warehouse inventory page
    Given I am on the "<inventorybyWarehousePage>" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I have searched "<column>" for "<value>"
    Then I focus and activate the "search" icon on the "<column>" column
    Then the screen reader should announce that the search modal is displayed
    And I focus and activate the "Reset" button
    Then the screen reader should announce all rows where the "<column>" column contains any value

    Examples:
      | inventorybyWarehousePage | column      | value              |
      | warehouse 1              | Brand       | Peak               |
      | warehouse 1              | Name        | Eagle Eye Climbing |
      | warehouse 1              | Description |          60 meters |

  Scenario Outline: Search inventory items on a product category page
    Given I am on the "<inventorybyProductCategoryPage>" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I focus and activate the "search" icon on the "<column>" column
    Then the screen reader should announce that the search modal is displayed
    Then I should input "<value>" in the search field
    And I focus and activate the "Search" button
    Then the screen reader should announce rows where the "<column>" column contains "<value>"

    Examples:
      | inventorybyProductCategoryPage | column      | value            |
      | category 1                     | Brand       | SummitKing       |
      | category 1                     | Name        | Peak Performance |
      | category 1                     | Description | grip             |

  Scenario Outline: Reset search of inventory items on a product category page
    Given I am on the "<inventorybyProductCategoryPage>" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I have searched "<column>" for "<value>"
    Then I focus and activate the "search" icon on the "<column>" column
    Then the screen reader should announce that the search modal is displayed
    And I focus and activate the "Reset" button
    Then the screen reader should announce all rows where the "<column>" column contains any value

    Examples:
      | inventorybyProductCategoryPage | column      | value            |
      | category 1                     | Brand       | SummitKing       |
      | category 1                     | Name        | Peak Performance |
      | category 1                     | Description | grip             |
