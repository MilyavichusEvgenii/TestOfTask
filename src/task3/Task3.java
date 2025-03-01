package task3;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    public static void main(String[] args) throws IOException {
        String[] cmdInput = cmdInput(args);
        if (cmdInput == null) {
            return;
        }
        ArrayList<String> test = fileReader(cmdInput[0]);
        ArrayList<String> values = fileReader(cmdInput[1]);
        parserJson(test, values, cmdInput[2]);
    }

    public static ArrayList<String> fileReader(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line + "\r\n");
        }
        br.close();
        return lines;
    }

    public static void parserJson(ArrayList<String> test, ArrayList<String> values, String resultPath)
            throws IOException {
        Pattern idTest = Pattern.compile("(\"id\":)\\s+\\d+,");
        Pattern idValue = Pattern.compile("(\"id\":)\\s+\\d+,");
        Matcher mId;
        Matcher mValID;
        String temp;
        for (int i = 0; i < test.size(); i++) {
            mId = idTest.matcher(test.get(i));
            if (mId.find()) { //Находим id в массиве test
                for (int j = 0; j < values.size(); j++) {
                    mValID = idValue.matcher(values.get(j));
                    if (mValID.find()) { //Находим id в массиве values
                        // сравниваем id test и value
                        if (test.get(i).replace(" ", "")
                                .equals(values.get(j).replace(" ", ""))) {
                            temp = parseExchangeElement(test.get(i + 2), values.get(j + 1));
                            if (test.get(i + 2).contains(",")) { // ищем запятую в строке test
                                test.set(i + 2, temp.concat(",")); // меняем value в test
                            } else {
                                test.set(i + 2, temp);
                            }
                        }
                    }
                }
            }
        }
        fileWriter(resultPath, test);
    }

    public static void fileWriter(String filePath, ArrayList<String> lines) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for (String line : lines) {
            fw.write(line.replaceAll("(\\r\\n)", "").concat("\n"));
            fw.flush();
        }
        fw.close();
    }
    /*
    Строки преобразуются в массив, заменяется элемент lineTest на элемент lineValue
     */
    public static String parseExchangeElement(String test, String values) {
        String[] lineTest = test.split(" ");
        String[] lineValues = values.split(" ");
        lineTest[lineTest.length - 1] = " " +lineValues[lineValues.length - 1];
        StringBuilder battery = new StringBuilder();
        for (int i = 0; i < lineTest.length; i++) {
            if (lineTest[i].isEmpty()) {
                battery.append(" ");
            } else {
                battery.append(lineTest[i]);
            }

        }
        return battery.toString();
    }

    public static String[] cmdInput(String[] args) {
        if (args.length == 0) {
            System.out.println("Вы не ввели значение");
            return null;
        } else if (args.length == 1) {
            System.out.println("Вы ввели:" + args[0] + ". Введите два значения");
            return null;
        } else if (args.length == 2) {
            System.out.println("Вы ввели:" + args[0] + " , " + args[1] + ". Введите три значения");
            return null;
        }
        return args;
    }
}
