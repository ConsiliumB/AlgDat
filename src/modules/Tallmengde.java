package modules;

import java.util.*;

public final class Tallmengde                // legges på filen Tallmengde.java
{
    private final int[] a;                     // en heltallstabell

    public Tallmengde()                        // standardkonstruktør
    {
        a = new int[0];                          // en tom mengde
    }

    public Tallmengde (int[] b)                // konstruktør
    {
        if (b == null) throw new IllegalArgumentException("Tabellen er null!");

        Tabell.innsettingssortering(b);          // sorterer

        boolean duplikater = false;

        for (int i = 1; i < b.length; i++)
        {
            if (b[i-1] == b[i])                    // det er duplikater
            {
                duplikater = true;
                break;
            }
        }

        int antall = b.length;

        if (duplikater)
        {
            antall = 1;
            for (int i = 1; i < b.length; i++)     // sammenligner
            {
                if (b[i-1] < b[i]) b[antall++] = b[i];  // kopierer
            }
        }

        a = Arrays.copyOf(b, antall);            // a er nå korrekt
    }

    public Tallmengde(Tallmengde B)            // kopieringskonstruktør
    {
        a = B.a.clone();
    }

    public Tallmengde(int element)             // mengde med ett element
    {
        a = new int[] { element };
    }

    private Tallmengde(int[] b, int n)
    {
        a = Arrays.copyOf(b, n);                 // de n første verdiene i b
    }

    public Tallmengde union(Tallmengde B)                // returnerer unionen
    {
        int[] c = new int[antall() + B.antall()];          // en stor nok tabell
        int n = Tabell.union(a, B.a, c);                   // unionen
        return new Tallmengde(c,n);                        // privat konstruktør
    }

    public Tallmengde union(int element)                 // mengde med elementet
    {
        int pos = Tabell.binærsøk(a, element);
        if (pos >= 0) return this;                         // samme mengde

        pos = -(pos + 1);                                  // innsettingspunktet
        int n = antall();
        Tallmengde B = new Tallmengde(a, n + 1);           // plass til en mer

        int[] b = B.a;
        System.arraycopy(a, pos, b, pos + 1, n - pos);     // gjør plass
        b[pos] = element;                                  // setter inn

        return B;                                          // ny mengde
    }

    public Tallmengde snitt(Tallmengde B)                // returnerer snittet
    {
        int[] c = new int[Math.max(antall(),B.antall())];  // en stor nok tabell
        int n = Tabell.snitt(a, B.a, c);                   // snittet
        return new Tallmengde(c,n);                        // privat konstruktør
    }

    public Tallmengde differens(Tallmengde B)            // differensen
    {
        int[] c = new int[antall()];
        int n = Tabell.differens(a, B.a ,c);
        return new Tallmengde(c,n);
    }

    public Tallmengde differens(int element)             // mengde uten elementet
    {
        int pos = Tabell.binærsøk(a, element);
        if (pos < 0) return this;                          // samme mengde

        int n = antall();
        Tallmengde B = new Tallmengde(a, n - 1);           // plass til en mindre

        int[] b = B.a;
        System.arraycopy(a, pos + 1, b, pos, n - pos - 1); // tetter igjen

        return B;                                          // ny mengde
    }

    public Tallmengde xunion(Tallmengde B)               // returnerer xunionen
    {
        int[] c = new int[antall() + B.antall()];          // en stor nok tabell
        int n = Tabell.xunion(a, B.a, c);                  // xunionen
        return new Tallmengde(c,n);                        // privat konstruktør
    }

  /* Oppgave 10
  public Tallmengde xunion(Tallmengde B)              // returnerer xunionen
  {
    return differens(B).union(B.differens(this));
  }
  */

  /* Oppgave 11
  public Tallmengde xunion(Tallmengde B)              // returnerer xunionen
  {
    return union(B).differens(snitt(B));
  }
  */

    public boolean erElement(int element)            // er element i mengden?
    {
        return Tabell.binærsøk(a, element) >= 0;
    }

    public boolean inklusjon(Tallmengde B)
    {
        return Tabell.inklusjon(a, B.a);
    }

  /* Oppgave 8
  public boolean inklusjon2(Tallmengde B)
  {
    return B.equals(snitt(B));
  }
  */

    public boolean tom() { return a.length == 0; }   // er mengden tom?

    public int antall() { return a.length; }         // antallet i mengden

    @Override
    public boolean equals(Object o)                  // like mengder?
    {
        if (!(o instanceof Tallmengde)) return false;
        Tallmengde B = (Tallmengde)o;

        if (antall() != B.antall()) return false;      // ikke like lange

        int[] b = B.a;

        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i]) return false;

        return true;
    }

    @Override
    public String toString()                         // for utskrift
    {
        StringJoiner s = new StringJoiner(",","{","}");
        for (int element : a) s.add(Integer.toString(element));
        return s.toString();
    }

    public int[] tilTabell()                         // mengden som tabell
    {
        return a.clone();
    }
}
