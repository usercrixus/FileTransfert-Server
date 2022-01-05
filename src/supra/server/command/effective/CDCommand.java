package supra.server.command.effective;

import java.io.File;
import java.io.IOException;

import supra.server.command.Command;
import supra.server.entity.Client;

public class CDCommand extends Command{
	public CDCommand(Client client) {
		if(client.getArgs().length == 2) {
			sendCommandResponse(client, cd(client.getArgs()[1]));
		}
	}

	public String cd(String path) {
		String response = "false";
		try {
			File directoryPath = new File(path);
			if(directoryPath.isDirectory()) {
				response = directoryPath.getCanonicalPath();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
