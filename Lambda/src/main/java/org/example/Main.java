package org.example;

import java.util.ArrayList;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {


    public List<Hotel> testLambda(){
        HotelService hotelService = new HotelService();
        final int PRICE = 2000;
        FilteringCondition lambdaExp = hotel -> {
            return hotel.getPricePerNight() <= PRICE;
        };

        // instead of FilteringCondition u can use predicate

//        Predicate<Hotel> lambdaExp = hotel -> {
//            return hotel.getPricePerNight() <= PRICE;
//        };

        // u cannot modify value passed in lambdaExp, and it must be final or effectively final
        // lambda function do value capture of variable passed in lambda expression
        return hotelService.filterHotel(lambdaExp);
    }
    static void main() {


        HotelService hotelService = new HotelService();
        List<Hotel> hotels = hotelService.filterHotel(new FilterHotelLessThan2000());
//        System.out.println(hotels);

        List<Hotel> hotels5Stars = hotelService.filterHotel(new FilterFiveStars());
//        System.out.println(hotels5Stars);

        // Anonymous Inner Class
        List<Hotel> hotels1 = hotelService.filterHotel(new FilteringCondition() {
            @Override
            public boolean test(Hotel hotel) {
                return false;
            }
        });
//        System.out.println(hotels1);

        List<Hotel> hotelsByLambda = hotelService.filterHotel((Hotel hotel)-> hotel.getPricePerNight() <= 2000);
//        System.out.println(hotelsByLambda);


        // What if we have one than one interface ?
        // if you are using lambda expression do not create more than 1 abstract method in interface and
        // those type of interface called Functional Interface.

        //there is 2 abstract method in Comparator.class but second method it is overridden by object class

        List<Integer> lst = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        // sorting in descending order
        lst.sort((Integer a, Integer b) -> b - a);
//        Collections.sort(lst,(Integer a, Integer b) -> b - a);

//        System.out.println(lst);




        // Rules of Lambda Expression & Type Checking
        // infer type parameter  Collections.sort(lst,(a, b) -> b - a);
        // if you are declaring parameter in lambda expression final then you have declare type of parameter

//        Predicate<Hotel> lambdaExp = hotel -> hotel.getPricePerNight() <=2000;
        FilteringCondition lambdaExp = hotel -> hotel.getPricePerNight() <=2000;

        List<Hotel> hotelsByLambdaExp = hotelService.filterHotel(lambdaExp);


        Predicate<Integer> divisibleBy2 =  no -> no%2 == 0;
        Predicate<Integer> divisibleBy3 =  no -> no%3 == 0;

        Predicate<Integer> divisibleBy6 = divisibleBy2.and(divisibleBy3);

//        System.out.println(divisibleBy6.test(6));


        Consumer<Integer> consumer = a -> System.out.println(a);
//        lst.forEach(consumer);

        Supplier<Double> supplier = () -> Math.random();
//        System.out.println(supplier.get());

        Function<String,Integer> steToLenMap = string->string.length();
        Integer len =  steToLenMap.apply("Rahul");
//        System.out.println(len);

        Function<Integer,Integer> square = a -> a*a;
        Function<Integer,Integer> add1 = a -> a+1;
//        System.out.println(square.andThen(add1).apply(5));


//        Method References
//        List.of(1,2,3,4).forEach(x-> System.out.println(x));
//        List.of(1,2,3,4).forEach(System.out::println);


//        (arg) -> ClassName.staticMethod(arg)
//                ClassName::staticMethod

//        (arg) -> arg.someMethod()
//                ClassName::someMethod

//        (arg1,arg2,...) -> arg1.someMethod(arg2)
//                ClassName::someMethod


        List<String> str = new ArrayList<>(List.of("abr", "jncfd", "urenfr"));
//        str.sort((s1,s2)->s1.compareToIgnoreCase(s2));
        str.sort(String::compareToIgnoreCase);

        System.out.println(str);

        // in place of this ()->new ArrayList<>();
//         write ArrayList::new

    }
}
