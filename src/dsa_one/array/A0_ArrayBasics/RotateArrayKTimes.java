package dsa_one.array.A0_ArrayBasics;

public class RotateArrayKTimes {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 19;
        rotate(arr, k);
    }

    private static void rotate(int[] arr, int k) {
        // What Does rotate means , it means if we assume it as a circular
        // so it ,
        // If I rotate it 1 time
        // arr = [10, 1, 2, 3, 4, 5, 6, 7, 8, 9];

        // If I rotate it 2 time
        // arr = [9, 10, 1, 2, 3, 4, 5, 6, 7, 8];

        // If I rotate it 3 time
        // arr = [8, 9, 10, 1, 2, 3, 4, 5, 6, 7];

        // Observation 1: last K elements are at the start of the array
        // Observation 2: first N-K elements are at the last of the array

        // for example: [8, 9, 10] are at the start of the array
        // [1, 2, 3, 4, 5, 6, 7] are at the end of the array

        // How can we get 8, 9, 10 at the start of the array ?
        // Swap it ?

        // swap the whole array
        // [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]

        // swap first k elements

        // [8, 9, 10][7, 6, 5, 4, 3, 2, 1]
        // Swap last N-K elements

        // Final Array
        // [8, 9, 10, 1, 2, 3, 4, 5, 6, 7]
        int n = arr.length;
        int start = 0;
        int end = n - 1;

        k = k % n; // this is needed to handle the cases when k >n-1
        BasicOperations.reverseArray(arr, start, end);
        BasicOperations.reverseArray(arr, 0, k - 1);
        BasicOperations.reverseArray(arr, k, end);

        for (int val : arr) {
            System.out.println(val);
        }
    }

    // Time Complexity : O(N + N + N) = O(N)
    // Space complexity : O(1)
}
