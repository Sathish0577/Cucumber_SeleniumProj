Feature: Search for a list of products with required quantity and place order

Scenario Outline: Search for a product, add quantity and proceed to checkout , verify details and place Order

Given User is on GreenKart Landing page
When User clicks TopDeals link
Then User searches for products <prod> with different page size selection <sz>

Examples:
|prod | sz |
|Tomato | 5|
|Pineapple|10|
|Chocolate|10|
|Brinjal |5|
|Almond |5|
|Apple|20|