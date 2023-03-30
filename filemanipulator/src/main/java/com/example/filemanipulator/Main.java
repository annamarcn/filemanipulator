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
import java.util.ArrayList;
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

    private void loadingFile(String file, String path, Stage stage) throws FileNotFoundException {
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File(path));
        fileChooser.showOpenDialog(stage);
        File fileToRead = new File(path + file);
        Scanner scanner = new Scanner(fileToRead);
        ArrayList<String> currentLineStringList = new ArrayList<>(); //this will be used to save all the lines from the document
        //save the entire document into currentLineStringList.
        //we need to save the data somewhere so that later on we can manipulate it
        ArrayList<Transaction> transactions = new ArrayList<>();

        while (scanner.hasNext()) {
            currentLineStringList.add(scanner.nextLine());
        }

        for (int i = 0; i < currentLineStringList.size(); i++) { //loop over the entire document
            String currentLine = currentLineStringList.get(i); //save the current line
            for (int j = 0; j < currentLine.length(); j++) { //loop over the current line
            }



        /*
        for(int i = 0; i < currentLineStringList.size(); i++){
            System.out.println(currentLineStringList);
        }
         */
        }
    }
}