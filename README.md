Use this for README.md:
# Library Management System

A Java CLI application for managing books and members in a library.

## Features

- Register and manage books
- Register and manage members
- Borrow books
- Return books
- Display total books count
- Classify books as Standard, Popular, or Premium
- Export books and members as JSON

## Book Categories

A book is classified as:

- `STANDARD`: Published less than 5 years ago
- `POPULAR`: Published 5 or more years ago and borrowed at least 50 times
- `PREMIUM`: Published 10 or more years ago and borrowed at least 100 times

## Project Structure

```text
src/main/java/edu/miu
├── cli
├── dto
├── model
├── repository
├── service
└── utility

Runtime Requirements
•
Java 17 or later
•
Maven 3.8 or later
Build Instructions
mvn clean package
Run Instructions
java -jar target/mid-1.0-SNAPSHOT.jar
CLI Options
1. List books
2. List members
3. Borrow book
4. Return book
5. Show total books count
6. Export books as JSON
7. Export members as JSON
0. Exit
Testing
Run all tests with:
mvn test
CI/CD
This project uses GitHub Actions to build the application on every push and pull request.
Workflow file:
.github/workflows/build.yml
Docker
Build the Docker image:
docker build -t lms .
Run the Docker container:
docker run -it lms
Technologies Used
•
Java
•
Maven
•
Jackson
Maven Shade Plugin
•
JUnit
•
GitHub Actions
•
Docker