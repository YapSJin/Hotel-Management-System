import java.util.*;

public class Payment {
    private String paymentID;
    private String bookingID;
    private double amountPaid;
    private String paymentMethod;
    private String cardID;
    private String status; // "Paid", "Unpaid", "Refunded"

    public Payment(String paymentID, String bookingID, double amountPaid, String paymentMethod, String cardID, String status) {
        this.paymentID = paymentID;
        this.bookingID = bookingID;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.cardID = cardID;
        this.status = status;
    }

    public String getPaymentID() { return paymentID; }
    public String getBookingID() { return bookingID; }
    public double getAmountPaid() { return amountPaid; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getCardID() { return cardID; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public static void makePayment(Scanner scanner, ArrayList<Booking> bookings, ArrayList<Payment> payments, int paymentCounter) {
        if (bookings == null || bookings.isEmpty()) {
            System.out.println("No bookings available for payment.");
            return;
        }
        System.out.print("Enter booking ID to pay: ");
        String bid = scanner.next();

        Booking target = null;
        for (Booking b : bookings) {
            if (b.getBookingID().equals(bid)) { target = b; break; }
        }
        if (target == null) { System.out.println("Booking not found."); return; }
        if ("Paid".equalsIgnoreCase(target.getPayment())) {
            System.out.println("This booking is already paid.");
            return;
        }

        double amount = target.getTotalAmount();
        System.out.println("Amount due: RM " + amount);
        System.out.print("Enter payment method (Card/Cash): ");
        String method = scanner.next();

        String card = "-";
        if ("Card".equalsIgnoreCase(method)) {
            System.out.print("Enter card ID: ");
            card = scanner.next();
        }

        String pid = "P" + paymentCounter;
        payments.add(new Payment(pid, bid, amount, method, card, "Paid"));
        target.setPayment("Paid");
        System.out.println("Payment successful. Payment ID: " + pid);
    }

    public static void viewPayments(ArrayList<Payment> payments) {
        if (payments == null || payments.isEmpty()) {
            System.out.println("No payments recorded.");
            return;
        }
        System.out.println("\nPayments:");
        for (Payment p : payments) {
            System.out.println(p.paymentID + " | Booking: " + p.bookingID + " | RM" + p.amountPaid + " | " + p.paymentMethod + " | " + p.status);
        }
    }
}
