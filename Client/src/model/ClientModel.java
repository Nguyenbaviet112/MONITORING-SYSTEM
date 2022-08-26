package model;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientModel {
	
	public boolean ConnetServer(int Port, String IP)
	{
		try {
			
			Socket socket = new Socket(IP, Port);
			socket.setSoTimeout(100);
			System.out.println("Client: " + socket.getLocalAddress() + " " + socket.getLocalPort());
			System.out.println("Server: " + socket.getInetAddress() + " " + socket.getPort());
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
			
		}
		return true;
	}
	
}
