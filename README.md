# Java-automation-testing
---

This project is an automated functional testing for **Wirecard** system


## Pre-requisites:

- NodeJS
- Java 8 JDK
- Maven
- Rest-Assured
- Chrome browser (latest version)
- Firefox browser (latest version)


## Installation:

It is required to setup **ChromeDriver** and **GeckoDriver** on your system. Follows below:

- **Chrome**: To run the tests locally with _Chrome_, install ChromeDriver from [here](http://chromedriver.chromium.org/)


- **Firefox**: To run the tests locally with _Firefox_, install GeckoDriver from [here](https://github.com/mozilla/geckodriver/releases)


## Technologies ##

- **TestNG**: Allows to use data provider to manage all test data in each test scenario as well as this tool provides listeners to interact in test execution


- **Allure**: Generate a readable report with screenshot for those failure scenarios


## Supported environment ##

The browser value is passed as a parameter in test suite xml.

- **CHROME**: `<parameter name="browser" value="chrome" />`



- **FIREFOX**: `<parameter name="browser" value="firefox" />`


## Annotations ##

There are 2 annotations for API execution, such as:

- **@CreatePayment:** Should be created a new _Customer_, _Order_ and _Payment_


- **@CreateOrder:** Should be created a new _Customer_ and _Order_


_Note: These annotations must be used for each test method for E2E testing_


## Running tests ##

- Run *E2E* test suite

```console
$ mvn clean test -DsuiteXmlFile=suite-tests/E2ETestSuite.xml
```

- Run *API* test suite

```console
$ mvn clean test -DsuiteXmlFile=suite-tests/APITestSuite.xml
```

- Run *ALL* suites

```console
$ mvn clean test -DsuiteXmlFile=suite-tests/AllSuites.xml
```

## Generate Allure Report:

After test execution using maven, run the command:

```console
$ allure serve target/allure-results/
```

## References:

[Allure Documentation](https://docs.qameta.io/allure/)
