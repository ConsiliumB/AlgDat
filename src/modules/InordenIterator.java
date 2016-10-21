package modules;
////////////////// class InordenIterator //////////////////////////////

public class InordenIterator //implements Iterator<T>
{
    /*private Stakk<Node<T>> s = new TabellStakk<>();
    private Node<T> p = null;

    private Node<T> først(Node<T> q)   // en hjelpemetode
    {
        while (q.venstre != null)        // starter i q
        {
            s.leggInn(q);                  // legger q på stakken
            q = q.venstre;                 // går videre mot venstre
        }
        return q;                        // q er lengst ned til venstre
    }

    private InordenIterator()          // konstruktør
    {
        if (tom()) return;               // treet er tomt
        p = først(rot);                  // bruker hjelpemetoden
    }

    @Override
    public T next()
    {
        if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");

        T verdi = p.verdi;                        // tar vare på verdien

        if (p.høyre != null) p = først(p.høyre);  // p har høyre subtre
        else if (s.tom()) p = null;               // stakken er tom
        else p = s.taUt();                        // tar fra stakken

        return verdi;                             // returnerer verdien
    }

    @Override
    public boolean hasNext()
    {
        return p != null;
    }
*/
} // InordenIterator
