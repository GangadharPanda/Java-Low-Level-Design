package collections.set;

import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetExample {

    public static void main(String[] args) {
        SortedSet<String> fruits = new TreeSet<>();

        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Cherry");

        System.out.println("All fruits: " + fruits); // Output: [Apple, Banana, Cherry, Orange]
        
        //How did you sort this?
        System.out.println(fruits.comparator());
        
        
        //What is the First fruit in the List?
        String firstFruit = fruits.first();
        System.out.println("First fruit: " + firstFruit); // Output: Apple
        
        //What is the Last fruit in the List?
        String lastFruit = fruits.last();
        System.out.println("Last fruit: " + lastFruit); // Output: Orange

        // Get fruits whose head is "Orange"
        SortedSet<String> belowOrange = fruits.headSet("Orange");
        System.out.println("Fruits below Orange: " + belowOrange); // Output: [Apple, Banana, Cherry]
        
        // Get fruits whose tail is "Orange"
        SortedSet<String> aboveOrange = fruits.tailSet("Orange");
        System.out.println("Fruits above or equal to Orange: " + aboveOrange); // Output: [Orange]

        // Get fruits between "Apple" and "Cherry" (excluding Cherry)
        SortedSet<String> betweenFruits = fruits.subSet("Apple", "Cherry");
        System.out.println("Fruits between Apple and Cherry (excluding Cherry): " + betweenFruits); // Output: [Apple, Banana]

        boolean hasMango = fruits.contains("Mango");
        System.out.println("Does the set contain Mango? " + hasMango); // Output: false

        // Remove a fruit
        fruits.remove("Banana");
        System.out.println("Updated fruits: " + fruits); // Output: [Apple, Cherry, Orange]
    }
}
