package programs;

import static obligs.Oblig1.*;
import modules.Tabell;
import modules.Table;

import java.util.Arrays;

/**
 * Created by Consilium on 29.08.2016.
 */
public class Oblig1Main {
    public static void main(String[] args) {
        String str = "str";
        int a = str.charAt(1) - '0';
        int b = str.charAt(1);
        System.out.println(a);
        System.out.println(b);
    }
    
    public static void oppgave1(){
        //int[] a = {10,3,4,5,6,7,1,2,8,9};
        //System.out.println(maks(a));

        int avg = 0;
        int[] b = Tabell.randPerm(1000);
        for (int i = 0; i < 100; i++) {
            Tabell.randPerm(b);
            //System.out.println(Arrays.toString(b));
            int omb = ombyttinger(b);
            System.out.println(omb);
            avg += omb;
        }

        System.out.println(avg/100);
    }

    public static void oppgave2(){
        int[] a = {2,2,3,4,4,4,5};
        System.out.println(antallUlikeSortert(a));
    }
    
    public static void oppgave3(){
        int[] a = {5, 3, 7, 4, 3, 5, 7, 8, 6, 7};

        System.out.println(antallUlikeUsortert(a));
    }
    
    public static void oppgave4(){
        //int[] a = {6, 10, 9, 4, 1, 3, 8, 5, 2, 7};
        //delsortering(a);
        //System.out.println(Arrays.toString(a));
        //int[] b = {1, 3, 5, 7, 8, 9, 10, 11, 12, 13};
        //int[] b = {1,2,3,4,5,6};
        //int[] b = {12,4,5,6,8,10,2,14,3,16};
        int[] b = {-4, -1, 3, 0, 2, -3, -2, 4, 1};
        delsortering(b);
        System.out.println(Arrays.toString(b));
        // Utskrift: [1, 3, 5, 7, 9, 2, 4, 6, 8, 10]
    }

    public static void testBinarySearch() {
        int[] a = {0, 1, 2, 4, 5, 6, 7, 9, 10};
        System.out.println(binarySearch(a, 8, 0, a.length));

    }
    
    public static void oppgave5() {
        char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        rotasjon(a);
        System.out.println(Arrays.toString(a));
    }

    public static void oppgave7a() {
        String a = flett("ABC","DEFGH");
        String b = flett("IJKLMN","OPQ");
        String c = flett("","AB");
        System.out.println(a + " " + b + " " + c);
    }

    public static void oppgave7b() {
        String a = flett("AM ","L","GEDS","ORATKRR","","R TRTE","IO","TGAUU");
        System.out.println(a);
    }

    public static void oppgave8() {
        long tid = System.currentTimeMillis();
        int[] a = {6,10,16,11,7,12,3,9,8,5};
        int[] index = indekssortering(a);

        System.out.println(System.currentTimeMillis()-tid);
        System.out.println(Arrays.toString(index));
    }

    public static void oppgave9() {
        int[] a = {7, 10, 4, 3, 5, 1, 6, 8, 2, 9};
        int[] b = {1,2,3,4,5,6};

        System.out.println(Arrays.toString(tredjeMin(b)));
    }
}
