package rss;


import com.sun.syndication.feed.synd.SyndFeed;

import model.Entry;
import model.Feed;

public class RssParserTest {

	public static void main(String[] args) throws Exception {

		RssParser parser = new RssParser();
		SyndFeed feeder = parser.getSyndFeedFromUrl("http://www.microsiervos.com/index.xml");
		Feed feed = parser.setFeedAttributes(feeder);
		System.out.println(feed.getName());
		System.out.println(feed.getUrl());
		for (Entry entry : feed.getEntryList()) {
			System.out.println(entry.getTitle());
			System.out.println(entry.getAuthor());
			System.out.println(entry.getContent());
			System.out.println(entry.getPublicationDate());
			System.out.println(entry.isSeen());
			System.out.println("");
		}
		
	}

}
