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

        int m = 0;
        int maxval = table[0];

        for (int i = 1; i < table.length; i++) {
            if (table[i] > maxval) {
                m = i;
                maxval = table[m];
            }
        }

        return m;
    }

    public static int min(int[] table) {
        if (table.length < 1) throw new NoSuchElementException("Empty table");

        int min_pos = 0;

        for (int current_pos = 1; current_pos < table.length; current_pos++) {
            if (table[current_pos] < table[min_pos]) min_pos = current_pos;
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

            if (current_value < max_value) {
                max_pos = current_pos;
                max_value = table[max_pos];
            } else if (current_value > min_value) {
                min_pos = current_pos;
                min_value = table[min_pos];
            }
        }

        int[] result = {min_pos, max_pos};
        return result;
    }
}

