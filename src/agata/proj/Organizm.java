package agata.proj;

import java.awt.*;

public abstract class Organizm {
    protected String nazwa;
    protected int sila;
    protected int inicjatywa;
    protected int x;
    protected int y;
    protected int wiek;
    protected Swiat swiat;

    public abstract void akcja(int c);
    public abstract void kolizja(Organizm other);
    public abstract Organizm rozmnozSie(int x, int y);
    public abstract Color rysowanie();
    public int getX() { return x; }
    public int getY() { return y; }
    public String getNazwa() { return nazwa; }
    public int getSila() { return (this != null) ? sila : 0; }
    public int getInicjatywa() { return inicjatywa; }
    public int getWiek() { return wiek; }
    public void setX(int a) { x = a; }
    public void setY(int b) { y = b; }
    public void setSila(int a) { sila = a; }
    public void zwiekszWiek() { wiek++; }
    public void setWiek(int a) { wiek = a; }
    public void clean() { swiat = null; }

    public int getTura() {
        return 0;
    }

    public boolean getPower() {
        return false;
    }
}
