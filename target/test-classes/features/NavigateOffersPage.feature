Feature: Search for a list of products with required quantity and place order


Scenario: Search for a product, add quantity and proceed to checkout , verify details and place Order

Given User is on GreenKart Landing page
When User clicks TopDeals link
Then User searches for product "Almond" with different page size "20"