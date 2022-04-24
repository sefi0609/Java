package com.example.dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryController {
    private boolean startUp = true;
    private myDictionary d = new myDictionary();
    @FXML
    private TextField keyField;
    @FXML
    private ListView<String> listView;
    @FXML
    private TextArea textArea;
    //after the application is loaded the initialize method is called
    @FXML
    public void initialize() {
        if (startUp) {//upload the dictionary file at start up
            File file = new File(System.getProperty("user.dir") + "\\Dictionary file.txt");//this path string should work for all users,
                                                                                                    //the Dictionary file is provided in the project folder
            try {
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    d.save(data);
                }
            } catch (FileNotFoundException e) {//popup for initial file not found
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setContentText("The initial file was not found");
            alert.show();
            }
            startUp = false;
        }
        ObservableList<String> items = FXCollections.observableArrayList (d.getKeys());
        Collections.sort(items);//sort the keys of the dictionary,
                                //this d(myDictionary) is not a sorted Data Structure!
        listView.setItems(items);
        textArea.clear();
    }
    //information for about menu item
    @FXML
    void actionAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Dictionary By Yosefi");
        alert.setContentText("This is a simple dictionary application" +
                "\nYou can search for a value by typing it in the search bar," +
                "\nYou can upload a new dictionary file and add it to the dictionary's keys," +
                "\nYou can clear all the dictionary keys and values," +
                "\nYou can update existing dictionary keys," +
                "\nYou can also add new values and remove them one by one.\n" +
                "\nIf you want to upload a file the syntax of the file should be:" +
                "\n\"key1\" : \"value1\"\n\"key2\" : \"value2\"\n\"key3\" : \"value3\"\n" );
        alert.show();
    }
    //for every string(letter) in the keyField, show the keys that start with that letter in the list view
    @FXML
    void clickForKey(KeyEvent event) {
        ObservableList<String> items = FXCollections.observableArrayList (d.startWith(keyField.getText()));
        listView.setItems(items);
        if (keyField.getText() == "")
            initialize();
    }
    //click a key in the list view to show the value of that key in the text area
    @FXML
    void clickToShow(MouseEvent event) {
        String word = d.toString(listView.getSelectionModel().getSelectedItem());
        textArea.setText(word);
    }
    //open new scene for updating a key
    //update a dictionary key, show error message if the key does not exist
    @FXML
    int clickForUpdate(ActionEvent event) throws IOException{
        Stage popupwindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("updatePopup.fxml"));
        Scene scene = new Scene(root);
        popupwindow.setTitle("Update a value");
        popupwindow.setScene(scene);
        popupwindow.showAndWait();

        KeyHolder holder = KeyHolder.getInstance();
        Key k = holder.getKey();
        if(k == null)
            return 0;//if the user closed the window without choosing a key
        for (String s : d.getKeys())
            if (k.getKey().equals(s)){
                d.insert(k.getKey(), k.getValue());
                initialize();
                return 0;//if the key exists do not show the alert
            }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("This key does not exists,\nIf you want to add it to the dictionary,\nClick the 'Enter new key' option");
        alert.show();
        return 0;
    }
    //open new scene for adding new value
    //add a new value to the dictionary, show error message if the key already exist
    @FXML
    int clickForNew(ActionEvent event) throws IOException{
        Stage popupwindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("newPopup.fxml"));
        Scene scene = new Scene(root);
        popupwindow.setTitle("Enter new value");
        popupwindow.setScene(scene);
        popupwindow.showAndWait();

        KeyHolder holder = KeyHolder.getInstance();
        Key k = holder.getKey();
        if(k == null)
            return 0;//if the user closed the window without choosing a key
        for (String s : d.getKeys())
            if (k.getKey().equals(s)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("This key already exists,\nIf you want to update it,\nClick on the 'Update a key' option");
                alert.show();
                return 0;//if the key exists, don't insert it
            }
        d.insert(k.getKey(), k.getValue());
        holder.setKey(null);//if the user try to enter a new value and then close the window without choosing a key,
                            //the key will be null so that the next time the error will not show
        initialize();
        return 0;
    }
    //close the program for close on menu item
    @FXML
    void clickToClose(ActionEvent event) {System.exit(0);}
    //remove a key from the dictionary, show error message if the key does not exist, show a confirmation message of deleting
    @FXML
    void clickToRemove(MouseEvent event) {
        String key = keyField.getText();
        if(!d.delete(key)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if(key == "")
                alert.setContentText("Please enter a key to remove");
            else
                alert.setContentText(key + " is not in the dictionary");
            alert.setTitle("Error");
            alert.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Removed");
            alert.setContentText(key + " was removed from the dictionary");
            alert.show();
        }
        initialize();
    }
    //clear all words from the dictionary, show a confirmation message of clearing
    @FXML
    void clearDictionary(MouseEvent event) {
        d = new myDictionary();
        initialize();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Dictionary Is Empty");
        alert.show();
    }
    //upload a new dictionary file, with a file chooser
    @FXML
    void clickToUpload(ActionEvent event){
        String s = "";
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        try {
            File myObj = chooser.showOpenDialog(new Stage());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                d.save(data);
            }
            initialize();
            myReader.close();
        } catch (FileNotFoundException e) {//popup for file not found
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setContentText("The file was not found");
            alert.show();
        }
    }
}
