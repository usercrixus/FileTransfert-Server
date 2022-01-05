package supra.server.command;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import supra.server.entity.Client;

public class Command {
	
	public void sendCommandResponse(Client client, String result) {
		try {
			PrintWriter writer = new PrintWriter(client.getSocket().getOutputStream());
			writer.println(result);
			writer.flush();
			client.getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendFile(Client client, File file) {
		try {
			int count;
			byte[] buffer = new byte[1024];
			
			OutputStream out = client.getSocket().getOutputStream();
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			while ((count = in.read(buffer)) > 0) {
			     out.write(buffer, 0, count);
			     out.flush();
			}
			in.close();
			out.close();
			client.getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
