package dsa_one.maths;

/*
Write a Java method or program that takes an integer n as input
and returns the total count of its positive factors

Constraints : 1 <= N <= 10^9
 */
public class FactorCounts {

    // 24 : 1, 2, 3, 4, 6, 8, 12, 24

    // A number which can divide the N, is a factor of N

    public static void main(String[] args) {
        int number = 16;
        int count = bruteForce(number);

        System.out.println(count);

        System.out.println(optimized(number));
    }

    private static int bruteForce(int number) {
        int count = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                count++;
            }
        }
        return count;
    }

    // Observations :

    // Smallest Factor = 1
    // Largest Factor = N

    //So Range [1, N]

    // Let's say there are two factors I and J
    // So, it should be I * J < = Number
    // J <= Number / I

    // Whenever we find a factor 'I',. we can find the another factor 'J' using the formula
    // J = Number / I
    // So In one iteration we have two factors I and N/I
    // We wil increase the count by two for each iteration

    // So we don't need to iterate thorough all the numbers from 1 -> N , instead we can simply Go till
    // I =Number / I => I * I <= N

    // NOTE : For perfect squares, in one scenario we get I and J equal , so if we increment the count by two times
    // It will count the same value two times
    // for 16 -> when I = 4 , J = 16/4 = 4 .
    // So I == J , In this case we need to increment the variable by 1 NOT two

    public static int optimized(int number) {
        int count = 0;
        for (int i = 1; i * i <= number; i++) {
            if (number % i == 0) {
                int secondFactor = number / i;
                if (i == secondFactor) {
                    count++;
                } else {
                    count += 2;
                }
            }
        }
        return count;
    }


}
