package modules;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Created by Consilium on 29.08.2016.
 */
public class Table {
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

    public static int last_max_sentinel_no_typelimit(int[] a)  // versjon 3 av maks-metoden
    {
        int sist = a.length - 1;       // siste posisjon i tabellen
        int m = 0;                     // indeks til største verdi
        int maksverdi = a[0];          // største verdi
        int temp = a[sist];            // tar vare på siste verdi
        a[sist] = maksverdi;          // legger tallet 2147483647 sist

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
                    a[sist] = maksverdi;
                    m = i;                   // m oppdateres
                }
            }
    } // maks

    public static double harmonic(int n){
        double result = 0;
        for (int i = 1; i <= n; i++) {
            result = result + 1/(double)i;
        }
        return result;
    }

    public static double euler(int n){
        return harmonic(n)-Math.log(n);
    }

    public static void randPerm(int[] a)  // stokker om a
    {
        Random r = new Random();     // en randomgenerator

        for (int k = a.length - 1; k > 0; k--)
        {
            int i = r.nextInt(k + 1);  // tilfeldig tall fra [0,k]
            bytt(a,k,i);
        }
    }

    public static void bytt(int[] a, int i, int j)
    {
        int temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    public static int kostnader(int[] a)  // legges i class Program
    {
        int m = 0;
        for (int i = 1; i < a.length; i++) {}  // en tom blokk
        return m;
    }

    public static int maks(int[] a) {
        return maks(a, 0, a.length);
    }

    public static int maks(int[] a, int fra, int til)
    {
        if (fra < 0 || til > a.length || fra >= til)
        {
            throw new IllegalArgumentException("Illegalt intervall!");
        }

        int m = fra;              // indeks til største verdi i a[fra:til>
        int maksverdi = a[fra];   // største verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++)
        {
            if (a[i] > maksverdi)
            {
                m = i;                // indeks til største verdi oppdateres
                maksverdi = a[m];     // største verdi oppdateres
            }
        }

        return m;  // posisjonen til største verdi i a[fra:til>
    }

    public static int[] nextMaks(int[] a) {
        if (a.length < 2) throw new NoSuchElementException("For få verdier");

        int m = maks(a);

        int nm = (m == 0) ? 1 : 0;
        int nestmaksverdi = a[nm];

        for (int i = 0; i < a.length; i++) {
            if (i != m && a[i] > nestmaksverdi) {
                nm = i;
                nestmaksverdi = a[i];
            }
        }
        return new int[]{m,nm};
    }

    public static int[] two_largest(int[] table, int from, int to)
    {
        if (from < 0 || to > table.length || from >= to)
        {
            throw new IllegalArgumentException("Illegal interval!");
        }

        int secondHighestPosition = table[from] >= table[from+1] ? from+1 : from;
        int maxPos = (secondHighestPosition+1) & 0x00000001;
        int maxValue = table[maxPos];

        for (int i = from + 2; i < to; i++)
        {
            int j = table[i];
            if (j > table[secondHighestPosition])
            {
                if (j > maxValue) {
                    secondHighestPosition = maxPos;
                    maxPos = i;                // indeks til største verdi oppdateres
                    maxValue = table[maxPos];     // største verdi oppdateres
                } else {
                    secondHighestPosition = i;
                }
            }
        }

        return new int[] {maxPos,secondHighestPosition};  // posisjonen til største verdi i a[fra:til>
    }


    public static int[] randPerm_slow(int n)  // virker, men er svært ineffektiv
    {
        Random r = new Random();      // en randomgenerator
        int[] a = new int[n];         // en tabell med plass til n tall

        for (int i = 0; i < n; )      // vi skal legge inn n tall
        {
            int k = r.nextInt(n) + 1;   // trekker et nytt tall k

            int j = 0;
            for ( ; j < i; j++)         // leter i intervallet a[0:i>
            {
                if (a[j] == k) break;     // stopper hvis vi har k fra før
            }
            if (i == j) a[i++] = k;     // legger inn k og øker i
        }
        return a;                     // tabellen returneres
    }

    public static int[] randPerm_faster(int n)  // virker, men er ineffektiv
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];            // en tabell med plass til n tall
        boolean[] har = new boolean[n];  // en boolsk tabell

        for (int i = 0; i < n; )         // vi skal legge inn n tall
        {
            int k = r.nextInt(n);          // trekker en indeks k
            if (har[k] == false)           // sjekker
            {
                har[k] = true;               // oppdaterer den boolske tabellen
                a[i++] = k + 1;              // legger inn k + 1 i a
            }
        }
        return a;                        // tabellen returneres
    }

    public static int[] randPerm(int n)  // en effektiv versjon
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];            // en tabell med plass til n tall

        Arrays.setAll(a, i -> i + 1);    // legger inn tallene 1, 2, . , n

        for (int k = n - 1; k > 0; k--)  // løkke som går n - 1 ganger
        {
            int i = r.nextInt(k+1);        // en tilfeldig tall fra 0 til k
            bytt(a,k,i);                   // bytter om
        }

        return a;                        // permutasjonen returneres
    }
}
