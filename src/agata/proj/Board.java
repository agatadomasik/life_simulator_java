package agata.proj;

import agata.proj.rosliny.*;
import agata.proj.zwierzeta.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static agata.proj.Swiat.events;
import static java.lang.Math.max;

public class Board extends JFrame implements KeyListener, MouseListener {
    Swiat s;
    private int rows;
    private int cols;
    ;
    private JPanel[][] panels;
    private JPanel boardPanel;
    private JPanel menuPanel;
    private int posX;
    private int posY;

    /*    public void setSwiat(int n, int m){
            this.s = new Swiat(n, m);
            rows = n;
            cols = m;
            //System.out.println("setSwiat() -> " + s.getN() + " " + s.getM());
        }*/
    public Board(Swiat s) {

        this.rows = s.getN();
        this.cols = s.getM();
        this.posX = 0;
        this.posY = 0;
        this.s = s;

        setTitle("Plansza");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // utworzenie panelu głównego i ustawienie BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(cols * 30 + 220, max(rows * 30, 500)));
        getContentPane().add(mainPanel);

        // utworzenie panelu planszy i dodanie do głównego panelu na lewo
        boardPanel = new JPanel(new GridLayout(rows, cols));
        boardPanel.addMouseListener(this);

        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boardPanel.setPreferredSize(new Dimension(cols * 30, rows * 30));
        boardPanel.setMaximumSize(new Dimension(cols * 30, rows * 30));
        boardPanel.setMinimumSize(new Dimension(cols * 30, rows * 30));
        boardPanel.addKeyListener(this);
        boardPanel.setFocusable(true);
        boardPanel.requestFocusInWindow();
        JPanel bag = new JPanel(new GridBagLayout());
        bag.add(boardPanel);

        mainPanel.add(bag, BorderLayout.WEST);

        // utworzenie paneli kwadratów planszy i dodanie do panelu planszy
        panels = new JPanel[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)));
                panel.setPreferredSize(new Dimension(30, 30));
                panel.setMaximumSize(new Dimension(30, 30));
                panel.setMinimumSize(new Dimension(30, 30));
                if (s.getOrganizmy()[i][j] != null)
                    panel.setBackground(s.getOrganizmy()[i][j].rysowanie());
                panels[i][j] = panel;
                boardPanel.add(panel);
            }
        }

        menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuPanel.setSize(new Dimension(200, 800));
        mainPanel.add(menuPanel, BorderLayout.EAST);

        Organizm tab[] = {new Czlowiek(0, 0, s), new Owca(0, 0, s), new Antylopa(0, 0, s),
                new Lis(0, 0, s), new Wilk(0, 0, s), new Zolw(0, 0, s), new Trawa(0, 0, s),
                new Mlecz(0, 0, s), new Guarana(0, 0, s), new WilczeJagody(0, 0, s),
                new BarszczSosnowskiego(0, 0, s)};

        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new GridLayout(0, 1)); // set 0 columns and 1 row
        //legendPanel.add(new JLabel("Legenda:"));
        legendPanel.add(new JLabel(""));

        for (int i = 0; i < 11; i++) {
            JPanel c = new JPanel();
            c.setPreferredSize(new Dimension(30, 30));
            c.setBackground(tab[i].rysowanie());
            c.setBorder(BorderFactory.createLineBorder(new Color(171, 171, 171)));

            JLabel label = new JLabel(" " + tab[i].getNazwa() + " "); // create a label for the colored square
            JPanel labelPanel = new JPanel(new BorderLayout()); // create a panel to hold the label and the colored square
            labelPanel.add(c, BorderLayout.WEST); // add the colored square to the left
            JPanel labelSubPanel = new JPanel(new BorderLayout()); // create a nested panel to hold the label
            labelSubPanel.add(label, BorderLayout.WEST); // add the label to the right
            labelPanel.add(labelSubPanel, BorderLayout.CENTER); // add the nested panel to the center

            legendPanel.add(labelPanel);
            legendPanel.add(Box.createRigidArea(new Dimension(0, 10))); // add 10 pixels of vertical space

        }
        JButton nextRoundButton = new JButton("Nowa tura");
        JButton printEventsButton = new JButton("Zdarzenia");
        JButton saveButton = new JButton("Zapisz");
        JButton loadButton = new JButton("Wczytaj");

        nextRoundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (s.is_alive == true) JOptionPane.showMessageDialog(null, "Porusz sie za pomoca strzalek");
                else {
                    s.wykonajTure(s.getVector(), 13);
                    rysuj();
                }
                boardPanel.requestFocusInWindow();
            }
        });
        printEventsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = "";
                int n = events.size();
                if (n < 15) {
                    for (int i = n - 1; i >= 0; i--) {
                        str += events.get(i);
                        str += "\n";
                    }
                } else {
                    for (int i = n - 1; i >= n - 15; i--) {
                        str += events.get(i);
                        str += "\n";
                    }
                }
                JOptionPane.showMessageDialog(null, str);
                boardPanel.requestFocusInWindow();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String filename = JOptionPane.showInputDialog("Podaj wartość: ");

                    FileWriter f = new FileWriter(filename);
                    f.write(s.getN() + " " + s.getM() + "\n");
                    f.write(s.getVector().size() + " " + events.size() + " " + s.getTura() + " " + s.getPower() + "\n");
                    for (Organizm o : s.getVector()) {
                        f.write(o.getNazwa() + " " + o.getX() + " " + o.getY() + " " + o.getWiek() + "\n");
                    }
                    for (String s : events) {
                        f.write(s + "\n");
                    }
                    f.close();
                    JOptionPane.showMessageDialog(null, "zapisano");
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(null, "Nie udalo sie otworzyc pliku");
                }
                boardPanel.requestFocusInWindow();
            }
        });

        loadButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String filename = JOptionPane.showInputDialog("Podaj nazwe pliku: ");
                    FileReader f = new FileReader(filename);
                    BufferedReader br = new BufferedReader(f);

                    String[] firstLine = br.readLine().split(" ");
                    int n = Integer.parseInt(firstLine[0]);
                    int m = Integer.parseInt(firstLine[1]);

                    Swiat newS = new Swiat(n, m);

                    String[] secondLine = br.readLine().split(" ");
                    int size1 = Integer.parseInt(secondLine[0]);
                    int size2 = Integer.parseInt(secondLine[1]);
                    int tura = Integer.parseInt(secondLine[2]);
                    boolean power = Boolean.parseBoolean(secondLine[3]);
                    //s.wyczysc();
                    //s.setM(m);
                    //s.setN(n);

                    for (int i = 0; i < size1; i++) {
                        String[] line = br.readLine().split(" ");
                        String nazwa = line[0];
                        System.out.println(nazwa);
                        int x = Integer.parseInt(line[1]);
                        System.out.println(x);
                        int y = Integer.parseInt(line[2]);
                        System.out.println(y);
                        int wiek = Integer.parseInt(line[3]);
                        System.out.println(wiek);
                        Organizm o = null;

                        switch (nazwa) {
                            case "Antylopa":
                                o = new Antylopa(x, y, s);
                                break;
                            case "BarszczSosnowskiego":
                                o = new BarszczSosnowskiego(x, y, s);
                                break;
                            case "Czlowiek":
                                o = new Czlowiek(x, y, s);
                                ((Czlowiek) o).setTura(tura);
                                ((Czlowiek) o).setPower(power);
                                break;
                            case "Guarana":
                                o = new Guarana(x, y, s);
                                break;
                            case "Lis":
                                o = new Lis(x, y, s);
                                break;
                            case "Mlecz":
                                o = new Mlecz(x, y, s);
                                break;
                            case "Owca":
                                o = new Owca(x, y, s);
                                break;
                            case "Trawa":
                                o = new Trawa(x, y, s);
                                break;
                            case "WilczeJagody":
                                o = new WilczeJagody(x, y, s);
                                break;
                            case "Wilk":
                                o = new Wilk(x, y, s);
                                break;
                            case "Zolw":
                                o = new Zolw(x, y, s);
                                break;
                        }
                        o.setWiek(wiek);
                        newS.dodajOrganizm(o);
                    }

                    events.clear();
                    ArrayList<String> events = new ArrayList<>();
                    for (int i = 0; i < size2; i++) {
                        events.add(br.readLine());
                    }

                    f.close();
                    br.close();
                    JOptionPane.showMessageDialog(null, "wczytano");

                    //Board.this = new Board(newS);
                    Board newBoard = new Board(newS);
                    newBoard.setVisible(true);
                    dispose();

                    boardPanel.requestFocusInWindow();
                    rysuj();

                } catch (IOException err) {
                    JOptionPane.showMessageDialog(null, "Nie udalo sie otworzyc pliku");
                }
            }
        });

        legendPanel.add(nextRoundButton);
        legendPanel.add(Box.createRigidArea(new Dimension(0, 10))); // add 10 pixels of vertical space
        legendPanel.add(printEventsButton);
        legendPanel.add(Box.createRigidArea(new Dimension(0, 10))); // add 10 pixels of vertical space
        legendPanel.add(saveButton);
        legendPanel.add(Box.createRigidArea(new Dimension(0, 10))); // add 10 pixels of vertical space
        legendPanel.add(loadButton);
        legendPanel.add(Box.createRigidArea(new Dimension(0, 10))); // add 10 pixels of vertical space

        menuPanel.add(legendPanel);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0)); // add an empty border to move the panel to the left

        //przyciski


        setResizable(false);
        //pack();
        //setSize(cols * 30 + 220, Math.max(rows * 20, 800));
        setSize(1500, 1000);
        setVisible(true);

    }

    private void rysuj() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (s.getOrganizmy()[i][j] != null) {
                    panels[i][j].setBackground(s.getOrganizmy()[i][j].rysowanie());
                } else {
                    panels[i][j].setBackground(Color.WHITE);
                }
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Main.z = e.getKeyCode();
        s.wykonajTure(s.getVector(), Main.z);
        rysuj();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

