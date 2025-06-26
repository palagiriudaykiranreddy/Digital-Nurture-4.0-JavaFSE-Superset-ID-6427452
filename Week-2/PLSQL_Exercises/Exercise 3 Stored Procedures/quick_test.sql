-- Quick Test Script for Banking Stored Procedures
-- Run this file to quickly test all three procedures

USE BankingDB;

-- ============================================================================
-- QUICK TEST: All Three Procedures
-- ============================================================================

-- Test 1: Process Monthly Interest
SELECT '=== TESTING MONTHLY INTEREST ===' as Test;
CALL ProcessMonthlyInterest();

-- Test 2: Update Employee Bonus (5% for Finance department)
SELECT '=== TESTING EMPLOYEE BONUS ===' as Test;
CALL UpdateEmployeeBonus(2, 5.00);

-- Test 3: Transfer Funds
SELECT '=== TESTING FUND TRANSFER ===' as Test;
CALL TransferFunds(2, 4, 200.00, @transfer_status);
SELECT @transfer_status as TransferResult;

-- ============================================================================
-- QUICK VERIFICATION
-- ============================================================================

SELECT '=== VERIFICATION RESULTS ===' as Section;

-- Check savings account balances (should show interest applied)
SELECT 
    AccountID,
    AccountNumber,
    AccountType,
    Balance,
    'Savings accounts with interest' as Note
FROM Accounts 
WHERE AccountType = 'Savings';

-- Check employee salaries (should show bonus applied to Finance dept)
SELECT 
    e.EmployeeID,
    CONCAT(e.FirstName, ' ', e.LastName) as EmployeeName,
    d.DepartmentName,
    e.Salary,
    'Employees with updated salaries' as Note
FROM Employees e
JOIN Departments d ON e.DepartmentID = d.DepartmentID
WHERE d.DepartmentID = 2;

-- Check recent transactions
SELECT 
    TransactionID,
    TransactionType,
    Amount,
    Description,
    TransactionDate
FROM Transactions 
ORDER BY TransactionDate DESC 
LIMIT 5;

SELECT '=== ALL TESTS COMPLETED ===' as Status; 