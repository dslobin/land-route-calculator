## Table of contents
- [Requirements](#requirements)
- [Application properties](#application-properties)
- [How to run](#how-to-run)

## Requirements
- Java 17
- Maven

## Application properties

#### route.algorithm
Algorithm for graph search. **breadth-first-search** set by default. <br />
:warning: Only one algorithm is implemented in the current version of the application.

#### route.resource-location
Specifies file downloading policy. There are 2 possible options: **local** and **remote**
- local - loads file from path (set by default)
- remote - loads file from remote resource

#### route.local.uri
Path to the resource file (required property if `resource-location: local` set)

#### route.remote.uri
URI to the resource file (required property if `resource-location: remote` set)

## How to run
- Build the application: `mvn clean install`
- Run the application: `mvn spring-boot:run`
- Try it out in Postman / Fiddler / Command line:
  ```
  curl --request GET \
   --url http://localhost:8080/api/v1/routing/CZE/ITA
  ```
