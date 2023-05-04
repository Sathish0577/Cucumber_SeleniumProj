Feature: Search for a list of products with required quantity and place order


Scenario: Search for a product, add quantity and proceed to checkout , verify details and place Order

Given User is on GreenKart Landing page
When User adds multiple products and required quantity with following details
|Mango|1|
|Cucumber|1|
|Walnuts |4|
Then User clicks on Cart icon and click proceed to CheckOut
Then Validate products exists in CheckOut page and matches with given products and quantity
|Mango|1|
|Cucumber|1|
|Walnuts |4|
And  Validate total price matches with total after discount
Then User places clicks placeorder 
And  User select one country "India" and submit