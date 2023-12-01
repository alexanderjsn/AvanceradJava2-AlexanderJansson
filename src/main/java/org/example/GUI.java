package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

public class GUI extends JFrame implements ActionListener{
    private JButton fileButton = new JButton("Choose file");
    private JFrame frame = new JFrame();


    private String[] columnLabel = {"1","2","3"};
    private DefaultTableModel tableModel = new DefaultTableModel(columnLabel, 0 );

    private JTable table = new JTable(tableModel);


    public GUI() {
        setSize(1500,1500);
        setLayout(new BorderLayout());
        add(fileButton,BorderLayout.NORTH);
        fileButton.addActionListener(this);
        fileButton.setBackground(Color.GRAY);
        add(table,BorderLayout.CENTER);



        frame.pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fileButton){
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);
            fileChooser.setCurrentDirectory(new File("*"));//fixa
            FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV");
            fileChooser.addChoosableFileFilter(csvFilter);

            if (response == JFileChooser.APPROVE_OPTION){
                //lägg itll
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                if (csvFilter.accept(file)){
                    System.out.println("Funkar");
                }
            }


            /*
            String[] text = {"B","C","A"};
            Arrays.sort(text);

            for (String str : text){
                JLabel label = new JLabel(str);

                tableModel.addRow(text);*/
            }
        }
    }

    // lägg till actionlistener

    // lägg till metod här
