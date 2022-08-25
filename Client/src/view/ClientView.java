package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ClientListener;
import model.ClientModel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClientView extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Port;
	private JTextField textField_IP;
	private ClientModel clientModel;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ClientView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		clientModel = new ClientModel();
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
		
		textField_IP = new JTextField();
		textField_IP.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_IP.setColumns(10);
		textField_IP.setBounds(139, 56, 145, 27);
		panel.add(textField_IP);
		
		JButton btn_Connet_Server = new JButton("Kết nối");
		btn_Connet_Server.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_Connet_Server.setBounds(330, 32, 85, 33);
		panel.add(btn_Connet_Server);
		this.setLocationRelativeTo(null);
		btn_Connet_Server.addActionListener(clientListener);
		
		this.setVisible(true);
	}
	
	public void ConnetServer()
	{
		int Port = Integer.parseInt(textField_Port.getText()) ;
		String IP = textField_IP.getText();
		this.clientModel.ConnetServer(Port, IP);
	}
	
}
