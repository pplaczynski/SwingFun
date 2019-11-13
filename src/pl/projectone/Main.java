package pl.projectone;

import java.awt.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        EventQueue.invokeLater(() -> {
            var ramka = new myFirstFrame("My First Frame");
            ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ramka.setVisible(true);
        });
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
        int sWidth = screenSize.width;
        int sHeight = screenSize.height;
        setBounds((sWidth - sWidth/2)/2, (sHeight - sHeight/2)/2, sWidth/2, sHeight/2);
        setTitle(title);
        setResizable(false);
    }
}
