package model;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ServerModel{
	
	private ServerSocket ServerSocket;
	private int port;
	private String IP;
	private ArrayList<String> ListClient;
	private Socket clientSocket;
	private String Path_Server;
	private WatchFolder wc;

	
	
	public String getPath_Server()
	{
		return this.Path_Server;
	}
	
	public void setPathServer(String path_Server)
	{
		this.Path_Server = path_Server;
	}

	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	
	public ServerModel()
	{
		this.ListClient = new ArrayList<String>();
		this.setPort(3007);
	}
	
	
	public ArrayList<String> getListClient() {
		return ListClient;
	}
	public void setListClient(ArrayList<String> listClient) {
		ListClient = listClient;
	}
	
	
	public void get_IP_Port()
	{
		try {
			  String IP_temp = InetAddress .getLocalHost().getHostAddress();
			  this.setIP(IP_temp);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void CreateServerSocket()
	{
		this.wc = new WatchFolder();
		Thread t1 = new Thread(wc);
		t1.start();
		
		try {
			
			ServerSocket = new ServerSocket(this.port);
			while (true)
			{
				
				try {
					clientSocket = ServerSocket.accept();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				new ServerThread(clientSocket, this).start();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	
	
}

