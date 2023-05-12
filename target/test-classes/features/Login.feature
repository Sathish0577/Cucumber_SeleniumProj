Feature: Application Login





@RegTest
Scenario: Home page default login
Given User is on NetBanking landing page
When User login into application with username "abc" and password "agbj"
Then Home page is populated
And Cards displayed are "true"


@SmokeTest
Scenario: Home page default login
Given User is on NetBanking landing page
When User login in to application with username "serd" and password "dgfgbj"
Then Home page is populated
And Cards displayed are "false"

@RegTest
Scenario Outline: Home page default login
Given User is on NetBanking landing page
When User login into application with username <usrname> and password <passwd>
Then Home page is populated
And Cards displayed are "false"

Examples:
| usrname  | passwd |
| user1     | pass1    |
| user2     | pass2    |
| user3     | pass3    |