package com.example.mim.dhakacity;

public class Star {

    private String id;
    private  String hotelName;
    private String hotelAdress;
    private String hotelContactNo;


    public Star(String id,String hotelName,String hotelAdress,String hotelContactNo) {
        this.id = id;
        this.hotelName = hotelName;
        this.hotelAdress = hotelAdress;
        this.hotelContactNo= hotelContactNo;

    }

    public Star() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAdress() {
        return hotelAdress;
    }

    public void setHotelAdress(String hotelAdress) {
        this.hotelAdress = hotelAdress;
    }

    public String getHotelContactNo() {
        return hotelContactNo;
    }

    public void setHotelContactNo(String hotelContactNo) {
        this.hotelContactNo = hotelContactNo;
    }
}
