package com.example.filemanipulator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main extends Application {
    Stage window;
    Scene scene1;
    String fileName = "TestFile.txt";
    String pathName = "/Users/annamarcinkowska/Desktop/Test/";
    FileChooser fileChooser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        window = stage;
        fileChooser = new FileChooser();

        //--------------------------------------MENU BAR----------------------------------------------------------------//
        //File menu
        Menu fileMenu = new Menu("_File");

        //Menu items
        MenuItem newFile = new MenuItem("New...");
        newFile.setOnAction(e -> System.out.println("Create a new file..."));
        fileMenu.getItems().add(newFile);

        MenuItem loadFile = new MenuItem("Load file...");
        loadFile.setOnAction(e -> {
            try {
                loadingFile(fileName, pathName, stage);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        });
        MenuItem saveAs = new MenuItem("Save As...");
        MenuItem exit = new MenuItem("Exit...");

        fileMenu.getItems().add(loadFile); //the three dots means that when you click it a new window opens
        fileMenu.getItems().add(saveAs);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(exit);

        //Main menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);

        BorderPane layout = new BorderPane();
        menuBar.prefWidthProperty().bind(layout.widthProperty());
        layout.setTop(menuBar);

        scene1 = new Scene(layout, 500, 500);

        window.setScene(scene1);
        window.setTitle("Title here");
        window.show();


    }

    private void loadingFile(String file, String path, Stage stage) throws FileNotFoundException, ParseException {
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File(path));
        fileChooser.showOpenDialog(stage);
        File fileToRead = new File(path + file);
        Scanner scanner = new Scanner(fileToRead);
        ArrayList<String> currentLineStringList = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();
        String[] currentLine = new String[0];
        int count = 0;
        int countTransaction = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        while (scanner.hasNext()) {
            currentLineStringList.add(scanner.nextLine());
        }

        for (int i = 0; i < currentLineStringList.size(); i++) { //loop over the entire document
            currentLine = currentLineStringList.get(i).split("\t"); //save the current line & split on tabs
            for(int j = 0; j < currentLine.length; j++){
                String tempString = null;
                String tempAmount = null;

                if(!Character.isDigit(currentLine[j].charAt(0))){ //if string starts with a letter
                    for(int x = 0; x < currentLine[j].length(); x++){
                        if(!Character.isDigit(currentLine[j].charAt(x))){ //while character is not a digit
                            tempString += currentLine[j]; //add to tempstring
                            x++;
                        }
                        if(Character.isDigit(currentLine[j].charAt(x))){
                            tempAmount += currentLine[j];
                            x++;
                        }
                    }
                    transactions.get(count).setDescription(tempString);
                    transactions.get(count).setAmount(Float.parseFloat(tempAmount));
                }
                transactions.get(count).setAccountingDate(formatter.parse(currentLine[j]));
                transactions.get(count).setTransactionDate(formatter.parse(currentLine[j+1]));
                transactions.get(count).setBalance(Float.parseFloat(currentLine[j+2]));
            }
        }

            /*
            for(int j = 0; j < count; j++){
                transactions.get(countTransaction).setAccountingDate(formatter.parse(currentLine[j]));
                transactions.get(countTransaction).setTransactionDate(formatter.parse(currentLine[j+1]));
                countTransaction++;
                count = count + 4;
            }
             */


    }
}