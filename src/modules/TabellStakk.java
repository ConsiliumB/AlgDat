package modules;

/**
 * Created by Consilium on 06.10.2016.
 */
import java.util.*;

public class TabellStakk<T> implements Stakk<T>
{
    private T[] a;                     // en T-tabell
    private int antall;                // antall verdier på stakken

    public TabellStakk()               // konstruktør - tabellengde 8
    {
        this(8);
    }

    public TabellStakk(int lengde)     // valgfri tabellengde
    {
        if (lengde < 0)
            throw new IllegalArgumentException("Negativ tabellengde!");

        a = (T[])new Object[lengde];     // oppretter tabellen
        antall = 0;                      // stakken er tom
    }

    public void leggInn(T verdi) {
        if (antall == a.length)
            a = Arrays.copyOf(a, antall == 0 ? 1 : 2*antall);   // dobler

        a[antall++] = verdi;
    }

    @Override
    public T kikk() {
        if (tom()) throw new NoSuchElementException("Tom stakk");

        return a[antall-1];
    }

    @Override
    public T taUt() {
        if (tom()) throw new NoSuchElementException("Tom stakk");

        antall--;
        T verdi = a[antall];
        a[antall] = null;
        return verdi;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public void nullstill() {
        while (antall > 0) {
            a[antall--] = null;
        }
    }

    public String toString() {
        StringJoiner s = new StringJoiner(", ", "[", "]");

        for (int i = antall - 1; i > 0; i--) {
            s.add(a[i] == null ? "null" : a[i].toString());
        }
        return s.toString();
    }

    // de andre metodene skal inn her!

}  // class TabellStakk
