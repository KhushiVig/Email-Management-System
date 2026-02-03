import java.security.SecureRandom;
import java.time.Year;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents an email account with password management and mailbox configuration.
 */
public class Email {

    private final String firstName;
    private final String lastName;
    private final String department;
    private final String emailAddress;

    private String password;
    private int mailboxCapacity;
    private String alternateEmail;

    // Constants
    public static final int DEFAULT_CAPACITY = 500;
    public static final int MAX_CAPACITY = 50_000;
    private static final int MIN_PASSWORD_LENGTH = 12;
    private static final String DOMAIN = "company-com";

    // Password generation components
    private static final String[] ADJECTIVES = {
        "Blue", "Green", "Silver", "Golden", "Crimson",
        "Purple", "Azure", "Amber", "Emerald", "Ivory",
        "Scarlet", "Violet", "Copper", "Bronze", "Pearl",
        "Ruby", "Jade", "Topaz", "Coral", "Platinum"
    };

    private static final String[] NOUNS = {
        "Sky", "Ocean", "Forest", "River", "Moon",
        "Star", "Cloud", "Fire", "Stone", "Leaf",
        "Wind", "Rain", "Snow", "Sun", "Wave",
        "Mountain", "Valley", "Lake", "Desert", "Meadow"
    };

    private static final char[] SPECIAL_CHARS = {'@', '#', '$', '&', '!', '*', '%', '+', '='};

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    /**
     * Constructs an Email account.
     */
    public Email(String firstName, String lastName, String department) {
        this.firstName = validateName(firstName, "First Name");
        this.lastName = validateName(lastName, "Last Name");
        this.department = normalizeDepartment(department);

        this.emailAddress = generateEmail();
        this.password = generatePassword();
        this.mailboxCapacity = DEFAULT_CAPACITY;
    }

    private String validateName(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be empty");
        }
        return value.trim();
    }

    private String normalizeDepartment(String department) {
        return (department == null) ? "none" : department.toLowerCase();
    }

    /**
     * Generates email address.
     */
    private String generateEmail() {
        String base = firstName.toLowerCase() + "-" + lastName.toLowerCase();
        if ("none".equals(department)) {
            return base + "@" + DOMAIN;
        }
        return base + "@" + department + "-" + DOMAIN;
    }

    /**
     * Generates unique password.
     */
    private String generatePassword() {
        String adjective = ADJECTIVES[RANDOM.nextInt(ADJECTIVES.length)];
        String noun = NOUNS[RANDOM.nextInt(NOUNS.length)];
        char symbol = SPECIAL_CHARS[RANDOM.nextInt(SPECIAL_CHARS.length)];
        int year = Year.now().getValue();
        int randomDigits = RANDOM.nextInt(100);
        
        return String.format("%s%s%c%d-%02d", adjective, noun, symbol, year, randomDigits);
    }

    public boolean verifyPassword(String input) {
        return input != null && input.equals(password);
    }

    public void changePassword(String newPassword) {
        if (newPassword == null || newPassword.length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException(
                "Password must be at least " + MIN_PASSWORD_LENGTH + " characters"
            );
        }
        this.password = newPassword;
    }

    public void resetPassword() {
        this.password = generatePassword();
    }

    public void setAlternateEmail(String email) {
        if (email != null && !email.isBlank() && !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.alternateEmail = (email == null || email.isBlank()) ? null : email;
    }

    public void setMailboxCapacity(int capacity) {
        if (capacity < 1 || capacity > MAX_CAPACITY) {
            throw new IllegalArgumentException(
                "Capacity must be between 1 and " + MAX_CAPACITY + " MB"
            );
        }
        this.mailboxCapacity = capacity;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public String getDepartment() {
        if ("none".equals(department)) {
            return "None";
        }
        return department.substring(0, 1).toUpperCase() + department.substring(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email email = (Email) o;
        return Objects.equals(emailAddress, email.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + emailAddress + ")";
    }
}