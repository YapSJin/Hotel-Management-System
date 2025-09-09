/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
import java.util.List;
import java.util.Scanner;

public class BookingManager {
    private final Hotel hotel;

    public BookingManager(Hotel hotel) { this.hotel = hotel; }

    public void showBookingMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== BOOKING MENU ===");
            System.out.println("1. List bookings");
            System.out.println("2. Add booking");
            System.out.println("3. Cancel booking");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            while (!scanner.hasNextInt()) { scanner.next(); System.out.print("Enter number: "); }
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> listBookings();
                case 2 -> addBooking(scanner);
                case 3 -> cancelBooking(scanner);
                case 4 -> System.out.println("Returning...");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 4);
    }

    private void listBookings() {
        List<Hotel.Booking> bookings = hotel.getBookings();
        if (bookings.isEmpty()) { System.out.println("No bookings."); return; }
        int i = 1;
        for (Hotel.Booking b : bookings) {
            System.out.println(i++ + ". Room " + b.getRoom().getNumber() +
                    " (" + b.getRoom().getType() + "), nights: " + b.getNights());
        }
    }

    private void addBooking(Scanner scanner) {
        hotel.printRooms();
        System.out.print("Enter room number to book: ");
        int num = scanner.nextInt();
        System.out.print("Enter nights: ");
        int nights = scanner.nextInt();

        for (Hotel.Room r : hotel.getRooms()) {
            if (r.getNumber() == num && r.isAvailable()) {
                hotel.getBookings().add(new Hotel.Booking(r, nights));
                System.out.println("Booked room " + num + " for " + nights + " nights.");
                return;
            }
        }
        System.out.println("Room not found or not available.");
    }

    private void cancelBooking(Scanner scanner) {
        List<Hotel.Booking> bookings = hotel.getBookings();
        if (bookings.isEmpty()) { System.out.println("No bookings to cancel."); return; }
        listBookings();
        System.out.print("Enter booking index to cancel: ");
        int idx = scanner.nextInt();
        if (idx < 1 || idx > bookings.size()) { System.out.println("Invalid index."); return; }
        Hotel.Booking b = bookings.remove(idx - 1);
        b.getRoom().setAvailable(true);
        System.out.println("Cancelled booking for room " + b.getRoom().getNumber() + ".");
    }
}
