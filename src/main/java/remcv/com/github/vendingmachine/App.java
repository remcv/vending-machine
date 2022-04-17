package remcv.com.github.vendingmachine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class App {

    public static void main( String[] args ) {

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);

        System.out.println("numbers: " + numbers);

        Collection<Integer> numbersCopy = new ArrayList<>(numbers);
        System.out.println("numbersCopy before: " + numbersCopy);

        numbers.clear();
        System.out.println("numbersCopy after: " + numbersCopy);
    }
}
