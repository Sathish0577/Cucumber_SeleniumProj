Feature: Search for a product and proceed to CheckOut


Scenario Outline: Search for a product, add quantity and proceed to checkout , verify details

Given User is on GreenKart Landing page
When User searched with shortname <prod> and extracted actual name of product
Then User add <Qty> quantity of the product and click on AddToCart
Then User clicks on Cart icon and click proceed to CheckOut
Then User is on CheckOut page
Then Validate product exist in CheckOut page and matches in previous page
And  Validate buttons Apply and Place Order are enabled

Examples:
|prod |Qty|
|Tomato|2|
|Mango|3|
|Cucum|2|