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
