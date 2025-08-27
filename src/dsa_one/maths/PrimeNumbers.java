package dsa_one.maths;

public class PrimeNumbers {
    public static void main(String[] args) {
        System.out.println(isPrime(11));
    }

    private static boolean isPrime(int number) {
        int count = FactorCounts.optimized(number);
        return count == 2;
    }

}
