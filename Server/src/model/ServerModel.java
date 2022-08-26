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

public class ServerModel extends Thread {
	
	private ServerSocket serverSocket;
	private int port;
	private String IP;
	
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
	
	
	
	
	
	public void get_IP_Port()
	{
		try {
			  String IP_temp = InetAddress .getLocalHost().getHostAddress();
			  this.setIP(IP_temp);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setPort(3007);
		
	}
	
	public void CreateServerSocket(int port)
	{
		try {
			
			serverSocket = new ServerSocket(port);
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
			Socket server;
			try {
				server = serverSocket.accept();
				System.out.println("Client: " + server.getLocalSocketAddress());
				System.out.println("Client: " + server.getInetAddress());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
}

