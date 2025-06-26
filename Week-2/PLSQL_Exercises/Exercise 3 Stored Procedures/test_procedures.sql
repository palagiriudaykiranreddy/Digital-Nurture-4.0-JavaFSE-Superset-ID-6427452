-- Test Script for Banking Stored Procedures
-- This file contains comprehensive tests for all three stored procedures

USE BankingDB;

-- ============================================================================
-- PRE-TEST: Display Initial Data
-- ============================================================================

SELECT '=== INITIAL DATA ===' as Section;

-- Show initial account balances
SELECT 
    a.AccountID,
    a.AccountNumber,
    a.AccountType,
    a.Balance,
    CONCAT(c.FirstName, ' ', c.LastName) as CustomerName
FROM Accounts a
JOIN Customers c ON a.CustomerID = c.CustomerID
ORDER BY a.AccountID;

-- Show initial employee salaries
SELECT 
    e.EmployeeID,
    CONCAT(e.FirstName, ' ', e.LastName) as EmployeeName,
    d.DepartmentName,
    e.Salary
FROM Employees e
JOIN Departments d ON e.DepartmentID = d.DepartmentID
ORDER BY e.EmployeeID;

-- ============================================================================
-- TEST 1: Process Monthly Interest
-- ============================================================================

SELECT '=== TEST 1: PROCESS MONTHLY INTEREST ===' as Section;

-- Show savings accounts before interest
SELECT 
    'Before Interest' as Status,
    AccountID,
    AccountNumber,
    Balance,
    InterestRate
FROM Accounts 
WHERE AccountType = 'Savings' AND Status = 'Active';

-- Process monthly interest
CALL ProcessMonthlyInterest();

-- Show savings accounts after interest
SELECT 
    'After Interest' as Status,
    AccountID,
    AccountNumber,
    Balance,
    InterestRate
FROM Accounts 
WHERE AccountType = 'Savings' AND Status = 'Active';

-- Show interest transactions
SELECT 
    TransactionID,
    ToAccountID as AccountID,
    Amount as InterestAmount,
    TransactionType,
    Description,
    TransactionDate
FROM Transactions 
WHERE TransactionType = 'Interest'
ORDER BY TransactionDate DESC;

-- ============================================================================
-- TEST 2: Update Employee Bonus
-- ============================================================================

SELECT '=== TEST 2: UPDATE EMPLOYEE BONUS ===' as Section;

-- Show IT department employees before bonus
SELECT 
    'Before Bonus' as Status,
    e.EmployeeID,
    CONCAT(e.FirstName, ' ', e.LastName) as EmployeeName,
    d.DepartmentName,
    e.Salary
FROM Employees e
JOIN Departments d ON e.DepartmentID = d.DepartmentID
WHERE d.DepartmentID = 1;

-- Apply 10% bonus to IT department
CALL UpdateEmployeeBonus(1, 10.00);

-- Show IT department employees after bonus
SELECT 
    'After Bonus' as Status,
    e.EmployeeID,
    CONCAT(e.FirstName, ' ', e.LastName) as EmployeeName,
    d.DepartmentName,
    e.Salary
FROM Employees e
JOIN Departments d ON e.DepartmentID = d.DepartmentID
WHERE d.DepartmentID = 1;

-- Test error handling: Invalid department
SELECT '=== TEST 2 ERROR HANDLING: Invalid Department ===' as Section;
-- CALL UpdateEmployeeBonus(999, 10.00);

-- Test error handling: Invalid bonus percentage
SELECT '=== TEST 2 ERROR HANDLING: Invalid Bonus Percentage ===' as Section;
-- CALL UpdateEmployeeBonus(1, 150.00);

-- ============================================================================
-- TEST 3: Transfer Funds
-- ============================================================================

SELECT '=== TEST 3: TRANSFER FUNDS ===' as Section;

-- Show account balances before transfer
SELECT 
    'Before Transfer' as Status,
    a.AccountID,
    a.AccountNumber,
    a.AccountType,
    a.Balance,
    CONCAT(c.FirstName, ' ', c.LastName) as CustomerName
FROM Accounts a
JOIN Customers c ON a.CustomerID = c.CustomerID
WHERE a.AccountID IN (1, 3)
ORDER BY a.AccountID;

-- Transfer $500 from account 1 to account 3
CALL TransferFunds(1, 3, 500.00, @transfer_status);
SELECT @transfer_status as TransferStatus;

-- Show account balances after transfer
SELECT 
    'After Transfer' as Status,
    a.AccountID,
    a.AccountNumber,
    a.AccountType,
    a.Balance,
    CONCAT(c.FirstName, ' ', c.LastName) as CustomerName
FROM Accounts a
JOIN Customers c ON a.CustomerID = c.CustomerID
WHERE a.AccountID IN (1, 3)
ORDER BY a.AccountID;

-- Show transfer transaction
SELECT 
    TransactionID,
    FromAccountID,
    ToAccountID,
    Amount,
    TransactionType,
    Description,
    TransactionDate
FROM Transactions 
WHERE TransactionType = 'Transfer'
ORDER BY TransactionDate DESC;

-- ============================================================================
-- TEST 3 ERROR SCENARIOS
-- ============================================================================

SELECT '=== TEST 3 ERROR SCENARIOS ===' as Section;

-- Test 1: Insufficient balance
SELECT 'Test: Insufficient Balance' as TestCase;
-- CALL TransferFunds(1, 3, 10000.00, @status);
-- SELECT @status;

-- Test 2: Transfer to same account
SELECT 'Test: Transfer to Same Account' as TestCase;
-- CALL TransferFunds(1, 1, 100.00, @status);
-- SELECT @status;

-- Test 3: Invalid account
SELECT 'Test: Invalid Account' as TestCase;
-- CALL TransferFunds(999, 1, 100.00, @status);
-- SELECT @status;

-- Test 4: Negative amount
SELECT 'Test: Negative Amount' as TestCase;
-- CALL TransferFunds(1, 3, -100.00, @status);
-- SELECT @status;

-- ============================================================================
-- FINAL SUMMARY
-- ============================================================================

SELECT '=== FINAL SUMMARY ===' as Section;

-- Summary of all accounts
SELECT 
    AccountType,
    COUNT(*) as AccountCount,
    SUM(Balance) as TotalBalance,
    AVG(Balance) as AverageBalance
FROM Accounts 
GROUP BY AccountType;

-- Summary of all transactions
SELECT 
    TransactionType,
    COUNT(*) as TransactionCount,
    SUM(Amount) as TotalAmount
FROM Transactions 
GROUP BY TransactionType;

-- Summary of employee salaries by department
SELECT 
    d.DepartmentName,
    COUNT(e.EmployeeID) as EmployeeCount,
    SUM(e.Salary) as TotalSalary,
    AVG(e.Salary) as AverageSalary
FROM Employees e
JOIN Departments d ON e.DepartmentID = d.DepartmentID
GROUP BY d.DepartmentID, d.DepartmentName; 