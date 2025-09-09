/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;
/**
 *
 * @author USER
 */
// Admin Dashboard class
public class AdminDashboard {
    private final Hotel hotel;
    private final EmployeeManager employeeManager;
    private final BookingManager bookingManager;
    private final PaymentCalculator paymentCalculator;

    public AdminDashboard(Hotel hotel) {
        this.hotel = hotel;
        this.employeeManager = new EmployeeManager();
        this.bookingManager = new BookingManager(hotel);
        this.paymentCalculator = new PaymentCalculator();
    }

    public void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== ADMIN DASHBOARD ===");
            System.out.println("1. Show hotel statistics");
            System.out.println("2. Manage bookings");
            System.out.println("3. Calculate revenue");
            System.out.println("4. Manage employees");
            System.out.println("5. List rooms");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            while (!scanner.hasNextInt()) { scanner.next(); System.out.print("Enter number: "); }
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> displayHotelStatistics();
                case 2 -> bookingManager.showBookingMenu();
                case 3 -> paymentCalculator.showPaymentMenu(hotel);
                case 4 -> employeeManager.showEmployeeMenu();
                case 5 -> hotel.printRooms();
                case 6 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 6);
    }

    // keep this INSIDE the class
    private void displayHotelStatistics() {
        System.out.println("\n=== HOTEL STATISTICS ===");

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
