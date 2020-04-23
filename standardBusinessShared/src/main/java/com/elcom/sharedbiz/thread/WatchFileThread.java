package com.elcom.sharedbiz.thread;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import com.elcom.data.factory.HibernateMySQLFactory;
import com.elcom.model.constant.InterviewConstant;

/** 
 * Phát hiện file mới tạo, đọc file và xử lý insert vào bảng MEDIA.
 * @author anhdv
*/
public class WatchFileThread implements Runnable {

	private static final Logger logger = Logger.getLogger(WatchFileThread.class.getName());
	private WatchService watcher = null;
    private Map<WatchKey, Path> keys = null;
	
    /**
     * Register the given directory with the WatchService; This function will be called by FileVisitor
     */
    private void registerDirectory(Path dir) throws IOException 
    {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        keys.put(key, dir);
    }
 
    /**
     * Register the given directory, and all its sub-directories, with the WatchService.
     */
    private void walkAndRegisterDirectories(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
 
    /**
     * Process all events for keys queued to the watcher
     */
    @SuppressWarnings("rawtypes")
	private void processEvents() {
    	
        for (;;) {
 
            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }
 
            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }
 
            for (WatchEvent<?> event : key.pollEvents()) {
            	
                WatchEvent.Kind kind = event.kind();
 
                // Context for directory entry event is the file name of entry
                @SuppressWarnings("unchecked")
                Path name = ((WatchEvent<Path>)event).context();
                Path child = dir.resolve(name);
 
                if (kind == ENTRY_CREATE) {
                	try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                	try {
            		  List<String> contents = Files.readAllLines(child);
            		  String folderName = "";
            		  for(String content:contents) {
            			  if( content.contains("recordings_directory:") ) {
            				  
            				  String fullPathFile = "";
            				  folderName = content.substring(content.indexOf("/recordings/")+12, content.length()-1);
            				  File folder = new File(InterviewConstant.RECORD_FOLDER + "/" + folderName);
            				  File[] files = folder.listFiles((dir1, fileName) -> fileName.endsWith(".mp4"));
            				  for(File f: files) {
            					  fullPathFile = InterviewConstant.RECORD_FOLDER + "/" + folderName + "/" + f.getName();
      			       		  }
            				  
            				  /*File folder = new File(InterviewConstant.RECORD_FOLDER + filePath);
            				  List<String> result = new ArrayList<>();
            				  search(".*\\.mp4", folder, result);
            				  for (String s : result) {
            					  System.out.println(InterviewConstant.RECORD_FOLDER + filePath + "/" + s);
            				  }*/
            				  
            				  /*try (Stream<Path> walk = Files.walk(Paths.get(InterviewConstant.RECORD_FOLDER + filePath))) {
            			            // We want to find only regular files
            			            List<String> result = walk.filter(Files::isRegularFile)
            			                    .map(x -> x.toString()).collect(Collectors.toList());

            			            for(String s: result) {
            			       			System.out.println(InterviewConstant.RECORD_FOLDER + filePath + "/" + s);
            			       		}
            			            
            			        } catch (IOException e) {
            			            e.printStackTrace();
            			        }*/
            				  
            				  /* /home/truongdx/.jitsi-meet-cfg/jibri/recordings/qjjbspquntwdgonz/j1u31_2020-03-25-14-01-32.mp4 */
            				  
            				    System.out.println("------------------------------------------------------------");
            			        System.out.println("file:" + fullPathFile);
            			        System.out.println("------------------------------------------------------------");
            			        if( !"".equals(fullPathFile) ) {
            			        	
            			        	String fileName = fullPathFile.substring(fullPathFile.lastIndexOf("/")+1);
                			    	String roomName = fileName.split("_")[0];
                			    	
                			    	if( roomName.indexOf("j") != -1 && roomName.indexOf("u") != -1 ) {
                			    		
                			    		String jobId = roomName.substring(1, roomName.indexOf("u"));
                    			    	String userId = roomName.substring(roomName.indexOf("u")+1);
                    			        
                    					SessionFactory sessionFactoryMysql = HibernateMySQLFactory.getInstance();
                    					Session sessionMysql = sessionFactoryMysql.openSession();
                    					boolean isCloseMySqlConnection = false;
                    					
                    					String sqlInsert = "";
                    					NativeQuery queryInsert = null;
                    					
                    					try {
                    						
                    						if ( !sessionMysql.getTransaction().isActive() )
                    							sessionMysql.getTransaction().begin();
                    						
                    						sqlInsert = " insert into media (job_id, media_type, file_path, user_id) " +
                    									" values (:job_id, 'video', :file_path, :user_id) ";
                    						queryInsert = sessionMysql.createNativeQuery(sqlInsert);
                    						queryInsert.setParameter("job_id", Long.parseLong(jobId));
                    						queryInsert.setParameter("file_path", fullPathFile);
                    						queryInsert.setParameter("user_id", Long.parseLong(userId));
                    						queryInsert.executeUpdate();
                    						
                    					}catch(Exception ex) {
                    						System.out.println("[WatchFileThread] doTask().ex: " + ex.toString());
                    						logger.error("[WatchFileThread] doTask().ex: " + ex.toString());
                    						sessionMysql.getTransaction().rollback();
                    					} finally {
                    						if ( sessionMysql.getTransaction().isActive() )
                    							sessionMysql.getTransaction().commit();
                    						
                    						sessionMysql.clear();
                    						sessionMysql.close();
                    						isCloseMySqlConnection = true;
                    					}
                    					
                    					if( !isCloseMySqlConnection ) {
                    						sessionMysql.clear();
                    						sessionMysql.close();
                    					}
                			    	}
            			        }
            			  }
            		  }
        		  }catch(IOException ex){
            		  ex.printStackTrace();//handle exception here
        		  }
                }
            }
 
            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);
 
                // all directories are inaccessible
                if (keys.isEmpty())
                    break;
            }
        }
    }
    
	/*private static void search(final String pattern, final File folder, List<String> result) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(pattern, f, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f.getAbsolutePath());
                }
            }

        }
    }*/
    
	//@SuppressWarnings({ "rawtypes" })
	private void doTask(Path dir) throws IOException {
		
		this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey, Path>();
 
        this.walkAndRegisterDirectories(dir);
		
        this.processEvents();
    }

	@Override
    public void run() {
        try {
        	synchronized (WatchFileThread.class) {
        		//Path dir = Paths.get("D:\\app");
        		Path dir = Paths.get(InterviewConstant.RECORD_LOG_FOLDER);
        		this.doTask(dir);
        	}
        } catch (Exception ex) {
        	System.out.println("[WatchFileThread] run().ex: " + ex.toString());
        	logger.error("[WatchFileThread] run().ex: " + ex.toString());
            //Thread.currentThread().interrupt();
        }
    }
}
