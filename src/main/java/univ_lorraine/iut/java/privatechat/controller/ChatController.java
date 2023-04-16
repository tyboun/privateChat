package univ_lorraine.iut.java.privatechat.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import univ_lorraine.iut.java.privatechat.App;
import univ_lorraine.iut.java.privatechat.clientserver.User;

public class ChatController {

    public TextArea TextShowed;
    //TextArea.setEditable(false);
    public TextField messSend;
    public Button buttonSend;

    public ListView contacts;
    public Button reloadContactList;

    @FXML
    private void logout() throws IOException {
        App.setRoot("login");
    }
    private void send() throws IOException {

    }
    private void receive() throws IOException {

    }
    private void ajoutContact() throws IOException {
        File passwordFile = new File("src/main/java/univ_lorraine/iut/java/privatechat/data/contacts.pwd");
        Path path = passwordFile.toPath();

    }
    public User[] getContact() throws IOException {
        Path path = Paths.get("src/main/java/univ_lorraine/iut/java/privatechat/data/", "contacts.pwd");
        List<String> result = new ArrayList<>();

        if (Files.exists(path)) {
            var lines = Files.readAllLines(path);
            for (String line : lines) {
                result.add(line);
                result.add("\n"); // ajoute un séparateur de ligne
            }
        } else {
            System.err.println("Error: File not found: " + path.toAbsolutePath());
        }

// convertit la liste en tableau de chaînes de caractères
        String[] input = result.toArray(new String[0]);
        User[] contacts = new User[input.length];
        for (int i = 0; i < input.length; i++) {
            String[] contact = input[i].split(" ");
            InetAddress ip = InetAddress.getByName(contact[0]);
           // a voir comment mettre en place plus tard
           // contacts[i] = new User(ip,contact[1],contact[2],contact[3]);
        }
        return contacts;
    }
    public void listContacts() throws IOException {
        File contactFile = new File("src/main/java/univ_lorraine/iut/java/privatechat/data/contacts.pwd");
        Path path = contactFile.toPath();
        //check if the file exists if not create it
        if (!contactFile.exists()) {
            contactFile.createNewFile();
        }
        //read and add contacts to the list
        ObservableList<User> contacts = FXCollections.observableArrayList();
        ListView<User> ListView = new ListView<User>(contacts);
        // listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // listView.getSelectionModel().selectIndices();
       // listView.getFocusModel().focus(0);


    }
    public  void reloadContactList() throws IOException {
        this.contacts.getItems().clear();
        listContacts();

    }
}