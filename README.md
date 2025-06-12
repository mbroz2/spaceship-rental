# spaceship-rental

Learn how to add AI to your Java apps.
Each of the step-xx directories introduces a new AI app concept, and each is a complete app you can run.

All apps in this repo use Quarkus and LangChain4j.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

The following details apply to all of the step-xx directories.

## Prerequisites
The demos use Large Language Models (LLMs).  The default configuration expects [Ollama](https://ollama.com/download) to be running on your system.  Alternatively, you can update the demo's `/src/resources/application.properties` to set a different provider.

To compile and run any of the demos, Java SE JDK 21 or later is required.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/spaceship-rental-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.
