package model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import view.ClientView;

public class ClientModel {
	

    public Socket clientSocket  = null;
    private WatchFolder wc;
    public int check_connect;
   	public int check_disconnect;
   	private BufferedWriter os = null;
   	private ClientView cv;
   	public String path_monitoring;
   	private BufferedWriter  bufferedWriter;

    
   
   public ClientModel(ClientView cv)
   {
	   this.path_monitoring = "C:\\Users\\NguyenBaViet\\Music\\Client_01"; 
	   this.cv = cv;
	   this.check_connect = 1;
	   this.check_connect = 1;
	   
	   
	   
   }
  

	
	public void ConnetServer(int Port, String IP)
	{
		wc = new WatchFolder(this, this.cv);
		Thread t1 = new Thread(wc);
		t1.start();
		try {

			try {
				
				clientSocket = new Socket(IP, Port);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String ip_client = clientSocket.getLocalAddress().toString().replace("/", "");
			String message = "C:\\Users\\NguyenBaViet\\Music\\Client_01";  
			String Acction = "Connected";
			String Detail = "Connected to server";
			
				
			
			
			os = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

			os.write(message);
			os.newLine();
			os.flush();
			
			os.write(Acction);
			os.newLine();
			os.flush();
			
			os.write(ip_client);
			os.newLine();
			os.flush();
			
			os.write(Detail);
			os.newLine();
			os.flush();

			os.close();
			
			
			
			ClientInfo sv_info = new ClientInfo();

			sv_info.setSTT(this.cv.ListClientInfo.size() + 1 + "");
			sv_info.setPathMonitoring(message);
			sv_info.setAction(Acction);
			String Time_Connect = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
					.format(Calendar.getInstance().getTime());
			sv_info.setTime(Time_Connect);
			sv_info.setIPClient(clientSocket.getInetAddress().toString().replace("/", "") + " Server");
			sv_info.setDetail(Detail);

			this.cv.ListClientInfo.add(sv_info);

			this.cv.defaultTableModel.addRow(new Object[] { sv_info.getSTT(), sv_info.getPathMonitoring(),
					sv_info.getTime(), sv_info.getAction(), sv_info.getIPClient(), sv_info.getDetail() });
			
			clientSocket.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.check_connect = 0;
		}
		
		
	}
	
	public void Disconnet_Server(int Port, String IP) {

		try {

			try {
				
				clientSocket = new Socket(IP, Port);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String ip_client = clientSocket.getLocalAddress().toString().replace("/", "");
			String message = "C:\\Users\\NguyenBaViet\\Music\\Client_01";  
			String Acction = "Disconnected";
			String Detail = "Disconnected to server";
			
				
			
			os = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

			os.write(message);
			os.newLine();
			os.flush();
			
			os.write(Acction);
			os.newLine();
			os.flush();
			
			os.write(ip_client);
			os.newLine();
			os.flush();
			
			os.write(Detail);
			os.newLine();
			os.flush();

			os.close();
			
			
			ClientInfo sv_info = new ClientInfo();

			sv_info.setSTT(this.cv.ListClientInfo.size() + 1 + "");
			sv_info.setPathMonitoring(message);
			sv_info.setAction(Acction);
			String Time_Connect = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
					.format(Calendar.getInstance().getTime());
			sv_info.setTime(Time_Connect);
			sv_info.setIPClient(clientSocket.getInetAddress().toString().replace("/", "") + " Server");
			sv_info.setDetail(Detail);

			this.cv.ListClientInfo.add(sv_info);

			this.cv.defaultTableModel.addRow(new Object[] { sv_info.getSTT(), sv_info.getPathMonitoring(),
					sv_info.getTime(), sv_info.getAction(), sv_info.getIPClient(), sv_info.getDetail() });
			
			clientSocket.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.check_disconnect = 0;
		}


	}
	
	
	
}
