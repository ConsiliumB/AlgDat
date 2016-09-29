package modules;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Consilium on 23.09.2016.
 */

public class TabellListe<T> {
    private T[] a;
    private int antall;

    @SuppressWarnings("unchecked")          // pga. konverteringen: Object[] -> T[]
    public TabellListe(int størrelse)       // konstruktør
    {
        a = (T[])new Object[størrelse];       // oppretter tabellen
        antall = 0;                           // foreløpig ingen verdier
    }

    public TabellListe()                    // standardkonstruktør
    {
        this(10);                             // startstørrelse på 10
    }

    public TabellListe(T[] b)                    // en T-tabell som parameter
    {
        this(b.length);                            // kaller den andre konstruktøren

        for (T verdi : b)
        {
            if (verdi != null) a[antall++] = verdi;  // hopper over null-verdier
        }
    }

    public TabellListe(Iterable<T> itererbar)    // konstruktør
    {
        for (T verdi : itererbar) leggInn(verdi);     // kopierer
    }

    public boolean leggInn(T t)
    {
        // En full tabell utvides med 50%
        if (antall == a.length) a = Arrays.copyOf(a,(3*antall)/2 + 1);
        a[antall++] = t;
        return true;   // vellykket innlegging
    }

    public Iterator<T> iterator() { return null; }  // foreløpig kode

    public int antall()
    {
        return antall;          // returnerer antallet
    }

    public boolean tom()
    {
        return antall == 0;     // listen er tom hvis antall er 0
    }

    public int indeksTil(T verdi)
    {
        for (int i = 0; i < antall; i++)
        {
            if (a[i].equals(verdi)) return i;   // funnet!
        }
        return -1;   // ikke funnet!
    }

    public boolean inneholder(T verdi)
    {
        return indeksTil(verdi) != -1;
    }
}
