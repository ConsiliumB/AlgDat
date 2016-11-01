package programs;

import modules.ObligSBinTre;

import java.util.Comparator;

/**
 * Created by Consilium on 25.10.2016.
 */
public class Oblig3Main {
    public static void main(String[] args) {
        oppgave8b();
    }
    
    public static void oppgave0(){
        ObligSBinTre<String> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall());  // Utskrift: 0
    }
    
    public static void oppgave1(){
        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);
        System.out.println(tre.antall());  // Utskrift: 10
    }

    public static void oppgave2() {
        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);

        System.out.println(tre.antall());      // Utskrift: 10
        System.out.println(tre.antall(5));     // Utskrift: 0
        System.out.println(tre.antall(4));     // Utskrift: 3
        System.out.println(tre.antall(7));     // Utskrift: 2
        System.out.println(tre.antall(10));    // Utskrift: 1
    }

    public static void oppgave3() {
        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
//        for (int verdi : a) tre.leggInn(verdi);

        System.out.println(tre);  // [1, 2, 4, 4, 4, 6, 7, 7, 8, 9, 10]
    }

    public static void oppgave3_1() {
        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);

        ObligSBinTre.Node<Integer> p = tre.rot;
        //40,70,20,90,41,100,80,71,42,60,10
        while (p != null) {
            System.out.println(p.toString());
            p = tre.nesteInorden(p);
        }
    }

    public static void oppgave4() {
//        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        int[] a = {40,70,20,90,41,100,80,71,42,60,10};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);

        System.out.println(tre.omvendtString());  // [10, 9, 8, 7, 7, 6, 4, 4, 4, 2, 1]
    }

    public static void oppgave4_1() {
//        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        int[] a = {40,70,20,90,41,100,80,71,42,60,10};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);

        System.out.println(tre.omvendtString());  // [1, 2, 4, 4, 4, 6, 7, 7, 8, 9, 10]
    }

    public static void oppgave5_1() {
        int[] a = {4,70,20,90,4,100,80,71,42,60,10};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);

        tre.fjernAlle(4);

        System.out.println(tre.omvendtString());  // [1, 2, 4, 4, 4, 6, 7, 7, 8, 9, 10]
    }

    public static void oppgave5() {
        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);

        System.out.println(tre.fjernAlle(4));  // 3
        tre.fjernAlle(7); tre.fjern(8);

        System.out.println(tre.antall());  // 5

        System.out.println(tre + " " + tre.omvendtString());
        // [1, 2, 6, 9, 10] [10, 9, 6, 2, 1]
    }

    public static void oppgave6() {
        ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        char[] verdier = "IATBHJCRSOFELKGDMPQN".toCharArray();
        for (char c : verdier) tre.leggInn(c);

        System.out.println(tre);

        System.out.println(tre.høyreGren()+ " " + tre.lengstGren());
    }

    public static void oppgave7() {
        ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        char[] verdier = "IATBHJCRSOFELKGDMPQN".toCharArray();
        for (char c : verdier) tre.leggInn(c);

        System.out.println(tre.høyreGren() + " " + tre.lengstGren());

        // Utskrift: [I, T, J, R, S] [I, A, B, H, C, F, E, D]

        String[] s = tre.grener();

        for (String gren : s) System.out.println(gren);

        // Utskrift:
        // [I, A, B, H, C, F, E, D]
        // [I, A, B, H, C, F, G]
        // [I, T, J, R, O, L, K]
        // [I, T, J, R, O, L, M, N]
        // [I, T, J, R, O, P, Q]
        // [I, T, J, R, S]
    }

    public static void oppgave8b() {
        ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        char[] verdier = "IATBHJCRSOFELKGDMPQN".toCharArray();
        for (char c : verdier) tre.leggInn(c);

        System.out.println(tre.postString());

    }
}