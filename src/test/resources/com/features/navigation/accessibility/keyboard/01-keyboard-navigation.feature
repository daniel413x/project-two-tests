@keyboard-navigation
Feature: Navigation via keyboard

  Scenario Outline: Side Navigation via keyboard
    Given I am on the "<current page>" page
    When I focus and select "<page>" in the side navigation
    Then I should be navigated to "<page>"

    Examples:
      | current page | page      |
      | Dashboard    | Products  |
      | Products     | Inventory |
      | Warehouses   | Inventory |

  Scenario Outline: Breadcrumbs via keyboard
    Given I am on the "<current page>" page
    When I focus and select "<page>" in the breadcrumbs
    Then I should be navigated to "<page>"

    Examples:
      | current page               | page       |
      | Inventory - Climbing Shoes | Products   |
      | Inventory - CA1            | Warehouses |
