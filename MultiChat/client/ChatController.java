package com.example.multchat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
// GUI controller for the chat window
public class ChatController {
    private String clientName;
    private ChatClient client;
    private ChatLogic logic;
    @FXML
    private TextField host;
    @FXML
    private Button connectBtn;

    @FXML
    private Button disconnectBtn;

    @FXML
    private TextField enterText;

    @FXML
    private TextField port;

    @FXML
    private Button sendButton;

    @FXML
    private TextArea textArea;
    // initialize the GUI on start up
    @FXML
    void initialize() {
        logic = new ChatLogic();
        uiStateDisconnected();
    }
    // connect to the chat (server)
    @FXML
    void chatConnect(MouseEvent event) {
        // after the connection is established ask the client has for his name
        TextInputDialog td = new TextInputDialog("Enter name");
        // check for mistakes in the input of host and port
        if(!(logic.isValidInetAddress(host.getText()) || host.getText().equals("localhost"))) {
            showAlertGeneral("Please provide a valid IP address");
            return;
        }
        if(!(logic.isValidPort(port.getText()))){
            showAlertGeneral("Please provide a valid port number");
            return;
        }
        // connect to the server
        if(host.getText() != "" && port.getText() != "") {
            // enter name on chat
            do {
                td.setTitle("Your action is needed");
                td.setHeaderText("Please enter your name");
                td.showAndWait();
                clientName = td.getEditor().getText();
            } while (clientName.equals("Enter name"));
            // adjust the GUI
            connectBtn.setDisable(true);
            disconnectBtn.setDisable(false);
            host.setDisable(true);
            port.setDisable(true);
            try {
                client = new ChatClient(host.getText(), Integer.parseInt(port.getText()), clientName);
            } catch (IOException e) { 
                showAlertTimeOut(); 
                return; 
            }
            textArea.setText(client.readFromBuffer());
            // listen for messages from the chat buddy (server) until disconnect
            client.listenForMessages(this);
        }
        else
            showAlertGeneral("Please provide IP and PORT");
    }
    // disconnect from the chat (server)
    @FXML
    void chatDisconnect(MouseEvent event) {
        try {
            // send the chat buddy a message that you are disconnecting
            client.writeToBuffer("Disconnected");
            client.closeResources();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Error while disconnecting");
        }
        uiStateDisconnected();
        textArea.setText("Disconnected");
    }
    // show about the chat
    @FXML
    void clickAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("About the author");
        alert.setContentText("Author: Yosefi kroytoro\nThis is a chat application for two people to chat with each other");
        alert.show();
    }
    // click to close the window
    @FXML
    void clickToClose(ActionEvent event) { System.exit(0);}
    // send a message with keyboard
    @FXML
    void sendMessKeyboard(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER) && enterText.getText() != "")
            sendMessage();
    }
    // send a message with button
    @FXML
    void sendMessMouse(MouseEvent event) {
        if (enterText.getText() != "")
            sendMessage();
    }
    // send a message to the chat buddy
    void sendMessage() {
        try {
            client.writeToBuffer(clientName + " : " + enterText.getText());
            setTextArea("You: " + enterText.getText() + "\n");

        } catch (IOException e) { 
            showAlertTimeOut(); 
            return; 
        }
        enterText.clear();
    }
    // enables/disables the buttons and text fields accordingly to the fact that we
    // are now connected
    public void uiStateConnected() {
        connectBtn.setDisable(true);
        host.setDisable(true);
        port.setDisable(true);
        disconnectBtn.setDisable(false);
        sendButton.setDisable(false);
        enterText.setDisable(false);
    }
    // enables/disables the buttons and text fields accordingly to the fact that we
    // are now disconnected
    public void uiStateDisconnected() {
        connectBtn.setDisable(false);
        host.setDisable(false);
        port.setDisable(false);
        enterText.setDisable(true);
        disconnectBtn.setDisable(true);
        sendButton.setDisable(true);
    }
    // shows an alert can't connect to the server
    public void showAlertTimeOut(){
        connectBtn.setDisable(false);
        disconnectBtn.setDisable(true);
        host.setDisable(false);
        port.setDisable(false);
        showAlertGeneral("Can't connect to the server");
    }
    public void showAlertGeneral(String s){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(s);
        alert.show();
    }
    // adds the text to the chat's text area in a new line
    public void setTextArea(String text){ textArea.setText(textArea.getText() + text); }
}
