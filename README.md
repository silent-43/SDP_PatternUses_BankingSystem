# Banking System by SK

## Description
The Banking System is a Java Swing application that simulates basic banking operations. It provides a user-friendly interface for managing accounts, transactions, and other essential banking functions.

## Features

### Core Banking Operations
- **Secure Login**: User authentication system to access banking operations
- **Account Creation**: Create three types of accounts (Savings, Current, Student)
- **Deposit Money**: Add funds to any account
- **Withdraw Money**: Withdraw funds with account-type specific rules and limits
- **Account Management**: View account details and display all accounts

### Account Types

#### 1. **Savings Account**
- 5% interest rate on balance
- Minimum balance requirement: Rs. 2000
- Maximum withdrawal limit per transaction
- Suitable for personal savings

#### 2. **Current Account**
- For business entities with trade license number
- Minimum balance requirement: Rs. 5000
- No withdrawal limits
- Higher security for business transactions

#### 3. **Student Account**
- Special account for students
- Inherits interest benefits from Savings Account (5% rate)
- Very low minimum balance: Rs. 100
- Maximum withdrawal limit of Rs. 20000
- Requires institution name

### Security & Validation
- Account-type specific minimum balance enforcement
- Withdrawal limit validation per account type
- Unique account number generation for each account
- File-based data persistence

### User Interface
- User-friendly Java Swing GUI
- Clean dashboard with all operations readily accessible
- Real-time account information display
- Input validation and error handling

## Design Patterns Used

### 1. **Factory Pattern**

- **Purpose**: Creates different types of bank accounts (SavingsAccount, CurrentAccount, StudentAccount) without specifying their concrete classes
- **Benefits**: Encapsulates object creation logic and allows easy addition of new account types

### 2. **Strategy Pattern**
- **Purpose**: Defines different withdrawal strategies for different account types
- **Benefits**: Allows runtime selection of withdrawal behavior based on account type

### 3. **Data Access Object (DAO) Pattern**
- **Purpose**: Abstracts database/file operations from business logic
- **Benefits**: Centralizes data access logic and makes it easy to switch storage implementations

### 4. **Model-View-Controller (MVC) Pattern**

- **Purpose**: Separates concerns into data, presentation, and business logic
- **Benefits**: Improves code maintainability and testability

## Technologies Used
- Java
- Java Swing for GUI
