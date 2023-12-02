package org.example;

import com.eclipsesource.json.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

public class GUI extends JFrame implements ActionListener{

    // knapp för file chooser
    private JButton fileButton = new JButton("Choose file");
    private JFrame frame = new JFrame();

    // table settings
    private String[] columnLabel; // = {"Item", "Amount per unit", "Total amount"};
    // sätter tableModel på rowCount 0 (eftersom det ska finnas 0 rader innan innehåll tas in)
    private DefaultTableModel tableModel = new DefaultTableModel(3, 0 );

    private JTable table = new JTable(tableModel);
    private File file;


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
            //FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(Files.newBufferedReader(file.toPath()));
            //Array som fylls med cells
            String[] csvRow;

            /*****************************
                Author : Alrik He
                Date: 2023
            *******************************/
            // fixa läs första , kör en readNext()   läs in split( ",") array
            //  tableModel;// = new DefaultTableModel(columnLabel, 0 );




            // fyller Array med datan/cells från CSV till sista fil (null)
            while((csvRow = csvReader.readNext()) != null){
                //lägger in en ny rad för varje cell
                tableModel.addRow(csvRow);
            }
            //fileReader.close();
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
    private void jsonMethod()  {


        /*****************************
            Author : Alrik He
            Date: 2023
        *******************************/
               // JsonValue jv = Json.parse( file.getPath() ); //reader // path
        try {
            JsonValue jv = Json.parse(Files.newBufferedReader(file.toPath())); //reader // path
            JsonArray ja = jv.asArray();
            JsonObject firstJo = ja.get(0).asObject();
            String[] header = firstJo.names().toArray(new String[0]);
            String firstData = String.valueOf(firstJo.get(header[0]));
            System.out.println(Arrays.deepToString(header));
            System.out.println(firstData);
        }catch (IOException e){
            System.out.println(e);
        }
        //  tableModel;// = new DefaultTableModel(columnLabel, 0 );


  /*      try {
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
        } ;*/
    }


    //FileChooser
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fileButton){
            JFileChooser fileChooser = new JFileChooser();
            //sätter directory till projektets src dokument
            fileChooser.setCurrentDirectory(new File("src"));
            // lägger till filter för att kunna se om filen är en CSV fil
            FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV files", "csv");
            FileNameExtensionFilter jsonFilter = new FileNameExtensionFilter("json files", "json");
            fileChooser.addChoosableFileFilter(csvFilter);
            fileChooser.addChoosableFileFilter(jsonFilter);
            //öppnar rutan
            int response = fileChooser.showOpenDialog(null);

            // om användaren klickar öppna
            if (response == JFileChooser.APPROVE_OPTION) {
                //ta in filen som var vald
                //File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                file = fileChooser.getSelectedFile();
                // kolla om den stämmer med csvFiltret
                tableModel.setRowCount(0);


                if (csvFilter.accept(file)) {
                    //rensa table (ifall tidigare data är där)
                    //kalla csv metoden
                    csvMethod();
                }else if(jsonFilter.accept(file) ){
                    jsonMethod();
                    System.out.println("JSON");

                }
            }
        }
    }
}

