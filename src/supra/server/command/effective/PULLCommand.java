package supra.server.command.effective;

import java.io.File;

import supra.server.command.Command;
import supra.server.entity.Client;

public class PULLCommand extends Command{
	public PULLCommand(Client client) {
		if(client.getArgs().length == 2) {
			sendFile(client, pull(client.getArgs()[1]));
		}
	}
	
	public File pull(String path){
		return new File(path);
	}

}
