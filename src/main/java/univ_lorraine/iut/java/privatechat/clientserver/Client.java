package univ_lorraine.iut.java.privatechat.clientserver;

import univ_lorraine.iut.java.privatechat.model.Message;
import univ_lorraine.iut.java.privatechat.model.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

        InetAddress host = InetAddress.getLocalHost();
        Socket socket = new Socket(host.getHostName(), 9876);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Scanner keyboard = new Scanner(System.in);

        var communicationThread = new ClientCommunication(socket, message -> {
            System.out.println("Message recu serveur: " + message.getContent());
        });
        communicationThread.start();

        String input = "";
        while (!input.equals("exit")) {
            input = keyboard.nextLine();
            Message message = new Message();
            message.setContent(input);
            message.setType(MessageType.MESSAGE);
            communicationThread.sendMessage(message);
        }

        communicationThread.close();
        socket.close();
        ois.close();
        oos.close();
    }
}