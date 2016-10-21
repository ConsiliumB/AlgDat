/*////////// LZW //////////////////////

package bitio;

import hjelpeklasser.*;
import java.io.*;
import java.net.URL;
import java.util.*;

public class LZW
{
  private static final int LZW17 = 17;

  private static final int NYTT_BITFORMAT = 256;  // skilletegn
  private static final int FØRSTE_KODE = 257;
  private static final int MAKSKODE = (1 << LZW17) - 1;


  // Deloppgave 2

  // Kaller de to komprimeringsmetodene til deloppgave 2
  // for komprimer1 og dekomprimer1

  public static void komprimerTilKonsoll(String melding)
  {
    char c = melding.charAt(0);
    String s = "" + c;
    int kode = c;
    int nestekode = 256;

    Map<String,Integer> ordbok = new TreeMap<>();

    for (int i = 1; i < melding.length(); i++)
    {
      c = melding.charAt(i);

      Integer ordkode = ordbok.get(s + c);
      if (ordkode == null)
      {
        System.out.println(kode + " " + Integer.toBinaryString(kode));
        ordbok.put(s + c, nestekode);
        nestekode++;
        s = "" + c;
        kode = c;
      }
      else
      {
        kode = ordkode;
        s = s + c;
      }
    }
    System.out.println(kode);

  }  // komprimerTilKonsoll

  public static void dekomprimerFraTabell(int[] a)
  {
    int kode = a[0]; // første tallkode - er < 256
    int nestekode = 256;

    String denne  = "" + (char)kode; // første tegn
    String forrige = null;
    char c;

    System.out.print(denne); // denne skrives ut

    Map<Integer,String> ordbok = new TreeMap<>();

    for (int i = 1; i < a.length; i++)
    {
      kode = a[i];
      forrige = denne;

      if (kode < nestekode)
      {
        denne = kode < 256 ? "" + (char)kode : ordbok.get(kode);
        c = denne.charAt(0);
      }
      else
      {
        c = forrige.charAt(0);
        denne = forrige + c;
      }

      System.out.print(denne);
      ordbok.put(nestekode,forrige + c);
      nestekode++;
    }
  }  // dekomprimerFraTabell

  public static void komprimer(String fraUrl, String tilFil)
  throws IOException
  {
    int bitformat = 9;     // starter med 9 biters utskrift
    int bitGrense = 512;   // grensen for 9 biter

    BitOutputStream ut = BitOutputStream.toFile(tilFil);
    InputStream inn =
      new BufferedInputStream((new URL(fraUrl)).openStream());

    int c = inn.read();
    if (c == -1) return;   // filen er tom

    Map<String,Integer> ordbok = new HashMap<>(); // ordboken

    String s = "" + (char)c;  // må bruke typen char her
    int kode = c;
    int nesteKode = FØRSTE_KODE;  // første ledige tall

    Integer ikode = null;     // hjelpevariabel

    while ((c = inn.read()) != -1)  // slutt hvis c er -1
    {
      ikode = ordbok.get(s + (char)c);  // søker etter s + c

      if (ikode == null)      // vi fant ikke s + c i ordboken
      {
        if (kode >= bitGrense)
        {
          ut.writeBits(NYTT_BITFORMAT,bitformat);  // skriver ut skilletegnet
          bitformat++;        // øker bitformat med 1
          bitGrense *= 2;     // dobler bitGrense
        }

        ut.writeBits(kode,bitformat);  // skriver tallkoden til fil

        if (nesteKode < MAKSKODE)
        {
          ordbok.put(s + (char)c, nesteKode);  // s + c og nesteKode i ordboken
          nesteKode++;       // øker nesteKode med 1
        }

        s = "" + (char)c;      // s = c

        kode = c;
      }

      else     // vi fant s + c i "ordboken"
      {
        kode = ikode;     // tallkoden til s + c
        s += (char)c;     // legger c bakerst i s
      }
    }

    if (kode >= bitGrense)
    {
      ut.writeBits(NYTT_BITFORMAT,bitformat);
      bitformat++;      // øker bitformat med 1
    }

    ut.writeBits(kode,bitformat); // skriver ut siste kode

    ut.close();
    inn.close();
  }

  public static void dekomprimer(String fraUrl, String tilFil)
  throws IOException
  {
    int bitformat = 9;     // starter med 9 biters innlesing

    BitInputStream inn =
      new BitInputStream((new URL(fraUrl)).openStream());

    BufferedWriter ut = new BufferedWriter(new FileWriter(tilFil));

    int kode = inn.readBits(bitformat);
    if (kode == -1) return;   // filen er tom

    Map<Integer,String> ordbok = new HashMap<>();  // ordboken

    int nesteKode = FØRSTE_KODE;

    String denne  = "" + (char)kode;  // første tegn
    String forrige = null;

    char c;

    ut.write(denne); // denne skrives ut

    while ((kode = inn.readBits(bitformat)) != -1)
    {
      if (kode == NYTT_BITFORMAT)
      {
        bitformat++;
        kode = inn.readBits(bitformat);
      }

      forrige = denne;

      if (kode < nesteKode)
      {
        if (kode < 256) // koden reperesenterer et tegn
          denne = "" + (char)kode;
        else // har hatt denne koden før - henter fra ordboken
          denne = ordbok.get(kode);

        c = denne.charAt(0);  // c = første tegn i denne
      }
      else // har ikke hatt denne koden før
      {
        c = forrige.charAt(0); // c = første tegn i forrige
        denne = forrige + c;
      }

      ut.write(denne); // skriver ut denne

      // legger et nytt ord i ordboken
      ordbok.put(nesteKode++,forrige + c);

    } // while

    inn.close();
    ut.close();
  }


  // Deloppgave 3

  // Kaller de to komprimeringsmetodene til deloppgave 3
  // for komprimer2 og dekomprimer2

  private static class Node implements Comparable<Node>
  {
    private byte tegn;
    private int forelder;  // tallkoden til foreldernoden

    private Node(byte tegn, int forelder)     // konstruktør
    {
      this.tegn = tegn;
      this.forelder = forelder;
    }

    public int hashCode() { return (tegn << 7) ^ forelder; }

    public boolean equals(Object o)
    {
      if (this == o) return true;
      if (!(o instanceof Node)) return false;
      Node p = (Node)o;
      return forelder == p.forelder && tegn == p.tegn;
    }

    public int compareTo(Node p)
    {
      if (forelder < p.forelder) return -1;
      else if (forelder > p.forelder) return 1;
      else return tegn - p.tegn;
    }
  }

  public static void komprimer2(String fraUrl, String tilFil)
  throws IOException
  {
    int bitformat = 9;     // starter med 9 biters utskrift
    int bitGrense = 512;   // grensen for 9 biter

    BitOutputStream ut = BitOutputStream.toFile(tilFil);
    InputStream inn =
      new BufferedInputStream((new URL(fraUrl)).openStream());

    int c = inn.read();
    if (c == -1) return;   // filen er tom

    Map<Node,Integer> ordbok = new TreeMap<>(); // ordboken

    int kode = c;
    int nesteKode = FØRSTE_KODE;      // første ledige tall

    while ((c = inn.read()) != -1)  // slutt hvis c er -1
    {
      Node p = new Node((byte)c,kode);  // lager en node

      Integer ikode = ordbok.get(p);  // søker etter noden

      if (ikode == null)      // vi fant ikke noden i ordboken
      {
        if (kode >= bitGrense)
        {
          ut.writeBits(NYTT_BITFORMAT,bitformat);  // skriver ut skilletegnet
          bitformat++;        // øker bitformat med 1
          bitGrense *= 2;     // dobler bitGrense
        }

        ut.writeBits(kode,bitformat);  // skriver tallkoden til fil

        if (nesteKode < MAKSKODE)
        {
          ordbok.put(p,nesteKode);  // noden og nesteKode i ordboken
          nesteKode++;       // øker nesteKode med 1
        }

        kode = c;
      }
      else     // vi fant noden i "ordboken"
      {
        kode = ikode;     // tallkoden til noden
      }
    }

    if (kode >= bitGrense)
    {
      ut.writeBits(NYTT_BITFORMAT,bitformat);
      bitformat++;      // øker bitformat med 1
    }

    ut.writeBits(kode,bitformat); // skriver ut siste kode

    ut.close();
    inn.close();

  }

  public static void dekomprimer2(String fraUrl, String tilFil)
  throws IOException
  {
    BitInputStream inn =
      new BitInputStream((new URL(fraUrl)).openStream());

    BufferedWriter ut = new BufferedWriter(new FileWriter(tilFil));

    int bitformat = 9;     // starter med 9 biters innlesing

    int kode = inn.readBits(bitformat);
    if (kode == -1) return;   // filen er tom

    Map<Integer,Node> ordbok = new HashMap<>();  // ordboken

    for (int i = 0; i < 256; i++) ordbok.put(i,new Node((byte)i,-1));

    Stakk<Integer> s = new TabellStakk<>();

    int nesteKode = FØRSTE_KODE;
    int forelder = 0;
    int forrige = kode;
    int c = kode; // første tegn

    ut.write(c);

    while ((kode = inn.readBits(bitformat)) != -1)
    {
      if (kode == NYTT_BITFORMAT)
      {
        bitformat++;
        kode = inn.readBits(bitformat);
      }

      if (kode < nesteKode)
      {
        forelder = kode;
      }
      else // har ikke hatt denne koden før
      {
        forelder = forrige;
        s.leggInn(c);
      }

      while (forelder != -1)
      {
        Node p = ordbok.get(forelder);
        s.leggInn((int)p.tegn);
        forelder = p.forelder;
      }

      c = s.kikk();

      while (!s.tom()) ut.write(s.taUt());

      if (nesteKode < MAKSKODE)
      {
        ordbok.put(nesteKode,new Node((byte)c,forrige));
        nesteKode++;
      }

      forrige = kode;

    } // while

    inn.close();
    ut.close();
  }

} // class LZW*/