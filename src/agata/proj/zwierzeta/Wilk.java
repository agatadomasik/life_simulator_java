package agata.proj.zwierzeta;

import agata.proj.Organizm;
import agata.proj.Swiat;
import agata.proj.Zwierze;

import java.awt.*;

public class Wilk extends Zwierze {

    public Wilk(int a, int b, Swiat s) {
        nazwa = "Wilk";
        sila = 9;
        inicjatywa = 5;
        x = a;
        y = b;
        swiat = s;
        wiek = 0;
    }
    @Override
    public Color rysowanie() {
        return new Color(138, 135, 135);
    }
    @Override
    public Organizm rozmnozSie(int a, int b) {
        return new Wilk(a, b, swiat);
    }
}

