-- Banking Database Schema
-- This file contains the database structure needed for the stored procedures

-- Create database
CREATE DATABASE IF NOT EXISTS BankingDB;
USE BankingDB;

-- Drop tables if they exist (for clean setup)
DROP TABLE IF EXISTS Transactions;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Employees;
DROP TABLE IF EXISTS Departments;

-- Create Departments table
CREATE TABLE Departments (
    DepartmentID INT PRIMARY KEY AUTO_INCREMENT,
    DepartmentName VARCHAR(50) NOT NULL,
    Location VARCHAR(100)
);

-- Create Employees table
CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE,
    DepartmentID INT,
    Salary DECIMAL(10, 2) NOT NULL,
    HireDate DATE,
    FOREIGN KEY (DepartmentID) REFERENCES Departments(DepartmentID)
);

-- Create Customers table
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE,
    Phone VARCHAR(20),
    Address TEXT,
    DateOfBirth DATE,
    CreatedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Accounts table
CREATE TABLE Accounts (
    AccountID INT PRIMARY KEY AUTO_INCREMENT,
    CustomerID INT NOT NULL,
    AccountNumber VARCHAR(20) UNIQUE NOT NULL,
    AccountType ENUM('Savings', 'Checking', 'Credit') NOT NULL,
    Balance DECIMAL(12, 2) DEFAULT 0.00,
    InterestRate DECIMAL(5, 4) DEFAULT 0.0100, -- 1% default for savings
    Status ENUM('Active', 'Inactive', 'Suspended') DEFAULT 'Active',
    CreatedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LastUpdated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- Create Transactions table
CREATE TABLE Transactions (
    TransactionID INT PRIMARY KEY AUTO_INCREMENT,
    FromAccountID INT,
    ToAccountID INT,
    Amount DECIMAL(12, 2) NOT NULL,
    TransactionType ENUM('Deposit', 'Withdrawal', 'Transfer', 'Interest') NOT NULL,
    Description TEXT,
    TransactionDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (FromAccountID) REFERENCES Accounts(AccountID),
    FOREIGN KEY (ToAccountID) REFERENCES Accounts(AccountID)
);

-- Insert sample data
INSERT INTO Departments (DepartmentName, Location) VALUES
('IT', 'New York'),
('Finance', 'Chicago'),
('Customer Service', 'Los Angeles'),
('Operations', 'Houston');

INSERT INTO Employees (FirstName, LastName, Email, DepartmentID, Salary, HireDate) VALUES
('John', 'Smith', 'john.smith@bank.com', 1, 75000.00, '2020-01-15'),
('Sarah', 'Johnson', 'sarah.johnson@bank.com', 2, 65000.00, '2019-03-20'),
('Michael', 'Brown', 'michael.brown@bank.com', 1, 80000.00, '2021-06-10'),
('Emily', 'Davis', 'emily.davis@bank.com', 3, 55000.00, '2020-11-05'),
('David', 'Wilson', 'david.wilson@bank.com', 2, 70000.00, '2018-09-12');

INSERT INTO Customers (FirstName, LastName, Email, Phone, Address, DateOfBirth) VALUES
('Alice', 'Johnson', 'alice.johnson@email.com', '555-0101', '123 Main St, New York, NY', '1985-03-15'),
('Bob', 'Williams', 'bob.williams@email.com', '555-0102', '456 Oak Ave, Chicago, IL', '1990-07-22'),
('Carol', 'Brown', 'carol.brown@email.com', '555-0103', '789 Pine Rd, Los Angeles, CA', '1988-11-08'),
('David', 'Miller', 'david.miller@email.com', '555-0104', '321 Elm St, Houston, TX', '1992-04-30');

INSERT INTO Accounts (CustomerID, AccountNumber, AccountType, Balance, InterestRate) VALUES
(1, 'SAV001', 'Savings', 5000.00, 0.0100),
(1, 'CHK001', 'Checking', 2500.00, 0.0000),
(2, 'SAV002', 'Savings', 8000.00, 0.0100),
(2, 'CHK002', 'Checking', 1500.00, 0.0000),
(3, 'SAV003', 'Savings', 12000.00, 0.0100),
(4, 'SAV004', 'Savings', 3000.00, 0.0100),
(4, 'CHK003', 'Checking', 4000.00, 0.0000);

-- Display the created data
SELECT 'Departments' as TableName, COUNT(*) as RecordCount FROM Departments
UNION ALL
SELECT 'Employees', COUNT(*) FROM Employees
UNION ALL
SELECT 'Customers', COUNT(*) FROM Customers
UNION ALL
SELECT 'Accounts', COUNT(*) FROM Accounts; 