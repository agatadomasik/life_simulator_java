package agata.proj;

import java.awt.*;
import java.util.Random;

public class Roslina extends Organizm {
    @Override
    public Organizm rozmnozSie(int x, int y){return null;};
    @Override
    public Color rysowanie(){
        return null;
    };
    public void akcja(int c) {
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

    public void kolizja(Organizm other) {
    }
}



