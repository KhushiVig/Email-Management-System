import java.util.Scanner;

public class InputValidator {

    public static int getMenuChoice(Scanner scanner, int min, int max) {
        while (true) {
            ConsoleUI.printMenuPrompt(min, max);

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                ConsoleUI.printError("Please enter a valid number-");
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < min || choice > max) {
                ConsoleUI.printError("Please enter a number between " + min + " and " + max + "-");
                continue;
            }

            return choice;
        }
    }

    /**
     * Reads and validates an integer input.
     */
    public static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            ConsoleUI.printPrompt(prompt);

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                ConsoleUI.printError("Please enter a valid number-");
                continue;
            }

            int value = scanner.nextInt();
            scanner.nextLine();

            if (value < min || value > max) {
                ConsoleUI.printError("Please enter a number between " + min + " and " + max + "-");
                continue;
            }

            return value;
        }
    }

    /**
     * Reads and validates non-empty input.
     */
    public static String getNonEmptyInput(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                ConsoleUI.printPrompt("Input cannot be empty- Try again: ");
                continue;
            }

            return input;
        }
    }

    /**
     * Waits for user to press ENTER key.
     */
    public static void waitForEnter(Scanner scanner) {
        scanner.nextLine();
    }

    /**
     * Reads a password input.
     */
    public static String getPasswordInput(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Reads optional input.
     */
    public static String getOptionalInput(Scanner scanner) {
        return scanner.nextLine().trim();
    }

    /**
     * Reads a department choice.
     */
    public static String getDepartmentChoice(Scanner scanner) {
        int choice = getMenuChoice(scanner, 1, 4);
        return switch (choice) {
            case 1 -> "sales";
            case 2 -> "dev";
            case 3 -> "acct";
            default -> "none";
        };
    }

    /**
     * Consumes remaining input.
     */
    public static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}