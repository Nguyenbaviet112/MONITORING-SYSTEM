package test;

import model.ServerModel;
import view.ServerView;

public class main {
	public static void main(String[] args) {
		ServerView frame = new ServerView();
		ServerModel sv = new ServerModel();
		sv.CreateServerSocket();
	}
}
