package model;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import view.ServerManagementView;



public class ServerThread extends Thread 
{

	private ServerModel sv_Model;
	private ArrayList<String> ListClient;
	private Socket socket;
	private DataInputStream in;
	private ServerManagementView sv_Manager;
	
	public ServerThread(Socket clientSocket, ServerModel sv_Model, ServerManagementView sv_Manager)
	{
		this.socket = clientSocket;
		this.sv_Model = sv_Model;
		this.ListClient = new ArrayList<String>();
		this.sv_Manager = sv_Manager;
	}
	
	public void run() 
	{
		
		String ip = socket.getInetAddress()+"";
		ip = ip.replace("/", "");
		ListClient.add(ip);
		
		for (String client : ListClient)
		{
			this.sv_Manager.listModel.addElement(client);
		}
		
		
		
		try {
			
			in = new DataInputStream(socket.getInputStream());
			
			while (in.available() > 0)
			{
				System.out.println(in.readUTF());
			}
			
			
			
			//this.sv_Model.setPathServer(in.readUTF());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
}
