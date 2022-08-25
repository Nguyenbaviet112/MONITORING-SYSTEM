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

public class ServerModel {
	
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
	
	public void CreateSocketServer(int port)
	{
		try {
			
			ServerSocket serverSocket = new ServerSocket(port);
			
			
			while (true)
			{
				Socket socket = serverSocket.accept();
				
				SocketAddress socketAddress = socket.getRemoteSocketAddress();
				
				if (socketAddress instanceof InetSocketAddress) {
				    InetAddress inetAddress = ((InetSocketAddress)socketAddress).getAddress();
				    if (inetAddress instanceof Inet4Address)
				        System.out.println("IPv4: " + inetAddress);
				    else if (inetAddress instanceof Inet6Address)
				        System.out.println("IPv6: " + inetAddress);
				    else
				        System.err.println("Not an IP address.");
				} else {
				    System.err.println("Not an internet protocol socket.");
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
}
