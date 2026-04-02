# Bank Management System (Java + SQLite)

A comprehensive ATM simulation application built with Java Swing GUI and SQLite database, designed to demonstrate core banking operations in a desktop environment.

---<img width="1063" height="599" alt="Screenshot 2026-04-02 235803" src="https://github.com/user-attachments/assets/147d0c92-a2b9-4774-9839-c61b252f7e19" />


## Detailed Project Overview

https://github.com/user-attachments/assets/db5d5b62-c2d3-4fde-a8f7-564b02db8dcb

This project implements a fully functional Bank Management System that simulates ATM operations. Users can create accounts, log in, and perform various banking transactions through an intuitive graphical interface. The system uses SQLite for persistent data storage, ensuring data integrity and portability.

### Purpose
- Educational: Demonstrates Java GUI development, database integration, and secure coding practices
- Functional: Provides a working ATM simulation for testing banking workflows
- Scalable: Modular design allows easy addition of new features

### Technologies Used
- **Language**: Java (JDK 11+)
- **GUI Framework**: Swing (AWT components)
- **Database**: SQLite with JDBC driver
- **Build Tool**: Manual compilation with `javac`
- **Platform**: Cross-platform (Windows/Linux/Mac with adjustments)

---

## Key Features

### Account Management
- **Multi-step Signup**: 3-stage registration process collecting personal, additional, and account details
- **Secure Login**: Card number + PIN authentication
- **Account Types**: Savings, Current, etc.

### Banking Operations
- **Deposit**: Add funds to account with transaction logging
- **Withdrawal**: Withdraw cash with balance validation
- **Fast Cash**: Quick withdrawal options (₹100, ₹500, ₹1000, ₹2000, ₹5000, ₹10000)
- **Balance Inquiry**: Check current account balance
- **PIN Change**: Update account PIN securely
- **Mini Statement**: View recent transactions

### Security Features
- **Prepared Statements**: SQL injection prevention
- **Input Validation**: Form validation and error handling
- **Session Management**: Proper login/logout flow

### User Experience
- **Responsive GUI**: Clean, professional interface with icons
- **Error Handling**: Graceful failure with user-friendly messages
- **Fallback UI**: Works without images if resources unavailable

---

## System Architecture

### MVC Pattern
- **Model**: Database layer (`Connn.java`) handles data persistence
- **View**: Swing components in each form class
- **Controller**: Event handlers in each JFrame class

### Database Layer
- Centralized connection management
- Auto-table creation on first run
- Transaction logging for audit trail

### GUI Layer
- Modular form design (each operation = separate class)
- Shared utilities (`ImageUtil.java` for icon loading)
- Consistent styling and layout

### Flow Architecture
```
Signup Flow: Signup → Signup2 → Signup3 → Login
Main Flow: Login → main_Class → [Operations] → Logout
Operations: Deposit | Withdrawl | FastCash | BalanceEnquriy | Pin | mini
```

---

## Database Design Details

The system uses 5 core tables with proper relationships:

### Core Tables
1. **`signup`** - Personal Information
   - Stores basic user details (name, address, contact)
   - Primary key: `formno` (application number)

2. **`Signuptwo`** - Additional Details
   - Extended profile info (religion, income, occupation)
   - Linked by `formno` to signup table

3. **`signupthree`** - Account Creation
   - Final account setup (card number, PIN, services)
   - Generates login credentials

4. **`login`** - Authentication
   - Active login credentials (card + PIN)
   - Primary key: `card_number`

5. **`bank`** - Transaction Log
   - All financial transactions
   - Audit trail with timestamps

### Design Principles
- **Normalization**: Data split across related tables
- **Referential Integrity**: Foreign key relationships via formno/card_number
- **Audit Trail**: Every transaction logged
- **Security**: PINs stored as text (demo purposes; hash in production)

---

## Code Structure Explanation

### Package Organization
```
bank.management.system/
├── Connn.java          # Database connection & schema management
├── Login.java          # Authentication screen
├── Signup.java         # Personal info form
├── Signup2.java        # Additional details form
├── Signup3.java        # Account creation form
├── main_Class.java     # Main menu after login
├── Deposit.java        # Deposit transaction
├── Withdrawl.java      # Withdrawal transaction
├── FastCash.java       # Quick cash options
├── BalanceEnquriy.java # Balance check
├── Pin.java            # PIN change
├── mini.java           # Mini statement
└── ImageUtil.java      # Icon loading utility
```

### Key Classes Analysis

#### Database Layer (`Connn.java`)
- Singleton connection pattern
- Auto-creates tables on first run
- Provides prepared statement helpers
- Handles connection lifecycle

#### GUI Classes
- Each extends `JFrame` and implements `ActionListener`
- Follow consistent structure: constructor sets up UI, actionPerformed handles events
- Use `ImageUtil.loadIcon()` for safe image loading
- Database operations wrapped in try-catch

#### Utility Classes
- `ImageUtil`: Centralized icon management with null-safe loading
- Prevents code duplication and handles missing resources gracefully

### Design Patterns Used
- **Factory Pattern**: Database connections via `Connn.getConnection()`
- **Observer Pattern**: Swing event handling
- **Strategy Pattern**: Different transaction types
- **Singleton Pattern**: Database connection management

---

## User Guide

### Getting Started
1. Run the application using `run.bat`
2. First time: Create account via Signup process
3. Login with generated card number and PIN

