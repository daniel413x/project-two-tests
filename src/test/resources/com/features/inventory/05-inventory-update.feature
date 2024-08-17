@inventory-update
Feature: Update inventory items

  Scenario: Save inventory item update on all inventory page
    Given I am on the all inventory page
    And there is one or more existing inventory item rows
    When I select the Edit link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And be able to edit the "Brand", "Product Name", "Description", "Product Type", "Warehouse", "Price", "Size" and "Quantity" fields
    Then I should click the Save button
    And see the inventory item row updated

  Scenario: Save inventory item update on a warehouse inventory page
    Given I am on the inventory page for a specific warehouse
    And there is one or more existing inventory item rows
    When I select the Edit link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And be able to edit the "Brand", "Product Name", "Description", "Product Type", "Price", "Size" and "Quantity" fields
    Then I should click the Save button
    And see the inventory item row updated

  Scenario: Save inventory item update on a product category page
    Given I am on the inventory page for a specific product category
    And there is one or more existing inventory item rows
    When I select the Edit link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And be able to edit the "Brand", "Product Name", "Description", "Warehouse", "Price", "Size" and "Quantity" fields
    Then I should click the Save button
    And see the inventory item row updated

  Scenario: Cancel inventory item update on all inventory page
    Given I am on the all inventory page
    And there is one or more existing inventory item rows
    When I select the Edit link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And be able to edit the "Brand", "Product Name", "Description", "Product Type", "Warehouse", "Price", "Size" and "Quantity" fields
    Then I should click the Cancel button
    And see the inventory item row unchanged

  Scenario: Cancel inventory item update on a warehouse inventory page
    Given I am on the inventory page for a specific warehouse
    And there is one or more existing inventory item rows
    When I select the Edit link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And be able to edit the "Brand", "Product Name", "Description", "Product Type", "Price", "Size" and "Quantity" fields
    Then I should click the Cancel button
    And see the inventory item row unchanged

  Scenario: Cancel inventory item update on a product category page
    Given I am on the inventory page for a specific product category
    And there is one or more existing inventory item rows
    When I select the Edit link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And be able to edit the "Brand", "Product Name", "Description", "Warehouse", "Price", "Size" and "Quantity" fields
    Then I should click the Cancel button
    And see the inventory item row unchanged
