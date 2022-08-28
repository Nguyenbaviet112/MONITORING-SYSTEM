package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import view.ServerManagementView;



public class ServerThread extends Thread 
{

	private ServerModel sv_Model;
	private Socket socket;
	private DataInputStream in;
	BufferedReader is;
	private ServerManagementView sv_Manager;
	
	public ServerThread(Socket clientSocket, ServerModel sv_Model, ServerManagementView sv_Manager)
	{
		this.socket = clientSocket;
		this.sv_Model = sv_Model;
	
		this.sv_Manager = sv_Manager;
	}
	
	public void run() 
	{
		this.sv_Model.ListClient = new ArrayList<String>();
		String ip = socket.getInetAddress()+"";
		ip = ip.replace("/", "");
		
		
		
		try {
			
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			String line = null;
			
			while((line = is.readLine()) != null)
			{
				this.sv_Model.ListClient.add(line);
			}
			
			
			

			if (this.sv_Model.ListClientInfo.size() == 0) {
				this.sv_Manager.listModel.addElement(ip);
			}
			else
			{
				int temp = 1;
				
				for (ServerInfo sv : this.sv_Model.ListClientInfo)
				{
					
					if ((sv.getIPClient().equals(ip))) {
						temp = 0;
					}
					
				}

				if (temp == 1)
				{
					this.sv_Manager.listModel.addElement(ip);
				}
			}
			

			ServerInfo sv_info = new ServerInfo();

			sv_info.setSTT(this.sv_Model.ListClientInfo.size() + 1 + "");
			sv_info.setPathMonitoring(this.sv_Model.ListClient.get(0));
			sv_info.setAction(this.sv_Model.ListClient.get(1));
			String Time_Connect = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
					.format(Calendar.getInstance().getTime());
			sv_info.setTime(Time_Connect);
			sv_info.setIPClient(this.sv_Model.ListClient.get(2));
			sv_info.setDetail(this.sv_Model.ListClient.get(3));

			this.sv_Model.ListClientInfo.add(sv_info);

			this.sv_Manager.defaultTableModel.addRow(new Object[] { sv_info.getSTT(), sv_info.getPathMonitoring(),
					sv_info.getTime(), sv_info.getAction(), sv_info.getIPClient(), sv_info.getDetail() });

			
			
		
			
			this.sv_Model.setPathServer(sv_info.getPathMonitoring());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
    
}
