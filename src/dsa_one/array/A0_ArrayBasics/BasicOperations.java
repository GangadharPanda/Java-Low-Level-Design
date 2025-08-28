package dsa_one.array.A0_ArrayBasics;

public class BasicOperations {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        reverseArray(arr, start, end);

        for (int val : arr) {
            System.out.println(val);
        }
    }

    //1. Given an array , reverse entire array
    public static void reverseArray(int[] arr, int start, int end) {
        int n = arr.length;


        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    static void swap(int[] arr, int start, int end) {
        int temp = arr[end];
        arr[end] = arr[start];
        arr[start] = temp;
    }
}
