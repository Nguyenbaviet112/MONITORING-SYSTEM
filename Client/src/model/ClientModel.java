package model;


import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClientModel {
	

    private Socket clientSocket  = null;
    private WatchFolder wc;
    public int check_connect;
   	public int check_disconnect;
   	private BufferedWriter os = null;
    
   
   public ClientModel()
   {
	   this.check_connect = 1;
	   this.check_connect = 1;
   }
  

	
	public void ConnetServer(int Port, String IP)
	{
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
			
				
			wc = new WatchFolder();
			
			Thread t1 = new Thread(wc);
			t1.start();
			
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
			
				
			wc = new WatchFolder();
			
			Thread t1 = new Thread(wc);
			t1.start();
			
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
			clientSocket.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.check_disconnect = 0;
		}


	}
	
}
