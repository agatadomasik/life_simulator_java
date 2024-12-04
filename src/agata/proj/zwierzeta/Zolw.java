package agata.proj.zwierzeta;

import agata.proj.Organizm;
import agata.proj.Swiat;
import agata.proj.Zwierze;

import java.awt.*;
import java.util.Random;

import static agata.proj.Main.czyZwierze;

public class Zolw extends Zwierze {

    public Zolw(int a, int b, Swiat s) {
        nazwa = "Zolw";
        sila = 2;
        inicjatywa = 1;
        x = a;
        y = b;
        swiat = s;
        wiek = 0;
    }
    @Override
    public Color rysowanie() {
        return new Color(170, 199, 155);
    }
    @Override
    public Organizm rozmnozSie(int a, int b) {
        return new Wilk(a, b, swiat);
    }
    @Override
    public void akcja(int c) {
        Random rd = new Random();
        int n = rd.nextInt(4) + 1;
        if (n == 1) {
            swiat.events.add( "[Zolw] sie porusza" );

            do {
                rd = new Random();
                n = rd.nextInt(4) + 1;
                if (n == 1) {
                    if (x < swiat.getN() - 1) {
                        if (swiat.czyPuste(x + 1, y)) {
                            swiat.usunOrganizm(this);
                            x++;
                            swiat.dodajOrganizm(this);
                            break;
                        }
                        else {
                            kolizja(swiat.getOrganizmy()[x + 1][y]);
                            break;
                        }
                    }
                }
                else if (n == 2) {
                    if (x > 0) {
                        if (swiat.czyPuste(x - 1, y)) {
                            swiat.usunOrganizm(this);
                            x--;
                            swiat.dodajOrganizm(this);
                            break;
                        }
                        else {
                            kolizja(swiat.getOrganizmy()[x - 1][y]);
                            break;
                        }
                    }
                }
                else if (n == 3) {
                    if (y < swiat.getM() - 1) {
                        if (swiat.czyPuste(x, y + 1)) {
                            swiat.usunOrganizm(this);
                            y++;
                            swiat.dodajOrganizm(this);
                            break;
                        }
                        else {
                            kolizja(swiat.getOrganizmy()[x][y + 1]);
                            break;
                        }
                    }
                }
                else if (n == 4) {
                    if (y > 0) {
                        if (swiat.czyPuste(x, y - 1)) {
                            swiat.usunOrganizm(this);
                            y--;
                            swiat.dodajOrganizm(this);
                            break;
                        }
                        else {
                            kolizja(swiat.getOrganizmy()[x][y - 1]);
                            break;
                        }
                    }
                }
            } while (true);
        }
    }


    @Override
    public void kolizja(Organizm other) {

        //ROZMNAŻANIE
        if (this.nazwa == other.getNazwa()){
            if (swiat.czyPuste(x + 1, y) || swiat.czyPuste(x - 1, y) || swiat.czyPuste(x, y + 1) || swiat.czyPuste(x, y - 1)
                    || swiat.czyPuste(other.getX() + 1, other.getY()) || swiat.czyPuste(other.getX() - 1, other.getY()) ||
                    swiat.czyPuste(other.getX(), other.getY() + 1) || swiat.czyPuste(other.getX(), other.getY() - 1)) {
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
                    } else if (n == 2) {
                        if (x > 0) {
                            if (swiat.czyPuste(x - 1, y)) {
                                swiat.dodajOrganizm(this.rozmnozSie(x - 1, y));
                                break;
                            }
                        }
                    } else if (n == 3) {
                        if (y < swiat.getM() - 1) {
                            if (swiat.czyPuste(x, y + 1)) {
                                swiat.dodajOrganizm(this.rozmnozSie(x, y + 1));
                                break;
                            }
                        }
                    } else if (n == 4) {
                        if (y > 0) {
                            if (swiat.czyPuste(x, y - 1)) {
                                swiat.dodajOrganizm(this.rozmnozSie(x, y - 1));
                                break;
                            }
                        }
                    }

                    //Sprawdzanie pol wokol other
                    if (n == 1) {
                        if (x < swiat.getN() - 1) {
                            if (swiat.czyPuste(other.getX() + 1, other.getY())) {
                                swiat.dodajOrganizm(this.rozmnozSie(x + 1, y));
                                break;
                            }
                        }
                    } else if (n == 2) {
                        if (x > 0) {
                            if (swiat.czyPuste(other.getX() - 1, other.getY())) {
                                swiat.dodajOrganizm(this.rozmnozSie(x - 1, y));
                                break;
                            }
                        }
                    } else if (n == 3) {
                        if (y < swiat.getM() - 1) {
                            if (swiat.czyPuste(other.getX(), other.getY() + 1)) {
                                swiat.dodajOrganizm(this.rozmnozSie(x, y + 1));
                                break;
                            }
                        }
                    } else if (n == 4) {
                        if (y > 0) {
                            if (swiat.czyPuste(other.getX(), other.getY() - 1)) {
                                swiat.dodajOrganizm(this.rozmnozSie(x, y - 1));
                                break;
                            }
                        }
                    }
                } while (true);
            }
        }


        //WALKA
    else{
            if (sila >= other.getSila()) {
                if (czyZwierze(other.getNazwa()));
                    //cout << "[" << nazwa << "] zabija [" << other.getNazwa() << "]        " << endl;
                else {
                    swiat.events.add("[" + nazwa + "] zjada [" + other.getNazwa() + "]");
                    other.kolizja(this);
                }
                if (other.getNazwa() == "Czlowiek") swiat.is_alive = false;
                swiat.usunOrganizm(other);
                swiat.usunOrganizm(this);
                this.setX(other.getX());
                this.setY(other.getY());
                swiat.dodajOrganizm(this);
            } else {
                if (other.getSila() >= 5) {
                    swiat.events.add("[" + other.getNazwa() + "] zabija [" + nazwa + "]");
                    swiat.usunOrganizm(this);
                } else swiat.events.add("[Zolw] odpiera atak [" + other.getNazwa() + "]");
            }
        }
    }
}

