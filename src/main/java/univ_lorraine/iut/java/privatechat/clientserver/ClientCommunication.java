package univ_lorraine.iut.java.privatechat.clientserver;

import univ_lorraine.iut.java.privatechat.model.Message;
import univ_lorraine.iut.java.privatechat.model.MessageType;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientCommunication extends Thread {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private final BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();
    private final MessageHandler messageHandler;

    public interface MessageHandler {
        void handleMessage(Message message);
    }

    public ClientCommunication(Socket socket, MessageHandler messageHandler) {
        super();
        this.socket = socket;
        this.messageHandler = messageHandler;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) {
        messageQueue.add(message);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message receivedMessage = (Message) ois.readObject();
                if (receivedMessage.getType() == MessageType.MESSAGE) {
                    System.out.println(receivedMessage.getSender() + ": " + receivedMessage.getContent());
                } else if (receivedMessage.getType() == MessageType.SERVER) {
                    System.out.println("SERVER: " + receivedMessage.getContent());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}