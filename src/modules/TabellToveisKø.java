////////////////// class TabellToveiskø //////////////////////////////

package modules;

import java.util.*;

public class TabellToveiskø<T> implements Toveiskø<T>
{
    private T[] a;      // en tabell
    private int fra;    // posisjonen til den første i køen
    private int til;    // posisjonen til første ledige plass

    private T[] utvidTabell(int lengde)
    {
        T[] b = (T[])new Object[lengde];  // ny tabell

        // kopierer intervallet a[fra:a.length> over i b
        System.arraycopy(a,fra,b,0,a.length - fra);

        // kopierer intervallet a[0:fra> over i b
        System.arraycopy(a,0,b,a.length - fra, fra);

        fra = 0; til = a.length;

        return b;
    }

    public TabellToveiskø(int lengde)
    {
        if (lengde < 1)
            throw new IllegalArgumentException("Må ha positiv lengde!");

        a = (T[]) new Object[lengde];

        fra = til = 0;    // a[fra:til> er tom
    }

    public TabellToveiskø()   // standardkonstruktør
    {
        this(8);
    }

    public void leggInnFørst(T t)
    {
        fra--;
        if (fra == -1) fra = a.length - 1;
        a[fra] = t;
        if (fra == til)                   // sjekker om tabellen er full
            a = utvidTabell(2*a.length);    // dobler tabellen
    }

    public void leggInnSist(T t)
    {
        a[til] = t;                       // ny verdi bakerst gir ikke-tom kø
        til++;                            // øker til med 1
        if (til == a.length) til = 0;     // hopper til 0
        if (fra == til)                   // sjekker om tabellen er full
            a = utvidTabell(2*a.length);    // dobler tabellen
    }

    public T kikkFørst()
    {
        if (fra == til) throw new         // sjekker om køen er tom
                NoSuchElementException("Køen er tom!");

        return a[fra];  // returnerer den første
    }

    public T kikkSist()
    {
        if (fra == til) throw new         // sjekker om køen er tom
                NoSuchElementException("Køen er tom!");

        if (til == 0) return a[a.length - 1];
        else return a[til - 1];
    }

    public T taUtFørst()
    {
        if (fra == til) throw new         // sjekker om køen er tom
                NoSuchElementException("Køen er tom!");

        T temp = a[fra];                  // tar vare på den første i køen
        a[fra] = null;                    // nuller innholdet
        fra++;                            // øker fra med 1
        if (fra == a.length) fra = 0;     // hopper til 0
        return temp;                      // returnerer den første
    }


    public T taUtSist()
    {
        if (fra == til) throw new         // sjekker om køen er tom
                NoSuchElementException("Køen er tom!");

        til--;
        if (til == -1) til = a.length - 1;
        T temp = a[til];
        a[til] = null;
        return temp;
    }

    public int antall()
    {
        return fra <= til ? til - fra : a.length + til - fra;
    }

    public boolean tom()
    {
        return til == fra;
    }

    public void nullstill()
    {
        while (fra != til)
        {
            a[fra++] = null;
            if (fra == a.length) fra = 0;
        }
    }

    public String toString()
    {
        if (tom()) return "[]";

        StringBuilder s = new StringBuilder();

        int sfra = fra, stil = til;

        s.append('[').append(a[sfra++]);
        if (sfra == a.length) sfra = 0;

        while (sfra != stil)
        {
            s.append(',').append(' ').append(a[sfra++]);
            if (sfra == a.length) sfra = 0;
        }

        s.append(']');

        return s.toString();
    }

} // class TabellToveiskø
