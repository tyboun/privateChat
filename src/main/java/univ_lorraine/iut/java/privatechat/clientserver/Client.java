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

	public static void main(String[] args)
			throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		// get the localhost IP address, if server is running on some other IP, you need
		// to use that
		InetAddress host = InetAddress.getLocalHost(); //need to set to schoor ip at a later time
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Scanner keyboard = new Scanner(System.in);
		String ecris2 = keyboard.nextLine();
		Message message3 = new Message();
		message3.setContent(ecris2);
		message3.setType(MessageType.MESSAGE);
		var thread = new ClientCommunication(socket);
		thread.sendMessage(message3);
		thread.start();

		// establish socket connection to server
		socket = new Socket(host.getHostName(), 9876);
		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("tu peux envoyer un message");

	oos.writeObject(thread);
	oos.flush();
	// read the server response message
	ois = new ObjectInputStream(socket.getInputStream());
	Message message = (Message) ois.readObject();
	System.out.println("Message: " + message);
		/*
		oos.writeObject("Test 2");
		oos.flush();
*/
	// close resources
	ois.close();
	oos.close();
	Thread.sleep(2000);
}

	}
