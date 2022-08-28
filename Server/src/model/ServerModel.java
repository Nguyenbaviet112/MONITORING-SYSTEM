package model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
	public Socket clientSocket;
	private String Path_Server;
	private WatchFolder wc;
	private ServerManagementView sv_Magenager;
	BufferedReader is;
	private BufferedWriter  bufferedWriter;
	
	
	private List<String> List_IP;
	
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
		List_IP = new ArrayList<String>();
		this.setPathServer("C:\\Users\\NguyenBaViet\\Music\\Client_01");
		
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
		
		wc = new WatchFolder(this,this.sv_Magenager);
		Thread t1 = new Thread(wc);
		t1.start();
		
		try {
			
			ServerSocket = new ServerSocket(this.port);
			
			while (true)
			{
				clientSocket = ServerSocket.accept();
				try {
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				this.ListClient = new ArrayList<String>();
				String ip = clientSocket.getInetAddress()+"";
				ip = ip.replace("/", "");
				
				
				if (List_IP.size() == 0)
				{
					this.sv_Magenager.listModel.addElement(ip);
					List_IP.add(ip);
				}
				
				else 
				{
					if (!(List_IP.contains(ip))) {
						this.sv_Magenager.listModel.addElement(ip);
						System.out.println(ip);
					}
				}
				

				
				
				try {
					
					is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					
					
					String line = null;
					
					while((line = is.readLine()) != null)
					{
						this.ListClient.add(line);
					}
					
								

					ServerInfo sv_info = new ServerInfo();

					sv_info.setSTT(this.ListClientInfo.size() + 1 + "");
					sv_info.setPathMonitoring(this.ListClient.get(0));
					sv_info.setAction(this.ListClient.get(1));
					String Time_Connect = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
							.format(Calendar.getInstance().getTime());
					sv_info.setTime(Time_Connect);
					sv_info.setIPClient(this.ListClient.get(2));
					sv_info.setDetail(this.ListClient.get(3));

					this.ListClientInfo.add(sv_info);

					this.sv_Magenager.defaultTableModel.addRow(new Object[] { sv_info.getSTT(), sv_info.getPathMonitoring(),
							sv_info.getTime(), sv_info.getAction(), sv_info.getIPClient(), sv_info.getDetail() });

					
					
				
					
					this.setPathServer(sv_info.getPathMonitoring());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	
	

	public void WriteLogFile()
	{
	
		File file = new File("log.txt");
		this.bufferedWriter = null;
		
		try 
		{
			this.bufferedWriter = new BufferedWriter(new FileWriter(file));
			
			for (ServerInfo cl : this.ListClientInfo) 
			{
				bufferedWriter.write(cl.toString());
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
		} 
		catch (IOException ex) 
		{
		// TODO Auto-generated catch block
		ex.printStackTrace();
		}

		finally {
			  
	            try {
	  
	                // always close the writer
	            	bufferedWriter.close();
	            }
	            catch (Exception e) {
	            	
	            }
            }		
		
	}
	
	
	public void Load_Log()
	{
		try(BufferedReader br = new BufferedReader(new FileReader("log.txt"))) 
		{
		    
			
			
		    String line = br.readLine();
		    String[] temp = null;

		    ServerInfo sv_info = null;
		    
		    while (line != null)
		    {
		    	sv_info = new ServerInfo();
		    	temp = line.split("-");
		    	sv_info.setSTT(temp[0]);
				sv_info.setPathMonitoring(temp[1]);
				sv_info.setAction(temp[3]);
				String Time_Connect = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
						.format(Calendar.getInstance().getTime());
				sv_info.setTime(temp[2]);
				sv_info.setIPClient(temp[4]);
				sv_info.setDetail(temp[5]);
				line = br.readLine();
				this.ListClientInfo.add(sv_info);
		    }
		    
		    for (ServerInfo cl : ListClientInfo)
		    {
		    	this.sv_Magenager.defaultTableModel.addRow(new Object[] { cl.getSTT(), cl.getPathMonitoring(),
		    			cl.getTime(), cl.getAction(), cl.getIPClient(), cl.getDetail() });
		    }

			
		    		    
		}
	
		catch (IOException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	
}

