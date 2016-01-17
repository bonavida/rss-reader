package rss;


import com.sun.syndication.feed.synd.SyndFeed;

import model.Feed;

public class RssReader {
	private RssParser parser = new RssParser();
	
	public RssReader() {
	}
	
	public Feed readFeed(String URL) throws Exception {
		SyndFeed feeder = parser.getSyndFeedFromUrl(URL);
		Feed feed = parser.setFeedAttributes(feeder);
		return feed;
	}
}
