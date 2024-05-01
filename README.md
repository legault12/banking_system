# Banking System

## Overview
The "banking_system" project is a simple Java-based application designed to simulate basic banking operations. This project serves as a practice tool for understanding and implementing object-oriented programming concepts in Java. It provides a console-based interface for users to interact with various banking functionalities.

## Features
- **Account Management**: Users can create two types of bank accounts: Savings and Checking. Each account type handles deposits and withdrawals differently.
- **Transaction History**: Each account maintains a transaction history that users can query to see past account activities.
- **Interest Calculation**: Savings accounts accrue interest, which can be applied to the account balance.
- **Loan Management**: Users can apply for, receive, and repay loans. Each loan has an associated interest rate and repayment period.
- **Multi-Account Support**: Users can manage multiple accounts and view detailed information about each account, including any associated loans.

## Educational Context
This project was developed solely for the purpose of practicing Java. It covers fundamental programming techniques in Java, including but not limited to:
- Object-Oriented Programming (OOP)
- File I/O operations for loading and saving data
- Handling exceptions and managing errors
- Implementing simple algorithms for financial calculations

## How to Run
Ensure you have Java installed on your machine. Compile the Java files and run the `Main.java` to start the application. Follow the on-screen prompts to interact with the banking system.

## Repository Structure
- **src/**: Contains all source code files for the project.
- **src/Main.java**: Entry point of the application.
- **src/Account.java**: Defines the Account class and its methods.
- **src/SavingsAccount.java**: Subclass of Account for handling savings-type accounts.
- **src/CheckingAccount.java**: Subclass of Account for handling checking-type accounts.
- **src/Loan.java**: Class for managing loan-related functionality.
- **src/Bank.java**: Central class that manages all accounts and loans.

Feel free to explore the code and use it as a reference for learning Java and object-oriented design principles.
