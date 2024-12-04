package agata.proj.rosliny;

import agata.proj.Organizm;
import agata.proj.Roslina;
import agata.proj.Swiat;
import java.awt.*;
import java.util.Random;

public class Guarana extends Roslina {

    public Guarana(int a, int b, Swiat s) {
        nazwa = "Guarana";
        sila = 0;
        inicjatywa = 0;
        x = a;
        y = b;
        swiat = s;
        wiek = 0;
    }
    @Override
    public Color rysowanie() {
        return new Color(208, 50, 50);
    }
    @Override
    public Organizm rozmnozSie(int a, int b) {
        return new Guarana(a, b, swiat);
    }
    @Override
    public void kolizja(Organizm other) {
        other.setSila(other.getSila() + 3);
        swiat.events.add("Sila [" + other.getNazwa() + "] wynosi teraz " + other.getSila());
    }
}
