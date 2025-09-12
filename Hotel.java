/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private final List<Room> rooms = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();

    public Hotel() {
        rooms.add(new Room(101, "Standard", 120.0, true));
        rooms.add(new Room(102, "Deluxe", 180.0, false));
        rooms.add(new Room(201, "Suite", 300.0, true));
        bookings.add(new Booking(rooms.get(1), 2)); // simple sample booking
    }

    public List<Room> getRooms() { return rooms; }
    public List<Booking> getBookings() { return bookings; }

    public void printRooms() {
        System.out.println("\n=== ROOMS ===");
        for (Room r : rooms) {
            System.out.println(r.getNumber() + " | " + r.getType() + " | $" + r.getPricePerNight()
                    + " | " + (r.isAvailable() ? "Available" : "Occupied"));
        }
    }

    public static class Room {
        private final int number;
        private final String type;
        private final double pricePerNight;
        private boolean available;

        public Room(int number, String type, double pricePerNight, boolean available) {
            this.number = number; this.type = type; this.pricePerNight = pricePerNight; this.available = available;
        }
        public int getNumber() { return number; }
        public String getType() { return type; }
        public double getPricePerNight() { return pricePerNight; }
        public boolean isAvailable() { return available; }
        public void setAvailable(boolean a) { available = a; }
    }

    public static class Booking {
        private final Room room;
        private final int nights;

        public Booking(Room room, int nights) {
            this.room = room; this.nights = nights;
            room.setAvailable(false);
        }
        public Room getRoom() { return room; }
        public int getNights() { return nights; }
        public double getTotalCost() { return nights * room.getPricePerNight(); }
    }
}

