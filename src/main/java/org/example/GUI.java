package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    public GUI() {
        JFrame frame = new JFrame();
        frame.setSize(1500,1500);
        frame.setLayout(new BorderLayout());

        JButton fileButton = new JButton("Choose file");
        frame.add(fileButton,BorderLayout.NORTH);

        frame.setVisible(true);
    }

    // lägg till actionlistener
    // lägg till metod här
}
