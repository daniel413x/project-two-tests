@screen-reader-navigation
Feature: Navigation via screen reader

  Scenario Outline: Side Navigation via screen reader
    Given I am on the "<current page>" page
    And a screen reader is enabled
    When I focus and activate "<page>" in the side navigation
    Then the screen reader should announce the page title as "<page> - Crag Supply Co."

    Examples:
      | current page | page      |
      | Dashboard    | Products  |
      | Products     | Inventory |
      | Warehouses   | Inventory |

  Scenario Outline: Breadcrumbs via screen reader
    Given I am on the "<current page>" page
    And a screen reader is enabled
    When I focus and activate "<page>" in the breadcrumbs
    Then the screen reader should announce the page title as "<page> - Crag Supply Co."

    Examples:
      | current page               | page       |
      | Inventory - Climbing Shoes | Products   |
      | Inventory - CA1            | Warehouses |
