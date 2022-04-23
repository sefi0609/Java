package com.example.dictionary;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
//New stage to update a word, "Update a key" in the menu item
public class UpdatePopupController {
    @FXML
    private TextField newKey;
    @FXML
    private TextArea keyValue;
    @FXML
    private Button okButton;

    //When the user clicks the "OK" button, the new word is added to the dictionary
    //if the user did not enter a key or a value, an alert is shown and the user can try again
    //the return in this case is to the updatePopup, to avoid entering incomplete word
    @FXML
    int clickOk(MouseEvent event) {
        boolean isKey = false;//boolean to check if the key is valid
        if (newKey.getText().isEmpty() || keyValue.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You need to enter a key and value,\nPlease try again");
            alert.showAndWait();
            return 0;//exit the function if the user did not enter a key or value
        }
        // get a handle to the stage
        Stage stage = (Stage) okButton.getScene().getWindow();
        // do what you have to do
        stage.close();
        Key k = new Key(newKey.getText(), keyValue.getText());
        KeyHolder holder = KeyHolder.getInstance();
        holder.setKey(k);
        return 0;

    }
}
