import java.util.ArrayList;
import java.util.Scanner;

public class EmailApp {

    private static final String ADMIN_KEY = "admin123";
    private static final int MAX_CAPACITY = 50_000;

    private static ArrayList<Email> emailAccounts;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        try {
            emailAccounts = initializeAccounts();
            displayWelcome();
            runMainMenu();
        } finally {
            scanner.close();
        }
    }

    private static ArrayList<Email> initializeAccounts() {
        ArrayList<Email> accounts = new ArrayList<>();
        accounts.add(new Email("Rose", "Taylor", "dev"));
        accounts.add(new Email("Ryan", "Smith", "sales"));
        accounts.add(new Email("Thomas", "Brown", "acct"));
        accounts.add(new Email("Olivia", "Jones", "sales"));
        accounts.add(new Email("Michael", "Anderson", "dev"));
        accounts.add(new Email("Jennifer", "Davis", "acct"));
        return accounts;
    }

    /**
     * Displays welcome screen with test accounts and admin key.
     */
    private static void displayWelcome() {
        ConsoleUI.clearScreen();
        ConsoleUI.printTitle("EMAIL MANAGEMENT SYSTEM");
        ConsoleUI.printSection("TEST ACCOUNTS - SAVE THESE PASSWORDS");

        ConsoleUI.printPasswordTable(emailAccounts);

        ConsoleUI.printSection("ADMIN KEY");
        System.out.println("  Key: admin123 (Use in Option 6 for password reset)");

        ConsoleUI.printSection("TIPS");
        System.out.println("  - Passwords shown on startup & in Option 7");
        System.out.println("  - Admin key available in Option 7");
        System.out.println("  - Password format: Word+Word+Symbol+Year-Digits");

        ConsoleUI.pauseForInput();
        InputValidator.waitForEnter(scanner);
    }

    /**
     * Main menu loop.
     */
    private static void runMainMenu() {
        int choice;
        do {
            displayMainMenu();
            choice = InputValidator.getMenuChoice(scanner, 1, 8);

            try {
                switch (choice) {
                    case 1 -> createNewAccount();
                    case 2 -> changePassword();
                    case 3 -> setAlternateEmail();
                    case 4 -> setMailboxCapacity();
                    case 5 -> displayAllAccounts();
                    case 6 -> adminResetPassword();
                    case 7 -> viewPasswordReference();
                    case 8 -> exitApplication();
                }
            } catch (IllegalArgumentException e) {
                ConsoleUI.printError(e.getMessage());
                pressEnterToContinue();
            }
        } while (choice != 8);
    }

    /**
     * Displays the main menu.
     */
    private static void displayMainMenu() {
        ConsoleUI.clearScreen();
        ConsoleUI.printTitle("MAIN MENU");
        System.out.println("\n  1- Create New Email Account");
        System.out.println("  2- Change Password");
        System.out.println("  3- Set Alternate Email");
        System.out.println("  4- Set Mailbox Capacity");
        System.out.println("  5- Display All Accounts");
        System.out.println("  6- Admin Password Reset");
        System.out.println("  7- View Password Reference");
        System.out.println("  8- Exit Application");
        System.out.println();
        ConsoleUI.printSeparator();
    }

    /**
     * Option 1: Create a new email account.
     */
    private static void createNewAccount() {
        ConsoleUI.clearScreen();
        ConsoleUI.printTitle("CREATE NEW EMAIL ACCOUNT");

        ConsoleUI.printPrompt("First Name: ");
        String firstName = InputValidator.getNonEmptyInput(scanner);

        ConsoleUI.printPrompt("Last Name:  ");
        String lastName = InputValidator.getNonEmptyInput(scanner);

        System.out.println("\n  Select Department:");
        System.out.println("    1- Sales");
        System.out.println("    2- Development");
        System.out.println("    3- Accounting");
        System.out.println("    4- None");
        String department = InputValidator.getDepartmentChoice(scanner);

        try {
            Email newAccount = new Email(firstName, lastName, department);
            emailAccounts.add(newAccount);

            ConsoleUI.printSeparator();
            ConsoleUI.printSuccess("Account Created Successfully!");
            System.out.println("\n  Account Details:");
            ConsoleUI.printField("Email", newAccount.getEmailAddress());
            ConsoleUI.printField("Password", newAccount.getPassword());
            ConsoleUI.printField("Department", department);
            ConsoleUI.printNotice("SAVE THIS PASSWORD! View again in Option 7");
            ConsoleUI.printSeparator();

        } catch (IllegalArgumentException e) {
            ConsoleUI.printError("Failed to create account: " + e.getMessage());
        }

        pressEnterToContinue();
    }

    /**
     * Option 2: Change password.
     */
    private static void changePassword() {
        ConsoleUI.clearScreen();
        ConsoleUI.printTitle("CHANGE PASSWORD");

        Email account = selectAccount();
        if (account == null) {
            pressEnterToContinue();
            return;
        }

        ConsoleUI.printPrompt("Current Password: ");
        String currentPassword = InputValidator.getPasswordInput(scanner);

        if (!account.verifyPassword(currentPassword)) {
            ConsoleUI.printError("Authentication failed- Password incorrect-");
            pressEnterToContinue();
            return;
        }

        ConsoleUI.printPrompt("New Password (12+ characters): ");
        String newPassword = InputValidator.getNonEmptyInput(scanner);

        try {
            account.changePassword(newPassword);
            ConsoleUI.printSuccess("Password changed successfully!");
            System.out.println("  View passwords anytime in Option 7");
        } catch (IllegalArgumentException e) {
            ConsoleUI.printError(e.getMessage());
        }

        pressEnterToContinue();
    }

    /**
     * Option 3: Set alternate email.
     */
    private static void setAlternateEmail() {
        ConsoleUI.clearScreen();
        ConsoleUI.printTitle("SET ALTERNATE EMAIL");

        Email account = selectAccount();
        if (account == null) {
            pressEnterToContinue();
            return;
        }

        ConsoleUI.printPrompt("Current Password: ");
        String currentPassword = InputValidator.getPasswordInput(scanner);

        if (!account.verifyPassword(currentPassword)) {
            ConsoleUI.printError("Authentication failed- Password incorrect-");
            pressEnterToContinue();
            return;
        }

        ConsoleUI.printPrompt("Alternate Email (or press ENTER to skip): ");
        String altEmail = InputValidator.getOptionalInput(scanner);

        if (altEmail.isEmpty()) {
            System.out.println("\n  No changes made-");
            pressEnterToContinue();
            return;
        }

        try {
            account.setAlternateEmail(altEmail);
            ConsoleUI.printSuccess("Alternate email set successfully!");
            ConsoleUI.printField("Alternate Email", altEmail);
        } catch (IllegalArgumentException e) {
            ConsoleUI.printError(e.getMessage());
        }

        pressEnterToContinue();
    }

    /**
     * Option 4: Set mailbox capacity.
     */
    private static void setMailboxCapacity() {
        ConsoleUI.clearScreen();
        ConsoleUI.printTitle("SET MAILBOX CAPACITY");

        Email account = selectAccount();
        if (account == null) {
            pressEnterToContinue();
            return;
        }

        ConsoleUI.printPrompt("Current Password: ");
        String currentPassword = InputValidator.getPasswordInput(scanner);

        if (!account.verifyPassword(currentPassword)) {
            ConsoleUI.printError("Authentication failed- Password incorrect-");
            pressEnterToContinue();
            return;
        }

        int capacity = InputValidator.getIntInput(scanner,
                "New Capacity (1-" + MAX_CAPACITY + " MB): ", 1, MAX_CAPACITY);

        if (capacity == 0) {
            pressEnterToContinue();
            return;
        }

        try {
            account.setMailboxCapacity(capacity);
            ConsoleUI.printSuccess("Mailbox capacity updated!");
            ConsoleUI.printField("New Capacity", capacity + " MB");
        } catch (IllegalArgumentException e) {
            ConsoleUI.printError(e.getMessage());
        }

        pressEnterToContinue();
    }

    /**
     * Option 5: Display all accounts.
     */
    private static void displayAllAccounts() {
        ConsoleUI.clearScreen();
        ConsoleUI.printTitle("ALL EMAIL ACCOUNTS (" + emailAccounts.size() + " total)");

        if (emailAccounts.isEmpty()) {
            System.out.println("\n  No accounts found-");
        } else {
            System.out.println();
            ConsoleUI.printAccountsTable(emailAccounts);
        }

        ConsoleUI.pauseForInput();
        InputValidator.waitForEnter(scanner);
    }

    /**
     * Option 6: Admin password reset.
     */
    private static void adminResetPassword() {
        ConsoleUI.clearScreen();
        ConsoleUI.printTitle("ADMIN PASSWORD RESET");

        ConsoleUI.printSection("ADMIN KEY REQUIRED");
        ConsoleUI.printPrompt("Enter Admin Key: ");
        String adminKey = InputValidator.getPasswordInput(scanner);

        if (!adminKey.equals(ADMIN_KEY)) {
            ConsoleUI.printError("Access denied- Invalid admin key-");
            System.out.println("  Hint: Check Option 7 for admin key");
            pressEnterToContinue();
            return;
        }

        Email account = selectAccount();
        if (account == null) {
            pressEnterToContinue();
            return;
        }

        account.resetPassword();
        ConsoleUI.printSeparator();
        ConsoleUI.printSuccess("Password reset successfully!");
        System.out.println("\n  Account Details:");
        ConsoleUI.printField("Email", account.getEmailAddress());
        ConsoleUI.printField("New Password", account.getPassword());
        ConsoleUI.printNotice("SAVE THIS PASSWORD! View again in Option 7");
        ConsoleUI.printSeparator();

        pressEnterToContinue();
    }

    /**
     * Option 7: View password reference.
     */
    private static void viewPasswordReference() {
        ConsoleUI.clearScreen();
        ConsoleUI.printTitle("PASSWORD REFERENCE");

        ConsoleUI.printSection("ACCOUNT PASSWORDS");
        ConsoleUI.printPasswordTable(emailAccounts);

        ConsoleUI.printSection("ADMIN KEY");
        System.out.println("  admin123 (Use in Option 6 for password reset)");

        System.out.println();
        ConsoleUI.printSeparator();
        ConsoleUI.pauseForInput();
        InputValidator.waitForEnter(scanner);
    }

    /**
     * Option 8: Exit application.
     */
    private static void exitApplication() {
        ConsoleUI.clearScreen();
        ConsoleUI.printTitle("Thank you for using Email Management System!");
        System.out.println("\n" + " ".repeat(25) + "Goodbye!\n");
        ConsoleUI.printSeparator();
    }

    /**
     * Displays account selection menu.
     */
    private static Email selectAccount() {
        if (emailAccounts.isEmpty()) {
            ConsoleUI.printError("No accounts available-");
            return null;
        }

        System.out.println("\n  Select Account (passwords shown for reference):\n");
        ConsoleUI.printSelectTable(emailAccounts);

        int choice = InputValidator.getMenuChoice(scanner, 0, emailAccounts.size());

        if (choice == 0) {
            System.out.println("\n  Selection cancelled-");
            return null;
        }

        return emailAccounts.get(choice - 1);
    }

    /**
     * Pauses for user input.
     */
    private static void pressEnterToContinue() {
        ConsoleUI.pauseForInput();
        InputValidator.waitForEnter(scanner);
    }
}