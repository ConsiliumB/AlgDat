////////////////// class Toveiskø //////////////////////////////

package modules;

import java.util.*;

public interface Toveiskø<T>          // eng: Deque
{
    public void leggInnFørst(T t);      // legger inn først i køen
    public void leggInnSist(T t);       // legger inn sist i køen
    public T kikkFørst();               // ser på den første
    public T kikkSist();                // ser på den siste
    public T taUtFørst();               // tar ut den første
    public T taUtSist();                // tar ut den siste
    public boolean tom();               // er køen tom?
    public int antall();                // antall i køen
    public void nullstill();            // nullstiller køen

} // interface Toveiskø
