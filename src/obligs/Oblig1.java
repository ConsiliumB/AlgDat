package obligs;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * John André Seem - s305366 - Informasjonsteknologi
 * Sondre Haldar-Iversen - s305344 - Informasjonsteknologi
 * Even Tsai Hansen - s167844 - Dataingeniør
 * Lise Estelle Prat - s305345 - Informasjonsteknologi
 * Benjamin Bryne - s305338 - Informasjonsteknologi
 */
public class Oblig1 {

    private Oblig1() {
    }

    //Oppgave 1
    //I en tabell med n verdier vil det blir gjort n-1 sammenligninger
    public static int maks(int[] a) {
        if (a.length < 1) throw new NoSuchElementException("Ugyldig input: Tom tabell");

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                bytt(a, i, i + 1);
            }
        }

        return a[a.length - 1];
    }

    //Det vil bli gjort flest ombyttinger i tabellen når det høyeste tallet ligger først i tabellen.
    //Det vil bli gjort færrest ombyttinger i tabellen når den er sortert stigende.
    //I gjennomsnitt vil dette bli gjort Summen av (n-1)/n. Kan også regnes med summen av 1-(1/n)
    //Vi kan på grunnlag av dette se at denne metoden er vesentlig dårligere enn de tidligere maks metodene
    public static int ombyttinger(int[] a) {
        if (a.length < 1) throw new NoSuchElementException("Ugyldig input: Tom tabell");

        int ombyttinger = 0;

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                bytt(a, i, i + 1);
                ombyttinger++;
            }
        }

        return ombyttinger;
    }

    //Oppgave 2
    public static int antallUlikeSortert(int[] a) {
        if (a.length == 0) {
            return 0;
        }

        int forrige = Integer.MIN_VALUE;
        int antallUlike = 0;

        for (int b : a) {
            if (b < forrige) {
                throw new IllegalStateException("Feil: Gitt tabell er ikke sortert");
            } else if (b > forrige) {
                antallUlike++;
                forrige = b;
            }
        }

        return antallUlike;
    }

    //oppgave 3
    public static int antallUlikeUsortert(int[] a){
        if (a.length == 0) {
            return 0;
        }

        int antallUlike = 1;

        for (int i = 1; i < a.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (a[i] == a[j]) {
                    break;
                }
                if (j == 0) {
                    antallUlike++;
                }
            }
        }
        return antallUlike;
    }

    //Oppgave 4
    public static void delsortering(int[] a) {
        int par = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 != 0) {
                bytt(a, i, par);
                par++;
            }
        }

        Arrays.sort(a, 0, par);
        Arrays.sort(a, par, a.length);
    }

    //Oppgave 5
    public static void rotasjon(char[] a) {
        if (a.length > 1) {
            char temp = a[a.length - 1];
            System.arraycopy(a, 0, a, 1, a.length - 1);
            a[0] = temp;
        }
    }

    //Oppgave 6
    public static void rotasjon(char[] a, int k) {
        if (a.length > 1) {
            k = ((k % a.length) + a.length) % a.length;
            char[] b = Arrays.copyOf(a, a.length);
            System.arraycopy(b, a.length - k, a, 0, k);
            System.arraycopy(b, 0, a, k, a.length - k);
        }
    }

    //Oppgave 7a
    public static String flett(String s, String t) {
        StringBuilder sb = new StringBuilder();

        int min = Math.min(s.length(), t.length());
        for (int i = 0; i < min; i++) {
            sb.append(s.charAt(i));
            sb.append(t.charAt(i));
        }

        if (s.length() < t.length()) {
            sb.append(t.substring(s.length(), t.length()));
        } else if (s.length() > t.length()) {
            sb.append(s.substring(t.length(), s.length()));
        }

        return sb.toString();
    }

    //Oppgave 7b
    public static String flett(String... s) {
        StringBuilder sb = new StringBuilder();

        int max = 0;

        for (String a : s) {
            if (a.length() > max) {
                max = a.length();
            }
        }

        for (int i = 0; i < max; i++) {
            for (String a : s) {
                if (a.length() > i) {
                    sb.append(a.charAt(i));
                }
            }
        }

        return sb.toString();
    }

    //Oppgave 8
    public static int[] indekssortering(int[] a) {
        int[] b = Arrays.copyOf(a, a.length);
        int[] index = new int[a.length];

        for (int i = 0; i < b.length; i++) {
            int x = min(b);
            index[i] = x;
            b[x] = Integer.MAX_VALUE;
        }

        return index;
    }

    //Oppgave 9
    public static int[] tredjeMin(int[] a)
    {
        if(a.length < 3)
        {
            throw new NoSuchElementException("Tabellen er for kort");
        }
        int[] b = indekssortering(Arrays.copyOfRange(a,0,3));

        int indexMinst = b[0];
        int indexNestMinst = b[1];
        int indexTredjeMinst = b[2];

        int minstVerdi = a[indexMinst];
        int nestMinstVerdi = a[indexNestMinst];
        int tredjeMinstVerdi = a[indexTredjeMinst];

        for (int i = 3; i < a.length; i++) {
            if(a[i]<tredjeMinstVerdi)
            {
                if(a[i]<nestMinstVerdi)
                {
                    if(a[i]<minstVerdi)
                    {
                        tredjeMinstVerdi = nestMinstVerdi;
                        nestMinstVerdi = minstVerdi;
                        minstVerdi = a[i];
                        b[2] = b[1];
                        b[1] = b[0];
                        b[0] = i;
                    }
                    else
                    {
                        tredjeMinstVerdi = nestMinstVerdi;
                        nestMinstVerdi = a[i];
                        b[2] = b[1];
                        b[1] = i;
                    }
                }
                else
                {
                    tredjeMinstVerdi = a[i];
                    b[2] = i;
                }
            }
        }

        return b;
    }

    //Oppgave 10
    public static boolean inneholdt(String a, String b) {
        int[][] c = new int[29][2];
        int charInt;

        boolean ok = true;

        for (int i = 0; i < b.length(); i++) {
            charInt = b.charAt(i) - 'A';

            if (charInt >= b.length()) {
                if (b.charAt(i) == 'Æ') {
                    c[26][0] = c[26][0] + 1;
                } else if (b.charAt(i) == 'Ø') {
                    c[27][0] = c[27][0] + 1;
                } else if (b.charAt(i) == 'Å') {
                    c[28][0] = c[28][0] + 1;
                }
            } else {
                c[charInt][0] = c[charInt][0] + 1;
            }
        }

        for (int i = 0; i < a.length(); i++) {
            charInt = a.charAt(i) - 'A';

            if (charInt >= a.length()) {
                if (a.charAt(i) == 'Æ') {
                    c[26][1] = c[26][1] + 1;
                } else if (a.charAt(i) == 'Ø') {
                    c[27][1] = c[27][1] + 1;
                } else if (a.charAt(i) == 'Å') {
                    c[28][1] = c[28][1] + 1;
                }
            } else {
                c[charInt][1] = c[charInt][1] + 1;
            }
        }

        for (int[] d : c) {
            if (d[0] < d[1]) {
                ok = false;
                break;
            }
        }

        return ok;
    }

    //Hjelpemetoder
    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int min(int[] a, int fra, int til) {
        if (fra < 0 || til > a.length || fra >= til)
            throw new IllegalArgumentException("Illegalt intervall!");

        int m = fra;
        int minverdi = a[fra];

        for (int i = fra + 1; i < til; i++)
            if (a[i] < minverdi) {
                m = i;
                minverdi = a[m];
            }

        return m;
    }

    public static int min(int[] a)
    {
        return min(a, 0, a.length);
    }
}