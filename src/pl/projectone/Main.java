package pl.projectone;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        myCard cardsPrint = new myCard();
        cardsPrint.addCard(new guiCard(1, 2, 1, 3));
        cardsPrint.addCard(new guiCard(2, 6, 2, 3));
        cardsPrint.addCard(new guiCard(3, 6, 3, 3));
        cardsPrint.addCard(new guiCard(3, 6, 1, 1));
        cardsPrint.addCard(new guiCard(3, 6, 1, 2));

        EventQueue.invokeLater(() -> {
            var ramka = new myFirstFrame("My First Frame");
            ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ramka.add(cardsPrint);
            ramka.setVisible(true);
        });

        cardsPrint.addCard(new guiCard(4, 2, 4, 3));




    }
}

class guiCard {

    private int suite;
    private int rank;
    private String cardImagePath;
    private int corX;
    private int corY;
    private int playerType; // 1 - dealer, 2 - computer, 3 - human
    private int sizeX; // different sizes for player, dealer and computer
    private int sizeY; // different sizes for player, dealer and computer


    public guiCard(int suite, int rank, int handSize, int playerType) {
        this.suite = suite;
        this.rank = rank;
        String suitePath = "";
        if (suite == 1) suitePath = "H";
        if (suite == 2) suitePath = "D";
        if (suite == 3) suitePath = "C";
        if (suite == 4) suitePath = "S";
        cardImagePath = "./png/"+rank+""+suitePath+".png";
        this.playerType = playerType;
        if (playerType == 1) { // dealer
            corY = 100;
            corX = 100;
            sizeX = 100;
            sizeY = 152;
        }
        if (playerType == 2) { // computer
            corY = 100;
            corX = 500;
            sizeX = 70;
            sizeY = 105;
        }
        if (playerType == 3) { // human
            corY = 500;
            corX = (handSize-1) * 120;
            sizeX = 100;
            sizeY = 152;
        }
    }

    public int getCorX() {
        return corX;
    }

    public int getCorY() {
        return corY;
    }

    public void setCorX(int corX) {
        this.corX = corX;
    }

    public void setCorY(int corY) {
        this.corY = corY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public String getCardImagePath() {
        return cardImagePath;
    }

    public void setCardImagePath(String cardImagePath) {
        this.cardImagePath = cardImagePath;
    }
}

class myCard extends JComponent {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 500;
    private ArrayList<guiCard> guiCards;
    private ArrayList<Rectangle2D> cardBounds;
    private guiCard currCard;
    private Rectangle2D currBound;
    private int currB = -1;
    private int currC = -1;

    public myCard() {
        guiCards = new ArrayList<>();
        cardBounds = new ArrayList<>();
        addMouseMotionListener(new MouseMotionHandler());
        //addMouseListener(new MouseHandler());
    }

    public void paintComponent(Graphics g) {
        Image cardImage;

        for (guiCard cards : guiCards) {
            cardImage = new ImageIcon(cards.getCardImagePath()).getImage();
            cardBounds.add(new Rectangle2D.Double(cards.getCorX(), cards.getCorY(), cards.getSizeX(), cards.getSizeY()));

            g.drawImage(cardImage,
                    cards.getCorX(),
                    cards.getCorY(),
                    cards.getSizeX(),
                    cards.getSizeY(),
                    null);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void addCard(guiCard gC) {
        guiCards.add(gC);
        repaint();
    }

    public int checkBounds(Point2D point) {
        for (Rectangle2D rects : cardBounds) {
            if (rects.contains(point)) return cardBounds.indexOf(rects);
        }
        return -1;
    }

    private class MouseHandler extends MouseAdapter {

        public void mouseClicked(MouseEvent mEvent) {
            currB = checkBounds(mEvent.getPoint());
            if (currB > -1) {
                guiCards.get(currB).setCorY(guiCards.get(currB).getCorY()-50);
                cardBounds.get(currB).setFrame(
                        cardBounds.get(currB).getX(),
                        cardBounds.get(currB).getY()-50,
                        cardBounds.get(currB).getWidth(),
                        cardBounds.get(currB).getHeight());
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (currB > -1) {
                guiCards.get(currB).setCorY(guiCards.get(currB).getCorY()+50);
                cardBounds.get(currB).setFrame(
                        cardBounds.get(currB).getX(),
                        cardBounds.get(currB).getY()+50,
                        cardBounds.get(currB).getWidth(),
                        cardBounds.get(currB).getHeight());
                repaint();
            }
        }
    }

    private class MouseMotionHandler implements MouseMotionListener {

        public void mouseMoved(MouseEvent mEvent) {
            //if (checkBounds(mEvent.getPoint()) == -1) setCursor(Cursor.getDefaultCursor());
            //else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

            currB = checkBounds(mEvent.getPoint());

            if (currC != currB && currC > -1) {
                guiCards.get(currC).setCorY(guiCards.get(currC).getCorY()+50);
                //cardBounds.get(currC).setFrame(
                //        cardBounds.get(currC).getX(),
                //        cardBounds.get(currC).getY()+50,
                //        cardBounds.get(currC).getWidth(),
                //        cardBounds.get(currC).getHeight());
                repaint();
                currC = -1;
            }

            if ((currB > -1) && (cardBounds.get(currB).getY() == guiCards.get(currB).getCorY())) {
                guiCards.get(currB).setCorY(guiCards.get(currB).getCorY()-50);
                //cardBounds.get(currB).setFrame(
                //        cardBounds.get(currB).getX(),
                //        cardBounds.get(currB).getY()-50,
                //        cardBounds.get(currB).getWidth(),
                //        cardBounds.get(currB).getHeight());
                currC = currB;
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent mEvent) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
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
        // add(new myCard());
        //setResizable(false);
        //pack();
    }
}
