package rss;


import com.sun.syndication.feed.synd.SyndFeed;

import model.Entry;
import model.Feed;
import rome.RssParser;

public class RssReaderTest {

	public static void main(String[] args) throws Exception {
		/*
		URL url = new URL("http://feeds2.feedburner.com/elabacabu");
		//URL url = new URL("http://www.meneame.net/rss");
		XmlReader xmlReader = null;
		
		try {
			
			xmlReader = new XmlReader(url);
			SyndFeed feeder = new SyndFeedInput().build(xmlReader);
//					xmlReader = new XmlReader(new File("blog1.xml"));
//					SyndFeed feeder = new SyndFeedInput().build(xmlReader);
			if (feeder.getAuthor() != null)
				System.out.println("Autor " + feeder.getAuthor() + " | " + feeder.getTitle());
			
			for (Object entradaObj : feeder.getEntries()) {
				SyndEntry entrada = (SyndEntry) entradaObj;
				System.out.println(entrada.getTitle());
				System.out.println("Autor: " + entrada.getAuthor());
				System.out.println("Description:");
				System.out.println(entrada.getDescription().getValue());
				break;
			}
		} finally {
			if (xmlReader != null)
				xmlReader.close();
		}
		*/

		RssParser parser = new RssParser();
		SyndFeed feeder = parser.getSyndFeedFromUrl("http://www.microsiervos.com/index.xml");
		Feed feed = parser.setFeedAttributes(feeder);
		System.out.println(feed.getName());
		System.out.println(feed.getUrl());
		for (Entry entry : feed.getEntryList()) {
			System.out.println("*** " + entry.getTitle());
			System.out.println("*** " + entry.getAuthor());
			System.out.println("*** " + entry.getContent());
			System.out.println("*** " + entry.getPublicationDate());
			System.out.println("*** " + entry.isSeen());
			System.out.println("");
		}
		
	}

}
