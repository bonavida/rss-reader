package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.NotAllowedOperationException;
import model.Entry;
import model.Feed;
import model.Folder;
import model.Tag;

public class TestFolder {
	Folder folder;
	Feed feed;
	
	@Before
	public void Before() {
		folder = new Folder();
		feed = new Feed();
	}

	@Test
	public void getFeed_EmptyList_AddedItem() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		
		try {
			folder.addFeed(feed);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(feed, folder.getFeed(feedName));
	}
	
	@Test
	public void addFeed_ValidFeed_AddedItem() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		
		try {
			folder.addFeed(feed);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(feed, folder.getFeed(feedName));
	}

	@Test
	public void addFeed_ValidFeed_AddedList() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String feedUrl2 = "";
		String feedName2 = "Meneame";
		Feed feed2 = new Feed(feedName2, feedUrl2);
		
		try {
			folder.addFeed(feed);
			folder.addFeed(feed2);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(feed, folder.getFeed(feedName));
		assertEquals(feed2, folder.getFeed(feedName2));
	}
	
	@Test
	public void addFeed_ValidFeed_ErrorDuplicate() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String feedUrl2 = "";
		String feedName2 = "Meneame";
		Feed feed2 = new Feed(feedName2, feedUrl2);
		
		try {
			folder.addFeed(feed);
			folder.addFeed(feed2);
			folder.addFeed(feed);
			fail("Exception not throwed");
		} catch (NotAllowedOperationException e) {
			System.out.println(e);
		}
	}

	@Test
	public void addFeed_ValidFeed_ErrorNull() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);		

		try {
			folder.addFeed(feed);
			folder.addFeed(null);
			fail("Exception not throwed");
		} catch (NotAllowedOperationException e){
			System.out.println(e);
		}	
	}

	@Test
	public void removeFeed_ValidFeed_RemovedItem() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		
		try {
			folder.addFeed(feed);
			folder.removeFeed(feedName);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(null, folder.getFeed(feedName));
	}

	@Test
	public void removeFeed_ValidFeed_RemovedList() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String feedUrl2 = "";
		String feedName2 = "Meneame";
		Feed feed2 = new Feed(feedName2, feedUrl2);
		
		try {
			folder.addFeed(feed);
			folder.addFeed(feed2);
			folder.removeFeed(feedName);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(null, folder.getFeed(feedName));
	}

	@Test
	public void getFeedList_NotEmptyList_FeedList() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String feedUrl2 = "";
		String feedName2 = "Meneame";
		Feed feed2 = new Feed(feedName2, feedUrl2);
		List<Feed> feedList = new ArrayList<Feed>();
		feedList.add(feed);
		feedList.add(feed2);
		
		try {
			folder.addFeed(feed);
			folder.addFeed(feed2);		
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(feedList, folder.getFeedList());
	}

	@Test
	public void getFeedList_EmptyList_EmptyFeedList() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		List<Feed> feedList = new ArrayList<Feed>();
		
		assertEquals(feedList, folder.getFeedList());
	}
	
	@Test
	public void getEntryList_NotEmptyList_EntryList() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Meneame";
		feed = new Feed(feedName, feedUrl);
		String entryName = "¡Feliz 2016!";
		Entry entry = new Entry(entryName, "AdminMeneame", "", new Date(), false);
		List<Entry> entryList = new ArrayList<Entry>();
		entryList.add(entry);
		
		try {			
			feed.addEntry(entry);
			folder.addFeed(feed);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(entryList, folder.getFeed(feedName).getEntryList());
	}
	
	@Test
	public void getEntryList_EmptyList_EmptyEntryList() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		List<Entry> entryList = new ArrayList<Entry>();
		
		try {
			folder.addFeed(feed);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(entryList, folder.getFeed(feedName).getEntryList());
	}
	
	@Test
	public void setEntryToSeen_EntryNotSeen_EntrySeen() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String entryName = "¡Feliz 2016!";
		Entry entry = new Entry(entryName, "AdminBarrapunto", "", new Date(), false);
		
		try {
			folder.addFeed(feed);
			folder.getFeed(feedName).addEntry(entry);
			folder.getFeed(feedName).getEntry(entryName).setSeen(true);
		} catch (Exception e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(folder.getFeed(feedName).getEntry(entryName).isSeen(), true);
	}
	
	@Test
	public void setEntryToNotSeen_EntrySeen_EntryNotSeen() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String entryName = "¡Feliz 2016!";
		Entry entry = new Entry(entryName, "AdminBarrapunto", "", new Date(), true);
		
		try {
			folder.addFeed(feed);
			folder.getFeed(feedName).addEntry(entry);
			folder.getFeed(feedName).getEntry(entryName).setSeen(false);
		} catch (Exception e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(folder.getFeed(feedName).getEntry(entryName).isSeen(), false);
	}
	
	@Test
	public void assignTagToFeed_ValidTag_TagAssigned() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String tagName = "Linux";
		Tag tag = new Tag(tagName);
		
		try {
			folder.addFeed(feed);
			folder.getFeed(feedName).addTag(tag);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(tag, folder.getFeed(feedName).getTag(tagName));
	}
	
	@Test
	public void assignTagToFeed_DuplicatedTag_ErrorDuplicated() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String tagName = "Linux";
		Tag tag = new Tag(tagName);
		
		try {
			folder.addFeed(feed);
			folder.getFeed(feedName).addTag(tag);
			folder.getFeed(feedName).addTag(tag);
			fail("Exception not throwed");
		} catch (NotAllowedOperationException e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void unassignTag_ValidTag_TagUnassigned() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String tagName = "Linux";
		Tag tag = new Tag(tagName);
		
		try {
			folder.addFeed(feed);
			folder.getFeed(feedName).addTag(tag);
			folder.getFeed(feedName).removeTag(tagName);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(null, folder.getFeed(feedName).getTag(tagName));
	}
	
	@Test
	public void getAssignedFeedList_AssignedFeeds_AssignedFeedList() {	
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String feedUrl2 = "";
		String feedName2 = "Meneame";
		Feed feed2 = new Feed(feedName2, feedUrl2);
		List<Feed> assignedFeedList = new ArrayList<Feed>();
		assignedFeedList.add(feed);
		assignedFeedList.add(feed2);
		String tagName = "Linux";
		Tag tag = new Tag(tagName);
		
		try {
			folder.addFeed(feed);
			folder.addFeed(feed2);
			folder.getFeed(feedName).addTag(tag);
			folder.getFeed(feedName2).addTag(tag);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(assignedFeedList, tag.getAssignedFeedList());
	}
	
	@Test
	public void getAssignedFeedList_NotAssignedFeeds_EmptyFeedList() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String feedUrl2 = "";
		String feedName2 = "Meneame";
		Feed feed2 = new Feed(feedName2, feedUrl2);
		List<Feed> list = new ArrayList<Feed>();
		String tagName = "Android";
		Tag tag = new Tag(tagName);		
		
		try {
			folder.addFeed(feed);
			folder.addFeed(feed2);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(list, tag.getAssignedFeedList());
	}

}
