package model;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;



public class ServerThread extends Thread 
{

	private ServerModel sv_Model;
	private ArrayList<String> ListClient;
	private Socket socket;
	private DataInputStream in;
	
	public ServerThread(Socket clientSocket, ServerModel sv_Model)
	{
		this.socket = clientSocket;
		this.sv_Model = sv_Model;
		this.ListClient = new ArrayList<String>();
	}
	
	public void run() 
	{
		
		String ip = socket.getInetAddress()+"";
		ip = ip.replace("/", "");
		ListClient.add(ip);
		
		for (String client : ListClient)
		{
			System.out.println(client);
		}
		
		
		
		try {
			in = new DataInputStream(socket.getInputStream());
			this.sv_Model.setPathServer(in.readUTF());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
}
