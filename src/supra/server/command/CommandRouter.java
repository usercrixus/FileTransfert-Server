package supra.server.command;

import supra.server.command.effective.CDCommand;
import supra.server.command.effective.LSCommand;
import supra.server.command.effective.PULLCommand;
import supra.server.entity.Client;

public class CommandRouter{
	public CommandRouter(Client client) {
		switch (client.getCommand()) {
		case LS: {
			new LSCommand(client);
			break;
		}
		case CD: {
			new CDCommand(client);
			break;
		}
		case PULL: {
			new PULLCommand(client);
			break;
		}
		default:
			
		}
	}
	
}
