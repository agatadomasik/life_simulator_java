package agata.proj;

import agata.proj.rosliny.*;
import agata.proj.zwierzeta.*;

import java.util.ArrayList;
import java.util.Random;

public class Swiat {
    private int N;
    private int M;
    private Organizm[][] organizmy;
    public static ArrayList<String> events;
    public boolean is_alive;
    public Swiat(int a, int b) {
        N = a;
        M = b;
        is_alive = true;

        organizmy = new Organizm[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                organizmy[i][j] = null;
            }
        }
        events = new ArrayList<String>();
    }

    public static String[] getListaOrganizmow() {
        String[] tab = {"Antylopa", "Lis", "Owca", "Wilk", "Zolw", "BarszczSosnowskiego", "Guarana", "Mlecz", "Trawa", "WilczeJagody"};
        return tab;
    }

    public void rysujSwiat(int x, int y) {
        //gorna ramka
        System.out.print((char) 201);
        for (int i = 0; i < N * 2 + 1; i++) {
            System.out.print((char) 205);
        }
        System.out.println((char) 187);

        //dolna ramka
        System.out.print((char) 200);
        for (int i = 0; i < N * 2 + 1; i++) {
            System.out.print((char) 205);
        }
        System.out.println((char) 188);

        //boki
        for (int i = 0; i < M; i++) {
            System.out.print((char) 186);
            System.out.print(" ");
            System.out.print((char) 186);
            System.out.println();
        }

        //organizmy
        System.out.print(x + " " + y);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (organizmy[j][i] == null) {
                    System.out.print("  ");
                } else {
                    organizmy[j][i].rysowanie();
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void dodajOrganizm(Organizm o) {
        int x = o.getX();
        int y = o.getY();
        if (x < N && y < M) {
            organizmy[x][y] = o;
        }
    }

    public void usunOrganizm(Organizm o) {
        int x = o.getX();
        int y = o.getY();
        organizmy[x][y] = null;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public Organizm[][] getOrganizmy() {
        return organizmy;
    }

    public boolean czyPuste(int x, int y) {
        if (x < N && y < M && x >= 0 && y >= 0) {
            if (organizmy[x][y] == null) {
                return true;
            }
        }
        return false;
    }

    public void wyczysc() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                organizmy[i][j] = null;
            }
        }
    }

    public int getTura() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (organizmy[i][j] != null && organizmy[i][j].getNazwa() == "Czlowiek")
                    return organizmy[i][j].getTura();
            }
        }
        return 0;
    }

    public void setN(int a) {
        N = a;
    }

    public void setM(int a) {
        M = a;
    }

    public void wykonajTure(ArrayList<Organizm> tmp, int z) {
        for (int i = 0; i < tmp.size(); i++) {
                if (!tmp.get(i).getNazwa().equals("Czlowiek")) {
                    if (organizmy[tmp.get(i).getX()][tmp.get(i).getY()] == tmp.get(i)) {
                        tmp.get(i).akcja(z);
                    } else {
                        tmp.remove(i);
                    }
                } else {
                    tmp.get(i).akcja(z);
                }
            }

    }

    ArrayList<Organizm> getVector() {
        //dodawanie do listy
        ArrayList<Organizm> w = new ArrayList<Organizm>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (organizmy[i][j] != null) {
                    w.add(organizmy[i][j]);
                    organizmy[i][j].zwiekszWiek();
                }
            }
        }

        //sortowanie listy
        for (int i = 0; i < w.size(); i++)
            for (int j = 1; j < w.size() - i; j++)
                if (w.get(j - 1).getInicjatywa() < w.get(j).getInicjatywa()) {
                    Organizm temp = w.get(j - 1);
                    w.set(j - 1, w.get(j));
                    w.set(j, temp);
                } else if (w.get(j - 1).getInicjatywa() == w.get(j).getInicjatywa() && w.get(j - 1).getWiek() < w.get(j).getWiek()) {
                    Organizm temp = w.get(j - 1);
                    w.set(j - 1, w.get(j));
                    w.set(j, temp);
                }
        return w;
    }

    public void generuj(){
        for (int i = 0; i < 3; i++) {
            int[][] tmp = new int[10][2];

            Random rd = new Random();

            for (int j = 0; j < 10; j++) {
                tmp[j][0] = rd.nextInt(N);
                tmp[j][1] = rd.nextInt(M);
            }

            Organizm[] tab = new Organizm[10];
            tab[0] = new Wilk(tmp[0][0], tmp[0][1], this);
            tab[1] = new Owca(tmp[1][0], tmp[1][1], this);
            tab[2] = new Lis(tmp[2][0], tmp[2][1], this);
            tab[3] = new Zolw(tmp[3][0], tmp[3][1], this);
            tab[4] = new Antylopa(tmp[4][0], tmp[4][1], this);
            tab[5] = new Trawa(tmp[5][0], tmp[5][1], this);
            tab[6] = new Mlecz(tmp[6][0], tmp[6][1], this);
            tab[7] = new Guarana(tmp[7][0], tmp[7][1], this);
            tab[8] = new WilczeJagody(tmp[8][0], tmp[8][1], this);
            tab[9] = new BarszczSosnowskiego(tmp[9][0], tmp[9][1], this);
            for (int j = 0; j < 10; j++) {
                this.dodajOrganizm(tab[j]);
            }
        }
        Czlowiek c1 = new Czlowiek(0, 0, this);
        this.dodajOrganizm(c1);
    }

    public boolean getPower() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (organizmy[i][j] != null && organizmy[i][j].getNazwa() == "Czlowiek")
                    return organizmy[i][j].getPower();
            }
        }
        return false;
    }
}

