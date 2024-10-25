@keyboard-inventory-search
Feature: Search inventory items via keyboard

  Scenario Outline: Search inventory items on all inventory page via keyboard
    Given I am on the "all" inventory page
    When the inventory has loaded
    And I tab and select the "search" icon on the "<column>" column
    Then I should see a search modal displayed
    Then I should tab and enter "<value>" in the search field
    And tab and select the "Search" button
    Then I should see rows displayed where the "<column>" column has a value that contains "<value>"

    Examples:
      | column      | value     |
      | Brand       | Peak      |
      | Name        | Ascend    |
      | Description | ergonomic |

  Scenario Outline: Reset search of inventory items on all inventory page via keyboard
    Given I am on the "all" inventory page
    When the inventory has loaded
    And I have searched "<column>" for "<value>"
    Then I tab and select the "search" icon on the "<column>" column
    Then I should see a search modal displayed
    And tab and select the "Reset" button
    Then I should see all rows displayed where the column contains any value

    Examples:
      | column      | value     |
      | Brand       | Peak      |
      | Name        | Ascend    |
      | Description | ergonomic |

  Scenario Outline: Search inventory items on a warehouse inventory page via keyboard
    Given I am on the "<inventorybyWarehousePage>" inventory page
    When the inventory has loaded
    And I tab and select the "search" icon on the "<column>" column
    Then I should see a search modal displayed
    Then I should tab and enter "<value>" in the search field
    And tab and select the "Search" button
    Then I should see rows displayed where the "<column>" column has a value that contains "<value>"

    Examples:
      | inventorybyWarehousePage | column      | value              |
      | warehouse 1              | Brand       | Peak               |
      | warehouse 1              | Name        | Eagle Eye Climbing |
      | warehouse 1              | Description |          60 meters |

  Scenario Outline: Reset search of inventory items on a warehouse inventory page via keyboard
    Given I am on the "<inventorybyWarehousePage>" inventory page
    When the inventory has loaded
    And I have searched "<column>" for "<value>"
    Then I tab and select the "search" icon on the "<column>" column
    Then I should see a search modal displayed
    And tab and select the "Reset" button
    Then I should see all rows displayed where the column contains any value

    Examples:
      | inventorybyWarehousePage | column      | value              |
      | warehouse 1              | Brand       | Peak               |
      | warehouse 1              | Name        | Eagle Eye Climbing |
      | warehouse 1              | Description |          60 meters |

  Scenario Outline: Search inventory items on a product category page via keyboard
    Given I am on the "<inventorybyProductCategoryPage>" inventory page
    When the inventory has loaded
    And I tab and select the "search" icon on the "<column>" column
    Then I should see a search modal displayed
    Then I should tab and enter "<value>" in the search field
    And tab and select the "Search" button
    Then I should see rows displayed where the "<column>" column has a value that contains "<value>"

    Examples:
      | inventorybyProductCategoryPage | column      | value            |
      | category 1                     | Brand       | SummitKing       |
      | category 1                     | Name        | Peak Performance |
      | category 1                     | Description | grip             |

  Scenario Outline: Reset search of inventory items on a product category page via keyboard
    Given I am on the "<inventorybyProductCategoryPage>" inventory page
    When the inventory has loaded
    And I have searched "<column>" for "<value>"
    Then I tab and select the "search" icon on the "<column>" column
    Then I should see a search modal displayed
    And tab and select the "Reset" button
    Then I should see all rows displayed where the column contains any value

    Examples:
      | inventorybyProductCategoryPage | column      | value            |
      | category 1                     | Brand       | SummitKing       |
      | category 1                     | Name        | Peak Performance |
      | category 1                     | Description | grip             |
