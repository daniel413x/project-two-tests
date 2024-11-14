@screen-reader-product-categories-delete
Feature: Delete product category via screen reader

  Scenario: Delete product category via screen reader
    Given I am on the product categories page
    And a screen reader is enabled
    When the product categories load
    And I focus and activate the "delete" icon on a category card 
    Then the screen reader should announce that the category was deleted