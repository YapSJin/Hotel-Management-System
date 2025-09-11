import java.util.*;

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
    
    public static void makePayment(Scanner scanner, ArrayList<Booking> bookings, ArrayList<Payment> payments, int paymentCounter) {
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
    
       public static void viewPayments(ArrayList<Payment> payments) {
        System.out.println("\nPayment Records:");
        for (Payment p : payments) {
            System.out.println(p);
        }
        if (payments.isEmpty()) {
            System.out.println("No payments recorded yet.");
       }
    }
       
       public static void cancelPayment(Scanner scanner, ArrayList<Payment> payments, ArrayList<Booking> bookings) {
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
