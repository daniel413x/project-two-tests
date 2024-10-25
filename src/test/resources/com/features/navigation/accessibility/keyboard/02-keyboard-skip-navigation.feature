@keyboard-skip-navigation
Feature: Skip navigation via keyboard

  Scenario Outline: Skip navigation link is present and functional via keyboard
    Given I am on the "<current-page>" page
    When I tab the skip navigation link into focus
    And the skip navigation link is displayed
    Then I click the skip navigation link
    Then I should be taken to the main content of the page
    
    Examples:
      | current-page |
      | Dashboard    |
      | Warehouses   |
      | Products     |
      | Inventory    |
