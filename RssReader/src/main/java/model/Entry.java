package model;

import java.io.Serializable;
import java.util.Date;

public class Entry implements Serializable{

	private static final long serialVersionUID = -2313327649594693846L;
	
	private String title;
	private String author;
	private String content;
	private Date publicationDate;
	private boolean seen = false;
	
	public Entry() {
		super();		
	}
	
	public Entry(String title, String author, String content, Date publicationDate, boolean seen) {
		super();
		this.title = title;
		this.author = author;
		this.content = content;
		this.publicationDate = publicationDate;
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
	
	public Date getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	public boolean isSeen() {
		return this.seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	
}
