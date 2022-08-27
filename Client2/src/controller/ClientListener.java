package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ClientView;

public class ClientListener implements ActionListener {

	private ClientView cv;
	
	public ClientListener(ClientView cv)
	{
		this.cv = cv;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String button = e.getActionCommand();
		if (button.equals("Kết nối"))
		{
			cv.ConnetServer();
		}
	}
	
}
