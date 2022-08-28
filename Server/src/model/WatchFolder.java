package model;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import model.ServerInfo;
import model.ServerModel;
import view.ServerManagementView;

public class WatchFolder implements Runnable {

	private ServerModel sv_Model;
	private ServerManagementView sv_Manager;
	private Socket clientSocket;
	
	public WatchFolder(ServerModel sv_Model, ServerManagementView sv_Manager, Socket clientSocket)
	{
		this.clientSocket = clientSocket;
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
			int check = 0;
			WatchKey queuedKey = watchService.take();
			for (WatchEvent<?> watchEvent : queuedKey.pollEvents()) {

				String context = watchEvent.context().toString();

				if (!context.equals("New Text Document.txt") && !context.equals("!context.equals")) {
					
					ServerInfo sv_info = new ServerInfo();

					sv_info.setSTT(this.sv_Model.ListClientInfo.size() + 1 + "");
					sv_info.setPathMonitoring(this.sv_Model.getPath_Server());
					sv_info.setAction(watchEvent.kind().toString());
					String Time_Connect = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
							.format(Calendar.getInstance().getTime());
					sv_info.setTime(Time_Connect);
					sv_info.setIPClient(clientSocket.getInetAddress().toString());
					sv_info.setDetail(watchEvent.kind().toString() + "successfuly" + watchEvent.context());

					this.sv_Model.ListClientInfo.add(sv_info);

					this.sv_Manager.defaultTableModel.addRow(new Object[] { sv_info.getSTT(), sv_info.getPathMonitoring(),
							sv_info.getTime(), sv_info.getAction(), sv_info.getIPClient(), sv_info.getDetail() });

					
					System.out.printf("\nEvent... kind=%s, count=%d, context=%s ", watchEvent.kind(),
							watchEvent.count(), watchEvent.context());
				}

				// do something useful here

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