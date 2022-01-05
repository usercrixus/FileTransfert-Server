package supra.server.entity;

import java.net.Socket;

public class Client {
	
	private Command command;
	private String[] args;
	private Socket socket;
	
	public Client(Socket socket, String command, String[] args) {
		try {
			this.command = Command.valueOf(command);			
		} catch (Exception e) {
			System.out.println("Bad command used");
			e.printStackTrace();
			this.command = Command.ERROR;
		}
		this.args = args;
		this.socket = socket;
	}

	public Command getCommand() {
		return command;
	}
	
	public String[] getArgs() {
		return args;
	}

	public Socket getSocket() {
		return socket;
	}


}
