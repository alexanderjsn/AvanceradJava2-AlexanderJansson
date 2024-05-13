package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GUI extends JFrame implements ActionListener{

    // knapp för file chooser
    private JButton fileButton = new JButton("Choose file");

    // table column  - byggs på i metoder
    private String[] columnLabel = {"TEST"};
    // sätter tableModel på rowCount 0 (eftersom det ska finnas 0 rader innan innehåll tas in)
    private DefaultTableModel tableModel = new DefaultTableModel(columnLabel, 0 );
    private TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
    private JTable table = new JTable(tableModel);
    private File file;

    public GUI() {
        setSize(1500,1500);
        setLayout(new BorderLayout());
        add(fileButton,BorderLayout.NORTH);
        fileButton.addActionListener(this);
        fileButton.setBackground(Color.GRAY);
        table.setRowSorter(sorter);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }


    // metod som läser in CSV celler
    private void csvMethod(){
        try {
            //readers som läser in filen
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
        
            String[] csvColumn = csvReader.readNext();

            if(csvColumn != null){
                tableModel.setColumnIdentifiers(csvColumn);
            }
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

    private void jsonMethod(){
        try{

            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder jsonData = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null){
                jsonData.append(line);
            }


            String json = jsonData.toString();

            Gson gson = new Gson();

            java.lang.reflect.Type listType = new TypeToken<List<JSONBlueprint>>(){}.getType();
            List<JSONBlueprint> jsonList = gson.fromJson(json, listType);

            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(json);
            
            if(jsonTree.isJsonArray()){
                JsonArray jsonArray = jsonTree.getAsJsonArray();
                if (!jsonArray.isEmpty()) {
                    JsonObject first = jsonArray.get(0).getAsJsonObject();
                    Set<String> jsonKeys = first.keySet();
                        columnLabel = jsonKeys.toArray(new String[0]);
                        tableModel.setColumnIdentifiers(columnLabel);
                }                            
            }
            for(JSONBlueprint jsonBlueprint : jsonList){
                tableModel.addRow(new Object[]{
                    jsonBlueprint.getOrderDate(),
                    jsonBlueprint.getRegion(),
                    jsonBlueprint.getRep1(),
                    jsonBlueprint.getRep2(),
                    jsonBlueprint.getItem(),
                    jsonBlueprint.getUnits(),
                    jsonBlueprint.getUnitCost(),
                    jsonBlueprint.getTotal()
                });
            }
            reader.close();
    }
        catch (Exception e){
            throw new RuntimeException(e);
        }
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
               fileChooser.addChoosableFileFilter(csvFilter);
                  // lägger till filter för att kunna se om filen är en CSV fil
                FileNameExtensionFilter jsonFilter = new FileNameExtensionFilter("JSON files", "json");
                fileChooser.addChoosableFileFilter(jsonFilter);


            //öppnar rutan
            int response = fileChooser.showOpenDialog(null);

            // om användaren klickar öppna
            if (response == JFileChooser.APPROVE_OPTION){
                //ta in filen som var vald
                 file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                // kolla om den stämmer med csvFiltret
                if (csvFilter.accept(file)){
                    //rensa table (ifall tidigare data är där)
                    tableModel.setRowCount(0);
                    //kalla csv metoden
                    csvMethod();
                } else if(jsonFilter.accept(file)){
                    tableModel.setRowCount(0);
                    jsonMethod();
                }
            }
        }
    }
}

