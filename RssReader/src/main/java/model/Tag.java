package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tag implements Serializable{

	private static final long serialVersionUID = 7190998613851133448L;
	
	private String name;
	private List<Feed> assignedFeedList = new ArrayList<Feed>();;
	
	public Tag() {
		super();
	}
	
	public Tag(String name) {
		super();
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
