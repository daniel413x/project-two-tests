@inventory-sort
Feature: Sort inventory items

  Scenario Outline: Sort inventory items on all inventory page
    Given I am on the all inventory page
    When I select the sort icon on the "<column>" column
    Then "<column>" rows should be in "<order>" order

    Examples:
      | column      | order |
      | Brand       | desc  |
      | Name        | asc   |
      | Description | asc   |
      | Price       | asc   |

  Scenario Outline: Sort inventory items on a warehouse inventory page
    Given I am on the inventory page for a specific warehouse
    When I select the sort icon on the "<column>" column
    Then "<column>" rows should be in "<order>" order

    Examples:
      | column      | order |
      | Brand       | desc  |
      | Name        | asc   |
      | Description | asc   |
      | Price       | asc   |

  Scenario Outline: Sort inventory items on a product category page
    Given I am on the inventory page for a specific product category
    When I select the sort icon on the "<column>" column
    Then "<column>" rows should be in "<order>" order

    Examples:
      | column      | order |
      | Brand       | desc  |
      | Name        | asc   |
      | Description | asc   |
      | Price       | asc   |
