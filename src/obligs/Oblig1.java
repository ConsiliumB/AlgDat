package obligs;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by Consilium on 29.08.2016.
 */
public class Oblig1 {

    private Oblig1() {}

    public static int maks(int[] a) {
        if (a.length < 1) throw new NoSuchElementException("Ugyldig input: Tom tabell");

        for (int i = 0; i < a.length-1; i++) {
            if (a[i] > a[i + 1]) {
                int temp = a[i + 1];
                a[i + 1] = a[i];
                a[i] = temp;
            }
        }

        return a[a.length-1];
    }

    public static int ombyttinger(int[] a){
        if (a.length < 1) throw new NoSuchElementException("Ugyldig input: Tom tabell");

        int ombyttinger = 0;

        for (int i = 0; i < a.length-1; i++) {
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

    public static int antallUlikeUsortert(int[] a) {
        return (int)Arrays.stream(a).distinct().count();
    }

    public static int antallUlikeUSortert(int[] a){
        for (int i = 0; i < a.length; i++) {
            int b = a[i];
            for (int j = i; j > 0; j--) {
                if (a[i] == a[j]) {

                }
            }
        }

        return 0;
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

        for (int i = 0; i < a.length; i++) {
            if (Math.abs(a[i] % 2) == 1) {
                continue;
            }

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

        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                offset = i;
                break;
            }
        }

        Arrays.sort(a, 0,  offset);
        Arrays.sort(a, offset, a.length);
    }
    
    public static void rotasjon(char[] a){
        if (a.length > 1) {
            char temp = a[a.length - 1];
            System.arraycopy(a, 0, a, 1, a.length - 1);
            a[0] = temp;
        }
    }
    
    public static void rotasjon(char[] a, int k){
        if (a.length > 1) {
            k = k % a.length;
            k = (a.length + k) % a.length;
            int otherk = a.length - k;
            char[] b = Arrays.copyOf(a, a.length);
            System.arraycopy(b, otherk, a, 0, k);
            System.arraycopy(b, 0, a, k, otherk);
        }
    }
}
