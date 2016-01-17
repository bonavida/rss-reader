package rss;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import exceptions.NotAllowedOperationException;
import model.Entry;
import model.Feed;

public class RssParser {
	
	public RssParser() {
		
	}
	
	public SyndFeed getSyndFeedFromUrl(String url)
			throws MalformedURLException, IOException, IllegalArgumentException, FeedException {
		
		XmlReader xmlReader = null;
		SyndFeed feeder = null;
		
		try {
			xmlReader = new XmlReader(new URL(url));
			feeder = new SyndFeedInput().build(xmlReader);
		} finally {
			if (xmlReader != null)
				xmlReader.close();
		}

		return feeder; 
	}


	public SyndFeed getSyndFeedFromLocalFile(String filePath)
			throws MalformedURLException, IOException, IllegalArgumentException, FeedException {

		XmlReader xmlReader = null;
		SyndFeed feeder = null;
		
		try {
			xmlReader = new XmlReader(new File(filePath));
			feeder = new SyndFeedInput().build(xmlReader);
		} finally {
			if (xmlReader != null)
				xmlReader.close();
		}

		return feeder;
	}
	
	
	public Feed setFeedAttributes(SyndFeed feeder) {
		
		Feed feed = new Feed();
		
		feed.setName(feeder.getTitle());
		feed.setURL(feeder.getLink());
		
		try {
			for (Object entryObject : feeder.getEntries()) {
				SyndEntry syndEntry = (SyndEntry) entryObject;
				Entry entry = new Entry();
				entry.setTitle(syndEntry.getTitle());
				entry.setAuthor(syndEntry.getAuthor());
				entry.setContent(syndEntry.getDescription().getValue());
				entry.setPublicationDate(syndEntry.getPublishedDate());
				entry.setSeen(false);

				feed.addEntry(entry);
			}
		
		} catch (NotAllowedOperationException e) {
			System.out.println("Error adding an entry to the feed: " + e);
		}
		
		return feed;
	}
}