package supra.server.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSocketCommand extends ServerSocket implements FTPServerSocket{


	ArrayList<Socket> clients;
	ArrayList<ConnectionObserver> connectionObservers;

	public ServerSocketCommand() throws IOException {
		super(2100); // Server start on port 21, classic for ftp command.
		clients = new ArrayList<>();
		connectionObservers = new ArrayList<>();
	}

	public void start() {
		System.out.println("Command server launched");
		try {
			while(!isClosed()) {
				clients.add(accept());
				System.out.println("A new client just connected to command server");
				notifyConnection();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	//------------Observer-Pattern---------
	
	public void notifyConnection() {
		for (ConnectionObserver connectionObserver : connectionObservers) {
			connectionObserver.updateAfterConnectionToCommandServer();
		}
	}
	
	public void subscribeNotifyConnection(ConnectionObserver observer) {
		connectionObservers.add(observer);
	}
	
	public void unsubscribeNotifyConnection(ConnectionObserver observer) {
		connectionObservers.remove(observer);
	}
	
	//----------Getter-Setter-----------
	
	public ArrayList<Socket> getClients() {
		return clients;
	}
}
