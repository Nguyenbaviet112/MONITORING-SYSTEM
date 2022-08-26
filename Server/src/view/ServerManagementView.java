package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import model.ServerModel;

public class ServerManagementView extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Port;
	private JTextField textField_SVIP;
	private ServerModel serverModel;



	/**
	 * Create the frame.
	 */
	public ServerManagementView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1338, 763);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		this.serverModel = new ServerModel();
		
		
		JLabel lblNewLabel = new JLabel("Port:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(24, 10, 39, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblServerIp = new JLabel("Server IP:");
		lblServerIp.setFont(new Font("Arial", Font.PLAIN, 15));
		lblServerIp.setBounds(251, 10, 77, 24);
		contentPane.add(lblServerIp);
		
		textField_Port = new JTextField();
		textField_Port.setBounds(73, 10, 125, 23);
		contentPane.add(textField_Port);
		textField_Port.setColumns(10);
		textField_Port.setEditable(false);
		
		textField_SVIP = new JTextField();
		textField_SVIP.setColumns(10);
		textField_SVIP.setBounds(356, 10, 125, 23);
		textField_SVIP.setEditable(false);
		contentPane.add(textField_SVIP);
		
		this.setVisible(true);
		this.show_IP_Port();
		this.serverModel.CreateServerSocket();
	}
	
	public void show_IP_Port()
	{
		this.serverModel.get_IP_Port();
		String Port = this.serverModel.getPort()+"";
		textField_Port.setText(Port);
		String IP = this.serverModel.getIP()+"";
		textField_SVIP.setText(IP);
	}
}
