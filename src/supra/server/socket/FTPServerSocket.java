package supra.server.socket;

import java.net.Socket;
import java.util.ArrayList;

public interface FTPServerSocket {
	public void subscribeNotifyConnection(ConnectionObserver observer);
	public void unsubscribeNotifyConnection(ConnectionObserver observer);
	
	public void start();
	public ArrayList<Socket> getClients();
	
}
