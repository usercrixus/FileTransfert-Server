package supra.server.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import supra.server.command.CommandRouter;
import supra.server.entity.Client;

public class ClientBuilder {

	ServerSocket serverCommand;
	ServerSocket serverFile;
	
	public ClientBuilder() {
		try {
			serverCommand = new ServerSocket(2100);
			Thread serverCommandThread = new Thread() {
				public void run() {
					while(!serverCommand.isClosed()) {
						try {
							CommandRouter.route(build(serverCommand.accept()));
							System.out.println("A new client just connected to command server");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			      }
			};

			serverFile = new ServerSocket(2200);
			Thread serverFileThread = new Thread() {
				public void run() {
					while(!serverFile.isClosed()) {
						try {
							CommandRouter.route(build(serverFile.accept()));
							System.out.println("A new client just connected to files server");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			      }
			};
			
			serverCommandThread.start();
			serverFileThread.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Client build(Socket socketClient) {
		Client client = null;
		try {
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
