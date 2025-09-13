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
    public void showPaymentMenu(Hotel hotel) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== PAYMENT CALCULATION ===");
            System.out.println("1. Show revenue summary");
            System.out.println("2. Back");
            System.out.print("Enter choice: ");
            while (!scanner.hasNextInt()) { scanner.next(); System.out.print("Enter number: "); }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:showRevenue(hotel);
                case 2:System.out.println("Returning...");
                default: System.out.println("Invalid option.");
            }
        } while (choice != 2);
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
