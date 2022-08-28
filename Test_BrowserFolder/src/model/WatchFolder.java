package model;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class WatchFolder {
	
	private Set<String> Set_Action;
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
							
							String detail = watchEvent.kind() + " " + action + " " + watchEvent.context();
							
							
							Set_Action.add(detail);
						
							
							List<String> List_Action = new ArrayList<String>();
							List_Action.addAll(Set_Action);
							
							if (List_Action.contains("ENTRY_DELETE") && List_Action.contains("ENTRY_CREATE"))
							{
								System.out.println("RENAME");
							}
							
							for (String s: List_Action)
							{
								System.out.println(s);
							}
						}
					}
					
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
}