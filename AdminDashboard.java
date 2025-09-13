/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;
public class AdminDashboard {
    private final Hotel hotel;
    private final EmployeeManager employeeManager;
    private final PaymentCalculator paymentCalculator;

    public AdminDashboard(Hotel hotel) {
        this.hotel = hotel;
        this.employeeManager = new EmployeeManager();
        this.paymentCalculator = new PaymentCalculator();
    }

    public void showAdminMenu() {
    Scanner scanner = new Scanner(System.in);
    int choice;
    do {    
        System.out.println("\r\n"+ //
                                "+----+-------------------------+\r\n"+//
                                "| NO |     ADMIN DASHBOARD     |\r\n"+//
                                "+----+-------------------------+\r\n"+//
                                "| 1. | Show Hotel Statistics   |\r\n"+//
                                "| 2. | Payment & CHarges       |\r\n"+//
                                "| 3. | Manage Employees        |\r\n"+//
                                "| 4. | View All Accounts       |\r\n"+//
                                "| 0. | Logout                  |\r\n"+//
                                "+----+-------------------------+\r\n"+//
                                "\r\n"+//
                                "");
        System.out.print("Choose an option: ");

        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                displayHotelStatistics();
                break;
            case 2:
                paymentCalculator.showPaymentMenu(hotel);
                break;
            case 3:
                employeeManager.showEmployeeMenu();
                break;
            case 4:
                Security.viewAccounts();
                break;
            case 0:
                System.out.println("Logging out...");
                return;
            default:
                System.out.println("Invalid option. Try again.");
        }
    } while (true);
}

    private void displayHotelStatistics() {
        int totalRooms = hotel.getRooms().size();
        System.out.println("Total Rooms: " + totalRooms);

        int occupiedRooms = 0;
        for (Hotel.Room r : hotel.getRooms()) {
            if (!r.isAvailable()) occupiedRooms++;
        }
        System.out.println("Occupied Rooms: " + occupiedRooms);

        double occupancyRate = totalRooms == 0 ? 0.0 : (occupiedRooms * 100.0) / totalRooms;
        System.out.printf("Occupancy Rate: %.2f%%%n", occupancyRate);

        System.out.println("Total Bookings: " + hotel.getBookings().size());
    }
}
