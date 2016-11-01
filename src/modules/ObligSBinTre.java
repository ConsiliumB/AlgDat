////////////////// ObligSBinTre /////////////////////////////////

package modules;

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{
    public static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
        {
            this.verdi = verdi;
            venstre = v; høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString(){ return "" + verdi;}

    } // class Node

    public Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public ObligSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    @Override
    public boolean leggInn(T verdi)
    {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) {
            q.venstre = p;         // venstre barn til q
        }
        else {
            q.høyre = p;                        // høyre barn til q
        }

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging
    }

    @Override
    public boolean inneholder(T verdi)
    {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null)
        {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    @Override
    public boolean fjern(T verdi)
    {
        if (verdi == null) return false;  // treet har ingen nullverdier

        Node<T> p = rot, q = null;   // q skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
            else break;    // den søkte verdien ligger i p
        }
        if (p == null) return false;   // finner ikke verdi

        fjern(p);
        return true;
    }

    private boolean fjern(Node<T> p){
        Node<T> q = p.forelder;

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot) {
                rot = b;
                if (b != null) {
                    b.forelder = null;
                }
            }
            else if (p == q.venstre) {
                q.venstre = b;
                if (b != null) {
                    b.forelder = q;
                }
            }
            else {
                q.høyre = b;
                if (b != null) {
                    b.forelder = q;
                }
            }
        }
        else  // Tilfelle 3)
        {
            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) {
                s.venstre = r.høyre;
            }
            else {
                s.høyre = r.høyre;
            }
            if (r.høyre != null) {
                r.høyre.forelder = s;
            }
        }

        antall--;

        return true;
    }

    public int fjernAlle(T verdi)
    {
        int ant = 0;
        if (tom()) return ant;

        /*for (int i = 0; i < ant; i++) {
            fjern(verdi);
        }*/

        Node<T> p = rot;
        Node<T> q;

        while (p.venstre != null) {
            p = p.venstre;
        }

        while (p != null && p.verdi != verdi) {
            p = nesteInorden(p);
        }

        if (p == null) {
            return ant;
        }

        while (p != null && p.verdi == verdi) {
            q = p;
            p = nesteInorden(p);
            fjern(q);
            ant++;
        }

        return ant;
    }

    @Override
    public int antall()
    {
        return antall;
    }

    public int antall(T verdi)
    {
        if (verdi == null) return 0;

        Node<T> p = rot;
        int antall = 0;

        while (p != null)
        {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else {
                antall++;
                p = p.høyre;
            }
        }

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
        if (!tom()) nullstill(rot);  // nullstiller
        rot = null; antall = 0;      // treet er nå tomt
    }

    private void nullstill(Node<T> p)
    {
        if (p.venstre != null)
        {
            nullstill(p.venstre);      // venstre subtre
            p.venstre = null;          // nuller peker
        }
        if (p.høyre != null)
        {
            nullstill(p.høyre);        // høyre subtre
            p.høyre = null;            // nuller peker
        }
        p.forelder = null;
        p.verdi = null;              // nuller verdien
    }

    public static <T> Node<T> nesteInorden(Node<T> p)
    {
        Node<T> q = p.forelder;

        if (p.høyre != null) {
            p = p.høyre;
            while (p != null) {
                q = p;
                p = p.venstre;
            }
        } else {
            while (q != null && q.høyre == p) {
                p = q;
                q = p.forelder;
            }
        }

        return q;
    }

    @Override
    public String toString()
    {
        if (tom()) return "[]";

        StringJoiner sj = new StringJoiner(", ", "[", "]");
        Node<T> p = rot;

        while (p.venstre != null) {
            p = p.venstre;
        }

        while (p != null) {
            sj.add(p.toString());
            p = nesteInorden(p);
        }

        return sj.toString();
    }

    public String omvendtString()
    {
        if (tom()) return "[]";

        ArrayDeque deque = new ArrayDeque();
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        Node<T> p = rot;

        while (p.høyre != null) {
            deque.add(p);
            p = p.høyre;
        }

        while (true) {
            sj.add(p.toString());
            if (p.venstre != null) {
                p = p.venstre;
                while (p.høyre != null) {
                    deque.add(p);
                    p = p.høyre;
                }
            } else if (!deque.isEmpty()) {
                p = (Node<T>)deque.pollLast();
            } else break;
        }

        return sj.toString();
    }

    public String høyreGren()
    {
        StringJoiner st = new StringJoiner(", ", "[", "]");

        if (tom()) {
            return st.toString();
        }
        Node<T> p = rot;
        while (!(p.venstre == null && p.høyre == null)) {
            st.add(p.toString());
            if (p.høyre != null) {
                p = p.høyre;
            } else if (p.venstre != null) {
                p = p.venstre;
            }
        }
        st.add(p.toString());

        return st.toString();
    }

    public String lengstGren()
    {
        ArrayDeque ad = new ArrayDeque();
        StringJoiner st = new StringJoiner(", ", "[", "]");

        if (tom()) {
            return st.toString();
        }

        Object[] rekurtemp = new Object[2];
        rekurtemp[0] = new Integer(-1);

        høyde(rot, 0, rekurtemp);

        Node<T> p = (Node<T>)rekurtemp[1];

        while (p != null) {
            ad.add(p);
            p = p.forelder;
        }

        while (!ad.isEmpty()) {
            p = (Node<T>)ad.pollLast();
            st.add(p.toString());
        }

        return st.toString();
    }


    private static void høyde(Node<?> p, Integer nivå, Object[] maksnivå)
    {
        if (nivå.compareTo((Integer) maksnivå[0]) > 0) {
            maksnivå[0] = nivå;
            maksnivå[1] = p;
        }
        if (p.venstre != null) høyde(p.venstre, nivå + 1, maksnivå);
        if (p.høyre != null) høyde(p.høyre, nivå + 1, maksnivå);
    }

    private static String finnGren(Node<?> p){
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        ArrayDeque ad = new ArrayDeque();
        while (p != null) {
            ad.add(p);
            p = p.forelder;
        }

        while (!ad.isEmpty()) {
            p = (Node<?>)ad.pollLast();
            sj.add(p.toString());
        }

        return sj.toString();
    }

    private ArrayList<Node<T>> finnBladNoder(){
        ArrayList<Node<T>> bladNodeListe = new ArrayList<>();
        Node<T> p = rot;
        if (tom()) { return bladNodeListe;}

        while (p.venstre != null) {
            p = p.venstre;
        }

        while (p != null) {
            if (p.venstre == null && p.høyre == null) {
                bladNodeListe.add(p);
            }
            p = nesteInorden(p);
        }

        return bladNodeListe;
    }

    public String[] grener()
    {
        ArrayList<Node<T>> al = finnBladNoder();

        String[] str = new String[al.size()];

        int i = 0;
        for (Node<T> b : al) {
            str[i] = finnGren(b);
            i++;
        }

        return str;
    }

    private void postString0(Node<T> p, StringJoiner sj) {
        if (p.venstre != null) postString0(p.venstre, sj);
        if (p.høyre != null) postString0(p.høyre, sj);

        sj.add(p.toString());
    }

    private void bladnodeverdier0(Node<T> p, StringJoiner sj) {
        if (p.venstre != null) bladnodeverdier0(p.venstre, sj);
        if (p.venstre == null && p.høyre == null) {
            sj.add(p.toString());
        }
        if (p.høyre != null) bladnodeverdier0(p.høyre, sj);
    }

    public String bladnodeverdier()
    {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        if (tom()) return sj.toString();

        bladnodeverdier0(rot, sj);

        return sj.toString();
    }

    private Node<T> nestePostorden(Node<T> q) {
        if (tom() || q.forelder == null) return null;
        Node<T> p = q.forelder;

        while (true) {
            if (p.høyre != null && p.høyre != q) {
                p = p.høyre;
                while (p.venstre != null) {
                    p = p.venstre;
                    while (p.venstre == null && p.høyre != null) {
                        p = p.høyre;
                    }
                }
            } else break;
        }

        return p;
    }

    public String postString()
    {
        if (tom()) return "[]";

        StringJoiner sj = new StringJoiner(", ", "[", "]");

        Node<T> p = rot;

        while (!(p.venstre == null && p.høyre == null)) {
            if (p.venstre != null) {
                p = p.venstre;
            } else if (p.høyre != null) {
                p = p.høyre;
            }
        }

        while (p != null) {
            sj.add(p.toString());
            p = nestePostorden(p);
        }

        return sj.toString();
    }

    @Override
    public Iterator<T> iterator()
    {
        return new BladnodeIterator();
    }

    private class BladnodeIterator implements Iterator<T>
    {
        private Node<T> p = rot, q = null;
        private boolean removeOK = false;
        private int iteratorendringer = endringer;

        private BladnodeIterator()  // konstruktør
        {
            throw new UnsupportedOperationException("Ikke kodet ennå!");
        }

        @Override
        public boolean hasNext()
        {
            return p != null;  // Denne skal ikke endres!
        }

        @Override
        public T next()
        {
            throw new UnsupportedOperationException("Ikke kodet ennå!");
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Ikke kodet ennå!");
        }

    } // BladnodeIterator

} // ObligSBinTre
