package univ_lorraine.iut.java.privatechat.clientserver;

import univ_lorraine.iut.java.privatechat.model.Message;

import java.net.InetAddress;
import java.net.Socket;

public class User {
    private InetAddress ipAdress;
    private String Username;
    private Socket socket;
    private Message messages[];

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }

    public Message[] getMessages() {
        return messages;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUsername() {
        return Username;
    }

    public InetAddress getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(InetAddress ipAdress) {
        this.ipAdress = ipAdress;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setUsername(String username) {

    }
}
