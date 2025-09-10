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
