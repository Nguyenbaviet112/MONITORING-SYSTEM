package model;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.ClientInfo;
import model.ClientModel;
import view.ClientView;

public class WatchFolder implements Runnable {

	private Set<String> Set_Action;
	private ClientModel sv_Model;
	private ClientView sv_Manager;
	
	public WatchFolder(ClientModel sv_Model, ClientView sv_Manager)
	{
		this.sv_Manager = sv_Manager;
		this.sv_Model = sv_Model;
		
	}
	
	private static Map<WatchKey, Path> keyPathMap = new HashMap<>();

	public void Init(String path) {
		try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
			this.registerDir(Paths.get(path), watchService);
			try {
				this.startListening(watchService);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registerDir(Path path, WatchService watchService) throws IOException {

		if (!Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
			return;
		}

		
		WatchKey key = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
		keyPathMap.put(key, path);

		for (File f : path.toFile().listFiles()) {
			registerDir(f.toPath(), watchService);
		}
	}

	public void startListening(WatchService watchService) throws Exception {
		while (true) {
		
			List<String> List_Create = new ArrayList<String>();
			WatchKey queuedKey = watchService.take();
			for (WatchEvent<?> watchEvent : queuedKey.pollEvents()) {

				this.Set_Action = new HashSet<String>();
				
				String context = watchEvent.context().toString();
				String action = "";
				
				List_Create.add(context);
				
				
				if (!(context.equals("New folder")))
				{
					if (!(context.equals("New Microsoft Word Document.docx"))) 
					{
						if ( !(context.equals("New Text Document.txt")) )
						{
							
							if (List_Create.contains("New folder"))
							{
								action = "New folder";
							}
							else if (List_Create.contains("New Microsoft Word Document.docxr"))
							{
								action = "New Microsoft Word Document";
							}
							else if (List_Create.contains("New Text Document.txt"))
							{
								action = "New Text Document";
							}
							
							String detail = watchEvent.kind() + "-" + action + "-" + watchEvent.context();
							
							
							Set_Action.add(detail);
						
							
							List<String> List_Action = new ArrayList<String>();
							List_Action.addAll(Set_Action);
							
							Set<String> temp_Set = new HashSet<>(List_Action);
							List_Action = new ArrayList<String>();
							List_Action.addAll(temp_Set);
							
							
							for (String s: List_Action)
							{
								String[] temp = s.split("-");
								
								ClientInfo sv_info = new ClientInfo();

								sv_info.setSTT(this.sv_Manager.ListClientInfo.size() + 1 + "");
								sv_info.setPathMonitoring(this.sv_Model.path_monitoring);
								sv_info.setAction(temp[0] + " " + temp[1]);
								String Time_Connect = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
										.format(Calendar.getInstance().getTime());
								sv_info.setTime(Time_Connect);
								sv_info.setIPClient(this.sv_Model.clientSocket.getInetAddress().toString().replace("/", ""));
								sv_info.setDetail(temp[0] + " " + temp[1] + " " + "successfuly" + " " +temp[2]);

								this.sv_Manager.ListClientInfo.add(sv_info);

								this.sv_Manager.defaultTableModel.addRow(new Object[] { sv_info.getSTT(), sv_info.getPathMonitoring(),
										sv_info.getTime(), sv_info.getAction(), sv_info.getIPClient(), sv_info.getDetail() });
								
							}
						}
					}
					
				}
				
				

				
				


				if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_CREATE) {

					// this is not a complete path
					Path path = (Path) watchEvent.context();
					// need to get parent path
					Path parentPath = keyPathMap.get(queuedKey);
					// get complete path
					path = parentPath.resolve(path);

					registerDir(path, watchService);
				}

			}
			if (!queuedKey.reset()) {
				keyPathMap.remove(queuedKey);
			}
			if (keyPathMap.isEmpty()) {
				break;
			}
		}
	}

	@Override
	public void run() {
		Init("C:\\Users\\NguyenBaViet\\Music\\Client_01");
	}
	
}