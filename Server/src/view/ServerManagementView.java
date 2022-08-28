package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JTextField;

import model.ServerModel;
import model.WatchFolder;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import controller.SerManagementListener;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ServerManagementView extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Port;
	private JTextField textField_SVIP;
	private ServerModel serverModel;
	private SerManagementListener listener;
	private JList jlist;
	public DefaultListModel listModel;
	private JTable table_Server;
	public DefaultTableModel defaultTableModel;
	private JScrollPane scrollPane;
	private JTextField textField_Path;
	



	/**
	 * Create the frame.
	 */
	public ServerManagementView() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1370, 764);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		this.serverModel = new ServerModel(this);
		this.listener = new SerManagementListener(this, this.serverModel);
		
		this.addWindowListener(listener);
		
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
		
		listModel = new DefaultListModel();
		jlist = new JList(listModel);
		jlist.setBackground(Color.WHITE);
		
		jlist.setBounds(10, 176, 106, 520);
		contentPane.add(jlist);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(126, 176, 1220, 520);
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
		
		textField_Path = new JTextField();
		textField_Path.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_Path.setBounds(708, 21, 442, 33);
		contentPane.add(textField_Path);
		textField_Path.setColumns(10);
		textField_Path.setEditable(false);
		textField_Path.setText(this.serverModel.getPath_Server().toString());
		
		JButton btn_LoadLog = new JButton("Load Log");
		btn_LoadLog.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_LoadLog.setBounds(1175, 10, 171, 54);
		contentPane.add(btn_LoadLog);
		btn_LoadLog.addActionListener(listener);
		

		table_Server = new JTable(defaultTableModel);
		table_Server.setFont(new Font("Arial", Font.PLAIN, 14));
		
		
		
		this.show_IP_Port();
		this.serverModel.start();;
		this.setVisible(true);
		
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
