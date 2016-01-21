package main;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import model.Folder;
import model.Tag;

public class StorageService {

	private static BTreeMap<String, Folder> folderList;
	private static BTreeMap<String, Tag> tagList;
	private static DB db;
	
	public StorageService() {	

		db = DBMaker.fileDB(new File("rss.db"))
		        .closeOnJvmShutdown()
		        .make();
		
		folderList = db.treeMap("folderList");
		tagList = db.treeMap("tagList");	

	}
	
	public Map<String, Folder> getFolderList(){		
		Map<String, Folder> fldList = new HashMap<String, Folder>();
		
		for(String key: folderList.keySet()){
			fldList.put(key, folderList.get(key));
		}	
		
		return fldList;
	}
	
	public Map<String, Tag> getTagList(){
		Map<String, Tag> tgList = new HashMap<String, Tag>();
		
		for(String key: tagList.keySet()){
			tgList.put(key, tagList.get(key));
		}	
		
		return tgList;
	}
	
	public void saveFolderList(Map<String, Folder> fldList){		
		Map<String, Folder> deletedFolderList = new HashMap<String, Folder>();
		for(String key: folderList.keySet()){
			deletedFolderList.put(key, folderList.get(key));
		}
		deletedFolderList.keySet().removeAll(fldList.keySet());
		
		for(String key: fldList.keySet()){
			folderList.put(key, fldList.get(key));
			db.commit();
		}	
		for(String key: deletedFolderList.keySet()){
			folderList.remove(key);
			db.commit();
		}
	}
	
	public void saveTagList(Map<String, Tag> tgList){
		
		Map<String, Tag> deletedTagList = new HashMap<String, Tag>();
		for(String key: tagList.keySet()){
			deletedTagList.put(key, tagList.get(key));
		}
		deletedTagList.keySet().removeAll(tgList.keySet());
		
		for(String key: tgList.keySet()){
			tagList.put(key, tgList.get(key));
			db.commit();
		}
		
		for(String key: deletedTagList.keySet()){
			tagList.remove(key);
			db.commit();
		}
	}
	
	public void close(){
		db.close();
	}
	
	
	

}
