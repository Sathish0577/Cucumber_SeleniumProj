Feature: Search and Place the order for Products

Scenario: Search Experience for product search in both Main page and Offers page

Given User is on GreenKart Landing page
When User searched with shortname "Tom" and extracted actual name of product
Then User searched for same shortname "Tom" in offers page and check if product exist with same name
Then Validate product name in offers page with landing page