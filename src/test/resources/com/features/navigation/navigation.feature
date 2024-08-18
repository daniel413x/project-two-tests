@navigation
Feature: Navigation

  Scenario Outline: Side Navigation
    Given I am on the "<current page>" page
    When I click on "<page>" in the side navigation
    Then I should be navigated to "<page>"

    Examples:
      | current page | page       |
      | Dashboard    | Products   |
      | Products     | Inventory  |
      | Products     | Warehouses |

  Scenario Outline: Breadcrumbs
    Given I am on the "<current page>" page
    When I click on "<page>" in the breadcrumbs
    Then I should be navigated to "<page>"

    Examples:
      | current page               | page       |
      | Inventory - Climbing Shoes | Products   |
      | Inventory - CO4            | Warehouses |
