package modules;

import java.util.*;

public class Tabell {
    private Tabell() {}

    public static void bytt(int[] a, int i, int j)
    {
        int temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    public static int[] randPerm(int n)
    {
        Random r = new Random();
        int[] a = new int[n];

        Arrays.setAll(a, i -> i + 1);

        for (int k = n - 1; k > 0; k--)
        {
            int i = r.nextInt(k+1);
            bytt(a,k,i);
        }

        return a;
    }

    public static
    void randPerm(int[] a)
    {
        Random r = new Random();

        for (int k = a.length - 1; k > 0; k--)
        {
            int i = r.nextInt(k + 1);
            bytt(a,k,i);
        }
    }

    public static int maks(int[] a, int fra, int til)
    {
        if (fra < 0 || til > a.length || fra >= til)
        {
            throw new IllegalArgumentException("Illegalt intervall!");
        }

        int m = fra;
        int maksverdi = a[fra];

        for (int i = fra + 1; i < til; i++)
        {
            if (a[i] > maksverdi)
            {
                m = i;
                maksverdi = a[m];
            }
        }

        return m;
    }

    public static int maks(int[] a)
    {
        return maks(a,0,a.length);
    }

    public static int min(int[] a, int fra, int til)
    {
        if (fra < 0 || til > a.length || fra >= til)
            throw new IllegalArgumentException("Illegalt intervall!");

        int m = fra;
        int minverdi = a[fra];

        for (int i = fra + 1; i < til; i++) if (a[i] < minverdi)
        {
            m = i;
            minverdi = a[m];
        }

        return m;
    }

    public static int min(int[] a)
    {
        return min(a,0,a.length);
    }

    public static void bytt(char[] a, int i, int j)
    {
        char temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    public static void skriv(int[] a, int fra, int til)
    {
        fratilKontroll(a.length, fra, til);
        if (til - fra > 0) System.out.print(a[fra]);
        for (int i = fra + 1; i < til; i++) System.out.print(" " + a[i]);
    }

    public static void skriv(int[] a)
    {
        skriv(a, 0, a.length);
    }

    public static void skrivln(int[] a, int fra, int til)
    {
        skriv(a,fra,til);
        System.out.println();
    }

    public static void skrivln(int[] a)
    {
        skrivln(a, 0, a.length);
    }

    public static int[] naturligeTall(int n)
    {
        if (n < 1) throw new
                IllegalArgumentException("n(" + n + ") er mindre enn 1!");

        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i + 1;
        return a;
    }

    public static int[] heleTall(int fra, int til)
    {
        if (fra > til) throw new
                IllegalArgumentException("fra(" + fra + ") > til(" + til + ")");

        int[] a = new int[til - fra];
        for (int i = fra; i < til; i++) a[i-fra] = i;
        return a;
    }

    public static void fratilKontroll(int tablengde, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new ArrayIndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > tablengde)                          // til er utenfor tabellen
            throw new ArrayIndexOutOfBoundsException
                    ("til(" + til + ") > tablengde(" + tablengde + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public static void vhKontroll(int tablengde, int v, int h)
    {
        if (v < 0)
            throw new ArrayIndexOutOfBoundsException("v(" + v + ") < 0");

        if (h >= tablengde)
            throw new ArrayIndexOutOfBoundsException
                    ("h(" + h + ") >= tablengde(" + tablengde + ")");

        if (v > h + 1)
            throw new IllegalArgumentException
                    ("v = " + v + ", h = " + h);
    }

    public static int[] nestMaks(int[] a) // ny versjon
    {
        int n = a.length;     // tabellens lengde
        if (n < 2) throw      // må ha minst to verdier
                new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int m = 0;      // m er posisjonen til største verdi
        int nm = 1;     // nm er posisjonen til nest største verdi

        // bytter om m og nm hvis a[1] er større enn a[0]
        if (a[1] > a[0]) { m = 1; nm = 0; }

        int maksverdi = a[m];                // største verdi
        int nestmaksverdi = a[nm];           // nest største verdi

        for (int i = 2; i < n; i++)
        {
            if (a[i] > nestmaksverdi)
            {
                if (a[i] > maksverdi)
                {
                    nm = m;
                    nestmaksverdi = maksverdi;     // ny nest størst

                    m = i;
                    maksverdi = a[m];              // ny størst
                }
                else
                {
                    nm = i;
                    nestmaksverdi = a[nm];         // ny nest størst
                }
            }
        } // for

        return new int[] {m,nm};    // n i posisjon 0, nm i posisjon 1

    } // nestMaks

    public static void snu(int[] a, int v, int h)  // snur intervallet a[v:h]
    {
        while (v < h) bytt(a, v++, h--);
    }

    public static void snu(int[] a, int v)  // snur fra og med v og ut tabellen
    {
        snu(a, v, a.length - 1);
    }

    public static void snu(int[] a)  // snur hele tabellen
    {
        snu(a, 0, a.length - 1);
    }

    public static boolean nestePermutasjon(int[] a)
    {
        int i = a.length - 2;                    // i starter nest bakerst
        while (i >= 0 && a[i] > a[i + 1]) i--;   // går mot venstre
        if (i < 0) return false;                 // a = {n, n-1, . . . , 2, 1}

        int j = a.length - 1;                    // j starter bakerst
        while (a[j] < a[i]) j--;                 // stopper når a[j] > a[i]
        bytt(a,i,j); snu(a,i + 1);               // bytter og snur

        return true;                             // en ny permutasjon
    }

    public static int inversjoner(int[] a)
    {
        int antall = 0;  // antall inversjoner
        for (int i = 0; i < a.length - 1; i++)
        {
            for (int j = i + 1; j < a.length; j++)
            {
                if (a[i] > a[j]) antall++;  // en inversjon siden i < j
            }
        }
        return antall;
    }

    public static boolean erSortert(int[] a)  // legges i samleklassen Tabell
    {
        for (int i = 1; i < a.length; i++)      // starter med i = 1
            if (a[i-1] > a[i]) return false;      // en inversjon

        return true;
    }

    public static int boble(int[] a, int n)  // legges i samleklassen Tabell
    {
        int antall = 0;                 // antall ombyttinger i tabellen
        for (int i = 1; i < n; i++)     // går fra 1 til n
        {
            if (a[i - 1] > a[i])          // sammenligner to naboverdier
            {
                bytt(a,i - 1, i);           // bytter om to naboverdier
                antall++;                   // teller opp ombyttingene
            }
        }
        return antall;                  // returnerer
    }

    public static void boblesortering(int[] a)
    {
        for (int n = a.length; n > 1; n--)
        {
            if (boble(a, n) == 0) break;
        }
    }

    public static void utvalgssortering(int[] a)
    {
        for (int i = 0; i < a.length - 1; i++)
            bytt(a, i, min(a, i, a.length));  // to hjelpemetoder
    }

    public static int lineærsøk(int[] a, int verdi) // legges i class Tabell
    {
        if (a.length == 0 || verdi > a[a.length-1])
            return -(a.length + 1);  // verdi er større enn den største

        int i = 0; for( ; a[i] < verdi; i++);  // siste verdi er vaktpost

        return verdi == a[i] ? i : -(i + 1);   // sjekker innholdet i a[i]
    }

    public static void shell(int[] a, int k)
    {
        for (int i = k; i < a.length; i++)
        {
            int temp = a[i], j = i - k;
            for ( ; j >= 0 && temp < a[j]; j -= k) a[j + k] = a[j];
            a[j + k] = temp;
        }
    }

    public static void innsettingssortering(int[] a)
    {
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            int temp = a[i];  // hjelpevariabel
            for (int j = i - 1; j >= 0 && temp < a[j]; j--) Tabell.bytt(a, j, j + 1);
        }
    }

    public static void innsettingssortering(int[] a, int fra, int til)
    {
        fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)

        for (int i = fra + 1; i < til; i++)  // a[fra] er første verdi
        {
            int temp = a[i];  // flytter a[i] til en hjelpevariabel

            // verdier flyttes inntil rett sortert plass i a[fra:i> er funnet
            int j = i-1; for (; j >= fra && temp < a[j]; j--) a[j+1] = a[j];

            a[j+1] = temp;  // verdien settes inn på rett sortert plass
        }
    }

    public static int binærsøk(int[] a, int fra, int til, int verdi)
    {
        Tabell.fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)
        int v = fra, h = til - 1;  // v og h er intervallets endepunkter

        while (v < h)  // obs. må ha v < h her og ikke v <= h
        {
            int m = (v + h)/2;  // heltallsdivisjon - finner midten

            if (verdi > a[m]) v = m + 1;   // verdi må ligge i a[m+1:h]
            else  h = m;                   // verdi må ligge i a[v:m]
        }
        if (h < v || verdi < a[v]) return -(v + 1);  // ikke funnet
        else if (verdi == a[v]) return v;            // funnet
        else  return -(v + 2);                       // ikke funnet
    }

