package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.ServerView;

public class ServerListener implements ActionListener {

	private ServerView sv;
	
	public ServerListener(ServerView sv)
	{
		this.sv = sv;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String button = e.getActionCommand();
		
		if (button.equals("Start"))
		{
			this.sv.NewJFrame();
		}

		
	}

}
