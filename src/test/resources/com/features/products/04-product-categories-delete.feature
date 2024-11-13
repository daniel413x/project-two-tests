@product-categories-delete
Feature: Delete product category

  Scenario: Delete product category
    Given I am on the product categories page
    When the product categories load
    And I click the "delete" icon on a category card 
    Then I should not see the category card displayed
