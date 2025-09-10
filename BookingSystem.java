import java.util.*;

class Room {
    private String roomID;
    private String type;
    private int pricePerNight;
    private String status;

    public Room(String roomID, String type, int pricePerNight, String status) {
        this.roomID = roomID;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return roomID + "    | " + type + " |  RM" + pricePerNight + "  | " + status;
    }
}

class Booking {
    private String bookingID;
    private String guestID;
    private String guestName;
    private String phoneNo;
    private Room room;
    private String checkIn;
    private String checkOut;
    private int nights;
    private double totalAmount;
    private String status;
    private String payment;

    public Booking(String bookingID, String guestID, String guestName, String phoneNo, Room room, String checkIn, String checkOut, int nights) {
        this.bookingID = bookingID;
        this.guestID = guestID;
        this.guestName = guestName;
        this.phoneNo = phoneNo;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.nights = nights;
        this.totalAmount = room.getPricePerNight() * nights;
        this.status = "Booked";
        this.payment = "Unpaid";
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
        this.totalAmount = room.getPricePerNight() * nights;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return " Booking ID: " + bookingID + " | Name: " + guestName + " (" + phoneNo + ") | Room: " + room.getRoomID() + " | " + checkIn + " - " + checkOut + " | Nights: " + nights + " | Total: RM" + totalAmount + " | " + status + " | " + payment;
    }
}

class Payment {
    private String paymentID;
    private String bookingID;
    private double amountPaid;
    private String paymentMethod;
    private String cardID; 
    private String status;

    public Payment(String paymentID, String bookingID, double amountPaid, String paymentMethod, String cardID, String status) {
        this.paymentID = paymentID;
        this.bookingID = bookingID;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.cardID =cardID;
        this.status = "Success";
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    @Override
    public String toString() {
        return paymentID + " | Booking ID: " + bookingID + " | Amount: RM" + amountPaid + " | Method: " + paymentMethod + " | Status: " + status;
    }
}

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
        rooms.add(new Room("R003", "Suite ", 300, "Available"));

