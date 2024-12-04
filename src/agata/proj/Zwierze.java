package agata.proj;

import java.awt.*;
import java.util.Random;

public class Zwierze extends Organizm {
    @Override
    public Organizm rozmnozSie(int x, int y){return null;};
    @Override
    public Color rysowanie(){
        return null;
    };
    public void akcja(int c) {
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
                        kolizja(swiat.getOrganizmy()[x + 1][y]);
                        break;
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
                        kolizja(swiat.getOrganizmy()[x - 1][y]);
                        break;
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
                        kolizja(swiat.getOrganizmy()[x][y + 1]);
                        break;
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
                        kolizja(swiat.getOrganizmy()[x][y - 1]);
                        break;
                    }
                }
            }
        } while (true);
    }

    public void kolizja(Organizm other) {
        // ROZMNAŻANIE
        if (this.nazwa.equals(other.getNazwa())) {
            if (swiat.czyPuste(x + 1, y) || swiat.czyPuste(x - 1, y) || swiat.czyPuste(x, y + 1) || swiat.czyPuste(x, y - 1)
                    || swiat.czyPuste(other.getX() + 1, other.getY()) || swiat.czyPuste(other.getX() - 1, other.getY()) ||
                    swiat.czyPuste(other.getX(), other.getY() + 1) || swiat.czyPuste(other.getX(), other.getY() - 1)) {
                do {
                    Random rd = new Random();
                    int n = rd.nextInt(4) + 1;

                    // Sprawdzanie pól wokół this
                    if (n == 1) {
                        if (x < swiat.getN() - 1) {
                            if (swiat.czyPuste(x + 1, y)) {
                                swiat.dodajOrganizm(this.rozmnozSie(x + 1, y));
                                swiat.events.add("Narodziny [" + this.getNazwa() + "]");
                                break;
                            }
                        }
                    } else if (n == 2) {
                        if (x > 0) {
                            if (swiat.czyPuste(x - 1, y)) {
                                swiat.dodajOrganizm(this.rozmnozSie(x - 1, y));
                                swiat.events.add("Narodziny [" + this.getNazwa() + "]");
                                break;
                            }
                        }
                    } else if (n == 3) {
                        if (y < swiat.getM() - 1) {
                            if (swiat.czyPuste(x, y + 1)) {
                                swiat.dodajOrganizm(this.rozmnozSie(x, y + 1));
                                swiat.events.add("Narodziny [" + this.getNazwa() + "]");
                                break;
                            }
                        }
                    } else if (n == 4) {
                        if (y > 0) {
                            if (swiat.czyPuste(x, y - 1)) {
                                swiat.dodajOrganizm(this.rozmnozSie(x, y - 1));
                                swiat.events.add("Narodziny [" + this.getNazwa() + "]");
                                break;
                            }
                        }
                    }

                    // Sprawdzanie pól wokół other
                    if (n == 1) {
                        if (x < swiat.getN() - 1) {
                            if (swiat.czyPuste(other.getX() + 1, other.getY())) {
                                swiat.dodajOrganizm(this.rozmnozSie(x + 1, y));
                                swiat.events.add("Narodziny [" + this.getNazwa() + "]");
                                break;
                            }
                        }
                    } else if (n == 2) {
                        if (x > 0) {
                            if (swiat.czyPuste(other.getX() - 1, other.getY())) {
                                swiat.dodajOrganizm(this.rozmnozSie(x - 1, y));
                                swiat.events.add("Narodziny [" + this.getNazwa() + "]");
                                break;
                            }
                        }
                    } else if (n == 3) {
                        if (x > 0) {
                            if (swiat.czyPuste(other.getX() - 1, other.getY())) {
                                swiat.dodajOrganizm(this.rozmnozSie(x, y + 1));
                                swiat.events.add("Narodziny [" + this.getNazwa() + "]");
                                break;
                            }
                        }
                    } else if (n == 4) {
                        if (x > 0) {
                            if (swiat.czyPuste(other.getX() - 1, other.getY())) {
                                swiat.dodajOrganizm(this.rozmnozSie(x, y - 1));
                                swiat.events.add("Narodziny [" + this.getNazwa() + "]");
                                break;
                            }
                        }
                    }
                } while (true);
            }
        }

        else {
            if (sila >= other.getSila()) {
                if (!Main.czyOdpiera(other)) {
                    if (!Main.czyUcieka(other)) {
                        if (other.getNazwa() == "Czlowiek") swiat.is_alive = false;
                        if (Main.czyZwierze(other.getNazwa()))
                            swiat.events.add("[" + nazwa + "] zabija [" + other.getNazwa() + "]");
                        else {
                            swiat.events.add("[" + nazwa + "] zjada [" + other.getNazwa() + "]");
                            other.kolizja(this);
                        }
                        swiat.usunOrganizm(other);
                        swiat.usunOrganizm(this);
                        this.setX(other.getX());
                        this.setY(other.getY());
                        swiat.dodajOrganizm(this);
                    } else {
                        swiat.usunOrganizm(this);
                        this.setX(other.getX());
                        this.setY(other.getY());
                        other.kolizja(this);
                        swiat.dodajOrganizm(this);
                    }
                } else other.kolizja(this);
            } else {
                swiat.events.add("[" + other.getNazwa() + "] zabija [" + nazwa + "]");
                if (nazwa == "Czlowiek") swiat.is_alive = false;
                swiat.usunOrganizm(this);
            }
        }
    }
}


