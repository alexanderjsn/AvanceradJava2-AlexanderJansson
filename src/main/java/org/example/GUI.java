package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
    private JButton fileButton = new JButton("Choose file");
    private JFrame frame = new JFrame();

    public GUI() {
        frame.setSize(1500,1500);
        frame.setLayout(new BorderLayout());
        frame.add(fileButton,BorderLayout.NORTH);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fileButton){
        frame.setBackground(Color.cyan);
        }
    }

    // lägg till actionlistener

    // lägg till metod här
}
