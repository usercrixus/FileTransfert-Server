package supra.server.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import supra.server.command.CommandRouter;
import supra.server.entity.Client;
import supra.server.socket.ConnectionObserver;
import supra.server.socket.FTPServerSocket;
import supra.server.socket.ServerSocketCommand;
import supra.server.socket.ServerSocketFile;

public class ClientBuilder implements ConnectionObserver {

	FTPServerSocket serverCommand;
	FTPServerSocket serverFile;
	
	public ClientBuilder() {
		try {
			serverCommand = new ServerSocketCommand();
			serverCommand.subscribeNotifyConnection(this);
			
			Thread serverCommandThread = new Thread() {
				public void run() {
					serverCommand.start();	
			      }
			};

			serverFile = new ServerSocketFile();
			serverFile.subscribeNotifyConnection(this);
			
			Thread serverFileThread = new Thread() {
				public void run() {
					serverFile.start();
			      }
			};
			
			serverCommandThread.start();
			serverFileThread.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateAfterConnectionToCommandServer() {
		new CommandRouter(build(serverCommand));
	}
	
	@Override
	public void updateAfterConnectionToFileServer() {
		new CommandRouter(build(serverFile));
	}
	
	private Client build(FTPServerSocket server) {
		Client client = null;
		try {
			Socket socketClient = server.getClients().remove(0);
			BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			String command = in.readLine();
			String[] args = command.split(" ");
			System.out.println(args[0]);
			client = new Client(socketClient, args[0], args);
		} catch (IOException e) {
			e.printStackTrace();

		}
		return client;
	}

}
