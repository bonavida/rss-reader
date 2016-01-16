package model;

import java.util.ArrayList;
import java.util.List;

public class Tag {
	
	private String name;
	private List<Feed> assignedFeedList = new ArrayList<Feed>();;
	
	public Tag() {
		
	}
	
	public Tag(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Feed> getAssignedFeedList() {
		return this.assignedFeedList;
	}
	
	public void setAssignedFeedList(List<Feed> assignedFeedList) {
		this.assignedFeedList = assignedFeedList;
	}
	
	public void addAssignedFeed(Feed feed) {
		this.assignedFeedList.add(feed);
	}
	
	public void removeAssignedFeed(Feed feed) {
		this.assignedFeedList.remove(feed);
	}
}
