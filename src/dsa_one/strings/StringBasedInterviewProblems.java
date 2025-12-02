package dsa_one.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringBasedInterviewProblems {

    // Predict the output

    public static void main(String[] args) {
        // String str = "Hello";
        // makeChange(str);
        //System.out.println(str);

        List<Transaction> transactions = new ArrayList<>();

        Transaction dog = new Transaction();
        dog.name = "Bruno";
        dog.noOfFeets = 4;
        dog.age = 1;
        transactions.add(dog);

        Transaction cat = new Transaction();
        cat.name = "Suno";
        cat.noOfFeets = 5;
        cat.age = 2;
        transactions.add(cat);

//        for (Animal animal: animals){
//            System.out.println(animal);
//        }

        List<String> names1 = transactions.stream()
                .filter(txn -> txn.getNoOfFeets() == 5)
                .map(Transaction::getName)
                .toList();

        System.out.println(names1);

        List<Integer> noOff = transactions.stream().map(Transaction::getNoOfFeets).toList();
        System.out.println(noOff);


        List<String> names2 = new ArrayList<>();
        for (Transaction transaction : transactions) {
            names2.add(transaction.getName());
        }

        System.out.println(names2);

    }

    private static void makeChange(String str) {
        System.out.println(str);
        str = "My Name is Xded";
        System.out.println(str);


    }
}
