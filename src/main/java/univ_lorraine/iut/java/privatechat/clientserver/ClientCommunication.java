package univ_lorraine.iut.java.privatechat.clientserver;

import univ_lorraine.iut.java.privatechat.model.Message;
import univ_lorraine.iut.java.privatechat.model.MessageType;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class ClientCommunication extends Thread {

	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private final BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();
	private ClientCommunication.MessageHandler messageHandler;
	public void MessageThread(ClientCommunication.MessageHandler handler) {
		messageHandler = handler;
	}

	public void mess(ClientCommunication.MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	public void sendMessage(Message message) {
		messageQueue.add(message);
	}
	public interface MessageHandler {
		void handleMessage(Message message);
	}

	public ClientCommunication(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			try {
				Message message = messageQueue.take();
				messageHandler.handleMessage(message);
			} catch (InterruptedException e) {
				interrupt();
			}
		}
	}


	public void close() {
		try {
			// close resources
			ois.close();
			oos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


