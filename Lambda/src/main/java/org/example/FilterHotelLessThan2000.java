package org.example;

public class FilterHotelLessThan2000 implements FilteringCondition{
    @Override
    public boolean test(Hotel hotel) {
        return hotel.getPricePerNight() <= 2000;
    }
}
