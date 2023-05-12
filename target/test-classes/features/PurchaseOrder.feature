Feature: Search for a list of products with required quantity and place order


Scenario Outline: Search for a product, add quantity and proceed to checkout , verify details and place Order

Given User is on GreenKart Landing page
When User adds one product <prod> with required quantity <qty> 
Then User clicks on Cart icon and click proceed to CheckOut
Then Validate products exists in CheckOut page and matches with given products <prod> and <qty>
And  Validate total price matches with total after discount
Then User places clicks placeorder 
And  User selects country <country> and submit

Examples:
|prod | qty|country|
|Mango|2|India|
|Walnuts|3|Austria|