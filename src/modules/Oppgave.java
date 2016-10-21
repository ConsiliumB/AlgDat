/////////// interface Oppgave ///////////////////////

package modules;

import java.util.Objects;

@FunctionalInterface
public interface Oppgave<T>
{
    void utførOppgave(T t);                         // en anstrakt metode

    public static <T> Oppgave<T> konsollutskrift()  // en konstruksjonsmetode
    {
        return t -> System.out.print(t + " ");
    }

    public static <T> Oppgave<T> konsollutskrift(String format)
    {
        return t -> System.out.printf(format,t);
    }

    default Oppgave<T> deretter(Oppgave<? super T> oppgave)
    {
        Objects.requireNonNull(oppgave);
        return t -> { utførOppgave(t); oppgave.utførOppgave(t); };
    }

} // Oppgave
