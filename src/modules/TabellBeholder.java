package modules;

import java.util.Arrays;
import java.util.Iterator;

public class TabellBeholder<T> extends AbstraktBeholder<T>
{
    private T[] a;           // en tabell
    private int antall;      // antallet verdier

    public TabellBeholder(int størrelse)   // konstruktør
    {
        a = (T[])new Object[størrelse];      // oppretter en tabell
        antall = 0;                          // foreløpig ingen verdier
    }

    public TabellBeholder()                // standardkonstruktør
    {
        this(10);                            // 10 som startstørrelse
    }

    public TabellBeholder(Iterable<T> itererbar)    // konstruktør
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
}