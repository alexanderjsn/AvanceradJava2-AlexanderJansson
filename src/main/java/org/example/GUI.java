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

    // knapp för file chooser
    private JButton fileButton = new JButton("Choose file");
    private JFrame frame = new JFrame();

    // table settings
    private String[] columnLabel = {"Item", "Amount per unit", "Total amount"};
    // sätter tableModel på rowCount 0 (eftersom det ska finnas 0 rader innan innehåll tas in)
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


    // metod som läser in CSV celler
    private void csvMethod(){
        try {
            //filväg till CSV fil
            String filePath = "src/Materiallista.csv";
            //readers som läser in filen
            FileReader fileReader = new FileReader(filePath);
            CSVReader csvReader = new CSVReader(fileReader);
            //Array som fylls med cells
            String[] csvRow;

            // fyller Array med datan/cells från CSV till sista fil (null)
            while((csvRow = csvReader.readNext()) != null){
                //lägger in en ny rad för varje cell
                tableModel.addRow(csvRow);


            }
            fileReader.close();
            csvReader.close();
        }

        //Exceptions
         catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } ;
    }


    //FileChooser
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fileButton){
            JFileChooser fileChooser = new JFileChooser();
            //öppnar rutan
            int response = fileChooser.showOpenDialog(null);
            //sätter directory till projektets src dokument
            fileChooser.setCurrentDirectory(new File("src"));
            // lägger till filter för att kunna se om filen är en CSV fil
            FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV files", "csv");
            fileChooser.addChoosableFileFilter(csvFilter);

            // om användaren klickar öppna
            if (response == JFileChooser.APPROVE_OPTION){
                //ta in filen som var vald
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                // kolla om den stämmer med csvFiltret
                if (csvFilter.accept(file)){
                    //rensa table (ifall tidigare data är där)
                    tableModel.setRowCount(0);
                    //kalla csv metoden
                    csvMethod();
                }}}}}

