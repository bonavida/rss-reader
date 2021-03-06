package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.NotAllowedOperationException;
import rss.CustomComparator;

public class Feed implements Serializable{

	private static final long serialVersionUID = -1670066042621425421L;
	
	private String name;
	private String url;
	private Folder folder;
	private Map<String, Entry> entryList = new HashMap<String, Entry>();
	private Map<String, Tag> tagList = new HashMap<String, Tag>();
	
	public Feed() {
		super();
	}
	
	public Feed(String name, String url ) {
		super();
		this.name = name;
		this.url = url;
		this.folder = new Folder();
	}

	public String getName() {		
		return this.name;
	}
	
	public void setName(String name) {		
		this.name = name;
	}
	
	public String getUrl() {		
		return this.url;
	}
	
	public void setURL(String url) {		
		this.url = url;
	}
	
	public Folder getFolder() {
		return this.folder;
	}
	
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	public List<Entry> getEntryList() {
		List<Entry> list = new ArrayList<Entry>();
		for(Entry e: this.entryList.values()){
			list.add(e);
		}
		Collections.sort(list, new CustomComparator());
		return list;
	}
	
	public void setEntryList(Map<String, Entry> entryList) {
		this.entryList = entryList;
	}
	
	public Entry getEntry(String name) {
		return this.entryList.get(name);
	}
	
	public void addEntry(Entry entry) throws NotAllowedOperationException {
		if ((entry != null && entry.getTitle() != null) && !entryList.containsKey(entry.getTitle())) {
			entryList.put(entry.getTitle(), entry);
		} else {
			throw new NotAllowedOperationException("No se puede añadir una entrada.");
		}
	}
	
	public void removeEntry(Entry entry) {
		this.entryList.remove(entry);
	}
	
	public List<Tag> getTagList() {
		List<Tag> list = new ArrayList<Tag>();
		for(Tag t: this.tagList.values()){
			list.add(t);
		}
		return list;
	}
	
	public void setTagList(Map<String, Tag> tagList) {
		this.tagList = tagList;
	}
	
	public Tag getTag(String name) {
		return this.tagList.get(name);
	}
	
	public void addTag(Tag tag) throws NotAllowedOperationException {
		if ((tag != null && tag.getName() != null) && !tagList.containsKey(tag.getName())) {
			tag.addAssignedFeed(this);
			tagList.put(tag.getName(), tag);
		} else {
			throw new NotAllowedOperationException("No se puede asignar una etiqueta ya asignada.");
		}
	}
	
	public void removeTag(String name) {
		tagList.get(name).removeAssignedFeed(this);
		this.tagList.remove(name);
	}
	
	@Override
	public String toString(){
		return name+"\n"+url;
	}

}
