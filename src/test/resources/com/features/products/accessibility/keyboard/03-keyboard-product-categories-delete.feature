@keyboard-product-categories-delete
Feature: Delete product category via keyboard

  Scenario: Delete product category via keyboard
    Given I am on the product categories page
    When the product categories load
    And I tab and select the "..." icon on a category card 
    And tab and select Delete from the dropdown
    Then I should not see the category card displayed
