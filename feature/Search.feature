Feature: Search
  Scenario: search about sweet by id only
    Given I am on the search page
    When I select search by sweet id "01"
    And I submit the search
    Then I should see the sweet with id


  Scenario: search about non-existing sweet by id
    Given I am on the search page
    When I select search by sweet id  "non-existing"
    And I submit the search
    Then I should not see any result
    And The massage  no result appears

    Scenario: search about sweet by name only
        Given I am on the search page
        When I select search by sweet name "Cake of chocolate"
        And I submit the search
        Then I should see the sweet with name


      Scenario: search about non-existing sweet by name
        Given I am on the search page
        When I select search by sweet name  "non-existing"
        And I submit the search
        Then I should not see any result
        And The massage  no result appears


  Scenario: search about sweet by name and type
    Given I am on the search page
    When I select search by sweet name "Cake of chocolate" and sweet type "zero sugar"
    And I submit the search
    Then I should see the sweet with this name and type


  Scenario: search about non-existing sweet by name and type
    Given I am on the search page
    When I select search by sweet name "non-existing" or sweet type "non-existing"
    And I submit the search
    Then I should not see any result
    And The massage  no result appears


  Scenario:search about sweet by name and id
    Given I am on the search page
    When I select search by sweet name "Cake of chocolate" and sweet id "01"
    And I submit the search
    Then I should see the sweet with this name and id


  Scenario: search about non-existing  events by name and id
    Given I am on the search page
    When I select search by sweet name "non-existing" or  no id "non-existing"
    And I submit the search
    Then I should not see any result
    And The massage  no result appears


  Scenario:search about sweet by name ,type and id
    Given I am on the search page
    When I select search by sweet name "Cake of chocolate" ,sweet type "zero sugar" and id "01"
    And I submit the search
    Then I should see the sweet with this name , type and id


  Scenario: search about non-existing sweet by name ,type and id
    Given I am on the search page
    When I select search by sweet name "non-existing" or sweet type "non-existing" or sweet id "non-existing"
    And I submit the search
    Then I should not see any result
    And The massage  no result appears



    Scenario: search about sweet by price only
        Given I am on the search page
        When I select search by sweet price range between Min Price "200 " and Max Price "1000"
        And I submit the search
        Then I should see the sweet with price


      Scenario: search about non-existing sweet by price
        Given I am on the search page
        When I select search by sweet price range between Min Price "5 " and Max Price "15"
        And I submit the search
        Then I should not see any result
        And The massage no result appears

  Scenario: showing all sweets
    Given I am on the search page
    When I need to see all sweets
    Then I should see all sweets
