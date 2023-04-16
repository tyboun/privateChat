package univ_lorraine.iut.java.privatechat.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import univ_lorraine.iut.java.privatechat.App;
import univ_lorraine.iut.java.privatechat.clientserver.User;

public class LoginController {

    public PasswordField password;
    public TextArea id;

    @FXML
    private void login() throws IOException, ClassNotFoundException {
        File passwordFile = new File("src/main/java/univ_lorraine/iut/java/privatechat/data/password.pwd");
        Path path = passwordFile.toPath();
        //check if the file exists if not create it
        if (!passwordFile.exists()) {
            passwordFile.createNewFile();
            Files.write(path, password.getText().concat("\n").getBytes());
            Files.write(path, id.getText().concat("\n").getBytes(), StandardOpenOption.APPEND);
            App.setRoot("chat");
        }
        //read the password file to see if the credentials are good
        List<String> idMdp = Files.readAllLines(path);
        if (idMdp.get(0).equals(id.getText()) && idMdp.get(1).equals(password.getText())) {
            App.setRoot("chat");
        } else {
            password.setText("");
            id.setText("");
        }
    }
}

