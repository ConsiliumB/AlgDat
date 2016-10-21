package modules;

////////////////// class TabellKø //////////////////////////////


        import java.util.NoSuchElementException;


public class TabellKø<T> implements Kø<T>
{
    private T[] a;      // en tabell
    private int fra;    // posisjonen til den første i køen
    private int til;    // posisjonen til første ledige plass

    // Køen består av det sirkelformede halvåpne intervallet a[fra:til>

    @SuppressWarnings("unchecked")      // pga. konverteringen: Object[] -> T[]
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

    public TabellKø(int lengde)
    {
        if (lengde < 1)
            throw new IllegalArgumentException("Må ha positiv lengde!");

        a = (T[])new Object[lengde];

        fra = til = 0;    // a[fra:til> er tom
    }

    public TabellKø()   // standardkonstruktør
    {
        this(8);
    }

    @Override
    public boolean leggInn(T t)
    {
        a[til] = t;                         // ny verdi bakerst i køen
        til++;                              // øker til med 1
        if (til == a.length) til = 0;       // hopper til 0
        if (fra == til)                     // sjekker om tabellen er full
            a = utvidTabell(2*a.length);    // dobler tabellen
        return true;
    }

    @Override
    public T kikk()
    {
        if (fra == til)
            throw new NoSuchElementException("Køen er tom!");

        return a[fra];
    }

    @Override
    public T taUt()
    {
        if (fra == til) throw new         // sjekker om køen er tom
                NoSuchElementException("Køen er tom!");

        T temp = a[fra];                  // tar vare på den første i køen
        a[fra] = null;                    // nuller innholdet
        fra++;                            // øker fra med 1
        if (fra == a.length) fra = 0;     // hopper til 0
        return temp;                      // returnerer den første
    }

    @Override
    public int antall()
    {
        return fra <= til ? til - fra : a.length + til - fra;
    }

    @Override
    public boolean tom()
    {
        return til == fra;
    }

    @Override
    public void nullstill()
    {
        while (fra != til)
        {
            a[fra++] = null;
            if (fra == a.length) fra = 0;
        }
    }

    @Override
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

}  // TabellKø

