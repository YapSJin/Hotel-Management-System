/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
import java.util.Scanner;

public class HotelManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        int choice;
        do {
            System.out.println("\n=== HOTEL MANAGEMENT SYSTEM ===");
            System.out.println("1. Admin login");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            while (!scanner.hasNextInt()) { scanner.next(); System.out.print("Enter number: "); }
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter admin password: ");
                    String pwd = new Scanner(System.in).nextLine();
                    if ("admin123".equals(pwd)) {
                        new AdminDashboard(hotel).showAdminMenu();
                    } else {
                        System.out.println("Invalid password.");
                    }
                }
                case 2 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 2);
    }
}
