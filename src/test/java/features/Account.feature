Feature: Application Login

@SmokeTest
Scenario: Home page default login
Given User is on NetBanking landing page
When User login into application with username "abc" and password "agbj"
Then Home page is populated
And Cards displayed are "true"



