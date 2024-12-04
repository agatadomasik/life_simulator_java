package agata.proj;

import agata.proj.rosliny.*;
import agata.proj.zwierzeta.*;

import javax.swing.*;

import java.util.ArrayList;

public class Main {
    public static int z;
    public static boolean czyZwierze(String s) {
        if (s == "Wilk" || s == "Owca" || s == "Lis" || s == "Zolw" || s == "Antylopa" || s == "Czlowiek") return true;
        return false;
    }

    public static boolean czyOdpiera(Organizm o) {
        if (o.getNazwa() == "Zolw") return true;
        return false;
    }

    public static boolean czyUcieka(Organizm o) {
        if (o.getNazwa() == "Antylopa") return true;
        return false;
    }
    public static void main(String[] args) {
        z = 0;
        int M = Integer.parseInt(JOptionPane.showInputDialog("Podaj ilość wierszy: "));
        int N = Integer.parseInt(JOptionPane.showInputDialog("Podaj ilość kolumn: "));

        Swiat s = new Swiat(N, M);
        s.generuj();
        Board board = new Board(s);
    }
}
