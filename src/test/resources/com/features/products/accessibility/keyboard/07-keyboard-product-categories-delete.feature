@keyboard-product-categories-delete
Feature: Delete product category via keyboard

  Scenario: Delete product category via keyboard
    Given I am on the product categories page
    When the product categories load
    And I focus and select the "delete" icon on a category card 
    Then I should not see the category card displayed
