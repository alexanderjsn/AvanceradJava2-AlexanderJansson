package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
    private JButton fileButton = new JButton("Choose file");
    private JFrame frame = new JFrame();

    public GUI() {
        setSize(1500,1500);
        setLayout(new BorderLayout());
        add(fileButton,BorderLayout.NORTH);
        fileButton.addActionListener(this);
        fileButton.setBackground(Color.GRAY);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fileButton){
        fileButton.setBackground(Color.cyan);
        }
    }

    // lägg till actionlistener

    // lägg till metod här
}
