import java.util.*;

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
    
    public static void bookRoom(Scanner scanner, ArrayList<Room> rooms, ArrayList<Booking> bookings, int bookingCounter) {
        System.out.print("Enter guest ID: ");
        String guestID = scanner.nextLine();
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNo = scanner.nextLine();
        Room.viewRooms(rooms);
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
        String checkIn, checkOut;
        while (true) {
            System.out.print("Enter check-in date (YYYY-MM-DD): ");
            checkIn = scanner.nextLine().trim();
            
            if (!checkIn.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Invalid format. Please use YYYY-MM-DD.");
            } else {
                System.out.print("Enter check-out date (YYYY-MM-DD): ");
                checkOut = scanner.nextLine().trim();
                if (!checkOut.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    System.out.println("Invalid format. Please use YYYY-MM-DD.");
                } else if (checkOut.compareTo(checkIn) <= 0) {
                    System.out.println("Check-out date must be after check-in date.");
                } else {
                    break;
                }
            }
        }

        int nights;
        while (true) {
            System.out.print("Enter number of nights: ");
            if (scanner.hasNextInt()) {
                nights = scanner.nextInt();
                scanner.nextLine();
                if (nights > 0) break;
            } else {
                scanner.nextLine();
            }
            System.out.println("Invalid number. Nights must be a positive integer.");
        }

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
    
    public static void modifyReservation(Scanner scanner, ArrayList<Booking> bookings, ArrayList<Room> rooms, ArrayList<Payment> payments) {
        System.out.print("Enter booking ID: ");
        String bookingID = scanner.nextLine();
        boolean found = false;

        for (Booking b : bookings) {
            if (b.getBookingID().equals(bookingID) && b.getStatus().equals("Booked")) {
                found = true; 
                System.out.println("Found booking: " + b);
                System.out.println("1. Change Dates");
                System.out.println("2. Change Nights");
                System.out.println("3. Cancel Reservation");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                int opt = scanner.nextInt(); scanner.nextLine();
                
                if (opt == 1) {
                    String newIn, newOut;
                    while (true) {
                        System.out.print("Enter new check-in date (YYYY-MM-DD): ");
                        newIn = scanner.nextLine().trim();
                        if (!newIn.matches("\\d{4}-\\d{2}-\\d{2}")) {
                            System.out.println("Invalid format. Please use YYYY-MM-DD.");
                        } else {
                            System.out.print("Enter new check-out date (YYYY-MM-DD): ");
                            newOut = scanner.nextLine().trim();
                            if (!newOut.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                System.out.println("Invalid format. Please use YYYY-MM-DD.");
                            } else if (newOut.compareTo(newIn) <= 0) {
                                System.out.println("Check-out must be after check-in.");
                            } else {
                                break;
                            }
                        }
                    }
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
        if (!found) {
            System.out.println("Booking not found.");
        }
    }
}
