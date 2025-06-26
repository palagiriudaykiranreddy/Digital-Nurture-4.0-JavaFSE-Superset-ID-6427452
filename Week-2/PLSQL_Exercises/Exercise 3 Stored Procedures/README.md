# Banking Database Stored Procedures Project

This project implements three essential stored procedures for a banking system, covering monthly interest processing, employee bonus management, and fund transfers between accounts.

## 📋 Project Overview

The project consists of three main scenarios:

1. **ProcessMonthlyInterest**: Calculates and applies 1% monthly interest to all savings accounts
2. **UpdateEmployeeBonus**: Updates employee salaries with performance-based bonuses by department
3. **TransferFunds**: Safely transfers funds between accounts with comprehensive validation

## 🗂️ File Structure

```
temporary/
├── database_schema.sql      # Database schema and sample data
├── stored_procedures.sql    # Three main stored procedures
├── test_procedures.sql      # Comprehensive test scenarios
└── README.md               # This file
```

## 🚀 Step-by-Step Setup Instructions

### Prerequisites
- MySQL Server (version 5.7 or higher)
- MySQL Command Line Client or MySQL Workbench
- Basic knowledge of SQL

### Step 1: Install MySQL (if not already installed)

**For Windows:**
1. Download MySQL Installer from [mysql.com](https://dev.mysql.com/downloads/installer/)
2. Run the installer and follow the setup wizard
3. Choose "Developer Default" or "Server only" installation
4. Set root password when prompted

**For macOS:**
```bash
brew install mysql
brew services start mysql
```

**For Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install mysql-server
sudo mysql_secure_installation
```

### Step 2: Connect to MySQL

Open Command Prompt/Terminal and connect to MySQL:
```bash
mysql -u root -p
```
Enter your password when prompted.

### Step 3: Create and Setup Database

1. **Navigate to your project directory:**
   ```bash
   cd /path/to/your/temporary/folder
   ```

2. **Run the database schema:**
   ```sql
   source database_schema.sql;
   ```

3. **Create the stored procedures:**
   ```sql
   source stored_procedures.sql;
   ```

### Step 4: Test the Procedures

Run the comprehensive test suite:
```sql
source test_procedures.sql;
```

## 📊 Stored Procedures Details

### 1. ProcessMonthlyInterest()

**Purpose:** Processes monthly interest for all savings accounts

**Features:**
- Applies 1% monthly interest to all active savings accounts
- Uses cursors to process accounts individually
- Records interest transactions in the Transactions table
- Provides summary of processed accounts and total interest paid

**Usage:**
```sql
CALL ProcessMonthlyInterest();
```

### 2. UpdateEmployeeBonus(IN p_department_id INT, IN p_bonus_percentage DECIMAL(5, 2))

**Purpose:** Updates employee salaries with performance bonuses

**Parameters:**
- `p_department_id`: Department ID to apply bonus to
- `p_bonus_percentage`: Bonus percentage (0-100)

**Features:**
- Validates input parameters
- Checks department existence
- Updates all employees in the specified department
- Returns summary of affected employees and total bonus paid

**Usage:**
```sql
CALL UpdateEmployeeBonus(1, 10.00);  -- 10% bonus for department 1
```

### 3. TransferFunds(IN p_from_account_id INT, IN p_to_account_id INT, IN p_amount DECIMAL(12, 2), OUT p_status VARCHAR(100))

**Purpose:** Transfers funds between accounts with comprehensive validation

**Parameters:**
- `p_from_account_id`: Source account ID
- `p_to_account_id`: Destination account ID
- `p_amount`: Transfer amount
- `p_status`: Output parameter for transfer status

**Features:**
- Validates account existence and status
- Checks sufficient balance
- Prevents transfers to same account
- Records transfer transactions
- Comprehensive error handling

**Usage:**
```sql
CALL TransferFunds(1, 3, 500.00, @status);
SELECT @status;
```

## 🧪 Testing Scenarios

The `test_procedures.sql` file includes comprehensive tests for:

### Test 1: Monthly Interest Processing
- Shows account balances before and after interest
- Displays interest transactions
- Provides processing summary

### Test 2: Employee Bonus Updates
- Tests valid bonus application
- Demonstrates error handling for invalid parameters
- Shows salary changes

### Test 3: Fund Transfers
- Tests successful transfers
- Demonstrates error scenarios:
  - Insufficient balance
  - Invalid accounts
  - Same account transfers
  - Negative amounts

## 📈 Sample Data

The database includes sample data for testing:

**Departments:** IT, Finance, Customer Service, Operations
**Employees:** 5 employees across different departments
**Customers:** 4 customers with various account types
**Accounts:** 7 accounts (savings and checking) with different balances

## 🔍 Expected Results

After running the test procedures, you should see:

1. **Interest Processing:** Savings account balances increased by 1%
2. **Bonus Updates:** IT department salaries increased by 10%
3. **Fund Transfer:** $500 transferred from account 1 to account 3
4. **Transaction Records:** New entries in the Transactions table

## 🛠️ Troubleshooting

### Common Issues:

1. **Access Denied Error:**
   ```sql
   GRANT ALL PRIVILEGES ON BankingDB.* TO 'your_username'@'localhost';
   FLUSH PRIVILEGES;
   ```

2. **Procedure Already Exists:**
   ```sql
   DROP PROCEDURE IF EXISTS ProcessMonthlyInterest;
   DROP PROCEDURE IF EXISTS UpdateEmployeeBonus;
   DROP PROCEDURE IF EXISTS TransferFunds;
   ```

3. **Database Connection Issues:**
   - Verify MySQL service is running
   - Check username and password
   - Ensure proper permissions

### Error Handling:
All procedures include comprehensive error handling with meaningful error messages for:
- Invalid parameters
- Insufficient funds
- Non-existent accounts/departments
- Account status issues

## 📚 Learning Objectives

This project demonstrates:
- **Stored Procedure Development:** Creating complex business logic in SQL
- **Transaction Management:** Ensuring data consistency
- **Error Handling:** Comprehensive validation and error reporting
- **Cursor Usage:** Processing data row by row
- **Parameter Validation:** Input sanitization and validation
- **Database Design:** Proper table relationships and constraints

## 🤝 Contributing

Feel free to extend this project by:
- Adding more validation rules
- Implementing additional banking operations
- Creating user interface components
- Adding audit logging features

## 📄 License

This project is for educational purposes. Feel free to use and modify as needed. 