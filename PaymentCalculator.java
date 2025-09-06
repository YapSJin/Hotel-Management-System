/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
public class PaymentCalculator {
    public void showPaymentMenu(Hotel hotel) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n=== PAYMENT CALCULATION ===");
            System.out.println("1. Calculate Employee Payments");
            System.out.println("2. Calculate Revenue Report");
            System.out.println("3. Back to Admin Menu");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    calculateEmployeePayments();
                    break;
                case 2:
                    calculateRevenueReport(hotel);
                    break;
                case 3:
                    System.out.println("Returning to admin menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }
    
    private void calculateEmployeePayments() {
        // In a real application, this would calculate payments for all employees
        System.out.println("\nEmployee payment calculation feature would be implemented here.");
        System.out.println("This would connect to the employee database and calculate salaries,");
        System.out.println("deductions, bonuses, and generate payment reports.");
    }
    
    private void calculateRevenueReport(Hotel hotel) {
        System.out.println("\n=== REVENUE REPORT ===");
        
        double totalRevenue = 0;
        Map<String, Double> revenueByRoomType = new HashMap<>();
        
        for (Booking booking : hotel.getBookings()) {
            double bookingRevenue = booking.getRoom().getPrice() * booking.getNights();
            totalRevenue += bookingRevenue;
            
            String roomType = booking.getRoom().getType();
            revenueByRoomType.put(roomType, 
                revenueByRoomType.getOrDefault(roomType, 0.0) + bookingRevenue);
        }
        
        System.out.printf("Total Revenue: $%.2f\n", totalRevenue);
        System.out.println("\nRevenue by Room Type:");
        for (Map.Entry<String, Double> entry : revenueByRoomType.entrySet()) {
            System.out.printf("%-10s: $%.2f\n", entry.getKey(), entry.getValue());
        }
    }
}