    private static int parter0(int[] a, int v, int h, int skilleverdi)
    {
        while (true)                                  // stopper når v > h
        {
            while (v <= h && a[v] < skilleverdi) v++;   // h er stoppverdi for v
            while (v <= h && a[h] >= skilleverdi) h--;  // v er stoppverdi for h

            if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }

    private static int sParter0(int[] a, int v, int h, int indeks)
    {
        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter0(a, v, h - 1, a[h]);  // partisjonerer a[v:h − 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien
    }

    private static void kvikksortering0(int[] a, int v, int h)  // en privat metode
    {
        if (v >= h) return;  // a[v:h] er tomt eller har maks ett element
        int k = sParter0(a, v, h, (v + h)/2);  // bruker midtverdien
        kvikksortering0(a, v, k - 1);     // sorterer intervallet a[v:k-1]
        kvikksortering0(a, k + 1, h);     // sorterer intervallet a[k+1:h]
    }

    public static void kvikksortering(int[] a, int fra, int til) // a[fra:til>
    {
        fratilKontroll(a.length, fra, til);  // sjekker når metoden er offentlig
        kvikksortering0(a, fra, til - 1);  // v = fra, h = til - 1
    }

    public static void kvikksortering(int[] a)   // sorterer hele tabellen
    {
        kvikksortering0(a, 0, a.length - 1);
    }

    public static int flett(int[] a, int m, int[] b, int n, int[] c)
    {
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) c[k++] = a[i] <= b[j] ? a[i++] : b[j++];

        while (i < m) c[k++] = a[i++];   // tar med resten av a
        while (j < n) c[k++] = b[j++];   // tar med resten av b

        return k;   // antallet verdier som er lagt inn i c
    }

