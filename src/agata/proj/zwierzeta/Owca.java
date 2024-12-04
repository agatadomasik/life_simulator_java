package agata.proj.zwierzeta;

import agata.proj.Organizm;
import agata.proj.Swiat;
import agata.proj.Zwierze;

import java.awt.*;

public class Owca extends Zwierze {

    public Owca(int a, int b, Swiat s) {
        nazwa = "Owca";
        sila = 4;
        inicjatywa = 4;
        x = a;
        y = b;
        swiat = s;
        wiek = 0;
    }
    @Override
    public Color rysowanie() {
        return new Color(222, 216, 201);
    }
    @Override
    public Organizm rozmnozSie(int a, int b) {
        return new Owca(a, b, swiat);
    }
}
