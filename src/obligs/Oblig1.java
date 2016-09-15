package obligs;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by Consilium on 29.08.2016.
 */
public class Oblig1 {

    private Oblig1() {
    }

    public static int maks(int[] a) {
        if (a.length < 1) throw new NoSuchElementException("Ugyldig input: Tom tabell");

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                int temp = a[i + 1];
                a[i + 1] = a[i];
                a[i] = temp;
            }
        }

        return a[a.length - 1];
    }

    public static int ombyttinger(int[] a) {
        if (a.length < 1) throw new NoSuchElementException("Ugyldig input: Tom tabell");

        int ombyttinger = 0;

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                int temp = a[i + 1];
                a[i + 1] = a[i];
                a[i] = temp;
                ombyttinger++;
            }
        }

        return ombyttinger;
    }

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

/*    public static int antallUlikeUsortert(int[] a) {
        return (int)Arrays.stream(a).distinct().count();
    }*/

    public static int antallUlikeUsortert(int[] a) {
        int antallUlike = 0;
        boolean ok = true;
        for (int i = 0; i < a.length; i++) {
            int b = a[i];
            for (int j = i + 1; j < a.length; j++) {
                if (b == a[j]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                antallUlike++;
            }
            ok = true;
        }

        return antallUlike;
    }

    public static int binarySearch(int[] a, int value, int fromIndex, int toIndex) {
        int middle = (fromIndex + toIndex) / 2;

        int nextMiddle;
        while (true) {
            if (a[middle] > value) {
                toIndex = middle;
                nextMiddle = (fromIndex + middle) / 2;
            } else {
                fromIndex = middle;
                nextMiddle = (middle + toIndex) / 2;
            }

            if ((middle + 1 == nextMiddle) || (middle - 1 == nextMiddle) || middle == nextMiddle) {
                return nextMiddle;
            } else {
                middle = nextMiddle;
            }
        }
    }

    public static void delsortering(int[] a) {
        int temp;
        int offset = 0;

        for (int i = 0; i < a.length - offset; i++) {
            if (a[i] % 2 == 0) {
                for (int j = a.length - 1 - offset; j > i; j--) {
                    if (Math.abs(a[j] % 2) == 1) {
                        temp = a[j];
                        a[j] = a[i];
                        a[i] = temp;
                        offset = a.length - j;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                offset = i;
                break;
            }
        }

        Arrays.sort(a, 0, offset);
        Arrays.sort(a, offset, a.length);
    }

    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void delsortering_v2(int[] a) {
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

    public static void rotasjon(char[] a) {
        if (a.length > 1) {
            char temp = a[a.length - 1];
            System.arraycopy(a, 0, a, 1, a.length - 1);
            a[0] = temp;
        }
    }

    public static void rotasjon(char[] a, int k) {
        if (a.length > 1) {
            k = ((k % a.length) + a.length) % a.length;
            char[] b = Arrays.copyOf(a, a.length);
            System.arraycopy(b, a.length - k, a, 0, k);
            System.arraycopy(b, 0, a, k, a.length - k);
        }
    }

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

    public static int[] indekssortering(int[] a) {
        int[] b = Arrays.copyOf(a, a.length);
        int[] c = new int[a.length];
        Arrays.setAll(c, i -> i);



        for (int i = 0; i < b.length; i++) {
            int x = min(b, i, b.length);
            bytt(b, x, i);
            bytt(c, x, i);
        }

        int[] index = new int[a.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = c[i];
        }

        return index;
    }

    public static int min(int[] a, int fra, int til) {
        if (fra < 0 || til > a.length || fra >= til)
            throw new IllegalArgumentException("Illegalt intervall!");

        int m = fra;             // indeks til minste verdi i a[fra:til>
        int minverdi = a[fra];   // minste verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++)
            if (a[i] < minverdi) {
                m = i;               // indeks til minste verdi oppdateres
                minverdi = a[m];     // minste verdi oppdateres
            }

        return m;  // posisjonen til minste verdi i a[fra:til>
    }

    public static int min(int[] a)  // bruker hele tabellen
    {
        return min(a, 0, a.length);     // kaller metoden over
    }

    public static int[] tredjeMinJuks(int[] a) {
        if (a.length < 3) throw new NoSuchElementException("Error");

        int[] b = Arrays.copyOfRange(indekssortering(a), 0, 3);

        return b;
    }

    public static int[] tredjeMin(int[] a) {
        if (a.length < 3) throw new NoSuchElementException("Error");

        int[] temp = indekssortering(Arrays.copyOfRange(a, 0, 3));

        int minPos = temp[0];
        int medPos = temp[1];
        int maxPos = temp[2];
        int minVal = a[minPos];
        int medVal = a[medPos];
        int maxVal = a[maxPos];

        for (int i = 3; i < a.length; i++) {
            if (i != minPos && i != medPos && i != maxPos) {
                int curVal = a[i];
                if (curVal < maxVal) {
                    if (curVal < medVal) {
                        if (curVal < minVal) {
                            maxVal = medVal;
                            maxPos = medPos;
                            medVal = minVal;
                            medPos = minPos;
                            minVal = curVal;
                            minPos = i;
                        } else {
                            maxVal = medVal;
                            maxPos = medPos;
                            medVal = curVal;
                            medPos = i;
                        }
                    } else {
                        maxVal = curVal;
                        maxPos = i;
                    }
                }
            }
        }

        return new int[]{minPos, medPos, maxPos};
    }

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
}