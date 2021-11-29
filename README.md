# **Ticket Viewer (Zendesk Coding Challenge)** #

This project is a CLI appplication that allows you to view your Zendesk support tickets.

## Prerequisites ##

The following requirements are necessary to run this application:
    
- Java JDK 11 or above
- Gradle 7 or above
- A Zendesk support URL and your email and password. Make sure to enable password access in your Admin Center first before attempting to use the application.

## Usage ##

Navigate your terminal to the project directory and run
```
gradle run
```
to launch the applications. Follow the intsructions on the CLI and have fun viewing your Zendesk support tickets!

## Setup ##

You do not need to build the project to run. However, if you desire, navigate your terminal to the project directory and run
```
gradle build
```

IMPORTANT: Note that in order for the tests to pass, make sure to change the appropriate value in `app/src/test/java/com/zendesk/zccvho/ticketviewer/ClientTest.java` to your credentials. For security reasons I cannot put my own onto a public repository.

## Building ##

The project uses gradle for building. Standard gradle tasks for the java plugin can be found [here](https://docs.gradle.org/current/userguide/java_plugin.html). They can be invoked on the command line by running `gradlew` or `gradlew.bat` with the name of the task, for example `gradlew jar` to create the jar archive.

## Tests ##

There are in total 2 tests to keep the API requests part of the application to run smoothly. As I have said in the Setup section, please change your credentials in the appropriate files to make the tests pass.
