/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
import java.util.*;
import java.time.*;
import java.text.SimpleDateFormat;

// Main class to run the hotel management system
public class HotelManagementSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("TAR Hotel");
        hotel.initializeRooms();
        
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n=== " + hotel.getName() + " Management System ===");
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Check-In Guest");
            System.out.println("3. Check-Out Guest");
            System.out.println("4. View Guest Information");
            System.out.println("5. Display All Bookings");
            System.out.println("6. Exit");
            System.out.println("7. Admin Login");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter guest name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter guest phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter number of nights: ");
                    int nights = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    
                    Guest newGuest = new Guest(name, phone);
                    hotel.checkIn(newGuest, roomNumber, nights);
                    break;
                case 3:
                    System.out.print("Enter room number to check-out: ");
                    int checkOutRoom = scanner.nextInt();
                    hotel.checkOut(checkOutRoom);
                    break;
                case 4:
                    System.out.print("Enter room number: ");
                    int guestRoom = scanner.nextInt();
                    hotel.displayGuestInfo(guestRoom);
                    break;
                case 5:
                    hotel.displayAllBookings();
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    break;
                case 7:
                System.out.print("Enter admin password: ");
                String password = scanner.nextLine();
                if ("admin123".equals(password)) { // In real app, use secure authentication
                    AdminDashboard adminDashboard = new AdminDashboard(hotel);
                    adminDashboard.showAdminMenu();
                } else {
                    System.out.println("Invalid password!");
                }
            break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
        
        scanner.close();
    }
}