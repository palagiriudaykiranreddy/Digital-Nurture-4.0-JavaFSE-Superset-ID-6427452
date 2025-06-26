-- Stored Procedures for Banking System
-- This file contains the three required stored procedures

USE BankingDB;

-- ============================================================================
-- SCENARIO 1: Process Monthly Interest for Savings Accounts
-- ============================================================================

DELIMITER $$

CREATE PROCEDURE ProcessMonthlyInterest()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE account_id INT;
    DECLARE current_balance DECIMAL(12, 2);
    DECLARE interest_rate DECIMAL(5, 4);
    DECLARE interest_amount DECIMAL(12, 2);
    DECLARE account_cursor CURSOR FOR 
        SELECT AccountID, Balance, InterestRate 
        FROM Accounts 
        WHERE AccountType = 'Savings' AND Status = 'Active';
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    -- Start transaction
    START TRANSACTION;
    
    -- Open cursor
    OPEN account_cursor;
    
    -- Process each savings account
    read_loop: LOOP
        FETCH account_cursor INTO account_id, current_balance, interest_rate;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        -- Calculate interest (1% monthly)
        SET interest_amount = current_balance * 0.01;
        
        -- Update account balance
        UPDATE Accounts 
        SET Balance = Balance + interest_amount,
            LastUpdated = CURRENT_TIMESTAMP
        WHERE AccountID = account_id;
        
        -- Record the interest transaction
        INSERT INTO Transactions (ToAccountID, Amount, TransactionType, Description)
        VALUES (account_id, interest_amount, 'Interest', 
                CONCAT('Monthly interest applied at ', (interest_rate * 100), '%'));
        
    END LOOP;
    
    -- Close cursor
    CLOSE account_cursor;
    
    -- Commit transaction
    COMMIT;
    
    -- Return summary
    SELECT 
        'Monthly Interest Processing Complete' as Status,
        COUNT(*) as AccountsProcessed,
        SUM(Balance * 0.01) as TotalInterestPaid
    FROM Accounts 
    WHERE AccountType = 'Savings' AND Status = 'Active';
    
END$$

DELIMITER ;

-- ============================================================================
-- SCENARIO 2: Update Employee Bonus Based on Department Performance
-- ============================================================================

DELIMITER $$

CREATE PROCEDURE UpdateEmployeeBonus(
    IN p_department_id INT,
    IN p_bonus_percentage DECIMAL(5, 2)
)
BEGIN
    DECLARE affected_rows INT DEFAULT 0;
    DECLARE total_bonus DECIMAL(12, 2) DEFAULT 0;
    
    -- Validate input parameters
    IF p_bonus_percentage < 0 OR p_bonus_percentage > 100 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Bonus percentage must be between 0 and 100';
    END IF;
    
    -- Check if department exists
    IF NOT EXISTS (SELECT 1 FROM Departments WHERE DepartmentID = p_department_id) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Department does not exist';
    END IF;
    
    -- Start transaction
    START TRANSACTION;
    
    -- Update employee salaries with bonus
    UPDATE Employees 
    SET Salary = Salary + (Salary * p_bonus_percentage / 100)
    WHERE DepartmentID = p_department_id;
    
    -- Get affected rows and total bonus
    SET affected_rows = ROW_COUNT();
    
    SELECT SUM(Salary * p_bonus_percentage / 100) INTO total_bonus
    FROM Employees 
    WHERE DepartmentID = p_department_id;
    
    -- Commit transaction
    COMMIT;
    
    -- Return results
    SELECT 
        d.DepartmentName,
        affected_rows as EmployeesUpdated,
        p_bonus_percentage as BonusPercentage,
        total_bonus as TotalBonusPaid,
        'Bonus update completed successfully' as Status
    FROM Departments d
    WHERE d.DepartmentID = p_department_id;
    
END$$

DELIMITER ;

-- ============================================================================
-- SCENARIO 3: Transfer Funds Between Accounts
-- ============================================================================

DELIMITER $$

