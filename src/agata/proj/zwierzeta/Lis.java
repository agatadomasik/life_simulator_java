package agata.proj.zwierzeta;

import agata.proj.Organizm;
import agata.proj.Swiat;
import agata.proj.Zwierze;

import java.awt.*;
import java.util.Random;

public class Lis extends Zwierze {

    public Lis(int a, int b, Swiat s) {
        nazwa = "Lis";
        sila = 3;
        inicjatywa = 7;
        x = a;
        y = b;
        swiat = s;
        wiek = 0;
    }

    @Override
    public Color rysowanie() {
        return new Color(255, 141, 60);
    }

    @Override
    public Organizm rozmnozSie(int a, int b) {
        return new Lis(a, b, swiat);
    }

    @Override
    public void akcja(int c) {

        if ((x < swiat.getN() - 1 && ( (swiat.getOrganizmy()[x + 1][y] != null && swiat.getOrganizmy()[x + 1][y].getSila() <= sila) || (swiat.getOrganizmy()[x + 1][y] == null)) ) ||
                (x > 0 && ((swiat.getOrganizmy()[x - 1][y] != null && swiat.getOrganizmy()[x - 1][y].getSila() <= sila) || (swiat.getOrganizmy()[x - 1][y] == null) )||
                (y < swiat.getM() - 1 && ( ( (swiat.getOrganizmy()[x][y + 1] != null && swiat.getOrganizmy()[x][y + 1].getSila() <= sila) || (swiat.getOrganizmy()[x][y + 1] == null) )||
                (y > 0 && (swiat.getOrganizmy()[x][y - 1] != null && swiat.getOrganizmy()[x][y - 1].getSila() <= sila) ) || (swiat.getOrganizmy()[x][y - 1] == null) ) ))){
            {
                do {
                    Random rd = new Random();
                    int n = rd.nextInt(4) + 1;
                    if (n == 1) {
                        if (x < swiat.getN() - 1) {
                            if (swiat.czyPuste(x + 1, y)) {
                                swiat.usunOrganizm(this);
                                x++;
                                swiat.dodajOrganizm(this);
                                break;
                            } else {
                                if (swiat.getOrganizmy()[x + 1][y].getSila() <= sila) {
                                    kolizja(swiat.getOrganizmy()[x + 1][y]);
                                    break;
                                } else swiat.events.add("[Lis] wyczuwa silniejszy organizm");
                            }
                        }
                    } else if (n == 2) {
                        if (x > 0) {
                            if (swiat.czyPuste(x - 1, y)) {
                                swiat.usunOrganizm(this);
                                x--;
                                swiat.dodajOrganizm(this);
                                break;
                            } else {
                                if (swiat.getOrganizmy()[x - 1][y].getSila() <= sila) {
                                    kolizja(swiat.getOrganizmy()[x - 1][y]);
                                    break;
                                } else swiat.events.add("[Lis] wyczuwa silniejszy organizm");

                            }
                        }
                    } else if (n == 3) {
                        if (y < swiat.getM() - 1) {
                            if (swiat.czyPuste(x, y + 1)) {
                                swiat.usunOrganizm(this);
                                y++;
                                swiat.dodajOrganizm(this);
                                break;
                            } else {
                                if (swiat.getOrganizmy()[x][y + 1].getSila() <= sila) {
                                    kolizja(swiat.getOrganizmy()[x][y + 1]);
                                    break;
                                } else swiat.events.add("[Lis] wyczuwa silniejszy organizm");
                            }
                        }
                    } else if (n == 4) {
                        if (y > 0) {
                            if (swiat.czyPuste(x, y - 1)) {
                                swiat.usunOrganizm(this);
                                y--;
                                swiat.dodajOrganizm(this);
                                break;
                            } else {
                                if (swiat.getOrganizmy()[x][y - 1].getSila() <= sila) {
                                    kolizja(swiat.getOrganizmy()[x][y - 1]);
                                    break;
                                } else swiat.events.add("[Lis] wyczuwa silniejszy organizm");
                            }
                        }
                    }
                } while (true);
            }
            //else swiat.events.add("[Lis] nie ma gdzie sie poruszyc");
        }
    }
}
