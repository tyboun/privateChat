package univ_lorraine.iut.java.privatechat.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import univ_lorraine.iut.java.privatechat.App;

public class LoginController {

    @FXML
    private void login() throws IOException, ClassNotFoundException {
        App.setRoot("chat");
        String args[] = new String[0];
        univ_lorraine.iut.java.privatechat.clientserver.Serveur.main(args);
    }
}
