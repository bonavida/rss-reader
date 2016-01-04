package model;

public class Entry {
	
	private String title;
	private String author;
	private String content;
	private boolean seen = false;
	
	public Entry() {
		
	}
	
	public Entry(String title, String author, String content, boolean seen) {
		this.title = title;
		this.author = author;
		this.content = content;
		this.seen = seen;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isSeen() {
		return this.seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	
}
