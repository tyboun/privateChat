package univ_lorraine.iut.java.privatechat.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import univ_lorraine.iut.java.privatechat.App;

public class ChatController {

    public TextArea TextShowed;
    //TextArea.setEditable(false);
    public TextField messSend;
    public Button buttonSend;

    @FXML
    private void logout() throws IOException {
        App.setRoot("login");
    }
    private void send() throws IOException {

        App.setRoot("login");
    }
    private void receive() throws IOException {

        App.setRoot("login");
    }
    private void ajoutContact() throws IOException {

        App.setRoot("login");
    }
}