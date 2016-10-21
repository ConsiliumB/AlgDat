package modules;

////////// SortertTabellPrioritetsKø ////////////////

        import java.util.*;

public class SortertTabellPrioritetsKø<T> implements PrioritetsKø<T>
{
    private T[] a;                       // en usortert tabell
    private int antall;                  // antall verdier i køen
    private Comparator<? super T> c;     // en komparator

    public SortertTabellPrioritetsKø(int størrelse, Comparator<? super T> c)
    {
        a = (T[])new Object[størrelse];   // tabellens startstørrelse
        antall = 0;
        this.c = c;
    }

    public SortertTabellPrioritetsKø(Comparator<? super T> c)
    {
        this(8,c);  // bruker 8 som startstørrelse
    }

    public static <T extends Comparable<? super T>> PrioritetsKø<T> naturligOrdenKø()
    {
        return new SortertTabellPrioritetsKø<>(Comparator.naturalOrder());
    }

    @Override
    public void leggInn(T verdi)
    {
        if (antall == a.length) a = Arrays.copyOf(a, 2*antall+1);

        int i = antall - 1;   //  vi sammenligner og flytter
        for (; i >= 0 && c.compare(verdi,a[i]) > 0; i--) a[i+1] = a[i];
        a[i+1] = verdi;
        antall++;
    }

    @Override
    public int antall()
    {
        return antall;
    }

    @Override
    public boolean tom()
    {
        return antall == 0;
    }

    @Override
    public T kikk()
    {
        if (tom()) throw new NoSuchElementException("Køen er tom!");
        return a[antall-1];
    }

    @Override
    public T taUt()
    {
        if (antall == 0) throw new NoSuchElementException("Køen er tom!");

        T minverdi = a[--antall];      // tar vare på den bakerste
        a[antall] = null;              // klargjør for resirkulering
        return minverdi;               // returnerer den største
    }

    @Override
    public boolean taUt(T verdi)
    {
        int i = 0;
        for (; i < antall; i++)
        {
            if (c.compare(a[i],verdi) == 0) break;
        }

        if (i == antall) return false;  // fant ikke verdi

        antall--;            // reduserer antallet

        while (i < antall)   // tetter igjen "hullet"
        {
            a[i] = a[i+1];
            i++;
        }

        a[antall] = null;    // klargjør for resirkulering

        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append('[');

        if (antall > 0) s.append(a[antall-1]);

        for (int i = antall - 2; i >= 0; i--)
        {
            s.append(',').append(' ').append(a[i]);
        }

        s.append(']');

        return s.toString();
    }

    @Override
    public void nullstill()
    {
        while (antall > 0) a[--antall] = null;
    }

}  // SortertTabellPrioritetsKø