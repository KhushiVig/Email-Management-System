import java.util.ArrayList;

public class ConsoleUI {

    // Consistent border length throughout application
    private static final int BORDER_WIDTH = 90;
    private static final String HORIZONTAL_BORDER = "=".repeat(BORDER_WIDTH);
    private static final String SEPARATOR = "-".repeat(BORDER_WIDTH);
    
    private static final String PASSWORD_TABLE_TOP = "+-----+--------------------+--------------------------------------+------------------------+";
    private static final String PASSWORD_TABLE_HEADER = "| NO- | NAME               | EMAIL ADDRESS                        | PASSWORD               |";
    private static final String PASSWORD_TABLE_SEP = "+-----+--------------------+--------------------------------------+------------------------+";
    private static final String PASSWORD_TABLE_BOTTOM = "+-----+--------------------+--------------------------------------+------------------------+";

    private static final String ACCOUNTS_TABLE_TOP = "+-----+--------------------+--------------------------------------+------------+----------------+";
    private static final String ACCOUNTS_TABLE_HEADER = "| NO- | NAME               | EMAIL ADDRESS                        | DEPARTMENT | CAPACITY (MB)  |";
    private static final String ACCOUNTS_TABLE_SEP = "+-----+--------------------+--------------------------------------+------------+----------------+";
    private static final String ACCOUNTS_TABLE_BOTTOM = "+-----+--------------------+--------------------------------------+------------+----------------+";

    private static final String SELECT_TABLE_TOP = "+-----+--------------------+--------------------------------------+------------------------+";
    private static final String SELECT_TABLE_HEADER = "| NO- | NAME               | EMAIL ADDRESS                        | PASSWORD               |";
    private static final String SELECT_TABLE_SEP = "+-----+--------------------+--------------------------------------+------------------------+";
    private static final String SELECT_TABLE_BOTTOM = "+-----+--------------------+--------------------------------------+------------------------+";

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    public static void printTitle(String title) {
        System.out.println("\n" + HORIZONTAL_BORDER);
        int padding = Math.max(0, (BORDER_WIDTH - title.length()) / 2);
        System.out.println(" ".repeat(padding) + title);
        System.out.println(HORIZONTAL_BORDER);
    }

    public static void printSection(String section) {
        System.out.println("\n  [" + section + "]\n");
    }

    public static void printSuccess(String message) {
        System.out.println("\n   [SUCCESS] " + message);
    }

    public static void printError(String message) {
        System.out.println("\n   [ERROR] " + message);
    }

    public static void printField(String label, String value) {
        System.out.printf("    %-22s: %s%n", label, value);
    }

    public static void printField(String label, int value) {
        System.out.printf("    %-22s: %d%n", label, value);
    }

    /**
     * Prints password table with account information.
     */
    public static void printPasswordTable(ArrayList<Email> accounts) {
        System.out.println(PASSWORD_TABLE_TOP);
        System.out.println(PASSWORD_TABLE_HEADER);
        System.out.println(PASSWORD_TABLE_SEP);

        for (int i = 0; i < accounts.size(); i++) {
            Email email = accounts.get(i);
            String name = email.getFirstName() + " " + email.getLastName();
            System.out.printf("| %-3d | %-18s | %-36s | %-22s |%n",
                    i + 1, 
                    truncate(name, 18), 
                    truncate(email.getEmailAddress(), 36),
                    truncate(email.getPassword(), 22));
        }

        System.out.println(PASSWORD_TABLE_BOTTOM);
    }

    /**
     * Prints detailed accounts table.
     */
    public static void printAccountsTable(ArrayList<Email> accounts) {
        System.out.println(ACCOUNTS_TABLE_TOP);
        System.out.println(ACCOUNTS_TABLE_HEADER);
        System.out.println(ACCOUNTS_TABLE_SEP);

        for (int i = 0; i < accounts.size(); i++) {
            Email acc = accounts.get(i);
            String name = acc.getFirstName() + " " + acc.getLastName();
            System.out.printf("| %-3d | %-18s | %-36s | %-10s | %-14d |%n",
                    i + 1,
                    truncate(name, 18),
                    truncate(acc.getEmailAddress(), 36),
                    truncate(acc.getDepartment(), 10),
                    acc.getMailboxCapacity());

            if (acc.getAlternateEmail() != null && !acc.getAlternateEmail().isEmpty()) {
                System.out.printf("|     | %-18s | %-36s | %-10s | %-14s |%n",
                        "Alt:", truncate(acc.getAlternateEmail(), 36), "", "");
            }
        }

        System.out.println(ACCOUNTS_TABLE_BOTTOM);
    }

    /**
     * Prints selection table with passwords.
     */
    public static void printSelectTable(ArrayList<Email> accounts) {
        System.out.println(SELECT_TABLE_TOP);
        System.out.println(SELECT_TABLE_HEADER);
        System.out.println(SELECT_TABLE_SEP);

        for (int i = 0; i < accounts.size(); i++) {
            Email email = accounts.get(i);
            String name = email.getFirstName() + " " + email.getLastName();
            System.out.printf("| %-3d | %-18s | %-36s | %-22s |%n",
                    i + 1,
                    truncate(name, 18),
                    truncate(email.getEmailAddress(), 36),
                    truncate(email.getPassword(), 22));
        }

        System.out.println(SELECT_TABLE_BOTTOM);
    }

    private static String truncate(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "---";
    }

    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public static void pauseForInput() {
        System.out.print("\n  Press ENTER to continue---");
    }

    public static void printMenuPrompt(int min, int max) {
        if (max == 0) {
            System.out.print("  Enter choice (0 to cancel, 1-" + min + "): ");
        } else {
            System.out.print("  Enter choice (" + min + "-" + max + "): ");
        }
    }

    public static void printPrompt(String prompt) {
        System.out.print("  " + prompt);
    }

    public static void printNotice(String notice) {
        System.out.println("  [!] " + notice);
    }
}