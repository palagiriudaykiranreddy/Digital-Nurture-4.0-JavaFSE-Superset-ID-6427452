@echo off
echo ========================================
echo Banking Database Stored Procedures
echo ========================================
echo.

echo This script will help you set up and test the banking stored procedures.
echo.

REM Check if MySQL is installed
mysql --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: MySQL is not installed or not in PATH
    echo Please install MySQL first and add it to your system PATH
    echo.
    echo Download from: https://dev.mysql.com/downloads/installer/
    pause
    exit /b 1
)

echo MySQL is installed. Version:
mysql --version
echo.

echo ========================================
echo Step 1: Database Setup
echo ========================================
echo.

REM Prompt for MySQL credentials
set /p mysql_user="Enter MySQL username (default: root): "
if "%mysql_user%"=="" set mysql_user=root

set /p mysql_password="Enter MySQL password: "

echo.
echo Creating database and tables...
mysql -u %mysql_user% -p%mysql_password% < database_schema.sql
if %errorlevel% neq 0 (
    echo ERROR: Failed to create database schema
    echo Please check your MySQL credentials and try again
    pause
    exit /b 1
)

echo Database schema created successfully!
echo.

echo ========================================
echo Step 2: Creating Stored Procedures
echo ========================================
echo.

echo Creating stored procedures...
mysql -u %mysql_user% -p%mysql_password% BankingDB < stored_procedures.sql
if %errorlevel% neq 0 (
    echo ERROR: Failed to create stored procedures
    pause
    exit /b 1
)

echo Stored procedures created successfully!
echo.

echo ========================================
echo Step 3: Running Tests
echo ========================================
echo.

echo Running comprehensive tests...
mysql -u %mysql_user% -p%mysql_password% BankingDB < test_procedures.sql
if %errorlevel% neq 0 (
    echo ERROR: Failed to run tests
    pause
    exit /b 1
)

echo.
echo ========================================
echo Project Setup Complete!
echo ========================================
echo.
echo The banking database has been created with:
echo - 4 Departments
echo - 5 Employees  
echo - 4 Customers
echo - 7 Accounts (Savings and Checking)
echo - 3 Stored Procedures
echo.
echo You can now connect to MySQL and run:
echo   USE BankingDB;
echo   CALL ProcessMonthlyInterest();
echo   CALL UpdateEmployeeBonus(1, 10.00);
echo   CALL TransferFunds(1, 3, 500.00, @status);
echo.
echo Press any key to exit...
pause >nul 