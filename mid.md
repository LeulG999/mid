Problem Description

Assume you have been hired by a company called CS425 Library Corporation.

They need a CLI application called Library Management System (LMS).

The system manages Books and Members.

Special Categories of Books

A book is classified as:

Standard
Published less than 5 years ago
Popular
Published 5 or more years ago
Borrowed at least 50 times
Premium
Published 10 or more years ago
Borrowed at least 100 times
Library Statistics

The Library Managers need the system to display:

Total Books Count

which is calculated by counting all books registered in the system.

Solution Model

A Member can borrow one or many Books.

A Book can be borrowed by only one Member at a time.

Entities
Member

Attributes:

memberId
fullName
email
phoneNumber
Book

Attributes:

bookId
title
author
publishedYear
timesBorrowed
memberId
Required Tasks
Task 1

Draw a Domain Model UML Class Diagram showing:

Member
Book

and their relationship.

Task 2

Define a solution architecture and draw a detailed design diagram.

Apply:

Separation of Concerns (SoC)
Layering
DDD concepts
Task 3

Using Java and your preferred tech stack:

Implement the application.

You may use in-memory storage.

Task 4

Use build automation.

Choose:

Maven
or
Gradle
Task 5

Create an executable artifact.

Example:

java -jar lms.jar
Task 6

Create README.md containing:

Runtime requirements
Java version
Build instructions
Run instructions
Task 7

Push project to GitHub.

Create a CI/CD workflow so every push triggers a build.

Task 8

Publish a Release on GitHub.

Task 9 

Containerize the application using Docker.

Requirements:

Create Dockerfile
Build Docker image
Run application inside container

