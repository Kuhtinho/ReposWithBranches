# ReposWithBranches

## Overview

**ReposWithBranches** is a Java project designed to retrieve repositories and their branches for particular user.

## Features

- Fetch and display information about repositories for given username.
- List all branches with latest commit sha for each repository of user.

## Installation

To install and run this project locally, follow these steps:

1. **Clone the repository:**
   git clone https://github.com/Kuhtinho/ReposWithBranches.git
2. **Run the project:**
   To run the application, run the following command in a terminal window directory:
   ./gradlew bootRun

## Usage

To check functionality you need to provide either "http://localhost:8080/github-repos?userName={YourUserName}" in browser or "curl http://localhost:8080/github-repos?userName={YourUserName}" in terminal.

## Technologies and Tools

- **Java 21**: The primary programming language used for developing this project.
- **Gradle 8.8**: The build automation tool used for managing project dependencies and building the project.
- **Spring Boot 3.3**: A framework for building Java-based applications, used to create the backend service of the project.
- **JUnit**: The testing framework used for writing and running tests.
- **GitHub API**: Used to interact with GitHub repositories and fetch repository and branch information.
- **IntelliJ IDEA**: IDE for developing and maintaining the project.
