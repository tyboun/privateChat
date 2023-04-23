package univ_lorraine.iut.java.privatechat.clientserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import univ_lorraine.iut.java.privatechat.model.Message;
import univ_lorraine.iut.java.privatechat.model.MessageType;

public class Serveur {

    private static ServerSocket server;

    private static int port = 9876;
    private static Boolean running = true;
    private static final List<ClientCommunication> clientCommunications = new ArrayList<>();

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        server = new ServerSocket(port);

        List<Thread> threadList = new ArrayList<>();
        while (running) {
            System.out.println("attente reponse client");
            Socket socket = server.accept();
            ClientCommunication communicationThread = new ClientCommunication(socket, (message) -> {
                System.out.println("Message recue de client: " + message.getContent());
                broadcastMessage(message);
            });
            Thread thread = new Thread(communicationThread);
            threadList.add(thread);
            thread.start();
            clientCommunications.add(communicationThread);


            Message welcomeMessage = new Message();
            welcomeMessage.setType(MessageType.SERVER_INFO);
            welcomeMessage.setSender("SERVER");
            welcomeMessage.setContent("bienvenue");
            communicationThread.sendMessage(welcomeMessage);
        }
        System.out.println("stop");

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        server.close();
    }

    private static void broadcastMessage(Message message) {
        for (ClientCommunication clientCommunication : clientCommunications) {
            clientCommunication.sendMessage(message);
        }
    }
}