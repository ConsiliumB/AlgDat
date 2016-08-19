package programs;

import modules.*;
import java.util.Arrays;

public class TableTesting {
    public static void main(String[] args) {
        System.out.println("Initiating AlgDat ...");

        int[] table = {4, 6, 8, 2, 3, 1, 5, 9};
        long tid = System.nanoTime();
        System.out.println(Arrays.toString(Tables.minmax(table)));
        System.out.println(System.nanoTime() - tid);
    }
}