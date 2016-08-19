package programs;

import modules.*;
import java.util.Arrays;

public class TableTesting {
    public static void main(String[] args) {
        System.out.println("Initiating AlgDat ...\n");


        run_tables_factorial();
        run_tables_minmax();
        run_tables_minmax_lazy();
    }

    public static void run_tables_factorial() {
        long tid = System.nanoTime();
        long result = Tables.factorial(5);
        tid = System.nanoTime() - tid;
        System.out.println("Factorial calculation took "+tid+"ns.");
        System.out.println("Found that 5! = "+result+".\n");
    }

    public static void run_tables_minmax() {
        int[] table = {4, 6, 8, 2, 3, 1, 5, 9};
        long tid = System.nanoTime();
        int[] result = Tables.minmax(table);
        tid = System.nanoTime() - tid;
        System.out.println("Optimized Min & Max position calculation took "+tid+"ns.");
        System.out.println("Given " + Arrays.toString(table) + " found " + Arrays.toString(result) + ".\n");
    }

    public static void run_tables_minmax_lazy() {
        int[] table = {4, 6, 8, 2, 3, 1, 5, 9};
        long tid = System.nanoTime();
        int[] result = Tables.minmax_lazy(table);
        tid = System.nanoTime() - tid;
        System.out.println("Lazy Min & Max position calculation took "+tid+"ns.");
        System.out.println("Given " + Arrays.toString(table) + " found " + Arrays.toString(result) + ".\n");
    }
}