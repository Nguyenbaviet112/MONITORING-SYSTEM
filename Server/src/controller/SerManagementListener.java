package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ServerModel;
import view.ServerManagementView;

public class SerManagementListener implements ActionListener {

	private ServerManagementView managementView;
	private ServerModel Sv_Model;
	
	public SerManagementListener(ServerManagementView managementView, ServerModel Sv_Model)
	{
		this.managementView = managementView;
		this.Sv_Model = Sv_Model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String button = e.getActionCommand();
		
		if(button.equals("Brower"))
		{
			this.managementView.show_Folder(this.Sv_Model.getPath_Server());
		}
	}

}
