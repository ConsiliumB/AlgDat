
/////////// DobbeltLenketListe ////////////////////////////////////
package modules;

import java.util.*;

public class DobbeltLenketListe<T> implements Liste<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        // instansvariabler
        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste)  // konstruktør
        {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        protected Node(T verdi)  // konstruktør
        {
            this(verdi, null, null);
        }

    } // Node

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;   // antall endringer i listen

    // hjelpemetode
    public Node<T> finnNode(int indeks) {
        if (indeks < antall / 2) {
            Node<T> element = hode;
            for (int i = 0; i < indeks; i++) {
                element = element.neste;
            }
//            System.out.println(indeks+" ergo forfra: "+element.verdi);
            return element;
        } else {
            Node<T> element = hale;
            for (int i = antall-1; i > indeks; i--) {
                element = element.forrige;
            }
//            System.out.println(indeks+" ergo bakfra: "+element.verdi);
            return element;
        }
    }

    // konstruktør
    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    // konstruktør
    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a, "Tabellen er null!");

        if (a.length > 0) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != null) {
                    if (hode == null) {
                        hale = hode = new Node<>(a[i]);
                    } else {
                        hale.neste = new Node<>(a[i]);
                        hale.neste.forrige = hale;
                        hale = hale.neste;
                    }
                    antall++;
                }
            }
        }
    }

    // subliste
    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall, fra, til);
        DobbeltLenketListe<T> dll = new DobbeltLenketListe<>();

        Node<T> element = finnNode(fra);
        for (int i = fra; i < til; i++) {
            dll.leggInn(element.verdi);
            element = element.neste;
        }
        return dll;
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
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi);

        if (hode == null) {
            hode = hale = new Node<>(verdi);
        } else {
            hale.neste = hale = new Node<>(verdi, hale, null);
        }
        antall++;
        endringer++;

        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Kan ikke legge inn nullverdier!");
        indeksKontroll(indeks, true);
        if (tom()) {
            hode = hale = new Node<>(verdi);
        } else if (indeks == 0) {
            hode.forrige = hode = new Node<>(verdi, null, hode);
        } else if (indeks == antall) {
            hale.neste = hale = new Node<>(verdi,hale,null);
        } else {
            Node<T> element = finnNode(indeks);
            element.forrige.neste = element.forrige = new Node<>(verdi, element.forrige, element);
        }
        endringer++;
        antall++;
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);

        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        Node<T> element = hode;
        for (int i = 0; i < antall; i++) {
            if (element.verdi.equals(verdi) ) {
                return i;
            }
            element = element.neste;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi);
        indeksKontroll(indeks, false);
        T verdi;
        if (indeks == 0) {
            verdi = hode.verdi;
            if (antall == 1) {
                hode = hale = new Node<>(nyverdi);
            } else {
                hode.neste.forrige = hode = new Node<>(nyverdi, null, hode.neste);
            }
        } else if (indeks == antall - 1) {
            verdi = hale.verdi;
            hale.forrige.neste = hale = new Node<>(nyverdi, hale.forrige, null);
        } else {
            Node<T> element = finnNode(indeks);
            verdi = element.verdi;
            element.forrige.neste = element.neste.forrige = new Node<>(nyverdi, element.forrige, element.neste);
        }
        endringer++;
        return verdi;
    }

    private static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    @Override
    public boolean fjern(T verdi) {
        if (antall == 0) {
            return false;
        }
        Node<T> element = hode;
        while (element != null) {
            if (element.verdi.equals(verdi)) {
                if (antall == 1) {
                    hode = hale = null;
                } else if (element == hode) {
                    hode = hode.neste;
                    hode.forrige = null;
                } else if (element == hale) {
                    hale = hale.forrige;
                    hale.neste = null;
                } else {
                    element.forrige.neste = element.neste;
                    element.neste.forrige = element.forrige;
                }
                antall--;
                endringer++;
                return true;
            }
            element = element.neste;
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);

        T verdi;
        if (antall == 1) {
            verdi = hode.verdi;
            hode = hale = null;
        } else if (indeks == 0) {
            verdi = hode.verdi;
            hode = hode.neste;
            hode.forrige = null;
        } else if (indeks == antall - 1) {
            verdi = hale.verdi;
            hale = hale.forrige;
            hale.neste = null;
        } else {
            Node<T> element = finnNode(indeks);
            verdi = element.verdi;
            element.forrige.neste = element.neste;
            element.neste.forrige = element.forrige;
        }

        antall--;
        endringer++;

        return verdi;
    }


    public void nullstill_slow() {
        while (hode != null) {
            fjern(0);
        }
    }

    @Override
    public void nullstill() {
        Node<T> element = hode;
        Node<T> nesteelement = hode;
        while (nesteelement != null) {
            nesteelement = element.neste;
            element.neste = element.forrige = null;
        }
        hode = hale = null;
        antall = 0;
        endringer++;
    }

    @Override
    public String toString() {
        /*StringBuilder sb = new StringBuilder("[");

        Node<T> element = hode;
        if (antall > 0) {
            sb.append(element.verdi);
        }
        for (int i = 1; i < antall; i++) {
            element = element.neste;
            sb.append(", ");
            sb.append(element.verdi);
        }

        sb.append("]");
        return sb.toString();*/
        StringJoiner sj = new StringJoiner(", ", "[", "]");

        Node<T> element = hode;
        while (element != null) {
            sj.add(element.verdi.toString());
            element = element.neste;
        }

        return sj.toString();
    }

    public String omvendtString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");

        Node<T> element = hale;
        while (element != null) {
            sj.add(element.verdi.toString());
            element = element.forrige;
        }

        return sj.toString();
    }

    public static <T> void sorter2(Liste<T> liste, Comparator<? super T> c) {
        for (int i = 1; i < liste.antall(); i++) {
            T denne = liste.hent(i);
            for (int j = i-1; j >= 0 ; j--) {
                T sammenlignes = liste.hent(j);
                if (c.compare(denne, sammenlignes) < 0) {
                    if (j < i) {
//                        System.out.println(denne+" fjernes fra "+i+", men legges inn på "+j);
                        liste.fjern(i-(i-j)+1);
                        liste.leggInn(j,denne);
                    }
                } else break;
            }
        }
    }

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        T denne;
        int plassering = 0;
        for (int i = 1; i < liste.antall(); i++) {
            denne = liste.hent(i);
            for (int j = i-1; j >= 0 ; j--) {
                plassering = j;
                if (c.compare(denne, liste.hent(j)) >= 0) {
                    plassering = j+1;
                    break;
                }
            }
            if (plassering != i) {
                liste.fjern(i);
                liste.leggInn(plassering,denne);
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // denne starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);     // denne starter på noden på angitt indeks
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        @Override
        public boolean hasNext() {
            return denne != null;  // denne koden skal ikke endres!
        }

        @Override
        public T next() {
            if (iteratorendringer != endringer) throw new ConcurrentModificationException("Noe har blitt endret eksternt. Avbryter");
            if (!hasNext()) throw new NoSuchElementException("Ingen flere verdier!");
            fjernOK = true;
            T verdi = denne.verdi;
            denne = denne.neste;
            return verdi;
        }

        @Override
        public void remove() {
            if (!fjernOK) throw new IllegalStateException("Ulovlig kall!");
            if (endringer != iteratorendringer) throw new ConcurrentModificationException("ConcMod");

            fjernOK = false;

            if (antall == 1) {
                hode = hale = null;
            } else if (denne == null) {
                hale = hale.forrige;
                hale.neste = null;
            } else if (denne.forrige == hode) {
                hode = hode.neste;
                hode.forrige = null;
            } else {
                denne.forrige.forrige.neste = denne;
                denne.forrige = denne.forrige.forrige;
            }
            antall--;
            endringer++;
            iteratorendringer++;
        }

    } // DobbeltLenketListeIterator

} // DobbeltLenketListe

