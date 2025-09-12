
import java.util.ArrayList;
import java.util.Scanner;

public class Security {
    private static Scanner scanner = new Scanner(System.in);
    private String user_name;
    private String password;
    private String role; // "admin" or "user"
    private static ArrayList<Security> account = new ArrayList<>();

    static {
        new Security("admin", "admin123", "admin");
        new Security("user", "user123", "user");
    }

    public Security(String user_name, String password, String role) {
        this.user_name = user_name;
        this.password = password;
        this.role = role.toLowerCase();
        account.add(this);
    }

    public String getRole() {
        return role;
    }

    public String getUserName() {
        return user_name;
    }

    private String getPassword() {
        return password;
    }

    private void setUserName(String user_name) {
        this.user_name = user_name;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public static Security login(String expectedRole) {
        System.out.println("\n=== " + expectedRole.toUpperCase() + " LOGIN ===");
        System.out.print("Username: ");
        String inputUser = scanner.nextLine();
        System.out.print("Password: ");
        String inputPass = scanner.nextLine();

        for (Security acc : account) {
            if (acc.getUserName().equals(inputUser)
                    && acc.getPassword().equals(inputPass)
                    && acc.getRole().equals(expectedRole)) {
                System.out.println("\nLogin successful! Welcome " + acc.getUserName());
                return acc;
            }
        }

        System.out.println("\n Invalid credentials for " + expectedRole + "!");
        return null;
    }

    public static void createAccount(String roles) {
        System.out.println("\n=== CREATE " + roles.toUpperCase() + " ACCOUNT ===");
        System.out.print("Enter the User Name: ");
        String newUserName = scanner.nextLine();
        System.out.print("Enter the Password: ");
        String newPassword = scanner.nextLine();

        boolean userNameExists = account.stream()
                .anyMatch(acc -> acc.getUserName().equals(newUserName));

        if (userNameExists) {
            System.out.println("Username already exists!");
            return;
        }

        new Security(newUserName, newPassword, roles);
        System.out.println("" + roles + " account created successfully!");
    }

    public static void deleteAccount() {
        System.out.println("\n=== DELETE ACCOUNT ===");
        System.out.print("Enter Username to delete: ");
        String delUser = scanner.nextLine();
        System.out.print("Enter Password: ");
        String delPass = scanner.nextLine();

        boolean removed = account.removeIf(acc ->
                acc.getUserName().equals(delUser)
                        && acc.getPassword().equals(delPass)
                        && !acc.getUserName().equalsIgnoreCase("admin")
        );

        if (removed) {
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found or cannot delete default admin.");
        }
    }

    public static void viewAccounts() {
        System.out.println("\n=== ACCOUNT LIST ===");
        for (Security acc : account) {
            System.out.println( "" + acc.getUserName() + " (" + acc.getRole() + ")");
        }
    }
}