CREATE PROCEDURE TransferFunds(
    IN p_from_account_id INT,
    IN p_to_account_id INT,
    IN p_amount DECIMAL(12, 2),
    OUT p_status VARCHAR(100)
)
BEGIN
    DECLARE from_balance DECIMAL(12, 2);
    DECLARE to_account_exists INT;
    DECLARE from_account_exists INT;
    DECLARE from_account_status VARCHAR(20);
    DECLARE to_account_status VARCHAR(20);
    
    -- Initialize status
    SET p_status = 'Transfer initiated';
    
    -- Validate amount
    IF p_amount <= 0 THEN
        SET p_status = 'Error: Transfer amount must be greater than 0';
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Transfer amount must be greater than 0';
    END IF;
    
    -- Check if accounts exist
    SELECT COUNT(*) INTO from_account_exists 
    FROM Accounts WHERE AccountID = p_from_account_id;
    
    SELECT COUNT(*) INTO to_account_exists 
    FROM Accounts WHERE AccountID = p_to_account_id;
    
    IF from_account_exists = 0 THEN
        SET p_status = 'Error: Source account does not exist';
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Source account does not exist';
    END IF;
    
    IF to_account_exists = 0 THEN
        SET p_status = 'Error: Destination account does not exist';
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Destination account does not exist';
    END IF;
    
    -- Check if accounts are the same
    IF p_from_account_id = p_to_account_id THEN
        SET p_status = 'Error: Cannot transfer to the same account';
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Cannot transfer to the same account';
    END IF;
    
    -- Get account statuses
    SELECT Status INTO from_account_status 
    FROM Accounts WHERE AccountID = p_from_account_id;
    
    SELECT Status INTO to_account_status 
    FROM Accounts WHERE AccountID = p_to_account_id;
    
    -- Check account statuses
    IF from_account_status != 'Active' THEN
        SET p_status = 'Error: Source account is not active';
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Source account is not active';
    END IF;
    
    IF to_account_status != 'Active' THEN
        SET p_status = 'Error: Destination account is not active';
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Destination account is not active';
    END IF;
    
    -- Get source account balance
    SELECT Balance INTO from_balance 
    FROM Accounts WHERE AccountID = p_from_account_id;
    
    -- Check sufficient balance
    IF from_balance < p_amount THEN
        SET p_status = 'Error: Insufficient balance in source account';
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Insufficient balance in source account';
    END IF;
    
    -- Start transaction
    START TRANSACTION;
    
    -- Deduct from source account
    UPDATE Accounts 
    SET Balance = Balance - p_amount,
        LastUpdated = CURRENT_TIMESTAMP
    WHERE AccountID = p_from_account_id;
    
    -- Add to destination account
    UPDATE Accounts 
    SET Balance = Balance + p_amount,
        LastUpdated = CURRENT_TIMESTAMP
    WHERE AccountID = p_to_account_id;
    
    -- Record the transaction
    INSERT INTO Transactions (FromAccountID, ToAccountID, Amount, TransactionType, Description)
    VALUES (p_from_account_id, p_to_account_id, p_amount, 'Transfer', 
            CONCAT('Transfer from account ', p_from_account_id, ' to account ', p_to_account_id));
    
    -- Commit transaction
    COMMIT;
    
    -- Set success status
    SET p_status = 'Transfer completed successfully';
    
    -- Return transfer details
    SELECT 
        p_from_account_id as FromAccountID,
        p_to_account_id as ToAccountID,
        p_amount as TransferAmount,
        p_status as Status,
        CURRENT_TIMESTAMP as TransferDate;
    
END$$

DELIMITER ;

-- ============================================================================
-- Test Procedures
-- ============================================================================

-- Test 1: Process Monthly Interest
-- CALL ProcessMonthlyInterest();

-- Test 2: Update Employee Bonus (10% bonus for IT department)
-- CALL UpdateEmployeeBonus(1, 10.00);

-- Test 3: Transfer Funds (transfer $500 from account 1 to account 3)
-- CALL TransferFunds(1, 3, 500.00, @status);
-- SELECT @status; 