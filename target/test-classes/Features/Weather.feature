@apiTesting
Feature: MetaWeather API Testing

  @automated
  Scenario: 01-To verify the weather forcast for London for today
    Given I'm using the MetaWeather API
    When I make a GET request to "/api/location/44418/"
    Then I verify the response code of 200
    When I generate the weather forcast Dictionary
    Then I verify the expected weather "Showers" for the Day "Today"

  @automated
  Scenario Outline: 02-To verify the weather forcast for next few days for London
    Given I'm using the MetaWeather API
    When I make a GET request to "/api/location/44418/"
    Then I verify the response code of 200
    When I generate the weather forcast Dictionary
    Then I verify the expected weather "<expected_weather>" for the Day "<day>"

    Examples: 
      | day        | expected_weather |
      | Today      | Heavy Cloud      |
      | Tomorrow   | Showers          |
      | Tomorrow+1 | Clear            |
      | Tomorrow+2 | Light Cloud      |

  @manual
  Scenario: 03-To verify the MetaWeather API's response's Content-type
    Given I'm using the MetaWeather API
    When I make a GET request to "/api/location/44418/"
    Then I verify the response code of 200
    And I verify the Content-Type MetaWeather API's response is returning.
      | expected_content-type |
      | application/json      |

  @manual
  Scenario: 04-To verify the MetaWeather API's response has the correct woeid
    Given I'm using the MetaWeather API
    When I make a GET request to "/api/location/44418/"
    Then I verify the response code of 200
    And I verify the woeid MetaWeather API's response is returning.
      | expected_woeid |
      |          44418 |

  @manual
  Scenario: 05-To verify the MetaWeather API's response has the correct City name
    Given I'm using the MetaWeather API
    When I make a GET request to "/api/location/44418/"
    Then I verify the response code of 200
    And I verify the city name MetaWeather API's response is returning.
      | expected_city |
      | London        |

  @manual
  Scenario: 06-To verify the number of days of forecate MetaWeather API's response is returning
    Given I'm using the MetaWeather API
    When I make a GET request to "/api/location/44418/"
    Then I verify the response code of 200
    And I verify the  number of days of forecast MetaWeather API's is returning.
      | expected_no_of_days |
      |                   6 |

  @manual
  Scenario: 07-To verify the Time zone in MetaWeather API's response is returning
    Given I'm using the MetaWeather API
    When I make a GET request to "/api/location/44418/"
    Then I verify the response code of 200
    And I verify the Time zone MetaWeather API's response is returning.
      | expected_timezone |
      | Europe/London     |

  @manual
  Scenario: 08-To verify the MetaWeather API's response has correct location-type
    Given I'm using the MetaWeather API
    When I make a GET request to "/api/location/44418/"
    Then I verify the response code of 200
    And I verify the  location-type MetaWeather API's response is returning.
      | location-type |
      | city          |

  Scenario: 09-To verify the functionality when user send wrong woeid in MetaWeather API's request
    Given I'm using the MetaWeather API
    When I make a GET request to "/api/location/ab1234/"
    Then I verify the response code in MetaWeather API's response is returning.
      | expected_code |
      |           404 |

  @manual
  Scenario: 10-To verify weather_state_abbr for each weather_state_name in MetaWeather API's response
    Given I'm using the MetaWeather API
    When I make a GET request to "/api/location/44418/"
    Then I verify the response code of 200
    And I verify weather_state_abbr for each weather_state_name in MetaWeather API's is returning.
      | weather_state_name | weather_state_abbr |
      | Heavy Rain         | hr                 |
      | Light Rain         | lr                 |
      | Heavy Cloud        | hc                 |
      | Clear              | c                  |
      | Shower             | s                  |
