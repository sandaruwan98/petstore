# PetStore Application

## Introduction

MicroProfile Starter has generated this MicroProfile application for you.

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Packaging and running the application

If you want to build an _??ber-jar_, execute the following command:

    ./gradlew build -Dquarkus.package.type=uber-jar

To run the application:

    java -jar build/petstore-runner.jar

The application can be also packaged using simple:

    ./gradlew build

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it is not an _??ber-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

To launch the test page, open your browser at the following URL

    http://localhost:8080/index.html

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

    ./gradlew quarkusDev

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Creating a native executable

Mind having GRAALVM_HOME set to your Mandrel or GraalVM installation.

You can create a native executable using:

    ./gradlew build -Dquarkus.package.type=native

Or, if you don't have [Mandrel](https://github.com/graalvm/mandrel/releases/) or
[GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases) installed, you can run the native executable
build in a container using:

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true

Or to use Mandrel distribution:

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-mandrel:20.3-java11

You can then execute your native executable with:

    ./build/petstore-runner

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Deploying Application

To deploy the demo app on a docker-compose please visit [./deploy](https://github.com/rasika/petstore/tree/master/deploy)

First goto ./deploy directory in project folder.
Then docker-compose.yaml change petstore image to your docker image name.

After that run

    docker-compose up -d



## Testing Application

To run the test suite

    ./gradlew test
You can see test results in 'build/test-results' folder

## Test the APIs once deployed

#### To Get all pets
    curl -X GET "http://localhost:8080/v1/pets" -H  "accept: application/json"

#### To Get pet by id
    curl -X GET "http://localhost:8080/v1/pets/{petid}" -H  "accept: application/json"

#### To Search pets by age
    curl -X GET "http://localhost:8080/v1/pets/search/age/{petAge}" -H  "accept: */*"
#### To Search pet by pet name
    curl -X GET "http://localhost:8080/v1/pets/search/name/{petName}" -H  "accept: */*"
#### To Search pets by pet type name
    curl -X GET "http://localhost:8080/v1/pets/search/petType/name/{pet type name}" -H  "accept: */*"
#### To Search pets by pet type id
    curl -X GET "http://localhost:8080/v1/pets/search/petType/id/{pet type id}" -H  "accept: */*"

#### To add this example pet
    curl -X POST "http://localhost:8080/v1/pets" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"id\":1,\"petAge\":0,\"petName\":\"string\",\"petTypeId\":1}"

#### To update pet with is 2 
    curl -X PUT "http://localhost:8080/v1/pets/2" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"petAge\":0,\"petName\":\"string\",\"petTypeId\":2}"

#### To Delete pet with is 3 
    curl -X DELETE "http://localhost:8080/v1/pets/2" -H  "accept: application/json"


#### To Get all pet types
    curl -X GET "http://localhost:8080/v1/pettypes" -H  "accept: application/json"

#### To Get pet type by id
    curl -X GET "http://localhost:8080/v1/pettypes/{pet type id}" -H  "accept: application/json"

#### To add this example pet type
    curl -X POST "http://localhost:8080/v1/pettypes" -H  "accept: application/json" -H  "Content-Type: application/json"-d "{\"typeName\":\"string\"}"

#### To update pet with is 3
    curl -X PUT "http://localhost:8080/v1/pettypes" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"id\":3,\"typeName\":\"string\"}"

#### To Delete pet with is 3
    curl -X DELETE "http://localhost:8080/v1/pettypes/2" -H  "accept: application/json"