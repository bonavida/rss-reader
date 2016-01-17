package main;
import java.io.File;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import model.Folder;
import model.Tag;

public class StorageService {

	private static BTreeMap<String, Folder> folderList;
	private static BTreeMap<String, Tag> tagList;
	
	public StorageService() {	

		DB db = DBMaker.fileDB(new File("rss.db"))
		        .closeOnJvmShutdown()
		        .transactionDisable()
		        .make();
		
		folderList = db.treeMap("folderList");
		tagList = db.treeMap("tagList");	

	}
	
	public BTreeMap<String, Folder> getFolderList(){
		return folderList;
	}
	
	public BTreeMap<String, Tag> getTagList(){
		return tagList;
	}

}
