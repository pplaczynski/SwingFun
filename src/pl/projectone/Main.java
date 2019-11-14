package pl.projectone;

import java.awt.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            var ramka = new myFirstFrame("My First Frame");
            ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ramka.setVisible(true);
        });
       myCard karta = new myCard(100, 400);
       karta.createNew(100, 100);
    }
}

class myCard extends JComponent {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 500;
    private static int suite;
    private static int rank;

    public myCard(int suite, int rank) {
        this.suite = suite;
        this.rank = rank;
    }

    public void paintComponent(Graphics g) {
        Image cardImage = new ImageIcon("./png/2C.png").getImage();
        g.drawImage(cardImage, 100+suite, 100+rank, 100, 152, null);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void createNew(int x, int y) {
        //new myCard(x,y);
        repaint();
    }
}

class myFirstFrame extends JFrame {

    public myFirstFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
    }

    public myFirstFrame(String title) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int sWidth = screenSize.width-100;
        int sHeight = screenSize.height-100;
        setBounds((screenSize.width - sWidth)/2, (screenSize.height-sHeight)/2, sWidth, sHeight);
        //setBounds((sWidth - sWidth/2)/2, (sHeight - sHeight/2)/2, sWidth/2, sHeight/2);
        setTitle(title);
        //setResizable(false);
        add(new myCard(1, 4));
        //pack();
    }
}
