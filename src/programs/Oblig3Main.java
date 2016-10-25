package programs;

import modules.ObligSBinTre;

import java.util.Comparator;

/**
 * Created by Consilium on 25.10.2016.
 */
public class Oblig3Main {
    public static void main(String[] args) {
        ObligSBinTre<String> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall());  // Utskrift: 0
    }
}