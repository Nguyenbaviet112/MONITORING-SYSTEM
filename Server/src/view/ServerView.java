package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ServerListener;
import model.ServerModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JTextField;

public class ServerView extends JFrame {

	private JPanel contentPane;
	private JTextField Port_textField;
	private JTextField IP_textField;
	private ServerModel serverModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerView frame = new ServerView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerView() {
		this.setTitle("Server");
		setBounds(100, 100, 539, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		ServerListener sl = new ServerListener(this);
		serverModel = new ServerModel();
		
		
		JButton btn_StartServer = new JButton("Start");
		btn_StartServer.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_StartServer.setBounds(10, 31, 111, 37);
		contentPane.add(btn_StartServer);
		btn_StartServer.addActionListener(sl);
		
		JPanel panel = new JPanel();
		panel.setBounds(162, 10, 180, 80);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Port:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 48, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("IP:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 46, 45, 13);
		panel.add(lblNewLabel_1);
		
		Port_textField = new JTextField();
		Port_textField.setFont(new Font("Arial", Font.PLAIN, 14));
		Port_textField.setBounds(50, 15, 120, 19);
		Port_textField.setEditable(false);
		panel.add(Port_textField);
		Port_textField.setColumns(10);
		
		IP_textField = new JTextField();
		IP_textField.setFont(new Font("Arial", Font.PLAIN, 14));
		IP_textField.setColumns(10);
		IP_textField.setEditable(false);
		IP_textField.setBounds(50, 44, 120, 19);
		panel.add(IP_textField);
	}
	
	public void show_IP_Port()
	{
		this.serverModel.get_IP_Port();
		String Port = this.serverModel.getPort()+"";
		Port_textField.setText(Port);
		String IP = this.serverModel.getIP()+"";
		IP_textField.setText(IP);
	}
	
}
