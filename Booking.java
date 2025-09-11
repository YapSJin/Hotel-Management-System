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
