package test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.WatchService;
import model.WatchFolder;

public class main {
	public static void main(String[] args) {
	
		WatchFolder wf = new WatchFolder();
		wf.Init("C:\\Users\\NguyenBaViet\\Music\\Client_01");
		
		
	}

}
