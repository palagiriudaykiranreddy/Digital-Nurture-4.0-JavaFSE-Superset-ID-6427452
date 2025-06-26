-- =====================================================
-- Exercise 1: Control Structures - Bank Management System
-- =====================================================

-- Step 1: Drop existing tables if they exist (for clean runs)
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Loans';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Customers';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

-- Step 2: Create Tables
CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Age NUMBER,
    Balance NUMBER,
    IsVIP CHAR(1) DEFAULT 'N'
);

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    InterestRate NUMBER(5,2),
    DueDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- Step 3: Insert Sample Data
INSERT INTO Customers VALUES (1, 'Alice Johnson', 65, 12000, 'N');
INSERT INTO Customers VALUES (2, 'Bob Smith', 45, 8000, 'N');
INSERT INTO Customers VALUES (3, 'Charlie Brown', 70, 9500, 'N');
INSERT INTO Customers VALUES (4, 'Diana Wilson', 30, 15000, 'N');
INSERT INTO Customers VALUES (5, 'Eva Davis', 55, 7500, 'N');
INSERT INTO Customers VALUES (6, 'Frank Miller', 68, 18000, 'N');

INSERT INTO Loans VALUES (101, 1, 5.5, SYSDATE + 10);
INSERT INTO Loans VALUES (102, 2, 6.0, SYSDATE + 40);
INSERT INTO Loans VALUES (103, 3, 7.0, SYSDATE + 20);
INSERT INTO Loans VALUES (104, 4, 4.5, SYSDATE + 5);
INSERT INTO Loans VALUES (105, 5, 6.5, SYSDATE + 15);
INSERT INTO Loans VALUES (106, 6, 5.8, SYSDATE + 25);

COMMIT;

-- Display initial data
SELECT 'Initial Customer Data:' AS Status FROM DUAL;
SELECT * FROM Customers;

SELECT 'Initial Loan Data:' AS Status FROM DUAL;
SELECT * FROM Loans;

-- =====================================================
-- Scenario 1: Apply 1% discount to loan interest rates 
-- for customers above 60 years old
-- =====================================================

SELECT 'Executing Scenario 1: Applying 1% discount for customers above 60...' AS Status FROM DUAL;

BEGIN
    FOR rec IN (
        SELECT l.LoanID, l.InterestRate, c.Age, c.Name
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
    )
    LOOP
        IF rec.Age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE LoanID = rec.LoanID;
            
            DBMS_OUTPUT.PUT_LINE('Applied 1% discount to ' || rec.Name || ' (Age: ' || rec.Age || ') - New Rate: ' || (rec.InterestRate - 1) || '%');
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- =====================================================
-- Scenario 2: Promote customers to VIP status based on balance
-- Set IsVIP to 'Y' for customers with balance over $10,000
-- =====================================================

SELECT 'Executing Scenario 2: Promoting customers to VIP based on balance...' AS Status FROM DUAL;

BEGIN
    FOR rec IN (SELECT CustomerID, Name, Balance FROM Customers)
    LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'Y'
            WHERE CustomerID = rec.CustomerID;
            
            DBMS_OUTPUT.PUT_LINE('Promoted ' || rec.Name || ' to VIP (Balance: $' || rec.Balance || ')');
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- =====================================================
-- Scenario 3: Send reminders for loans due within 30 days
-- =====================================================

SELECT 'Executing Scenario 3: Sending reminders for loans due in 30 days...' AS Status FROM DUAL;

SET SERVEROUTPUT ON;

BEGIN
    FOR rec IN (
        SELECT l.LoanID, l.DueDate, c.Name, c.CustomerID
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.DueDate BETWEEN SYSDATE AND SYSDATE + 30
        ORDER BY l.DueDate
    )
    LOOP
        DBMS_OUTPUT.PUT_LINE('REMINDER: Loan #' || rec.LoanID || ' for customer ' || rec.Name || ' (ID: ' || rec.CustomerID || ') is due on ' || TO_CHAR(rec.DueDate, 'YYYY-MM-DD'));
    END LOOP;
END;
/

-- =====================================================
-- Final Results Display
-- =====================================================

SELECT 'Final Customer Data After All Updates:' AS Status FROM DUAL;
SELECT CustomerID, Name, Age, Balance, IsVIP FROM Customers ORDER BY CustomerID;

SELECT 'Final Loan Data After All Updates:' AS Status FROM DUAL;
SELECT l.LoanID, l.CustomerID, c.Name, l.InterestRate, l.DueDate 
FROM Loans l 
JOIN Customers c ON l.CustomerID = c.CustomerID 
ORDER BY l.LoanID;

-- Summary Report
SELECT 'SUMMARY REPORT:' AS Report FROM DUAL;
SELECT 
    'Total Customers: ' || COUNT(*) AS Summary
FROM Customers
UNION ALL
SELECT 
    'VIP Customers: ' || COUNT(*) AS Summary
FROM Customers 
WHERE IsVIP = 'Y'
UNION ALL
SELECT 
    'Customers Above 60: ' || COUNT(*) AS Summary
FROM Customers 
WHERE Age > 60
UNION ALL
SELECT 
    'Loans Due in Next 30 Days: ' || COUNT(*) AS Summary
FROM Loans 
WHERE DueDate BETWEEN SYSDATE AND SYSDATE + 30; 