package programs;

import static modules.Table.*;
import modules.Tabell;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("... Initiating AlgDat ...\n");


    }

    public static void task_1189() {
        int n = 34000;
        long tid = System.currentTimeMillis();
        int[] a = randPerm(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid);
    }

    public static void task_1185() {
        int n = 34000;
        long tid = System.currentTimeMillis();
        int[] a = randPerm_faster(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid);
    }

    public static void task_1182() {
        int n = 34000;
        long tid = System.currentTimeMillis();
        int[] a = randPerm_slow(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid);
    }

    public static void task_1184() {
        for (int i = 0; i < 50; i++) {
            System.out.println(Arrays.toString(randPerm_faster(10)));
        }
    }

    public static void task_1181() {
        System.out.println(Arrays.toString(randPerm(10)));
    }

    public static void task_1165() {
        int[] ant = {10, 100, 1000, 10000, 100000, 1000000, 10000000};
        for (int digits : ant) {
            System.out.println(Math.log(digits) - 0.423);
        }
    }
    
    public static void example_1_1_10(){
        // main-metoden i class Program skal nå inneholde:
        int n = 100_000, antall = 2_000; // tabellstørrelse og gjentagelser
        long tid = 0;                    // for tidsmåling
        int a[] = randPerm(n);           // en permutasjon av 1, . .  n

        tid = System.currentTimeMillis();    // leser av klokken
        for (int i = 0; i < antall; i++) kostnader(a);
        tid = System.currentTimeMillis() - tid;    // medgått tid
        System.out.println("Faste kostnader: " + tid + " millisek");

        //tid = System.currentTimeMillis();    // leser av klokken
        //for (int i = 0; i < antall; i++) maks1(a);  // Programkode 1.1.2
        //tid = System.currentTimeMillis() - tid;     // medgått tid
        //System.out.println("Maks1-metoden: " + tid + " millisek");

        //tid = System.currentTimeMillis();    // leser av klokken
        //for (int i = 0; i < antall; i++) maks2(a);  // Programkode 1.1.4
        //tid = System.currentTimeMillis() - tid;     // medgått tid
        //System.out.println("Maks2-metoden: " + tid + " millisek");

        //tid = System.currentTimeMillis();    // leser av klokken
        //for (int i = 0; i < antall; i++) maks3(a);  // Programkode 1.1.5
        //tid = System.currentTimeMillis() - tid;     // medgått tid
        //System.out.println("Maks3-metoden: " + tid + " millisek");

    }

    public static void task_1161() {
        int[][] numbers = {
                {1, 2, 3, 4, 5}, {1, 2, 3, 5, 4}, {1, 2, 4, 3, 5}, {1, 2, 4, 5, 3}, {1, 2, 5, 3, 4}, {1, 2, 5, 4, 3}, {1, 3, 2, 4, 5}, {1, 3, 2, 5, 4}, {1, 3, 4, 2, 5}, {1, 3, 4, 5, 2}, {1, 3, 5, 2, 4}, {1, 3, 5, 4, 2}, {1, 4, 2, 3, 5}, {1, 4, 2, 5, 3}, {1, 4, 3, 2, 5}, {1, 4, 3, 5, 2}, {1, 4, 5, 2, 3}, {1, 4, 5, 3, 2}, {1, 5, 2, 3, 4}, {1, 5, 2, 4, 3}, {1, 5, 3, 2, 4}, {1, 5, 3, 4, 2}, {1, 5, 4, 2, 3}, {1, 5, 4, 3, 2}, {2, 1, 3, 4, 5}, {2, 1, 3, 5, 4}, {2, 1, 4, 3, 5}, {2, 1, 4, 5, 3}, {2, 1, 5, 3, 4}, {2, 1, 5, 4, 3}, {2, 3, 1, 4, 5}, {2, 3, 1, 5, 4}, {2, 3, 4, 1, 5}, {2, 3, 4, 5, 1}, {2, 3, 5, 1, 4}, {2, 3, 5, 4, 1}, {2, 4, 1, 3, 5}, {2, 4, 1, 5, 3}, {2, 4, 3, 1, 5}, {2, 4, 3, 5, 1}, {2, 4, 5, 1, 3}, {2, 4, 5, 3, 1}, {2, 5, 1, 3, 4}, {2, 5, 1, 4, 3}, {2, 5, 3, 1, 4}, {2, 5, 3, 4, 1}, {2, 5, 4, 1, 3}, {2, 5, 4, 3, 1}, {3, 1, 2, 4, 5}, {3, 1, 2, 5, 4}, {3, 1, 4, 2, 5}, {3, 1, 4, 5, 2}, {3, 1, 5, 2, 4}, {3, 1, 5, 4, 2}, {3, 2, 1, 4, 5}, {3, 2, 1, 5, 4}, {3, 2, 4, 1, 5}, {3, 2, 4, 5, 1}, {3, 2, 5, 1, 4}, {3, 2, 5, 4, 1}, {3, 4, 1, 2, 5}, {3, 4, 1, 5, 2}, {3, 4, 2, 1, 5}, {3, 4, 2, 5, 1}, {3, 4, 5, 1, 2}, {3, 4, 5, 2, 1}, {3, 5, 1, 2, 4}, {3, 5, 1, 4, 2}, {3, 5, 2, 1, 4}, {3, 5, 2, 4, 1}, {3, 5, 4, 1, 2}, {3, 5, 4, 2, 1}, {4, 1, 2, 3, 5}, {4, 1, 2, 5, 3}, {4, 1, 3, 2, 5}, {4, 1, 3, 5, 2}, {4, 1, 5, 2, 3}, {4, 1, 5, 3, 2}, {4, 2, 1, 3, 5}, {4, 2, 1, 5, 3}, {4, 2, 3, 1, 5}, {4, 2, 3, 5, 1}, {4, 2, 5, 1, 3}, {4, 2, 5, 3, 1}, {4, 3, 1, 2, 5}, {4, 3, 1, 5, 2}, {4, 3, 2, 1, 5}, {4, 3, 2, 5, 1}, {4, 3, 5, 1, 2}, {4, 3, 5, 2, 1}, {4, 5, 1, 2, 3}, {4, 5, 1, 3, 2}, {4, 5, 2, 1, 3}, {4, 5, 2, 3, 1}, {4, 5, 3, 1, 2}, {4, 5, 3, 2, 1}, {5, 1, 2, 3, 4}, {5, 1, 2, 4, 3}, {5, 1, 3, 2, 4}, {5, 1, 3, 4, 2}, {5, 1, 4, 2, 3}, {5, 1, 4, 3, 2}, {5, 2, 1, 3, 4}, {5, 2, 1, 4, 3}, {5, 2, 3, 1, 4}, {5, 2, 3, 4, 1}, {5, 2, 4, 1, 3}, {5, 2, 4, 3, 1}, {5, 3, 1, 2, 4}, {5, 3, 1, 4, 2}, {5, 3, 2, 1, 4}, {5, 3, 2, 4, 1}, {5, 3, 4, 1, 2}, {5, 3, 4, 2, 1}, {5, 4, 1, 2, 3}, {5, 4, 1, 3, 2}, {5, 4, 2, 1, 3}, {5, 4, 2, 3, 1}, {5, 4, 3, 1, 2}, {5, 4, 3, 2, 1}
        };

        int count = 0;
        for (int[] tuple : numbers) {
            int max = tuple[0];
            for (int num : tuple) {
                if (num > max) {
                    max = num;
                    count++;
                }
            }
        }

        System.out.println("Counted " + count + " increases in 120 numbers.");
        System.out.println("1/2+1/3+1/4+1/5 = 154/120.");
    }

    public static void table_two_largest() {
        int[] a = {11, 9, 3, 10, 8, 7, 2};
        System.out.println(Arrays.toString(two_largest(a, 0, a.length)));
    }


    public static void task_1164() {
        for (int i = 0; i < 10000; i++) {
            if (euler(i) < 0.578) {
                System.out.println(i + " " + euler(i));
                break;
            }
        }
    }

    public static void tables_factorial() {
        long tid = System.nanoTime();
        long result = factorial(5);
        tid = System.nanoTime() - tid;
        System.out.println("Factorial calculation took " + tid + "ns.");
        System.out.println("Found that 5! = " + result + ".\n");
    }

    public static void tables_minmax() {
        int[] table = {4, 6, 8, 2, 3, 1, 5, 9};
        long tid = System.nanoTime();
        int[] result = minmax(table);
        tid = System.nanoTime() - tid;
        System.out.println("Optimized Min & Max position calculation took " + tid + "ns.");
        System.out.println("Given " + Arrays.toString(table) + " found " + Arrays.toString(result) + ".\n");
    }

    public static void tables_minmax_lazy() {
        int[] table = {4, 6, 8, 2, 3, 1, 5, 9};
        long tid = System.nanoTime();
        int[] result = minmax_lazy(table);
        tid = System.nanoTime() - tid;
        System.out.println("Lazy Min & Max position calculation took " + tid + "ns.");
        System.out.println("Given " + Arrays.toString(table) + " found " + Arrays.toString(result) + ".\n");
    }

    public static void tables_max() {
        int[] table = {4, 6, 9, 2, 3, 1, 5, 9};
        long tid = System.nanoTime();
        int result = max(table);
        tid = System.nanoTime() - tid;
        System.out.println("Max calculation took " + tid + "ns.");
        System.out.println("Given " + Arrays.toString(table) + " found " + result + ".\n");
    }

    public static void tables_first_max_sentinel() {
        int[] table = {4, 6, 9, 2, 3, 1, 5, 9};
        long tid = System.nanoTime();
        int result = first_max_sentinel(table);
        tid = System.nanoTime() - tid;
        System.out.println("Max calculation returning first value using Sentinel took " + tid + "ns.");
        System.out.println("Given " + Arrays.toString(table) + " found " + result + ".\n");
    }

    public static void tables_last_max_sentinel() {
        int[] table = {4, 6, 9, 2, 3, 1, 5, 9};
        long tid = System.nanoTime();
        int result = last_max_sentinel(table);
        tid = System.nanoTime() - tid;
        System.out.println("Max calculation returning last value using Sentinel took " + tid + "ns.");
        System.out.println("Given " + Arrays.toString(table) + " found " + result + ".\n");
    }
}