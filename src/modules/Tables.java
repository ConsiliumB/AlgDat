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
        int current_value;

        for (int current_pos = 1; current_pos < table.length; current_pos++) {
            current_value = table[current_pos];

            if (current_value > max_value) {
                max_pos = current_pos;
                max_value = current_value;
            } else if (current_value < min_value) {
                min_pos = current_pos;
                min_value = current_value;
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
        long result = number;
        for (int index = 1; index < number-1; index++) {
            result = result * (number - index);
        }
        return result;
    }

    public static int last_max_sentinel(int[] a)  // versjon 3 av maks-metoden
    {
        int sist = a.length - 1;       // siste posisjon i tabellen
        int m = 0;                     // indeks til største verdi
        int maksverdi = a[0];          // største verdi
        int temp = a[sist];            // tar vare på siste verdi
        a[sist] = 0x7fffffff;          // legger tallet 2147483647 sist

        for (int i = 0; ; i++)         // i starter med 0
            if (a[i] >= maksverdi)       // denne blir sann til slutt
            {
                if (i == sist)             // sjekker om vi er ferdige
                {
                    a[sist] = temp;          // legger siste verdi tilbake
                    return temp >= maksverdi ? sist : m;   // er siste størst?
                }
                else
                {
                    maksverdi = a[i];        // maksverdi oppdateres
                    m = i;                   // m oppdateres
                }
            }
    } // maks

    public static int first_max_sentinel(int[] a)  // versjon 3 av maks-metoden
    {
        int sist = a.length - 1;       // siste posisjon i tabellen
        int m = 0;                     // indeks til største verdi
        int maksverdi = a[0];          // største verdi
        int temp = a[sist];            // tar vare på siste verdi
        a[sist] = 0x7fffffff;          // legger tallet 2147483647 sist

        

        for (int i = 0; ; i++)         // i starter med 0
            if (a[i] > maksverdi)       // denne blir sann til slutt
            {
                if (i == sist)             // sjekker om vi er ferdige
                {
                    a[sist] = temp;          // legger siste verdi tilbake
                    return temp > maksverdi ? sist : m;   // er siste størst?
                }
                else
                {
                    maksverdi = a[i];        // maksverdi oppdateres
                    m = i;                   // m oppdateres
                }
            }
    } // maks


}

