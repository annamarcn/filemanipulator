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
        String currentLine = null;
        String firstDateString = null;
        String secondDateString = null;
        String description = null;
        String amount = null;
        String balance = null;
        String restOfString = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        while (scanner.hasNext()) {
            currentLineStringList.add(scanner.nextLine());
        }

        for (int i = 0; i < currentLineStringList.size(); i++) { //loop over the entire document
            currentLine = currentLineStringList.get(i);
            firstDateString = currentLine.substring(0, 10);
            secondDateString = currentLine.substring(11, 21);

            restOfString = reverseString(currentLine.substring(22, currentLine.length()));
            balance = restOfString.substring(0, restOfString.indexOf("\t"));
            restOfString = restOfString.substring(balance.length()+1, restOfString.length());
            amount = restOfString.substring(1, restOfString.indexOf(" "));
            restOfString = restOfString.substring(amount.length()+2, restOfString.length());
            description = restOfString.substring(0, restOfString.length());

            balance = reverseString(balance);
            amount = reverseString(amount);
            description = reverseString(description);

            System.out.println("firstDateString = " + firstDateString + "\nsecondDateString = " + secondDateString
                    + "\ndescription = " + description + "\nbalance = " + balance + "\namount = " + amount);
            restOfString = null;
        }

    }
    public static String reverseString(String str){
        StringBuilder sb=new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }
}