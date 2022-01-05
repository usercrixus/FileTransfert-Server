package supra.server.command.effective;

import java.io.File;

import supra.server.command.Command;
import supra.server.entity.Client;

public class LSCommand extends Command {
	
	public LSCommand(Client client) {
		if(client.getArgs().length == 2) {
			sendCommandResponse(client, ls(client.getArgs()[1]));
		}else if(client.getArgs().length == 1) {
			sendCommandResponse(client, ls("./") );
		}
	}
	
	public String ls(String path) {
		StringBuilder result = new StringBuilder();
		
		File directoryPath = new File(path);
		if(directoryPath.isDirectory()) {
			File[] fileList =  directoryPath.listFiles();
			if(fileList != null) {
				for (File file : fileList) {
					if(file.isDirectory()) result.append("./");
					result.append(file.getName());
					result.append("  ");
				}			
			}			
		}else {
			result.append(path + " isnt a directory path");
		}

		return result.toString();
	}

}
