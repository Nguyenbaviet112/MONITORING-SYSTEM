package model;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientModel {
	

    private Socket clientSocket  = null;
    private WatchFolder wc;
    

	
	public boolean ConnetServer(int Port, String IP)
	{
		try {
			
			try {
				clientSocket  = new Socket(IP, Port);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String ip_client = clientSocket.getLocalAddress().toString();
			String message = "C:\\Users\\NguyenBaViet\\Music\\Client_01";
			
			wc = new WatchFolder();
			
			Thread t1 = new Thread(wc);
			t1.start();
			
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			
			out.writeUTF(ip_client);
			out.writeUTF(message);
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		return true;
	}
	
	public void Disconnet_Server()
	{
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
