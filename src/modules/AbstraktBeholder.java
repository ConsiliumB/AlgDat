package modules;

import java.util.Iterator;

public abstract class AbstraktBeholder<T> implements Beholder<T>
{
    public abstract boolean leggInn(T t);    // en abstrakt metode

    public boolean inneholder(T t)
    {
        return false;   // foreløpig kode
    }

    public boolean fjern(T t)
    {
        Iterator<T> i = iterator();

        if (t == null)               // fjerner en eventuell null-verdi
        {
            while (i.hasNext())        // flere igjen
            {
                if (i.next() == null)    // sammenligner
                {
                    i.remove();            // fjerner
                    return true;           // vellykket fjerning
                }
            }
        }
        else                         // t er ikke lik null
        {
            while (i.hasNext())        // flere igjen
            {
                if (t.equals(i.next()))  // sammneligner
                {
                    i.remove();            // fjerner
                    return true;           // vellykket fjerning
                }
            }
        }
        return false;                // mislykket fjerning
    }

    public int antall()
    {
        int antall = 0;
        for (T t : this) antall++;   // bruker en forAlle-løkke
        return antall;
    }

    public boolean tom()
    {
        return antall() == 0;   // ferdig kode
    }

    public void nullstill()
    {
        // foreløpig kode
    }

    public abstract Iterator<T> iterator();    // en abstrakt metode

    public String toString()
    {
        return null;   // foreløpig kode
    }
}