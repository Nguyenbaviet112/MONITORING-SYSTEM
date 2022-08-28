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
	private ServerModel serverModel;
	private JButton btn_StartServer;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ServerView() {
		this.setTitle("Server");
		setBounds(100, 100, 415, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		ServerListener sl = new ServerListener(this);

		
		btn_StartServer = new JButton("Start");
		btn_StartServer.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_StartServer.setBounds(10, 35, 111, 37);
		contentPane.add(btn_StartServer);
		btn_StartServer.addActionListener(sl);
		
		JPanel panel = new JPanel();
		panel.setBounds(164, 25, 207, 54);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Port:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 48, 26);
		panel.add(lblNewLabel);
		
		Port_textField = new JTextField();
		Port_textField.setText("3007");
		Port_textField.setEditable(false);
		Port_textField.setFont(new Font("Arial", Font.PLAIN, 14));
		Port_textField.setBounds(50, 15, 120, 19);
		panel.add(Port_textField);
		Port_textField.setColumns(10);
		
		this.setVisible(true);
		
	}
	

	public void Is_dipose()
	{	
		new ServerManagementView();
		this.dispose();
	}
	

}