    public static int flett(int[] a, int[] b, int[] c) // legges i samleklassen Tabell
    {
        return flett(a, a.length, b, b.length, c);
    }

    private static void flett(int[] a, int[] b, int fra, int m, int til)
    {
        int n = m - fra;                // antall elementer i a[fra:m>
        System.arraycopy(a,fra,b,0,n);  // kopierer a[fra:m> over i b[0:n>

        int i = 0, j = m, k = fra;      // løkkeST0r og indekser

        while (i < n && j < til)        // fletter b[0:n> og a[m:til> og
        {                               // legger resultatet i a[fra:til>
            a[k++] = b[i] <= a[j] ? b[i++] : a[j++];
        }

        while (i < n) a[k++] = b[i++];  // tar med resten av b[0:n>
    }

    private static void flettesortering(int[] a, int[] b, int fra, int til)
    {
        if (til - fra <= 1) return;   // a[fra:til> har maks ett element
        int m = (fra + til)/2;        // midt mellom fra og til

        flettesortering(a,b,fra,m);   // sorterer a[fra:m>
        flettesortering(a,b,m,til);   // sorterer a[m:til>

        if (a[m-1] > a[m]) flett(a,b,fra,m,til);  // fletter a[fra:m> og a[m:til>
    }

    public static void flettesortering(int[] a)
    {
        int[] b = Arrays.copyOf(a, a.length/2);   // en hjelpetabell for flettingen
        flettesortering(a,b,0,a.length);          // kaller metoden over
    }