/*    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / 30; // obliczenie indeksu wiersza z kliknięcia myszą
        int y = e.getY() / 30; // obliczenie indeksu kolumny z kliknięcia myszą
        if (s.getOrganizmy()[x][y] == null) { // sprawdzenie, czy pole jest wolne
            JPopupMenu popupMenu = new JPopupMenu(); // utworzenie menu podręcznego
            for (Organizm o : Swiat.getListaOrganizmow(x, y)) { // dodanie opcji dodania każdego z organizmów do menu
                JMenuItem menuItem = new JMenuItem(o.getNazwa());
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            s.dodajOrganizm(o); // dodanie organizmu do świata
                            //panels[x][y].setBackground(o.rysowanie()); // ustawienie tła panelu planszy na kolor nowego organizmu
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                popupMenu.add(menuItem);
            }
            popupMenu.show(boardPanel, e.getX(), e.getY()); // wyświetlenie menu podręcznego w miejscu kliknięcia myszą
        }
    }
}*/

    @Override
    public void mouseClicked(MouseEvent e) {
        int y = e.getX() / 30;
        int x = e.getY() / 30;
        if (s.getOrganizmy()[x][y] == null) {
            JPopupMenu popupMenu = new JPopupMenu();
            for (String nazwa : Swiat.getListaOrganizmow()) {
                JMenuItem menuItem = new JMenuItem(nazwa);
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Organizm o = null;
                            switch (nazwa) {
                                case "Antylopa":
                                    o = new Antylopa(x, y, s);
                                    break;
                                case "Lis":
                                    o = new Lis(x, y, s);
                                    break;
                                case "Owca":
                                    o = new Owca(x, y, s);
                                    break;
                                case "Wilk":
                                    o = new Wilk(x, y, s);
                                    break;
                                case "Zolw":
                                    o = new Zolw(x, y, s);
                                    break;
                                case "BarszczSosnowskiego":
                                    o = new BarszczSosnowskiego(x, y, s);
                                    break;
                                case "Guarana":
                                    o = new Guarana(x, y, s);
                                    break;
                                case "Mlecz":
                                    o = new Mlecz(x, y, s);
                                    break;
                                case "Trawa":
                                    o = new Trawa(x, y, s);
                                    break;
                                case "WilczeJagody":
                                    o = new WilczeJagody(x, y, s);
                                    break;
                            }
                            System.out.println(o.getNazwa());
                            s.dodajOrganizm(o);
                            rysuj();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                popupMenu.add(menuItem);
            }
            popupMenu.show(boardPanel, e.getX(), e.getY()); // wyświetlenie menu podręcznego w miejscu kliknięcia myszą
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
