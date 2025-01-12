package task1;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        System.out.print("Enter the interval length: ");
        int m = sc.nextInt() - 1;
        /*
        init array
         */
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        int current = 0;
        int count = 0;
        boolean flag = true;
        String path = "";
        System.out.print("Path: ");
        do {
            if (current == arr.length) {
                current = 0;
            }
            if (arr[current] == 1 && count == 0) {
                if (flag == false) {
                    break;
                }
                flag = false;
            }
            if (count == m) {
                count = 0;
            } else {
                if (count == 0) {
                    path += arr[current];
                }
                current++;
                count++;
            }
        } while (true);
        System.out.println();
        System.out.println(path);
    }
}
