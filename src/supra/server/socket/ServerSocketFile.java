package supra.server.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSocketFile extends ServerSocket implements FTPServerSocket {
	ArrayList<Socket> clients;
	ArrayList<ConnectionObserver> connectionObservers;

	public ServerSocketFile() throws IOException {
		super(2200); // Server start on port 20, classic for ftp data.
		clients = new ArrayList<>();
		connectionObservers = new ArrayList<>();
	}

	public void start() {
		System.out.println("File server launched");
		try {
			while(!isClosed()) {
				clients.add(accept());
				System.out.println("A new client just connected to file server");
				notifyConnection();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	//------------Observer-Pattern---------
	
	public void notifyConnection() {
		for (ConnectionObserver connectionObserver : connectionObservers) {
			connectionObserver.updateAfterConnectionToFileServer();
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