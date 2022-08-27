package model;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;


public class WatchFolder implements Runnable {
	
	
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

   public void registerDir (Path path, WatchService watchService) throws
                       IOException {


       if (!Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
           return;
       }


       WatchKey key = path.register(watchService,
                           StandardWatchEventKinds.ENTRY_CREATE,
                           StandardWatchEventKinds.ENTRY_MODIFY,
                           StandardWatchEventKinds.ENTRY_DELETE);
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
				
				if (!context.equals("New Text Document.txt") && !context.equals("!context.equals"))
				{
					System.out.printf("\nEvent... kind=%s, count=%d, context=%s " , watchEvent.kind(),
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
		// TODO Auto-generated method stub
		Init("C:\\Users\\NguyenBaViet\\Music\\Client_01");
		
	}
}