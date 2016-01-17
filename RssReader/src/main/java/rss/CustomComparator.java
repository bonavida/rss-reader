package rss;

import java.util.Comparator;

import model.Entry;

public class CustomComparator implements Comparator<Entry> {

	@Override
	public int compare(Entry o1, Entry o2) {
		return o2.getPublicationDate().compareTo(o1.getPublicationDate());
	}

}
