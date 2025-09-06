/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
// Admin Dashboard class
public class AdminDashboard {
    private Hotel hotel;
    private EmployeeManager employeeManager;
    private BookingManager bookingManager;
    private PaymentCalculator paymentCalculator;
    
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
            System.out.println("1. Employee Management");
            System.out.println("2. Manage Bookings");
            System.out.println("3. Calculate Payments");
            System.out.println("4. View Hotel Statistics");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    employeeManager.showEmployeeMenu();
                    break;
                case 2:
                    bookingManager.showBookingMenu();
                    break;
                case 3:
                    paymentCalculator.showPaymentMenu(hotel);
                    break;
                case 4:
                    displayHotelStatistics();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
    
    private void displayHotelStatistics() {
        System.out.println("\n=== HOTEL STATISTICS ===");
        System.out.println("Total Rooms: " + hotel.getRooms().size());
        
        long occupiedRooms = hotel.getRooms().stream()
                .filter(room -> !room.isAvailable())
                .count();
        System.out.println("Occupied Rooms: " + occupiedRooms);
        
        double occupancyRate = (double) occupiedRooms / hotel.getRooms().size() * 100;
        System.out.printf("Occupancy Rate: %.2f%%\n", occupancyRate);
        
        System.out.println("Total Bookings: " + hotel.getBookings().size());
    }
}
