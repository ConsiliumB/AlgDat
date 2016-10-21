package programs;

import modules.DobbeltLenketListe;
import modules.Liste;
import modules.Tabell;

import java.util.*;

/**
 * Created by Consilium on 27.09.2016.
 */
public class Oblig2Main {
    public static void main(String[] args) {
        oppgave10_2();
        oppgave10_2();
    }

    public static void oppgave1() {
        String[] s = {"Ole", null, "Per", "Kari", null};
        Liste<String> liste = new DobbeltLenketListe<>(s);
        System.out.println(liste.antall() + " " + liste.tom());
    }

    public static void oppgave2a() {
        String[] s1 = null, s2 = {"A"}, s3 = {null,"A",null,"B",null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);

        System.out.println(l1.toString() + " " + l2.toString()
                + " " + l3.toString() + " " + l1.omvendtString() + " "
                + l2.omvendtString() + " " + l3.omvendtString());
    }

    public static void oppgave2b() {
        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

        System.out.println(liste.toString() + " " + liste.omvendtString());

        for (int i = 1; i <= 3; i++)
        {
            liste.leggInn(i);
            System.out.println(liste.toString() + " " + liste.omvendtString());
        }
    }

    public static void oppgave2bfinnNodeTest() {
        String[] s1 = {}, s2 = {"A"}, s3 = {"A","B","C","D","E","F"};
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);
        l3.finnNode(3);
        l3.finnNode(1);
        l3.finnNode(2);
        l3.finnNode(0);
        l3.finnNode(4);
    }

    public static void oppgave3b() {
        Character[] c = {'A','B','C','D','E','F','G','H','I','J',};
        DobbeltLenketListe<Character> liste = new DobbeltLenketListe<>(c);
        System.out.println(liste.subliste(3,8));  // [D, E, F, G, H]
        System.out.println(liste.subliste(5,5));  // []
        System.out.println(liste.subliste(8,liste.antall()));  // [I, J]
//         System.out.println(liste.subliste(0,11));  // skal kaste unntak
    }

    public static void oppgave4test() {
        String[] s3 = {"A","B","C","D","E","F"};
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);

        System.out.println(l3.indeksTil("W"));
    }

    public static void oppgave5test() {
        String[] s3 = {"A","B","C","D","E","F"};
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);

        l3.leggInn(0,"A");
        System.out.println(l3.toString());
        l3.leggInn(l3.antall(),"3");
        System.out.println(l3.toString());
        l3.leggInn(3,"x");
        System.out.println(l3.toString());
    }

    public static void oppgave7tidstest() {
        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();
        for (int i = 0; i < 2000000; i++) liste.leggInn(i);
        long tid = System.currentTimeMillis();
        liste.nullstill();
        tid = System.currentTimeMillis()-tid;
        System.out.println("Tom? "+liste.toString());
        System.out.println("Ver. 1 brukte: "+tid+"ms");
        for (int i = 0; i < 2000000; i++) liste.leggInn(i);
        tid = System.currentTimeMillis();
        //liste.nullstill_slow(); IS ON GIT
        tid = System.currentTimeMillis()-tid;
        System.out.println("Tom? "+liste.toString());
        System.out.println("Ver. 2 brukte: "+tid+"ms");
    }

    public static void oppgave8() {
        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Liste<String> liste = new DobbeltLenketListe<>(navn);

        liste.forEach(s -> System.out.print(s + " "));
        System.out.println();
        for (String s : liste) System.out.print(s + " ");

        // Utskrift:
        // Lars Anders Bodil Kari Per Berit
        // Lars Anders Bodil Kari Per Berit
    }

    public static void oppgave9() {
        DobbeltLenketListe<String> liste =
                new DobbeltLenketListe<>(new String[]
                        {"Birger","Lars","Anders","Bodil","Kari","Per","Berit"});

        liste.fjernHvis(navn -> navn.charAt(0) == 'B'); // fjerner navn som starter med B

        System.out.println(liste + " " + liste.omvendtString());

        // Utskrift: [Lars, Anders, Kari, Per] [Per, Kari, Anders, Lars]
    }

    public static void oppgave10() {
//        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Integer[] a = new Integer[2000];
        Random rand = new Random();
        Arrays.setAll(a,x->rand.nextInt());
        Liste<Integer> liste = new DobbeltLenketListe<>(a);
        long tid = System.currentTimeMillis();
        DobbeltLenketListe.sorter(liste, Comparator.naturalOrder());
        tid = System.currentTimeMillis() - tid;
        System.out.println("V1: Tok "+tid+"ms");
        System.out.println(liste);

        liste = new DobbeltLenketListe<>(a);
        tid = System.currentTimeMillis();
        DobbeltLenketListe.sorter(liste, Comparator.naturalOrder());
        tid = System.currentTimeMillis() - tid;
        System.out.println("V2: Tok "+tid+"ms");
        System.out.println(liste);

        // Utskrift:
        // [Lars, Anders, Bodil, Kari, Per, Berit]
        // [Anders, Berit, Bodil, Kari, Lars, Per]
    }

    public static void oppgave10_2() {
        Integer[] a = Tabell.randPermInteger(20);
        Liste<Integer> liste = new DobbeltLenketListe<>(a);
        System.out.println(liste.toString());
        DobbeltLenketListe.sorter(liste, Comparator.reverseOrder());
        System.out.println(liste.toString());
    }

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c)
    {

        if (liste.antall() > 0) {
            T min = liste.hent(0);
            T current = min;
            int i = 0;
            for (int j = 1; j < liste.antall(); j++) {
                Iterator<T> it = liste.iterator();
                while (it.hasNext() && i < liste.antall() - j + 1) {
                    i++;
                    current = it.next();
                    min = c.compare(current, min) == -1 ? current : min;
                    System.out.println(min+"  "+i+" "+min);
                }
                liste.fjern(min);
                liste.leggInn(min);
                min = liste.hent(0);
                i = 0;
            }
        }

    }
}
