package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.File;

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

public class ServerManagementView extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Port;
	private JTextField textField_SVIP;
	private ServerModel serverModel;
	private SerManagementListener listener;
	private JList jlist;
	public DefaultListModel listModel;
	private JTable table_Server;



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
		
		this.serverModel = new ServerModel(this);
		this.listener = new SerManagementListener(this, this.serverModel);
		
		
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
		
		jlist.setBounds(10, 164, 144, 532);
		contentPane.add(jlist);
		
		JButton btn_BrowerFolder = new JButton("Brower");
		btn_BrowerFolder.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_BrowerFolder.setBounds(1141, 10, 173, 38);
		contentPane.add(btn_BrowerFolder);
		
		table_Server = new JTable();
		table_Server.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, ""},
			},
			new String[] {
				"STT", "Path Monitoring", "Time", "Action", "IP client", "Detail"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_Server.setFont(new Font("Arial", Font.PLAIN, 14));
		table_Server.setBounds(202, 174, 1112, 520);
		contentPane.add(table_Server);
		
		
		
		btn_BrowerFolder.addActionListener(listener);
		
		
		
		this.show_IP_Port();
		this.setVisible(true);
		this.serverModel.start();
		
	}
	
	public void show_IP_Port()
	{
		this.serverModel.get_IP_Port();
		String Port = this.serverModel.getPort()+"";
		textField_Port.setText(Port);
		String IP = this.serverModel.getIP()+"";
		textField_SVIP.setText(IP);
	}
	
	public void show_Folder(String path)
	{
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File(path)); // start at application current directory
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showSaveDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		    File yourFolder = fc.getSelectedFile();
		}
	}
}
