Feature: Search
  Scenario: search about sweet by id only
    Given I am on the search page
    When I select search by sweet id "02"
    And I submit the search
    Then I should see the sweet with id


  Scenario: search about non-existing sweet by id
    Given I am on the search page
    When I select search by sweet id does not exist "11"
    And I submit the search
    Then I should not see any result
    And The massage  no result appears

   Scenario: search about sweet by name only
        Given I am on the search page
        When I select search by sweet name "Lotus cheesecake"
        And I submit the search
        Then I should see the sweet with name


   Scenario: search about non-existing sweet by name
        Given I am on the search page
        When I select search by sweet name does not exist "Cake of chocolate"
        And I submit the search
        Then I should not see any result
        And The massage  no result appears


   Scenario: search about sweet by name and type
    Given I am on the search page
    When I select search by sweet name "Vegan carrot cake" and sweet type "Vegetarian diet"
    And I submit the search
    Then I should see the sweet with this name and type


   Scenario: search about non-existing sweet by name and type
    Given I am on the search page
    When I select search by sweet name does not exist "Cake of chocolate" or sweet type does not exist "zero sugar"
    And I submit the search
    Then I should not see any result
    And The massage  no result appears


   Scenario:search about sweet by name and id
    Given I am on the search page
    When I select search by sweet name "Lotus cheesecake" and sweet id "02"
    And I submit the search
    Then I should see the sweet with this name and id


   Scenario: search about non-existing  events by name and id
    Given I am on the search page
    When I select search by sweet name does not exist "Cake of chocolate" or  no id "01"
    And I submit the search
    Then I should not see any result
    And The massage  no result appears


   Scenario:search about sweet by name ,type and id
    Given I am on the search page
    When I select search by sweet name "Macaron" ,sweet type "zero sugar" and id "06"
    And I submit the search
    Then I should see the sweet with this name , type and id


   Scenario: search about non-existing sweet by name ,type and id
    Given I am on the search page
    When I select search by sweet name does not exist "Crepe" or sweet type does not exist "sweet" or sweet id does not exist "12"
    And I submit the search
    Then I should not see any result
    And The massage  no result appears


   Scenario: search about sweet by price only
        Given I am on the search page
        When I select search by sweet price range between Min Price "20" and Max Price "50"
        And I submit the search
        Then I should see the sweet with price



    Scenario: showing all sweets
    Given I am on the search page
    When I need to see all sweets
    Then I should see all sweets