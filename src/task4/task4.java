package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class task4 {
    public static void main(String[] args) throws IOException {
        int[] array = initArray("src/task4/numbers.txt");
        int number = checkNumber(array);
        equalizationArray(array, number);
    }

    public static int[] initArray(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        ArrayList<Integer> list = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            list.add(Integer.parseInt(line));
        }
        br.close();
        int[] arrInt = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrInt[i] = list.get(i);
        }
        return arrInt;
    }

    public static int checkNumber(int[] numbers) {
        int sum = 0;
        int number;
        int temp = 0;
        int result = 0;
        for (int i = 0; i < numbers.length; i++) {
            number = numbers[i];
            for (int j = 0; j < numbers.length; j++) {
                temp = temp + numbers[j] % number;
            }
            if (temp > sum) {
                sum = temp;
                result = number;
            }
        }
        return result;
    }

    public static void equalizationArray(int[] array, int number) {
        int step = 0;
        for (int i = 0; i < array.length; i++) {
            while (true) {
                if (array[i] == number) {
                    break;
                } else if (array[i] > number) {
                    array[i] -= 1;
                    step++;

                } else if (array[i] < number) {
                    array[i] += 1;
                    step++;
                }
            }

        }
        System.out.println("Минимальное количество шагов " + step);
    }
}
