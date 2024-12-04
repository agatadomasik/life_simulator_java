package agata.proj.rosliny;

import agata.proj.Organizm;
import agata.proj.Roslina;
import agata.proj.Swiat;

import java.awt.*;

public class WilczeJagody extends Roslina {

    public WilczeJagody(int a, int b, Swiat s) {
        nazwa = "WilczeJagody";
        sila = 99;
        inicjatywa = 0;
        x = a;
        y = b;
        swiat = s;
        wiek = 0;
    }
    @Override
    public Color rysowanie() {
        return new Color(87, 55, 183);
    }
    @Override
    public Organizm rozmnozSie(int a, int b) {
        return new WilczeJagody(a, b, swiat);
    }
}