    public static boolean erLik(int[] a, int m, int[] b, int n)
    {
        if (m < 0 || m > a.length)
            throw new IllegalArgumentException("a[0:m> er ulovlig!");

        if (n < 0 || n > b.length)
            throw new IllegalArgumentException("b[0:n> er ulovlig!");

        if (m != n) return false;  // forskjellige lengder

        if (a == b) return true;   // det samme intervallet

        for (int i = 0; i < m; i++)
            if (a[i] != b[i]) return false;

        return true;
    }

    public static boolean erLik(int[] a, int[] b)
    {
        return erLik(a,a.length,b,b.length);
    }

    public static int differens(int[] a, int m, int[] b, int n, int[] c)
    {
        if (m < 0 || m > a.length)
            throw new IllegalArgumentException("a[0:m> er ulovlig!");

        if (n < 0 || n > b.length)
            throw new IllegalArgumentException("b[0:n> er ulovlig!");

        int i = 0, j = 0, k = 0;

        while (i < m && j < n)
        {
            if (a[i] < b[j]) c[k++] = a[i++];
            else if (a[i] == b[j]) { i++; j++;}
            else j++;
        }
        while (i < m) c[k++] = a[i++];

        return k;
    }

    public static int differens(int[] a, int[] b, int[] c)
    {
        return differens(a, a.length, b, b.length,c);
    }

    public static boolean inklusjon(int[] a, int m, int[] b, int n)
    {
        if (m < 0 || m > a.length)
            throw new IllegalArgumentException("a[0:m> er ulovlig!");

        if (n < 0 || n > b.length)
            throw new IllegalArgumentException("b[0:n> er ulovlig!");

        int i = 0, j = 0;

        while (i < m && j < n)
        {
            if (a[i] < b[j]) i++;
            else if (a[i] == b[j]) {i++; j++;}
            else return false;
        }

        return j == n;
    }

    public static boolean inklusjon(int[] a, int[] b)
    {
        return inklusjon(a, a.length, b, b.length);
    }

    public static int xunion(int[] a, int m, int[] b, int n, int[] c)
    {
        if (m < 0 || m > a.length)
            throw new IllegalArgumentException("a[0:m> er ulovlig!");

        if (n < 0 || n > b.length)
            throw new IllegalArgumentException("b[0:n> er ulovlig!");

        int i = 0, j = 0, k = 0;

        while (i < m && j < n)
        {
            if (a[i] < b[j]) c[k++] = a[i++];
            else if (a[i] == b[j]) { i++; j++;}
            else c[k++] = b[j++];
        }
        while (i < m) c[k++] = a[i++];
        while (j < n) c[k++] = b[j++];

        return k;
    }

    public static int xunion(int[] a, int[] b, int[] c)
    {
        return xunion(a, a.length, b, b.length,c);
    }


    public static int binærsøk(int[] a, int element) {
        return binærsøk(a,0,a.length,element);
    }

    public static int snitt(int[] a, int m, int[] b, int n, int[] c)
    {
        int i = 0, j = 0, k = 0;             // indekser for a, b og c
        while (i < m && j < n)
        {
            if (a[i] < b[j]) i++;              // hopper over a[i]
            else if (a[i] == b[j])             // a[i] og b[j] er like
            {
                c[k++] = a[i++]; j++;            // tar med a[i], men ikke b[j]
            }
            else  j++;                         // hopper over b[j]
        }

        return k;                            // antall i snittet
    }

    public static int snitt(int[] a, int[] b, int[] c)  // hele tabeller
    {
        return snitt(a, a.length, b, b.length, c);
    }

