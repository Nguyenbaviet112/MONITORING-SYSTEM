package model;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import controller.SerManagementListener;
import view.ServerManagementView;

public class ServerModel extends Thread{
	
	private ServerSocket ServerSocket;
	private int port = 3007;
	private String IP;
	public ArrayList<ServerInfo> ListClientInfo;
	public ArrayList<String> ListClient;
	private Socket clientSocket;
	private String Path_Server;
	private WatchFolder wc;
	private ServerManagementView sv_Magenager;
	BufferedReader is;
	
	
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
	
	public ServerModel(ServerManagementView sv_Magenager)
	{
		this.ListClientInfo = new ArrayList<ServerInfo>();
		this.sv_Magenager = sv_Magenager;
		this.setPort(3007);
	}
	
	
	

	
	public ArrayList<String> getListClient() {
		return ListClient;
	}

	public void setListClient(ArrayList<String> listClient) {
		ListClient = listClient;
	}

	public ArrayList<ServerInfo> getListClientInfo() {
		return ListClientInfo;
	}

	public void setListClientInfo(ArrayList<ServerInfo> listClientInfo) {
		ListClientInfo = listClientInfo;
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
			
			ServerSocket = new ServerSocket(this.port);
			
			while (true)
			{
				
				try {
					clientSocket = ServerSocket.accept();
					wc = new WatchFolder(this,this.sv_Magenager, clientSocket);
					Thread t1 = new Thread(wc);
					t1.start();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				new ServerThread(clientSocket, this, this.sv_Magenager).start();
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void run()
	{
		this.CreateServerSocket();
	}

	
	
}

