import java.util.InputMismatchException;
import java.util.Scanner;

public class MainPage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            
            System.out.println("\r\n"+ //
                                "+----+----------------------+\r\n"+//
                                "| NO |        Menu          |\r\n"+//
                                "+----+----------------------+\r\n"+//
                                "| 1. | Login As Admin       |\r\n"+//
                                "| 2. | Login As User        |\r\n"+//
                                "| 3. | Create user account  |\r\n"+//
                                "| 0. | Exit                 |\r\n"+//
                                "+----+----------------------+\r\n"+//
                                "\r\n"+//
                                "");
            System.out.print("Enter choice: ");
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // clear newline
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number!");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 0: {
                    System.out.println("👋 Goodbye!");
                    return;
                }
                case 1: { // Admin login
                    Security admin = Security.login("admin");
                    if (admin != null) {
                        adminDashBoard.show(admin);
                    }
                    break; // Added missing break
                }
                case 2: { 
                    Security user = Security.login("user");
                    if (user != null) {
                        Booking.show(user);
                    }
                    break; // Added missing break
                }
                case 3: {
                    Security.createAccount("user");
                    break; // Added missing break
                }
                default: {
                    System.out.println("Invalid choice, try again!");
                    break;
                }
            }
        }
    }
}