        while (true) {
            System.out.println("\n==== Hotel Management System ====");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Modify Reservation");
            System.out.println("4. Make Payment");
            System.out.println("5. View Payments");
            System.out.println("6. Cancel Payments");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewRooms();
                case 2 -> bookRoom();
                case 3 -> modifyReservation();
                case 4 -> makePayment();
                case 5 -> viewPayments();
                case 6 -> cancelPayment();
                case 7 -> { System.out.println("Exiting...");
                return; 
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public static void viewRooms() {
        System.out.println("\nRoomID  |  Type  |  Price  |  Status ");
        System.out.println("=======================================");
        for (Room r : rooms) {
            System.out.println(r);
        }
    }

    public static void bookRoom() {
        System.out.print("Enter guest ID: ");
        String guestID = scanner.nextLine();
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNo = scanner.nextLine();
        viewRooms();
        String type;
        while (true) {
        System.out.print("\nEnter room type (Single/Double/Suite): ");
        type = scanner.nextLine().trim();
        if (type.equalsIgnoreCase("Single") || type.equalsIgnoreCase("Double") || type.equalsIgnoreCase("Suite")) {
            break;
        } else {
            System.out.println("Invalid room type. Please enter Single, Double, or Suite.");
        }
    }
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkIn = scanner.nextLine();
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOut = scanner.nextLine();
        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();
        scanner.nextLine();

        Room chosen = null;
        for (Room r : rooms) {
            if (r.getType().equalsIgnoreCase(type) && r.getStatus().equals("Available")) {
                chosen = r;
                r.setStatus("Booked");
                break;
            }
        }

        if (chosen == null) {
            System.out.println("No available " + type + " rooms.");
            return;
        }

        String bookingID = String.format("B%03d", bookingCounter++);
        Booking booking = new Booking(bookingID, guestID, guestName, phoneNo, chosen, checkIn, checkOut, nights);
        bookings.add(booking);

        System.out.println("Booking successful! " + booking);
    }

public static void makePayment() {
    System.out.print("Enter booking ID: ");
    String bookingID = scanner.nextLine();

    for (Booking b : bookings) {
        if (b.getBookingID().equals(bookingID) && b.getPayment().equals("Unpaid")) {
            System.out.println("Booking found: " + b);
            double amount = b.getTotalAmount();
            System.out.println("Amount to pay: RM" + amount);

            String method;
            while (true) {
                System.out.print("Enter payment method (Cash/Card/Ewallet): ");
                method = scanner.nextLine().trim();

                if (method.equalsIgnoreCase("Cash") || method.equalsIgnoreCase("Card") || method.equalsIgnoreCase("Ewallet")) {
                    break;
                } else {
                    System.out.println("Invalid payment method. Please enter Cash, Card, or Ewallet.");
                }
            }

            String cardID = null;
            if (method.equalsIgnoreCase("Card") || method.equalsIgnoreCase("Ewallet")) {
                while (true) {
                    System.out.print("Enter " + method + " ID: ");
                    cardID = scanner.nextLine().trim();
                    if (!cardID.isEmpty()) {
                        break;
                    } else {
                        System.out.println("Error! " + method + " ID cannot be empty.");
                    }
                }
            }

            String paymentID = String.format("P%03d", paymentCounter++);
            Payment payment = new Payment(paymentID, bookingID, amount, method, cardID, "Paid");
            payment.setCardID(cardID);
            payments.add(payment);

            b.setPayment("Paid");

            System.out.println("Payment successful: " + payment);
            return;
        }
    }
    System.out.println("Booking not found or already paid.");
}


    public static void modifyReservation() {
        System.out.print("Enter booking ID: ");
        String bookingID = scanner.nextLine();

        for (Booking b : bookings) {
            if (b.getBookingID().equals(bookingID) && b.getStatus().equals("Booked")) {
                System.out.println("Found booking: " + b);
                System.out.println("1. Change Dates");
                System.out.println("2. Change Nights");
                System.out.println("3. Cancel Reservation");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                int opt = scanner.nextInt(); scanner.nextLine();
                
                if (opt == 1) {
                    System.out.print("Enter new check-in date: ");
                    String newIn = scanner.nextLine();
                    System.out.print("Enter new check-out date: ");
                    String newOut = scanner.nextLine();
                    b.setCheckIn(newIn);
                    b.setCheckOut(newOut);
                    System.out.println("Reservation dates updated: " + b);
                }
                else if (opt == 2) {
                    System.out.print("Enter new number of nights: ");
                    int newNights = scanner.nextInt(); scanner.nextLine();
                    b.setNights(newNights);
                    System.out.println("Reservation nights updated: " + b);
                }
                else if (opt == 3) {
                    b.setStatus("Cancelled");
                    b.setPayment("Unpaid");
                    b.getRoom().setStatus("Available");
                    System.out.println("Reservation cancelled: " + b);
                }
                else if (opt == 4) {
                    return;
                }
            }
        }
        System.out.println("Booking not found.");
    }

    public static void viewPayments() {
        System.out.println("\nPayment Records:");
        for (Payment p : payments) {
            System.out.println(p);
        }
        if (payments.isEmpty()) {
            System.out.println("No payments recorded yet.");
        }
    }
    
    public static void cancelPayment() {
    System.out.print("Enter payment ID to cancel: ");
    String pid = scanner.nextLine();

    for (int i = 0; i < payments.size(); i++) {
        Payment p = payments.get(i);
        if (p.getPaymentID().equals(pid)) {
            for (Booking b : bookings) {
                if (b.getBookingID().equals(p.getBookingID())) {
                    b.setPayment("Unpaid");
                }
            }

            payments.remove(i);
            System.out.println("Payment " + pid + " cancelled. Booking set to Unpaid.");
            return;
        }
    }
    System.out.println("Payment not found.");
}
}
