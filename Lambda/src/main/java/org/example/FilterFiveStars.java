package org.example;

public class FilterFiveStars implements  FilteringCondition{

    @Override
    public boolean test(Hotel hotel) {
        return hotel.getHotelType()==HotelType.FIVE_STAR;
    }
}
