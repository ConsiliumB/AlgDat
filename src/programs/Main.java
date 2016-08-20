package programs;

import modules.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("... Initiating AlgDat ...\n");

        System.out.println(Tables.factorial(0));
        //tables_factorial();
        //tables_minmax();
        //tables_minmax_lazy();
        //tables_max();
        //tables_first_max_sentinel();
        //tables_last_max_sentinel();
    }

    public static void tables_factorial() {
        long tid = System.nanoTime();
        long result = Tables.factorial(5);
        tid = System.nanoTime() - tid;
        System.out.println("Factorial calculation took "+tid+"ns.");
        System.out.println("Found that 5! = "+result+".\n");
    }

    public static void tables_minmax() {
        int[] table = {4, 6, 8, 2, 3, 1, 5, 9};
        long tid = System.nanoTime();
        int[] result = Tables.minmax(table);
        tid = System.nanoTime() - tid;
        System.out.println("Optimized Min & Max position calculation took "+tid+"ns.");
        System.out.println("Given " + Arrays.toString(table) + " found " + Arrays.toString(result) + ".\n");
    }

    public static void tables_minmax_lazy() {
        int[] table = {4, 6, 8, 2, 3, 1, 5, 9};
        long tid = System.nanoTime();
        int[] result = Tables.minmax_lazy(table);
        tid = System.nanoTime() - tid;
        System.out.println("Lazy Min & Max position calculation took "+tid+"ns.");
        System.out.println("Given " + Arrays.toString(table) + " found " + Arrays.toString(result) + ".\n");
    }

    public static void tables_max() {
        int[] table = {4, 6, 9, 2, 3, 1, 5, 9};
        long tid = System.nanoTime();
        int result = Tables.max(table);
        tid = System.nanoTime() - tid;
        System.out.println("Max calculation took "+tid+"ns.");
        System.out.println("Given " + Arrays.toString(table) + " found " + result + ".\n");
    }

    public static void tables_first_max_sentinel() {
        int[] table = {4, 6, 9, 2, 3, 1, 5, 9};
        long tid = System.nanoTime();
        int result = Tables.first_max_sentinel(table);
        tid = System.nanoTime() - tid;
        System.out.println("Max calculation returning first value using Sentinel took "+tid+"ns.");
        System.out.println("Given " + Arrays.toString(table) + " found " + result + ".\n");
    }

    public static void tables_last_max_sentinel() {
        int[] table = {4, 6, 9, 2, 3, 1, 5, 9};
        long tid = System.nanoTime();
        int result = Tables.last_max_sentinel(table);
        tid = System.nanoTime() - tid;
        System.out.println("Max calculation returning last value using Sentinel took "+tid+"ns.");
        System.out.println("Given " + Arrays.toString(table) + " found " + result + ".\n");
    }
}