package agata.proj.zwierzeta;

import java.awt.*;
import agata.proj.Organizm;
import agata.proj.Swiat;
import agata.proj.Zwierze;
import java.util.Random;

public class Czlowiek extends Zwierze {
    private int wait = 0;
    private boolean power = false;
    private int tura;

    public Czlowiek(int a, int b, Swiat s) {
        nazwa = "Czlowiek";
        sila = 5;
        inicjatywa = 4;
        x = a;
        y = b;
        swiat = s;
        wiek = 0;
        tura = 0;
    }
    @Override
    public Color rysowanie() {
        return new Color(0, 187, 255);
    }

    @Override
    public Organizm rozmnozSie(int a, int b) {
        return new Czlowiek(a, b, swiat);
    }
    @Override
    public void akcja(int c) {
        System.out.println(c);
        if (!power) {
            if (c == 40) {
                tura++;
                //cout << "prawo" << endl;
                if (x < swiat.getN() - 1) {
                    if (swiat.czyPuste(x + 1, y)) {
                        swiat.usunOrganizm(this);
                        x++;
                        swiat.dodajOrganizm(this);

                        //break;
                    }
                    else {
                        kolizja(swiat.getOrganizmy()[x + 1][y]);
                        //break;
                    }
                }
            }
            else if (c == 38) {
                tura++;
                //cout << "lewo" << endl;
                if (x > 0) {
                    if (swiat.czyPuste(x - 1, y)) {
                        swiat.usunOrganizm(this);
                        x--;
                        swiat.dodajOrganizm(this);
                        //break;
                    }
                    else {
                        kolizja(swiat.getOrganizmy()[x - 1][y]);
                        //break;
                    }
                }
            }
            else if (c == 39) {
                tura++;
                //cout << "dol" << endl;
                if (y < swiat.getM() - 1) {
                    if (swiat.czyPuste(x, y + 1)) {
                        swiat.usunOrganizm(this);
                        y++;
                        swiat.dodajOrganizm(this);
                        //break;
                    }
                    else {
                        kolizja(swiat.getOrganizmy()[x][y + 1]);
                        //break;
                    }
                }
            }
            else if (c == 37) {
                if (y > 0) {
                    tura++;
                    //cout << "gora" << endl;
                    if (swiat.czyPuste(x, y - 1)) {
                        swiat.usunOrganizm(this);
                        y--;
                        swiat.dodajOrganizm(this);
                        //break;
                    }
                    else {
                        kolizja(swiat.getOrganizmy()[x][y - 1]);
                        //break;
                    }
                }
            }
        }

        //SZYBKOSC ANTYLOPY
        else {
            if (c == 40) {
                tura++;
                //cout << "prawo" << endl;
                if (x < swiat.getN() - 2) {
                    if (swiat.czyPuste(x + 2, y)) {
                        swiat.usunOrganizm(this);
                        x+=2;
                        swiat.dodajOrganizm(this);
                        //break;
                    }
                    else {
                        kolizja(swiat.getOrganizmy()[x + 2][y]);
                        //break;
                    }
                }
            }
            else if (c == 38) {
                tura++;
                //cout << "lewo" << endl;
                if (x > 1) {
                    if (swiat.czyPuste(x - 2, y)) {
                        swiat.usunOrganizm(this);
                        x-=2;
                        swiat.dodajOrganizm(this);
                        //break;
                    }
                    else {
                        kolizja(swiat.getOrganizmy()[x - 2][y]);
                        //break;
                    }
                }
            }
            else if (c == 39) {
                tura++;
                //cout << "dol" << endl;
                if (y < swiat.getM() - 2) {
                    if (swiat.czyPuste(x, y + 2)) {
                        swiat.usunOrganizm(this);
                        y+=2;
                        swiat.dodajOrganizm(this);
                        //break;
                    }
                    else {
                        kolizja(swiat.getOrganizmy()[x][y + 2]);
                        //break;
                    }
                }
            }
            else if (c == 37) {
                tura++;
                if (y > 0) {
                    //cout << "gora" << endl;
                    if (swiat.czyPuste(x, y - 2)) {
                        swiat.usunOrganizm(this);
                        y-=2;
                        swiat.dodajOrganizm(this);
                        //break;
                    }
                    else {
                        kolizja(swiat.getOrganizmy()[x][y - 2]);
                        //break;
                    }
                }
            }
        }

        if ((tura == -6 || tura == -5) && c != -32) {
            Random rd = new Random();
            int n = rd.nextInt(2) + 1;
            if (n == 1)power = false;
            else power = true;
            //events.push_back("n->" + to_string(n));
        }

        if (tura == -5 && c != -32) {
            swiat.events.add("Szybkosc antylopy dezaktywowana");
            power = false;
        }

        if (c == ' ' && tura >= 0) {
            swiat.events.add("Szybkosc antylopy aktywowana");
            power = true;
            tura = -10;
        }
    }
    @Override
    public int getTura() { return tura; }

    @Override
    public boolean getPower() { return power; }
    public void setTura(int a) { tura = a; }

    public void setPower(boolean power) {
        this.power=power;
    }
}
