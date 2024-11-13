import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args){
        // Подзадача 1
        out.print("1.Введите количество строк:");
        int n = in.nextInt(); // Вводим количество строк
        int [][] mass = new int[n][]; // Создаем двумерный массив
        int sum = 0; // переменная для подсчета суммы всех элементов (нужна в подзадаче 3)
        int cnt = 0; // переменная для подсчета количества элементов (нужна в подзадаче 3)


        for (int i = 0; i < mass.length; i++){ // Цикл для создания строк и заполнения массива элементами
            out.print("Введите количество элементов строки:");
            int m = in.nextInt(); // Вводим размерность строки
            cnt += m; // Считаем количество элементов (подзадача 3)
            mass[i] = new int[m]; // Создаем строку
            for (int j = 0; j < m; j++){ // Цикл для заполнения массива элементами
                out.print("Введите элемент строки:");
                mass[i][j] = in.nextInt(); // Вводим элементы
                sum += mass[i][j]; // Считаем сумму элементов (подзадача 3)
            }
        }
        out.println();



        out.println("Введенный массив:"); // Вывод введенного массива
        for (int i = 0; i < mass.length; i++){
            for (int j = 0; j < mass[i].length; j++){
                out.print(mass[i][j] + " ");
            }
            out.println();
        }
        out.println();


        // Подзадача 2
        out.print("Введите А:");
        int a = in.nextInt(); // Вводим переменную A, для обозначения интервала [A, B]
        out.print("Введите В:");
        int b = in.nextInt();// Вводим переменную B, для обозначения интервала [A, B]
        int [] temp_mass = new int [n]; // Создаем временный массив для сортировки основного массива
        int count = 0; // переменная для подсчета количества элементов строки, находящихся в заданном интервале


        for (int i = 0; i < mass.length; i++){
            for (int j = 0; j < mass[i].length; j++){
                if (mass[i][j] >= a && mass[i][j] <= b) { // проверяем находится ли элемент в заданном интервале
                    count++; // если да, то увеличиваем счетчик
                }
            }
            temp_mass[i] = count; // заносим количество во временный массив
            count = 0; // обнуляем счетчик для подсчета в следующей строке
        }


        for (int i = 0; i < temp_mass.length - 1; i++){ // сортировка массива пузырьковой сортировкой
            for (int j = 0; j < temp_mass.length - 1; j++){
                if (temp_mass[j] < temp_mass[j + 1]){ // сортируем временный массив и если переставляем какие-то элементы
                    int temp = temp_mass[j];//во временном массиве, значит нужно переставить соответствующие строки в основном массиве,
                    temp_mass[j] = temp_mass[j + 1];
                    temp_mass[j + 1] = temp;
                    int [] temp2 = mass[j];
                    mass[j] = mass[j + 1];
                    mass[j + 1] = temp2;
                }
                int sum1 = 0; // переменные для подсчета суммы элементов не входящих в интервал
                int sum2 = 0;
                if (temp_mass[j] == temp_mass[j + 1]){ // проверяем если элементы равны, то считаем суммы элементов за пределами интервала для обеих строк
                    for (int k = 0; k < mass[j].length; k++) {
                        if (mass[j][k] < a || mass[j][k] > b) // если элементы не входят в интервал, то увеличиваем сумму на значение элемента
                            sum1 += mass[j][k];
                    }
                    for (int k = 0; k < mass[j + 1].length; k ++){ // аналогично, для второй строки
                        if (mass[j + 1][k] < a || mass[j + 1][k] > b)
                            sum2 += mass[j + 1][k];
                    }

                    if (sum1 < sum2){ // сортируем строки по сумме элементов, не входящих в промежуток
                        int temp = temp_mass[j];
                        temp_mass[j] = temp_mass[j + 1];
                        temp_mass[j + 1] = temp;
                        int [] temp2 = mass[j];
                        mass[j] = mass[j + 1];
                        mass[j + 1] = temp2;
                    }
                }
            }
        }
        out.println();



        out.println("2.Отсортированный массив:"); // выводим отсортированный массив
        for (int i = 0; i < mass.length; i++){
            for (int j = 0; j < mass[i].length; j++){
                out.print(mass[i][j] + " ");
            }
            out.println();
        }
        out.println();

        // Подзадача 3
        double mid = (double)sum / cnt; // вычисляем среднее арифметическое всех элементов
        int col = 0; // переменная для подсчета количества элементов в строке, равных среднему элементу
        int max = 0; // переменная для определения максимального количества нужных нам элементов в строке
        int num = -1; // переменная для определения строки, содержащей максимальное количество элементов, равных среднему
        for (int i = 0; i < mass.length; i++){
            for (int j = 0; j < mass[i].length; j++){
                if (mass[i][j] == mid)
                    col++; // если элемент равен среднему значению, увеличиваем количество на 1
            }
            if (col > max) { // если количество больше ранее найденного максиму, присваиваем максимуму новое значение
                max = col;
                num = i; // запоминаем номер строки
            }
            col = 0;
        }
        if (max != 0) // если в массиве содержится элемент, равный среднему значению, выводим номер строки
            out.printf("3.Номер строки с наибольшим числом элементов, равных среднему значению всех элементов массива: %d\n", num + 1);
        else // если в массиве отсутствует элемент, равный среднему значению, выводим сообщение об этом
            out.print("3.В массиве отсутствуют строки с элементами, равными среднему значению всех элементов массива.\n");
        out.printf("Среднее значение всех элементов: %.3f\n", mid); // выводим среднее значение
        out.println();


        //Подзадача 4
        out.println("4. Вывод массива в виде пирамиды:"); // вывод массива в виде пирамиды
        int len = 1;
        int len_2 = 0;
        for (int i = 0; i < mass.length; i++){
            for (int j = 0; j < mass[i].length; j++){
                if (len > 0){
                    out.print(mass[i][j] + " ");
                    len--;
                    len_2++;
                }
                else {
                    out.println();
                    out.print(mass[i][j] + " ");
                    len = len_2;
                    len_2 = 1;
                }
            }

        }
        out.println();


        //Подзадача 5
        out.println();
        out.println("5.Массив, в котором каждый элемент заменен на Log по основанию 2 от модуля элемента:");
        double [][] mass_log = new double[n][]; // создаем вещественный массив
        for (int i = 0; i < mass.length; i++){
            for (int j = 0; j < mass[i].length; j++) {
                mass_log[i] = new double[mass[i].length]; // создаем строки вещественного массива
                mass_log[i][j] = Math.abs(mass[i][j]); // меняем все элементы на их модуль
                if (mass_log[i][j] == 0) // если элемент равен 0, то логарифма не существует
                    out.print("!не существует! ");
                else{
                    mass_log[i][j] = (Math.log(mass_log[i][j]) / Math.log(2)); // заменяем каждый элемент на его логарифм
                    out.printf("%.2f ", mass_log[i][j]); // выводим элемент
                }
            }
            out.println();
        }
    }
}