    public static int union(int[] a, int m, int[] b, int n, int[] c)
    {
        int i = 0, j = 0, k = 0;             // indekser for a, b og c
        while (i < m && j < n)
        {
            if (a[i] < b[j]) c[k++] = a[i++];  // tar med a[i]
            else if (a[i] == b[j])             // a[i] og b[j] er like
            {
                c[k++] = a[i++]; j++;            // tar med a[i], men ikke b[j]
            }
            else  c[k++] = b[j++];             // tar med b[j]
        }

        while (i < m) c[k++] = a[i++];       // tar med resten av a[0:m>
        while (j < n) c[k++] = b[j++];       // tar med resten av b[0:n>

        return k;                            // antall verdier i unionen
    }

    public static int union(int[] a, int[] b, int[] c)
    {
        return union(a, a.length, b, b.length, c);
    }

    public static int maks(double[] a)     // legges i class Tabell
    {
        int m = 0;                           // indeks til største verdi
        double maksverdi = a[0];             // største verdi

        for (int i = 1; i < a.length; i++) if (a[i] > maksverdi)
        {
            maksverdi = a[i];     // største verdi oppdateres
            m = i;                // indeks til største verdi oppdaters
        }
        return m;     // returnerer posisjonen til største verdi
    }

    public static int maks(String[] a)    // legges i class Tabell
    {
        int m = 0;                          // indeks til største verdi
        String maksverdi = a[0];            // største verdi

        for (int i = 1; i < a.length; i++) if (a[i].compareTo(maksverdi) > 0)
        {
            maksverdi = a[i];  // største verdi oppdateres
            m = i;             // indeks til største verdi oppdaters
        }
        return m;  // returnerer posisjonen til største verdi
    }

    public static <T extends Comparable<? super T>> int maks(T[] a)
    {
        int m = 0;                     // indeks til største verdi
        T maksverdi = a[0];            // største verdi

        for (int i = 1; i < a.length; i++) if (a[i].compareTo(maksverdi) > 0)
        {
            maksverdi = a[i];  // største verdi oppdateres
            m = i;             // indeks til største verdi oppdaters
        }
        return m;  // returnerer posisjonen til største verdi
    } // maks

    public static <T extends Comparable<? super T>> void innsettingssortering(T[] a)
    {
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            T verdi = a[i];        // verdi er et tabellelemnet
            int  j = i - 1;        // j er en indeks
            // sammenligner og forskyver:
            for (; j >= 0 && verdi.compareTo(a[j]) < 0 ; j--) a[j+1] = a[j];

            a[j + 1] = verdi;      // j + 1 er rett sortert plass
        }
    }

    public static void bytt(Object[] a, int i, int j)
    {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static Integer[] randPermInteger(int n)
    {
        Integer[] a = new Integer[n];               // en Integer-tabell
        Arrays.setAll(a, i -> i + 1);               // tallene fra 1 til n

        Random r = new Random();   // hentes fra  java.util

        for (int k = n - 1; k > 0; k--)
        {
            int i = r.nextInt(k+1);  // tilfeldig tall fra [0,k]
            bytt(a,k,i);             // bytter om
        }
        return a;  // tabellen med permutasjonen returneres
    }

    public static void skriv(Object[] a, int fra, int til)
    {
        fratilKontroll(a.length,fra,til);

        for (int i = fra; i < til; i++) System.out.print(a[i] + " ");
    }

    public static void skriv(Object[] a)
    {
        skriv(a,0,a.length);
    }

    public static void skrivln(Object[] a, int fra, int til)
    {
        skriv(a,fra,til);
        System.out.println();
    }

    public static void skrivln(Object[] a)
    {
        skrivln(a,0,a.length);
    }

    public static <T> void innsettingssortering(T[] a, Komparator<? super T> c)
    {
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            T verdi = a[i];        // verdi er et tabellelemnet
            int  j = i - 1;        // j er en indeks

            // sammenligner og forskyver:
            for (; j >= 0 && c.compare(verdi,a[j]) < 0 ; j--) a[j+1] = a[j];

            a[j + 1] = verdi;      // j + 1 er rett sortert plass
        }
    }
}