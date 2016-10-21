package modules;

////////////////// class HeapPrioritetsKø //////////////////////////////

        import java.util.*;

public class HeapPrioritetsKø<T> implements PrioritetsKø<T>
{
    private T[] heap;                          // heaptabellen
    private int antall;                        // antall verdier i køen
    private Comparator<? super T> comp;        // for sammenligninger

    public HeapPrioritetsKø(int kapasitet, Comparator<? super T> c)
    {
        if (kapasitet < 0)
            throw new IllegalArgumentException("Må ha kapasitet >= 0!");

        heap = (T[])new Object[kapasitet + 1];  // posisjon 0 brukes ikke
        antall = 0;
        comp = c;
    }

    public HeapPrioritetsKø(Comparator<? super T> c)
    {
        this(8,c);  // bruker 8 som startkapasitet
    }

    public static <T extends Comparable<? super T>>
    HeapPrioritetsKø<T> naturligOrdenKø(int kapasitet)
    {
        return new HeapPrioritetsKø<>(kapasitet, Comparator.naturalOrder());
    }

    public static
    <T extends Comparable<? super T>> HeapPrioritetsKø<T> naturligOrdenKø()
    {
        return naturligOrdenKø(8);
    }

    @Override
    public void leggInn(T verdi)
    {
        antall++;                          // øker antall med 1

        // tabellen må "utvides" hvis den er full
        if (antall == heap.length) heap = Arrays.copyOf(heap,2*antall);

        int k = antall;                    // første ledige plass i tabellen
        heap[0] = verdi;                   // stoppverdi for while-løkken

        while (comp.compare(heap[k/2],verdi) > 0)
        {
            heap[k] = heap[k/2];             // trekker verdien i heap[k/2] nedover
            k /= 2;                          // k går opp til forelderen
        }
        heap[k] = verdi;                   // verdi skal ligge i posisjon k
    }

    @Override
    public T kikk()
    {
        if (antall == 0)
            throw new NoSuchElementException("Køen er tom!");
        return heap[1];
    }

    @Override
    public T taUt()
    {
        if (antall == 0)
            throw new NoSuchElementException("Køen er tom!");

        T min = heap[1];                   // maksverdien ligger øverst
        T verdi = heap[antall];            // skal omplasseres
        heap[antall] = null;               // for resirkulasjon
        antall--;                          // en verdi mindre i køen

        int i = 1;
        int anthalv = antall/2;

        while (i <= anthalv)
        {
            int j = 2*i;
            if (j < antall && comp.compare(heap[j+1],heap[j]) < 0) j++;
            if (comp.compare(verdi,heap[j]) <= 0) break;
            heap[i] = heap[j];
            i = j;
        }

        heap[i] = verdi;                   // legges i posisjon i
        return min;
    }

    @Override
    public boolean taUt(T verdi)         // skal legges i klassen HeapPrioritetsKø
    {
        if (verdi == null) return false;   // køen har ikke nullverdier

        T omplasseres = heap[antall];      // skal omplasseres
        heap[antall] = null;               // for resirkulasjon
        antall--;                          // en verdi mindre i køen

        // sjekker om det er siste verdi som skal tas ut
        if (comp.compare(verdi,omplasseres) == 0) return true;

        int k = 1; for (; k <= antall; k++)  // leter etter verdien som skal ut
    {
        if (comp.compare(verdi,heap[k]) == 0) break;
    }

        if (k > antall) return false;      // fant ikke verdi

        // k er nå posisjonen til verdien som skal tas ut
        while ((k << 1) < antall)          // så lenge k har to barn
        {
            k <<= 1;                         // til venstre ved å doble k

            // hvis høyre barn k + 1 er minst, setter vi k dit, dvs. k++
            if (comp.compare(heap[k + 1], heap[k]) < 0) k++;

            heap[k >>> 1] = heap[k];         // forskyver oppover
        }

        if (2*k == antall)                 // har k et barn?
        {
            k *= 2;                          // går til venstre barn
            heap[k/2] = heap[k];             // forskyver oppover
        }

        // verdien som skal omplasseres settes inn på rett sortert
        // plass i grenen som ender i noden med posisjon k

        heap[0] = omplasseres;             // blir vaktpost

        while (comp.compare(heap[k/2],omplasseres) > 0)
        {
            heap[k] = heap[k/2];             // trekker verdien nedover
            k /= 2;                          // k går opp til forelderen
        }

        heap[k] = omplasseres;             // verdi skal ligge i posisjon k

        return true;
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
    public void nullstill()
    {
        for (int i = 0; i <= antall; i++) heap[i] = null;
        antall = 0;
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append('[');

        if (antall > 0) s.append(heap[1]);

        for (int i = 2; i <= antall; i++)
        {
            s.append(',');
            s.append(' ');
            s.append(heap[i]);
        }

        s.append(']');

        return s.toString();
    }

}  // HeapPrioritetsKø
