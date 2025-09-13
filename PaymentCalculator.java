/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PaymentCalculator {
    
    // Simple placeholder to pause and avoid accidental fall-through read issues
    private void pause(Scanner scanner) {
        System.out.print("Press Enter to continue...");
        // Ensure any pending newline is consumed first
        if (scanner.hasNextLine()) scanner.nextLine();
        scanner.nextLine();
        System.out.println();
    }
    
    public void showPaymentMenu(Hotel hotel) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\r\n"+ //
                                "+----+-------------------------+\r\n"+//
                                "| NO |   PAYMENT CALCULATION   |\r\n"+//
                                "+----+-------------------------+\r\n"+//
                                "| 1. | Show Revenue Summary    |\r\n"+//
                                "| 0. | Back                    |\r\n"+//
                                "+----+-------------------------+\r\n"+//
                                "\r\n"+//
                                "");
            System.out.print("Enter choice: ");
            while (!scanner.hasNextInt()) { 
                scanner.next(); 
                System.out.print("Enter number: "); 
            }
            choice = scanner.nextInt();
            if (scanner.hasNextLine()) scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: 
                    showRevenue(hotel);
                    pause(scanner);
                    break;
                case 0: 
                    System.out.println("Returning...");
                    break;
                default: 
                    System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }

    private void showRevenue(Hotel hotel) {
        double totalRevenue = 0.0;
        Map<String, Double> revenueByRoomType = new HashMap<>();

        for (Hotel.Booking booking : hotel.getBookings()) {
            double bookingRevenue = booking.getTotalCost();
            totalRevenue += bookingRevenue;
            String roomType = booking.getRoom().getType();
            double prev = revenueByRoomType.getOrDefault(roomType, 0.0);
            revenueByRoomType.put(roomType, prev + bookingRevenue);
        }

        System.out.printf("Total Revenue: $%.2f%n", totalRevenue);
        System.out.println("Revenue by Room Type:");
        for (Map.Entry<String, Double> e : revenueByRoomType.entrySet()) {
            System.out.printf("  %-10s : $%.2f%n", e.getKey(), e.getValue());
        }
    }
}
