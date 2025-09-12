
import java.util.*;

public class BookingSystem {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static ArrayList<Payment> payments = new ArrayList<>();
    static int bookingCounter = 1;
    static int paymentCounter = 1;

    public static void main(String[] args) {
        rooms.add(new Room("R001", "Single", 100, "Available"));
        rooms.add(new Room("R002", "Double", 150, "Available"));
        rooms.add(new Room("R003", "Suite", 300, "Available"));

        while (true) {
            System.out.println("\n+----+--------------------+");
            System.out.println("| NO |   Booking System   |");
            System.out.println("+----+--------------------+");
            System.out.println("| 1. | View Rooms         |");
            System.out.println("| 2. | Book Room          |");
            System.out.println("| 3. | Modify Reservation |");
            System.out.println("| 4. | Make Payment       |");
            System.out.println("| 5. | View Payments      |");
            System.out.println("| 6. | Exit               |");
            System.out.println("+----+--------------------+");
            System.out.print("Enter choice (1 - 6): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 : Room.viewRooms(rooms);break;
                case 2 : Booking.bookRoom(scanner, rooms, bookings, bookingCounter++);break;
                case 3 : Booking.modifyReservation(scanner, bookings, rooms, payments);break;
                case 4 : Payment.makePayment(scanner, bookings, payments, paymentCounter++);break;
                case 5 : Payment.viewPayments(payments);break;
                case 6 : { System.out.println("Exiting..."); return; }
                default : System.out.println("Invalid choice!");
            }
        }
    }
}
