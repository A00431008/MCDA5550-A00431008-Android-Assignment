package com.example.mcda5550_a00431008_android_assignment;

public class HotelListData {

    String hotelName;
    String price;
    String availability;

    public HotelListData(String hotel_name, String price, String availability) {
        this.hotelName = hotel_name;
        this.price = price;
        this.availability = availability;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotel_name(String hotel_name) {
        this.hotelName = hotel_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
