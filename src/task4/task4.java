package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Task4 {
    public static void main(String[] args) throws IOException {
        String[] cmdInput = cmdInput(args);
        if (cmdInput == null) {
            return;
        }
        int[] array = initArray(cmdInput[0]);
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
                if (number > numbers[j]) {
                    temp += number - numbers[j];
                } else if (number < numbers[j]) {
                    temp += numbers[j] - number;
                }
            }
            if (i == 0) {
                sum = temp;
                result = number;
            } else if (temp < sum) {
                sum = temp;
                result = number;
            }
            temp = 0;
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

    public static String[] cmdInput(String[] args) {
        if (args.length == 0) {
            System.out.println("Вы не ввели значение");
            return null;
        }
        return args;
    }
}
