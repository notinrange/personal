package org.example;

public class Hotel {
    public int pricePerNight;
    public int rating;
    public HotelType hotelType;

    public Hotel(int pricePerNight,int rating, HotelType hotelType){
        this.pricePerNight = pricePerNight;
        this.rating = rating;
        this.hotelType = hotelType;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public int getRating() {
        return rating;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setHotelType(HotelType hotelType) {
        this.hotelType = hotelType;
    }

    @Override
    public String toString() {
        return "Hotel [pricePerNight : " + pricePerNight + ", ratings : " + rating + ", HotelType : "+hotelType ;
    }
}
