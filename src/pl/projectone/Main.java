package pl.projectone;

import java.awt.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame ramka = new JFrame("Ramka1");
        ramka.setContentPane(new FirstForm().form1);
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.pack();
        ramka.setVisible(true);
    }
}
