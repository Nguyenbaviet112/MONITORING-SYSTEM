package model;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ServerModel extends Thread{
	
	private ServerSocket serverSocket;
	private int port;
	private String IP;
	private ArrayList<String> ListClient;
	private Socket server;
	
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
	
		try {
			
			serverSocket = new ServerSocket(this.port);
			start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void run()
	{
		while (true)
		{
			
			try {
				server = serverSocket.accept();
				String ip = server.getInetAddress()+"";
				ip = ip.replace("/", "");
				ListClient.add(ip);
				for (String client : ListClient) {
					System.out.println(client);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
}

