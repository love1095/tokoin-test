# Introduction
Using the provided data (**tickets.json** and **users.json** and **organization.json**)

Write a simple command line application to search the data and return the results
in a human readable format.

* Feel free to use libraries or roll your own code as you see fit.
* Where the data exists, values from any related entities **MUST** be included in
the results, i.e.
    * Searching **organization MUST** return its **ticket subject** and **users name**.
    * Searching **users MUST** return his/her **assignee ticket subject** and **submitted ticket subject** and his/her **organization name**.
    * Searching **tickets MUST** return its **assignee name**, **submitter name**, and **organization name**.
* The user should be able to search on any field, full value matching is fine
(e.g. "mar" won't return "mary").
* The user should also be able to search for empty values, e.g. where
description is empty.
* Search can get pretty complicated pretty easily, we just want to see that you
can code a basic search application.

## Technologies
### Prerequisite
- Spring boot(v2.1.9) and dependency (dependency management) must be installed.
- JDK1.8

## How to start project?
1. Build the project with this command line: ```mvn clean install```

## How to run application?
1. Build the project with this command line: ```java -jar tokointest-1.0-SNAPSHOT.jar```

## Test Coverage
- Use ``Mockito`` to coverage unit tests
- Tests run: 29, Failures: 0, Errors: 0, Skipped: 0


## Structure
```shell script
tokoin-test
├───src
    ├───main
    │   ├───java
    │   │   └───com.tokointest
    │   │               ├───config
    │   │               ├───handler
    │   │               ├───repository
    │   │               ├───services
    │   │               └───utils
    │   └───resources
    └───test

```
