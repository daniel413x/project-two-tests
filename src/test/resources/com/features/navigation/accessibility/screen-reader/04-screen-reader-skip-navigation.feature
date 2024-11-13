@screen-reader-skip-navigation
Feature: Skip navigation via keyboard

  Scenario Outline: Skip navigation link is present and functional via screen reader
    Given I am on the "<current-page>" page
    And a screen reader is enabled
    When I focus on the skip navigation link 
    And the screen reader announces the "Skip to main content" link
    Then I activate the skip navigation link
    Then the screen reader should announce the main content of the page
    
    Examples:
      | current-page |
      | Dashboard    |
      | Warehouses   |
      | Products     |
      | Inventory    |
