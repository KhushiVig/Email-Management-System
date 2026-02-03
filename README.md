# Email Management System

A Java console application for managing corporate email accounts with automated email generation, secure password management, and administrative controls.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [How It Works](#how-it-works)
- [Menu Options](#menu-options)
- [Test Accounts](#test-accounts)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [Author](#author)

## Overview

The Email Management System is a command-line application designed to simplify employee email account management in corporate environments. It automates email address generation based on employee information and department assignments, creates secure random passwords, and provides comprehensive account management features.

Key capabilities include password management with authentication, alternate email configuration, mailbox capacity settings, and administrative password reset functionality.

## Features

- **Automated Email Generation**: Creates professional email addresses based on employee name and department
- **Secure Password Generation**: Generates strong, random passwords using a combination of words, symbols, years, and digits
- **Department Management**: Supports multiple departments (Sales, Development, Accounting) with appropriate email routing
- **Password Management**: 
  - User-driven password changes with authentication
  - Admin password reset with secure admin key
  - Password verification system
  - Minimum 12-character password requirement
- **Alternate Email Configuration**: Set and validate backup email addresses
- **Mailbox Capacity Control**: Configure storage limits from 1 MB to 50,000 MB
- **Account Display**: View all accounts with comprehensive details
- **Password Reference System**: Administrative access to all account passwords
- **Input Validation**: Comprehensive validation for emails, numbers, and text inputs
- **Professional Console UI**: Clean, table-based interface with consistent formatting
- **Pre-loaded Test Data**: 6 sample accounts for immediate testing

## Technologies

- **Language**: Java (JDK 8 or higher)
- **Development Environment**: Visual Studio Code
- **Standard Java Libraries**:
  - `java.util.Scanner` - User input handling
  - `java.util.ArrayList` - Dynamic account storage
  - `java.security.SecureRandom` - Cryptographically secure random number generation
  - `java.time.Year` - Current year integration
  - `java.util.regex.Pattern` - Email format validation
  - `java.util.Objects` - Object comparison and hashing

## Project Structure

```
EmailManagementSystem/
├── src/
│   └── main/
│       └── java/
│           ├── Email.java            # Email account model class
│           ├── EmailApp.java         # Main application and menu controller
│           ├── ConsoleUI.java        # User interface and display formatting
│           └── InputValidator.java   # Input validation utilities
├── .gitignore
└── README.md
```

### Component Description

- **Email.java**: Core data model representing an email account. Handles email generation, password creation, and account settings validation.

- **EmailApp.java**: Main application entry point. Manages the menu system, user interactions, authentication flows, and coordinates between components.

- **ConsoleUI.java**: Responsible for all console output formatting. Provides methods for displaying tables, messages, and maintaining consistent visual styling.

- **InputValidator.java**: Utility class for validating and processing user inputs. Ensures data integrity with type checking and range validation.

## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Any Java IDE (Visual Studio Code, Eclipse, IntelliJ IDEA) or terminal/command prompt

### Verify Java Installation

```bash
java -version
javac -version
```

### Clone and Setup

1. Clone the repository:
```bash
git clone https://github.com/KhushiVig/email-management-system.git
cd email-management-system
```

2. Navigate to source directory:
```bash
cd src/main/java
```

3. Compile all Java files:
```bash
javac *.java
```

4. Run the application:
```bash
java EmailApp
```

### Alternative: Using VS Code

1. Open the `EmailManagementSystem` folder in VS Code
2. Install the Java Extension Pack (if not already installed)
3. Open `src/main/java/EmailApp.java`
4. Click the "Run" button above the `main` method or press `F5`
5. The application launches in the integrated terminal

### Alternative: Using Eclipse/IntelliJ

1. Import the project as a Java project
2. Locate `EmailApp.java` in the project explorer
3. Right-click and select "Run As" → "Java Application"

## Usage

### Starting the Application

When you run the application, you'll see:
1. A welcome screen displaying all test account passwords
2. The admin key for password resets
3. Helpful tips for using the system

### Creating a New Account

```
1. Select "Create New Email Account" from the main menu
2. Enter first name: John
3. Enter last name: Doe
4. Select department: 1 (Sales)

Output:
✓ Email: john-doe@sales-company-com
✓ Password: SilverStar#2026-42
```

### Changing a Password

```
1. Select "Change Password" from the main menu
2. Choose the account from the list
3. Enter current password for authentication
4. Enter new password (minimum 12 characters)
5. Password updated successfully
```

### Admin Password Reset

```
1. Select "Admin Password Reset" from the main menu
2. Enter admin key: admin123
3. Select the account to reset
4. New password generated and displayed
```

## How It Works

### Application Flow

1. **Initialization**: Application starts and creates 6 pre-configured test accounts
2. **Welcome Screen**: Displays all passwords and admin key for user reference
3. **Main Menu Loop**: Presents 8 management options
4. **Authentication**: Validates passwords before sensitive operations
5. **Data Management**: Maintains all changes in memory during the session

### Password Generation Algorithm

Passwords are generated using a cryptographically secure algorithm:

- Random adjective from a pool of 20 options
- Random noun from a pool of 20 options  
- Random special character from 9 options
- Current year (4 digits)
- Two random digits (00-99)

**Format**: `AdjectiveNounSymbolYear-Digits`

**Example**: `BlueOcean@2026-47`

### Email Address Format

Email addresses follow a standardized pattern:

- **With Department**: `firstname-lastname@department-company-com`
- **Without Department**: `firstname-lastname@company-com`

All names are automatically converted to lowercase with hyphens between first and last names.

### Authentication System

- **User Authentication**: Password verification required for password changes, alternate email setup, and capacity changes
- **Admin Authentication**: Separate admin key (`admin123`) required for administrative password resets
- **Secure Verification**: All passwords checked before allowing sensitive operations

## Menu Options

The application provides 8 main options:

1. **Create New Email Account** - Add new employee email accounts to the system
2. **Change Password** - Update account password with current password authentication
3. **Set Alternate Email** - Configure a backup email address with format validation
4. **Set Mailbox Capacity** - Adjust storage limits between 1 MB and 50,000 MB
5. **Display All Accounts** - View comprehensive list of all accounts with details
6. **Admin Password Reset** - Reset any account password using admin key
7. **View Password Reference** - Display all current passwords for administrative purposes
8. **Exit Application** - Close the program

## Test Accounts

The application includes 6 pre-configured accounts for immediate testing:

| Name | Department | Email |
|------|------------|-------|
| Rose Taylor | Development | rose-taylor@dev-company-com |
| Ryan Smith | Sales | ryan-smith@sales-company-com |
| Thomas Brown | Accounting | thomas-brown@acct-company-com |
| Olivia Jones | Sales | olivia-jones@sales-company-com |
| Michael Anderson | Development | michael-anderson@dev-company-com |
| Jennifer Davis | Accounting | jennifer-davis@acct-company-com |

**Admin Key**: `admin123` (used for Option 6 - Admin Password Reset)

**Note**: All passwords are displayed on startup and can be viewed anytime via Option 7.

## Future Enhancements

- Implement persistent data storage using file I/O or database (MySQL/PostgreSQL)
- Add email sending functionality via JavaMail API
- Implement role-based access control (Admin, Manager, Employee roles)
- Add account deletion and archival features with soft delete
- Create password expiration and rotation policies
- Implement multi-factor authentication (MFA) support
- Add comprehensive activity logging and audit trails
- Develop account search and filtering capabilities
- Enable batch account creation from CSV file import
- Add email signature management system
- Implement automatic backup and restore functionality
- Create department management (CRUD operations for departments)
- Build graphical user interface using JavaFX or Swing
- Add password encryption using BCrypt or similar
- Implement password strength meter and requirements checker
- Add email templates for common communications

## Contributing

Contributions are welcome! If you'd like to contribute to this project:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

Please make sure to:
- Follow Java coding conventions
- Test your changes thoroughly
- Update documentation as needed
- Write clear commit messages

For major changes, please open an issue first to discuss what you would like to change.

## Author

**Khushi Vig**
- GitHub: [@KhushiVig](https://github.com/KhushiVig)
- LinkedIn: [Khushi Vig](https://www.linkedin.com/in/khushi-vig/)
- Email: khushivig30@gmail.com

---

*This project demonstrates object-oriented programming principles, secure password generation, input validation, and professional software design patterns in Java. Developed as a practical application for corporate email account management.*

