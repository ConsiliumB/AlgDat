package modules;

/**
 * Created by Consilium on 19.08.2016.
 */

import java.util.NoSuchElementException;

public final class Tables {
    public static int max(int[] table) {
        if (table.length < 1) throw new NoSuchElementException("Empty table");

        int m = 0;

        for (int i = 1; i < table.length; i++) {
            if (table[i] > table[m]) m = i;
        }

        return m;
    }

    public static int max_optimized(int[] table) {
        if (table.length < 1) throw new NoSuchElementException("Empty table");

        int max_pos = 0;
        int max_val = table[0];

        for (int index = 1; index < table.length; index++) {
            if (table[index] > max_val) {
                max_pos = index;
                max_val = table[max_pos];
            }
        }

        return max_pos;
    }

    public static int min(int[] table) {
        if (table.length < 1) throw new NoSuchElementException("Empty table");

        int min_pos = 0;

        for (int current_pos = 1; current_pos < table.length; current_pos++) {
            if (table[current_pos] < table[min_pos]) min_pos = current_pos;
        }

        return min_pos;
    }

    public static int min_optimized(int[] table) {
        if (table.length < 1) throw new NoSuchElementException("Empty table");

        int min_pos = 0;
        int min_val = table[0];

        for (int index = 1; index < table.length; index++) {
            if (table[index] < min_val) {
                min_pos = index;
                min_val = table[min_pos];
            }
        }

        return min_pos;
    }

    public static int[] minmax(int[] table) {
        if (table.length < 1) throw new NoSuchElementException("Empty table");

        int max_pos = 0;
        int min_pos = 0;
        int max_value = table[0];
        int min_value = table[0];

        for (int current_pos = 1; current_pos < table.length; current_pos++) {
            int current_value = table[current_pos];

            if (current_value > max_value) {
                max_pos = current_pos;
                max_value = table[max_pos];
            } else if (current_value < min_value) {
                min_pos = current_pos;
                min_value = table[min_pos];
            }
        }

        int[] result = {min_pos, max_pos};
        return result;
    }

    public static final int[] minmax_lazy(int[] table) {
        int[] result = {min_optimized(table), max_optimized(table)};
        return result;
    }

    public static long factorial(int number) {
        int result = number;
        for (int index = 1; index < number-1; index++) {
            result = result * (number - index);
        }
        return result;
    }
}