### Signup Process
1. **Signup**: Enter personal details (name, DOB, address, etc.)
2. **Signup2**: Additional info (religion, income, occupation, PAN, Aadhar)
3. **Signup3**: Account type selection and service preferences
4. System generates card number and PIN

### Daily Operations
1. **Login**: Enter card number and PIN
2. **Main Menu**: Choose from available operations
3. **Transactions**: Follow on-screen prompts
4. **Logout**: Return to login screen

### Transaction Types
- **Deposit**: Enter amount, confirm
- **Withdraw**: Enter amount (max ₹10,000), confirm
- **Fast Cash**: Select preset amount
- **Balance**: View current balance
- **PIN Change**: Enter old PIN, set new PIN
- **Mini Statement**: View last transactions

---

## Development Notes

### Code Quality
- Uses PreparedStatement for SQL security
- Proper exception handling
- Consistent naming conventions
- Modular class design

### Limitations
- Single-user simulation (no concurrent access handling)
- PIN stored in plain text (demo only)
- No encryption for sensitive data
- Basic UI without advanced styling

### Future Enhancements
- Add user session management
- Implement transaction limits
- Add account transfer functionality
- Integrate with real banking APIs
- Add admin panel for account management
- Implement proper password hashing
- Add transaction categories and filtering

---

## 1) Project structure
- `src/bank/management/system`: Java source files
- `lib`: SQLite JDBC driver should be placed here
- `classes`: compiled `.class` files (generated at build)
- `run.bat`: full build + run script for Windows
- `bank.db`: runtime SQLite database file (auto-created)

---

## 2) Requirements
- Java JDK installed (11+, recommended 21)
- Windows (works as-is); Linux/Mac needs path adjustments in `run.bat` or manual commands
- Internet access to download JDBC JAR if not already available

---

## 3) Quick setup
1. Verify Java:
   - `java -version`
   - `javac -version`
2. Run:
   ```cmd
   cd "C:\Users\nites\Downloads\Bank-Management-System--master"
   run.bat
   ```
   - Or double-click `Run Bank App.bat` (copy to desktop for easy access)

---

## 4) What `run.bat` does
- Checks Java installation
- Auto-downloads SQLite JDBC jar to `lib/` if missing
- Compiles Java files from `src/bank/management/system` into `classes`
- Runs `bank.management.system.Login` GUI
- Produces console output for errors/warnings

---

## 5) This app flow
- **Signup**: `Signup` -> `Signup2` -> `Signup3` (collects personal, additional, account info)
- **Login**: `Login` screen uses card + PIN from `signupthree` and `login` tables
- **Main**: `main_Class` menu opens operations
- **Operations**: `Deposit`, `Withdrawl`, `FastCash`, `BalanceEnquriy`, `Pin`, `mini`
- Actions write entries into `bank` table and update account info via `login` and `signupthree`

---

## 6) Important process & implementation points
- DB connection management is centralized in `Connn.java`:
  - opens `jdbc:sqlite:bank.db`
  - auto-creates tables with `CREATE TABLE IF NOT EXISTS`
- SQL statements use `PreparedStatement` where possible (hardening against injection)
- GUI icons in `ImageUtil.java` are loaded safely, with fallback to plain labels if resources are missing
- The app uses Swing and event handles to avoid locking UI with database operations

---

## 7) DB schema (auto-created)
- `login(card_number TEXT PRIMARY KEY, pin TEXT)`
- `signup(formno TEXT PRIMARY KEY, name TEXT, fname TEXT, dob TEXT, gender TEXT, email TEXT, marital TEXT, address TEXT, city TEXT, pincode TEXT, state TEXT)`
- `Signuptwo(formno TEXT PRIMARY KEY, religion TEXT, category TEXT, income TEXT, education TEXT, occupation TEXT, pan TEXT, aadhar TEXT, senior_citizen TEXT, existing_account TEXT)`
- `signupthree(formno TEXT PRIMARY KEY, account_type TEXT, card_number TEXT, pin TEXT, services TEXT)`
- `bank(pin TEXT, date TEXT, type TEXT, amount TEXT)`

---

## 8) How to reset state
- Stop app
- Delete `bank.db` file
- Re-run `run.bat` to recreate tables

---

## 9) Troubleshooting
- `no such table`: delete `bank.db` and restart
- `JAR not found`: confirm `lib\sqlite-jdbc-*.jar`
- `NullPointerException` on icon load: this is tolerated with fallback image-free UI
- `PreparedStatement` not found compile error: ensure import `java.sql.*` exists in classes using DB queries (already in `Connn`, `Withdrawl`, `mini`)

---

## 10) Code quality checks
- Use `javac` with `-Xlint:unchecked` and `-Xlint:deprecation` to find warnings
- Keep connection/statement/result set close in `finally` blocks (or `try-with-resources`) if expanding functionality

---

## 11) Git ignore recommendation
Add to `.gitignore`:
- `bank.db`
- `classes/`
- `*.log`

---

## 12) Future enhancements
- Add password/PIN hash (instead of plain text)
- Add transaction history UI with filters
- Add account transfer between accounts
- Add JUnit tests for DAO operations and validation rules

---

## 13) Quick debug run
```cmd
cd "C:\Users\nites\Downloads\Bank-Management-System--master"
run.bat
```
- If `run.bat` fails, run manual commands:
  - `javac -d classes -cp lib/sqlite-jdbc-3.51.3.0.jar src\bank\management\system\*.java`
  - `java -cp "classes;lib/sqlite-jdbc-3.51.3.0.jar" bank.management.system.Login`

