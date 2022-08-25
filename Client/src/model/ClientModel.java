package model;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientModel {
	
	public void ConnetServer(int Port, String IP)
	{
		try {
			Socket socket = new Socket(IP, Port);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
