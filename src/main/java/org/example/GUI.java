package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    private void csvMethod(){
        try {
            String filePath = "src/Materiallista.csv"; //fixa
            FileReader fileReader = new FileReader(filePath);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] csvRow;

            while((csvRow = csvReader.readNext()) != null){
                Arrays.sort(csvRow);
                tableModel.addRow(csvRow);
            }
            fileReader.close();
            csvReader.close();
        }

         catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } ;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fileButton){
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);
            fileChooser.setCurrentDirectory(new File("*"));//fixa
            FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV files", "csv");
            fileChooser.addChoosableFileFilter(csvFilter);

            if (response == JFileChooser.APPROVE_OPTION){
                //lägg till
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                if (csvFilter.accept(file)){
                    tableModel.setRowCount(0);
                    csvMethod();
            }}}}}

