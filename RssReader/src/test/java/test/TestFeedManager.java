package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import main.FeedManager;
import model.Entry;
import model.Feed;
import model.Folder;
import model.Tag;
import exceptions.NotAllowedOperationException;


public class TestFeedManager {
	FeedManager fm;
	Folder folder;
	Feed feed;
	
	
	@Before
	public void Before() {
		fm = new FeedManager();
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
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(feed, fm.getFolder(folderName).getFeed(feedName));
	}

	@Test
	public void addFeed_ValidFeed_AddedItem() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		
		try {
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(feed, fm.getFolder(folderName).getFeed(feedName));
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
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).addFeed(feed2);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(feed, fm.getFolder(folderName).getFeed(feedName));
		assertEquals(feed2, fm.getFolder(folderName).getFeed(feedName2));
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
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).addFeed(feed2);
			fm.getFolder(folderName).addFeed(feed);
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
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).addFeed(null);
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
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).removeFeed(feedName);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(null, fm.getFolder(folderName).getFeed(feedName));
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
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).addFeed(feed2);
			fm.getFolder(folderName).removeFeed(feedName);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(null, fm.getFolder(folderName).getFeed(feedName));
	}

	@Test
	public void getAllFeed_FeedList_ListedFeeds() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String feedUrl2 = "";
		String feedName2 = "Meneame";
		Feed feed2 = new Feed(feedName2, feedUrl2);
		List<Feed> list = new ArrayList<Feed>();
		list.add(feed);
		list.add(feed2);
		
		try {
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).addFeed(feed2);			
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(list, fm.getFolder(folderName).getFeedList());
	}

	@Test
	public void getAllFeed_EmptyList_Empty() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		List<Feed> list = new ArrayList<Feed>();
		
		try {
			fm.addFolder(folder);			
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(list, fm.getFolder(folderName).getFeedList());
	}
	
	@Test
	public void getAllEntries_EntriesList_ListedEntries() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Meneame";
		feed = new Feed(feedName, feedUrl);
		String entryName = "¡Feliz 2016!";
		Entry entry = new Entry(entryName, "AdminMeneame", "", false);
		List<Entry> entries = new ArrayList<Entry>();
		entries.add(entry);
		
		try {			
			fm.addFolder(folder);
			feed.addEntry(entry);
			fm.getFolder(folderName).addFeed(feed);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(entries, fm.getFolder(folderName).getFeed(feedName).getEntryList());
	}
	
	@Test
	public void getAllEntries_EmptyList_Empty() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		List<Entry> entries = new ArrayList<Entry>();
		
		try {
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(entries, fm.getFolder(folderName).getFeed(feedName).getEntryList());
	}
	
	@Test
	public void setEntryToSeen_EntryNotSeen_EntrySeen() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String entryName = "¡Feliz 2016!";
		Entry entry = new Entry(entryName, "AdminBarrapunto", "", false);
		
		try {
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).getFeed(feedName).addEntry(entry);
			fm.getFolder(folderName).getFeed(feedName).getEntry(entryName).setSeen(true);
		} catch (Exception e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(fm.getFolder(folderName).getFeed(feedName).getEntry(entryName).isSeen(), true);
	}
	
	@Test
	public void setEntryToNotSeen_EntrySeen_EntryNotSeen() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String entryName = "¡Feliz 2016!";
		Entry entry = new Entry(entryName, "AdminBarrapunto", "", true);
		
		try {
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).getFeed(feedName).addEntry(entry);
			fm.getFolder(folderName).getFeed(feedName).getEntry(entryName).setSeen(false);
		} catch (Exception e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(fm.getFolder(folderName).getFeed(feedName).getEntry(entryName).isSeen(), false);
	}
	
	@Test
	public void addTag_ValidTag_AddedTag() {
		String tagName = "Linux";
		Tag tag = new Tag(tagName);

		try {
			fm.addTag(tag);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}

		assertEquals(tag, fm.getTag(tagName));
	}
	
	@Test
	public void addTag_DuplicatedTag_ErrorDuplicate() {
		String tagName = "Linux";
		Tag tag = new Tag(tagName);

		try {
			fm.addTag(tag);
			fm.addTag(tag);
			fail("Exception not throwed");
		} catch (NotAllowedOperationException e) {
			System.out.println(e);
		}
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
			fm.addTag(tag);
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).getFeed(feedName).addTag(tag);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(tag, fm.getFolder(folderName).getFeed(feedName).getTag(tagName));
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
			fm.addTag(tag);
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).getFeed(feedName).addTag(tag);
			fm.getFolder(folderName).getFeed(feedName).addTag(tag);
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
			fm.addTag(tag);
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).getFeed(feedName).addTag(tag);
			fm.getFolder(folderName).getFeed(feedName).removeTag(tagName);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(null, fm.getFolder(folderName).getFeed(feedName).getTag(tagName));
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
		List<Feed> list = new ArrayList<Feed>();
		list.add(feed);
		list.add(feed2);
		String tagName = "Linux";
		Tag tag = new Tag(tagName);
		
		try {
			fm.addTag(tag);
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).addFeed(feed2);
			fm.getFolder(folderName).getFeed(feedName).addTag(tag);
			fm.getFolder(folderName).getFeed(feedName2).addTag(tag);
			fm.getTag(tagName).addAssignedFeed(feed);
			fm.getTag(tagName).addAssignedFeed(feed2);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(list, fm.getTag(tagName).getAssignedFeedList());
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
			fm.addTag(tag);
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).addFeed(feed2);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(list, fm.getTag(tagName).getAssignedFeedList());
	}
	
	@Test
	public void getAssignedFeedList_NotValidTag_ErrorNull() {
		String tagName = "Ciencia";
		Tag tag = new Tag(tagName);
		
		assertEquals(null, fm.getTag(tagName));
	}
	
	@Test
	public void removeTag_AssignedFeedList_AssignedFeedListRemoved() {
		String folderName = "Sin asignar";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String tagName = "Linux";
		Tag tag = new Tag(tagName);
		
		try {
			fm.addTag(tag);
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.getFolder(folderName).getFeed(feedName).addTag(tag);
			fm.getTag(tagName).addAssignedFeed(feed);
			fm.removeTag(tagName);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(null, fm.getFolder(folderName).getFeed(feedName).getTag(tagName));
	}
	
	@Test
	public void removeTag_EmptyFeedList_TagRemoved() {
		String tagName = "Android";
		Tag tag = new Tag(tagName);
		
		try {
			fm.addTag(tag);
			fm.removeTag(tagName);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(null, fm.getTag(tagName));
	}
	
	@Test
	public void addFolder_ValidFolder_FolderAdded() {
		String folderName = "Música";
		folder = new Folder(folderName);
		
		try {
			fm.addFolder(folder);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(folder, fm.getFolder(folderName));
	}
	
	@Test
	public void addFolder_DuplicatedFolder_ErrorDuplicated() {
		String folderName = "Música";
		folder = new Folder(folderName);
		
		try {
			fm.addFolder(folder);
			fm.addFolder(folder);
			fail("Exception not throwed");
		} catch (NotAllowedOperationException e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void moveFeed_ValidFeed_FeedMoved() {
		String oldFolderName = "Noticias tecnológicas";
		Folder oldFolder = new Folder(oldFolderName);
		String newFolderName = "Noticias";
		Folder newFolder = new Folder(newFolderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		
		try {
			fm.addFolder(oldFolder);
			fm.addFolder(newFolder);
			fm.getFolder(oldFolderName).addFeed(feed);
			fm.move(feed, newFolderName);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(feed, fm.getFolder(newFolderName).getFeed(feedName));
	}
	
	@Test
	public void removeFolder_NotEmptyFolder_AllRemoved() {
		String folderName = "Noticias tecnológicas";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		
		try {
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
			fm.removeFolder(folderName);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(null, fm.getFolder(folderName));
	}
	
	@Test
	public void removeFolder_EmptyFolder_FolderRemoved() {
		String folderName = "Noticias tecnológicas";
		folder = new Folder(folderName);
		
		try {
			fm.addFolder(folder);
			fm.removeFolder(folderName);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(null, fm.getFolder(folderName));
	}
	
}
