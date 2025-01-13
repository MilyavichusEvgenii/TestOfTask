package task2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2 {
    public static void main(String[] args) throws IOException {
        String[] input = cmdInput(args);
        if (input == null) {
            return;
        }
        StringBuilder circle = fileReader(input[0]);
        StringBuilder points = fileReader(input[1]);
        resultCheckPoints(circle, points);

    }
    /*
    Проверка точки относительно окружности
     */
    public static void circleCheck(double pointX, double pointY, double rad, double inX, double inY) {
        double expression = Math.pow((inX - pointX), 2) + Math.pow((inY - pointY), 2);
        boolean onCircle = expression == Math.pow(rad, 2);
        boolean inCircle = expression < Math.pow(rad, 2);
        boolean outCircle = expression > Math.pow(rad, 2);
        if (onCircle) {
            System.out.println("0 - точка лежит на окружности\r\n");
        } else if (inCircle) {
            System.out.println("1 - точка внутри\r\n");
        } else if (outCircle) {
            System.out.println("2 - точка снаружи\r\n");
        }
    }
    /*
    Чтение файлов
     */
    public static StringBuilder fileReader(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\r\n");
        }
        br.close();
        return sb;
    }
    /*
    главный контроллер приложения
     */
    public static void resultCheckPoints(StringBuilder circle, StringBuilder points) {
        String [] arrCircle = circle.toString().split("\r\n");
        String [] arrPoints = points.toString().split("\r\n");
        if (!checkSizeArrPoints(arrPoints)) {
            System.out.println("Превышено предельное количество координат точек, координат точек в файле более 100");
            return;
        } else if (circle.isEmpty() || points.isEmpty()) {
            System.out.println("Загрузите файлы с данными");
            return;
        }
        String [] pointCentre = arrCircle[0].split(" ");
        double pointXCircle = Double.parseDouble(pointCentre[0]);
        double pointYCircle = Double.parseDouble(pointCentre[1]);
        double radius = Double.parseDouble(arrCircle[1]);
        String [] tempPoints;
        double pointX;
        double pointY;
        for (int i = 0; i < arrPoints.length; i++) {
            tempPoints = arrPoints[i].split(" ");
            pointX = Double.parseDouble(tempPoints[0]);
            pointY = Double.parseDouble(tempPoints[1]);
            circleCheck(pointXCircle, pointYCircle, radius, pointX, pointY);
        }
    }
    /*
    Проверка массива на предельный размер
     */
    public static boolean checkSizeArrPoints(String [] arrPoints) {
        return arrPoints.length <= 100 ? true : false;
    }

    public static String[] cmdInput(String[] args) {
        if (args.length == 0) {
            System.out.println("Вы не ввели значение");
            return null;
        } else if (args.length == 1) {
            System.out.println("Вы ввели:" + args[0] + ". Введите два значения");
            return null;
        }
        return args;
    }
}
