package org.example;

import java.util.ArrayList;
import java.util.List;

public class HotelService {
    List<Hotel> hotels = new ArrayList<>();

    public HotelService(){
        hotels.add(new Hotel(2000,2,HotelType.THREE_STAR));
        hotels.add(new Hotel(1000,1,HotelType.THREE_STAR));
        hotels.add(new Hotel(10000,4,HotelType.FOUR_STAR));
        hotels.add(new Hotel(20000,5,HotelType.FIVE_STAR));
        hotels.add(new Hotel(5000,3,HotelType.FOUR_STAR));
        hotels.add(new Hotel(2500,4,HotelType.THREE_STAR));
    }

//    public boolean isHotelLessThan(int price, Hotel hotel){
//        return price < hotel.getPricePerNight();
//    }
//    public List<Hotel> filterHotelIsLessThan(int price){
//        List<Hotel> filterHotels = new ArrayList<>();
//        for(Hotel hotel : hotels){
//            if(isHotelLessThan(price, hotel)){
//                filterHotels.add(hotel);
//            }
//        }
//        return  filterHotels;
//    }

    public List<Hotel> filterHotel(FilteringCondition filteringCondition){
        List<Hotel> filterHotels = new ArrayList<>();
        for(Hotel hotel : hotels){
            if(filteringCondition.test(hotel)){
                filterHotels.add(hotel);
            }
        }
        return  filterHotels;
    }
}
