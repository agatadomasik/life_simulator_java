package agata.proj.rosliny;

import agata.proj.Organizm;
import agata.proj.Roslina;
import agata.proj.Swiat;
import java.awt.*;

public class Trawa extends Roslina {

    public Trawa(int a, int b, Swiat s) {
        nazwa = "Trawa";
        sila = 0;
        inicjatywa = 0;
        x = a;
        y = b;
        swiat = s;
        wiek = 0;
    }
    @Override
    public Color rysowanie() {
        return new Color(37, 157, 0);
    }
    @Override
    public Organizm rozmnozSie(int a, int b) {
        return new Trawa(a, b, swiat);
    }
}
