package agata.proj.rosliny;

import agata.proj.Organizm;
import agata.proj.Roslina;
import agata.proj.Swiat;

import java.awt.*;
import java.util.Random;

public class Mlecz extends Roslina {

    public Mlecz(int a, int b, Swiat s) {
        nazwa = "Mlecz";
        sila = 0;
        inicjatywa = 0;
        x = a;
        y = b;
        swiat = s;
        wiek = 0;
    }
    @Override
    public Color rysowanie() {
        return new Color(255, 221, 61);
    }
    @Override
    public Organizm rozmnozSie(int a, int b) {
        return new Mlecz(a, b, swiat);
    }
    @Override
    public void akcja(int c) {
        for (int i = 0; i < 3; i++) {
            if (swiat.czyPuste(x + 1, y) || swiat.czyPuste(x - 1, y) || swiat.czyPuste(x, y + 1) || swiat.czyPuste(x, y - 1)) {
                do {
                    Random rd = new Random();
                    int n = rd.nextInt(4) + 1;

                    //Sprawdzanie pol wokol this
                    if (n == 1) {
                        if (x < swiat.getN() - 1) {
                            if (swiat.czyPuste(x + 1, y)) {
                                swiat.dodajOrganizm(this.rozmnozSie(x + 1, y));
                                break;
                            }
                        }
                    }
                    else if (n == 2) {
                        if (x > 0) {
                            if (swiat.czyPuste(x - 1, y)) {
                                swiat.dodajOrganizm(this.rozmnozSie(x - 1, y));
                                break;
                            }
                        }
                    }
                    else if (n == 3) {
                        if (y < swiat.getM() - 1) {
                            if (swiat.czyPuste(x, y + 1)) {
                                swiat.dodajOrganizm(this.rozmnozSie(x, y + 1));
                                break;
                            }
                        }
                    }
                    else if (n == 4) {
                        if (y > 0) {
                            if (swiat.czyPuste(x, y - 1)) {
                                swiat.dodajOrganizm(this.rozmnozSie(x, y - 1));
                                break;
                            }
                        }
                    }
                } while (true);
            }
        }
    }
}
