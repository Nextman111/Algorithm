package lesson1;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        int n = 4;
//        System.out.println(SummPosityve(n));
//        System.out.println(SummPosityve2(n));

        System.out.println(SimpleNumbers(n));

    }

    /* Алгоритм считающий сумму от 1 до N */
    public static int SummPosityve(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public static int SummPosityve2(int n) {
        return n * (n + 1) / 2;
    }

    /* Алгоритм поиска простых чисел (Делятся только на 1 и на себя) */
    public static ArrayList<Integer> SimpleNumbers(int n){
        ArrayList<Integer> result = new ArrayList<Integer>();
        int counter = 1;
        boolean flag = true;
        while (result.size() <= n)  {
            for (int i = 2; counter < n; i++){
                if (counter % i == 0 );
                flag = false;
                break;
            }

            if (flag == false){
                result.add(counter);
                flag = true;
            }
            counter ++;
        }
        return  result;
    }


}
