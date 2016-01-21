package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import exceptions.NotAllowedOperationException;
import main.FeedManager;
import model.Entry;
import model.Feed;
import model.Folder;
import model.Tag;


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
	public void getTag_NotValidTag_ErrorNull() {
		String tagName = "Ciencia";
		
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
	
	@Test
	public void getFeed_ValidFeed_Feed() {
		String folderName = "Noticias tecnológicas";
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
		
		assertEquals(feed, fm.getFeed(feedName));
	}
	
	@Test
	public void getFeed_NotValidFeed_Null() {
		String folderName = "Noticias tecnológicas";
		folder = new Folder(folderName);
		String feedName = "Barrapunto";
		
		try {
			fm.addFolder(folder);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(null, fm.getFeed(feedName));
	}
	
	@Test
	public void getEntry_ValidEntry_Entry() {
		String folderName = "Noticias tecnológicas";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String entryName = "¡Feliz 2016!";
		Entry entry = new Entry(entryName, "AdminMeneame", "", new Date(), false);
		
		try {
			fm.addFolder(folder);
			feed.addEntry(entry);
			fm.getFolder(folderName).addFeed(feed);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(entry, fm.getEntry(entryName));
	}
	
	@Test
	public void getEntry_NotValidEntry_Null() {
		String folderName = "Noticias tecnológicas";
		folder = new Folder(folderName);
		String feedUrl = "";
		String feedName = "Barrapunto";
		feed = new Feed(feedName, feedUrl);
		String entryName = "¡Feliz 2016!";
		
		try {
			fm.addFolder(folder);
			fm.getFolder(folderName).addFeed(feed);
		} catch (NotAllowedOperationException e) {
			fail("Exception throwed");
			System.out.println(e);
		}
		
		assertEquals(null, fm.getEntry(entryName));
	}
	
}
