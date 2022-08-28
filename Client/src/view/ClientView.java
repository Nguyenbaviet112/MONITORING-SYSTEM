package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ClientListener;
import model.ClientInfo;
import model.ClientModel;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class ClientView extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Port;
	private JTextField textField_IP;
	private ClientModel clientModel;
	private JButton btn_Connet_Server;
	public DefaultListModel listModel;
	private JTable table_Server;
	public DefaultTableModel defaultTableModel;
	private JScrollPane scrollPane;
	
	public ArrayList<ClientInfo> ListClientInfo;
	private BufferedWriter  bufferedWriter;
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ClientView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1384, 788);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ListClientInfo = new ArrayList<ClientInfo>();
		
		clientModel = new ClientModel(this);
		ClientListener clientListener = new ClientListener(this);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 474, 101);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhập Port:");
		lblNewLabel.setBounds(10, 10, 82, 33);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblNhpIp = new JLabel("Nhập IP Server:");
		lblNhpIp.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNhpIp.setBounds(10, 53, 107, 33);
		panel.add(lblNhpIp);
		
		textField_Port = new JTextField();
		textField_Port.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_Port.setBounds(139, 13, 145, 27);
		panel.add(textField_Port);
		textField_Port.setColumns(10);
		textField_Port.setText("3007");
		
		textField_IP = new JTextField();
		textField_IP.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_IP.setColumns(10);
		textField_IP.setBounds(139, 56, 145, 27);
		panel.add(textField_IP);
		textField_IP.setText("192.168.1.60");
		
		btn_Connet_Server = new JButton("Connect");
		btn_Connet_Server.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_Connet_Server.setBounds(317, 32, 120, 33);
		panel.add(btn_Connet_Server);
		this.setLocationRelativeTo(null);
		btn_Connet_Server.addActionListener(clientListener);
		
		
		listModel = new DefaultListModel();
		this.addWindowListener(clientListener);
		
		JButton btn_Load_Log = new JButton("Load log");
		btn_Load_Log.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_Load_Log.setBounds(1141, 10, 173, 38);
		btn_Load_Log.addActionListener(clientListener);
		contentPane.add(btn_Load_Log);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 164, 1350, 520);
		contentPane.add(scrollPane);
		
		
		this.defaultTableModel = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Path Monitoring", "Time", "Acction", "ip", "Detail"
			}
		);
		table_Server = new JTable(defaultTableModel);
		table_Server.getColumnModel().getColumn(1).setPreferredWidth(90);
		scrollPane.setViewportView(table_Server);
		

		table_Server = new JTable(defaultTableModel);
		table_Server.setFont(new Font("Arial", Font.PLAIN, 14));
		
		
		this.setVisible(true);
	}
	
	public void ConnetServer()
	{
		int Port = Integer.parseInt(textField_Port.getText()) ;
		String IP = textField_IP.getText();
		
		this.clientModel.ConnetServer(Port, IP);
	
		if (this.clientModel.check_connect == 1)
		{
			JOptionPane.showMessageDialog(this, "Connect sucessfully",
	                "INFORMATION",
	                JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Connect fail",
	                "INFORMATION",
	                JOptionPane.ERROR_MESSAGE);
		}
		
		
		this.btn_Connet_Server.setText("Disconnect");
		
		
		
	}
	
	
	public void change_Stage()
	{
		int Port = Integer.parseInt(textField_Port.getText()) ;
		String IP = textField_IP.getText();
		
		this.clientModel = new ClientModel(this);
		
		this.clientModel.Disconnet_Server(Port, IP);
		
		if (this.clientModel.check_connect == 1)
		{
			JOptionPane.showMessageDialog(this, "DisConnect sucessfully",
	                "INFORMATION",
	                JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Disconnect fail",
	                "INFORMATION",
	                JOptionPane.ERROR_MESSAGE);
		}
		
		
		this.btn_Connet_Server.setText("Connect");
	}
	
	public void WriteLogFile()
	{
	
		File file = new File("log.txt");
		this.bufferedWriter = null;
		
		try 
		{
			this.bufferedWriter = new BufferedWriter(new FileWriter(file));
			
			for (ClientInfo cl : this.ListClientInfo) 
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

		    ClientInfo sv_info = null;
		    
		    while (line != null)
		    {
		    	sv_info = new ClientInfo();
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
		    
		    for (ClientInfo cl : this.ListClientInfo)
		    {
		    	this.defaultTableModel.addRow(new Object[] { cl.getSTT(), cl.getPathMonitoring(),
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
